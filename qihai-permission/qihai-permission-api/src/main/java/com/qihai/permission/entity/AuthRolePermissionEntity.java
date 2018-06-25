package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import com.qihai.permission.entity.common.CommonEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("角色权限关联模型")
@TableName("auth_role_permission")

public class AuthRolePermissionEntity extends CommonEntity<AuthRolePermissionEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(name = "roleId", value = "角色ID", required = true)
	@NotNull(message = "角色不可为空")
	private Long roleId;

	/**
	 * 权限ID
	 */
	@ApiModelProperty(name = "permissionId", value = "权限ID", required = true)
	@NotNull(message = "权限不可为空")
	private Long permissionId;

	/**
	 * 日志跟踪id
	 */
	@ApiModelProperty(hidden = true)
	private String traceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
