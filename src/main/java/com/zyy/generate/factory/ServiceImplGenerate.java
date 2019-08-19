package com.zyy.generate.factory;

import com.zyy.generate.config.BeanConfig;
import com.zyy.generate.util.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceImplGenerate implements Generate {
    @Override
    public String getPackagePath(BeanConfig beanConfig) {
        return StrUtils.getPackagePath(beanConfig.getServiceImplPackage(), beanConfig);
    }

    @Override
    public String getTemplateName() {
        return "serviceImpl.ftl";
    }

    @Override
    public String getName(BeanConfig beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getServiceImplNamePre(), beanName, beanConfig.getServiceImplNameSuf());
    }

    @Override
    public String getFileType() {
        return "java";
    }
}
