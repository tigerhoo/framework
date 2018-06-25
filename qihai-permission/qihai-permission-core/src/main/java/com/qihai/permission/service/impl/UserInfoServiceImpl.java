package com.qihai.permission.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.Query;
import com.qihai.permission.dao.UserInfoDao;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.UserInfoService;
import com.qihai.permission.vo.UserInfoVO;

@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;

	@Override
    public PageUtils queryPage(Map<String, Object> params,UserInfoEntity userInfo) {
    	Page<UserInfoEntity> page = this.selectPage(
           new Query<UserInfoEntity>(params).getPage(), new EntityWrapper<UserInfoEntity>(userInfo)
        );

        return new PageUtils(page);
    }

	@Override
	public Page<UserRoleDTO> listUserRole(Page<UserRoleDTO> page, Long id) {
		Page<UserRoleDTO> pages = page.setRecords(userInfoDao.listUserRole(page,id));
		return pages;
	}

	@Override
	public Page<UserInfoVO> listUserInfo(Page<UserInfoVO> pages,UserInfoEntity userInfo) {
		
		return pages.setRecords(userInfoDao.listUserInfo(pages,userInfo));
		
	}

}
