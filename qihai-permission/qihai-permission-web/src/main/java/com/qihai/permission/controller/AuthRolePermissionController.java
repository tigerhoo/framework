package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;

import com.qihai.commerce.framework.utils.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qihai.permission.entity.AuthRolePermissionEntity;
import com.qihai.permission.service.AuthRolePermissionService;
import com.qihai.R;
import com.qihai.commerce.framework.utils.PageUtils;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@RestController
@RequestMapping("permission/authrolepermission")
public class AuthRolePermissionController {
    @Autowired
    private AuthRolePermissionService authRolePermissionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authrolepermission:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authRolePermissionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authrolepermission:info")
    public R info(@PathVariable("id") Long id){
        AuthRolePermissionEntity authRolePermission = authRolePermissionService.selectById(id);

        return R.ok().put("authRolePermission", authRolePermission);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authrolepermission:save")
    public R save(@RequestBody AuthRolePermissionEntity authRolePermission){
        authRolePermissionService.insert(authRolePermission);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authrolepermission:update")
    public R update(@RequestBody AuthRolePermissionEntity authRolePermission){
        ValidatorUtils.validateEntity(authRolePermission);
        authRolePermissionService.updateAllColumnById(authRolePermission);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authrolepermission:delete")
    public R delete(@RequestBody Long[] ids){
        authRolePermissionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
