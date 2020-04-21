package com.zyy.generate.core;

import com.zyy.generate.pojo.Table;
import lombok.AllArgsConstructor;

/**
 * @author yangyang
 */
@AllArgsConstructor
public class DefaultModel extends AbstractModel {

    public DefaultModel(String path, String templateName, String fileName, String fileType) {
        this.path = path;
        this.templateName = templateName;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @Override
    public String getFileName(Table table) {
        return super.getFileName(table);
    }
}
