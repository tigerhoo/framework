package com.qihai.framework.security.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.qihai.commerce.framework.utils.ContextUtils;
import com.qihai.commerce.framework.utils.SpringContextsUtil;
import com.qihai.framework.security.cache.ProfileCache;
import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.event.SessionSyncApplicationEvent;
import com.qihai.framework.security.model.MyContext;
import com.qihai.framework.security.model.MyProfile;

/**
 * Created by liheng on 2017/6/8.
 */
public class TokenFilter extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH");
            response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-from, x-device, x-requested-with, token");
            response.setHeader("Access-Control-Max-Age", "3600");
            return;
        }

        boolean hasToken = false;
        String jwt = request.getHeader(Const.REQUEST_HEADER_KEY_TOKEN);
        try {
             if(!verifyFilterUrl(request.getRequestURI())){
                if (ContextUtils.get() == null) {
                    String[] strArray = MyProfile.extractToken(jwt);
                    String sid = strArray[0];
                    String originalJwt = strArray[1];
                    if (sid == null) {
                    	returnJson(response, request, Const.ERROR_INVALID_TOKEN);
                        return;
                    }else{
                        MyProfile myProfile = ProfileCache.get(sid);
                        if (myProfile == null) {
                            returnJson(response, request, Const.ERROR_INVALID_TOKEN);
                            return;
                        }
                        myProfile.setJwt(originalJwt);

                        verifyToken(request, response, myProfile, sid);


                        MyContext myContext = new MyContext();
                        myContext.setMyProfile(myProfile);
                        myContext.setUser(myProfile.getUserInfo());
                        ContextUtils.set(myContext);

                        boolean isWebUser = StringUtils.isEmpty(request.getHeader(Const.REQUEST_HEADER_KEY_DEVICE));
                        //根据x-device判断是否为app或web登录，如果为web则需要自动刷新token的有效时间
                        if (isWebUser) {
                        	SpringContextsUtil.publishEvent(new SessionSyncApplicationEvent(this, ProfileCache.getKey(myProfile.getSid())));
                        }

                        hasToken = true;
                        jwt = originalJwt;
                    }
                }
            }

            HttpTokenRequestWrapper httpTokenRequestWrapper = new HttpTokenRequestWrapper(request, jwt);
            filterChain.doFilter(httpTokenRequestWrapper, response);
        } catch (Exception e) {
            logger.error("TokenFilter porcess error!", e);
        } finally {
            if (hasToken) {
                ContextUtils.unset();
            }
        }
    }

    private boolean verifyFilterUrl(String url){
        boolean flag = false;
        String contextPath = SpringContextsUtil.getProperty("server.servlet.context-path", "/");
        if (contextPath.length() != 1) {
        	url = url.substring(contextPath.length());
		}
        for (String u : Const.URLS_FILTER) {
            if(url.startsWith(u)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    private void verifyToken(HttpServletRequest request, HttpServletResponse response, MyProfile myProfile, String sid) {
        boolean accessFlag = myProfile.verifyToken();
        if (!accessFlag) {
            returnResponse(response, request, sid, Const.ERROR_INVALID_TOKEN, false);
            return;
        }

        String refreshToken = request.getHeader(Const.REQUEST_HEADER_KEY_REFRESH_TOKEN);
        if (Const.URL_REFRESH_TOKEN.equals(request.getRequestURI()) && StringUtils.hasLength(refreshToken)) {
            if (!myProfile.verifyRefreshToken(refreshToken)) {
                returnResponse(response, request, sid, Const.ERROR_REFRESH_TOKEN_DISACCORD, false);
                return;
            }
        } else {
            accessFlag = !myProfile.verifyExpire();
            if (!accessFlag) {
                returnResponse(response, request, sid, Const.ERROR_TOKEN_EXPIRED, true);
                return;
            }
        }
    }

    private void returnResponse(HttpServletResponse response, HttpServletRequest request, String sid, String json, boolean deleted) {
        if (deleted) {
            ProfileCache.delete(sid);
        }
        returnJson(response, request, json);
    }

    private void returnJson(HttpServletResponse response, HttpServletRequest request, String json) {
        try {
            String contentType = "application/json; charset=UTF-8";
            if (request != null) {
                String accept = request.getHeader("accept");
                if (accept != null && !accept.contains("json")) {
                    contentType = "text/html; charset=UTF-8";
                }
            }
            response.setContentType(contentType);
            response.getWriter().write(json);
            response.getWriter().flush();
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("returnJson is error!", e);
            }
        }
    }

    public static class HttpTokenRequestWrapper extends HttpServletRequestWrapper {
        private Map<String, String> headerMap = new HashMap<String, String>();
        private Map<String, String> parameterMap = new HashMap<String, String>();

        public HttpTokenRequestWrapper(HttpServletRequest request, String originalJwt) {
            super(request);
            headerMap.put(Const.REQUEST_HEADER_KEY_TOKEN, originalJwt);
        }

        @Override
        public String getHeader(String name) {
            if (headerMap.containsKey(name)) {
                return headerMap.get(name);
            } else {
                return super.getHeader(name);
            }
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return super.getHeaderNames();
        }

        public String getParameter(String name) {
            if (parameterMap.containsKey(name)) {
                return parameterMap.get(name);
            } else {
                return super.getParameter(name);
            }
        }

        public void setParameterMap(Map<String, String> parameterMap) {
            if (parameterMap != null) {
                this.parameterMap.putAll(parameterMap);
            }
        }
    }
}