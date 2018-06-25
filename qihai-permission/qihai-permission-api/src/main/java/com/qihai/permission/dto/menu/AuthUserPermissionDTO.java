package com.qihai.permission.dto.menu;

import com.qihai.permission.entity.AuthPermissionEntity;

public class AuthUserPermissionDTO extends AuthPermissionEntity {

	private static final long serialVersionUID = 1L;

	private String moduleName;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
