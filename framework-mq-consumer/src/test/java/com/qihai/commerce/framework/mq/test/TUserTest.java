package com.qihai.commerce.framework.mq.test;

import java.io.Serializable;

public class TUserTest implements Serializable{

	private static final long serialVersionUID = -1161367755335903616L;
	private String id;
	private String name;
	
	public TUserTest() {
		
	}
	
	public TUserTest(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
