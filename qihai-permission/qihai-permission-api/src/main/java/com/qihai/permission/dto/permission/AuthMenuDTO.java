package com.qihai.permission.dto.permission;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("菜单DTO模型")
public class AuthMenuDTO {

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

}
