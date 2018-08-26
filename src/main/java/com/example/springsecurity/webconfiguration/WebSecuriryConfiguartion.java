package com.example.springsecurity.webconfiguration;

import com.example.springsecurity.constant.SecurityEnum;
import com.example.springsecurity.filter.AfterFilter;
import com.example.springsecurity.filter.BeforeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liuxy
 * @date 2018-08-13 17:34
 */
@Configuration
@EnableWebSecurity
public class WebSecuriryConfiguartion extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MysqlUserDetailService mysqlUserDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/js/**",
                        "/css/**",
                        "/img/**");
    }

    /**
     * Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity)
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            //.antMatchers("/user/**").hasRole(SecurityEnum.USER.getValue())
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/user")
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        http.addFilterBefore(new BeforeFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new AfterFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("liuxy")
                .password(passwordEncoder.encode("123456"))
                .roles("USER");*/

        auth.userDetailsService(mysqlUserDetailService).passwordEncoder(passwordEncoder);
    }

}
