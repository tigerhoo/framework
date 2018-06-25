package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;
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
import com.qihai.permission.entity.AuthUserRoleEntity;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.AuthUserRoleService;
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
@Api(value = "用户与角色关联管理")
@RestController
@RequestMapping("${adminPath}/permission/authuserrole")
public class AuthUserRoleController {
	@Autowired
	private AuthUserRoleService authUserRoleService;

	// /**
	// * 列表
	// */
	// @ApiOperation(value = "查询用户角色关联列表", httpMethod = "POST", response =
	// UserInfoEntity.class, notes = "返回查询结果集以及分页")
	// @ApiImplicitParam(name = "params", value =
	// "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
	// @PostMapping("/list")
	// public R<PageUtils> list(@RequestParam Map<String, Object> params,
	// @RequestBody(required = false) AuthUserRoleEntity authUserRole) {
	// PageUtils page = authUserRoleService.queryPage(params, authUserRole);
	// return new R<PageUtils>().ok(page);
	// }

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加用户角色关联", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthUserRoleEntity authUserRole) {
		ValidatorUtils.validateEntity(authUserRole);
		AuthUserRoleEntity authUserRoleTemp = new AuthUserRoleEntity();
		authUserRoleTemp.setDataRangeId(authUserRole.getDataRangeId());
		authUserRoleTemp.setUserId(authUserRole.getUserId());
		authUserRoleTemp.setRoleId(authUserRole.getRoleId());
		// 查询是否已用户与角色是否已经有关联，如果已经有关联，则不再添加关联关系
		int count = authUserRoleService.selectCount(new EntityWrapper<AuthUserRoleEntity>(authUserRoleTemp));
		if (count == 0) {
			authUserRoleService.insert(authUserRole);
		}
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按关联id删除用户与角色关联", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		}
		authUserRoleService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

}
