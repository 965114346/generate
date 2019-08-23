package ${goModelGenerate@packagePath}

import (
	"fmt"
	logf "zebraKingdom/app_manage/log"
	"zebraKingdom/app_manage/models"
)

type ${goModelGenerate@name} struct {
<#list columnList as column>
    <#if column.columnComment??>
    // ${column.columnComment}
    </#if>
    <#if column.columnKey == "PRI">
    ${column.firstWordUpperCase} ${column.classSimpleName} `gorm:"primary_key" gorm:"column:${column.columnName}"`
    <#else >
    ${column.firstWordUpperCase} ${column.classSimpleName} `gorm:"column:${column.columnName}"`
    </#if>
</#list>
}

var ${beanVar}Column = "<#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>"

// 设置${goModelGenerate@name}的表名为`${tableName}`
func (${goModelGenerate@name}) TableName() string {
	return "${tableName}"
}

func Select${goModelGenerate@name}ByPrimaryKey(primaryKey <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>) *${goModelGenerate@name} {
	logf.Req.Info(fmt.Sprintf("Select${goModelGenerate@name}ByPrimaryKey, param:%d", primaryKey))
	var ${beanVar} ${goModelGenerate@name}

    models.Db().Select(${beanVar}Column).First(&${beanVar}, primaryKey)
	return &${beanVar}
}

func Select${goModelGenerate@name}One(where *${goModelGenerate@name}) *${goModelGenerate@name} {
	logf.Req.Info(fmt.Sprintf("Select${goModelGenerate@name}One, param:%+v", *where))

	var ${beanVar} ${goModelGenerate@name}
    models.Db().Select(${beanVar}Column).Where(where).First(&${beanVar})

	return &${beanVar}
}

func Select${goModelGenerate@name}ByCondition(where *${goModelGenerate@name}) []${goModelGenerate@name} {
	logf.Req.Info(fmt.Sprintf("select${goModelGenerate@name}ByCondition, param:%+v", *where))

    ${beanVar} := make([]${goModelGenerate@name}, 0)
    models.Db().Select(${beanVar}Column).Where(where).Find(&${beanVar})

    return ${beanVar}
}

func Select${goModelGenerate@name}ByPage(pageNo int, pageSize int, where *${goModelGenerate@name}) ([]${goModelGenerate@name}, int64) {
	logf.Req.Info(fmt.Sprintf("Select${goModelGenerate@name}ByPage, pageNo:%d, pageSize:%d, param:%+v", pageNo, pageSize, *where))
	pageNum := (pageNo - 1) * pageSize
    if pageNum < 0 {
        pageNum = 0
    }

    if pageSize < 10 {
        pageSize = 10
    }
    if pageSize > 200 {
        pageSize = 200
    }

	var total int64
    ${beanVar} := make([]${goModelGenerate@name}, pageSize)

	// 获取总条数
	models.Db().Model(&${goModelGenerate@name}{}).Count(&total)
	// 分页查询
	models.Db().Select(${beanVar}Column).Limit(pageSize).Offset(pageNum).Where(where).Find(&${beanVar})
	return ${beanVar}, total
}

func (this *${goModelGenerate@name})Save${goModelGenerate@name}() error{
    if this.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> == 0{
        logf.Req.Info(fmt.Sprintf("insert param:%v",this))
    }else{
        logf.Req.Info(fmt.Sprintf("update param:%v byId:%d",this,this.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>))
    }
    return models.Db().Save(this).Error
}

func Insert${goModelGenerate@name}Selective(${beanVar} *${goModelGenerate@name}) *${goModelGenerate@name} {
	logf.Req.Info(fmt.Sprintf("Insert${goModelGenerate@name}Selective, param:%+v", *${beanVar}))
	models.Db().Create(${beanVar})
	return ${beanVar}
}

func Update${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@name}) {
	logf.Req.Info(fmt.Sprintf("Update${goModelGenerate@name}ByPrimaryKey, param:%+v", *${beanVar}))
    if ${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> == 0 {
	    logf.Req.Info(fmt.Sprintf("primaryKey must not 0"))
        return
    }

    primary := &${goModelGenerate@name}{
        <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>:${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>,
	}

    ${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> = 0
	models.Db().Model(primary).Update(${beanVar})
}

func (${beanVar} *${goModelGenerate@name})Delete${goModelGenerate@name}ByPrimaryKey() error {
	logf.Req.Info(fmt.Sprintf("Delete${goModelGenerate@name}ByPrimaryKey, param:%+v", *${beanVar}))
	if ${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> == 0 {
	    logf.Req.Info(fmt.Sprintf("primaryKey must not 0"))
        return nil
    }

    primary := &${goModelGenerate@name}{
        <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>:${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>,
	}
	return models.Db().Delete(primary).Error
}