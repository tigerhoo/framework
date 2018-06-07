package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("模块实体模型")
@TableName("auth_module")
public class AuthModuleEntity extends DataEntity<AuthModuleEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * id
	 */
    private Long id;
    
    /**
	 * 服务编码
	 */
    @ApiModelProperty(name="serviceCode",value="服务编码")
    private String serviceCode;
    
    /**
	 * 模块编码
	 */
    @ApiModelProperty(name="moduleName",value="模块编码")
    private String moduleName;
    
    /**
	 * 模块映射路径
	 */
    @ApiModelProperty(name="moduleMapping",value="模块映射路径")
    private String moduleMapping;
    
    /**
	 * 模块描述
	 */
    @ApiModelProperty(name="description",value="模块描述")
    private String description;
    
    
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
    @ApiModelProperty(name="version",value="版本")
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
	 * 获取：模块编码
	 */
	public String getModuleName() {
		return moduleName;
	}
	
	/**
	 * 设置：模块编码
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	/**
	 * 获取：模块映射路径
	 */
	public String getModuleMapping() {
		return moduleMapping;
	}
	
	/**
	 * 设置：模块映射路径
	 */
	public void setModuleMapping(String moduleMapping) {
		this.moduleMapping = moduleMapping;
	}
	
	/**
	 * 获取：模块描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置：模块描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
