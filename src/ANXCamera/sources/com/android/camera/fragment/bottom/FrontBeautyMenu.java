package com.android.camera.fragment.bottom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.constant.ColorConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.global.DataItemGlobal;
import com.android.camera.fragment.beauty.BeautyParameters;
import com.android.camera.fragment.beauty.MenuItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.ui.ColorActivateTextView;
import com.mi.config.b;

public class FrontBeautyMenu extends AbBottomMenu implements OnClickListener {
    private SparseArray mFrontBeautyMenuTabList;
    private int mLastCamerId = -1;
    private SparseArray<ColorActivateTextView> mMenuTextViewList;

    public FrontBeautyMenu(Context context, LinearLayout linearLayout, BeautyMenuAnimator beautyMenuAnimator) {
        super(context, linearLayout, beautyMenuAnimator);
    }

    public void addAllView() {
        this.mMenuTextViewList = new SparseArray();
        SparseArray menuData = getMenuData();
        for (int i = 0; i < menuData.size(); i++) {
            MenuItem menuItem = (MenuItem) menuData.valueAt(i);
            if (!isJustBeautyTab() || menuItem.type == 1) {
                ColorActivateTextView colorActivateTextView = (ColorActivateTextView) LayoutInflater.from(this.mContext).inflate(2130968578, this.mContainerView, false);
                colorActivateTextView.setNormalCor(ColorConstant.WHITE_ALPHA_99);
                if (isJustBeautyTab()) {
                    colorActivateTextView.setActivateColor(ColorConstant.COLOR_COMMON_NORMAL);
                } else {
                    colorActivateTextView.setActivateColor(ColorConstant.COLOR_COMMON_SELECTED);
                }
                colorActivateTextView.setText(menuItem.text);
                colorActivateTextView.setTag(Integer.valueOf(menuItem.type));
                colorActivateTextView.setOnClickListener(this);
                if (menuItem.redDot) {
                    Drawable drawable = this.mContext.getResources().getDrawable(2130837732);
                    colorActivateTextView.setCompoundDrawablePadding(this.mContext.getResources().getDimensionPixelOffset(2131361898));
                    colorActivateTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                }
                if (1 == menuItem.type) {
                    colorActivateTextView.setActivated(true);
                    this.mCurrentBeautyTextView = colorActivateTextView;
                } else {
                    colorActivateTextView.setActivated(false);
                }
                this.mMenuTextViewList.put(menuItem.type, colorActivateTextView);
                this.mContainerView.addView(colorActivateTextView);
            }
        }
        if ("pref_camera_face_beauty_key".equals(CameraSettings.getFaceBeautySwitch())) {
            selectBeautyType(1);
        } else {
            selectBeautyType(2);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public SparseArray<ColorActivateTextView> getChildMenuViewList() {
        return this.mMenuTextViewList;
    }

    /* Access modifiers changed, original: 0000 */
    public SparseArray<MenuItem> getMenuData() {
        if (this.mFrontBeautyMenuTabList != null && this.mFrontBeautyMenuTabList.size() > 0) {
            return this.mFrontBeautyMenuTabList;
        }
        this.mFrontBeautyMenuTabList = new SparseArray();
        String string = CameraAppImpl.getAndroidContext().getString(2131296721);
        String string2 = CameraAppImpl.getAndroidContext().getString(2131296722);
        String string3 = CameraAppImpl.getAndroidContext().getString(2131296818);
        if (b.hX()) {
            string = CameraAppImpl.getAndroidContext().getString(2131296723);
            string2 = CameraAppImpl.getAndroidContext().getString(2131296724);
        }
        MenuItem menuItem = new MenuItem();
        menuItem.type = 1;
        menuItem.text = string;
        menuItem.number = 0;
        MenuItem menuItem2 = new MenuItem();
        menuItem2.type = 2;
        menuItem2.text = string2;
        menuItem2.number = 1;
        this.mFrontBeautyMenuTabList.put(1, menuItem);
        this.mFrontBeautyMenuTabList.put(2, menuItem2);
        if (DataRepository.dataItemFeature().fp() && CameraSettings.isSupportBeautyMakeup()) {
            menuItem2 = new MenuItem();
            menuItem2.type = 3;
            menuItem2.text = string3;
            menuItem2.number = 2;
            this.mFrontBeautyMenuTabList.put(3, menuItem2);
        }
        return this.mFrontBeautyMenuTabList;
    }

    /* Access modifiers changed, original: 0000 */
    public int getDefaultType() {
        return 1;
    }

    /* Access modifiers changed, original: 0000 */
    public void switchMenu() {
        if (this.mBeautyMenuAnimator != null) {
            this.mBeautyMenuAnimator.resetAll();
        }
        this.mContainerView.removeAllViews();
        addAllView();
        selectBeautyType(getDefaultType());
        if (!CameraSettings.isFaceBeautyOn(CameraSettings.getFaceBeautifyValue())) {
            hideAdvance();
        }
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        selectBeautyType(intValue);
        ((MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194)).switchBeautyType(intValue);
        if (intValue == 3 && !CameraSettings.isBeautyMakeupClicked()) {
            CameraSettings.setBeautyMakeupClicked();
            this.mCurrentBeautyTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    private boolean isJustBeautyTab() {
        return BeautyParameters.isCurrentModeSupportVideoBeauty() || CameraSettings.isFrontPortrait();
    }

    private boolean isCameraSwitch() {
        int currentCameraId = ((DataItemGlobal) DataRepository.provider().dataGlobal()).getCurrentCameraId();
        if (this.mLastCamerId != currentCameraId) {
            return true;
        }
        this.mLastCamerId = currentCameraId;
        return false;
    }

    public boolean isRefreshUI() {
        return isCameraSwitch() || isJustBeautyTab();
    }
}
