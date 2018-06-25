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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
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
@RequestMapping("${adminPath}/permission/authusergroup")
public class AuthUserGroupController {
	@Autowired
	private AuthUserGroupService authUserGroupService;

	// /**
	// * 列表
	// */
	// @ApiOperation(value = "查询用户与组关系表", httpMethod = "POST", notes =
	// "返回查询结果集以及分页")
	// @PostMapping("/list")
	// public R<PageUtils> list(@RequestParam Map<String, Object> params) {
	// PageUtils page = authUserGroupService.queryPage(params);
	//
	// return new R<PageUtils>().ok(page);
	// }

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加用户与组之间的关联关系", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthUserGroupEntity authUserGroup) {
		ValidatorUtils.validateEntity(authUserGroup);
		AuthUserGroupEntity authUserGroupTemp = new AuthUserGroupEntity();
		authUserGroupTemp.setUserId(authUserGroup.getUserId());
		authUserGroupTemp.setGroupId(authUserGroup.getGroupId());
		// 校验是否已经有数据了.
		int count = authUserGroupService.selectCount(new EntityWrapper<AuthUserGroupEntity>(authUserGroupTemp));
		if (count == 0) {
			authUserGroupService.insert(authUserGroup);
		}
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按关联id删除用户与组之间的关联", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		}
		authUserGroupService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

}
