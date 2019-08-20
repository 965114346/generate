package com.zyy.generate.factory;

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

    private Map<String, Generate> generateMap = new HashMap<>();

    @PostConstruct
    public void init() {
        this.generateMap = applicationContext.getBeansOfType(Generate.class);
    }

    /**
     * 获取自定义的生成器集合
     * @return 生成器
     */
    public Map<String, Generate> getGenerateMap() {
        return generateMap;
    }
}
