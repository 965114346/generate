package com.zyy.generate.core;

import com.zyy.generate.pojo.Table;

/**
 * @author yangyang
 */
public interface Model {

    String JAVA_TYPE = "java";

    /**
     * 获取包路径
     * @return 包路径
     */
    String getPath();

    /**
     * 获取模板名称
     * @return 模板名
     */
    String getTemplateName();

    /**
     *
     * 返回要生成的类的全名称
     * @param table
     * @return 类名
     */
    String getFileName(Table table);

    /**
     * 返回要生成的文件类型
     * @return 文件类型
     */
    String getFileType();

    /**
     * 获取前缀
     * @return
     */
    String getPreString();

    /**
     * 获取后缀
     * @return
     */
    String getSufString();

    /**
     * 获取表前缀
     * @return
     */
    String getTablePre();
}
