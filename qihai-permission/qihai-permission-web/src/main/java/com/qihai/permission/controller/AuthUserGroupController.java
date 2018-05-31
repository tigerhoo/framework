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

import com.qihai.permission.entity.AuthUserGroupEntity;
import com.qihai.permission.service.AuthUserGroupService;
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
@RequestMapping("permission/authusergroup")
public class AuthUserGroupController {
    @Autowired
    private AuthUserGroupService authUserGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authusergroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authUserGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authusergroup:info")
    public R info(@PathVariable("id") Long id){
        AuthUserGroupEntity authUserGroup = authUserGroupService.selectById(id);

        return R.ok().put("authUserGroup", authUserGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authusergroup:save")
    public R save(@RequestBody AuthUserGroupEntity authUserGroup){
        authUserGroupService.insert(authUserGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authusergroup:update")
    public R update(@RequestBody AuthUserGroupEntity authUserGroup){
        ValidatorUtils.validateEntity(authUserGroup);
        authUserGroupService.updateAllColumnById(authUserGroup);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authusergroup:delete")
    public R delete(@RequestBody Long[] ids){
        authUserGroupService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
