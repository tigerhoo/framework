package com.qihai.framework.security.model;

import java.util.Date;

import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qihai.commerce.framework.utils.ContextUtils;
import com.qihai.commerce.framework.utils.EncryptUtils;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.framework.security.utils.ProfileUtils;

/**
 * Created by liheng on 2017/6/2
 */
public class MyProfile {
    private static Logger logger = LoggerFactory.getLogger(MyProfile.class);

    private UserInfo userInfo;
    private CommonProfile profile;
    private String jwt;
    private String sid;
    private String refreshToken;
    private String checkSum;
    private Long expire;
    private Long expireTime;

    public String getUserId() {
        String userId = null;
        if (this.userInfo != null) {
            userId = this.userInfo.getId();
        }

        return userId;
    }

    public void setUserId(String userId) {
        if (this.userInfo == null) {
            this.userInfo = new UserInfo();
        }

        this.userInfo.setId(userId);
    }

    public CommonProfile getProfile() {
        return profile;
    }

    public void setProfile(CommonProfile profile) {
        this.profile = profile;

        if (this.userInfo == null && profile != null) {
            UserInfo userInfo = ProfileUtils.toUserInfo(profile.getAttributes());
            if (userInfo != null) {
                setUserInfo(userInfo);
            }
        }
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        String username = null;
        if (this.userInfo != null) {
            username = this.userInfo.getUsername();
        }

        return username;
    }

    public void setUsername(String username) {
        if (this.userInfo == null) {
            this.userInfo = new UserInfo();
        }

        this.userInfo.setUsername(username);
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpire() {
        return this.expire;
    }

    public Long getExpireTime() {
        return this.expireTime;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
        this.expireTime = calculateExpire(this.expire);
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

//    public String getSessionId() {
//        return sessionId;
//    }
//
//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean verifyExpire() {
        boolean result = true;

        result = this.expire < System.currentTimeMillis();
        if (result) {
            logger.error("token已过期！[sid = {}]", this.sid);
        }

        return result;
    }

    public boolean verifyToken() {
        boolean result = false;

        String source = EncryptUtils.md5(this.jwt);

        result = source.equals(this.checkSum);
        if (!result) {
            logger.error("token校验码不一致！[sid = {}]", this.sid);
        }

        return result;
    }

    public boolean verifyRefreshToken(String inputRefreshToken) {
        boolean result = false;

        result = this.refreshToken.equals(inputRefreshToken);
        if (!result) {
            logger.error("refresh_token不一致！[sid = {}]", this.sid);
        }

        return result;
    }

    public static String calculateSID(String jwt) {
        String result = null;

        if (jwt != null) {
            result = EncryptUtils.md5(System.nanoTime() + ":" + jwt);
        }

        return result;
    }

    public static String wrapToken(String jwt, String sid) {
        String result = jwt;

        if (jwt != null) {
            int pos = jwt.indexOf("..");
            if (pos != -1) {
                pos ++;
                result = jwt.substring(0, pos) + sid + jwt.substring(pos);
            }
        }

        return result;
    }

    public static String[] extractToken(String token) {
        String result[] = new String[2];

        if (token != null) {
            try {
                int pos = token.indexOf(".");
                if (pos != -1) {
                    int pos2 = token.indexOf(".", pos + 1);
                    if (pos2 != -1) {
                        String sid = token.substring(pos + 1, pos2);
                        if (sid.length() == 32) {
                            result[0] = sid;
                            result[1] = token.substring(0, pos + 1) + token.substring(pos2);
                        } else {
                            logger.error("sid长度不足！[token = {}]", token);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("token解析出错！[token = {}]", token);
            }
        }

        return result;
    }

    public static MyProfile getMyProfile4Context() {
        MyProfile myProfile = null;
        MyContext myContext = null;
        if (ContextUtils.get() != null) {
            myContext = (MyContext) ContextUtils.get();

            myProfile = myContext.getMyProfile();
        }

        return myProfile;
    }


    private Long calculateExpire(long due) {
        final Date now = new Date();
        long expireTime = now.getTime() + due * 1000;
        return expireTime;
    }

    public static void main(String[] args) {
        String jwt = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0..FY8DZLr6NHpCcY_F.qZr9M4u2I7uRUnnfxZ0vkw_VnM18BK9V6gwYHnK2bscoTNBMfIDplCT6F50RiKcfovo2Oqal408RymF-nfsAsSX6-8kIVhHDPzGrzkJPo9Cd5e3yJD4UkVcOXveDoEJU2ZeX8NBb0l7kgVb1gwp3snZApLwbpb1hsM-GZD14WnpaR_UASElp-e1uERFEl8bxjD_TiJSFfXWtxyXEhnZ8TDai57LpFNDM-x7JVScdzRguut0eacmcaAm2f9xzW9EahvA9TmEt04cxE76tRr7SA5uhq-ABofLQW5RbTOVvt5l-g5P_uAKmE3DxHJ7N8UDA9jTy1GlYZle3Z67pBz9As1d1FZ9yxWyFdtBcLa-cBIhvQyn8UE8DVMh4PukTP6voqTQPrMS3J19H6n2KuYWEq9pdZOBLWuScfh5mNHw3c1TdXatAcQ-mRyv0PWehsA_1oxQpOQxZvaxpgE8rBRJG6VOZ5198ZDKeqi9IuFWbiYh0aAjEO__LqzPWtnfGdJXK.LFB94NXtPVdh5Y4MIf-9Jw";
        String sid = calculateSID(jwt);
        String token = wrapToken(jwt, sid);

        String[] temps = extractToken(token);

        System.out.println("sid0: " + sid);
        System.out.println("sid1: " + temps[0]);
        System.out.println("jwt0: " + jwt);
        System.out.println("jwt1: " + temps[1]);
        System.out.println("token: " + token);

    }
}
