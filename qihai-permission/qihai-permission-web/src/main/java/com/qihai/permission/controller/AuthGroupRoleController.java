package com.qihai.permission.controller;

import java.util.Arrays;
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
import com.qihai.permission.entity.AuthDataRangeEntity;
import com.qihai.permission.entity.AuthGroupRoleEntity;
import com.qihai.permission.service.AuthGroupRoleService;

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
@Api("组与角色关联管理")
@RestController
@RequestMapping("${adminPath}/permission/authgrouprole")
public class AuthGroupRoleController {
	@Autowired
	private AuthGroupRoleService authGroupRoleService;

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加组与角色关联关系", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthGroupRoleEntity authGroupRole) {
		ValidatorUtils.validateEntity(authGroupRole);
		AuthGroupRoleEntity authGroupRoleTemp = new AuthGroupRoleEntity();
		authGroupRoleTemp.setRoleId(authGroupRole.getRoleId());
		authGroupRoleTemp.setGroupId(authGroupRole.getGroupId());
		authGroupRoleTemp.setDataRangeId(authGroupRole.getDataRangeId());
		int count = authGroupRoleService.selectCount(new EntityWrapper<AuthGroupRoleEntity>(authGroupRoleTemp));
		if (count == 0) {
			authGroupRoleService.insert(authGroupRole);
		}
		return new R<Object>().ok(null);
	}


	/**
	 * 删除
	 */
	@ApiOperation(value = "按id删除组与角色关联，逻辑删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键ID数组，批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authGroupRoleService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

}
