package com.qihai.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qihai.permission.entity.AuthDimensionValueScopeEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Mapper
public interface AuthDimensionValueScopeDao extends BaseMapper<AuthDimensionValueScopeEntity> {

	List<String> getDimensionValue(@Param("dataRangeId") Long dataRangeId, @Param("dimensionId") Long dimensionId);

}
