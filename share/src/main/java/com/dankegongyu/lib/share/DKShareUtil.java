package com.dankegongyu.lib.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.dankegongyu.lib.share.bean.DKShareImage;
import com.dankegongyu.lib.share.bean.DKShareMiniProgram;
import com.dankegongyu.lib.share.bean.DKShareObject;
import com.dankegongyu.lib.share.bean.DKShareWeb;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

/**
 * https://developer.umeng.com/docs/66632/detail/66639
 *
 * @author WuPuquan
 * @version 1.0
 * @since 2017/11/13 11:10
 */

@SuppressWarnings("JavaDoc")
public class DKShareUtil {

    /**
     * 初始化
     */
    public static void init(Context context, String appKey, String channel) {
        // 参数：context, appKey, 渠道, UMConfigure.DEVICE_TYPE_PHONE, 传空即可
        UMConfigure.init(context, appKey, channel, UMConfigure.DEVICE_TYPE_PHONE, "");

        // 设置每次登录拉取确认界面，建议在application中初始化
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(context).setShareConfig(config);
    }

    public static void setWeixin(String appId, String appSecret) {
        // 参数：appId, appSecret
        PlatformConfig.setWeixin(appId, appSecret);
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    /**
     * 分享点击，适用于自定义分享页面
     *
     * @param platform      分享平台，使用默认平台{@link DK_SHARE_MEDIA} 或 自定义
     * @param shareObject   分享内容
     * @param shareListener 分享回调
     */
    public static void share(String platform, DKShareObject shareObject, DKShareCallback shareListener) {
        onShareItemClick(platform, shareObject, shareListener, null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends DKShareObject> void shareDefault(final T shareObject, final DKShareCallback shareListener) {
        if (shareObject == null) {
            return;
        }
        if (shareObject.getCustomPlatforms() != null) {
            List<DKShareButton> list = shareObject.getCustomPlatforms();
            for (DKShareButton shareButton : list) {
                if (DK_SHARE_MEDIA.contains(shareButton.platform)) {
                    throw new IllegalArgumentException("Share platform " + shareButton.platform + " is already defined，please use another name instead!");
                }
            }
        }

        DKShareDialog.newInstance()
                .setTitle(shareObject.getTitle())
                .setSubTitle(shareObject.getSubTitle())
                .setPlatforms(shareObject.getPlatforms())
                .setCustomPlatforms(shareObject.getCustomPlatforms())
                .setOnShareItemClickListener(new DKShareOnItemClickListener() {
                    @Override
                    public void onShareItemClick(DKShareDialog shareDialog, DKShareButton shareButton) {
//                        Log.e("share", shareObject.shareContent + "");
                        if (shareObject.getShareInterceptor() != null) {
                            shareObject.getShareInterceptor().interceptor(shareObject, DK_SHARE_MEDIA.getSHARE_MEDIA(shareButton.platform));
                        }
//                        Log.e("share2", shareObject.shareContent + "");
                        DKShareUtil.onShareItemClick(shareButton.platform, shareObject, shareListener, shareDialog);
                    }
                })
                .show(shareObject.getActivity().getSupportFragmentManager());
    }

    private static void onShareItemClick(String platform, DKShareObject shareObject, DKShareCallback shareListener, DKShareDialog shareDialog) {
        DK_SHARE_MEDIA dk_share_media = DK_SHARE_MEDIA.getSHARE_MEDIA(platform);
        SHARE_MEDIA share_media = DKShareMediaConverter.getShareMedia(dk_share_media);
        if (share_media == null) {
            // 说明是自定义的
            if (shareDialog != null) {
                shareDialog.dismiss();
            }
            if (shareListener != null) {
                shareListener.onStart(platform);
                shareListener.onResult(platform, true);
            }
        } else {
            ShareAction shareAction = new ShareAction(shareObject.getActivity());
            shareAction.setPlatform(share_media);
            // web | 小程序
            if (shareObject.getShareMedia() == DKShareObject.DKMediaType.WEB) {
                DKShareWeb shareWeb = (DKShareWeb) shareObject;
                if (SHARE_MEDIA.WEIXIN == share_media && shareWeb.getMiniProgram() != null) {
                    // 小程序
                    shareAction.withMedia(getUMMin(shareWeb.getActivity(), shareWeb.getMiniProgram()));
                } else {
                    // web
                    shareAction.withMedia(getUMWeb(shareWeb));
                }
            }
            // image
            else if (shareObject.getShareMedia() == DKShareObject.DKMediaType.IMAGE) {
                DKShareImage shareImage = (DKShareImage) shareObject;
                shareAction.withMedia(getUMImage(shareImage));
            }
            shareAction
                    .setCallback(createUMShareListener(shareObject.getActivity(), platform, shareListener, shareDialog))
                    .share();
        }
    }

    /**
     * 小程序
     */
    private static UMMin getUMMin(Context context, DKShareMiniProgram miniProgram) {
        UMImage thumb = null;
        if (miniProgram.getShareImage() != null) {
            thumb = new UMImage(context, miniProgram.getShareImage());
        } else if (!TextUtils.isEmpty(miniProgram.getShareImageUrl())) {
            thumb = new UMImage(context, miniProgram.getShareImageUrl());
        } else if (miniProgram.getShareImageIcon() != 0) {
            thumb = new UMImage(context, miniProgram.getShareImageIcon());
        }
        UMMin umMin = new UMMin(miniProgram.getShareUrl());
        umMin.setTitle(miniProgram.getShareTitle());
        umMin.setDescription(miniProgram.getShareContent());
        umMin.setThumb(thumb);
        umMin.setPath(miniProgram.getPath());
        umMin.setUserName(miniProgram.getUserName());
        return umMin;
    }

    /**
     * web
     */
    private static UMWeb getUMWeb(DKShareWeb dkShareWeb) {
        UMImage thumb = null;
        if (dkShareWeb.getShareImage() != null) {
            thumb = new UMImage(dkShareWeb.getActivity(), dkShareWeb.getShareImage());
        } else if (!TextUtils.isEmpty(dkShareWeb.getShareImageUrl())) {
            thumb = new UMImage(dkShareWeb.getActivity(), dkShareWeb.getShareImageUrl());
        } else if (dkShareWeb.getShareImageIcon() != 0) {
            thumb = new UMImage(dkShareWeb.getActivity(), dkShareWeb.getShareImageIcon());
        }
        UMWeb web = new UMWeb(dkShareWeb.getShareUrl());
        web.setTitle(dkShareWeb.getShareTitle()); //标题
        web.setDescription(dkShareWeb.getShareContent()); //描述
        web.setThumb(thumb); //缩略图
        return web;
    }

    /**
     * image
     */
    private static UMImage getUMImage(DKShareImage dkShareImage) {
        UMImage image = null;
        UMImage thumb = null;
        if (dkShareImage.getBitmap() != null) {
            image = new UMImage(dkShareImage.getActivity(), dkShareImage.getBitmap());
            thumb = new UMImage(dkShareImage.getActivity(), dkShareImage.getBitmap());
        } else if (dkShareImage.getFile() != null) {
            image = new UMImage(dkShareImage.getActivity(), dkShareImage.getFile());
            thumb = new UMImage(dkShareImage.getActivity(), dkShareImage.getFile());
        } else if (dkShareImage.getBytes() != null) {
            image = new UMImage(dkShareImage.getActivity(), dkShareImage.getBytes());
            thumb = new UMImage(dkShareImage.getActivity(), dkShareImage.getBytes());
        } else if (!TextUtils.isEmpty(dkShareImage.getImageUrl())) {
            image = new UMImage(dkShareImage.getActivity(), dkShareImage.getImageUrl());
            thumb = new UMImage(dkShareImage.getActivity(), dkShareImage.getImageUrl());
        } else if (dkShareImage.getImageRes() != 0) {
            image = new UMImage(dkShareImage.getActivity(), dkShareImage.getImageRes());
            thumb = new UMImage(dkShareImage.getActivity(), dkShareImage.getImageRes());
        }
        if (image != null) {
            // 压缩格式设置：
            image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
            image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
            image.setThumb(thumb);
            image.setTitle(dkShareImage.getShareTitle());
            image.setDescription(dkShareImage.getShareContent());
        }
        return image;
    }

    @SuppressWarnings("WeakerAccess")
    public static UMShareListener createUMShareListener(final Activity activity, final String platformName, final DKShareCallback shareListener, final DKShareDialog shareDialog) {
        return new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                if (shareDialog != null) {
                    shareDialog.dismiss();
                }
                if (shareListener != null) {
                    shareListener.onStart(platformName);
                }
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
//                Toast.makeText(activity.getApplicationContext(), activity.getString(R.string.dk_share_success), Toast.LENGTH_SHORT).show();
                if (shareListener != null) {
                    shareListener.onResult(platformName, true);
                }
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Log.e("share error: ", t + "");
                boolean notInstalled = DKShareUtil.handlePlatformNotInstalled(activity, platform);
                if (!notInstalled) {
                    if (t != null) {
                        Toast.makeText(activity, activity.getString(R.string.dk_share_failure) + t.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, activity.getString(R.string.dk_share_failure), Toast.LENGTH_SHORT).show();
                    }
                }
                if (shareListener != null) {
                    shareListener.onResult(platformName, false);
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
//                Toast.makeText(activity, activity.getString(R.string.dk_share_cancel), Toast.LENGTH_SHORT).show();
                if (shareListener != null) {
                    shareListener.onResult(platformName, false);
                }
            }
        };
    }

    /**
     * 处理微信QQ等客户端未安装的情况
     */
    public static boolean handlePlatformNotInstalled(Activity activity, DK_SHARE_MEDIA dk_share_media) {
        SHARE_MEDIA share_media = DKShareMediaConverter.getShareMedia(dk_share_media);
        return handlePlatformNotInstalled(activity, share_media);
    }

    /**
     * 处理微信QQ等客户端未安装的情况
     *
     * @param activity target Activity
     * @param platform 分享平台
     */
    private static boolean handlePlatformNotInstalled(Activity activity, SHARE_MEDIA platform) {
        if (!UMShareAPI.get(activity).isInstall(activity, platform)) {
            String appName = "";
            switch (platform) {
                case WEIXIN:
                case WEIXIN_CIRCLE:
                    appName = activity.getString(R.string.dk_share_weixin);
                    break;
                case QQ:
//                case QZONE: // QQ空间有独立的App，包名跟QQ不一样，所以这里暂不提示了
                    appName = activity.getString(R.string.dk_share_qq);
                    break;
            }
            Toast.makeText(activity, String.format(activity.getString(R.string.dk_share_not_installed), appName), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 内存泄漏解决方案
     * 在使用分享或者授权的Activity的onDestroy()中调用下面的方法
     */
    public static void release(Context context) {
        UMShareAPI.get(context).release();
    }

    /**
     * QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中的onActivityResult中调用本方法
     * 注意onActivityResult不可在fragment中实现，如果在fragment中调用登录或分享，需要在fragment依赖的Activity中实现
     *
     * @param activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);
    }
}
