package com.android.camera.fragment.beauty;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.android.camera.fragment.beauty.BeautyParameters.Type;
import com.android.camera.fragment.beauty.MakeupSingleCheckAdapter.MakeupItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.MakeupProtocol;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.ColorImageView;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import java.util.ArrayList;
import java.util.List;

public class LiveBeautyModeFragment extends BaseBeautyMakeupFragment {
    private ColorImageView mHeaderImageView;

    /* Access modifiers changed, original: protected */
    public List<MakeupItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MakeupItem(2130837887, 2131296728, CameraBeautyParameterType.LIVE_SHRINK_FACE_RATIO));
        arrayList.add(new MakeupItem(2130837884, 2131296727, CameraBeautyParameterType.LIVE_ENLARGE_EYE_RATIO));
        arrayList.add(new MakeupItem(2130837902, 2131296726, CameraBeautyParameterType.LIVE_SMOOTH_STRENGTH));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void onAdapterItemClick(MakeupItem makeupItem) {
        if (makeupItem.getCameraBeautyParameterType().beautyType == 7) {
            BeautyHelper.setType(makeupItem.getCameraBeautyParameterType().beautyParamType);
            MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
            if (makeupProtocol != null) {
                makeupProtocol.onMakeupItemSelected();
            }
            CameraStatUtil.trackLiveBeautyCounter(makeupItem.getCameraBeautyParameterType().beautyParamType);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onHeaderClick() {
        if (this.mHeaderImageView != null) {
            this.mHeaderImageView.clearAnimation();
            this.mHeaderImageView.setRotation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            ViewCompat.animate(this.mHeaderImageView).rotation(360.0f).setDuration(500).setListener(new ViewPropertyAnimatorListenerAdapter() {
                public void onAnimationEnd(View view) {
                    super.onAnimationEnd(view);
                    LiveBeautyModeFragment.this.mHeaderImageView.setRotation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                }
            }).start();
        }
        BeautyHelper.resetBeauty();
        BeautyHelper.setType(CameraBeautyParameterType.LIVE_SHRINK_FACE_RATIO.beautyParamType);
        this.mSelectedParam = 0;
        this.mMakeupAdapter.setSelectedPosition(this.mSelectedParam);
        this.mLayoutManager.scrollToPosition(this.mSelectedParam);
        MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
        if (makeupProtocol != null) {
            makeupProtocol.onMakeupItemSelected();
        }
        this.mMakeupAdapter.notifyDataSetChanged();
        makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
        if (makeupProtocol != null) {
            makeupProtocol.setSeekBarMode(1);
        }
        toast(getResources().getString(2131296857));
        CameraStatUtil.trackLiveBeautyCounter(Type.BEAUTY_RESET);
    }

    /* Access modifiers changed, original: protected */
    public View getHeaderView() {
        View inflate = LayoutInflater.from(getContext()).inflate(2130968576, null);
        LayoutParams layoutParams = (LayoutParams) ((LinearLayout) inflate.findViewById(2131558409)).getLayoutParams();
        layoutParams.width = getResources().getDimensionPixelSize(2131362103);
        inflate.setLayoutParams(layoutParams);
        this.mHeaderImageView = (ColorImageView) inflate.findViewById(2131558410);
        this.mHeaderImageView.setColor(getResources().getColor(2131427417));
        this.mHeaderImageView.setImageResource(2130837878);
        TextView textView = (TextView) inflate.findViewById(2131558411);
        textView.setText(2131296788);
        textView.setTextColor(getResources().getColor(2131427419));
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public String getClassString() {
        return LiveBeautyModeFragment.class.getSimpleName();
    }

    /* Access modifiers changed, original: protected */
    public int getListItemMargin() {
        return super.getListItemMargin();
    }

    /* Access modifiers changed, original: protected */
    public void setListPadding(RecyclerView recyclerView) {
        super.setListPadding(recyclerView);
        if (recyclerView != null) {
            recyclerView.setPadding(0, 0, 0, 0);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isCustomWidth() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int customItemWidth() {
        return getResources().getDimensionPixelSize(2131362102);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate != null) {
            if (!z && baseDelegate.getActiveFragment(2131558655) == 252) {
                baseDelegate.delegateEvent(3);
            } else if (z && baseDelegate.getActiveFragment(2131558655) != 252) {
                reSelectItem();
                baseDelegate.delegateEvent(3);
            }
        }
    }

    private void reSelectItem() {
        if (this.mItemList != null && this.mSelectedParam < this.mItemList.size()) {
            MakeupItem makeupItem = (MakeupItem) this.mItemList.get(this.mSelectedParam);
            BeautyHelper.setCurrentBeautyParameterType(makeupItem.getCameraBeautyParameterType());
            BeautyHelper.setType(makeupItem.getCameraBeautyParameterType().beautyParamType);
        }
    }
}
