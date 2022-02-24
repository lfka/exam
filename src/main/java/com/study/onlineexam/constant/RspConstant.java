package com.study.onlineexam.constant;


/**
 * @Description 返回值值常量类
 */
public class RspConstant {

    //已有注册账号
    public static final Integer DUPLICATE_NAME_CODE  = 1010;
    public static final String DUPLICATE_NAME_MESSAGE = "账号重复";

    //注册成功
    public static final Integer REG_SUCCESS_CODE = 1011;
    public static final String REG_SUCCESS_MESSAGE = "注册成功";

    //注册失败
    public static final Integer REG_FAIL_CODE = 1012;
    public static final String REG_FAI_MESSAGE = "注册失败";

    //用户名或密码为空
    public static final Integer U_P_CODE = 1013;
    public static final String U_P_MESSAGE = "输入值为空";

}
