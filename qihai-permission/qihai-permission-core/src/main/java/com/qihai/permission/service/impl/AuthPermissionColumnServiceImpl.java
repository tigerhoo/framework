package com.qihai.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthPermissionColumnDao;
import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.service.AuthPermissionColumnService;

@Service("authPermissionColumnService")
public class AuthPermissionColumnServiceImpl extends ServiceImpl<AuthPermissionColumnDao, AuthPermissionColumnEntity>
		implements AuthPermissionColumnService {

	@Autowired
	private AuthPermissionColumnDao authPermissionColumnDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthPermissionColumnEntity authPermissionColumn) {
		Page<AuthPermissionColumnEntity> page = this.selectPage(new Query<AuthPermissionColumnEntity>(params).getPage(),
				new EntityWrapper<AuthPermissionColumnEntity>(authPermissionColumn));

		return new PageUtils(page);
	}

	@Override
	public List<AuthPermissionColumnEntity> listByPermissionId(Long permissionId) {
		AuthPermissionColumnEntity authPermissionColumnEntity = new AuthPermissionColumnEntity();
		authPermissionColumnEntity.setPermissionId(permissionId);
		List<AuthPermissionColumnEntity> list = authPermissionColumnDao
				.selectList(new EntityWrapper<AuthPermissionColumnEntity>(authPermissionColumnEntity));
		return list;
	}

}
