package com.example.springsecurity.constant;

/**
 * @author liuxy
 * @date 2018-08-14 11:09
 */
public enum SecurityEnum {

    /**
     * 用户权限
     */
    ROLE_USER("ROLE_USER"),

    /**
     * 管理员权限
     */
    ROLE_ADMIN("ROLE_ADMIN"),

    /**
     * 用户权限
     */
    USER("USER");

    private String value;

    public String getValue() {
        return value;
    }

    private SecurityEnum(String value){
        this.value = value;
    }
}
