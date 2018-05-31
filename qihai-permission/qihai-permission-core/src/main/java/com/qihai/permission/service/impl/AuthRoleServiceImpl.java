package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthRoleDao;
import com.qihai.permission.entity.AuthRoleEntity;
import com.qihai.permission.service.AuthRoleService;


@Service("authRoleService")
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleDao, AuthRoleEntity> implements AuthRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthRoleEntity> page = this.selectPage(
                new Query<AuthRoleEntity>(params).getPage(),
                new EntityWrapper<AuthRoleEntity>()
        );

        return new PageUtils(page);
    }

}
