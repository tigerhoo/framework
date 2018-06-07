package com.qihai.permission.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.entity.AuthUserGroupEntity;
import com.qihai.permission.entity.UserInfoEntity;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
public interface AuthUserGroupService extends IService<AuthUserGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    public Page<UserInfoEntity> selectByUserIdOrGroupId(Page<UserInfoEntity> page,AuthUserGroupEntity userGroup);
    
    
}

