package com.qihai.sms.modules.sso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qihai.commerce.framework.vo.UserResources;
import com.qihai.sms.modules.sso.entity.UserInfoEntity;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
	
	/**
     * @param userId
     * @return
     */
    List<Long> getUerRoles(@Param("userId") Long userId);

    /**
     * @param userId
     * @return
     */
    List<Long> getUserGroupRoles(@Param("userId") Long userId);
    
    /**
     * 查找用户id对应的角色列表
     *
     * @param userId
     * @return
     
    List<Long> selectRolesByUserId(Long userId);*/
    
    /**
     * 批量查找角色绑定的权限(资源)
     *
     * @param roleIds
     * @return
     */
    List<UserResources> selectPermissionByRoleIds(Long... roleIds);

}
