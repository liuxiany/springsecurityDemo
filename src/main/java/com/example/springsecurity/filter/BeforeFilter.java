package com.example.springsecurity.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义filter
 * @author liuxy
 * @date 2018-08-14 14:16
 */
public class BeforeFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("This is a filter before UsernamePasswordAuthenticationFilter.");

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
