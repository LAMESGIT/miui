package com.android.camera.fragment.dual;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import com.android.camera.Camera;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.animation.type.TranslateYAlphaOutOnSubscribe;
import com.android.camera.animation.type.TranslateYOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.config.ComponentManuallyDualLens;
import com.android.camera.data.data.config.ComponentManuallyDualZoom;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.manually.ManuallyListener;
import com.android.camera.fragment.manually.adapter.ExtraSlideZoomAdapter;
import com.android.camera.module.Panorama3Module;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.DualController;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.ManuallyValueChanged;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.SnapShotIndicator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.HorizontalSlideView;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.mi.config.b;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import java.util.List;
import miui.view.animation.BackEaseOutInterpolator;
import miui.view.animation.QuadraticEaseInOutInterpolator;
import miui.view.animation.QuadraticEaseOutInterpolator;

public class FragmentDualCameraAdjust extends BaseFragment implements OnClickListener, OnLongClickListener, ManuallyListener, DualController, HandleBackTrace, SnapShotIndicator {
    public static final int FRAGMENT_INFO = 4084;
    private static final int HIDE_POPUP = 1;
    private int mCurrentState = -1;
    private TextAppearanceSpan mDigitsTextStyle;
    private ViewGroup mDualParentLayout;
    private TextView mDualZoomButton;
    private int mDualZoomButtonHeight;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                FragmentDualCameraAdjust.this.onBackEvent(5);
            }
        }
    };
    private ViewGroup mHorizontalSlideLayout;
    private HorizontalSlideView mHorizontalSlideView;
    private boolean mIsHiding;
    private boolean mIsZoomAnimationEnabled;
    private boolean mIsZoomTo2X;
    private boolean mPassTouchFromZoomButtonToSlide;
    private int mSlideLayoutHeight;
    private ExtraSlideZoomAdapter mSlidingAdapter;
    private TextAppearanceSpan mSnapStyle;
    private SpannableStringBuilder mStringBuilder;
    private TextAppearanceSpan mXTextStyle;
    private AnimatorSet mZoomInAnimator;
    private AnimatorSet mZoomInOutAnimator;
    private AnimatorSet mZoomOutAnimator;
    private OnTouchListener mZoomPopupTouchListener = new OnTouchListener() {
        private boolean mAnimated = false;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 2) {
                if (!this.mAnimated) {
                    FragmentDualCameraAdjust.this.mZoomInAnimator.start();
                    this.mAnimated = true;
                }
            } else if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && this.mAnimated) {
                FragmentDualCameraAdjust.this.mZoomOutAnimator.start();
                this.mAnimated = false;
            }
            FragmentDualCameraAdjust.this.sendHideMessage();
            return false;
        }
    };
    private float mZoomRatio;
    private int mZoomRatioTele;
    private int mZoomRatioWide;
    private ObjectAnimator mZoomRequestAnimator;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mStringBuilder = new SpannableStringBuilder();
        ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = Util.getBottomHeight(getResources());
        this.mDualParentLayout = (ViewGroup) view.findViewById(2131558473);
        this.mDualZoomButton = (TextView) view.findViewById(2131558474);
        this.mHorizontalSlideLayout = (ViewGroup) view.findViewById(2131558475);
        this.mHorizontalSlideView = (HorizontalSlideView) this.mHorizontalSlideLayout.findViewById(2131558476);
        this.mHorizontalSlideView.setOnTouchListener(this.mZoomPopupTouchListener);
        this.mDualZoomButton.setOnClickListener(this);
        this.mDigitsTextStyle = new TextAppearanceSpan(getContext(), 2131492884);
        this.mXTextStyle = new TextAppearanceSpan(getContext(), 2131492885);
        this.mZoomRatioWide = 1;
        this.mZoomRatioTele = 2;
        this.mIsZoomAnimationEnabled = Util.isZoomAnimationEnabled();
        this.mZoomRequestAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), 2131099651);
        this.mZoomRequestAnimator.setTarget(this.mDualZoomButton);
        if (!this.mIsZoomAnimationEnabled) {
            this.mZoomRequestAnimator.setDuration(0);
        }
        this.mZoomRequestAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FragmentDualCameraAdjust.this.requestZoomRatio(((Float) valueAnimator.getAnimatedValue()).floatValue(), false);
            }
        });
        this.mZoomRequestAnimator.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                FragmentDualCameraAdjust.this.notifyZooming(true);
                if (FragmentDualCameraAdjust.this.mIsZoomTo2X) {
                    FragmentDualCameraAdjust.this.notifyZoom2X(true);
                } else {
                    FragmentDualCameraAdjust.this.notifyZoom2X(false);
                }
            }

            public void onAnimationEnd(Animator animator) {
                FragmentDualCameraAdjust.this.notifyZooming(false);
                FragmentDualCameraAdjust.this.mIsZoomTo2X = false;
                FragmentDualCameraAdjust.this.notifyZoom2X(false);
            }

            public void onAnimationCancel(Animator animator) {
                FragmentDualCameraAdjust.this.notifyZooming(false);
                FragmentDualCameraAdjust.this.mIsZoomTo2X = false;
                FragmentDualCameraAdjust.this.notifyZoom2X(false);
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.mZoomInOutAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099649);
        this.mZoomInOutAnimator.setTarget(this.mDualZoomButton);
        this.mZoomInOutAnimator.setInterpolator(new QuadraticEaseOutInterpolator());
        this.mZoomInAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099648);
        this.mZoomInAnimator.setTarget(this.mDualZoomButton);
        this.mZoomInAnimator.setInterpolator(new QuadraticEaseInOutInterpolator());
        this.mZoomOutAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099650);
        this.mZoomOutAnimator.setTarget(this.mDualZoomButton);
        this.mZoomOutAnimator.setInterpolator(new QuadraticEaseInOutInterpolator());
        int i = getResources().getDisplayMetrics().widthPixels;
        float f = (float) i;
        this.mHorizontalSlideLayout.getLayoutParams().height = ((int) (((f / 0.75f) - f) / 2.0f)) + getResources().getDimensionPixelSize(2131361987);
        this.mSlideLayoutHeight = this.mHorizontalSlideLayout.getLayoutParams().height;
        this.mDualZoomButtonHeight = this.mDualZoomButton.getLayoutParams().height;
        adjustViewBackground(this.mHorizontalSlideLayout, this.mCurrentMode);
        provideAnimateElement(this.mCurrentMode, null, 2);
        this.mDualZoomButton.setOnTouchListener(new OnTouchListener() {
            private int mMiddleX = (FragmentDualCameraAdjust.this.getResources().getDisplayMetrics().widthPixels / 2);
            private float mTouchDownX = -1.0f;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!FragmentDualCameraAdjust.this.mPassTouchFromZoomButtonToSlide) {
                    return false;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (FragmentDualCameraAdjust.this.mDegree == 90) {
                    motionEvent.setLocation(-y, x);
                } else if (FragmentDualCameraAdjust.this.mDegree == 180) {
                    motionEvent.setLocation(-x, -y);
                } else if (FragmentDualCameraAdjust.this.mDegree == 270) {
                    motionEvent.setLocation(y, x);
                }
                switch (motionEvent.getAction()) {
                    case 1:
                    case 3:
                        motionEvent.setLocation(((float) this.mMiddleX) + (motionEvent.getX() - this.mTouchDownX), motionEvent.getY());
                        FragmentDualCameraAdjust.this.mHorizontalSlideView.onTouchEvent(motionEvent);
                        FragmentDualCameraAdjust.this.mZoomPopupTouchListener.onTouch(null, motionEvent);
                        FragmentDualCameraAdjust.this.mPassTouchFromZoomButtonToSlide = false;
                        this.mTouchDownX = -1.0f;
                        break;
                    case 2:
                        if (this.mTouchDownX != -1.0f) {
                            motionEvent.setLocation(((float) this.mMiddleX) + (motionEvent.getX() - this.mTouchDownX), motionEvent.getY());
                            FragmentDualCameraAdjust.this.mHorizontalSlideView.onTouchEvent(motionEvent);
                            FragmentDualCameraAdjust.this.mZoomPopupTouchListener.onTouch(null, motionEvent);
                            break;
                        }
                        this.mTouchDownX = motionEvent.getX();
                        motionEvent.setAction(0);
                        motionEvent.setLocation((float) this.mMiddleX, motionEvent.getY());
                        FragmentDualCameraAdjust.this.mHorizontalSlideView.onTouchEvent(motionEvent);
                        break;
                }
                return true;
            }
        });
    }

    private void sendHideMessage() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 5000);
    }

    private void resetZoomIcon() {
        if (!CameraSettings.isSwitchCameraZoomMode()) {
            this.mZoomRatio = (float) this.mZoomRatioWide;
        } else if (ComponentManuallyDualLens.LENS_WIDE.equals(CameraSettings.getCameraZoomMode(this.mCurrentMode))) {
            this.mZoomRatio = (float) this.mZoomRatioWide;
        } else {
            this.mZoomRatio = (float) this.mZoomRatioTele;
        }
        setZoomText();
    }

    @TargetApi(21)
    public void updateZoomValue() {
        if (!CameraSettings.isSwitchCameraZoomMode()) {
            this.mZoomRatio = CameraSettings.readZoom();
        } else if (ComponentManuallyDualLens.LENS_WIDE.equals(CameraSettings.getCameraZoomMode(this.mCurrentMode))) {
            this.mZoomRatio = (float) this.mZoomRatioWide;
        } else {
            this.mZoomRatio = (float) this.mZoomRatioTele;
        }
        if (!this.mZoomRequestAnimator.isRunning() || this.mZoomRatio == ((float) this.mZoomRatioWide) || this.mZoomRatio == ((float) this.mZoomRatioTele)) {
            setZoomText();
        }
    }

    @TargetApi(21)
    private void setZoomText() {
        this.mStringBuilder.clear();
        int i = (int) this.mZoomRatio;
        int i2 = (int) ((this.mZoomRatio - ((float) i)) * 10.0f);
        if (i2 == 0) {
            Util.appendInApi26(this.mStringBuilder, String.valueOf(i), this.mDigitsTextStyle, 33);
        } else {
            SpannableStringBuilder spannableStringBuilder = this.mStringBuilder;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(".");
            stringBuilder.append(i2);
            Util.appendInApi26(spannableStringBuilder, stringBuilder.toString(), this.mDigitsTextStyle, 33);
        }
        if (Util.isAccessible()) {
            this.mDualZoomButton.setContentDescription(getString(2131296504, new Object[]{this.mStringBuilder}));
            this.mDualZoomButton.announceForAccessibility(getString(2131296504, new Object[]{this.mStringBuilder}));
        }
        Util.appendInApi26(this.mStringBuilder, "X", this.mXTextStyle, 33);
        this.mDualZoomButton.setText(this.mStringBuilder);
    }

    public boolean isZoomVisible() {
        return this.mCurrentState == 1 && this.mDualZoomButton.getVisibility() == 0;
    }

    public int visibleHeight() {
        if (this.mCurrentState == -1) {
            return 0;
        }
        if (this.mHorizontalSlideLayout.getVisibility() == 0) {
            return this.mSlideLayoutHeight + this.mDualZoomButton.getHeight();
        }
        return this.mDualZoomButtonHeight;
    }

    public void hideZoomButton() {
        if (this.mCurrentState != -1) {
            this.mCurrentState = -1;
            Completable.create(new AlphaOutOnSubscribe(this.mDualZoomButton)).subscribe();
            if (this.mHorizontalSlideLayout != null && this.mHorizontalSlideLayout.getVisibility() == 0) {
                this.mIsHiding = true;
                this.mSlidingAdapter.setEnable(false);
                this.mHorizontalSlideLayout.setVisibility(4);
                ViewCompat.setTranslationY(this.mHorizontalSlideLayout, (float) this.mSlideLayoutHeight);
                ViewCompat.setTranslationY(this.mDualZoomButton, (float) this.mSlideLayoutHeight);
            }
        }
    }

    public void showZoomButton() {
        if (this.mCurrentState != 1) {
            this.mCurrentState = 1;
            updateZoomValue();
            ViewCompat.setRotation(this.mDualZoomButton, (float) this.mDegree);
            Completable.create(new AlphaInOnSubscribe(this.mDualZoomButton)).subscribe();
        }
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(182, this);
        registerBackStack(modeCoordinator, this);
        if (b.isSupportedOpticalZoom()) {
            modeCoordinator.attachProtocol(184, this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        this.mHandler.removeCallbacksAndMessages(null);
        modeCoordinator.detachProtocol(182, this);
        unRegisterBackStack(modeCoordinator, this);
        if (b.isSupportedOpticalZoom()) {
            modeCoordinator.detachProtocol(184, this);
        }
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968598;
    }

    public int getFragmentInto() {
        return 4084;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Missing block: B:10:0x002c, code skipped:
            if (com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn() != false) goto L_0x0026;
     */
    public void provideAnimateElement(int r6, java.util.List<io.reactivex.Completable> r7, int r8) {
        /*
        r5 = this;
        r0 = r5.mCurrentMode;
        super.provideAnimateElement(r6, r7, r8);
        r8 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r1 = -1;
        r2 = 1;
        if (r6 == r8) goto L_0x0028;
    L_0x000c:
        r8 = 173; // 0xad float:2.42E-43 double:8.55E-322;
        if (r6 == r8) goto L_0x0030;
    L_0x0010:
        switch(r6) {
            case 165: goto L_0x0030;
            case 166: goto L_0x0014;
            default: goto L_0x0013;
        };
    L_0x0013:
        goto L_0x0026;
    L_0x0014:
        r8 = com.android.camera.data.DataRepository.dataItemFeature();
        r8 = r8.fM();
        if (r8 == 0) goto L_0x0026;
        r8 = r5.mDualZoomButton;
        r3 = 0;
        r8.setOnLongClickListener(r3);
        goto L_0x003f;
    L_0x0026:
        r8 = r1;
        goto L_0x0040;
    L_0x0028:
        r8 = com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn();
        if (r8 == 0) goto L_0x0030;
        goto L_0x0026;
        r8 = r5.mDualZoomButton;
        r3 = r5.mDegree;
        r3 = (float) r3;
        android.support.v4.view.ViewCompat.setRotation(r8, r3);
        r8 = r5.mDualZoomButton;
        r8.setOnLongClickListener(r5);
    L_0x003f:
        r8 = r2;
    L_0x0040:
        r3 = r5.mCurrentMode;
        r3 = com.android.camera.CameraSettings.isUltraWideConfigOpen(r3);
        if (r3 == 0) goto L_0x004a;
        r8 = r1;
    L_0x004a:
        if (r8 != r2) goto L_0x005c;
    L_0x004c:
        r3 = 4;
        r5.onBackEvent(r3);
        r3 = com.android.camera.data.DataRepository.dataItemGlobal();
        r3 = r3.getCurrentCameraId();
        if (r3 != r2) goto L_0x005c;
        r8 = r1;
    L_0x005c:
        r3 = r5.mCurrentState;
        if (r3 != r8) goto L_0x0066;
    L_0x0060:
        if (r8 != r2) goto L_0x0065;
    L_0x0062:
        r5.resetZoomIcon();
    L_0x0065:
        return;
    L_0x0066:
        r5.mCurrentState = r8;
        r3 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        r4 = 80;
        if (r8 == r1) goto L_0x00c4;
    L_0x006e:
        if (r8 == r2) goto L_0x0072;
    L_0x0070:
        goto L_0x00fe;
    L_0x0072:
        r8 = r5.mDualParentLayout;
        r8 = r8.getVisibility();
        if (r8 == 0) goto L_0x007f;
    L_0x007a:
        r8 = r5.mDualParentLayout;
        com.android.camera.animation.type.SlideInOnSubscribe.directSetResult(r8, r4);
    L_0x007f:
        r8 = r5.mHorizontalSlideLayout;
        com.android.camera.animation.type.SlideOutOnSubscribe.directSetResult(r8, r4);
        r8 = r5.mDualZoomButton;
        r1 = r5.mSlideLayoutHeight;
        r1 = (float) r1;
        android.support.v4.view.ViewCompat.setTranslationY(r8, r1);
        r5.resetZoomIcon();
        if (r7 == 0) goto L_0x00be;
    L_0x0091:
        r8 = 167; // 0xa7 float:2.34E-43 double:8.25E-322;
        if (r6 != r3) goto L_0x0098;
    L_0x0095:
        if (r0 == r8) goto L_0x0098;
    L_0x0097:
        goto L_0x00be;
    L_0x0098:
        if (r0 != r8) goto L_0x00af;
    L_0x009a:
        r6 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r8 = r5.mDualZoomButton;
        r6.<init>(r8);
        r8 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r6 = r6.setStartDelayTime(r8);
        r6 = io.reactivex.Completable.create(r6);
        r7.add(r6);
        goto L_0x00fe;
    L_0x00af:
        r6 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r8 = r5.mDualZoomButton;
        r6.<init>(r8);
        r6 = io.reactivex.Completable.create(r6);
        r7.add(r6);
        goto L_0x00fe;
    L_0x00be:
        r6 = r5.mDualZoomButton;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r6);
        goto L_0x00fe;
    L_0x00c4:
        r6 = r5.mHorizontalSlideLayout;
        r6 = r6.getVisibility();
        if (r6 != 0) goto L_0x00e5;
    L_0x00cc:
        if (r7 != 0) goto L_0x00d6;
    L_0x00ce:
        r6 = r5.mHorizontalSlideLayout;
        r7 = 8;
        r6.setVisibility(r7);
        goto L_0x00fe;
    L_0x00d6:
        r6 = new com.android.camera.animation.type.SlideOutOnSubscribe;
        r8 = r5.mDualParentLayout;
        r6.<init>(r8, r4);
        r6 = io.reactivex.Completable.create(r6);
        r7.add(r6);
        goto L_0x00fe;
    L_0x00e5:
        if (r7 == 0) goto L_0x00f9;
    L_0x00e7:
        if (r0 != r3) goto L_0x00ea;
    L_0x00e9:
        goto L_0x00f9;
    L_0x00ea:
        r6 = new com.android.camera.animation.type.AlphaOutOnSubscribe;
        r8 = r5.mDualZoomButton;
        r6.<init>(r8);
        r6 = io.reactivex.Completable.create(r6);
        r7.add(r6);
        goto L_0x00fe;
    L_0x00f9:
        r6 = r5.mDualZoomButton;
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r6);
    L_0x00fe:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.dual.FragmentDualCameraAdjust.provideAnimateElement(int, java.util.List, int):void");
    }

    public boolean onBackEvent(int i) {
        if (this.mDualParentLayout.getVisibility() != 0 || this.mIsHiding) {
            return false;
        }
        boolean z = i == 3 && this.mCurrentMode == 173;
        if (!z && this.mHorizontalSlideLayout.getVisibility() != 0) {
            return false;
        }
        if (z) {
            alphaOutZoomButtonAndSlideView();
        } else {
            hideSlideView();
        }
        return true;
    }

    public void onClick(View view) {
        if (isEnableClick()) {
            CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
            if ((cameraAction == null || !cameraAction.isDoingAction()) && view.getId() == 2131558474) {
                if (this.mCurrentMode == 166) {
                    Camera camera = (Camera) getContext();
                    Panorama3Module panorama3Module = null;
                    if (camera != null) {
                        panorama3Module = (Panorama3Module) camera.getCurrentModule();
                    }
                    if (panorama3Module != null && panorama3Module.isPanoramaDoing()) {
                        return;
                    }
                }
                if (CameraSettings.isSwitchCameraZoomMode()) {
                    toggle();
                } else if (this.mZoomRatio == ((float) this.mZoomRatioWide)) {
                    CameraStatUtil.trackDualZoomChanged(this.mCurrentMode, CameraStat.ZOOM_2X);
                    this.mIsZoomTo2X = true;
                    this.mZoomRequestAnimator.setFloatValues(new float[]{this.mZoomRatio, (float) this.mZoomRatioTele});
                    this.mZoomRequestAnimator.start();
                } else if (this.mZoomRatio <= ((float) this.mZoomRatioTele)) {
                    CameraStatUtil.trackDualZoomChanged(this.mCurrentMode, CameraStat.ZOOM_1X);
                    this.mIsZoomTo2X = false;
                    this.mZoomRequestAnimator.setFloatValues(new float[]{this.mZoomRatio, (float) this.mZoomRatioWide});
                    this.mZoomRequestAnimator.start();
                } else {
                    CameraStatUtil.trackDualZoomChanged(this.mCurrentMode, CameraStat.ZOOM_1X);
                    requestZoomRatio((float) this.mZoomRatioTele, true);
                    requestZoomRatio((float) this.mZoomRatioWide, false);
                }
                this.mZoomInOutAnimator.start();
                onBackEvent(5);
            }
        }
    }

    private void notifyZoom2X(boolean z) {
        ManuallyValueChanged manuallyValueChanged = (ManuallyValueChanged) ModeCoordinatorImpl.getInstance().getAttachProtocol(174);
        if (manuallyValueChanged == null) {
            return;
        }
        if (this.mIsZoomAnimationEnabled || z) {
            manuallyValueChanged.onDualZoomHappened(z);
        }
    }

    private void notifyZooming(boolean z) {
        ManuallyValueChanged manuallyValueChanged = (ManuallyValueChanged) ModeCoordinatorImpl.getInstance().getAttachProtocol(174);
        if (manuallyValueChanged != null) {
            manuallyValueChanged.onDualLensZooming(z);
        }
    }

    private void toggle() {
        ComponentManuallyDualLens manuallyDualLens = DataRepository.dataItemConfig().getManuallyDualLens();
        ManuallyValueChanged manuallyValueChanged = (ManuallyValueChanged) ModeCoordinatorImpl.getInstance().getAttachProtocol(174);
        if (manuallyValueChanged != null) {
            manuallyValueChanged.onDualLensSwitch(manuallyDualLens, this.mCurrentMode);
            updateZoomValue();
        }
        CameraStatUtil.trackDualZoomChanged(this.mCurrentMode, ComponentManuallyDualLens.LENS_WIDE.equals(manuallyDualLens.getComponentValue(this.mCurrentMode)) ? CameraStat.ZOOM_1X : CameraStat.ZOOM_2X);
    }

    public boolean onLongClick(View view) {
        if (!isEnableClick()) {
            return false;
        }
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        if ((cameraAction != null && cameraAction.isDoingAction()) || view.getId() != 2131558474) {
            return false;
        }
        initSlideZoomView(new ComponentManuallyDualZoom(DataRepository.dataItemRunning()));
        showSlideView();
        this.mPassTouchFromZoomButtonToSlide = true;
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips != null) {
            bottomPopupTips.hideQrCodeTip();
        }
        return true;
    }

    private void initSlideZoomView(ComponentData componentData) {
        this.mSlidingAdapter = new ExtraSlideZoomAdapter(getContext(), componentData, this.mCurrentMode, this);
        this.mHorizontalSlideView.setOnPositionSelectListener(this.mSlidingAdapter);
        this.mHorizontalSlideView.setJustifyEnabled(false);
        this.mHorizontalSlideView.setDrawAdapter(this.mSlidingAdapter);
        this.mHorizontalSlideView.setSelection(this.mSlidingAdapter.mapZoomRatioToPosition(CameraSettings.readZoom()) / ((float) (this.mSlidingAdapter.getCount() - 1)));
    }

    private void showSlideView() {
        this.mIsHiding = false;
        this.mSlidingAdapter.setEnable(true);
        this.mHorizontalSlideLayout.setVisibility(0);
        ViewCompat.setTranslationY(this.mHorizontalSlideLayout, (float) this.mSlideLayoutHeight);
        ViewCompat.setAlpha(this.mHorizontalSlideLayout, 1.0f);
        Completable.create(new TranslateYOnSubscribe(this.mHorizontalSlideLayout, 0).setInterpolator(new DecelerateInterpolator())).subscribe();
        ViewCompat.setTranslationY(this.mDualZoomButton, (float) this.mSlideLayoutHeight);
        Completable.create(new TranslateYOnSubscribe(this.mDualZoomButton, 0).setInterpolator(new BackEaseOutInterpolator())).subscribe();
        notifyTipsMargin();
    }

    private void alphaOutZoomButtonAndSlideView() {
        if (this.mHorizontalSlideLayout.getVisibility() == 0) {
            this.mIsHiding = true;
            this.mSlidingAdapter.setEnable(false);
            Completable.create(new TranslateYAlphaOutOnSubscribe(this.mHorizontalSlideLayout, this.mSlideLayoutHeight).setInterpolator(new OvershootInterpolator())).subscribe(new Action() {
                public void run() throws Exception {
                    FragmentDualCameraAdjust.this.mIsHiding = false;
                    FragmentDualCameraAdjust.this.mHorizontalSlideLayout.setVisibility(4);
                }
            });
            this.mCurrentState = -1;
            ViewCompat.setTranslationY(this.mDualZoomButton, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            Completable.create(new TranslateYAlphaOutOnSubscribe(this.mDualZoomButton, this.mSlideLayoutHeight).setInterpolator(new OvershootInterpolator())).subscribe();
        } else {
            hideZoomButton();
            TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
            if (topAlert != null) {
                topAlert.alertUpdateValue(2);
            }
        }
        notifyTipsMargin();
    }

    private void hideSlideView() {
        this.mIsHiding = true;
        this.mSlidingAdapter.setEnable(false);
        Completable.create(new TranslateYOnSubscribe(this.mHorizontalSlideLayout, this.mSlideLayoutHeight).setInterpolator(new OvershootInterpolator())).subscribe(new Action() {
            public void run() throws Exception {
                FragmentDualCameraAdjust.this.mIsHiding = false;
                FragmentDualCameraAdjust.this.mHorizontalSlideLayout.setVisibility(4);
            }
        });
        ViewCompat.setTranslationY(this.mDualZoomButton, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        Completable.create(new TranslateYOnSubscribe(this.mDualZoomButton, this.mSlideLayoutHeight).setInterpolator(new OvershootInterpolator())).subscribe();
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips != null) {
            bottomPopupTips.reInitTipImage();
        }
    }

    private void notifyTipsMargin() {
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips != null) {
            bottomPopupTips.directHideTipImage();
            bottomPopupTips.directlyHideTips();
            if (Util.UI_DEBUG()) {
                bottomPopupTips.directShowOrHideLeftTipImage(false);
            }
        }
    }

    public void onManuallyDataChanged(ComponentData componentData, String str, String str2, boolean z) {
        if (!isInModeChanging()) {
            requestZoomRatio(Float.valueOf(str2).floatValue(), false);
        }
    }

    private void requestZoomRatio(float f, boolean z) {
        ManuallyValueChanged manuallyValueChanged = (ManuallyValueChanged) ModeCoordinatorImpl.getInstance().getAttachProtocol(174);
        if (manuallyValueChanged != null) {
            manuallyValueChanged.onDualZoomValueChanged(f, z);
        }
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        if (this.mDualZoomButton.getVisibility() == 0) {
            list.add(this.mDualZoomButton);
        }
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        if (i == 3) {
            adjustViewBackground(this.mHorizontalSlideLayout, this.mCurrentMode);
        }
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    private void adjustViewBackground(View view, int i) {
        view.setBackgroundResource(2131427401);
    }

    public void setSnapNumVisible(boolean z, boolean z2) {
        if (this.mHorizontalSlideLayout != null) {
            if (z) {
                if (z2) {
                    if (this.mHorizontalSlideLayout.getVisibility() == 0) {
                        hideSlideView();
                    }
                    this.mZoomInAnimator.start();
                } else {
                    ViewCompat.setRotation(this.mDualZoomButton, (float) this.mDegree);
                    this.mDualZoomButton.setVisibility(0);
                }
            } else if (z2) {
                this.mZoomOutAnimator.start();
                updateZoomValue();
            } else {
                this.mDualZoomButton.setVisibility(4);
            }
        }
    }

    public void setSnapNumValue(int i) {
        if (this.mSnapStyle == null) {
            this.mSnapStyle = new TextAppearanceSpan(getContext(), 2131492883);
        }
        this.mStringBuilder.clear();
        Util.appendInApi26(this.mStringBuilder, String.format("%02d", new Object[]{Integer.valueOf(i)}), this.mSnapStyle, 33);
        this.mDualZoomButton.setText(this.mStringBuilder);
    }
}
