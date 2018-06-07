package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthRolePermissionEntity;
import com.qihai.permission.service.AuthRolePermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Api("角色关联权限")
@RestController
@RequestMapping("permission/authrolepermission")
public class AuthRolePermissionController {
    @Autowired
    private AuthRolePermissionService authRolePermissionService;

    /**
     * 列表
     */
    @ApiOperation("查询角色与权限关联关系")
    @PostMapping("/list")
    @RequiresPermissions("permission:authrolepermission:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = authRolePermissionService.queryPage(params);

        return new R<PageUtils>().ok(page);
    }


//    /**
//     * 信息
//     */
//    @GetMapping("/info/{id}")
//    @RequiresPermissions("permission:authrolepermission:info")
//    public R<AuthRolePermissionEntity> info(@PathVariable("id") Long id){
//        AuthRolePermissionEntity authRolePermission = authRolePermissionService.selectById(id);
//
//        return new R<AuthRolePermissionEntity>().ok(authRolePermission);
//    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authrolepermission:save")
    public R<Object> save(@RequestBody AuthRolePermissionEntity authRolePermission){
        authRolePermissionService.insert(authRolePermission);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authrolepermission:update")
    public R<Object> update(@RequestBody AuthRolePermissionEntity authRolePermission){
        ValidatorUtils.validateEntity(authRolePermission);
        authRolePermissionService.updateById(authRolePermission);//全部更新
        
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authrolepermission:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authRolePermissionService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
