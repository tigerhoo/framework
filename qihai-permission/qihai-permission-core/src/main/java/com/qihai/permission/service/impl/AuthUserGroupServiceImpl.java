package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthUserGroupDao;
import com.qihai.permission.entity.AuthUserGroupEntity;
import com.qihai.permission.service.AuthUserGroupService;


@Service("authUserGroupService")
public class AuthUserGroupServiceImpl extends ServiceImpl<AuthUserGroupDao, AuthUserGroupEntity> implements AuthUserGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthUserGroupEntity> page = this.selectPage(
                new Query<AuthUserGroupEntity>(params).getPage(),
                new EntityWrapper<AuthUserGroupEntity>()
        );

        return new PageUtils(page);
    }

}
