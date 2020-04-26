package com.zyy.generate.core;

import java.util.List;

/**
 * @author yangyang
 */
public interface GeneratorSubject {

    /**
     * 注册生成者
     * @param generator
     */
    void registerGenerator(AbstractGenerator generator);

    /**
     * 注册生成者
     * @param generatorList
     */
    void registerAllGenerator(List<AbstractGenerator> generatorList);

    /**
     * 通知生成者生成
     */
    void notifyGenerators();
}
