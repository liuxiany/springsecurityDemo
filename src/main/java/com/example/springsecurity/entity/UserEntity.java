package com.example.springsecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "admin_user")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    /**
     * 账号
     */
    @Column(name = "user_name")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickname;

    /**
     * 权限
     */
    @Column(name = "role_id")
//    @JoinColumn(table = "admin_role",referencedColumnName = "id")
    private String roleId;

    @Column(name = "create_time")
    private Date createTime;
}
