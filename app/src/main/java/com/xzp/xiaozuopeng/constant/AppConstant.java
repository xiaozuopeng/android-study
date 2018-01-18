package com.xzp.xiaozuopeng.constant;

import android.os.Environment;

import com.xzp.xiaozuopeng.BuildConfig;

import java.io.File;

/**
 * @author: xzp
 * @date: 2018/1/18
 * @desc:
 */
public class AppConstant {

    //文件保存路径
    public static final String BASE_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + BuildConfig.APPLICATION_ID + File.separator;
}
