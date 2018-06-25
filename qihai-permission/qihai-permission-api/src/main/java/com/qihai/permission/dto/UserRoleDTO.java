package com.qihai.permission.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserRoleDTO {

	private Long id;

	private String roleCode;
	private String roleName;
	private String dataRangeName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDataRangeName() {
		return dataRangeName;
	}

	public void setDataRangeName(String dataRangeName) {
		this.dataRangeName = dataRangeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
