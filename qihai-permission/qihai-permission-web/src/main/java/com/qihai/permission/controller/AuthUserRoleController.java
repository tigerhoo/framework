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

import com.qihai.permission.entity.AuthUserRoleEntity;
import com.qihai.permission.service.AuthUserRoleService;
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
@RequestMapping("permission/authuserrole")
public class AuthUserRoleController {
    @Autowired
    private AuthUserRoleService authUserRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authuserrole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authUserRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authuserrole:info")
    public R info(@PathVariable("id") Long id){
        AuthUserRoleEntity authUserRole = authUserRoleService.selectById(id);

        return R.ok().put("authUserRole", authUserRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authuserrole:save")
    public R save(@RequestBody AuthUserRoleEntity authUserRole){
        authUserRoleService.insert(authUserRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authuserrole:update")
    public R update(@RequestBody AuthUserRoleEntity authUserRole){
        ValidatorUtils.validateEntity(authUserRole);
        authUserRoleService.updateAllColumnById(authUserRole);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authuserrole:delete")
    public R delete(@RequestBody Long[] ids){
        authUserRoleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
