package com.qihai.permission.service;

import com.baomidou.mybatisplus.service.IService;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.vo.menu.AuthMenuVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
public interface AuthMenuService extends IService<AuthMenuEntity> {

	PageUtils queryPage(Map<String, Object> params);

	List<AuthMenuEntity> listMenu(Long parentId);
	
	List<AuthMenuEntity> listMenusByRoleIds(Set<Long> roleIds);

	List<AuthMenuVO> listAllPermissionByRoleIds(Set<Long> roleIds);

}
