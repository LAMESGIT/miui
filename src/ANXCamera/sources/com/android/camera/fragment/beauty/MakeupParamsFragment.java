package com.android.camera.fragment.beauty;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.camera.fragment.beauty.MakeupSingleCheckAdapter.MakeupItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.MakeupProtocol;
import com.android.camera.ui.ColorImageView;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import java.util.ArrayList;
import java.util.List;

public class MakeupParamsFragment extends BaseBeautyMakeupFragment {
    private ColorImageView mHeaderImageView;

    /* Access modifiers changed, original: protected */
    public List<MakeupItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MakeupItem(2130837887, 2131296728, CameraBeautyParameterType.SHRINK_FACE_RATIO));
        arrayList.add(new MakeupItem(2130837884, 2131296727, CameraBeautyParameterType.ENLARGE_EYE_RATIO));
        arrayList.add(new MakeupItem(2130837900, 2131296725, CameraBeautyParameterType.WHITEN_STRENGTH));
        arrayList.add(new MakeupItem(2130837902, 2131296726, CameraBeautyParameterType.SMOOTH_STRENGTH));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void onAdapterItemClick(MakeupItem makeupItem) {
        if (makeupItem.getCameraBeautyParameterType().beautyType == 2) {
            BeautyHelper.setType(makeupItem.getCameraBeautyParameterType().beautyModelParameterType);
            MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
            if (makeupProtocol != null) {
                makeupProtocol.onMakeupItemSelected();
            }
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
                    MakeupParamsFragment.this.mHeaderImageView.setRotation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                }
            }).start();
        }
        BeautyHelper.resetBeauty();
        BeautyHelper.setType(CameraBeautyParameterType.SHRINK_FACE_RATIO.beautyModelParameterType);
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
        toast(getResources().getString(2131296803));
    }

    /* Access modifiers changed, original: protected */
    public View getHeaderView() {
        View inflate = LayoutInflater.from(getContext()).inflate(2130968576, null);
        this.mHeaderImageView = (ColorImageView) inflate.findViewById(2131558410);
        this.mHeaderImageView.setColor(getResources().getColor(2131427417));
        this.mHeaderImageView.setImageResource(2130837878);
        TextView textView = (TextView) inflate.findViewById(2131558411);
        textView.setText(2131296788);
        textView.setTextColor(getResources().getColor(2131427419));
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        super.initView(view);
        if (getUserVisibleHint()) {
            reSelectItem();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate != null) {
            if (!z && baseDelegate.getActiveFragment(2131558655) == 252) {
                baseDelegate.delegateEvent(3);
            } else if (z && baseDelegate.getActiveFragment(2131558655) != 252) {
                baseDelegate.delegateEvent(3);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void reSelectItem() {
        if (this.mItemList != null && this.mSelectedParam < this.mItemList.size()) {
            BeautyHelper.setType(((MakeupItem) this.mItemList.get(this.mSelectedParam)).getCameraBeautyParameterType().beautyModelParameterType);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isCustomWidth() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int customItemWidth() {
        if (isNeedScroll()) {
            return super.customItemWidth();
        }
        return getContext().getResources().getDimensionPixelSize(2131362024);
    }

    /* Access modifiers changed, original: protected */
    public String getClassString() {
        return getClass().getSimpleName();
    }
}
