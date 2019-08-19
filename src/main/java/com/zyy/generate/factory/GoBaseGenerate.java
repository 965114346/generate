package com.zyy.generate.factory;

import com.zyy.generate.config.BeanConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class GoBaseGenerate implements Generate {
    @Override
    public String getPackagePath(BeanConfig beanConfig) {
        return beanConfig.getModelName();
    }

    @Override
    public String getTemplateName() {
        return "goBase.ftl";
    }

    @Override
    public String getName(BeanConfig beanConfig, String beanName) {
        return beanName;
    }

    @Override
    public String getFileType() {
        return "go";
    }
}
