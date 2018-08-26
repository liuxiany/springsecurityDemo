package com.example.springsecurity.webconfiguration;

import com.example.springsecurity.constant.SecurityEnum;
import com.example.springsecurity.dao.RoleJpaDao;
import com.example.springsecurity.entity.RoleEntity;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxy
 * @date 2018-08-14 11:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MysqlUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleJpaDao roleJpaDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userService.getByUsername(userName);

        if(userEntity == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        List<SimpleGrantedAuthority> authorityList = createAuthorities(userEntity.getRoleId());

        User user = new User(userEntity.getUsername(),userEntity.getPassword(),authorityList);

        return user;
    }

    /**
     * 权限字符串转化
     *
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleId 权限id
     */
    private List<SimpleGrantedAuthority> createAuthorities(String roleId){

        RoleEntity roleEntity = userService.getRoleByRoleId(roleId);

        if(roleEntity == null){
            return null;
        }

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleEntity.getRoleValue());
        simpleGrantedAuthorities.add(simpleGrantedAuthority);

        return simpleGrantedAuthorities;
    }
}
