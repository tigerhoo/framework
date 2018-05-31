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
@TableName("auth_role_permission_column")
public class AuthRolePermissionColumnEntity extends DataEntity<AuthRolePermissionColumnEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 主键
	 */
    private Long id;
    
    /**
	 * 角色ID
	 */
    private Long roleId;
    
    /**
	 * 权限资源ID
	 */
    private Long permissionId;
    
    /**
	 * 列权限ID
	 */
    private Long permissionColumnId;
    
  
    /**
	 * 日志跟踪id
	 */
    private String traceId;
    
    /**
	 * 
	 */
    private String createdBy;
    
    /**
	 * 最后更新人
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
	 * 获取：角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	/**
	 * 设置：角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 获取：权限资源ID
	 */
	public Long getPermissionId() {
		return permissionId;
	}
	
	/**
	 * 设置：权限资源ID
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	/**
	 * 获取：列权限ID
	 */
	public Long getPermissionColumnId() {
		return permissionColumnId;
	}
	
	/**
	 * 设置：列权限ID
	 */
	public void setPermissionColumnId(Long permissionColumnId) {
		this.permissionColumnId = permissionColumnId;
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
	 * 获取：
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * 设置：
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
