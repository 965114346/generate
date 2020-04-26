package com.zyy.generate.core;

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
