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

    @Value("jdbc:mysql:///zebra?useUnicode=true&characterEncoding=utf8&useSSL=false")
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

        String[] tableList = {"t_user_base", "t_user_fund"};

        // 生成表
        config.setTableList(Arrays.asList(tableList));
        // 输出路径
        config.setOutput("output");
        // 模块名
        config.setModelName("login_and_register");

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
