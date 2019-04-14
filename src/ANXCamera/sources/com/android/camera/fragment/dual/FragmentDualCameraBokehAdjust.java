package com.android.camera.fragment.dual;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.animation.type.TranslateYAlphaInOnSubscribe;
import com.android.camera.animation.type.TranslateYAlphaOutOnSubscribe;
import com.android.camera.animation.type.TranslateYOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.config.ComponentManuallyDualZoom;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.manually.ManuallyListener;
import com.android.camera.fragment.manually.adapter.ExtraSlideFNumberAdapter;
import com.android.camera.log.Log;
import com.android.camera.module.loader.FunctionParseBeautyBodySlimCount;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.ActionProcessing;
import com.android.camera.protocol.ModeProtocol.BokehFNumberController;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.ManuallyValueChanged;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.ui.HorizontalSlideView;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import io.reactivex.Completable;
import java.util.List;
import miui.view.animation.BackEaseOutInterpolator;
import miui.view.animation.QuadraticEaseInOutInterpolator;
import miui.view.animation.QuadraticEaseOutInterpolator;

public class FragmentDualCameraBokehAdjust extends BaseFragment implements OnClickListener, ManuallyListener, BokehFNumberController, HandleBackTrace {
    public static final int FRAGMENT_INFO = 4091;
    private static final int HIDE_POPUP = 1;
    public static final String TAG = "BokehAdjust";
    private int mBokehFButtonHeight;
    private int mCurrentState = -1;
    private TextAppearanceSpan mDigitsTextStyle;
    private View mDualBokehFButton;
    private ViewGroup mDualParentLayout;
    private boolean mFNumberHideStatus;
    private TextView mFNumberTextView;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                FragmentDualCameraBokehAdjust.this.onBackEvent(5);
            }
        }
    };
    private ViewGroup mHorizontalSlideLayout;
    private HorizontalSlideView mHorizontalSlideView;
    private ImageView mImageIndicator;
    private boolean mShowImageIndicator = false;
    private int mSlideLayoutHeight;
    private ExtraSlideFNumberAdapter mSlidingAdapter;
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
                    FragmentDualCameraBokehAdjust.this.mZoomInAnimator.start();
                    this.mAnimated = true;
                }
            } else if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && this.mAnimated) {
                FragmentDualCameraBokehAdjust.this.mZoomOutAnimator.start();
                this.mAnimated = false;
            }
            FragmentDualCameraBokehAdjust.this.sendHideMessage();
            return false;
        }
    };

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mStringBuilder = new SpannableStringBuilder();
        ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = Util.getBottomHeight(getResources());
        this.mDualParentLayout = (ViewGroup) view.findViewById(2131558473);
        this.mDualBokehFButton = view.findViewById(2131558477);
        this.mFNumberTextView = (TextView) this.mDualBokehFButton.findViewById(2131558479);
        this.mImageIndicator = (ImageView) view.findViewById(2131558480);
        this.mHorizontalSlideLayout = (ViewGroup) view.findViewById(2131558481);
        this.mHorizontalSlideView = (HorizontalSlideView) this.mHorizontalSlideLayout.findViewById(2131558482);
        this.mHorizontalSlideView.setOnTouchListener(this.mZoomPopupTouchListener);
        this.mImageIndicator.setOnClickListener(this);
        if (Util.isAccessible()) {
            this.mImageIndicator.setContentDescription(getString(2131296518));
            this.mImageIndicator.postDelayed(new Runnable() {
                public void run() {
                    if (FragmentDualCameraBokehAdjust.this.isAdded()) {
                        FragmentDualCameraBokehAdjust.this.mImageIndicator.sendAccessibilityEvent(4);
                    }
                }
            }, 3000);
        }
        this.mDualBokehFButton.setOnClickListener(this);
        this.mBokehFButtonHeight = getResources().getDimensionPixelSize(2131361942);
        this.mDigitsTextStyle = new TextAppearanceSpan(getContext(), 2131492884);
        this.mXTextStyle = new TextAppearanceSpan(getContext(), 2131492885);
        this.mZoomInOutAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099649);
        this.mZoomInOutAnimator.setTarget(this.mDualBokehFButton);
        this.mZoomInOutAnimator.setInterpolator(new QuadraticEaseOutInterpolator());
        this.mZoomInAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099648);
        this.mZoomInAnimator.setTarget(this.mDualBokehFButton);
        this.mZoomInAnimator.setInterpolator(new QuadraticEaseInOutInterpolator());
        this.mZoomOutAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099650);
        this.mZoomOutAnimator.setTarget(this.mDualBokehFButton);
        this.mZoomOutAnimator.setInterpolator(new QuadraticEaseInOutInterpolator());
        int i = getResources().getDisplayMetrics().widthPixels;
        float f = (float) i;
        this.mHorizontalSlideLayout.getLayoutParams().height = ((int) (((f / 0.75f) - f) / 2.0f)) + getResources().getDimensionPixelSize(2131361987);
        this.mSlideLayoutHeight = this.mHorizontalSlideLayout.getLayoutParams().height;
        adjustViewBackground(this.mHorizontalSlideLayout, this.mCurrentMode);
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    private void sendHideMessage() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, FunctionParseBeautyBodySlimCount.TIP_INTERVAL_TIME);
    }

    private void resetFNumber() {
        setFNumberText();
    }

    @TargetApi(21)
    private void setFNumberText() {
        this.mStringBuilder.clear();
        Util.appendInApi26(this.mStringBuilder, CameraSettings.readFNumber(), this.mDigitsTextStyle, 33);
        if (Util.isAccessible()) {
            this.mDualBokehFButton.setContentDescription(getString(2131296517, new Object[]{r0}));
            this.mDualBokehFButton.postDelayed(new Runnable() {
                public void run() {
                    if (FragmentDualCameraBokehAdjust.this.isAdded()) {
                        FragmentDualCameraBokehAdjust.this.mDualBokehFButton.sendAccessibilityEvent(4);
                    }
                }
            }, 3000);
        }
        this.mFNumberTextView.setText(this.mStringBuilder);
    }

    public void updateFNumberValue() {
        setFNumberText();
    }

    public boolean isFNumberVisible() {
        if (this.mCurrentState == 1 && isVisible(this.mDualParentLayout) && (isVisible(this.mImageIndicator) || isVisible(this.mDualBokehFButton))) {
            return true;
        }
        return false;
    }

    public int visibleHeight() {
        if (this.mCurrentState == -1 || !isVisible(this.mDualParentLayout)) {
            return 0;
        }
        if (this.mShowImageIndicator) {
            return this.mBokehFButtonHeight;
        }
        return this.mBokehFButtonHeight + this.mSlideLayoutHeight;
    }

    public void hideFNumberPanel(boolean z, boolean z2) {
        if (z2) {
            this.mFNumberHideStatus = true;
        }
        if (this.mCurrentState != -1) {
            this.mCurrentState = -1;
            if (z) {
                hideSlideView();
                Completable.create(new AlphaOutOnSubscribe(this.mDualParentLayout).targetGone()).subscribe();
            } else {
                this.mDualParentLayout.setVisibility(8);
            }
        }
    }

    public void showFNumberPanel() {
        this.mFNumberHideStatus = false;
        if (this.mCurrentState != 1) {
            this.mCurrentState = 1;
            Log.d(TAG, "showFNumber");
            hideSlideView();
        }
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(210, this);
        registerBackStack(modeCoordinator, this);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        this.mHandler.removeCallbacksAndMessages(null);
        modeCoordinator.detachProtocol(210, this);
        unRegisterBackStack(modeCoordinator, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968599;
    }

    public int getFragmentInto() {
        return 4091;
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
        if (i2 == 3) {
            this.mFNumberHideStatus = false;
        }
        if (i == 171 && DataRepository.dataItemGlobal().isNormalIntent() && (i2 == 3 || !this.mFNumberHideStatus)) {
            this.mDualBokehFButton.setRotation((float) this.mDegree);
            this.mImageIndicator.setRotation((float) this.mDegree);
            i = 1;
        } else {
            i = -1;
        }
        if (this.mCurrentState == i) {
            if (1 == this.mCurrentState && isVisible(this.mDualParentLayout)) {
                hideSlideView();
            }
            return;
        }
        this.mCurrentState = i;
        if (i == -1) {
            if (isVisible(this.mDualParentLayout)) {
                if (list == null) {
                    this.mDualParentLayout.setVisibility(8);
                } else {
                    list.add(Completable.create(new AlphaOutOnSubscribe(this.mDualParentLayout).targetGone()));
                }
            }
            this.mShowImageIndicator = false;
        } else if (i == 1) {
            AlphaInOnSubscribe.directSetResult(this.mDualParentLayout);
            if (this.mHorizontalSlideLayout.getVisibility() == 0) {
                this.mHorizontalSlideLayout.setVisibility(4);
            }
            this.mDualBokehFButton.setVisibility(4);
            this.mImageIndicator.setTranslationY(this.mHorizontalSlideLayout.getVisibility() != 8 ? (float) this.mSlideLayoutHeight : PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            resetFNumber();
            if (!isVisible(this.mImageIndicator)) {
                if (list == null) {
                    AlphaInOnSubscribe.directSetResult(this.mImageIndicator);
                } else {
                    list.add(Completable.create(new AlphaInOnSubscribe(this.mImageIndicator)));
                }
            }
        }
    }

    private boolean isVisible(View view) {
        return view.getVisibility() == 0 && view.getAlpha() != PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO;
    }

    public boolean onBackEvent(int i) {
        if (this.mShowImageIndicator || this.mCurrentMode != 171 || !isVisible(this.mDualParentLayout)) {
            return false;
        }
        if (i == 3) {
            this.mHorizontalSlideView.stopScroll();
            return false;
        }
        hideSlideView();
        return true;
    }

    public void onClick(View view) {
        if (isEnableClick()) {
            CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
            if (cameraAction == null || !cameraAction.isDoingAction()) {
                int id = view.getId();
                if (id != 2131558477) {
                    if (id == 2131558480) {
                        if (this.mImageIndicator.getAlpha() != PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO) {
                            initSlideFNumberView(new ComponentManuallyDualZoom(DataRepository.dataItemRunning()));
                            sendHideMessage();
                            ActionProcessing actionProcessing = (ActionProcessing) ModeCoordinatorImpl.getInstance().getAttachProtocol(162);
                            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
                            if (!(actionProcessing == null || !actionProcessing.isShowFilterView() || configChanges == null)) {
                                configChanges.showOrHideFilter();
                            }
                            showSlideView();
                        } else {
                            hideSlideView();
                        }
                    }
                } else if (this.mDualBokehFButton.getAlpha() != PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO) {
                    hideSlideView();
                }
            }
        }
    }

    private void initSlideFNumberView(ComponentData componentData) {
        this.mSlidingAdapter = new ExtraSlideFNumberAdapter(getContext(), componentData, this.mCurrentMode, this);
        this.mHorizontalSlideView.setOnPositionSelectListener(this.mSlidingAdapter);
        this.mHorizontalSlideView.setJustifyEnabled(true);
        this.mHorizontalSlideView.setDrawAdapter(this.mSlidingAdapter);
        this.mHorizontalSlideView.setSelection(this.mSlidingAdapter.mapFNumberToPosition(CameraSettings.readFNumber()));
        resetFNumber();
    }

    private void showSlideView() {
        MiBeautyProtocol miBeautyProtocol = (MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194);
        if (miBeautyProtocol == null || !miBeautyProtocol.isBeautyPanelShow()) {
            this.mSlidingAdapter.setEnable(true);
            AlphaInOnSubscribe.directSetResult(this.mDualParentLayout);
            this.mHorizontalSlideLayout.setVisibility(0);
            this.mHorizontalSlideLayout.setTranslationY((float) this.mSlideLayoutHeight);
            Completable.create(new TranslateYAlphaInOnSubscribe(this.mHorizontalSlideLayout, 0).setInterpolator(new DecelerateInterpolator())).subscribe();
            this.mDualBokehFButton.setTranslationY((float) (this.mSlideLayoutHeight + this.mBokehFButtonHeight));
            Completable.create(new TranslateYAlphaInOnSubscribe(this.mDualBokehFButton, this.mSlideLayoutHeight).setInterpolator(new BackEaseOutInterpolator())).subscribe();
            notifyTipsMargin(this.mSlideLayoutHeight);
            BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
            if (bottomPopupTips != null) {
                bottomPopupTips.directHideTipImage();
                if (Util.UI_DEBUG()) {
                    bottomPopupTips.directShowOrHideLeftTipImage(false);
                }
            }
            this.mImageIndicator.setTranslationY((float) this.mSlideLayoutHeight);
            Completable.create(new TranslateYAlphaOutOnSubscribe(this.mImageIndicator, 0).setInterpolator(new BackEaseOutInterpolator())).subscribe();
            this.mShowImageIndicator = false;
            return;
        }
        Log.v(TAG, "beauty panel shown. do not show the slide view.");
    }

    private void hideSlideView() {
        this.mShowImageIndicator = true;
        if (this.mSlidingAdapter != null) {
            this.mSlidingAdapter.setEnable(false);
        }
        boolean isVisible = isVisible(this.mDualParentLayout);
        AlphaInOnSubscribe.directSetResult(this.mDualParentLayout);
        if (isVisible(this.mHorizontalSlideLayout)) {
            if (isVisible) {
                Completable.create(new TranslateYAlphaOutOnSubscribe(this.mHorizontalSlideLayout, this.mSlideLayoutHeight).setInterpolator(new OvershootInterpolator())).subscribe();
            } else {
                AlphaOutOnSubscribe.directSetResult(this.mHorizontalSlideLayout);
            }
        }
        if (isVisible(this.mDualBokehFButton)) {
            if (isVisible) {
                Completable.create(new TranslateYAlphaOutOnSubscribe(this.mDualBokehFButton, this.mSlideLayoutHeight).setInterpolator(new OvershootInterpolator())).subscribe();
            } else {
                AlphaOutOnSubscribe.directSetResult(this.mDualBokehFButton);
            }
        }
        if (isVisible(this.mImageIndicator)) {
            Completable.create(new TranslateYOnSubscribe(this.mImageIndicator, this.mHorizontalSlideLayout.getVisibility() != 8 ? this.mSlideLayoutHeight : 0).setInterpolator(new OvershootInterpolator())).subscribe();
        } else {
            Completable.create(new TranslateYAlphaInOnSubscribe(this.mImageIndicator, this.mHorizontalSlideLayout.getVisibility() != 8 ? this.mSlideLayoutHeight : 0).setInterpolator(new OvershootInterpolator())).subscribe();
        }
        notifyTipsMargin(0);
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips != null) {
            bottomPopupTips.reInitTipImage();
        }
    }

    private void notifyTipsMargin(int i) {
        ((BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175)).updateTipBottomMargin(i, true);
    }

    public void onManuallyDataChanged(ComponentData componentData, String str, String str2, boolean z) {
        if (!isInModeChanging()) {
            requestFNumber(str2);
        }
    }

    private void requestFNumber(String str) {
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        if (cameraAction == null || !cameraAction.isDoingAction()) {
            ManuallyValueChanged manuallyValueChanged = (ManuallyValueChanged) ModeCoordinatorImpl.getInstance().getAttachProtocol(174);
            if (manuallyValueChanged != null) {
                manuallyValueChanged.onBokehFNumberValueChanged(str);
            }
            setFNumberText();
            return;
        }
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("new f number is ignored: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        if (this.mDualBokehFButton.getVisibility() == 0) {
            list.add(this.mDualBokehFButton);
            list.add(this.mImageIndicator);
        }
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        if (i == 3) {
            adjustViewBackground(this.mHorizontalSlideLayout, this.mCurrentMode);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mCurrentMode == 171 && isVisible(this.mDualParentLayout) && isVisible(this.mHorizontalSlideLayout)) {
            hideSlideView();
        }
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        if (this.mCurrentMode == 171) {
            ActionProcessing actionProcessing = (ActionProcessing) ModeCoordinatorImpl.getInstance().getAttachProtocol(162);
            if (actionProcessing == null || !actionProcessing.isShowLightingView()) {
                MiBeautyProtocol miBeautyProtocol = (MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194);
                if (miBeautyProtocol != null && miBeautyProtocol.isBeautyPanelShow()) {
                    return;
                }
            }
            return;
        }
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    private void adjustViewBackground(View view, int i) {
        view.setBackgroundResource(2131427401);
    }
}
