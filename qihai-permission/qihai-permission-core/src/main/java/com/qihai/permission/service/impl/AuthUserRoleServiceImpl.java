package com.qihai.permission.service.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthUserRoleDao;
import com.qihai.permission.entity.AuthUserRoleEntity;
import com.qihai.permission.service.AuthUserRoleService;

@Service("authUserRoleService")
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleDao, AuthUserRoleEntity>
		implements AuthUserRoleService {

	@Autowired
	private AuthUserRoleDao authUserRoleDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthUserRoleEntity authUserRole) {
		EntityWrapper<AuthUserRoleEntity> wrapper = new EntityWrapper<AuthUserRoleEntity>();
		wrapper.setEntity(authUserRole);
		Page<AuthUserRoleEntity> page = this.selectPage(new Query<AuthUserRoleEntity>(params).getPage(), wrapper);

		return new PageUtils(page);
	}

	@Override
	public Set<Long> listUserAllRole(Long userId) {
		return authUserRoleDao.listUserAllRole(userId);
	}

}
