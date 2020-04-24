package com.zyy.generate.custom;

import com.zyy.generate.config.DataMap;
import com.zyy.generate.core.Generator;
import com.zyy.generate.util.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author yangyang.zhang
 * @date 2019年08月17日22:36:09
 */
@Component
public class BeanGenerator implements Generator {

    @Override
    public String getTemplateName() {
        return "bean.ftl";
    }

    @Override
    public String getPackagePath(DataMap beanConfig) {
        return StrUtils.getPackagePath(beanConfig.getBeanPackage(), beanConfig);
    }

    @Override
    public String getName(DataMap beanConfig, String beanName) {
        return StringUtils.join(beanConfig.getBeanNamePre(), beanName, beanConfig.getBeanNameSuf());
    }

    @Override
    public String getFileType() {
        return "java";
    }
}
