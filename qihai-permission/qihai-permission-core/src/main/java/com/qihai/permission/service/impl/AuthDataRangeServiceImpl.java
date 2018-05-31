package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthDataRangeDao;
import com.qihai.permission.entity.AuthDataRangeEntity;
import com.qihai.permission.service.AuthDataRangeService;


@Service("authDataRangeService")
public class AuthDataRangeServiceImpl extends ServiceImpl<AuthDataRangeDao, AuthDataRangeEntity> implements AuthDataRangeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthDataRangeEntity> page = this.selectPage(
                new Query<AuthDataRangeEntity>(params).getPage(),
                new EntityWrapper<AuthDataRangeEntity>()
        );

        return new PageUtils(page);
    }

}
