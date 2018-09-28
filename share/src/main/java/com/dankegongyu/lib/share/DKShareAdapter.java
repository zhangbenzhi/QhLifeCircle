package com.dankegongyu.lib.share;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/3/12 15:53
 */
public class DKShareAdapter extends RecyclerView.Adapter<DKShareAdapter.DKShareViewHolder> {

    private DKShareDialog mShareDialog;
    private List<DKShareButton> mList;
    private DKShareOnItemClickListener mOnShareItemClickListener;

    public DKShareAdapter(DKShareDialog shareDialog, List<DKShareButton> list, DKShareOnItemClickListener onShareItemClickListener) {
        this.mShareDialog = shareDialog;
        mList = list;
        this.mOnShareItemClickListener = onShareItemClickListener;
    }

    @Override
    public DKShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DKShareViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dk_share_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DKShareViewHolder holder, int position) {
        final DKShareButton shareButton = mList.get(position);
        if (shareButton != null) {
            holder.mTvItem.setText(shareButton.share_text);
            Drawable drawableTop = ContextCompat.getDrawable(holder.itemView.getContext(), shareButton.share_icon);
            holder.mTvItem.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
            holder.mTvItem.setCompoundDrawablePadding(DKShareUtil.dp2px(holder.itemView.getContext(), 10));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnShareItemClickListener != null) {
                    mOnShareItemClickListener.onShareItemClick(mShareDialog, shareButton);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class DKShareViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvItem;

        public DKShareViewHolder(View itemView) {
            super(itemView);

            mTvItem = (TextView) itemView.findViewById(R.id.dk_share_tv_item);
        }
    }

}
