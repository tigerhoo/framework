package com.qihai.permission.dto.permission;

import java.util.List;

public class AuthRolePermissionDTO {

	private Long roleId;

	private List<PermissionDTO> addList;

	private List<PermissionDTO> deleteList;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<PermissionDTO> getAddList() {
		return addList;
	}

	public void setAddList(List<PermissionDTO> addList) {
		this.addList = addList;
	}

	public List<PermissionDTO> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<PermissionDTO> deleteList) {
		this.deleteList = deleteList;
	}

}
