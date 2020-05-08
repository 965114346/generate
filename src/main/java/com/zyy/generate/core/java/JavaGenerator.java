package com.zyy.generate.core.java;

import com.zyy.generate.core.AbstractGenerator;

/**
 * @author yangyang
 */
public class JavaGenerator extends AbstractGenerator {

    public JavaGenerator(String name, String path, String templateName, String subString, String preString) {
        this.name = name;
        this.path = path;
        this.templateName = templateName;
        this.subString = subString;
        this.preString = preString;
        this.columnTypeConverter = new JavaColumnTypeConverter();
    }

    @Override
    public String getType() {
        return "java";
    }
}
