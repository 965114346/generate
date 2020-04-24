package com.zyy.generate.core;

import java.util.Map;

/**
 * @author yangyang
 */
public interface ColumnTypeMapper<T> {

    /**
     * 添加参数映射
     * @param columnType
     * @param t
     */
    void addColumnTypeMapping(String columnType, T t);
}
