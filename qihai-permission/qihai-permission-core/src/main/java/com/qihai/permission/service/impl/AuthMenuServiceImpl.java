package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthMenuDao;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.service.AuthMenuService;


@Service("authMenuService")
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuDao, AuthMenuEntity> implements AuthMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthMenuEntity> page = this.selectPage(
                new Query<AuthMenuEntity>(params).getPage(),
                new EntityWrapper<AuthMenuEntity>()
        );

        return new PageUtils(page);
    }

}
