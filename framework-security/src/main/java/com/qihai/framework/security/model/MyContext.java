package com.qihai.framework.security.model;

import com.qihai.commerce.framework.vo.Context;

/**
 * Created by liheng on 2017/6/2.
 */
public class MyContext extends Context {
    private MyProfile myProfile = new MyProfile();

    public MyProfile getMyProfile() {
        return myProfile;
    }

    public void setMyProfile(MyProfile myProfile) {
        this.myProfile = myProfile;
    }
}
