package com.qihai.permission.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

public class UserInfoVO {

	private Long id;

	@ApiModelProperty(value = "用户名", name = "userName")
	private String userName;

	@ApiModelProperty(value = "登录名", name = "loginName")
	private String loginName;

	@ApiModelProperty(value = "账号状态(A:activate激活,L:lock锁定)", name = "status")
	private String status;

	@ApiModelProperty(value = "注册时间", name = "registTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registTime;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", name = "createdBy", hidden = true)
	private String createdBy;

	/**
	 * 最后更新人
	 */
	@ApiModelProperty(value = "最后更新人", name = "updatedBy", hidden = true)
	private String updatedBy;

	@ApiModelProperty(value = "创建时间", name = "createTime", hidden = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@ApiModelProperty(value = "最后更新时间", name = "updateTime", hidden = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
