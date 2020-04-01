package com.fc.psi.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/security.properties")
@ConfigurationProperties(prefix = "security")
public class SecurityYml {

    private String username;
    private String password;
    private String loginPage;
    private String failureUrl;
    private String logoutUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    @Override
    public String toString() {
        return "SecurityYml{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", loginPage='" + loginPage + '\'' + ", failureUrl='" + failureUrl + '\'' + ", logoutUrl='" + logoutUrl + '\'' + '}';
    }
}
