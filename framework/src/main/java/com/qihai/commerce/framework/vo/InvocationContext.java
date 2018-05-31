package com.qihai.commerce.framework.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import com.qihai.commerce.framework.enums.RequestBrowser;

/**
 * 调用上下文
 * 
 * @author Chenmm
 */
public final class InvocationContext implements Serializable {

	private static final long serialVersionUID = -6349106477027516204L;

	private static final HttpHeaders EMPTY_HTTP_HEADERS = HttpHeaders.readOnlyHttpHeaders(new HttpHeaders());
	private static final Map<String, String> EMPTY_HTTP_COOKIES = Collections.unmodifiableMap(Collections.<String, String> emptyMap());

	private final ViewState viewState;
	private final Map<String, String> httpCookies;
	private final HttpHeaders httpHeaders;
	private final String ipAddress;
	private final RequestBrowser browser;
	private final Map<String, String> attributes = new HashMap<String, String>();

	public InvocationContext(ViewState viewState) {
		this(viewState, null);
	}

	public InvocationContext(ViewState viewState, Map<String, String> httpCookies) {
		this(viewState, httpCookies, null);
	}

	public InvocationContext(ViewState viewState, Map<String, String> httpCookies, HttpHeaders httpHeaders) {
		this(viewState, httpCookies, httpHeaders, null, null);
	}

	public InvocationContext(ViewState viewState, Map<String, String> httpCookies, HttpHeaders httpHeaders, String ipAddress, RequestBrowser browser) {
		this(viewState, httpCookies, httpHeaders, ipAddress, browser, Collections.<String, String> emptyMap());
	}

	public InvocationContext(ViewState viewState, Map<String, String> httpCookies, HttpHeaders httpHeaders, String ipAddress, RequestBrowser browser, Map<String, String> attributes) {
		this.viewState = viewState;
		this.httpCookies = httpCookies == null ? EMPTY_HTTP_COOKIES : Collections.unmodifiableMap(httpCookies);
		this.httpHeaders = httpHeaders == null ? EMPTY_HTTP_HEADERS : HttpHeaders.readOnlyHttpHeaders(httpHeaders);
		this.ipAddress = ipAddress;
		this.browser = browser == null ? RequestBrowser.UNKNOWN : browser;
		if (attributes != null && attributes.size() > 0) {
			this.attributes.putAll(attributes);
		}
	}

	/**
	 * 获取当前用户（不存在返回空）
	 * 
	 * @return ViewState
	 */
	public final ViewState getViewState() {
		return viewState;
	}

	/**
	 * 获取 IPAddress
	 * 
	 * @return String
	 */
	public final String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 获取 Browser
	 * 
	 * @return RequestBrowser
	 */
	public final RequestBrowser getBrowser() {
		return browser;
	}

	/**
	 * 获取 Header
	 * 
	 * @param name
	 * @return String
	 */
	public final String getHeader(String headerName) {
		return httpHeaders.getFirst(headerName);
	}

	/**
	 * 获取 Headers
	 * 
	 * @param name
	 * @return List
	 */
	public final List<String> getHeaders(String headerName) {
		return httpHeaders.get(headerName);
	}

	/**
	 * 获取 Headers
	 * 
	 * @return Map
	 */
	public final HttpHeaders getHeaders() {
		return httpHeaders;
	}

	/**
	 * 获取 Cookie
	 * 
	 * @param name
	 * @return HttpCookie
	 */
	public final String getCookie(String cookieName) {
		return httpCookies.get(cookieName);
	}

	/**
	 * 获取 Cookies
	 * 
	 * @return Map
	 */
	public final Map<String, String> getCookies() {
		return httpCookies;
	}

	/**
	 * 设置 Attribute
	 * 
	 * @param key
	 * @param value
	 */
	public final void setAttribute(String key, String value) {
		attributes.put(key, value);
	}

	/**
	 * 获取 Attribute
	 * 
	 * @param key
	 * @return String
	 */
	public final String getAttribute(String key) {
		return attributes.get(key);
	}

	/**
	 * 获取 Attributes
	 * 
	 * @return Map
	 */
	public final Map<String, String> getAttributes() {
		return attributes;
	}
}
