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
@ApiModel("用户与组关联模型")
@TableName("auth_user_group")

public class AuthUserGroupEntity extends CommonEntity<AuthUserGroupEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id,更新时必传参数", name = "id")
	private Long id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(name = "用户ID", value = "userId", required = true)
	@NotNull(message = "用户不可为空")
	private Long userId;

	/**
	 * 用户组ID
	 */
	@ApiModelProperty(name = "用户组ID", value = "groupId", required = true)
	@NotNull(message = "用户组不可为空")
	private Long groupId;

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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
