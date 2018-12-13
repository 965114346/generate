package com.zyy.generate.handler;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    private static Map<String, Class<?>> columnMapping = new HashMap<>();
    
    static {
        columnMapping.put("int", Integer.class);
        columnMapping.put("bigint", Long.class);
        columnMapping.put("tinyint", Integer.class);
        columnMapping.put("double", Double.class);
        columnMapping.put("float", Float.class);
        columnMapping.put("decimal", BigDecimal.class);
        columnMapping.put("date", Date.class);
        columnMapping.put("timestamp", Date.class);
        columnMapping.put("datetime", Date.class);
        columnMapping.put("varchar", String.class);
        columnMapping.put("text", String.class);
        columnMapping.put("longtext", String.class);
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
            Class<?> aClass = columnMapping.get(bf.getColumnType());
            if (Objects.isNull(aClass)) {
                throw new NullPointerException("未匹配到映射类型" + bf.getColumnType());
            }

            String type = aClass.getSimpleName();
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
