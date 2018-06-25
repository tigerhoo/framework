package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.qihai.permission.entity.common.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiModel("资源维度模型")
@TableName("auth_dimension")
public class AuthDimensionEntity extends CommonEntity<AuthDimensionEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "id,更新时必传参数", name = "id")
	private Long id;

	/**
	 * 维度编码
	 */
	@ApiModelProperty(value = "维度编码", name = "dimensionCode", required = true)
	@NotBlank(message = "维度编码不能为空")
	private String dimensionCode;

	/**
	 * 维度名称
	 */
	@ApiModelProperty(value = "维度名称", name = "dimensionName", required = true)
	@NotBlank(message = "维度名称不可为空")
	private String dimensionName;

	/**
	 * 值列表显示方式 （10代表固定值 20 选择 30 数据字典）
	 */
	@ApiModelProperty(value = "值列表显示方式 （10代表固定值 20 选择  30 数据字典）", name = "displayMode", required = true)
	@NotNull(message = "值列表显示方式不可为空")
	private Integer displayMode;

	/**
	 * 选择的URL地址
	 */
	@ApiModelProperty(value = "URL地址", name = "openUrl")
	private String openUrl;

	/**
	 * 属性类型(10 数字，20 字符串)
	 */
	@ApiModelProperty(value = "属性类型,10 数字，20 字符串", name = "propertyType")
	private String propertyType;

	/**
	 * 维度描述
	 */
	@ApiModelProperty(value = "维度描述", name = "dimensionDesc")
	private String dimensionDesc;

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

	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getDimensionDesc() {
		return dimensionDesc;
	}

	public void setDimensionDesc(String dimensionDesc) {
		this.dimensionDesc = dimensionDesc;
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
