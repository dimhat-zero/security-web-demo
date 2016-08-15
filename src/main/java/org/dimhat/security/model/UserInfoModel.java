package org.dimhat.security.model;

/**
 * session中的用户信息模型
 * @author dimhat
 * @date 2016年8月15日 下午4:23:30
 * @version 1.0
 */
public class UserInfoModel {

	private Long id;

	private String nickName; //昵称

	private String username; //用户名

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
