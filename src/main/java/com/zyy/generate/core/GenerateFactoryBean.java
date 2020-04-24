package com.zyy.generate.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成工厂
 * @author yangyang.zhang
 * @date 2019年08月17日22:30:45
 */
@Component
public class GenerateFactoryBean {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, Generator> generateMap = new HashMap<>();

    @PostConstruct
    public void init() {
        this.generateMap = applicationContext.getBeansOfType(Generator.class);
    }

    /**
     * 获取生成器
     * @param generateName 生成器bean名称
     * @return 对应bean的生成器
     */
    public Generator getGenerate(String generateName) {
        return generateMap.get(generateName);
    }

    public Map<String, Generator> getGenerateMap() {
        return generateMap;
    }
}
