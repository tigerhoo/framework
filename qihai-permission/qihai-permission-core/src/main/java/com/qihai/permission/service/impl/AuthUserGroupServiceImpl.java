package com.qihai.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthUserGroupDao;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.entity.AuthUserGroupEntity;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.AuthUserGroupService;


@Service("authUserGroupService")
public class AuthUserGroupServiceImpl extends ServiceImpl<AuthUserGroupDao, AuthUserGroupEntity> implements AuthUserGroupService {

	@Autowired
	private AuthUserGroupDao authUserGroupDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthUserGroupEntity> page = this.selectPage(
                new Query<AuthUserGroupEntity>(params).getPage(),
                new EntityWrapper<AuthUserGroupEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public Page<UserInfoEntity> selectByUserIdOrGroupId(Page<UserInfoEntity> page,AuthUserGroupEntity userGroup) {
		Page<UserInfoEntity> pages = page.setRecords(authUserGroupDao.selectByUserIdOrGroupId(page,userGroup));
		return pages;
	}

}
