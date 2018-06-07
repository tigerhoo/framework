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

import com.qihai.commerce.framework.exception.BaseException;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthDataRangeEntity;
import com.qihai.permission.entity.AuthGroupRoleEntity;
import com.qihai.permission.service.AuthGroupRoleService;

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
@Api("组与角色关联关系")
@RestController
@RequestMapping("permission/authgrouprole")
public class AuthGroupRoleController {
	@Autowired
	private AuthGroupRoleService authGroupRoleService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询组与角色关联关系", httpMethod = "POST")
	@PostMapping("/list")
	@RequiresPermissions("permission:authgrouprole:list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params) {
		PageUtils page = authGroupRoleService.queryPage(params);
		return new R<PageUtils>().ok(page);
	}

	// /**
	// * 信息
	// */
	// @ApiOperation(value = "按id查询组与角色", httpMethod = "GET")
	// @ApiImplicitParam(name = "id", value = "数据范围的id", dataType = "Long",
	// paramType = "path", required = true)
	// @GetMapping("/info/{id}")
	// @RequiresPermissions("permission:authgrouprole:info")
	// public R<AuthGroupRoleEntity> info(@PathVariable("id") Long id) {
	// AuthGroupRoleEntity authGroupRole = authGroupRoleService.selectById(id);
	//
	// return new R<AuthGroupRoleEntity>().ok(authGroupRole);
	// }

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加组与角色关联关系", httpMethod = "POST")
	@PostMapping("/save")
	@RequiresPermissions("permission:authgrouprole:save")
	public R<Object> save(@RequestBody AuthGroupRoleEntity authGroupRole) {
		ValidatorUtils.validateEntity(authGroupRole);
		authGroupRoleService.insert(authGroupRole);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "更新组与角色关联关系", httpMethod = "POST")
	@PostMapping("/update")
	@RequiresPermissions("permission:authgrouprole:update")
	public R<Object> update(@RequestBody AuthGroupRoleEntity authGroupRole) {
		if (authGroupRole == null || authGroupRole.getId() == null) {
			throw new BaseException("修改时id为必传参数");
		}
		ValidatorUtils.validateEntity(authGroupRole);
		authGroupRoleService.updateById(authGroupRole);// 全部更新
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按id删除用户，逻辑删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键ID数组，批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	@RequiresPermissions("permission:authgrouprole:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BaseException("请传入需要删除的数据的id");
		}
		authGroupRoleService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

}
