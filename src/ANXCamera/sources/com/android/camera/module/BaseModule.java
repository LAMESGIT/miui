package com.android.camera.module;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.annotation.WorkerThread;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import com.android.camera.Camera;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraErrorCallbackImpl;
import com.android.camera.CameraSettings;
import com.android.camera.CameraSize;
import com.android.camera.FileCompat;
import com.android.camera.JpegEncodingQualityMappings;
import com.android.camera.MutexModeManager;
import com.android.camera.MutexModeManager.MutexCallBack;
import com.android.camera.ThermalDetector;
import com.android.camera.Util;
import com.android.camera.constant.AutoFocus;
import com.android.camera.constant.GlobalConstant;
import com.android.camera.constant.UpdateConstant.UpdateType;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.global.DataItemGlobal;
import com.android.camera.data.provider.DataProvider.ProviderEditor;
import com.android.camera.effect.EffectController;
import com.android.camera.effect.draw_mode.DrawExtTexAttribute;
import com.android.camera.log.Log;
import com.android.camera.module.loader.ActionHideLensDirtyDetectHint;
import com.android.camera.module.loader.ActionUpdateLensDirtyDetect;
import com.android.camera.module.loader.StartControl;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera.preferences.SettingsOverrider;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BackStack;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.DualController;
import com.android.camera.protocol.ModeProtocol.EvChangedProtocol;
import com.android.camera.protocol.ModeProtocol.MainContentProtocol;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.protocol.ModeProtocol.UltraWideProtocol;
import com.android.camera.protocol.ModeProtocol.ZoomProtocol;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.storage.Storage;
import com.android.camera.ui.FocusView.ExposureViewListener;
import com.android.camera2.Camera2Proxy;
import com.android.camera2.Camera2Proxy.CameraMetaDataCallback;
import com.android.camera2.CameraCapabilities;
import com.android.camera2.CaptureResultParser;
import com.mi.config.b;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseModule implements MutexCallBack, Module, EvChangedProtocol, UltraWideProtocol, ZoomProtocol, ExposureViewListener, CameraMetaDataCallback, Consumer<int[]> {
    protected static final int BACK_PRESSED_TIME_INTERVAL = 3000;
    public static final int[] CAMERA_MODES = new int[]{0, 2, 4, 6};
    public static final int CAMERA_MODE_IMAGE_CAPTURE = 2;
    public static final int CAMERA_MODE_NORMAL = 0;
    public static final int CAMERA_MODE_SCAN_QR_CODE = 6;
    public static final int CAMERA_MODE_VIDEO_CAPTURE = 4;
    protected static final boolean DEBUG = Util.isDebugOsBuild();
    public static final int LENS_DIRTY_DETECT_HINT_DURATION = 8000;
    protected static final int LENS_DIRTY_DETECT_TIMEOUT = 15000;
    protected static final int SCREEN_DELAY = 60000;
    protected static final int SCREEN_DELAY_KEYGUARD = 30000;
    public static final int SHUTTER_DOWN_FROM_BUTTON = 2;
    public static final int SHUTTER_DOWN_FROM_HARD_KEY = 1;
    public static final int SHUTTER_DOWN_FROM_UNKNOWN = 0;
    public static final int SHUTTER_DOWN_FROM_UNLOCK_OR_LONG_CLICK_OR_AUDIO = 3;
    private static final String TAG = "BaseModule";
    private static final float ULTRA_WIDE_ZOOM_MAX = 2.1f;
    private boolean isShowPreviewDebugInfo;
    protected boolean isZooming;
    protected Camera mActivity;
    protected int mActualCameraId;
    protected boolean mAeLockSupported;
    protected boolean mAwbLockSupported;
    protected int mBogusCameraId;
    protected Camera2Proxy mCamera2Device;
    protected CameraCapabilities mCameraCapabilities;
    protected boolean mCameraDisabled;
    protected int mCameraDisplayOrientation;
    protected boolean mCameraHardwareError;
    private int mCameraState = 0;
    protected ContentResolver mContentResolver;
    protected boolean mContinuousFocusSupported;
    protected float mDeviceRotation = -1.0f;
    protected Rect mDisplayRect;
    protected int mDisplayRotation = -1;
    protected CameraErrorCallbackImpl mErrorCallback;
    private int mEvState;
    private int mEvValue;
    protected String mFlashAutoModeState;
    protected boolean mFocusAreaSupported;
    protected boolean mIgnoreFocusChanged;
    private boolean mIgnoreTouchEvent;
    private AtomicBoolean mIsCreated = new AtomicBoolean(false);
    private AtomicBoolean mIsDeparted = new AtomicBoolean(false);
    private AtomicBoolean mIsFrameAvailable = new AtomicBoolean(false);
    protected long mLastBackPressedTime;
    private Disposable mLensDirtyDetectDisposable;
    private Disposable mLensDirtyDetectHintDisposable;
    protected MainContentProtocol mMainProtocol;
    protected long mMainThreadId = Thread.currentThread().getId();
    protected int mMaxFaceCount;
    protected boolean mMeteringAreaSupported;
    protected int mModuleIndex;
    protected MutexModeManager mMutexModePicker;
    protected boolean mObjectTrackingStarted;
    protected boolean mOpenCameraFail;
    protected int mOrientation = -1;
    protected int mOrientationCompensation = 0;
    protected CameraSize mOutPutSize;
    protected volatile boolean mPaused;
    protected int mPendingScreenSlideKeyCode;
    protected CameraSize mPictureSize;
    protected CameraSize mPreviewSize;
    private boolean mRestoring;
    protected SettingsOverrider mSettingsOverrider = new SettingsOverrider();
    protected long mSurfaceCreatedTimestamp;
    protected int mTriggerMode = 10;
    protected int mUIStyle = -1;
    protected String mUnInterruptableReason;
    private Disposable mUpdateWorkThreadDisposable;
    private ObservableEmitter<int[]> mUpdateWorkThreadEmitter;
    protected float mZoomMaxRatio = 1.0f;
    private float mZoomScaled;
    protected boolean mZoomSupported;
    protected float mZoomValue = 1.0f;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CameraMode {
    }

    public abstract int getOperatingMode();

    public abstract void openSettingActivity();

    public boolean isDeparted() {
        return this.mIsDeparted.get();
    }

    public void setFrameAvailable(boolean z) {
        this.mIsFrameAvailable.set(z);
    }

    public boolean isFrameAvailable() {
        return this.mIsFrameAvailable.get();
    }

    public void setDeparted() {
        Log.d(TAG, "setDeparted");
        if (this.mUpdateWorkThreadDisposable != null) {
            this.mUpdateWorkThreadDisposable.dispose();
        }
        if (this.mLensDirtyDetectDisposable != null) {
            this.mLensDirtyDetectDisposable.dispose();
        }
        if (this.mLensDirtyDetectHintDisposable != null) {
            this.mLensDirtyDetectHintDisposable.dispose();
        }
        if (this.mActivity != null) {
            this.mActivity.hideLensDirtyDetectedHint();
        }
        this.mIsDeparted.set(true);
        this.mIsFrameAvailable.set(false);
    }

    public int getModuleIndex() {
        return this.mModuleIndex;
    }

    /* Access modifiers changed, original: protected */
    public void openCamera() {
    }

    public Camera getActivity() {
        return this.mActivity;
    }

    public void setActivity(Camera camera) {
        this.mActivity = camera;
    }

    public void onCreate(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onCreate moduleIndex->");
        stringBuilder.append(i);
        stringBuilder.append(", cameraId->");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        this.mModuleIndex = i;
        this.mBogusCameraId = i2;
        this.mContentResolver = this.mActivity.getContentResolver();
        this.mErrorCallback = new CameraErrorCallbackImpl(this.mActivity);
        this.mMainProtocol = (MainContentProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(166);
        this.mMutexModePicker = new MutexModeManager(this);
        this.mUpdateWorkThreadDisposable = Observable.create(new ObservableOnSubscribe<int[]>() {
            public void subscribe(ObservableEmitter<int[]> observableEmitter) throws Exception {
                BaseModule.this.mUpdateWorkThreadEmitter = observableEmitter;
            }
        }).observeOn(GlobalConstant.sCameraSetupScheduler).subscribe((Consumer) this);
        if (DataRepository.dataItemFeature().fJ() && CameraSettings.isLensDirtyDetectEnabled()) {
            this.mLensDirtyDetectDisposable = Completable.complete().delay(15000, TimeUnit.MILLISECONDS, GlobalConstant.sCameraSetupScheduler).doOnComplete(new ActionUpdateLensDirtyDetect(this, true)).subscribe();
        }
        setCreated(true);
        this.mIsDeparted.set(false);
        this.isShowPreviewDebugInfo = Util.isShowPreviewDebugInfo();
    }

    private void setCreated(boolean z) {
        this.mIsCreated.set(z);
    }

    public void setModuleIndex(int i) {
        this.mModuleIndex = i;
    }

    public boolean isCreated() {
        return this.mIsCreated.get();
    }

    public void onNewIntent() {
    }

    @CallSuper
    public void onDestroy() {
        if (this.mActivity != null) {
            this.mActivity.getSensorStateManager().setSensorStateListener(null);
        }
        setCreated(false);
        this.mActivity.getImageSaver().onModuleDestroy();
        Log.d(TAG, "onDestroy");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != Util.KEYCODE_SLIDE_OFF && i != Util.KEYCODE_SLIDE_ON) {
            return false;
        }
        if (!isUnInterruptable()) {
            this.mPendingScreenSlideKeyCode = 0;
            this.mActivity.handleScreenSlideKeyEvent(i, keyEvent);
            return true;
        } else if (i == Util.KEYCODE_SLIDE_OFF && this.mActivity.getCameraIntentManager().isFromScreenSlide().booleanValue() && !this.mActivity.isModeSwitched()) {
            this.mActivity.moveTaskToBack(false);
            this.mActivity.overridePendingTransition(2131034114, 2131034115);
            return true;
        } else {
            this.mPendingScreenSlideKeyCode = i;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pending screen slide: ");
            stringBuilder.append(i);
            stringBuilder.append(", reason: ");
            stringBuilder.append(getUnInterruptableReason());
            Log.d(str, stringBuilder.toString());
            return false;
        }
    }

    public void registerProtocol() {
        Log.d(TAG, "registerProtocol");
        ModeCoordinatorImpl.getInstance().attachProtocol(170, this);
        ModeCoordinatorImpl.getInstance().attachProtocol(200, this);
    }

    public void unRegisterProtocol() {
        Log.d(TAG, "unRegisterProtocol");
        ModeCoordinatorImpl.getInstance().detachProtocol(170, this);
        ModeCoordinatorImpl.getInstance().detachProtocol(200, this);
    }

    public void unRegisterModulePersistProtocol() {
        Log.d(TAG, "unRegisterModulePersist");
    }

    public void onEvChanged(int i, int i2) {
        if (isAlive()) {
            this.mEvValue = i;
            this.mEvState = i2;
            if (i2 == 1) {
                CameraSettings.writeExposure(i);
                updateStatusBar(1);
            }
            updatePreferenceInWorkThread(12);
        }
    }

    /* Access modifiers changed, original: protected|final */
    @WorkerThread
    public final void setEvValue() {
        if (isAlive()) {
            if (this.mEvState == 2) {
                this.mCamera2Device.setAWBLock(false);
            } else if (this.mEvState == 1) {
                this.mCamera2Device.setExposureCompensation(this.mEvValue);
                this.mCamera2Device.setAWBLock(true);
            }
        }
    }

    public void resetEvValue() {
        if (isDeviceAlive()) {
            this.mCamera2Device.setExposureCompensation(0);
            this.mCamera2Device.setAWBLock(false);
            CameraSettings.resetExposure();
            updatePreferenceInWorkThread(new int[0]);
        }
    }

    public void notifyZooming(boolean z) {
        this.isZooming = z;
    }

    public void notifyDualZoom(boolean z) {
        if (this.mCamera2Device != null) {
            this.mCamera2Device.setOpticalZoomToTele(z);
            this.mCamera2Device.resumePreview();
        }
    }

    public void onZoomRatioChanged(float f, boolean z) {
        onZoomValueChanged(f, z);
    }

    public void onZoomSwitchCamera() {
        if (isAlive() && CameraSettings.isSupportedOpticalZoom() && CameraSettings.isSwitchCameraZoomMode()) {
            this.mActivity.getCameraScreenNail().disableSwitchAnimationOnce();
        }
    }

    @CallSuper
    public void onResume() {
        Log.d(TAG, "onResume");
        this.mPaused = false;
    }

    @CallSuper
    public void onPause() {
        Log.d(TAG, "onPause");
        this.mPaused = true;
        this.mPendingScreenSlideKeyCode = 0;
        this.mUpdateWorkThreadDisposable.dispose();
        if (this.mLensDirtyDetectDisposable != null) {
            this.mLensDirtyDetectDisposable.dispose();
        }
        if (this.mLensDirtyDetectHintDisposable != null) {
            this.mLensDirtyDetectHintDisposable.dispose();
        }
        if (this.mActivity != null) {
            this.mActivity.hideLensDirtyDetectedHint();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            this.mIgnoreFocusChanged = false;
        }
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean isVideoRecording() {
        return false;
    }

    public boolean isSelectingCapturedResult() {
        return false;
    }

    public boolean shouldReleaseLater() {
        return false;
    }

    public boolean isPostProcessing() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean currentIsMainThread() {
        return this.mMainThreadId == Thread.currentThread().getId();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 82 && !this.mActivity.startFromSecureKeyguard()) {
            openSettingActivity();
        }
        return false;
    }

    private void setIgnoreTouchEvent(boolean z) {
        this.mIgnoreTouchEvent = z;
    }

    public boolean isIgnoreTouchEvent() {
        return this.mIgnoreTouchEvent;
    }

    public List<String> getSupportedSettingKeys() {
        return new ArrayList();
    }

    public boolean onScaleBegin(float f, float f2) {
        this.mZoomScaled = Float.MIN_VALUE;
        return true;
    }

    public boolean onScale(float f, float f2, float f3) {
        if (isZoomEnabled()) {
            this.mZoomScaled += (f3 - 1.0f) / 4.0f;
            if (scaleZoomValue(this.mZoomScaled)) {
                this.mZoomScaled = Float.MIN_VALUE;
                return true;
            }
        }
        return false;
    }

    public void onScaleEnd() {
    }

    /* Access modifiers changed, original: protected */
    public boolean isZoomEnabled() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean isZoomSupported() {
        return this.mZoomSupported;
    }

    protected static String getColorEffectKey() {
        if (b.gi()) {
            return "pref_camera_shader_coloreffect_key";
        }
        return CameraSettings.KEY_COLOR_EFFECT;
    }

    public boolean scaleZoomValue(float f) {
        float f2 = this.mZoomMaxRatio * f;
        if (Math.abs(f2) < 0.01f) {
            return false;
        }
        return onZoomValueChanged(this.mZoomValue + f2);
    }

    /* Access modifiers changed, original: protected */
    public boolean retryOnceIfCameraError(Handler handler) {
        if (DataRepository.dataItemGlobal().isRetriedIfCameraError() || this.mActivity.isActivityPaused()) {
            return false;
        }
        Log.e(TAG, "onCameraException: retry1");
        DataRepository.dataItemGlobal().setRetriedIfCameraError(true);
        handler.post(new Runnable() {
            public void run() {
                BaseModule.this.mActivity.onModeSelected(StartControl.create(BaseModule.this.mModuleIndex).setViewConfigType(1).setNeedBlurAnimation(false).setNeedReConfigureCamera(true));
            }
        });
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onCameraException() {
        if (currentIsMainThread()) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onCameraException: ");
            stringBuilder.append(this.mModuleIndex);
            stringBuilder.append(" | ");
            stringBuilder.append(this.mBogusCameraId);
            Log.e(str, stringBuilder.toString());
            if ((this.mOpenCameraFail || this.mCameraHardwareError) && ((!this.mActivity.isActivityPaused() || this.mOpenCameraFail) && this.mActivity.couldShowErrorDialog())) {
                int i;
                Camera camera = this.mActivity;
                if (Util.isInVideoCall(this.mActivity)) {
                    i = 2131296694;
                } else if (CameraSettings.updateOpenCameraFailTimes() > 1) {
                    i = 2131296263;
                } else {
                    i = 2131296262;
                }
                Util.showErrorAndFinish(camera, i);
                this.mActivity.showErrorDialog();
            }
            if (this.mCameraDisabled && this.mActivity.couldShowErrorDialog()) {
                Util.showErrorAndFinish(this.mActivity, 2131296264);
                this.mActivity.showErrorDialog();
            }
        } else {
            sendOpenFailMessage();
        }
        enableCameraControls(false);
    }

    /* Access modifiers changed, original: protected */
    public void sendOpenFailMessage() {
    }

    /* Access modifiers changed, original: protected */
    public boolean hasCameraException() {
        return this.mCameraDisabled || this.mOpenCameraFail || this.mCameraHardwareError;
    }

    public void requestRender() {
    }

    public void onLongPress(int i, int i2) {
    }

    public void onPreviewPixelsRead(byte[] bArr, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(DrawExtTexAttribute drawExtTexAttribute) {
    }

    public void onSurfaceTextureReleased() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onOrientationChanged(int i, int i2) {
        if (i != -1) {
            this.mOrientation = i;
            EffectController.getInstance().setOrientation(Util.getShootOrientation(this.mActivity, this.mOrientation));
            checkActivityOrientation();
            if (this.mOrientationCompensation != i2) {
                this.mOrientationCompensation = i2;
            }
        }
    }

    public void checkActivityOrientation() {
        if (isDeviceAlive() && this.mDisplayRotation != Util.getDisplayRotation(this.mActivity)) {
            checkDisplayOrientation();
        }
    }

    public void checkDisplayOrientation() {
        this.mDisplayRotation = Util.getDisplayRotation(this.mActivity);
        this.mCameraDisplayOrientation = Util.getDisplayOrientation(this.mDisplayRotation, this.mBogusCameraId);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkDisplayOrientation: ");
        stringBuilder.append(this.mDisplayRotation);
        stringBuilder.append(" | ");
        stringBuilder.append(this.mCameraDisplayOrientation);
        Log.v(str, stringBuilder.toString());
        if (this.mActivity.getCameraScreenNail() != null) {
            this.mActivity.getCameraScreenNail().setDisplayOrientation(this.mDisplayRotation);
        }
    }

    public void onUserInteraction() {
    }

    public void onStop() {
    }

    public void onSharedPreferenceChanged() {
    }

    public void preTransferOrientation(int i, int i2) {
        this.mOrientation = i;
        this.mOrientationCompensation = i2;
    }

    public View findViewById(int i) {
        return this.mActivity.findViewById(i);
    }

    public String getString(int i) {
        return CameraAppImpl.getAndroidContext().getString(i);
    }

    public Resources getResources() {
        return this.mActivity.getResources();
    }

    public Window getWindow() {
        return this.mActivity.getWindow();
    }

    /* Access modifiers changed, original: protected */
    public boolean handleVolumeKeyEvent(boolean z, boolean z2, int i) {
        if (!isAlive()) {
            return true;
        }
        String string;
        if (ModuleManager.isLiveModule()) {
            string = DataRepository.dataItemGlobal().getString(CameraSettings.KEY_VOLUME_LIVE_FUNCTION, getString(2131296545));
        } else if (ModuleManager.isVideoCategory(getModuleIndex())) {
            string = DataRepository.dataItemGlobal().getString(CameraSettings.KEY_VOLUME_VIDEO_FUNCTION, getString(2131296544));
        } else {
            string = DataRepository.dataItemGlobal().getString(CameraSettings.KEY_VOLUME_CAMERA_FUNCTION, getString(2131296543));
        }
        if (string.equals(getString(2131296540)) || string.equals(getString(2131296541))) {
            performVolumeKeyClicked(string, i, z2);
            return true;
        } else if (ModuleManager.isPanoramaModule() || !string.equals(getString(2131296542)) || !isZoomSupported() || !z2) {
            return false;
        } else {
            if (!isZoomEnabled()) {
                return true;
            }
            if (z) {
                z = addZoom(0.1f);
            } else {
                z = addZoom(-0.1f);
            }
            if (i == 0 && r5) {
                CameraStatUtil.trackZoomAdjusted(CameraStat.ZOOM_MODE_VOLUME);
            }
            return true;
        }
    }

    /* Access modifiers changed, original: protected */
    public void initializeCapabilities() {
        this.mCameraCapabilities.setOperatingMode(getOperatingMode());
        this.mAeLockSupported = this.mCameraCapabilities.isAELockSupported();
        this.mAwbLockSupported = this.mCameraCapabilities.isAWBLockSupported();
        this.mFocusAreaSupported = this.mCameraCapabilities.isAFRegionSupported();
        this.mMeteringAreaSupported = this.mCameraCapabilities.isAERegionSupported();
        this.mMaxFaceCount = this.mCameraCapabilities.getMaxFaceCount();
        initializeZoom();
    }

    private void initializeZoom() {
        this.mZoomSupported = this.mCameraCapabilities.isZoomSupported();
        if (this.mZoomSupported) {
            this.mZoomMaxRatio = this.mCameraCapabilities.getMaxZoomRatio();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("zoomMaxRatio: ");
            stringBuilder.append(this.mZoomMaxRatio);
            Log.d(str, stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean addZoom(float f) {
        return onZoomValueChanged(this.mZoomValue + f);
    }

    public void enableCameraControls(boolean z) {
        setIgnoreTouchEvent(z ^ 1);
    }

    public void exitMutexMode(int i) {
    }

    public void enterMutexMode(int i) {
    }

    public MutexModeManager getMutexModePicker() {
        return this.mMutexModePicker;
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Missing block: B:12:0x0021, code skipped:
            return;
     */
    public final void playCameraSound(int r2) {
        /*
        r1 = this;
        r0 = r1.mActivity;
        if (r0 == 0) goto L_0x0021;
    L_0x0004:
        r0 = r1.mActivity;
        r0 = r0.isActivityPaused();
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        goto L_0x0021;
    L_0x000d:
        r0 = r1.isNeedMute();
        if (r0 == 0) goto L_0x0014;
    L_0x0013:
        return;
    L_0x0014:
        r0 = com.android.camera.CameraSettings.isCameraSoundOpen();
        if (r0 != 0) goto L_0x001b;
    L_0x001a:
        return;
    L_0x001b:
        r0 = r1.mActivity;
        r0.playCameraSound(r2);
        return;
    L_0x0021:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.BaseModule.playCameraSound(int):void");
    }

    public static int getPreferencesLocalId(int i, int i2) {
        return i + i2;
    }

    public boolean isPortraitMode() {
        return this.mModuleIndex == 171;
    }

    public final boolean isFrontCamera() {
        return this.mBogusCameraId == 1;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isBackCamera() {
        return this.mBogusCameraId == 0;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isDualCamera() {
        return this.mActualCameraId == Camera2DataContainer.getInstance().getMuxCameraId() || this.mActualCameraId == Camera2DataContainer.getInstance().getBokehCameraId();
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isDualFrontCamera() {
        return this.mActualCameraId == Camera2DataContainer.getInstance().getMuxFrontCameraId() || this.mActualCameraId == Camera2DataContainer.getInstance().getBokehFrontCameraId();
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isBokehFrontCamera() {
        return this.mActualCameraId == Camera2DataContainer.getInstance().getBokehFrontCameraId();
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isUltraWideBackCamera() {
        return this.mActualCameraId == Camera2DataContainer.getInstance().getUltraWideCameraId();
    }

    public boolean isInTapableRect(int i, int i2) {
        if (this.mDisplayRect == null) {
            return false;
        }
        return getTapableRectWithEdgeSlop().contains(i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void mapTapCoordinate(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point) obj;
            point.x -= this.mDisplayRect.left;
            point.y -= this.mDisplayRect.top;
        } else if (obj instanceof RectF) {
            RectF rectF = (RectF) obj;
            rectF.left -= (float) this.mDisplayRect.left;
            rectF.right -= (float) this.mDisplayRect.left;
            rectF.top -= (float) this.mDisplayRect.top;
            rectF.bottom -= (float) this.mDisplayRect.top;
        }
    }

    private Rect getTapableRectWithEdgeSlop() {
        int i;
        int i2;
        Rect rect = new Rect(this.mDisplayRect);
        if (!isVideoRecording()) {
            i = this.mUIStyle;
            if (i == 1) {
                rect.bottom -= CameraSettings.BOTTOM_CONTROL_HEIGHT - Util.sNavigationBarHeight;
                if (Util.isLongRatioScreen && !Util.isNotchDevice) {
                    rect.bottom += Math.abs(rect.top - ((Util.sWindowHeight - ((int) (((float) (Util.sWindowWidth * 16)) / 9.0f))) - Util.sNavigationBarHeight));
                }
            } else if (i == 3) {
                rect.bottom -= CameraSettings.BOTTOM_CONTROL_HEIGHT;
            } else if (this.mModuleIndex == 165) {
                i2 = (((int) (((float) Util.sWindowWidth) / 0.75f)) - Util.sWindowWidth) / 2;
                rect.top = ((Util.sWindowHeight - CameraSettings.BOTTOM_CONTROL_HEIGHT) - Util.sWindowWidth) - i2;
                rect.bottom -= i2;
            }
        }
        i = Util.calculateDefaultPreviewEdgeSlop(this.mActivity);
        i2 = SystemProperties.getInt("camera_touch_edge_slop", -1);
        if (i2 != -1) {
            i = Util.dpToPixel((float) i2);
        }
        rect.inset(i, i);
        return rect;
    }

    private boolean onZoomValueChanged(float f) {
        return onZoomValueChanged(f, false);
    }

    private boolean onZoomValueChanged(float f, boolean z) {
        if (!isDeviceAlive()) {
            return false;
        }
        if (f < 1.0f) {
            f = 1.0f;
        } else if (f > this.mZoomMaxRatio) {
            f = this.mZoomMaxRatio;
        }
        if (this.mZoomValue == f) {
            return false;
        }
        z = this.mZoomValue == 1.0f || f == 1.0f;
        if (!CameraSettings.isUltraWideConfigOpen(getModuleIndex())) {
            setZoomValue(f);
        } else if (f < ULTRA_WIDE_ZOOM_MAX) {
            setZoomValue(f);
        }
        if (z) {
            updatePreferenceTrampoline(11, 34, 42, 30, 20);
        }
        updatePreferenceInWorkThread(24);
        DualController dualController = (DualController) ModeCoordinatorImpl.getInstance().getAttachProtocol(182);
        if (dualController == null || !dualController.isZoomVisible()) {
            updateStatusBar(2);
        } else {
            dualController.updateZoomValue();
        }
        String simpleName = getClass().getSimpleName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("zoom=");
        stringBuilder.append(f);
        Log.d(simpleName, stringBuilder.toString());
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void updateStatusBar(int i) {
        ((TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172)).alertUpdateValue(i);
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public void setZoomValue(float f) {
        this.mZoomValue = f;
        CameraSettings.writeZoom(f);
    }

    /* Access modifiers changed, original: protected */
    public float getZoomValue() {
        return this.mZoomValue;
    }

    public float getZoomMaxRatio() {
        return this.mZoomMaxRatio;
    }

    public void notifyError() {
        this.mCameraHardwareError = true;
        setCameraState(0);
        if (this.mActivity.isActivityPaused()) {
            this.mActivity.finish();
        } else {
            onCameraException();
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isNeedMute() {
        return false;
    }

    public boolean isShowCaptureButton() {
        return false;
    }

    public boolean isShowAeAfLockIndicator() {
        return false;
    }

    public boolean isSupportFocusShoot() {
        return false;
    }

    public boolean isMeteringAreaOnly() {
        return false;
    }

    public boolean isNeedHapticFeedback() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int getCameraRotation() {
        return 0;
    }

    /* Access modifiers changed, original: protected */
    public void performVolumeKeyClicked(String str, int i, boolean z) {
    }

    public void tryRemoveCountDownMessage() {
    }

    public void onSingleTapUp(int i, int i2) {
    }

    public boolean onGestureTrack(RectF rectF, boolean z) {
        return true;
    }

    public boolean canIgnoreFocusChanged() {
        return this.mIgnoreFocusChanged;
    }

    public boolean isKeptBitmapTexture() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public int getScreenDelay() {
        return (this.mActivity == null || this.mActivity.startFromKeyguard()) ? 30000 : 60000;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(Util.KEY_KILLED_MODULE_INDEX, ModuleManager.getActiveModuleIndex());
    }

    public void setRestoring(boolean z) {
        this.mRestoring = z;
    }

    /* Access modifiers changed, original: protected */
    public boolean isRestoring() {
        return this.mRestoring;
    }

    public void updatePreviewSurface() {
    }

    /* Access modifiers changed, original: protected */
    public void updateCameraScreenNailSize(int i, int i2) {
        String simpleName = getClass().getSimpleName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateCameraScreenNailSize: ");
        stringBuilder.append(i);
        stringBuilder.append("x");
        stringBuilder.append(i2);
        Log.d(simpleName, stringBuilder.toString());
        if (!(this.mActivity.getCameraScreenNail().getWidth() == i && this.mActivity.getCameraScreenNail().getHeight() == i2 && !isSquareModeChange())) {
            this.mActivity.getCameraScreenNail().setPreviewSize(i, i2);
        }
        this.mMainProtocol.setPreviewSize(i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void onCameraOpened() {
        initializeCapabilities();
        this.mCamera2Device.setMetaDataCallback(this);
        if (isFrontCamera() && this.mActivity.isScreenSlideOff()) {
            this.mCamera2Device.setAELock(true);
        }
        updateLensDirtyDetect(false);
    }

    @CallSuper
    public void onPreviewMetaDataUpdate(CaptureResult captureResult) {
        detectLensDirty(captureResult);
        if (this.isShowPreviewDebugInfo) {
            boolean z = (isDoingAction() || this.mModuleIndex == 166) ? false : true;
            showDebug(captureResult, z);
        }
    }

    private void detectLensDirty(CaptureResult captureResult) {
        if (this.mLensDirtyDetectDisposable != null && CaptureResultParser.isLensDirtyDetected(captureResult)) {
            this.mLensDirtyDetectDisposable.dispose();
            this.mLensDirtyDetectDisposable = null;
            Completable.complete().observeOn(GlobalConstant.sCameraSetupScheduler).doOnComplete(new ActionUpdateLensDirtyDetect(this, false)).subscribe();
            if (CameraSettings.shouldShowLensDirtyDetectHint()) {
                this.mActivity.showLensDirtyDetectedHint();
                this.mLensDirtyDetectHintDisposable = Completable.complete().delay(8000, TimeUnit.MILLISECONDS, GlobalConstant.sCameraSetupScheduler).doOnComplete(new ActionHideLensDirtyDetectHint(this)).subscribe();
            }
        }
    }

    private void showDebug(final CaptureResult captureResult, final boolean z) {
        if (Util.isShowAfRegionView() && z) {
            this.mMainProtocol.setAfRegionView((MeteringRectangle[]) captureResult.get(CaptureResult.CONTROL_AF_REGIONS), getActiveArraySize(), this.mZoomValue);
        }
        if (Util.isShowDebugInfoView()) {
            this.mActivity.runOnUiThread(new Runnable() {
                public void run() {
                    if (z) {
                        BaseModule.this.mActivity.showDebugInfo(Util.getDebugInformation(captureResult));
                    } else {
                        BaseModule.this.mActivity.showDebugInfo("");
                    }
                }
            });
        }
    }

    public boolean isCaptureIntent() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void changeConflictPreference() {
        if (CameraSettings.isStereoModeOn()) {
            List<String> supportedSettingKeys = getSupportedSettingKeys();
            if (supportedSettingKeys != null) {
                ProviderEditor editor = DataRepository.dataItemConfig().editor();
                for (String str : supportedSettingKeys) {
                    if (CameraSettings.isSwitchOn(str)) {
                        editor.remove(str);
                    }
                }
                editor.apply();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isSquareModeChange() {
        if (ModuleManager.isSquareModule() != (this.mActivity.getCameraScreenNail().getRenderTargetRatio() == 2)) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void updateTipMessage(int i, @StringRes int i2, int i3) {
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips != null) {
            bottomPopupTips.showTips(i, i2, i3);
        }
    }

    /* Access modifiers changed, original: protected */
    public void hideTipMessage(@StringRes int i) {
        BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
        if (bottomPopupTips == null) {
            return;
        }
        if (i <= 0 || bottomPopupTips.containTips(i)) {
            bottomPopupTips.directlyHideTips();
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateAntiBanding(String str) {
        if (isDeviceAlive()) {
            int parseInt = Util.parseInt(str, 3);
            if (Util.isSupported(parseInt, this.mCameraCapabilities.getSupportedAntiBandingModes())) {
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("antiBanding: ");
                stringBuilder.append(str);
                Log.d(str2, stringBuilder.toString());
                this.mCamera2Device.setAntiBanding(parseInt);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void updateExposureMeteringMode() {
        if (isDeviceAlive()) {
            this.mCamera2Device.setExposureMeteringMode(CameraSettings.getExposureMeteringMode());
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateFlashPreference() {
    }

    /* Access modifiers changed, original: protected */
    public void updateHDRPreference() {
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void restoreBottom() {
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        ((BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171)).handleBackStackFromShutter();
        if (baseDelegate.getActiveFragment(2131558427) != 241) {
            baseDelegate.delegateEvent(7);
        }
    }

    @CallSuper
    public void resetMutexModeManually() {
        this.mMutexModePicker.resetMutexMode();
    }

    /* Access modifiers changed, original: 0000 */
    public void trackGeneralInfo(int i, boolean z) {
        CameraStatUtil.trackGeneralInfo(i, z, this.mModuleIndex, getTriggerMode(), isFrontCamera(), this.mMutexModePicker, this.mFlashAutoModeState);
    }

    /* Access modifiers changed, original: 0000 */
    public void trackPictureTaken(int i, boolean z, boolean z2) {
        trackPictureTaken(i, z, z2, null, false, false);
    }

    /* Access modifiers changed, original: 0000 */
    public void trackPictureTaken(int i, boolean z, boolean z2, String str, boolean z3, boolean z4) {
        CameraStatUtil.trackPictureTaken(i, z, this.mModuleIndex, isFrontCamera(), z2, str);
        trackModeCustomInfo(i);
        CameraStatUtil.trackBeautyBodyCapture();
        CameraStatUtil.trackMoonMode(z3, z4);
    }

    /* Access modifiers changed, original: protected */
    public void trackModeCustomInfo(int i) {
    }

    /* Access modifiers changed, original: protected */
    public int getTriggerMode() {
        return this.mTriggerMode;
    }

    /* Access modifiers changed, original: protected */
    public void setTriggerMode(int i) {
        this.mTriggerMode = i;
    }

    public void setCameraDevice(Camera2Proxy camera2Proxy) {
        this.mCamera2Device = camera2Proxy;
        this.mCameraCapabilities = camera2Proxy.getCapabilities();
        this.mActualCameraId = camera2Proxy.getId();
    }

    public Camera2Proxy getCameraDevice() {
        return this.mCamera2Device;
    }

    public int getActualCameraId() {
        return this.mActualCameraId;
    }

    public CameraCapabilities getCameraCapabilities() {
        return this.mCameraCapabilities;
    }

    public CameraSize getPreviewSize() {
        return this.mPreviewSize;
    }

    /* Access modifiers changed, original: protected */
    public CameraSize getJpegThumbnailSize() {
        return Util.getOptimalJpegThumbnailSize(this.mCameraCapabilities.getSupportedThumbnailSizes(), ((double) this.mPreviewSize.getWidth()) / ((double) this.mPreviewSize.getHeight()));
    }

    public static int getJpegQuality(boolean z) {
        return JpegEncodingQualityMappings.getQualityNumber(CameraSettings.getJpegQuality(z));
    }

    @WorkerThread
    public final void updatePreferenceInWorkThread(@UpdateType int... iArr) {
        if (this.mUpdateWorkThreadDisposable != null && !this.mUpdateWorkThreadDisposable.isDisposed()) {
            this.mUpdateWorkThreadEmitter.onNext(iArr);
        }
    }

    public final void updatePreferenceTrampoline(@UpdateType int... iArr) {
        consumePreference(iArr);
    }

    /* Access modifiers changed, original: protected|varargs */
    public void consumePreference(@UpdateType int... iArr) {
    }

    public void setDisplayRectAndUIStyle(Rect rect, int i) {
        this.mDisplayRect = rect;
        this.mUIStyle = i;
    }

    public void onPreviewLayoutChanged(Rect rect) {
    }

    public void onPreviewSizeChanged(int i, int i2) {
    }

    /* Access modifiers changed, original: protected */
    public int getCameraState() {
        return this.mCameraState;
    }

    /* Access modifiers changed, original: protected */
    public void setCameraState(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setCameraState: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        this.mCameraState = i;
    }

    public void updateLensDirtyDetect(boolean z) {
        if (this.mCamera2Device == null) {
            Log.e(TAG, "updateLensDirtyDetect: mCamera2Device is null...");
            return;
        }
        boolean isLensDirtyDetectEnabled = CameraSettings.isLensDirtyDetectEnabled();
        if (!(isLensDirtyDetectEnabled || this.mLensDirtyDetectDisposable == null)) {
            this.mLensDirtyDetectDisposable.dispose();
            this.mLensDirtyDetectDisposable = null;
        }
        this.mCamera2Device.setLensDirtyDetect(isLensDirtyDetectEnabled);
        if (z && isFrameAvailable() && !isDoingAction() && !isPanoramaDoing()) {
            this.mCamera2Device.resumePreview();
        }
    }

    /* Access modifiers changed, original: protected */
    public void setColorEffect(String str) {
        if (isDeviceAlive()) {
            int parseInt = Util.parseInt(str, 0);
            if (Util.isSupported(parseInt, this.mCameraCapabilities.getSupportedColorEffects())) {
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("colorEffect: ");
                stringBuilder.append(str);
                Log.d(str2, stringBuilder.toString());
                this.mCamera2Device.setColorEffect(parseInt);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void setFlashMode(String str) {
        if (isDeviceAlive()) {
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("flashMode: ");
            stringBuilder.append(str);
            Log.d(str2, stringBuilder.toString());
            int parseInt = Util.parseInt(str, 0);
            if (ThermalDetector.getInstance().thermalConstrained() || (isFrontCamera() && this.mActivity.isScreenSlideOff())) {
                parseInt = 0;
            }
            this.mCamera2Device.setOptimizedFlash(CameraSettings.isOptimizedFlashEnable());
            this.mCamera2Device.setFlashMode(parseInt);
        }
    }

    /* Access modifiers changed, original: protected */
    public void setFocusMode(String str) {
        if (isDeviceAlive()) {
            int convertToFocusMode = AutoFocus.convertToFocusMode(str);
            if (Util.isSupported(convertToFocusMode, this.mCameraCapabilities.getSupportedFocusModes())) {
                this.mCamera2Device.setFocusMode(convertToFocusMode);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void setAWBMode(String str) {
        if (isDeviceAlive()) {
            if (str.equals("manual")) {
                this.mCamera2Device.setAWBMode(0);
                this.mCamera2Device.setCustomAWB(CameraSettings.getCustomWB());
            } else {
                int parseInt = Util.parseInt(str, 1);
                if (Util.isSupported(parseInt, this.mCameraCapabilities.getSupportedAWBModes())) {
                    this.mCamera2Device.setAWBMode(parseInt);
                } else {
                    this.mCamera2Device.setAWBMode(1);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void setZoomRatio(float f) {
        this.mCamera2Device.setZoomRatio(f);
    }

    /* Access modifiers changed, original: protected */
    public void focusCenter() {
    }

    /* Access modifiers changed, original: protected */
    public Rect getCropRegion() {
        Rect activeArraySize = this.mCameraCapabilities.getActiveArraySize();
        Rect rect = new Rect();
        int width = activeArraySize.width() / 2;
        int height = activeArraySize.height() / 2;
        int width2 = (int) (((float) activeArraySize.width()) / (this.mZoomValue * 2.0f));
        int height2 = (int) (((float) activeArraySize.height()) / (2.0f * this.mZoomValue));
        rect.set(width - width2, height - height2, width + width2, height + height2);
        return rect;
    }

    /* Access modifiers changed, original: protected */
    public Rect getActiveArraySize() {
        return this.mCameraCapabilities.getActiveArraySize();
    }

    public void accept(@UpdateType int[] iArr) throws Exception {
        if (!this.mUpdateWorkThreadDisposable.isDisposed() && isDeviceAlive()) {
            consumePreference(iArr);
            if (this.mActivity.getCameraScreenNail().getSurfaceCreatedTimestamp() == this.mSurfaceCreatedTimestamp) {
                this.mCamera2Device.resumePreview();
            }
        }
    }

    public boolean isAlive() {
        return isCreated() && !isDeparted();
    }

    @CallSuper
    public void onBroadcastReceived(Context context, Intent intent) {
        if (this.mActivity != null && !this.mActivity.isFinishing() && intent != null) {
            String action = intent.getAction();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onReceive: action=");
            stringBuilder.append(action);
            Log.v(str, stringBuilder.toString());
            if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                Log.d(TAG, "SD card available");
                Storage.initStorage(context);
                this.mActivity.getScreenHint().updateHint();
            } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                Log.d(TAG, "SD card unavailable");
                FileCompat.updateSDPath();
                this.mActivity.getScreenHint().updateHint();
                this.mActivity.getThumbnailUpdater().getLastThumbnail();
            } else if ("android.intent.action.MEDIA_SCANNER_STARTED".equals(action)) {
                Log.d(TAG, "media scanner started");
            } else if ("android.intent.action.MEDIA_SCANNER_FINISHED".equals(action)) {
                this.mActivity.getThumbnailUpdater().getLastThumbnail();
                Log.d(TAG, "media scanner finisheded");
            }
        }
    }

    public void onHostStopAndNotifyActionStop() {
    }

    public void onNewUriArrived(Uri uri, String str) {
    }

    public boolean isDeviceAlive() {
        boolean z = this.mCamera2Device != null && isAlive();
        if (!z) {
            String str = "device: %s module: %s|%s";
            Object[] objArr = new Object[3];
            objArr[0] = this.mCamera2Device != null ? "valid" : "invalid";
            objArr[1] = isCreated() ? "created" : "destroyed";
            objArr[2] = isDeparted() ? "departed" : "alive";
            String format = String.format(str, objArr);
            if (DEBUG) {
                new RuntimeException(format).printStackTrace();
            } else {
                str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Util.getCallers(1));
                stringBuilder.append("|");
                stringBuilder.append(format);
                Log.e(str, stringBuilder.toString());
            }
        }
        return z;
    }

    public final void restartModule() {
        if (!this.mActivity.isActivityPaused()) {
            this.mActivity.onModeSelected(StartControl.create(this.mModuleIndex).setViewConfigType(3).setNeedReConfigureData(false).setNeedBlurAnimation(true).setNeedReConfigureCamera(true));
        }
    }

    public String getUnInterruptableReason() {
        return this.mUnInterruptableReason;
    }

    /* Access modifiers changed, original: protected */
    public void handlePendingScreenSlide() {
        if (this.mPendingScreenSlideKeyCode > 0 && this.mActivity != null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("process pending screen slide: ");
            stringBuilder.append(this.mPendingScreenSlideKeyCode);
            Log.d(str, stringBuilder.toString());
            this.mActivity.handleScreenSlideKeyEvent(this.mPendingScreenSlideKeyCode, null);
            this.mPendingScreenSlideKeyCode = 0;
        }
    }

    public void updateScreenSlide(boolean z) {
        if (z && this.mCamera2Device != null) {
            this.mCamera2Device.setAELock(false);
            updatePreferenceInWorkThread(10, 36);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean isTextureExpired() {
        return this.mActivity.getCameraScreenNail().getSurfaceCreatedTimestamp() != this.mSurfaceCreatedTimestamp;
    }

    public boolean isThermalThreshold() {
        return false;
    }

    public boolean isPanoramaDoing() {
        return false;
    }

    public void onSwitchUltraWide() {
        DataItemGlobal dataItemGlobal = (DataItemGlobal) DataRepository.provider().dataGlobal();
        int currentMode = dataItemGlobal.getCurrentMode();
        dataItemGlobal.setCameraId(0);
        this.mActivity.onModeSelected(StartControl.create(currentMode).setStartDelay(0).setResetType(5).setViewConfigType(2).setNeedBlurAnimation(true));
    }
}
