package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.service.AuthMenuService;
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
@Api("菜单管理")
@RestController
@RequestMapping("${adminPath}/permission/authmenu")
public class AuthMenuController {

	@Autowired
	private AuthMenuService authMenuService;

	@ApiOperation(value = "查询某个菜单下的子菜单", httpMethod = "POST")
	@PostMapping("listMenu")
	public R<List<AuthMenuEntity>> listMenu(@RequestBody Map<String, Long> params) {
		List<AuthMenuEntity> list = authMenuService.listMenu(params.getOrDefault("parentId", 0L));
		return new R<List<AuthMenuEntity>>().ok(list);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "保存菜单信息", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthMenuEntity authMenuResources) {
		ValidatorUtils.validateEntity(authMenuResources);
		authMenuService.insert(authMenuResources);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "更新菜单信息", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthMenuEntity authMenuResources) {
		ValidatorUtils.validateEntity(authMenuResources);
		if (authMenuResources.getId() == null) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), "更新时主键id不可为空");
		}
		authMenuService.updateById(authMenuResources);// 全部更新
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除菜单信息", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "菜单主键ID数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authMenuService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

}
