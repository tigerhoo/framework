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
import com.qihai.commerce.framework.utils.R;
import com.baomidou.mybatisplus.plugins.Page;
import com.qihai.commerce.framework.exception.BaseException;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@Api(value="用户管理",description="")
@RestController
@RequestMapping("permission/userinfo")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "查询用户列表", httpMethod = "POST", notes = "返回查询结果集以及分页")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "params", value = "分页请求参数，请放到请求url路径后用page=1&limit=10来表示请求第1页，每页显示10条，此值为默认", paramType = "query", required = false) })
	@PostMapping("/list")
	@RequiresPermissions("permission:userinfo:list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) UserInfoEntity userInfo) {
		PageUtils page = userInfoService.queryPage(params, userInfo);
		return new R<PageUtils>().ok(page);
	}

	/**
	 * 信息
	 */
	@ApiOperation(value = "按id查询某一个用户的信息", httpMethod = "GET", notes = "返回用户信息")
	@ApiImplicitParam(name = "id", value = "用户的id，表示查哪个用户，参数放在路径上", required = true, paramType = "path", dataType = "Long")
	@GetMapping("/info/{id}")
	@RequiresPermissions("permission:userinfo:info")
	public R<UserInfoEntity> info(@PathVariable("id") Long id) {
		UserInfoEntity userInfo = userInfoService.selectById(id);
		return new R<UserInfoEntity>().ok(userInfo);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加用户", httpMethod = "POST")
	@PostMapping("/save")
	@RequiresPermissions("permission:userinfo:save")
	public R<Object> save(@RequestBody UserInfoEntity userInfo) {
		ValidatorUtils.validateEntity(userInfo);
		userInfoService.insert(userInfo);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "按id修改用户信息", httpMethod = "POST")
	@PostMapping("/update")
	@RequiresPermissions("permission:userinfo:update")
	public R<Object> update(@RequestBody UserInfoEntity userInfo) {
		if(userInfo==null||userInfo.getId()==null) {
    		throw new BaseException("修改时id为必传参数");
    	}
		ValidatorUtils.validateEntity(userInfo);
		userInfoService.updateById(userInfo);
		return new R<Object>().ok(null);

	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按id删除用户，逻辑删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "用户ID数组，批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	@RequiresPermissions("permission:userinfo:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BaseException("请传入需要删除的数据的id");
		} else {
			userInfoService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

	@ApiOperation(value = "查询某个用户拥有的角色和数据范围(已关联角色)", httpMethod = "POST")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10"),
			@ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", paramType = "path") })
	@GetMapping("/listRole/{userId}")
	public R<PageUtils> listRole(Integer page, Integer limit, @PathVariable("userId") Long userId) {
		Page<UserRoleDTO> pages = new Page<UserRoleDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<UserRoleDTO> userRole = userInfoService.listUserRole(pages, userId);

		PageUtils pageUtils = new PageUtils(userRole.getRecords(), userRole.getTotal(), userRole.getSize(),
				userRole.getCurrent());

		return new R<PageUtils>().ok(pageUtils);

	}

}
