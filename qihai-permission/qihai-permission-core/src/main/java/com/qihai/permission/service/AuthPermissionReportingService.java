package com.qihai.permission.service;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthPermissionReportingEntity;

import java.util.Map;

/**
 * 微服务上报管理
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthPermissionReportingService extends IService<AuthPermissionReportingEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthPermissionReportingEntity authPermissionReporting);
}

