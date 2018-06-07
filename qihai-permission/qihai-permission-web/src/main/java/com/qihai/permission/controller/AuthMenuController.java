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
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.service.AuthMenuService;




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
    @PostMapping("/list")
    @RequiresPermissions("permission:authmenu:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = authMenuService.queryPage(params);

        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authmenu:info")
    public R<AuthMenuEntity> info(@PathVariable("id") Long id){
        AuthMenuEntity authMenu = authMenuService.selectById(id);

        return new R<AuthMenuEntity>().ok(authMenu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authmenu:save")
    public R<Object> save(@RequestBody AuthMenuEntity authMenu){
        authMenuService.insert(authMenu);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authmenu:update")
    public R<Object> update(@RequestBody AuthMenuEntity authMenu){
        ValidatorUtils.validateEntity(authMenu);
        authMenuService.updateById(authMenu);//全部更新
        
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authmenu:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authMenuService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
