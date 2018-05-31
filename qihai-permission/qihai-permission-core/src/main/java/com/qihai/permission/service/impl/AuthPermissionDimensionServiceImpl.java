package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthPermissionDimensionDao;
import com.qihai.permission.entity.AuthPermissionDimensionEntity;
import com.qihai.permission.service.AuthPermissionDimensionService;


@Service("authPermissionDimensionService")
public class AuthPermissionDimensionServiceImpl extends ServiceImpl<AuthPermissionDimensionDao, AuthPermissionDimensionEntity> implements AuthPermissionDimensionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthPermissionDimensionEntity> page = this.selectPage(
                new Query<AuthPermissionDimensionEntity>(params).getPage(),
                new EntityWrapper<AuthPermissionDimensionEntity>()
        );

        return new PageUtils(page);
    }

}
