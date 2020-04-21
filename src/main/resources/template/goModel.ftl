package models

import (
    "github.com/jinzhu/gorm"
)

type ${goModelGenerate@name} struct {
<#list columnList as column>
    <#if column.columnComment??>
    // ${column.columnComment}
    </#if>
    <#if column.columnKey == "PRI">
    ${column.firstWordUpperCase} ${column.classSimpleName} `json:"${column.columnName}" gorm:"primary_key" gorm:"column:${column.columnName}"`
    <#else >
    ${column.firstWordUpperCase} ${column.classSimpleName} `json:"${column.columnName}" gorm:"column:${column.columnName}"`
    </#if>
</#list>
}
<#--var ${beanVar}Column = "<#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>"-->

// 设置${goModelGenerate@name}的表名为`${tableName}`
func (${goModelGenerate@name}) TableName() string {
	return "${tableName}"
}

// Exist${goModelGenerate@name}ByID checks if an ${beanVar} exists based on ID
func Exist${goModelGenerate@name}ByID(id <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>) (bool, error) {
    var ${beanVar} ${goModelGenerate@name}
    err := db.Select("<#list columnList as column><#if column.columnKey == "PRI">${column.columnName}</#if></#list>").Where("<#list columnList as column><#if column.columnKey == "PRI">${column.columnName}</#if></#list> = ? ", id).First(&${beanVar}).Error
    if err != nil && err != gorm.ErrRecordNotFound {
        return false, err
    }

    if ${beanVar}.<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.firstWordUpperCase}</#if></#list> > 0 {
        return true, nil
    }

    return false, nil
}

// Get${goModelGenerate@name}Total gets the total number of ${beanVar}s based on the constraints
func Get${goModelGenerate@name}Total(maps interface{}) (int, error) {
    var count int
    if err := db.Model(&${goModelGenerate@name}{}).Where(maps).Count(&count).Error; err != nil {
        return 0, err
    }

    return count, nil
}

// Get${goModelGenerate@name}s gets a list of ${beanVar}s based on paging constraints
func Get${goModelGenerate@name}s(pageNum int, pageSize int, maps interface{}) ([]*${goModelGenerate@name}, error) {
    var ${beanVar}s []*${goModelGenerate@name}
    err := db.Where(maps).Offset(pageNum).Limit(pageSize).Find(&${beanVar}s).Error
    if err != nil && err != gorm.ErrRecordNotFound {
        return nil, err
    }

    return ${beanVar}s, nil
}

// Get${goModelGenerate@name} Get a single ${beanVar} based on ID
func Get${goModelGenerate@name}(id <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>) (*${goModelGenerate@name}, error) {
    var ${beanVar} ${goModelGenerate@name}
    err := db.Where("<#list columnList as column><#if column.columnKey == "PRI">${column.columnName}</#if></#list> = ?", id).First(&${beanVar}).Error
    if err != nil && err != gorm.ErrRecordNotFound {
        return nil, err
    }

    return &${beanVar}, nil
}

// Edit${goModelGenerate@name} modify a single ${beanVar}
func Edit${goModelGenerate@name}(id <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>, data interface{}) error {
    if err := db.Model(&${goModelGenerate@name}{}).Where("<#list columnList as column><#if column.columnKey == "PRI">${column.columnName}</#if></#list> = ?", id).Updates(data).Error; err != nil {
        return err
    }

    return nil
}

// Add${goModelGenerate@name} add a single ${beanVar}
func Add${goModelGenerate@name}(data map[string]interface{}) error {
    ${beanVar} := ${goModelGenerate@name}{
    <#list columnList as column>
    <#if column.columnKey == "PRI">
    <#else >
        ${column.firstWordUpperCase}: data["${column.columnName}"].(${column.classSimpleName}),
    </#if>
    </#list>
    }
    if err := db.Create(&${beanVar}).Error; err != nil {
        return err
    }

    return nil
}

// Delete${goModelGenerate@name} delete a single ${beanVar}
func Delete${goModelGenerate@name}(id <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>) error {
    if err := db.Where("<#list columnList as column><#if column.columnKey == "PRI">${column.columnName}</#if></#list> = ?", id).Delete(${goModelGenerate@name}{}).Error; err != nil {
        return err
    }

    return nil
}