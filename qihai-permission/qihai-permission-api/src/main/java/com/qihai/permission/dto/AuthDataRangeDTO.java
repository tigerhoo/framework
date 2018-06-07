package com.qihai.permission.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AuthDataRangeDTO {

	@ApiModelProperty(value = "id,更新时必传参数", name = "id")
	private Long id;

	/**
	 * 范围名称
	 */
	@ApiModelProperty(value = "范围名称", name = "dataRangeName", required = true)
	@NotBlank(message = "范围名称不可为空")
	private String dataRangeName;

	/**
	 * 范围描述
	 */
	@ApiModelProperty(value = "范围描述", name = "description")
	private String description;

	private List<AuthDimensionValueDTO> authDimensionValue=new ArrayList<AuthDimensionValueDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataRangeName() {
		return dataRangeName;
	}

	public void setDataRangeName(String dataRangeName) {
		this.dataRangeName = dataRangeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AuthDimensionValueDTO> getAuthDimensionValue() {
		return authDimensionValue;
	}

	public void setAuthDimensionValue(List<AuthDimensionValueDTO> authDimensionValue) {
		this.authDimensionValue = authDimensionValue;
	}

}
