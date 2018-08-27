package com.huaao.ejingwu.common.http.retrofit;


import com.google.gson.JsonObject;
import com.huaao.ejingwu.common.BuildConfig;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 服务端请求的封装
 */
public interface HttpRequestUtils {

    String HOST_RELEASE = "https://www.baidu.com";
    String HOST_DEBUG = "https://www.baidu.com";

    String HOST = BuildConfig.DEBUG ? HOST_RELEASE : HOST_DEBUG;

    /*-----------------------------------------------------------------------------------------------------------------------*/

    @FormUrlEncoded
    @POST("http://wx.hbsdenterprise.com/emsweixin/wxwh/wxWhsfzRedirect.do")
    Observable<JsonObject> emsPay(@Field("data") String data);
}