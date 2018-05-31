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

import com.qihai.permission.entity.AuthPermissionDimensionEntity;
import com.qihai.permission.service.AuthPermissionDimensionService;
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
@RequestMapping("permission/authpermissiondimension")
public class AuthPermissionDimensionController {
    @Autowired
    private AuthPermissionDimensionService authPermissionDimensionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authpermissiondimension:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authPermissionDimensionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authpermissiondimension:info")
    public R info(@PathVariable("id") Long id){
        AuthPermissionDimensionEntity authPermissionDimension = authPermissionDimensionService.selectById(id);

        return R.ok().put("authPermissionDimension", authPermissionDimension);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authpermissiondimension:save")
    public R save(@RequestBody AuthPermissionDimensionEntity authPermissionDimension){
        authPermissionDimensionService.insert(authPermissionDimension);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authpermissiondimension:update")
    public R update(@RequestBody AuthPermissionDimensionEntity authPermissionDimension){
        ValidatorUtils.validateEntity(authPermissionDimension);
        authPermissionDimensionService.updateAllColumnById(authPermissionDimension);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authpermissiondimension:delete")
    public R delete(@RequestBody Long[] ids){
        authPermissionDimensionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
