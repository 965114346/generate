package com.zyy.generate.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author yangyang
 */
@Configuration
@ComponentScan(basePackages = "com.zyy.generate")
public class ApplicationConfig {

    @Value("com.mysql.jdbc.Driver")
    private String driverClass;

    @Value("jdbc:mysql:///web3?useUnicode=true&characterEncoding=utf8&useSSL=false")
    private String jdbcUrl;

    @Value("root")
    private String user;

    @Value("232511")
    private String password;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        // 配置mapper的扫描，找到所有的mapper.xml映射文件
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/*.xml");
        bean.setMapperLocations(resources);
        return bean;
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
