package com.qihai.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthRolePermissionDao;
import com.qihai.permission.dto.permission.AuthMenuDTO;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.dto.permission.AuthPermissionColumnDTO;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.entity.AuthRolePermissionColumnEntity;
import com.qihai.permission.entity.AuthRolePermissionEntity;
import com.qihai.permission.service.AuthMenuService;
import com.qihai.permission.service.AuthPermissionColumnService;
import com.qihai.permission.service.AuthRolePermissionColumnService;
import com.qihai.permission.service.AuthRolePermissionService;

@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl extends ServiceImpl<AuthRolePermissionDao, AuthRolePermissionEntity>
		implements AuthRolePermissionService {

	@Autowired
	private AuthMenuService authMenuService;

	@Autowired
	private AuthPermissionColumnService authPermissionColumnService;

	@Autowired
	private AuthRolePermissionColumnService authRolePermissionColumnService;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthRolePermissionEntity authRolePermission) {
		Page<AuthRolePermissionEntity> page = this.selectPage(new Query<AuthRolePermissionEntity>(params).getPage(),
				new EntityWrapper<AuthRolePermissionEntity>(authRolePermission));

		return new PageUtils(page);
	}

	public List<AuthRolePermissionEntity> listAll(AuthRolePermissionEntity authRolePermission) {
		List<AuthRolePermissionEntity> list = this
				.selectList(new EntityWrapper<AuthRolePermissionEntity>(authRolePermission));
		return list;
	}

	@Override
	public List<AuthMenuDTO> listRolePermission(Long roleId, Long moduleId) {
		List<AuthMenuEntity> authMenuEntitys = authMenuService.listByModuleId(moduleId);
		List<AuthMenuDTO> authMenus = new ArrayList<AuthMenuDTO>();
		if (authMenuEntitys != null && authMenuEntitys.size() > 0) {
			for (AuthMenuEntity authMenuEntity : authMenuEntitys) {
				AuthMenuDTO authMenu = new AuthMenuDTO();
				authMenu.setId(authMenuEntity.getId());
				authMenu.setIsAuth(authMenuEntity.getIsAuth());
				authMenu.setMenuName(authMenuEntity.getMenuName());
				authMenu.setMethodCode(authMenuEntity.getMethodCode());
				authMenu.setServiceCode(authMenuEntity.getServiceCode());
				authMenu.setUrl(authMenuEntity.getUrl());
				authMenu.setVersion(authMenuEntity.getVersion());

				AuthRolePermissionEntity authRolePermission = new AuthRolePermissionEntity();
				authRolePermission.setRoleId(roleId);
				authRolePermission.setPermissionId(authMenuEntity.getId());
				List<AuthRolePermissionEntity> list = this.listAll(authRolePermission);
				if (list != null && list.size() > 0) {
					authMenu.setFlag(true);
				} else {
					authMenu.setFlag(false);
				}
				authMenus.add(authMenu);
			}
		}
		return authMenus;
	}

	@Override
	public List<AuthPermissionColumnDTO> listPermissionColumns(Long roleId, Long moduleId, Long permissionId) {

		List<AuthPermissionColumnEntity> permissionColumns = authPermissionColumnService
				.listByPermissionId(permissionId);
		List<AuthPermissionColumnDTO> authPermissionColumns = new ArrayList<AuthPermissionColumnDTO>();
		if (permissionColumns != null && permissionColumns.size() > 0) {
			for (AuthPermissionColumnEntity permissionColumn : permissionColumns) {

				AuthPermissionColumnDTO authPermissionColumn = new AuthPermissionColumnDTO();
				authPermissionColumn.setId(permissionColumn.getId());
				authPermissionColumn.setName(permissionColumn.getName());

				AuthRolePermissionColumnEntity authRolePermissionColumn = new AuthRolePermissionColumnEntity();
				authRolePermissionColumn.setRoleId(roleId);
				authRolePermissionColumn.setPermissionId(permissionId);
				authRolePermissionColumn.setPermissionColumnId(permissionColumn.getId());

				List<AuthRolePermissionColumnEntity> rolePermissionColumns = authRolePermissionColumnService
						.selectList(new EntityWrapper<AuthRolePermissionColumnEntity>(authRolePermissionColumn));
				if (rolePermissionColumns != null && rolePermissionColumns.size() > 0) {
					authPermissionColumn.setFlag(true);
				} else {
					authPermissionColumn.setFlag(false);
				}
				authPermissionColumns.add(authPermissionColumn);
			}
		}

		return authPermissionColumns;
	}

	@Override
	@Transactional
	public void saveRolePermission(Long roleId, List<AuthModuleDTO> authModules) {
		List<AuthRolePermissionEntity> authRolePermissions = new ArrayList<AuthRolePermissionEntity>();
		List<AuthRolePermissionColumnEntity> authRolePermissionColumns = new ArrayList<AuthRolePermissionColumnEntity>();
		for (AuthModuleDTO authModuleDTO : authModules) {
			List<AuthMenuDTO> authMenus = authModuleDTO.getAuthMenus();
			if (authMenus != null && authMenus.size() > 0) {
				for (AuthMenuDTO authMenuDTO : authMenus) {
					Long permissionId = authMenuDTO.getId();
					AuthRolePermissionEntity authRolePermission = new AuthRolePermissionEntity();
					authRolePermission.setRoleId(roleId);
					authRolePermission.setPermissionId(permissionId);
					authRolePermissions.add(authRolePermission);
					List<AuthPermissionColumnDTO> authPermissionColumns = authMenuDTO.getAuthPermissionColumns();
					if (authPermissionColumns != null && authPermissionColumns.size() > 0) {
						for (AuthPermissionColumnDTO authPermissionColumnDTO : authPermissionColumns) {
							Long permissionColumnId = authPermissionColumnDTO.getId();
							if (permissionColumnId != null) {
								AuthRolePermissionColumnEntity authRolePermissionColumn = new AuthRolePermissionColumnEntity();
								authRolePermissionColumn.setRoleId(roleId);
								authRolePermissionColumn.setPermissionColumnId(permissionColumnId);
								authRolePermissionColumn.setPermissionId(permissionId);
								authRolePermissionColumns.add(authRolePermissionColumn);
							}
						}
					}
				}
			}
		}

		if (authRolePermissions != null && authRolePermissions.size() > 0) {
			this.insertBatch(authRolePermissions);
		}
		if (authRolePermissionColumns != null && authRolePermissionColumns.size() > 0) {
			authRolePermissionColumnService.insertBatch(authRolePermissionColumns);
		}

	}

}
