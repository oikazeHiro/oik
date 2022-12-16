package com.oik.api.config.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties(prefix="oik.shiro")
public class ShiroProperties {
    private List<String> anonUrl = new ArrayList<>();

    public List<String> getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(List<String> anonUrl) {
        this.anonUrl = anonUrl;
    }
}
