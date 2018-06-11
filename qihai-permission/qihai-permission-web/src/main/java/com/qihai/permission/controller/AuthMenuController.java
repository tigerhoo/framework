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
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.entity.AuthMenuEntity;
import com.qihai.permission.service.AuthMenuService;

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
@Api("菜单按钮管理")
@RestController
@RequestMapping("permission/authmenu")
public class AuthMenuController {
	@Autowired
	private AuthMenuService authMenuService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "按条件分页查询按钮权限", httpMethod = "POST")
	@PostMapping("/list")
	@RequiresPermissions("permission:authmenu:list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params, @RequestBody AuthMenuEntity authMenu) {
		PageUtils page = authMenuService.queryPage(params, authMenu);

		return new R<PageUtils>().ok(page);
	}

	@ApiOperation(value = "按条件查询符合条件的所有按钮权限", httpMethod = "POST")
	@PostMapping("/listAll")
	@RequiresPermissions("permission:authmenu:listAll")
	public R<List<AuthMenuEntity>> listAll(@RequestBody AuthMenuEntity authMenu) {
		List<AuthMenuEntity> list = authMenuService.selectList(new EntityWrapper<AuthMenuEntity>(authMenu));
		return new R<List<AuthMenuEntity>>().ok(list);
	}

	@ApiOperation(value = "查询某个module下的所有菜单", httpMethod = "GET")
	@ApiImplicitParam(name = "moduleId", value = "模块id", dataType = "Long", paramType = "path", required = true)
	@GetMapping("/listMenuByModuleId/{moduleId}")
	@RequiresPermissions("permission:authmenu:listMenuByModuleId")
	public R<List<AuthMenuEntity>> listMenuByModuleId(@PathVariable("moduleId") Long moduleId) {
		List<AuthMenuEntity> list = authMenuService.listByModuleId(moduleId);
		return new R<List<AuthMenuEntity>>().ok(list);
	}
	

	/**
	 * 信息
	 */
	@ApiOperation(value = "获取某一个按钮信息", httpMethod = "GET")
	@ApiImplicitParam(name = "id", value = "按钮信息的id", dataType = "Long", paramType = "path", required = true)
	@GetMapping("/info/{id}")
	@RequiresPermissions("permission:authmenu:info")
	public R<AuthMenuEntity> info(@PathVariable("id") Long id) {
		AuthMenuEntity authMenu = authMenuService.selectById(id);

		return new R<AuthMenuEntity>().ok(authMenu);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加按钮信息", httpMethod = "POST")
	@PostMapping("/save")
	@RequiresPermissions("permission:authmenu:save")
	public R<Object> save(@RequestBody(required = false) AuthMenuEntity authMenu) {
		ValidatorUtils.validateEntity(authMenu);
		authMenuService.insert(authMenu);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改按钮信息", httpMethod = "POST")
	@PostMapping("/update")
	@RequiresPermissions("permission:authmenu:update")
	public R<Object> update(@RequestBody AuthMenuEntity authMenu) {
		ValidatorUtils.validateEntity(authMenu);
		authMenuService.updateById(authMenu);// 全部更新

		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除按钮信息", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "按钮信息id数组", dataType = "Long[]")
	@PostMapping("/delete")
	@RequiresPermissions("permission:authmenu:delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		authMenuService.deleteBatchIds(Arrays.asList(ids));

		return new R<Object>().ok(null);
	}

}
