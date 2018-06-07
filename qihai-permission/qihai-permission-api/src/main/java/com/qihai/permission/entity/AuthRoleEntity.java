package com.qihai.permission.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.qihai.commerce.framework.entity.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author liujinguo
 * @email ${email}
 * @date 2018-05-29 09:05:48
 */
@ApiModel("角色实体")
@TableName("auth_role")
public class AuthRoleEntity extends DataEntity<AuthRoleEntity> {

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

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人", name = "createdBy", hidden = true)
	private String createdBy;

	/**
	 * 最后更新人
	 */
	@ApiModelProperty(value = "最后更新人", name = "updatedBy", hidden = true)
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
	 * 获取：角色名
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置：角色名
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取：角色编码
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * 设置：角色编码
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
