package com.qihai.permission.entity;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qihai.permission.entity.common.CommonEntity;

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

public class AuthUserRoleEntity extends CommonEntity<AuthUserRoleEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@ApiModelProperty(name = "id", value = "id")
	private Long id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(name = "用户ID", value = "userId", required = true)
	@NotNull(message = "用户不可为空")
	private Long userId;

	/**
	 * 角色
	 */
	@ApiModelProperty(name = "角色ID", value = "roleId", required = true)
	@NotNull(message = "角色不可为空")
	private Long roleId;

	/**
	 * 
	 */
	@ApiModelProperty(name = "数据范围", value = "dataRangeId", required = true)
	@NotNull(message = "数据范围不可为空")
	private Long dataRangeId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDataRangeId() {
		return dataRangeId;
	}

	public void setDataRangeId(Long dataRangeId) {
		this.dataRangeId = dataRangeId;
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
