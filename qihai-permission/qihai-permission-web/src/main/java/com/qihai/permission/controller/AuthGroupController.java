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
import com.qihai.permission.entity.AuthGroupEntity;
import com.qihai.permission.service.AuthGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Api("用户组管理")
@RestController
@RequestMapping("permission/authgroup")
public class AuthGroupController {
    @Autowired
    private AuthGroupService authGroupService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询用户组列表",httpMethod ="POST", response = PageUtils.class,notes = "返回查询结果集以及分页信息")
    @ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
    @PostMapping("/list")
    @RequiresPermissions("permission:authgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authGroupService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "按id查询某个用户组信息",httpMethod ="GET", response = AuthGroupEntity.class,notes = "返回要查询的用户组")
    @ApiImplicitParam(name = "id", value = "用户组的id",paramType="path", required = true,dataType="Long")
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authgroup:info")
    public R info(@PathVariable("id") Long id){
        AuthGroupEntity authGroup = authGroupService.selectById(id);
        return R.ok().put("authGroup", authGroup);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增用户组",httpMethod ="POST")
    @PostMapping("/save")
    @RequiresPermissions("permission:authgroup:save")
    public R save(@RequestBody AuthGroupEntity authGroup){
        authGroupService.insert(authGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户组信息",httpMethod ="POST")
    @PostMapping("/update")
    @RequiresPermissions("permission:authgroup:update")
    public R update(@RequestBody AuthGroupEntity authGroup){
        ValidatorUtils.validateEntity(authGroup);
        //authGroupService.updateAllColumnById(authGroup);//全部更新
        authGroupService.updateById(authGroup);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除用户组",httpMethod ="POST")
    @PostMapping("/delete")
    @RequiresPermissions("permission:authgroup:delete")
    public R delete(@RequestBody Long[] ids){
        authGroupService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
