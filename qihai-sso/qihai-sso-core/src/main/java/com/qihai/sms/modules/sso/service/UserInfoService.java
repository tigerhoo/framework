package com.qihai.sms.modules.sso.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.vo.UserResources;
import com.qihai.sms.modules.sso.entity.UserInfoEntity;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface UserInfoService extends IService<UserInfoEntity> {

	/**
	 * 根据用户ID，查询用户权限
	 */
	List<UserResources> getUserResourcesById(String userId);
	
}

