package com.qihai.framework.security.cache;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.commerce.framework.vo.UserResources;
import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.model.MyProfile;
import com.qihai.framework.security.utils.CacheUtils;
import com.qihai.framework.security.utils.ProfileUtils;

/**
 * Created by liheng on 2017/5/27.
 */
public class ProfileCache {
    private static Logger logger = LoggerFactory.getLogger(ProfileCache.class);
    private static String[] KEYS = {UserInfo.KEY_ID, UserInfo.KEY_USERNAME, UserInfo.KEY_NICKNAME, UserInfo.KEY_PHONE, UserInfo.KEY_TYPE, UserInfo.KEY_FROM
                                    , Const.CACHE_KEY_EXPIRES_IN, Const.CACHE_KEY_CHECK_SUM, Const.CACHE_KEY_REFRESH_TOKEN, UserInfo.KEY_RESOURCES};

    public static void add(MyProfile myProfile) {
        add(myProfile, CacheUtils.TIME_OUT_WEEK);
    }

    public static void add(MyProfile myProfile, long timeout) {
        String cacheKey = getKey(myProfile.getSid());

        Map<String, Object> cacheMap = ProfileUtils.toMap(myProfile.getUserInfo());
        cacheMap.put(Const.CACHE_KEY_EXPIRES_IN, myProfile.getExpireTime());
        cacheMap.put(Const.CACHE_KEY_CHECK_SUM, myProfile.getCheckSum());
        cacheMap.put(Const.CACHE_KEY_REFRESH_TOKEN, myProfile.getRefreshToken());

        CacheUtils.putAll4Hash(cacheKey, cacheMap, timeout);
    }

    public static MyProfile get(String key) {
        String cacheKey = getKey(key);
        MyProfile myProfile = null;

        List<Object> cacheValues = CacheUtils.gets4Hash(cacheKey, KEYS);
        if (cacheValues != null && cacheValues.size() > 0) {
            String userId = getValue(cacheValues.get(0));
            if (userId != null) {
                myProfile = new MyProfile();
                myProfile.setUserId(userId);
                myProfile.getUserInfo().setUsername(getValue(cacheValues.get(1)));
                myProfile.getUserInfo().setNickname(getValue(cacheValues.get(2)));
                myProfile.getUserInfo().setPhone(getValue(cacheValues.get(3)));
                myProfile.getUserInfo().setType(getValue(cacheValues.get(4)));
                myProfile.getUserInfo().setFrom(getValue(cacheValues.get(5)));
                myProfile.setExpire(getExpireTime(cacheValues.get(6)));
                myProfile.setCheckSum(getValue(cacheValues.get(7)));
                myProfile.setRefreshToken(getValue(cacheValues.get(8)));
                myProfile.getUserInfo().setResources((List<UserResources>)cacheValues.get(9));

                myProfile.setSid(key);
            }
        }

        return myProfile;
    }

    public static void delete(String key) {
        String cacheKey = getKey(key);
        CacheUtils.delete(cacheKey);
    }



    public static String getKey(String key) {
        return Const.CACHE_KEY_PREFIX_PROFILE + key;
    }

    private static String getValue(Object temp) {
        String result = null;

        if (temp != null) {
            result = temp.toString();
        }

        return result;
    }


    private static Long getExpireTime(Object temp) {
        Long result = null;

        if (temp != null) {
            if (temp.getClass().isAssignableFrom(Long.class)) {
                result = (Long) temp;
            }
        }

        if (result == null) {
            result = 0L;
        }

        return result;
    }

    public static void main(String[] args) {
    }
}
