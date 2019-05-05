package ${servicePackage};

import com.alibaba.fastjson.JSONObject;
import ${beanPackage}.${beanName};
import com.github.pagehelper.PageInfo;

public interface ${serviceName} {

    /**
    * 根据id查询${tableComment}
    * @param id
    * @return
    */
    ${beanName} selectById(Integer id);

    /**
    * 分页查询${tableComment}
    * @param ${beanVar}
    * @param pageNo
    * @param pageSize
    * @return
    */
    PageInfo<${beanName}> selectByPage(${beanName} ${beanVar}, Integer pageNo, Integer pageSize);

    /**
    * 不分页查询${tableComment}
    * @param ${beanVar}
    * @return
    */
    List<${beanName}> selectByCondition(${beanName} ${beanVar});

    /**
    * 添加${tableComment}
    * @param ${beanVar}
    * @return
    */
    JSONObject insert(${beanName} ${beanVar});

    /**
    * 根据id更新${tableComment}
    * @param ${beanVar}
    * @return
    */
    JSONObject updateById(${beanName} ${beanVar});

    /**
    * 单个删除${tableComment}
    * @param id
    * @return
    */
    JSONObject deleteById(Integer id);

    /**
    * 批量删除${tableComment}(也可单个删除${tableComment})
    * @param ids ${tableComment}ID字符串用逗号进行分割
    * @return
    */
    JSONObject batchDeleteById(List<Integer> ids);
}
