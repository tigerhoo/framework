package com.qihai.commerce.framework.dao;

import org.springframework.stereotype.Repository;

import com.qihai.commerce.framework.domain.RealtorAdmin;

@Repository
public interface RealtorAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RealtorAdmin record);

    int insertSelective(RealtorAdmin record);

    RealtorAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RealtorAdmin record);

    int updateByPrimaryKey(RealtorAdmin record);
}