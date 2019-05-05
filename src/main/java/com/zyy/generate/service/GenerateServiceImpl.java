package com.zyy.generate.service;

import com.zyy.generate.config.BeanConfig;
import com.zyy.generate.dao.GenerateMapper;
import com.zyy.generate.pojo.Column;
import com.zyy.generate.pojo.Table;
import com.zyy.generate.util.IOUtils;
import com.zyy.generate.util.StrUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author yangyang.zhang
 * @date 2019/3/31 16:45
 */
@Slf4j
@Service
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    private GenerateMapper generateDao;

    @Autowired
    private Configuration configuration;

    @Autowired
    @Qualifier("beanConfig")
    private BeanConfig beanConfig;

    @Override
    public void run(List<String> tableList) {
        for (String tableName : tableList) {
            run(tableName);
        }
    }

    @Override
    public void run(String tableName) {
        List<Column> columnList = getColumnList(tableName);
        Table table = generateDao.queryTable(tableName);
        String tableComment = table.getTableComment();
        log.info("======================>> tableComment: {}", tableComment);
        log.info("======================>> columnList  : {}", columnList);

        // 截取第一个下划线后的字符串   adv_picture  <--  rcg_adv_picture
        // String entity = StringUtils.substringAfter(tableName, "_");
        String entity = tableName;
        // advPicture  <--  adv_picture
        String beanVar = StrUtils.str2hump(entity);
        // 类名驼峰命名  AdvPicture  <-- adv_picture
        String bean = StringUtils.capitalize(beanVar);


        String beanName = StringUtils.join(beanConfig.getBeanNamePre(), bean, beanConfig.getBeanNameSuf());
        String beanPackage = getPackagePath(beanConfig.getBeanPackage());
        log.info("beanName: {}", beanName);
        String daoName = StringUtils.join(beanConfig.getDaoNamePre(), bean, beanConfig.getDaoNameSuf());
        String daoPackage = getPackagePath(beanConfig.getDaoPackage());
        log.info("daoName: {}", daoName);
        // 这里mapper.xml文件并不是Mapper接口名称
        String mapperName = beanVar;
        String mapperPackage = getPackagePath(beanConfig.getMapperPackage());
        log.info("mapperName: {}", mapperName);
        String serviceName = StringUtils.join(beanConfig.getServiceNamePre(), bean, beanConfig.getServiceNameSuf());
        String servicePackage = getPackagePath(beanConfig.getServicePackage());
        log.info("serviceName: {}", serviceName);
        String serviceImplName = StringUtils.join(beanConfig.getServiceImplNamePre(), bean, beanConfig.getServiceImplNameSuf());
        String serviceImplPackage = getPackagePath(beanConfig.getServiceImplPackage());
        log.info("serviceImplName: {}", serviceImplName);
        String controllerName = StringUtils.join(beanConfig.getControllerNamePre(), bean, beanConfig.getControllerNameSuf());
        String controllerPackage = getPackagePath(beanConfig.getControllerPackage());
        log.info("controllerName: {}", controllerName);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("author", beanConfig.getAuthor());
        dataMap.put("bean", bean);
        dataMap.put("beanVar", beanVar);
        dataMap.put("beanName", beanName);
        dataMap.put("beanPackage", beanPackage);
        dataMap.put("columnList", columnList);
        dataMap.put("tableComment", tableComment);
        dataMap.put("daoPackage", daoPackage);
        dataMap.put("daoName", daoName);
        dataMap.put("tableName", tableName);
        dataMap.put("serviceName", serviceName);
        dataMap.put("servicePackage", servicePackage);
        dataMap.put("serviceImplName", serviceImplName);
        dataMap.put("serviceImplPackage", serviceImplPackage);
        dataMap.put("controllerName", controllerName);
        dataMap.put("controllerPackage", controllerPackage);

        generateJava("bean.ftl", dataMap, beanName, beanPackage);
        generateJava("dao.ftl", dataMap, daoName, daoPackage);
        generateXml("mapper.ftl", dataMap, mapperName, mapperPackage);
        generateJava("service.ftl", dataMap, serviceName, servicePackage);
        generateJava("serviceImpl.ftl", dataMap, serviceImplName, serviceImplPackage);
        generateJava("controller.ftl", dataMap, controllerName, controllerPackage);
    }

    /**
     * mysql类型与java类型部分对应关系
     */
    private static Map<String, Class<?>> columnMapping = new HashMap<>();

    static {
        columnMapping.put("int", Integer.class);
        columnMapping.put("bigint", Long.class);
        columnMapping.put("tinyint", Integer.class);
        columnMapping.put("double", Double.class);
        columnMapping.put("float", Float.class);
        columnMapping.put("decimal", BigDecimal.class);
        columnMapping.put("date", Date.class);
        columnMapping.put("timestamp", Date.class);
        columnMapping.put("datetime", Date.class);
        columnMapping.put("varchar", String.class);
        columnMapping.put("char", String.class);
        columnMapping.put("text", String.class);
        columnMapping.put("longtext", String.class);
        columnMapping.put("mediumtext", String.class);
    }

    private List<Column> getColumnList(String tableName) {
        List<Column> columnList = generateDao.queryColumns(tableName);
        for (Column column: columnList) {
            log.info("column: {}", column);
            // java变量名称驼峰命名
            column.setName(StrUtils.str2hump(column.getColumnName()));
            column.setFirstWordUpperCase(StringUtils.capitalize(column.getName()));

            Class<?> aClass = columnMapping.get(column.getDataType());
            if (Objects.isNull(aClass)) {
                log.error("未匹配到映射类型: {}", column.getDataType());
                log.error("            表名: {}", tableName);
                throw new RuntimeException();
            }

            column.setClassName(aClass.getName());
            column.setClassSimpleName(aClass.getSimpleName());
        }
        return columnList;
    }

    /**
     * 获取ftl模板
     * @param name 模板名称
     * @return 模板对象
     * @throws IOException
     */
    private Template getTemplate(String name) throws IOException {
        return configuration.getTemplate(name);
    }

    private String getPackagePath(String name) {
        return StringUtils.join(Arrays.asList(beanConfig.getBasePackage(), name, beanConfig.getModelName()), ".");
    }

    /**
     * 生成文件
     * @param templateName 目标模板
     * @param dataMap 数据模型
     * @param fileName 文件名
     * @param fileType 生成文件类型
     */
    private void generate(String templateName, Map<String, Object> dataMap, String fileName, String filePath, String fileType) {
        Writer out = null;

        try{
            // 加载模板文件
            Template template = getTemplate(templateName);
            // 整合模板和数据
            File path = new File(StringUtils.join(beanConfig.getOutput(), "/", StringUtils.replace(filePath, ".", "/"), "/"));
            if (!path.exists()) {
                log.info("路径{}不存在,正在构建路径...", path);
                if (path.mkdirs()) {
                    log.info("路径构建成功!");
                } else {
                    log.info("路径构建失败!");
                }
            }
            File file = new File(path, StringUtils.join(fileName, ".", fileType));
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            // 输出文件
            template.process(dataMap, out);
            log.info("======================>> {}.{}文件创建", fileName, fileType);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(out);
        }
    }

    /**
     * 生成Java文件
     * @param templateName 目标模板
     * @param dataMap 数据模型
     * @param fileName 文件名
     */
    private void generateJava(String templateName, Map<String, Object> dataMap, String fileName, String filePath) {
        generate(templateName, dataMap, fileName, filePath, "java");
    }

    /**
     * 生成XML文件
     * @param templateName 目标模板
     * @param dataMap 数据模型
     * @param fileName 文件名
     */
    private void generateXml(String templateName, Map<String, Object> dataMap, String fileName, String filePath) {
        generate(templateName, dataMap, fileName + "Mapper", filePath,"xml");
    }
}
