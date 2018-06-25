package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qihai.permission.entity.common.CommonEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("用户信息")
@TableName("user_info")

public class UserInfoEntity extends CommonEntity<UserInfoEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id,更新时必传参数", name = "id")
	private Long id;

	/**
	 * 父ID
	 */
	@ApiModelProperty(value = "父ID", name = "parentId", hidden = true)
	private Long parentId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", name = "userName", example = "zs", notes = "111", required = true)
	@NotBlank(message = "用户名不可为空")
	private String userName;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "用户密码", name = "password")
	private String password;

	/**
	 * 工号
	 */
	@ApiModelProperty(value = "登录账号", name = "loginName", required = true)
	@NotBlank(message = "登录账号不可为空")
	private String loginName;

	/**
	 * 用户类型(C:用户,B:管理员,S:运营)
	 */
	@ApiModelProperty(value = "用户类型(C:用户,B:管理员,S:运营)", name = "type", hidden = true)
	private String type;

	/**
	 * 日志跟踪id
	 */
	@ApiModelProperty(value = "日志跟踪id", name = "traceId", hidden = true)
	private String traceId;

	/**
	 * 账号状态(A:activate激活,L:lock锁定)
	 */
	@ApiModelProperty(value = "账号状态(A:activate激活,L:lock锁定)", name = "status", required = true)
	@NotBlank(message = "账户状态不可为空")
	private String status;

	/**
	 * 注册时间
	 */
	@ApiModelProperty(value = "注册时间", name = "registTime", hidden = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registTime;

	/**
	 * 用户来源(2:APP,3:官网,4:微官网,6:微信小程序,7:PC客户端,8:电商商户后台,9:电商运营管理后台)
	 */
	@ApiModelProperty(value = "用户来源(2:APP,3:官网,4:微官网,6:微信小程序,7:PC客户端,8:电商商户后台,9:电商运营管理后台)", name = "fromSource", hidden = true)
	private Integer fromSource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getFromSource() {
		return fromSource;
	}

	public void setFromSource(Integer fromSource) {
		this.fromSource = fromSource;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
