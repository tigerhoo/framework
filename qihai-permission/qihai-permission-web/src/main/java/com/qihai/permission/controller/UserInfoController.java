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
import com.qihai.R;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Api("用户管理")
@RestController
@RequestMapping("permission/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询用户列表",httpMethod ="POST", response = UserInfoEntity.class,notes = "返回查询结果集以及分页")
    @ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
    @PostMapping("/list")
    @RequiresPermissions("permission:userinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "按id查询某一个用户的信息",httpMethod ="GET", response = UserInfoEntity.class,notes = "返回用户信息")
    @ApiImplicitParam(name = "id", value = "用户的id，表示查哪个用户", required = true,paramType="path", dataType="Long")
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:userinfo:info")
    public R info(@PathVariable("id") Long id){
        UserInfoEntity userInfo = userInfoService.selectById(id);
        return R.ok().put("userInfo", userInfo);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "添加用户",httpMethod ="POST")
    @PostMapping("/save")
    @RequiresPermissions("permission:userinfo:save")
    public R save(@RequestBody UserInfoEntity userInfo){
        ValidatorUtils.validateEntity(userInfo);
        userInfoService.insert(userInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "按id修改用户信息",httpMethod ="POST")
    @PostMapping("/update")
    @RequiresPermissions("permission:userinfo:update")
    public R update(@RequestBody UserInfoEntity userInfo){
        //ValidatorUtils.validateEntity(userInfo);
        //userInfoService.updateAllColumnById(userInfo);//全部更新
        userInfoService.updateById(userInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "按id删除用户，逻辑删除",httpMethod ="POST")
    @ApiImplicitParam(name = "ids", value = "用户ID数组，批量删除", required = true, dataType = "Long[]")
    @PostMapping("/delete")
    @RequiresPermissions("permission:userinfo:delete")
    public R delete(@RequestBody Long[] ids){
        userInfoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
