package com.zyy.generate.core;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author yangyang
 */
@Slf4j
public class DefaultStringColumnTypeConverter extends AbstractColumnTypeConverter<String> {

    /**
     * mysql类型与java类型部分对应关系
     */
    private static final Map<String, String> COLUMN_MAPPING = new HashMap<>();

    static {
        List<String> list = Arrays.asList("int", "mediumtext", "longtext", "text", "char", "varchar");
        for (String item : list) {
            COLUMN_MAPPING.put(item, "string");
        }
    }

    public DefaultStringColumnTypeConverter() {
        columnTypeMapping.putAll(COLUMN_MAPPING);
    }

    @Override
    public String convert(String columnType) {
        String convertResult = columnTypeMapping.get(columnType);
        if (Objects.isNull(convertResult)) {
            log.error("     未匹配到映射类型: {}", columnType);
        }

        return convertResult;
    }
}
