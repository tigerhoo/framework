package com.qihai.permission.service;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.permission.dto.permission.AuthPermissionDTO;
import com.qihai.permission.dto.permission.AuthRolePermissionDTO;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.dto.permission.AuthPermissionColumnDTO;
import com.qihai.permission.entity.AuthRolePermissionColumnEntity;
import com.qihai.permission.entity.AuthRolePermissionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthRolePermissionService extends IService<AuthRolePermissionEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthRolePermissionEntity authRolePermission);

	List<AuthPermissionDTO> listRolePermission(Long roleId, Long moduleId);

	List<AuthPermissionColumnDTO> listPermissionColumns(Long roleId, Long moduleId, Long permissionId);
	
	//void saveRolePermission(Long roleId, List<AuthModuleDTO> authModules);

	void saveOrUpdate(AuthRolePermissionDTO rolePermission);
	
}

