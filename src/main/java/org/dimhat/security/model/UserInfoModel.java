package org.dimhat.security.model;

import org.dimhat.security.authz.AuthorizationInfo;

import java.util.Set;

/**
 * session中的用户信息模型
 * @author dimhat
 * @date 2016年8月15日 下午4:23:30
 * @version 1.0
 */
public class UserInfoModel implements AuthorizationInfo {

	private Long id;

	private String nickName; //昵称

	private String username; //用户名

	private Set<String> roles;//角色字符串集合

	private Set<String> perms;//权限字符串集合

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPerms() {
		return perms;
	}

	public void setPerms(Set<String> perms) {
		this.perms = perms;
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

}
