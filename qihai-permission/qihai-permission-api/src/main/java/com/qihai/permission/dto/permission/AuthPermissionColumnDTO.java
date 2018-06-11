package com.qihai.permission.dto.permission;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("菜单列DTO模型")
public class AuthPermissionColumnDTO {

	private Long id;
	/**
	 * 数据列名
	 */
	private String name;

	/**
	 * 是否被选中(true,是，false 否）
	 */
	private Boolean flag;

}
