package com.qihai.permission.service;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthRolePermissionEntity;

import java.util.Map;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthRolePermissionService extends IService<AuthRolePermissionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

