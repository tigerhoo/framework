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
@ApiModel("数据范围维度关联表")
@TableName("auth_dimension_value")
public class AuthDimensionValueEntity extends CommonEntity<AuthDimensionValueEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(name = "id", value = "id")
	private Long id;

	/**
	 * 维度定义ID
	 */
	@ApiModelProperty(name = "authDimensionId", value = "维度定义ID", required = true, dataType = "Long")
	@NotNull(message = "维度定义ID不可为空")
	private Long authDimensionId;

	/**
	 * 数据范围ID
	 */
	@ApiModelProperty(name = "authDimensionId", value = "维度定义ID", required = true, dataType = "Long")
	@NotNull(message = "数据范围ID不可为空")
	private Long dataRangeId;

	/**
	 * 日志跟踪id
	 */
	@ApiModelProperty(hidden = true)
	private String traceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthDimensionId() {
		return authDimensionId;
	}

	public void setAuthDimensionId(Long authDimensionId) {
		this.authDimensionId = authDimensionId;
	}

	public Long getDataRangeId() {
		return dataRangeId;
	}

	public void setDataRangeId(Long dataRangeId) {
		this.dataRangeId = dataRangeId;
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
