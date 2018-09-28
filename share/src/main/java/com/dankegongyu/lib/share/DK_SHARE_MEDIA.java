//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dankegongyu.lib.share;

public enum DK_SHARE_MEDIA {

    WEIXIN("WEIXIN"),
    WEIXIN_CIRCLE("WEIXIN_CIRCLE");

    private String name;

    DK_SHARE_MEDIA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean contains(String platform) {
        for (DK_SHARE_MEDIA share_media : DK_SHARE_MEDIA.values()) {
            if (share_media.getName().equals(platform)) {
                return true;
            }
        }
        return false;
    }

    public static DK_SHARE_MEDIA getSHARE_MEDIA(String mediaName) {
        for (DK_SHARE_MEDIA share_media : DK_SHARE_MEDIA.values()) {
            if (share_media.getName().equals(mediaName)) {
                return share_media;
            }
        }
        return null;
    }
}
