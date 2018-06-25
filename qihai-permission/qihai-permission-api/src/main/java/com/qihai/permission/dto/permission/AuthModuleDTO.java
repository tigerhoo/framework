package com.qihai.permission.dto.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("模块DTO模型")
public class AuthModuleDTO {

	private Long id;

	/**
	 * 模块编码
	 */
	@ApiModelProperty(name = "moduleName", value = "模块编码")
	private String moduleName;

	/**
	 * 模块描述
	 */
	@ApiModelProperty(name = "description", value = "模块描述")
	private String description;

	private List<AuthPermissionDTO> authMenus = new ArrayList<AuthPermissionDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AuthPermissionDTO> getAuthMenus() {
		return authMenus;
	}

	public void setAuthMenus(List<AuthPermissionDTO> authMenus) {
		this.authMenus = authMenus;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
