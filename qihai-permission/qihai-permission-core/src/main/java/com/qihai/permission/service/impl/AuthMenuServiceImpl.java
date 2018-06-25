package com.qihai.permission.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthMenuDao;
import com.qihai.permission.dao.AuthPermissionDao;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.entity.AuthPermissionEntity;
import com.qihai.permission.service.AuthMenuService;
import com.qihai.permission.vo.menu.AuthMenuVO;
import com.qihai.permission.vo.menu.AuthPermissionVO;

@Service("authMenuResourcesService")
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuDao, AuthMenuEntity> implements AuthMenuService {

	@Autowired
	private AuthMenuDao authMenuDao;

	@Autowired
	private AuthPermissionDao authPermissionDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AuthMenuEntity> page = this.selectPage(new Query<AuthMenuEntity>(params).getPage(),
				new EntityWrapper<AuthMenuEntity>());

		return new PageUtils(page);
	}

	@Override
	public List<AuthMenuEntity> listMenu(Long parentId) {
		return authMenuDao.listMenu(parentId);
	}

	@Override
	public List<AuthMenuEntity> listMenusByRoleIds(Set<Long> roleIds) {
		List<AuthMenuEntity> listMenus = authMenuDao.listMenusByRoleIds(roleIds);
		return listMenus;
	}

	@Override
	public List<AuthMenuVO> listAllPermissionByRoleIds(Set<Long> roleIds) {
		List<AuthMenuVO> menuVos = new ArrayList<AuthMenuVO>();
		if (roleIds != null && roleIds.size() > 0) {
			List<AuthMenuEntity> listMenus = authMenuDao.listMenusByRoleIds(roleIds);
			if (listMenus != null && listMenus.size() > 0) {
				for (AuthMenuEntity authMenuEntity : listMenus) {
					AuthMenuVO authMenuVo = new AuthMenuVO();
					try {
						BeanUtils.copyProperties(authMenuVo, authMenuEntity);
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<AuthPermissionEntity> permissions = authPermissionDao.listUserAllPermission(roleIds,
							authMenuEntity.getId());
					for (AuthPermissionEntity authPermissionEntity : permissions) {
						AuthPermissionVO apVo=new AuthPermissionVO();
						try {
							BeanUtils.copyProperties(apVo, authPermissionEntity);
						} catch (Exception e) {
							e.printStackTrace();
						} 
						
						authMenuVo.getAuthPermission().add(apVo);
					}
					menuVos.add(authMenuVo);
				}
			}
		}
		return menuVos;
	}

}
