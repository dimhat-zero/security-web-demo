package org.dimhat.security.model;

import org.dimhat.security.entity.Role;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by think on 2016/8/25.
 */
public class UserModel {
    private Long              id;

    private String            nickName;                                //昵称

    private String            username;                                //用户名

    private Boolean           locked           = Boolean.FALSE;        //是否锁定

    private Date lastLogin;                               //最后登录时间

    private List<Role> role;

    public String roleNames(){
        return "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
