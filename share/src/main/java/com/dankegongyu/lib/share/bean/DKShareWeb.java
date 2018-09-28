package com.dankegongyu.lib.share.bean;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.dankegongyu.lib.share.DKShareButton;
import com.dankegongyu.lib.share.DKShareCallback;
import com.dankegongyu.lib.share.DKShareInterceptor;
import com.dankegongyu.lib.share.DKShareUtil;
import com.dankegongyu.lib.share.DK_SHARE_MEDIA;

import java.util.Arrays;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/3/12 17:33
 */
public class DKShareWeb extends DKShareObject<DKShareWeb> {

    private String shareUrl;
    private String shareImageUrl;
    private Bitmap shareImage;
    private int shareImageIcon;
    private DKShareMiniProgram miniProgram;

    private DKShareWeb(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public static DKShareWeb with(@NonNull FragmentActivity activity) {
        return new DKShareWeb(activity);
    }

    @Override
    public DKShareWeb setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public DKShareWeb setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    @Override
    public DKShareWeb setPlatforms(DK_SHARE_MEDIA... platforms) {
        this.platforms = Arrays.asList(platforms);
        return this;
    }

    @Override
    public DKShareWeb setCustomPlatforms(DKShareButton... customPlatforms) {
        this.customPlatforms = Arrays.asList(customPlatforms);
        return this;
    }

    @Override
    public DKShareWeb setInterceptor(DKShareInterceptor<DKShareWeb> shareInterceptor) {
        this.shareInterceptor = shareInterceptor;
        return this;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public DKShareWeb setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
        return this;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    @Override
    public DKShareWeb setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
        return this;
    }

    public String getShareContent() {
        return shareContent;
    }

    @Override
    public DKShareWeb setShareContent(String shareContent) {
        this.shareContent = shareContent;
        return this;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public DKShareWeb setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
        return this;
    }

    public Bitmap getShareImage() {
        return shareImage;
    }

    public DKShareWeb setShareImage(Bitmap shareImage) {
        this.shareImage = shareImage;
        return this;
    }

    public int getShareImageIcon() {
        return shareImageIcon;
    }

    public DKShareWeb setShareImageIcon(int shareImageIcon) {
        this.shareImageIcon = shareImageIcon;
        return this;
    }

    @Override
    public void show() {
        DKShareUtil.shareDefault(this, null);
    }

    @Override
    public void show(DKShareCallback shareListener) {
        DKShareUtil.shareDefault(this, shareListener);
    }

    @Override
    public DKMediaType getShareMedia() {
        return DKMediaType.WEB;
    }

    public DKShareMiniProgram getMiniProgram() {
        return miniProgram;
    }

    /**
     * 小程序
     *
     * @param miniProgram {@link DKShareMiniProgram}
     */
    public DKShareWeb setMiniProgram(DKShareMiniProgram miniProgram) {
        this.miniProgram = miniProgram;
        return this;
    }

}
