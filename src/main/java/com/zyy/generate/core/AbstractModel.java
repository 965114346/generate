package com.zyy.generate.core;

import com.zyy.generate.pojo.Table;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangyang
 */
@Setter
public abstract class AbstractModel implements Model {

    protected String path;

    protected String templateName;

    protected String fileName;

    protected String fileType;

    /**
     * 设置此字段会忽略表前缀生成文件名称
     */
    protected String tablePre;

    protected String preString;

    protected String sufString;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }

    @Override
    public String getFileName(Table table) {

        if (StringUtils.isNotBlank(tablePre)) {
            fileName = StringUtils.substringAfter(fileName, tablePre);
        }

        return fileName;
    }

    @Override
    public String getFileType() {
        return fileType;
    }

    @Override
    public String getPreString() {
        return preString;
    }

    @Override
    public String getSufString() {
        return sufString;
    }

    @Override
    public String getTablePre() {
        return tablePre;
    }
}
