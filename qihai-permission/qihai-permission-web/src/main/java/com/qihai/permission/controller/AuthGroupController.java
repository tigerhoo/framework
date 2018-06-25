package com.qihai.permission.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.DBErrorType;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.dto.GroupRoleDTO;
import com.qihai.permission.dto.UserGroupDTO;
import com.qihai.permission.entity.AuthGroupEntity;
import com.qihai.permission.service.AuthGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@Api("组管理")
@RestController
@RequestMapping("${adminPath}/permission/authgroup")
public class AuthGroupController {
	@Autowired
	private AuthGroupService authGroupService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询用户组列表", httpMethod = "POST", response = PageUtils.class, notes = "返回查询结果集以及分页信息")
	@ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
	@PostMapping("/list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthGroupEntity authGroup) {
		PageUtils page = authGroupService.queryPage(params, authGroup);
		return new R<PageUtils>().ok(page);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "新增用户组", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody AuthGroupEntity authGroup) {
		return checkExists(authGroup, "save");
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改用户组信息", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthGroupEntity authGroup) {
		return checkExists(authGroup, "update");
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除用户组", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authGroupService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

	@ApiOperation(value = "查询已关联的用户", httpMethod = "POST")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10") })
	@PostMapping("/listAttachUser")
	public R<PageUtils> listAttachUser(Integer page, Integer limit, @RequestBody Map<String, Long> params) {
		Page<UserGroupDTO> pages = new Page<UserGroupDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<UserGroupDTO> userRole = authGroupService.listAttachUser(pages, params.get("groupId"));

		PageUtils pageUtils = new PageUtils(userRole.getRecords(), userRole.getTotal(), userRole.getSize(),
				userRole.getCurrent());

		return new R<PageUtils>().ok(pageUtils);
	}

	@ApiOperation(value = "查询已关联的角色", httpMethod = "POST")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10") })
	@PostMapping("/listAttachRole")
	public R<PageUtils> listAttachRole(Integer page, Integer limit, @RequestBody Map<String, Long> params) {
		Page<GroupRoleDTO> pages = new Page<GroupRoleDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<GroupRoleDTO> groupRole = authGroupService.listAttachRole(pages, params.get("groupId"));

		PageUtils pageUtils = new PageUtils(groupRole.getRecords(), groupRole.getTotal(), groupRole.getSize(),
				groupRole.getCurrent());

		return new R<PageUtils>().ok(pageUtils);
	}

	private R<Object> checkExists(AuthGroupEntity authGroup, String type) {
		ValidatorUtils.validateEntity(authGroup);
		DBErrorType errorType = BizErrorCode.DBErrorType.DATA_ALREADY_EXISTED;
		if ("save".equals(type)) {
			AuthGroupEntity authGroupEntity = new AuthGroupEntity();
			authGroupEntity.setGroupCode(authGroup.getGroupCode());
			int count = authGroupService.selectCount(new EntityWrapper<AuthGroupEntity>(authGroupEntity));
			if (count > 0) {
				return new R<Object>().error(errorType.getCode(), "组编码已存在");
			} else {
				authGroupService.insert(authGroup);
			}
		} else if ("update".equals(type)) {
			if (authGroup == null || authGroup.getId() == null) {
				ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
				return new R<Object>().error(validateErrorType.getCode(), "更新时主键id不可为空");
			}
			int count = authGroupService.selectCount(new EntityWrapper<AuthGroupEntity>()
					.eq("group_code", authGroup.getGroupCode()).ne("id", authGroup.getId()));
			if (count > 0) {
				return new R<Object>().error(errorType.getCode(), "组编码已存在");
			} else {
				authGroupService.updateById(authGroup);
			}
		}
		return new R<Object>().ok(null);
	}

}
