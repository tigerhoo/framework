package com.qihai.permission.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.entity.AuthRoleEntity;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthRoleService extends IService<AuthRoleEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthRoleEntity roleEntity);

	List<AuthModuleDTO> attachPermission(Long id);

}

