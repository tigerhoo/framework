package com.qihai.permission.dto.permission;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("资源与模块关联DTO")
public class AuthPermissionModuleDTO {

	@ApiModelProperty(name = "id", value = "资源id")
	private Long id;

	@ApiModelProperty(name = "menuName", value = "菜单名称")
	private String menuName;

	@ApiModelProperty(name = "moduleName", value = "模块名称")
	private String moduleName;

	@ApiModelProperty(name = "url", value = "资源URL")
	private String url;

	@ApiModelProperty(name = "description", value = "资源功能描述")
	private String description;

	@ApiModelProperty(name = "serviceCode", value = "服务编码")
	private String serviceCode;

	@ApiModelProperty(name = "menuMapping", value = "菜单方法路径映射")
	private String menuMapping;

	@ApiModelProperty(name = "remarks", value = "备注")
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getMenuMapping() {
		return menuMapping;
	}

	public void setMenuMapping(String menuMapping) {
		this.menuMapping = menuMapping;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
