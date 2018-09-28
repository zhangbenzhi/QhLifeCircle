package com.zbz.qhlifecircle.util;


import com.zbz.qhlifecircle.app.MyAppliaction;

public class DKUtil {

    public static String getChannel() {
        return AppUtils.getMetaDataValue(MyAppliaction.instantce, "UMENG_CHANNEL");
    }
}
