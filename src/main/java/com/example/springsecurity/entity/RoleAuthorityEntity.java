package com.example.springsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
@Data
@Entity
@Table(name = "admin_role_authority")
public class RoleAuthorityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "role_id")
//    @JoinColumn(table = "admin_role", referencedColumnName = "id")
    private String roleId;

    @Column(name = "authority_id")
//    @JoinColumn(table = "admin_authority",referencedColumnName = "id")
    private String authorityId;

    @Column(name = "create_time")
    private Date createTime;
}
