# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\worksoftware\AndroidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:

#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------
#-keep class com.huaao.ejingwu.standard.bean.** { *; }
#-keepattributes EnclosingMethod
##数据存储类
#-keep class com.huaao.ejingwu.standard.system.UserInfoHelper
##常量类
#-keep class com.huaao.ejingwu.standard.constans.Constants
##工具包
#-keep class com.huaao.ejingwu.standard.utils.** { *; }
##自定义控件包
#-keep class com.huaao.ejingwu.standard.widget.** { *; }

#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------
#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------

# -keep class com.huaao.ejingwu.standard.html.** { *; }

#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------


#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------

#---------------------------------基本指令区----------------------------------
# 指定代码的压缩级别
-optimizationpasses 5
# 是否使用大小写混合
-dontusemixedcaseclassnames
# 是否混淆第三方jar
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
# 混淆时是否做预校验
-dontpreverify
# 混淆时是否记录日志
-verbose
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-printmapping proguardMapping.txt
#保护注解、内部类
-keepattributes *Annotation*,InnerClasses
#避免混淆泛型 如果混淆报错建议关掉
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#不优化输入的类文件，（如果没有使用该语句，说明要优化，就要指定相关的优化语句,如以下两条语句）
-dontoptimize
#忽略警告
-ignorewarning
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity                               # 保持哪些类不被混淆
-keep public class * extends android.app.Application                            # 保持哪些类不被混淆
-keep public class * extends android.app.Service                                # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver                  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider                    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper               # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference                      # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService              # 保持哪些类不被混淆
-keep public class * extends android.view.View
-keep public class * extends android.support.**   #如果有引用v4、v7包可以添加下面这行
# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持自定义控件类不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 保持 Serializable 不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
     void *(**On*Event);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
    *;
}

#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#确保openFileChooser方法不被混淆
-keepclassmembers class * extends android.webkit.WebChromeClient{
    public void openFileChooser(...);
 }
#----------------------------------------------------------------------------

