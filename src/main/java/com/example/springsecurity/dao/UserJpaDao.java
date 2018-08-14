package com.example.springsecurity.dao;

import com.example.springsecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuxy
 * @date 2018-08-14 10:56
 */
public interface UserJpaDao extends JpaRepository<UserEntity,Long> {
    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    public UserEntity findUserEntityByUsername(String userName);
}
