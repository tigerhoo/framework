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

import com.qihai.permission.entity.AuthRolePermissionColumnEntity;
import com.qihai.permission.service.AuthRolePermissionColumnService;
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
@RequestMapping("permission/authrolepermissioncolumn")
public class AuthRolePermissionColumnController {
    @Autowired
    private AuthRolePermissionColumnService authRolePermissionColumnService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authrolepermissioncolumn:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authRolePermissionColumnService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authrolepermissioncolumn:info")
    public R info(@PathVariable("id") Long id){
        AuthRolePermissionColumnEntity authRolePermissionColumn = authRolePermissionColumnService.selectById(id);

        return R.ok().put("authRolePermissionColumn", authRolePermissionColumn);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authrolepermissioncolumn:save")
    public R save(@RequestBody AuthRolePermissionColumnEntity authRolePermissionColumn){
        authRolePermissionColumnService.insert(authRolePermissionColumn);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authrolepermissioncolumn:update")
    public R update(@RequestBody AuthRolePermissionColumnEntity authRolePermissionColumn){
        ValidatorUtils.validateEntity(authRolePermissionColumn);
        authRolePermissionColumnService.updateAllColumnById(authRolePermissionColumn);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authrolepermissioncolumn:delete")
    public R delete(@RequestBody Long[] ids){
        authRolePermissionColumnService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
