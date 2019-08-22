package ${goModelGenerate@packagePath}

import (
	"fmt"
	log "github.com/sirupsen/logrus"
	"zebraKingdom/app_manage/models"
)

package service

import (
	"zebraKingdom/app_manage/models/user"
)

type IUserInvitationService interface {

	SelectUserInvitationByPrimaryKey(primaryKey int64) *user.UserInvitation

	SelectUserInvitationByCondition(where *user.UserInvitation) []user.UserInvitation

	SelectUserInvitationByPage(pageNo int, pageSize int, where *user.UserInvitation) ([]user.UserInvitation, int64)

	InsertUserInvitationSelective(userInvitation *user.UserInvitation) *user.UserInvitation

	UpdateUserInvitationByPrimaryKey(userInvitation *user.UserInvitation)

	DeleteUserInvitationByPrimaryKey(userInvitation *user.UserInvitation)
}

type UserInvitationServiceImpl struct {

}

func (service *UserInvitationServiceImpl)SelectUserInvitationByPrimaryKey(primaryKey int64) *user.UserInvitation {
	return user.SelectUserInvitationByPrimaryKey(primaryKey)
}

func (service *UserInvitationServiceImpl)SelectUserInvitationByCondition(where *user.UserInvitation) []user.UserInvitation {
	return user.SelectUserInvitationByCondition(where)
}

func (service *UserInvitationServiceImpl)SelectUserInvitationByPage(pageNo int, pageSize int, where *user.UserInvitation) ([]user.UserInvitation, int64) {
	return user.SelectUserInvitationByPage(pageNo, pageSize, where)
}

func (service *UserInvitationServiceImpl)InsertUserInvitationSelective(userInvitation *user.UserInvitation) *user.UserInvitation {
	return user.InsertUserInvitationSelective(userInvitation)
}

func (service *UserInvitationServiceImpl)UpdateUserInvitationByPrimaryKey(userInvitation *user.UserInvitation) {
	user.UpdateUserInvitationByPrimaryKey(userInvitation)
}

func (service *UserInvitationServiceImpl)DeleteUserInvitationByPrimaryKey(userInvitation *user.UserInvitation) {
	user.DeleteUserInvitationByPrimaryKey(userInvitation)
}

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
	log.Info(fmt.Sprintf("Select${goModelGenerate@name}ByPrimaryKey, param:%d", primaryKey))
	var ${beanVar} ${goModelGenerate@name}
	models.Db().Select(${beanVar}Column).First(&${beanVar}, primaryKey)
	return &${beanVar}
}

func select${goModelGenerate@name}ByCondition(where *UserInvitation) []UserInvitation {
	log.Info(fmt.Sprintf("select${goModelGenerate@name}ByCondition, param:%+v", *where))

    ${beanVar} := make([]${goModelGenerate@name}, 0)
	models.Db().Select(${beanVar}Column).Where(where).Find(&${beanVar})

    return ${beanVar}
}

func Select${goModelGenerate@name}ByPage(pageNo int, pageSize int, where *${goModelGenerate@name}) ([]${goModelGenerate@name}, int64) {
	log.Info(fmt.Sprintf("Select${goModelGenerate@name}ByPage, pageNo:%d, pageSize:%d, param:%+v", pageNo, pageSize, *where))
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

func Insert${goModelGenerate@name}Selective(${beanVar} *${goModelGenerate@name}) *${goModelGenerate@name} {
	log.Info(fmt.Sprintf("Insert${goModelGenerate@name}Selective, param:%+v", *${beanVar}))
	models.Db().Create(${beanVar})
	return ${beanVar}
}

func Update${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@name}) {
	log.Info(fmt.Sprintf("Update${goModelGenerate@name}ByPrimaryKey, param:%+v", *${beanVar}))
    if ${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> == 0 {
	    log.Info(fmt.Sprintf("primaryKey must not 0"))
        return
    }

    primary := &${goModelGenerate@name}{
		<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>:${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list>,
	}

	<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> = 0
	models.Db().Model(primary).Update(${beanVar})
}

func Delete${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@name}) {
	log.Info(fmt.Sprintf("Delete${goModelGenerate@name}ByPrimaryKey, param:%+v", *${beanVar}))
	if ${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> == 0 {
	    log.Infof(fmt.Sprintf("primaryKey must not 0"))
        return
    }

	models.Db().Delete(${beanVar})
}