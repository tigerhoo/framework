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

import com.qihai.permission.entity.AuthGroupRoleEntity;
import com.qihai.permission.service.AuthGroupRoleService;
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
@RequestMapping("permission/authgrouprole")
public class AuthGroupRoleController {
    @Autowired
    private AuthGroupRoleService authGroupRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authgrouprole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authGroupRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authgrouprole:info")
    public R info(@PathVariable("id") Long id){
        AuthGroupRoleEntity authGroupRole = authGroupRoleService.selectById(id);

        return R.ok().put("authGroupRole", authGroupRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authgrouprole:save")
    public R save(@RequestBody AuthGroupRoleEntity authGroupRole){
        authGroupRoleService.insert(authGroupRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authgrouprole:update")
    public R update(@RequestBody AuthGroupRoleEntity authGroupRole){
        ValidatorUtils.validateEntity(authGroupRole);
        authGroupRoleService.updateAllColumnById(authGroupRole);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authgrouprole:delete")
    public R delete(@RequestBody Long[] ids){
        authGroupRoleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
