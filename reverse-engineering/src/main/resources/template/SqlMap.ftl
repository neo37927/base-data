<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${poName}">
	<sql id="baseColumnList">
		<#list columnList as obj>
			u.${obj.dbName} as ${obj.name} <#if obj_has_next>,</#if>
		</#list>
	</sql>
	<sql id="searchCriteria">
		<#list columnList as obj>
		<if test="${obj.name} != null">
			and u.${obj.dbName} = #{${obj.name},jdbcType=${obj.sqlMapColumnType}}
		</if>
		<#if obj.type == "String">
		<if test="${obj.name}Like != null">
			and u.${obj.dbName} like CONCAT('%',#{${obj.name}Like,jdbcType=${obj.sqlMapColumnType}},'%') 
		</if>
		</#if>
		</#list>
	</sql>

	<!-- 查询列表数据 -->
	<select id="queryListResult" parameterType="map" resultType="${poName}">
		select
			<include refid="baseColumnList" />
		from
			${tableName} u
		where
			<#if existDisabled == "0">
		    u.disabled = 0
			<#else>
			1=1
			</#if>
			<include refid="searchCriteria" />
			<include refid="Util.orderCriteria" />
			<include refid="Util.mysqlPage" />
	</select>
	<select id="queryListResultCount" parameterType="map" resultType="long">
		select
			count(u.id)
		from
			${tableName} u
		where
			<#if existDisabled == "0">
		    u.disabled = 0
			<#else>
			1=1
			</#if>
		<include refid="searchCriteria" />
	</select>
	<!-- 自定义查询列表where条件 -->
	<select id="queryListResultByWhere" parameterType="java.util.Map" statementType="STATEMENT" resultType="${poName}">
		select
			<include refid="baseColumnList" />
		from
			${tableName} u
		where
		    u.disabled = 0 
		    <if test="whereSqlStr != null">
			AND %%{whereSqlStr}
			</if>
	</select>
	<select id="queryListResultCountByWhere" parameterType="java.util.Map" statementType="STATEMENT" resultType="long">
		select
			count(u.id)
		from
			${tableName} u
		where
		    u.disabled = 0 
			<if test="whereSqlStr != null">
			AND %%{whereSqlStr}
			</if>
	</select>
	
	<!-- 查询一条数据 -->
	<select id="querySingleResultById" parameterType="long" resultType="${poName}">
		select
			<include refid="baseColumnList" />
		from
			${tableName} u
		where
			<#if existDisabled == "0">
		    u.disabled = 0
			<#else>
			1=1
			</#if>
			and u.ID = #{id,jdbcType=NUMERIC}
	</select>
	
	<#if existUuid=="0">
	<select id="querySingleResultByUUID" parameterType="String" resultType="${poName}">
		select
			<include refid="baseColumnList" />
		from
			${tableName} u
		where
			<#if existDisabled == "0">
			    u.disabled = 0
			<#else>
				1=1
			</#if>
			and u.uuid = #{uuid,jdbcType=VARCHAR}
	</select>
	</#if>
	<select id="querySingleResultByParams" parameterType="map" resultType="${poName}">
		select
			<include refid="baseColumnList" />
		from
			${tableName} u
		where
			<#if existDisabled == "0">
			u.disabled = 0
			<#else>
			1=1
			</#if>
			<include refid="searchCriteria" />
	</select>

	<insert id="save" parameterType="${poName}" useGeneratedKeys="true" keyProperty="id">
		insert into ${tableName}(
	<#list columnList as obj>
		<#if obj.name != "id">
			${obj.dbName}<#if obj_has_next>,</#if>
		</#if>
	</#list>
		)values(
	<#list columnList as obj>
		<#if obj.name != "id">
			#{${obj.name},jdbcType=${obj.sqlMapColumnType}}<#if obj_has_next>,</#if>
		</#if>
	</#list>
		)
	</insert>
	<insert id="saveBatch" parameterType="ArrayList">
		insert into ${tableName}(
	<#list columnList as obj>
		<#if obj.name != "id">
			${obj.dbName}<#if obj_has_next>,</#if>
		</#if>
	</#list>
		)values
		<foreach collection="list" item="obj" separator=",">
		(
	<#list columnList as obj>
		<#if obj.name != "id">
			#{obj.${obj.name},jdbcType=${obj.sqlMapColumnType}}<#if obj_has_next>,</#if>
		</#if>
	</#list>
		)
		</foreach>
	</insert>

	<!-- 修改 -->
	<update id="update" parameterType="${poName}">
		update ${tableName} u
		<set>
			u.create_time = #{createTime,jdbcType=TIMESTAMP} 
			<#list columnList as obj>
				 <#if obj.name != "id" && obj.name != 'createUser' && obj.name != 'createTime' >
                 	<#if existUuid=="0">
                 		<#if obj.name != "uuid">
 			<if test="${obj.name} != null">
				,u.${obj.dbName}=#{${obj.name},jdbcType=${obj.sqlMapColumnType}} 
			</if>
            			</#if>
            		<#else>
			<if test="${obj.name} != null">
				,u.${obj.dbName}=#{${obj.name},jdbcType=${obj.sqlMapColumnType}} 
			</if>
            		</#if>
            	</#if>
			</#list>
		</set>
		where
            u.ID = #{id,jdbcType=NUMERIC}
            
	</update>
	<!-- 自定义修改列以及修改条件 -->
	<update id="updateCustomColumnByWhere" parameterType="map" statementType="STATEMENT">
		UPDATE  ${tableName}  %%{updateSqlStr}
		where
			%%{whereSqlStr}
	</update>
	
	<#if existDisabled == "0">
	<!-- 逻辑删除 -->
	<update id="logicDelete" parameterType="map">
		update ${tableName} u set
		u.DISABLED = 1
		<if test="updateTime != null">
		    ,u.UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}
		</if>
		<if test="updateUser != null">
            ,u.UPDATE_USER = #{updateUser,jdbcType=NUMERIC}
		</if>
		where
			<if test="id != null">
				u.ID = #{id,jdbcType=NUMERIC}
			</if>
			<#if existUuid=="0">
			<if test="id == null and uuid != null">
				u.uuid = #{uuid,jdbcType=VARCHAR}
			</if>
			<if test="id == null and uuid == null">
				u.id = null
			</if>
			</#if>
	</update>
	<!-- 自定义逻辑条件 -->
	<update id="logicWhereDelete" parameterType="java.util.Map" statementType="STATEMENT">
		update ${tableName} set DISABLED = 1
		where
			%%{whereSqlStr}
	</update>
	</#if>
	<!-- 物理删除 -->
	<delete id="physicalDelete" parameterType="java.lang.Integer">
		delete from ${tableName} where id=#{id,jdbcType=INTEGER}
	</delete>
	
	<!-- 自定义物理条件 -->
	<delete id="physicalWhereDelete" parameterType="java.util.Map" statementType="STATEMENT">
		DELETE FROM ${tableName} WHERE %%{whereSqlStr}
	</delete>
	
	
</mapper>