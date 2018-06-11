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

import com.qihai.permission.dao.AuthMenuDao;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.service.AuthMenuService;

@Service("authMenuService")
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuDao, AuthMenuEntity> implements AuthMenuService {

	@Autowired
	private AuthMenuDao authMenuDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthMenuEntity authMenu) {
		Page<AuthMenuEntity> page = this.selectPage(new Query<AuthMenuEntity>(params).getPage(),
				new EntityWrapper<AuthMenuEntity>(authMenu));
		return new PageUtils(page);
	}

	@Override
	public List<AuthMenuEntity> listByModuleId(Long moduleId) {
		AuthMenuEntity authMenuEntity = new AuthMenuEntity();
		authMenuEntity.setModuleId(moduleId);
		List<AuthMenuEntity> list = authMenuDao.selectList(new EntityWrapper<AuthMenuEntity>(authMenuEntity));
		return list;
	}

}
