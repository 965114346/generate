package com.zyy.generate.pojo;

import lombok.Data;

@Data
public class BeanField {

    /**
     * 数据库字段名称
     */
    private String columnName;

    /**
     * 数据库字段类型
     */
    private String columnType;

    /**
     * 数据库字段注释
     */
    private String columnComment;

    /**
     * 数据库字段默认值
     */
    private String columnDefault;

    /**
     * java字段名称
     */
    private String name;

    /**
     * java字段类型
     */
    private String type;

}
