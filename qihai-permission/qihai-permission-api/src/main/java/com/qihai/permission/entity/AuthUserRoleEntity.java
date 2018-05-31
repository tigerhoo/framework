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
@TableName("auth_user_role")
public class AuthUserRoleEntity extends DataEntity<AuthUserRoleEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * ID
	 */
    private Long id;
    
    /**
	 * 用户ID
	 */
    private Long userId;
    
    /**
	 * 角色
	 */
    private Long roleId;
    
    /**
	 * 
	 */
    private Long dataRangeId;
    
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
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取：角色
	 */
	public Long getRoleId() {
		return roleId;
	}
	
	/**
	 * 设置：角色
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 获取：
	 */
	public Long getDataRangeId() {
		return dataRangeId;
	}
	
	/**
	 * 设置：
	 */
	public void setDataRangeId(Long dataRangeId) {
		this.dataRangeId = dataRangeId;
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
