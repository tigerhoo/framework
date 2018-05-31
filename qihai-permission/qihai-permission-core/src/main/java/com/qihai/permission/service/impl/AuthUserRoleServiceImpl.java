package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthUserRoleDao;
import com.qihai.permission.entity.AuthUserRoleEntity;
import com.qihai.permission.service.AuthUserRoleService;


@Service("authUserRoleService")
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleDao, AuthUserRoleEntity> implements AuthUserRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthUserRoleEntity> page = this.selectPage(
                new Query<AuthUserRoleEntity>(params).getPage(),
                new EntityWrapper<AuthUserRoleEntity>()
        );

        return new PageUtils(page);
    }

}
