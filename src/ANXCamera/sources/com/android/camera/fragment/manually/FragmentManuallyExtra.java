package com.android.camera.fragment.manually;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListAdapter;
import com.android.camera.animation.type.SlideInOnSubscribe;
import com.android.camera.animation.type.SlideOutOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.config.ComponentManuallyWB;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.FragmentUtils;
import com.android.camera.fragment.manually.adapter.ExtraCustomWBListAdapter;
import com.android.camera.fragment.manually.adapter.ExtraHorizontalListAdapter;
import com.android.camera.fragment.manually.adapter.ExtraRecyclerViewAdapter;
import com.android.camera.fragment.manually.adapter.ExtraSlideFocusAdapter;
import com.android.camera.ui.HorizontalListView;
import com.android.camera.ui.HorizontalSlideView;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import java.util.List;

public class FragmentManuallyExtra extends BaseFragment {
    public static final int FRAGMENT_INFO = 254;
    private int mCurrentTitle = -1;
    private ComponentData mData;
    private RecyclerView mExtraList;
    private HorizontalListView mExtraListHorizontal;
    private HorizontalSlideView mHorizontalSlideView;
    private boolean mIsAnimateIng;
    private ManuallyListener mManuallyListener;
    private boolean mNeedAnimation;
    private View mTargetView;

    public void setComponentData(ComponentData componentData, int i, boolean z, ManuallyListener manuallyListener) {
        this.mData = componentData;
        this.mCurrentMode = i;
        this.mNeedAnimation = z;
        this.mManuallyListener = manuallyListener;
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mExtraList = (RecyclerView) view.findViewById(2131558532);
        this.mExtraListHorizontal = (HorizontalListView) view.findViewById(2131558533);
        this.mHorizontalSlideView = (HorizontalSlideView) view.findViewById(2131558534);
        if (this.mData != null) {
            adjustViewBackground(view, this.mCurrentMode);
            initAdapter(this.mData);
            this.mCurrentTitle = this.mData.getDisplayTitleString();
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mNeedAnimation) {
            this.mNeedAnimation = false;
            this.mIsAnimateIng = true;
            Completable.create(new SlideInOnSubscribe(view, 80)).subscribe(new Action() {
                public void run() throws Exception {
                    FragmentManuallyExtra.this.mIsAnimateIng = false;
                }
            });
        }
    }

    public void resetData(ComponentData componentData) {
        this.mData = componentData;
        initAdapter(this.mData);
        this.mCurrentTitle = this.mData.getDisplayTitleString();
    }

    public int getCurrentTitle() {
        return this.mCurrentTitle;
    }

    public void updateData() {
        initAdapter(this.mData);
    }

    public void animateOut() {
        this.mIsAnimateIng = true;
        Completable.create(new SlideOutOnSubscribe(getView(), 80)).subscribe(new Action() {
            public void run() throws Exception {
                FragmentUtils.removeFragmentByTag(FragmentManuallyExtra.this.getFragmentManager(), FragmentManuallyExtra.this.getFragmentTag());
                FragmentManuallyExtra.this.mIsAnimateIng = false;
            }
        });
    }

    private void initAdapter(ComponentData componentData) {
        int displayTitleString = componentData.getDisplayTitleString();
        if (displayTitleString == 2131296357) {
            initWBRecyclerView(componentData);
        } else if (displayTitleString == 2131296427 || displayTitleString == 2131296565) {
            initHorizontalListView(componentData);
        } else if (displayTitleString == 2131296613) {
            initSlideFocusView(componentData);
        }
    }

    private void initWBRecyclerView(ComponentData componentData) {
        if (this.mTargetView != null) {
            this.mTargetView.setVisibility(8);
        }
        this.mTargetView = this.mExtraList;
        int i = (int) (((float) getResources().getDisplayMetrics().widthPixels) / 5.5f);
        if (componentData.getItems().size() * i < getResources().getDisplayMetrics().widthPixels) {
            i = getResources().getDisplayMetrics().widthPixels / componentData.getItems().size();
        }
        ComponentData componentData2 = componentData;
        AnonymousClass3 anonymousClass3 = new ExtraRecyclerViewAdapter(componentData2, this.mCurrentMode, this.mManuallyListener, i) {
            /* Access modifiers changed, original: protected */
            public boolean couldNewValueTakeEffect(String str) {
                if (str == null || !str.equals("manual")) {
                    return super.couldNewValueTakeEffect(str);
                }
                return true;
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.mExtraList.setLayoutManager(linearLayoutManager);
        this.mExtraList.setAdapter(anonymousClass3);
        this.mExtraList.scrollToPosition(anonymousClass3.getValuePosition());
        this.mExtraList.setVisibility(0);
    }

    private void initHorizontalListView(ComponentData componentData) {
        if (this.mTargetView != null) {
            this.mTargetView.setVisibility(8);
        }
        this.mTargetView = this.mExtraListHorizontal;
        ListAdapter extraHorizontalListAdapter = new ExtraHorizontalListAdapter(componentData, this.mCurrentMode, this.mManuallyListener);
        this.mExtraListHorizontal.setAdapter(extraHorizontalListAdapter);
        this.mExtraListHorizontal.setOnItemClickListener(extraHorizontalListAdapter);
        this.mExtraListHorizontal.setOnItemSingleTapDownListener(extraHorizontalListAdapter);
        this.mExtraListHorizontal.setSelection(extraHorizontalListAdapter.getValuePosition());
        this.mExtraListHorizontal.setVisibility(0);
    }

    private void initSlideFocusView(ComponentData componentData) {
        if (this.mTargetView != null) {
            this.mTargetView.setVisibility(8);
        }
        this.mTargetView = this.mHorizontalSlideView;
        ExtraSlideFocusAdapter extraSlideFocusAdapter = new ExtraSlideFocusAdapter(getContext(), componentData, this.mCurrentMode, this.mManuallyListener);
        this.mHorizontalSlideView.setOnItemSelectListener(extraSlideFocusAdapter);
        this.mHorizontalSlideView.setDrawAdapter(extraSlideFocusAdapter);
        this.mHorizontalSlideView.setSelection(extraSlideFocusAdapter.mapFocusToIndex(Integer.valueOf(componentData.getComponentValue(this.mCurrentMode)).intValue()));
        this.mHorizontalSlideView.setVisibility(0);
    }

    public void showCustomWB(ComponentManuallyWB componentManuallyWB) {
        this.mTargetView = this.mExtraListHorizontal;
        ListAdapter extraCustomWBListAdapter = new ExtraCustomWBListAdapter(componentManuallyWB, this.mManuallyListener);
        this.mExtraListHorizontal.setAdapter(extraCustomWBListAdapter);
        this.mExtraListHorizontal.setOnItemClickListener(extraCustomWBListAdapter);
        this.mExtraListHorizontal.setOnItemSingleTapDownListener(extraCustomWBListAdapter);
        this.mExtraListHorizontal.setSelection(extraCustomWBListAdapter.getValuePosition());
        this.mExtraListHorizontal.setVisibility(0);
        this.mExtraList.setVisibility(8);
    }

    public boolean isAnimateIng() {
        return this.mIsAnimateIng;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968614;
    }

    public int getFragmentInto() {
        return 254;
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        adjustViewBackground(getView(), this.mCurrentMode);
    }

    private void adjustViewBackground(View view, int i) {
        i = DataRepository.dataItemRunning().getUiStyle();
        if (i != 3) {
            switch (i) {
                case 0:
                    view.setBackgroundResource(2131427400);
                    return;
                case 1:
                    break;
                default:
                    return;
            }
        }
        view.setBackgroundResource(2131427401);
    }
}
