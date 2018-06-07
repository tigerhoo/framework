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
@ApiModel("数据范围维度关联表")
@TableName("auth_dimension_value")
public class AuthDimensionValueEntity extends DataEntity<AuthDimensionValueEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 主键
	 */
	@ApiModelProperty(name="id",value="id")
    private Long id;
    
    /**
	 * 维度定义ID 
	 */
	@ApiModelProperty(name="authDimensionId",value="维度定义ID",required=true,dataType="Long")
    @NotBlank(message="维度定义ID不可为空")
	private Long authDimensionId;
    
    /**
	 * 数据范围ID
	 */
	@ApiModelProperty(name="authDimensionId",value="维度定义ID",required=true,dataType="Long")
    @NotBlank(message="数据范围ID不可为空")
	private Long dataRangeId;
    
   
    /**
	 * 日志跟踪id
	 */
	@ApiModelProperty(hidden = true)
    private String traceId;
    
    /**
	 * 创建人
	 */
	@ApiModelProperty(hidden = true)
    private String createdBy;
    
    /**
	 * 修改人
	 */
	@ApiModelProperty(hidden = true)
    private String updatedBy;
	
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：维度定义ID 
	 */
	public Long getAuthDimensionId() {
		return authDimensionId;
	}
	
	/**
	 * 设置：维度定义ID 
	 */
	public void setAuthDimensionId(Long authDimensionId) {
		this.authDimensionId = authDimensionId;
	}
	
	/**
	 * 获取：数据范围ID
	 */
	public Long getDataRangeId() {
		return dataRangeId;
	}
	
	/**
	 * 设置：数据范围ID
	 */
	public void setDataRangeId(Long dataRangeId) {
		this.dataRangeId = dataRangeId;
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
