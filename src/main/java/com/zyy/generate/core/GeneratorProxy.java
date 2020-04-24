package com.zyy.generate.core;

import com.zyy.generate.dao.GenerateMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * @author yangyang
 */
public class GeneratorProxy implements GeneratorSubject {

    private final List<Generator> generatorList = new ArrayList<>();

    private GenerateMapper generateMapper;

    private final String[] list;

    public GeneratorProxy(String... tableName) {
        this.list = tableName;
    }

    public void setGenerateMapper(GenerateMapper generateMapper) {
        this.generateMapper = generateMapper;
    }

    @Override
    public void registerGenerator(Generator generator) {
        generatorList.add(generator);
    }

    @Override
    public void registerAllGenerator(List<Generator> registerList) {
        generatorList.addAll(registerList);
    }

    @Override
    public void notifyGenerators() {
        for (Generator generator : generatorList) {
            GeneratorAdapter generatorAdapter = new GeneratorAdapter(generateMapper, generator);
            generatorAdapter.adapter(list);
        }
    }
}
