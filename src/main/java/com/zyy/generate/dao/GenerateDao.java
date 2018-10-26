package com.zyy.generate.dao;

import java.util.List;

import com.zyy.generate.pojo.BeanField;

/**
 * 
 * GenerateDao
 */
public interface GenerateDao {

    List<BeanField> query(String tableName);
}
