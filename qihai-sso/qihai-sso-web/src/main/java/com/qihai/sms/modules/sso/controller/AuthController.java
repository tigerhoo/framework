package com.qihai.sms.modules.sso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.utils.ContextUtils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.StringUtil;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.constants.LoginParamConts;
import com.qihai.framework.security.filter.TokenFilter;
import com.qihai.sms.modules.sso.service.impl.SsoAuthenticationService;

/**
 * Created by liheng on 2017/5/23.
 */
@Controller
public class AuthController {

    @Autowired
    private SsoAuthenticationService ssoAuthenticationService;

    /**
     * 用户登录
     * 
     * @param params
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = Const.URL_LOGIN, method = RequestMethod.POST)
    public @ResponseBody
    R<Map<String, Object>> login(@RequestBody Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
        TokenFilter.HttpTokenRequestWrapper httpTokenRequestWrapper = new TokenFilter.HttpTokenRequestWrapper(request, "");
        httpTokenRequestWrapper.setParameterMap(params);
        final WebContext context = new J2EContext(httpTokenRequestWrapper, response);
        if (params == null) {
            params = new HashMap<String, String>();
        }
        R<Map<String, Object>> result = new R<Map<String, Object>>();
        result.setCode(BizErrorCode.ValidateErrorType.PARAMS_IS_INVALID.getCode());
        result.setMsg(BizErrorCode.ValidateErrorType.PARAMS_IS_INVALID.getDesc());

        boolean isExecuteLogin = false;
        String username = params.get(LoginParamConts.USERNAME);//用户名 or 手机号
        String password = params.get(LoginParamConts.PASSWORD);//密码
        if (isExecuteLogin = (StringUtil.isNotBlank(username) && StringUtil.isNotBlank(password))){
            getHeaderParamValues(params, request);
            params.put(LoginParamConts.PHONE_NUMBER, username);

            //密码不为空，则表示用户名密码登录
            params.put(LoginParamConts.USERNAME, username);
            params.put(LoginParamConts.PASSWORD, password);
        }

        String logintype = params.get(LoginParamConts.X_LOGINTYPE);
        //验证数据有效性
        if(isExecuteLogin && StringUtil.isNotBlank(logintype)) {
            //前端请求头x-from传递值不正确
            if ("-1".equals(logintype)) {
                isExecuteLogin = false;
                result.setCode(BizErrorCode.ValidateErrorType.PARAMS_IS_INVALID.getCode());
                result.setMsg("请求来源不能为空");
            }
        }

        if(isExecuteLogin) {
            httpTokenRequestWrapper.setParameterMap(params);
            result = ssoAuthenticationService.login(context);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        return result;
    }

    /**
     * 获取登录来源信息
     *
     * @param params
     * @param request
     */
    private void getHeaderParamValues(Map<String, String> params, HttpServletRequest request){
        if (params == null) {
            params = new HashMap<String, String>();
        }
        
        //直接给默认值，目前这些值没有功能上用到
        params.clear();
        params.put(LoginParamConts.X_FROM,UserInfo.ENTRY_FROM_OPERATOR);
        params.put(LoginParamConts.X_TYPE, LoginParamConts.X_TYPE_S);
        params.put(LoginParamConts.X_LOGINTYPE, "9");
    }

    /**
     * 获得登录人信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = Const.URL_USER_INFO, method = RequestMethod.GET)
    public @ResponseBody
    R<UserInfo> getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        //final WebContext context = new J2EContext(request, response);
        //R<UserInfo> result = ssoAuthenticationService.getUserInfo(context);
        UserInfo userInfo = ContextUtils.getUserInfo();
        R<UserInfo> result = new R<UserInfo>().ok(userInfo);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return result;
    }

    /**
     * 刷新token
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = Const.URL_REFRESH_TOKEN, method = RequestMethod.POST)
    public @ResponseBody
    R<Map<String, Object>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final WebContext context = new J2EContext(request, response);
        R<Map<String, Object>> result = ssoAuthenticationService.refreshToken(context);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return result;
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = Const.URL_LOGOUT, method = RequestMethod.POST)
    public @ResponseBody
    R<String> logout(HttpServletRequest request, HttpServletResponse response) {
        TokenFilter.HttpTokenRequestWrapper httpTokenRequestWrapper = new TokenFilter.HttpTokenRequestWrapper(request, "");
        R<String> result = new R<String>().ok("");
        Map<String, String> params = new HashMap<String, String>();
        getHeaderParamValues(params,request);//获取请求头信息 来源
        String logintype = params.get(LoginParamConts.X_LOGINTYPE);
        params.clear();
        params.put(LoginParamConts.X_LOGINTYPE,logintype);
        httpTokenRequestWrapper.setParameterMap(params);
        final WebContext context = new J2EContext(httpTokenRequestWrapper, response);
        ssoAuthenticationService.logout(context);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return result;
    }
}