package com.huaao.ejingwu.common.utils;

import android.util.Log;

import com.huaao.ejingwu.common.BuildConfig;

/**
 * @author: xzp
 * @date: 2018/8/2
 * @desc:
 */
public class LogUtils {
    private static boolean ENABLE_LOG = BuildConfig.DEBUG;

    public LogUtils() {
    }

    public static final void enableDebugMode(boolean enabled) {
        ENABLE_LOG = enabled;
    }

    public static final void d(String tag, String msg) {
        if(ENABLE_LOG) {
            Log.d(tag, msg);
        }

    }

    public static final void d(String tag, String msg, Throwable tr) {
        if(ENABLE_LOG) {
            Log.d(tag, msg, tr);
        }

    }

    public static final void i(String tag, String msg) {
        if(ENABLE_LOG) {
            Log.i(tag, msg);
        }

    }

    public static final void i(String tag, String msg, Throwable tr) {
        if(ENABLE_LOG) {
            Log.i(tag, msg, tr);
        }

    }

    public static final void w(String tag, String msg) {
        if(ENABLE_LOG) {
            Log.w(tag, msg);
        }

    }

    public static final void w(String tag, Throwable tr) {
        if(ENABLE_LOG) {
            Log.w(tag, tr);
        }

    }

    public static final void w(String tag, String msg, Throwable tr) {
        if(ENABLE_LOG) {
            Log.w(tag, msg, tr);
        }

    }

    public static final void e(String tag, String msg) {
        if(ENABLE_LOG) {
            Log.e(tag, msg);
        }

    }

    public static final void e(String tag, String msg, Throwable tr) {
        if(ENABLE_LOG) {
            Log.e(tag, msg, tr);
        }

    }

    public static final void p(Object obj) {
        if(ENABLE_LOG) {
            System.out.println(obj);
        }

    }

    public static String getTraceInfo() {
        if(!ENABLE_LOG) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            StackTraceElement[] stacks = (new Throwable()).getStackTrace();
            sb.append("[file:").append(stacks[1].getFileName()).append(",line:").append(stacks[1].getLineNumber()).append(",method:").append(stacks[1].getMethodName() + "];");
            return sb.toString();
        }
    }
}
