package com.dankegongyu.lib.share.bean;

import android.graphics.Bitmap;

/**
 * 微信小程序
 *
 * @author wupuquan
 * @version 1.0
 * @since 2018/8/1 10:59
 */
public class DKShareMiniProgram {

    private String shareTitle;
    private String shareContent;
    private String shareUrl;
    private Bitmap shareImage;
    private String shareImageUrl;
    private int shareImageIcon;
    private String path;
    private String userName;

    private DKShareMiniProgram(Builder builder) {
        this.shareTitle = builder.shareTitle;
        this.shareContent = builder.shareContent;
        this.shareUrl = builder.shareUrl;
        this.shareImageUrl = builder.shareImageUrl;
        this.shareImage = builder.shareImage;
        this.shareImageIcon = builder.shareImageIcon;
        this.path = builder.path;
        this.userName = builder.userName;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public Bitmap getShareImage() {
        return shareImage;
    }

    public int getShareImageIcon() {
        return shareImageIcon;
    }

    public String getPath() {
        return path;
    }

    public String getUserName() {
        return userName;
    }

    public static class Builder {
        private String shareTitle;
        private String shareContent;
        private String shareUrl;
        private String shareImageUrl;
        private Bitmap shareImage;
        private int shareImageIcon;
        private String path;
        private String userName;

        public Builder() {

        }

        /**
         * 小程序消息标题
         */
        public Builder setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
            return this;
        }

        /**
         * 小程序消息内容
         */
        public Builder setShareContent(String shareContent) {
            this.shareContent = shareContent;
            return this;
        }

        /**
         * 兼容低版本的网页链接
         *
         * @param shareUrl e.g. http://mobile.umeng.com/social
         */
        public Builder setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
            return this;
        }

        /**
         * 小程序消息封面图片
         */
        public Builder setShareImageUrl(String shareImageUrl) {
            this.shareImageUrl = shareImageUrl;
            return this;
        }

        /**
         * 小程序消息封面图片
         */
        public Builder setShareImage(Bitmap shareImage) {
            this.shareImage = shareImage;
            return this;
        }

        /**
         * 小程序消息封面图片
         */
        public Builder setShareImageIcon(int shareImageIcon) {
            this.shareImageIcon = shareImageIcon;
            return this;
        }

        /**
         * 小程序页面路径
         *
         * @param path e.g. pages/page10007/page10007
         */
        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        /**
         * 小程序原始id,在微信平台查询
         *
         * @param userName e.g. gh_3ac2059ac66f
         */
        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public DKShareMiniProgram build() {
            return new DKShareMiniProgram(this);
        }
    }
}
