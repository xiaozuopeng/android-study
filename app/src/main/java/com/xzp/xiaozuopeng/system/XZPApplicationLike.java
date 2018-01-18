package com.xzp.xiaozuopeng.system;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.xzp.xiaozuopeng.BuildConfig;
import com.xzp.xiaozuopeng.constant.AppConstant;

import java.io.File;

/**
 * @author: xzp
 * @date: 2018/1/18
 * @desc: 程序入口
 * <p>
 * 注意：tinker需要你开启MultiDex,你需要在dependencies中进行配置compile "com.android.support:multidex:1.0.1"才可以使用MultiDex.install方法；
 * SampleApplicationLike这个类是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里，在onCreate方法调用SDK的初始化方法，
 * 在onBaseContextAttached中调用Beta.installTinker(this);。
 */
public class XZPApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker.XZPApplicationLike";
    private String crashLogPath = AppConstant.BASE_FILE_PATH + "crash" + File.separator;

    public XZPApplicationLike(Application application, int tinkerFlags,
                              boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                              long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (isApplicationProcess()) {//判断是否为主进程，防止其他进程重复初始化第三方sdk
            String buglyId = "";
            try {
                ApplicationInfo appInfo = getAppContext().getPackageManager()
                        .getApplicationInfo(getAppContext().getPackageName(), PackageManager.GET_META_DATA);
                buglyId = appInfo.metaData.getString("BUGLY_APP_ID");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
            // 调试时，将第三个参数改为true
            Bugly.init(getApplication(), buglyId, BuildConfig.isDebug);

            CrashHandler.getInstance().init(getAppContext(), crashLogPath);
//            Beta.autoCheckUpgrade = false;//自动更新关闭
//            Beta.autoDownloadOnWifi = true;//WiFi下自动下载
            Beta.initDelay = 6 * 1000;//bugly延迟初始化
        }
    }

    /**
     * 获取应用主进程的context
     */
    private Context getAppContext() {
        return getApplication().getBaseContext();
    }

    /**
     * 判断是否为主进程
     */
    private boolean isApplicationProcess() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getAppContext().getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName.equals(BuildConfig.APPLICATION_ID);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context context) {
        super.onBaseContextAttached(context);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(context);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}