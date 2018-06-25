package com.qihai.framework.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qihai.commerce.framework.utils.SpringContextsUtil;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.commerce.framework.vo.UserResources;
import com.qihai.framework.security.model.MyProfile;

/**
 * Created by liheng on 2017/9/15.
 */
@Service
public class AuthClientService {
    private static Logger logger = LoggerFactory.getLogger(AuthClientService.class);

    /**
     * 鉴权
     *
     * @param microserviceCode
     * @param userid
     * @param url
     * @param method
     * @return
     */
    public boolean isPermit(String url, String method) {
        boolean result = false;
        try {
        	String contextPath = SpringContextsUtil.getProperty("server.servlet.context-path", "/");
        	if (contextPath.length() != 1) {
        		contextPath = contextPath.substring("/".length());
    		}
        	
            UserResources userResources = new UserResources();
            userResources.setUrl(url);
            userResources.setMethod(method);
            userResources.setServiceCode(contextPath);
            
            MyProfile myProfile = MyProfile.getMyProfile4Context();
            UserInfo userInfo = myProfile.getUserInfo();
            List<UserResources> resources = userInfo.getResources();
            if (resources.contains(userResources)) {
            	result = true;
			}
        } catch (Exception e) {
            logger.error("isPermit is error!", e);
        }

        return result;
    }
    
}
