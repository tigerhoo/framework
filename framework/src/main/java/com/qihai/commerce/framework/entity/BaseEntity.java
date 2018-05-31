package com.qihai.commerce.framework.entity;

import java.io.Serializable;

//import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 基础实体对象
 * 
 * @author zhugj
 * @date 2017年8月17日 下午2:03:29
 * @version 1.0.0
 */
public abstract class BaseEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 删除标记（0：正常；1：删除；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	
	/**
	 * 实体编号（唯一标识）
	 */
	//@TableId
	//public String id;
	
	public BaseEntity() {
		
	}
	/*
	public BaseEntity(String id) {
		this();
		this.id = id;
	}
	*/
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
/*
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	*/
}
