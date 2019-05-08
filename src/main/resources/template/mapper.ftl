<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage}.${daoName}" >

	<resultMap id="BaseResultMap" type="${beanPackage}.${beanName}" >
		<#list columnList as beanField>
		<#if beanField.columnKey == "PRI">
		<id column="${beanField.columnName}" property="${beanField.name}" />
		<#else >
		<result column="${beanField.columnName}" property="${beanField.name}" />
		</#if>
		</#list>
	</resultMap>

	<sql id="Base_Column_List" >
		<#list columnList as beanField>`${beanField.columnName}`<#if beanField_has_next>, </#if></#list>
	</sql>

	<!-- 根据id查询${tableComment} -->
	<select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>" >
		select
		<include refid="Base_Column_List" />
		from ${tableName}
		<where>
		<#list columnList as beanField>
		<#if beanField.columnKey == "PRI">
			${beanField.columnName} = <#noparse>#</#noparse>{id}
		</#if>
		</#list>
			and `is_delete` = 1
		</where>
	</select>

	<!-- 条件查询${tableComment}列表 -->
	<select id="selectByCondition" resultMap="BaseResultMap" parameterType="${beanPackage}.${beanName}" >
		select
		<include refid="Base_Column_List" />
		from ${tableName}
		<where>
			<#list columnList as beanField>
			<#if beanField.name != 'isDelete'>
			<if test="${beanField.name} != null<#if beanField.classSimpleName == 'String'> and ${beanField.name} != ''</#if>" >
				and `${beanField.columnName}` = <#noparse>#</#noparse>{${beanField.name}}
			</if>
			<#else >
			<choose>
				<when test="isDelete != null">
					and `is_delete` = <#noparse>#</#noparse>{${beanField.name}}
				</when>
				<otherwise>
					and `is_delete` = 1
				</otherwise>
			</choose>
			</#if>
			</#list>
		</where>
	</select>

	<!-- 插入${tableComment} -->
	<insert id="insertSelective" parameterType="${beanPackage}.${beanName}" >
		<selectKey resultType="<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>" keyProperty="id" order="AFTER">
			SELECT LAST_INSERT_ID()
		</selectKey>
		insert into ${tableName}
		<trim prefix="(" suffix=")" suffixOverrides="," >
			<#list columnList as beanField>
			<if test="${beanField.name} != null" >
				`${beanField.columnName}`,
			</if>
			</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
			<#list columnList as beanField>
			<if test="${beanField.name} != null" >
				<#noparse>#</#noparse>{${beanField.name}},
			</if>
			</#list>
		</trim>
	</insert>

	<!-- 更新${tableComment} -->
	<update id="updateByPrimaryKey" parameterType="${beanPackage}.${beanName}" >
		update ${tableName}
		<set >
			<#list columnList as beanField>
			<#if beanField.columnKey != "PRI">
			<if test="${beanField.name} != null<#if beanField.classSimpleName == 'String'> and ${beanField.name} != ''</#if>" >
				`${beanField.columnName}` = <#noparse>#</#noparse>{${beanField.name}},
			</if>
			</#if>
			</#list>
		</set>
		<where>
			<#list columnList as beanField>
			<#if beanField.columnKey == "PRI">
			${beanField.columnName} = <#noparse>#</#noparse>{${beanField.name}}
			</#if>
			</#list>
		</where>
	</update>

	<!-- 根据id删除${tableComment} -->
	<update id="deleteByPrimaryKey" parameterType="<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>">
		update ${tableName} set is_delete = 2 where id = <#noparse>#</#noparse>{id}
	</update>

	<!-- 根据id批量删除${tableComment} -->
	<update id="batchDeleteByPrimaryKey" parameterType="<#list columnList as beanField><#if beanField.columnKey == "PRI">${beanField.className}</#if></#list>">
		update ${tableName} set is_delete = 2 where id in
		<foreach item="id" collection="list" open="(" separator="," close=")">
			<#noparse>#</#noparse>{id}
		</foreach>
	</update>

</mapper>
