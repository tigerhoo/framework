package com.qihai.permission.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("组已关联用户")
public class UserGroupDTO {

	private Long id;

	@ApiModelProperty(value = "用户名", name = "userName")
	private String userName;

	/**
	 * 登录账号
	 */
	@ApiModelProperty(value = "登录账号", name = "loginName")
	private String loginName;

	/**
	 * 用户类型(C:用户,B:管理员,S:运营)
	 */
	@ApiModelProperty(value = "用户类型(C:用户,B:管理员,S:运营)", name = "type")
	private String type;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", name = "createdBy")
	private String createdBy;

	/**
	 * 最后更新人
	 */
	@ApiModelProperty(value = "最后更新人", name = "updatedBy")
	private String updatedBy;

	/**
	 * 账号状态(A:activate激活,L:lock锁定)
	 */
	@ApiModelProperty(value = "账号状态(A:activate激活,L:lock锁定)", name = "status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
