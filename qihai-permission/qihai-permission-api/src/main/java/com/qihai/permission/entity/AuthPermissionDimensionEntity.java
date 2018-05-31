package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import java.util.Date;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@TableName("auth_permission_dimension")
public class AuthPermissionDimensionEntity extends DataEntity<AuthPermissionDimensionEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * id
	 */
    private Long id;
    
    /**
	 * 维度
	 */
    private Long dimensionId;
    
    /**
	 * 维度参数
	 */
    private String dimensionParam;
    
    /**
	 * 字段名
	 */
    private String fieldName;
    
    /**
	 * 属性名
	 */
    private String attributeName;
    
    /**
	 * 菜单ID
	 */
    private Long permissionId;
    
    
    /**
	 * 日志跟踪id
	 */
    private String traceId;
    
    /**
	 * 创建人
	 */
    private String createdBy;
    
    /**
	 * 最后更新人
	 */
    private String updatedBy;
	
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：维度
	 */
	public Long getDimensionId() {
		return dimensionId;
	}
	
	/**
	 * 设置：维度
	 */
	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}
	
	/**
	 * 获取：维度参数
	 */
	public String getDimensionParam() {
		return dimensionParam;
	}
	
	/**
	 * 设置：维度参数
	 */
	public void setDimensionParam(String dimensionParam) {
		this.dimensionParam = dimensionParam;
	}
	
	/**
	 * 获取：字段名
	 */
	public String getFieldName() {
		return fieldName;
	}
	
	/**
	 * 设置：字段名
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
	 * 获取：属性名
	 */
	public String getAttributeName() {
		return attributeName;
	}
	
	/**
	 * 设置：属性名
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	/**
	 * 获取：菜单ID
	 */
	public Long getPermissionId() {
		return permissionId;
	}
	
	/**
	 * 设置：菜单ID
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	
	/**
	 * 获取：日志跟踪id
	 */
	public String getTraceId() {
		return traceId;
	}
	
	/**
	 * 设置：日志跟踪id
	 */
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	
	/**
	 * 获取：创建人
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * 设置：创建人
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * 获取：最后更新人
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	/**
	 * 设置：最后更新人
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
