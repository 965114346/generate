package com.zyy.generate.dao;

import com.zyy.generate.pojo.Column;
import com.zyy.generate.pojo.Table;

import java.util.List;

/**
 * @author yangyang.zhang
 */
public interface GenerateMapper {

    /**
     * 查询字段属性
     * @param tableName 表名称
     * @return 表字段集合
     */
    List<Column> queryColumns(String tableName);

    /**
     * 查询表属性
     * @param tableName 表名称
     * @return 表结构
     */
    Table queryTable(String tableName);
}
