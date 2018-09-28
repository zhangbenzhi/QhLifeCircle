package com.dankegongyu.lib.share;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * 分享按钮
 *
 * @author wupuquan
 * @version 1.0
 * @since 2018/2/7 20:57
 */
public class DKShareButton implements Serializable {

    public String platform;
    public String share_text;
    public int share_icon;
    public int index; // 位置

    private DKShareButton() {
    }

    /**
     * 分享按钮
     * @param platfrom 分享平台
     * @param share_text 平台名称
     * @param share_icon 平台图标
     * @param index 显示位置，如：如果想显示在第一位则传0，如果想显示在最后则可以传大点的数比如100
     */
    public DKShareButton(String platfrom, String share_text, @DrawableRes int share_icon, int index) {
        this.platform = platfrom;
        this.share_text = share_text;
        this.share_icon = share_icon;
        this.index = index;
    }
}
