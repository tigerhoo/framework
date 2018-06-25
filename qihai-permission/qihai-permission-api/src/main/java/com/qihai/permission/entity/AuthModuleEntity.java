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
 * @date 2018-05-29 09:05:48
 */
@ApiModel("模块实体模型")
@TableName("auth_module")
public class AuthModuleEntity extends CommonEntity<AuthModuleEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 服务编码
	 */
	@ApiModelProperty(name = "serviceCode", value = "服务编码")
	private String serviceCode;

	/**
	 * 模块编码
	 */
	@ApiModelProperty(name = "moduleName", value = "模块编码")
	private String moduleName;

	/**
	 * 模块映射路径
	 */
	@ApiModelProperty(name = "moduleMapping", value = "模块映射路径")
	private String moduleMapping;

	/**
	 * 模块描述
	 */
	@ApiModelProperty(name = "description", value = "模块描述")
	private String description;

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

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleMapping() {
		return moduleMapping;
	}

	public void setModuleMapping(String moduleMapping) {
		this.moduleMapping = moduleMapping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
