package com.qihai.permission.entity;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("用户角色关联表")
@TableName("auth_user_role")
public class AuthUserRoleEntity extends DataEntity<AuthUserRoleEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * ID
	 */
	@ApiModelProperty(name="id",value="id")
    private Long id;
    
    /**
	 * 用户ID
	 */
	@ApiModelProperty(name="用户ID",value="userId",required=true)
    @NotNull(message="用户不可为空")
    private Long userId;
    
    /**
	 * 角色
	 */
	@ApiModelProperty(name="角色ID",value="roleId",required=true)
    @NotNull(message="角色不可为空")
    private Long roleId;
    
    /**
	 * 
	 */
	@ApiModelProperty(name="数据范围",value="dataRangeId",required=true)
    @NotNull(message="数据范围不可为空")
    private Long dataRangeId;
    
    /**
	 * 日志跟踪id
	 */
	@ApiModelProperty(hidden=true)
    private String traceId;
    
    /**
	 * 创建人
	 */
	@ApiModelProperty(hidden=true)
    private String createdBy;
    
    /**
	 * 最后更新人
	 */
	@ApiModelProperty(hidden=true)
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
