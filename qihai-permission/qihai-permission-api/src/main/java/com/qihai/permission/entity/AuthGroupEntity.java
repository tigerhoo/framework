package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:47
 */
@ApiModel("用户组模型")
@TableName("auth_group")
public class AuthGroupEntity extends DataEntity<AuthGroupEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * id
	 */
	@ApiModelProperty(value="id,更新时必传参数",name="id")
    private Long id;
    
    /**
	 * 组名
	 */
	@ApiModelProperty(value="组名",name="groupName",required=true)
    @NotBlank(message="组名不能为空")
    private String groupName;
    
    /**
	 * 组编码
	 */
	@ApiModelProperty(value="组编码",name="groupCode",required=true)
    @NotNull(message="组编码不能为空")
    private String groupCode;
    
    /**
	 * 分类编码
	 */
	@ApiModelProperty(value="分类编码",name="categoryCode")
    private String categoryCode;
    
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
	 * 最后更新人
	 */
	@ApiModelProperty(hidden=true)
    private String updatedBy;
	
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：组名
	 */
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * 设置：组名
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * 获取：组编码
	 */
	public String getGroupCode() {
		return groupCode;
	}
	
	/**
	 * 设置：组编码
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	/**
	 * 获取：分类编码
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	
	/**
	 * 设置：分类编码
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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
	 * 获取：最后更新人
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	/**
	 * 设置：最后更新人
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
