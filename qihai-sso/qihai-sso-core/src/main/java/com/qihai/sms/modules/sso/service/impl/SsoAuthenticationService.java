package com.qihai.sms.modules.sso.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.pac4j.core.config.Config;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.http.client.direct.DirectFormClient;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qihai.commerce.framework.enums.BizErrorCode;
import com.qihai.commerce.framework.utils.EncryptUtils;
import com.qihai.commerce.framework.utils.MD5Utils;
import com.qihai.commerce.framework.utils.R;
import com.qihai.commerce.framework.utils.StringUtil;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.commerce.framework.vo.UserResources;
import com.qihai.framework.security.cache.ProfileCache;
import com.qihai.framework.security.constants.Const;
import com.qihai.framework.security.constants.LoginParamConts;
import com.qihai.framework.security.model.MyProfile;
import com.qihai.framework.security.utils.CacheUtils;
import com.qihai.framework.security.utils.ProfileUtils;
import com.qihai.sms.modules.sso.entity.UserInfoEntity;
import com.qihai.sms.modules.sso.service.UserInfoService;

@Service
public class SsoAuthenticationService {
	
	private static Logger logger = LoggerFactory.getLogger(SsoAuthenticationService.class);

	@Value("${salt}")
    private String salt;

    /*
     * pc登录用户会话超时时间，单位为秒，默认2个小时（2 * 60 * 60）
     */
    @Value("${webuser.session.timeout:7200}")
    private long webSessionTimeout;

    /*
     * app登录用户会话超时时间，单位为秒，默认1个月（31 * 24 * 60 * 60）
     */
    @Value("${appuser.session.timeout:2678400}")
    private long appSessionTimeout;

    @Autowired
    private Config config;
    
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录（账号/密码方式）
     * @param context
     * @return
     */
    public R<Map<String, Object>> login(final WebContext context) {
        String msg = "登录失败！";
        return kyErpLogin(context, msg);
    }

    /**
     * 刷新Token
     * @param context
     * @return
     */
    public R<Map<String, Object>> refreshToken(final WebContext context) {
        String msg = "刷新accessToken失败！";

        MyProfile myProfile = MyProfile.getMyProfile4Context();
        MyProfile myProfile4Jwt = getCommonProfileFromSession(context);

        if (myProfile4Jwt != null && myProfile4Jwt.getProfile() != null) {
            myProfile.setProfile(myProfile4Jwt.getProfile());
        }

        if (myProfile != null) {
            CommonProfile commonProfile = myProfile.getProfile();
            if (commonProfile != null) {
                ProfileCache.delete(myProfile.getSid());
                commonProfile.removeAttribute("iat");
            }
        }

        return generatorJwt(context, myProfile, msg);
    }


    public R<UserInfo> getUserInfo(final WebContext context) {
        R<UserInfo> result = null;
        try {
            final HeaderClient client = (HeaderClient) config.getClients().findClient("HeaderClient");
            TokenCredentials tokenCredentials = client.getCredentials(context);
            if (tokenCredentials != null) {
                CommonProfile commonProfile = client.getUserProfile(tokenCredentials, context);
                if (commonProfile != null) {
                    UserInfo userInfo = ProfileUtils.toUserInfo(commonProfile.getAttributes());
                    result = new R<UserInfo>().ok(userInfo);
                }
            }
        } catch (Exception e) {
            logger.info("获取用户信息失败： 用户未登录或登录超时！", e);
        }

        if (result == null) {
        	result = new R<UserInfo>().error(BizErrorCode.UserErrorType.USER_ACCOUNT_ERROR.getCode(), "获取用户信息失败！");
            logger.info("获取用户信息失败： 用户未登录或登录超时！");
        }

        return result;
    }

    public MyProfile getCommonProfile(final WebContext context) {
        MyProfile myProfile = new MyProfile();

        CommonProfile commonProfile = null;
        try {
            final DirectFormClient client = (DirectFormClient) config.getClients().findClient("DirectFormClient");
            //HttpAction httpAction = client.redirect(context);
            commonProfile = client.getUserProfile(client.getCredentials(context), context);
        } catch (Exception e) {
            logger.error("获取CommonProfile出错！", e);
        }

        myProfile.setProfile(commonProfile);

        return myProfile;
    }

    public void logout(final WebContext context) {
        MyProfile myProfile = MyProfile.getMyProfile4Context();
        if (myProfile != null) {
            try {
                //清理缓存登录信息
                ProfileCache.delete(myProfile.getSid());
            } catch(Exception e){
                logger.error("登出时, 删除缓存出错！", e);
            }
        }
    }

    private R<Map<String, Object>> kyErpLogin(final WebContext context, String msg) {
        R<Map<String, Object>> result = null;
        String userId = null;
        
        //优先取手机号和验证码
        Map<String, Object> paramMap = new HashMap<>();
        String username = context.getRequestParameter(LoginParamConts.USERNAME);
        String password = context.getRequestParameter(LoginParamConts.PASSWORD);

        //用户名密码登录
        paramMap.put("login_name", username);
        paramMap.put("password", MD5Utils.encryption(password).toUpperCase());
        //调用数据库，用户登录
        List<UserInfoEntity> userInfoEntities = userInfoService.selectByMap(paramMap);
        //responseData = testUserService(paramMap);

        if (null != userInfoEntities && userInfoEntities.size() == 1) {
        	UserInfoEntity userInfoEntity = userInfoEntities.get(0);
        	if ("A".equals(userInfoEntity.getStatus())) {
        		userId = userInfoEntity.getId().toString();
        		
        		MyProfile myProfile = null;
                if (myProfile == null) {
                    myProfile = getCommonProfileFromSession(context);
                }

                if (myProfile.getUserInfo() == null) {
                    myProfile = getCommonProfile(context);
                }

                if (userId != null && myProfile.getProfile() != null && myProfile.getUserInfo() != null) {
                    myProfile.getProfile().setId(userId);
                    myProfile.getProfile().addAttribute(UserInfo.KEY_ID, userId);
                    myProfile.getUserInfo().setId(userId);
                    myProfile.getUserInfo().setNickname(userInfoEntity.getUserName());
                    
                    List<UserResources> resources = userInfoService.getUserResourcesById(userId);
                    
                    //添加测试数据
                    if (null == resources) {
                    	resources = new ArrayList<>();
					}
                    UserResources userResources1 = new UserResources();
                    userResources1.setServiceCode("dms");
                    userResources1.setUrl("/api/demo");
                    userResources1.setMethod("POST");
                    resources.add(userResources1);
                    
                    myProfile.getUserInfo().setResources(resources);
                } else {
                    myProfile = null;
                }

                result = generatorJwt(context, myProfile, msg);
			}else {
				result = new R<Map<String, Object>>().error(BizErrorCode.UserErrorType.USER_ACCOUNT_FORBIDDEN.getCode(), BizErrorCode.UserErrorType.USER_ACCOUNT_FORBIDDEN.getDesc());
			}
        }else if (null != userInfoEntities && userInfoEntities.size() > 1) {
        	result = new R<Map<String, Object>>().error(BizErrorCode.UserErrorType.USER_HAS_EXIST.getCode(), BizErrorCode.UserErrorType.USER_HAS_EXIST.getDesc());
		}else{
			result = new R<Map<String, Object>>().error(BizErrorCode.UserErrorType.USER_ACCOUNT_ERROR.getCode(), BizErrorCode.UserErrorType.USER_ACCOUNT_ERROR.getDesc());
        }

        return result;
    }

    private R<Map<String, Object>> generatorJwt(final WebContext context, MyProfile myProfile, String msg) {
        R<Map<String, Object>> result = null;

        Map<String, Object> map = new HashMap<String, Object>();
        String code = BizErrorCode.ReturnType.IS_FAIL.getCode();
        String token = "";

        if (myProfile != null) {
            UserInfo userInfo = null;
            userInfo = myProfile.getUserInfo();
            if (userInfo != null) {
                final JwtGenerator<CommonProfile> generator = new JwtGenerator<>(new SecretSignatureConfiguration(salt), new SecretEncryptionConfiguration(salt));
                token = generator.generate(myProfile.getProfile());

                myProfile.setJwt(token);
                myProfile.setSid(MyProfile.calculateSID(token));
                myProfile.setCheckSum(EncryptUtils.md5(myProfile.getJwt()));
                myProfile.setRefreshToken(myProfile.getCheckSum());

                long timeout = 0L;
                boolean isWebRequest = StringUtil.isEmpty(context.getRequestHeader(Const.REQUEST_HEADER_KEY_DEVICE));
                if (isWebRequest) {
                    timeout = webSessionTimeout;
                } else {
                    timeout = appSessionTimeout;
                }

                if (timeout < 1) {
                    timeout = CacheUtils.TIME_OUT_TWO_HOUR;
                }
                //token过期时长
                myProfile.setExpire(timeout);

                ProfileCache.add(myProfile, timeout);

                if (!isWebRequest) {
                    map.put(Const.CACHE_KEY_REFRESH_TOKEN, myProfile.getRefreshToken());
                }
                map.put(Const.CACHE_KEY_EXPIRES_IN, myProfile.getExpire());

                token = MyProfile.wrapToken(token, myProfile.getSid());

                code = BizErrorCode.ReturnType.IS_SUCCESS.getCode();
                msg = "OK";
            }
        }
        map.put(LoginParamConts.TOKEN, token);

        result = new R<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(map);

        return result;
    }

    private MyProfile getCommonProfileFromSession(final WebContext context) {
        MyProfile myProfile = new MyProfile();
        CommonProfile commonProfile = null;

        try {
            final ProfileManager<CommonProfile> manager = new ProfileManager<>(context);
            final Optional<CommonProfile> profile = manager.get(true);
            if (profile.isPresent()) {
                commonProfile = profile.get();
            }
        } catch (Exception e) {
            logger.error("获取CommonProfile出错！", e);
        }
        myProfile.setProfile(commonProfile);

        return myProfile;
    }
}
