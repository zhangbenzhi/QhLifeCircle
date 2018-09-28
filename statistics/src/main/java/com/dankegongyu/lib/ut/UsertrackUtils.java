package com.dankegongyu.lib.ut;

import android.content.Context;

import com.ta.utdid2.device.UTDevice;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.Map;

/**
 * Created by gulliver on 2017/11/15.
 */

public class UsertrackUtils {

    private static final Object clock=new Object();
    private UsertrackUtils() {
    }

    private static UsertrackUtils instance;

    public static UsertrackUtils getInstance() {
        if (instance == null) {
            synchronized (clock) {
                instance = new UsertrackUtils();
            }
        }
        return instance;
    }

    public void init(Context context, String umengAppkey, String channel, boolean debug) {
        // umeng
        UMConfigure.init(context.getApplicationContext(), umengAppkey, channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setCatchUncaughtExceptions(false);
    }

    public void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    public void onPageStartActivity(String pageName) {
        MobclickAgent.onPageStart(pageName);
    }

    public void onPageEndActivity(String pageName) {
        MobclickAgent.onPageEnd(pageName);
    }

    public void onPageStartFragment(String pageName) {
        MobclickAgent.onPageStart(pageName);
    }

    public void onPageEndFragment(String pageName) {
        MobclickAgent.onPageEnd(pageName);
    }

    /**
     * 控件计数统计
     *
     * @param context
     * @param eventId
     */
    public void onCalculateCount(Context context, String eventId) {
        MobclickAgent.onEvent(context, eventId);
    }

    /**
     * 控件计数统计带参数
     *
     * @param context
     * @param eventId
     * @param map
     */

    public void onCalculateCountWithParams(Context context, String eventId, Map<String, String> map) {
        MobclickAgent.onEvent(context, eventId, map);
    }

    /**
     * 事件计数
     *
     * @param context
     * @param eventId
     * @param map
     * @param value
     */

    public void onCalculateEventCount(Context context, String eventId, Map<String, String> map, int value) {
        MobclickAgent.onEventValue(context, eventId, map, value);
    }

    public String getUtdid(Context context) {
        return UTDevice.getUtdid(context);
    }

    /**
     * 登录账号统计 http://dev.umeng.com/analytics/android-doc/integration#2_4
     */
    public void onProfileSignIn(String ID) {
        MobclickAgent.onProfileSignIn(ID);
    }

    /**
     * 退出登录账号统计
     */
    public void onProfileSignOff() {
        MobclickAgent.onProfileSignOff();
    }
}
