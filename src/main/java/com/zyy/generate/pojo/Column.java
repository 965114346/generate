package com.zyy.generate.pojo;

import lombok.Data;

/**
 * @author yangyang.zhang
 */
@Data
public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 驼峰名称
     */
    private String humpName;

    /**
     * 大写名称
     */
    private String upperCaseName;

    /**
     * 无下划线大写名称
     */
    private String noUnderlineUpperCaseName;

    /**
     * 表限定符
     */
    private String tableCatalog;

    /**
     * 表所有者/表格所属的库
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列标识号
     */
    private String ordinalPosition;

    /**
     * 列的默认值
     */
    private String columnDefault;

    /**
     * 列的为空性。如果列允许 NULL，那么该列返回 YES。否则，返回 NO
     * 字段是否可以是NULL。该列记录的值是YES或者NO。
     */
    private String isNullable;

    /**
     * 数据类型。
     * 里面的值是字符串，比如varchar，float，int。
     */
    private String dataType;

    /**
     * 字段的最大字符数。
     * 假如字段设置为varchar(50)，那么这一列记录的值就是50。
     * 该列只适用于二进制数据，字符，文本，图像数据。其他类型数据比如int，float，datetime等，在该列显示为NULL。
     */
    private String characterMaximumLength;

    /**
     * 字段的最大字节数。
     * 和最大字符数一样，只适用于二进制数据，字符，文本，图像数据，其他类型显示为NULL。
     * 和最大字符数的数值有比例关系，和字符集有关。比如UTF8类型的表，最大字节数就是最大字符数的3倍。
     */
    private String characterOcterLength;

    /**
     * 数字精度。
     * 适用于各种数字类型比如int，float之类的。
     * 如果字段设置为int(10)，那么在该列保存的数值是9，少一位，还没有研究原因。
     * 如果字段设置为float(10,3)，那么在该列报错的数值是10。
     * 非数字类型显示为在该列NULL。
     */
    private String numericPrecision;

    /**
     * 近似数字数据、精确数字数据、整型数据或货币数据的精度基数。否则，返回 NULL
     */
    private String numericPrecisionRadix;

    /**
     * 小数位数。
     * 和数字精度一样，适用于各种数字类型比如int，float之类。
     * 如果字段设置为int(10)，那么在该列保存的数值是0，代表没有小数。
     * 如果字段设置为float(10,3)，那么在该列报错的数值是3。
     * 非数字类型显示为在该列NULL。
     */
    private String numericScale;

    /**
     * datetime 及 SQL-92 interval 数据类型的子类型代码。对于其它数据类型，返回 NULL
     */
    private String datetimePrecision;

    /**
     * 字段字符集名称。比如utf8。
     */
    private String characterSetName;

    /**
     * 字符集排序规则。比如utf8_general_ci，是不区分大小写一种排序规则。utf8_general_cs，是区分大小写的排序规则
     */
    private String collationName;

    /**
     * 字段类型。比如float(9,3)，varchar(50)
     */
    private String columnType;

    /**
     * 索引类型
     */
    private String columnKey;

    /**
     * 其他信息。比如主键的auto_increment
     */
    private String extra;

    /**
     * 权限。多个权限用逗号隔开，比如 select,insert,update,references
     */
    private String privileges;

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 组合字段的公式
     */
    private String generationExpressic;
}
