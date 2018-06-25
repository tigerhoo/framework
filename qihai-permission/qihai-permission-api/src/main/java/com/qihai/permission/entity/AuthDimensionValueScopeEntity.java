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
@ApiModel("维度与维度值关联表")
@TableName("auth_dimension_value_scope")
public class AuthDimensionValueScopeEntity extends CommonEntity<AuthDimensionValueScopeEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 
	 */
	@ApiModelProperty(name="id",value="id")
    private Long id;
    
    /**
	 * 数据维度ID，参照AuthDimensionValueEntity实体的id
	 */
	@ApiModelProperty(name="dimensionValueId",value="数据维度ID",required=true,dataType="Long")
    @NotNull(message="数据维度ID不可为空")
	private Long dimensionValueId;
    
    /**
	 * 值
	 */
	@ApiModelProperty(name="dimensionValue",value="维度对应的值",dataType="String")
	@NotBlank(message="维度值不可为空")
    private String dimensionValue;
    
    /**
	 * 
	 */
	@ApiModelProperty(name="displayName",value="显示值",dataType="String")
    private String displayName;
    
   
    /**
	 * 日志跟踪id
	 */
    @ApiModelProperty(hidden=true)
    private String traceId;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getDimensionValueId() {
		return dimensionValueId;
	}


	public void setDimensionValueId(Long dimensionValueId) {
		this.dimensionValueId = dimensionValueId;
	}


	public String getDimensionValue() {
		return dimensionValue;
	}


	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
