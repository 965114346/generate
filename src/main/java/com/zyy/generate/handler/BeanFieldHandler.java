package com.zyy.generate.handler;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;

import com.zyy.generate.pojo.BeanField;
import com.zyy.generate.util.StrUtils;

/**
 * query结果字段映射处理器
 */
public class BeanFieldHandler implements ResultSetHandler<List<BeanField>> {

    /**
     * mysql类型与java类型部分对应关系
     */
    private static Map<String, String> columnMapping = new HashMap<>();
    
    static {
        columnMapping.put("int", Integer.class.getSimpleName());
        columnMapping.put("tinyint", Integer.class.getSimpleName());
        columnMapping.put("double", Double.class.getSimpleName());
        columnMapping.put("float", Float.class.getSimpleName());
        columnMapping.put("decimal", BigDecimal.class.getSimpleName());
        columnMapping.put("date", Date.class.getSimpleName());
        columnMapping.put("timestamp", Date.class.getSimpleName());
        columnMapping.put("datetime", Date.class.getSimpleName());
        columnMapping.put("varchar", String.class.getSimpleName());
        columnMapping.put("text", String.class.getSimpleName());
        columnMapping.put("longtext", String.class.getSimpleName());
    }

    @Override
    public List<BeanField> handle(ResultSet rs) throws SQLException {
        List<BeanField> list = new ArrayList<>();
        while (rs.next()) {
            BeanField bf = new BeanField();
            bf.setColumnName(rs.getString("column_name"));
            bf.setColumnType(rs.getString("data_type"));
            bf.setColumnComment(rs.getString("column_comment"));
            bf.setColumnDefault(rs.getString("column_default"));
            
            bf.setName(StrUtils.str2hump(bf.getColumnName()));
            String type = columnMapping.get(bf.getColumnType());
            if (type == null) {
                type = String.class.getSimpleName();
            }
            bf.setType(type);
            
            // columnName为id的java类型为Long
            if ("id".equals(bf.getName())) {
                bf.setType(Long.class.getSimpleName());
            }
            
            bf.setColumnDefault(bf.getColumnDefault());
            list.add(bf);
        }
        return list;
    }

}
