package com.qihai.permission.vo.menu;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModelProperty;

public class AuthPermissionVO {

	@ApiModelProperty(name = "id", value = "主键id")
	private Long id;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(name = "menuName", value = "菜单名称")
	private String menuName;

	/**
	 * 菜单URL
	 */
	@ApiModelProperty(name = "url", value = "菜单URL")
	private String url;

	/**
	 * 菜单功能描述
	 */
	@ApiModelProperty(name = "description", value = "菜单功能描述")
	private String description;

	/**
	 * 服务编码
	 */
	@ApiModelProperty(name = "serviceCode", value = "服务编码")
	private String serviceCode;

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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
