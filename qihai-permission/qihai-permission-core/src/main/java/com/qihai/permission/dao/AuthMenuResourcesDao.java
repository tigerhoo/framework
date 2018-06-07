package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qihai.permission.entity.AuthMenuResourcesEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Mapper
public interface AuthMenuResourcesDao extends BaseMapper<AuthMenuResourcesEntity> {

	List<AuthMenuResourcesEntity> listMenu(Long parentId);
	
}
