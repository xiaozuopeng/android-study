package com.huaao.ejingwu.standard.rebuild.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.huaao.ejingwu.common.utils.CommonUtils;
import com.huaao.ejingwu.common.utils.SPUtils;
import com.huaao.ejingwu.standard.rebuild.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

public class WelcomeActivity extends AppCompatActivity {

    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new View(this);
        setContentView(view);

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE, Permission.Group.CAMERA)
                .onGranted(permissions -> {
                    goToMain();
                })
                .onDenied(permissions -> {
                    CommonUtils.goToSetting(WelcomeActivity.this);
                })
                .start();
    }

    private void goToMain() {
        new Handler().postDelayed(() -> {
            //是否启动过
            boolean isStarted = SPUtils.getBoolean("isStarted");
            if (isStarted) {//启动过
                startActivity(new Intent(view.getContext(), MainActivity.class));
            } else {//没启动过
                startActivity(new Intent(view.getContext(), GuideActivity.class));
            }
            finish();
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }, 2000);
    }
}
