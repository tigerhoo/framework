package com.qihai.framework.security.config;

import java.util.List;

import org.pac4j.core.authorization.authorizer.ProfileAuthorizer;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.CommonProfile;

import com.qihai.commerce.framework.utils.SpringContextsUtil;
import com.qihai.framework.security.model.MyProfile;
import com.qihai.framework.security.service.MyAuthenticationService;

public class CustomAuthorizer extends ProfileAuthorizer<CommonProfile> {

    public CustomAuthorizer() {
    }

    @Override
    public boolean isAuthorized(final WebContext context, final List<CommonProfile> profiles) throws HttpAction {
        String method = context.getRequestMethod();
        if (!isAnyAuthorized(context, profiles)) {
            return false;
        }

        String url = ((J2EContext) context).getPath();

        return isUrlAuthorized(url, method);
    }

    @Override
    public boolean isProfileAuthorized(final WebContext context, final CommonProfile profile) {
        if (profile == null || profile.getId() == null) {
            return false;
        }

        MyProfile myProfile = MyProfile.getMyProfile4Context();
        if (myProfile == null) {
            return false;
        }

        return true;
    }

    private boolean isUrlAuthorized(String url, String method) {
        boolean result = false;

        MyProfile myProfile = MyProfile.getMyProfile4Context();
        if (myProfile != null) {
            /*if ("test".equalsIgnoreCase(SpringUtils.getProperty("spring.profiles.active", "prod"))) {
                result = true;
            } else {*/
                result = getCasAuthenticationService().checdkUrlAuthorized(myProfile.getUserId(), url, method);
            //}
        }

        return result;
    }

    private MyAuthenticationService getCasAuthenticationService() {
        return (MyAuthenticationService)SpringContextsUtil.getBean(MyAuthenticationService.class);
    }
}