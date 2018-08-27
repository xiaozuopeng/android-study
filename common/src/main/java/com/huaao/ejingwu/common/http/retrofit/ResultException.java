package com.huaao.ejingwu.common.http.retrofit;

/**
 * Author: xzp
 * Date: 2017/3/20
 */
public class ResultException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public ResultException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
