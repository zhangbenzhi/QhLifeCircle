package com.dankegongyu.lib.share.bean;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.dankegongyu.lib.share.DKShareButton;
import com.dankegongyu.lib.share.DKShareCallback;
import com.dankegongyu.lib.share.DKShareInterceptor;
import com.dankegongyu.lib.share.DK_SHARE_MEDIA;

import java.util.List;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/3/12 17:24
 */
public abstract class DKShareObject<T> {

    FragmentActivity activity;
    String title;
    String subTitle;
    String shareTitle;
    String shareContent;
    List<DK_SHARE_MEDIA> platforms;
    List<DKShareButton> customPlatforms;
    DKShareInterceptor<T> shareInterceptor;

    DKShareObject(@NonNull FragmentActivity activity) {
        this.activity = activity;
    }

    /**
     * 分享面板标题
     *
     * @param title 如：分享评价
     */
    public abstract T setTitle(String title);

    /**
     * 分享面板副标题
     *
     * @param subTitle 如：分享到 | 蛋壳不错，分享一波！
     */
    public abstract T setSubTitle(String subTitle);

    /**
     * 分享标题
     *
     * @param shareTitle 如：蛋壳公寓
     */
    public abstract T setShareTitle(String shareTitle);

    /**
     * 分享内容
     *
     * @param shareContent 住蛋壳，有所居有好居
     */
    public abstract T setShareContent(String shareContent);

    /**
     * 分享平台
     */
    public abstract T setPlatforms(DK_SHARE_MEDIA... platforms);

    /**
     * 自定义分享平台
     *
     * @param customShareButtons {@link DKShareButton}
     */
    public abstract T setCustomPlatforms(DKShareButton... customShareButtons);

    /**
     * 分享拦截，主要针对新浪微博，需要在分享内容后面加@XXX
     *
     * @param shareInterceptor 分享拦截器{@link DKShareInterceptor}
     */
    public abstract T setInterceptor(DKShareInterceptor<T> shareInterceptor);

    /**
     * 调起分享面板
     */
    public abstract void show();

    /**
     * 调起分享面板
     *
     * @param shareListener 分享回调{@link DKShareCallback}
     */
    public abstract void show(DKShareCallback shareListener);

    public FragmentActivity getActivity() {
        return activity;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public List<DK_SHARE_MEDIA> getPlatforms() {
        return platforms;
    }

    public List<DKShareButton> getCustomPlatforms() {
        return customPlatforms;
    }

    public DKShareInterceptor<T> getShareInterceptor() {
        return shareInterceptor;
    }

    public abstract DKMediaType getShareMedia();

    public enum DKMediaType {
        IMAGE {
            public String toString() {
                return "0";
            }
        },
        WEB {
            public String toString() {
                return "1";
            }
        };

        private DKMediaType() {
        }
    }
}

