package com.qihai.permission.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthDimensionValueDTO {

	private Long authDimensionId;

	private String dimensionName;

	private List<AuthDimensionValueScopeDTO> dimensionValueScopes=new ArrayList<AuthDimensionValueScopeDTO>();

	public Long getAuthDimensionId() {
		return authDimensionId;
	}

	public void setAuthDimensionId(Long authDimensionId) {
		this.authDimensionId = authDimensionId;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public List<AuthDimensionValueScopeDTO> getDimensionValueScopes() {
		return dimensionValueScopes;
	}

	public void setDimensionValueScopes(List<AuthDimensionValueScopeDTO> dimensionValueScopes) {
		this.dimensionValueScopes = dimensionValueScopes;
	}

}
