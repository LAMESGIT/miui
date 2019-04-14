package com.android.camera.fragment.bottom;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.constant.ColorConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.beauty.MenuItem;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.ColorActivateTextView;

public class BackBeautyMenu extends AbBottomMenu implements OnClickListener {
    private SparseArray<MenuItem> mBackBeautyMenuTabList;
    private SparseArray<ColorActivateTextView> mMenuTextViewList;

    public BackBeautyMenu(Context context, LinearLayout linearLayout, BeautyMenuAnimator beautyMenuAnimator) {
        super(context, linearLayout, beautyMenuAnimator);
    }

    /* Access modifiers changed, original: 0000 */
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
        if (this.mBackBeautyMenuTabList != null) {
            return this.mBackBeautyMenuTabList;
        }
        this.mBackBeautyMenuTabList = new SparseArray();
        String string = CameraAppImpl.getAndroidContext().getString(2131296723);
        MenuItem menuItem = new MenuItem();
        menuItem.type = 1;
        menuItem.text = string;
        menuItem.number = 0;
        this.mBackBeautyMenuTabList.put(1, menuItem);
        if (DataRepository.dataItemFeature().isSupportBeautyBody()) {
            string = CameraAppImpl.getAndroidContext().getString(2131296824);
            menuItem = new MenuItem();
            menuItem.type = 5;
            menuItem.text = string;
            menuItem.number = 1;
            this.mBackBeautyMenuTabList.put(5, menuItem);
        }
        return this.mBackBeautyMenuTabList;
    }

    /* Access modifiers changed, original: 0000 */
    public int getDefaultType() {
        return 1;
    }

    /* Access modifiers changed, original: 0000 */
    public void switchMenu() {
        this.mContainerView.removeAllViews();
        addAllView();
        selectBeautyType(getDefaultType());
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isRefreshUI() {
        return true;
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        selectBeautyType(intValue);
        MiBeautyProtocol miBeautyProtocol = (MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194);
        if (miBeautyProtocol != null) {
            miBeautyProtocol.switchBeautyType(intValue);
        }
        CameraStatUtil.trackBeautyBodyCounterPort(intValue);
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips != null) {
            bottomPopupTips.hideQrCodeTip();
        }
    }

    private boolean isJustBeautyTab() {
        return CameraSettings.isSupportBeautyBody() ^ 1;
    }
}
