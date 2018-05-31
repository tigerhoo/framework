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

import com.qihai.permission.entity.AuthDimensionValueEntity;
import com.qihai.permission.service.AuthDimensionValueService;
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
@RequestMapping("permission/authdimensionvalue")
public class AuthDimensionValueController {
    @Autowired
    private AuthDimensionValueService authDimensionValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authdimensionvalue:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authDimensionValueService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authdimensionvalue:info")
    public R info(@PathVariable("id") Long id){
        AuthDimensionValueEntity authDimensionValue = authDimensionValueService.selectById(id);

        return R.ok().put("authDimensionValue", authDimensionValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authdimensionvalue:save")
    public R save(@RequestBody AuthDimensionValueEntity authDimensionValue){
        authDimensionValueService.insert(authDimensionValue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authdimensionvalue:update")
    public R update(@RequestBody AuthDimensionValueEntity authDimensionValue){
        ValidatorUtils.validateEntity(authDimensionValue);
        authDimensionValueService.updateAllColumnById(authDimensionValue);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authdimensionvalue:delete")
    public R delete(@RequestBody Long[] ids){
        authDimensionValueService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
