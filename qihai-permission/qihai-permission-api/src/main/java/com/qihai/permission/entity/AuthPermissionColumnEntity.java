package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.qihai.commerce.framework.entity.DataEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@TableName("auth_permission_column")
public class AuthPermissionColumnEntity extends DataEntity<AuthPermissionColumnEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 主键
	 */
    private Long id;
    
    /**
	 * 资源id
	 */
    private Long permissionId;
    
    /**
	 * 数据列名
	 */
    private String name;
    
    /**
	 * 属性名[JSON中需要过滤的属性]
	 */
    private String attribute;
    
    /**
	 * 描述
	 */
    private String description;
    
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
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：资源id
	 */
	public Long getPermissionId() {
		return permissionId;
	}
	
	/**
	 * 设置：资源id
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	/**
	 * 获取：数据列名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置：数据列名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取：属性名[JSON中需要过滤的属性]
	 */
	public String getAttribute() {
		return attribute;
	}
	
	/**
	 * 设置：属性名[JSON中需要过滤的属性]
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
