package com.qihai.permission.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qihai.permission.entity.AuthPermissionReportingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 微服务上报管理
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Mapper
public interface AuthPermissionReportingDao extends BaseMapper<AuthPermissionReportingEntity> {
	
}
