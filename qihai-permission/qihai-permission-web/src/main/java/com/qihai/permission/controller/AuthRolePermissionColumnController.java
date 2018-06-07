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
import com.qihai.permission.entity.AuthRolePermissionColumnEntity;
import com.qihai.permission.service.AuthRolePermissionColumnService;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@RestController
@RequestMapping("permission/authrolepermissioncolumn")
public class AuthRolePermissionColumnController {
    @Autowired
    private AuthRolePermissionColumnService authRolePermissionColumnService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @RequiresPermissions("permission:authrolepermissioncolumn:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = authRolePermissionColumnService.queryPage(params);

        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authrolepermissioncolumn:info")
    public R<AuthRolePermissionColumnEntity> info(@PathVariable("id") Long id){
        AuthRolePermissionColumnEntity authRolePermissionColumn = authRolePermissionColumnService.selectById(id);

        return new R<AuthRolePermissionColumnEntity>().ok(authRolePermissionColumn);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authrolepermissioncolumn:save")
    public R<Object> save(@RequestBody AuthRolePermissionColumnEntity authRolePermissionColumn){
        authRolePermissionColumnService.insert(authRolePermissionColumn);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authrolepermissioncolumn:update")
    public R<Object> update(@RequestBody AuthRolePermissionColumnEntity authRolePermissionColumn){
        ValidatorUtils.validateEntity(authRolePermissionColumn);
        authRolePermissionColumnService.updateById(authRolePermissionColumn);//全部更新
        
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authrolepermissioncolumn:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authRolePermissionColumnService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
