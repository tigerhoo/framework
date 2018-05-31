package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthDimensionEntity> page = this.selectPage(
                new Query<AuthDimensionEntity>(params).getPage(),
                new EntityWrapper<AuthDimensionEntity>()
        );

        return new PageUtils(page);
    }

}
