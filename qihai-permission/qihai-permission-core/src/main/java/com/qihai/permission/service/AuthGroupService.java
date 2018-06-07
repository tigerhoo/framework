package com.qihai.permission.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.dto.GroupRoleDTO;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.AuthGroupEntity;

import java.util.Map;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
public interface AuthGroupService extends IService<AuthGroupEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthGroupEntity authGroup);

	Page<UserGroupDTO> listAttachUser(Page<UserGroupDTO> page, Long groupId);

	Page<GroupRoleDTO> listAttachRole(Page<GroupRoleDTO> pages, Long groupId);
}

