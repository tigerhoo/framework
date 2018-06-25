package com.qihai.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthPermissionDao;
import com.qihai.permission.dto.menu.AuthUserPermissionDTO;
import com.qihai.permission.dto.permission.AuthPermissionModuleDTO;
import com.qihai.permission.entity.AuthPermissionEntity;
import com.qihai.permission.service.AuthPermissionService;
import com.qihai.permission.service.AuthUserRoleService;

@Service("authMenuService")
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionDao, AuthPermissionEntity>
		implements AuthPermissionService {

	@Autowired
	private AuthPermissionDao authPermissionDao;

	@Autowired
	private AuthUserRoleService authUserRoleService;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthPermissionEntity authMenu) {
		Page<AuthPermissionEntity> page = this.selectPage(new Query<AuthPermissionEntity>(params).getPage(),
				new EntityWrapper<AuthPermissionEntity>(authMenu));
		return new PageUtils(page);
	}

	@Override
	public List<AuthPermissionEntity> listByModuleId(Long moduleId) {
		AuthPermissionEntity authMenuEntity = new AuthPermissionEntity();
		authMenuEntity.setModuleId(moduleId);
		List<AuthPermissionEntity> list = authPermissionDao
				.selectList(new EntityWrapper<AuthPermissionEntity>(authMenuEntity));
		return list;
	}

	@Override
	public List<AuthUserPermissionDTO> listUserPermission(Long userId) {
		Set<Long> roleIds = authUserRoleService.listUserAllRole(userId);
		List<AuthUserPermissionDTO> authPermissions = authPermissionDao.listUserPermissionByRoleIds(roleIds);
		return authPermissions;
	}

	@Override
	public Page<AuthPermissionModuleDTO> listPermission(Page<AuthPermissionModuleDTO> pages,
			AuthPermissionModuleDTO permissionMoudule) {

		Page<AuthPermissionModuleDTO> page = pages
				.setRecords(authPermissionDao.selectPermission(pages, permissionMoudule));

		return page;
	}

}
