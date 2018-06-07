package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qihai.permission.dto.GroupRoleDTO;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.entity.AuthGroupEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Mapper
public interface AuthGroupDao extends BaseMapper<AuthGroupEntity> {

	List<UserGroupDTO> listAttachUser(Pagination page,@Param("groupId") Long groupId);

	List<GroupRoleDTO> listAttachRole(Pagination page,@Param("groupId") Long groupId);
	
}
