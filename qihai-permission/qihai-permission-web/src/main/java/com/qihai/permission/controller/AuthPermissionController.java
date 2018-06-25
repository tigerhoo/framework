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
import com.baomidou.mybatisplus.plugins.Page;
import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.permission.dto.permission.AuthPermissionModuleDTO;
import com.qihai.permission.entity.AuthPermissionEntity;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.AuthPermissionService;
import com.qihai.permission.vo.UserInfoVO;

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
@Api("资源管理")
@RestController
@RequestMapping("${adminPath}/permission/authpermission")
public class AuthPermissionController {
	@Autowired
	private AuthPermissionService authPermissionService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "按条件分页查询按钮权限", httpMethod = "POST")
	@PostMapping("/list")
	public R<PageUtils> list(@RequestParam Map<String, Object> params,
			@RequestBody(required = false) AuthPermissionEntity authMenu) {
		PageUtils page = authPermissionService.queryPage(params, authMenu);

		return new R<PageUtils>().ok(page);
	}

	/**
	 * 列表
	 */
	@ApiOperation(value = "按条件分页查询资源列表", httpMethod = "POST")
	@PostMapping("/listPermission")
	public R<PageUtils> listPermission(Integer page, Integer limit,
			@RequestBody(required = false) AuthPermissionModuleDTO permissionMoudule) {

		Page<AuthPermissionModuleDTO> pages = new Page<AuthPermissionModuleDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<AuthPermissionModuleDTO> permissionModuleDto = authPermissionService.listPermission(pages,
				permissionMoudule);
		PageUtils pageUtils = new PageUtils(permissionModuleDto.getRecords(), permissionModuleDto.getTotal(),
				permissionModuleDto.getSize(), permissionModuleDto.getCurrent());

		return new R<PageUtils>().ok(pageUtils);
	}

	@ApiOperation(value = "按条件查询符合条件的所有按钮权限", httpMethod = "POST")
	@PostMapping("/listAll")
	public R<List<AuthPermissionEntity>> listAll(@RequestBody AuthPermissionEntity authMenu) {
		List<AuthPermissionEntity> list = authPermissionService
				.selectList(new EntityWrapper<AuthPermissionEntity>(authMenu));
		return new R<List<AuthPermissionEntity>>().ok(list);
	}

	@ApiOperation(value = "查询某个module下的所有按钮", httpMethod = "POST")
	@PostMapping("/listPermissionByModuleId")
	public R<List<AuthPermissionEntity>> listMenuByModuleId(@RequestBody Map<String, Long> params) {
		List<AuthPermissionEntity> list = authPermissionService.listByModuleId(params.get("moduleId"));
		return new R<List<AuthPermissionEntity>>().ok(list);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加按钮信息", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody(required = false) AuthPermissionEntity authMenu) {
		ValidatorUtils.validateEntity(authMenu);
		authPermissionService.insert(authMenu);
		return new R<Object>().ok(null);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "修改按钮信息", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody AuthPermissionEntity authMenu) {
		ValidatorUtils.validateEntity(authMenu);
		if(authMenu.getId()==null) {
			return new R<Object>().error(BizErrorCode.ValidateErrorType.PARAMS_IS_NULL.getCode(), "更新时主键id不可为空");
		}
		authPermissionService.updateById(authMenu);
		return new R<Object>().ok(null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除按钮信息", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "按钮信息id数组", dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			authPermissionService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}

	}

}
