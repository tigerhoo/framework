package com.qihai.framework.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qihai.commerce.framework.utils.EncryptUtils;
import com.qihai.commerce.framework.utils.StringUtil;
import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.utils.CacheUtils;

/**
 * Created by liheng on 2017/5/18.
 */
@Service
public class MyAuthenticationService {
    private static Logger logger = LoggerFactory.getLogger(MyAuthenticationService.class);

    @Autowired
    private AuthClientService authClientService;

    public boolean checdkUrlAuthorized(String userid, String url, String method) {
        boolean result = false;
        boolean newFlag = false;

        if (Const.URLS_LOCAL.contains(url)) {
            return true;
        }

        String cacheKey = getKey(userid);
        String fullUrl = url;

        String key = EncryptUtils.md516(fullUrl + ":" + method);

        try {
            if (key != null) {
                Object temp = CacheUtils.get4Hash(cacheKey, key);
                if (temp != null) {
                    result = ((Boolean) temp).booleanValue();
                    logger.info("url权限校验, cache is hit[userid: {}, url: {}, method: {}]: {}", userid, url, method, result);
                }
            }

            if (!result) {
                //远程调用鉴权中心，验证登录用户是否有访问该url地址权限 url：/api/...
                result = isPermit(url, method);
                logger.info("url权限校验, remote verify[userid: {}, url: {}, method: {}]: {}", userid, url, method, result);
                newFlag = true;
            }
        } catch (Exception e) {
            logger.error("url权限校验出错！", e);
        }

        if (newFlag) {
            CacheUtils.put4Hash(cacheKey, key, result, CacheUtils.TIME_OUT_HALF_HOUR);
            logger.info("url权限校验, save to cache[userid: {}, url: {}, method: {}]: {}", userid, url, method, result);
        }

        return result;
    }

    public boolean isPermit(String url, String method) {
        boolean result = false;

        try {
            //权限认证
            if(StringUtil.isNotBlank(url) && StringUtil.isNotBlank(method)) {
                result = authClientService.isPermit(url, method);
            }
        } catch (Exception e) {
            logger.error("权限校验出错！", e);
        }

        return result;
    }
    
    private String getKey(String userid) {
        return Const.CACHE_KEY_PREFIX_AUTH + userid;
    }
}