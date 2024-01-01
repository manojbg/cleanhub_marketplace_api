package com.cleanhub.restapi.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "clean.api")
public class ApiProperties {
    private String logo;
    private String order;

}
