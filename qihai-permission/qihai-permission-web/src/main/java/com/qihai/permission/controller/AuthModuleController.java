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

import com.qihai.permission.entity.AuthModuleEntity;
import com.qihai.permission.service.AuthModuleService;
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
@RequestMapping("permission/authmodule")
public class AuthModuleController {
    @Autowired
    private AuthModuleService authModuleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authmodule:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authModuleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authmodule:info")
    public R info(@PathVariable("id") Long id){
        AuthModuleEntity authModule = authModuleService.selectById(id);

        return R.ok().put("authModule", authModule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authmodule:save")
    public R save(@RequestBody AuthModuleEntity authModule){
        authModuleService.insert(authModule);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authmodule:update")
    public R update(@RequestBody AuthModuleEntity authModule){
        ValidatorUtils.validateEntity(authModule);
        authModuleService.updateAllColumnById(authModule);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authmodule:delete")
    public R delete(@RequestBody Long[] ids){
        authModuleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
