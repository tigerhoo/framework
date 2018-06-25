package com.qihai.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthRoleDao;
import com.qihai.permission.dto.permission.AuthPermissionDTO;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.dto.permission.AuthPermissionColumnDTO;
import com.qihai.permission.entity.AuthPermissionEntity;
import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.entity.AuthRoleEntity;
import com.qihai.permission.service.AuthPermissionService;
import com.qihai.permission.service.AuthModuleService;
import com.qihai.permission.service.AuthPermissionColumnService;
import com.qihai.permission.service.AuthRoleService;

@Service("authRoleService")
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleDao, AuthRoleEntity> implements AuthRoleService {

	@Autowired
	private AuthModuleService authModuleService;

	@Autowired
	private AuthPermissionService authMenuService;

	@Autowired
	private AuthPermissionColumnService authPermissionColumnService;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthRoleEntity roleEntity) {
		EntityWrapper<AuthRoleEntity> wrapper = new EntityWrapper<AuthRoleEntity>();
		wrapper.setEntity(roleEntity);
		Page<AuthRoleEntity> page = this.selectPage(new Query<AuthRoleEntity>(params).getPage(), wrapper);

		return new PageUtils(page);
	}

	@Override
	public List<AuthModuleDTO> attachPermission(Long id) {
		List<AuthModuleDTO> authModules = authModuleService.listAll();
		for (AuthModuleDTO authModuleDTO : authModules) {
			Long authModuleId = authModuleDTO.getId();
			List<AuthPermissionDTO> authMenus = authModuleDTO.getAuthMenus();
			List<AuthPermissionEntity> authMenuEntitys = authMenuService.listByModuleId(authModuleId);
			for (AuthPermissionEntity authMenuEntity : authMenuEntitys) {
				Long authMenuId = authMenuEntity.getId();
				AuthPermissionDTO authMenu = new AuthPermissionDTO();
				authMenu.setId(authMenuEntity.getId());
				authMenu.setMenuName(authMenuEntity.getMenuName());
				authMenu.setIsAuth(authMenuEntity.getIsAuth());
				authMenu.setMethodCode(authMenuEntity.getMethodCode());
				authMenu.setServiceCode(authMenuEntity.getServiceCode());
				authMenu.setUrl(authMenuEntity.getUrl());
				authMenu.setVersion(authMenuEntity.getVersion());
				
				List<AuthPermissionColumnEntity> list = authPermissionColumnService.listByPermissionId(authMenuId);
				List<AuthPermissionColumnDTO> authPermissionColumns=authMenu.getAuthPermissionColumns();
				for (AuthPermissionColumnEntity authPermissionColumnEntity : list) {
					AuthPermissionColumnDTO authPermissionColumnDTO=new AuthPermissionColumnDTO();
					authPermissionColumnDTO.setId(authPermissionColumnEntity.getId());
					authPermissionColumnDTO.setName(authPermissionColumnEntity.getName());
					authPermissionColumns.add(authPermissionColumnDTO);
				}
				
				authMenus.add(authMenu);
			}
		}

		return authModules;
	}

}
