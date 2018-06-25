package com.qihai.permission.service;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthUserRoleEntity;

import java.util.Map;
import java.util.Set;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthUserRoleService extends IService<AuthUserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params, AuthUserRoleEntity authUserRole);

	Set<Long> listUserAllRole(Long userId);
}

