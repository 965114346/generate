package com.zyy.generate.core;

/**
 * @author yangyang
 */
public interface ColumnTypeConverter<T> extends ColumnTypeMapper<T> {

    /**
     * 转换值
     * @param columnType 数据库字段类型值
     * @return 转换后的类型
     */
    String convert(String columnType);
}
