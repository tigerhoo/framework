package com.qihai.permission.dto.permission;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
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

	
	private List<AuthMenuDTO> authMenus=new ArrayList<AuthMenuDTO>();

}
