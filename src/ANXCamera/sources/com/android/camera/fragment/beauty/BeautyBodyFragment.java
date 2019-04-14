package com.android.camera.fragment.beauty;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.View;
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

public class BeautyBodyFragment extends BaseBeautyMakeupFragment {
    private ColorImageView mHeaderImageView;

    /* Access modifiers changed, original: protected */
    public List<MakeupItem> initItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MakeupItem(2130837888, 2131296825, CameraBeautyParameterType.HEAD_SLIM_RATIO));
        arrayList.add(new MakeupItem(2130837880, 2131296826, CameraBeautyParameterType.BODY_SLIM_RATIO));
        arrayList.add(new MakeupItem(2130837899, 2131296827, CameraBeautyParameterType.SHOULDER_SLIM_RATIO));
        arrayList.add(new MakeupItem(2130837890, 2131296828, CameraBeautyParameterType.LEG_SLIM_RATIO));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void onAdapterItemClick(MakeupItem makeupItem) {
        BeautyHelper.setType(makeupItem.getCameraBeautyParameterType().beautyParamType);
        MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
        if (makeupProtocol != null) {
            makeupProtocol.onMakeupItemSelected();
        }
        CameraStatUtil.trackBeautyBodyCounter(makeupItem.getCameraBeautyParameterType().beautyParamType);
    }

    /* Access modifiers changed, original: protected */
    public void onHeaderClick() {
        if (this.mHeaderImageView != null) {
            this.mHeaderImageView.clearAnimation();
            this.mHeaderImageView.setRotation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            ViewCompat.animate(this.mHeaderImageView).rotation(360.0f).setDuration(500).setListener(new ViewPropertyAnimatorListenerAdapter() {
                public void onAnimationEnd(View view) {
                    super.onAnimationEnd(view);
                    BeautyBodyFragment.this.mHeaderImageView.setRotation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                }
            }).start();
        }
        BeautyHelper.resetBeauty();
        this.mSelectedParam = 0;
        this.mMakeupAdapter.setSelectedPosition(this.mSelectedParam);
        this.mLayoutManager.scrollToPosition(this.mSelectedParam);
        reSelectItem();
        MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
        if (makeupProtocol != null) {
            makeupProtocol.onMakeupItemSelected();
        }
        this.mMakeupAdapter.notifyDataSetChanged();
        toast(getResources().getString(2131296829));
        CameraStatUtil.trackBeautyBodyCounter(Type.BEAUTY_RESET);
    }

    /* Access modifiers changed, original: protected */
    public View getHeaderView() {
        View inflate = LayoutInflater.from(getContext()).inflate(2130968576, null);
        this.mHeaderImageView = (ColorImageView) inflate.findViewById(2131558410);
        this.mHeaderImageView.setColor(getResources().getColor(2131427417));
        this.mHeaderImageView.setImageResource(2130837878);
        ((TextView) inflate.findViewById(2131558411)).setText(2131296788);
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public String getClassString() {
        return BeautyBodyFragment.class.getSimpleName();
    }

    private void reSelectItem() {
        if (this.mItemList != null && this.mSelectedParam < this.mItemList.size()) {
            MakeupItem makeupItem = (MakeupItem) this.mItemList.get(this.mSelectedParam);
            BeautyHelper.setCurrentBeautyParameterType(makeupItem.getCameraBeautyParameterType());
            BeautyHelper.setType(makeupItem.getCameraBeautyParameterType().beautyParamType);
        }
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
}
