package com.zyy.generate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zyy.generate.core.AbstractGenerator;
import com.zyy.generate.core.GeneratorProxy;
import com.zyy.generate.core.GeneratorSubject;
import com.zyy.generate.core.java.JavaGenerator;
import com.zyy.generate.dao.GenerateMapper;
import com.zyy.generate.handler.DataSourceTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

/**
 * 代码生成入口
 * @author yangyang.zhang
 */
@Slf4j
@Configuration
@MapperScan(basePackages = "com.zyy.generate.dao")
@ComponentScan(basePackages = "com.zyy.generate")
public class GenerateApplication {

    @Value("com.mysql.jdbc.Driver")
    private String driverClass;

    @Value("jdbc:mysql://111.230.48.104:3306/web3?useUnicode=true&characterEncoding=utf8&useSSL=false")
    private String jdbcUrl;

    @Value("root")
    private String user;

    @Value("232511")
    private String password;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GenerateApplication.class);
        GeneratorProxy proxy = applicationContext.getBean(GeneratorProxy.class);

        AbstractGenerator generator = new JavaGenerator("bean", "com.zyy.entity", "bean.ftl", null, null);

        AbstractGenerator daoGenerator = new JavaGenerator("dao", "com.zyy.dao", "dao.ftl", "Dao", null);

        proxy.registerGenerator(generator);
        proxy.registerGenerator(daoGenerator);

        proxy.setTableNames("sys_log", "sys_user");
        proxy.notifyGenerators();

        applicationContext.close();
    }

    @Bean
    public GeneratorSubject generatorBuilder(freemarker.template.Configuration configuration, GenerateMapper mapper) {
        GeneratorProxy proxy = new GeneratorProxy();
        proxy.setConfiguration(configuration);
        proxy.setGenerateMapper(mapper);
        return proxy;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        // 配置mapper的扫描，找到所有的mapper.xml映射文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
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

    @Bean
    public freemarker.template.Configuration configuration(DataSourceTemplateLoader dataSourceTemplateLoader) throws IOException {
        //文件模板
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File("src/main/resources/template"));

        //聚合模板 按照顺序加载模板
        TemplateLoader[] templateLoaders = {dataSourceTemplateLoader, fileTemplateLoader};
        MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);

        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        configuration.setTemplateLoader(multiTemplateLoader);
        return configuration;
    }
}
