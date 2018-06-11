package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthRolePermissionColumnDao;
import com.qihai.permission.entity.AuthRolePermissionColumnEntity;
import com.qihai.permission.service.AuthRolePermissionColumnService;


@Service("authRolePermissionColumnService")
public class AuthRolePermissionColumnServiceImpl extends ServiceImpl<AuthRolePermissionColumnDao, AuthRolePermissionColumnEntity> implements AuthRolePermissionColumnService {

    @Override
    public PageUtils queryPage(Map<String, Object> params,AuthRolePermissionColumnEntity authRolePermissionColumn) {
        Page<AuthRolePermissionColumnEntity> page = this.selectPage(
                new Query<AuthRolePermissionColumnEntity>(params).getPage(),
                new EntityWrapper<AuthRolePermissionColumnEntity>(authRolePermissionColumn)
        );

        return new PageUtils(page);
    }

}
