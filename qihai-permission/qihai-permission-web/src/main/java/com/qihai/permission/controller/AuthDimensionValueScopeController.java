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
import com.qihai.permission.entity.AuthDimensionValueScopeEntity;
import com.qihai.permission.service.AuthDimensionValueScopeService;
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
@RequestMapping("permission/authdimensionvaluescope")
public class AuthDimensionValueScopeController {
    @Autowired
    private AuthDimensionValueScopeService authDimensionValueScopeService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @RequiresPermissions("permission:authdimensionvaluescope:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = authDimensionValueScopeService.queryPage(params);

        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authdimensionvaluescope:info")
    public R<AuthDimensionValueScopeEntity> info(@PathVariable("id") Long id){
        AuthDimensionValueScopeEntity authDimensionValueScope = authDimensionValueScopeService.selectById(id);

        return new R<AuthDimensionValueScopeEntity>().ok(authDimensionValueScope);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authdimensionvaluescope:save")
    public R<Object> save(@RequestBody AuthDimensionValueScopeEntity authDimensionValueScope){
        authDimensionValueScopeService.insert(authDimensionValueScope);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authdimensionvaluescope:update")
    public R<Object> update(@RequestBody AuthDimensionValueScopeEntity authDimensionValueScope){
        ValidatorUtils.validateEntity(authDimensionValueScope);
        authDimensionValueScopeService.updateById(authDimensionValueScope);//全部更新
        
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authdimensionvaluescope:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authDimensionValueScopeService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
