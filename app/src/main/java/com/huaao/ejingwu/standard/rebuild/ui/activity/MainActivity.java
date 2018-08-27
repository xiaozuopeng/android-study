package com.huaao.ejingwu.standard.rebuild.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import com.huaao.ejingwu.common.app.AppManager;
import com.huaao.ejingwu.common.base.BaseMvvmActivity;
import com.huaao.ejingwu.common.utils.GlideUtils;
import com.huaao.ejingwu.common.utils.ToastUtils;
import com.huaao.ejingwu.standard.rebuild.R;
import com.huaao.ejingwu.standard.rebuild.databinding.ActivityMainBinding;
import com.huaao.ejingwu.standard.rebuild.ui.viewmodel.MainViewModel;
import com.huaao.ejingwu.tesseractocrlibrary.OCRActivity;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> implements View.OnClickListener {

    private long exitTime;

    @Override
    public MainViewModel initViewModel() {
        return new MainViewModel(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        GlideUtils.loadGifImage(this, R.drawable.gif_image, mBinding.iv1);
        GlideUtils.loadCircleImage(this, R.drawable.guide_two, mBinding.iv2);
        GlideUtils.loadRoundedCornerImage(this, R.drawable.guide_three, 20, mBinding.iv3);
        GlideUtils.loadBlurImage(this, R.drawable.guide_four, 25L, mBinding.iv4);

        mBinding.btnMap.setOnClickListener(this);
        mBinding.btnWebView.setOnClickListener(this);
        mBinding.btnWxPay.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 1000) {
            ToastUtils.ToastShort("再点一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_map:
                startActivity(new Intent(v.getContext(), MapViewActivity.class));
                break;
            case R.id.btn_web_view:
                startActivity(new Intent(v.getContext(), WebViewActivity.class));
                break;
            case R.id.btn_ocr:
                startActivity(new Intent(v.getContext(), OCRActivity.class));
                break;
            case R.id.btn_wx_pay:
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // TODO: handle exception
                    ToastUtils.ToastShort("检查到您手机没有安装微信，请安装后使用该功能");
                }
                break;
            default:
                break;
        }
    }
}
