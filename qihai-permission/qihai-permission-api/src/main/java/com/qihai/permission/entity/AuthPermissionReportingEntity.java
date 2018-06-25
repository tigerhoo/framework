package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import com.qihai.permission.entity.common.CommonEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 微服务上报管理
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("资源上报模型")
@TableName("auth_permission_reporting")

public class AuthPermissionReportingEntity extends CommonEntity<AuthPermissionReportingEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(name = "id", value = "id", dataType = "Long")
	private Long id;

	/**
	 * 微服务名(SUB_SYSTEM)。SYSTEM_CENTER-系统中心，AUTH_CENTER-授权中心，CRM-客户关系管理，OMS-订单系统
	 */
	@ApiModelProperty(name = "serviceName", value = "微服务名", required = true, dataType = "String")
	@NotNull(message = "微服务名不能为空")
	private String serviceName;

	/**
	 * 访问这个域名的地址，如http://127.0.0.1:13018/
	 */
	@ApiModelProperty(name = "url", value = "域名", required = true, dataType = "String")
	@NotNull(message = "域名不能为空")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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
