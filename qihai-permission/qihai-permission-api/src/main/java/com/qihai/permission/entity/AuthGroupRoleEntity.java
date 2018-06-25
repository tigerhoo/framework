package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import com.qihai.permission.entity.common.CommonEntity;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@TableName("auth_group_role")
public class AuthGroupRoleEntity extends CommonEntity<AuthGroupRoleEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id,更新时必传参数", name = "id")
	private Long id;

	/**
	 * 用户组ID
	 */
	@ApiModelProperty(value = "用户组ID", name = "groupId", required = true)
	private Long groupId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID", name = "roleId", required = true)
	private Long roleId;

	/**
	 * 
	 */
	@ApiModelProperty(value = "数据范围id", name = "dataRangeId", required = true)
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
