package com.zyy.generate.config;

import lombok.Data;

/**
 * @author yangyang.zhang
 * @date 2019/3/31 16:18
 */
@Data
public class BeanConfig {

    /**
     * 作者
     */
    private String author;

    /**
     * 模块名
     */
    private String modelName;

    /**
     * 基础包名称
     */
    private String basePackage;

    /**
     * bean包名称
     */
    private String beanPackage;

    /**
     * dao包名称
     */
    private String daoPackage;

    /**
     * mapper包名称
     */
    private String mapperPackage;

    /**
     * service包名称
     */
    private String servicePackage;

    /**
     * serviceImpl包名称
     */
    private String serviceImplPackage;

    /**
     * controller包名称
     */
    private String controllerPackage;

    /**
     * Bean名称前缀
     */
    private String beanNamePre;

    /**
     * Bean名称后缀
     */
    private String beanNameSuf;

    /**
     * Dao名称前缀
     */
    private String daoNamePre;

    /**
     * Dao名称后缀
     */
    private String daoNameSuf;

    /**
     * Service名称前缀
     */
    private String serviceNamePre;

    /**
     * Service名称后缀
     */
    private String serviceNameSuf;

    /**
     * ServiceImpl名称前缀
     */
    private String serviceImplNamePre;

    /**
     * ServiceImpl名称后缀
     */
    private String serviceImplNameSuf;

    /**
     * Controller名称前缀
     */
    private String controllerNamePre;

    /**
     * Controller名称后缀
     */
    private String controllerNameSuf;

    /**
     * 文件输出
     */
    private String output;
}
