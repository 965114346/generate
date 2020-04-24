package com.zyy.generate.core;

import com.zyy.generate.dao.GenerateMapper;
import com.zyy.generate.pojo.Table;

/**
 * 适配器模式
 * @author yangyang
 */
public class GeneratorAdapter implements Adapter {

    private final GenerateMapper generateDao;

    private final Generator generator;

    public GeneratorAdapter(GenerateMapper dao, Generator generator) {
        this.generateDao = dao;
        this.generator = generator;
    }

    @Override
    public void adapter(String... tableNames) {
        for (String name : tableNames) {
            Table table = generateDao.queryTable(name);
            generator.gen(tableName);
        }

    }
}
