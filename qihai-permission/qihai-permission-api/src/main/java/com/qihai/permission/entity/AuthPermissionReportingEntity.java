package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 微服务上报管理
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@TableName("auth_permission_reporting")
public class AuthPermissionReportingEntity extends DataEntity<AuthPermissionReportingEntity> {

	private static final long serialVersionUID = 1L;
    
    /**
	 * 主键ID
	 */
    private Long id;
    
    /**
	 * 微服务名(SUB_SYSTEM)。SYSTEM_CENTER-系统中心，AUTH_CENTER-授权中心，CRM-客户关系管理，OMS-订单系统
	 */
    @NotNull(message="微服务名不能为空")
    private String serviceName;
    
    /**
	 * 访问这个域名的地址，如http://127.0.0.1:13018/
	 */
    @NotNull(message="域名不能为空")
    private String url;
    
    /**
	 * 上报状态(auth_app_status)。10-未上报，20-已上报
	 */
    private String reportStatus;
    
    /**
	 * 上报人姓名
	 */
    private String reportName;
    
    /**
	 * 上报人ID
	 */
    private Long reportId;
    
    /**
	 * 上报时间
	 */
    private Date reportDate;
    
    /**
	 * 上报次数
	 */
    private Integer count;
    
    
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
	 * 获取：主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取：微服务名(SUB_SYSTEM)。SYSTEM_CENTER-系统中心，AUTH_CENTER-授权中心，CRM-客户关系管理，OMS-订单系统
	 */
	public String getServiceName() {
		return serviceName;
	}
	
	/**
	 * 设置：微服务名(SUB_SYSTEM)。SYSTEM_CENTER-系统中心，AUTH_CENTER-授权中心，CRM-客户关系管理，OMS-订单系统
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	/**
	 * 获取：访问这个域名的地址，如http://127.0.0.1:13018/
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 设置：访问这个域名的地址，如http://127.0.0.1:13018/
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 获取：上报状态(auth_app_status)。10-未上报，20-已上报
	 */
	public String getReportStatus() {
		return reportStatus;
	}
	
	/**
	 * 设置：上报状态(auth_app_status)。10-未上报，20-已上报
	 */
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	
	/**
	 * 获取：上报人姓名
	 */
	public String getReportName() {
		return reportName;
	}
	
	/**
	 * 设置：上报人姓名
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	/**
	 * 获取：上报人ID
	 */
	public Long getReportId() {
		return reportId;
	}
	
	/**
	 * 设置：上报人ID
	 */
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	
	/**
	 * 获取：上报时间
	 */
	public Date getReportDate() {
		return reportDate;
	}
	
	/**
	 * 设置：上报时间
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	/**
	 * 获取：上报次数
	 */
	public Integer getCount() {
		return count;
	}
	
	/**
	 * 设置：上报次数
	 */
	public void setCount(Integer count) {
		this.count = count;
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
