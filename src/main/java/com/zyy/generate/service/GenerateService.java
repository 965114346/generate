package com.zyy.generate.service;

import java.util.List;

/**
 * @author yangyang.zhang
 * @date 2019/3/31 16:45
 */
public interface GenerateService {

    /**
     * 单表
     * @param tableName
     */
    void run(String tableName);

    /**
     * 多表
     * @param tableList
     */
    void run(List<String> tableList);
}
