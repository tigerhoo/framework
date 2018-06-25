package com.qihai.permission.dto.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.permission.entity.AuthMenuEntity;

public class AuthMenuDTO {

	private AuthMenuEntity authMenuEntity;
	private List<AuthMenuDTO> children = new ArrayList<AuthMenuDTO>();

	public AuthMenuEntity getAuthMenuEntity() {
		return authMenuEntity;
	}

	public void setAuthMenuEntity(AuthMenuEntity authMenuEntity) {
		this.authMenuEntity = authMenuEntity;
	}

	public List<AuthMenuDTO> getChildren() {
		return children;
	}

	public void setChildren(List<AuthMenuDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
