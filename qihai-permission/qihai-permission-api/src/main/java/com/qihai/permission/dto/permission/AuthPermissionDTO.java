package com.qihai.permission.dto.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("资源DTO模型")
public class AuthPermissionDTO {

	private List<AuthPermissionColumnDTO> authPermissionColumns = new ArrayList<AuthPermissionColumnDTO>();

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
	 * 方法编码
	 */
	@ApiModelProperty(name = "methodCode", value = "方法编码")
	private String methodCode;

	/**
	 * 服务编码
	 */
	@ApiModelProperty(name = "serviceCode", value = "服务编码")
	private String serviceCode;

	/**
	 * 是否需要鉴权(1:是,0:否,-1:没有配置)
	 */
	@ApiModelProperty(name = "isAuth", value = "是否需要鉴权(1:是,0:否,-1:没有配置)")
	private Integer isAuth;

	/**
	 * 版本
	 */
	@ApiModelProperty(name = "version", value = "版本")
	private Long version;

	/**
	 * 是否被选中标志
	 */
	private Boolean flag;

	public List<AuthPermissionColumnDTO> getAuthPermissionColumns() {
		return authPermissionColumns;
	}

	public void setAuthPermissionColumns(List<AuthPermissionColumnDTO> authPermissionColumns) {
		this.authPermissionColumns = authPermissionColumns;
	}

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

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
