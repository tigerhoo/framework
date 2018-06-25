package com.qihai.permission.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.enums.BizErrorCode.ValidateErrorType;
import com.qihai.commerce.framework.utils.ContextUtils;
import com.qihai.commerce.framework.utils.MD5Utils;
import com.qihai.commerce.framework.utils.PageUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.ValidatorUtils;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.permission.dto.UserRoleDTO;
import com.qihai.permission.entity.UserInfoEntity;
import com.qihai.permission.service.AuthMenuService;
import com.qihai.permission.service.AuthUserRoleService;
import com.qihai.permission.service.UserInfoService;
import com.qihai.permission.vo.UserInfoVO;
import com.qihai.permission.vo.menu.AuthMenuVO;

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
@Api(value = "用户管理")
@RestController
@RequestMapping("${adminPath}/permission/userinfo")
public class UserInfoController {

	
	private static final String initPassword = "123456";

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AuthUserRoleService authUserRole;

	@Autowired
	private AuthMenuService authMenuService;

	@ApiOperation(value = "查询用户列表", httpMethod = "POST", notes = "返回查询结果集以及分页")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10") })
	@PostMapping("/listUserInfo")
	public R<PageUtils> listUserInfo(Integer page, Integer limit,
			@RequestBody(required = false) UserInfoEntity userInfo) {
		Page<UserInfoVO> pages = new Page<UserInfoVO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<UserInfoVO> userInfoVo = userInfoService.listUserInfo(pages, userInfo);

		PageUtils pageUtils = new PageUtils(userInfoVo.getRecords(), userInfoVo.getTotal(), userInfoVo.getSize(),
				userInfoVo.getCurrent());

		return new R<PageUtils>().ok(pageUtils);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加用户", httpMethod = "POST")
	@PostMapping("/save")
	public R<Object> save(@RequestBody UserInfoEntity userInfo) {
		ValidatorUtils.validateEntity(userInfo);
		UserInfoEntity userInfoTemp = new UserInfoEntity();
		userInfoTemp.setLoginName(userInfo.getLoginName());
		int count = userInfoService.selectCount(new EntityWrapper<UserInfoEntity>(userInfoTemp));
		if (count == 0) {
			if (StringUtils.isBlank(userInfo.getPassword())) {
				userInfo.setPassword(initPassword);
			}
			// 用户密码需要加密存储
			userInfo.setPassword(MD5Utils.encryption(userInfo.getPassword()).toUpperCase());
			userInfoService.insert(userInfo);
			return new R<Object>().ok(null);
		} else {
			return new R<Object>().error(BizErrorCode.DBErrorType.DATA_ALREADY_EXISTED.getCode(), "登录账号已存在");
		}

	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "按id修改用户信息", httpMethod = "POST")
	@PostMapping("/update")
	public R<Object> update(@RequestBody UserInfoEntity userInfo) {
		ValidatorUtils.validateEntity(userInfo);
		if (userInfo == null || userInfo.getId() == null) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), "更新时主键id不可为空");
		}
		userInfoService.updateById(userInfo);
		return new R<Object>().ok(null);

	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "按id删除用户，逻辑删除", httpMethod = "POST")
	@ApiImplicitParam(name = "ids", value = "用户ID数组，批量删除", required = true, dataType = "Long[]")
	@PostMapping("/delete")
	public R<Object> delete(@RequestBody Long[] ids) {
		if (ids == null || ids.length == 0) {
			ValidateErrorType validateErrorType = BizErrorCode.ValidateErrorType.PARAMS_IS_NULL;
			return new R<Object>().error(validateErrorType.getCode(), validateErrorType.getDesc());
		} else {
			userInfoService.deleteBatchIds(Arrays.asList(ids));
			return new R<Object>().ok(null);
		}
	}

	@ApiOperation(value = "查询某个用户拥有的角色和数据范围(已关联角色)", httpMethod = "POST")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "请求第几页", dataType = "Long", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页多少行记录", dataType = "Long", paramType = "query", defaultValue = "10") })
	@PostMapping("/listRole")
	public R<PageUtils> listRole(Integer page, Integer limit, @RequestBody Map<String, Long> params) {
		Page<UserRoleDTO> pages = new Page<UserRoleDTO>();
		pages.setCurrent(page == null ? 1 : page);
		pages.setSize(limit == null ? 10 : limit);
		Page<UserRoleDTO> userRole = userInfoService.listUserRole(pages, params.get("userId"));
		PageUtils pageUtils = new PageUtils(userRole.getRecords(), userRole.getTotal(), userRole.getSize(),
				userRole.getCurrent());
		return new R<PageUtils>().ok(pageUtils);

	}


	@ApiOperation(value = "获取当前登录的用户拥有的所有菜单和资源权限", httpMethod = "POST")
	@PostMapping("/listCurrentUserPermission")
	public R<List<AuthMenuVO>> listUserPermission(@RequestHeader("token") String token) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("token", token);
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8090/sms/api/userInfo",
				HttpMethod.GET, requestEntity, String.class, "");

		JSONObject jsonObject = JSONObject.parseObject(exchange.getBody());
		if ("0".equals(jsonObject.getString("code"))) {
			UserInfo userInfo = jsonObject.getJSONObject("data").toJavaObject(UserInfo.class);
			System.out.println(userInfo);
			Set<Long> roleIds = authUserRole.listUserAllRole(Long.parseLong(userInfo.getId()));
			List<AuthMenuVO> authMenus = new ArrayList<AuthMenuVO>();
			if (roleIds != null && roleIds.size() > 0) {
				authMenus = authMenuService.listAllPermissionByRoleIds(roleIds);
				authMenus = this.treeMenuResources(authMenus, 0L);
			}

			return new R<List<AuthMenuVO>>().ok(authMenus);
		} else {
			return new R<List<AuthMenuVO>>().error(jsonObject.getString("code"), jsonObject.getString("msg"));
		}

	}

	private List<AuthMenuVO> treeMenuResources(List<AuthMenuVO> menuResources, Long parentId) {
		List<AuthMenuVO> authMenus = new ArrayList<>();
		for (AuthMenuVO mr : menuResources) {
			AuthMenuVO authMenu = new AuthMenuVO();
			if (mr.getParentId() != null && mr.getParentId().longValue() == parentId.longValue()) {
				try {
					BeanUtils.copyProperties(authMenu, mr);
				} catch (Exception e) {
					e.printStackTrace();
				}
				authMenu.setChildrens(treeMenuResources(menuResources, mr.getId()));
				authMenus.add(authMenu);
			}
		}
		return authMenus;
	}
	
	
	/**
	 * 获取当前登录的用户拥有的所有资源权限.
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	// @ApiOperation(value = "获取当前登录的用户拥有的所有资源权限", httpMethod = "POST")
	// @PostMapping("/listCurrentUserPermission")
	// public R<List<AuthUserPermissionDTO>> listUserPermission() {
	// UserInfo userInfo = ContextUtils.getUserInfo();
	// // List<AuthUserPermissionDTO> authPermissions = authPermissionService
	// // .listUserPermission(Long.parseLong(userInfo.getUsername()));
	// List<AuthUserPermissionDTO> authPermissions =
	// authPermissionService.listUserPermission(1L);
	// return new R<List<AuthUserPermissionDTO>>().ok(authPermissions);
	// }

	/**
	 * 获取当前登录用户所有的菜单权限.
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	// @ApiOperation(value = "获取当前登录用户所有的菜单权限", httpMethod = "POST")
	// @PostMapping("/listCurrentUserMenu")
	// public R<List<AuthMenuDTO>> listCurrentUserMenu() {
	// List<AuthMenuDTO> authMenus = new ArrayList<AuthMenuDTO>();
	// // UserInfo userInfo = ContextUtils.getUserInfo();
	// // Long.parseLong(userInfo.getUsername())
	// Set<Long> roleIds = authUserRole.listUserAllRole(1L);
	// List<AuthMenuEntity> listMenus = new ArrayList<AuthMenuEntity>();
	// if (roleIds != null && roleIds.size() > 0) {
	// listMenus = authMenuService.listMenusByRoleIds(roleIds);
	// authMenus = this.treeMenuResources(listMenus, 0L);
	// }
	//
	// return new R<List<AuthMenuDTO>>().ok(authMenus);
	// }
	//
	//
	//
	//
	// private List<AuthMenuDTO> treeMenuResources(List<AuthMenuEntity>
	// menuResources, Long parentId) {
	// List<AuthMenuDTO> children = new ArrayList<>();
	// for (AuthMenuEntity mr : menuResources) {
	// if (mr.getParentId() != null && mr.getParentId().longValue() ==
	// parentId.longValue()) {
	// AuthMenuDTO authMenu = new AuthMenuDTO();
	// authMenu.setAuthMenuEntity(mr);
	// authMenu.setChildren(treeMenuResources(menuResources, mr.getId()));
	// children.add(authMenu);
	// }
	// }
	// return children;
	// }

}
