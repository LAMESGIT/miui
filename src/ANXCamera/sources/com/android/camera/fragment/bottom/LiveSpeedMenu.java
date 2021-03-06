package com.android.camera.fragment.bottom;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.android.camera.CameraAppImpl;
import com.android.camera.constant.ColorConstant;
import com.android.camera.fragment.beauty.MenuItem;
import com.android.camera.ui.ColorActivateTextView;

public class LiveSpeedMenu extends AbBottomMenu implements OnClickListener {
    private static final int LIVE_SPEED_TYPE = 0;
    private SparseArray<MenuItem> mLiveSpeedMenuTabList;
    private SparseArray<ColorActivateTextView> mMenuTextViewList;

    public LiveSpeedMenu(Context context, LinearLayout linearLayout, BeautyMenuAnimator beautyMenuAnimator) {
        super(context, linearLayout, beautyMenuAnimator);
    }

    /* Access modifiers changed, original: 0000 */
    public void addAllView() {
        this.mMenuTextViewList = new SparseArray();
        SparseArray menuData = getMenuData();
        for (int i = 0; i < menuData.size(); i++) {
            MenuItem menuItem = (MenuItem) menuData.valueAt(i);
            ColorActivateTextView colorActivateTextView = (ColorActivateTextView) LayoutInflater.from(this.mContext).inflate(2130968578, this.mContainerView, false);
            colorActivateTextView.setNormalCor(ColorConstant.WHITE_ALPHA_99);
            colorActivateTextView.setActivateColor(ColorConstant.COLOR_COMMON_NORMAL);
            colorActivateTextView.setText(menuItem.text);
            colorActivateTextView.setTag(Integer.valueOf(menuItem.type));
            colorActivateTextView.setOnClickListener(this);
            if (menuItem.type == 0) {
                colorActivateTextView.setActivated(true);
                this.mCurrentBeautyTextView = colorActivateTextView;
            } else {
                colorActivateTextView.setActivated(false);
            }
            this.mMenuTextViewList.put(menuItem.type, colorActivateTextView);
            this.mContainerView.addView(colorActivateTextView);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public SparseArray<ColorActivateTextView> getChildMenuViewList() {
        return this.mMenuTextViewList;
    }

    /* Access modifiers changed, original: 0000 */
    public SparseArray<MenuItem> getMenuData() {
        if (this.mLiveSpeedMenuTabList != null) {
            return this.mLiveSpeedMenuTabList;
        }
        this.mLiveSpeedMenuTabList = new SparseArray();
        MenuItem menuItem = new MenuItem();
        menuItem.type = 0;
        menuItem.text = CameraAppImpl.getAndroidContext().getString(2131296810);
        menuItem.number = 0;
        this.mLiveSpeedMenuTabList.put(0, menuItem);
        return this.mLiveSpeedMenuTabList;
    }

    /* Access modifiers changed, original: 0000 */
    public void switchMenu() {
        this.mContainerView.removeAllViews();
        addAllView();
        selectBeautyType(getDefaultType());
    }

    /* Access modifiers changed, original: 0000 */
    public int getDefaultType() {
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isRefreshUI() {
        return true;
    }

    public void onClick(View view) {
    }
}
