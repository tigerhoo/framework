package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthGroupRoleDao;
import com.qihai.permission.entity.AuthGroupRoleEntity;
import com.qihai.permission.service.AuthGroupRoleService;


@Service("authGroupRoleService")
public class AuthGroupRoleServiceImpl extends ServiceImpl<AuthGroupRoleDao, AuthGroupRoleEntity> implements AuthGroupRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthGroupRoleEntity> page = this.selectPage(
                new Query<AuthGroupRoleEntity>(params).getPage(),
                new EntityWrapper<AuthGroupRoleEntity>()
        );

        return new PageUtils(page);
    }

}
