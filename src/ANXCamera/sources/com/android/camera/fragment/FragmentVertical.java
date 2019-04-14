package com.android.camera.fragment;

import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.VerticalProtocol;
import io.reactivex.Completable;
import java.util.List;

public class FragmentVertical extends BaseFragment implements VerticalProtocol {
    private TextView mLeftAlertStatus;
    private TextView mLeftLightingPattern;
    private TextView mRightAlertStatus;
    private TextView mRightLightingPattern;
    private String mStateValueText = "";
    private View mVerticalViewMenu;
    private int oldDegree;
    private int stringLightingRes;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mVerticalViewMenu = view.findViewById(2131558588);
        adjustViewHeight(this.mVerticalViewMenu);
        this.mLeftAlertStatus = (TextView) view.findViewById(2131558589);
        this.mLeftLightingPattern = (TextView) view.findViewById(2131558590);
        this.mRightAlertStatus = (TextView) view.findViewById(2131558591);
        this.mRightLightingPattern = (TextView) view.findViewById(2131558592);
        this.oldDegree = this.mDegree;
        ViewCompat.setRotation(this.mLeftAlertStatus, 90.0f);
        ViewCompat.setRotation(this.mLeftLightingPattern, 90.0f);
        ViewCompat.setRotation(this.mRightAlertStatus, 270.0f);
        ViewCompat.setRotation(this.mRightLightingPattern, 270.0f);
    }

    private void adjustViewHeight(View view) {
        ((MarginLayoutParams) view.getLayoutParams()).topMargin = (Util.sWindowHeight - ((int) (((float) getResources().getDisplayMetrics().widthPixels) / 0.75f))) - Util.getBottomHeight(getContext().getResources());
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968630;
    }

    public int getFragmentInto() {
        return BaseFragmentDelegate.FRAGMENT_VERTICAL;
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(198, this);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        modeCoordinator.detachProtocol(198, this);
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
        if (isAnyViewVisible()) {
            updateLightingRelativeView(i2 == 3, false);
        }
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        if (this.mCurrentMode == 171) {
            updateLightingRelativeView(false, true);
        }
    }

    public void setLightingPattern(java.lang.String r2) {
        /*
        r1 = this;
        r0 = r2.hashCode();
        switch(r0) {
            case 48: goto L_0x0059;
            case 49: goto L_0x004f;
            case 50: goto L_0x0045;
            case 51: goto L_0x003b;
            case 52: goto L_0x0031;
            case 53: goto L_0x0027;
            case 54: goto L_0x001d;
            case 55: goto L_0x0013;
            case 56: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0063;
    L_0x0008:
        r0 = "8";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x0010:
        r2 = 8;
        goto L_0x0064;
    L_0x0013:
        r0 = "7";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x001b:
        r2 = 7;
        goto L_0x0064;
    L_0x001d:
        r0 = "6";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x0025:
        r2 = 6;
        goto L_0x0064;
    L_0x0027:
        r0 = "5";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x002f:
        r2 = 5;
        goto L_0x0064;
    L_0x0031:
        r0 = "4";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x0039:
        r2 = 4;
        goto L_0x0064;
    L_0x003b:
        r0 = "3";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x0043:
        r2 = 3;
        goto L_0x0064;
    L_0x0045:
        r0 = "2";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x004d:
        r2 = 2;
        goto L_0x0064;
    L_0x004f:
        r0 = "1";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x0057:
        r2 = 1;
        goto L_0x0064;
    L_0x0059:
        r0 = "0";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0063;
    L_0x0061:
        r2 = 0;
        goto L_0x0064;
    L_0x0063:
        r2 = -1;
    L_0x0064:
        r0 = -2;
        switch(r2) {
            case 0: goto L_0x0099;
            case 1: goto L_0x0093;
            case 2: goto L_0x008d;
            case 3: goto L_0x0087;
            case 4: goto L_0x0081;
            case 5: goto L_0x007b;
            case 6: goto L_0x0075;
            case 7: goto L_0x006f;
            case 8: goto L_0x0069;
            default: goto L_0x0068;
        };
    L_0x0068:
        goto L_0x009c;
    L_0x0069:
        r2 = 2131296783; // 0x7f09020f float:1.8211492E38 double:1.0530005216E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x006f:
        r2 = 2131296780; // 0x7f09020c float:1.8211486E38 double:1.05300052E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x0075:
        r2 = 2131296781; // 0x7f09020d float:1.8211488E38 double:1.0530005206E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x007b:
        r2 = 2131296782; // 0x7f09020e float:1.821149E38 double:1.053000521E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x0081:
        r2 = 2131296779; // 0x7f09020b float:1.8211484E38 double:1.0530005196E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x0087:
        r2 = 2131296778; // 0x7f09020a float:1.8211482E38 double:1.053000519E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x008d:
        r2 = 2131296777; // 0x7f090209 float:1.821148E38 double:1.0530005186E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x0093:
        r2 = 2131296776; // 0x7f090208 float:1.8211478E38 double:1.053000518E-314;
        r1.stringLightingRes = r2;
        goto L_0x009c;
    L_0x0099:
        r1.stringLightingRes = r0;
    L_0x009c:
        r2 = r1.stringLightingRes;
        if (r2 != r0) goto L_0x00ab;
    L_0x00a0:
        r2 = r1.mLeftLightingPattern;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r2);
        r2 = r1.mRightLightingPattern;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r2);
        goto L_0x00d0;
    L_0x00ab:
        r2 = r1.mLeftLightingPattern;
        r0 = r1.stringLightingRes;
        r2.setText(r0);
        r2 = r1.mRightLightingPattern;
        r0 = r1.stringLightingRes;
        r2.setText(r0);
        r2 = r1.isLeftLandScape();
        if (r2 == 0) goto L_0x00c5;
    L_0x00bf:
        r2 = r1.mLeftLightingPattern;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r2);
        goto L_0x00d0;
    L_0x00c5:
        r2 = r1.isRightLandScape();
        if (r2 == 0) goto L_0x00d0;
    L_0x00cb:
        r2 = r1.mRightLightingPattern;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r2);
    L_0x00d0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentVertical.setLightingPattern(java.lang.String):void");
    }

    public void alertLightingHint(int i) {
        switch (i) {
            case 3:
                i = 2131296774;
                break;
            case 4:
                i = 2131296773;
                break;
            case 5:
                i = 2131296772;
                break;
            default:
                i = -1;
                break;
        }
        if (i == -1) {
            this.mStateValueText = "";
            AlphaOutOnSubscribe.directSetResult(this.mRightAlertStatus);
            AlphaOutOnSubscribe.directSetResult(this.mLeftAlertStatus);
            return;
        }
        this.mStateValueText = getResources().getString(i);
        this.mRightAlertStatus.setText(this.mStateValueText);
        this.mLeftAlertStatus.setText(this.mStateValueText);
        if (isLeftLandScape()) {
            AlphaInOnSubscribe.directSetResult(this.mLeftAlertStatus);
        } else if (isRightLandScape()) {
            AlphaInOnSubscribe.directSetResult(this.mRightAlertStatus);
        }
    }

    private void updateLightingRelativeView(boolean z, boolean z2) {
        AlphaOutOnSubscribe.directSetResult(this.mRightAlertStatus);
        AlphaOutOnSubscribe.directSetResult(this.mRightLightingPattern);
        AlphaOutOnSubscribe.directSetResult(this.mLeftAlertStatus);
        AlphaOutOnSubscribe.directSetResult(this.mLeftLightingPattern);
        if (z) {
            this.stringLightingRes = -2;
            this.mStateValueText = "";
        } else if (!DataRepository.dataItemRunning().getComponentRunningLighting().getComponentValue(171).equals("0")) {
            if (isLandScape()) {
                if (isLeftLandScape()) {
                    if (!TextUtils.isEmpty(this.mStateValueText)) {
                        startAnimateViewVisible(this.mLeftAlertStatus, z2);
                    }
                    if (this.stringLightingRes > 0) {
                        startAnimateViewVisible(this.mLeftLightingPattern, z2);
                    }
                } else if (isRightLandScape()) {
                    if (!TextUtils.isEmpty(this.mStateValueText)) {
                        startAnimateViewVisible(this.mRightAlertStatus, z2);
                    }
                    if (this.stringLightingRes > 0) {
                        startAnimateViewVisible(this.mRightLightingPattern, z2);
                    }
                }
            } else if (this.oldDegree == 90) {
                starAnimatetViewGone(this.mRightAlertStatus, false);
                starAnimatetViewGone(this.mRightLightingPattern, false);
                starAnimatetViewGone(this.mLeftAlertStatus, z2);
                starAnimatetViewGone(this.mLeftLightingPattern, z2);
            } else if (this.oldDegree == 270) {
                starAnimatetViewGone(this.mLeftAlertStatus, false);
                starAnimatetViewGone(this.mLeftLightingPattern, false);
                starAnimatetViewGone(this.mRightAlertStatus, z2);
                starAnimatetViewGone(this.mRightLightingPattern, z2);
            }
            this.oldDegree = this.mDegree;
        }
    }

    public boolean isAnyViewVisible() {
        return this.mLeftAlertStatus.getVisibility() == 0 || this.mRightAlertStatus.getVisibility() == 0 || this.mLeftLightingPattern.getVisibility() == 0 || this.mRightLightingPattern.getVisibility() == 0;
    }
}
