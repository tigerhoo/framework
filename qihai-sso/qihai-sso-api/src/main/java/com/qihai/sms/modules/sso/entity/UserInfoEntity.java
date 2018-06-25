package com.qihai.sms.modules.sso.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import com.qihai.commerce.framework.entity.DataEntity;
import java.util.Date;
import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@TableName("user_info")
public class UserInfoEntity extends DataEntity<UserInfoEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 父ID
	 */
	private Long parentId;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不可为空")
	private String userName;

	@JsonIgnore//查询时不能返回密码给前台
	private String password;

	/**
	 * 登录账号
	 */
	@NotBlank(message = "登录账号不可为空")
	private String loginName;

	/**
	 * 用户类型(C:用户,B:管理员,S:运营)
	 */
	@NotBlank(message = "用户类型不可为空")
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
	 * 账号状态(A:activate激活,L:lock锁定)
	 */
	@NotBlank(message = "账户状态不可为空")
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
	 * 获取：登录账号
	 */
	public String getLoginName() {
		return loginName;
	}
	
	/**
	 * 设置：登录账号
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
