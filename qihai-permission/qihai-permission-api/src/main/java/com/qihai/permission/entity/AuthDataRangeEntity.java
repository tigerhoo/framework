package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiModel("数据范围模型")
@TableName("auth_data_range")
public class AuthDataRangeEntity extends DataEntity<AuthDataRangeEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@ApiModelProperty(value = "id,更新时必传参数", name = "id")
	private Long id;

	/**
	 * 范围名称
	 */
	@ApiModelProperty(value = "范围名称", name = "dataRangeName", required = true)
	@NotBlank(message = "范围名称不可为空")
	private String dataRangeName;

	/**
	 * 范围描述
	 */
	@ApiModelProperty(value = "范围描述", name = "description")
	private String description;

	/**
	 * 创建人
	 */
	@ApiModelProperty(hidden = true)
	private String createdBy;

	/**
	 * 日志跟踪id
	 */
	@ApiModelProperty(hidden = true)
	private String traceId;

	/**
	 * 修改人
	 */
	@ApiModelProperty(hidden = true)
	private String updatedBy;

	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：范围名称
	 */
	public String getDataRangeName() {
		return dataRangeName;
	}

	/**
	 * 设置：范围名称
	 */
	public void setDataRangeName(String dataRangeName) {
		this.dataRangeName = dataRangeName;
	}

	/**
	 * 获取：范围描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置：范围描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 获取：修改人
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * 设置：修改人
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
