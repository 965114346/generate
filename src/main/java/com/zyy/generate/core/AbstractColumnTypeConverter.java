package com.zyy.generate.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangyang
 */
public abstract class AbstractColumnTypeConverter<T> implements ColumnTypeConverter<T> {

    protected final Map<String, T> columnTypeMapping = new HashMap<>();

    @Override
    public void addColumnTypeMapping(String columnType, T t) {
        columnTypeMapping.put(columnType, t);
    }
}
