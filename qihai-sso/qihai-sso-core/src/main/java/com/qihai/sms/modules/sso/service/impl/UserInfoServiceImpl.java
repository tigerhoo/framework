package com.qihai.sms.modules.sso.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qihai.commerce.framework.vo.UserResources;
import com.qihai.sms.modules.sso.dao.UserInfoDao;
import com.qihai.sms.modules.sso.entity.UserInfoEntity;
import com.qihai.sms.modules.sso.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;

	/**
     * 根据userId查找能访问的资源
     *
     * @param userId  4975140289935036
     * @return
     */
	@Override
	public List<UserResources> getUserResourcesById(String userId) {
		List<UserResources> result = null;
        List<Long> roleIds = new ArrayList<>(getRoleIdsByUserId(Long.parseLong(userId)));
        if (!CollectionUtils.isEmpty(roleIds)) {
            Long[] roleArr = roleIds.toArray(new Long[roleIds.size()]);
            result = userInfoDao.selectPermissionByRoleIds(roleArr);
        }
        return result;
	}
	
	/**
     * 根据用户ID获取所有的角色ID
     * @param userId
     * @return
     */
    private Set<Long> getRoleIdsByUserId(Long userId) {
        Set<Long> allRoles = new HashSet<>();
        //  1) user --> role
        List<Long> userRoles = userInfoDao.getUerRoles(userId);
        allRoles.addAll(userRoles);
        //  2) user --> group --> role
        List<Long> userGroupRoles = userInfoDao.getUserGroupRoles(userId);
        allRoles.addAll(userGroupRoles);
        return allRoles;
    }
}
