package com.qihai.commerce.framework.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户信息
 * @author liheng
 * @since 1.0
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String KEY_PREFIX = "X-";

	public static final String KEY_ID = "uid";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_NICKNAME = "nickname";
	public static final String KEY_TYPE = "type";
	public static final String KEY_FROM = "from";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_RESOURCES = "resources";
	public static final String ENTRY_FROM_SELLER = "BB";
	public static final String ENTRY_FROM_BUYER = "CF";
	public static final String ENTRY_FROM_OPERATOR = "OB";
	public static final String FLAG_SELLER = "B";

	/**
	 * 用户ID
	 */
	private String id;

	/**
	 * 账号
	 */
	private String username;

    /**
     * 昵称（中文名称）
     */
    private String nickname;

	/**
	 * 用户类型用户类型(C:用户,B:商家,S:运营)
     */
	private String type;

    /**
     * 登录入口来源(CF:电商前端,BB:商家后台,OB:运营后台)
     */
    private String from;

	/**
	 * 手机号
	 */
	private String phone;

    /**
     * 客户端请求IP
     */
	private String ipAddr;
	
	private List<UserResources> resources;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public static String getHeaderName(String key) {
        return UserInfo.KEY_PREFIX + key;
    }

    @JsonIgnore
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @JsonIgnore
	public List<UserResources> getResources() {
		return resources;
	}

	public void setResources(List<UserResources> resources) {
		this.resources = resources;
	}
}
