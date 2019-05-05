package ${daoPackage};

import ${beanPackage}.${beanName};

import java.util.List;

<#if tableComment??>
/**
* ${tableComment}
*/
</#if>
public interface ${daoName} {

    /**
    * 根据id查询${tableComment}
    * @param id
    * @return
    */
    ${beanName} selectByPrimaryKey(Integer id);

    /**
    * 条件查询${tableComment}列表
    * @param ${beanVar}
    * @return
    */
    List<${beanName}> selectByCondition(${beanName} ${beanVar});

    /**
    * 插入${tableComment}
    * @param ${beanVar}
    * @return
    */
    Integer insertSelective(${beanName} ${beanVar});

    /**
    * 更新${tableComment}
    * @param ${beanVar}
    * @return
    */
    Integer updateByPrimaryKey(${beanName} ${beanVar});

    /**
    * 根据id删除${tableComment}
    * @param id
    * @return
    */
    Integer deleteByPrimaryKey(Integer id);

    /**
    * 根据id批量删除${tableComment}
    * @param ids
    * @return
    */
    Integer batchDeleteByPrimaryKey(List<Integer> ids);
}