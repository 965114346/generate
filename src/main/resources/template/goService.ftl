package ${goServiceGenerate@packagePath}

import (
	"zebraKingdom/app_manage/models/${goModelGenerate@packagePath}"
)

type I${goModelGenerate@name}Service interface {
	Select${goModelGenerate@name}ByPrimaryKey(primaryKey int64) (*${goModelGenerate@packagePath}.${goModelGenerate@name}, error)

	Select${goModelGenerate@name}ByCondition(where *${goModelGenerate@packagePath}.${goModelGenerate@name}) []${goModelGenerate@packagePath}.${goModelGenerate@name}

	Select${goModelGenerate@name}One(where *${goModelGenerate@packagePath}.${goModelGenerate@name}) (*${goModelGenerate@packagePath}.${goModelGenerate@name}, error)

	Select${goModelGenerate@name}ByPage(pageNo int, pageSize int, where *${goModelGenerate@packagePath}.${goModelGenerate@name}) ([]${goModelGenerate@packagePath}.${goModelGenerate@name}, int64)

	Insert${goModelGenerate@name}Selective(${beanVar} *${goModelGenerate@packagePath}.${goModelGenerate@name}) (*${goModelGenerate@packagePath}.${goModelGenerate@name}, error)

	Update${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@packagePath}.${goModelGenerate@name}) error

	Delete${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@packagePath}.${goModelGenerate@name}) error
}

type ${goModelGenerate@name}ServiceImpl struct {
}

func (service *${goModelGenerate@name}ServiceImpl) Select${goModelGenerate@name}ByPrimaryKey(primaryKey int64) (*${goModelGenerate@packagePath}.${goModelGenerate@name}, error) {
	return ${goModelGenerate@packagePath}.Select${goModelGenerate@name}ByPrimaryKey(primaryKey)
}

func (service *${goModelGenerate@name}ServiceImpl) Select${goModelGenerate@name}ByCondition(where *${goModelGenerate@packagePath}.${goModelGenerate@name}) []${goModelGenerate@packagePath}.${goModelGenerate@name} {
	return ${goModelGenerate@packagePath}.Select${goModelGenerate@name}ByCondition(where)
}

func (service *${goModelGenerate@name}ServiceImpl) Select${goModelGenerate@name}One(where *${goModelGenerate@packagePath}.${goModelGenerate@name}) (*${goModelGenerate@packagePath}.${goModelGenerate@name}, error) {
	return ${goModelGenerate@packagePath}.Select${goModelGenerate@name}One(where)
}

func (service *${goModelGenerate@name}ServiceImpl) Select${goModelGenerate@name}ByPage(pageNo int, pageSize int, where *${goModelGenerate@packagePath}.${goModelGenerate@name}) ([]${goModelGenerate@packagePath}.${goModelGenerate@name}, int64) {
	return ${goModelGenerate@packagePath}.Select${goModelGenerate@name}ByPage(pageNo, pageSize, where)
}

func (service *${goModelGenerate@name}ServiceImpl) Insert${goModelGenerate@name}Selective(${beanVar} *${goModelGenerate@packagePath}.${goModelGenerate@name}) (*${goModelGenerate@packagePath}.${goModelGenerate@name}, error) {
	return ${goModelGenerate@packagePath}.Insert${goModelGenerate@name}Selective(${beanVar})
}

func (service *${goModelGenerate@name}ServiceImpl) Update${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@packagePath}.${goModelGenerate@name}) error {
    return ${goModelGenerate@packagePath}.Update${goModelGenerate@name}ByPrimaryKey(${beanVar})
}

func (service *${goModelGenerate@name}ServiceImpl) Delete${goModelGenerate@name}ByPrimaryKey(${beanVar} *${goModelGenerate@packagePath}.${goModelGenerate@name}) error {
	return ${beanVar}.Delete${goModelGenerate@name}ByPrimaryKey()
}
