package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("用户信息")
@TableName("user_info")
public class UserInfoEntity extends DataEntity<UserInfoEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * id
	 */
	@ApiModelProperty(value="id",name="id")
    private Long id;
    
    /**
	 * 父ID
	 */
	@ApiModelProperty(value="父ID",name="parentId")
    private Long parentId;
    
    /**
	 * 用户名
	 */
	@ApiModelProperty(value="用户名",name="userName",example="zs",notes="111")
	@NotBlank(message="用户名不可为空")
    private String userName;
    
    /**
	 * 手机号
	 */
	@ApiModelProperty(value="手机号",name="mobile")
	@NotBlank(message="手机号不可为空")
    private String mobile;
    
    /**
	 * 用户类型(C:用户,B:用户,S:运营)
	 */
	@NotBlank(message="用户类型不可为空")
    private String type;
    
    /**
	 * 日志跟踪id
	 */
    private String traceId;
    
    /**
	 * 创建人
	 */
    private String createdBy;
    
    /**
	 * 最后更新人
	 */
    private String updatedBy;
    
    /**
	 * 账号状态(A:activate激活,L:lock锁定))
	 */
    private String status;
    
    /**
	 * 注册时间
	 */
    private Date registTime;
    
    /**
	 * 用户来源(2:APP,3:官网,4:微官网,6:微信小程序,7:PC客户端,8:电商商户后台,9:电商运营管理后台)
	 */
    private Integer fromSource;
	
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
	 * 获取：父ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 设置：父ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 获取：用户类型(C:用户,B:用户,S:运营)
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 设置：用户类型(C:用户,B:用户,S:运营)
	 */
	public void setType(String type) {
		this.type = type;
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
	
	/**
	 * 获取：账号状态(A:activate激活,L:lock锁定))
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置：账号状态(A:activate激活,L:lock锁定))
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取：注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegistTime() {
		return registTime;
	}
	
	/**
	 * 设置：注册时间
	 */
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	
	/**
	 * 获取：用户来源(2:APP,3:官网,4:微官网,6:微信小程序,7:PC客户端,8:电商商户后台,9:电商运营管理后台)
	 */
	public Integer getFromSource() {
		return fromSource;
	}
	
	/**
	 * 设置：用户来源(2:APP,3:官网,4:微官网,6:微信小程序,7:PC客户端,8:电商商户后台,9:电商运营管理后台)
	 */
	public void setFromSource(Integer fromSource) {
		this.fromSource = fromSource;
	}

    @Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
