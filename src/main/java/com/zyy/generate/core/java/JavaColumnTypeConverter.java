package com.zyy.generate.core.java;

import com.zyy.generate.core.AbstractColumnTypeConverter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangyang
 */
@Slf4j
public class JavaColumnTypeConverter extends AbstractColumnTypeConverter<Class<?>> {

    /**
     * mysql类型与java类型部分对应关系
     */
    private static final Map<String, Class<?>> COLUMN_MAPPING = new HashMap<>();

    static {
        COLUMN_MAPPING.put("int", Integer.class);
        COLUMN_MAPPING.put("bigint", Long.class);
        COLUMN_MAPPING.put("tinyint", Integer.class);
        COLUMN_MAPPING.put("double", Double.class);
        COLUMN_MAPPING.put("float", Float.class);
        COLUMN_MAPPING.put("decimal", BigDecimal.class);
        COLUMN_MAPPING.put("date", Date.class);
        COLUMN_MAPPING.put("timestamp", Date.class);
        COLUMN_MAPPING.put("datetime", Date.class);
        COLUMN_MAPPING.put("varchar", String.class);
        COLUMN_MAPPING.put("char", String.class);
        COLUMN_MAPPING.put("text", String.class);
        COLUMN_MAPPING.put("longtext", String.class);
        COLUMN_MAPPING.put("mediumtext", String.class);
    }

    public JavaColumnTypeConverter() {
        columnTypeMapping.putAll(COLUMN_MAPPING);
    }

    @Override
    public String convert(String columnType) {
        Class<?> aClass = columnTypeMapping.get(columnType);

        if (Objects.isNull(aClass)) {
            log.error("     未匹配到映射类型: {}", columnType);
            throw new RuntimeException();
        }

        return aClass.getSimpleName();
    }
}
