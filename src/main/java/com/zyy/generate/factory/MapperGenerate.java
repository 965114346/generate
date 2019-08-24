package com.zyy.generate.factory;

import com.zyy.generate.config.BeanConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MapperGenerate implements Generate {
    @Override
    public String getPackagePath(BeanConfig beanConfig) {
        return beanConfig.getMapperPackage();
    }

    @Override
    public String getTemplateName() {
        return "mapper.ftl";
    }

    @Override
    public String getName(BeanConfig beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getDaoNamePre(), beanName, beanConfig.getDaoNameSuf());
    }

    @Override
    public String getFileType() {
        return "xml";
    }
}
