package com.qihai.permission.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthMenuEntity;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
public interface AuthMenuService extends IService<AuthMenuEntity> {

    PageUtils queryPage(Map<String, Object> params,AuthMenuEntity authMenu);
    
    List<AuthMenuEntity> listByModuleId(Long moduleId);
}

