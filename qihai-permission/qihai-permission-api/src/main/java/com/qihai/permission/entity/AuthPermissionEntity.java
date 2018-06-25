package com.qihai.permission.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.baomidou.mybatisplus.annotations.TableName;

import com.qihai.permission.entity.common.CommonEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiModel("资源/权限模型(菜单按钮模型)")
@TableName("auth_permission")

public class AuthPermissionEntity extends CommonEntity<AuthPermissionEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(name = "id", value = "主键id")
	private Long id;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(name = "menuName", value = "菜单名称")
	private String menuName;

	/**
	 * 菜单父ID
	 */
	@ApiModelProperty(name = "parentId", value = "菜单父ID")
	private Long parentId;

	/**
	 * 模块id
	 */
	@ApiModelProperty(name = "moduleId", value = "模块id")
	private Long moduleId;

	/**
	 * 菜单URL
	 */
	@ApiModelProperty(name = "url", value = "菜单URL")
	private String url;

	/**
	 * 分类编码
	 */
	@ApiModelProperty(name = "categoryCode", value = "分类编码")
	private String categoryCode;

	/**
	 * 方法编码
	 */
	@ApiModelProperty(name = "methodCode", value = "方法编码")
	private String methodCode;

	/**
	 * 菜单功能描述
	 */
	@ApiModelProperty(name = "description", value = "菜单功能描述")
	private String description;

	/**
	 * 服务编码
	 */
	@ApiModelProperty(name = "serviceCode", value = "服务编码")
	private String serviceCode;

	/**
	 * 是否锁定（0:使用中,1:锁定）默认:0
	 */
	@ApiModelProperty(name = "isLocked", value = "是否锁定（0:使用中,1:锁定）默认:0")
	private Integer isLocked;

	/**
	 * 是否需要鉴权(1:是,0:否,-1:没有配置)
	 */
	@ApiModelProperty(name = "isAuth", value = "是否需要鉴权(1:是,0:否,-1:没有配置)")
	private Integer isAuth;

	/**
	 * 是否为公共菜单(1:是,0:否)
	 */
	@ApiModelProperty(name = "isPublic", value = "是否为公共菜单(1:是,0:否)")
	private Integer isPublic;

	/**
	 * 权限访问范围(default：默认 使用鉴权；public：公开 不需要权限认证；lock：锁定)
	 */
	@ApiModelProperty(name = "privilege", value = "权限访问范围(default：默认 使用鉴权；public：公开 不需要权限认证；lock：锁定)")
	private String privilege;

	/**
	 * 菜单方法路径映射
	 */
	@ApiModelProperty(name = "menuMapping", value = "菜单方法路径映射")
	private String menuMapping;

	/**
	 * 日志跟踪id
	 */
	@ApiModelProperty(hidden = true)
	private String traceId;

	/**
	 * 版本
	 */
	@ApiModelProperty(name = "version", value = "版本")
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getMenuMapping() {
		return menuMapping;
	}

	public void setMenuMapping(String menuMapping) {
		this.menuMapping = menuMapping;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
