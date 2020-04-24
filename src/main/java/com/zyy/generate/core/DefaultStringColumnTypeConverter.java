package com.zyy.generate.core;

import lombok.extern.slf4j.Slf4j;
import java.util.Objects;

/**
 * @author yangyang
 */
@Slf4j
public class DefaultStringColumnTypeConverter extends AbstractColumnTypeConverter<String> {

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
