package com.huaao.ejingwu.standard.rebuild.ui.activity;


import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.huaao.ejingwu.common.base.BaseActivity;
import com.huaao.ejingwu.common.base.BaseViewModel;
import com.huaao.ejingwu.standard.rebuild.R;
import com.huaao.ejingwu.standard.rebuild.databinding.ActivityWebViewBinding;

/**
 * @author: xzp
 * @date: 2018/7/23
 * @desc:
 */
public class WebViewActivity extends BaseActivity<ActivityWebViewBinding, BaseViewModel> {
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
        mBinding.webView.loadUrl("file:///android_asset/wxPay.html");
        mBinding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
