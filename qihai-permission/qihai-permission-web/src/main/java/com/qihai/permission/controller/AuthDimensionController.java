package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.qihai.permission.entity.AuthDimensionEntity;
import com.qihai.permission.service.AuthDimensionService;

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
@Api("维度定义管理")
@RestController
@RequestMapping("${adminPath}/permission/authdimension")
public class AuthDimensionController {
	@Autowired
	private AuthDimensionService authDimensionService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "维度分页查询", httpMethod = "POST", notes = "返回查询结果集以及分页")
	@PostMapping("/list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthDimensionEntity authDimensiony) {
		PageUtils page = authDimensionService.queryPage(params, authDimensiony);

		return new R<PageUtils>().ok(page);
	}

	/**
	 * 列表
	 */
	@ApiOperation(value = "根据条件查询所有符合条件的维度", httpMethod = "POST", notes = "返回查询结果")
	@PostMapping("/listAll")
	public R<List<AuthDimensionEntity>> listAll(@RequestBody(required = false) AuthDimensionEntity authDimension) {
		List<AuthDimensionEntity> list = authDimensionService.selectList(new EntityWrapper<>(authDimension));
		return new R<List<AuthDimensionEntity>>().ok(list);
	}


	/**
	 * 保存
	 */
	@ApiOperation(value = "添加维度", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthDimensionEntity authDimension) {
		authDimensionService.insert(authDimension);

		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改维度", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthDimensionEntity authDimension) {
		ValidatorUtils.validateEntity(authDimension);
		authDimensionService.updateById(authDimension);
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按id删除维度，逻辑删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键ID数组，批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authDimensionService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

}
