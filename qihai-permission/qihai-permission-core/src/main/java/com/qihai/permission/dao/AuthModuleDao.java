package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.entity.AuthModuleEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Mapper
public interface AuthModuleDao extends BaseMapper<AuthModuleEntity> {

	List<AuthModuleDTO> listAll();

}
