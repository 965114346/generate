package com.zyy.generate.core;

import lombok.Data;

@Data
public class BaseGenerate {

    private String beanId;

    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 模板名称
     */
    private String templateName;

    private String name;

    /**
     * 文件类型
     */
    private String fileType;
}
