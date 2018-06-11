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
import com.qihai.permission.dto.permission.AuthMenuDTO;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.dto.permission.AuthPermissionColumnDTO;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.entity.AuthRoleEntity;
import com.qihai.permission.service.AuthMenuService;
import com.qihai.permission.service.AuthModuleService;
import com.qihai.permission.service.AuthPermissionColumnService;
import com.qihai.permission.service.AuthRoleService;

@Service("authRoleService")
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleDao, AuthRoleEntity> implements AuthRoleService {

	@Autowired
	private AuthModuleService authModuleService;

	@Autowired
	private AuthMenuService authMenuService;

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
			List<AuthMenuDTO> authMenus = authModuleDTO.getAuthMenus();
			List<AuthMenuEntity> authMenuEntitys = authMenuService.listByModuleId(authModuleId);
			for (AuthMenuEntity authMenuEntity : authMenuEntitys) {
				Long authMenuId = authMenuEntity.getId();
				AuthMenuDTO authMenu = new AuthMenuDTO();
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
