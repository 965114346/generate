package com.zyy.generate.core;

import com.zyy.generate.dao.GenerateMapper;
import com.zyy.generate.pojo.Column;
import com.zyy.generate.pojo.Table;

import java.util.List;

/**
 * @author yangyang
 */
public abstract class AbstractGeneratorSubject implements GeneratorSubject {

    protected GenerateMapper generateMapper;

    protected AbstractGeneratorSubject setGenerateMapper(GenerateMapper generateMapper) {
        this.generateMapper = generateMapper;
        return this;
    }

    protected List<Column> queryColumns(String tableName) {
        return generateMapper.queryColumns(tableName);
    }

    protected Table queryTable(String tableName) {
        return generateMapper.queryTable(tableName);
    }
}
