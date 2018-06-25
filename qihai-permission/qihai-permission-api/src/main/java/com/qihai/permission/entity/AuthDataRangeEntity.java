package com.qihai.permission.entity;

import javax.validation.constraints.NotBlank;

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
@ApiModel("数据范围模型")
@TableName("auth_data_range")
public class AuthDataRangeEntity extends CommonEntity<AuthDataRangeEntity> {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataRangeName() {
		return dataRangeName;
	}

	public void setDataRangeName(String dataRangeName) {
		this.dataRangeName = dataRangeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
