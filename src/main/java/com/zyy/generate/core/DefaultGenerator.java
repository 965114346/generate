package com.zyy.generate.core;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyang
 */
@Slf4j
public class DefaultGenerator extends AbstractGenerator {

    public DefaultGenerator(String name, String path, String templateName, String type) {
        this.name = name;
        this.path = path;
        this.templateName = templateName;
        this.type = type;
    }
}
