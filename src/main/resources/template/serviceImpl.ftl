package ${serviceImplGenerate@packagePath};

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${beanGenerate@packagePath}.${beanGenerate@name};
import ${daoGenerate@packagePath}.${daoGenerate@name};
import ${serviceGenerate@packagePath}.${serviceGenerate@name};

@Service
public class ${serviceImplGenerate@name} implements ${serviceGenerate@name} {
    private static final Logger log = LoggerFactory.getLogger(${serviceImplGenerate@name}.class);

    @Autowired
    private ${daoGenerate@name} mapper;

    @Override
    public ${beanGenerate@name} selectByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.classSimpleName} ${beanField.name}</#if></#list>) {
        return mapper.selectByPrimaryKey(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.name}</#if></#list>);
    }

    @Override
    public PageInfo<${beanGenerate@name}> selectByPage(${beanGenerate@name} ${beanVar}, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<${beanGenerate@name}> list = mapper.selectByCondition(${beanVar});
        return new PageInfo<>(list);
    }

    @Override
    public List<${beanGenerate@name}> selectByCondition(${beanGenerate@name} ${beanVar}){
        return mapper.selectByCondition(${beanVar});
    }

    @Transactional
    @Override
    public Integer insert(${beanGenerate@name} ${beanVar}){
        return mapper.insertSelective(${beanVar});
    }

    @Transactional
    @Override
    public Integer updateByPrimaryKey(${beanGenerate@name} ${beanVar}){
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
