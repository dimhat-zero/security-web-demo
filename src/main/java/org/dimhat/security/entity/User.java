package org.dimhat.security.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户表
 * @author dimhat
 * @date 2016年8月13日 下午2:33:35
 * @version 1.0
 */
public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -8700535822113311779L;

    private Long              id;

    private String            nickName;                                //昵称

    private String            username;                                //用户名

    private String            password;                                //密码

    private String            salt;                                    //盐

    private Boolean           locked           = Boolean.FALSE;        //是否锁定

    private Timestamp         lastLogin;                               //最后登录时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

}
