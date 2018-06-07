package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.qihai.commerce.framework.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiModel("维度与维度值关联表")
@TableName("auth_dimension_value_scope")
public class AuthDimensionValueScopeEntity extends DataEntity<AuthDimensionValueScopeEntity> {

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
    @NotBlank(message="数据维度ID不可为空")
	private Long dimensionValueId;
    
    /**
	 * 值
	 */
	@ApiModelProperty(name="dimensionValue",value="维度对应的值",dataType="String")
	@NotBlank(message="值不可为空")
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
    
    /**
	 * 创建人
	 */
    @ApiModelProperty(hidden=true)
    private String createdBy;
    
    /**
	 * 修改人
	 */
    @ApiModelProperty(hidden=true)
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
	 * 获取：数据维度ID
	 */
	public Long getDimensionValueId() {
		return dimensionValueId;
	}
	
	/**
	 * 设置：数据维度ID
	 */
	public void setDimensionValueId(Long dimensionValueId) {
		this.dimensionValueId = dimensionValueId;
	}
	
	/**
	 * 获取：值
	 */
	public String getDimensionValue() {
		return dimensionValue;
	}
	
	/**
	 * 设置：值
	 */
	public void setDimensionValue(String dimensionValue) {
		this.dimensionValue = dimensionValue;
	}
	
	/**
	 * 获取：
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * 设置：
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
