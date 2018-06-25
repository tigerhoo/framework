package com.qihai.permission.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qihai.permission.entity.AuthMenuEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Mapper
public interface AuthMenuDao extends BaseMapper<AuthMenuEntity> {

	List<AuthMenuEntity> listMenu(Long parentId);

	List<AuthMenuEntity> listMenusByRoleIds(@Param("roleIds")Set<Long> roleIds);

}
