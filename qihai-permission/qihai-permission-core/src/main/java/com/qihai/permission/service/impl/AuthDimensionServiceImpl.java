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

import com.qihai.permission.dao.AuthDimensionDao;
import com.qihai.permission.entity.AuthDimensionEntity;
import com.qihai.permission.service.AuthDimensionService;


@Service("authDimensionService")
public class AuthDimensionServiceImpl extends ServiceImpl<AuthDimensionDao, AuthDimensionEntity> implements AuthDimensionService {

	@Autowired
	private AuthDimensionDao authDimensionDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params,AuthDimensionEntity authDimensiony) {
        Page<AuthDimensionEntity> page = this.selectPage(
                new Query<AuthDimensionEntity>(params).getPage(),
                new EntityWrapper<AuthDimensionEntity>(authDimensiony)
        );

        return new PageUtils(page);
    }

	@Override
	public List<AuthDimensionEntity> listAll() {
		List<AuthDimensionEntity> list = authDimensionDao.listAll();
		return list;
	}

}
