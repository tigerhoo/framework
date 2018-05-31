package com.qihai.commerce.framework.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.qihai.commerce.framework.constant.Constant;
import com.qihai.commerce.framework.utils.SingleThreadInvocationContextHolder;
import com.qihai.commerce.framework.utils.WebRequestUtils;
import com.qihai.commerce.framework.vo.InvocationContext;
import com.qihai.commerce.framework.vo.ViewState;

/**
 * RequestVariablesFilter
 * 
 * @author Chenmm
 */
public class RequestVariablesFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * 描述 : rms扫描路径，用于在RequestVariablesFilter中排除过虑
     */
    private String startWith;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		startWith = filterConfig.getInitParameter("startWith");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		final String requestUri = httpRequest.getRequestURI();
		try {
			if (!isStartWith(requestUri)) {//需要进行拦截的地址(过虑了部分不进行拦截的地址)
				// Get cookies
				Map<String, String> httpCookies = new HashMap<String, String>();
				Cookie[] cookies = httpRequest.getCookies();
				if (ArrayUtils.isNotEmpty(cookies)) {
					for (Cookie cookie : cookies) {
						httpCookies.put(cookie.getName(), cookie.getValue());
					}
				}
				
				// Get headers
				HttpHeaders httpHeaders = new HttpHeaders();
				for (Enumeration<?> headerNames = httpRequest.getHeaderNames(); headerNames.hasMoreElements();) {
					String headerName = (String) headerNames.nextElement();
					for (Enumeration<?> headerValues = httpRequest.getHeaders(headerName); headerValues.hasMoreElements();) {
						Object headerValue = headerValues.nextElement();
						if (headerValue != null) {
							httpHeaders.add(headerName, String.valueOf(headerValue));
						}
					}
				}
				
				// Get view state from [cas, redis, headers, cookies]
				ViewState viewState = null;
				viewState = new ViewState();
				viewState.setId("123");
				viewState.setLoginName("admin");
				viewState.setUserNo("no1");
				viewState.setUserName("管理员");
				
				
				// New invocation context
				InvocationContext invocationContext = new InvocationContext(viewState, httpCookies, httpHeaders, WebRequestUtils.getRequestIpAddress(httpRequest),
						WebRequestUtils.getRequestBrowser(httpRequest));
				
				// Set invocation context for current thread
				SingleThreadInvocationContextHolder.setInvocationContext(invocationContext);
				request.setAttribute(Constant.VIEW_STATE_NAME, invocationContext);
			}

			// Do next filter
			chain.doFilter(request, response);
		} finally {
			// Reset invocation context
			SingleThreadInvocationContextHolder.resetInvocationContext();
			request.removeAttribute(Constant.VIEW_STATE_NAME);
		}
	}

	@Override
	public void destroy() {
		// NO-OP
	}
	
	/**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

}
