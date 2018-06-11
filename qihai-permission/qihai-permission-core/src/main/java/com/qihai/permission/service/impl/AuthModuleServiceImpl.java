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
import com.qihai.permission.dao.AuthModuleDao;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.entity.AuthModuleEntity;
import com.qihai.permission.service.AuthModuleService;

@Service("authModuleService")
public class AuthModuleServiceImpl extends ServiceImpl<AuthModuleDao, AuthModuleEntity> implements AuthModuleService {

	@Autowired
	private AuthModuleDao authModuleDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params, AuthModuleEntity authModuleEntity) {
		Page<AuthModuleEntity> page = this.selectPage(new Query<AuthModuleEntity>(params).getPage(),
				new EntityWrapper<AuthModuleEntity>(authModuleEntity));
		return new PageUtils(page);
	}

	@Override
	public List<AuthModuleDTO> listAll() {
		return authModuleDao.listAll();
	}

}
