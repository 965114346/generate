package com.zyy.generate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zyy.generate.config.BeanConfig;
import com.zyy.generate.factory.GenerateProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 代码生成入口
 * @author yangyang.zhang
 */
@Slf4j
@Configuration
public class GenerateApplication {

    @Value("com.mysql.jdbc.Driver")
    private String driverClass;

    @Value("jdbc:mysql:///web3?useUnicode=true&characterEncoding=utf8&useSSL=false")
    private String jdbcUrl;

    @Value("root")
    private String user;

    @Value("root")
    private String password;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/application.xml");
        GenerateProxy bean = applicationContext.getBean(GenerateProxy.class);
        bean.generate();
        applicationContext.close();
    }

    /**
     * 包，名称生成配置
     * @return 配置类
     */
    @Bean
    public BeanConfig beanConfig() {
        BeanConfig config = new BeanConfig();

        String[] tableList = {"sys_template"};

        // 生成表
        config.setTableList(Arrays.asList(tableList));

        // 作者
        config.setAuthor("yangyang.zhang");
        // 输出路径
        config.setOutput("output");
        // 基础包
        config.setBasePackage("com.fh");
        // 模块名
        config.setModelName("");

        // bean包名，前后缀配置
        config.setBeanPackage("entity");
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

    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
