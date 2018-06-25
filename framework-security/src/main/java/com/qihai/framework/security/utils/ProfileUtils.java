package com.qihai.framework.security.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qihai.commerce.framework.utils.StringUtil;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.commerce.framework.vo.UserResources;

/**
 * Created by liheng on 2017/5/20.
 */
public class ProfileUtils {

    public static UserInfo toUserInfo(Map<String, Object> attributes) {
        UserInfo userInfo = null;
        if (attributes != null) {
            userInfo = new UserInfo();
            userInfo.setId(getValue(attributes, UserInfo.KEY_ID));
            userInfo.setUsername(getValue(attributes, UserInfo.KEY_USERNAME));
            userInfo.setNickname(getValue(attributes, UserInfo.KEY_NICKNAME));
            userInfo.setPhone(getValue(attributes, UserInfo.KEY_PHONE));
            userInfo.setType(getValue(attributes, UserInfo.KEY_TYPE));
            userInfo.setFrom(getValue(attributes, UserInfo.KEY_FROM));
            userInfo.setResources((List<UserResources>)attributes.get(UserInfo.KEY_RESOURCES));
        }
        return userInfo;
    }

    public static Map<String, Object> toMap(UserInfo userInfo) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (userInfo != null) {

            String temp = userInfo.getId();
            if (StringUtil.isNotBlank(temp)) {
                map.put(UserInfo.KEY_ID, temp);
            }

            temp = userInfo.getUsername();
            if (StringUtil.isNotBlank(temp)) {
                map.put(UserInfo.KEY_USERNAME, temp);
            }

            temp = userInfo.getNickname();
            if (StringUtil.isNotBlank(temp)) {
                map.put(UserInfo.KEY_NICKNAME, temp);
            }

            temp = userInfo.getPhone();
            if (StringUtil.isNotBlank(temp)) {
                map.put(UserInfo.KEY_PHONE, temp);
            }

            temp = userInfo.getType();
            if (StringUtil.isNotBlank(temp)) {
                map.put(UserInfo.KEY_TYPE, temp);
            }

            temp = userInfo.getFrom();
            if (StringUtil.isNotBlank(temp)) {
                map.put(UserInfo.KEY_FROM, temp);
            }
            
            List<UserResources> resources = userInfo.getResources();
            if (resources != null && resources.size() > 0) {
                map.put(UserInfo.KEY_RESOURCES, resources);
            }
        }

        return map;
    }

    public static Map<String, String> toRequestHeaders(UserInfo userInfo) {
        HashMap<String, String> requestHeaders = new HashMap<String, String>();

        Map<String, Object> map = toMap(userInfo);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    String temp = entry.getValue().toString();
                    if (UserInfo.KEY_NICKNAME.equalsIgnoreCase(entry.getKey())) {
                        try {
                            temp = new String(temp.getBytes("UTF-8"), "iso8859-1");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    requestHeaders.put(UserInfo.getHeaderName(entry.getKey()), temp);
                }
            }
        }

        return requestHeaders;
    }

    public static String getUserid(Map<String, Object> attributes) {
        return getValue(attributes, UserInfo.KEY_ID);
    }


    public static String getUsername(Map<String, Object> attributes) {
        return getValue(attributes, UserInfo.KEY_USERNAME);
    }

    public static String getValue(Map<String, Object> attributes, String key) {
        String result = "";
        Object tempObj = attributes.get(key);
        if (tempObj != null) {
            result = tempObj.toString();

            if (result == null) {
                result = "";
            }
        }

        return result;
    }
}