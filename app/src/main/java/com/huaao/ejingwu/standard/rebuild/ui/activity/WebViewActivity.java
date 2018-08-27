package com.huaao.ejingwu.standard.rebuild.ui.activity;


import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.JsonObject;
import com.huaao.ejingwu.common.base.BaseMvvmActivity;
import com.huaao.ejingwu.common.base.BaseViewModel;
import com.huaao.ejingwu.common.http.retrofit.HttpListener;
import com.huaao.ejingwu.standard.rebuild.R;
import com.huaao.ejingwu.standard.rebuild.databinding.ActivityWebViewBinding;

/**
 * @author: xzp
 * @date: 2018/7/23
 * @desc:
 */
public class WebViewActivity extends BaseMvvmActivity<ActivityWebViewBinding, BaseViewModel> implements HttpListener<JsonObject> {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel(this);
    }

    @Override
    protected void init() {
//        HttpRequestClient httpRequestClient = HttpRequestClient.getInstance();
//        Observable<JsonObject> observable = httpRequestClient.getHttpRequestUtils().emsPay("\"4Z1/sck7HmdiyHwB3WPPTmG3gQ5fytMlNTSMc/4y3zl06bTYywX/upIXgUGoRky8WNtUY+BdzjhD/tHqC/a0iOEGJjRFPT1RHf7M+vwtsSplzSniEYmL4NP0hruEjLYS2FFC41gpeWCp3qq4GM+l426kabutosXM1RohqI+WSc8IJ1QV0hUmUDVkzjveGQB8mMADDX1vrlF0/WfdeTj1l/DapYKsXdAH6ZzFGKjeGya37XkmsGu9A5gSGesk8mInb4nash6hbR3VXs3xJ62c7bfGNi2fQ5NVuSZoUpGgQntwwldv5rK2fWfmoqAaBnBMwEDK3EymaYK1eIYoFvqTbQaWrJ+vJz4ZmKsaZuy+nh3IrWu5MuVk6It+ihEiKeGmV48SxgDa0Jxzw2AEZ879I1wrKZs7+AlAkheBQahGTLwkPC6bVN6UqtAraiz2hNgksWcdRPSHR80WdkF6t5eituQU3PDES5LFZxj1QSLtHTM=\"");
//        httpRequestClient.toSubscribe(observable, DataRequestType.REQUEST_LOGIN,this);

        WebSettings webSettings = mBinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        String ua = mBinding.webView.getSettings().getUserAgentString();//获取当前网址的UserAgent
//        if (!ua.contains("MicroMessenger")) {
//            mBinding.webView.getSettings().setUserAgentString(ua+" MicroMessenger");
//        }

        mBinding.webView.loadUrl("file:///android_asset/wxPay.html");
        mBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("aaaaaaaaaaaaaaaa",url);
//                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
//                if(url.startsWith("weixin://")) {
//
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//
//                    return true;
//                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 重写此方法可以让webview处理https请求
                handler.proceed();
            }
        });
    }

    @Override
    public void onSuccess(String dataRequestType, JsonObject jsonObject) {

    }

    @Override
    public void onFailure(String dataRequestType, String errorMsg, int errorCode) {

    }
}
