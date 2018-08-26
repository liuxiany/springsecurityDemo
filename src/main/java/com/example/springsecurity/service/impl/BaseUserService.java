package com.example.springsecurity.service.impl;

import com.example.springsecurity.constant.SecurityEnum;
import com.example.springsecurity.dao.AuthorityJpaDao;
import com.example.springsecurity.dao.RoleAuthorityJpaDao;
import com.example.springsecurity.dao.RoleJpaDao;
import com.example.springsecurity.dao.UserJpaDao;
import com.example.springsecurity.entity.AuthorityEntity;
import com.example.springsecurity.entity.RoleAuthorityEntity;
import com.example.springsecurity.entity.RoleEntity;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Primary
@Transactional(rollbackFor = Exception.class)
public class BaseUserService implements UserService {

    @Autowired
    private UserJpaDao userJpaDao;

    @Autowired
    private RoleJpaDao roleJpaDao;

    @Autowired
    private RoleAuthorityJpaDao roleAuthorityJpaDao;

    @Autowired
    private AuthorityJpaDao authorityJpaDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean insert(UserEntity userEntity) {

        String username = userEntity.getUsername();

        if (exist(username)){
            return false;
        }

        RoleEntity roleEntity = roleJpaDao.findByRoleValue(SecurityEnum.ROLE_USER.getValue());

        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setRoleId(roleEntity.getId());

        userEntity.setPassword(passwordEncoder.encode(StringUtils.trimAllWhitespace(userEntity.getPassword())));

        UserEntity result = userJpaDao.save(userEntity);

        return  result != null;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userJpaDao.findUserEntityByUsername(username);
    }

    @Override
    public RoleEntity getRoleByRoleId(String roleId) {
        RoleEntity roleEntity = roleJpaDao.getOne(roleId);
        return roleEntity;
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

    @Override
    public List<AuthorityEntity> getAuthorityListByRoleId(String roleId) {

        List<RoleAuthorityEntity> roleAuthorityEntityList = roleAuthorityJpaDao.findAllByRoleId(roleId);

        if(roleAuthorityEntityList != null && roleAuthorityEntityList.size() > 0){

            List<AuthorityEntity> authorityEntityList = new ArrayList<>();

            roleAuthorityEntityList.forEach(
                    roleAuthorityEntity ->
                        {
                            AuthorityEntity authorityEntity = authorityJpaDao.getOne(roleAuthorityEntity.getAuthorityId());
                            authorityEntityList.add(authorityEntity);
                        }

                    );
            return authorityEntityList;
        }

        return null;
    }

    @Override
    public RoleEntity getRoleByRoleValue(String roleValue) {

        RoleEntity roleEntity = roleJpaDao.findByRoleValue(roleValue);

        return roleEntity;
    }
}
