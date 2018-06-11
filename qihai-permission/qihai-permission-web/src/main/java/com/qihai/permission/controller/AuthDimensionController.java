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
import com.qihai.commerce.framework.exception.BaseException;
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
@RequestMapping("permission/authdimension")
public class AuthDimensionController {
	@Autowired
	private AuthDimensionService authDimensionService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "维度分页查询", httpMethod = "POST", notes = "返回查询结果集以及分页")
	@PostMapping("/list")
	@RequiresPermissions("permission:authdimension:list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody AuthDimensionEntity authDimensiony) {
		PageUtils page = authDimensionService.queryPage(params, authDimensiony);

		return new R<PageUtils>().ok(page);
	}

	/**
	 * 列表
	 */
	@ApiOperation(value = "根据条件查询所有符合条件的维度", httpMethod = "GET", notes = "返回查询结果")
	@PostMapping("/listAll")
	@RequiresPermissions("permission:authdimension:listAll")
	public R<List<AuthDimensionEntity>> listAll(@RequestBody(required = false) AuthDimensionEntity authDimension) {
		List<AuthDimensionEntity> list = authDimensionService.selectList(new EntityWrapper<>(authDimension));
		return new R<List<AuthDimensionEntity>>().ok(list);
	}

	/**
	 * 信息
	 */
	@ApiOperation(value = "按id查询某个维度信息", httpMethod = "GET", notes = "返回查询结果集以及分页")
	@ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "path", dataType = "Long")
	@GetMapping("/info/{id}")
	@RequiresPermissions("permission:authdimension:info")
	public R<AuthDimensionEntity> info(@PathVariable("id") Long id) {
		AuthDimensionEntity authDimension = authDimensionService.selectById(id);

		return new R<AuthDimensionEntity>().ok(authDimension);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加维度", httpMethod = "POST")
	@PostMapping("/save")
	@RequiresPermissions("permission:authdimension:save")
	public R<Object> save(@RequestBody AuthDimensionEntity authDimension) {
		authDimensionService.insert(authDimension);

		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改维度", httpMethod = "POST")
	@PostMapping("/update")
	@RequiresPermissions("permission:authdimension:update")
	public R<Object> update(@RequestBody AuthDimensionEntity authDimension) {
		ValidatorUtils.validateEntity(authDimension);
		authDimensionService.updateById(authDimension);// 全部更新

		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按id删除维度，逻辑删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键ID数组，批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	@RequiresPermissions("permission:authdimension:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BaseException("请传入需要删除的数据的id");
		}
		authDimensionService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

}
