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

import com.qihai.permission.entity.AuthDimensionValueScopeEntity;
import com.qihai.permission.service.AuthDimensionValueScopeService;
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
@RequestMapping("permission/authdimensionvaluescope")
public class AuthDimensionValueScopeController {
    @Autowired
    private AuthDimensionValueScopeService authDimensionValueScopeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authdimensionvaluescope:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authDimensionValueScopeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authdimensionvaluescope:info")
    public R info(@PathVariable("id") Long id){
        AuthDimensionValueScopeEntity authDimensionValueScope = authDimensionValueScopeService.selectById(id);

        return R.ok().put("authDimensionValueScope", authDimensionValueScope);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authdimensionvaluescope:save")
    public R save(@RequestBody AuthDimensionValueScopeEntity authDimensionValueScope){
        authDimensionValueScopeService.insert(authDimensionValueScope);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authdimensionvaluescope:update")
    public R update(@RequestBody AuthDimensionValueScopeEntity authDimensionValueScope){
        ValidatorUtils.validateEntity(authDimensionValueScope);
        authDimensionValueScopeService.updateAllColumnById(authDimensionValueScope);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authdimensionvaluescope:delete")
    public R delete(@RequestBody Long[] ids){
        authDimensionValueScopeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
