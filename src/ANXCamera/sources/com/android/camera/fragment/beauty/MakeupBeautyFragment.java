package com.android.camera.fragment.beauty;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.camera.CameraSettings;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.beauty.MakeupSingleCheckAdapter.MakeupItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.MakeupProtocol;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.ui.ColorImageView;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import java.util.ArrayList;
import java.util.List;

public class MakeupBeautyFragment extends BaseBeautyMakeupFragment {
    private static final String TAG = "MakeupBeautyFragment";
    private ColorImageView mHeaderImageView;

    /* Access modifiers changed, original: protected */
    public List<MakeupItem> initItems() {
        int currentCameraId = DataRepository.dataItemGlobal().getCurrentCameraId();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MakeupItem(2130837886, 2131296819, CameraBeautyParameterType.EYEBROW_DYE_RATIO));
        arrayList.add(new MakeupItem(2130837897, 2131296820, CameraBeautyParameterType.PUPIL_LINE_RATIO));
        arrayList.add(new MakeupItem(2130837889, 2131296821, CameraBeautyParameterType.JELLY_LIPS_RATIO));
        if (1 == currentCameraId && DataRepository.dataItemFeature().fw()) {
            arrayList.add(new MakeupItem(2130837885, 2131296789, CameraBeautyParameterType.EYE_LIGHT));
        }
        arrayList.add(new MakeupItem(2130837879, 2131296822, CameraBeautyParameterType.BLUSHER_RATIO));
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void onAdapterItemClick(MakeupItem makeupItem) {
        if (makeupItem.getCameraBeautyParameterType().beautyType == 3) {
            BeautyHelper.setType(makeupItem.getCameraBeautyParameterType().beautyParamType);
            MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
            if (makeupProtocol != null) {
                makeupProtocol.onMakeupItemSelected();
            }
        } else if (makeupItem.getCameraBeautyParameterType().beautyType == 4 && makeupItem.getCameraBeautyParameterType().customParameterType == CameraBeautyParameterType.EYE_LIGHT.customParameterType) {
            ((MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194)).switchBeautyType(4);
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
                    MakeupBeautyFragment.this.mHeaderImageView.setRotation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                }
            }).start();
        }
        CameraSettings.resetEyeLight();
        BeautyHelper.setType(CameraBeautyParameterType.EYEBROW_DYE_RATIO.beautyParamType);
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
        toast(getResources().getString(2131296804));
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

    /* Access modifiers changed, original: protected */
    public String getClassString() {
        return getClass().getSimpleName();
    }
}
