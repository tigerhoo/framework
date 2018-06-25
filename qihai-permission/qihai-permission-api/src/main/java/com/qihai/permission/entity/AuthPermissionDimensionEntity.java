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
 * @date 2018-05-29 09:05:48
 */
@ApiModel("资源维度模型")
@TableName("auth_permission_dimension")

public class AuthPermissionDimensionEntity extends CommonEntity<AuthPermissionDimensionEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(name = "id", value = "主键id,更新时必传", notes = "ids")
	private Long id;

	/**
	 * 维度
	 */
	@ApiModelProperty(name = "dimensionId", value = "维度", required = true, dataType = "Long")
	@NotNull(message = "维度不可为空")
	private Long dimensionId;

	/**
	 * 维度参数
	 */
	@ApiModelProperty(name = "dimensionParam", value = "维度参数", required = true, dataType = "String")
	@NotBlank(message = "维度参数不可为空")
	private String dimensionParam;

	/**
	 * 字段名
	 */
	@ApiModelProperty(name = "fieldName", value = "字段名", required = true, dataType = "String")
	@NotBlank(message = "字段名不可为空")
	private String fieldName;

	/**
	 * 属性名
	 */
	@ApiModelProperty(name = "attributeName", value = "属性名", required = true, dataType = "String")
	@NotBlank(message = "属性名不可为空")
	private String attributeName;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(name = "permissionId", value = "菜单ID", required = true, dataType = "Long")
	@NotNull(message = "维度参数不可为空")
	private Long permissionId;

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

	public Long getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Long dimensionId) {
		this.dimensionId = dimensionId;
	}

	public String getDimensionParam() {
		return dimensionParam;
	}

	public void setDimensionParam(String dimensionParam) {
		this.dimensionParam = dimensionParam;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
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
