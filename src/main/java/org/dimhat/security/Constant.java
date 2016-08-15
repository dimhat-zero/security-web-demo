package org.dimhat.security;

/**
 * 全局常量类
 * 
 * @author dimhat
 * @date 2016年8月15日 下午1:38:32
 * @version 1.0
 */
public class Constant {

	//用户登录后的信息
	public static final String userInfo = "userInfo";

	//验证码在session的名称
	public static final String veriCode = "veriCode";

	//登录出错次数在session的名称
	public static final String loginTime = "loginTime";

	//登录出错次数限制次数，超出出现验证码
	public static final Integer limitLoginTime = 3;
}
