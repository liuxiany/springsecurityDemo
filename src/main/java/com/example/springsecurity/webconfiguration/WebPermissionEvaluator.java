package com.example.springsecurity.webconfiguration;

import com.example.springsecurity.entity.AuthorityEntity;
import com.example.springsecurity.entity.RoleEntity;
import com.example.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
@Component
public class WebPermissionEvaluator implements PermissionEvaluator {

    private final static Logger logger = LoggerFactory.getLogger(WebPermissionEvaluator.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        logger.info("user {},permission {},on object {}" ,authentication.getName() ,permission ,target);

        //获取当前登陆的用户(即MysqlUserDetailService.loadUserByUsername方法返回的值)
        User user = (User) authentication.getPrincipal();

        //获得当前登陆的用户的所有角色
        Collection<GrantedAuthority> grantedAuthorityCollection = user.getAuthorities();

        if(grantedAuthorityCollection != null && grantedAuthorityCollection.size() > 0){
            for(GrantedAuthority grantedAuthority : grantedAuthorityCollection){
                //获得每个角色的所有权限
                RoleEntity roleEntity = userService.getRoleByRoleValue(grantedAuthority.getAuthority());
                List<AuthorityEntity> authorityEntityList = userService.getAuthorityListByRoleId(roleEntity.getId());

                //遍历权限列表
                if(authorityEntityList != null && authorityEntityList.size() > 0){
                    for(AuthorityEntity authorityEntity : authorityEntityList){
                        if(target.equals(authorityEntity.getAuthotiryUrl()) && permission.equals(authorityEntity.getAuthorityName())){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        logger.info("user {},permission {}, on object with id {}",authentication.getName() , permission , targetId);

        return false;
    }
}
