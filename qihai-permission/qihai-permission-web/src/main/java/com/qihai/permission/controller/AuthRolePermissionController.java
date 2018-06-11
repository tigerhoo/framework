package com.qihai.permission.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qihai.commerce.framework.utils.R;
import com.qihai.permission.dto.permission.AuthMenuDTO;
import com.qihai.permission.dto.permission.AuthModuleDTO;
import com.qihai.permission.dto.permission.AuthPermissionColumnDTO;
import com.qihai.permission.entity.AuthRolePermissionColumnEntity;
import com.qihai.permission.entity.AuthRolePermissionEntity;
import com.qihai.permission.service.AuthRolePermissionColumnService;
import com.qihai.permission.service.AuthRolePermissionService;

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
@Api("角色与权限关联管理")
@RestController
@RequestMapping("permission/authrolepermission")
public class AuthRolePermissionController {
	@Autowired
	private AuthRolePermissionService authRolePermissionService;

	// /**
	// * 列表
	// */
	// @ApiOperation(value = "分页查询角色与权限关联关系", httpMethod = "POST")
	// @PostMapping("/list")
	// @RequiresPermissions("permission:authrolepermission:list")
	// public R<PageUtils> list(@RequestParam Map<String, Object> params,
	// @RequestBody(required = false) AuthRolePermissionEntity authRolePermission) {
	// PageUtils page = authRolePermissionService.queryPage(params,
	// authRolePermission);
	//
	// return new R<PageUtils>().ok(page);
	// }

	// /**
	// * 信息
	// */
	// @GetMapping("/info/{id}")
	// @RequiresPermissions("permission:authrolepermission:info")
	// public R<AuthRolePermissionEntity> info(@PathVariable("id") Long id){
	// AuthRolePermissionEntity authRolePermission =
	// authRolePermissionService.selectById(id);
	//
	// return new R<AuthRolePermissionEntity>().ok(authRolePermission);
	// }

	// /**
	// * 保存
	// */
	// @ApiOperation(value = "添加角色权限关联", httpMethod = "POST")
	// @PostMapping("/save")
	// @RequiresPermissions("permission:authrolepermission:save")
	// public R<Object> save(@RequestBody List<AuthModuleDTO> authModules) {
	//
	// //authRolePermissionService.insert(authRolePermission);
	//
	//
	// return new R<Object>().ok(null);
	// }

	/**
	 * 修改
	 */
	@ApiOperation(value = "更新角色与权限关联关系(新增也视为更新)", httpMethod = "POST")
	@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long", paramType = "path")
	@PostMapping("/update/{roleId}")
	@RequiresPermissions("permission:authrolepermission:update")
	public R<Object> update(@PathVariable("roleId") Long roleId, @RequestBody List<AuthModuleDTO> authModules) {

		authRolePermissionService.saveRolePermission(roleId, authModules);
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除角色权限关联", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	@RequiresPermissions("permission:authrolepermission:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		authRolePermissionService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

	/**
	 * 查询某一个角色拥有的的某个模块的权限信息.
	 */
	@ApiOperation(value = "查询某一个角色拥有的的某个模块的权限信息", httpMethod = "GET")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "moduleId", value = "模块id", required = true, dataType = "Long", paramType = "path") })
	@GetMapping(value = { "/listPermission/{roleId}/{moduleId}" })
	@RequiresPermissions("permission:authrolepermission:listPermission")
	public R<List<AuthMenuDTO>> listPermission(@PathVariable("roleId") Long roleId,
			@PathVariable("moduleId") Long moduleId) {
		List<AuthMenuDTO> authMenus = authRolePermissionService.listRolePermission(roleId, moduleId);
		return new R<List<AuthMenuDTO>>().ok(authMenus);
	}

	@ApiOperation(value = "查询某一个角色拥有的的某个模块的列权限信息", httpMethod = "GET")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "moduleId", value = "模块id", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "permissionId", value = "菜案权限id，对应auth_menu的id", required = true, dataType = "Long", paramType = "path") })
	@GetMapping(value = { "/listPermission/{roleId}/{moduleId}/{permissionId}" })
	@RequiresPermissions("permission:authrolepermission:listPermissionColumns")
	public R<List<AuthPermissionColumnDTO>> listPermissionColumns(@PathVariable("roleId") Long roleId,
			@PathVariable("moduleId") Long moduleId, @PathVariable(value = "permissionId") Long permissionId) {
		List<AuthPermissionColumnDTO> permissionColumns = authRolePermissionService.listPermissionColumns(roleId,
				moduleId, permissionId);
		return new R<List<AuthPermissionColumnDTO>>().ok(permissionColumns);
	}

}
