package com.qihai.framework.security.custom;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.CommonProfile;

import com.qihai.commerce.framework.utils.StringUtil;
import com.qihai.commerce.framework.vo.UserInfo;
import com.qihai.framework.security.constants.LoginParamConts;
import com.qihai.framework.security.utils.ProfileUtils;

/**
 * Created by liheng on 2017/8/23.
 */
public class MyUsernamePasswordAuthenticator implements Authenticator<UsernamePasswordCredentials> {

    @Override
    public void validate(final UsernamePasswordCredentials credentials, final WebContext context) throws HttpAction, CredentialsException {
        if (credentials == null) {
            throwsException("No credential");
        }
        final CommonProfile profile = new CommonProfile();

        String phoneNumber = context.getRequestParameter(LoginParamConts.PHONE_NUMBER);
        String from = context.getRequestParameter(LoginParamConts.X_FROM);
        String type = context.getRequestParameter(LoginParamConts.X_TYPE);
        String userName = context.getRequestParameter(LoginParamConts.USERNAME);

        //TODO 对接真实数据
        UserInfo userInfo = new UserInfo();
        //userInfo.setId(username);
        if(StringUtil.isNotBlank(userName) && !LoginParamConts.USERNAME.equals(userName)) {
            userInfo.setUsername(userName);
        }else {
            userInfo.setUsername(phoneNumber);
        }
        //userInfo.setNickname(phoneNumber);//TODO 临时使用手机号 // TOFIXED
        userInfo.setPhone(phoneNumber);
        userInfo.setType(type);
        userInfo.setFrom(from);

        profile.addAttributes(ProfileUtils.toMap(userInfo));
        //profile.setId(userInfo.getId());
        credentials.setUserProfile(profile);
    }

    protected void throwsException(final String message) throws CredentialsException {
        throw new CredentialsException(message);
    }
}