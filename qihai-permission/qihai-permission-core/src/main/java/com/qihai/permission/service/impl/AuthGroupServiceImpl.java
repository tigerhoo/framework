package com.qihai.permission.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.AuthGroupDao;
import com.qihai.permission.dto.GroupRoleDTO;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.AuthGroupEntity;
import com.qihai.permission.service.AuthGroupService;


@Service("authGroupService")
public class AuthGroupServiceImpl extends ServiceImpl<AuthGroupDao, AuthGroupEntity> implements AuthGroupService {

	@Autowired
	private AuthGroupDao authGroupDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params,AuthGroupEntity authGroup) {
        Page<AuthGroupEntity> page = this.selectPage(
                new Query<AuthGroupEntity>(params).getPage(),
                new EntityWrapper<AuthGroupEntity>(authGroup)
        );

        return new PageUtils(page);
    }

	@Override
	public Page<UserGroupDTO> listAttachUser(Page<UserGroupDTO> page, Long groupId) {
		return page.setRecords(authGroupDao.listAttachUser(page,groupId));
	}

	@Override
	public Page<GroupRoleDTO> listAttachRole(Page<GroupRoleDTO> pages, Long groupId) {
	
		return pages.setRecords(authGroupDao.listAttachRole(pages,groupId));
	}

}
