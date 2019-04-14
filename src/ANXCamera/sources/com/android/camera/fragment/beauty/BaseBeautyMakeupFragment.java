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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.android.camera.ToastUtils;
import com.android.camera.Util;
import com.android.camera.fragment.DefaultItemAnimator;
import com.android.camera.fragment.beauty.MakeupSingleCheckAdapter.MakeupItem;
import com.android.camera.log.Log;
import java.util.List;

public abstract class BaseBeautyMakeupFragment extends BaseBeautyFragment {
    private static final String TAG = "BaseBeautyMakeup";
    protected OnItemClickListener mClickListener;
    private LinearLayout mHeaderRecyclerView;
    private View mHeaderView;
    protected List<MakeupItem> mItemList;
    private int mItemWidth;
    private long mLastClickTime;
    int mLastSelectedParam = -1;
    protected MyLayoutManager mLayoutManager;
    protected MakeupSingleCheckAdapter mMakeupAdapter;
    private RecyclerView mMakeupItemList;
    protected int mSelectedParam = 0;
    private int mTotalWidth;

    public class MyLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

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

        public void setScrollEnabled(boolean z) {
            this.isScrollEnabled = z;
        }

        public boolean canScrollHorizontally() {
            return this.isScrollEnabled && super.canScrollHorizontally();
        }
    }

    public abstract String getClassString();

    public abstract View getHeaderView();

    public abstract List<MakeupItem> initItems();

    public abstract void onAdapterItemClick(MakeupItem makeupItem);

    public abstract void onHeaderClick();

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968585, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mHeaderRecyclerView = (LinearLayout) view.findViewById(2131558424);
        this.mMakeupItemList = (RecyclerView) view.findViewById(2131558425);
        initHeaderView();
        this.mLayoutManager = new MyLayoutManager(getActivity());
        this.mLayoutManager.setOrientation(0);
        this.mLayoutManager.setScrollEnabled(true);
        this.mMakeupItemList.setLayoutManager(this.mLayoutManager);
        this.mMakeupItemList.setFocusable(false);
        this.mItemList = initItems();
        this.mItemWidth = getResources().getDimensionPixelSize(2131361889);
        this.mTotalWidth = getResources().getDisplayMetrics().widthPixels;
        setListPadding(this.mMakeupItemList);
        int listItemMargin = getListItemMargin();
        if (!isNeedScroll()) {
            this.mLayoutManager.setScrollEnabled(false);
        }
        this.mMakeupAdapter = new MakeupSingleCheckAdapter(getActivity(), this.mItemList, listItemMargin, isCustomWidth(), customItemWidth());
        this.mClickListener = initOnItemClickListener();
        this.mMakeupAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BaseBeautyMakeupFragment.this.mLastSelectedParam = BaseBeautyMakeupFragment.this.mSelectedParam;
                BaseBeautyMakeupFragment.this.mClickListener.onItemClick(adapterView, view, i, j);
                if (BaseBeautyMakeupFragment.this.isNeedScroll() && BaseBeautyMakeupFragment.this.scrollIfNeed(BaseBeautyMakeupFragment.this.mSelectedParam)) {
                    BaseBeautyMakeupFragment.this.notifyItemChanged(BaseBeautyMakeupFragment.this.mLastSelectedParam, BaseBeautyMakeupFragment.this.mSelectedParam);
                }
            }
        });
        this.mSelectedParam = beautyTypetoPosition();
        this.mMakeupAdapter.setSelectedPosition(this.mSelectedParam);
        this.mMakeupItemList.setAdapter(this.mMakeupAdapter);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setChangeDuration(150);
        defaultItemAnimator.setMoveDuration(150);
        defaultItemAnimator.setAddDuration(150);
        this.mMakeupItemList.setItemAnimator(defaultItemAnimator);
        this.mMakeupAdapter.notifyDataSetChanged();
        setItemInCenter(this.mSelectedParam);
    }

    /* Access modifiers changed, original: protected */
    public void setListPadding(RecyclerView recyclerView) {
        if (recyclerView != null) {
            int i;
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131362000);
            if (isNeedScroll()) {
                i = 0;
            } else {
                dimensionPixelSize = getResources().getDimensionPixelSize(2131362004);
                i = getResources().getDimensionPixelSize(2131362003);
            }
            recyclerView.setPadding(dimensionPixelSize, 0, i, 0);
        }
    }

    /* Access modifiers changed, original: protected */
    public int getListItemMargin() {
        if (isNeedScroll()) {
            return 0;
        }
        return getResources().getDimensionPixelSize(2131362002);
    }

    /* Access modifiers changed, original: protected */
    public boolean isNeedScroll() {
        return this.mItemList != null && this.mItemList.size() > 4;
    }

    private void initHeaderView() {
        this.mHeaderView = getHeaderView();
        if (this.mHeaderView != null) {
            LayoutParams layoutParams = new LayoutParams(-2, -1);
            this.mHeaderRecyclerView.addView(this.mHeaderView, 0);
            this.mHeaderView.setLayoutParams(layoutParams);
            this.mHeaderView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (System.currentTimeMillis() - BaseBeautyMakeupFragment.this.mLastClickTime < 1000) {
                        Log.d(BaseBeautyMakeupFragment.TAG, "onHeaderClick: too quick!");
                        return;
                    }
                    BaseBeautyMakeupFragment.this.mLastClickTime = System.currentTimeMillis();
                    BaseBeautyMakeupFragment.this.onHeaderClick();
                }
            });
        }
    }

    /* Access modifiers changed, original: protected */
    public OnItemClickListener initOnItemClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BaseBeautyMakeupFragment.this.mLastClickTime = System.currentTimeMillis();
                BaseBeautyMakeupFragment.this.mSelectedParam = i;
                Object tag = view.getTag();
                if (tag != null && (tag instanceof MakeupItem)) {
                    MakeupItem makeupItem = (MakeupItem) tag;
                    BeautyHelper.setCurrentBeautyParameterType(makeupItem.getCameraBeautyParameterType());
                    BaseBeautyMakeupFragment.this.onAdapterItemClick(makeupItem);
                }
            }
        };
    }

    private int beautyTypetoPosition() {
        if (this.mItemList == null || this.mItemList.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < this.mItemList.size(); i++) {
            if (((MakeupItem) this.mItemList.get(i)).getCameraBeautyParameterType() == BeautyHelper.getCurrentBeautyParameterType()) {
                return i;
            }
        }
        return 0;
    }

    private void setItemInCenter(int i) {
        this.mLayoutManager.scrollToPositionWithOffset(i, (this.mTotalWidth / 2) - (this.mItemWidth / 2));
    }

    private void notifyItemChanged(int i, int i2) {
        if (i > -1) {
            this.mMakeupAdapter.notifyItemChanged(i);
        }
        if (i2 > -1) {
            this.mMakeupAdapter.notifyItemChanged(i2);
        }
    }

    private boolean scrollIfNeed(int i) {
        int max;
        if (i == this.mLayoutManager.findFirstVisibleItemPosition() || i == this.mLayoutManager.findFirstCompletelyVisibleItemPosition()) {
            max = Math.max(0, i - 1);
        } else if (i == this.mLayoutManager.findLastVisibleItemPosition() || i == this.mLayoutManager.findLastCompletelyVisibleItemPosition()) {
            max = Math.min(i + 1, this.mLayoutManager.getItemCount() - 1);
        } else {
            max = i;
        }
        if (max == i) {
            return false;
        }
        this.mLayoutManager.scrollToPosition(max);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public View getAnimateView() {
        return this.mHeaderRecyclerView;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mItemList != null) {
            BeautyHelper.setCurrentBeautyParameterType(((MakeupItem) this.mItemList.get(this.mSelectedParam)).getCameraBeautyParameterType());
        }
    }

    /* Access modifiers changed, original: protected */
    public void toast(String str) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131362020);
        if (!Util.sIsFullScreenNavBarHidden) {
            dimensionPixelSize -= Util.sNavigationBarHeight;
        }
        ToastUtils.showToast(getContext(), str, 80, 0, dimensionPixelSize - (getResources().getDimensionPixelSize(2131362019) / 2));
    }

    /* Access modifiers changed, original: protected */
    public boolean isCustomWidth() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public int customItemWidth() {
        return getContext().getResources().getDimensionPixelSize(2131361889);
    }
}
