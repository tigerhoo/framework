package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.qihai.permission.entity.AuthPermissionDimensionEntity;
import com.qihai.permission.service.AuthPermissionDimensionService;
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
@Api("资源维度管理")
@RestController
@RequestMapping("${adminPath}/permission/authpermissiondimension")
public class AuthPermissionDimensionController {
	@Autowired
	private AuthPermissionDimensionService authPermissionDimensionService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询资源维度", httpMethod = "POST")
	@PostMapping("/list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody AuthPermissionDimensionEntity authPermissionDimension) {
		PageUtils page = authPermissionDimensionService.queryPage(params, authPermissionDimension);

		return new R<PageUtils>().ok(page);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加资源维度", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthPermissionDimensionEntity authPermissionDimension) {
		ValidatorUtils.validateEntity(authPermissionDimension);
		authPermissionDimensionService.insert(authPermissionDimension);

		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改资源维度", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthPermissionDimensionEntity authPermissionDimension) {
		ValidatorUtils.validateEntity(authPermissionDimension);
		if (authPermissionDimension == null || authPermissionDimension.getId() == null) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), "更新时主键id不可为空");
		} else {
			authPermissionDimensionService.updateById(authPermissionDimension);// 根据主键更新
			return new R<Object>().ok(null);
		}

	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除资源维度", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authPermissionDimensionService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}

	}

}
