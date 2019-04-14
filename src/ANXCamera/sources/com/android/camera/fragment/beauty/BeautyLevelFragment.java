package com.android.camera.fragment.beauty;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.fragment.beauty.SingleCheckAdapter.LevelItem;
import java.util.List;

public abstract class BeautyLevelFragment extends BaseBeautyFragment {
    private RecyclerView mLevelItemList;
    private SingleCheckAdapter mSingleCheckAdapter;

    public class MyLayoutManager extends LinearLayoutManager {
        public MyLayoutManager(Context context) {
            super(context);
        }

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            View viewForPosition = recycler.getViewForPosition(0);
            if (viewForPosition != null) {
                measureChild(viewForPosition, i, i2);
                setMeasuredDimension(MeasureSpec.getSize(i), viewForPosition.getMeasuredHeight());
            }
        }
    }

    public abstract void onLevelClick(AdapterView<?> adapterView, View view, int i, long j);

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968584, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        this.mLevelItemList = (RecyclerView) view.findViewById(2131558423);
        MyLayoutManager myLayoutManager = new MyLayoutManager(getActivity());
        myLayoutManager.setOrientation(0);
        this.mLevelItemList.setLayoutManager(myLayoutManager);
        this.mLevelItemList.setFocusable(false);
        List initBeautyItems = initBeautyItems();
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mLevelItemList.getLayoutParams();
        int provideItemHorizontalMargin = provideItemHorizontalMargin();
        marginLayoutParams.setMarginStart((getResources().getDisplayMetrics().widthPixels - ((getResources().getDimensionPixelSize(2131361899) + (provideItemHorizontalMargin * 2)) * initBeautyItems.size())) / 2);
        this.mSingleCheckAdapter = new SingleCheckAdapter(getActivity(), initBeautyItems, provideItemHorizontalMargin);
        this.mSingleCheckAdapter.setOnItemClickListener(initOnItemClickListener());
        this.mSingleCheckAdapter.setSelectedPosition(beautyLevelToPosition(CameraSettings.getBeautyShowLevel(), initBeautyItems.size() - 1));
        this.mLevelItemList.setAdapter(this.mSingleCheckAdapter);
        this.mSingleCheckAdapter.notifyDataSetChanged();
    }

    /* Access modifiers changed, original: protected */
    public List<LevelItem> initBeautyItems() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public int provideItemHorizontalMargin() {
        return getResources().getDimensionPixelSize(2131361900);
    }

    /* Access modifiers changed, original: protected */
    public OnItemClickListener initOnItemClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BeautyLevelFragment.this.onLevelClick(adapterView, view, i, j);
            }
        };
    }

    /* Access modifiers changed, original: protected */
    public int beautyLevelToPosition(int i, int i2) {
        return Util.clamp(i, 0, i2);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            if (this.mSingleCheckAdapter != null) {
                this.mSingleCheckAdapter.setEnableClick(true);
            }
        } else if (this.mSingleCheckAdapter != null) {
            this.mSingleCheckAdapter.setEnableClick(false);
        }
    }

    /* Access modifiers changed, original: protected */
    public View getAnimateView() {
        return this.mLevelItemList;
    }

    public void setEnableClick(boolean z) {
        if (this.mSingleCheckAdapter != null) {
            this.mSingleCheckAdapter.setEnableClick(z);
        }
    }
}
