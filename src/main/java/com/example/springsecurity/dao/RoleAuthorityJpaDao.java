package com.example.springsecurity.dao;

import com.example.springsecurity.entity.RoleAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
public interface RoleAuthorityJpaDao extends JpaRepository<RoleAuthorityEntity,String> {
    /**
     * 根据角色id获取列表
     * @param roleId
     * @return
     */
    List<RoleAuthorityEntity> findAllByRoleId(String roleId);
}
