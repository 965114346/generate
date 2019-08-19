package com.zyy.generate.factory;

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
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 代理生成
 * @author yangyang.zhang
 * @date 2019年08月19日13:34:29
 */
@Slf4j
@Component
public class GenerateProxy {

    @Autowired
    private GenerateMapper generateDao;

    @Autowired
    private GenerateFactoryBean generateFactoryBean;

    @Autowired
    private Configuration configuration;

    @Autowired
    private BeanConfig beanConfig;

    public void generate() {
        Map<String, Generate> generateMap = generateFactoryBean.getGenerateMap();
        List<Generate> generateList = generateMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

        List<String> tableList = beanConfig.getTableList();

        for (String tableName : tableList) {
            List<Column> columnList = getColumnList(tableName);
            Table table = generateDao.queryTable(tableName);
            String tableComment = table.getTableComment();
            log.info("======================>> tableComment: {}", tableComment);
            log.info("======================>> columnList  : {}", columnList);

            // 截取第一个下划线后的字符串   adv_picture  <--  rcg_adv_picture
            String entity = StringUtils.substringAfter(tableName, "_");
            //String entity = tableName;
            // advPicture  <--  adv_picture
            String beanVar = StrUtils.str2hump(entity);
            // 类名驼峰命名  AdvPicture  <-- adv_picture
            String bean = StringUtils.capitalize(beanVar);

            List<BaseGenerate> list = new ArrayList<>();
            Set<Map.Entry<String, Generate>> entrySet = generateMap.entrySet();
            for (Map.Entry<String, Generate> entry : entrySet) {
                String key = entry.getKey();
                Generate generate = entry.getValue();

                BaseGenerate baseGenerate = new BaseGenerate();
                baseGenerate.setPackagePath(generate.getPackagePath(beanConfig));
                baseGenerate.setName(generate.getName(beanConfig, bean));
                baseGenerate.setBeanId(key);
                baseGenerate.setFileType(generate.getFileType());
                baseGenerate.setTemplateName(generate.getTemplateName());

                list.add(baseGenerate);
            }

            Map<String, Object> dataMap = new HashMap<>();

            dataMap.put("author", beanConfig.getAuthor());
            dataMap.put("bean", bean);
            dataMap.put("beanVar", beanVar);
            dataMap.put("tableName", tableName);
            dataMap.put("columnList", columnList);
            dataMap.put("tableComment", tableComment);

            for (BaseGenerate baseGenerate: list) {
                String beanId = baseGenerate.getBeanId();
                dataMap.put(beanId + "@packagePath", baseGenerate.getPackagePath());
                dataMap.put(beanId + "@name", baseGenerate.getName());
            }

            log.info("dataMap: {}", dataMap);
            for (Generate generate : generateList) {
                generate(generate.getTemplateName(),
                        dataMap,
                        StringUtils.uncapitalize(generate.getName(beanConfig, bean)),
                        generate.getPackagePath(beanConfig),
                        generate.getFileType());
            }
        }

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
            Template template = configuration.getTemplate(templateName);
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
            log.error("{}", e);
        } finally {
            IOUtils.close(out);
        }
    }

    /**
     * mysql类型与java类型部分对应关系
     */
    private static Map<String, String> columnMapping = new HashMap<>();

    static {
        columnMapping.put("int", "int64");
        columnMapping.put("bigint", "int64");
        columnMapping.put("tinyint", "int");
        columnMapping.put("date", "string");
        columnMapping.put("datetime", "string");
        columnMapping.put("varchar", "string");
        columnMapping.put("char", "string");
        columnMapping.put("text", "string");
        columnMapping.put("longtext", "string");
        columnMapping.put("mediumtext", "string");
    }

    private List<Column> getColumnList(String tableName) {
        List<Column> columnList = generateDao.queryColumns(tableName);
        for (Column column: columnList) {
            log.info("column: {}", column);
            // java变量名称驼峰命名
            column.setName(StrUtils.str2hump(column.getColumnName()));
            column.setFirstWordUpperCase(StringUtils.capitalize(column.getName()));

            String aClass = columnMapping.get(column.getDataType());
            if (Objects.isNull(aClass)) {
                log.error("            表名: {}", tableName);
                log.error("        字段名称: {}", column.getColumnName());
                log.error("未匹配到映射类型: {}", column.getDataType());
                throw new RuntimeException();
            }

            column.setClassName(aClass);
            column.setClassSimpleName(aClass);
        }
        return columnList;
    }
}
