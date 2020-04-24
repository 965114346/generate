package com.zyy.generate.core;

import com.zyy.generate.pojo.Table;

/**
 * @author yangyang.zhang
 * @date 2019年08月17日22:36:09
 */
public interface Generator {

    String getTemplateName();

    String getPath();

    String getName();
}
