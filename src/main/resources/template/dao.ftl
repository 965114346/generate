package ${dao.path};

import ${bean.path}.${dao.table.upperCaseHumpName};

import java.util.List;

<#if dao.table.comment??>
/**
* ${dao.table.comment}
*/
</#if>
public interface ${dao.table.upperCaseHumpName}Dao {

    /**
    * 根据id查询${dao.table.comment}
    * @param id
    * @return
    */
    ${dao.table.upperCaseHumpName} selectByPrimaryKey(<#list dao.columnList as beanField><#if beanField.columnKey == "PRI">${beanField.convertType}</#if></#list> id);

    /**
    * 条件查询${dao.table.comment}列表
    * @param ${dao.table.humpName}
    * @return
    */
    List<${dao.table.upperCaseHumpName}> selectByCondition(${dao.table.upperCaseHumpName} ${dao.table.humpName});

    /**
    * 插入${dao.table.comment}
    * @param ${dao.table.humpName}
    * @return
    */
    boolean insertSelective(${dao.table.upperCaseHumpName} ${dao.table.humpName});

    /**
    * 更新${dao.table.comment}
    * @param ${dao.table.humpName}
    * @return
    */
    boolean updateByPrimaryKey(${dao.table.upperCaseHumpName} ${dao.table.humpName});

    /**
    * 根据id删除${dao.table.comment}
    * @param id
    * @return
    */
    boolean deleteByPrimaryKey(<#list dao.columnList as beanField><#if beanField.columnKey == "PRI">${beanField.convertType}</#if></#list> id);

    /**
    * 根据id批量删除${dao.table.comment}
    * @param ids
    * @return
    */
    Integer batchDeleteByPrimaryKey(List<<#list dao.columnList as beanField><#if beanField.columnKey == "PRI">${beanField.convertType}</#if></#list>> ids);
}