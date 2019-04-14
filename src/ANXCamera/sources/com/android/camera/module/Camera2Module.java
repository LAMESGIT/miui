package com.android.camera.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.SensorEvent;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureResult;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Range;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.View;
import com.android.camera.BasePreferenceActivity;
import com.android.camera.Camera;
import com.android.camera.CameraIntentManager;
import com.android.camera.CameraIntentManager.CameraExtras;
import com.android.camera.CameraPreferenceActivity;
import com.android.camera.CameraSettings;
import com.android.camera.CameraSize;
import com.android.camera.Exif;
import com.android.camera.LocalParallelService.LocalBinder;
import com.android.camera.LocalParallelService.ServiceStatusListener;
import com.android.camera.LocationManager;
import com.android.camera.PictureSizeManager;
import com.android.camera.SensorStateManager.SensorStateListener;
import com.android.camera.Thumbnail;
import com.android.camera.ToastUtils;
import com.android.camera.Util;
import com.android.camera.constant.BeautyConstant;
import com.android.camera.constant.CameraScene;
import com.android.camera.constant.EyeLightConstant;
import com.android.camera.constant.GlobalConstant;
import com.android.camera.constant.UpdateConstant;
import com.android.camera.constant.UpdateConstant.UpdateType;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.config.ComponentConfigFlash;
import com.android.camera.data.data.config.ComponentConfigHdr;
import com.android.camera.data.data.config.SupportedConfigFactory;
import com.android.camera.data.data.runing.DataItemRunning;
import com.android.camera.db.DbRepository;
import com.android.camera.db.element.SaveTask;
import com.android.camera.effect.EffectController;
import com.android.camera.effect.FilterInfo;
import com.android.camera.effect.draw_mode.DrawExtTexAttribute;
import com.android.camera.effect.renders.DualWatermarkParam;
import com.android.camera.effect.renders.SnapshotEffectRender;
import com.android.camera.fragment.beauty.BeautyParameters;
import com.android.camera.fragment.beauty.BeautyValues;
import com.android.camera.fragment.top.FragmentTopAlert;
import com.android.camera.log.Log;
import com.android.camera.module.loader.FunctionParseAiScene;
import com.android.camera.module.loader.FunctionParseAsdFace;
import com.android.camera.module.loader.FunctionParseAsdHdr;
import com.android.camera.module.loader.FunctionParseAsdScene;
import com.android.camera.module.loader.FunctionParseAsdUltraWide;
import com.android.camera.module.loader.FunctionParseBeautyBodySlimCount;
import com.android.camera.module.loader.PredicateFilterAiScene;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera.module.loader.camera2.FocusManager2;
import com.android.camera.module.loader.camera2.FocusManager2.Listener;
import com.android.camera.module.loader.camera2.FocusTask;
import com.android.camera.parallel.AlgoConnector;
import com.android.camera.preferences.CameraSettingPreferences;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.ActionProcessing;
import com.android.camera.protocol.ModeProtocol.BackStack;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.CameraModuleSpecial;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.FaceBeautyProtocol;
import com.android.camera.protocol.ModeProtocol.FilterProtocol;
import com.android.camera.protocol.ModeProtocol.FullScreenProtocol;
import com.android.camera.protocol.ModeProtocol.OnFaceBeautyChangedProtocol;
import com.android.camera.protocol.ModeProtocol.RecordState;
import com.android.camera.protocol.ModeProtocol.SnapShotIndicator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.protocol.ModeProtocol.TopConfigProtocol;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.statistic.ScenarioTrackUtil;
import com.android.camera.storage.Storage;
import com.android.camera.ui.ObjectView.ObjectViewListener;
import com.android.camera.ui.RotateTextToast;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.android.camera2.Camera2Proxy;
import com.android.camera2.Camera2Proxy.BeautyBodySlimCountCallback;
import com.android.camera2.Camera2Proxy.CameraMetaDataCallback;
import com.android.camera2.Camera2Proxy.CameraPreviewCallback;
import com.android.camera2.Camera2Proxy.FaceDetectionCallback;
import com.android.camera2.Camera2Proxy.FocusCallback;
import com.android.camera2.Camera2Proxy.HDRCheckerCallback;
import com.android.camera2.Camera2Proxy.PictureCallback;
import com.android.camera2.Camera2Proxy.PictureCallbackWrapper;
import com.android.camera2.Camera2Proxy.ScreenLightCallback;
import com.android.camera2.Camera2Proxy.UltraWideCheckCallback;
import com.android.camera2.CameraCapabilities;
import com.android.zxing.PreviewDecodeManager;
import com.google.lens.sdk.LensApi;
import com.google.lens.sdk.LensApi.LensAvailabilityCallback;
import com.mi.config.b;
import com.xiaomi.camera.base.CameraDeviceUtil;
import com.xiaomi.camera.base.PerformanceTracker;
import com.xiaomi.camera.core.ParallelTaskData;
import com.xiaomi.camera.core.ParallelTaskDataParameter;
import com.xiaomi.camera.core.PictureInfo;
import com.xiaomi.camera.liveshot.CircularMediaRecorder;
import com.xiaomi.engine.BufferFormat;
import com.xiaomi.engine.GraphDescriptorBean;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@TargetApi(21)
public class Camera2Module extends BaseModule implements Listener, CameraAction, CameraModuleSpecial, FaceBeautyProtocol, FilterProtocol, OnFaceBeautyChangedProtocol, TopConfigProtocol, ObjectViewListener, BeautyBodySlimCountCallback, CameraMetaDataCallback, CameraPreviewCallback, FaceDetectionCallback, FocusCallback, HDRCheckerCallback, PictureCallback, ScreenLightCallback, UltraWideCheckCallback {
    private static final int BURST_SHOOTING_DELAY = 0;
    private static final long CAPTURE_DURATION_THRESHOLD = 12000;
    private static final int REQUEST_CROP = 1000;
    private static final String TAG = Camera2Module.class.getSimpleName();
    private static boolean mIsBeautyFrontOn = false;
    private static final String sTempCropFilename = "crop-temp";
    private volatile boolean isDetectedInHDR;
    private volatile boolean isResetFromMutex = false;
    private boolean isSilhouette;
    private boolean m3ALocked;
    private int mAFEndLogTimes;
    private Disposable mAiSceneDisposable;
    private boolean mAiSceneEnabled;
    private FlowableEmitter<CaptureResult> mAiSceneFlowableEmitter;
    private String mAlgorithmName;
    private BeautyValues mBeautyValues;
    private Intent mBroadcastIntent;
    private Disposable mBurstDisposable;
    private ObservableEmitter mBurstEmitter;
    private long mBurstNextDelayTime = 0;
    private long mBurstStartTime;
    private long mCaptureStartTime;
    private String mCaptureWaterMarkStr;
    private CircularMediaRecorder mCircularMediaRecorder = null;
    private final Object mCircularMediaRecorderStateLock = new Object();
    private Disposable mCountdownDisposable;
    private String mCropValue;
    private int mCurrentAiScene;
    private int mCurrentAsdScene = -1;
    private int mCurrentDetectedScene;
    private SnapshotEffectRender mEffectProcessor;
    private boolean mEnableParallelSession;
    private boolean mEnabledPreviewThumbnail;
    private boolean mEnteringMoonMode;
    private boolean mFaceDetectionEnabled;
    private boolean mFaceDetectionStarted;
    private FocusManager2 mFocusManager;
    private FunctionParseAiScene mFunctionParseAiScene;
    private MainHandler mHandler;
    private boolean mHasAiSceneFilterEffect;
    private boolean mHdrCheckEnabled;
    private boolean mIsBeautyBodySlimOn;
    private boolean mIsCurrentTaskIsParallel;
    private boolean mIsGenderAgeOn;
    private boolean mIsGradienterOn;
    private boolean mIsImageCaptureIntent;
    private boolean mIsLensServiceBound = false;
    private boolean mIsMagicMirrorOn;
    private boolean mIsMicrophoneEnabled = true;
    private boolean mIsMoonMode;
    private boolean mIsPortraitLightingOn;
    private boolean mIsSaveCaptureImage;
    private int mJpegRotation;
    private boolean mKeepBitmapTexture;
    private long mLastCaptureTime;
    private long mLastChangeSceneTime = 0;
    private String mLastFlashMode;
    private LensApi mLensApi;
    private int mLensStatus = 1;
    private Location mLocation;
    private boolean mLongPressedAutoFocus;
    private Disposable mMetaDataDisposable;
    private FlowableEmitter<CaptureResult> mMetaDataFlowableEmitter;
    private boolean mMultiSnapStatus = false;
    private boolean mMultiSnapStopRequest = false;
    private boolean mNeedAutoFocus;
    private long mOnResumeTime;
    private int mOperatingMode;
    private boolean mPendingMultiCapture;
    private boolean mQuickCapture;
    private int mReceivedJpegCallbackNum = 0;
    private Uri mSaveUri;
    private String mSceneMode;
    private SensorStateListener mSensorStateListener = new SensorStateListener() {
        public void onDeviceBecomeStable() {
        }

        public boolean isWorking() {
            return Camera2Module.this.isAlive() && Camera2Module.this.getCameraState() != 0;
        }

        public void onDeviceKeepMoving(double d) {
            if (!Camera2Module.this.mPaused && Camera2Module.this.mFocusManager != null && !Camera2Module.this.mMultiSnapStatus && !Camera2Module.this.is3ALocked() && !Camera2Module.this.mMainProtocol.isEvAdjusted(true)) {
                Camera2Module.this.mFocusManager.onDeviceKeepMoving(d);
            }
        }

        public void onDeviceBeginMoving() {
            if (!Camera2Module.this.mPaused && CameraSettings.isEdgePhotoEnable()) {
                Camera2Module.this.mActivity.getEdgeShutterView().onDeviceMoving();
            }
        }

        public void onDeviceKeepStable() {
        }

        public void onDeviceOrientationChanged(float f, boolean z) {
            Camera2Module.this.mDeviceRotation = f;
            if (Camera2Module.this.getCameraState() != 3) {
                EffectController.getInstance().setDeviceRotation(Camera2Module.this.mActivity.getSensorStateManager().isDeviceLying(), Util.getShootRotation(Camera2Module.this.mActivity, Camera2Module.this.mDeviceRotation));
            }
            Camera2Module.this.mHandler.removeMessages(33);
            if (!Camera2Module.this.mPaused) {
                int roundOrientation = Util.roundOrientation(Math.round(f), Camera2Module.this.mOrientation);
                Camera2Module.this.mHandler.obtainMessage(33, roundOrientation, (Util.getDisplayRotation(Camera2Module.this.mActivity) + roundOrientation) % 360).sendToTarget();
            }
        }

        public void notifyDevicePostureChanged() {
        }

        public void onDeviceRotationChanged(float[] fArr) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
        }
    };
    private ServiceStatusListener mServiceStatusListener;
    private int mShootOrientation;
    private float mShootRotation;
    private boolean mShouldDoMFNR;
    private long mShutterCallbackTime;
    private long mShutterLag;
    private Disposable mSuperNightDisposable;
    private int mTotalJpegCallbackNum = 1;
    private boolean mUpdateImageTitle = false;
    private CameraSize mVideoSize;
    private boolean mVolumeLongPress = false;
    private boolean mWaitingSuperNightResult;

    private class MainHandler extends Handler {
        public MainHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (!Camera2Module.this.isCreated()) {
                removeCallbacksAndMessages(null);
            } else if (Camera2Module.this.getActivity() != null) {
                int i = message.what;
                if (i == 2) {
                    Camera2Module.this.getWindow().clearFlags(128);
                } else if (i == 4) {
                    Camera2Module.this.checkActivityOrientation();
                    if (SystemClock.uptimeMillis() - Camera2Module.this.mOnResumeTime < 5000) {
                        Camera2Module.this.mHandler.sendEmptyMessageDelayed(4, 100);
                    }
                } else if (i == 17) {
                    Camera2Module.this.mHandler.removeMessages(17);
                    Camera2Module.this.mHandler.removeMessages(2);
                    Camera2Module.this.getWindow().addFlags(128);
                    Camera2Module.this.mHandler.sendEmptyMessageDelayed(2, (long) Camera2Module.this.getScreenDelay());
                } else if (i == 31) {
                    Camera2Module.this.setOrientationParameter();
                } else if (i != 33) {
                    boolean z = false;
                    if (i != 35) {
                        switch (i) {
                            case 9:
                                Camera2Module.this.mMainProtocol.initializeFocusView(Camera2Module.this);
                                Camera2Module.this.mMainProtocol.setObjectViewListener(Camera2Module.this);
                                break;
                            case 10:
                                Camera2Module.this.mOpenCameraFail = true;
                                Camera2Module.this.onCameraException();
                                break;
                            case 11:
                                break;
                            default:
                                switch (i) {
                                    case 44:
                                        Camera2Module.this.restartModule();
                                        break;
                                    case 45:
                                        Camera2Module.this.setActivity(null);
                                        break;
                                    default:
                                        switch (i) {
                                            case 48:
                                                Camera2Module.this.setCameraState(1);
                                                break;
                                            case 49:
                                                if (Camera2Module.this.isAlive()) {
                                                    Camera2Module.this.stopMultiSnap();
                                                    Camera2Module.this.mBurstEmitter.onComplete();
                                                    break;
                                                }
                                                return;
                                            case 50:
                                                Log.w(Camera2Module.TAG, "Oops, capture timeout later release timeout!");
                                                Camera2Module.this.onPictureTakenFinished(false);
                                                break;
                                            case 51:
                                                if (!Camera2Module.this.mActivity.isActivityPaused()) {
                                                    Camera2Module.this.mOpenCameraFail = true;
                                                    Camera2Module.this.onCameraException();
                                                    break;
                                                }
                                                break;
                                            case 52:
                                                Camera2Module.this.onShutterButtonClick(Camera2Module.this.getTriggerMode());
                                                break;
                                            default:
                                                switch (i) {
                                                    case 56:
                                                        if (Camera2Module.this.mMainProtocol != null && Camera2Module.this.mMainProtocol.isFaceExists(1) && Camera2Module.this.mMainProtocol.isFocusViewVisible() && 4 == Camera2Module.this.mCamera2Device.getFocusMode()) {
                                                            Camera2Module.this.mMainProtocol.clearFocusView(7);
                                                            break;
                                                        }
                                                    case 57:
                                                        PreviewDecodeManager.getInstance().reset();
                                                        break;
                                                    default:
                                                        StringBuilder stringBuilder = new StringBuilder();
                                                        stringBuilder.append("no consumer for this message: ");
                                                        stringBuilder.append(message.what);
                                                        throw new RuntimeException(stringBuilder.toString());
                                                }
                                        }
                                        break;
                                }
                        }
                    }
                    Camera2Module camera2Module = Camera2Module.this;
                    boolean z2 = message.arg1 > 0;
                    if (message.arg2 > 0) {
                        z = true;
                    }
                    camera2Module.handleUpdateFaceView(z2, z);
                } else {
                    Camera2Module.this.setOrientation(message.arg1, message.arg2);
                }
            }
        }
    }

    private final class JpegQuickPictureCallback extends PictureCallbackWrapper {
        String mBurstShotTitle;
        boolean mDropped;
        Location mLocation;
        String mPressDownTitle;
        int mSavedJpegCallbackNum;

        public JpegQuickPictureCallback(Location location) {
            this.mLocation = location;
        }

        private String getBurstShotTitle() {
            String stringBuilder;
            if (Camera2Module.this.mUpdateImageTitle && this.mBurstShotTitle != null && this.mSavedJpegCallbackNum == 1) {
                this.mPressDownTitle = this.mBurstShotTitle;
                this.mBurstShotTitle = null;
            }
            if (this.mBurstShotTitle == null) {
                long currentTimeMillis = System.currentTimeMillis();
                this.mBurstShotTitle = Util.createJpegName(currentTimeMillis);
                if (this.mBurstShotTitle.length() != 19) {
                    this.mBurstShotTitle = Util.createJpegName(currentTimeMillis + 1000);
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mBurstShotTitle);
            if (Camera2Module.this.mMutexModePicker.isUbiFocus()) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(Storage.UBIFOCUS_SUFFIX);
                stringBuilder3.append(this.mSavedJpegCallbackNum - 1);
                stringBuilder = stringBuilder3.toString();
            } else {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("_BURST");
                stringBuilder4.append(this.mSavedJpegCallbackNum);
                stringBuilder = stringBuilder4.toString();
            }
            stringBuilder2.append(stringBuilder);
            return stringBuilder2.toString();
        }

        public void onPictureTaken(byte[] bArr) {
            if (!Camera2Module.this.mPaused && bArr != null && Camera2Module.this.mReceivedJpegCallbackNum < Camera2Module.this.mTotalJpegCallbackNum && Camera2Module.this.mMultiSnapStatus) {
                if (!(this.mSavedJpegCallbackNum != 1 || Camera2Module.this.mMultiSnapStopRequest || Camera2Module.this.mMutexModePicker.isUbiFocus())) {
                    Camera2Module.this.mActivity.getImageSaver().updateImage(getBurstShotTitle(), this.mPressDownTitle);
                }
                if (Storage.isLowStorageAtLastPoint()) {
                    if (!Camera2Module.this.mMutexModePicker.isUbiFocus() && Camera2Module.this.mMultiSnapStatus) {
                        Camera2Module.this.trackGeneralInfo(this.mSavedJpegCallbackNum, true);
                        Camera2Module.this.trackPictureTaken(this.mSavedJpegCallbackNum, true, this.mLocation != null, Camera2Module.this.getCurrentAiSceneName(), Camera2Module.this.mEnteringMoonMode, Camera2Module.this.mIsMoonMode);
                        Camera2Module.this.stopMultiSnap();
                    }
                    return;
                }
                Camera2Module.access$704(Camera2Module.this);
                if (Camera2Module.this.mActivity.getImageSaver().isSaveQueueFull()) {
                    String access$1400 = Camera2Module.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("CaptureBurst queue full and drop ");
                    stringBuilder.append(Camera2Module.this.mReceivedJpegCallbackNum);
                    Log.e(access$1400, stringBuilder.toString());
                    this.mDropped = true;
                    if (Camera2Module.this.mReceivedJpegCallbackNum >= Camera2Module.this.mTotalJpegCallbackNum) {
                        Camera2Module.this.mActivity.getThumbnailUpdater().getLastThumbnailUncached();
                    }
                } else {
                    int i;
                    int width;
                    int height;
                    this.mSavedJpegCallbackNum++;
                    if (!Camera2Module.this.mMutexModePicker.isUbiFocus()) {
                        Camera2Module.this.playCameraSound(4);
                    }
                    Camera2Module.this.mBurstEmitter.onNext(Integer.valueOf(this.mSavedJpegCallbackNum));
                    boolean z = Camera2Module.this.mMutexModePicker.isUbiFocus() && Camera2Module.this.mReceivedJpegCallbackNum <= Camera2Module.this.mTotalJpegCallbackNum;
                    if (z) {
                        i = 0;
                    } else {
                        i = Exif.getOrientation(bArr);
                    }
                    if ((Camera2Module.this.mJpegRotation + i) % 180 == 0) {
                        width = Camera2Module.this.mPictureSize.getWidth();
                        height = Camera2Module.this.mPictureSize.getHeight();
                    } else {
                        width = Camera2Module.this.mPictureSize.getHeight();
                        height = Camera2Module.this.mPictureSize.getWidth();
                    }
                    int i2 = width;
                    int i3 = height;
                    String burstShotTitle = getBurstShotTitle();
                    boolean z2 = Camera2Module.this.mMutexModePicker.isUbiFocus() && Camera2Module.this.mReceivedJpegCallbackNum == Camera2Module.this.mTotalJpegCallbackNum - 1;
                    if (!(Camera2Module.this.mMutexModePicker.isUbiFocus() && Camera2Module.this.mReceivedJpegCallbackNum == Camera2Module.this.mTotalJpegCallbackNum)) {
                        boolean z3 = Camera2Module.this.mReceivedJpegCallbackNum != 1 && (Camera2Module.this.mReceivedJpegCallbackNum == Camera2Module.this.mTotalJpegCallbackNum || Camera2Module.this.mMultiSnapStopRequest || this.mDropped);
                        boolean z4 = false;
                        Camera2Module.this.mActivity.getImageSaver().addImage(bArr, z3, burstShotTitle, null, System.currentTimeMillis(), null, this.mLocation, i2, i3, null, i, z, z2, true, false, false, null, Camera2Module.this.getPictureInfo(), -1);
                        this.mDropped = z4;
                    }
                }
                if (Camera2Module.this.mReceivedJpegCallbackNum >= Camera2Module.this.mTotalJpegCallbackNum || Camera2Module.this.mMultiSnapStopRequest || this.mDropped) {
                    Camera2Module.this.stopMultiSnap();
                }
            }
        }

        public void onPictureTakenFinished(boolean z) {
            Camera2Module.this.stopMultiSnap();
            Camera2Module.this.mBurstEmitter.onComplete();
        }
    }

    private final class JpegRepeatingCaptureCallback extends PictureCallbackWrapper {
        String mBurstShotTitle;
        private boolean mDropped;
        ParallelTaskDataParameter mParallelParameter;
        String mPressDownTitle;

        private JpegRepeatingCaptureCallback() {
            this.mParallelParameter = null;
        }

        /* synthetic */ JpegRepeatingCaptureCallback(Camera2Module camera2Module, AnonymousClass1 anonymousClass1) {
            this();
        }

        private String getBurstShotTitle() {
            String stringBuilder;
            if (Camera2Module.this.mUpdateImageTitle && this.mBurstShotTitle != null && Camera2Module.this.mReceivedJpegCallbackNum == 1) {
                this.mPressDownTitle = this.mBurstShotTitle;
                this.mBurstShotTitle = null;
            }
            if (this.mBurstShotTitle == null) {
                long currentTimeMillis = System.currentTimeMillis();
                this.mBurstShotTitle = Util.createJpegName(currentTimeMillis);
                if (this.mBurstShotTitle.length() != 19) {
                    this.mBurstShotTitle = Util.createJpegName(currentTimeMillis + 1000);
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mBurstShotTitle);
            if (Camera2Module.this.mMutexModePicker.isUbiFocus()) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(Storage.UBIFOCUS_SUFFIX);
                stringBuilder3.append(Camera2Module.this.mReceivedJpegCallbackNum - 1);
                stringBuilder = stringBuilder3.toString();
            } else {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("_BURST");
                stringBuilder4.append(Camera2Module.this.mReceivedJpegCallbackNum);
                stringBuilder = stringBuilder4.toString();
            }
            stringBuilder2.append(stringBuilder);
            return stringBuilder2.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x00aa  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00a5  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00f3  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00f1  */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x0141  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0135  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x0151  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x014c  */
        public com.xiaomi.camera.core.ParallelTaskData onCaptureStart(com.xiaomi.camera.core.ParallelTaskData r8, com.android.camera.CameraSize r9) {
            /*
            r7 = this;
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mIsCurrentTaskIsParallel;
            r1 = 0;
            if (r0 == 0) goto L_0x02db;
        L_0x0009:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mPaused;
            if (r0 != 0) goto L_0x02db;
        L_0x000f:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mReceivedJpegCallbackNum;
            r2 = com.android.camera.module.Camera2Module.this;
            r2 = r2.mTotalJpegCallbackNum;
            if (r0 >= r2) goto L_0x02db;
        L_0x001d:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mMultiSnapStatus;
            if (r0 != 0) goto L_0x0027;
        L_0x0025:
            goto L_0x02db;
        L_0x0027:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mReceivedJpegCallbackNum;
            r2 = 1;
            if (r0 != r2) goto L_0x0064;
        L_0x0030:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mMultiSnapStopRequest;
            if (r0 != 0) goto L_0x0064;
        L_0x0038:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.is3ALocked();
            if (r0 != 0) goto L_0x0049;
        L_0x0040:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mFocusManager;
            r0.onShutter();
        L_0x0049:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mMutexModePicker;
            r0 = r0.isUbiFocus();
            if (r0 != 0) goto L_0x0064;
        L_0x0053:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mActivity;
            r0 = r0.getImageSaver();
            r3 = r7.getBurstShotTitle();
            r4 = r7.mPressDownTitle;
            r0.updateImage(r3, r4);
        L_0x0064:
            r0 = r7.tryCheckNeedStop();
            if (r0 == 0) goto L_0x0074;
        L_0x006a:
            r8 = com.android.camera.module.Camera2Module.TAG;
            r9 = "onMultiSnapCaptureStart: need stop multi capture, return null";
            com.android.camera.log.Log.d(r8, r9);
            return r1;
        L_0x0074:
            r0 = r7.mParallelParameter;
            r3 = 0;
            if (r0 != 0) goto L_0x01c2;
            r0 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
            if (r0 == 0) goto L_0x009e;
        L_0x0080:
            r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
            r4 = 166; // 0xa6 float:2.33E-43 double:8.2E-322;
            r0 = r0.getAttachProtocol(r4);
            r0 = (com.android.camera.protocol.ModeProtocol.MainContentProtocol) r0;
            r0 = r0.getFaceWaterMarkInfos();
            if (r0 == 0) goto L_0x009e;
        L_0x0092:
            r4 = r0.isEmpty();
            if (r4 != 0) goto L_0x009e;
        L_0x0098:
            r4 = new java.util.ArrayList;
            r4.<init>(r0);
            goto L_0x009f;
        L_0x009e:
            r4 = r1;
        L_0x009f:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mOutPutSize;
            if (r0 != 0) goto L_0x00aa;
        L_0x00a5:
            r0 = r9.toSizeObject();
            goto L_0x00b2;
        L_0x00aa:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mOutPutSize;
            r0 = r0.toSizeObject();
        L_0x00b2:
            r5 = new com.xiaomi.camera.core.ParallelTaskDataParameter$Builder;
            r6 = com.android.camera.module.Camera2Module.this;
            r6 = r6.mPreviewSize;
            r6 = r6.toSizeObject();
            r9 = r9.toSizeObject();
            r5.<init>(r6, r9, r0);
            r9 = com.android.camera.CameraSettings.isDualCameraWaterMarkOpen();
            r9 = r5.setHasDualWaterMark(r9);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.isFrontMirror();
            r9 = r9.setMirror(r0);
            r0 = com.android.camera.CameraSettings.getPortraitLightingPattern();
            r9 = r9.setLightingPattern(r0);
            r0 = com.android.camera.effect.EffectController.getInstance();
            r0 = r0.getEffectForSaving(r3);
            r9 = r9.setFilterId(r0);
            r0 = -1;
            r5 = com.android.camera.module.Camera2Module.this;
            r5 = r5.mOrientation;
            if (r0 != r5) goto L_0x00f3;
        L_0x00f1:
            r0 = r3;
            goto L_0x00f7;
        L_0x00f3:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mOrientation;
        L_0x00f7:
            r9 = r9.setOrientation(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mJpegRotation;
            r9 = r9.setJpegRotation(r0);
            r0 = com.android.camera.CameraSettings.isGradienterOn();
            if (r0 == 0) goto L_0x0119;
        L_0x010b:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mShootRotation;
            r5 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1));
            if (r0 != 0) goto L_0x0119;
        L_0x0117:
            r0 = 0;
            goto L_0x011f;
        L_0x0119:
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mShootRotation;
        L_0x011f:
            r9 = r9.setShootRotation(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mShootOrientation;
            r9 = r9.setShootOrientation(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mLocation;
            if (r0 == 0) goto L_0x0141;
        L_0x0135:
            r0 = new android.location.Location;
            r5 = com.android.camera.module.Camera2Module.this;
            r5 = r5.mLocation;
            r0.<init>(r5);
            goto L_0x0142;
        L_0x0141:
            r0 = r1;
        L_0x0142:
            r9 = r9.setLocation(r0);
            r0 = com.android.camera.CameraSettings.isTimeWaterMarkOpen();
            if (r0 == 0) goto L_0x0151;
        L_0x014c:
            r0 = com.android.camera.Util.getTimeWatermark();
            goto L_0x0152;
        L_0x0151:
            r0 = r1;
        L_0x0152:
            r9 = r9.setTimeWaterMarkString(r0);
            r9 = r9.setFaceWaterMarkList(r4);
            r0 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
            r9 = r9.setAgeGenderAndMagicMirrorWater(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.isFrontCamera();
            r9 = r9.setFrontCamera(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.isBokehFrontCamera();
            r9 = r9.setBokehFrontCamera(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mAlgorithmName;
            r9 = r9.setAlgorithmName(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.getPictureInfo();
            r9 = r9.setPictureInfo(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.getSuffix();
            r9 = r9.setSuffix(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mIsGradienterOn;
            r9 = r9.setGradienterOn(r0);
            r0 = com.android.camera.module.Camera2Module.getTiltShiftMode();
            r9 = r9.setTiltShiftMode(r0);
            r9 = r9.setSaveGroupshotPrimitive(r3);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.getDualWaterMarkParam();
            r9 = r9.setDualWatermarkParam(r0);
            r0 = com.android.camera.module.BaseModule.getJpegQuality(r2);
            r9 = r9.setJpegQuality(r0);
            r9 = r9.build();
            r7.mParallelParameter = r9;
        L_0x01c2:
            r9 = r7.mParallelParameter;
            r8.fillParameter(r9);
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mActivity;
            r9 = r9.getImageSaver();
            r9 = r9.isSaveQueueFull();
            if (r9 != 0) goto L_0x0281;
        L_0x01d6:
            r9 = com.android.camera.module.Camera2Module.this;
            com.android.camera.module.Camera2Module.access$704(r9);
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mMutexModePicker;
            r9 = r9.isUbiFocus();
            if (r9 != 0) goto L_0x01eb;
        L_0x01e5:
            r9 = com.android.camera.module.Camera2Module.this;
            r0 = 4;
            r9.playCameraSound(r0);
        L_0x01eb:
            r9 = com.android.camera.module.Camera2Module.TAG;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r4 = "onCaptureStart: mReceivedJpegCallbackNum = ";
            r0.append(r4);
            r4 = com.android.camera.module.Camera2Module.this;
            r4 = r4.mReceivedJpegCallbackNum;
            r0.append(r4);
            r0 = r0.toString();
            com.android.camera.log.Log.d(r9, r0);
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mBurstEmitter;
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mReceivedJpegCallbackNum;
            r0 = java.lang.Integer.valueOf(r0);
            r9.onNext(r0);
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mMutexModePicker;
            r9 = r9.isUbiFocus();
            if (r9 != 0) goto L_0x02ba;
        L_0x0226:
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mReceivedJpegCallbackNum;
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mTotalJpegCallbackNum;
            if (r9 > r0) goto L_0x02ba;
        L_0x0234:
            r9 = r7.getBurstShotTitle();
            r9 = com.android.camera.storage.Storage.generateFilepath(r9);
            r0 = com.android.camera.module.Camera2Module.TAG;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r4 = "onCaptureStart: savePath = ";
            r1.append(r4);
            r1.append(r9);
            r1 = r1.toString();
            com.android.camera.log.Log.d(r0, r1);
            r8.setSavePath(r9);
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mReceivedJpegCallbackNum;
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mTotalJpegCallbackNum;
            if (r9 == r0) goto L_0x0274;
        L_0x0265:
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mMultiSnapStopRequest;
            if (r9 != 0) goto L_0x0274;
        L_0x026d:
            r9 = r7.mDropped;
            if (r9 == 0) goto L_0x0272;
        L_0x0271:
            goto L_0x0274;
        L_0x0272:
            r2 = r3;
            goto L_0x0275;
        L_0x0275:
            r8.setNeedThumbnail(r2);
            r9 = com.android.camera.module.Camera2Module.this;
            r9.beginParallelProcess(r8, r3);
            r7.mDropped = r3;
            goto L_0x02bb;
        L_0x0281:
            r8 = com.android.camera.module.Camera2Module.TAG;
            r9 = new java.lang.StringBuilder;
            r9.<init>();
            r0 = "onCaptureStart queue full and drop ";
            r9.append(r0);
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mReceivedJpegCallbackNum;
            r9.append(r0);
            r9 = r9.toString();
            com.android.camera.log.Log.e(r8, r9);
            r7.mDropped = r2;
            r8 = com.android.camera.module.Camera2Module.this;
            r8 = r8.mReceivedJpegCallbackNum;
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mTotalJpegCallbackNum;
            if (r8 < r9) goto L_0x02ba;
        L_0x02af:
            r8 = com.android.camera.module.Camera2Module.this;
            r8 = r8.mActivity;
            r8 = r8.getThumbnailUpdater();
            r8.getLastThumbnailUncached();
        L_0x02ba:
            r8 = r1;
        L_0x02bb:
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mReceivedJpegCallbackNum;
            r0 = com.android.camera.module.Camera2Module.this;
            r0 = r0.mTotalJpegCallbackNum;
            if (r9 >= r0) goto L_0x02d5;
        L_0x02c9:
            r9 = com.android.camera.module.Camera2Module.this;
            r9 = r9.mMultiSnapStopRequest;
            if (r9 != 0) goto L_0x02d5;
        L_0x02d1:
            r9 = r7.mDropped;
            if (r9 == 0) goto L_0x02da;
        L_0x02d5:
            r9 = com.android.camera.module.Camera2Module.this;
            r9.stopMultiSnap();
        L_0x02da:
            return r8;
        L_0x02db:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module$JpegRepeatingCaptureCallback.onCaptureStart(com.xiaomi.camera.core.ParallelTaskData, com.android.camera.CameraSize):com.xiaomi.camera.core.ParallelTaskData");
        }

        private boolean tryCheckNeedStop() {
            if (!Storage.isLowStorageAtLastPoint()) {
                return false;
            }
            if (!Camera2Module.this.mMutexModePicker.isUbiFocus() && Camera2Module.this.mMultiSnapStatus) {
                Camera2Module.this.trackGeneralInfo(Camera2Module.this.mReceivedJpegCallbackNum, true);
                Camera2Module.this.trackPictureTaken(Camera2Module.this.mReceivedJpegCallbackNum, true, Camera2Module.this.mLocation != null, Camera2Module.this.getCurrentAiSceneName(), Camera2Module.this.mEnteringMoonMode, Camera2Module.this.mIsMoonMode);
                Camera2Module.this.stopMultiSnap();
            }
            return true;
        }

        public void onPictureTakenFinished(boolean z) {
            Camera2Module.this.stopMultiSnap();
            Camera2Module.this.mBurstEmitter.onComplete();
        }
    }

    static /* synthetic */ int access$704(Camera2Module camera2Module) {
        int i = camera2Module.mReceivedJpegCallbackNum + 1;
        camera2Module.mReceivedJpegCallbackNum = i;
        return i;
    }

    public void registerProtocol() {
        super.registerProtocol();
        ModeCoordinatorImpl.getInstance().attachProtocol(161, this);
        ModeCoordinatorImpl.getInstance().attachProtocol(169, this);
        ModeCoordinatorImpl.getInstance().attachProtocol(165, this);
        ModeCoordinatorImpl.getInstance().attachProtocol(193, this);
        ModeCoordinatorImpl.getInstance().attachProtocol(195, this);
        if (b.hM()) {
            ModeCoordinatorImpl.getInstance().attachProtocol(185, this);
        }
        ModeCoordinatorImpl.getInstance().attachProtocol(199, this);
        getActivity().getImplFactory().initAdditional(getActivity(), 164, 174);
        if (getModuleIndex() == 173) {
            getActivity().getImplFactory().initAdditional(getActivity(), 212);
        }
    }

    public void unRegisterProtocol() {
        super.unRegisterProtocol();
        ModeCoordinatorImpl.getInstance().detachProtocol(161, this);
        ModeCoordinatorImpl.getInstance().detachProtocol(169, this);
        ModeCoordinatorImpl.getInstance().detachProtocol(165, this);
        ModeCoordinatorImpl.getInstance().detachProtocol(193, this);
        ModeCoordinatorImpl.getInstance().detachProtocol(195, this);
        if (b.hM()) {
            ModeCoordinatorImpl.getInstance().detachProtocol(185, this);
        }
        ModeCoordinatorImpl.getInstance().detachProtocol(199, this);
        getActivity().getImplFactory().detachAdditional();
    }

    public boolean scanQRCodeEnabled() {
        return (!CameraSettings.isScanQRCode(this.mActivity) || this.mModuleIndex != 163 || this.mIsImageCaptureIntent || !CameraSettings.isBackCamera() || this.mMultiSnapStatus || CameraSettings.isStereoModeOn() || CameraSettings.isPortraitModeBackOn() || (DataRepository.dataItemFeature().gb() && CameraSettings.isRearMenuUltraPixelPhotographyOn())) ? false : true;
    }

    public void startFocus() {
        if (isDeviceAlive() && isFrameAvailable()) {
            if (this.mFocusAreaSupported) {
                this.mCamera2Device.startFocus(FocusTask.create(1), this.mModuleIndex);
            } else {
                this.mCamera2Device.resumePreview();
            }
        }
    }

    public void cancelFocus(boolean z) {
        if (isAlive() && isFrameAvailable() && getCameraState() != 0) {
            if (this.mCamera2Device != null) {
                if (z) {
                    this.mCamera2Device.setFocusMode(4);
                }
                this.mCamera2Device.cancelFocus(this.mModuleIndex);
            }
            if (getCameraState() != 3) {
                setCameraState(1);
            }
        }
    }

    public boolean onWaitingFocusFinished() {
        if (isDoingAction() || !isAlive()) {
            return false;
        }
        startNormalCapture(getTriggerMode());
        return true;
    }

    public boolean multiCapture() {
        if (isDoingAction() || !this.mPendingMultiCapture) {
            return false;
        }
        this.mPendingMultiCapture = false;
        this.mActivity.getScreenHint().updateHint();
        if (Storage.isLowStorageAtLastPoint()) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Not enough space or storage not ready. remaining=");
            stringBuilder.append(Storage.getLeftSpace());
            Log.i(str, stringBuilder.toString());
            return false;
        } else if (this.mActivity.getImageSaver().isBusy()) {
            Log.d(TAG, "ImageSaver is busy, wait for a moment!");
            RotateTextToast.getInstance(this.mActivity).show(2131296693, 0);
            return false;
        } else {
            ((ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164)).closeMutexElement(SupportedConfigFactory.CLOSE_BY_BURST_SHOOT, 193, 194, 196, 239, 201, 206);
            prepareMultiCapture();
            Observable.create(new ObservableOnSubscribe<Integer>() {
                public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                    Camera2Module.this.mBurstEmitter = observableEmitter;
                }
            }).observeOn(AndroidSchedulers.mainThread()).map(new Function<Integer, Integer>() {
                public Integer apply(Integer num) throws Exception {
                    ((SnapShotIndicator) ModeCoordinatorImpl.getInstance().getAttachProtocol(184)).setSnapNumValue(num.intValue());
                    return num;
                }
            }).subscribe(new Observer<Integer>() {
                public void onSubscribe(Disposable disposable) {
                    Camera2Module.this.mBurstStartTime = System.currentTimeMillis();
                    Camera2Module.this.mBurstDisposable = disposable;
                }

                public void onNext(Integer num) {
                }

                public void onError(Throwable th) {
                }

                public void onComplete() {
                    ((SnapShotIndicator) ModeCoordinatorImpl.getInstance().getAttachProtocol(184)).setSnapNumVisible(false, true);
                    ((ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164)).restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_BURST_SHOOT);
                }
            });
            this.mBurstNextDelayTime = 0;
            if (isParallelSessionEnable()) {
                this.mCamera2Device.setShotType(9);
                this.mCamera2Device.captureBurstPictures(this.mTotalJpegCallbackNum, new JpegRepeatingCaptureCallback(this, null), this.mActivity.getImageSaver());
            } else {
                this.mCamera2Device.setShotType(3);
                this.mCamera2Device.captureBurstPictures(this.mTotalJpegCallbackNum, new JpegQuickPictureCallback(LocationManager.instance().getCurrentLocation()), this.mActivity.getImageSaver());
            }
            return true;
        }
    }

    private void stopMultiSnap() {
        Log.d(TAG, "stopMultiSnap: start");
        this.mHandler.removeMessages(49);
        if (this.mMultiSnapStatus) {
            int i;
            this.mLastCaptureTime = System.currentTimeMillis();
            this.mMultiSnapStatus = false;
            this.mCamera2Device.captureAbortBurst();
            if (this.mMutexModePicker.isUbiFocus()) {
                i = 1;
            } else {
                i = this.mReceivedJpegCallbackNum;
            }
            int isUbiFocus = this.mMutexModePicker.isUbiFocus() ^ 1;
            trackGeneralInfo(i, isUbiFocus);
            trackPictureTaken(i, isUbiFocus, this.mLocation != null, getCurrentAiSceneName(), this.mEnteringMoonMode, this.mIsMoonMode);
            animateCapture();
            this.mUpdateImageTitle = false;
            this.mHandler.sendEmptyMessageDelayed(48, 800);
        }
    }

    private void onFilterChanged() {
        updatePreferenceTrampoline(2);
        ((TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172)).updateConfigItem(196);
        this.mMainProtocol.updateEffectViewVisible();
    }

    public void onBroadcastReceived(Context context, Intent intent) {
        if (intent != null && isAlive()) {
            if (CameraIntentManager.ACTION_VOICE_CONTROL.equals(intent.getAction())) {
                Log.d(TAG, "on Receive voice control broadcast action intent");
                String voiceControlAction = CameraIntentManager.getInstance(intent).getVoiceControlAction();
                this.mBroadcastIntent = intent;
                Object obj = -1;
                if (voiceControlAction.hashCode() == 1270567718 && voiceControlAction.equals("CAPTURE")) {
                    obj = null;
                }
                if (obj == null) {
                    onShutterButtonClick(getTriggerMode());
                    this.mBroadcastIntent = null;
                }
                CameraIntentManager.removeInstance(intent);
            }
            super.onBroadcastReceived(context, intent);
        }
    }

    public CircularMediaRecorder getCircularMediaRecorder() {
        CircularMediaRecorder circularMediaRecorder;
        synchronized (this.mCircularMediaRecorderStateLock) {
            circularMediaRecorder = this.mCircularMediaRecorder;
        }
        return circularMediaRecorder;
    }

    public void startLiveShot() {
        synchronized (this.mCircularMediaRecorderStateLock) {
            if (this.mCircularMediaRecorder == null) {
                this.mCircularMediaRecorder = new CircularMediaRecorder(this.mVideoSize.width, this.mVideoSize.height, getActivity().getGLView().getEGLContext14(), this.mIsMicrophoneEnabled);
                this.mCircularMediaRecorder.setFpsReduction(15.0f);
            }
            this.mCircularMediaRecorder.setOrientationHint(this.mOrientationCompensation);
            this.mCircularMediaRecorder.start();
        }
    }

    public void stopLiveShot(boolean z) {
        synchronized (this.mCircularMediaRecorderStateLock) {
            if (this.mCircularMediaRecorder != null) {
                this.mCircularMediaRecorder.stop();
                if (z) {
                    this.mCircularMediaRecorder.release();
                    this.mCircularMediaRecorder = null;
                }
            }
        }
    }

    private void updateLiveShot() {
        if (!DataRepository.dataItemFeature().fE() || this.mModuleIndex != 163) {
            return;
        }
        if (CameraSettings.isLiveShotOn()) {
            startLiveShot();
        } else {
            stopLiveShot(false);
        }
    }

    private void setVideoSize(int i, int i2) {
        if (this.mCameraDisplayOrientation % 180 == 0) {
            this.mVideoSize = new CameraSize(i, i2);
        } else {
            this.mVideoSize = new CameraSize(i2, i);
        }
    }

    private void startLiveShotAnimation() {
        synchronized (this.mCircularMediaRecorderStateLock) {
            if (!(this.mCircularMediaRecorder == null || this.mHandler == null)) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                        if (topAlert != null) {
                            topAlert.startLiveShotAnimation();
                        }
                    }
                });
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0083 A:{RETURN} */
    private boolean isTriggerQcfaModeChange(boolean r6, boolean r7) {
        /*
        r5 = this;
        r0 = r5.mCameraCapabilities;
        r0 = r0.isSupportedQcfa();
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r0 = r5.mModuleIndex;
        r2 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        if (r0 != r2) goto L_0x0017;
    L_0x0010:
        r0 = r5.isBokehFrontCamera();
        if (r0 == 0) goto L_0x0017;
    L_0x0016:
        return r1;
    L_0x0017:
        r0 = com.android.camera.data.DataRepository.dataItemFeature();
        r0 = r0.fS();
        if (r0 <= 0) goto L_0x0022;
    L_0x0021:
        return r1;
    L_0x0022:
        r0 = com.android.camera.fragment.beauty.BeautyParameters.isFaceBeautyOn();
        r2 = 32773; // 0x8005 float:4.5925E-41 double:1.6192E-319;
        r3 = 32775; // 0x8007 float:4.5928E-41 double:1.6193E-319;
        r4 = 1;
        if (r6 == 0) goto L_0x0058;
    L_0x002f:
        r6 = mIsBeautyFrontOn;
        if (r6 == r0) goto L_0x0083;
    L_0x0033:
        if (r0 == 0) goto L_0x003a;
    L_0x0035:
        r6 = r5.mOperatingMode;
        if (r6 != r3) goto L_0x003a;
    L_0x0039:
        return r4;
    L_0x003a:
        r6 = com.android.camera.data.DataRepository.dataItemConfig();
        r6 = r6.getComponentHdr();
        r7 = r5.mModuleIndex;
        r6 = r6.getComponentValue(r7);
        if (r0 != 0) goto L_0x0057;
    L_0x004a:
        r7 = "off";
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x0057;
    L_0x0052:
        r6 = r5.mOperatingMode;
        if (r6 != r2) goto L_0x0057;
    L_0x0056:
        return r4;
    L_0x0057:
        goto L_0x0083;
    L_0x0058:
        if (r7 == 0) goto L_0x0083;
    L_0x005a:
        if (r0 != 0) goto L_0x0083;
    L_0x005c:
        r6 = com.android.camera.data.DataRepository.dataItemConfig();
        r6 = r6.getComponentHdr();
        r7 = r5.mModuleIndex;
        r6 = r6.getComponentValue(r7);
        r7 = r5.mOperatingMode;
        if (r7 != r2) goto L_0x0076;
    L_0x006e:
        r7 = "off";
        r7 = r6.equals(r7);
        if (r7 != 0) goto L_0x0082;
    L_0x0076:
        r7 = r5.mOperatingMode;
        if (r7 != r3) goto L_0x0083;
    L_0x007a:
        r7 = "off";
        r6 = r6.equals(r7);
        if (r6 != 0) goto L_0x0083;
    L_0x0082:
        return r4;
    L_0x0083:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.isTriggerQcfaModeChange(boolean, boolean):boolean");
    }

    private void onBeautyParameterChanged() {
        if (isTriggerQcfaModeChange(true, false)) {
            restartModule();
        } else if (b.hT()) {
            updatePreferenceInWorkThread(13, 34, 42);
        } else {
            updatePreferenceInWorkThread(13);
        }
        mIsBeautyFrontOn = BeautyParameters.isFaceBeautyOn();
        this.mIsBeautyBodySlimOn = CameraSettings.isBeautyBodySlimOn();
    }

    private void updateAiScene() {
        if (this.mFunctionParseAiScene != null) {
            this.mFunctionParseAiScene.resetScene();
        }
        this.mCurrentAiScene = 0;
        this.mAiSceneEnabled = CameraSettings.getAiSceneOpen();
        if (isFrontCamera() && this.mActivity.isScreenSlideOff()) {
            this.mAiSceneEnabled = false;
        }
        this.mCamera2Device.setASD(this.mAiSceneEnabled);
        if ((isFrontCamera() && ModuleManager.isCapture()) || !this.mAiSceneEnabled) {
            this.mCamera2Device.setCameraAI30(this.mAiSceneEnabled);
        }
        setAiSceneEffect(0);
        if (this.mAiSceneEnabled) {
            this.mCamera2Device.setASDPeriod(300);
            return;
        }
        hideSceneSelector();
        updateHDRPreference();
        updateFlashPreference();
        updateBeauty();
    }

    private void updateBokeh() {
        boolean z = isFrontCamera() && !isBokehFrontCamera() && ModuleManager.isPortraitModule() && b.hz();
        this.mCamera2Device.setBokeh(z);
    }

    private void updateFaceScore() {
        this.mIsMagicMirrorOn = DataRepository.dataItemRunning().isSwitchOn("pref_camera_magic_mirror_key");
        this.mCamera2Device.setFaceScore(this.mIsMagicMirrorOn);
        if (EffectController.getInstance().hasEffect()) {
            prepareEffectProcessor(FilterInfo.FILTER_ID_NONE);
        }
    }

    private void updateFaceAgeAnalyze() {
        this.mIsGenderAgeOn = DataRepository.dataItemRunning().isSwitchOn("pref_camera_show_gender_age_key");
        boolean z = true;
        if (!this.mIsGenderAgeOn) {
            z = this.mBeautyValues != null ? true ^ CameraSettings.getFaceBeautyCloseValue().equals(this.mBeautyValues.mBeautyLevel) : false;
        }
        this.mCamera2Device.setFaceAgeAnalyze(z);
        if (EffectController.getInstance().hasEffect()) {
            prepareEffectProcessor(FilterInfo.FILTER_ID_NONE);
        }
    }

    private void updateFrontMirror() {
        boolean isFrontMirror;
        if (isFrontCamera()) {
            isFrontMirror = isFrontMirror();
        } else {
            isFrontMirror = false;
        }
        this.mCamera2Device.setFrontMirror(isFrontMirror);
    }

    private void hideSceneSelector() {
        this.mHandler.post(-$$Lambda$Camera2Module$6MhBAG8t9gKD6JetAb6jENHe1rY.INSTANCE);
    }

    static /* synthetic */ void lambda$hideSceneSelector$0() {
        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
        if (topAlert != null) {
            topAlert.alertAiSceneSelector(8);
        }
    }

    public void onHDRSceneChanged(final boolean z) {
        if (this.isDetectedInHDR != z && !this.mPaused) {
            ComponentConfigHdr componentHdr = DataRepository.dataItemConfig().getComponentHdr();
            if (!componentHdr.isEmpty()) {
                if (!"auto".equals(componentHdr.getComponentValue(this.mModuleIndex))) {
                    return;
                }
                if (!z || (this.mZoomValue <= 1.0f && this.mCurrentAiScene != -1 && (this.mCamera2Device == null || !this.mCamera2Device.isNeedFlashOn()))) {
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            ((TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172)).alertHDR(z ? 0 : 8, false, false);
                        }
                    });
                    if (z) {
                        if (this.mMutexModePicker.isNormal()) {
                            this.mMutexModePicker.setMutexMode(1);
                        }
                    } else if (this.mMutexModePicker.isMorphoHdr()) {
                        this.mMutexModePicker.resetMutexMode();
                    }
                    this.isDetectedInHDR = z;
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onHDRSceneChanged: ");
                    stringBuilder.append(z);
                    Log.d(str, stringBuilder.toString());
                }
            }
        }
    }

    public boolean isHdrDetectStarted() {
        return this.mHdrCheckEnabled;
    }

    public void onFaceBeautySwitched(boolean z) {
        CameraSettings.setFaceBeautySwitch(z ? CameraSettings.KEY_FACE_BEAUTY_ADVANCED : "pref_camera_face_beauty_key");
        if (b.hM()) {
            onBeautyChanged();
        }
    }

    public void onBeautyChanged() {
        onBeautyParameterChanged();
    }

    /* JADX WARNING: Missing block: B:16:0x0034, code skipped:
            return;
     */
    public void startFaceDetection() {
        /*
        r2 = this;
        r0 = r2.mFaceDetectionEnabled;
        if (r0 == 0) goto L_0x0034;
    L_0x0004:
        r0 = r2.mFaceDetectionStarted;
        if (r0 != 0) goto L_0x0034;
    L_0x0008:
        r0 = r2.mActivity;
        if (r0 == 0) goto L_0x0034;
    L_0x000c:
        r0 = r2.mActivity;
        r0 = r0.isActivityPaused();
        if (r0 != 0) goto L_0x0034;
    L_0x0014:
        r0 = r2.isAlive();
        if (r0 != 0) goto L_0x001b;
    L_0x001a:
        goto L_0x0034;
    L_0x001b:
        r0 = r2.mMaxFaceCount;
        if (r0 <= 0) goto L_0x0033;
    L_0x001f:
        r0 = r2.mCamera2Device;
        if (r0 == 0) goto L_0x0033;
    L_0x0023:
        r0 = 1;
        r2.mFaceDetectionStarted = r0;
        r1 = r2.mMainProtocol;
        r1.setActiveIndicator(r0);
        r1 = r2.mCamera2Device;
        r1.startFaceDetection();
        r2.updateFaceView(r0, r0);
    L_0x0033:
        return;
    L_0x0034:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.startFaceDetection():void");
    }

    public void stopFaceDetection(boolean z) {
        if (this.mFaceDetectionEnabled && this.mFaceDetectionStarted) {
            if (!(b.isMTKPlatform() && (getCameraState() == 3 || getCameraState() == 0))) {
                this.mCamera2Device.stopFaceDetection();
            }
            this.mFaceDetectionStarted = false;
            this.mMainProtocol.setActiveIndicator(2);
            updateFaceView(false, z);
        }
    }

    /* JADX WARNING: Missing block: B:30:0x0079, code skipped:
            return;
     */
    public void onFaceDetected(com.android.camera2.CameraHardwareFace[] r4, com.android.camera.effect.FaceAnalyzeInfo r5) {
        /*
        r3 = this;
        r5 = r3.isAlive();
        if (r5 == 0) goto L_0x0079;
    L_0x0006:
        r5 = r3.mActivity;
        r5 = r5.getCameraScreenNail();
        r5 = r5.getFrameAvailableFlag();
        if (r5 != 0) goto L_0x0013;
    L_0x0012:
        goto L_0x0079;
    L_0x0013:
        if (r4 != 0) goto L_0x0016;
    L_0x0015:
        return;
    L_0x0016:
        r5 = com.mi.config.b.gE();
        if (r5 == 0) goto L_0x003a;
    L_0x001c:
        r5 = r4.length;
        if (r5 <= 0) goto L_0x003a;
    L_0x001f:
        r5 = 0;
        r5 = r4[r5];
        r5 = r5.faceType;
        r0 = 64206; // 0xface float:8.9972E-41 double:3.1722E-319;
        if (r5 != r0) goto L_0x003a;
    L_0x0029:
        r5 = r3.mObjectTrackingStarted;
        if (r5 == 0) goto L_0x0078;
    L_0x002d:
        r5 = r3.mMainProtocol;
        r0 = 3;
        r1 = r3.getActiveArraySize();
        r2 = r3.mZoomValue;
        r5.setFaces(r0, r4, r1, r2);
        goto L_0x0078;
    L_0x003a:
        r5 = r3.mMainProtocol;
        r0 = r3.getActiveArraySize();
        r1 = r3.mZoomValue;
        r2 = 1;
        r5 = r5.setFaces(r2, r4, r0, r1);
        if (r5 != 0) goto L_0x004a;
    L_0x0049:
        return;
    L_0x004a:
        r5 = r3.mIsPortraitLightingOn;
        if (r5 == 0) goto L_0x0053;
    L_0x004e:
        r5 = r3.mMainProtocol;
        r5.lightingDetectFace(r4);
    L_0x0053:
        r4 = r3.mMainProtocol;
        r4 = r4.isFaceExists(r2);
        r5 = 56;
        if (r4 == 0) goto L_0x0073;
    L_0x005d:
        r4 = r3.mMainProtocol;
        r4 = r4.isFocusViewVisible();
        if (r4 == 0) goto L_0x0073;
    L_0x0065:
        r4 = r3.mHandler;
        r4 = r4.hasMessages(r5);
        if (r4 != 0) goto L_0x0078;
    L_0x006d:
        r4 = r3.mHandler;
        r4.sendEmptyMessage(r5);
        goto L_0x0078;
    L_0x0073:
        r4 = r3.mHandler;
        r4.removeMessages(r5);
    L_0x0078:
        return;
    L_0x0079:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.onFaceDetected(com.android.camera2.CameraHardwareFace[], com.android.camera.effect.FaceAnalyzeInfo):void");
    }

    public boolean isFaceDetectStarted() {
        return this.mFaceDetectionStarted;
    }

    public boolean isUseFaceInfo() {
        return this.mIsGenderAgeOn || this.mIsMagicMirrorOn;
    }

    /* Access modifiers changed, original: protected */
    public void updateFaceView(boolean z, boolean z2) {
        if (this.mHandler.hasMessages(35)) {
            this.mHandler.removeMessages(35);
        }
        this.mHandler.obtainMessage(35, z, z2).sendToTarget();
    }

    private void handleUpdateFaceView(boolean z, boolean z2) {
        boolean isFrontCamera = isFrontCamera();
        if (!z) {
            this.mMainProtocol.updateFaceView(z, z2, isFrontCamera, false, -1);
        } else if ((this.mFaceDetectionStarted || isFaceBeautyMode()) && 1 != this.mCamera2Device.getFocusMode()) {
            this.mMainProtocol.updateFaceView(z, true, isFrontCamera, true, this.mCameraDisplayOrientation);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isFaceBeautyMode() {
        return false;
    }

    public void stopObjectTracking(boolean z) {
    }

    public void notifyFocusAreaUpdate() {
        updatePreferenceTrampoline(3);
    }

    public void playFocusSound(int i) {
        playCameraSound(i);
    }

    public boolean isNeedMute() {
        return CameraSettings.isLiveShotOn();
    }

    public void startObjectTracking() {
    }

    public void onObjectStable() {
    }

    private int getCountDownTimes(int i) {
        int timerDurationSeconds;
        if (this.mBroadcastIntent != null) {
            timerDurationSeconds = CameraIntentManager.getInstance(this.mBroadcastIntent).getTimerDurationSeconds();
        } else {
            timerDurationSeconds = this.mActivity.getCameraIntentManager().getTimerDurationSeconds();
        }
        if (timerDurationSeconds != -1) {
            if (this.mBroadcastIntent != null) {
                this.mBroadcastIntent.removeExtra(CameraExtras.TIMER_DURATION_SECONDS);
            } else {
                this.mActivity.getIntent().removeExtra(CameraExtras.TIMER_DURATION_SECONDS);
            }
            if (timerDurationSeconds == 0) {
                return 0;
            }
            if (timerDurationSeconds != 5) {
                return 3;
            }
            return 5;
        } else if (i != 100 || !CameraSettings.isHangGestureOpen()) {
            return CameraSettings.getCountDownTimes();
        } else {
            i = CameraSettings.getCountDownTimes();
            if (i == 0) {
                i = 3;
            }
            return i;
        }
    }

    /* JADX WARNING: Missing block: B:17:0x0037, code skipped:
            return false;
     */
    private boolean checkShutterCondition() {
        /*
        r2 = this;
        r0 = r2.isDoingAction();
        r1 = 0;
        if (r0 != 0) goto L_0x0037;
    L_0x0007:
        r0 = r2.isIgnoreTouchEvent();
        if (r0 == 0) goto L_0x000e;
    L_0x000d:
        goto L_0x0037;
    L_0x000e:
        r0 = com.android.camera.storage.Storage.isLowStorageAtLastPoint();
        if (r0 == 0) goto L_0x0015;
    L_0x0014:
        return r1;
    L_0x0015:
        r0 = r2.isFrontCamera();
        if (r0 == 0) goto L_0x0024;
    L_0x001b:
        r0 = r2.mActivity;
        r0 = r0.isScreenSlideOff();
        if (r0 == 0) goto L_0x0024;
    L_0x0023:
        return r1;
    L_0x0024:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.BackStack) r0;
        if (r0 == 0) goto L_0x0035;
    L_0x0032:
        r0.handleBackStackFromShutter();
    L_0x0035:
        r0 = 1;
        return r0;
    L_0x0037:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.checkShutterCondition():boolean");
    }

    public void onShutterButtonClick(int i) {
        int countDownTimes = getCountDownTimes(i);
        if (countDownTimes > 0) {
            startCount(countDownTimes, i);
        } else if (checkShutterCondition()) {
            setTriggerMode(i);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onShutterButtonClick ");
            stringBuilder.append(String.valueOf(getCameraState()));
            Log.d(str, stringBuilder.toString());
            this.mFocusManager.prepareCapture(this.mNeedAutoFocus, 2);
            this.mFocusManager.doSnap();
            if (this.mFocusManager.isFocusingSnapOnFinish()) {
                enableCameraControls(false);
            }
        }
    }

    private void prepareNormalCapture() {
        Log.d(TAG, "prepareNormalCapture");
        initFlashAutoStateForTrack(this.mCamera2Device.isNeedFlashOn());
        this.mEnabledPreviewThumbnail = false;
        this.mTotalJpegCallbackNum = 1;
        this.mReceivedJpegCallbackNum = 0;
        this.mCaptureStartTime = System.currentTimeMillis();
        ScenarioTrackUtil.trackCaptureTimeStart(isFrontCamera(), this.mModuleIndex);
        this.mLastCaptureTime = this.mCaptureStartTime;
        setCameraState(3);
        this.mJpegRotation = Util.getJpegRotation(this.mBogusCameraId, this.mOrientation);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("prepareNormalCapture: mOrientation = ");
        stringBuilder.append(this.mOrientation);
        stringBuilder.append(", mJpegRotation = ");
        stringBuilder.append(this.mJpegRotation);
        Log.d(str, stringBuilder.toString());
        this.mCamera2Device.setJpegRotation(this.mJpegRotation);
        Location currentLocation = LocationManager.instance().getCurrentLocation();
        this.mCamera2Device.setGpsLocation(currentLocation);
        this.mLocation = currentLocation;
        updateFrontMirror();
        updateBeauty();
        updateShotDetermine();
        if (this.mIsCurrentTaskIsParallel) {
            Camera2Proxy camera2Proxy = this.mCamera2Device;
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.createJpegName(System.currentTimeMillis()));
            stringBuilder.append(getSuffix());
            camera2Proxy.setShotSavePath(Storage.generateFilepath(stringBuilder.toString()));
        }
        setWaterMark();
        setPictureOrientation();
        updateJpegQuality();
        updateAlgorithmName();
        if (needShowThumbProgressImmediately()) {
            updateThumbProgress(false);
        }
        prepareSuperNight();
    }

    private void startNormalCapture(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startNormalCapture mode -> ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        prepareNormalCapture();
        if (!CameraSettings.isGroupShotOn() || isParallelSessionEnable()) {
            this.mHandler.sendEmptyMessageDelayed(50, calculateTimeout(this.mModuleIndex));
            this.mCamera2Device.takePicture(this, this.mActivity.getImageSaver());
            return;
        }
        this.mCamera2Device.captureGroupShotPictures(this, this.mActivity.getImageSaver(), this.mTotalJpegCallbackNum, this.mActivity);
    }

    private long calculateTimeout(int i) {
        long j = CAPTURE_DURATION_THRESHOLD;
        if (i == 167) {
            return (Long.parseLong(getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566))) / 1000000) + CAPTURE_DURATION_THRESHOLD;
        }
        if (i == 173) {
            j = 24000;
        }
        return j;
    }

    private void prepareMultiCapture() {
        this.mMultiSnapStatus = true;
        this.mMultiSnapStopRequest = false;
        Util.clearMemoryLimit();
        prepareNormalCapture();
        CameraCapabilities cameraCapabilities = this.mCameraCapabilities;
        this.mTotalJpegCallbackNum = CameraCapabilities.getBurstShootCount();
        this.mHandler.removeMessages(49);
        if (!is3ALocked()) {
            this.mFocusManager.onShutter();
        }
    }

    private void prepareSuperNight() {
        if (this.mModuleIndex == 173) {
            RecordState recordState = (RecordState) ModeCoordinatorImpl.getInstance().getAttachProtocol(212);
            recordState.onPrepare();
            recordState.onStart();
            this.mSuperNightDisposable = Observable.just(Integer.valueOf(300), Integer.valueOf(2000)).flatMap(new Function<Integer, ObservableSource<Integer>>() {
                public ObservableSource<Integer> apply(Integer num) throws Exception {
                    return Observable.just(num).delaySubscription((long) num.intValue(), TimeUnit.MILLISECONDS);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                public void accept(Integer num) throws Exception {
                    if (Camera2Module.this.isAlive()) {
                        int intValue = num.intValue();
                        if (intValue == 300) {
                            BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
                            if (bottomPopupTips != null) {
                                bottomPopupTips.showTips(11, 2131296806, 4);
                            }
                        } else if (intValue == 2000) {
                            Camera2Module.this.mWaitingSuperNightResult = true;
                            Camera2Module.this.animateCapture();
                            Camera2Module.this.playCameraSound(0);
                            RecordState recordState = (RecordState) ModeCoordinatorImpl.getInstance().getAttachProtocol(212);
                            if (recordState != null) {
                                recordState.onPostSavingStart();
                            }
                        }
                    }
                }
            });
        }
    }

    public void onBeautyBodySlimCountChange(final boolean z) {
        this.mHandler.post(new Runnable() {
            public void run() {
                TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                if (topAlert != null) {
                    if (z) {
                        topAlert.alertAiDetectTipHint(0, 2131296872, FunctionParseBeautyBodySlimCount.TIP_TIME);
                    } else {
                        topAlert.alertAiDetectTipHint(8, 2131296872, 0);
                    }
                }
            }
        });
    }

    public boolean isBeautyBodySlimCountDetectStarted() {
        return this.mIsBeautyBodySlimOn;
    }

    private DualWatermarkParam getDualWaterMarkParam() {
        return new DualWatermarkParam(CameraSettings.isDualCameraWaterMarkOpen(), CameraSettings.getDualCameraWaterMarkFilePathVendor(), CameraSettings.getResourceFloat(2131361965, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO), CameraSettings.getResourceFloat(2131361966, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO), CameraSettings.getResourceFloat(2131361967, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO));
    }

    private static String getTiltShiftMode() {
        if (CameraSettings.isTiltShiftOn()) {
            return DataRepository.dataItemRunning().getComponentRunningTiltValue().getComponentValue(160);
        }
        return null;
    }

    private void animateCapture() {
        if (!this.mIsImageCaptureIntent) {
            this.mActivity.getCameraScreenNail().animateCapture(getCameraRotation());
        }
    }

    public void onShutterButtonFocus(boolean z, int i) {
    }

    public void onLongPress(int i, int i2) {
        if (isInTapableRect(i, i2)) {
            onSingleTapUp(i, i2);
            if (CameraSettings.isAEAFLockSupport() && isBackCamera()) {
                lockAEAF();
            }
            if (isSupportFocusShoot() && !is3ALocked()) {
                setTriggerMode(80);
                onShutterButtonClick(getTriggerMode());
            }
            this.mMainProtocol.performHapticFeedback(0);
        }
    }

    public boolean onShutterButtonLongClick() {
        if (isDoingAction() || this.mIsImageCaptureIntent) {
            return false;
        }
        if (!CameraSettings.isBurstShootingEnable() || !ModuleManager.isCameraModule() || this.mIsImageCaptureIntent || CameraSettings.isGroupShotOn() || CameraSettings.isGradienterOn() || CameraSettings.isTiltShiftOn() || DataRepository.dataItemRunning().isSwitchOn("pref_camera_hand_night_key") || CameraSettings.isStereoModeOn() || CameraSettings.isPortraitModeBackOn() || !isBackCamera() || this.mMultiSnapStatus || this.mHandler.hasMessages(24) || this.mPendingMultiCapture || isUltraWideBackCamera() || CameraSettings.isRearMenuUltraPixelPhotographyOn()) {
            this.mLongPressedAutoFocus = true;
            this.mMainProtocol.setFocusViewType(false);
            this.mFocusManager.requestAutoFocus();
            this.mActivity.getScreenHint().updateHint();
            return false;
        }
        BackStack backStack = (BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171);
        if (backStack != null) {
            backStack.handleBackStackFromShutter();
        }
        if (b.gS()) {
            this.mUpdateImageTitle = true;
        }
        this.mPendingMultiCapture = true;
        this.mFocusManager.doMultiSnap(true);
        return true;
    }

    public void onShutterButtonLongClickCancel(boolean z) {
        Log.d(TAG, "onShutterButtonLongClickCancel: start");
        this.mPendingMultiCapture = false;
        if (this.mMultiSnapStatus) {
            this.mHandler.sendEmptyMessageDelayed(49, FragmentTopAlert.HINT_DELAY_TIME);
        }
        this.mMultiSnapStopRequest = true;
        if (!this.mLongPressedAutoFocus) {
            return;
        }
        if (z) {
            onShutterButtonClick(10);
            return;
        }
        this.mLongPressedAutoFocus = false;
        this.mFocusManager.cancelLongPressedAutoFocus();
    }

    public boolean isDoingAction() {
        return this.mPaused || this.isZooming || isKeptBitmapTexture() || this.mMultiSnapStatus || getCameraState() == 0 || getCameraState() == 3 || isParallelQueueFull() || isInCountDown();
    }

    public boolean isUnInterruptable() {
        this.mUnInterruptableReason = null;
        if (isKeptBitmapTexture()) {
            this.mUnInterruptableReason = "bitmap cover";
        } else if (getCameraState() == 3) {
            this.mUnInterruptableReason = "snapshot";
        }
        return this.mUnInterruptableReason != null;
    }

    public void onThumbnailClicked(View view) {
        if (!(isDoingAction() || this.mActivity.getThumbnailUpdater().getThumbnail() == null)) {
            this.mActivity.gotoGallery();
        }
    }

    private void startCount(final int i, int i2) {
        if (checkShutterCondition()) {
            setTriggerMode(i2);
            tryRemoveCountDownMessage();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startCount: ");
            stringBuilder.append(i);
            Log.d(str, stringBuilder.toString());
            Observable.interval(1, TimeUnit.SECONDS).take((long) i).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                public void onSubscribe(Disposable disposable) {
                    Camera2Module.this.mCountdownDisposable = disposable;
                    Camera2Module.this.playCameraSound(7);
                    TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                    if (topAlert != null) {
                        topAlert.hideAlert();
                    }
                    Camera2Module.this.mMainProtocol.clearFocusView(7);
                    Camera2Module.this.mMainProtocol.showDelayNumber(i);
                }

                public void onNext(Long l) {
                    int intValue = i - (l.intValue() + 1);
                    if (intValue > 0) {
                        Camera2Module.this.playCameraSound(5);
                        Camera2Module.this.mMainProtocol.showDelayNumber(intValue);
                    }
                }

                public void onError(Throwable th) {
                }

                public void onComplete() {
                    Camera2Module.this.tryRemoveCountDownMessage();
                    Camera2Module.this.onShutterButtonFocus(true, 3);
                    Camera2Module.this.startNormalCapture(Camera2Module.this.getTriggerMode());
                    Camera2Module.this.onShutterButtonFocus(false, 0);
                    TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                    if (topAlert != null) {
                        topAlert.reInitAlert(true);
                    }
                }
            });
        }
    }

    private boolean isInCountDown() {
        return (this.mCountdownDisposable == null || this.mCountdownDisposable.isDisposed()) ? false : true;
    }

    public void tryRemoveCountDownMessage() {
        if (this.mCountdownDisposable != null && !this.mCountdownDisposable.isDisposed()) {
            this.mCountdownDisposable.dispose();
            this.mCountdownDisposable = null;
            this.mHandler.post(new Runnable() {
                public void run() {
                    Log.d(Camera2Module.TAG, "run: hide delay number in main thread");
                    Camera2Module.this.mMainProtocol.hideDelayNumber();
                }
            });
        }
    }

    public boolean isKeptBitmapTexture() {
        return this.mKeepBitmapTexture;
    }

    public void onReviewDoneClicked() {
        doAttach();
    }

    public void onReviewCancelClicked() {
        this.mKeepBitmapTexture = false;
        if (isSelectingCapturedResult()) {
            this.mActivity.getCameraScreenNail().releaseBitmapIfNeeded();
            hidePostCaptureAlert();
            return;
        }
        this.mActivity.setResult(0, new Intent());
        this.mActivity.finish();
    }

    public boolean isSelectingCapturedResult() {
        boolean z = false;
        if (!this.mIsImageCaptureIntent) {
            return false;
        }
        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
        if (baseDelegate != null && baseDelegate.getActiveFragment(2131558427) == 4083) {
            z = true;
        }
        return z;
    }

    public void onPreviewLayoutChanged(Rect rect) {
        this.mActivity.onLayoutChange(rect);
        if (this.mFocusManager != null && this.mActivity.getCameraScreenNail() != null) {
            this.mFocusManager.setRenderSize(this.mActivity.getCameraScreenNail().getRenderWidth(), this.mActivity.getCameraScreenNail().getRenderHeight());
            this.mFocusManager.setPreviewSize(rect.width(), rect.height());
        }
    }

    public void onPreviewSizeChanged(int i, int i2) {
        if (this.mFocusManager != null) {
            this.mFocusManager.setPreviewSize(i, i2);
        }
    }

    public void onFilterChanged(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onFilterChanged: category = ");
        stringBuilder.append(i);
        stringBuilder.append(", newIndex = ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        updatePreferenceTrampoline(2);
        this.mMainProtocol.updateEffectViewVisible();
    }

    public boolean onBackPressed() {
        if (!isFrameAvailable()) {
            return false;
        }
        tryRemoveCountDownMessage();
        if (this.mMultiSnapStatus) {
            onShutterButtonLongClickCancel(false);
            return true;
        }
        if (getCameraState() == 3) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mModuleIndex == 173) {
                if (currentTimeMillis - this.mLastBackPressedTime > 3000) {
                    this.mLastBackPressedTime = currentTimeMillis;
                    ToastUtils.showToast(this.mActivity, 2131296547);
                    return true;
                }
            } else if (currentTimeMillis - this.mLastCaptureTime < CAPTURE_DURATION_THRESHOLD) {
                return true;
            }
        }
        return super.onBackPressed();
    }

    public void onResume() {
        super.onResume();
        this.mHandler.removeMessages(50);
        if (!isSelectingCapturedResult()) {
            this.mKeepBitmapTexture = false;
            this.mActivity.getCameraScreenNail().releaseBitmapIfNeeded();
        }
        keepScreenOnAwhile();
        this.mActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (Camera2Module.this.mLensApi != null && !Camera2Module.this.mIsLensServiceBound && !Camera2Module.this.mActivity.isActivityPaused()) {
                    Log.d(Camera2Module.TAG, "Bind Lens service: E");
                    Camera2Module.this.mLensApi.onResume();
                    Camera2Module.this.mIsLensServiceBound = true;
                    Log.d(Camera2Module.TAG, "Bind Lens service: X");
                }
            }
        });
    }

    public void setFrameAvailable(boolean z) {
        super.setFrameAvailable(z);
        if (z && CameraSettings.isCameraSoundOpen()) {
            this.mActivity.loadCameraSound(1);
            this.mActivity.loadCameraSound(0);
            this.mActivity.loadCameraSound(4);
            this.mActivity.loadCameraSound(5);
            this.mActivity.loadCameraSound(7);
        }
        if (z && this.mCamera2Device != null) {
            this.mCamera2Device.releaseFakeSurfaceIfNeed();
        }
    }

    public void onPause() {
        super.onPause();
        stopLiveShot(true);
        if (this.mFocusManager != null) {
            this.mFocusManager.removeMessages();
        }
        resetGradienter();
        tryRemoveCountDownMessage();
        this.mActivity.getSensorStateManager().reset();
        resetScreenOn();
        closeCamera();
        releaseEffectProcessor();
        this.mActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (Camera2Module.this.mLensApi != null && Camera2Module.this.mIsLensServiceBound) {
                    Log.d(Camera2Module.TAG, "Unbind Lens service: E");
                    Camera2Module.this.mIsLensServiceBound = false;
                    try {
                        Camera2Module.this.mLensApi.onPause();
                    } catch (Exception e) {
                        String access$1400 = Camera2Module.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unknown error when pause LensAPI->");
                        stringBuilder.append(e.getMessage());
                        Log.d(access$1400, stringBuilder.toString());
                    }
                    Log.d(Camera2Module.TAG, "Unbind Lens service: X");
                }
            }
        });
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessage(45);
        }
    }

    private boolean isLaunchedByMainIntent() {
        Object action;
        if (this.mActivity != null) {
            Intent intent = this.mActivity.getIntent();
            if (intent != null) {
                action = intent.getAction();
                return "android.intent.action.MAIN".equals(action);
            }
        }
        action = null;
        return "android.intent.action.MAIN".equals(action);
    }

    public boolean shouldReleaseLater() {
        return !this.mIsImageCaptureIntent && getCameraState() == 3 && !this.mMultiSnapStatus && ((this.mHandler == null || !(this.mHandler.hasMessages(48) || this.mHandler.hasMessages(49))) && !this.mFocusManager.isFocusing() && (this.mModuleIndex != 167 || getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566)).equals(getString(2131296566))));
    }

    public void onHostStopAndNotifyActionStop() {
        boolean z;
        super.onHostStopAndNotifyActionStop();
        if (this.mSuperNightDisposable == null || this.mSuperNightDisposable.isDisposed()) {
            z = false;
        } else {
            z = true;
            this.mSuperNightDisposable.dispose();
        }
        if (z || this.mWaitingSuperNightResult) {
            this.mWaitingSuperNightResult = false;
            RecordState recordState = (RecordState) ModeCoordinatorImpl.getInstance().getAttachProtocol(212);
            if (recordState != null) {
                recordState.onPostSavingFinish();
            }
        }
    }

    private void doLaterReleaseIfNeed() {
        if (this.mActivity != null) {
            if (this.mHandler != null) {
                this.mHandler.removeMessages(50);
            }
            if (this.mActivity.isActivityPaused()) {
                boolean z;
                if (this.mCamera2Device == null || !this.mCamera2Device.isSessionReady()) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    Log.d(TAG, "doLaterRelease");
                } else {
                    Log.d(TAG, "doLaterRelease but session is closed");
                }
                this.mActivity.releaseAll(true, z);
            } else if (!isDeparted()) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        Camera2Module.this.handlePendingScreenSlide();
                    }
                });
                if (isTextureExpired()) {
                    Log.d(TAG, "surfaceTexture expired, restartModule");
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            Camera2Module.this.restartModule();
                        }
                    });
                }
            }
        }
    }

    public void onCreate(int i, int i2) {
        super.onCreate(i, i2);
        parseIntent();
        this.mHandler = new MainHandler(this.mActivity.getMainLooper());
        this.mActivity.getSensorStateManager().setSensorStateListener(this.mSensorStateListener);
        onCameraOpened();
        this.mLensApi = new LensApi(this.mActivity);
        this.mLensApi.checkLensAvailability(new LensAvailabilityCallback() {
            public void onAvailabilityStatusFetched(int i) {
                Camera2Module.this.mLensStatus = i;
                CameraSettings.setGoogleLensAvailability(Camera2Module.this.mLensStatus == 0);
            }
        });
    }

    private void parseIntent() {
        CameraIntentManager cameraIntentManager = this.mActivity.getCameraIntentManager();
        this.mIsImageCaptureIntent = cameraIntentManager.isImageCaptureIntent();
        if (this.mIsImageCaptureIntent) {
            this.mSaveUri = cameraIntentManager.getExtraSavedUri();
            this.mCropValue = cameraIntentManager.getExtraCropValue();
            this.mIsSaveCaptureImage = cameraIntentManager.getExtraShouldSaveCapture().booleanValue();
            this.mQuickCapture = cameraIntentManager.isQuickCapture().booleanValue();
        }
    }

    public void closeCamera() {
        setCameraState(0);
        if (scanQRCodeEnabled() || b.fT()) {
            PreviewDecodeManager.getInstance().quit();
        }
        if (this.mCamera2Device != null) {
            if (this.mBurstDisposable != null) {
                this.mBurstDisposable.dispose();
            }
            if (this.mMetaDataFlowableEmitter != null) {
                this.mMetaDataFlowableEmitter.onComplete();
            }
            if (this.mMetaDataDisposable != null) {
                this.mMetaDataDisposable.dispose();
            }
            if (this.mAiSceneFlowableEmitter != null) {
                this.mAiSceneFlowableEmitter.onComplete();
            }
            if (this.mAiSceneDisposable != null) {
                this.mAiSceneDisposable.dispose();
            }
            if (this.mSuperNightDisposable != null) {
                this.mSuperNightDisposable.dispose();
            }
            this.mCamera2Device.setScreenLightCallback(null);
            this.mCamera2Device.setMetaDataCallback(null);
            this.mCamera2Device.setErrorCallback(null);
            this.mCamera2Device.releaseCameraPreviewCallback(null);
            this.mCamera2Device.setFocusCallback(null);
            this.mCamera2Device.setASD(false);
            this.mMetaDataFlowableEmitter = null;
            if (scanQRCodeEnabled() || b.fT()) {
                this.mCamera2Device.stopPreviewCallback(true);
            }
            if (this.mFaceDetectionStarted) {
                this.mFaceDetectionStarted = false;
            }
            this.m3ALocked = false;
            this.mCamera2Device = null;
        }
        if (this.mFocusManager != null) {
            this.mFocusManager.setAeAwbLock(false);
            this.mFocusManager.destroy();
        }
    }

    public void updatePreviewSurface() {
        if (this.mMainProtocol != null) {
            this.mMainProtocol.initEffectCropView();
        }
        checkDisplayOrientation();
        this.mSurfaceCreatedTimestamp = this.mActivity.getCameraScreenNail().getSurfaceCreatedTimestamp();
        if (this.mPreviewSize != null) {
            updateCameraScreenNailSize(this.mPreviewSize.width, this.mPreviewSize.height);
        }
        if (this.mCamera2Device != null) {
            this.mCamera2Device.updateDeferPreviewSession(new Surface(this.mActivity.getCameraScreenNail().getSurfaceTexture()));
        }
    }

    public void startPreview() {
        if (this.mCamera2Device != null) {
            Surface surface;
            this.mCamera2Device.setFocusCallback(this);
            this.mCamera2Device.setMetaDataCallback(this);
            this.mCamera2Device.setScreenLightCallback(this);
            this.mCamera2Device.setErrorCallback(this.mErrorCallback);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startPreview: ");
            stringBuilder.append(this.mPictureSize);
            Log.d(str, stringBuilder.toString());
            this.mCamera2Device.setPictureSize(this.mPictureSize);
            if (this.mEnableParallelSession) {
                this.mCamera2Device.setSubPictureSize(getSubPictureSize(35, isFrontCamera()));
            }
            if (this.mEnableParallelSession && isEnableQcfa()) {
                Log.d(TAG, "[QCFA] startPreview: set qcfa enable");
                this.mCamera2Device.setQcfaEnable(true);
            } else {
                Log.d(TAG, "[QCFA] startPreview: set qcfa disable");
                this.mCamera2Device.setQcfaEnable(false);
            }
            this.mSurfaceCreatedTimestamp = this.mActivity.getCameraScreenNail().getSurfaceCreatedTimestamp();
            boolean scanQRCodeEnabled = scanQRCodeEnabled();
            if (scanQRCodeEnabled) {
                PreviewDecodeManager.getInstance().init(this.mBogusCameraId, 0);
            }
            boolean z = b.fT() && isFrontCamera() && (this.mModuleIndex == 163 || this.mModuleIndex == 171);
            if (z) {
                PreviewDecodeManager.getInstance().init(this.mBogusCameraId, 1);
            }
            boolean z2 = scanQRCodeEnabled || z;
            SurfaceTexture surfaceTexture = this.mActivity.getCameraScreenNail().getSurfaceTexture();
            if (surfaceTexture != null) {
                surface = new Surface(surfaceTexture);
            } else {
                surface = null;
            }
            this.mCamera2Device.startPreviewSession(surface, z2, isNeedRawStream(), getOperatingMode(), this.mEnableParallelSession, this, PreviewDecodeManager.getInstance().getHandler());
        }
    }

    private void configParallelSession() {
        StringBuilder stringBuilder;
        GraphDescriptorBean graphDescriptorBean;
        if (isPortraitMode()) {
            int i;
            if (isDualFrontCamera() || isDualCamera()) {
                i = 2;
            } else {
                i = 1;
            }
            String str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("configParallelSession: inputStreamNum = ");
            stringBuilder.append(i);
            Log.d(str, stringBuilder.toString());
            graphDescriptorBean = new GraphDescriptorBean(32770, i, true, CameraDeviceUtil.getCameraCombinationMode(this.mActualCameraId));
        } else {
            graphDescriptorBean = new GraphDescriptorBean(0, 1, true, CameraDeviceUtil.getCameraCombinationMode(this.mActualCameraId));
        }
        String str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("[QCFA] configParallelSession: mPictureSize = ");
        stringBuilder.append(this.mPictureSize);
        Log.d(str2, stringBuilder.toString());
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("[QCFA] configParallelSession: mOutPutSize = ");
        stringBuilder.append(this.mOutPutSize);
        Log.d(str2, stringBuilder.toString());
        BufferFormat bufferFormat = new BufferFormat(this.mPictureSize.width, this.mPictureSize.height, 35, graphDescriptorBean);
        LocalBinder localBinder = AlgoConnector.getInstance().getLocalBinder(true);
        localBinder.configCaptureSession(bufferFormat);
        localBinder.setImageSaver(this.mActivity.getImageSaver());
        localBinder.setJpegOutputSize(this.mOutPutSize.width, this.mOutPutSize.height);
    }

    private boolean isNeedRawStream() {
        if (ModuleManager.isManualModule() && CameraSettings.isEnableDNG()) {
            for (CameraSize cameraSize : this.mCameraCapabilities.getSupportedOutputSize(37)) {
                if (cameraSize.width == this.mPictureSize.width && cameraSize.height == this.mPictureSize.height) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isEnableQcfa() {
        return this.mCameraCapabilities.isSupportedQcfa() && isFrontCamera() && (getModuleIndex() == 163 || getModuleIndex() == 165);
    }

    /* Access modifiers changed, original: protected */
    public int getOperatingMode() {
        if (!isParallelSessionEnable()) {
            int i = 32769;
            int i2;
            if (isFrontCamera()) {
                mIsBeautyFrontOn = BeautyParameters.isFaceBeautyOn();
                if (isPortraitMode() && DataRepository.dataItemFeature().fb()) {
                    if (!isBokehFrontCamera()) {
                        i2 = 33009;
                        i = i2;
                        i = 32775;
                    }
                } else if (!(isPortraitMode() && isBokehFrontCamera())) {
                    if (!this.mCameraCapabilities.isSupportedQcfa() || mIsBeautyFrontOn || !"off".equals(DataRepository.dataItemConfig().getComponentHdr().getComponentValue(this.mModuleIndex)) || DataRepository.dataItemFeature().fS() > 0) {
                        i2 = 32773;
                        i = i2;
                        if (this.mModuleIndex == 163 && CameraSettings.isFrontMenuUltraPixelPhotographyOn() && CameraSettings.isFrontSupportedUltraPixelPhotography(this.mCameraCapabilities)) {
                            i = 32775;
                        }
                    } else {
                        i = 32775;
                        i = 32775;
                    }
                }
                i = 32770;
                i = 32775;
            } else {
                i2 = getModuleIndex();
                if (i2 != 163) {
                    if (i2 == 167) {
                        i = (CameraSettings.isUltraPixelPhotographyOn() && CameraSettings.isSupportedUltraPixelPhotography(this.mCameraCapabilities)) ? CameraCapabilities.SESSION_OPERATION_MODE_PROFESSIONAL_ULTRA_PIXEL_PHOTOGRAPHY : 32771;
                    } else if (i2 == 171) {
                        i = 32770;
                    } else if (i2 == 173) {
                        i = CameraCapabilities.SESSION_OPERATION_MODE_SUPER_NIGHT;
                    }
                } else if (CameraSettings.isRearMenuUltraPixelPhotographyOn() && CameraSettings.isSupportedUltraPixelPhotography(this.mCameraCapabilities)) {
                    i = CameraCapabilities.SESSION_OPERATION_MODE_NORMAL_ULTRA_PIXEL_PHOTOGRAPHY;
                }
            }
            this.mOperatingMode = i;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getOperatingMode: ");
            stringBuilder.append(String.format("operatingMode = 0x%x", new Object[]{Integer.valueOf(i)}));
            Log.d(str, stringBuilder.toString());
            return i;
        } else if (isEnableQcfa()) {
            Log.d(TAG, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_QCFA");
            return CameraCapabilities.SESSION_OPERATION_MODE_ALGO_UP_QCFA;
        } else if (171 != getModuleIndex()) {
            Log.d(TAG, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_SAT");
            return CameraCapabilities.SESSION_OPERATION_MODE_ALGO_UP_SAT;
        } else if (!isFrontCamera() || isDualFrontCamera()) {
            Log.d(TAG, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_DUAL_BOKEH");
            return 36864;
        } else {
            Log.d(TAG, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_SINGLE_BOKEH");
            return CameraCapabilities.SESSION_OPERATION_MODE_ALGO_UP_SINGLE_BOKEH;
        }
    }

    public void onPreviewSessionSuccess(CameraCaptureSession cameraCaptureSession) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPreviewSessionSuccess: ");
        stringBuilder.append(Thread.currentThread().getName());
        Log.d(str, stringBuilder.toString());
        if (cameraCaptureSession != null && isAlive()) {
            if (!isKeptBitmapTexture()) {
                this.mHandler.sendEmptyMessage(9);
            }
            if (this.mEnableParallelSession) {
                configParallelSession();
            }
            previewWhenSessionSuccess();
            if (this.mActivity.getCameraIntentManager().checkCallerLegality()) {
                if (this.mActivity.getCameraIntentManager().isOpenOnly()) {
                    this.mActivity.getIntent().removeExtra(CameraExtras.TIMER_DURATION_SECONDS);
                } else {
                    this.mActivity.getIntent().removeExtra(CameraExtras.CAMERA_OPEN_ONLY);
                    this.mHandler.sendEmptyMessageDelayed(52, 1000);
                }
            }
        }
    }

    private void previewWhenSessionSuccess() {
        setCameraState(1);
        this.mFaceDetectionEnabled = false;
        updatePreferenceInWorkThread(UpdateConstant.CAMERA_TYPES_ON_PREVIEW_SUCCESS);
        if (ModuleManager.isManualModule()) {
            updatePreferenceInWorkThread(UpdateConstant.CAMERA_TYPES_MANUALLY);
        }
    }

    public void onPreviewSessionFailed(CameraCaptureSession cameraCaptureSession) {
        if (isTextureExpired() && retryOnceIfCameraError(this.mHandler)) {
            Log.d(TAG, "sessionFailed due to surfaceTexture expired, retry");
        } else {
            this.mHandler.sendEmptyMessage(51);
        }
    }

    public void onPreviewSessionClosed(CameraCaptureSession cameraCaptureSession) {
        Log.d(TAG, "onPreviewSessionClosed: ");
        setCameraState(0);
    }

    public void onFocusStateChanged(FocusTask focusTask) {
        if (isFrameAvailable()) {
            switch (focusTask.getFocusTrigger()) {
                case 1:
                    Log.v(TAG, String.format(Locale.ENGLISH, "FocusTime=%1$dms focused=%2$b", new Object[]{Long.valueOf(focusTask.getElapsedTime()), Boolean.valueOf(focusTask.isSuccess())}));
                    if (!(this.mFocusManager.isFocusingSnapOnFinish() || getCameraState() == 3)) {
                        setCameraState(1);
                    }
                    this.mFocusManager.onFocusResult(focusTask);
                    this.mActivity.getSensorStateManager().reset();
                    if (focusTask.isSuccess() && this.m3ALocked) {
                        this.mCamera2Device.lockExposure(true);
                        break;
                    }
                case 2:
                case 3:
                    String str = null;
                    if (focusTask.isFocusing()) {
                        str = "onAutoFocusMoving start";
                        this.mAFEndLogTimes = 0;
                    } else if (this.mAFEndLogTimes == 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onAutoFocusMoving end. result=");
                        stringBuilder.append(focusTask.isSuccess());
                        str = stringBuilder.toString();
                        this.mAFEndLogTimes++;
                    }
                    if (Util.sIsDumpLog && str != null) {
                        Log.v(TAG, str);
                    }
                    if ((getCameraState() != 3 || focusTask.getFocusTrigger() == 3) && !this.m3ALocked) {
                        this.mFocusManager.onFocusResult(focusTask);
                        break;
                    }
            }
        }
    }

    public void pausePreview() {
        Log.v(TAG, "pausePreview");
        this.mCamera2Device.pausePreview();
        setCameraState(0);
    }

    public void resumePreview() {
        Log.v(TAG, "resumePreview");
        previewWhenSessionSuccess();
    }

    private void resumePreviewInWorkThread() {
        updatePreferenceInWorkThread(new int[0]);
    }

    private boolean isParallelQueueFull() {
        boolean z = false;
        if (!this.mIsCurrentTaskIsParallel) {
            return false;
        }
        if (this.mActivity.getImageSaver().isSaveQueueFull()) {
            Log.d(TAG, "isParallelQueueFull: ImageSaver queue is full");
            return true;
        }
        LocalBinder localBinder = AlgoConnector.getInstance().getLocalBinder();
        if (localBinder != null) {
            z = localBinder.needWaitProcess();
        } else {
            Log.w(TAG, "isParallelQueueFull: NOTICE: CHECK WHY BINDER IS NULL!");
        }
        return z;
    }

    private void beginParallelProcess(ParallelTaskData parallelTaskData, boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("algo begin: ");
        stringBuilder.append(parallelTaskData.getSavePath());
        stringBuilder.append(" | ");
        stringBuilder.append(Thread.currentThread().getName());
        Log.i(str, stringBuilder.toString());
        if (z) {
            SaveTask saveTask = (SaveTask) DbRepository.dbItemSaveTask().generateItem(System.currentTimeMillis());
            saveTask.setPath(parallelTaskData.getSavePath());
            DbRepository.dbItemSaveTask().endItemAndInsert(saveTask, 0);
        }
        if (this.mServiceStatusListener == null) {
            this.mServiceStatusListener = new ServiceStatusListener() {
                public void onImagePostProcessStart(ParallelTaskData parallelTaskData) {
                    if (!Camera2Module.this.mMultiSnapStatus) {
                        Camera2Module.this.onPictureTakenFinished(true);
                    }
                    PerformanceTracker.trackPictureCapture(1);
                    String access$1400 = Camera2Module.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onImagePostProcessStart: ");
                    stringBuilder.append(parallelTaskData);
                    Log.d(access$1400, stringBuilder.toString());
                }
            };
            AlgoConnector.getInstance().setServiceStatusListener(this.mServiceStatusListener);
        }
    }

    private boolean enablePreviewAsThumbnail() {
        boolean z = false;
        if (!isAlive()) {
            return false;
        }
        if (this.mIsCurrentTaskIsParallel) {
            return true;
        }
        if (this.mIsPortraitLightingOn) {
            return false;
        }
        if (CameraSettings.isLiveShotOn()) {
            return true;
        }
        if (CameraSettings.isGradienterOn()) {
            return false;
        }
        if (CameraSettings.isPortraitModeBackOn()) {
            return true;
        }
        if (this.mModuleIndex == 167 || this.mModuleIndex == 173 || CameraSettings.showGenderAge() || CameraSettings.isMagicMirrorOn()) {
            return false;
        }
        if ((this.mCameraCapabilities != null && CameraSettings.isSupportedUltraPixelPhotography(this.mCameraCapabilities) && CameraSettings.isRearMenuUltraPixelPhotographyOn()) || CameraSettings.isFrontMenuUltraPixelPhotographyOn()) {
            return false;
        }
        if (this.mCamera2Device != null && this.mCamera2Device.isNeedPreviewThumbnail()) {
            z = true;
        }
        return z;
    }

    public void onPreviewPixelsRead(byte[] bArr, int i, int i2) {
        Object bitmapData;
        animateCapture();
        playCameraSound(0);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        boolean z = isFrontCamera() && !isFrontMirror();
        if (this.mIsCurrentTaskIsParallel) {
            if (z) {
                createBitmap = Util.flipBitmap(createBitmap);
                z = false;
            }
            bitmapData = Util.getBitmapData(createBitmap);
        } else {
            bitmapData = null;
        }
        int i3 = this.mShootOrientation - this.mDisplayRotation;
        if (isFrontCamera() && b.hI() && i3 % 180 == 0) {
            i3 = 0;
        }
        if (isAlive() && isDeviceAlive()) {
            String shotSavePath = this.mCamera2Device.getShotSavePath();
            Thumbnail createThumbnail = Thumbnail.createThumbnail(null, createBitmap, i3, z);
            createThumbnail.startWaitingForUri();
            this.mActivity.getThumbnailUpdater().setThumbnail(createThumbnail, true, true);
            this.mCamera2Device.onPreviewThumbnailReceived(createThumbnail);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onPreviewPixelsRead: ");
            stringBuilder.append(bitmapData);
            stringBuilder.append("--");
            stringBuilder.append(this.mIsCurrentTaskIsParallel);
            Log.d(str, stringBuilder.toString());
            if (bitmapData != null && this.mIsCurrentTaskIsParallel) {
                ParallelTaskData parallelTaskData = new ParallelTaskData(System.currentTimeMillis(), -1, shotSavePath);
                parallelTaskData.fillJpegData(bitmapData, 0);
                this.mActivity.getImageSaver().onParallelProcessFinish(parallelTaskData);
            }
        }
    }

    public void onSurfaceTextureUpdated(DrawExtTexAttribute drawExtTexAttribute) {
        if (this.mCircularMediaRecorder != null) {
            this.mCircularMediaRecorder.onSurfaceTextureUpdated(drawExtTexAttribute);
        }
    }

    public void onSurfaceTextureReleased() {
        Log.d(TAG, "onSurfaceTextureReleased: no further preview frame will be available");
    }

    /* Access modifiers changed, original: protected */
    public boolean isAutoRestartInNonZSL() {
        return false;
    }

    private void updateAlgorithmName() {
        String str;
        if (b.hZ()) {
            str = null;
        } else if (this.mCamera2Device.isBokehEnabled()) {
            if (DataRepository.dataItemFeature().fO() > 0) {
                str = Util.ALGORITHM_NAME_SOFT_PORTRAIT_ENCRYPTED;
            } else {
                str = Util.ALGORITHM_NAME_SOFT_PORTRAIT;
            }
        } else if (isPortraitMode()) {
            str = "portrait";
        } else {
            str = this.mMutexModePicker.getAlgorithmName();
        }
        this.mAlgorithmName = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016c  */
    public com.xiaomi.camera.core.ParallelTaskData onCaptureStart(com.xiaomi.camera.core.ParallelTaskData r6, com.android.camera.CameraSize r7) {
        /*
        r5 = this;
        r0 = com.android.camera.CameraSettings.isLiveShotOn();
        if (r0 == 0) goto L_0x0009;
    L_0x0006:
        r5.startLiveShotAnimation();
    L_0x0009:
        r5.onShutter();
        r0 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
        r1 = 0;
        if (r0 == 0) goto L_0x0032;
    L_0x0014:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r2 = 166; // 0xa6 float:2.33E-43 double:8.2E-322;
        r0 = r0.getAttachProtocol(r2);
        r0 = (com.android.camera.protocol.ModeProtocol.MainContentProtocol) r0;
        r0 = r0.getFaceWaterMarkInfos();
        if (r0 == 0) goto L_0x0032;
    L_0x0026:
        r2 = r0.isEmpty();
        if (r2 != 0) goto L_0x0032;
    L_0x002c:
        r2 = new java.util.ArrayList;
        r2.<init>(r0);
        goto L_0x0033;
    L_0x0032:
        r2 = r1;
    L_0x0033:
        r0 = TAG;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "onCaptureStart: ";
        r3.append(r4);
        r4 = r7.width;
        r3.append(r4);
        r4 = "x";
        r3.append(r4);
        r4 = r7.height;
        r3.append(r4);
        r3 = r3.toString();
        com.android.camera.log.Log.d(r0, r3);
        r0 = r5.mOutPutSize;
        if (r0 != 0) goto L_0x005e;
    L_0x0059:
        r0 = r7.toSizeObject();
        goto L_0x0064;
    L_0x005e:
        r0 = r5.mOutPutSize;
        r0 = r0.toSizeObject();
    L_0x0064:
        r3 = new com.xiaomi.camera.core.ParallelTaskDataParameter$Builder;
        r4 = r5.mPreviewSize;
        r4 = r4.toSizeObject();
        r7 = r7.toSizeObject();
        r3.<init>(r4, r7, r0);
        r7 = com.android.camera.CameraSettings.isDualCameraWaterMarkOpen();
        r7 = r3.setHasDualWaterMark(r7);
        r0 = r5.isFrontMirror();
        r7 = r7.setMirror(r0);
        r0 = com.android.camera.CameraSettings.getPortraitLightingPattern();
        r7 = r7.setLightingPattern(r0);
        r0 = com.android.camera.effect.EffectController.getInstance();
        r3 = 0;
        r0 = r0.getEffectForSaving(r3);
        r7 = r7.setFilterId(r0);
        r0 = -1;
        r4 = r5.mOrientation;
        if (r0 != r4) goto L_0x009f;
    L_0x009d:
        r0 = r3;
        goto L_0x00a1;
    L_0x009f:
        r0 = r5.mOrientation;
    L_0x00a1:
        r7 = r7.setOrientation(r0);
        r0 = r5.mJpegRotation;
        r7 = r7.setJpegRotation(r0);
        r0 = com.android.camera.CameraSettings.isGradienterOn();
        if (r0 == 0) goto L_0x00bb;
    L_0x00b1:
        r0 = r5.mShootRotation;
        r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x00bb;
    L_0x00b9:
        r0 = 0;
        goto L_0x00bd;
    L_0x00bb:
        r0 = r5.mShootRotation;
    L_0x00bd:
        r7 = r7.setShootRotation(r0);
        r0 = r5.mShootOrientation;
        r7 = r7.setShootOrientation(r0);
        r0 = r5.mLocation;
        r7 = r7.setLocation(r0);
        r0 = com.android.camera.CameraSettings.isTimeWaterMarkOpen();
        if (r0 == 0) goto L_0x00d8;
    L_0x00d3:
        r1 = com.android.camera.Util.getTimeWatermark();
    L_0x00d8:
        r7 = r7.setTimeWaterMarkString(r1);
        r7 = r7.setFaceWaterMarkList(r2);
        r0 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
        r7 = r7.setAgeGenderAndMagicMirrorWater(r0);
        r0 = r5.isFrontCamera();
        r7 = r7.setFrontCamera(r0);
        r0 = r5.isBokehFrontCamera();
        r7 = r7.setBokehFrontCamera(r0);
        r0 = r5.mAlgorithmName;
        r7 = r7.setAlgorithmName(r0);
        r0 = r5.getPictureInfo();
        r7 = r7.setPictureInfo(r0);
        r0 = r5.getSuffix();
        r7 = r7.setSuffix(r0);
        r0 = r5.mIsGradienterOn;
        r7 = r7.setGradienterOn(r0);
        r0 = getTiltShiftMode();
        r7 = r7.setTiltShiftMode(r0);
        r0 = com.android.camera.CameraSettings.isSaveGroushotPrimitiveOn();
        r7 = r7.setSaveGroupshotPrimitive(r0);
        r0 = r5.getDualWaterMarkParam();
        r7 = r7.setDualWatermarkParam(r0);
        r0 = com.android.camera.module.BaseModule.getJpegQuality(r3);
        r7 = r7.setJpegQuality(r0);
        r7 = r7.build();
        r6.fillParameter(r7);
        r7 = r5.mEnabledPreviewThumbnail;
        r0 = 1;
        r7 = r7 ^ r0;
        r6.setNeedThumbnail(r7);
        r7 = r5.mModuleIndex;
        r6.setCurrentModuleIndex(r7);
        r7 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "onCaptureStart: ";
        r1.append(r2);
        r2 = r5.mIsCurrentTaskIsParallel;
        r1.append(r2);
        r1 = r1.toString();
        com.android.camera.log.Log.d(r7, r1);
        r7 = r5.mIsCurrentTaskIsParallel;
        if (r7 == 0) goto L_0x0166;
    L_0x0163:
        r5.beginParallelProcess(r6, r0);
    L_0x0166:
        r7 = com.android.camera.CameraSettings.isHangGestureOpen();
        if (r7 == 0) goto L_0x0181;
    L_0x016c:
        r7 = TAG;
        r0 = "send msg: reset hand gesture";
        com.android.camera.log.Log.d(r7, r0);
        r7 = r5.mHandler;
        r0 = 57;
        r7.removeMessages(r0);
        r7 = r5.mHandler;
        r1 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r7.sendEmptyMessageDelayed(r0, r1);
    L_0x0181:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.onCaptureStart(com.xiaomi.camera.core.ParallelTaskData, com.android.camera.CameraSize):com.xiaomi.camera.core.ParallelTaskData");
    }

    private void onShutter() {
        if (getCameraState() != 0) {
            this.mShutterCallbackTime = System.currentTimeMillis();
            this.mShutterLag = this.mShutterCallbackTime - this.mCaptureStartTime;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mShutterLag = ");
            stringBuilder.append(this.mShutterLag);
            stringBuilder.append("ms");
            Log.v(str, stringBuilder.toString());
            if (this.mReceivedJpegCallbackNum == 0) {
                if (enablePreviewAsThumbnail()) {
                    this.mEnabledPreviewThumbnail = true;
                    this.mActivity.getCameraScreenNail().requestReadPixels();
                } else if (this.mModuleIndex != 173) {
                    updateThumbProgress(false);
                    animateCapture();
                    playCameraSound(0);
                }
            }
        }
    }

    public boolean onPictureTakenImageConsumed(Image image) {
        return false;
    }

    public void onPictureTaken(byte[] bArr) {
    }

    public void onPictureTakenFinished(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPictureTakenFinished: succeed = ");
        stringBuilder.append(z);
        Log.d(str, stringBuilder.toString());
        if (z) {
            trackGeneralInfo(1, false);
            trackPictureTaken(1, false, this.mLocation != null, getCurrentAiSceneName(), this.mEnteringMoonMode, this.mIsMoonMode);
            long currentTimeMillis = System.currentTimeMillis() - this.mCaptureStartTime;
            CameraStatUtil.trackTakePictureCost(currentTimeMillis, isFrontCamera(), this.mModuleIndex);
            if (this.mModuleIndex == 171 && DataRepository.dataItemFeature().isSupportBokehAdjust()) {
                CameraStatUtil.trackBokehTaken();
            }
            ScenarioTrackUtil.trackCaptureTimeEnd();
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("mCaptureStartTime(from onShutterButtonClick start to jpegCallback finished) = ");
            stringBuilder2.append(currentTimeMillis);
            stringBuilder2.append("ms");
            Log.d(str2, stringBuilder2.toString());
            if (this.mIsImageCaptureIntent) {
                if (this.mQuickCapture) {
                    doAttach();
                } else if (isAlive()) {
                    this.mKeepBitmapTexture = true;
                    showPostCaptureAlert();
                }
            } else if (this.mLongPressedAutoFocus) {
                this.mLongPressedAutoFocus = false;
                this.mFocusManager.cancelLongPressedAutoFocus();
            }
        }
        if (!(isKeptBitmapTexture() || this.mMultiSnapStatus)) {
            setCameraState(1);
            enableCameraControls(true);
        }
        this.mHandler.removeMessages(50);
        if (this.mModuleIndex == 173) {
            this.mWaitingSuperNightResult = false;
            if (!(this.mSuperNightDisposable == null || this.mSuperNightDisposable.isDisposed())) {
                this.mSuperNightDisposable.dispose();
            }
            this.mHandler.post(new Runnable() {
                public void run() {
                    RecordState recordState = (RecordState) ModeCoordinatorImpl.getInstance().getAttachProtocol(212);
                    if (recordState != null) {
                        recordState.onPostSavingFinish();
                    }
                }
            });
        }
        doLaterReleaseIfNeed();
    }

    private boolean isFrontMirror() {
        if (!isFrontCamera()) {
            return false;
        }
        if (CameraSettings.isLiveShotOn()) {
            return true;
        }
        return CameraSettings.isFrontMirror();
    }

    private void showPostCaptureAlert() {
        enableCameraControls(false);
        this.mFocusManager.removeMessages();
        stopFaceDetection(true);
        pausePreview();
        this.mMainProtocol.setEffectViewVisible(false);
        ((BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160)).delegateEvent(6);
        resetMetaDataManager();
    }

    private void hidePostCaptureAlert() {
        enableCameraControls(true);
        if (this.mCamera2Device.isSessionReady()) {
            resumePreview();
        } else {
            startPreview();
        }
        this.mMainProtocol.setEffectViewVisible(true);
        ((BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160)).delegateEvent(6);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:56:0x010b=Splitter:B:56:0x010b, B:51:0x00fc=Splitter:B:51:0x00fc} */
    private void doAttach() {
        /*
        r6 = this;
        r0 = r6.mPaused;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r6.mActivity;
        r0 = r0.getImageSaver();
        r0 = r0.getStoredJpegData();
        r1 = r6.mIsSaveCaptureImage;
        if (r1 == 0) goto L_0x001c;
    L_0x0013:
        r1 = r6.mActivity;
        r1 = r1.getImageSaver();
        r1.saveStoredData();
    L_0x001c:
        r1 = r6.mCropValue;
        r2 = 0;
        if (r1 != 0) goto L_0x008e;
    L_0x0021:
        r1 = r6.mSaveUri;
        r3 = -1;
        if (r1 == 0) goto L_0x0067;
        r1 = r6.mContentResolver;	 Catch:{ Exception -> 0x004b }
        r4 = r6.mSaveUri;	 Catch:{ Exception -> 0x004b }
        r1 = r1.openOutputStream(r4);	 Catch:{ Exception -> 0x004b }
        r1.write(r0);	 Catch:{ Exception -> 0x0046, all -> 0x0043 }
        r1.close();	 Catch:{ Exception -> 0x0046, all -> 0x0043 }
        r0 = r6.mActivity;	 Catch:{ Exception -> 0x0046, all -> 0x0043 }
        r0.setResult(r3);	 Catch:{ Exception -> 0x0046, all -> 0x0043 }
        r0 = r6.mActivity;
        r0.finish();
        com.android.camera.Util.closeSilently(r1);
        goto L_0x005b;
    L_0x0043:
        r0 = move-exception;
        r2 = r1;
        goto L_0x005e;
    L_0x0046:
        r0 = move-exception;
        r2 = r1;
        goto L_0x004c;
    L_0x0049:
        r0 = move-exception;
        goto L_0x005e;
    L_0x004b:
        r0 = move-exception;
    L_0x004c:
        r1 = TAG;	 Catch:{ all -> 0x0049 }
        r3 = "Exception when doAttach: ";
        com.android.camera.log.Log.e(r1, r3, r0);	 Catch:{ all -> 0x0049 }
        r0 = r6.mActivity;
        r0.finish();
        com.android.camera.Util.closeSilently(r2);
        goto L_0x00ef;
    L_0x005e:
        r1 = r6.mActivity;
        r1.finish();
        com.android.camera.Util.closeSilently(r2);
        throw r0;
    L_0x0067:
        r1 = 51200; // 0xc800 float:7.1746E-41 double:2.5296E-319;
        r1 = com.android.camera.Util.makeBitmap(r0, r1);
        r0 = com.android.camera.Exif.getOrientation(r0);
        r0 = com.android.camera.Util.rotate(r1, r0);
        r1 = r6.mActivity;
        r2 = new android.content.Intent;
        r4 = "inline-data";
        r2.<init>(r4);
        r4 = "data";
        r0 = r2.putExtra(r4, r0);
        r1.setResult(r3, r0);
        r0 = r6.mActivity;
        r0.finish();
        goto L_0x00ef;
        r1 = 0;
        r3 = r6.mActivity;	 Catch:{ FileNotFoundException -> 0x010a, IOException -> 0x00fb }
        r4 = "crop-temp";
        r3 = r3.getFileStreamPath(r4);	 Catch:{ FileNotFoundException -> 0x010a, IOException -> 0x00fb }
        r3.delete();	 Catch:{ FileNotFoundException -> 0x010a, IOException -> 0x00fb }
        r4 = r6.mActivity;	 Catch:{ FileNotFoundException -> 0x010a, IOException -> 0x00fb }
        r5 = "crop-temp";
        r4 = r4.openFileOutput(r5, r1);	 Catch:{ FileNotFoundException -> 0x010a, IOException -> 0x00fb }
        r4.write(r0);	 Catch:{ FileNotFoundException -> 0x00f6, IOException -> 0x00f3, all -> 0x00f0 }
        r4.close();	 Catch:{ FileNotFoundException -> 0x00f6, IOException -> 0x00f3, all -> 0x00f0 }
        r0 = android.net.Uri.fromFile(r3);	 Catch:{ FileNotFoundException -> 0x010a, IOException -> 0x00fb }
        com.android.camera.Util.closeSilently(r2);
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = "circle";
        r3 = r6.mCropValue;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00c9;
    L_0x00c2:
        r2 = "circleCrop";
        r3 = "true";
        r1.putString(r2, r3);
    L_0x00c9:
        r2 = r6.mSaveUri;
        if (r2 == 0) goto L_0x00d5;
    L_0x00cd:
        r2 = "output";
        r3 = r6.mSaveUri;
        r1.putParcelable(r2, r3);
        goto L_0x00db;
    L_0x00d5:
        r2 = "return-data";
        r3 = 1;
        r1.putBoolean(r2, r3);
    L_0x00db:
        r2 = new android.content.Intent;
        r3 = "com.android.camera.action.CROP";
        r2.<init>(r3);
        r2.setData(r0);
        r2.putExtras(r1);
        r0 = r6.mActivity;
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0.startActivityForResult(r2, r1);
    L_0x00ef:
        return;
    L_0x00f0:
        r0 = move-exception;
        r2 = r4;
        goto L_0x0119;
    L_0x00f3:
        r0 = move-exception;
        r2 = r4;
        goto L_0x00fc;
    L_0x00f6:
        r0 = move-exception;
        r2 = r4;
        goto L_0x010b;
    L_0x00f9:
        r0 = move-exception;
        goto L_0x0119;
    L_0x00fb:
        r0 = move-exception;
    L_0x00fc:
        r0 = r6.mActivity;	 Catch:{ all -> 0x00f9 }
        r0.setResult(r1);	 Catch:{ all -> 0x00f9 }
        r0 = r6.mActivity;	 Catch:{ all -> 0x00f9 }
        r0.finish();	 Catch:{ all -> 0x00f9 }
        com.android.camera.Util.closeSilently(r2);
        return;
    L_0x010a:
        r0 = move-exception;
    L_0x010b:
        r0 = r6.mActivity;	 Catch:{ all -> 0x00f9 }
        r0.setResult(r1);	 Catch:{ all -> 0x00f9 }
        r0 = r6.mActivity;	 Catch:{ all -> 0x00f9 }
        r0.finish();	 Catch:{ all -> 0x00f9 }
        com.android.camera.Util.closeSilently(r2);
        return;
    L_0x0119:
        com.android.camera.Util.closeSilently(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.doAttach():void");
    }

    /* Access modifiers changed, original: protected */
    public void resetMetaDataManager() {
        CameraSettings.isSupportedMetadata();
    }

    /* Access modifiers changed, original: protected */
    public void openSettingActivity() {
        Intent intent = new Intent();
        intent.setClass(this.mActivity, CameraPreferenceActivity.class);
        intent.putExtra(BasePreferenceActivity.FROM_WHERE, this.mModuleIndex);
        intent.putExtra(CameraPreferenceActivity.IS_IMAGE_CAPTURE_INTENT, this.mIsImageCaptureIntent);
        intent.putExtra(":miui:starting_window_label", getResources().getString(2131296325));
        if (this.mActivity.startFromKeyguard()) {
            intent.putExtra("StartActivityWhenLocked", true);
        }
        this.mActivity.startActivity(intent);
        this.mActivity.setJumpFlag(2);
        CameraStatUtil.trackGotoSettings(this.mModuleIndex);
    }

    /* Access modifiers changed, original: protected */
    public void onCameraOpened() {
        super.onCameraOpened();
        initializeFocusManager();
        updatePreferenceTrampoline(UpdateConstant.CAMERA_TYPES_INIT);
        if (this.mEnableParallelSession && isPortraitMode()) {
            Util.saveCameraCalibrationToFile(this.mCameraCapabilities.getCameraCalibrationData(), getCalibrationDataFileName(this.mActualCameraId));
        }
        if (!isKeptBitmapTexture()) {
            startPreview();
        }
        initMetaParser();
        if (b.gL()) {
            initAiSceneParser();
        }
        this.mOnResumeTime = SystemClock.uptimeMillis();
        this.mHandler.sendEmptyMessage(4);
        this.mHandler.sendEmptyMessage(31);
    }

    private String getCalibrationDataFileName(int i) {
        if (isFrontCamera()) {
            return "front_dual_camera_caldata.bin";
        }
        if (i == Camera2DataContainer.getInstance().getUltraWideBokehCameraId()) {
            return "back_dual_camera_caldata_wu.bin";
        }
        return "back_dual_camera_caldata.bin";
    }

    public void initializeCapabilities() {
        super.initializeCapabilities();
        this.mContinuousFocusSupported = Util.isSupported(4, this.mCameraCapabilities.getSupportedFocusModes());
        this.mMaxFaceCount = this.mCameraCapabilities.getMaxFaceCount();
    }

    private void initializeFocusManager() {
        Rect renderRect;
        this.mFocusManager = new FocusManager2(this.mCameraCapabilities, this, isFrontCamera(), this.mActivity.getMainLooper());
        if (this.mActivity.getCameraScreenNail() != null) {
            renderRect = this.mActivity.getCameraScreenNail().getRenderRect();
        } else {
            renderRect = null;
        }
        if (renderRect == null || renderRect.width() <= 0) {
            this.mFocusManager.setRenderSize(Util.sWindowWidth, Util.sWindowHeight);
            this.mFocusManager.setPreviewSize(Util.sWindowWidth, Util.sWindowHeight);
            return;
        }
        this.mFocusManager.setRenderSize(this.mActivity.getCameraScreenNail().getRenderWidth(), this.mActivity.getCameraScreenNail().getRenderHeight());
        this.mFocusManager.setPreviewSize(renderRect.width(), renderRect.height());
    }

    public void consumePreference(@UpdateType int... iArr) {
        for (int i : iArr) {
            switch (i) {
                case 1:
                    updatePictureAndPreviewSize();
                    break;
                case 2:
                    updateFilter();
                    break;
                case 3:
                    updateFocusArea();
                    break;
                case 4:
                case 50:
                    break;
                case 5:
                    updateFace();
                    break;
                case 6:
                    updateWhiteBalance();
                    break;
                case 7:
                    updateJpegQuality();
                    break;
                case 8:
                    updateJpegThumbnailSize();
                    break;
                case 9:
                    updateAntiBanding(CameraSettings.getAntiBanding());
                    break;
                case 10:
                    updateFlashPreference();
                    break;
                case 11:
                    updateHDRPreference();
                    break;
                case 12:
                    setEvValue();
                    break;
                case 13:
                    updateBeauty();
                    updateEyeLight();
                    break;
                case 14:
                    updateFocusMode();
                    break;
                case 15:
                    updateISO();
                    break;
                case 16:
                    updateExposureTime();
                    break;
                case 19:
                    updateFpsRange();
                    break;
                case 20:
                    updateOIS();
                    break;
                case 21:
                    updateMute();
                    break;
                case 22:
                    updateZsl();
                    break;
                case 23:
                    updateDecodePreview();
                    break;
                case 24:
                    setZoomRatio(getZoomValue());
                    break;
                case 25:
                    focusCenter();
                    break;
                case 26:
                    updateContrast();
                    break;
                case 27:
                    updateSaturation();
                    break;
                case 28:
                    updateSharpness();
                    break;
                case 29:
                    updateExposureMeteringMode();
                    break;
                case 30:
                    updateSuperResolution();
                    break;
                case 34:
                    updateMfnr(CameraSettings.isMfnrSatEnable());
                    break;
                case 35:
                    updateDeviceOrientation();
                    break;
                case 36:
                    updateAiScene();
                    break;
                case 37:
                    updateBokeh();
                    break;
                case 38:
                    updateFaceAgeAnalyze();
                    break;
                case 39:
                    updateFaceScore();
                    break;
                case 40:
                    updateFrontMirror();
                    break;
                case 42:
                    updateSwMfnr();
                    break;
                case 43:
                    updatePortraitLighting();
                    break;
                case 44:
                    updateShotDetermine();
                    break;
                case 45:
                    updateEyeLight();
                    break;
                case 46:
                    updateNormalWideLDC();
                    break;
                case 47:
                    updateUltraWideLDC();
                    break;
                case 48:
                    updateFNumber();
                    break;
                case 49:
                    updateLiveShot();
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("no consumer for this updateType: ");
                    stringBuilder.append(i);
                    throw new RuntimeException(stringBuilder.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    private void updateShotDetermine() {
        /*
        r7 = this;
        r0 = r7.mModuleIndex;
        r1 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        r2 = 0;
        if (r0 != r1) goto L_0x002b;
    L_0x0008:
        r0 = r7.isBackCamera();
        if (r0 == 0) goto L_0x0022;
    L_0x000e:
        r0 = com.mi.config.b.hS();
        if (r0 != 0) goto L_0x0020;
    L_0x0014:
        r0 = com.android.camera.data.DataRepository.dataItemFeature();
        r0 = r0.fa();
        if (r0 == 0) goto L_0x001f;
    L_0x001e:
        goto L_0x0020;
    L_0x001f:
        goto L_0x002b;
    L_0x0020:
        r0 = 1;
        goto L_0x002c;
    L_0x0022:
        r0 = com.android.camera.data.DataRepository.dataItemFeature();
        r0 = r0.fb();
        goto L_0x002c;
    L_0x002b:
        r0 = r2;
    L_0x002c:
        r3 = r7.mIsImageCaptureIntent;
        r4 = 8;
        if (r3 == 0) goto L_0x003a;
    L_0x0032:
        if (r0 == 0) goto L_0x0038;
    L_0x0034:
        r0 = -3;
    L_0x0035:
        r4 = r0;
        goto L_0x0090;
    L_0x0038:
        r0 = -2;
        goto L_0x0035;
    L_0x003a:
        r7.mEnableParallelSession = r2;
        r3 = r7.mModuleIndex;
        r5 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r6 = 5;
        if (r3 == r5) goto L_0x0079;
    L_0x0043:
        r5 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r3 == r5) goto L_0x0079;
    L_0x0047:
        if (r3 == r1) goto L_0x004a;
    L_0x0049:
        return;
    L_0x004a:
        r1 = r7.isParallelSessionEnable();
        r7.mEnableParallelSession = r1;
        r1 = r7.mEnableParallelSession;
        if (r1 == 0) goto L_0x0073;
    L_0x0054:
        r1 = r7.shouldDoMultiFrameCapture();
        if (r1 == 0) goto L_0x005b;
    L_0x005a:
        goto L_0x0090;
    L_0x005b:
        r1 = r7.isDualFrontCamera();
        if (r1 != 0) goto L_0x006e;
    L_0x0061:
        r1 = r7.isDualCamera();
        if (r1 == 0) goto L_0x0068;
    L_0x0067:
        goto L_0x006e;
    L_0x0068:
        if (r0 == 0) goto L_0x006c;
    L_0x006a:
        r0 = 7;
        goto L_0x0035;
    L_0x006c:
        r4 = r6;
        goto L_0x0090;
    L_0x006e:
        if (r0 == 0) goto L_0x0072;
    L_0x0070:
        r0 = 6;
        goto L_0x0035;
    L_0x0072:
        goto L_0x006c;
    L_0x0073:
        if (r0 == 0) goto L_0x0078;
    L_0x0075:
        r2 = 2;
    L_0x0076:
        r4 = r2;
        goto L_0x0090;
    L_0x0078:
        goto L_0x0076;
    L_0x0079:
        r0 = r7.isParallelSessionEnable();
        r7.mEnableParallelSession = r0;
        r0 = r7.mEnableParallelSession;
        if (r0 != 0) goto L_0x0088;
    L_0x0083:
        r4 = com.android.camera.CameraSettings.isLiveShotOn();
        goto L_0x0090;
    L_0x0088:
        r0 = r7.shouldDoMultiFrameCapture();
        if (r0 == 0) goto L_0x008f;
    L_0x008e:
        goto L_0x0090;
    L_0x008f:
        r4 = r6;
    L_0x0090:
        r0 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "enableParallel=";
        r1.append(r2);
        r2 = r7.mEnableParallelSession;
        r1.append(r2);
        r2 = " shotType=";
        r1.append(r2);
        r1.append(r4);
        r1 = r1.toString();
        com.android.camera.log.Log.d(r0, r1);
        r0 = r7.mCamera2Device;
        r0.setShotType(r4);
        r0 = com.xiaomi.camera.base.Constants.isParallelEnabled(r4);
        r7.mIsCurrentTaskIsParallel = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.updateShotDetermine():void");
    }

    private boolean isParallelSessionEnable() {
        return (!CameraSettings.isCameraParallelProcessEnable() || getModuleIndex() == 173 || getModuleIndex() == 167 || this.mIsImageCaptureIntent) ? false : true;
    }

    private boolean shouldDoMultiFrameCapture() {
        boolean z = this.mMutexModePicker.isHdr() || this.mShouldDoMFNR || this.mMutexModePicker.isSuperResolution() || CameraSettings.isGroupShotOn();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("shouldDoMultiFrameCapture: ");
        stringBuilder.append(z);
        Log.d(str, stringBuilder.toString());
        return z;
    }

    private void updatePictureAndPreviewSize() {
        CameraSize bestPictureSize = getBestPictureSize(this.mCameraCapabilities.getSupportedOutputSize(this.mEnableParallelSession ? 35 : 256));
        this.mPictureSize = bestPictureSize;
        this.mPreviewSize = Util.getOptimalPreviewSize(this.mModuleIndex, this.mBogusCameraId, this.mCameraCapabilities.getSupportedOutputSize(SurfaceTexture.class), (double) CameraSettings.getPreviewAspectRatio(bestPictureSize.width, bestPictureSize.height));
        this.mCamera2Device.setPreviewSize(this.mPreviewSize);
        this.mCamera2Device.setPreviewFormat(35);
        if (this.mEnableParallelSession) {
            List supportedOutputSize = this.mCameraCapabilities.getSupportedOutputSize(256);
            if (this.mModuleIndex == 165) {
                this.mOutPutSize = PictureSizeManager.getBestSquareSize(supportedOutputSize);
                if (this.mOutPutSize.getWidth() == 0) {
                    int i = bestPictureSize.width > bestPictureSize.height ? bestPictureSize.height : bestPictureSize.width;
                    throw new RuntimeException(String.format(Locale.ENGLISH, "size %dx%d is not supported!", new Object[]{Integer.valueOf(i), Integer.valueOf(i)}));
                }
            }
            this.mOutPutSize = PictureSizeManager.getBestPictureSize(supportedOutputSize);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("outputSize: ");
            stringBuilder.append(this.mOutPutSize);
            Log.v(str, stringBuilder.toString());
        }
        Log.d(TAG, String.format(Locale.ENGLISH, "updateSize: %dx%d %dx%d", new Object[]{Integer.valueOf(bestPictureSize.width), Integer.valueOf(bestPictureSize.height), Integer.valueOf(this.mPreviewSize.width), Integer.valueOf(this.mPreviewSize.height)}));
        updateCameraScreenNailSize(this.mPreviewSize.width, this.mPreviewSize.height);
        checkDisplayOrientation();
        setVideoSize(this.mPreviewSize.width, this.mPreviewSize.height);
    }

    private CameraSize getSubPictureSize(int i, boolean z) {
        int auxFrontCameraId;
        if (z) {
            auxFrontCameraId = Camera2DataContainer.getInstance().getAuxFrontCameraId();
        } else {
            auxFrontCameraId = Camera2DataContainer.getInstance().getAuxCameraId();
        }
        CameraSize maxPictureSize = getMaxPictureSize(auxFrontCameraId, i);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(z ? "frontSubSize: " : "backSubSize: ");
        stringBuilder.append(maxPictureSize);
        Log.d(str, stringBuilder.toString());
        return maxPictureSize;
    }

    private CameraSize getMaxPictureSize(int i, int i2) {
        CameraCapabilities capabilities = Camera2DataContainer.getInstance().getCapabilities(i);
        if (capabilities != null) {
            return PictureSizeManager.getBestPictureSize(capabilities.getSupportedOutputSize(i2));
        }
        return null;
    }

    private void updateFilter() {
        setEffectFilter(CameraSettings.getShaderEffect());
    }

    private void setEffectFilter(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setEffectFilter: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        EffectController.getInstance().setEffect(i);
        if (this.mCircularMediaRecorder != null) {
            this.mCircularMediaRecorder.setFilterId(i);
        }
        if (EffectController.getInstance().hasEffect()) {
            prepareEffectProcessor(i);
        }
    }

    private void setAiSceneEffect(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAiSceneEffect: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        if (DataRepository.dataItemFeature().fj() && CameraSettings.isBackCamera() && i == 25) {
            Log.d(TAG, "supportAi30: AI 3.0 back camera in HUMAN SCENE not apply filter!");
            return;
        }
        if (CameraSettings.isFrontCamera() || isPortraitMode()) {
            if (i != 0) {
                Log.d(TAG, "setAiSceneEffect: front camera or portrait mode nonsupport!");
                return;
            } else if (CameraSettings.getPortraitLightingPattern() != 0) {
                Log.d(TAG, "setAiSceneEffect: scene = 0 but portrait lighting is on...");
                return;
            }
        }
        ArrayList filterInfo = EffectController.getInstance().getFilterInfo(5);
        if (i < 0 || i > filterInfo.size()) {
            Log.e(TAG, "setAiSceneEffect: scene unknown!");
            return;
        }
        int shaderEffect = CameraSettings.getShaderEffect();
        if (FilterInfo.getCategory(shaderEffect) == 5 || shaderEffect == FilterInfo.FILTER_ID_NONE) {
            i = ((FilterInfo) filterInfo.get(i)).getId();
            EffectController.getInstance().setAiSceneEffect(i);
            this.mHasAiSceneFilterEffect = i != FilterInfo.FILTER_ID_NONE;
            if (EffectController.getInstance().hasEffect()) {
                prepareEffectProcessor(i);
            }
        }
    }

    private void setLightingEffect() {
        int shaderEffect = CameraSettings.getShaderEffect();
        if (FilterInfo.getCategory(shaderEffect) == 5 || shaderEffect == FilterInfo.FILTER_ID_NONE) {
            shaderEffect = CameraSettings.getPortraitLightingPattern();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setLightingEffect: ");
            stringBuilder.append(shaderEffect);
            Log.d(str, stringBuilder.toString());
            shaderEffect = ((FilterInfo) EffectController.getInstance().getFilterInfo(6).get(shaderEffect)).getId();
            EffectController.getInstance().setLightingEffect(shaderEffect);
            if (EffectController.getInstance().hasEffect()) {
                prepareEffectProcessor(shaderEffect);
            }
        }
    }

    private void prepareEffectProcessor(int i) {
        if (this.mEffectProcessor == null) {
            this.mEffectProcessor = new SnapshotEffectRender(this.mActivity, this.mIsImageCaptureIntent);
            this.mEffectProcessor.setImageSaver(this.mActivity.getImageSaver());
            this.mEffectProcessor.prepareEffectRender(i);
            this.mEffectProcessor.setQuality(BaseModule.getJpegQuality(this.mMultiSnapStatus));
        }
    }

    public void updateFlashPreference() {
        String componentValue = DataRepository.dataItemConfig().getComponentFlash().getComponentValue(this.mModuleIndex);
        String requestFlashMode = getRequestFlashMode();
        if (Util.parseInt(requestFlashMode, 0) != 0) {
            resetAiSceneInHdrOrFlashOn();
        }
        setFlashMode(requestFlashMode);
        if (!TextUtils.equals(componentValue, this.mLastFlashMode) && (Util.parseInt(componentValue, 0) == 103 || Util.parseInt(componentValue, 0) == 0)) {
            resetAsdSceneInHdrOrFlashChange();
        }
        this.mLastFlashMode = componentValue;
        stopObjectTracking(true);
    }

    public void updateHDRPreference() {
        ComponentConfigHdr componentHdr = DataRepository.dataItemConfig().getComponentHdr();
        if (!componentHdr.isEmpty()) {
            String componentValue = componentHdr.getComponentValue(this.mModuleIndex);
            if (this.mZoomValue > 1.0f && this.mMutexModePicker.isHdr() && "auto".equals(componentValue)) {
                onHDRSceneChanged(false);
            }
            updateHDR(componentValue);
            if ((!"off".equals(componentValue) || this.mAiSceneEnabled) && this.mZoomValue <= 1.0f) {
                resetAiSceneInHdrOrFlashOn();
                resetAsdSceneInHdrOrFlashChange();
                if ("auto".equals(componentValue)) {
                    this.mHdrCheckEnabled = true;
                } else {
                    this.mHdrCheckEnabled = false;
                }
                this.mCamera2Device.setHDRCheckerEnable(true);
            } else {
                this.mCamera2Device.setHDRCheckerEnable(false);
                this.mHdrCheckEnabled = false;
            }
        }
    }

    private void updateHDR(String str) {
        if ("auto".equals(str)) {
            this.isDetectedInHDR = false;
        }
        int mutexHdrMode = getMutexHdrMode(str);
        stopObjectTracking(true);
        if (mutexHdrMode != 0) {
            this.mMutexModePicker.setMutexMode(mutexHdrMode);
        } else if (this.mMutexModePicker.isHdr()) {
            resetMutexModeManually();
        }
        if (isFrontCamera() && isTriggerQcfaModeChange(false, true)) {
            this.mHandler.sendEmptyMessage(44);
        }
    }

    private void updatePortraitLighting() {
        String valueOf = String.valueOf(CameraSettings.getPortraitLightingPattern());
        this.mIsPortraitLightingOn = valueOf.equals("0") ^ 1;
        this.mCamera2Device.setPortraitLighting(Integer.parseInt(valueOf));
        setLightingEffect();
    }

    private void updateEyeLight() {
        if (isFrontCamera() && DataRepository.dataItemFeature().fw()) {
            Object eyeLightType = isFaceBeautyOn(this.mBeautyValues) ? CameraSettings.getEyeLightType() : EyeLightConstant.OFF;
            final TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
            if (topAlert != null) {
                if (EyeLightConstant.OFF.equals(eyeLightType)) {
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            topAlert.alertTopHint(8, 0);
                        }
                    });
                } else {
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            topAlert.alertTopHint(0, 2131296789);
                        }
                    });
                }
            }
            this.mCamera2Device.setEyeLight(Integer.parseInt(eyeLightType));
        }
    }

    private void updateFNumber() {
        this.mCamera2Device.setFNumber(CameraSettings.readFNumber());
    }

    private void updateFocusArea() {
        if (this.mActivity != null && !this.mActivity.isActivityPaused() && isAlive()) {
            Rect cropRegion = getCropRegion();
            Rect activeArraySize = getActiveArraySize();
            this.mActivity.getSensorStateManager().setFocusSensorEnabled(this.mFocusManager.getMeteringAreas(cropRegion, activeArraySize) != null);
            this.mCamera2Device.setAERegions(this.mFocusManager.getMeteringAreas(cropRegion, activeArraySize));
            if (this.mFocusAreaSupported) {
                this.mCamera2Device.setAFRegions(this.mFocusManager.getFocusAreas(cropRegion, activeArraySize));
            } else {
                this.mCamera2Device.resumePreview();
            }
        }
    }

    private void updateScene() {
        DataItemRunning dataItemRunning = DataRepository.dataItemRunning();
        if (this.mMutexModePicker.isSceneHdr()) {
            this.mSceneMode = CameraScene.HDR;
        } else if (dataItemRunning.isSwitchOn("pref_camera_scenemode_setting_key")) {
            this.mSceneMode = dataItemRunning.getComponentRunningSceneValue().getComponentValue(this.mModuleIndex);
        } else {
            this.mSceneMode = "0";
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sceneMode=");
        stringBuilder.append(this.mSceneMode);
        stringBuilder.append(" mutexMode=");
        stringBuilder.append(this.mMutexModePicker.getMutexMode());
        Log.d(str, stringBuilder.toString());
        if (!setSceneMode(this.mSceneMode)) {
            this.mSceneMode = "0";
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                Camera2Module.this.updateSceneModeUI();
            }
        });
        if (!"0".equals(this.mSceneMode) && CameraScene.HDR.equals(this.mSceneMode)) {
        }
    }

    private void updateSceneModeUI() {
        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
        if (DataRepository.dataItemRunning().isSwitchOn("pref_camera_scenemode_setting_key")) {
            DataRepository.dataItemConfig().getComponentHdr().setComponentValue(163, "off");
            String flashModeByScene = CameraSettings.getFlashModeByScene(this.mSceneMode);
            if (topAlert != null) {
                topAlert.disableMenuItem(194);
                if (flashModeByScene != null) {
                    topAlert.disableMenuItem(193);
                } else {
                    topAlert.enableMenuItem(193);
                }
                topAlert.hideExtraMenu();
            }
        } else if (topAlert != null) {
            topAlert.enableMenuItem(193, 194);
        }
        updatePreferenceInWorkThread(11, 10);
    }

    private void updateBeauty() {
        if (this.mModuleIndex == 163 || this.mModuleIndex == 165 || this.mModuleIndex == 171) {
            if (this.mBeautyValues == null) {
                this.mBeautyValues = new BeautyValues();
            }
            this.mBeautyValues.mBeautyLevel = CameraSettings.getFaceBeautyCloseValue();
            if (!DataRepository.dataItemConfig().getComponentConfigBeauty().isClosed(this.mModuleIndex)) {
                CameraSettings.initBeautyValues(this.mBeautyValues, b.hM());
                if (this.mCurrentAiScene == 25 && BeautyConstant.LEVEL_CLOSE.equals(this.mBeautyValues.mBeautyLevel)) {
                    this.mBeautyValues.mBeautyLevel = BeautyConstant.LEVEL_LOW;
                    Log.d(TAG, String.format(Locale.ENGLISH, "Human scene mode detected, auto set beauty level from %s to %s", new Object[]{BeautyConstant.LEVEL_CLOSE, this.mBeautyValues.mBeautyLevel}));
                }
            }
            if (DataRepository.dataItemFeature().isSupportBeautyBody()) {
                CameraSettings.initBeautyBody(this.mBeautyValues);
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("updateBeauty(): ");
            stringBuilder.append(this.mBeautyValues);
            Log.d(str, stringBuilder.toString());
            this.mCamera2Device.setBeautyValues(this.mBeautyValues);
            updateFaceAgeAnalyze();
        }
    }

    private void updateFocusMode() {
        String focusMode;
        if (this.mIsMoonMode) {
            focusMode = this.mFocusManager.setFocusMode("manual");
        } else {
            focusMode = this.mFocusManager.setFocusMode(CameraSettings.getFocusMode());
        }
        setFocusMode(focusMode);
        if (CameraSettings.isFocusModeSwitching() && isBackCamera()) {
            CameraSettings.setFocusModeSwitching(false);
            this.mFocusManager.resetFocusStateIfNeeded();
        }
        if (focusMode.equals("manual")) {
            float minimumFocusDistance = (this.mCameraCapabilities.getMinimumFocusDistance() * ((float) CameraSettings.getFocusPosition())) / 1000.0f;
            if (this.mIsMoonMode) {
                minimumFocusDistance = PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO;
            }
            this.mCamera2Device.setFocusDistance(minimumFocusDistance);
        }
    }

    /* Access modifiers changed, original: protected */
    public void focusCenter() {
    }

    private void updateFpsRange() {
        Range[] supportedFpsRange = this.mCameraCapabilities.getSupportedFpsRange();
        int i = 0;
        Range range = supportedFpsRange[0];
        int length = supportedFpsRange.length;
        while (i < length) {
            Range range2 = supportedFpsRange[i];
            if (((Integer) range.getUpper()).intValue() < ((Integer) range2.getUpper()).intValue() || (range.getUpper() == range2.getUpper() && ((Integer) range.getLower()).intValue() < ((Integer) range2.getLower()).intValue())) {
                range = range2;
            }
            i++;
        }
        if (b.qU && CameraSettings.isPortraitModeBackOn()) {
            this.mCamera2Device.setFpsRange(new Range(Integer.valueOf(30), Integer.valueOf(30)));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0071  */
    private void updateFace() {
        /*
        r6 = this;
        r0 = r6.mMultiSnapStatus;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0051;
    L_0x0007:
        r0 = r6.mMutexModePicker;
        r0 = r0.isUbiFocus();
        if (r0 == 0) goto L_0x0010;
    L_0x000f:
        goto L_0x0051;
    L_0x0010:
        r0 = com.android.camera.CameraSettings.isMagicMirrorOn();
        if (r0 != 0) goto L_0x004d;
    L_0x0016:
        r0 = com.android.camera.CameraSettings.isPortraitModeBackOn();
        if (r0 != 0) goto L_0x004d;
    L_0x001c:
        r0 = com.android.camera.CameraSettings.isGroupShotOn();
        if (r0 != 0) goto L_0x004d;
    L_0x0022:
        r0 = com.android.camera.CameraSettings.showGenderAge();
        if (r0 == 0) goto L_0x0029;
    L_0x0028:
        goto L_0x004d;
    L_0x0029:
        r0 = com.android.camera.data.DataRepository.dataItemGlobal();
        r3 = "pref_camera_facedetection_key";
        r4 = r6.getResources();
        r5 = 2131689478; // 0x7f0f0006 float:1.9007973E38 double:1.0531945387E-314;
        r4 = r4.getBoolean(r5);
        r0 = r0.getBoolean(r3, r4);
        r3 = com.android.camera.CameraSettings.isGradienterOn();
        if (r3 != 0) goto L_0x004a;
    L_0x0044:
        r3 = com.android.camera.CameraSettings.isTiltShiftOn();
        if (r3 == 0) goto L_0x0053;
        r3 = r1;
        goto L_0x0054;
        r0 = r2;
        r3 = r0;
        goto L_0x0054;
        r0 = r1;
    L_0x0053:
        r3 = r2;
    L_0x0054:
        r4 = r6.mMainProtocol;
        if (r4 == 0) goto L_0x0065;
    L_0x0058:
        r4 = r6.mMainProtocol;
        if (r0 == 0) goto L_0x0061;
    L_0x005c:
        if (r3 != 0) goto L_0x005f;
    L_0x005e:
        goto L_0x0061;
    L_0x005f:
        r3 = r1;
        goto L_0x0062;
    L_0x0061:
        r3 = r2;
    L_0x0062:
        r4.setSkipDrawFace(r3);
    L_0x0065:
        if (r0 == 0) goto L_0x0071;
    L_0x0067:
        r0 = r6.mFaceDetectionEnabled;
        if (r0 != 0) goto L_0x007a;
    L_0x006b:
        r6.mFaceDetectionEnabled = r2;
        r6.startFaceDetection();
        goto L_0x007a;
    L_0x0071:
        r0 = r6.mFaceDetectionEnabled;
        if (r0 == 0) goto L_0x007a;
    L_0x0075:
        r6.stopFaceDetection(r2);
        r6.mFaceDetectionEnabled = r1;
    L_0x007a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.updateFace():void");
    }

    private void updateJpegQuality() {
        this.mCamera2Device.setJpegQuality(BaseModule.getJpegQuality(this.mMultiSnapStatus));
    }

    private void updateOIS() {
        boolean z = isDualCamera() && this.mZoomValue > 1.0f;
        boolean isPortraitModeBackOn = CameraSettings.isPortraitModeBackOn();
        boolean z2 = this.mModuleIndex == 167 && Long.parseLong(getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566))) > 1000000000;
        if (z || isPortraitModeBackOn || z2) {
            this.mCamera2Device.setEnableOIS(false);
        } else {
            this.mCamera2Device.setEnableOIS(true);
        }
    }

    private void updateMute() {
    }

    private void updateZsl() {
        this.mCamera2Device.setEnableZsl(this.mModuleIndex != 167);
    }

    private void updateMfnr(boolean z) {
        if (this.mModuleIndex == 167 || ((b.qT && !isDualCamera()) || !this.mMutexModePicker.isNormal() || CameraSettings.isGroupShotOn() || !z || ((isFrontCamera() && !(b.hT() && this.mOperatingMode == 32773)) || !(DataRepository.dataItemFeature().eW() || this.mZoomValue == 1.0f || isUltraWideBackCamera())))) {
            z = false;
        } else {
            z = true;
        }
        if (isUseSwMfnr()) {
            z = false;
        }
        if (this.mCamera2Device != null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setMfnr to ");
            stringBuilder.append(z);
            Log.d(str, stringBuilder.toString());
            this.mCamera2Device.setMfnr(z);
        }
    }

    private boolean isUseSwMfnr() {
        boolean isMfnrSatEnable = CameraSettings.isMfnrSatEnable();
        boolean eY = DataRepository.dataItemFeature().eY();
        boolean z = false;
        if (CameraSettings.isGroupShotOn()) {
            Log.d(TAG, "GroupShot is on");
            return false;
        } else if (isUltraWideBackCamera()) {
            Log.d(TAG, "SwMfnr force off for ultra wide camera");
            return false;
        } else if (!isMfnrSatEnable) {
            Log.d(TAG, "Mfnr not enabled");
            return false;
        } else if (!eY) {
            Log.d(TAG, "SwMfnr is not supported");
            return false;
        } else if (!this.mMutexModePicker.isNormal()) {
            Log.d(TAG, "Mutex mode is not normal");
            return false;
        } else if (!DataRepository.dataItemFeature().eZ() || this.mModuleIndex == 167 || this.mModuleIndex == 173) {
            if (isFrontCamera() && !isDualFrontCamera() && b.hT() && (this.mOperatingMode == 32773 || Util.UI_DEBUG())) {
                z = true;
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("isUseSwMfnr: supportSwMfnr = ");
            stringBuilder.append(eY);
            stringBuilder.append(", isFrontCamera = ");
            stringBuilder.append(isFrontCamera());
            stringBuilder.append(", isMfnrEnabled = ");
            stringBuilder.append(isMfnrSatEnable);
            stringBuilder.append(", result = ");
            stringBuilder.append(z);
            Log.d(str, stringBuilder.toString());
            return z;
        } else {
            Log.d(TAG, "For the devices does not have hardware MFNR, use software MFNR");
            return true;
        }
    }

    private void updateSwMfnr() {
        boolean isUseSwMfnr = isUseSwMfnr();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSwMfnr to ");
        stringBuilder.append(isUseSwMfnr);
        Log.d(str, stringBuilder.toString());
        this.mCamera2Device.setSwMfnr(isUseSwMfnr);
    }

    private void updateSuperResolution() {
        if (!isFrontCamera() && this.mModuleIndex != 173) {
            if (isUltraWideBackCamera()) {
                Log.d(TAG, "SR force off for ultra wide camera");
            } else if (CameraSettings.isSREnable()) {
                if (this.mZoomValue == 1.0f) {
                    if (!DataRepository.dataItemRunning().isSwitchOn("pref_camera_super_resolution_key")) {
                        if (this.mMutexModePicker.isSuperResolution()) {
                            this.mMutexModePicker.resetMutexMode();
                        } else if (this.mCamera2Device != null) {
                            this.mCamera2Device.setSuperResolution(false);
                        }
                    }
                } else if (CameraSettings.isGroupShotOn()) {
                    if (this.mMutexModePicker.isSuperResolution()) {
                        this.mMutexModePicker.resetMutexMode();
                    }
                } else if (this.mMutexModePicker.isNormal()) {
                    this.mMutexModePicker.setMutexMode(10);
                }
            }
        }
    }

    private void updateWhiteBalance() {
        setAWBMode(getManualValue(CameraSettings.KEY_WHITE_BALANCE, "1"));
    }

    private void updateISO() {
        String string = getString(2131296426);
        String manualValue = getManualValue(CameraSettings.KEY_QC_ISO, string);
        if (manualValue == null || manualValue.equals(string)) {
            this.mCamera2Device.setISO(0);
        } else {
            this.mCamera2Device.setISO(Math.min(Util.parseInt(manualValue, 0), this.mCameraCapabilities.getMaxIso()));
        }
        updateManualEvAdjust();
    }

    private void updateExposureTime() {
        this.mCamera2Device.setExposureTime(Long.parseLong(getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566))));
        updateManualEvAdjust();
    }

    public void updateManualEvAdjust() {
        if (this.mModuleIndex == 167) {
            boolean equals;
            String manualValue = getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566));
            String manualValue2 = getManualValue(CameraSettings.KEY_QC_ISO, getString(2131296426));
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MODE_MANUAL: exposureTime = ");
            stringBuilder.append(manualValue);
            stringBuilder.append("iso = ");
            stringBuilder.append(manualValue2);
            Log.d(str, stringBuilder.toString());
            if (b.ia()) {
                equals = getString(2131296566).equals(manualValue);
            } else if (getString(2131296426).equals(manualValue2) || getString(2131296566).equals(manualValue)) {
                equals = true;
            } else {
                equals = false;
            }
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        Camera2Module.this.mMainProtocol.setEvAdjustable(equals);
                    }
                });
            }
            if (1 != this.mCamera2Device.getFocusMode() || !this.m3ALocked) {
                return;
            }
            if (!getString(2131296566).equals(manualValue) || !getString(2131296426).equals(manualValue2)) {
                this.mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        Camera2Module.this.mFocusManager.cancelFocus();
                    }
                });
                unlockAEAF();
            }
        }
    }

    private void updateNormalWideLDC() {
        this.mCamera2Device.setNormalWideLDC(shouldApplyNormalWideLDC());
    }

    private boolean shouldApplyNormalWideLDC() {
        if (!CameraSettings.shouldNormalWideLDCBeVisibleInMode(this.mModuleIndex) || this.mActualCameraId == Camera2DataContainer.getInstance().getUltraWideCameraId() || CameraSettings.isRearMenuUltraPixelPhotographyOn() || CameraSettings.isUltraPixelPhotographyOn()) {
            return false;
        }
        return CameraSettings.isNormalWideLDCEnabled();
    }

    private void updateUltraWideLDC() {
        this.mCamera2Device.setUltraWideLDC(shouldApplyUltraWideLDC());
    }

    private boolean shouldApplyUltraWideLDC() {
        if (CameraSettings.shouldUltraWideLDCBeVisibleInMode(this.mModuleIndex) && this.mActualCameraId == Camera2DataContainer.getInstance().getUltraWideCameraId()) {
            return CameraSettings.isUltraWideLDCEnabled();
        }
        return false;
    }

    private void updateContrast() {
        this.mCamera2Device.setContrast(Integer.parseInt(CameraSettings.getContrast()));
    }

    private void updateSaturation() {
        this.mCamera2Device.setSaturation(Integer.parseInt(CameraSettings.getSaturation()));
    }

    private void updateSharpness() {
        this.mCamera2Device.setSharpness(Integer.parseInt(CameraSettings.getSharpness()));
    }

    private void updateDecodePreview() {
        if (scanQRCodeEnabled() || b.fT()) {
            if (scanQRCodeEnabled()) {
                PreviewDecodeManager.getInstance().setPreviewSize(this.mPreviewSize.width, this.mPreviewSize.height);
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("updateDecodePreview: PreviewDecodeManager mPreviewSize = ");
            stringBuilder.append(this.mPreviewSize);
            Log.d(str, stringBuilder.toString());
            this.mCamera2Device.startPreviewCallback(PreviewDecodeManager.getInstance().getPreviewCallback());
            PreviewDecodeManager.getInstance().startDecode();
        }
    }

    private void updateJpegThumbnailSize() {
        CameraSize jpegThumbnailSize = getJpegThumbnailSize();
        this.mCamera2Device.setJpegThumbnailSize(jpegThumbnailSize);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("thumbnailSize=");
        stringBuilder.append(jpegThumbnailSize);
        Log.d(str, stringBuilder.toString());
    }

    private void updateDeviceOrientation() {
        this.mCamera2Device.setDeviceOrientation(this.mOrientation);
    }

    private boolean setSceneMode(String str) {
        int parseInt = Util.parseInt(str, -1);
        if (!Util.isSupported(parseInt, this.mCameraCapabilities.getSupportedSceneModes())) {
            return false;
        }
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sceneMode=");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.mCamera2Device.setSceneMode(parseInt);
        return true;
    }

    private String getSuffix() {
        if (this.mMutexModePicker.isNormal()) {
            return "";
        }
        return this.mMutexModePicker.getSuffix();
    }

    private String getRequestFlashMode() {
        String flashModeByScene;
        if (isSupportSceneMode()) {
            flashModeByScene = CameraSettings.getFlashModeByScene(this.mSceneMode);
            if (!TextUtils.isEmpty(flashModeByScene)) {
                return flashModeByScene;
            }
        }
        if (!this.mMutexModePicker.isSupportedFlashOn() && !this.mMutexModePicker.isSupportedTorch()) {
            return "0";
        }
        flashModeByScene = DataRepository.dataItemConfig().getComponentFlash().getComponentValue(this.mModuleIndex);
        if (this.mCurrentAsdScene == 9) {
            if (flashModeByScene.equals("3")) {
                return "2";
            }
            if (flashModeByScene.equals(ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_AUTO)) {
                return ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_ON;
            }
        }
        return flashModeByScene;
    }

    /* Access modifiers changed, original: protected */
    public boolean isSupportSceneMode() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public CameraSize getBestPictureSize(List<CameraSize> list) {
        PictureSizeManager.initialize(list, getMaxPictureSize());
        return PictureSizeManager.getBestPictureSize();
    }

    /* Access modifiers changed, original: protected */
    public int getMaxPictureSize() {
        return 0;
    }

    private void setPictureOrientation() {
        this.mShootRotation = this.mActivity.getSensorStateManager().isDeviceLying() ? -1.0f : this.mDeviceRotation;
        this.mShootOrientation = this.mOrientation == -1 ? 0 : this.mOrientation;
    }

    private void setWaterMark() {
        if (this.mMultiSnapStatus || this.mModuleIndex == 165 || CameraSettings.isGradienterOn() || CameraSettings.getShaderEffect() != FilterInfo.FILTER_ID_NONE || this.mHasAiSceneFilterEffect || CameraSettings.isTiltShiftOn() || DataRepository.dataItemFeature().fg()) {
            this.mCamera2Device.setDualCamWaterMarkEnable(false);
            this.mCamera2Device.setTimeWaterMarkEnable(false);
            return;
        }
        if (CameraSettings.isDualCameraWaterMarkOpen()) {
            this.mCamera2Device.setDualCamWaterMarkEnable(true);
        } else {
            this.mCamera2Device.setDualCamWaterMarkEnable(false);
        }
        if (CameraSettings.isTimeWaterMarkOpen()) {
            this.mCamera2Device.setTimeWaterMarkEnable(true);
            this.mCaptureWaterMarkStr = Util.getTimeWatermark();
            this.mCamera2Device.setTimeWatermarkValue(this.mCaptureWaterMarkStr);
        } else {
            this.mCaptureWaterMarkStr = null;
            this.mCamera2Device.setTimeWaterMarkEnable(false);
        }
    }

    public void onOrientationChanged(int i, int i2) {
        if (!this.mIsGradienterOn) {
            setOrientation(i, i2);
        }
    }

    private void setOrientation(int i, int i2) {
        if (i != -1) {
            this.mOrientation = i;
            EffectController.getInstance().setOrientation(Util.getShootOrientation(this.mActivity, this.mOrientation));
            checkActivityOrientation();
            if (this.mOrientationCompensation != i2) {
                this.mOrientationCompensation = i2;
                setOrientationParameter();
            }
        }
    }

    private void setOrientationParameter() {
        if (!isDeparted()) {
            if (!(this.mCamera2Device == null || this.mOrientation == -1)) {
                if (getCameraState() == 1) {
                    updatePreferenceInWorkThread(35);
                } else {
                    this.mCamera2Device.setDeviceOrientation(this.mOrientation);
                }
            }
            if (this.mCircularMediaRecorder != null) {
                this.mCircularMediaRecorder.setOrientationHint(this.mOrientationCompensation);
            }
        }
    }

    public void checkDisplayOrientation() {
        if (isCreated()) {
            super.checkDisplayOrientation();
            if (this.mMainProtocol != null) {
                this.mMainProtocol.setCameraDisplayOrientation(this.mCameraDisplayOrientation);
            }
            if (this.mFocusManager != null) {
                this.mFocusManager.setDisplayOrientation(this.mCameraDisplayOrientation);
            }
        }
    }

    /* JADX WARNING: Missing block: B:50:0x0103, code skipped:
            return;
     */
    public void onSingleTapUp(int r4, int r5) {
        /*
        r3 = this;
        r0 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "onSingleTapUp mPaused: ";
        r1.append(r2);
        r2 = r3.mPaused;
        r1.append(r2);
        r2 = "; mCamera2Device: ";
        r1.append(r2);
        r2 = r3.mCamera2Device;
        r1.append(r2);
        r2 = "; isInCountDown: ";
        r1.append(r2);
        r2 = r3.isInCountDown();
        r1.append(r2);
        r2 = "; getCameraState: ";
        r1.append(r2);
        r2 = r3.getCameraState();
        r1.append(r2);
        r2 = "; mMultiSnapStatus: ";
        r1.append(r2);
        r2 = r3.mMultiSnapStatus;
        r1.append(r2);
        r2 = "; Camera2Module: ";
        r1.append(r2);
        r1.append(r3);
        r1 = r1.toString();
        com.android.camera.log.Log.v(r0, r1);
        r0 = r3.mPaused;
        if (r0 != 0) goto L_0x0103;
    L_0x0050:
        r0 = r3.mCamera2Device;
        if (r0 == 0) goto L_0x0103;
    L_0x0054:
        r0 = r3.mCamera2Device;
        r0 = r0.isSessionReady();
        if (r0 == 0) goto L_0x0103;
    L_0x005c:
        r0 = r3.mCamera2Device;
        r0 = r0.isPreviewReady();
        if (r0 == 0) goto L_0x0103;
    L_0x0064:
        r0 = r3.isInTapableRect(r4, r5);
        if (r0 == 0) goto L_0x0103;
    L_0x006a:
        r0 = r3.getCameraState();
        r1 = 3;
        if (r0 == r1) goto L_0x0103;
    L_0x0071:
        r0 = r3.getCameraState();
        r1 = 4;
        if (r0 == r1) goto L_0x0103;
    L_0x0078:
        r0 = r3.getCameraState();
        if (r0 == 0) goto L_0x0103;
    L_0x007e:
        r0 = r3.isInCountDown();
        if (r0 != 0) goto L_0x0103;
    L_0x0084:
        r0 = r3.mMultiSnapStatus;
        if (r0 != 0) goto L_0x0103;
    L_0x0088:
        r0 = r3.mIsMoonMode;
        if (r0 == 0) goto L_0x008e;
    L_0x008c:
        goto L_0x0103;
    L_0x008e:
        r0 = r3.isFrameAvailable();
        if (r0 != 0) goto L_0x0095;
    L_0x0094:
        return;
    L_0x0095:
        r0 = r3.isFrontCamera();
        if (r0 == 0) goto L_0x00a4;
    L_0x009b:
        r0 = r3.mActivity;
        r0 = r0.isScreenSlideOff();
        if (r0 == 0) goto L_0x00a4;
    L_0x00a3:
        return;
    L_0x00a4:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.BackStack) r0;
        r0 = r0.handleBackStackFromTapDown(r4, r5);
        if (r0 == 0) goto L_0x00b7;
    L_0x00b6:
        return;
    L_0x00b7:
        r3.tryRemoveCountDownMessage();
        r0 = r3.mFocusAreaSupported;
        if (r0 != 0) goto L_0x00c3;
    L_0x00be:
        r0 = r3.mMeteringAreaSupported;
        if (r0 != 0) goto L_0x00c3;
    L_0x00c2:
        return;
    L_0x00c3:
        r0 = r3.mMutexModePicker;
        r0 = r0.isUbiFocus();
        if (r0 == 0) goto L_0x00cc;
    L_0x00cb:
        return;
    L_0x00cc:
        r0 = r3.mObjectTrackingStarted;
        r1 = 1;
        if (r0 == 0) goto L_0x00d4;
    L_0x00d1:
        r3.stopObjectTracking(r1);
    L_0x00d4:
        r0 = r3.mMainProtocol;
        r0.setFocusViewType(r1);
        r0 = new android.graphics.Point;
        r0.<init>(r4, r5);
        r3.mapTapCoordinate(r0);
        r3.unlockAEAF();
        r4 = 2;
        r3.setCameraState(r4);
        r4 = r3.mFocusManager;
        r5 = r0.x;
        r0 = r0.y;
        r4.onSingleTapUp(r5, r0);
        r4 = r3.mFocusAreaSupported;
        if (r4 != 0) goto L_0x0102;
    L_0x00f5:
        r4 = r3.mMeteringAreaSupported;
        if (r4 == 0) goto L_0x0102;
    L_0x00f9:
        r4 = r3.mActivity;
        r4 = r4.getSensorStateManager();
        r4.reset();
    L_0x0102:
        return;
    L_0x0103:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.onSingleTapUp(int, int):void");
    }

    private void unlockAEAF() {
        Log.d(TAG, "unlockAEAF");
        this.m3ALocked = false;
        if (this.mAeLockSupported) {
            this.mCamera2Device.unlockExposure();
        }
        if (this.mFocusManager != null) {
            this.mFocusManager.setAeAwbLock(false);
        }
    }

    private void lockAEAF() {
        Log.d(TAG, "lockAEAF");
        if (this.mFocusManager != null) {
            this.mFocusManager.setAeAwbLock(true);
        }
        this.m3ALocked = true;
    }

    private boolean is3ALocked() {
        return this.m3ALocked;
    }

    public void onGradienterSwitched(boolean z) {
        this.mIsGradienterOn = z;
        this.mActivity.getSensorStateManager().setGradienterEnabled(z);
        updatePreferenceTrampoline(2, 5);
    }

    public void onTiltShiftSwitched(boolean z) {
        if (z) {
            resetEvValue();
        }
        this.mMainProtocol.initEffectCropView();
        updatePreferenceTrampoline(2, 5);
        this.mMainProtocol.updateEffectViewVisible();
        this.mMainProtocol.setEvAdjustable(z ^ 1);
    }

    public void onHanGestureSwitched(boolean z) {
        if (z) {
            PreviewDecodeManager.getInstance().init(this.mBogusCameraId, 1);
            PreviewDecodeManager.getInstance().startDecode();
            return;
        }
        PreviewDecodeManager.getInstance().stopDecode(1);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (!isFrameAvailable()) {
            return false;
        }
        switch (i) {
            case 24:
            case 25:
            case 87:
            case 88:
                if (i == 24 || i == 88) {
                    z = true;
                }
                if (handleVolumeKeyEvent(z, true, keyEvent.getRepeatCount())) {
                    return true;
                }
                break;
            case 27:
            case 66:
                if (keyEvent.getRepeatCount() == 0) {
                    if (!Util.isFingerPrintKeyEvent(keyEvent)) {
                        onShutterButtonClick(40);
                    } else if (CameraSettings.isFingerprintCaptureEnable()) {
                        onShutterButtonClick(30);
                    }
                }
                return true;
            case 80:
                if (keyEvent.getRepeatCount() == 0) {
                    onShutterButtonFocus(true, 1);
                }
                return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                if (((BackStack) ModeCoordinatorImpl.getInstance().getAttachProtocol(171)).handleBackStackFromKeyBack()) {
                    return true;
                }
                break;
            case 24:
            case 25:
            case 87:
            case 88:
                boolean z = i == 24 || i == 88;
                if (handleVolumeKeyEvent(z, false, keyEvent.getRepeatCount())) {
                    return true;
                }
                break;
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* Access modifiers changed, original: protected */
    public void performVolumeKeyClicked(String str, int i, boolean z) {
        if (!this.mPaused && getCameraState() != 0) {
            if (!isDoingAction()) {
                restoreBottom();
            }
            if (i == 0) {
                if (z) {
                    onShutterButtonFocus(true, 1);
                    if (str.equals(getString(2131296541))) {
                        startCount(2, 20);
                    } else {
                        onShutterButtonClick(20);
                    }
                } else {
                    onShutterButtonFocus(false, 0);
                    if (this.mVolumeLongPress) {
                        this.mVolumeLongPress = false;
                        onShutterButtonLongClickCancel(false);
                    }
                }
            } else if (z && !this.mVolumeLongPress) {
                this.mUpdateImageTitle = true;
                this.mVolumeLongPress = onShutterButtonLongClick();
                if (!this.mVolumeLongPress && this.mLongPressedAutoFocus) {
                    this.mVolumeLongPress = true;
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isZoomEnabled() {
        return (getCameraState() == 3 || this.mMutexModePicker.isUbiFocus() || CameraSettings.isStereoModeOn() || CameraSettings.isPortraitModeBackOn() || isFrontCamera() || CameraSettings.isRearMenuUltraPixelPhotographyOn() || CameraSettings.isUltraPixelPhotographyOn()) ? false : true;
    }

    public void onScaleEnd() {
        super.onScaleEnd();
        boolean isRearMenuUltraPixelPhotographyOn = CameraSettings.isRearMenuUltraPixelPhotographyOn();
        boolean isUltraPixelPhotographyOn = CameraSettings.isUltraPixelPhotographyOn();
        if (isRearMenuUltraPixelPhotographyOn || isUltraPixelPhotographyOn) {
            BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
            if (bottomPopupTips != null) {
                bottomPopupTips.showTips(15, 2131296895, 1);
            }
        }
    }

    private String getManualValue(String str, String str2) {
        CameraSettingPreferences instance = CameraSettingPreferences.instance();
        if (ModuleManager.isManualModule()) {
            return instance.getString(str, str2);
        }
        return str2;
    }

    private void resetGradienter() {
        if (CameraSettings.isGradienterOn()) {
            this.mActivity.getSensorStateManager().setGradienterEnabled(false);
        }
    }

    private void resetScreenOn() {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(17);
            this.mHandler.removeMessages(2);
        }
    }

    private void keepScreenOnAwhile() {
        this.mHandler.sendEmptyMessageDelayed(17, 1000);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        if (!isDoingAction()) {
            keepScreenOnAwhile();
        }
    }

    private void releaseEffectProcessor() {
        if (this.mEffectProcessor != null) {
            this.mEffectProcessor.releaseIfNeeded();
            this.mEffectProcessor = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isDetectedHHT() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public int getMutexHdrMode(String str) {
        if ("normal".equals(str)) {
            int i;
            if (!b.isMTKPlatform() || b.gJ()) {
                i = 1;
            } else {
                i = 5;
            }
            return i;
        } else if (b.gB() && ComponentConfigHdr.HDR_VALUE_LIVE.equals(str)) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean isShowCaptureButton() {
        return !this.mMutexModePicker.isBurstShoot() && isSupportFocusShoot();
    }

    public boolean isShowAeAfLockIndicator() {
        return this.m3ALocked;
    }

    public boolean isSupportFocusShoot() {
        return DataRepository.dataItemGlobal().isGlobalSwitchOn("pref_camera_focus_shoot_key");
    }

    public boolean isMeteringAreaOnly() {
        boolean z = false;
        if (this.mCamera2Device == null) {
            return false;
        }
        int focusMode = this.mCamera2Device.getFocusMode();
        if ((!this.mFocusAreaSupported && this.mMeteringAreaSupported) || 5 == focusMode || focusMode == 0) {
            z = true;
        }
        return z;
    }

    public void enterMutexMode(int i) {
        if (this.mCamera2Device == null) {
            Log.d(TAG, "enterMutexMode error, mCamera2Device is null");
            return;
        }
        if (i == 1) {
            this.mCamera2Device.setHDR(true);
        } else if (i == 3) {
            this.mCamera2Device.setHHT(true);
        } else if (i == 10) {
            this.mCamera2Device.setSuperResolution(true);
        }
        updateMfnr(CameraSettings.isMfnrSatEnable());
        updateSwMfnr();
    }

    public void exitMutexMode(int i) {
        if (i == 1) {
            this.mCamera2Device.setHDR(false);
            updateSuperResolution();
        } else if (i == 3) {
            this.mCamera2Device.setHHT(false);
        } else if (i == 10) {
            this.mCamera2Device.setSuperResolution(false);
        }
        updateMfnr(CameraSettings.isMfnrSatEnable());
        updateSwMfnr();
    }

    private boolean needShowThumbProgressImmediately() {
        return Long.parseLong(getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566))) > 250000000 && this.mModuleIndex != 173;
    }

    private void updateThumbProgress(boolean z) {
        ActionProcessing actionProcessing = (ActionProcessing) ModeCoordinatorImpl.getInstance().getAttachProtocol(162);
        if (actionProcessing != null) {
            actionProcessing.updateLoading(z);
        }
    }

    public void startAiLens() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                Camera2Module.this.startLensActivity();
            }
        }, 300);
    }

    private void startLensActivity() {
        if (!Util.isGlobalVersion()) {
            Log.d(TAG, "start ai lens");
            try {
                Intent intent = new Intent();
                intent.setAction("android.media.action.XIAOAI_CONTROL");
                intent.setPackage(CameraSettings.AI_LENS_PACKAGE);
                intent.putExtra("preview_width", this.mPreviewSize.width);
                intent.putExtra("preview_height", this.mPreviewSize.height);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(2131034112, 2131034113);
            } catch (Exception e) {
                Log.e(TAG, "onClick: occur a exception", e);
            }
        } else if (this.mLensApi != null && this.mLensStatus == 0) {
            this.mLensApi.launchLensActivity(this.mActivity, 0);
        }
    }

    public void showQRCodeResult() {
        if (!this.mPaused) {
            String scanResult = PreviewDecodeManager.getInstance().getScanResult();
            if (scanResult == null || scanResult.isEmpty()) {
                Log.e(TAG, "showQRCodeResult: get a null result!");
                return;
            }
            Camera camera = this.mActivity;
            camera.dismissKeyguard();
            Intent intent = new Intent(Util.QRCODE_RECEIVER_ACTION);
            intent.addFlags(32);
            intent.setPackage("com.xiaomi.scanner");
            intent.putExtra("result", scanResult);
            camera.sendBroadcast(intent);
            camera.setJumpFlag(3);
            PreviewDecodeManager.getInstance().resetScanResult();
        }
    }

    /* Access modifiers changed, original: protected */
    public void sendOpenFailMessage() {
        this.mHandler.sendEmptyMessage(10);
    }

    public void onPreviewMetaDataUpdate(CaptureResult captureResult) {
        if (captureResult != null) {
            super.onPreviewMetaDataUpdate(captureResult);
            Integer num = (Integer) captureResult.get(CaptureResult.SENSOR_SENSITIVITY);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onPreviewMetaDataUpdate: ISO=");
            stringBuilder.append(num);
            Log.c(str, stringBuilder.toString());
            boolean z = num != null && num.intValue() >= 800 && isFrontCamera();
            this.mShouldDoMFNR = z;
            if (this.mMetaDataFlowableEmitter != null) {
                this.mMetaDataFlowableEmitter.onNext(captureResult);
            }
            if (!(this.mAiSceneFlowableEmitter == null || !this.mAiSceneEnabled || this.mCamera2Device == null)) {
                this.mAiSceneFlowableEmitter.onNext(captureResult);
            }
        }
    }

    private void initMetaParser() {
        this.mMetaDataDisposable = Flowable.create(new FlowableOnSubscribe<CaptureResult>() {
            public void subscribe(FlowableEmitter<CaptureResult> flowableEmitter) throws Exception {
                Camera2Module.this.mMetaDataFlowableEmitter = flowableEmitter;
            }
        }, BackpressureStrategy.DROP).observeOn(GlobalConstant.sCameraSetupScheduler).map(new FunctionParseAsdFace(this, isFrontCamera())).map(new FunctionParseAsdHdr(this)).map(new FunctionParseAsdUltraWide(this)).sample(500, TimeUnit.MILLISECONDS).observeOn(GlobalConstant.sCameraSetupScheduler).map(new FunctionParseAsdScene(this)).observeOn(AndroidSchedulers.mainThread()).onTerminateDetach().subscribe(new Consumer<Integer>() {
            public void accept(Integer num) {
                Camera2Module.this.consumeAsdSceneResult(num.intValue());
            }
        });
    }

    private void consumeAsdSceneResult(int i) {
        if (this.mCurrentAsdScene != i && !isDoingAction() && isAlive() && !this.mActivity.isActivityPaused()) {
            exitAsdScene(this.mCurrentAsdScene);
            enterAsdScene(i);
            this.mCurrentAsdScene = i;
        }
    }

    private void exitAsdScene(int i) {
        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
        if (i == 0) {
            String componentValue = DataRepository.dataItemConfig().getComponentFlash().getComponentValue(this.mModuleIndex);
            if (!"1".equals(componentValue) && !ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_ON.equals(componentValue) && !"2".equals(componentValue)) {
                topAlert.alertFlash(8, false, false);
            }
        } else if (i != 9) {
            switch (i) {
                case 4:
                case 5:
                case 6:
                    if (!this.m3ALocked) {
                        hideTipMessage(0);
                        return;
                    }
                    return;
                case 7:
                    if (!this.m3ALocked) {
                        setPortraitSuccessHintVisible(8);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            if (ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_AUTO.equals(DataRepository.dataItemConfig().getComponentFlash().getComponentValue(this.mModuleIndex))) {
                topAlert.alertFlash(8, false, false);
            }
            updatePreferenceInWorkThread(10);
        }
    }

    private void enterAsdScene(int i) {
        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
        if (i == 0) {
            topAlert.alertFlash(0, false, false);
        } else if (i != 9) {
            switch (i) {
                case 4:
                    if (!this.m3ALocked) {
                        updateTipMessage(6, 2131296669, 4);
                        return;
                    }
                    return;
                case 5:
                    if (!this.m3ALocked) {
                        updateTipMessage(6, 2131296670, 4);
                        return;
                    }
                    return;
                case 6:
                    if (!this.m3ALocked) {
                        updateTipMessage(6, 2131296668, 4);
                        return;
                    }
                    return;
                case 7:
                    if (!this.m3ALocked) {
                        setPortraitSuccessHintVisible(0);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            String componentValue = DataRepository.dataItemConfig().getComponentFlash().getComponentValue(this.mModuleIndex);
            if ("3".equals(componentValue)) {
                updatePreferenceInWorkThread(10);
            } else if (ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_AUTO.equals(componentValue)) {
                topAlert.alertFlash(0, false, false);
                updatePreferenceInWorkThread(10);
            }
        }
    }

    private boolean isPortraitSuccessHintShowing() {
        return ((BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175)).isPortraitHintVisible();
    }

    private void setPortraitSuccessHintVisible(int i) {
        ((BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175)).setPortraitHintVisible(i);
    }

    private void initAiSceneParser() {
        this.mFunctionParseAiScene = new FunctionParseAiScene(this.mModuleIndex);
        this.mAiSceneDisposable = Flowable.create(new FlowableOnSubscribe<CaptureResult>() {
            public void subscribe(FlowableEmitter<CaptureResult> flowableEmitter) throws Exception {
                Camera2Module.this.mAiSceneFlowableEmitter = flowableEmitter;
            }
        }, BackpressureStrategy.DROP).observeOn(GlobalConstant.sCameraSetupScheduler).map(this.mFunctionParseAiScene).filter(new PredicateFilterAiScene(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            public void accept(Integer num) {
                Camera2Module.this.consumeAiSceneResult(num.intValue(), false);
            }
        });
    }

    private void consumeAiSceneResult(int i, boolean z) {
        if (this.mCurrentAiScene != i && !isDoingAction() && isAlive() && !this.mActivity.isActivityPaused()) {
            if (!z || !this.isResetFromMutex) {
                boolean z2 = false;
                if (!z) {
                    this.isResetFromMutex = false;
                }
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("consumeAiSceneResult: ");
                stringBuilder.append(i);
                stringBuilder.append("; isReset: ");
                stringBuilder.append(z);
                Log.d(str, stringBuilder.toString());
                TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
                if (!isFrontCamera()) {
                    this.mCamera2Device.setCameraAI30(i == 25);
                }
                checkCloseMoonMode(i, 8);
                if (i != -1) {
                    int parseInt;
                    if (i == 1) {
                        parseInt = Integer.parseInt(CameraSettings.getSharpness());
                        if (parseInt < 6) {
                            parseInt++;
                        }
                        this.mCurrentAiScene = i;
                        configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                        this.mCamera2Device.setSharpness(parseInt);
                    } else if (i == 4) {
                        this.mCamera2Device.setContrast(Integer.parseInt(CameraSettings.getContrast()));
                        this.mCurrentAiScene = i;
                        configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                        updateSuperResolution();
                    } else if (i != 10) {
                        if (i != 15 && i != 19) {
                            switch (i) {
                                case 7:
                                case 8:
                                    this.mCurrentAiScene = i;
                                    configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                                    break;
                                default:
                                    switch (i) {
                                        case 25:
                                            trackAISceneChanged(this.mModuleIndex, 25);
                                            topAlert.setAiSceneImageLevel(25);
                                            topAlert.alertAiSceneSelector(8);
                                            setAiSceneEffect(25);
                                            this.mCurrentAiScene = i;
                                            configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                                            this.mCamera2Device.setASD(false);
                                            resumePreviewInWorkThread();
                                            return;
                                        case 26:
                                        case 27:
                                        case 28:
                                        case 29:
                                        case 30:
                                        case 31:
                                            if (!DataRepository.dataItemFeature().eU()) {
                                                configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                                                updatePreferenceInWorkThread(UpdateConstant.AI_SCENE_CONFIG);
                                                i = 0;
                                                break;
                                            }
                                            this.mCurrentAiScene = i;
                                            configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                                            break;
                                        default:
                                            switch (i) {
                                                case 34:
                                                case 36:
                                                    this.mCurrentAiScene = i;
                                                    break;
                                                case 35:
                                                    if (showMoonMode()) {
                                                        topAlert.setAiSceneImageLevel(i);
                                                        this.mCamera2Device.setASD(true);
                                                        trackAISceneChanged(this.mModuleIndex, i);
                                                        this.mCurrentAiScene = i;
                                                        return;
                                                    }
                                                    break;
                                                default:
                                                    configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                                                    updatePreferenceInWorkThread(UpdateConstant.AI_SCENE_CONFIG);
                                                    break;
                                            }
                                    }
                            }
                        }
                        parseInt = Integer.parseInt(CameraSettings.getSharpness());
                        if (parseInt < 6) {
                            parseInt++;
                        }
                        this.mCamera2Device.setSharpness(parseInt);
                        this.mCurrentAiScene = i;
                        configChanges.restoreAllMutexElement(SupportedConfigFactory.CLOSE_BY_AI);
                    } else {
                        configChanges.closeMutexElement(SupportedConfigFactory.CLOSE_BY_AI, 193);
                        setFlashMode("0");
                        updateMfnr(true);
                        updateOIS();
                    }
                    z2 = true;
                    trackAISceneChanged(this.mModuleIndex, i);
                    topAlert.setAiSceneImageLevel(i);
                    topAlert.alertAiSceneSelector(8);
                    if (z2) {
                        setAiSceneEffect(i);
                    }
                    this.mCamera2Device.setASD(true);
                    if (!z) {
                        this.mCurrentAiScene = i;
                    }
                    updateBeauty();
                    resumePreviewInWorkThread();
                    return;
                }
                configChanges.closeMutexElement(SupportedConfigFactory.CLOSE_BY_AI, 193);
                topAlert.setAiSceneImageLevel(23);
                topAlert.alertHDR(8, false, false);
                topAlert.alertAiSceneSelector(0);
                applyBacklightEffect();
                this.mCamera2Device.setASD(false);
                resumePreviewInWorkThread();
                this.mCurrentAiScene = i;
            }
        }
    }

    public void updateBacklight() {
        if (!isDoingAction() && isAlive()) {
            this.isSilhouette = false;
            applyBacklightEffect();
            resumePreviewInWorkThread();
        }
    }

    public void updateSilhouette() {
        if (!isDoingAction() && isAlive()) {
            this.isSilhouette = true;
            trackAISceneChanged(this.mModuleIndex, 24);
            setAiSceneEffect(24);
            updateHDR("off");
            resumePreviewInWorkThread();
        }
    }

    private void checkCloseMoonMode(int i, int i2) {
        if (!this.mEnteringMoonMode) {
            return;
        }
        if ((this.mCurrentAiScene == 10 || this.mCurrentAiScene == 35) && i != this.mCurrentAiScene) {
            TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
            if (topAlert != null) {
                topAlert.alertMoonModeSelector(i2);
                if (i2 != 0) {
                    this.mEnteringMoonMode = false;
                }
            }
            updateMoon(false);
            if (this.mMutexModePicker.isSuperResolution()) {
                this.mCamera2Device.setSuperResolution(true);
            }
        }
    }

    private boolean showMoonMode() {
        TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
        if (topAlert == null) {
            return false;
        }
        this.mEnteringMoonMode = true;
        topAlert.alertMoonModeSelector(0);
        updateMoonNight();
        return true;
    }

    public void updateMoonNight() {
        this.mIsMoonMode = false;
        checkCloseMoonMode(10, 0);
        ((ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164)).closeMutexElement(SupportedConfigFactory.CLOSE_BY_AI, 193);
        setFlashMode("0");
        updateMfnr(true);
        updateOIS();
        setAiSceneEffect(10);
        this.mCurrentAiScene = 10;
        resumePreviewInWorkThread();
    }

    public void updateMoon(boolean z) {
        if (z) {
            this.mIsMoonMode = true;
            this.mCamera2Device.setSuperResolution(false);
            updateFocusMode();
            this.mCurrentAiScene = 35;
            this.mCamera2Device.setASDScene(35);
            resumePreviewInWorkThread();
        } else if (this.mIsMoonMode) {
            this.mIsMoonMode = false;
            setFocusMode(this.mFocusManager.setFocusMode(CameraSettings.getFocusMode()));
            this.mCamera2Device.setASDScene(-35);
        }
    }

    /* JADX WARNING: Missing block: B:11:0x002a, code skipped:
            return false;
     */
    private boolean shouldChangeAiScene(int r7) {
        /*
        r6 = this;
        r0 = r6.isDoingAction();
        r1 = 0;
        if (r0 != 0) goto L_0x002a;
    L_0x0007:
        r0 = r6.isAlive();
        if (r0 != 0) goto L_0x000e;
    L_0x000d:
        goto L_0x002a;
    L_0x000e:
        r0 = r6.mCurrentDetectedScene;
        if (r0 == r7) goto L_0x0029;
    L_0x0012:
        r2 = java.lang.System.currentTimeMillis();
        r4 = r6.mLastChangeSceneTime;
        r2 = r2 - r4;
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x0029;
    L_0x001f:
        r6.mCurrentDetectedScene = r7;
        r0 = java.lang.System.currentTimeMillis();
        r6.mLastChangeSceneTime = r0;
        r7 = 1;
        return r7;
    L_0x0029:
        return r1;
    L_0x002a:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.shouldChangeAiScene(int):boolean");
    }

    private void resetAiSceneInHdrOrFlashOn() {
        if (this.mAiSceneEnabled && !this.isResetFromMutex && this.mCurrentAiScene != 0) {
            if (this.mCurrentAiScene == -1 || this.mCurrentAiScene == 10) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        Camera2Module.this.consumeAiSceneResult(0, true);
                        Camera2Module.this.isResetFromMutex = true;
                    }
                });
            }
        }
    }

    /* JADX WARNING: Missing block: B:10:0x0023, code skipped:
            return;
     */
    private void resetAsdSceneInHdrOrFlashChange() {
        /*
        r2 = this;
        r0 = com.mi.config.b.hR();
        if (r0 == 0) goto L_0x0023;
    L_0x0006:
        r0 = r2.isFrontCamera();
        if (r0 == 0) goto L_0x0023;
    L_0x000c:
        r0 = r2.mCurrentAsdScene;
        r1 = -1;
        if (r0 != r1) goto L_0x0012;
    L_0x0011:
        goto L_0x0023;
    L_0x0012:
        r0 = r2.mCurrentAsdScene;
        r1 = 9;
        if (r0 != r1) goto L_0x0022;
    L_0x0018:
        r0 = r2.mHandler;
        r1 = new com.android.camera.module.Camera2Module$30;
        r1.<init>();
        r0.post(r1);
    L_0x0022:
        return;
    L_0x0023:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.Camera2Module.resetAsdSceneInHdrOrFlashChange():void");
    }

    private void trackAISceneChanged(int i, int i2) {
        CameraStatUtil.trackAISceneChanged(i, i2, getResources());
    }

    private void applyBacklightEffect() {
        trackAISceneChanged(this.mModuleIndex, 23);
        setAiSceneEffect(23);
        updateHDR("normal");
        resetEvValue();
    }

    public void startScreenLight(final int i, final int i2) {
        if (!this.mPaused) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (Camera2Module.this.mActivity != null) {
                        Camera2Module.this.mActivity.setWindowBrightness(i2);
                    }
                    FullScreenProtocol fullScreenProtocol = (FullScreenProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(196);
                    if (fullScreenProtocol != null) {
                        fullScreenProtocol.setScreenLightColor(i);
                        fullScreenProtocol.showScreenLight();
                    }
                }
            });
        }
    }

    public void stopScreenLight() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (Camera2Module.this.mActivity != null) {
                    Camera2Module.this.mActivity.restoreWindowBrightness();
                }
                FullScreenProtocol fullScreenProtocol = (FullScreenProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(196);
                String access$1400 = Camera2Module.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("stopScreenLight: protocol = ");
                stringBuilder.append(fullScreenProtocol);
                stringBuilder.append(", mHandler = ");
                stringBuilder.append(Camera2Module.this.mHandler);
                Log.d(access$1400, stringBuilder.toString());
                if (fullScreenProtocol != null) {
                    fullScreenProtocol.hideScreenLight();
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void trackModeCustomInfo(int i) {
        if (this.mModuleIndex == 167) {
            trackManualInfo(i);
        } else if (this.mModuleIndex == 163) {
            if (isFaceBeautyOn(this.mBeautyValues)) {
                trackBeautyInfo(i, isFrontCamera(), new BeautyValues(this.mBeautyValues));
            }
            CameraStatUtil.trackUltraWidePictureTaken();
        } else if (this.mModuleIndex == 165) {
            CameraStatUtil.trackUltraWidePictureTaken();
        }
    }

    private void trackManualInfo(int i) {
        CameraStatUtil.trackPictureTakenInManual(i, getManualValue(CameraSettings.KEY_WHITE_BALANCE, getString(2131296356)), getManualValue(CameraSettings.KEY_QC_EXPOSURETIME, getString(2131296566)), getManualValue(CameraSettings.KEY_QC_ISO, getString(2131296426)), this.mModuleIndex);
        CameraStatUtil.trackUltraWideManualTaken(this.mModuleIndex);
    }

    private boolean isFaceBeautyOn(BeautyValues beautyValues) {
        boolean z = false;
        if (beautyValues == null) {
            return false;
        }
        if (!b.hM()) {
            return BeautyConstant.LEVEL_CLOSE.equals(beautyValues.mBeautyLevel) ^ 1;
        }
        if (!CameraSettings.isAdvancedBeautyOn()) {
            return BeautyConstant.LEVEL_CLOSE.equals(beautyValues.mBeautyLevel) ^ 1;
        }
        if (beautyValues.mBeautySkinSmooth > 0 || beautyValues.mBeautySkinColor > 0 || beautyValues.mBeautySlimFace > 0) {
            z = true;
        }
        return z;
    }

    private void trackBeautyInfo(int i, boolean z, BeautyValues beautyValues) {
        CameraStatUtil.trackBeautyInfo(i, z ? CameraStat.VALUE_FRONT_CAMERA : CameraStat.VALUE_BACK_CAMERA, beautyValues);
    }

    private void initFlashAutoStateForTrack(boolean z) {
        this.mFlashAutoModeState = null;
        String componentValue = DataRepository.dataItemConfig().getComponentFlash().getComponentValue(this.mModuleIndex);
        if (!componentValue.equals("3") && !componentValue.equals(ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_AUTO)) {
            return;
        }
        if (this.mCurrentAsdScene == 9 || z) {
            this.mFlashAutoModeState = CameraStatUtil.AUTO_ON;
        } else {
            this.mFlashAutoModeState = CameraStatUtil.AUTO_OFF;
        }
    }

    private PictureInfo getPictureInfo() {
        PictureInfo pictureInfo = new PictureInfo();
        pictureInfo.setFrontMirror(isFrontMirror());
        pictureInfo.setSensorType(isFrontCamera());
        pictureInfo.setBokehFrontCamera(isBokehFrontCamera());
        if (isPortraitMode()) {
            pictureInfo.setAiEnabled(this.mAiSceneEnabled);
            pictureInfo.setAiType(this.mCurrentAiScene);
        }
        pictureInfo.end();
        return pictureInfo;
    }

    private String getCurrentAiSceneName() {
        int i = this.mCurrentAiScene;
        if (this.mModuleIndex != 163 && this.mModuleIndex != 167) {
            return null;
        }
        String str = "off";
        if (!CameraSettings.getAiSceneOpen()) {
            return str;
        }
        String str2;
        str = "未知";
        if (i == -1) {
            i = this.isSilhouette ? 24 : 23;
        }
        TypedArray obtainTypedArray = getResources().obtainTypedArray(2131623936);
        if (i < 0 || i >= obtainTypedArray.length()) {
            str2 = str;
        } else {
            str2 = obtainTypedArray.getString(i);
        }
        obtainTypedArray.recycle();
        return str2;
    }

    public void onUltraWideChanged(final boolean z) {
        this.mHandler.post(new Runnable() {
            public void run() {
                BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
                if (bottomPopupTips != null) {
                    if (z) {
                        Camera2Module.this.trackAISceneChanged(Camera2Module.this.mModuleIndex, 36);
                        if (Camera2Module.this.getModuleIndex() != 171) {
                            bottomPopupTips.showTips(14, 2131296833, 5);
                        } else {
                            return;
                        }
                    }
                    Camera2Module.this.trackAISceneChanged(Camera2Module.this.mModuleIndex, 0);
                    bottomPopupTips.directlyHideTips();
                }
            }
        });
    }

    public boolean isUltraWideDetectStarted() {
        return true;
    }
}
