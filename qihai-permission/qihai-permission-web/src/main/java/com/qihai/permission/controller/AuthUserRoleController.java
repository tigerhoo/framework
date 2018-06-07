package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthUserRoleEntity;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.AuthUserRoleService;

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
@Api(value="用户角色管理")
@RestController
@RequestMapping("permission/authuserrole")
public class AuthUserRoleController {
    @Autowired
    private AuthUserRoleService authUserRoleService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询用户角色关联列表",httpMethod ="POST", response = UserInfoEntity.class,notes = "返回查询结果集以及分页")
    @ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
    @PostMapping("/list")
    @RequiresPermissions("permission:authuserrole:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params,@RequestBody AuthUserRoleEntity authUserRole){
        PageUtils page = authUserRoleService.queryPage(params,authUserRole);
        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authuserrole:info")
    public R<AuthUserRoleEntity> info(@PathVariable("id") Long id){
        AuthUserRoleEntity authUserRole = authUserRoleService.selectById(id);

        return new R<AuthUserRoleEntity>().ok(authUserRole);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("permission:authuserrole:save")
    public R<Object> save(@RequestBody AuthUserRoleEntity authUserRole){
        authUserRoleService.insert(authUserRole);

        return new R<Object>().ok(null);    
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("permission:authuserrole:update")
    public R<Object> update(@RequestBody AuthUserRoleEntity authUserRole){
        ValidatorUtils.validateEntity(authUserRole);
        authUserRoleService.updateById(authUserRole);//全部更新
        
        return new R<Object>().ok(null);    
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authuserrole:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authUserRoleService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);    
    }

}
