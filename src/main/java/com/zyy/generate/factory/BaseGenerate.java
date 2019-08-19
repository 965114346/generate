package com.zyy.generate.factory;

import lombok.Data;

@Data
public class BaseGenerate {

    private String beanId;

    private String packagePath;

    private String templateName;

    private String name;

    private String fileType;
}
