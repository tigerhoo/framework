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

import com.qihai.permission.entity.AuthPermissionReportingEntity;
import com.qihai.permission.service.AuthPermissionReportingService;
import com.qihai.R;
import com.qihai.commerce.framework.utils.PageUtils;




/**
 * 微服务上报管理
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@RestController
@RequestMapping("permission/authpermissionreporting")
public class AuthPermissionReportingController {
    @Autowired
    private AuthPermissionReportingService authPermissionReportingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authpermissionreporting:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authPermissionReportingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authpermissionreporting:info")
    public R info(@PathVariable("id") Long id){
        AuthPermissionReportingEntity authPermissionReporting = authPermissionReportingService.selectById(id);

        return R.ok().put("authPermissionReporting", authPermissionReporting);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authpermissionreporting:save")
    public R save(@RequestBody AuthPermissionReportingEntity authPermissionReporting){
        authPermissionReportingService.insert(authPermissionReporting);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authpermissionreporting:update")
    public R update(@RequestBody AuthPermissionReportingEntity authPermissionReporting){
        ValidatorUtils.validateEntity(authPermissionReporting);
        authPermissionReportingService.updateAllColumnById(authPermissionReporting);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authpermissionreporting:delete")
    public R delete(@RequestBody Long[] ids){
        authPermissionReportingService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
