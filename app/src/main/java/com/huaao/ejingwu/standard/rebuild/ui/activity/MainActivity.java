package com.huaao.ejingwu.standard.rebuild.ui.activity;

import android.content.Intent;
import android.view.View;

import com.huaao.ejingwu.common.app.AppManager;
import com.huaao.ejingwu.common.base.BaseActivity;
import com.huaao.ejingwu.common.utils.ToastUtils;
import com.huaao.ejingwu.standard.rebuild.R;
import com.huaao.ejingwu.standard.rebuild.databinding.ActivityMainBinding;
import com.huaao.ejingwu.standard.rebuild.ui.viewmodel.MainViewModel;
import com.huaao.ejingwu.tesseractocrlibrary.OCRActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

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
        mBinding.btnWxPay.setOnClickListener((View view) -> {
//            startActivity(new Intent(view.getContext(), WebViewActivity.class));

            startActivity(new Intent(view.getContext(), OCRActivity.class));

//            try {
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setComponent(cmp);
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                // TODO: handle exception
//                ToastUtils.ToastShort("检查到您手机没有安装微信，请安装后使用该功能");
//            }
        });
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
}
