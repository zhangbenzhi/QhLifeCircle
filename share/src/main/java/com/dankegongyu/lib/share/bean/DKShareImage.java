package com.dankegongyu.lib.share.bean;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.dankegongyu.lib.share.DKShareButton;
import com.dankegongyu.lib.share.DKShareCallback;
import com.dankegongyu.lib.share.DKShareInterceptor;
import com.dankegongyu.lib.share.DKShareUtil;
import com.dankegongyu.lib.share.DK_SHARE_MEDIA;

import java.io.File;
import java.util.Arrays;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/3/12 17:58
 */
public class DKShareImage extends DKShareObject<DKShareImage> {

    private String imageUrl; //网络图片
    private int imageRes; //资源文件
    private Bitmap bitmap; //bitmap文件
    private File file; //本地文件
    private byte[] bytes; //字节流

    private DKShareImage(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public static DKShareImage with(@NonNull FragmentActivity activity) {
        return new DKShareImage(activity);
    }

    @Override
    public DKShareImage setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public DKShareImage setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    @Override
    public DKShareImage setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
        return this;
    }

    @Override
    public DKShareImage setShareContent(String shareContent) {
        this.shareContent = shareContent;
        return this;
    }

    @Override
    public DKShareImage setPlatforms(DK_SHARE_MEDIA... platforms) {
        this.platforms = Arrays.asList(platforms);
        return this;
    }

    @Override
    public DKShareImage setCustomPlatforms(DKShareButton... customPlatforms) {
        this.customPlatforms = Arrays.asList(customPlatforms);
        return this;
    }

    @Override
    public DKShareImage setInterceptor(DKShareInterceptor<DKShareImage> shareInterceptor) {
        this.shareInterceptor = shareInterceptor;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 网络图片
     *
     * @param imageUrl 图片地址
     */
    public DKShareImage setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getImageRes() {
        return imageRes;
    }

    /**
     * 资源文件
     *
     * @param imageRes 本地drawable文件
     */
    public DKShareImage setImageRes(int imageRes) {
        this.imageRes = imageRes;
        return this;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * bitmap文件
     *
     * @param bitmap bitmap图片
     */
    public DKShareImage setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public File getFile() {
        return file;
    }

    /**
     * 本地文件
     *
     * @param file 本地图片文件
     */
    public DKShareImage setFile(File file) {
        this.file = file;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    /**
     * 字节流
     *
     * @param bytes 字节流
     */
    public DKShareImage setBytes(byte[] bytes) {
        this.bytes = bytes;
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
        return DKMediaType.IMAGE;
    }

}
