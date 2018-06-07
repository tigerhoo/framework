package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.UserInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

	List<UserRoleDTO> listUserRole(Pagination page, @Param("userId") Long userId);

}
