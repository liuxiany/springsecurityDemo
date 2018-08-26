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
@Table(name = "admin_role")
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_value")
    private String roleValue;

    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private Date createTime;
}
