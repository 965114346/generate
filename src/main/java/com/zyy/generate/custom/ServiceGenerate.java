package com.zyy.generate.custom;

import com.zyy.generate.config.DataMap;
import com.zyy.generate.core.Generate;
import com.zyy.generate.util.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceGenerate implements Generate {

    @Override
    public String getPackagePath(DataMap beanConfig) {
        return StrUtils.getPackagePath(beanConfig.getServicePackage(), beanConfig);
    }

    @Override
    public String getTemplateName() {
        return "service.ftl";
    }

    @Override
    public String getName(DataMap beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getServiceNamePre(), beanName, beanConfig.getServiceNameSuf());
    }

    @Override
    public String getFileType() {
        return "java";
    }
}
