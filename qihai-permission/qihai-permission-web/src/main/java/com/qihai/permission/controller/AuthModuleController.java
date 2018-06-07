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
import com.qihai.permission.entity.AuthModuleEntity;
import com.qihai.permission.service.AuthModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;




/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Api("模块")
@RestController
@RequestMapping("permission/authmodule")
public class AuthModuleController {
    @Autowired
    private AuthModuleService authModuleService;

    /**
     * 列表
     */
	@ApiOperation(value = "模块分页查询", httpMethod = "POST", notes = "返回查询结果集以及分页")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", paramType = "query", required = false) })
    @PostMapping("/list")
    @RequiresPermissions("permission:authmodule:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params,@RequestBody AuthModuleEntity authModuleEntity){
        PageUtils page = authModuleService.queryPage(params,authModuleEntity);

        return new R<PageUtils>().ok(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("permission:authmodule:info")
    public R<AuthModuleEntity> info(@PathVariable("id") Long id){
        AuthModuleEntity authModule = authModuleService.selectById(id);

        return new R<AuthModuleEntity>().ok(authModule);
    }

    /**
     * 保存
     */
    @PostMapping("/saveOrUpdate")
    @RequiresPermissions("permission:authmodule:save")
    public R<Object> saveOrUpdate(@RequestBody AuthModuleEntity authModule){
    	ValidatorUtils.validateEntity(authModule);
    	if(authModule!=null) {
    		if(authModule.getId()==null) {
    	        authModuleService.insert(authModule);
    		}else {
    	        authModuleService.updateById(authModule);
    		}
    	}

        return new R<Object>().ok(null);
    }

	// /**
	// * 修改
	// */
	// @PostMapping("/update")
	// @RequiresPermissions("permission:authmodule:update")
	// public R<Object> update(@RequestBody AuthModuleEntity authModule){
	// ValidatorUtils.validateEntity(authModule);
	//
	// return new R<Object>().ok(null);
	// }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("permission:authmodule:delete")
    public R<Object> delete(@RequestBody Long[] ids){
        authModuleService.deleteBatchIds(Arrays.asList(ids));

        return new R<Object>().ok(null);
    }

}
