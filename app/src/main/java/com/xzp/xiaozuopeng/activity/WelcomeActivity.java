package com.xzp.xiaozuopeng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.xzp.xiaozuopeng.base.BaseActivity;
import com.xzp.xiaozuopeng.constant.AppConstant;
import com.xzp.xiaozuopeng.utils.CommonUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;

public class WelcomeActivity extends BaseActivity {

    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new View(this);
        setContentView(view);

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                    Toast.makeText(getApplicationContext(), AppConstant.BASE_FILE_PATH + "crash" + File.separator, Toast.LENGTH_SHORT).show();
                    goToMain();
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                    CommonUtils.goToSetting(WelcomeActivity.this);
                })
                .start();

//        goToMain();
    }

    private void goToMain() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }
}
