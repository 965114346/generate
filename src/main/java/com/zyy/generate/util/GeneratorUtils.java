package com.zyy.generate.util;

import com.alibaba.fastjson.JSONObject;
import com.zyy.generate.core.ColumnTypeConverter;
import com.zyy.generate.dao.GenerateMapper;
import com.zyy.generate.pojo.Column;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangyang
 */
@Slf4j
public class GeneratorUtils {

    public static List<Column> getColumnList(GenerateMapper generateMapper, ColumnTypeConverter<?> columnTypeConverter, String tableName) {
        List<Column> columnList = generateMapper.queryColumns(tableName);
        for (Column column: columnList) {
            log.info("column: {}", column);
            // java变量名称驼峰命名
            column.setHumpName(StrUtils.str2hump(column.getName()));
            column.setUpperCaseHumpName(StringUtils.capitalize(column.getHumpName()));

            String convert = columnTypeConverter.convert(column.getDataType());
            if (Objects.isNull(convert)) {
                log.error("     表名: {}", tableName);
                log.error("     字段名称: {}", column.getName());
                throw new RuntimeException();
            }

            column.setConvertType(convert);
            //column.setClassName(aClass.getName());
            //column.setClassSimpleName(aClass.getSimpleName());
        }
        return columnList;
    }

    /**
     * 生成文件
     * @param templateName 目标模板
     * @param dataMap 数据模型
     * @param fileName 文件名
     * @param fileType 生成文件类型
     */
    public static void generate(Configuration configuration, String templateName, Map<String, Object> dataMap, String fileName, String filePath, String fileType) {
        Writer out = null;

        try{
            // 加载模板文件
            Template template = configuration.getTemplate(templateName);
            // 整合模板和数据
            File path = new File(StringUtils.join(StringUtils.replace(filePath, ".", "/"), "/"));
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
}
