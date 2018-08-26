package com.example.springsecurity.dao;

import com.example.springsecurity.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
public interface AuthorityJpaDao extends JpaRepository<AuthorityEntity,String> {
}
