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
 * @date 2018-05-29 09:05:47
 */
@TableName("auth_dimension_value_scope")
public class AuthDimensionValueScopeEntity extends DataEntity<AuthDimensionValueScopeEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 
	 */
    private Long id;
    
    /**
	 * 数据维度ID
	 */
    private Long dimensionValueId;
    
    /**
	 * 值
	 */
    private String dimensionValue;
    
    /**
	 * 
	 */
    private String displayName;
    
   
    /**
	 * 日志跟踪id
	 */
    private String traceId;
    
    /**
	 * 创建人
	 */
    private String createdBy;
    
    /**
	 * 修改人
	 */
    private String updatedBy;
	
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：数据维度ID
	 */
	public Long getDimensionValueId() {
		return dimensionValueId;
	}
	
	/**
	 * 设置：数据维度ID
	 */
	public void setDimensionValueId(Long dimensionValueId) {
		this.dimensionValueId = dimensionValueId;
	}
	
	/**
	 * 获取：值
	 */
	public String getDimensionValue() {
		return dimensionValue;
	}
	
	/**
	 * 设置：值
	 */
	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}
	
	/**
	 * 获取：
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * 设置：
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * 获取：修改人
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	/**
	 * 设置：修改人
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
