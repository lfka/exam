package com.study.onlineexam.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseVo<T> implements Serializable {

    private static final long serialVersionUID = -1;

    private Integer code ;

    private String msg ;

    private T data;

    public ResponseVo(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseVo(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseVo result(Integer code, String msg) {
        ResponseVo responseVo = new ResponseVo(code,msg);
        return responseVo;
    }
    public static <T>ResponseVo<T> result(Integer code, String msg,T data) {
        ResponseVo<T> responseVo = new ResponseVo(code,msg,data);
        return responseVo;
    }
}
