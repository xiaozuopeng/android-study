package com.xzp.xiaozuopeng.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xzp.xiaozuopeng.R;
import com.xzp.xiaozuopeng.constant.AppConstant;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {
        Toast.makeText(getApplicationContext(), AppConstant.BASE_FILE_PATH + "crash" + File.separator, Toast.LENGTH_SHORT).show();
    }
}


