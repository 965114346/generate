package com.zyy.generate.core;

import com.alibaba.fastjson.JSONObject;
import com.zyy.generate.dao.GenerateMapper;
import com.zyy.generate.pojo.Column;
import com.zyy.generate.pojo.Table;
import com.zyy.generate.util.GeneratorUtils;
import com.zyy.generate.util.StrUtils;
import freemarker.template.Configuration;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangyang
 */
@Slf4j
public abstract class AbstractGenerator implements Generator {

    private static final Map<String, Object> DATA_MAP = new HashMap<>();

    private static final Map<String, Table> TABLE_CACHE = new HashMap<>();

    private static final Map<String, List<Column>> COLUMN_CACHE = new HashMap<>();

    static {
        DATA_MAP.put("author", System.getProperty("user.name"));
    }

    @Setter
    protected Configuration configuration;

    @Setter
    protected GenerateMapper generateMapper;

    @Setter
    protected ColumnTypeConverter<?> columnTypeConverter = new DefaultStringColumnTypeConverter();

    @Override
    public void gen(String... tableNames) {
        for (String tableName : tableNames) {

            Table table = TABLE_CACHE.get(tableName);
            if (Objects.isNull(table)) {
                table = generateMapper.queryTable(tableName);
                TABLE_CACHE.put(tableName, table);
            }

            List<Column> columnList = COLUMN_CACHE.get(tableName);
            if (Objects.isNull(columnList)) {
                columnList = GeneratorUtils.getColumnList(generateMapper, this.columnTypeConverter, tableName);
                COLUMN_CACHE.put(tableName, columnList);
            }


            table.setHumpName(StrUtils.str2hump(table.getName()));
            table.setUpperCaseHumpName(StringUtils.capitalize(table.getHumpName()));

            JSONObject data = new JSONObject();
            data.put("columnList", columnList);
            data.put("table", table);
            data.put("path", path);

            DATA_MAP.put(name, data);
            log.info("name = {}", name);
            log.info("val = {}", data);
            GeneratorUtils.generate(configuration, templateName, DATA_MAP, StringUtils.join(preString, table.getUpperCaseHumpName(), subString), path, getType());
        }
    }

    /**
     * 生产者名称
     */
    protected String name;

    /**
     * 路径
     */
    protected String path;

    /**
     * 模板名称
     */
    protected String templateName;

    /**
     * 类型
     */
    @Getter
    protected String type;

    /**
     * 后缀
     */
    protected String subString;

    /**
     * 前缀
     */
    protected String preString;
}
