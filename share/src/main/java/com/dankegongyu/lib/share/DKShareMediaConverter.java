package com.dankegongyu.lib.share;

import com.umeng.socialize.bean.SHARE_MEDIA;

public class DKShareMediaConverter {

    /**
     * 由 {@link SHARE_MEDIA} 映射到 {@link DK_SHARE_MEDIA}
     *
     * @param share_media {@link SHARE_MEDIA}
     * @return {@link DK_SHARE_MEDIA}
     */
    public static DK_SHARE_MEDIA getDKShareMedia(SHARE_MEDIA share_media) {
        if (share_media == null) {
            return null;
        }
        switch (share_media) {
            case WEIXIN:
                return DK_SHARE_MEDIA.WEIXIN;
            case WEIXIN_CIRCLE:
                return DK_SHARE_MEDIA.WEIXIN_CIRCLE;
        }
        return null;
    }

    /**
     * 由 {@link DK_SHARE_MEDIA} 映射到 {@link SHARE_MEDIA}
     *
     * @param dk_share_media {@link DK_SHARE_MEDIA}
     * @return {@link SHARE_MEDIA}
     */
    public static SHARE_MEDIA getShareMedia(DK_SHARE_MEDIA dk_share_media) {
        if (dk_share_media == null) {
            return null;
        }
        switch (dk_share_media) {
            case WEIXIN:
                return SHARE_MEDIA.WEIXIN;
            case WEIXIN_CIRCLE:
                return SHARE_MEDIA.WEIXIN_CIRCLE;
        }
        return null;
    }

}
