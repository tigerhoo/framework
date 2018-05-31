package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthDimensionValueDao;
import com.qihai.permission.entity.AuthDimensionValueEntity;
import com.qihai.permission.service.AuthDimensionValueService;


@Service("authDimensionValueService")
public class AuthDimensionValueServiceImpl extends ServiceImpl<AuthDimensionValueDao, AuthDimensionValueEntity> implements AuthDimensionValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthDimensionValueEntity> page = this.selectPage(
                new Query<AuthDimensionValueEntity>(params).getPage(),
                new EntityWrapper<AuthDimensionValueEntity>()
        );

        return new PageUtils(page);
    }

}
