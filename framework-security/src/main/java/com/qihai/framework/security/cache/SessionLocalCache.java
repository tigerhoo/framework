package com.qihai.framework.security.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liheng on 2017/7/3.
 */
public class SessionLocalCache {
    private static Set<String> profileIdSetFirst = new HashSet<String>();
    private static Set<String> profileIdSetSecond = new HashSet<String>();
    private static boolean saveToFirst = true;

    public static void addProfileId(String profileId) {
        if (saveToFirst) {
            profileIdSetFirst.add(profileId);
        } else {
            profileIdSetSecond.add(profileId);
        }
    }

    public static String[] getProfileIds() {
        boolean firstProfileIdSetFlag = saveToFirst;
        saveToFirst = !saveToFirst;
        String[] profileIds = null;
        if (firstProfileIdSetFlag) {
            profileIds = profileIdSetFirst.toArray(new String[profileIdSetFirst.size()]);
            profileIdSetFirst.clear();
        } else {
            profileIds = profileIdSetSecond.toArray(new String[profileIdSetSecond.size()]);
            profileIdSetSecond.clear();
        }

        return profileIds;
    }
}
