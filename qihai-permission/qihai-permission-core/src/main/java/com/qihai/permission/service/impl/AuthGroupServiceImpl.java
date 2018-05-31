package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthGroupDao;
import com.qihai.permission.entity.AuthGroupEntity;
import com.qihai.permission.service.AuthGroupService;


@Service("authGroupService")
public class AuthGroupServiceImpl extends ServiceImpl<AuthGroupDao, AuthGroupEntity> implements AuthGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthGroupEntity> page = this.selectPage(
                new Query<AuthGroupEntity>(params).getPage(),
                new EntityWrapper<AuthGroupEntity>()
        );

        return new PageUtils(page);
    }

}
