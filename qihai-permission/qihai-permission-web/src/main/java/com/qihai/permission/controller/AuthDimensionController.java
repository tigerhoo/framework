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

import com.qihai.permission.entity.AuthDimensionEntity;
import com.qihai.permission.service.AuthDimensionService;
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
@RequestMapping("permission/authdimension")
public class AuthDimensionController {
    @Autowired
    private AuthDimensionService authDimensionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authdimension:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authDimensionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authdimension:info")
    public R info(@PathVariable("id") Long id){
        AuthDimensionEntity authDimension = authDimensionService.selectById(id);

        return R.ok().put("authDimension", authDimension);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authdimension:save")
    public R save(@RequestBody AuthDimensionEntity authDimension){
        authDimensionService.insert(authDimension);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authdimension:update")
    public R update(@RequestBody AuthDimensionEntity authDimension){
        ValidatorUtils.validateEntity(authDimension);
        authDimensionService.updateAllColumnById(authDimension);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authdimension:delete")
    public R delete(@RequestBody Long[] ids){
        authDimensionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
