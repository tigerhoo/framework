package com.qihai.permission.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiModel("菜单资源模型")
@TableName("auth_menu_resources")
public class AuthMenuResourcesEntity extends DataEntity<AuthMenuResourcesEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 主键
	 */
    private Long id;
    
    /**
	 * 路径编码
	 */
    @ApiModelProperty(name="menuCode",value="路径",required=true)
    @NotBlank(message="路径不可为空")
    private String menuCode;
    
    /**
	 * 标题
	 */
    @ApiModelProperty(name="menuTitle",value="标题",required=true,dataType="String")
    @NotBlank(message="标题不可为空")
    private String menuTitle;
    
    /**
	 * 父级节点
	 */
    @ApiModelProperty(name="parentId",value="父级节点",required=true,dataType="Long")
    @NotNull(message="父节点不可为空")
    private Long parentId;
    
    /**
	 * 图标
	 */
    @ApiModelProperty(name="menuIcon",value="图标",dataType="String")
    private String menuIcon;
    
    /**
	 * 资源路径
	 */
    private String menuPath;
    
    /**
	 * 打开方式(_self 本页面打开，_blank 新建标签页打开)
	 */
    @ApiModelProperty(name="menuType",value="打开方式(_self 本页面打开，_blank 新建标签页打开)",dataType="String")
    private String menuType;
    
    /**
	 * 排序
	 */
    @ApiModelProperty(name="displaySequence",value="排序",dataType="Integer")
    @NotNull(message="排序不可为空")
    private Integer displaySequence;
    
    /**
	 * 描述
	 */
    @ApiModelProperty(name="description",value="描述",dataType="String")
    private String description;
    
    /**
	 * 前端组件
	 */
    private String component;
    
    /**
	 * 权限ID
	 */
    @ApiModelProperty(name="authMenuId",value="权限ID，关联auth_menu的id")
    private Long authMenuId;
    
    /**
	 * 是否显示（1、代表显示 0、代表隐藏）
	 */
    private Integer displayFlag;
    
    
    /**
	 * 日志跟踪id
	 */
    private String traceId;
    
    /**
	 * 创建人
	 */
    private String createdBy;
    
    /**
	 * 最后更新人
	 */
    private String updatedBy;
	
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：路径编码
	 */
	public String getMenuCode() {
		return menuCode;
	}
	
	/**
	 * 设置：路径编码
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	/**
	 * 获取：标题
	 */
	public String getMenuTitle() {
		return menuTitle;
	}
	
	/**
	 * 设置：标题
	 */
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	
	/**
	 * 获取：父级节点
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 设置：父级节点
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取：图标
	 */
	public String getMenuIcon() {
		return menuIcon;
	}
	
	/**
	 * 设置：图标
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	
	/**
	 * 获取：资源路径
	 */
	public String getMenuPath() {
		return menuPath;
	}
	
	/**
	 * 设置：资源路径
	 */
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	
	/**
	 * 获取：打开方式
	 */
	public String getMenuType() {
		return menuType;
	}
	
	/**
	 * 设置：打开方式
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	/**
	 * 获取：排序
	 */
	public Integer getDisplaySequence() {
		return displaySequence;
	}
	
	/**
	 * 设置：排序
	 */
	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}
	
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取：前端组件
	 */
	public String getComponent() {
		return component;
	}
	
	/**
	 * 设置：前端组件
	 */
	public void setComponent(String component) {
		this.component = component;
	}
	
	/**
	 * 获取：权限ID
	 */
	public Long getAuthMenuId() {
		return authMenuId;
	}
	
	/**
	 * 设置：权限ID
	 */
	public void setAuthMenuId(Long authMenuId) {
		this.authMenuId = authMenuId;
	}
	
	/**
	 * 获取：是否显示（1、代表显示 0、代表隐藏）
	 */
	public Integer getDisplayFlag() {
		return displayFlag;
	}
	
	/**
	 * 设置：是否显示（1、代表显示 0、代表隐藏）
	 */
	public void setDisplayFlag(Integer displayFlag) {
		this.displayFlag = displayFlag;
	}
	
	
	/**
	 * 获取：日志跟踪id
	 */
	public String getTraceId() {
		return traceId;
	}
	
	/**
	 * 设置：日志跟踪id
	 */
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	
	/**
	 * 获取：创建人
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * 设置：创建人
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * 获取：最后更新人
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	/**
	 * 设置：最后更新人
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
