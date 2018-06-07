package com.qihai.permission.service;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthMenuResourcesEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
public interface AuthMenuResourcesService extends IService<AuthMenuResourcesEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<AuthMenuResourcesEntity> listMenu(Long parentId);
}

