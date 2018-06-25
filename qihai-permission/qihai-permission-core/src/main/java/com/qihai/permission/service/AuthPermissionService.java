package com.qihai.permission.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.dto.menu.AuthUserPermissionDTO;
import com.qihai.permission.dto.permission.AuthPermissionModuleDTO;
import com.qihai.permission.entity.AuthPermissionEntity;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
public interface AuthPermissionService extends IService<AuthPermissionEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthPermissionEntity authMenu);
        
    List<AuthPermissionEntity> listByModuleId(Long moduleId);

	List<AuthUserPermissionDTO> listUserPermission(Long userId);

	Page<AuthPermissionModuleDTO> listPermission(Page<AuthPermissionModuleDTO> pages,
			AuthPermissionModuleDTO permissionMoudule);

	
}

