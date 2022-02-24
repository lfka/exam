/*
 * <b>文件名</b>：ShiroConstant.java
 *
 * 文件描述：
 *
 *
 * 2017年11月17日  下午1:36:42
 */

package com.study.onlineexam.constant;



/**
 * @Description shiro的常量类
 */

public class ShiroConstant {
	
	//未登录
	public static final Integer NO_LOGIN_CODE = 1001;
	public static final String NO_LOGIN_MESSAGE = "请先进行登录";

	//登录成功
	public static final Integer LOGIN_SUCCESS_CODE = 1002;
	public static final String LOGIN_SUCCESS_MESSAGE = "登录成功";

	//登录失败
	public static final Integer LOGIN_FAILURE_CODE = 1003;
	public static final String LOGIN_FAILURE_MESSAGE = "登录失败";

	//缺少用户权限
	public static final Integer NO_AUTH_CODE = 1005;
	public static final String NO_AUTH_MESSAGE = "权限不足";

	//缺少用户角色
	public static final Integer NO_ROLE_CODE = 1006;
	public static final String NO_ROLE_MESSAGE = "用户角色不符合";

	//Token过期或无效
	public static final Integer TOKEN_TIMEOUT_CODE = 1007;
	public static final String TOKEN_TIMEOUT_MESSAGE = "TOKEN过期或无效，请重新登录";

	//密码错误次数过多
	public static final Integer PWD_COUNT_CODE = 1008;
	public static final String PWD_COUNT_MESSAGE = "错误次数过多，请十分钟后重试";

	//未经授权
	public static final Integer NO_AUTHED_CODE = 1009;
	public static final String NO_AUTHED_MESSAGE = "未经授权!";

}
