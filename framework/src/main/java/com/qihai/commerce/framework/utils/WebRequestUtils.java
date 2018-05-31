package com.qihai.commerce.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qihai.commerce.framework.enums.RequestBrowser;

/**
 * 
 * @author Ching
 * @date 2014-3-4 下午1:51:47
 * @version v1.0
 */
public abstract class WebRequestUtils {

	/**
	 * 获取请求基础路径
	 * 
	 * @return String
	 */
	public static String getRequestBasePath(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(request.getScheme());
		builder.append("://");
		builder.append(request.getServerName());
		builder.append(":");
		builder.append(request.getServerPort());
		builder.append(request.getContextPath());
		return builder.toString();
	}

	/**
	 * 获取请求IP
	 * 
	 * @param request
	 * @return String
	 */
	public static String getRequestIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(ipAddress) == false) {
			// 多级反向代理 X-Forwarded-For 中第一个非 unknown 的字符串为有效 IP
			Matcher matcher = Pattern.compile("(?<!unknown)(\\d+\\.\\d+\\.\\d+\\.\\d+)").matcher(ipAddress);
			ipAddress = matcher.find() ? matcher.group(1) : null;
		}
		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		return StringUtils.trimToNull(ipAddress);
	}

	/**
	 * 获取请求浏览器类型<br>
	 * 
	 * @param request
	 * @return RequestBrowser
	 */
	public static RequestBrowser getRequestBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (StringUtils.isEmpty(userAgent)) {
			return RequestBrowser.UNKNOWN;
		} else if (userAgent.indexOf("MSIE") != -1) {
			return RequestBrowser.MSIE;
		} else if (userAgent.indexOf("Firefox") != -1) {
			return RequestBrowser.FIREFOX;
		} else if (userAgent.indexOf("Safari") != -1) {
			return RequestBrowser.SAFARI;
		} else if (userAgent.indexOf("Chrome") != -1) {
			return RequestBrowser.CHROME;
		} else {
			return RequestBrowser.OTHER;
		}
	}
}
