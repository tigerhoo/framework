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

import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.service.AuthMenuService;
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
@RequestMapping("permission/authmenu")
public class AuthMenuController {
    @Autowired
    private AuthMenuService authMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authmenu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authmenu:info")
    public R info(@PathVariable("id") Long id){
        AuthMenuEntity authMenu = authMenuService.selectById(id);

        return R.ok().put("authMenu", authMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authmenu:save")
    public R save(@RequestBody AuthMenuEntity authMenu){
        authMenuService.insert(authMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authmenu:update")
    public R update(@RequestBody AuthMenuEntity authMenu){
        ValidatorUtils.validateEntity(authMenu);
        authMenuService.updateAllColumnById(authMenu);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authmenu:delete")
    public R delete(@RequestBody Long[] ids){
        authMenuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
