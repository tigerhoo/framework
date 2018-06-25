package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;
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
import com.qihai.permission.entity.AuthPermissionReportingEntity;
import com.qihai.permission.service.AuthPermissionReportingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 微服务上报管理
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Api("上报管理")
@RestController
@RequestMapping("${adminPath}/permission/authpermissionreporting")
public class AuthPermissionReportingController {
	@Autowired
	private AuthPermissionReportingService authPermissionReportingService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询资源", httpMethod = "POST", response = R.class, notes = "返回查询结果集以及分页")
	@PostMapping("/list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthPermissionReportingEntity authPermissionReporting) {
		PageUtils page = authPermissionReportingService.queryPage(params, authPermissionReporting);

		return new R<PageUtils>().ok(page);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加资源", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthPermissionReportingEntity authPermissionReporting) {
		authPermissionReportingService.insert(authPermissionReporting);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "更新资源", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthPermissionReportingEntity authPermissionReporting) {
		ValidatorUtils.validateEntity(authPermissionReporting);
		authPermissionReportingService.updateById(authPermissionReporting);// 按id更新
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除资源，可批量删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组，可批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authPermissionReportingService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}

	}

}
