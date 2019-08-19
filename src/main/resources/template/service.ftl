package ${serviceGenerate@packagePath};

import java.util.List;

import com.github.pagehelper.PageInfo;
import ${beanGenerate@packagePath}.${beanGenerate@name};

public interface ${serviceGenerate@name} {

    /**
    * 根据id查询${tableComment}
    * @param <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}</#if></#list>
    * @return
    */
    ${beanGenerate@name} selectByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName} ${beanField.name}</#if></#list>);

    /**
    * 分页查询${tableComment}
    * @param ${beanVar}
    * @param pageNo
    * @param pageSize
    * @return
    */
    PageInfo<${beanGenerate@name}> selectByPage(${beanGenerate@name} ${beanVar}, Integer pageNo, Integer pageSize);

    /**
    * 不分页查询${tableComment}
    * @param ${beanVar}
    * @return
    */
    List<${beanGenerate@name}> selectByCondition(${beanGenerate@name} ${beanVar});

    /**
    * 添加${tableComment}
    * @param ${beanVar}
    * @return
    */
    Integer insert(${beanGenerate@name} ${beanVar});

    /**
    * 根据id更新${tableComment}
    * @param ${beanVar}
    * @return
    */
    Integer updateByPrimaryKey(${beanGenerate@name} ${beanVar});

    /**
    * 单个删除${tableComment}
    * @param <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}</#if></#list>
    * @return
    */
    Integer deleteByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName} ${beanField.name}</#if></#list>);

    /**
    * 批量删除${tableComment}(也可单个删除${tableComment})
    * @param <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}s</#if></#list>
    * @return
    */
    Integer batchDeleteByPrimaryKey(List<<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName}</#if></#list>> <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}s</#if></#list>);
}
