package com.android.camera.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.camera.ActivityBase;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.animation.FragmentAnimationFactory;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.animation.type.TranslateYOnSubscribe;
import com.android.camera.constant.DurationConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.beauty.MiBeauty;
import com.android.camera.fragment.top.FragmentTopAlert;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.ActionProcessing;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.BokehFNumberController;
import com.android.camera.protocol.ModeProtocol.BottomMenuProtocol;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.DualController;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.ManuallyAdjust;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.protocol.ModeProtocol.VerticalProtocol;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import io.reactivex.Completable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import miui.view.animation.BackEaseOutInterpolator;

public class FragmentBottomPopupTips extends BaseFragment implements OnClickListener, BottomPopupTips, HandleBackTrace {
    private static final int ANIM_DELAY_SHOW = 3;
    private static final int ANIM_DIRECT_HIDE = 1;
    private static final int ANIM_DIRECT_SHOW = 0;
    private static final int ANIM_HIDE = 4;
    private static final int ANIM_SHOW = 2;
    private static final int CALL_TYPE_NOTIFY = 1;
    private static final int CALL_TYPE_PROVIDE = 0;
    private static final int CENTER_TIP_IMAGE_SPEED = 33;
    public static final int FRAGMENT_INFO = 4081;
    private static final int LEFT_TIP_IMAGE_CLOSE = 20;
    private static final int LEFT_TIP_IMAGE_LIGHTING = 19;
    private static final int LEFT_TIP_IMAGE_STICKER = 18;
    private static final int LEFT_TIP_IMAGE_ULTRA_WIDE = 21;
    private static final int MSG_HIDE_TIP = 1;
    private static final int TIP_BACK_FACING_IMAGE_BEAUTY = 4;
    private static final int TIP_IMAGE_BEAUTY = 3;
    private static final int TIP_IMAGE_INVALID = -1;
    private static final int TIP_IMAGE_STICKER = 2;
    private int mBottomTipHeight;
    private ImageView mCenterTipImage;
    private ImageView mCloseIv;
    private int mCloseType = 0;
    @StringRes
    private int mCurrentTipMessage;
    private int mCurrentTipType;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                FragmentBottomPopupTips.this.mTipMessage.setVisibility(8);
                if (FragmentBottomPopupTips.this.mLastTipType == 6 && FragmentBottomPopupTips.this.mCurrentTipType != 8 && !FragmentBottomPopupTips.this.isPortraitHintVisible()) {
                    FragmentBottomPopupTips.this.showTips(FragmentBottomPopupTips.this.mLastTipType, FragmentBottomPopupTips.this.mLastTipMessage, 4);
                } else if (FragmentBottomPopupTips.this.mLastTipType == 10) {
                    FragmentBottomPopupTips.this.showTips(FragmentBottomPopupTips.this.mLastTipType, FragmentBottomPopupTips.this.mLastTipMessage, 4);
                }
                FragmentBottomPopupTips.this.mLastTipType = 4;
            }
        }
    };
    private boolean mIsShowLeftImageIntro;
    @StringRes
    private int mLastTipMessage;
    private int mLastTipType;
    private FrameLayout mLeftImageIntro;
    private AnimatorSet mLeftImageIntroAnimator;
    private TextView mLeftImageIntroContent;
    private int mLeftImageIntroRadius;
    private int mLeftImageIntroWidth;
    private ImageView mLeftTipImage;
    private TextView mLightingPattern;
    private View mPortraitSuccessHint;
    private TextView mQrCodeButton;
    private View mRootView;
    private ViewGroup mSpeedTipImage;
    private ImageView mTipImage;
    private TextView mTipMessage;
    private int stringLightingRes;

    @Retention(RetentionPolicy.SOURCE)
    @interface TipImageType {
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mRootView = view;
        this.mTipImage = (ImageView) view.findViewById(2131558449);
        if (Util.UI_DEBUG()) {
            ((LayoutParams) this.mTipImage.getLayoutParams()).gravity = 8388693;
            this.mLeftTipImage = (ImageView) view.findViewById(2131558450);
            ((LayoutParams) this.mLeftTipImage.getLayoutParams()).gravity = 8388691;
            this.mLeftTipImage.setImageResource(2130837821);
            this.mLeftTipImage.setOnClickListener(this);
            this.mSpeedTipImage = (ViewGroup) view.findViewById(2131558454);
            this.mSpeedTipImage.setOnClickListener(this);
            this.mCenterTipImage = (ImageView) view.findViewById(2131558451);
            ((LayoutParams) this.mCenterTipImage.getLayoutParams()).gravity = 81;
            this.mCenterTipImage.setOnClickListener(this);
            this.mLeftImageIntro = (FrameLayout) view.findViewById(2131558452);
            this.mLeftImageIntro.setOnClickListener(this);
            this.mLeftImageIntroContent = (TextView) view.findViewById(2131558453);
            this.mLeftImageIntroRadius = getResources().getDimensionPixelSize(2131361950);
            this.mLeftImageIntroWidth = getLeftImageIntroWide();
        }
        this.mQrCodeButton = (TextView) view.findViewById(2131558456);
        this.mTipMessage = (TextView) view.findViewById(2131558455);
        this.mPortraitSuccessHint = view.findViewById(2131558457);
        this.mLightingPattern = (TextView) view.findViewById(2131558458);
        ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = Util.getBottomHeight(getResources());
        this.mTipImage.setOnClickListener(this);
        this.mQrCodeButton.setOnClickListener(this);
        adjustViewBackground(this.mCurrentMode);
        provideAnimateElement(this.mCurrentMode, null, 2);
        if (((ActivityBase) getContext()).getCameraIntentManager().isFromScreenSlide().booleanValue()) {
            Util.startScreenSlideAlphaInAnimation(this.mTipImage);
        }
        this.mCloseIv = (ImageView) view.findViewById(2131558459);
        this.mCloseIv.setOnClickListener(this);
        if (!Util.UI_DEBUG()) {
            this.mCloseIv.setBackground(null);
        }
        this.mBottomTipHeight = getResources().getDimensionPixelSize(2131361849);
    }

    private int getLeftImageIntroWide() {
        this.mLeftImageIntroContent.measure(0, 0);
        int measuredWidth = this.mLeftImageIntroContent.getMeasuredWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131361951);
        return ((measuredWidth + dimensionPixelSize) + getResources().getDimensionPixelSize(2131361952)) + ((this.mLeftImageIntroRadius - dimensionPixelSize) * 2);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968589;
    }

    public int getFragmentInto() {
        return 4081;
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(175, this);
        registerBackStack(modeCoordinator, this);
        if (CameraSettings.isShowUltraWideIntro()) {
            this.mIsShowLeftImageIntro = true;
        }
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        this.mHandler.removeCallbacksAndMessages(null);
        modeCoordinator.detachProtocol(175, this);
        unRegisterBackStack(modeCoordinator, this);
        this.mIsShowLeftImageIntro = false;
    }

    /* JADX WARNING: Missing block: B:25:0x0095, code skipped:
            if (((java.lang.Integer) r3.getTag()).intValue() == 18) goto L_0x0098;
     */
    /* JADX WARNING: Missing block: B:26:0x0098, code skipped:
            hideAllTipImage();
            showLiveSticker();
     */
    /* JADX WARNING: Missing block: B:27:0x009f, code skipped:
            onLeftImageClick(r3);
     */
    /* JADX WARNING: Missing block: B:38:0x00fe, code skipped:
            return;
     */
    public void onClick(android.view.View r3) {
        /*
        r2 = this;
        r0 = r2.isEnableClick();
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.CameraAction) r0;
        if (r0 == 0) goto L_0x001c;
    L_0x0015:
        r0 = r0.isDoingAction();
        if (r0 == 0) goto L_0x001c;
    L_0x001b:
        return;
    L_0x001c:
        r0 = com.android.camera.CameraSettings.isFrontCamera();
        if (r0 == 0) goto L_0x002f;
    L_0x0022:
        r0 = r2.getContext();
        r0 = (com.android.camera.Camera) r0;
        r0 = r0.isScreenSlideOff();
        if (r0 == 0) goto L_0x002f;
    L_0x002e:
        return;
    L_0x002f:
        r0 = r3.getId();
        switch(r0) {
            case 2131558449: goto L_0x00a3;
            case 2131558450: goto L_0x009f;
            case 2131558451: goto L_0x0089;
            case 2131558452: goto L_0x0079;
            case 2131558453: goto L_0x0036;
            case 2131558454: goto L_0x0064;
            case 2131558455: goto L_0x0036;
            case 2131558456: goto L_0x0049;
            case 2131558457: goto L_0x0036;
            case 2131558458: goto L_0x0036;
            case 2131558459: goto L_0x0038;
            default: goto L_0x0036;
        };
    L_0x0036:
        goto L_0x00fe;
    L_0x0038:
        r3 = r2.mCloseType;
        switch(r3) {
            case 1: goto L_0x0044;
            case 2: goto L_0x003f;
            default: goto L_0x003d;
        };
    L_0x003d:
        goto L_0x00fe;
    L_0x003f:
        r2.closeLight();
        goto L_0x00fe;
    L_0x0044:
        r2.closeFilter();
        goto L_0x00fe;
    L_0x0049:
        r2.hideQrCodeTip();
        r3 = "counter";
        r0 = "qrcode_detected";
        com.android.camera.statistic.CameraStat.recordCountEvent(r3, r0);
        r3 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r0 = 195; // 0xc3 float:2.73E-43 double:9.63E-322;
        r3 = r3.getAttachProtocol(r0);
        r3 = (com.android.camera.protocol.ModeProtocol.CameraModuleSpecial) r3;
        r3.showQRCodeResult();
        goto L_0x00fe;
    L_0x0064:
        r0 = r3.getTag();
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = 33;
        if (r0 != r1) goto L_0x0089;
    L_0x0072:
        r2.hideAllTipImage();
        r2.showLiveSpeed();
        goto L_0x0089;
    L_0x0079:
        r0 = 21;
        r0 = java.lang.Integer.valueOf(r0);
        r3.setTag(r0);
        com.android.camera.CameraSettings.setPopupUltraWideIntroClicked();
        r2.directHideLeftImageIntro();
        goto L_0x009f;
    L_0x0089:
        r3 = r3.getTag();
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        r0 = 18;
        if (r3 == r0) goto L_0x0098;
    L_0x0097:
        goto L_0x00fe;
    L_0x0098:
        r2.hideAllTipImage();
        r2.showLiveSticker();
        goto L_0x00fe;
    L_0x009f:
        r2.onLeftImageClick(r3);
        goto L_0x00fe;
    L_0x00a3:
        r3 = r3.getTag();
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        r2.hideAllTipImage();
        com.android.camera.CameraSettings.setPopupTipBeautyIntroClicked();
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.BaseDelegate) r0;
        switch(r3) {
            case 2: goto L_0x00f8;
            case 3: goto L_0x00c3;
            case 4: goto L_0x00c3;
            default: goto L_0x00c2;
        };
    L_0x00c2:
        goto L_0x00fd;
    L_0x00c3:
        r3 = "counter";
        r0 = "beauty_button";
        com.android.camera.statistic.CameraStat.recordCountEvent(r3, r0);
        r3 = r2.mCurrentMode;
        r2.showBeauty(r3);
        r3 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r0 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        r3 = r3.getAttachProtocol(r0);
        r3 = (com.android.camera.protocol.ModeProtocol.ActionProcessing) r3;
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 164; // 0xa4 float:2.3E-43 double:8.1E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.ConfigChanges) r0;
        if (r3 == 0) goto L_0x00f4;
    L_0x00e9:
        r3 = r3.isShowFilterView();
        if (r3 == 0) goto L_0x00f4;
    L_0x00ef:
        if (r0 == 0) goto L_0x00f4;
    L_0x00f1:
        r0.showOrHideFilter();
    L_0x00f4:
        r2.hideQrCodeTip();
        goto L_0x00fd;
    L_0x00f8:
        r3 = 4;
        r0.delegateEvent(r3);
    L_0x00fe:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.onClick(android.view.View):void");
    }

    private void onLeftImageClick(View view) {
        ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
        switch (((Integer) view.getTag()).intValue()) {
            case 19:
                if (configChanges != null) {
                    configChanges.showOrHideLighting(true);
                    return;
                }
                return;
            case 20:
                switch (this.mCloseType) {
                    case 1:
                        closeFilter();
                        return;
                    case 2:
                        closeLight();
                        return;
                    default:
                        return;
                }
            case 21:
                if (configChanges != null) {
                    configChanges.onConfigChanged(205);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void showBeauty(int i) {
        MiBeauty.showBeautyActionFromMode(i);
        if (this.mCurrentMode == 171) {
            BokehFNumberController bokehFNumberController = (BokehFNumberController) ModeCoordinatorImpl.getInstance().getAttachProtocol(210);
            if (bokehFNumberController != null) {
                bokehFNumberController.hideFNumberPanel(false, false);
            }
        }
    }

    private void hideZoomTipImage(int i) {
        if (!(i == 163 || i == 173)) {
            switch (i) {
                case 165:
                    break;
                case 166:
                    if (!DataRepository.dataItemFeature().fM()) {
                        return;
                    }
                    break;
            }
        }
        DualController dualController = (DualController) ModeCoordinatorImpl.getInstance().getAttachProtocol(182);
        if (dualController != null) {
            dualController.hideZoomButton();
            TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
            if (topAlert != null) {
                topAlert.alertUpdateValue(2);
            }
        }
    }

    private void showLiveSticker() {
        CameraStatUtil.trackLiveClick(CameraStat.KEY_LIVE_STICKER);
        BottomMenuProtocol bottomMenuProtocol = (BottomMenuProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(197);
        if (bottomMenuProtocol != null) {
            bottomMenuProtocol.onSwitchLiveActionMenu(164);
        }
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate != null) {
            baseDelegate.delegateEvent(12);
        }
    }

    private void showLiveSpeed() {
        CameraStatUtil.trackLiveClick(CameraStat.KEY_LIVE_SPEED);
        BottomMenuProtocol bottomMenuProtocol = (BottomMenuProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(197);
        if (bottomMenuProtocol != null) {
            bottomMenuProtocol.onSwitchLiveActionMenu(165);
        }
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate != null) {
            baseDelegate.delegateEvent(13);
        }
    }

    private void closeLight() {
        showCloseTip(2, false);
        ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
        if (configChanges != null) {
            configChanges.showOrHideLighting(false);
        }
        if (Util.UI_DEBUG()) {
            updateLeftTipImage();
        }
    }

    private void closeFilter() {
        showCloseTip(1, false);
        ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
        if (configChanges != null) {
            configChanges.showOrHideFilter();
        }
    }

    private void hideAllTipImage() {
        hideTipImage();
        if (Util.UI_DEBUG()) {
            hideLeftTipImage();
            hideSpeedTipImage();
            hideZoomTipImage(this.mCurrentMode);
            hideCenterTipImage();
        }
    }

    public void hideTipImage() {
        if (this.mTipImage != null && this.mTipImage.getVisibility() != 4) {
            this.mTipImage.setTag(Integer.valueOf(-1));
            Completable.create(new AlphaOutOnSubscribe(this.mTipImage)).subscribe();
        }
    }

    public void directHideTipImage() {
        if (this.mTipImage.getVisibility() != 4) {
            this.mTipImage.setTag(Integer.valueOf(-1));
            this.mTipImage.setVisibility(4);
        }
    }

    public void reInitTipImage() {
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    public void updateTipBottomMargin(int i, boolean z) {
        if (this.mRootView.getPaddingTop() < i) {
            this.mRootView.setPadding(0, (int) (((float) i) * 1.2f), 0, 0);
        }
        if (!z) {
            TranslateYOnSubscribe.directSetResult(this.mRootView, -i);
        } else if (((float) i) < ViewCompat.getTranslationY(this.mRootView)) {
            Completable.create(new TranslateYOnSubscribe(this.mRootView, -i).setInterpolator(new OvershootInterpolator())).subscribe();
        } else {
            Completable.create(new TranslateYOnSubscribe(this.mRootView, -i).setInterpolator(new BackEaseOutInterpolator())).subscribe();
        }
    }

    public void showTips(int i, @StringRes int i2, int i3) {
        if (i == 6 && this.mCurrentMode != 171) {
            return;
        }
        if ((i != 10 || CameraSettings.getBogusCameraId() != 0) && !isLightingHintVisible()) {
            if (isPortraitSuccessHintVisible()) {
                hideTip(this.mPortraitSuccessHint);
            }
            this.mLastTipType = this.mCurrentTipType;
            this.mLastTipMessage = this.mCurrentTipMessage;
            this.mCurrentTipType = i;
            this.mCurrentTipMessage = i2;
            hideTip(this.mQrCodeButton);
            reIntTipViewMarginBottom(this.mTipMessage, this.mBottomTipHeight);
            AlphaInOnSubscribe.directSetResult(this.mTipMessage);
            this.mTipMessage.setText(i2);
            if (Util.isAccessible()) {
                this.mTipMessage.setContentDescription(getString(this.mCurrentTipMessage));
                this.mTipMessage.postDelayed(new Runnable() {
                    public void run() {
                        if (FragmentBottomPopupTips.this.isAdded()) {
                            FragmentBottomPopupTips.this.mTipMessage.sendAccessibilityEvent(4);
                        }
                    }
                }, 3000);
            }
            i = 0;
            switch (i3) {
                case 1:
                    i = 1000;
                    break;
                case 2:
                    i = 5000;
                    break;
                case 3:
                    i = DurationConstant.DURATION_VIDEO_RECORDING_FUN;
                    break;
                case 5:
                    i = 2000;
                    break;
                case 6:
                    i = 3000;
                    break;
            }
            this.mHandler.removeCallbacksAndMessages(null);
            if (i > 0) {
                this.mHandler.sendEmptyMessageDelayed(1, (long) i);
            }
        }
    }

    public void showTips(final int i, final int i2, final int i3, int i4) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                FragmentBottomPopupTips.this.showTips(i, i2, i3);
            }
        }, (long) i4);
    }

    private boolean isPortraitSuccessHintVisible() {
        return this.mPortraitSuccessHint.getVisibility() == 0;
    }

    private void reIntTipViewMarginBottom(View view, int i) {
        int dimensionPixelSize;
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        DualController dualController = (DualController) ModeCoordinatorImpl.getInstance().getAttachProtocol(182);
        ManuallyAdjust manuallyAdjust = (ManuallyAdjust) ModeCoordinatorImpl.getInstance().getAttachProtocol(181);
        MiBeautyProtocol miBeautyProtocol = (MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194);
        BokehFNumberController bokehFNumberController = (BokehFNumberController) ModeCoordinatorImpl.getInstance().getAttachProtocol(210);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131362109);
        if (this.mCurrentMode == 165) {
            if (!CameraSettings.isUltraWideConfigOpen(this.mCurrentMode) || this.mCenterTipImage == null || this.mCenterTipImage.getVisibility() == 0) {
                dimensionPixelSize = getResources().getDimensionPixelSize(2131361946) + ((((int) (((float) Util.sWindowWidth) / 0.75f)) - Util.sWindowWidth) / 2);
            } else {
                dimensionPixelSize = (getResources().getDimensionPixelSize(2131361942) / 2) - (i / 2);
            }
        } else if (manuallyAdjust != null && manuallyAdjust.visibleHeight() > 0) {
            dimensionPixelSize = manuallyAdjust.visibleHeight();
        } else if (Util.UI_DEBUG() ? this.mCenterTipImage.getVisibility() != 0 : this.mTipImage.getVisibility() != 0) {
            if (dualController != null && dualController.isZoomVisible()) {
                dimensionPixelSize = dualController.visibleHeight();
            } else if (bokehFNumberController == null || !bokehFNumberController.isFNumberVisible()) {
                BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
                if (baseDelegate != null && baseDelegate.getActiveFragment(2131558655) == 252) {
                    dimensionPixelSize = getResources().getDimensionPixelSize(2131361901) + getResources().getDimensionPixelSize(2131362005);
                } else if (miBeautyProtocol == null || !miBeautyProtocol.isBeautyPanelShow()) {
                    dimensionPixelSize = (getResources().getDimensionPixelSize(2131361942) / 2) - (i / 2);
                } else {
                    dimensionPixelSize = getResources().getDimensionPixelSize(2131362005) + dimensionPixelSize2;
                }
            } else {
                dimensionPixelSize = (bokehFNumberController.visibleHeight() + ((int) this.mRootView.getTranslationY())) + dimensionPixelSize2;
            }
        } else if (miBeautyProtocol == null || !miBeautyProtocol.isBeautyPanelShow()) {
            dimensionPixelSize = this.mTipImage.getHeight();
        } else {
            dimensionPixelSize = getResources().getDimensionPixelSize(2131362005) + dimensionPixelSize2;
        }
        if (marginLayoutParams.bottomMargin != dimensionPixelSize) {
            marginLayoutParams.bottomMargin = dimensionPixelSize;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public void directlyHideTips() {
        ViewCompat.animate(this.mTipMessage).cancel();
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mTipMessage.getVisibility() == 0) {
            this.mTipMessage.setVisibility(8);
            if (this.mLastTipType == 6 && !isPortraitHintVisible()) {
                showTips(this.mLastTipType, this.mLastTipMessage, 4);
            }
            this.mLastTipType = 4;
        }
    }

    public boolean containTips(@StringRes int i) {
        return this.mTipMessage.getVisibility() == 0 && getString(i).equals(this.mTipMessage.getText().toString());
    }

    public void setPortraitHintVisible(int i) {
        if (i != 0 || !isLightingHintVisible()) {
            this.mLastTipType = i == 0 ? 7 : 4;
            directlyHideTips();
            if (i == 0) {
                reIntTipViewMarginBottom(this.mPortraitSuccessHint, this.mBottomTipHeight);
            }
            this.mPortraitSuccessHint.setVisibility(i);
        }
    }

    public boolean isPortraitHintVisible() {
        return this.mPortraitSuccessHint.getVisibility() == 0;
    }

    public boolean isLightingHintVisible() {
        boolean isAnyViewVisible;
        VerticalProtocol verticalProtocol = (VerticalProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(198);
        if (verticalProtocol != null) {
            isAnyViewVisible = verticalProtocol.isAnyViewVisible();
        } else {
            isAnyViewVisible = false;
        }
        if (isAnyViewVisible || this.mLightingPattern.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public void setLightingPattern(java.lang.String r3) {
        /*
        r2 = this;
        r0 = -2;
        r2.stringLightingRes = r0;
        r1 = r3.hashCode();
        switch(r1) {
            case 48: goto L_0x005c;
            case 49: goto L_0x0052;
            case 50: goto L_0x0048;
            case 51: goto L_0x003e;
            case 52: goto L_0x0034;
            case 53: goto L_0x002a;
            case 54: goto L_0x0020;
            case 55: goto L_0x0016;
            case 56: goto L_0x000b;
            default: goto L_0x000a;
        };
    L_0x000a:
        goto L_0x0066;
    L_0x000b:
        r1 = "8";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x0013:
        r3 = 8;
        goto L_0x0067;
    L_0x0016:
        r1 = "7";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x001e:
        r3 = 7;
        goto L_0x0067;
    L_0x0020:
        r1 = "6";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x0028:
        r3 = 6;
        goto L_0x0067;
    L_0x002a:
        r1 = "5";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x0032:
        r3 = 5;
        goto L_0x0067;
    L_0x0034:
        r1 = "4";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x003c:
        r3 = 4;
        goto L_0x0067;
    L_0x003e:
        r1 = "3";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x0046:
        r3 = 3;
        goto L_0x0067;
    L_0x0048:
        r1 = "2";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x0050:
        r3 = 2;
        goto L_0x0067;
    L_0x0052:
        r1 = "1";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x005a:
        r3 = 1;
        goto L_0x0067;
    L_0x005c:
        r1 = "0";
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x0066;
    L_0x0064:
        r3 = 0;
        goto L_0x0067;
    L_0x0066:
        r3 = -1;
    L_0x0067:
        switch(r3) {
            case 0: goto L_0x009b;
            case 1: goto L_0x0095;
            case 2: goto L_0x008f;
            case 3: goto L_0x0089;
            case 4: goto L_0x0083;
            case 5: goto L_0x007d;
            case 6: goto L_0x0077;
            case 7: goto L_0x0071;
            case 8: goto L_0x006b;
            default: goto L_0x006a;
        };
    L_0x006a:
        goto L_0x009e;
    L_0x006b:
        r3 = 2131296783; // 0x7f09020f float:1.8211492E38 double:1.0530005216E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x0071:
        r3 = 2131296780; // 0x7f09020c float:1.8211486E38 double:1.05300052E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x0077:
        r3 = 2131296781; // 0x7f09020d float:1.8211488E38 double:1.0530005206E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x007d:
        r3 = 2131296782; // 0x7f09020e float:1.821149E38 double:1.053000521E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x0083:
        r3 = 2131296779; // 0x7f09020b float:1.8211484E38 double:1.0530005196E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x0089:
        r3 = 2131296778; // 0x7f09020a float:1.8211482E38 double:1.053000519E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x008f:
        r3 = 2131296777; // 0x7f090209 float:1.821148E38 double:1.0530005186E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x0095:
        r3 = 2131296776; // 0x7f090208 float:1.8211478E38 double:1.053000518E-314;
        r2.stringLightingRes = r3;
        goto L_0x009e;
    L_0x009b:
        r2.stringLightingRes = r0;
    L_0x009e:
        r3 = r2.stringLightingRes;
        if (r3 != r0) goto L_0x00a8;
    L_0x00a2:
        r3 = r2.mLightingPattern;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r3);
        goto L_0x00d2;
    L_0x00a8:
        r3 = 12;
        r2.mCurrentTipType = r3;
        r3 = r2.mTipMessage;
        r2.hideTip(r3);
        r3 = r2.mPortraitSuccessHint;
        r2.hideTip(r3);
        r2.directHideTipImage();
        r3 = r2.mLightingPattern;
        r0 = r2.mBottomTipHeight;
        r2.reIntTipViewMarginBottom(r3, r0);
        r3 = r2.mLightingPattern;
        r0 = r2.stringLightingRes;
        r3.setText(r0);
        r3 = r2.isLandScape();
        if (r3 != 0) goto L_0x00d2;
    L_0x00cd:
        r3 = r2.mLightingPattern;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r3);
    L_0x00d2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.setLightingPattern(java.lang.String):void");
    }

    public void showQrCodeTip() {
        if (this.mQrCodeButton.getVisibility() != 0) {
            hideTip(this.mTipMessage);
            reIntTipViewMarginBottom(this.mQrCodeButton, this.mQrCodeButton.getBackground().getIntrinsicHeight());
            AlphaInOnSubscribe.directSetResult(this.mQrCodeButton);
            if (Util.isAccessible()) {
                this.mQrCodeButton.postDelayed(new Runnable() {
                    public void run() {
                        FragmentBottomPopupTips.this.mQrCodeButton.sendAccessibilityEvent(128);
                    }
                }, 100);
            }
        }
    }

    public void hideQrCodeTip() {
        if (this.mQrCodeButton.getVisibility() != 8) {
            this.mQrCodeButton.setVisibility(8);
        }
    }

    /* JADX WARNING: Missing block: B:14:0x0024, code skipped:
            if (r3.mCurrentTipType != 9) goto L_0x0027;
     */
    /* JADX WARNING: Missing block: B:15:0x0026, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:16:0x0027, code skipped:
            hideTip(r3.mTipMessage);
            hideTip(r3.mPortraitSuccessHint);
            hideTip(r3.mQrCodeButton);
            hideTip(r3.mLightingPattern);
            r3.mHandler.removeCallbacksAndMessages(null);
     */
    /* JADX WARNING: Missing block: B:17:0x0042, code skipped:
            return false;
     */
    public boolean onBackEvent(int r4) {
        /*
        r3 = this;
        r0 = 9;
        r1 = 0;
        switch(r4) {
            case 1: goto L_0x0022;
            case 2: goto L_0x0042;
            case 3: goto L_0x0007;
            default: goto L_0x0006;
        };
    L_0x0006:
        goto L_0x0027;
    L_0x0007:
        r4 = r3.mCurrentTipType;
        r2 = 6;
        if (r4 == r2) goto L_0x0021;
    L_0x000c:
        r4 = r3.mCurrentTipType;
        r2 = 7;
        if (r4 == r2) goto L_0x0021;
    L_0x0011:
        r4 = r3.mCurrentTipType;
        r2 = 11;
        if (r4 == r2) goto L_0x0021;
    L_0x0017:
        r4 = r3.mCurrentTipType;
        if (r4 == r0) goto L_0x0021;
    L_0x001b:
        r4 = r3.mCurrentTipType;
        r2 = 12;
        if (r4 != r2) goto L_0x0022;
    L_0x0021:
        return r1;
    L_0x0022:
        r4 = r3.mCurrentTipType;
        if (r4 != r0) goto L_0x0027;
    L_0x0026:
        return r1;
    L_0x0027:
        r4 = r3.mTipMessage;
        r3.hideTip(r4);
        r4 = r3.mPortraitSuccessHint;
        r3.hideTip(r4);
        r4 = r3.mQrCodeButton;
        r3.hideTip(r4);
        r4 = r3.mLightingPattern;
        r3.hideTip(r4);
        r4 = r3.mHandler;
        r0 = 0;
        r4.removeCallbacksAndMessages(r0);
    L_0x0042:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.onBackEvent(int):boolean");
    }

    private boolean hideTip(View view) {
        if (view.getVisibility() == 8) {
            return false;
        }
        view.setVisibility(8);
        return true;
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        if (i2 == 3 || this.mCurrentMode != i) {
            this.mCloseIv.setVisibility(8);
            this.mCloseType = 0;
        }
        int i3 = this.mCurrentMode;
        super.provideAnimateElement(i, list, i2);
        onBackEvent(4);
        updateTipBottomMargin(0, false);
        updateTipImage(i, i3, list);
        if (Util.UI_DEBUG()) {
            updateLeftTipImage(0, i, i3, list);
            updateSpeedTipImage(i, i3, list);
            updateCenterTipImage(i, i3, list);
        }
    }

    private void setBeautyIntroButtonWidth(View view, int i) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = i;
            view.setLayoutParams(layoutParams);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x013d  */
    /* JADX WARNING: Missing block: B:7:0x0019, code skipped:
            if (com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn() != false) goto L_0x0080;
     */
    /* JADX WARNING: Missing block: B:9:0x0027, code skipped:
            if (com.android.camera.data.DataRepository.dataItemRunning().isSwitchOn("pref_camera_auto_zoom") != false) goto L_0x0080;
     */
    /* JADX WARNING: Missing block: B:14:0x0042, code skipped:
            if (r5.isSupportVideoBeauty() != false) goto L_0x0075;
     */
    /* JADX WARNING: Missing block: B:19:0x0051, code skipped:
            if (r5.isSupportVideoBeauty() != false) goto L_0x007e;
     */
    /* JADX WARNING: Missing block: B:27:0x006b, code skipped:
            if (com.android.camera.Util.UI_DEBUG() != false) goto L_0x007e;
     */
    /* JADX WARNING: Missing block: B:32:0x007b, code skipped:
            if (com.android.camera.Util.UI_DEBUG() != false) goto L_0x007e;
     */
    /* JADX WARNING: Missing block: B:33:0x007e, code skipped:
            r5 = 4;
     */
    /* JADX WARNING: Missing block: B:65:0x0118, code skipped:
            if (r9 != 0) goto L_0x011b;
     */
    /* JADX WARNING: Missing block: B:76:0x0134, code skipped:
            if (r11.mCurrentMode != 165) goto L_0x0139;
     */
    private void updateTipImage(int r12, int r13, java.util.List<io.reactivex.Completable> r14) {
        /*
        r11 = this;
        r0 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        r1 = -1;
        r2 = 4;
        r3 = 3;
        r4 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r12 == r4) goto L_0x006e;
    L_0x000a:
        r5 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        if (r12 == r5) goto L_0x0054;
    L_0x000e:
        if (r12 == r0) goto L_0x002a;
    L_0x0010:
        switch(r12) {
            case 161: goto L_0x002a;
            case 162: goto L_0x001d;
            case 163: goto L_0x0015;
            default: goto L_0x0013;
        };
    L_0x0013:
        goto L_0x0080;
    L_0x0015:
        r5 = com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn();
        if (r5 == 0) goto L_0x006e;
    L_0x001b:
        goto L_0x0080;
    L_0x001d:
        r5 = com.android.camera.data.DataRepository.dataItemRunning();
        r6 = "pref_camera_auto_zoom";
        r5 = r5.isSwitchOn(r6);
        if (r5 == 0) goto L_0x002a;
    L_0x0029:
        goto L_0x0080;
    L_0x002a:
        r5 = com.android.camera.module.loader.camera2.Camera2DataContainer.getInstance();
        r6 = com.android.camera.CameraSettings.getBogusCameraId();
        r5 = r5.getCapabilitiesByBogusCameraId(r6, r12);
        r6 = com.android.camera.CameraSettings.isFrontCamera();
        if (r6 == 0) goto L_0x0045;
    L_0x003c:
        if (r5 == 0) goto L_0x0080;
    L_0x003e:
        r5 = r5.isSupportVideoBeauty();
        if (r5 == 0) goto L_0x0080;
    L_0x0044:
        goto L_0x0075;
    L_0x0045:
        r6 = com.android.camera.Util.UI_DEBUG();
        if (r6 == 0) goto L_0x0080;
    L_0x004b:
        if (r5 == 0) goto L_0x0080;
    L_0x004d:
        r5 = r5.isSupportVideoBeauty();
        if (r5 == 0) goto L_0x0080;
    L_0x0053:
        goto L_0x007e;
    L_0x0054:
        r5 = com.android.camera.CameraSettings.isFrontCamera();
        if (r5 == 0) goto L_0x0060;
    L_0x005a:
        r5 = com.mi.config.b.hz();
        if (r5 != 0) goto L_0x0066;
    L_0x0060:
        r5 = com.android.camera.Util.UI_DEBUG();
        if (r5 == 0) goto L_0x0067;
    L_0x0066:
        goto L_0x0075;
    L_0x0067:
        r5 = com.android.camera.Util.UI_DEBUG();
        if (r5 == 0) goto L_0x0080;
    L_0x006d:
        goto L_0x007e;
    L_0x006e:
        r5 = com.android.camera.CameraSettings.isFrontCamera();
        if (r5 == 0) goto L_0x0077;
    L_0x0075:
        r5 = r3;
        goto L_0x0081;
    L_0x0077:
        r5 = com.android.camera.Util.UI_DEBUG();
        if (r5 == 0) goto L_0x0080;
    L_0x007e:
        r5 = r2;
        goto L_0x0081;
    L_0x0080:
        r5 = r1;
        r6 = 1;
        r7 = 0;
        if (r5 == r1) goto L_0x00ca;
    L_0x0087:
        switch(r5) {
            case 2: goto L_0x00c3;
            case 3: goto L_0x008e;
            case 4: goto L_0x008e;
            default: goto L_0x008a;
        };
    L_0x008a:
        r9 = r6;
        r0 = r7;
        r8 = r0;
        goto L_0x00cf;
    L_0x008e:
        r8 = com.android.camera.Util.UI_DEBUG();
        r9 = 2130837695; // 0x7f0200bf float:1.7280351E38 double:1.052773702E-314;
        if (r8 != 0) goto L_0x0098;
    L_0x0097:
        goto L_0x00bc;
    L_0x0098:
        r8 = r11.mCurrentMode;
        if (r8 == r0) goto L_0x00af;
    L_0x009c:
        r0 = com.android.camera.data.DataRepository.dataItemConfig();
        r0 = r0.getComponentConfigBeauty();
        r8 = r11.mCurrentMode;
        r0 = r0.getComponentValue(r8);
        r0 = com.android.camera.CameraSettings.isFaceBeautyOn(r0);
        goto L_0x00b4;
    L_0x00af:
        r0 = com.android.camera.CameraSettings.isLiveBeautyOpen();
    L_0x00b4:
        if (r0 == 0) goto L_0x00bb;
    L_0x00b6:
        r0 = 2130837696; // 0x7f0200c0 float:1.7280353E38 double:1.0527737025E-314;
        r9 = r0;
        goto L_0x00bc;
    L_0x00bc:
        r0 = 2131296500; // 0x7f0900f4 float:1.8210918E38 double:1.0530003818E-314;
        r8 = r0;
        r0 = r9;
        r9 = r6;
        goto L_0x00cf;
    L_0x00c3:
        r0 = 2130837698; // 0x7f0200c2 float:1.7280357E38 double:1.0527737034E-314;
        r9 = r6;
        r8 = r7;
        goto L_0x00cf;
        r0 = r7;
        r8 = r0;
        r9 = r8;
    L_0x00cf:
        if (r0 <= 0) goto L_0x00d6;
    L_0x00d1:
        r10 = r11.mTipImage;
        r10.setImageResource(r0);
    L_0x00d6:
        if (r8 <= 0) goto L_0x00e7;
    L_0x00d8:
        r0 = com.android.camera.Util.isAccessible();
        if (r0 == 0) goto L_0x00e7;
    L_0x00de:
        r0 = r11.mTipImage;
        r8 = r11.getString(r8);
        r0.setContentDescription(r8);
    L_0x00e7:
        r0 = r11.mTipImage;
        r11.updateImageBgColor(r12, r0, r7);
        r12 = r11.mTipImage;
        r12 = r12.getTag();
        if (r12 == 0) goto L_0x0103;
    L_0x00f4:
        r12 = r11.mTipImage;
        r12 = r12.getTag();
        r12 = (java.lang.Integer) r12;
        r12 = r12.intValue();
        if (r12 != r5) goto L_0x0103;
    L_0x0102:
        return;
    L_0x0103:
        if (r9 == 0) goto L_0x010d;
    L_0x0105:
        r12 = r11.mTipImage;
        r0 = r11.mDegree;
        r0 = (float) r0;
        android.support.v4.view.ViewCompat.setRotation(r12, r0);
    L_0x010d:
        r12 = r11.mTipImage;
        r0 = java.lang.Integer.valueOf(r5);
        r12.setTag(r0);
        if (r14 != 0) goto L_0x0120;
    L_0x0118:
        if (r9 == 0) goto L_0x011d;
    L_0x011b:
        r2 = r7;
        goto L_0x0139;
    L_0x011e:
        r2 = r6;
        goto L_0x0139;
    L_0x0120:
        if (r9 == 0) goto L_0x0130;
    L_0x0122:
        if (r13 != r4) goto L_0x012e;
    L_0x0124:
        r12 = com.mi.config.b.isSupportedOpticalZoom();
        if (r12 == 0) goto L_0x012b;
    L_0x012a:
        goto L_0x011b;
        r2 = r3;
        goto L_0x0139;
    L_0x012e:
        r2 = 2;
        goto L_0x0139;
    L_0x0130:
        if (r13 == r4) goto L_0x0138;
    L_0x0132:
        r12 = r11.mCurrentMode;
        if (r12 != r4) goto L_0x0137;
    L_0x0136:
        goto L_0x0138;
    L_0x0137:
        goto L_0x0139;
    L_0x0138:
        goto L_0x011e;
    L_0x0139:
        switch(r2) {
            case 0: goto L_0x017a;
            case 1: goto L_0x0174;
            case 2: goto L_0x0165;
            case 3: goto L_0x014c;
            case 4: goto L_0x013d;
            default: goto L_0x013c;
        };
    L_0x013c:
        goto L_0x01a2;
    L_0x013d:
        r12 = new com.android.camera.animation.type.AlphaOutOnSubscribe;
        r13 = r11.mTipImage;
        r12.<init>(r13);
        r12 = io.reactivex.Completable.create(r12);
        r14.add(r12);
        goto L_0x01a2;
    L_0x014c:
        r12 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r13 = r11.mTipImage;
        r12.<init>(r13);
        r13 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r12 = r12.setStartDelayTime(r13);
        r12 = r12.setDurationTime(r13);
        r12 = io.reactivex.Completable.create(r12);
        r14.add(r12);
        goto L_0x01a2;
    L_0x0165:
        r12 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r13 = r11.mTipImage;
        r12.<init>(r13);
        r12 = io.reactivex.Completable.create(r12);
        r14.add(r12);
        goto L_0x01a2;
    L_0x0174:
        r12 = r11.mTipImage;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r12);
        goto L_0x01a2;
    L_0x017a:
        r12 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r13 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        r12 = r12.getAttachProtocol(r13);
        r12 = (com.android.camera.protocol.ModeProtocol.ActionProcessing) r12;
        if (r12 == 0) goto L_0x018f;
    L_0x0188:
        r12 = r12.isShowLightingView();
        if (r12 == 0) goto L_0x018f;
    L_0x018e:
        goto L_0x0190;
    L_0x018f:
        r6 = r7;
    L_0x0190:
        if (r6 == 0) goto L_0x019b;
    L_0x0192:
        r12 = r11.mTipImage;
        r13 = java.lang.Integer.valueOf(r1);
        r12.setTag(r13);
    L_0x019b:
        if (r6 != 0) goto L_0x01a2;
    L_0x019d:
        r12 = r11.mTipImage;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r12);
    L_0x01a2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.updateTipImage(int, int, java.util.List):void");
    }

    private void updateImageBgColor(int i, View view, boolean z) {
        int i2;
        int i3;
        if (z) {
            i2 = 2130837511;
            i3 = 2130837965;
        } else {
            i2 = 2130837514;
            i3 = 2130837966;
        }
        if (i != 165) {
            view.setBackgroundResource(i2);
        } else {
            view.setBackgroundResource(i3);
        }
    }

    /* JADX WARNING: Missing block: B:6:0x003a, code skipped:
            if (com.android.camera.data.DataRepository.dataItemFeature().fb() != false) goto L_0x0076;
     */
    /* JADX WARNING: Missing block: B:8:0x0045, code skipped:
            if (com.android.camera.data.DataRepository.dataItemFeature().fa() != false) goto L_0x0076;
     */
    /* JADX WARNING: Missing block: B:13:0x0054, code skipped:
            if (com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn() != false) goto L_0x0075;
     */
    /* JADX WARNING: Missing block: B:27:0x0073, code skipped:
            r8 = 21;
     */
    /* JADX WARNING: Missing block: B:28:0x0075, code skipped:
            r8 = -1;
     */
    /* JADX WARNING: Missing block: B:29:0x0076, code skipped:
            r8 = checkLeftImageTipClose(r8);
            r0 = true;
     */
    /* JADX WARNING: Missing block: B:30:0x007f, code skipped:
            if (r8 == -1) goto L_0x00c6;
     */
    /* JADX WARNING: Missing block: B:31:0x0081, code skipped:
            if (r8 == 21) goto L_0x00a9;
     */
    /* JADX WARNING: Missing block: B:32:0x0083, code skipped:
            switch(r8) {
                case 18: goto L_0x0093;
                case 19: goto L_0x008a;
                default: goto L_0x0086;
            };
     */
    /* JADX WARNING: Missing block: B:33:0x0086, code skipped:
            r4 = 1;
            r2 = 0;
            r3 = r2;
     */
    /* JADX WARNING: Missing block: B:34:0x008a, code skipped:
            r2 = 2130837753;
            r3 = 2131296870;
            r4 = 1;
     */
    /* JADX WARNING: Missing block: B:36:0x009d, code skipped:
            if ("".equals(com.android.camera.CameraSettings.getCurrentLiveSticker()) == false) goto L_0x00a5;
     */
    /* JADX WARNING: Missing block: B:37:0x009f, code skipped:
            r2 = 2130837793;
     */
    /* JADX WARNING: Missing block: B:38:0x00a2, code skipped:
            r4 = 1;
            r3 = 0;
     */
    /* JADX WARNING: Missing block: B:39:0x00a5, code skipped:
            r2 = 2130837794;
     */
    /* JADX WARNING: Missing block: B:40:0x00a9, code skipped:
            r2 = com.android.camera.CameraSettings.isUltraWideConfigOpen(r7.mCurrentMode);
     */
    /* JADX WARNING: Missing block: B:41:0x00af, code skipped:
            if (r2 == false) goto L_0x00b5;
     */
    /* JADX WARNING: Missing block: B:42:0x00b1, code skipped:
            r3 = 2130837883;
     */
    /* JADX WARNING: Missing block: B:43:0x00b5, code skipped:
            r3 = 2130837882;
     */
    /* JADX WARNING: Missing block: B:44:0x00b8, code skipped:
            if (r2 == false) goto L_0x00be;
     */
    /* JADX WARNING: Missing block: B:45:0x00ba, code skipped:
            r2 = 2131296840;
     */
    /* JADX WARNING: Missing block: B:46:0x00be, code skipped:
            r2 = 2131296841;
     */
    /* JADX WARNING: Missing block: B:47:0x00c1, code skipped:
            r4 = 1;
            r6 = r3;
            r3 = r2;
            r2 = r6;
     */
    /* JADX WARNING: Missing block: B:48:0x00c6, code skipped:
            r2 = 0;
            r3 = r2;
            r4 = r3;
     */
    /* JADX WARNING: Missing block: B:49:0x00cb, code skipped:
            if (r2 <= 0) goto L_0x00d2;
     */
    /* JADX WARNING: Missing block: B:50:0x00cd, code skipped:
            r7.mLeftTipImage.setImageResource(r2);
     */
    /* JADX WARNING: Missing block: B:51:0x00d2, code skipped:
            if (r3 <= 0) goto L_0x00e3;
     */
    /* JADX WARNING: Missing block: B:53:0x00d8, code skipped:
            if (com.android.camera.Util.isAccessible() == false) goto L_0x00e3;
     */
    /* JADX WARNING: Missing block: B:54:0x00da, code skipped:
            r7.mLeftTipImage.setContentDescription(getString(r3));
     */
    /* JADX WARNING: Missing block: B:55:0x00e3, code skipped:
            updateImageBgColor(r9, r7.mLeftTipImage, false);
     */
    /* JADX WARNING: Missing block: B:56:0x00ee, code skipped:
            if (r7.mLeftTipImage.getTag() == null) goto L_0x00ff;
     */
    /* JADX WARNING: Missing block: B:58:0x00fc, code skipped:
            if (((java.lang.Integer) r7.mLeftTipImage.getTag()).intValue() != r8) goto L_0x00ff;
     */
    /* JADX WARNING: Missing block: B:59:0x00fe, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:60:0x00ff, code skipped:
            if (r4 == 0) goto L_0x0109;
     */
    /* JADX WARNING: Missing block: B:61:0x0101, code skipped:
            android.support.v4.view.ViewCompat.setRotation(r7.mLeftTipImage, (float) r7.mDegree);
     */
    /* JADX WARNING: Missing block: B:62:0x0109, code skipped:
            r7.mLeftTipImage.setTag(java.lang.Integer.valueOf(r8));
     */
    /* JADX WARNING: Missing block: B:63:0x0112, code skipped:
            if (r11 != null) goto L_0x011a;
     */
    /* JADX WARNING: Missing block: B:64:0x0114, code skipped:
            if (r4 == 0) goto L_0x013b;
     */
    /* JADX WARNING: Missing block: B:65:0x0116, code skipped:
            r0 = false;
     */
    /* JADX WARNING: Missing block: B:67:0x011c, code skipped:
            if (r4 == 0) goto L_0x0131;
     */
    /* JADX WARNING: Missing block: B:68:0x011e, code skipped:
            if (r10 != 165) goto L_0x012b;
     */
    /* JADX WARNING: Missing block: B:70:0x0124, code skipped:
            if (com.mi.config.b.isSupportedOpticalZoom() == false) goto L_0x0129;
     */
    /* JADX WARNING: Missing block: B:71:0x0126, code skipped:
            r0 = false;
     */
    /* JADX WARNING: Missing block: B:72:0x0129, code skipped:
            r8 = true;
     */
    /* JADX WARNING: Missing block: B:73:0x012b, code skipped:
            r8 = true;
     */
    /* JADX WARNING: Missing block: B:74:0x012c, code skipped:
            r0 = r8;
     */
    /* JADX WARNING: Missing block: B:75:0x012d, code skipped:
            directHideLeftImageIntro();
     */
    /* JADX WARNING: Missing block: B:76:0x0131, code skipped:
            if (r10 == 165) goto L_0x013b;
     */
    /* JADX WARNING: Missing block: B:78:0x0135, code skipped:
            if (r7.mCurrentMode != 165) goto L_0x0138;
     */
    /* JADX WARNING: Missing block: B:79:0x0138, code skipped:
            r0 = true;
     */
    /* JADX WARNING: Missing block: B:80:0x013b, code skipped:
            switch(r0) {
                case 0: goto L_0x017c;
                case 1: goto L_0x0176;
                case 2: goto L_0x0167;
                case 3: goto L_0x014e;
                case 4: goto L_0x013f;
                default: goto L_0x013e;
            };
     */
    /* JADX WARNING: Missing block: B:81:0x013f, code skipped:
            r11.add(io.reactivex.Completable.create(new com.android.camera.animation.type.AlphaOutOnSubscribe(r7.mLeftTipImage)));
     */
    /* JADX WARNING: Missing block: B:82:0x014e, code skipped:
            r11.add(io.reactivex.Completable.create(new com.android.camera.animation.type.AlphaInOnSubscribe(r7.mLeftTipImage).setStartDelayTime(150).setDurationTime(150)));
     */
    /* JADX WARNING: Missing block: B:83:0x0167, code skipped:
            r11.add(io.reactivex.Completable.create(new com.android.camera.animation.type.AlphaInOnSubscribe(r7.mLeftTipImage)));
     */
    /* JADX WARNING: Missing block: B:84:0x0176, code skipped:
            com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r7.mLeftTipImage);
     */
    /* JADX WARNING: Missing block: B:85:0x017c, code skipped:
            com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r7.mLeftTipImage);
     */
    /* JADX WARNING: Missing block: B:86:0x0182, code skipped:
            return;
     */
    private void updateLeftTipImage(int r8, int r9, int r10, java.util.List<io.reactivex.Completable> r11) {
        /*
        r7 = this;
        r0 = com.android.camera.data.DataRepository.dataItemGlobal();
        r0 = r0.getCurrentCameraId();
        r1 = com.android.camera.data.DataRepository.dataItemGlobal();
        r1 = r1.isNormalIntent();
        r2 = com.android.camera.data.DataRepository.dataItemFeature();
        r2 = r2.isSupportUltraWide();
        r3 = com.android.camera.module.loader.camera2.Camera2DataContainer.getInstance();
        r4 = r7.mCurrentMode;
        r3 = r3.getCapabilitiesByBogusCameraId(r0, r4);
        r4 = -1;
        r5 = 21;
        switch(r9) {
            case 161: goto L_0x0066;
            case 162: goto L_0x0057;
            case 163: goto L_0x004a;
            case 164: goto L_0x0029;
            case 165: goto L_0x0066;
            case 166: goto L_0x0029;
            case 167: goto L_0x0029;
            case 168: goto L_0x0057;
            case 169: goto L_0x0057;
            case 170: goto L_0x0057;
            case 171: goto L_0x002a;
            case 172: goto L_0x0029;
            case 173: goto L_0x0029;
            case 174: goto L_0x004a;
            default: goto L_0x0029;
        };
    L_0x0029:
        goto L_0x0075;
    L_0x002a:
        if (r1 == 0) goto L_0x0075;
    L_0x002c:
        r8 = 19;
        switch(r0) {
            case 0: goto L_0x003d;
            case 1: goto L_0x0032;
            default: goto L_0x0031;
        };
    L_0x0031:
        goto L_0x0048;
    L_0x0032:
        r0 = com.android.camera.data.DataRepository.dataItemFeature();
        r0 = r0.fb();
        if (r0 == 0) goto L_0x0048;
    L_0x003c:
        goto L_0x0049;
    L_0x003d:
        r0 = com.android.camera.data.DataRepository.dataItemFeature();
        r0 = r0.fa();
        if (r0 == 0) goto L_0x0048;
    L_0x0047:
        goto L_0x0049;
    L_0x0048:
        r8 = r4;
    L_0x0049:
        goto L_0x0076;
    L_0x004a:
        r1 = com.android.camera.CameraSettings.isSupportedUltraPixelPhotography(r3);
        if (r1 == 0) goto L_0x0066;
    L_0x0050:
        r1 = com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn();
        if (r1 == 0) goto L_0x0066;
    L_0x0056:
        goto L_0x0075;
    L_0x0057:
        if (r0 != 0) goto L_0x0075;
    L_0x0059:
        if (r1 == 0) goto L_0x0075;
    L_0x005b:
        if (r2 == 0) goto L_0x0075;
    L_0x005d:
        r0 = r7.mIsShowLeftImageIntro;
        if (r0 == 0) goto L_0x0065;
    L_0x0061:
        r7.startLeftImageIntroAnim(r8);
        return;
    L_0x0065:
        goto L_0x0073;
    L_0x0066:
        if (r2 == 0) goto L_0x0075;
    L_0x0068:
        if (r0 != 0) goto L_0x0075;
    L_0x006a:
        r0 = r7.mIsShowLeftImageIntro;
        if (r0 == 0) goto L_0x0072;
    L_0x006e:
        r7.startLeftImageIntroAnim(r8);
        return;
    L_0x0073:
        r8 = r5;
        goto L_0x0076;
    L_0x0075:
        r8 = r4;
    L_0x0076:
        r8 = r7.checkLeftImageTipClose(r8);
        r0 = 1;
        r1 = 0;
        if (r8 == r4) goto L_0x00c6;
    L_0x0081:
        if (r8 == r5) goto L_0x00a9;
    L_0x0083:
        switch(r8) {
            case 18: goto L_0x0093;
            case 19: goto L_0x008a;
            default: goto L_0x0086;
        };
    L_0x0086:
        r4 = r0;
        r2 = r1;
        r3 = r2;
        goto L_0x00cb;
    L_0x008a:
        r2 = 2130837753; // 0x7f0200f9 float:1.7280469E38 double:1.0527737306E-314;
        r3 = 2131296870; // 0x7f090266 float:1.8211669E38 double:1.0530005646E-314;
        r4 = r0;
        goto L_0x00cb;
    L_0x0093:
        r2 = com.android.camera.CameraSettings.getCurrentLiveSticker();
        r3 = "";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x00a5;
    L_0x009f:
        r2 = 2130837793; // 0x7f020121 float:1.728055E38 double:1.0527737504E-314;
    L_0x00a2:
        r4 = r0;
        r3 = r1;
        goto L_0x00cb;
    L_0x00a5:
        r2 = 2130837794; // 0x7f020122 float:1.7280552E38 double:1.052773751E-314;
        goto L_0x00a2;
    L_0x00a9:
        r2 = r7.mCurrentMode;
        r2 = com.android.camera.CameraSettings.isUltraWideConfigOpen(r2);
        if (r2 == 0) goto L_0x00b5;
    L_0x00b1:
        r3 = 2130837883; // 0x7f02017b float:1.7280733E38 double:1.052773795E-314;
        goto L_0x00b8;
    L_0x00b5:
        r3 = 2130837882; // 0x7f02017a float:1.728073E38 double:1.0527737944E-314;
    L_0x00b8:
        if (r2 == 0) goto L_0x00be;
    L_0x00ba:
        r2 = 2131296840; // 0x7f090248 float:1.8211608E38 double:1.0530005497E-314;
        goto L_0x00c1;
    L_0x00be:
        r2 = 2131296841; // 0x7f090249 float:1.821161E38 double:1.05300055E-314;
    L_0x00c1:
        r4 = r0;
        r6 = r3;
        r3 = r2;
        r2 = r6;
        goto L_0x00cb;
        r2 = r1;
        r3 = r2;
        r4 = r3;
    L_0x00cb:
        if (r2 <= 0) goto L_0x00d2;
    L_0x00cd:
        r5 = r7.mLeftTipImage;
        r5.setImageResource(r2);
    L_0x00d2:
        if (r3 <= 0) goto L_0x00e3;
    L_0x00d4:
        r2 = com.android.camera.Util.isAccessible();
        if (r2 == 0) goto L_0x00e3;
    L_0x00da:
        r2 = r7.mLeftTipImage;
        r3 = r7.getString(r3);
        r2.setContentDescription(r3);
    L_0x00e3:
        r2 = r7.mLeftTipImage;
        r7.updateImageBgColor(r9, r2, r1);
        r9 = r7.mLeftTipImage;
        r9 = r9.getTag();
        if (r9 == 0) goto L_0x00ff;
    L_0x00f0:
        r9 = r7.mLeftTipImage;
        r9 = r9.getTag();
        r9 = (java.lang.Integer) r9;
        r9 = r9.intValue();
        if (r9 != r8) goto L_0x00ff;
    L_0x00fe:
        return;
    L_0x00ff:
        if (r4 == 0) goto L_0x0109;
    L_0x0101:
        r9 = r7.mLeftTipImage;
        r2 = r7.mDegree;
        r2 = (float) r2;
        android.support.v4.view.ViewCompat.setRotation(r9, r2);
    L_0x0109:
        r9 = r7.mLeftTipImage;
        r8 = java.lang.Integer.valueOf(r8);
        r9.setTag(r8);
        if (r11 != 0) goto L_0x011a;
    L_0x0114:
        if (r4 == 0) goto L_0x0119;
        r0 = r1;
        goto L_0x013b;
    L_0x0119:
        goto L_0x013b;
    L_0x011a:
        r8 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r4 == 0) goto L_0x0131;
    L_0x011e:
        if (r10 != r8) goto L_0x012b;
    L_0x0120:
        r8 = com.mi.config.b.isSupportedOpticalZoom();
        if (r8 == 0) goto L_0x0129;
        r0 = r1;
        goto L_0x012d;
    L_0x0129:
        r8 = 3;
        goto L_0x012c;
    L_0x012b:
        r8 = 2;
    L_0x012c:
        r0 = r8;
    L_0x012d:
        r7.directHideLeftImageIntro();
        goto L_0x013b;
    L_0x0131:
        if (r10 == r8) goto L_0x013a;
    L_0x0133:
        r9 = r7.mCurrentMode;
        if (r9 != r8) goto L_0x0138;
    L_0x0137:
        goto L_0x013a;
    L_0x0138:
        r0 = 4;
        goto L_0x013b;
    L_0x013b:
        switch(r0) {
            case 0: goto L_0x017c;
            case 1: goto L_0x0176;
            case 2: goto L_0x0167;
            case 3: goto L_0x014e;
            case 4: goto L_0x013f;
            default: goto L_0x013e;
        };
    L_0x013e:
        goto L_0x0182;
    L_0x013f:
        r8 = new com.android.camera.animation.type.AlphaOutOnSubscribe;
        r9 = r7.mLeftTipImage;
        r8.<init>(r9);
        r8 = io.reactivex.Completable.create(r8);
        r11.add(r8);
        goto L_0x0182;
    L_0x014e:
        r8 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r9 = r7.mLeftTipImage;
        r8.<init>(r9);
        r9 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r8 = r8.setStartDelayTime(r9);
        r8 = r8.setDurationTime(r9);
        r8 = io.reactivex.Completable.create(r8);
        r11.add(r8);
        goto L_0x0182;
    L_0x0167:
        r8 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r9 = r7.mLeftTipImage;
        r8.<init>(r9);
        r8 = io.reactivex.Completable.create(r8);
        r11.add(r8);
        goto L_0x0182;
    L_0x0176:
        r8 = r7.mLeftTipImage;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r8);
        goto L_0x0182;
    L_0x017c:
        r8 = r7.mLeftTipImage;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r8);
    L_0x0182:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.updateLeftTipImage(int, int, int, java.util.List):void");
    }

    private int checkLeftImageTipClose(int i) {
        ActionProcessing actionProcessing = (ActionProcessing) ModeCoordinatorImpl.getInstance().getAttachProtocol(162);
        if (actionProcessing == null || !actionProcessing.isShowLightingView()) {
            return i;
        }
        return 20;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008c  */
    /* JADX WARNING: Missing block: B:25:0x0067, code skipped:
            if (r1 != false) goto L_0x006a;
     */
    private void updateCenterTipImage(int r7, int r8, java.util.List<io.reactivex.Completable> r9) {
        /*
        r6 = this;
        r0 = 18;
        r1 = -1;
        r2 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        if (r7 == r2) goto L_0x000a;
    L_0x0008:
        r2 = r1;
        goto L_0x000c;
        r2 = r0;
        r3 = 1;
        r4 = 0;
        if (r2 == r1) goto L_0x002b;
    L_0x0011:
        if (r2 == r0) goto L_0x0016;
    L_0x0013:
        r1 = r3;
        r0 = r4;
        goto L_0x002f;
    L_0x0016:
        r0 = com.android.camera.CameraSettings.getCurrentLiveSticker();
        r1 = "";
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x0027;
    L_0x0022:
        r0 = 2130837793; // 0x7f020121 float:1.728055E38 double:1.0527737504E-314;
    L_0x0025:
        r1 = r3;
        goto L_0x002f;
    L_0x0027:
        r0 = 2130837794; // 0x7f020122 float:1.7280552E38 double:1.052773751E-314;
        goto L_0x0025;
        r0 = r4;
        r1 = r0;
    L_0x002f:
        if (r0 <= 0) goto L_0x0036;
    L_0x0031:
        r5 = r6.mCenterTipImage;
        r5.setImageResource(r0);
    L_0x0036:
        r0 = r6.mCenterTipImage;
        r6.updateImageBgColor(r7, r0, r3);
        r7 = r6.mCenterTipImage;
        r7 = r7.getTag();
        if (r7 == 0) goto L_0x0052;
    L_0x0043:
        r7 = r6.mCenterTipImage;
        r7 = r7.getTag();
        r7 = (java.lang.Integer) r7;
        r7 = r7.intValue();
        if (r7 != r2) goto L_0x0052;
    L_0x0051:
        return;
    L_0x0052:
        if (r1 == 0) goto L_0x005c;
    L_0x0054:
        r7 = r6.mCenterTipImage;
        r0 = r6.mDegree;
        r0 = (float) r0;
        android.support.v4.view.ViewCompat.setRotation(r7, r0);
    L_0x005c:
        r7 = r6.mCenterTipImage;
        r0 = java.lang.Integer.valueOf(r2);
        r7.setTag(r0);
        if (r9 != 0) goto L_0x006d;
    L_0x0067:
        if (r1 == 0) goto L_0x006c;
    L_0x006a:
        r3 = r4;
        goto L_0x0088;
    L_0x006c:
        goto L_0x0088;
    L_0x006d:
        r7 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r1 == 0) goto L_0x007e;
    L_0x0071:
        if (r8 != r7) goto L_0x007c;
    L_0x0073:
        r7 = com.mi.config.b.isSupportedOpticalZoom();
        if (r7 == 0) goto L_0x007a;
    L_0x0079:
        goto L_0x006a;
    L_0x007a:
        r3 = 3;
        goto L_0x0088;
    L_0x007c:
        r3 = 2;
        goto L_0x0088;
    L_0x007e:
        if (r8 == r7) goto L_0x0087;
    L_0x0080:
        r8 = r6.mCurrentMode;
        if (r8 != r7) goto L_0x0085;
    L_0x0084:
        goto L_0x0087;
    L_0x0085:
        r3 = 4;
        goto L_0x0088;
    L_0x0088:
        switch(r3) {
            case 0: goto L_0x00c9;
            case 1: goto L_0x00c3;
            case 2: goto L_0x00b4;
            case 3: goto L_0x009b;
            case 4: goto L_0x008c;
            default: goto L_0x008b;
        };
    L_0x008b:
        goto L_0x00cf;
    L_0x008c:
        r7 = new com.android.camera.animation.type.AlphaOutOnSubscribe;
        r8 = r6.mCenterTipImage;
        r7.<init>(r8);
        r7 = io.reactivex.Completable.create(r7);
        r9.add(r7);
        goto L_0x00cf;
    L_0x009b:
        r7 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r8 = r6.mLeftTipImage;
        r7.<init>(r8);
        r8 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r7 = r7.setStartDelayTime(r8);
        r7 = r7.setDurationTime(r8);
        r7 = io.reactivex.Completable.create(r7);
        r9.add(r7);
        goto L_0x00cf;
    L_0x00b4:
        r7 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r8 = r6.mCenterTipImage;
        r7.<init>(r8);
        r7 = io.reactivex.Completable.create(r7);
        r9.add(r7);
        goto L_0x00cf;
    L_0x00c3:
        r7 = r6.mCenterTipImage;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r7);
        goto L_0x00cf;
    L_0x00c9:
        r7 = r6.mCenterTipImage;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r7);
    L_0x00cf:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.updateCenterTipImage(int, int, java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00aa  */
    /* JADX WARNING: Missing block: B:30:0x008e, code skipped:
            if (r4 != 0) goto L_0x0091;
     */
    private void updateSpeedTipImage(int r11, int r12, java.util.List<io.reactivex.Completable> r13) {
        /*
        r10 = this;
        r0 = -1;
        r1 = 33;
        r2 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        if (r11 == r2) goto L_0x000b;
    L_0x0009:
        r11 = r0;
        goto L_0x000d;
        r11 = r1;
        r2 = 1;
        r3 = 0;
        if (r11 == r0) goto L_0x001f;
    L_0x0012:
        if (r11 == r1) goto L_0x0018;
    L_0x0014:
        r4 = r2;
        r5 = r4;
        r0 = r3;
        goto L_0x0024;
    L_0x0018:
        r0 = 2130968579; // 0x7f040003 float:1.7545816E38 double:1.0528383673E-314;
        r4 = r2;
        r5 = r3;
        goto L_0x0024;
        r5 = r2;
        r0 = r3;
        r4 = r0;
    L_0x0024:
        r6 = r10.mSpeedTipImage;
        r6 = r6.getTag();
        if (r6 == 0) goto L_0x003d;
    L_0x002c:
        r6 = r10.mSpeedTipImage;
        r6 = r6.getTag();
        r6 = (java.lang.Integer) r6;
        r6 = r6.intValue();
        if (r6 != r11) goto L_0x003d;
        r6 = r3;
        goto L_0x003e;
    L_0x003d:
        r6 = r2;
    L_0x003e:
        if (r6 == 0) goto L_0x0059;
    L_0x0040:
        r7 = r10.mSpeedTipImage;
        r7.removeAllViews();
        if (r0 <= 0) goto L_0x0059;
    L_0x0047:
        r7 = r10.mSpeedTipImage;
        r8 = r10.getContext();
        r8 = android.view.LayoutInflater.from(r8);
        r9 = 0;
        r0 = r8.inflate(r0, r9);
        r7.addView(r0);
    L_0x0059:
        if (r11 != r1) goto L_0x006d;
    L_0x005b:
        r0 = com.android.camera.CameraSettings.getCurrentLiveSpeedText();
        r1 = r10.mSpeedTipImage;
        r7 = 2131558416; // 0x7f0d0010 float:1.8742147E38 double:1.0531297854E-314;
        r1 = r1.findViewById(r7);
        r1 = (android.widget.TextView) r1;
        r1.setText(r0);
    L_0x006d:
        if (r6 != 0) goto L_0x0070;
    L_0x006f:
        return;
    L_0x0070:
        if (r4 == 0) goto L_0x007d;
    L_0x0072:
        if (r5 == 0) goto L_0x007d;
    L_0x0074:
        r0 = r10.mSpeedTipImage;
        r1 = r10.mDegree;
        r1 = (float) r1;
        android.support.v4.view.ViewCompat.setRotation(r0, r1);
        goto L_0x0083;
    L_0x007d:
        r0 = r10.mSpeedTipImage;
        r1 = 0;
        android.support.v4.view.ViewCompat.setRotation(r0, r1);
    L_0x0083:
        r0 = r10.mSpeedTipImage;
        r11 = java.lang.Integer.valueOf(r11);
        r0.setTag(r11);
        if (r13 != 0) goto L_0x0094;
    L_0x008e:
        if (r4 == 0) goto L_0x0093;
    L_0x0091:
        r2 = r3;
        goto L_0x00a6;
    L_0x0093:
        goto L_0x00a6;
    L_0x0094:
        if (r4 == 0) goto L_0x00a5;
    L_0x0096:
        r11 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        if (r12 != r11) goto L_0x00a3;
    L_0x009a:
        r11 = com.mi.config.b.isSupportedOpticalZoom();
        if (r11 == 0) goto L_0x00a1;
    L_0x00a0:
        goto L_0x0091;
    L_0x00a1:
        r2 = 3;
        goto L_0x00a6;
    L_0x00a3:
        r2 = 2;
        goto L_0x00a6;
    L_0x00a5:
        r2 = 4;
    L_0x00a6:
        switch(r2) {
            case 0: goto L_0x00e7;
            case 1: goto L_0x00e1;
            case 2: goto L_0x00d2;
            case 3: goto L_0x00b9;
            case 4: goto L_0x00aa;
            default: goto L_0x00a9;
        };
    L_0x00a9:
        goto L_0x00ed;
    L_0x00aa:
        r11 = new com.android.camera.animation.type.AlphaOutOnSubscribe;
        r12 = r10.mSpeedTipImage;
        r11.<init>(r12);
        r11 = io.reactivex.Completable.create(r11);
        r13.add(r11);
        goto L_0x00ed;
    L_0x00b9:
        r11 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r12 = r10.mSpeedTipImage;
        r11.<init>(r12);
        r12 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r11 = r11.setStartDelayTime(r12);
        r11 = r11.setDurationTime(r12);
        r11 = io.reactivex.Completable.create(r11);
        r13.add(r11);
        goto L_0x00ed;
    L_0x00d2:
        r11 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r12 = r10.mSpeedTipImage;
        r11.<init>(r12);
        r11 = io.reactivex.Completable.create(r11);
        r13.add(r11);
        goto L_0x00ed;
    L_0x00e1:
        r11 = r10.mSpeedTipImage;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r11);
        goto L_0x00ed;
    L_0x00e7:
        r11 = r10.mSpeedTipImage;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r11);
    L_0x00ed:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentBottomPopupTips.updateSpeedTipImage(int, int, java.util.List):void");
    }

    /* Access modifiers changed, original: protected */
    public Animation provideEnterAnimation(int i) {
        if (i == 240 || i == getFragmentInto()) {
            return null;
        }
        return FragmentAnimationFactory.wrapperAnimation(161);
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        if (this.mTipImage.getVisibility() == 0) {
            list.add(this.mTipImage);
        }
        if (Util.UI_DEBUG()) {
            if (this.mLeftTipImage != null && this.mLeftTipImage.getVisibility() == 0) {
                list.add(this.mLeftTipImage);
            }
            if (this.mCenterTipImage != null && this.mCenterTipImage.getVisibility() == 0) {
                list.add(this.mCenterTipImage);
            }
            if (!(this.mSpeedTipImage == null || this.mSpeedTipImage.getVisibility() != 0 || ((Integer) this.mSpeedTipImage.getTag()).intValue() == 33)) {
                list.add(this.mSpeedTipImage);
            }
            if (this.mCenterTipImage != null && this.mCenterTipImage.getVisibility() == 0) {
                list.add(this.mCenterTipImage);
            }
        }
        updateLightingPattern(false, true);
    }

    private void updateLightingPattern(boolean z, boolean z2) {
        if (z) {
            this.stringLightingRes = -2;
        }
        if (!DataRepository.dataItemRunning().getComponentRunningLighting().getComponentValue(171).equals("0")) {
            if (isLandScape()) {
                starAnimatetViewGone(this.mLightingPattern, z2);
            } else if (this.stringLightingRes > 0) {
                startAnimateViewVisible(this.mLightingPattern, z2);
            }
        }
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        switch (i) {
            case 2:
                directlyHideTips();
                break;
            case 3:
                adjustViewBackground(this.mCurrentMode);
                break;
        }
        updateTipImage(this.mCurrentMode, this.mCurrentMode, null);
        if (Util.UI_DEBUG()) {
            updateLeftTipImage(1, this.mCurrentMode, this.mCurrentMode, null);
            updateSpeedTipImage(this.mCurrentMode, this.mCurrentMode, null);
            updateCenterTipImage(this.mCurrentMode, this.mCurrentMode, null);
        }
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
    }

    private void adjustViewBackground(int i) {
        i = DataRepository.dataItemRunning().getUiStyle();
        if (i == 1 || i == 3) {
            this.mQrCodeButton.setBackgroundResource(2130837530);
        } else {
            this.mQrCodeButton.setBackgroundResource(2130837529);
        }
    }

    public void showCloseTip(int i, boolean z) {
        if (Util.UI_DEBUG()) {
            if (!z) {
                this.mCloseType = 0;
            } else if (this.mCurrentMode != 167) {
                this.mCloseType = i;
            } else {
                return;
            }
            showOrHideCloseImage(z);
        } else if (!z) {
            this.mCloseIv.setVisibility(8);
            this.mCloseType = 0;
        } else if (this.mCurrentMode != 167) {
            this.mCloseType = i;
            this.mCloseIv.setVisibility(0);
        }
    }

    public void hideLeftTipImage() {
        if (this.mLeftTipImage != null && this.mLeftTipImage.getVisibility() != 4) {
            this.mLeftTipImage.setTag(Integer.valueOf(-1));
            Completable.create(new AlphaOutOnSubscribe(this.mLeftTipImage)).subscribe();
        }
    }

    public void hideSpeedTipImage() {
        if (this.mSpeedTipImage != null && this.mSpeedTipImage.getVisibility() != 4) {
            this.mSpeedTipImage.setTag(Integer.valueOf(-1));
            Completable.create(new AlphaOutOnSubscribe(this.mSpeedTipImage)).subscribe();
        }
    }

    public void hideCenterTipImage() {
        if (this.mCenterTipImage != null && this.mCenterTipImage.getVisibility() != 4) {
            this.mCenterTipImage.setTag(Integer.valueOf(-1));
            Completable.create(new AlphaOutOnSubscribe(this.mCenterTipImage)).subscribe();
        }
    }

    public void selectBeautyTipImage(boolean z) {
        if (z) {
            this.mTipImage.setImageResource(2130837696);
        } else {
            this.mTipImage.setImageResource(2130837695);
        }
    }

    public void directShowOrHideLeftTipImage(boolean z) {
        if (this.mLeftTipImage != null) {
            if (z) {
                updateLeftTipImage();
                this.mLeftTipImage.setVisibility(0);
            } else {
                this.mLeftTipImage.setTag(Integer.valueOf(-1));
                this.mLeftTipImage.setVisibility(4);
            }
        }
    }

    public void showOrHideCloseImage(boolean z) {
        if (this.mLeftTipImage != null) {
            this.mLeftTipImage.setImageResource(2130837797);
            if (z) {
                if (Util.isAccessible() && this.mCloseType == 2) {
                    this.mLeftTipImage.setContentDescription(getString(2131296871));
                }
                this.mLeftTipImage.setTag(Integer.valueOf(20));
                Completable.create(new AlphaInOnSubscribe(this.mLeftTipImage)).subscribe();
            } else {
                this.mLeftTipImage.setTag(Integer.valueOf(-1));
                Completable.create(new AlphaOutOnSubscribe(this.mLeftTipImage)).subscribe();
            }
        }
    }

    public void updateLeftTipImage() {
        updateLeftTipImage(1, this.mCurrentMode, this.mCurrentMode, null);
    }

    private void startLeftImageIntroAnim(int i) {
        if (i != 1) {
            directShowOrHideLeftTipImage(false);
            this.mLeftImageIntro.setVisibility(0);
            if (this.mLeftImageIntroAnimator == null) {
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mLeftImageIntroWidth, this.mLeftImageIntroRadius * 2});
                ofInt.setDuration(300);
                ObjectAnimator.ofFloat(this.mLeftImageIntroContent, "alpha", new float[]{1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO}).setDuration(250);
                ofInt.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        FragmentBottomPopupTips.this.setBeautyIntroButtonWidth(FragmentBottomPopupTips.this.mLeftImageIntro, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                this.mLeftImageIntroAnimator = new AnimatorSet();
                this.mLeftImageIntroAnimator.playTogether(new Animator[]{ofInt, r3});
                this.mLeftImageIntroAnimator.setInterpolator(new PathInterpolator(0.25f, 0.1f, 0.25f, 0.1f));
                this.mLeftImageIntroAnimator.setStartDelay(FragmentTopAlert.HINT_DELAY_TIME);
                this.mLeftImageIntroAnimator.addListener(new AnimatorListenerAdapter() {
                    private boolean cancelled;

                    public void onAnimationStart(Animator animator) {
                        this.cancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        if (FragmentBottomPopupTips.this.canProvide() && !this.cancelled) {
                            FragmentBottomPopupTips.this.directHideLeftImageIntro();
                            FragmentBottomPopupTips.this.updateLeftTipImage();
                        }
                    }
                });
            } else {
                this.mLeftImageIntro.setAlpha(1.0f);
                this.mLeftImageIntroContent.clearAnimation();
                this.mLeftImageIntroAnimator.cancel();
            }
            this.mLeftImageIntroAnimator.start();
            CameraSettings.addPopupUltraWideIntroShowTimes();
        }
    }

    private void directHideLeftImageIntro() {
        this.mIsShowLeftImageIntro = false;
        if (this.mLeftImageIntro.getVisibility() == 0) {
            AlphaOutOnSubscribe.directSetResult(this.mLeftImageIntro);
        }
    }

    public void updateTipImage() {
        updateTipImage(this.mCurrentMode, this.mCurrentMode, null);
    }
}
