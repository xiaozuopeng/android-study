package com.huaao.ejingwu.common.http.retrofit;

/**
 * Author: xzp
 * Date: 2017/3/20
 */
public interface HttpListener<T> {

    void onSuccess(String dataRequestType, T t);

    void onFailure(String dataRequestType, String errorMsg, int errorCode);
}
