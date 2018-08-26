package com.example.springsecurity.dao;

import com.example.springsecurity.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
public interface RoleJpaDao extends JpaRepository<RoleEntity,String> {

    /**
     * 根据角色值查询角色信息
     * @param roleValue
     * @return
     */
    public RoleEntity findByRoleValue(String roleValue);
}
