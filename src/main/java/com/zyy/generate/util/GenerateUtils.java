package com.zyy.generate.util;

import java.math.BigDecimal;
import java.util.*;

import com.zyy.generate.GenerateApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.zyy.generate.dao.GenerateDao;
import com.zyy.generate.dao.GenerateDaoImpl;
import com.zyy.generate.pojo.BeanField;
import com.zyy.generate.pojo.BeanInfo;
import com.zyy.generate.pojo.GenerateConfig;

@Slf4j
public class GenerateUtils {

    private static String path;
    
    private static List<String> tableNameList;
    
    private static String mapperPkgName;
    
    private static String htmlPkgName;
    
    private static String beanPkgName;
    
    private static String enumPkgName;
    
    private static String beanDaoPkgName;
    
    private static String beanServicePkgName;
    
    private static String beanControllerPkgName;
    
    private static Map<String, List<BeanField>> BeanFieldMap = new HashMap<>();

    private static GenerateDao generateDao = new GenerateDaoImpl();
    
    static {
        log.info("loading...");
        path = GenerateApplication.class.getResource("/").getPath() + "file/";
        ResourceBundle bundle = ResourceBundle.getBundle("generateConfig");
        
        String basePackage = bundle.getString("generateConfig.basePackage");
        mapperPkgName = bundle.getString("generateConfig.mapperPackage");
        htmlPkgName = bundle.getString("generateConfig.htmlPackage");
        
        beanPkgName = basePackage + ".pojo";
        enumPkgName = basePackage + ".enums";
        beanDaoPkgName = basePackage + ".mapper";
        beanServicePkgName = basePackage + ".service";
        beanControllerPkgName = basePackage + ".controller";

        String tableNames = bundle.getString("generateConfig.tableNames");
        if (tableNames.contains(",")) {
            String[] split = tableNames.split(",");
            tableNameList = Arrays.asList(split);
        } else {
            tableNameList = Collections.singletonList(tableNames);
        }
        log.info("loading finished.");
        log.info("generateConfig : {}", new GenerateConfig(tableNameList, beanPkgName, beanDaoPkgName, mapperPkgName, beanServicePkgName, beanControllerPkgName));
        log.info("generatePath : {}", path);
    }

    /**
     * Generate All
     */
    public static void generate() {
        Map<String, String[]> map = new HashMap<>();
        // 模板名, [包名, 类名]
        map.put("baseMapper.txt", new String[]{beanDaoPkgName, "BaseMapper"});
        map.put("baseService.txt", new String[]{beanServicePkgName, "BaseService"});
        map.put("result.txt", new String[]{beanPkgName, "Result"});
        map.put("resultEnum.txt", new String[]{enumPkgName, "ResultEnum"});
        map.put("pageBean.txt", new String[]{beanPkgName, "PageBean"});
        map.put("pageResult.txt", new String[]{beanPkgName, "PageResult"});
        generateBase(map);
        tableNameList.forEach(tableName -> generateBean(getBeanInfo(tableName)));
    }
    
    private static BeanInfo getBeanInfo(String tableName) {
        String name = StrUtils.str2hump(tableName);
        String beanName = StrUtils.upperFirstChar(tableName);
        return new BeanInfo(tableName, 
                beanName, 
                name, 
                beanName + "Mapper", 
                name + "Mapper", 
                beanName + "Service", 
                name + "Service", 
                beanName + "ServiceImpl", 
                beanName + "Controller");
    }
    
    private static String getTemplete(String fileName, BeanInfo beanInfo) {
        String text = getTemplete(fileName);
        text = text.replace("{table_name}", beanInfo.getTableName());
        text = text.replace("{beanName}", beanInfo.getBeanName());
        text = text.replace("{bean}", beanInfo.getName());
        text = text.replace("{beanPackageName}", beanPkgName);
        text = text.replace("{enumPackageName}", enumPkgName);
        text = text.replace("{daoName}", beanInfo.getBeanDaoName());
        text = text.replace("{dao}", beanInfo.getDaoName());
        text = text.replace("{daoPackageName}", beanDaoPkgName);
        text = text.replace("{serviceName}", beanInfo.getBeanServiceName());
        text = text.replace("{service}", beanInfo.getServiceName());
        text = text.replace("{servicePackageName}", beanServicePkgName);
        text = text.replace("{serviceImplName}", beanInfo.getBeanServiceImplName());
        text = text.replace("{serviceImplPackageName}", beanServicePkgName + ".impl");
        String lowerFirstChar = StrUtils.lowerFirstChar(beanInfo.getBeanName());
        if (lowerFirstChar.endsWith("s")) {
            text = text.replace("{beanParamName}", lowerFirstChar.substring(0, lowerFirstChar.length()-1));
        } else {
            text = text.replace("{beanParamName}", lowerFirstChar);
        }
        text = text.replace("{controllerPkgName}", beanControllerPkgName);
        text = text.replace("{controllerName}", beanInfo.getBeanControllerName());
        return text;
    }

    /**
     * 获取生成模板内容   generate文件夹下
     * @param fileName  模板文件名
     */
    private static String getTemplete(String fileName) {
        return FileUtils.getText(GenerateUtils.class.getClassLoader().getResourceAsStream("generate/" + fileName));
    }
    
    /**
     * Generate Base
     */
    private static void generateBase(Map<String, String[]> map) {
        map.forEach((k, v) -> {
            String text = getTemplete(k);
            text = text.replace("{beanPackageName}", beanPkgName);
            text = text.replace("{enumPackageName}", enumPkgName);
            text = text.replace("{daoPackageName}", beanDaoPkgName);
            text = text.replace("{servicePackageName}", beanServicePkgName);
            FileUtils.saveTextFile(text, path + getPackagePath(v[0]) + v[1] + ".java");
            log.info("生成" +v[1] + " model：{}模板", v[1]);
        });
    }
    
    /**
     * Generate single JavaBean
     */
    private static void generateBean(BeanInfo beanInfo) {
        String text = getTemplete("bean.txt", beanInfo);
        
        List<BeanField> beanFields = getBeanFields(beanInfo.getTableName());
        List<String> beanFieldTypes = new ArrayList<>(beanFields.size());
        beanFields.forEach(beanField -> beanFieldTypes.add(beanField.getType()));

        String imports = "";
        if (beanFieldTypes.contains(BigDecimal.class.getSimpleName())) {
            imports += "import " + BigDecimal.class.getName() + ";\n";
        }
        if (beanFieldTypes.contains(Date.class.getSimpleName())) {
            imports += "import " + Date.class.getName() + ";\n";
        }
        text = text.replace("{import}", imports);

        String filelds = getFields(beanFields);
        text = text.replace("{filelds}", filelds);

        FileUtils.saveTextFile(text, path + getPackagePath(beanPkgName) + beanInfo.getBeanName() + ".java");
        log.info("生成bean model：{}模板", beanInfo.getBeanName());
        
        generateDao(beanInfo);
    }
    
    /**
     * Generate Dao
     */
    private static void generateDao(BeanInfo beanInfo) {
        String text = getTemplete("mapper.txt", beanInfo);
        
        FileUtils.saveTextFile(text, path + getPackagePath(beanDaoPkgName) + beanInfo.getBeanDaoName() + ".java");
        log.info("生成dao model：{}模板", beanInfo.getBeanDaoName());
        
        generateMapper(beanInfo);
    }
    
    /**
     * Generate Mapper
     */
    private static void generateMapper(BeanInfo beanInfo) {
        String text = getTemplete("mapper.xml", beanInfo);
        
        StringBuilder columnNames = new StringBuilder();
        StringBuilder resultMap = new StringBuilder();
        StringBuilder insertColumns = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        StringBuilder updateSets = new StringBuilder();
        StringBuilder where = new StringBuilder();
        
        List<BeanField> list = getBeanFields(beanInfo.getTableName());
        list.forEach(beanField -> {
            /* columnNames */
            columnNames.append(",").append("\n\t\t").append(beanField.getColumnName());

            /* resultMap */
            resultMap.append("\t\t<result column=\"").append(beanField.getColumnName()).append("\" property=\"").append(beanField.getName()).append("\" />");
            resultMap.append("\n");
            
            /* insertColumns,insertValues */
            if (!Objects.equals("id", beanField.getColumnName())) {
                insertColumns.append(beanField.getColumnName()).append(", ");
                insertValues.append("#{").append(beanField.getColumnName()).append("}, ");
            }
            
            /* updateSets */
            if (!Objects.equals("id", beanField.getColumnName())) {
                updateSets.append("\t\t\t<if test=\"").append(beanField.getName()).append(" != null\">\n");
                updateSets.append("\t\t\t\t").append(beanField.getColumnName()).append(" = #{").append(beanField.getName());
                if (list.indexOf(beanField) == (list.size()-1)) {
                    updateSets.append("} \n");
                } else {
                    updateSets.append("}, \n");
                }
                updateSets.append("\t\t\t</if>\n");
            }

            /* where */
            where.append("\t\t\t\t<if test=\"params.").append(beanField.getColumnName()).append(" != null");
            if (Objects.equals(beanField.getType(), String.class.getSimpleName())) {
                where.append(" and params.").append(beanField.getColumnName()).append(" != ''\">\n");
            } else {
                where.append("\">\n");
            }
            where.append("\t\t\t\t\tand ").append(beanField.getColumnName()).append(" = ").append("#{params.").append(beanField.getName())
                            .append("} \n");
            where.append("\t\t\t\t</if>\n");
        });
        
        text = text.replace("{columnNames}", columnNames.substring(1,columnNames.length()));
        text = text.replace("{resultMap}", resultMap.toString());
        text = text.replace("{insert_columns}", StringUtils.substringBeforeLast(insertColumns.toString(), ","));
        text = text.replace("{insert_values}", StringUtils.substringBeforeLast(insertValues.toString(), ","));
        text = text.replace("{update_sets}", updateSets.toString());
        text = text.replace("{where}", where.toString());
        
        FileUtils.saveTextFile(text, path + getPackagePath(mapperPkgName) + beanInfo.getBeanDaoName() + ".xml");
        log.info("生成Mapper model：{} 模板", beanInfo.getBeanDaoName());
        
        generateService(beanInfo);
    }
    
    /**
     * Generate Service
     */
    private static void generateService(BeanInfo beanInfo) {
        String text = getTemplete("service.txt", beanInfo);
        
        FileUtils.saveTextFile(text, path + getPackagePath(beanServicePkgName) + beanInfo.getBeanServiceName() + ".java");
        log.info("生成service model：{}模板", beanInfo.getBeanServiceName());
        
        generateServiceImpl(beanInfo);
    }
    
    /**
     * Generate ServiceImpl
     */
    private static void generateServiceImpl(BeanInfo beanInfo) {
        String text = getTemplete("serviceImpl.txt", beanInfo);
        
        FileUtils.saveTextFile(text, path + getPackagePath(beanServicePkgName) + "impl/" + beanInfo.getBeanServiceImplName() + ".java");
        log.info("生成serviceImpl model：{}模板", beanInfo.getBeanServiceImplName());
        
        generateController(beanInfo);
    }
    
    /**
     * Generate Controller
     */
    private static void generateController(BeanInfo beanInfo) {
        String text = getTemplete("controller.txt", beanInfo);
        
        FileUtils.saveTextFile(text, path + getPackagePath(beanControllerPkgName) + beanInfo.getBeanControllerName() + ".java");
        log.info("生成controller model：{}模板", beanInfo.getBeanControllerName());
        
        generateHTML(beanInfo);
    }
    
    /**
     * Generate Controller
     */
    private static void generateHTML(BeanInfo beanInfo) {
        String text = getTemplete("html.txt", beanInfo);
        
        StringBuilder modal = new StringBuilder();
        StringBuilder table = new StringBuilder();
        getBeanFields(beanInfo.getTableName()).forEach(beanField -> {
            String name = beanField.getName();
            String tab = "\t\t\t\t\t\t\t";
            
            modal.append(tab).append("<div class=\"form-group\">").append("\n")
                .append(tab).append("\t<label for=\"").append(name).append("\" class=\"col-sm-2 control-label\">").append(name).append("</label>").append("\n")
                .append(tab).append("\t<div class=\"col-sm-10\">").append("\n")
                .append(tab).append("\t\t<input id=\"").append(name).append("\" name=\"").append(name).append("\" type=\"text\" class=\"form-control\" placeholder=\"").append(name).append("\">").append("\n")
                .append(tab).append("\t</div>").append("\n")
                .append(tab).append("</div>").append("\n");
            
            table.append("{\n\t\t\t\t\tfield: \'").append(name).append("\',\n\t\t\t\t\ttitle: \'").append(name).append("\'\n\t\t\t\t}, ");
        });

        text = text.replace("{modal}", modal.toString());
        text = text.replace("{table}", table.toString());
        
        FileUtils.saveTextFile(text, path + getPackagePath(htmlPkgName) + beanInfo.getName() + ".html");
        log.info("生成html model：{}模板", beanInfo.getName());
    }

    private static List<BeanField> getBeanFields(String tableName) {
        List<BeanField> list = BeanFieldMap.get(tableName);
        if (Objects.isNull(list) || list.isEmpty()) {
            list = generateDao.query(tableName);
            BeanFieldMap.put(tableName, list);
        }
        return list;
    }

    /**
     * 获取属性
     */
    private static String getFields(List<BeanField> beanFields) {
        StringBuilder sb = new StringBuilder();

        beanFields.forEach(beanfield -> {
            boolean hasComment = StringUtils.isNotEmpty(beanfield.getColumnComment());
            
            if (hasComment) {
                sb.append("    /**").append("\n");
                sb.append("     * ").append(beanfield.getColumnComment()).append("\n");
                sb.append("     */").append("\n");
            }
            sb.append("    private ").append(beanfield.getType()).append(" ").append(beanfield.getName());
            sb.append(";\n\n");
        });

        return sb.toString();
    }

    /**
     * 由包名获取包路径
     */
    private static String getPackagePath(String packageName) {
        String packagePath = packageName.replace(".", "/");
        if (!packagePath.endsWith("/")) {
            packagePath = packagePath + "/";
        }

        return packagePath;
    }
}
