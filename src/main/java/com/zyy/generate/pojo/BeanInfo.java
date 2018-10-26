package com.zyy.generate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeanInfo {

    /**
     * 表名
     */
    private String tableName;
    
    /**
     * JavaBean类名
     */
    private String beanName;
    
    private String name;
    
    /**
     * Dao接口名
     */
    private String beanDaoName;
    
    private String daoName;
    
    /**
     * Service接口名
     */
    private String beanServiceName;
    
    private String serviceName;
    
    /**
     * ServiceImpl类名
     */
    private String beanServiceImplName;
    
    /**
     * Controller类名
     */
    private String beanControllerName;
}
