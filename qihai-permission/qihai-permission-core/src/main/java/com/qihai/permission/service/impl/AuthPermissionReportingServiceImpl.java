package com.qihai.permission.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;

import com.qihai.permission.dao.AuthPermissionReportingDao;
import com.qihai.permission.entity.AuthPermissionReportingEntity;
import com.qihai.permission.service.AuthPermissionReportingService;


@Service("authPermissionReportingService")
public class AuthPermissionReportingServiceImpl extends ServiceImpl<AuthPermissionReportingDao, AuthPermissionReportingEntity> implements AuthPermissionReportingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params,AuthPermissionReportingEntity authPermissionReporting) {
        Page<AuthPermissionReportingEntity> page = this.selectPage(
                new Query<AuthPermissionReportingEntity>(params).getPage(),
                new EntityWrapper<AuthPermissionReportingEntity>(authPermissionReporting)
        );

        return new PageUtils(page);
    }

}
