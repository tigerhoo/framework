package com.qihai.permission.dto.permission;

import java.util.List;

public class PermissionDTO {

	private Long permissionId;

	private List<Long> permissionColumnIds;

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public List<Long> getPermissionColumnIds() {
		return permissionColumnIds;
	}

	public void setPermissionColumnIds(List<Long> permissionColumnIds) {
		this.permissionColumnIds = permissionColumnIds;
	}

}
