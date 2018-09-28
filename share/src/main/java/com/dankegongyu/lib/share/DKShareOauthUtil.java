package com.dankegongyu.lib.share;

import android.app.Activity;
import android.util.Log;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class DKShareOauthUtil {

    /**
     * 微信授权
     */
    public static void weixinOauth(Activity activity, final DKShareOnOauthListener dkShareOnOauthListener) {
        oauth(activity, SHARE_MEDIA.WEIXIN, dkShareOnOauthListener);
    }

    private static void oauth(Activity activity, SHARE_MEDIA share_media, final DKShareOnOauthListener dkShareOnOauthListener) {
        UMShareAPI.get(activity).getPlatformInfo(activity, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                if (dkShareOnOauthListener != null) {
                    dkShareOnOauthListener.onStart(DKShareMediaConverter.getDKShareMedia(share_media));
                }
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int status, Map<String, String> map) {
                if (dkShareOnOauthListener != null) {
                    dkShareOnOauthListener.onComplete(DKShareMediaConverter.getDKShareMedia(share_media), status, map);
                }
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int status, Throwable throwable) {
                if (dkShareOnOauthListener != null) {
                    dkShareOnOauthListener.onError(DKShareMediaConverter.getDKShareMedia(share_media), status, throwable);
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int status) {
                if (dkShareOnOauthListener != null) {
                    dkShareOnOauthListener.onCancel(DKShareMediaConverter.getDKShareMedia(share_media), status);
                }
            }
        });
    }

    /**
     * 取消微信授权（一般退出登录时调用），否则再点微信登录时不会弹出微信选框，不能切换微信
     */
    public static void deleteWeixinOauth(Activity activity) {
        deleteOauth(activity, SHARE_MEDIA.WEIXIN);
    }

    /**
     * 取消第三方授权
     */
    private static void deleteOauth(Activity activity, SHARE_MEDIA share_media) {
        UMShareAPI.get(activity).deleteOauth(activity, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int status, Map<String, String> map) {
                Log.d("取消授权 -> ", status + ", " + map);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int status, Throwable throwable) {
                Log.e("取消授权 -> ", status + ", " + throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int status) {

            }
        });
    }

}
