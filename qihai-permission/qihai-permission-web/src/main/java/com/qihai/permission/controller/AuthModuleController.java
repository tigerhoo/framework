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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
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
@Api("模块管理")
@RestController
@RequestMapping("${adminPath}/permission/authmodule")
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
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthModuleEntity authModuleEntity) {
		PageUtils page = authModuleService.queryPage(params, authModuleEntity);
		return new R<PageUtils>().ok(page);
	}

	@ApiOperation(value = "按条件查询所有模块信息", httpMethod = "POST")
	@PostMapping("/listAll")
	public R<List<AuthModuleEntity>> listAll(@RequestBody(required = false) AuthModuleEntity authModuleEntity) {
		List<AuthModuleEntity> list = authModuleService
				.selectList(new EntityWrapper<AuthModuleEntity>(authModuleEntity));
		return new R<List<AuthModuleEntity>>().ok(list);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "保存模块信息", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthModuleEntity authModule) {
		ValidatorUtils.validateEntity(authModule);
		authModuleService.insert(authModule);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改模块信息", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthModuleEntity authModule) {
		ValidatorUtils.validateEntity(authModule);
		if (authModule.getId() == null) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), "更新时主键id不可为空");
		}
		authModuleService.updateById(authModule);
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除模块信息", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "要删除的模块的id数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authModuleService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

}
