package com.huaao.ejingwu.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author: xzp
 * @date: 2018/6/27
 * @desc:
 */
public final class UtilsManager {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private UtilsManager() {

    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        UtilsManager.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("utils should be initialized in application");
    }
}
