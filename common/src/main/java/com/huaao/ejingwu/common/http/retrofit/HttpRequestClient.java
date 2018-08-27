package com.huaao.ejingwu.common.http.retrofit;

import android.content.Context;
import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.huaao.ejingwu.common.R;
import com.huaao.ejingwu.common.utils.UtilsManager;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: xzp
 * Date: 2017/3/17
 */
public class HttpRequestClient {

    private static final String BASE_URL = HttpRequestUtils.HOST;
    private static HttpRequestClient instance;
    private HttpRequestUtils httpRequestUtils;
    private Retrofit retrofit;

    public static HttpRequestClient getInstance() {
        if (instance == null) {
            synchronized (HttpRequestClient.class) {
                if (instance == null) {
                    instance = new HttpRequestClient();
                }
            }
        }
        return instance;
    }

    public HttpRequestUtils getHttpRequestUtils() {
        if (null == httpRequestUtils) {
            httpRequestUtils = getRetrofit().create(HttpRequestUtils.class);
        }
        return httpRequestUtils;
    }

    private Retrofit getRetrofit() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory
                    (RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                    .addConverterFactory(CustomGsonConverterFactory.create()) // 添加Gson转换器
                    .client(getOkHttpClient()).build();
        }
        return retrofit;
    }

    /**
     * 动态设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    public HttpRequestUtils getHttpRequestUtils(String baseUrl) {
        return getRetrofit(baseUrl).create(HttpRequestUtils.class);
    }

    /**
     * 动态设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(getOkHttpClient()).build();
    }

    /**
     * 获取OkhttpClient,并进行配置
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        LoggingInterceptor logging = new LoggingInterceptor();
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)//连接超时时间
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    /**
     * 发起订阅请求
     *
     * @param observable
     * @param mHttpListener
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> observable, String dataRequestType, final HttpListener<T> mHttpListener) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(T value) {
                        if (null != mHttpListener) {
                            if (null != value) {
                                mHttpListener.onSuccess(dataRequestType, value);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String errorMsg = catchException(e);
                        int errorCode = -1;
                        if (e instanceof ResultException) {
                            ResultException resultException = (ResultException) e;
                            errorCode = resultException.getErrorCode();
                        }
                        if (null != mHttpListener) {
                            mHttpListener.onFailure(dataRequestType, errorMsg, errorCode);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 捕获异常
     *
     * @param e
     */
    private String catchException(Throwable e) {
        Context context = UtilsManager.getContext();
        //请求异常提示
        String errorMsg = "";
        if (e instanceof UnknownHostException) {
            errorMsg = context.getString(R.string.network_connection_faile);
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = context.getString(R.string.network_connection_time_out);
        } else if (e instanceof ConnectException) {
            errorMsg = context.getString(R.string.network_connection_time_out);
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int responseCode = httpException.code();
            if (responseCode >= 400 && responseCode <= 417) {
                errorMsg = context.getString(R.string.common_url_error);
            } else if (responseCode >= 500 && responseCode <= 505) {
                errorMsg = context.getString(R.string.network_connection_busy);
            } else {
                errorMsg = context.getString(R.string.network_connection_exception);
            }
        } else if (e instanceof ResultException) {
            ResultException resultException = (ResultException) e;
            errorMsg = resultException.getErrorMsg();
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof
                ParseException || e instanceof NullPointerException) {
            errorMsg = context.getString(R.string.common_data_exception);
        } else {
            errorMsg = context.getString(R.string.common_unknown_error);
        }
        return errorMsg;
    }
}