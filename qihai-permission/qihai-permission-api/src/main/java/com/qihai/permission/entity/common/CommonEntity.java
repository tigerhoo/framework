package com.qihai.permission.entity.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModelProperty;

public class CommonEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", name = "createdBy", hidden = true)
	private String createdBy;

	/**
	 * 最后更新人
	 */
	@ApiModelProperty(value = "最后更新人", name = "updatedBy", hidden = true)
	private String updatedBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
