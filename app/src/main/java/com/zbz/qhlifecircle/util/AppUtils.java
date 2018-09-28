package com.zbz.qhlifecircle.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import com.zbz.qhlifecircle.app.MyAppliaction;

/**
 * Created by Admin on 2017/11/16.
 */

public class AppUtils {

    /**
     * 获取App版本码
     *
     * @return App版本码
     */
    public static int getAppVersionCode() {
        return getAppVersionCode(MyAppliaction.instantce.getPackageName());
    }

    /**
     * 获取App版本码
     *
     * @param packageName 包名
     * @return App版本码
     */
    public static int getAppVersionCode(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return -1;
        try {
            PackageManager pm = MyAppliaction.instantce.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取App包名
     *
     * @return App包名
     */
    public static String getAppPackageName() {
        return MyAppliaction.instantce.getPackageName();
    }

    /**
     * 获取App版本号
     *
     * @return App版本号
     */
    public static String getAppVersionName() {
        return getAppVersionName(MyAppliaction.instantce.getPackageName());
    }

    /**
     * 获取App版本号
     *
     * @param packageName 包名
     * @return App版本号
     */
    public static String getAppVersionName(final String packageName) {
        if (TextUtils.isEmpty(packageName)) return null;
        try {
            PackageManager pm = MyAppliaction.instantce.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断App是否安装
     *
     * @param packageName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isInstallApp(String packageName) {
        return !TextUtils.isEmpty(packageName) && getLaunchAppIntent(packageName) != null;
    }

    /**
     * 获取打开App的意图
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(final String packageName) {
        return MyAppliaction.instantce.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 根据包名启动App
     * @param context 上下文
     * @param packageName 包名
     * @param uri 数据
     */
    public static void launchApp(Context context, String packageName, Uri uri) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            //noinspection ConstantConditions
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Manifest中meta-data信息
     */
    public static String getMetaDataValue(Context context, String metaDataName) {
        if (context == null || TextUtils.isEmpty(metaDataName)) {
            return "";
        }
        String resultData = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager
                        .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    resultData = String.valueOf(applicationInfo.metaData.get(metaDataName));
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }
}
