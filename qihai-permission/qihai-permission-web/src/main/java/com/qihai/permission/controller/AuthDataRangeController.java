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

import com.qihai.permission.entity.AuthDataRangeEntity;
import com.qihai.permission.service.AuthDataRangeService;
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
@RequestMapping("permission/authdatarange")
public class AuthDataRangeController {
    @Autowired
    private AuthDataRangeService authDataRangeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("permission:authdatarange:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authDataRangeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("permission:authdatarange:info")
    public R info(@PathVariable("id") Long id){
        AuthDataRangeEntity authDataRange = authDataRangeService.selectById(id);

        return R.ok().put("authDataRange", authDataRange);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("permission:authdatarange:save")
    public R save(@RequestBody AuthDataRangeEntity authDataRange){
        authDataRangeService.insert(authDataRange);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("permission:authdatarange:update")
    public R update(@RequestBody AuthDataRangeEntity authDataRange){
        ValidatorUtils.validateEntity(authDataRange);
        authDataRangeService.updateAllColumnById(authDataRange);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("permission:authdatarange:delete")
    public R delete(@RequestBody Long[] ids){
        authDataRangeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
