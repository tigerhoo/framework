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

import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.service.AuthPermissionColumnService;
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
@RequestMapping("permission/authpermissioncolumn")
public class AuthPermissionColumnController {
    @Autowired
    private AuthPermissionColumnService authPermissionColumnService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authpermissioncolumn:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authPermissionColumnService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authpermissioncolumn:info")
    public R info(@PathVariable("id") Long id){
        AuthPermissionColumnEntity authPermissionColumn = authPermissionColumnService.selectById(id);

        return R.ok().put("authPermissionColumn", authPermissionColumn);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authpermissioncolumn:save")
    public R save(@RequestBody AuthPermissionColumnEntity authPermissionColumn){
        authPermissionColumnService.insert(authPermissionColumn);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authpermissioncolumn:update")
    public R update(@RequestBody AuthPermissionColumnEntity authPermissionColumn){
        ValidatorUtils.validateEntity(authPermissionColumn);
        authPermissionColumnService.updateAllColumnById(authPermissionColumn);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authpermissioncolumn:delete")
    public R delete(@RequestBody Long[] ids){
        authPermissionColumnService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
