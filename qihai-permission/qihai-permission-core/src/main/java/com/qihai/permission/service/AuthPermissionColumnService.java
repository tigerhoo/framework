package com.qihai.permission.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthPermissionColumnEntity;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthPermissionColumnService extends IService<AuthPermissionColumnEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthPermissionColumnEntity authPermissionColumn);

    List<AuthPermissionColumnEntity> listByPermissionId(Long permissionId);
}

