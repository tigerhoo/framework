package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.entity.AuthUserGroupEntity;
import com.qihai.permission.entity.UserInfoEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Mapper
public interface AuthUserGroupDao extends BaseMapper<AuthUserGroupEntity> {
	
	
	public List<UserInfoEntity> selectByUserIdOrGroupId(Pagination page, AuthUserGroupEntity userGroup);
	
}
