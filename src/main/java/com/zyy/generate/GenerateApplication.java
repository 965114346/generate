package com.zyy.generate;

import com.zyy.generate.config.ApplicationConfig;
import com.zyy.generate.config.DataMap;
import com.zyy.generate.core.*;
import com.zyy.generate.core.java.JavaClassModel;
import com.zyy.generate.custom.DaoGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 代码生成入口
 * @author yangyang.zhang
 */
@Slf4j
@Configuration
public class GenerateApplication {

    public static void main(String[] args) {
        GeneratorSubject proxy = new GeneratorProxy();
        proxy.registerGenerator(new DefaultGenerator("dao").setPathStrategy(""));
        proxy.notifyGenerators();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationConfig.class);
        GenerateProxy bean = applicationContext.getBean(GenerateProxy.class);
        bean.generate();

        ArrayList<AbstractStrategy> objects = new ArrayList<>();
        new JavaClassModel("com.zyy.pojo", "mapper");

        DataMap dataMap = new DataMap();
        dataMap.setModelList(objects);


        applicationContext.close();
    }

    /**
     * 包，名称生成配置
     * @return 配置类
     */
    @Bean
    public DataMap beanConfig() {
        DataMap config = new DataMap();

        String[] tableList = {"sys_menu"};

        // 生成表
        config.setTableList(Arrays.asList(tableList));

        // 作者
        config.setAuthor("yangyang.zhang");
        // 输出路径
        config.setOutput("output");
        // 基础包
        config.setBasePackage("com.zyy.lemon");
        // 模块名
        config.setModelName("");

        // bean包名，前后缀配置
        config.setBeanPackage("pojo");
        config.setBeanNamePre("");
        config.setBeanNameSuf("");

        // dao包名，前后缀配置
        config.setDaoPackage("dao");
        config.setDaoNamePre("");
        config.setDaoNameSuf("Mapper");

        // service包名，前后缀配置
        config.setServicePackage("service");
        config.setServiceNamePre("");
        config.setServiceNameSuf("Service");

        // serviceImpl包名，前后缀配置
        config.setServiceImplPackage("service");
        config.setServiceImplNamePre("");
        config.setServiceImplNameSuf("ServiceImpl");

        // controller包名，前后缀配置
        config.setControllerPackage("controller");
        config.setControllerNamePre("");
        config.setControllerNameSuf("Controller");

        // mapper包
        config.setMapperPackage("mapper");

        return config;
    }
}
