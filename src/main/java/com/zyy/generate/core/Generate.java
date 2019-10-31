package com.zyy.generate.core;

import com.zyy.generate.config.BeanConfig;

/**
 * @author yangyang.zhang
 * @date 2019年08月17日22:36:09
 */
public interface Generate {

    /**
     * 获取包路径
     * @param beanConfig 配置类
     * @return 包路径
     */
    String getPackagePath(BeanConfig beanConfig);

    /**
     * 获取模板名称
     * @return 模板名
     */
    String getTemplateName();

    /**
     *
     * 返回要生成的类的全名称
     * @param beanConfig 配置类
     * @param beanName 按数据库表名称驼峰 RcgAdvPicture  <--  rcg_adv_picture
     * @return 类名
     */
    String getName(BeanConfig beanConfig, String beanName);

    /**
     * 返回要生成的文件类型
     * @return 文件类型
     */
    String getFileType();
}
