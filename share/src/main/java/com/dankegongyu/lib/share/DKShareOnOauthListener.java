package com.dankegongyu.lib.share;

import java.util.Map;

/**
 * 授权监听
 */
public interface DKShareOnOauthListener {

    void onStart(DK_SHARE_MEDIA dk_share_media);

    void onComplete(DK_SHARE_MEDIA dk_share_media, int status, Map<String, String> map);

    void onError(DK_SHARE_MEDIA dk_share_media, int status, Throwable throwable);

    void onCancel(DK_SHARE_MEDIA dk_share_media, int status);
}
