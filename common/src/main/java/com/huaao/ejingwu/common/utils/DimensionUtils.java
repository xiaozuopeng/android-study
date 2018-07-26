package com.huaao.ejingwu.common.utils;

import android.util.TypedValue;

/**
 * @author: xzp
 * @date: 2018/7/3
 * @desc:
 */
public class DimensionUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = UtilsManager.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = UtilsManager.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2Px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, UtilsManager.getContext()
                .getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     */
    public static int px2Sp(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, UtilsManager.getContext()
                .getResources().getDisplayMetrics());
    }
}
