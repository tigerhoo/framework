package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

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
@ApiModel("角色实体")
@TableName("auth_role")

public class AuthRoleEntity extends CommonEntity<AuthRoleEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id，更新时必传", name = "id")
	private Long id;

	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名", name = "roleName", required = true)
	@NotBlank(message = "角色名不可为空")
	private String roleName;

	/**
	 * 角色编码
	 */
	@ApiModelProperty(value = "角色编码", name = "roleCode", required = true)
	@NotBlank(message = "角色编码不可为空")
	private String roleCode;

	/**
	 * 分类编码
	 */
	@ApiModelProperty(value = "分类编码", name = "categoryCode")
	private String categoryCode;

	/**
	 * 日志跟踪id
	 */
	@ApiModelProperty(value = "日志跟踪id", name = "traceId", hidden = true)
	private String traceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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
