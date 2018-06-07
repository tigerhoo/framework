package com.qihai.permission.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("组关联角色")
public class GroupRoleDTO {

	private Long id;

	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名", name = "roleName")
	private String roleName;

	@ApiModelProperty(value = "创建人", name = "createdBy")
	private String createdBy;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;

	/**
	 * 范围名称
	 */
	@ApiModelProperty(value = "范围名称", name = "dataRangeName")
	private String dataRangeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDataRangeName() {
		return dataRangeName;
	}

	public void setDataRangeName(String dataRangeName) {
		this.dataRangeName = dataRangeName;
	}

}
