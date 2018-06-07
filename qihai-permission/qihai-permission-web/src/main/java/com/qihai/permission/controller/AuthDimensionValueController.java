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
import com.qihai.permission.entity.AuthDimensionValueEntity;
import com.qihai.permission.service.AuthDimensionValueService;

import springfox.documentation.annotations.ApiIgnore;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiIgnore
@RestController
@RequestMapping("permission/authdimensionvalue")
public class AuthDimensionValueController {
    @Autowired
    private AuthDimensionValueService authDimensionValueService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @RequiresPermissions("permission:authdimensionvalue:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = authDimensionValueService.queryPage(params);

        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authdimensionvalue:info")
    public R<AuthDimensionValueEntity> info(@PathVariable("id") Long id){
        AuthDimensionValueEntity authDimensionValue = authDimensionValueService.selectById(id);

        return new R<AuthDimensionValueEntity>().ok( authDimensionValue);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authdimensionvalue:save")
    public R<Object> save(@RequestBody AuthDimensionValueEntity authDimensionValue){
        authDimensionValueService.insert(authDimensionValue);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authdimensionvalue:update")
    public R<Object> update(@RequestBody AuthDimensionValueEntity authDimensionValue){
        ValidatorUtils.validateEntity(authDimensionValue);
        authDimensionValueService.updateById(authDimensionValue);//全部更新
        
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authdimensionvalue:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authDimensionValueService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
