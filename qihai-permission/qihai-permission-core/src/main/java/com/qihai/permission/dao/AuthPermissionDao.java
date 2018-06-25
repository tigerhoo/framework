package com.qihai.permission.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
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
@Mapper
public interface AuthPermissionDao extends BaseMapper<AuthPermissionEntity> {

	List<AuthUserPermissionDTO> listUserPermissionByRoleIds(@Param("roleIds") Set<Long> roleIds);

	List<AuthPermissionModuleDTO> selectPermission(Pagination page, AuthPermissionModuleDTO permissionMoudule);

	List<AuthPermissionEntity> listUserAllPermission(@Param("roleIds") Set<Long> roleIds,
			@Param("authMenuId") Long authMenuId);
}
