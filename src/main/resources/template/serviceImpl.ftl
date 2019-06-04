package ${serviceImplPackage};

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${beanPackage}.${beanName};
import ${daoPackage}.${daoName};
import ${servicePackage}.${serviceName};

@Service
public class ${serviceImplName} implements ${serviceName} {
    private static final Logger log = LoggerFactory.getLogger(${serviceImplName}.class);

    @Autowired
    private ${daoName} mapper;

    @Override
    public ${beanName} selectByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName} ${beanField.name}</#if></#list>) {
        return mapper.selectByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}</#if></#list>);
    }

    @Override
    public PageInfo<${beanName}> selectByPage(${beanName} ${beanVar}, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<${beanName}> list = mapper.selectByCondition(${beanVar});
        return new PageInfo<>(list);
    }

    @Override
    public List<${beanName}> selectByCondition(${beanName} ${beanVar}){
        return mapper.selectByCondition(${beanVar});
    }

    @Transactional
    @Override
    public Integer insert(${beanName} ${beanVar}){
        return mapper.insertSelective(${beanVar});
    }

    @Transactional
    @Override
    public Integer updateByPrimaryKey(${beanName} ${beanVar}){
        return mapper.updateByPrimaryKey(${beanVar});
    }

    @Transactional
    @Override
    public Integer deleteByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName} ${beanField.name}</#if></#list>) {
        return mapper.deleteByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}</#if></#list>);
    }

    @Transactional
    @Override
    public Integer batchDeleteByPrimaryKey(List<<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName}</#if></#list>> <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}s</#if></#list>) {
        return mapper.batchDeleteByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}s</#if></#list>);
    }

}
