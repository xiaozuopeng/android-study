package com.huaao.ejingwu.common.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author: xzp
 * @date: 2018/6/28
 * @desc: toast工具类
 */

public class ToastUtils {

    private static Toast instanceShort = null;
    private static Toast instanceLong = null;

    private ToastUtils() {
    }

    public static void ToastShort(@NonNull String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }

        if (instanceShort == null) {
            instanceShort = Toast.makeText(UtilsManager.getContext(), content, Toast.LENGTH_SHORT);
        } else {
            instanceShort.setText(content);
        }

        instanceShort.show();
    }

    public static void ToastLong(@NonNull String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }

        if (instanceLong == null) {
            instanceLong = Toast.makeText(UtilsManager.getContext(), content, Toast.LENGTH_LONG);
        } else {
            instanceLong.setText(content);
        }

        instanceLong.show();
    }

    public static void ToastShort(@StringRes int resId) {
        ToastShort(UtilsManager.getContext().getResources().getString(resId));
    }

    public static void ToastLong(@StringRes int resId) {
        ToastLong(UtilsManager.getContext().getResources().getString(resId));
    }

    private static void cancelToast() {
        if (instanceLong != null) {
            instanceLong.cancel();
        }
        if (instanceShort != null) {
            instanceShort.cancel();
        }
    }
}
