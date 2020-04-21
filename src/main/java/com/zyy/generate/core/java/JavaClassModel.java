package com.zyy.generate.core.java;

import com.zyy.generate.core.AbstractModel;
import com.zyy.generate.core.Model;
import com.zyy.generate.pojo.Table;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangyang
 */
public class JavaClassModel extends AbstractModel {

    public JavaClassModel(String path, String templateName) {
        this.path = path;
        this.templateName = templateName;
    }

    public JavaClassModel(String path, String templateName, String tablePre) {
        this.path = path;
        this.templateName = templateName;
        this.tablePre = tablePre;
    }

    public JavaClassModel(String path, String templateName, String tablePre, String preString, String sufString) {
        this.path = path;
        this.templateName = templateName;
        this.tablePre = tablePre;
        this.preString = preString;
        this.sufString = sufString;
    }

    @Override
    public String getFileName(Table table) {
        fileName = table.getTableName();

        if (StringUtils.isNotBlank(tablePre)) {
            fileName = StringUtils.substringAfter(fileName, tablePre);
        }

        fileName = StringUtils.join(preString, fileName, sufString);
        return fileName;
    }

    @Override
    public String getFileType() {
        return Model.JAVA_TYPE;
    }
}
