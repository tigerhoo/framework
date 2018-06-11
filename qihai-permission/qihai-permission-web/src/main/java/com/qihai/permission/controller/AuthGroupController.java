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
import com.baomidou.mybatisplus.plugins.Page;
import com.qihai.commerce.framework.exception.BaseException;
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
@Api("用户组管理")
@RestController
@RequestMapping("permission/authgroup")
public class AuthGroupController {
	@Autowired
	private AuthGroupService authGroupService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询用户组列表", httpMethod = "POST", response = PageUtils.class, notes = "返回查询结果集以及分页信息")
	@ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", required = false)
	@PostMapping("/list")
	@RequiresPermissions("permission:authgroup:list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthGroupEntity authGroup) {
		PageUtils page = authGroupService.queryPage(params, authGroup);
		return new R<PageUtils>().ok(page);
	}

	/**
	 * 信息
	 */
	@ApiOperation(value = "按id查询某个用户组信息", httpMethod = "GET", response = AuthGroupEntity.class, notes = "返回要查询的用户组")
	@ApiImplicitParam(name = "id", value = "用户组的id", paramType = "path", required = true, dataType = "Long")
	@GetMapping("/info/{id}")
	@RequiresPermissions("permission:authgroup:info")
	public R<AuthGroupEntity> info(@PathVariable("id") Long id) {
		AuthGroupEntity authGroup = authGroupService.selectById(id);
		return new R<AuthGroupEntity>().ok(authGroup);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "新增用户组", httpMethod = "POST")
	@PostMapping("/save")
	@RequiresPermissions("permission:authgroup:save")
	public R<Object> save(@RequestBody AuthGroupEntity authGroup) {
		return checkExists(authGroup, "save");
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改用户组信息", httpMethod = "POST")
	@PostMapping("/update")
	@RequiresPermissions("permission:authgroup:update")
	public R<Object> update(@RequestBody AuthGroupEntity authGroup) {
		return checkExists(authGroup, "update");
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除用户组", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "主键id数组", dataType = "Long[]", required = true)
	@PostMapping("/delete")
	@RequiresPermissions("permission:authgroup:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		authGroupService.deleteBatchIds(Arrays.asList(ids));
		return new R<Object>().ok(null);
	}

	@ApiOperation(value = "查询已关联的用户", httpMethod = "GET")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10"),
			@ApiImplicitParam(name = "groupId", value = "组ID", dataType = "Long", paramType = "path") })
	@GetMapping("/listAttachUser/{groupId}")
	public R<PageUtils> listAttachUser(Integer page, Integer limit, @PathVariable("groupId") Long groupId) {
		Page<UserGroupDTO> pages = new Page<UserGroupDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<UserGroupDTO> userRole = authGroupService.listAttachUser(pages, groupId);

		PageUtils pageUtils = new PageUtils(userRole.getRecords(), userRole.getTotal(), userRole.getSize(),
				userRole.getCurrent());

		return new R<PageUtils>().ok(pageUtils);
	}

	@ApiOperation(value = "查询已关联的角色", httpMethod = "GET")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10"),
			@ApiImplicitParam(name = "groupId", value = "组ID", dataType = "Long", paramType = "path") })
	@GetMapping("/listAttachRole/{groupId}")
	public R<PageUtils> listAttachRole(Integer page, Integer limit, @PathVariable("groupId") Long groupId) {
		Page<GroupRoleDTO> pages = new Page<GroupRoleDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<GroupRoleDTO> groupRole = authGroupService.listAttachRole(pages, groupId);

		PageUtils pageUtils = new PageUtils(groupRole.getRecords(), groupRole.getTotal(), groupRole.getSize(),
				groupRole.getCurrent());

		return new R<PageUtils>().ok(pageUtils);
	}

	private R<Object> checkExists(AuthGroupEntity authGroup, String type) {
		ValidatorUtils.validateEntity(authGroup);
		int count = authGroupService
				.selectCount(new EntityWrapper<AuthGroupEntity>().eq("group_code", authGroup.getGroupCode()));
		if (count > 0) {
			return new R<Object>().error(-1, "组编码已存在");
		} else {
			if ("save".equals(type)) {
				authGroupService.insert(authGroup);
			} else if ("update".equals(type)) {
				if (authGroup == null || authGroup.getId() == null) {
					throw new BaseException("修改时id为必传参数");
				}
				authGroupService.updateById(authGroup);
			}
			return new R<Object>().ok(null);
		}

	}

}
