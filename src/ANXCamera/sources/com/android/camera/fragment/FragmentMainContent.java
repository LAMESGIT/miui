package com.android.camera.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.log.Log;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.AutoZoomModuleProtocol;
import com.android.camera.protocol.ModeProtocol.AutoZoomViewProtocol;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.MainContentProtocol;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.SnapShotIndicator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.protocol.ModeProtocol.VerticalProtocol;
import com.android.camera.ui.AfRegionsView;
import com.android.camera.ui.FaceView;
import com.android.camera.ui.FocusIndicator;
import com.android.camera.ui.FocusView;
import com.android.camera.ui.FocusView.ExposureViewListener;
import com.android.camera.ui.LightingView;
import com.android.camera.ui.ObjectView;
import com.android.camera.ui.ObjectView.ObjectViewListener;
import com.android.camera.ui.V6EffectCropView;
import com.android.camera.ui.V6PreviewFrame;
import com.android.camera.ui.V6PreviewPanel;
import com.android.camera.watermark.WaterMarkData;
import com.android.camera2.CameraHardwareFace;
import com.android.camera2.autozoom.AutoZoomCaptureResult;
import com.android.camera2.autozoom.AutoZoomView;
import com.bumptech.glide.c;
import com.mi.config.b;
import io.reactivex.Completable;
import java.util.List;
import miui.view.animation.QuadraticEaseInOutInterpolator;

public class FragmentMainContent extends BaseFragment implements AutoZoomViewProtocol, HandleBackTrace, MainContentProtocol, SnapShotIndicator {
    public static final int FRAGMENT_INFO = 243;
    public static final int FRONT_CAMERA_ID = 1;
    private static final String TAG = "FragmentMainContent";
    private long lastConfirmTime;
    private int lastFaceResult;
    private boolean lastFaceSuccess;
    private int mActiveIndicator = 2;
    private AfRegionsView mAfRegionsView;
    private AutoZoomView mAutoZoomOverlay;
    private View mBottomCover;
    private TextView mCaptureDelayNumber;
    private ImageView mCenterHintIcon;
    private TextView mCenterHintText;
    private ViewGroup mCoverParent;
    private int mDisplayRectTopMargin;
    private V6EffectCropView mEffectCropView;
    private FaceView mFaceView;
    private FocusView mFocusView;
    private Handler mHandler = new Handler();
    private boolean mIsIntentAction;
    private int mLastCameraId = -1;
    private LightingView mLightingView;
    private TextView mMultiSnapNum;
    private ObjectView mObjectView;
    private ViewGroup mPreviewCenterHint;
    private V6PreviewFrame mPreviewFrame;
    private ViewGroup mPreviewPage;
    private V6PreviewPanel mPreviewPanel;
    private TextAppearanceSpan mSnapStyle;
    private SpannableStringBuilder mStringBuilder;
    private View mTopCover;
    private AnimatorSet mZoomInAnimator;
    private AnimatorSet mZoomOutAnimator;
    private RectF mergeRectF = new RectF();

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onStop() {
        super.onStop();
        this.mLightingView.clear();
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mCoverParent = (ViewGroup) view.findViewById(2131558522);
        this.mMultiSnapNum = (TextView) this.mCoverParent.findViewById(2131558525);
        this.mCaptureDelayNumber = (TextView) this.mCoverParent.findViewById(2131558526);
        this.mTopCover = this.mCoverParent.findViewById(2131558523);
        this.mBottomCover = this.mCoverParent.findViewById(2131558524);
        this.mPreviewPage = (ViewGroup) view.findViewById(2131558521);
        this.mPreviewPanel = (V6PreviewPanel) this.mPreviewPage.findViewById(2131558629);
        this.mPreviewFrame = (V6PreviewFrame) this.mPreviewPanel.findViewById(2131558631);
        this.mPreviewCenterHint = (ViewGroup) this.mPreviewPanel.findViewById(2131558642);
        this.mCenterHintIcon = (ImageView) this.mPreviewCenterHint.findViewById(2131558643);
        this.mCenterHintText = (TextView) this.mPreviewCenterHint.findViewById(2131558644);
        this.mEffectCropView = (V6EffectCropView) this.mPreviewPanel.findViewById(2131558641);
        this.mFaceView = (FaceView) this.mPreviewPanel.findViewById(2131558633);
        this.mFocusView = (FocusView) this.mPreviewPanel.findViewById(2131558635);
        this.mAutoZoomOverlay = (AutoZoomView) this.mPreviewPanel.findViewById(2131558636);
        this.mLightingView = (LightingView) this.mPreviewPanel.findChildrenById(2131558637);
        this.mObjectView = (ObjectView) this.mPreviewPanel.findViewById(2131558634);
        this.mAfRegionsView = (AfRegionsView) this.mPreviewPanel.findViewById(2131558638);
        this.mLightingView.setRotation(this.mDegree);
        adjustViewHeight();
        this.mCoverParent.getLayoutParams().height = Util.sWindowHeight - Util.getBottomHeight(getResources());
        this.mBottomCover.getLayoutParams().height = ((((int) (((float) Util.sWindowWidth) / 0.75f)) - Util.sWindowWidth) / 2) + getResources().getDimensionPixelSize(2131361987);
        this.mTopCover.getLayoutParams().height = (this.mCoverParent.getLayoutParams().height - Util.sWindowWidth) - this.mBottomCover.getLayoutParams().height;
        this.mIsIntentAction = DataRepository.dataItemGlobal().isIntentAction();
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    private void adjustViewHeight() {
        if (getContext() != null && this.mPreviewPanel != null) {
            ViewGroup viewGroup = (ViewGroup) this.mPreviewPanel.getParent();
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) viewGroup.getLayoutParams();
            MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) this.mPreviewPanel.getLayoutParams();
            MarginLayoutParams marginLayoutParams3 = (MarginLayoutParams) this.mPreviewCenterHint.getLayoutParams();
            Rect displayRect = Util.getDisplayRect(getContext());
            if (!(marginLayoutParams2.height == displayRect.height() && displayRect.top == this.mDisplayRectTopMargin)) {
                this.mDisplayRectTopMargin = displayRect.top;
                marginLayoutParams2.height = displayRect.height();
                marginLayoutParams2.topMargin = displayRect.top;
                this.mPreviewPanel.setLayoutParams(marginLayoutParams2);
                marginLayoutParams3.height = (displayRect.width() * 4) / 3;
                this.mPreviewCenterHint.setLayoutParams(marginLayoutParams3);
                marginLayoutParams.height = displayRect.height() + this.mDisplayRectTopMargin;
                viewGroup.setLayoutParams(marginLayoutParams);
                setDisplaySize(displayRect.width(), displayRect.height());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968612;
    }

    public int getFragmentInto() {
        return 243;
    }

    public boolean needViewClear() {
        return true;
    }

    public void provideAnimateElement(int r5, java.util.List<io.reactivex.Completable> r6, int r7) {
        /*
        r4 = this;
        r0 = r4.mCurrentMode;
        super.provideAnimateElement(r5, r6, r7);
        r7 = 1;
        r1 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r5 == r1) goto L_0x000d;
    L_0x000b:
        r1 = -1;
        goto L_0x0010;
        r1 = r7;
    L_0x0010:
        r2 = 0;
        r4.setSnapNumVisible(r2, r7);
        r4.hideDelayNumber();
        r3 = r4.mPreviewFrame;
        r3.hidePreviewGrid();
        r3 = r4.mFaceView;
        r3.clear();
        r3 = r4.mFaceView;
        r3.clearFaceFlags();
        r3 = r4.mFocusView;
        r3.clear();
        r3 = r4.mLightingView;
        r3.clear();
        r3 = r4.mAfRegionsView;
        r3.clear();
        r3 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        if (r0 == r3) goto L_0x003e;
    L_0x003a:
        switch(r0) {
            case 168: goto L_0x003e;
            case 169: goto L_0x003e;
            default: goto L_0x003d;
        };
    L_0x003d:
        goto L_0x0043;
    L_0x003e:
        if (r5 == r3) goto L_0x0045;
    L_0x0040:
        switch(r5) {
            case 168: goto L_0x0045;
            case 169: goto L_0x0045;
            default: goto L_0x0043;
        };
    L_0x0043:
        r2 = r7;
        goto L_0x0046;
    L_0x0046:
        if (r2 == 0) goto L_0x004d;
    L_0x0048:
        r5 = r4.mFocusView;
        r5.releaseListener();
    L_0x004d:
        r5 = r4.mTopCover;
        r5 = r5.getTag();
        if (r5 == 0) goto L_0x0064;
    L_0x0055:
        r5 = r4.mTopCover;
        r5 = r5.getTag();
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        if (r5 != r1) goto L_0x0064;
    L_0x0063:
        return;
    L_0x0064:
        r5 = r4.mTopCover;
        r0 = java.lang.Integer.valueOf(r1);
        r5.setTag(r0);
        r5 = 80;
        r0 = 48;
        if (r1 != r7) goto L_0x009d;
    L_0x0073:
        if (r6 != 0) goto L_0x0080;
    L_0x0075:
        r6 = r4.mTopCover;
        com.android.camera.animation.type.SlideInOnSubscribe.directSetResult(r6, r0);
        r6 = r4.mBottomCover;
        com.android.camera.animation.type.SlideInOnSubscribe.directSetResult(r6, r5);
        goto L_0x00d0;
    L_0x0080:
        r7 = new com.android.camera.animation.type.SlideInOnSubscribe;
        r1 = r4.mTopCover;
        r7.<init>(r1, r0);
        r7 = io.reactivex.Completable.create(r7);
        r6.add(r7);
        r7 = new com.android.camera.animation.type.SlideInOnSubscribe;
        r0 = r4.mBottomCover;
        r7.<init>(r0, r5);
        r5 = io.reactivex.Completable.create(r7);
        r6.add(r5);
        goto L_0x00d0;
    L_0x009d:
        if (r6 != 0) goto L_0x00aa;
    L_0x009f:
        r6 = r4.mTopCover;
        com.android.camera.animation.type.SlideOutOnSubscribe.directSetResult(r6, r0);
        r6 = r4.mBottomCover;
        com.android.camera.animation.type.SlideOutOnSubscribe.directSetResult(r6, r5);
        goto L_0x00d0;
    L_0x00aa:
        r7 = new com.android.camera.animation.type.SlideOutOnSubscribe;
        r1 = r4.mTopCover;
        r7.<init>(r1, r0);
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r7 = r7.setDurationTime(r0);
        r7 = io.reactivex.Completable.create(r7);
        r6.add(r7);
        r7 = new com.android.camera.animation.type.SlideOutOnSubscribe;
        r1 = r4.mBottomCover;
        r7.<init>(r1, r5);
        r5 = r7.setDurationTime(r0);
        r5 = io.reactivex.Completable.create(r5);
        r6.add(r5);
    L_0x00d0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.FragmentMainContent.provideAnimateElement(int, java.util.List, int):void");
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(166, this);
        modeCoordinator.attachProtocol(214, this);
        registerBackStack(modeCoordinator, this);
        if (!b.isSupportedOpticalZoom()) {
            modeCoordinator.attachProtocol(184, this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        modeCoordinator.detachProtocol(166, this);
        unRegisterBackStack(modeCoordinator, this);
        modeCoordinator.detachProtocol(214, this);
        if (!b.isSupportedOpticalZoom()) {
            modeCoordinator.detachProtocol(184, this);
        }
    }

    public void showReviewViews(Bitmap bitmap) {
        if (bitmap != null) {
            this.mPreviewPanel.mVideoReviewImage.setImageBitmap(bitmap);
            this.mPreviewPanel.mVideoReviewImage.setVisibility(0);
        }
        Util.fadeIn(this.mPreviewPanel.mVideoReviewPlay);
    }

    public void hideReviewViews() {
        if (this.mPreviewPanel.mVideoReviewImage.getVisibility() == 0) {
            Util.fadeOut(this.mPreviewPanel.mVideoReviewImage);
        }
        Util.fadeOut(this.mPreviewPanel.mVideoReviewPlay);
    }

    public void showDelayNumber(int i) {
        if (this.mCaptureDelayNumber.getVisibility() != 0) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131361839);
            if (this.mCurrentMode != 165) {
                dimensionPixelSize += this.mDisplayRectTopMargin;
            }
            if (this.mDisplayRectTopMargin == 0 || this.mCurrentMode == 165) {
                dimensionPixelSize += getResources().getDimensionPixelSize(2131361877);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131362119);
                if (dimensionPixelSize2 > 0) {
                    dimensionPixelSize += dimensionPixelSize2;
                }
            }
            ((MarginLayoutParams) this.mCaptureDelayNumber.getLayoutParams()).topMargin = dimensionPixelSize;
            if (this.mDegree > 0) {
                ViewCompat.setRotation(this.mCaptureDelayNumber, (float) this.mDegree);
            }
            Completable.create(new AlphaInOnSubscribe(this.mCaptureDelayNumber)).subscribe();
        }
        this.mCaptureDelayNumber.setText(String.valueOf(i));
    }

    public void hideDelayNumber() {
        if (this.mCaptureDelayNumber.getVisibility() != 8) {
            this.mCaptureDelayNumber.setVisibility(8);
        }
    }

    private void initSnapNumAnimator() {
        this.mZoomInAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099648);
        this.mZoomInAnimator.setTarget(this.mMultiSnapNum);
        this.mZoomInAnimator.setInterpolator(new QuadraticEaseInOutInterpolator());
        this.mZoomOutAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), 2131099650);
        this.mZoomOutAnimator.setTarget(this.mMultiSnapNum);
        this.mZoomOutAnimator.setInterpolator(new QuadraticEaseInOutInterpolator());
    }

    public void setSnapNumVisible(boolean z, boolean z2) {
        if (z != (this.mMultiSnapNum.getVisibility() == 0)) {
            if (this.mZoomInAnimator == null) {
                initSnapNumAnimator();
            }
            if (z) {
                AlphaInOnSubscribe.directSetResult(this.mMultiSnapNum);
                setSnapNumValue(0);
                this.mZoomInAnimator.start();
            } else {
                this.mZoomOutAnimator.start();
                Completable.create(new AlphaOutOnSubscribe(this.mMultiSnapNum).setStartDelayTime(500)).subscribe();
            }
        }
    }

    @TargetApi(21)
    public void setSnapNumValue(int i) {
        setSnapNumVisible(true, true);
        if (this.mSnapStyle == null) {
            this.mSnapStyle = new TextAppearanceSpan(getContext(), 2131492883);
        }
        if (this.mStringBuilder == null) {
            this.mStringBuilder = new SpannableStringBuilder();
        } else {
            this.mStringBuilder.clear();
        }
        this.mStringBuilder.append(String.format("%02d", new Object[]{Integer.valueOf(i)}), this.mSnapStyle, 33);
        this.mMultiSnapNum.setText(this.mStringBuilder);
    }

    public void setPreviewAspectRatio(float f) {
        adjustViewHeight();
    }

    public void performHapticFeedback(int i) {
        this.mPreviewFrame.performHapticFeedback(i);
    }

    public boolean onViewTouchEvent(int i, MotionEvent motionEvent) {
        if (i == this.mFocusView.getId()) {
            return this.mFocusView.onViewTouchEvent(motionEvent);
        }
        if (i == this.mEffectCropView.getId()) {
            return this.mEffectCropView.onViewTouchEvent(motionEvent);
        }
        if (i == this.mAutoZoomOverlay.getId()) {
            return this.mAutoZoomOverlay.onViewTouchEvent(motionEvent);
        }
        return false;
    }

    public void initEffectCropView() {
        this.mEffectCropView.onCreate();
    }

    public void destroyEffectCropView() {
        this.mEffectCropView.onDestroy();
    }

    public void removeTiltShiftMask() {
        this.mEffectCropView.removeTiltShiftMask();
    }

    public void setEffectViewVisible(boolean z) {
        if (z) {
            this.mEffectCropView.show();
        } else {
            this.mEffectCropView.hide();
        }
    }

    public void updateEffectViewVisible() {
        this.mEffectCropView.updateVisible();
    }

    public void updateEffectViewVisible(int i) {
        this.mEffectCropView.updateVisible(i);
    }

    public boolean onEffectViewTouchEvent(MotionEvent motionEvent) {
        return this.mEffectCropView.onTouchEvent(motionEvent);
    }

    public boolean isEffectViewMoved() {
        return this.mEffectCropView.isMoved();
    }

    public boolean isEffectViewVisible() {
        return this.mEffectCropView.isVisible();
    }

    public boolean isAutoZoomViewEnabled() {
        return this.mAutoZoomOverlay.isViewEnabled();
    }

    public void setCameraDisplayOrientation(int i) {
        if (this.mFaceView != null && this.mAfRegionsView != null) {
            this.mFaceView.setCameraDisplayOrientation(i);
            this.mAfRegionsView.setCameraDisplayOrientation(i);
        }
    }

    public void setShowGenderAndAge(boolean z) {
        this.mFaceView.setShowGenderAndAge(z);
    }

    public void setShowMagicMirror(boolean z) {
        this.mFaceView.setShowMagicMirror(z);
    }

    public void setSkipDrawFace(boolean z) {
        this.mFaceView.setSkipDraw(z);
    }

    public void updateFaceView(boolean z, boolean z2, boolean z3, boolean z4, int i) {
        if (z2) {
            this.mFaceView.clear();
        }
        this.mFaceView.setVisibility(z ? 0 : 8);
        if (i > 0) {
            this.mFaceView.setCameraDisplayOrientation(i);
        }
        this.mFaceView.setMirror(z3);
        if (z4) {
            this.mFaceView.resume();
        }
    }

    public CameraHardwareFace[] getFaces() {
        return this.mFaceView.getFaces();
    }

    public boolean isEvAdjusted(boolean z) {
        if (z) {
            return this.mFocusView.isEvAdjustedTime();
        }
        return this.mFocusView.isEvAdjusted();
    }

    public boolean isFocusViewVisible() {
        return this.mFocusView.isVisible();
    }

    public void setEvAdjustable(boolean z) {
        this.mFocusView.setEvAdjustable(z);
    }

    public void initializeFocusView(ExposureViewListener exposureViewListener) {
        this.mFocusView.initialize(exposureViewListener);
    }

    public void setFocusViewType(boolean z) {
        this.mFocusView.setFocusType(z);
    }

    public void setFocusViewPosition(int i, int i2, int i3) {
        this.mFocusView.setPosition(i, i2, i3);
        this.mFaceView.forceHideRect();
    }

    public void clearFocusView(int i) {
        this.mFocusView.clear(i);
    }

    public void reShowFaceRect() {
        this.mFaceView.reShowFaceRect();
    }

    public boolean initializeObjectTrack(RectF rectF, boolean z) {
        this.mFocusView.clear();
        this.mObjectView.clear();
        this.mObjectView.setVisibility(0);
        return this.mObjectView.initializeTrackView(rectF, z);
    }

    public boolean initializeObjectView(RectF rectF, boolean z) {
        return this.mObjectView.initializeTrackView(rectF, z);
    }

    public boolean isAdjustingObjectView() {
        return this.mObjectView.isAdjusting();
    }

    public boolean isObjectTrackFailed() {
        return this.mObjectView.isTrackFailed();
    }

    public void onStopObjectTrack() {
        this.mObjectView.clear();
        this.mObjectView.setVisibility(8);
    }

    public void setDisplaySize(int i, int i2) {
        this.mObjectView.setDisplaySize(i, i2);
    }

    public void setPreviewSize(int i, int i2) {
        if (this.mAutoZoomOverlay != null) {
            this.mAutoZoomOverlay.setPreviewSize(new Size(i, i2));
        }
    }

    public void setObjectViewListener(ObjectViewListener objectViewListener) {
        this.mObjectView.setObjectViewListener(objectViewListener);
    }

    public RectF getFocusRectInPreviewFrame() {
        return this.mObjectView.getFocusRectInPreviewFrame();
    }

    public boolean isFaceExists(int i) {
        if (i == 1) {
            return this.mFaceView.faceExists();
        }
        if (i != 3) {
            return false;
        }
        return this.mObjectView.faceExists();
    }

    public boolean isFaceStable(int i) {
        if (i == 1) {
            return this.mFaceView.isFaceStable();
        }
        if (i != 3) {
            return false;
        }
        return this.mObjectView.isFaceStable();
    }

    public boolean isIndicatorVisible(int i) {
        boolean z = true;
        switch (i) {
            case 1:
                if (this.mFaceView.getVisibility() != 0) {
                    z = false;
                }
                return z;
            case 2:
                if (this.mFocusView.getVisibility() != 0) {
                    z = false;
                }
                return z;
            case 3:
                if (this.mObjectView.getVisibility() != 0) {
                    z = false;
                }
                return z;
            default:
                return false;
        }
    }

    public boolean isNeedExposure(int i) {
        if (i == 1) {
            return this.mFaceView.isNeedExposure();
        }
        if (i != 3) {
            return false;
        }
        return this.mObjectView.isNeedExposure();
    }

    public boolean setFaces(int i, CameraHardwareFace[] cameraHardwareFaceArr, Rect rect, float f) {
        if (i == 1) {
            return this.mFaceView.setFaces(cameraHardwareFaceArr, rect, f);
        }
        if (i != 3) {
            return false;
        }
        if (cameraHardwareFaceArr != null && cameraHardwareFaceArr.length > 0) {
            this.mObjectView.setObject(cameraHardwareFaceArr[0]);
        }
        return true;
    }

    public void clearIndicator(int i) {
        switch (i) {
            case 1:
                this.mFaceView.clear();
                return;
            case 2:
                throw new RuntimeException("not allowed call in this method");
            case 3:
                this.mObjectView.clear();
                return;
            default:
                return;
        }
    }

    public void setActiveIndicator(int i) {
        this.mActiveIndicator = i;
    }

    public int getActiveIndicator() {
        return this.mActiveIndicator;
    }

    public void showIndicator(int i, int i2) {
        switch (i) {
            case 1:
                showIndicator(this.mFaceView, i2);
                return;
            case 2:
                showIndicator(this.mFocusView, i2);
                return;
            case 3:
                showIndicator(this.mObjectView, i2);
                return;
            default:
                return;
        }
    }

    private void showIndicator(FocusIndicator focusIndicator, int i) {
        switch (i) {
            case 1:
                focusIndicator.showStart();
                return;
            case 2:
                focusIndicator.showSuccess();
                return;
            case 3:
                focusIndicator.showFail();
                return;
            default:
                return;
        }
    }

    public RectF getFocusRect(int i) {
        if (i == 1) {
            return this.mFaceView.getFocusRect();
        }
        if (i == 3) {
            return this.mObjectView.getFocusRect();
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getFragmentTag());
        stringBuilder.append(": unexpected type ");
        stringBuilder.append(i);
        Log.w(str, stringBuilder.toString());
        return new RectF();
    }

    public void lightingDetectFace(CameraHardwareFace[] cameraHardwareFaceArr) {
        int i = 5;
        if (cameraHardwareFaceArr == null || cameraHardwareFaceArr.length == 0 || cameraHardwareFaceArr.length > 1) {
            consumeResult(5);
        } else if (this.lastConfirmTime != -1) {
            this.mFaceView.transToViewRect(cameraHardwareFaceArr[0].rect, this.mLightingView.getFaceViewRectF());
            RectF faceViewRectF = this.mLightingView.getFaceViewRectF();
            RectF focusRectF = this.mLightingView.getFocusRectF();
            if (isRectIntersect(faceViewRectF, focusRectF)) {
                getMergeRect(faceViewRectF, focusRectF);
                float width = faceViewRectF.width() * faceViewRectF.height();
                float width2 = this.mergeRectF.width() * this.mergeRectF.height();
                float width3 = focusRectF.width() * focusRectF.height();
                float f = 0.2f * width3;
                width3 *= 0.5f;
                float f2 = 0.5f * width;
                if (width2 >= f2) {
                    i = (width2 >= f || width2 <= f2) ? (width2 >= width3 || width >= width3) ? 3 : 6 : 4;
                }
            }
            consumeResult(i);
        }
    }

    private void consumeResult(int i) {
        if (System.currentTimeMillis() - this.lastConfirmTime >= 1000) {
            this.lastConfirmTime = System.currentTimeMillis();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append("");
            Log.d("faceResult:", stringBuilder.toString());
            if (this.lastFaceResult != i) {
                this.lastFaceResult = i;
                this.mLightingView.post(new Runnable() {
                    public void run() {
                        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                        if (topAlert != null) {
                            topAlert.alertLightingHint(FragmentMainContent.this.lastFaceResult);
                        }
                        VerticalProtocol verticalProtocol = (VerticalProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(198);
                        if (verticalProtocol != null) {
                            verticalProtocol.alertLightingHint(FragmentMainContent.this.lastFaceResult);
                        }
                    }
                });
                boolean z = i == 6;
                if (this.lastFaceSuccess != z) {
                    this.lastFaceSuccess = z;
                    this.mLightingView.post(new Runnable() {
                        public void run() {
                            if (FragmentMainContent.this.lastFaceSuccess) {
                                FragmentMainContent.this.mLightingView.triggerAnimateSuccess();
                            } else {
                                FragmentMainContent.this.mLightingView.triggerAnimateFocusing();
                            }
                        }
                    });
                }
            }
        }
    }

    private RectF getMergeRect(RectF rectF, RectF rectF2) {
        float max = Math.max(rectF.left, rectF2.left);
        float min = Math.min(rectF.right, rectF2.right);
        this.mergeRectF.set(max, Math.max(rectF.top, rectF2.top), min, Math.min(rectF.bottom, rectF2.bottom));
        return this.mergeRectF;
    }

    private boolean isRectIntersect(RectF rectF, RectF rectF2) {
        return rectF2.right >= rectF.left && rectF2.left <= rectF.right && rectF2.bottom >= rectF.top && rectF2.top <= rectF.bottom;
    }

    public void lightingStart() {
        this.mLightingView.triggerAnimateStart();
        this.lastFaceResult = -1;
        this.lastFaceSuccess = false;
        this.lastConfirmTime = System.currentTimeMillis();
        this.mFaceView.setLightingOn(true);
        this.mAfRegionsView.setLightingOn(true);
    }

    public void lightingFocused() {
        this.mLightingView.triggerAnimateSuccess();
    }

    public void lightingCancel() {
        this.mLightingView.triggerAnimateExit();
        this.lastConfirmTime = -1;
        this.mFaceView.setLightingOn(false);
        this.mAfRegionsView.setLightingOn(false);
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void onDestroy() {
        super.onDestroy();
        destroyEffectCropView();
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        boolean isIntentAction = DataRepository.dataItemGlobal().isIntentAction();
        if (isIntentAction != this.mIsIntentAction) {
            this.mIsIntentAction = isIntentAction;
            hideReviewViews();
        }
        if (DataRepository.dataItemGlobal().getCurrentCameraId() != this.mLastCameraId) {
            this.mLastCameraId = DataRepository.dataItemGlobal().getCurrentCameraId();
            if (Util.isAccessible()) {
                if (this.mLastCameraId != 1) {
                    this.mPreviewFrame.setContentDescription(getString(2131296501));
                    this.mPreviewFrame.announceForAccessibility(getString(2131296501));
                } else if (Util.isScreenSlideOff(getActivity())) {
                    this.mPreviewFrame.setContentDescription(getString(2131296503));
                    this.mPreviewFrame.announceForAccessibility(getString(2131296503));
                } else {
                    this.mPreviewFrame.setContentDescription(getString(2131296502));
                    this.mPreviewFrame.announceForAccessibility(getString(2131296502));
                }
            }
        }
        switch (i) {
            case 2:
                adjustViewHeight();
                return;
            case 3:
                adjustViewHeight();
                return;
            default:
                return;
        }
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        this.mPreviewFrame.updateReferenceLineAccordSquare();
        this.mPreviewFrame.updatePreviewGrid();
        this.mFocusView.reInit();
        this.mEffectCropView.updateVisible();
    }

    public boolean onBackEvent(int i) {
        return false;
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        this.mFaceView.setOrientation((360 - i) % 360, false);
        this.mAfRegionsView.setOrientation(i, false);
        this.mLightingView.setOrientation(i, false);
        this.mFocusView.setOrientation(i, false);
        list.add(this.mFocusView);
        list.add(this.mMultiSnapNum);
        list.add(this.mCaptureDelayNumber);
    }

    public List<WaterMarkData> getFaceWaterMarkInfos() {
        return this.mFaceView.getFaceWaterMarkInfos();
    }

    public void setAfRegionView(MeteringRectangle[] meteringRectangleArr, Rect rect, float f) {
        this.mAfRegionsView.setAfRegionRect(meteringRectangleArr, rect, f);
    }

    public void setCenterHint(int i, String str, String str2, int i2) {
        this.mHandler.removeCallbacksAndMessages(this.mPreviewCenterHint);
        if (i == 0) {
            this.mCenterHintText.setText(str);
            if (str == null || str.equals("")) {
                this.mCenterHintText.setVisibility(8);
            } else {
                this.mCenterHintText.setVisibility(0);
            }
            if (str2 == null || str2.equals("")) {
                this.mCenterHintIcon.setVisibility(8);
            } else {
                c.a((Fragment) this).a(str2).a(this.mCenterHintIcon);
                this.mCenterHintIcon.setVisibility(0);
            }
            if (i2 > 0) {
                this.mHandler.postAtTime(new Runnable() {
                    public void run() {
                        FragmentMainContent.this.mPreviewCenterHint.setVisibility(8);
                    }
                }, this.mPreviewCenterHint, SystemClock.uptimeMillis() + ((long) i2));
            }
        }
        this.mPreviewCenterHint.setVisibility(i);
    }

    public void updateContentDescription() {
        this.mPreviewFrame.setContentDescription(getString(2131296502));
        this.mPreviewFrame.announceForAccessibility(getString(2131296502));
    }

    public void onAutoZoomStarted() {
        this.mAutoZoomOverlay.setViewEnable(true);
        this.mAutoZoomOverlay.setViewActive(false);
        this.mAutoZoomOverlay.clear(0);
    }

    public void onAutoZoomStopped() {
        this.mAutoZoomOverlay.setViewEnable(false);
        this.mAutoZoomOverlay.setViewActive(false);
        this.mAutoZoomOverlay.clear(4);
    }

    public void feedData(AutoZoomCaptureResult autoZoomCaptureResult) {
        this.mAutoZoomOverlay.feedData(autoZoomCaptureResult);
    }

    public void onTrackingStarted(RectF rectF) {
        AutoZoomModuleProtocol autoZoomModuleProtocol = (AutoZoomModuleProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(215);
        if (autoZoomModuleProtocol != null) {
            autoZoomModuleProtocol.startTracking(rectF);
        }
    }

    public void onTrackingStopped(int i) {
        this.mAutoZoomOverlay.setViewActive(false);
        this.mAutoZoomOverlay.clear(0);
    }

    public boolean isAutoZoomActive() {
        return this.mAutoZoomOverlay.isViewActive();
    }

    public boolean isAutoZoomEnabled() {
        return this.mAutoZoomOverlay.isViewEnabled();
    }
}
