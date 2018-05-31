package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthDimensionValueScopeDao;
import com.qihai.permission.entity.AuthDimensionValueScopeEntity;
import com.qihai.permission.service.AuthDimensionValueScopeService;


@Service("authDimensionValueScopeService")
public class AuthDimensionValueScopeServiceImpl extends ServiceImpl<AuthDimensionValueScopeDao, AuthDimensionValueScopeEntity> implements AuthDimensionValueScopeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthDimensionValueScopeEntity> page = this.selectPage(
                new Query<AuthDimensionValueScopeEntity>(params).getPage(),
                new EntityWrapper<AuthDimensionValueScopeEntity>()
        );

        return new PageUtils(page);
    }

}
