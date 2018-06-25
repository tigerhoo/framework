package com.qihai.permission.dto.permission;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModel;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
