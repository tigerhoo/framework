package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
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
public class AuthPermissionColumnServiceImpl extends ServiceImpl<AuthPermissionColumnDao, AuthPermissionColumnEntity> implements AuthPermissionColumnService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthPermissionColumnEntity> page = this.selectPage(
                new Query<AuthPermissionColumnEntity>(params).getPage(),
                new EntityWrapper<AuthPermissionColumnEntity>()
        );

        return new PageUtils(page);
    }

}
