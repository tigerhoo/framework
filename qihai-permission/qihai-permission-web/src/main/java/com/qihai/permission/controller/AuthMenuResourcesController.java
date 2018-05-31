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

import com.qihai.permission.entity.AuthMenuResourcesEntity;
import com.qihai.permission.service.AuthMenuResourcesService;
import com.qihai.R;
import com.qihai.commerce.framework.utils.PageUtils;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@RestController
@RequestMapping("permission/authmenuresources")
public class AuthMenuResourcesController {
    @Autowired
    private AuthMenuResourcesService authMenuResourcesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authmenuresources:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authMenuResourcesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authmenuresources:info")
    public R info(@PathVariable("id") Long id){
        AuthMenuResourcesEntity authMenuResources = authMenuResourcesService.selectById(id);

        return R.ok().put("authMenuResources", authMenuResources);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authmenuresources:save")
    public R save(@RequestBody AuthMenuResourcesEntity authMenuResources){
        authMenuResourcesService.insert(authMenuResources);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authmenuresources:update")
    public R update(@RequestBody AuthMenuResourcesEntity authMenuResources){
        ValidatorUtils.validateEntity(authMenuResources);
        authMenuResourcesService.updateAllColumnById(authMenuResources);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authmenuresources:delete")
    public R delete(@RequestBody Long[] ids){
        authMenuResourcesService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
