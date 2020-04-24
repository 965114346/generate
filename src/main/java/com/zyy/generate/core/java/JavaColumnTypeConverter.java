package com.zyy.generate.core.java;

import com.zyy.generate.core.AbstractColumnTypeConverter;

import java.util.Objects;

/**
 * @author yangyang
 */
public class JavaColumnTypeConverter extends AbstractColumnTypeConverter<Class<?>> {

    @Override
    public String convert(String columnType) {
        String convertResult = columnTypeMapping.get(columnType);
        if (Objects.isNull(convertResult)) {
            log.error("未匹配到映射类型: {}", columnType);
            throw new RuntimeException();
        }

        return convertResult;
    }
}
