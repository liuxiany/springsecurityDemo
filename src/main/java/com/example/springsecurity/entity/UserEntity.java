package com.example.springsecurity.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 昵称
     */
    @Column
    private String nickname;

    /**
     * 权限
     */
    @Column
    private String roles;
}
