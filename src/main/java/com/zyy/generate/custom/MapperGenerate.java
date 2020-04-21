package com.zyy.generate.custom;

import com.zyy.generate.config.DataMap;
import com.zyy.generate.core.Generate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MapperGenerate implements Generate {
    @Override
    public String getPackagePath(DataMap beanConfig) {
        return beanConfig.getMapperPackage();
    }

    @Override
    public String getTemplateName() {
        return "mapper.ftl";
    }

    @Override
    public String getName(DataMap beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getDaoNamePre(), beanName, beanConfig.getDaoNameSuf());
    }

    @Override
    public String getFileType() {
        return "xml";
    }
}
