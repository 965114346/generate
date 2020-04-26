package com.zyy.generate.core.java;

import com.zyy.generate.core.AbstractGenerator;

/**
 * @author yangyang
 */
public class JavaGenerator extends AbstractGenerator {

    public JavaGenerator(String name, String path, String templateName, String type, String subString, String preString) {
        this.name = name;
        this.path = path;
        this.templateName = templateName;
        this.type = type;
        this.subString = subString;
        this.preString = preString;
        this.columnTypeConverter = new JavaColumnTypeConverter();
    }
}
