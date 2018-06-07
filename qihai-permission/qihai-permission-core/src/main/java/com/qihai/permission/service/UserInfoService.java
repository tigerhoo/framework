package com.qihai.permission.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.UserInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    PageUtils queryPage(Map<String, Object> params,UserInfoEntity userInfo);
    
	Page<UserRoleDTO> listUserRole(Page<UserRoleDTO> pages, Long id);
}

