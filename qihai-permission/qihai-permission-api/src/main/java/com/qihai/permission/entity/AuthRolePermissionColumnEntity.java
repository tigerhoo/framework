package com.qihai.permission.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qihai.permission.entity.common.CommonEntity;



/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@TableName("auth_role_permission_column")

public class AuthRolePermissionColumnEntity extends CommonEntity<AuthRolePermissionColumnEntity> {

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

	public Long getPermissionColumnId() {
		return permissionColumnId;
	}

	public void setPermissionColumnId(Long permissionColumnId) {
		this.permissionColumnId = permissionColumnId;
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
