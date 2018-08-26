package com.example.springsecurity.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxiany
 * @date 2018/08/26
 */
@Data
@Entity
@Table(name = "admin_authority")
public class AuthorityEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "authority_name")
    private String authorityName;

    @Column(name = "authotiry_url")
    private String authotiryUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private Date createTime;
}
