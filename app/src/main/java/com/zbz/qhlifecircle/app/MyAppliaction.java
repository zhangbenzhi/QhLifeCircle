package com.zbz.qhlifecircle.app;

import android.app.Application;
import android.provider.SyncStateContract;

import com.dankegongyu.lib.share.DKShareUtil;
import com.dankegongyu.lib.ut.UsertrackUtils;
import com.zbz.qhlifecircle.constant.MyConstant;
import com.zbz.qhlifecircle.util.DKUtil;

/**
 * @author 张本志
 * @date 2018/9/27 下午6:30
 */
public class MyAppliaction extends Application {


    public static MyAppliaction instantce;

    @Override
    public void onCreate() {
        super.onCreate();

        instantce = this;
        initFunction();
    }

    private void initFunction() {
        initUmeng();
    }


    private void initUmeng() {
        // 统计
        UsertrackUtils.getInstance().init(this, MyConstant.UMENT_APPKEY, DKUtil.getChannel(), MyConstant.isDebug);
        // 分享
        DKShareUtil.init(this, MyConstant.UMENT_APPKEY, DKUtil.getChannel());
        //DKShareUtil.setWeixin(SyncStateContract.Constants.WEIXIN_APP_ID, SyncStateContract.Constants.WEIXIN_APP_SECRET);
    }

}
