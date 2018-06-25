package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
import com.qihai.commerce.framework.utils.R;
import com.qihai.permission.dto.permission.AuthPermissionColumnDTO;
import com.qihai.permission.dto.permission.AuthPermissionDTO;
import com.qihai.permission.dto.permission.AuthRolePermissionDTO;
import com.qihai.permission.service.AuthRolePermissionService;

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
@Api("角色与权限关联管理")
@RestController
@RequestMapping("${adminPath}/permission/authrolepermission")
public class AuthRolePermissionController {
	@Autowired
	private AuthRolePermissionService authRolePermissionService;

	/**
	 * 更新角色与关联权限.
	 * 
	 * @param roleId
	 * @param authModules
	 * @return
	 */
	@ApiOperation(value = "保存或更新角色与权限关联关系", httpMethod = "POST")
	@PostMapping("/saveOrUpdate")
	public R<Object> saveOrUpdate(@RequestBody AuthRolePermissionDTO rolePermission) {
		authRolePermissionService.saveOrUpdate(rolePermission);
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除角色权限关联", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authRolePermissionService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}

	}

	/**
	 * 查询某一个角色拥有的的某个模块的权限信息.
	 */
	@ApiOperation(value = "查询某一个角色拥有的的某个模块的权限信息", httpMethod = "POST")
	@PostMapping(value = { "/listPermission" })
	public R<List<AuthPermissionDTO>> listPermission(@RequestBody Map<String, Long> params) {
		List<AuthPermissionDTO> authMenus = authRolePermissionService.listRolePermission(params.get("roleId"),
				params.get("moduleId"));
		return new R<List<AuthPermissionDTO>>().ok(authMenus);
	}

	@ApiOperation(value = "查询某一个角色拥有的的某个模块的列权限信息", httpMethod = "POST")
	@PostMapping(value = { "/listPermissionColumns" })
	public R<List<AuthPermissionColumnDTO>> listPermissionColumns(@RequestBody Map<String, Long> params) {
		List<AuthPermissionColumnDTO> permissionColumns = authRolePermissionService
				.listPermissionColumns(params.get("roleId"), params.get("moduleId"), params.get("permissionId"));
		return new R<List<AuthPermissionColumnDTO>>().ok(permissionColumns);
	}

}
