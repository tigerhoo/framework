package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qihai.permission.entity.AuthDimensionEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Mapper
public interface AuthDimensionDao extends BaseMapper<AuthDimensionEntity> {

	List<AuthDimensionEntity> listAll();
	
}
