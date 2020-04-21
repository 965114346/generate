package com.zyy.generate.config;

import com.alibaba.fastjson.JSONObject;
import com.zyy.generate.core.Model;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author yangyang.zhang
 * @date 2019/3/31 16:18
 */
@Data
public class DataMap {

    /**
     * 作者
     */
    private String author;

    /**
     * 文件输出
     */
    private String output;

    /**
     * 表名称列表
     */
    private List<String> tableList;

    private List<Model> modelList;

    /**
     * 组
     */
    private Map<String, List<JSONObject>> groups;

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
}
