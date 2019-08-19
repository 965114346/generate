package ${goBaseGenerate@packagePath};

import "zebraKingdom/app_manage/models"

type ${goBaseGenerate@name} struct {
<#list columnList as column>
    <#if column.columnComment??>
    // ${column.columnComment}
    </#if>
    ${column.firstWordUpperCase} ${column.classSimpleName} `db:"${column.columnName}"`
</#list>
    // <#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>
}

func Select${goBaseGenerate@name}ByPrimaryKey(primaryKey <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>) (*${goBaseGenerate@name}, error) {
	var ${beanVar} ${goBaseGenerate@name}
	err := models.NewquerySqlbuilder().
		TableName("${tableName}").
		Field("<#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>").
		Where("<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.columnName}</#if></#list> = '%s'", primaryKey).
		QueryOne(&${beanVar})
	if err != nil {
		return nil, err
	}
	return &${beanVar}, nil
}

func Select${goBaseGenerate@name}ByPage(pageNo int, pageSize int) ([]${goBaseGenerate@name}, error) {
    var ${beanVar} []${goBaseGenerate@name}
	err := models.NewquerySqlbuilder().
		TableName("${tableName}").
		Field("<#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>").
        Pagelimit(pageNo, pageSize).
		QueryList(${beanVar})
	if err != nil {
		return nil, err
	}
	return ${beanVar}, nil
}

func Insert${goBaseGenerate@name}Selective(${beanVar} *${goBaseGenerate@name}) (sql.Result, error) {
	insertSql := fmt.Sprintf(`INSERT INTO ${tableName}
    (<#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>)
    VALUES
    (<#list columnList as beanField>'%s'<#if beanField_has_next>, </#if></#list>);
    `, <#list columnList as beanField>${beanVar}.${beanField.firstWordUpperCase}<#if beanField_has_next>, </#if></#list>)
    return models.Exec(insertSql)
}

func Delete${goBaseGenerate@name}ByPrimaryKey(primaryKey <#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>) (error) {
    return nil
}