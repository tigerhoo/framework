package com.qihai.commerce.framework.vo;

import java.io.Serializable;

/**
 * 用户视图状态
 * 
 * @author Chenmm
 */
public final class ViewState implements Serializable {

	private static final long serialVersionUID = 4155185410488743905L;

	/**
	 * RPC用户视图状态(限内部子系统间RPC通信使用) 　<b>PS:
	 * </b>因内部子系统间RPC通信时无UVS(但RPC协议需要),所以特殊配置一个RPC专属UVS.
	 */
	public static final ViewState RPC_VS = new ViewState("0", "0", "RPC", "RPC");
	
	/** ID */
	private String id;

	/** 用户no */
	private String userNo;

	/** 用户名称  */
	private String userName;
	
	/** 登陆名称 */
	private String loginName;
	
	public ViewState() {
		super();
	}
	
	public ViewState(String userNo, String loginName) {
		setUserNo(userNo);
		setLoginName(loginName);
	}
	
	public ViewState(String id, String userNo, String userName, String loginName) {
		setId(id);
		setUserNo(userNo);
		setUserName(userName);
		setLoginName(loginName);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}
	
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return id;
	}
}
