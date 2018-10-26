package com.zyy.generate.util;

import org.apache.commons.dbutils.DbUtils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * 数据库工具类
 */
@Slf4j
public class DBUtils {
    
    /**
     * classpath路径
     */
    private static String path;
    
    /**
     * 数据源配置
     */
    private static HikariConfig config;
    
    /**
     * 数据源
     */
    @Getter
    private static HikariDataSource dataSource;
    
    static {
        path = DbUtils.class.getResource("/").getPath();
        
        log.info("classpath: {}", path);
        
        config = new HikariConfig(path + "hikari.properties");
        dataSource = new HikariDataSource(config);
    }
    
    /**
     * 默认generateConfig.properties配置文件，可指定文件名
     */
    public static HikariDataSource build(String propertyFileName) {
        config = new HikariConfig(path + propertyFileName);
        dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
