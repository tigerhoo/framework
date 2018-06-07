package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthMenuResourcesEntity;
import com.qihai.permission.service.AuthMenuResourcesService;
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
@RestController
@RequestMapping("permission/authmenuresources")
@Api("菜单管理")
public class AuthMenuResourcesController {
    @Autowired
    private AuthMenuResourcesService authMenuResourcesService;

    /**
     * 列表
     */
    
//    @PostMapping("/list")
//    @RequiresPermissions("permission:authmenuresources:list")
//    public R<PageUtils> list(@RequestParam Map<String, Object> params){
//        PageUtils page = authMenuResourcesService.queryPage(params);
//
//        return new R<PageUtils>().ok(page);
//    }
    
    
    @ApiOperation(value="查询某个菜单下的子菜单",httpMethod="GET")
    @ApiImplicitParam(name="parentId",value="父菜单id，如果不传，默认为0，即只查顶级菜单下的所有一级菜单",required=false,defaultValue="0",paramType="query",dataType="Long")
    @GetMapping("listMenu")
    public R<List<AuthMenuResourcesEntity>> listMenu(@RequestParam(defaultValue="0") Long parentId) {
    	List<AuthMenuResourcesEntity> list=authMenuResourcesService.listMenu(parentId);
    	return new R<List<AuthMenuResourcesEntity>>().ok(list);
    }


    /**
     * 信息
     */
//    @GetMapping("/info/{id}")
//    @RequiresPermissions("permission:authmenuresources:info")
//    public R<AuthMenuResourcesEntity> info(@PathVariable("id") Long id){
//        AuthMenuResourcesEntity authMenuResources = authMenuResourcesService.selectById(id);
//
//        return new R<AuthMenuResourcesEntity>().ok(authMenuResources);
//    }

    /**
     * 保存
     */
    @ApiOperation(value="保存菜单信息",httpMethod="POST")
    @PostMapping("/save")
    @RequiresPermissions("permission:authmenuresources:save")
    public R<Object> save(@RequestBody AuthMenuResourcesEntity authMenuResources){
        ValidatorUtils.validateEntity(authMenuResources);
    	authMenuResourcesService.insert(authMenuResources);

        return new R<Object>().ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation(value="更新菜单信息",httpMethod="POST")
    @PostMapping("/update")
    @RequiresPermissions("permission:authmenuresources:update")
    public R<Object> update(@RequestBody AuthMenuResourcesEntity authMenuResources){
        ValidatorUtils.validateEntity(authMenuResources);
        authMenuResourcesService.updateById(authMenuResources);//全部更新
        return new R<Object>().ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation(value="删除菜单信息",httpMethod="POST")
    @ApiImplicitParam(name="ids",value="菜单主键ID列表",paramType="Long[]")
    @PostMapping("/delete")
    @RequiresPermissions("permission:authmenuresources:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authMenuResourcesService.deleteBatchIds(Arrays.asList(ids));
        return new R<Object>().ok(null);
    }

}
