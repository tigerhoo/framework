package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthMenuResourcesDao;
import com.qihai.permission.entity.AuthMenuResourcesEntity;
import com.qihai.permission.service.AuthMenuResourcesService;


@Service("authMenuResourcesService")
public class AuthMenuResourcesServiceImpl extends ServiceImpl<AuthMenuResourcesDao, AuthMenuResourcesEntity> implements AuthMenuResourcesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthMenuResourcesEntity> page = this.selectPage(
                new Query<AuthMenuResourcesEntity>(params).getPage(),
                new EntityWrapper<AuthMenuResourcesEntity>()
        );

        return new PageUtils(page);
    }

}
