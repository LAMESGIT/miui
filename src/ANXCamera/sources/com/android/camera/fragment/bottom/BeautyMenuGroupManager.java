package com.android.camera.fragment.bottom;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

public class BeautyMenuGroupManager {
    private BeautyMenuAnimator mBeautyMenuAnimator;
    private SparseArray<AbBottomMenu> mBeautyMenuList;
    private AbBottomMenu mBottomMenu;
    private LinearLayout mContainerView;
    private Context mContext;
    private int mCurrentMenuGroupType = 160;

    public BeautyMenuGroupManager(Context context, LinearLayout linearLayout) {
        this.mContainerView = linearLayout;
        this.mContext = context;
        this.mBeautyMenuAnimator = BeautyMenuAnimator.animator(this.mContainerView);
        initView();
    }

    public void setCurrentBeautyMenuType(int i) {
        this.mCurrentMenuGroupType = i;
        updateCurrentMenu(i);
    }

    public int getCurrentBeautyMenuType() {
        return this.mCurrentMenuGroupType;
    }

    private void initView() {
        updateCurrentMenu(getCurrentBeautyMenuType());
        this.mBottomMenu.addAllView();
    }

    private void updateCurrentMenu(int i) {
        if (this.mBeautyMenuList != null) {
            AbBottomMenu abBottomMenu = (AbBottomMenu) this.mBeautyMenuList.get(i);
            if (abBottomMenu != null) {
                this.mBottomMenu = abBottomMenu;
                return;
            }
        }
        this.mBeautyMenuList = new SparseArray();
        FrontBeautyMenu frontBeautyMenu;
        switch (i) {
            case 161:
                frontBeautyMenu = new FrontBeautyMenu(this.mContext, this.mContainerView, this.mBeautyMenuAnimator);
                this.mBottomMenu = frontBeautyMenu;
                this.mBeautyMenuList.put(i, frontBeautyMenu);
                break;
            case 162:
                BackBeautyMenu backBeautyMenu = new BackBeautyMenu(this.mContext, this.mContainerView, this.mBeautyMenuAnimator);
                this.mBottomMenu = backBeautyMenu;
                this.mBeautyMenuList.put(i, backBeautyMenu);
                break;
            case 163:
                LiveBeautyMenu liveBeautyMenu = new LiveBeautyMenu(this.mContext, this.mContainerView, this.mBeautyMenuAnimator);
                this.mBottomMenu = liveBeautyMenu;
                this.mBeautyMenuList.put(i, liveBeautyMenu);
                break;
            case 164:
                LiveStickerMenu liveStickerMenu = new LiveStickerMenu(this.mContext, this.mContainerView, this.mBeautyMenuAnimator);
                this.mBottomMenu = liveStickerMenu;
                this.mBeautyMenuList.put(i, liveStickerMenu);
                break;
            case 165:
                LiveSpeedMenu liveSpeedMenu = new LiveSpeedMenu(this.mContext, this.mContainerView, this.mBeautyMenuAnimator);
                this.mBottomMenu = liveSpeedMenu;
                this.mBeautyMenuList.put(i, liveSpeedMenu);
                break;
            default:
                frontBeautyMenu = new FrontBeautyMenu(this.mContext, this.mContainerView, this.mBeautyMenuAnimator);
                this.mBottomMenu = frontBeautyMenu;
                this.mBeautyMenuList.put(i, frontBeautyMenu);
                break;
        }
    }

    public void switchMenu() {
        this.mBottomMenu.switchMenu();
    }

    public void animateExpanding(boolean z) {
        if (z) {
            this.mBeautyMenuAnimator.expandAnimate();
        } else {
            this.mBeautyMenuAnimator.shrinkAnimate();
        }
    }

    public void setVisibility(int i) {
        if (this.mContainerView != null) {
            this.mContainerView.setVisibility(i);
        }
    }

    public View getView() {
        return this.mContainerView;
    }

    public AbBottomMenu getBottomMenu() {
        return this.mBottomMenu;
    }
}
