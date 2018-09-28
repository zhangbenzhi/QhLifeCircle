package com.dankegongyu.lib.share;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/3/12 13:36
 */
@SuppressWarnings("UnusedReturnValue")
public class DKShareDialog extends DialogFragment {

    private RelativeLayout mRlTitle;
    private ImageView mIvClose;
    private TextView mTvTitle;
    private TextView mTvSubTitle;
    private RecyclerView mRvShareItems;
    private TextView mTvCancel;

    private String mTitle;
    private String mSubTitle;
    private List<DK_SHARE_MEDIA> mPlatforms;
    private List<DKShareButton> mCustomPlatforms;
    private DKShareOnItemClickListener mOnShareItemClickListener;

    public static DKShareDialog newInstance() {
        return new DKShareDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dk_share_dialog, container, false);
        mRlTitle = view.findViewById(R.id.dk_share_rl_title);
        mIvClose = view.findViewById(R.id.dk_share_iv_close);
        mTvTitle = view.findViewById(R.id.dk_share_tv_title);
        mTvSubTitle = view.findViewById(R.id.dk_share_tv_subTitle);
        mRvShareItems = view.findViewById(R.id.dk_share_rv_shareItems);
        mTvCancel = view.findViewById(R.id.dk_share_tv_cancel);

        mIvClose.setOnClickListener(mOnClickListener);
        mTvCancel.setOnClickListener(mOnClickListener);

        return view;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (R.id.dk_share_iv_close == v.getId() || R.id.dk_share_tv_cancel == v.getId()) {
                dismiss();
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 左上角和右上角设为圆角
        GradientDrawable shape = new GradientDrawable();
        float radius = DKShareUtil.dp2px(view.getContext(), 8.0f);
        float radiusArray[] = {radius, radius, radius, radius, 0f, 0f, 0f, 0f};
        shape.setCornerRadii(radiusArray);
        shape.setColor(Color.WHITE);
        view.setBackground(shape);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Window window = getDialog().getWindow();
        if (window != null) {
            //设置背景颜色,只有设置了这个属性,宽度才能全屏MATCH_PARENT
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; //这个属性需要配合透明背景颜色,才会真正的 MATCH_PARENT
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = 0.5f;
            lp.gravity = Gravity.BOTTOM;
            //设置dialog进入、退出的动画
            window.setWindowAnimations(R.style.DKShareDialogDefaultAnimation);
            window.setAttributes(lp);
        }
        setCancelable(true);

        // 标题
        if (TextUtils.isEmpty(mTitle)) {
            mRlTitle.setVisibility(View.GONE);
        } else {
            mRlTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(mTitle);
        }

        // 二级标题
        if (TextUtils.isEmpty(mSubTitle)) {
            mTvSubTitle.setText(getString(R.string.dk_share_dialog_subTitle));
        } else {
            mTvSubTitle.setText(mSubTitle);
        }

        // buttons
        List<DKShareButton> shareButtons = initShareButtons();
        setSpanCount(shareButtons);
        DKShareAdapter shareAdapter = new DKShareAdapter(this, shareButtons, mOnShareItemClickListener);
        mRvShareItems.setAdapter(shareAdapter);
    }

    private List<DKShareButton> initShareButtons() {
        List<DKShareButton> list = new ArrayList<>();
        // 已选平台
        if (mPlatforms != null && mPlatforms.size() > 0) {
            for (int i = 0; i < mPlatforms.size(); i++) {
                DK_SHARE_MEDIA platform = mPlatforms.get(i);
                switch (platform) {
                    case WEIXIN:
                        list.add(new DKShareButton(DK_SHARE_MEDIA.WEIXIN.getName(), getString(R.string.dk_share_weixin), R.drawable.umeng_socialize_wechat, i));
                        break;
                    case WEIXIN_CIRCLE:
                        list.add(new DKShareButton(DK_SHARE_MEDIA.WEIXIN_CIRCLE.getName(), getString(R.string.dk_share_weixin_circle), R.drawable.umeng_socialize_wxcircle, i));
                        break;
                }
            }
        } else {
            // 如果已选平台为空，则显示默认平台
            list.add(new DKShareButton(DK_SHARE_MEDIA.WEIXIN.getName(), getString(R.string.dk_share_weixin), R.drawable.umeng_socialize_wechat, 0));
            list.add(new DKShareButton(DK_SHARE_MEDIA.WEIXIN_CIRCLE.getName(), getString(R.string.dk_share_weixin_circle), R.drawable.umeng_socialize_wxcircle, 1));
        }
        // 自定义的分享平台
        if (mCustomPlatforms != null && mCustomPlatforms.size() > 0) {
            for (DKShareButton shareButton : mCustomPlatforms) {
                if (shareButton.index < 0) {
                    shareButton.index = 0;
                }
                if (shareButton.index >= list.size()) {
                    list.add(shareButton);
                } else {
                    list.add(shareButton.index, shareButton);
                }
            }
        }
        return list;
    }

    private void setSpanCount(List<DKShareButton> shareButtons) {
        int spanCount = shareButtons.size();
        if (shareButtons.size() > 3) {
            spanCount = 3;
        }
        mRvShareItems.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
    }

    public DKShareDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public DKShareDialog setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;
        return this;
    }

    public DKShareDialog setPlatforms(List<DK_SHARE_MEDIA> platforms) {
        this.mPlatforms = platforms;
        return this;
    }

    /**
     * 默认有微信、朋友圈
     *
     * @param customPlatforms 自定义分享按钮
     */
    public DKShareDialog setCustomPlatforms(List<DKShareButton> customPlatforms) {
        this.mCustomPlatforms = customPlatforms;
        return this;
    }

    public DKShareDialog setOnShareItemClickListener(DKShareOnItemClickListener onShareItemClickListener) {
        this.mOnShareItemClickListener = onShareItemClickListener;
        return this;
    }

    public DKShareDialog show(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (this.isAdded()) {
//            ft.remove(this).commit();
            ft.show(this);
        } else {
            ft.add(this, String.valueOf(System.currentTimeMillis()));
        }
        ft.commitAllowingStateLoss();
        return this;
    }

    @Override
    public void dismiss() {
        //super.dismiss();
        dismissAllowingStateLoss();
    }
}
