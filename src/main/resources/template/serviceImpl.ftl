package ${serviceImplPackage};

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${beanPackage}.${beanName};
import ${daoPackage}.${daoName};
import ${servicePackage}.${serviceName};
import java.util.List;

public class ${serviceImplName} implements ${serviceName} {

    private static final Logger log = LoggerFactory.getLogger(${serviceImplName}.class);

    @Override
    public ${beanName} selectById(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list> id) {
        try (SqlSession session = DBFactory.instance().getSession()){
            ${daoName} mapper = session.getMapper(${daoName}.class);
            return mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @Override
    public PageInfo<${beanName}> selectByPage(${beanName} ${beanVar}, Integer pageNo, Integer pageSize){
        try (SqlSession session = DBFactory.instance().getSession()){
            ${daoName} mapper = session.getMapper(${daoName}.class);
            PageHelper.startPage(pageNo,pageSize);
            List<${beanName}> list = mapper.selectByCondition(${beanVar});
            return new PageInfo<>(list);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @Override
    public List<${beanName}> selectByCondition(${beanName} ${beanVar}){
        try (SqlSession session = DBFactory.instance().getSession()){
            ${daoName} mapper = session.getMapper(${daoName}.class);
            return mapper.selectByCondition(${beanVar});
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @Override
    public JSONObject insert(${beanName} ${beanVar}){
        SqlSession session = DBFactory.instance().getSession();
        try {
            ${daoName} mapper = session.getMapper(${daoName}.class);
            if (mapper.insertSelective(${beanVar}) > 0) {
                session.commit();
                return StringUtils.formatSuccessJSON("添加成功");
            }
        } catch (Exception e) {
            log.error("{}", e);
            session.rollback();
        } finally {
            session.close();
        }
        return StringUtils.formatFailedJSON("添加出现错误，请刷新后再试！");
    }

    @Override
    public JSONObject updateById(${beanName} ${beanVar}){
        SqlSession session = DBFactory.instance().getSession();
        try {
            ${daoName} mapper = session.getMapper(${daoName}.class);
            if (mapper.updateByPrimaryKey(${beanVar}) > 0) {
                session.commit();
                return StringUtils.formatSuccessJSON("更新成功");
            }
        } catch (Exception e) {
            log.error("{}", e);
            session.rollback();
        } finally {
            session.close();
        }
        return StringUtils.formatFailedJSON("更新出现错误，请刷新后再试！");
    }

    @Override
    public JSONObject deleteById(<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list> id) {
        SqlSession session = DBFactory.instance().getSession();
        try {
            ${daoName} mapper = session.getMapper(${daoName}.class);
            if (mapper.deleteByPrimaryKey(id) > 0) {
                session.commit();
                return StringUtils.formatSuccessJSON("删除成功");
            }
        } catch (Exception e) {
            log.error("{}", e);
            session.rollback();
        } finally {
            session.close();
        }
        return StringUtils.formatFailedJSON("删除出现错误，请刷新后再试！");
    }

    @Override
    public JSONObject batchDeleteById(List<<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>> ids) {
        SqlSession session = DBFactory.instance().getSession();
        try {
            ${daoName} mapper = session.getMapper(${daoName}.class);
            if (mapper.batchDeleteByPrimaryKey(ids) > 0) {
                session.commit();
                return StringUtils.formatSuccessJSON("删除成功");
            }
        } catch (Exception e) {
            log.error("{}", e);
            session.rollback();
        } finally {
            session.close();
        }
        return StringUtils.formatFailedJSON("删除出现错误，请刷新后再试！");
    }

}
