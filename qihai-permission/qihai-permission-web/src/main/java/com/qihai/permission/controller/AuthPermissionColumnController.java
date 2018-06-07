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
import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.service.AuthPermissionColumnService;




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
    @PostMapping("/list")
    @RequiresPermissions("permission:authpermissioncolumn:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = authPermissionColumnService.queryPage(params);

        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authpermissioncolumn:info")
    public R<AuthPermissionColumnEntity> info(@PathVariable("id") Long id){
        AuthPermissionColumnEntity authPermissionColumn = authPermissionColumnService.selectById(id);

        return new R<AuthPermissionColumnEntity>().ok(authPermissionColumn);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authpermissioncolumn:save")
    public R<Object> save(@RequestBody AuthPermissionColumnEntity authPermissionColumn){
        authPermissionColumnService.insert(authPermissionColumn);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authpermissioncolumn:update")
    public R<Object> update(@RequestBody AuthPermissionColumnEntity authPermissionColumn){
        ValidatorUtils.validateEntity(authPermissionColumn);
        authPermissionColumnService.updateById(authPermissionColumn);//全部更新
        
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authpermissioncolumn:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authPermissionColumnService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
