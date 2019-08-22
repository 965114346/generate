package com.zyy.generate.factory;

import com.zyy.generate.config.BeanConfig;
import org.springframework.stereotype.Component;

@Component
public class GoModelGenerate implements Generate {
    @Override
    public String getPackagePath(BeanConfig beanConfig) {
        return beanConfig.getModelName();
    }

    @Override
    public String getTemplateName() {
        return "goModel.ftl";
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
