package com.example.springsecurity.service;


import com.example.springsecurity.entity.AuthorityEntity;
import com.example.springsecurity.entity.RoleEntity;
import com.example.springsecurity.entity.UserEntity;

import java.util.List;

public interface UserService {

    /**
     * 添加新用户
     *
     * username 唯一， 默认 USER 权限
     */
    boolean insert(UserEntity userEntity);

    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserEntity getByUsername(String username);

    /**
     * 根据主键查询角色信息
     * @param roleId
     * @return
     */
    RoleEntity getRoleByRoleId(String roleId);

    /**
     * 根据角色Id获得权限列表
     * @param roleId
     * @return
     */
    List<AuthorityEntity> getAuthorityListByRoleId(String roleId);

    /**
     * 根据角色值获得角色信息
     * @param roleValue
     * @return
     */
    RoleEntity getRoleByRoleValue(String roleValue);

}
