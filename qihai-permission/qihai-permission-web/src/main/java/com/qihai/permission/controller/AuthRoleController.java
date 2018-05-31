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

import com.baomidou.mybatisplus.plugins.Page;
import com.qihai.R;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthRoleEntity;
import com.qihai.permission.service.AuthRoleService;

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
@Api("角色管理")
@RestController
@RequestMapping("permission/authrole")
public class AuthRoleController {
    @Autowired
    private AuthRoleService authRoleService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询角色列表",httpMethod ="POST", response = PageUtils.class,notes = "返回查询结果集以及分页信息")
    @ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
    @PostMapping("/list")
    @RequiresPermissions("permission:authrole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "按id查询某个角色信息",httpMethod ="GET", response = AuthRoleEntity.class,notes = "返回要查询的角色")
    @ApiImplicitParam(name = "id", value = "角色的id",paramType="path", required = true,dataType="Long")
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authrole:info")
    public R info(@PathVariable("id") Long id){
        AuthRoleEntity authRole = authRoleService.selectById(id);

        return R.ok().put("authRole", authRole);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增角色",httpMethod ="POST", response = AuthRoleEntity.class,notes = "角色信息")
    @PostMapping("/save")
    @RequiresPermissions("permission:authrole:save")
    public R save(@RequestBody AuthRoleEntity authRole){
        authRoleService.insert(authRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改角色",httpMethod ="POST",notes = "角色信息")
    @PostMapping("/update")
    @RequiresPermissions("permission:authrole:update")
    public R update(@RequestBody AuthRoleEntity authRole){
        ValidatorUtils.validateEntity(authRole);
        authRoleService.updateAllColumnById(authRole);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "按id批量删除角色",httpMethod ="POST", notes = "角色信息")
    @PostMapping("/delete")
    @RequiresPermissions("permission:authrole:delete")
    public R delete(@RequestBody Long[] ids){
        authRoleService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
