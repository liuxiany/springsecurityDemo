package com.example.springsecurity.webconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebPermissionConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private WebPermissionEvaluator webPermissionEvaluator;

    @Override
    public MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(webPermissionEvaluator);
        return expressionHandler;

    }
}
