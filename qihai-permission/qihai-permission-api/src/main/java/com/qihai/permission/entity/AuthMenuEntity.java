package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@TableName("auth_menu")
public class AuthMenuEntity extends DataEntity<AuthMenuEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * id
	 */
    private Long id;
    
    /**
	 * 菜单名称
	 */
    private String menuName;
    
    /**
	 * 菜单父ID
	 */
    private Long parentId;
    
    /**
	 * 模块id
	 */
    private Long moduleId;
    
    /**
	 * 菜单URL
	 */
    private String url;
    
    /**
	 * 分类编码
	 */
    private String categoryCode;
    
    /**
	 * 方法编码
	 */
    private String methodCode;
    
    /**
	 * 菜单功能描述
	 */
    private String description;
    
    /**
	 * 服务编码
	 */
    private String serviceCode;
    
    /**
	 * 是否锁定（0:使用中,1:锁定）默认:0
	 */
    private Integer isLocked;
    
    /**
	 * 是否需要鉴权(1:是,0:否,-1:没有配置)
	 */
    private Integer isAuth;
    
    /**
	 * 是否为公共菜单(1:是,0:否)
	 */
    private Integer isPublic;
    
    /**
	 * 权限访问范围(default：默认 使用鉴权；public：公开 不需要权限认证；lock：锁定)
	 */
    private String privilege;
    
    /**
	 * 菜单方法路径映射
	 */
    private String menuMapping;
    
    /**
	 * 日志跟踪id
	 */
    @ApiModelProperty(hidden=true)
    private String traceId;
    
    /**
	 * 创建人
	 */
    @ApiModelProperty(hidden=true)
    private String createdBy;
    
    /**
	 * 最后更新人
	 */
    @ApiModelProperty(hidden=true)
    private String updatedBy;
    
    /**
	 * 版本
	 */
    private Long version;
	
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：菜单名称
	 */
	public String getMenuName() {
		return menuName;
	}
	
	/**
	 * 设置：菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	/**
	 * 获取：菜单父ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 设置：菜单父ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取：模块id
	 */
	public Long getModuleId() {
		return moduleId;
	}
	
	/**
	 * 设置：模块id
	 */
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
	/**
	 * 获取：菜单URL
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 设置：菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 获取：分类编码
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	
	/**
	 * 设置：分类编码
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * 获取：方法编码
	 */
	public String getMethodCode() {
		return methodCode;
	}
	
	/**
	 * 设置：方法编码
	 */
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	
	/**
	 * 获取：菜单功能描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置：菜单功能描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取：服务编码
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	
	/**
	 * 设置：服务编码
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	/**
	 * 获取：是否锁定（0:使用中,1:锁定）默认:0
	 */
	public Integer getIsLocked() {
		return isLocked;
	}
	
	/**
	 * 设置：是否锁定（0:使用中,1:锁定）默认:0
	 */
	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}
	
	/**
	 * 获取：是否需要鉴权(1:是,0:否,-1:没有配置)
	 */
	public Integer getIsAuth() {
		return isAuth;
	}
	
	/**
	 * 设置：是否需要鉴权(1:是,0:否,-1:没有配置)
	 */
	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}
	
	/**
	 * 获取：是否为公共菜单(1:是,0:否)
	 */
	public Integer getIsPublic() {
		return isPublic;
	}
	
	/**
	 * 设置：是否为公共菜单(1:是,0:否)
	 */
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
	/**
	 * 获取：权限访问范围(default：默认 使用鉴权；public：公开 不需要权限认证；lock：锁定)
	 */
	public String getPrivilege() {
		return privilege;
	}
	
	/**
	 * 设置：权限访问范围(default：默认 使用鉴权；public：公开 不需要权限认证；lock：锁定)
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	/**
	 * 获取：菜单方法路径映射
	 */
	public String getMenuMapping() {
		return menuMapping;
	}
	
	/**
	 * 设置：菜单方法路径映射
	 */
	public void setMenuMapping(String menuMapping) {
		this.menuMapping = menuMapping;
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
	
	/**
	 * 获取：版本
	 */
	public Long getVersion() {
		return version;
	}
	
	/**
	 * 设置：版本
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
