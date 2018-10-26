package com.zyy.generate.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateConfig {

    private List<String> tableNameList;
    
    private String beanPkgName;
    
    private String beanDaoPkgName;
    
    private String mapperPkgName;
    
    private String beanServicePkgName;
    
    private String beanControllerPkgName;
}
