package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.qihai.commerce.framework.exception.BaseException;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthUserGroupEntity;
import com.qihai.permission.service.AuthUserGroupService;
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
@Api("用户与组关联管理")
@RestController
@RequestMapping("permission/authusergroup")
public class AuthUserGroupController {
	@Autowired
	private AuthUserGroupService authUserGroupService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询用户与组关系表", httpMethod = "POST", notes = "返回查询结果集以及分页")
	@PostMapping("/list")
	@RequiresPermissions("permission:authusergroup:list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params) {
		PageUtils page = authUserGroupService.queryPage(params);

		return new R<PageUtils>().ok(page);
	}

	// /**
	// * 信息
	// */
	// @GetMapping("/info/{id}")
	// @RequiresPermissions("permission:authusergroup:info")
	// public R<AuthUserGroupEntity> info(@PathVariable("id") Long id) {
	// AuthUserGroupEntity authUserGroup = authUserGroupService.selectById(id);
	//
	// return new R<AuthUserGroupEntity>().ok(authUserGroup);
	// }

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加用户与组之间的关联关系", httpMethod = "POST")
	@PostMapping("/save")
	@RequiresPermissions("permission:authusergroup:save")
	public R<Object> save(@RequestBody AuthUserGroupEntity authUserGroup) {
		ValidatorUtils.validateEntity(authUserGroup);
		authUserGroupService.insert(authUserGroup);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改用户与组之间的关联关系", httpMethod = "POST")
	@PostMapping("/update")
	@RequiresPermissions("permission:authusergroup:update")
	public R<Object> update(@RequestBody AuthUserGroupEntity authUserGroup) {
		ValidatorUtils.validateEntity(authUserGroup);
		if (authUserGroup.getId() == null) {
			throw new BaseException("更新时主键id不可为空");
		}
		authUserGroupService.updateById(authUserGroup);// 全部更新
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除用户与组之间的关联", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	@RequiresPermissions("permission:authusergroup:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BaseException("请传入需要删除的数据的id数组");
		}
		authUserGroupService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

}
