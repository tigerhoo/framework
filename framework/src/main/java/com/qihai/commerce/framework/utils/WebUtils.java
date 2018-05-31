package com.qihai.commerce.framework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * WebUtils
 * 
 * @author zhugj
 *
 * 2017年12月12日 上午11:10:56
 */
public class WebUtils {
	
	/**
	 * 清空session
	 */
	public static final void clearSession(HttpServletRequest request) {
		//RedisWebUtils.removeRedisSystemLogin();
		//AssertionHolder.clear();
		request.getSession().invalidate();//非必须，单点登出接收到服务器消息时，会自动销毁session
	}
	
	public static void setBasePath(HttpServletRequest request, String basePath){
		request.getSession().setAttribute("BasePath", basePath);
	}
	
	/**
	 * 获取basePath
	 * @param request
	 * @return
	 */
	public static String getRootPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return rootPath;
	}
	
	/**
	 * 取实际用户的访问地址。
	 * 
	 * @param request
	 *            当前请求。
	 * @return 客户端IP地址。
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ips = request.getHeader("x-forwarded-for");
		if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
			ips = request.getHeader("Proxy-Client-IP");
		}
		if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
			ips = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
			ips = request.getRemoteAddr();
		}

		String[] ipArray = ips.split(",");
		String clientIP = null;
		for (String ip : ipArray) {
			if (!("unknown".equalsIgnoreCase(ip))) {
				clientIP = ip;
				break;
			}
		}
		return clientIP;
	}

	/**
	 * 查找指定请求中的指定名称的Cookie。
	 * 
	 * @param request
	 *            请求。
	 * @param name
	 *            cookie名称。
	 * @return 如果有相应名称的Cookie，则返回相应Cookie实例。没有返回null。
	 */
	public static Cookie findCookie(HttpServletRequest request, String name) {
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(name)) {
						return cookie;
					}
				}
			}
		}

		return null;
	}

	/**
	 * 查找指定请求中的指定名称Cookie的值，如果不存在将返回null。
	 * 
	 * @param request
	 *            请求。
	 * @param name
	 *            Cookie名称。
	 * @return cookie的值。
	 */
	public static String findCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = findCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 增加一个Cookie,使用默认域名。
	 * 
	 * @param request
	 *            请求。
	 * @param response
	 *            响应。
	 * @param name
	 *            Cookie名称 。
	 * @param value
	 *            Cookie的值。
	 * @param maxAge
	 *            生命周期。
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		addCookie(request, response, name, value, null, maxAge);
	}

	/**
	 * 增加一个Cookie,使用指定域名。
	 * 
	 * @param request
	 *            请求。
	 * @param response
	 *            响应。
	 * @param name
	 *            Cookie名称 。
	 * @param value
	 *            Cookie的值。
	 * @param maxAge
	 *            生命周期。
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			String domain, int maxAge) {
		String contextPath = request.getContextPath();
		if (contextPath == null || contextPath.isEmpty()) {
			contextPath = "/";
		}
		addCookie(request, response, name, value, domain, contextPath, maxAge);
	}

	/**
	 * 增加一个Cookie.ContextPath如果为空或者长度为0，将使用"/".
	 * 
	 * @param request
	 *            当前请求。
	 * @param response
	 *            当前响应。
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param domain
	 *            cookie域名
	 * @param contextPath
	 *            cookie路径。
	 * @param maxAge
	 *            有效时间。
	 */
	public static void addCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			String domain, String contextPath, int maxAge) {
		if (request != null && response != null) {
			Cookie cookie = new Cookie(name, value);
			cookie.setMaxAge(maxAge);
			cookie.setSecure(request.isSecure());

			if (contextPath == null || contextPath.isEmpty()) {
				cookie.setPath("/");
			} else {
				cookie.setPath(contextPath);
			}

			if (domain != null && !domain.isEmpty()) {
				cookie.setDomain(domain);
			}

			response.addCookie(cookie);
		}
	}

	/**
	 * 失效一个Cookie.
	 * 
	 * @param request
	 *            当前请求。
	 * @param response
	 *            当前响应。
	 * @param name
	 *            Cookie名称。
	 * @param domain
	 *            Cookie域名。
	 * @param contextPath
	 *            有效路径。
	 */
	public static void failureCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String domain,
			String contextPath) {
		if (request != null && response != null) {
			addCookie(request, response, name, null, domain, contextPath, 0);
		}
	}

	/**
	 * 将指定的Cookie失效掉。
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应。
	 * @param name
	 *            cookie名称。
	 * @param domain
	 *            cookie的域名。
	 */
	public static void failureCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String domain) {
		String contextPath = request.getContextPath();
		if (contextPath == null || contextPath.isEmpty()) {
			contextPath = "/";
		}
		failureCookie(request, response, name, domain, contextPath);
	}

	/**
	 * 将指定的Cookie失效掉。
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应。
	 * @param name
	 *            cookie名称。
	 */
	public static void failureCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		failureCookie(request, response, name, null);
	}

	/**
	 * 获取请求的完整地址。
	 * 
	 * @param request
	 *            请求。
	 * @return 完整地址。
	 */
	public static String completeTheRequestAddress(HttpServletRequest request) {
		StringBuilder buff = new StringBuilder(request.getRequestURL()
				.toString());
		String queryString = request.getQueryString();
		if (queryString != null) {
			buff.append("?").append(queryString);
		}

		return buff.toString();
	}

}
