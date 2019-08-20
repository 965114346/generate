package com.zyy.generate.factory;

import com.zyy.generate.config.BeanConfig;
import com.zyy.generate.util.StrUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangyang.zhang
 * @date 2019年08月17日22:36:09
 */
public class DaoGenerate implements Generate {

    @Override
    public String getTemplateName() {
        return "dao.ftl";
    }

    @Override
    public String getPackagePath(BeanConfig beanConfig) {
        return StrUtils.getPackagePath(beanConfig.getDaoPackage(), beanConfig);
    }

    @Override
    public String getName(BeanConfig beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getDaoNamePre(), beanName, beanConfig.getDaoNameSuf());
    }

    @Override
    public String getFileType() {
        return "java";
    }
}
