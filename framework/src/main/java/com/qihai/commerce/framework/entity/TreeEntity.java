package com.qihai.commerce.framework.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.qihai.commerce.framework.utils.Reflections;
import com.qihai.commerce.framework.utils.StringUtil;


/**
 * 树型实体对象
 * 
 * @author zhugj
 * @date 2016年3月1日 下午2:03:29
 * @version 0.1.0
 */
public abstract class TreeEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 父级编号
	 */
	@NotNull
	protected T parent;
	
	/**
	 * 所有父级编号
	 */
	@Length(min=1, max=2000)
	protected String parentIds;
	
	/**
	 * 名称
	 */
	@Length(min=1, max=100)
	protected String name;
	
	/**
	 * 排序
	 */
	protected Integer sort;
	
	public TreeEntity() {
		super();
		this.sort = 30;
	}
/*	
	public TreeEntity(String id) {
		super(id);
	}
*/	
	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	public abstract T getParent();

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	public abstract void setParent(T parent);

	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		String id = null;
		if (parent != null){
			id = (String)Reflections.getFieldValue(parent, "id");
		}
		return StringUtil.isNotBlank(id) ? id : "0";
	}
	
}
