package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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
@ApiModel("用户与组关联模型")
@TableName("auth_user_group")
public class AuthUserGroupEntity extends DataEntity<AuthUserGroupEntity> {

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

	/**
	 * 创建人
	 */
	@ApiModelProperty(hidden = true)
	private String createdBy;

	/**
	 * 最后更新人
	 */
	@ApiModelProperty(hidden = true)
	private String updatedBy;

	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：id
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
	 * 获取：用户组ID
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 设置：用户组ID
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
