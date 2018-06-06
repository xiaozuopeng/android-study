package com.xzp.xiaozuopeng.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xzp.xiaozuopeng.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {

//        AndPermission.with(this)
//                .runtime()
//                .permission(Permission.Group.STORAGE)
//                .onGranted(permissions -> {
//                    // Storage permission are allowed.
//                    Toast.makeText(getApplicationContext(), AppConstant.BASE_FILE_PATH + "crash" + File.separator, Toast.LENGTH_SHORT).show();
//                })
//                .onDenied(permissions -> {
//                    // Storage permission are not allowed.
//                    CommonUtils.goToSetting(MainActivity.this);
//                })
//                .start();
    }
}


