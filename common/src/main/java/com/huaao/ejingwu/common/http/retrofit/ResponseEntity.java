package com.huaao.ejingwu.common.http.retrofit;

import com.huaao.ejingwu.common.base.BaseBean;

/**
 * @author: xzp
 * @date: 2018/8/2
 * @desc:
 */
public class ResponseEntity<T> extends BaseBean {

    public static final Creator<ResponseEntity> CREATOR = new Creator<>(ResponseEntity.class);
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}