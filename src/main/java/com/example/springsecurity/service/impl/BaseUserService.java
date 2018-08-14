package com.example.springsecurity.service.impl;

import com.example.springsecurity.constant.SecurityEnum;
import com.example.springsecurity.dao.UserJpaDao;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Primary
public class BaseUserService implements UserService {

    @Autowired
    private UserJpaDao userJpaDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean insert(UserEntity userEntity) {

        String username = userEntity.getUsername();

        if (exist(username)){
            return false;
        }

        userEntity.setRoles(SecurityEnum.ROLE_USER.getValue());

        userEntity.setPassword(passwordEncoder.encode(StringUtils.trimAllWhitespace(userEntity.getPassword())));

        UserEntity result = userJpaDao.save(userEntity);

        return  result != null;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userJpaDao.findUserEntityByUsername(username);
    }

    /**
     * 判断用户是否存在
     * @param username 账号
     * @return 密码
     */
    private boolean exist(String username){
        UserEntity userEntity = userJpaDao.findUserEntityByUsername(username);
        return (userEntity != null);
    }

}
