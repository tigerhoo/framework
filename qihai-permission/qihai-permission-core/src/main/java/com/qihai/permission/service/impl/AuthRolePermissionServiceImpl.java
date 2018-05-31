package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthRolePermissionDao;
import com.qihai.permission.entity.AuthRolePermissionEntity;
import com.qihai.permission.service.AuthRolePermissionService;


@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl extends ServiceImpl<AuthRolePermissionDao, AuthRolePermissionEntity> implements AuthRolePermissionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthRolePermissionEntity> page = this.selectPage(
                new Query<AuthRolePermissionEntity>(params).getPage(),
                new EntityWrapper<AuthRolePermissionEntity>()
        );

        return new PageUtils(page);
    }

}
