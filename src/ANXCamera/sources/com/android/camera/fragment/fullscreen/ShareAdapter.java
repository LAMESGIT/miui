package com.android.camera.fragment.fullscreen;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.camera.fragment.CommonRecyclerViewHolder;
import java.util.List;

public class ShareAdapter extends Adapter<CommonRecyclerViewHolder> {
    private int mItemWidth;
    private OnClickListener mOnClickListener;
    private List<ShareInfo> mShareInfoList;

    public ShareAdapter(List<ShareInfo> list, OnClickListener onClickListener, int i) {
        this.mShareInfoList = list;
        this.mOnClickListener = onClickListener;
        this.mItemWidth = i;
    }

    public void setShareInfoList(List<ShareInfo> list) {
        this.mShareInfoList = list;
    }

    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2130968607, viewGroup, false);
        inflate.getLayoutParams().width = this.mItemWidth;
        return new CommonRecyclerViewHolder(inflate);
    }

    public void onBindViewHolder(CommonRecyclerViewHolder commonRecyclerViewHolder, int i) {
        ShareInfo shareInfo = (ShareInfo) this.mShareInfoList.get(i);
        commonRecyclerViewHolder.itemView.setTag(shareInfo);
        commonRecyclerViewHolder.itemView.setOnClickListener(this.mOnClickListener);
        ImageView imageView = (ImageView) commonRecyclerViewHolder.getView(2131558512);
        TextView textView = (TextView) commonRecyclerViewHolder.getView(2131558513);
        imageView.setImageResource(shareInfo.drawableRes);
        textView.setText(shareInfo.textRes);
    }

    public int getItemCount() {
        return this.mShareInfoList.size();
    }
}
