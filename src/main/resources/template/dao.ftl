package ${daoGenerate@packagePath};

import ${beanGenerate@packagePath}.${beanGenerate@name};

import java.util.List;

/**
<#if tableComment??>
 * ${tableComment}
 *
</#if>
 * @author ${author}
 * @date ${.now}
 */
public interface ${daoGenerate@name} {

    /**
    * 根据id查询${tableComment}
    * @param id
    * @return
    */
    ${beanGenerate@name} selectByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName}</#if></#list> id);

    /**
    * 条件查询${tableComment}列表
    * @param ${beanVar}
    * @return
    */
    List<${beanGenerate@name}> selectByCondition(${beanGenerate@name} ${beanVar});

    /**
    * 插入${tableComment}
    * @param ${beanVar}
    * @return
    */
    boolean insertSelective(${beanGenerate@name} ${beanVar});

    /**
    * 更新${tableComment}
    * @param ${beanVar}
    * @return
    */
    boolean updateByPrimaryKey(${beanGenerate@name} ${beanVar});

    /**
    * 根据id删除${tableComment}
    * @param id
    * @return
    */
    boolean deleteByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName}</#if></#list> id);

    /**
    * 根据id批量删除${tableComment}
    * @param ids
    * @return
    */
    boolean batchDeleteByPrimaryKey(List<<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName}</#if></#list>> ids);
}