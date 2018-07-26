package com.huaao.ejingwu.common.base;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author: xzp
 * @date: 2018/6/27
 * @desc: 避免内存泄漏
 */
public class BaseHandler<T extends BaseHandler.BaseHandlerCallBack> extends Handler {

    private WeakReference<T> wr;

    public BaseHandler(T t) {
        wr = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T t = wr.get();
        if (t != null) {
            t.callBack(msg);
        }
    }

    public interface BaseHandlerCallBack {
        void callBack(Message msg);
    }
}
