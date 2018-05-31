package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@TableName("auth_dimension")
public class AuthDimensionEntity extends DataEntity<AuthDimensionEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 主键
	 */
    private Long id;
    
    /**
	 * 维度编码
	 */
    @NotBlank(message="维度编码不能为空")
    private String dimensionCode;
    
    /**
	 * 维度名称
	 */
    @NotBlank(message="维度名称不可为空")
    private String dimensionName;
    
    /**
	 * 10代表固定值 20 选择  30 数据字典
	 */
    @NotNull(message="值列表显示方式不可为空")
    private Integer displayMode;
    
    /**
	 * 选择的URL地址
	 */
    private String openUrl;
    
    /**
	 * 属性类型
	 */
    private String propertyType;
    
    /**
	 * 维度描述
	 */
    private String dimensionDesc;
    
  
    /**
	 * 日志跟踪id
	 */
    private String traceId;
    
    /**
	 * 创建人
	 */
    private String createdBy;
    
    /**
	 * 修改人
	 */
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
	 * 获取：维度编码
	 */
	public String getDimensionCode() {
		return dimensionCode;
	}
	
	/**
	 * 设置：维度编码
	 */
	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}
	
	/**
	 * 获取：维度名称
	 */
	public String getDimensionName() {
		return dimensionName;
	}
	
	/**
	 * 设置：维度名称
	 */
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	
	/**
	 * 获取：10代表固定值 20 选择  30 数据字典
	 */
	public Integer getDisplayMode() {
		return displayMode;
	}
	
	/**
	 * 设置：10代表固定值 20 选择  30 数据字典
	 */
	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}
	
	/**
	 * 获取：选择的URL地址
	 */
	public String getOpenUrl() {
		return openUrl;
	}
	
	/**
	 * 设置：选择的URL地址
	 */
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	
	/**
	 * 获取：属性类型
	 */
	public String getPropertyType() {
		return propertyType;
	}
	
	/**
	 * 设置：属性类型
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	/**
	 * 获取：维度描述
	 */
	public String getDimensionDesc() {
		return dimensionDesc;
	}
	
	/**
	 * 设置：维度描述
	 */
	public void setDimensionDesc(String dimensionDesc) {
		this.dimensionDesc = dimensionDesc;
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
