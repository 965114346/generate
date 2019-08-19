package com.zyy.generate.factory;

import com.zyy.generate.config.BeanConfig;
import com.zyy.generate.util.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author yangyang.zhang
 * @date 2019年08月17日22:36:09
 */
public class BeanGenerate implements Generate {

    @Override
    public String getTemplateName() {
        return "bean.ftl";
    }

    @Override
    public String getPackagePath(BeanConfig beanConfig) {
        return StrUtils.getPackagePath(beanConfig.getBeanPackage(), beanConfig);
    }

    @Override
    public String getName(BeanConfig beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getBeanNamePre(), beanName, beanConfig.getBeanNameSuf());
    }

    @Override
    public String getFileType() {
        return "java";
    }
}
