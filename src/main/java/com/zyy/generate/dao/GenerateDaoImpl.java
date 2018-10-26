package com.zyy.generate.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbutils.QueryRunner;

import com.zyy.generate.handler.BeanFieldHandler;
import com.zyy.generate.pojo.BeanField;
import com.zyy.generate.util.DBUtils;

/**
 * 
 * GenerateDaoImpl
 */
@Slf4j
public class GenerateDaoImpl implements GenerateDao {

    @Override
    public List<BeanField> query(String tableName) {
        List<BeanField> list = new ArrayList<>();
        // 核心sql语句
        String sql = "select column_name, data_type, column_comment, column_default FROM information_schema.columns WHERE table_name= ? and table_schema = (select database())";
        QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
        try {
            log.debug("==>  Preparing: {}", sql);
            log.debug("==> Parameters: {}({})", tableName, tableName.getClass().getSimpleName());
            list = qr.query(sql, new BeanFieldHandler(), tableName);
            log.debug("<==      Total: {}", list.size());
        } catch (SQLException e) {
            log.error("SQLException : {}", e);
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("表" + tableName + "不存在");
        }
        return list;
    }

}
