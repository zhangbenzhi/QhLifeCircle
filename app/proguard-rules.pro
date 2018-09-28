# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\DanKe\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interfaces
# class:
#-keepclassmembers class fqcn.of.javascript.interfaces.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#--------------------------------------- 定制化区域 start----------------------------------------

#-------------------------- 某些地方报错，不知原因的情况下，可先保证不被混淆 start -------------
# 添加

#-------------------------- 某些地方报错，不知原因，可先保证不被混淆 end -------------


#-------------------------- 0.混淆 & 实体类 start ---------------------------

# 添加BindEventBus注解的类不混淆
-keep @com.dankegongyu.lib.common.annotation.BindEventBus class * {*;}

# tinker
-keep class * extends com.tencent.tinker.loader.app.DefaultApplicationLike {*;}

#-------------------------- 0.混淆 & 实体类 end -----------------------------


#-------------------------- 1.引用项目 library module start --------------------------

#数盟统计
-keep class cn.shuzilm.core.Main {public *;}
-keep class cn.shuzilm.core.Listener {public *;}
-keepclasseswithmembernames class cn.shuzilm.core.Main {native <methods>;}

# FaceID
#-keep public class com.megvii.**{*;}
-dontwarn com.moor.imkf.**
-keep class com.moor.imkf.** {*;}
-dontwarn javax.servlet.**
-dontwarn org.jboss.marshalling.**
-dontwarn org.osgi.**
-dontwarn java.net.**
-dontwarn java.nio.channels.**
-dontwarn org.apache.commons.**
-dontwarn org.apache.log4j.**
-dontwarn org.jboss.logging.**
-dontwarn org.slf4j.**
-dontwarn org.jboss.netty.**
-keep class org.jboss.netty.channel.socket.http.HttpTunnelingServlet {*;}
-keep class com.j256.ormlite.** { *; }

#佳信相关
-dontwarn org.jxmpp.**
-dontwarn com.jxccp.**
-dontwarn de.measite.minidns.**
-dontwarn org.jivesoftware.smack.**
-dontwarn org.jivesoftware.smackx.**
# Keep the support library
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
# Keep the support library
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-keep class com.alibaba.sdk.android.** {*;}
-keep class de.measite.minidns.** {*;}
-keep class org.jivesoftware.smack.** {*;}
-keep class org.jivesoftware.smackx.** {*;}
-keep class org.jxmpp.** {*;}
-keep class com.jxccp.** {*;}
-keep class com.dankegongyu.lib.im.** {*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#--------------------------weex start --------------------------------
-keep class com.taobao.weex.bridge.**{*;}
-keep class com.taobao.weex.dom.**{*;}
-keep class com.taobao.weex.adapter.**{*;}
-keep class com.taobao.weex.common.**{*;}
-keep class * implements com.taobao.weex.IWXObject{*;}
-keep class com.taobao.weex.ui.**{*;}
-keep class com.taobao.weex.ui.component.**{*;}
-keep class com.taobao.weex.utils.**{
    public <fields>;
    public <methods>;
    }
-keep class com.taobao.weex.view.**{*;}
-keep class com.taobao.weex.module.**{*;}
-keep public class * extends com.taobao.weex.common.WXModule{*;}
-keep public class * extends com.taobao.weex.ui.component.WXComponent{*;}
-keep class * implements com.taobao.weex.ui.IExternalComponentGetter{*;}
#--------------------------weex end --------------------------------

#-------------------------- 1.引用项目 library module end --------------------------


#-------------------------- 2.第三方包 start --------------------------

# Butterknife  https://github.com/JakeWharton/butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# OkHttp & Retrofit  https://github.com/square/retrofit
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn retrofit2.**

# RxJava & RxAndroid  https://github.com/ReactiveX/RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

# RxLifecycle  https://github.com/trello/RxLifecycle
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }


# Gson  https://github.com/google/gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# EventBus  https://github.com/greenrobot/EventBus
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
# Only required if you use AsyncExecutor
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}

# Fresco  https://github.com/facebook/fresco
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**

# Glide https://github.com/bumptech/glide
-dontwarn com.bumptech.glide.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# Picasso https://github.com/square/picasso
-dontwarn com.squareup.picasso.**
-dontwarn com.parse.**
-keep class com.parse.*{ *; }
-keepclasseswithmembernames class * {
    native <methods>;
}

# FlycoTabLayout_Lib  https://github.com/H07000223/FlycoTabLayout
-dontwarn com.flyco.tablayout.**
-keep class com.flyco.tablayout.**{*;}

# FlycoBanner_Master  https://github.com/H07000223/FlycoBanner_Master
-dontwarn com.flyco.banner.**
-keep class com.flyco.banner.**{*;}

# joda-time  https://github.com/JodaOrg/joda-time
-dontwarn org.joda.time.*
-dontwarn org.joda.convert.*
-keep class org.joda.time.** {*;}

# logger  https://github.com/orhanobut/logger
-dontwarn com.orhanobut.logger.*
-keep class com.orhanobut.logger.*{*;}

# RecyclerViewDivider https://github.com/Fondesa/RecyclerViewDivider
-dontwarn com.fondesa.recyclerviewdivider.*
-keep class com.fondesa.recyclerviewdivider.**{*;}

# 百度地图 http://lbsyun.baidu.com/index.php?title=androidsdk/guide/create-project/androidstudio
-dontwarn com.baidu.**
-keep class com.baidu.** {*;}
-keep class mapsdkvi.com.** {*;}

# FlowLayout https://github.com/hongyangAndroid/FlowLayout
-dontwarn com.zhy.view.flowlayout.**
-keep class com.zhy.view.flowlayout.** {*;}

# SectionedRecyclerViewAdapter https://github.com/luizgrp/SectionedRecyclerViewAdapter
-dontwarn io.github.luizgrp.sectionedrecyclerviewadapter.**
-keep class io.github.luizgrp.sectionedrecyclerviewadapter.** {*;}

# https://github.com/tbruyelle/RxPermissions
-dontwarn com.tbruyelle.rxpermissions.**
-keep class com.tbruyelle.rxpermissions.** {*;}

# https://github.com/Bigkoo/Android-PickerView
-dontwarn com.bigkoo.pickerview.**
-keep class com.bigkoo.pickerview.** {*;}

# https://github.com/ongakuer/PhotoDraweeView
-dontwarn me.relex.photodraweeview.**
-keep class me.relex.photodraweeview.** {*;}

# 七牛
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}

# bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# 友盟 http://dev.umeng.com/social/android/quick-integration#1_3_9
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep class com.umeng.commonsdk.** {*;}
-keep public class com.umeng.socialize.* {*;}
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
# https://developer.umeng.com/docs/66750/detail/66816
-keep class * extends com.umeng.socialize.net.base.SocializeReseponse {*;}


# ping++ https://www.pingxx.com/docs/client/sdk/android?sdk=true
# Ping++ 混淆过滤
-dontwarn com.pingplusplus.**
-keep class com.pingplusplus.** {*;}
# 支付宝混淆过滤
-dontwarn com.alipay.**
-keep class com.alipay.** {*;}
# 微信或QQ钱包混淆过滤
-dontwarn  com.tencent.**
-keep class com.tencent.** {*;}

# https://github.com/baoyongzhang/ParcelableGenerator
-dontwarn com.baoyz.pg.**
-keep class com.baoyz.pg.** {*;}
# 添加该注解的类、属性或方法都不会被混淆
-keep @com.baoyz.pg.Parcelable class * {*;}

# https://github.com/alibaba/ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * extends com.alibaba.android.arouter.facade.template.IProvider
# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider


# 神策 start
-dontwarn com.sensorsdata.analytics.android.**
-keep class com.sensorsdata.analytics.android.** {
*;
}
-keep class **.R$* {
    <fields>;
}
-keepnames class * implements android.view.View$OnClickListener
-keep public class * extends android.content.ContentProvider
-keepnames class * extends android.view.View

-keep class * extends android.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}
-keep class android.support.v4.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}
-keep class * extends android.support.v4.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}

# 如果使用了 DataBinding
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
-keep class com.dankegongyu.customer.databinding.** {
    <fields>;
    <methods>;
}
# 神策 end

#个推
-dontwarn com.igexin.**
-keep class com.igexin.** { *; }
-keep class org.json.** { *; }

#魅族
-keep class com.meizu.** { *; }
-dontwarn com.meizu.**
#小米
-keep class com.xiaomi.** { *; }
-dontwarn com.xiaomi.push.**
-keep class org.apache.thrift.** { *; }
#华为
-keep class com.huawei.hms.** { *; }
-dontwarn com.huawei.hms.**

#-------------------------- 2.第三方包 end --------------------------------


#-------------------------- 3.js WebView相关 start ------------------------
# refs : https://www.zhihu.com/question/31316646

-dontwarn android.webkit.WebView
-keep public class android.webkit.**

-keepattributes *JavascriptInterface*
-keep class android.webkit.JavascriptInterface {*;}

-keepclassmembers class * extends android.webkit.WebChromClient {
    public void openFileChooser(...);
}

-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}

#-------------------------- 3.js WebView相关 end ------------------------


#-------------------------- 4.反射相关的类和方法 start ----------------------

#-dontwarn com.jaydenxiao.common.commonutils.**
#-keep class com.jaydenxiao.common.commonutils.** { *; }

#-------------------------- 4.反射相关的类和方法 end ------------------------


#--------------------------------------- 基本不用动区域 start----------------------------------------

#----------------------------- 基本指令区 start ------------------------------

#-dontoptimize # 开启这个下面的配置无效
# 指定混淆时采用的算法，后面的参数是一个过滤器，这个过滤器是谷歌推荐的算法，一般不改变, https://blog.csdn.net/adark0915/article/details/55045453
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*,!code/allocation/variable
# 代码混淆压缩比，在0和7之间，默认为5，一般不需要改
-optimizationpasses 5

# 混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类和类的成员
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify

# 有了verbose这句话，混淆后就会生成映射文件，包含有类名->混淆后类名的映射关系，然后使用printmapping指定映射文件的名称
-verbose
-printmapping proguardMapping.txt

# 保护代码中的Annotation不被混淆，这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*

# 避免混淆泛型，这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature

# 抛出异常时保留代码行号
-keepattributes SourceFile
-keepattributes LineNumberTable

# 避免混淆内部类、匿名类
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# 异常
-keepattributes Exceptions

# 其他
-ignorewarnings
#-dontshrink

#----------------------------- 基本指令区 end ------------------------------


#----------------------------- 默认保留区 start ------------------------------

# 保留了继承自Activity、Application这些类的子类，因为这些子类，都有可能被外部调用
# 保留四大组件，自定义的Application等这些类不被混淆
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Fragment
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.backup.BackupAgentHelper
-keep class android.support.** {*;}
-keep class android.support.v4.app.NotificationCompat { *; }
-keep class android.support.v4.app.NotificationCompat$Builder { *; }

# 保留在Activity中的方法参数是view的方法，从而我们在layout里面编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void * (android.view.View);
}

# 保持枚举enum类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持自定义控件不被混淆
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

# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keepclasseswithmembers class * implements android.os.Parcelable {*;}

# 保留Serializable序列化类不被混淆
-keep class * implements java.io.Serializable {*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 对于R（资源）下的所有类及其方法，都不能被混淆
-keep class **.R$* {
    *;
}

# 对于带有回调函数XXEvent的，不能被混淆
-keepclassmembers class * {
    void * (**Event);
}

# 保留本地native方法不被混淆
-keepclassmembers class * {
    native <methods>;
}
# 保留R下面的资源
-keep class **.R$* {
    *;
}

# 其他
-dontwarn android.support.v4.**
-keep public class javax.**
-keep public class android.support.v4.content.FileProvider {*;}

# keep
-dontwarn android.support.annotation.Keep
-keep @android.support.annotation.Keep class * {*;}

# 去掉Log日志
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
}

#----------------------------- 默认保留区 end ------------------------------

#--------------------------------------- 基本不用动区域 end----------------------------------------