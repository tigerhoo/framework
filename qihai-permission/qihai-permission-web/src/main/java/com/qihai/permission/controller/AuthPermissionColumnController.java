package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.List;
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

import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthPermissionColumnEntity;
import com.qihai.permission.service.AuthPermissionColumnService;

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
@Api("权限资源列管理")
@RestController
@RequestMapping("${adminPath}/permission/authpermissioncolumn")
public class AuthPermissionColumnController {
	@Autowired
	private AuthPermissionColumnService authPermissionColumnService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "分页查询权限列", httpMethod = "POST", notes = "返回分页查询结果")
	@PostMapping("/list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthPermissionColumnEntity authPermissionColumn) {
		PageUtils page = authPermissionColumnService.queryPage(params, authPermissionColumn);

		return new R<PageUtils>().ok(page);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加资源列", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthPermissionColumnEntity authPermissionColumn) {
		authPermissionColumnService.insert(authPermissionColumn);

		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改资源列", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthPermissionColumnEntity authPermissionColumn) {
		ValidatorUtils.validateEntity(authPermissionColumn);
		authPermissionColumnService.updateById(authPermissionColumn);// 全部更新

		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除资源列", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "要删除的资源列数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authPermissionColumnService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

	// /**
	// * 列表，根据菜单id查询此菜单下的所有的资源列
	// */
	// @ApiOperation(value = "根据权限的id查询权限列", httpMethod = "POST", notes =
	// "返回分页查询结果")
	// @PostMapping("/listByPermissionId")
	// public R<List<AuthPermissionColumnEntity>> listByPermissionId(@RequestBody
	// Map<String, Long> params) {
	// List<AuthPermissionColumnEntity> list = authPermissionColumnService
	// .listByPermissionId(params.get("permissionId"));
	// return new R<List<AuthPermissionColumnEntity>>().ok(list);
	// }

}
