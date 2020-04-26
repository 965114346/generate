package com.zyy.generate.core;

import com.zyy.generate.dao.GenerateMapper;
import freemarker.template.Configuration;
import lombok.Setter;

import java.util.*;

/**
 * 观察者模式
 * @author yangyang
 */
public class GeneratorProxy implements GeneratorSubject {

    @Setter
    private Configuration configuration;

    @Setter
    private GenerateMapper generateMapper;

    private final List<Generator> generatorMap = new ArrayList<>();

    private String[] tableNames;

    public GeneratorProxy(String... tableNames) {
        this.tableNames = tableNames;
    }

    public void setTableNames(String... tableNames) {
        this.tableNames = tableNames;
    }

    @Override
    public void registerGenerator(AbstractGenerator generator) {
        generator.setGenerateMapper(generateMapper);
        generator.setConfiguration(configuration);
        generatorMap.add(generator);
    }

    @Override
    public void registerAllGenerator(List<AbstractGenerator> registerList) {
        for (AbstractGenerator generator : registerList) {
            generator.setGenerateMapper(generateMapper);
            generator.setConfiguration(configuration);
        }
        generatorMap.addAll(registerList);
    }

    @Override
    public void notifyGenerators() {
        generatorMap.forEach(item -> {
            item.gen(tableNames);
        });
    }
}
