package com.android.camera.fragment.beauty;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.constant.EyeLightConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.DefaultItemAnimator;
import com.android.camera.fragment.beauty.EyeLightSingleCheckAdapter.EyeLightItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import java.util.Arrays;
import java.util.List;
import miui.view.animation.QuinticEaseInInterpolator;
import miui.view.animation.QuinticEaseOutInterpolator;

public class BeautyEyeLightFragment extends BaseBeautyFragment implements OnClickListener {
    private static final String DEFAULT_TYPE = "1";
    private static List<EyeLightItem> EYE_LIGHT_TYPE_LIST = Arrays.asList(new EyeLightItem[]{new EyeLightItem(EyeLightConstant.OFF, EyeLightConstant.getDrawable(EyeLightConstant.OFF), EyeLightConstant.getString(EyeLightConstant.OFF)), new EyeLightItem("1", EyeLightConstant.getDrawable("1"), EyeLightConstant.getString("1")), new EyeLightItem("4", EyeLightConstant.getDrawable("4"), EyeLightConstant.getString("4")), new EyeLightItem("0", EyeLightConstant.getDrawable("0"), EyeLightConstant.getString("0")), new EyeLightItem("5", EyeLightConstant.getDrawable("5"), EyeLightConstant.getString("5")), new EyeLightItem("6", EyeLightConstant.getDrawable("6"), EyeLightConstant.getString("6")), new EyeLightItem("3", EyeLightConstant.getDrawable("3"), EyeLightConstant.getString("3")), new EyeLightItem("2", EyeLightConstant.getDrawable("2"), EyeLightConstant.getString("2"))});
    private EyeLightSingleCheckAdapter mAdapter;
    private int mBackButtonWidth;
    private View mBackView;
    private int mItemWidth;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private int mSelectPosition;
    private int mTotalWidth;

    public static int getEyeLightHintText(String str) {
        for (int i = 0; i < EYE_LIGHT_TYPE_LIST.size(); i++) {
            EyeLightItem eyeLightItem = (EyeLightItem) EYE_LIGHT_TYPE_LIST.get(i);
            if (str.equals(eyeLightItem.getType())) {
                return eyeLightItem.getTextResource();
            }
        }
        return 0;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968600, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mItemWidth = getResources().getDimensionPixelSize(2131362016);
        this.mTotalWidth = getResources().getDisplayMetrics().widthPixels;
        this.mBackButtonWidth = getResources().getDimensionPixelSize(2131362018);
        this.mBackView = view.findViewById(2131558483);
        this.mRecyclerView = (RecyclerView) view.findViewById(2131558484);
        this.mLayoutManager = new LinearLayoutManager(getContext());
        this.mLayoutManager.setOrientation(0);
        this.mAdapter = new EyeLightSingleCheckAdapter(getContext(), EYE_LIGHT_TYPE_LIST, 0);
        if (getUserVisibleHint()) {
            reSelectItem();
        }
        final boolean isLayoutRTL = Util.isLayoutRTL(getContext());
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setChangeDuration(150);
        defaultItemAnimator.setMoveDuration(150);
        defaultItemAnimator.setAddDuration(150);
        this.mRecyclerView.setItemAnimator(defaultItemAnimator);
        this.mRecyclerView.addItemDecoration(new ItemDecoration() {
            final int mLeftMargin = BeautyEyeLightFragment.this.getResources().getDimensionPixelSize(2131362014);
            final int mRightMargin = BeautyEyeLightFragment.this.getResources().getDimensionPixelSize(2131362015);

            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                if (isLayoutRTL) {
                    if (childAdapterPosition == 0) {
                        rect.set(this.mRightMargin, 0, this.mLeftMargin, 0);
                    } else {
                        rect.set(this.mRightMargin, 0, 0, 0);
                    }
                } else if (childAdapterPosition == 0) {
                    rect.set(this.mLeftMargin, 0, this.mRightMargin, 0);
                } else {
                    rect.set(0, 0, this.mRightMargin, 0);
                }
            }
        });
        this.mAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BeautyEyeLightFragment.this.onItemSelected(i);
            }
        });
        this.mBackView.setOnClickListener(this);
    }

    public void onClick(View view) {
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        if ((cameraAction == null || !cameraAction.isDoingAction()) && view.getId() == 2131558483) {
            ((MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194)).switchBeautyType(3);
        }
    }

    private void setItemInCenter(int i) {
        this.mLayoutManager.scrollToPositionWithOffset(i, ((this.mTotalWidth / 2) - (this.mItemWidth / 2)) - this.mBackButtonWidth);
    }

    private void notifyItemChanged(int i, int i2) {
        if (i > -1) {
            this.mAdapter.notifyItemChanged(i);
        }
        if (i2 > -1) {
            this.mAdapter.notifyItemChanged(i2);
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

    private void onItemSelected(int i) {
        int i2 = this.mSelectPosition;
        this.mSelectPosition = i;
        EyeLightItem eyeLightItem = (EyeLightItem) EYE_LIGHT_TYPE_LIST.get(i);
        String type = eyeLightItem.getType();
        CameraSettings.setEyeLight(type);
        ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
        if (configChanges != null) {
            configChanges.setEyeLight(type);
        }
        updateTipMessage(10, eyeLightItem.getTextResource(), 4);
        if (scrollIfNeed(this.mSelectPosition)) {
            notifyItemChanged(i2, this.mSelectPosition);
        }
    }

    private void reSelectItem() {
        String eyeLightType = CameraSettings.getEyeLightType();
        if (EyeLightConstant.OFF.equals(eyeLightType)) {
            eyeLightType = "1";
        }
        for (int i = 0; i < EYE_LIGHT_TYPE_LIST.size(); i++) {
            if (eyeLightType.equals(((EyeLightItem) EYE_LIGHT_TYPE_LIST.get(i)).getType())) {
                onItemSelected(i);
                break;
            }
        }
        this.mAdapter.setSelectedPosition(this.mSelectPosition);
        setItemInCenter(this.mSelectPosition);
        this.mAdapter.notifyDataSetChanged();
    }

    /* Access modifiers changed, original: protected */
    public View getAnimateView() {
        return null;
    }

    private void updateTipMessage(int i, @StringRes int i2, int i3) {
        ((BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175)).showTips(i, i2, i3);
    }

    private void hideTipMessage(@StringRes int i) {
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (i <= 0 || bottomPopupTips.containTips(i)) {
            bottomPopupTips.directlyHideTips();
        }
    }

    public void enterAnim(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setTranslationX(100.0f);
        view.setAlpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        ViewCompat.animate(view).translationX(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).alpha(1.0f).setDuration(280).setInterpolator(new QuinticEaseOutInterpolator()).setListener(viewPropertyAnimatorListener).setStartDelay(120).start();
    }

    public void exitAnim(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setTranslationX(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        view.setAlpha(1.0f);
        ViewCompat.animate(view).translationX(100.0f).alpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setDuration(120).setStartDelay(0).setInterpolator(new QuinticEaseInInterpolator()).setListener(viewPropertyAnimatorListener).start();
    }

    public final String getFragmentTag() {
        return getClass().getSimpleName();
    }

    public void userVisibleHint() {
        reSelectItem();
        if (hasFrontFlash()) {
            updateTipMessage(10, 2131296703, 2);
        }
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate != null && baseDelegate.getActiveFragment(2131558655) == 252) {
            baseDelegate.delegateEvent(3);
        }
    }

    /* JADX WARNING: Missing block: B:12:0x0040, code skipped:
            return;
     */
    public void userInvisibleHit() {
        /*
        r3 = this;
        r0 = hasFrontFlash();
        if (r0 == 0) goto L_0x000c;
    L_0x0006:
        r0 = 2131296703; // 0x7f0901bf float:1.821133E38 double:1.053000482E-314;
        r3.hideTipMessage(r0);
    L_0x000c:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.BaseDelegate) r0;
        r1 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r2 = 194; // 0xc2 float:2.72E-43 double:9.6E-322;
        r1 = r1.getAttachProtocol(r2);
        r1 = (com.android.camera.protocol.ModeProtocol.MiBeautyProtocol) r1;
        if (r0 == 0) goto L_0x0040;
    L_0x0026:
        if (r1 != 0) goto L_0x0029;
    L_0x0028:
        goto L_0x0040;
    L_0x0029:
        r1 = r1.getBeautyType();
        r2 = 1;
        if (r1 == r2) goto L_0x003f;
    L_0x0030:
        r1 = 2131558655; // 0x7f0d00ff float:1.8742632E38 double:1.0531299035E-314;
        r1 = r0.getActiveFragment(r1);
        r2 = 252; // 0xfc float:3.53E-43 double:1.245E-321;
        if (r1 == r2) goto L_0x003f;
    L_0x003b:
        r1 = 3;
        r0.delegateEvent(r1);
    L_0x003f:
        return;
    L_0x0040:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.beauty.BeautyEyeLightFragment.userInvisibleHit():void");
    }

    private static boolean hasFrontFlash() {
        return DataRepository.dataItemConfig().getComponentFlash().isHardwareSupported();
    }
}
