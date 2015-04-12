package com.yoolean.platform.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by chenhang on 2015/3/28.
 */
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        register(HotSpotResources.class);
    }
}
