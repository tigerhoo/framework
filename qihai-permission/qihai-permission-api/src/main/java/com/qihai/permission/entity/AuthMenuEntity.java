package com.qihai.permission.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel("菜单模型")
@TableName("auth_menu")
public class AuthMenuEntity extends CommonEntity<AuthMenuEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 菜单编码
	 */
	@ApiModelProperty(name = "menuCode", value = "菜单编码")
	private String menuCode;

	/**
	 * 标题
	 */
	@ApiModelProperty(name = "menuTitle", value = "标题", required = true, dataType = "String")
	@NotBlank(message = "标题不可为空")
	private String menuTitle;

	/**
	 * 父级节点
	 */
	@ApiModelProperty(name = "parentId", value = "父级节点", required = true, dataType = "Long")
	@NotNull(message = "父节点不可为空")
	private Long parentId;

	/**
	 * 图标
	 */
	@ApiModelProperty(name = "menuIcon", value = "图标", dataType = "String")
	private String menuIcon;

	/**
	 * 资源路径
	 */
	@ApiModelProperty(name = "menuPath", value = "菜单路径", dataType = "String", required = true)
	@NotBlank(message = "路径不可为空")
	private String menuPath;

	/**
	 * 打开方式(_self 本页面打开，_blank 新建标签页打开)
	 */
	@ApiModelProperty(name = "menuType", value = "打开方式(_self 本页面打开，_blank 新建标签页打开)", dataType = "String")
	private String menuType;

	/**
	 * 排序
	 */
	@ApiModelProperty(name = "displaySequence", value = "排序", dataType = "Integer")
	@NotNull(message = "排序不可为空")
	private Integer displaySequence;

	/**
	 * 描述
	 */
	@ApiModelProperty(name = "description", value = "描述", dataType = "String")
	private String description;

	/**
	 * 前端组件
	 */
	private String component;

	/**
	 * 权限ID
	 */
	@ApiModelProperty(name = "authPermissionId", value = "权限ID，关联auth_permission的id")
	private Long authPermissionId;

	/**
	 * 是否显示（1、代表显示 0、代表隐藏）
	 */
	private Integer displayFlag;

	/**
	 * 日志跟踪id
	 */
	private String traceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Integer getDisplaySequence() {
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Long getAuthPermissionId() {
		return authPermissionId;
	}

	public void setAuthPermissionId(Long authPermissionId) {
		this.authPermissionId = authPermissionId;
	}

	public Integer getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(Integer displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
