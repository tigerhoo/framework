package com.qihai.framework.security.custom;

import org.pac4j.core.client.Client;
import org.pac4j.core.client.IndirectClient;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.springframework.security.web.Pac4jEntryPoint;

import java.util.List;

import static org.pac4j.core.util.CommonHelper.isNotEmpty;

/**
 * Created by liheng on 2017/5/23.
 */
public class MyPac4jEntryPoint extends Pac4jEntryPoint {

    public MyPac4jEntryPoint() {
        super();
    }

    public MyPac4jEntryPoint(final Config config, final String clientName) {
        super(config, clientName);
    }

    protected boolean startAuthentication(final J2EContext context, final List<Client> currentClients) {
        logger.info("----url: {}, token: {}", context.getFullRequestURL(), context.getRequestHeader("token"));

        if ("application/json".equals(context.getRequest().getHeader("Content-Type"))) {
            return false;
        }
        return isNotEmpty(currentClients) && currentClients.get(0) instanceof IndirectClient;
    }
}