.class public abstract Lcom/android/camera/module/BaseModule;
.super Ljava/lang/Object;
.source "BaseModule.java"

# interfaces
.implements Lcom/android/camera/MutexModeManager$MutexCallBack;
.implements Lcom/android/camera/module/Module;
.implements Lcom/android/camera/protocol/ModeProtocol$EvChangedProtocol;
.implements Lcom/android/camera/protocol/ModeProtocol$UltraWideProtocol;
.implements Lcom/android/camera/protocol/ModeProtocol$ZoomProtocol;
.implements Lcom/android/camera/ui/FocusView$ExposureViewListener;
.implements Lcom/android/camera2/Camera2Proxy$CameraMetaDataCallback;
.implements Lio/reactivex/functions/Consumer;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/camera/module/BaseModule$CameraMode;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/android/camera/MutexModeManager$MutexCallBack;",
        "Lcom/android/camera/module/Module;",
        "Lcom/android/camera/protocol/ModeProtocol$EvChangedProtocol;",
        "Lcom/android/camera/protocol/ModeProtocol$UltraWideProtocol;",
        "Lcom/android/camera/protocol/ModeProtocol$ZoomProtocol;",
        "Lcom/android/camera/ui/FocusView$ExposureViewListener;",
        "Lcom/android/camera2/Camera2Proxy$CameraMetaDataCallback;",
        "Lio/reactivex/functions/Consumer<",
        "[I>;"
    }
.end annotation


# static fields
.field protected static final BACK_PRESSED_TIME_INTERVAL:I = 0xbb8

.field public static final CAMERA_MODES:[I

.field public static final CAMERA_MODE_IMAGE_CAPTURE:I = 0x2

.field public static final CAMERA_MODE_NORMAL:I = 0x0

.field public static final CAMERA_MODE_SCAN_QR_CODE:I = 0x6

.field public static final CAMERA_MODE_VIDEO_CAPTURE:I = 0x4

.field protected static final DEBUG:Z

.field public static final LENS_DIRTY_DETECT_HINT_DURATION:I = 0x1f40

.field protected static final LENS_DIRTY_DETECT_TIMEOUT:I = 0x3a98

.field protected static final SCREEN_DELAY:I = 0xea60

.field protected static final SCREEN_DELAY_KEYGUARD:I = 0x7530

.field public static final SHUTTER_DOWN_FROM_BUTTON:I = 0x2

.field public static final SHUTTER_DOWN_FROM_HARD_KEY:I = 0x1

.field public static final SHUTTER_DOWN_FROM_UNKNOWN:I = 0x0

.field public static final SHUTTER_DOWN_FROM_UNLOCK_OR_LONG_CLICK_OR_AUDIO:I = 0x3

.field private static final TAG:Ljava/lang/String; = "BaseModule"

.field private static final ULTRA_WIDE_ZOOM_MAX:F = 2.1f


# instance fields
.field private isShowPreviewDebugInfo:Z

.field protected isZooming:Z

.field protected mActivity:Lcom/android/camera/Camera;

.field protected mActualCameraId:I

.field protected mAeLockSupported:Z

.field protected mAwbLockSupported:Z

.field protected mBogusCameraId:I

.field protected mCamera2Device:Lcom/android/camera2/Camera2Proxy;

.field protected mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

.field protected mCameraDisabled:Z

.field protected mCameraDisplayOrientation:I

.field protected mCameraHardwareError:Z

.field private mCameraState:I

.field protected mContentResolver:Landroid/content/ContentResolver;

.field protected mContinuousFocusSupported:Z

.field protected mDeviceRotation:F

.field protected mDisplayRect:Landroid/graphics/Rect;

.field protected mDisplayRotation:I

.field protected mErrorCallback:Lcom/android/camera/CameraErrorCallbackImpl;

.field private mEvState:I

.field private mEvValue:I

.field protected mFlashAutoModeState:Ljava/lang/String;

.field protected mFocusAreaSupported:Z

.field protected mIgnoreFocusChanged:Z

.field private mIgnoreTouchEvent:Z

.field private mIsCreated:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private mIsDeparted:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private mIsFrameAvailable:Ljava/util/concurrent/atomic/AtomicBoolean;

.field protected mLastBackPressedTime:J

.field private mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

.field private mLensDirtyDetectHintDisposable:Lio/reactivex/disposables/Disposable;

.field protected mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

.field protected mMainThreadId:J

.field protected mMaxFaceCount:I

.field protected mMeteringAreaSupported:Z

.field protected mModuleIndex:I

.field protected mMutexModePicker:Lcom/android/camera/MutexModeManager;

.field protected mObjectTrackingStarted:Z

.field protected mOpenCameraFail:Z

.field protected mOrientation:I

.field protected mOrientationCompensation:I

.field protected mOutPutSize:Lcom/android/camera/CameraSize;

.field protected volatile mPaused:Z

.field protected mPendingScreenSlideKeyCode:I

.field protected mPictureSize:Lcom/android/camera/CameraSize;

.field protected mPreviewSize:Lcom/android/camera/CameraSize;

.field private mRestoring:Z

.field protected mSettingsOverrider:Lcom/android/camera/preferences/SettingsOverrider;

.field protected mSurfaceCreatedTimestamp:J

.field protected mTriggerMode:I

.field protected mUIStyle:I

.field protected mUnInterruptableReason:Ljava/lang/String;

.field private mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

.field private mUpdateWorkThreadEmitter:Lio/reactivex/ObservableEmitter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/ObservableEmitter<",
            "[I>;"
        }
    .end annotation
.end field

.field protected mZoomMaxRatio:F

.field private mZoomScaled:F

.field protected mZoomSupported:Z

.field protected mZoomValue:F


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 98
    invoke-static {}, Lcom/android/camera/Util;->isDebugOsBuild()Z

    move-result v0

    sput-boolean v0, Lcom/android/camera/module/BaseModule;->DEBUG:Z

    .line 1148
    const/4 v0, 0x4

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/android/camera/module/BaseModule;->CAMERA_MODES:[I

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x2
        0x4
        0x6
    .end array-data
.end method

.method public constructor <init>()V
    .locals 3

    .line 250
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 120
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mOrientationCompensation:I

    .line 121
    const/4 v1, -0x1

    iput v1, p0, Lcom/android/camera/module/BaseModule;->mOrientation:I

    .line 122
    const/high16 v2, -0x40800000    # -1.0f

    iput v2, p0, Lcom/android/camera/module/BaseModule;->mDeviceRotation:F

    .line 125
    new-instance v2, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v2, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v2, p0, Lcom/android/camera/module/BaseModule;->mIsCreated:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 126
    new-instance v2, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v2, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v2, p0, Lcom/android/camera/module/BaseModule;->mIsDeparted:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 127
    new-instance v2, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v2, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v2, p0, Lcom/android/camera/module/BaseModule;->mIsFrameAvailable:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 132
    iput v0, p0, Lcom/android/camera/module/BaseModule;->mCameraState:I

    .line 190
    const/high16 v0, 0x3f800000    # 1.0f

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    .line 191
    iput v0, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    .line 213
    iput v1, p0, Lcom/android/camera/module/BaseModule;->mUIStyle:I

    .line 222
    iput v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRotation:I

    .line 232
    const/16 v0, 0xa

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mTriggerMode:I

    .line 251
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Thread;->getId()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/android/camera/module/BaseModule;->mMainThreadId:J

    .line 252
    new-instance v0, Lcom/android/camera/preferences/SettingsOverrider;

    invoke-direct {v0}, Lcom/android/camera/preferences/SettingsOverrider;-><init>()V

    iput-object v0, p0, Lcom/android/camera/module/BaseModule;->mSettingsOverrider:Lcom/android/camera/preferences/SettingsOverrider;

    .line 253
    return-void
.end method

.method static synthetic access$002(Lcom/android/camera/module/BaseModule;Lio/reactivex/ObservableEmitter;)Lio/reactivex/ObservableEmitter;
    .locals 0

    .line 86
    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadEmitter:Lio/reactivex/ObservableEmitter;

    return-object p1
.end method

.method private detectLensDirty(Landroid/hardware/camera2/CaptureResult;)V
    .locals 4

    .line 1213
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    if-nez v0, :cond_0

    .line 1214
    return-void

    .line 1217
    :cond_0
    invoke-static {p1}, Lcom/android/camera2/CaptureResultParser;->isLensDirtyDetected(Landroid/hardware/camera2/CaptureResult;)Z

    move-result p1

    if-nez p1, :cond_1

    .line 1218
    return-void

    .line 1221
    :cond_1
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {p1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 1222
    const/4 p1, 0x0

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    .line 1225
    invoke-static {}, Lio/reactivex/Completable;->complete()Lio/reactivex/Completable;

    move-result-object p1

    sget-object v0, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 1226
    invoke-virtual {p1, v0}, Lio/reactivex/Completable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Completable;

    move-result-object p1

    new-instance v0, Lcom/android/camera/module/loader/ActionUpdateLensDirtyDetect;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/android/camera/module/loader/ActionUpdateLensDirtyDetect;-><init>(Lcom/android/camera/module/BaseModule;Z)V

    .line 1227
    invoke-virtual {p1, v0}, Lio/reactivex/Completable;->doOnComplete(Lio/reactivex/functions/Action;)Lio/reactivex/Completable;

    move-result-object p1

    .line 1228
    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    .line 1231
    invoke-static {}, Lcom/android/camera/CameraSettings;->shouldShowLensDirtyDetectHint()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 1232
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->showLensDirtyDetectedHint()V

    .line 1233
    invoke-static {}, Lio/reactivex/Completable;->complete()Lio/reactivex/Completable;

    move-result-object p1

    const-wide/16 v0, 0x1f40

    sget-object v2, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    sget-object v3, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 1234
    invoke-virtual {p1, v0, v1, v2, v3}, Lio/reactivex/Completable;->delay(JLjava/util/concurrent/TimeUnit;Lio/reactivex/Scheduler;)Lio/reactivex/Completable;

    move-result-object p1

    new-instance v0, Lcom/android/camera/module/loader/ActionHideLensDirtyDetectHint;

    invoke-direct {v0, p0}, Lcom/android/camera/module/loader/ActionHideLensDirtyDetectHint;-><init>(Lcom/android/camera/module/BaseModule;)V

    .line 1235
    invoke-virtual {p1, v0}, Lio/reactivex/Completable;->doOnComplete(Lio/reactivex/functions/Action;)Lio/reactivex/Completable;

    move-result-object p1

    .line 1236
    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    move-result-object p1

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectHintDisposable:Lio/reactivex/disposables/Disposable;

    .line 1240
    :cond_2
    return-void
.end method

.method protected static getColorEffectKey()Ljava/lang/String;
    .locals 1

    .line 551
    invoke-static {}, Lcom/mi/config/b;->gi()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 552
    const-string v0, "pref_camera_shader_coloreffect_key"

    goto :goto_0

    .line 553
    :cond_0
    const-string v0, "pref_camera_coloreffect_key"

    .line 551
    :goto_0
    return-object v0
.end method

.method public static getJpegQuality(Z)I
    .locals 0

    .line 1427
    invoke-static {p0}, Lcom/android/camera/CameraSettings;->getJpegQuality(Z)Ljava/lang/String;

    move-result-object p0

    .line 1428
    invoke-static {p0}, Lcom/android/camera/JpegEncodingQualityMappings;->getQualityNumber(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public static getPreferencesLocalId(II)I
    .locals 0

    .line 870
    add-int/2addr p0, p1

    return p0
.end method

.method private getTapableRectWithEdgeSlop()Landroid/graphics/Rect;
    .locals 4

    .line 933
    new-instance v0, Landroid/graphics/Rect;

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    invoke-direct {v0, v1}, Landroid/graphics/Rect;-><init>(Landroid/graphics/Rect;)V

    .line 934
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isVideoRecording()Z

    move-result v1

    if-nez v1, :cond_2

    .line 935
    iget v1, p0, Lcom/android/camera/module/BaseModule;->mUIStyle:I

    const/4 v2, 0x1

    if-eq v1, v2, :cond_1

    const/4 v2, 0x3

    if-eq v1, v2, :cond_0

    .line 939
    iget v1, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    const/16 v2, 0xa5

    if-ne v1, v2, :cond_2

    .line 940
    sget v1, Lcom/android/camera/Util;->sWindowHeight:I

    sget v2, Lcom/android/camera/CameraSettings;->BOTTOM_CONTROL_HEIGHT:I

    sub-int/2addr v1, v2

    .line 941
    sget v2, Lcom/android/camera/Util;->sWindowWidth:I

    int-to-float v2, v2

    const/high16 v3, 0x3f400000    # 0.75f

    div-float/2addr v2, v3

    float-to-int v2, v2

    sget v3, Lcom/android/camera/Util;->sWindowWidth:I

    sub-int/2addr v2, v3

    div-int/lit8 v2, v2, 0x2

    .line 942
    sget v3, Lcom/android/camera/Util;->sWindowWidth:I

    sub-int/2addr v1, v3

    sub-int/2addr v1, v2

    .line 945
    iput v1, v0, Landroid/graphics/Rect;->top:I

    .line 947
    iget v1, v0, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->bottom:I

    .line 948
    goto :goto_0

    .line 964
    :cond_0
    iget v1, v0, Landroid/graphics/Rect;->bottom:I

    sget v2, Lcom/android/camera/CameraSettings;->BOTTOM_CONTROL_HEIGHT:I

    sub-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->bottom:I

    goto :goto_0

    .line 952
    :cond_1
    iget v1, v0, Landroid/graphics/Rect;->bottom:I

    sget v2, Lcom/android/camera/CameraSettings;->BOTTOM_CONTROL_HEIGHT:I

    sget v3, Lcom/android/camera/Util;->sNavigationBarHeight:I

    sub-int/2addr v2, v3

    sub-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->bottom:I

    .line 956
    sget-boolean v1, Lcom/android/camera/Util;->isLongRatioScreen:Z

    if-eqz v1, :cond_2

    sget-boolean v1, Lcom/android/camera/Util;->isNotchDevice:Z

    if-nez v1, :cond_2

    .line 957
    sget v1, Lcom/android/camera/Util;->sWindowWidth:I

    mul-int/lit8 v1, v1, 0x10

    int-to-float v1, v1

    const/high16 v2, 0x41100000    # 9.0f

    div-float/2addr v1, v2

    float-to-int v1, v1

    .line 958
    sget v2, Lcom/android/camera/Util;->sWindowHeight:I

    sub-int/2addr v2, v1

    sget v1, Lcom/android/camera/Util;->sNavigationBarHeight:I

    sub-int/2addr v2, v1

    .line 959
    iget v1, v0, Landroid/graphics/Rect;->bottom:I

    iget v3, v0, Landroid/graphics/Rect;->top:I

    sub-int/2addr v3, v2

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v2

    add-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->bottom:I

    .line 960
    nop

    .line 968
    :cond_2
    :goto_0
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v1}, Lcom/android/camera/Util;->calculateDefaultPreviewEdgeSlop(Landroid/content/Context;)I

    move-result v1

    .line 969
    const-string v2, "camera_touch_edge_slop"

    const/4 v3, -0x1

    invoke-static {v2, v3}, Landroid/os/SystemProperties;->getInt(Ljava/lang/String;I)I

    move-result v2

    .line 971
    if-ne v2, v3, :cond_3

    .line 972
    goto :goto_1

    .line 974
    :cond_3
    int-to-float v1, v2

    invoke-static {v1}, Lcom/android/camera/Util;->dpToPixel(F)I

    move-result v1

    .line 976
    :goto_1
    invoke-virtual {v0, v1, v1}, Landroid/graphics/Rect;->inset(II)V

    .line 977
    return-object v0
.end method

.method private initializeZoom()V
    .locals 3

    .line 808
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isZoomSupported()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mZoomSupported:Z

    .line 809
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mZoomSupported:Z

    if-eqz v0, :cond_0

    .line 810
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getMaxZoomRatio()F

    move-result v0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    .line 811
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "zoomMaxRatio: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 813
    :cond_0
    return-void
.end method

.method private onZoomValueChanged(F)Z
    .locals 1

    .line 981
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/android/camera/module/BaseModule;->onZoomValueChanged(FZ)Z

    move-result p1

    return p1
.end method

.method private onZoomValueChanged(FZ)Z
    .locals 3

    .line 985
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result p2

    const/4 v0, 0x0

    if-nez p2, :cond_0

    .line 986
    return v0

    .line 988
    :cond_0
    const/high16 p2, 0x3f800000    # 1.0f

    cmpg-float v1, p1, p2

    if-gez v1, :cond_1

    .line 989
    nop

    .line 994
    move p1, p2

    goto :goto_0

    .line 990
    :cond_1
    iget v1, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    cmpl-float v1, p1, v1

    if-lez v1, :cond_2

    .line 991
    iget p1, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    .line 994
    :cond_2
    :goto_0
    iget v1, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    cmpl-float v1, v1, p1

    if-nez v1, :cond_3

    .line 995
    return v0

    .line 998
    :cond_3
    iget v1, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    cmpl-float v1, v1, p2

    const/4 v2, 0x1

    if-eqz v1, :cond_5

    cmpl-float p2, p1, p2

    if-nez p2, :cond_4

    goto :goto_1

    .line 1001
    :cond_4
    move p2, v0

    goto :goto_2

    .line 998
    :cond_5
    :goto_1
    nop

    .line 1001
    move p2, v2

    :goto_2
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getModuleIndex()I

    move-result v1

    .line 1002
    invoke-static {v1}, Lcom/android/camera/CameraSettings;->isUltraWideConfigOpen(I)Z

    move-result v1

    if-eqz v1, :cond_6

    .line 1003
    const v1, 0x40066666    # 2.1f

    cmpg-float v1, p1, v1

    if-gez v1, :cond_7

    .line 1004
    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->setZoomValue(F)V

    goto :goto_3

    .line 1007
    :cond_6
    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->setZoomValue(F)V

    .line 1009
    :cond_7
    :goto_3
    if-eqz p2, :cond_8

    .line 1011
    const/4 p2, 0x5

    new-array p2, p2, [I

    fill-array-data p2, :array_0

    invoke-virtual {p0, p2}, Lcom/android/camera/module/BaseModule;->updatePreferenceTrampoline([I)V

    .line 1024
    :cond_8
    new-array p2, v2, [I

    const/16 v1, 0x18

    aput v1, p2, v0

    invoke-virtual {p0, p2}, Lcom/android/camera/module/BaseModule;->updatePreferenceInWorkThread([I)V

    .line 1028
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p2

    const/16 v0, 0xb6

    .line 1029
    invoke-virtual {p2, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p2

    check-cast p2, Lcom/android/camera/protocol/ModeProtocol$DualController;

    .line 1030
    if-eqz p2, :cond_9

    invoke-interface {p2}, Lcom/android/camera/protocol/ModeProtocol$DualController;->isZoomVisible()Z

    move-result v0

    if-eqz v0, :cond_9

    .line 1031
    invoke-interface {p2}, Lcom/android/camera/protocol/ModeProtocol$DualController;->updateZoomValue()V

    goto :goto_4

    .line 1033
    :cond_9
    const/4 p2, 0x2

    invoke-virtual {p0, p2}, Lcom/android/camera/module/BaseModule;->updateStatusBar(I)V

    .line 1036
    :goto_4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object p2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "zoom="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p2, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1037
    return v2

    nop

    :array_0
    .array-data 4
        0xb
        0x22
        0x2a
        0x1e
        0x14
    .end array-data
.end method

.method private setCreated(Z)V
    .locals 1

    .line 289
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsCreated:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 290
    return-void
.end method

.method private setIgnoreTouchEvent(Z)V
    .locals 0

    .line 505
    iput-boolean p1, p0, Lcom/android/camera/module/BaseModule;->mIgnoreTouchEvent:Z

    .line 506
    return-void
.end method

.method private showDebug(Landroid/hardware/camera2/CaptureResult;Z)V
    .locals 4

    .line 1243
    invoke-static {}, Lcom/android/camera/Util;->isShowAfRegionView()Z

    move-result v0

    if-eqz v0, :cond_0

    if-eqz p2, :cond_0

    .line 1244
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    sget-object v1, Landroid/hardware/camera2/CaptureResult;->CONTROL_AF_REGIONS:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p1, v1}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/hardware/camera2/params/MeteringRectangle;

    .line 1245
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getActiveArraySize()Landroid/graphics/Rect;

    move-result-object v2

    iget v3, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    .line 1244
    invoke-interface {v0, v1, v2, v3}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setAfRegionView([Landroid/hardware/camera2/params/MeteringRectangle;Landroid/graphics/Rect;F)V

    .line 1247
    :cond_0
    invoke-static {}, Lcom/android/camera/Util;->isShowDebugInfoView()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1248
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    new-instance v1, Lcom/android/camera/module/BaseModule$3;

    invoke-direct {v1, p0, p2, p1}, Lcom/android/camera/module/BaseModule$3;-><init>(Lcom/android/camera/module/BaseModule;ZLandroid/hardware/camera2/CaptureResult;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 1259
    :cond_1
    return-void
.end method


# virtual methods
.method public bridge synthetic accept(Ljava/lang/Object;)V
    .locals 0
    .param p1    # Ljava/lang/Object;
        .annotation build Lcom/android/camera/constant/UpdateConstant$UpdateType;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 85
    check-cast p1, [I

    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->accept([I)V

    return-void
.end method

.method public accept([I)V
    .locals 4
    .param p1    # [I
        .annotation build Lcom/android/camera/constant/UpdateConstant$UpdateType;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .line 1596
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->isDisposed()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1597
    return-void

    .line 1600
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_1

    .line 1601
    return-void

    .line 1604
    :cond_1
    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->consumePreference([I)V

    .line 1605
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/CameraScreenNail;->getSurfaceCreatedTimestamp()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/android/camera/module/BaseModule;->mSurfaceCreatedTimestamp:J

    cmp-long p1, v0, v2

    if-nez p1, :cond_2

    .line 1606
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->resumePreview()V

    .line 1608
    :cond_2
    return-void
.end method

.method protected addZoom(F)Z
    .locals 1

    .line 826
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    add-float/2addr v0, p1

    invoke-direct {p0, v0}, Lcom/android/camera/module/BaseModule;->onZoomValueChanged(F)Z

    move-result p1

    return p1
.end method

.method public canIgnoreFocusChanged()Z
    .locals 1

    .line 1130
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mIgnoreFocusChanged:Z

    return v0
.end method

.method protected changeConflictPreference()V
    .locals 4

    .line 1266
    invoke-static {}, Lcom/android/camera/CameraSettings;->isStereoModeOn()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1267
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getSupportedSettingKeys()Ljava/util/List;

    move-result-object v0

    .line 1268
    if-eqz v0, :cond_2

    .line 1269
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/data/data/config/DataItemConfig;->editor()Lcom/android/camera/data/provider/DataProvider$ProviderEditor;

    move-result-object v1

    .line 1270
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 1271
    invoke-static {v2}, Lcom/android/camera/CameraSettings;->isSwitchOn(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 1272
    invoke-interface {v1, v2}, Lcom/android/camera/data/provider/DataProvider$ProviderEditor;->remove(Ljava/lang/String;)Lcom/android/camera/data/provider/DataProvider$ProviderEditor;

    .line 1274
    :cond_0
    goto :goto_0

    .line 1275
    :cond_1
    invoke-interface {v1}, Lcom/android/camera/data/provider/DataProvider$ProviderEditor;->apply()V

    .line 1278
    :cond_2
    return-void
.end method

.method public checkActivityOrientation()V
    .locals 2

    .line 695
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-eqz v0, :cond_0

    iget v0, p0, Lcom/android/camera/module/BaseModule;->mDisplayRotation:I

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v1}, Lcom/android/camera/Util;->getDisplayRotation(Landroid/app/Activity;)I

    move-result v1

    if-eq v0, v1, :cond_0

    .line 696
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->checkDisplayOrientation()V

    .line 698
    :cond_0
    return-void
.end method

.method public checkDisplayOrientation()V
    .locals 3

    .line 701
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v0}, Lcom/android/camera/Util;->getDisplayRotation(Landroid/app/Activity;)I

    move-result v0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mDisplayRotation:I

    .line 702
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mDisplayRotation:I

    iget v1, p0, Lcom/android/camera/module/BaseModule;->mBogusCameraId:I

    invoke-static {v0, v1}, Lcom/android/camera/Util;->getDisplayOrientation(II)I

    move-result v0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mCameraDisplayOrientation:I

    .line 703
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "checkDisplayOrientation: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mDisplayRotation:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, " | "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mCameraDisplayOrientation:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 706
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 708
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRotation:I

    invoke-virtual {v0, v1}, Lcom/android/camera/CameraScreenNail;->setDisplayOrientation(I)V

    .line 710
    :cond_0
    return-void
.end method

.method protected varargs consumePreference([I)V
    .locals 0
    .param p1    # [I
        .annotation build Lcom/android/camera/constant/UpdateConstant$UpdateType;
        .end annotation
    .end param

    .line 1464
    return-void
.end method

.method protected currentIsMainThread()Z
    .locals 4

    .line 493
    iget-wide v0, p0, Lcom/android/camera/module/BaseModule;->mMainThreadId:J

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->getId()J

    move-result-wide v2

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public enableCameraControls(Z)V
    .locals 0

    .line 831
    xor-int/lit8 p1, p1, 0x1

    invoke-direct {p0, p1}, Lcom/android/camera/module/BaseModule;->setIgnoreTouchEvent(Z)V

    .line 832
    return-void
.end method

.method public enterMutexMode(I)V
    .locals 0

    .line 844
    return-void
.end method

.method public exitMutexMode(I)V
    .locals 0

    .line 839
    return-void
.end method

.method public findViewById(I)Landroid/view/View;
    .locals 1

    .line 733
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, p1}, Lcom/android/camera/Camera;->findViewById(I)Landroid/view/View;

    move-result-object p1

    return-object p1
.end method

.method protected focusCenter()V
    .locals 0

    .line 1568
    return-void
.end method

.method protected getActiveArraySize()Landroid/graphics/Rect;
    .locals 1

    .line 1584
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getActiveArraySize()Landroid/graphics/Rect;

    move-result-object v0

    .line 1585
    return-object v0
.end method

.method public getActivity()Lcom/android/camera/Camera;
    .locals 1

    .line 242
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    return-object v0
.end method

.method public getActualCameraId()I
    .locals 1

    .line 1409
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    return v0
.end method

.method public getCameraCapabilities()Lcom/android/camera2/CameraCapabilities;
    .locals 1

    .line 1413
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    return-object v0
.end method

.method public getCameraDevice()Lcom/android/camera2/Camera2Proxy;
    .locals 1

    .line 1405
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    return-object v0
.end method

.method protected getCameraRotation()I
    .locals 1

    .line 1110
    const/4 v0, 0x0

    return v0
.end method

.method protected getCameraState()I
    .locals 1

    .line 1481
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mCameraState:I

    return v0
.end method

.method protected getCropRegion()Landroid/graphics/Rect;
    .locals 7

    .line 1571
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getActiveArraySize()Landroid/graphics/Rect;

    move-result-object v0

    .line 1572
    nop

    .line 1573
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    .line 1575
    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    .line 1576
    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    .line 1577
    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v4

    int-to-float v4, v4

    iget v5, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    const/high16 v6, 0x40000000    # 2.0f

    mul-float/2addr v5, v6

    div-float/2addr v4, v5

    float-to-int v4, v4

    .line 1578
    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v0

    int-to-float v0, v0

    iget v5, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    mul-float/2addr v6, v5

    div-float/2addr v0, v6

    float-to-int v0, v0

    .line 1579
    sub-int v5, v2, v4

    sub-int v6, v3, v0

    add-int/2addr v2, v4

    add-int/2addr v3, v0

    invoke-virtual {v1, v5, v6, v2, v3}, Landroid/graphics/Rect;->set(IIII)V

    .line 1580
    return-object v1
.end method

.method protected getJpegThumbnailSize()Lcom/android/camera/CameraSize;
    .locals 5

    .line 1421
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getSupportedThumbnailSizes()Ljava/util/List;

    move-result-object v0

    .line 1422
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mPreviewSize:Lcom/android/camera/CameraSize;

    .line 1423
    invoke-virtual {v1}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v1

    int-to-double v1, v1

    iget-object v3, p0, Lcom/android/camera/module/BaseModule;->mPreviewSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v3}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v3

    int-to-double v3, v3

    div-double/2addr v1, v3

    .line 1422
    invoke-static {v0, v1, v2}, Lcom/android/camera/Util;->getOptimalJpegThumbnailSize(Ljava/util/List;D)Lcom/android/camera/CameraSize;

    move-result-object v0

    return-object v0
.end method

.method public getModuleIndex()I
    .locals 1

    .line 173
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    return v0
.end method

.method public getMutexModePicker()Lcom/android/camera/MutexModeManager;
    .locals 1

    .line 849
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    return-object v0
.end method

.method protected abstract getOperatingMode()I
.end method

.method public getPreviewSize()Lcom/android/camera/CameraSize;
    .locals 1

    .line 1417
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mPreviewSize:Lcom/android/camera/CameraSize;

    return-object v0
.end method

.method public getResources()Landroid/content/res/Resources;
    .locals 1

    .line 741
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    return-object v0
.end method

.method protected getScreenDelay()I
    .locals 1

    .line 1152
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->startFromKeyguard()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1153
    :cond_0
    const v0, 0xea60

    goto :goto_1

    :cond_1
    :goto_0
    const/16 v0, 0x7530

    .line 1152
    :goto_1
    return v0
.end method

.method public getString(I)Ljava/lang/String;
    .locals 1

    .line 737
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public getSupportedSettingKeys()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 515
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    return-object v0
.end method

.method protected getTriggerMode()I
    .locals 1

    .line 1374
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mTriggerMode:I

    return v0
.end method

.method public getUnInterruptableReason()Ljava/lang/String;
    .locals 1

    .line 1682
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUnInterruptableReason:Ljava/lang/String;

    return-object v0
.end method

.method public getWindow()Landroid/view/Window;
    .locals 1

    .line 745
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getWindow()Landroid/view/Window;

    move-result-object v0

    return-object v0
.end method

.method public getZoomMaxRatio()F
    .locals 1

    .line 1057
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    return v0
.end method

.method protected getZoomValue()F
    .locals 1

    .line 1053
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    return v0
.end method

.method protected handlePendingScreenSlide()V
    .locals 3

    .line 1686
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    if-lez v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_0

    .line 1687
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "process pending screen slide: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1688
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    iget v1, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lcom/android/camera/Camera;->handleScreenSlideKeyEvent(ILandroid/view/KeyEvent;)Z

    .line 1689
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    .line 1691
    :cond_0
    return-void
.end method

.method protected handleVolumeKeyEvent(ZZI)Z
    .locals 4

    .line 749
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isAlive()Z

    move-result v0

    const/4 v1, 0x1

    if-nez v0, :cond_0

    .line 750
    return v1

    .line 753
    :cond_0
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isLiveModule()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 754
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    const-string v2, "pref_live_volumekey_function_key"

    const v3, 0x7f090121

    .line 756
    invoke-virtual {p0, v3}, Lcom/android/camera/module/BaseModule;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 754
    invoke-virtual {v0, v2, v3}, Lcom/android/camera/data/data/global/DataItemGlobal;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 757
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getModuleIndex()I

    move-result v0

    invoke-static {v0}, Lcom/android/camera/module/ModuleManager;->isVideoCategory(I)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 758
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    const-string v2, "pref_video_volumekey_function_key"

    const v3, 0x7f090120

    .line 760
    invoke-virtual {p0, v3}, Lcom/android/camera/module/BaseModule;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 758
    invoke-virtual {v0, v2, v3}, Lcom/android/camera/data/data/global/DataItemGlobal;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 762
    :cond_2
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    const-string v2, "pref_camera_volumekey_function_key"

    const v3, 0x7f09011f

    .line 764
    invoke-virtual {p0, v3}, Lcom/android/camera/module/BaseModule;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 762
    invoke-virtual {v0, v2, v3}, Lcom/android/camera/data/data/global/DataItemGlobal;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 767
    :goto_0
    const v2, 0x7f09011c

    invoke-virtual {p0, v2}, Lcom/android/camera/module/BaseModule;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_8

    const v2, 0x7f09011d

    .line 768
    invoke-virtual {p0, v2}, Lcom/android/camera/module/BaseModule;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    goto :goto_2

    .line 771
    :cond_3
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isPanoramaModule()Z

    move-result v2

    if-nez v2, :cond_7

    const v2, 0x7f09011e

    .line 772
    invoke-virtual {p0, v2}, Lcom/android/camera/module/BaseModule;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_7

    .line 775
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isZoomSupported()Z

    move-result v0

    if-eqz v0, :cond_7

    if-eqz p2, :cond_7

    .line 777
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isZoomEnabled()Z

    move-result p2

    if-nez p2, :cond_4

    .line 778
    return v1

    .line 781
    :cond_4
    if-eqz p1, :cond_5

    .line 782
    const p1, 0x3dcccccd    # 0.1f

    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->addZoom(F)Z

    move-result p1

    goto :goto_1

    .line 784
    :cond_5
    const p1, -0x42333333    # -0.1f

    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->addZoom(F)Z

    move-result p1

    .line 786
    :goto_1
    if-nez p3, :cond_6

    if-eqz p1, :cond_6

    .line 787
    const-string p1, "\u97f3\u91cf\u952e"

    invoke-static {p1}, Lcom/android/camera/statistic/CameraStatUtil;->trackZoomAdjusted(Ljava/lang/String;)V

    .line 789
    :cond_6
    return v1

    .line 792
    :cond_7
    const/4 p1, 0x0

    return p1

    .line 769
    :cond_8
    :goto_2
    invoke-virtual {p0, v0, p3, p2}, Lcom/android/camera/module/BaseModule;->performVolumeKeyClicked(Ljava/lang/String;IZ)V

    .line 770
    return v1
.end method

.method protected hasCameraException()Z
    .locals 1

    .line 640
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mCameraDisabled:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mOpenCameraFail:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mCameraHardwareError:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method protected hideTipMessage(I)V
    .locals 2
    .param p1    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param

    .line 1297
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 1298
    const/16 v1, 0xaf

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;

    .line 1299
    if-eqz v0, :cond_1

    if-lez p1, :cond_0

    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;->containTips(I)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 1300
    :cond_0
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;->directlyHideTips()V

    .line 1302
    :cond_1
    return-void
.end method

.method protected initializeCapabilities()V
    .locals 2

    .line 796
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getOperatingMode()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/android/camera2/CameraCapabilities;->setOperatingMode(I)V

    .line 798
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isAELockSupported()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mAeLockSupported:Z

    .line 799
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isAWBLockSupported()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mAwbLockSupported:Z

    .line 800
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isAFRegionSupported()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mFocusAreaSupported:Z

    .line 801
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isAERegionSupported()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mMeteringAreaSupported:Z

    .line 802
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getMaxFaceCount()I

    move-result v0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mMaxFaceCount:I

    .line 803
    invoke-direct {p0}, Lcom/android/camera/module/BaseModule;->initializeZoom()V

    .line 805
    return-void
.end method

.method public isAlive()Z
    .locals 1

    .line 1611
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isCreated()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeparted()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method protected final isBackCamera()Z
    .locals 1

    .line 882
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mBogusCameraId:I

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method protected final isBokehFrontCamera()Z
    .locals 2

    .line 896
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getBokehFrontCameraId()I

    move-result v1

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isCaptureIntent()Z
    .locals 1

    .line 1262
    const/4 v0, 0x0

    return v0
.end method

.method public isCreated()Z
    .locals 1

    .line 298
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsCreated:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    return v0
.end method

.method public isDeparted()Z
    .locals 1

    .line 137
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsDeparted:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    return v0
.end method

.method public isDeviceAlive()Z
    .locals 6

    .line 1651
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isAlive()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1652
    move v0, v2

    goto :goto_0

    .line 1651
    :cond_0
    nop

    .line 1652
    move v0, v1

    :goto_0
    if-nez v0, :cond_5

    .line 1653
    const-string v3, "device: %s module: %s|%s"

    const/4 v4, 0x3

    new-array v4, v4, [Ljava/lang/Object;

    .line 1654
    iget-object v5, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v5, :cond_1

    const-string v5, "valid"

    goto :goto_1

    :cond_1
    const-string v5, "invalid"

    :goto_1
    aput-object v5, v4, v1

    .line 1655
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isCreated()Z

    move-result v1

    if-eqz v1, :cond_2

    const-string v1, "created"

    goto :goto_2

    :cond_2
    const-string v1, "destroyed"

    :goto_2
    aput-object v1, v4, v2

    const/4 v1, 0x2

    .line 1656
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeparted()Z

    move-result v5

    if-eqz v5, :cond_3

    const-string v5, "departed"

    goto :goto_3

    :cond_3
    const-string v5, "alive"

    :goto_3
    aput-object v5, v4, v1

    .line 1653
    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    .line 1657
    sget-boolean v3, Lcom/android/camera/module/BaseModule;->DEBUG:Z

    if-eqz v3, :cond_4

    .line 1658
    new-instance v2, Ljava/lang/RuntimeException;

    invoke-direct {v2, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/lang/RuntimeException;->printStackTrace()V

    goto :goto_4

    .line 1660
    :cond_4
    const-string v3, "BaseModule"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {v2}, Lcom/android/camera/Util;->getCallers(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "|"

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v3, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1663
    :cond_5
    :goto_4
    return v0
.end method

.method protected final isDualCamera()Z
    .locals 2

    .line 886
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getMuxCameraId()I

    move-result v1

    if-eq v0, v1, :cond_1

    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    .line 887
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getBokehCameraId()I

    move-result v1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    .line 886
    :goto_1
    return v0
.end method

.method protected final isDualFrontCamera()Z
    .locals 2

    .line 891
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getMuxFrontCameraId()I

    move-result v1

    if-eq v0, v1, :cond_1

    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    .line 892
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getBokehFrontCameraId()I

    move-result v1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    .line 891
    :goto_1
    return v0
.end method

.method public isFrameAvailable()Z
    .locals 1

    .line 148
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsFrameAvailable:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    return v0
.end method

.method public final isFrontCamera()Z
    .locals 2

    .line 878
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mBogusCameraId:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    return v1
.end method

.method public isIgnoreTouchEvent()Z
    .locals 1

    .line 510
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mIgnoreTouchEvent:Z

    return v0
.end method

.method public isInTapableRect(II)Z
    .locals 1

    .line 911
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    if-nez v0, :cond_0

    .line 912
    const/4 p1, 0x0

    return p1

    .line 915
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/BaseModule;->getTapableRectWithEdgeSlop()Landroid/graphics/Rect;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Landroid/graphics/Rect;->contains(II)Z

    move-result p1

    return p1
.end method

.method public isKeptBitmapTexture()Z
    .locals 1

    .line 1134
    const/4 v0, 0x0

    return v0
.end method

.method public isMeteringAreaOnly()Z
    .locals 1

    .line 1097
    const/4 v0, 0x0

    return v0
.end method

.method public isNeedHapticFeedback()Z
    .locals 1

    .line 1102
    const/4 v0, 0x1

    return v0
.end method

.method protected isNeedMute()Z
    .locals 1

    .line 1077
    const/4 v0, 0x0

    return v0
.end method

.method public isPanoramaDoing()Z
    .locals 1

    .line 1713
    const/4 v0, 0x0

    return v0
.end method

.method public isPortraitMode()Z
    .locals 2

    .line 874
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    const/16 v1, 0xab

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isPostProcessing()Z
    .locals 1

    .line 489
    const/4 v0, 0x0

    return v0
.end method

.method protected isRestoring()Z
    .locals 1

    .line 1165
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mRestoring:Z

    return v0
.end method

.method public isSelectingCapturedResult()Z
    .locals 1

    .line 479
    const/4 v0, 0x0

    return v0
.end method

.method public isShowAeAfLockIndicator()Z
    .locals 1

    .line 1087
    const/4 v0, 0x0

    return v0
.end method

.method public isShowCaptureButton()Z
    .locals 1

    .line 1082
    const/4 v0, 0x0

    return v0
.end method

.method protected isSquareModeChange()Z
    .locals 5

    .line 1281
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isSquareModule()Z

    move-result v0

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    .line 1282
    invoke-virtual {v1}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/CameraScreenNail;->getRenderTargetRatio()I

    move-result v1

    const/4 v2, 0x0

    const/4 v3, 0x1

    const/4 v4, 0x2

    if-ne v1, v4, :cond_0

    move v1, v3

    goto :goto_0

    :cond_0
    move v1, v2

    :goto_0
    if-eq v0, v1, :cond_1

    .line 1281
    move v2, v3

    goto :goto_1

    .line 1282
    :cond_1
    nop

    .line 1281
    :goto_1
    return v2
.end method

.method public isSupportFocusShoot()Z
    .locals 1

    .line 1092
    const/4 v0, 0x0

    return v0
.end method

.method protected final isTextureExpired()Z
    .locals 4

    .line 1702
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getSurfaceCreatedTimestamp()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/android/camera/module/BaseModule;->mSurfaceCreatedTimestamp:J

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isThermalThreshold()Z
    .locals 1

    .line 1708
    const/4 v0, 0x0

    return v0
.end method

.method protected final isUltraWideBackCamera()Z
    .locals 2

    .line 900
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getUltraWideCameraId()I

    move-result v1

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isVideoRecording()Z
    .locals 1

    .line 474
    const/4 v0, 0x0

    return v0
.end method

.method protected isZoomEnabled()Z
    .locals 1

    .line 543
    const/4 v0, 0x1

    return v0
.end method

.method protected isZoomSupported()Z
    .locals 1

    .line 547
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mZoomSupported:Z

    return v0
.end method

.method protected mapTapCoordinate(Ljava/lang/Object;)V
    .locals 2

    .line 921
    instance-of v0, p1, Landroid/graphics/Point;

    if-eqz v0, :cond_0

    .line 922
    check-cast p1, Landroid/graphics/Point;

    iget v0, p1, Landroid/graphics/Point;->x:I

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    sub-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Point;->x:I

    .line 923
    iget v0, p1, Landroid/graphics/Point;->y:I

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    sub-int/2addr v0, v1

    iput v0, p1, Landroid/graphics/Point;->y:I

    goto :goto_0

    .line 924
    :cond_0
    instance-of v0, p1, Landroid/graphics/RectF;

    if-eqz v0, :cond_1

    .line 925
    check-cast p1, Landroid/graphics/RectF;

    iget v0, p1, Landroid/graphics/RectF;->left:F

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    iput v0, p1, Landroid/graphics/RectF;->left:F

    .line 926
    iget v0, p1, Landroid/graphics/RectF;->right:F

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    iput v0, p1, Landroid/graphics/RectF;->right:F

    .line 927
    iget v0, p1, Landroid/graphics/RectF;->top:F

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    iput v0, p1, Landroid/graphics/RectF;->top:F

    .line 928
    iget v0, p1, Landroid/graphics/RectF;->bottom:F

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    int-to-float v1, v1

    sub-float/2addr v0, v1

    iput v0, p1, Landroid/graphics/RectF;->bottom:F

    .line 930
    :cond_1
    :goto_0
    return-void
.end method

.method public notifyDualZoom(Z)V
    .locals 1

    .line 414
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-nez v0, :cond_0

    .line 415
    return-void

    .line 418
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setOpticalZoomToTele(Z)V

    .line 419
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->resumePreview()V

    .line 420
    return-void
.end method

.method public notifyError()V
    .locals 1

    .line 1062
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mCameraHardwareError:Z

    .line 1063
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/BaseModule;->setCameraState(I)V

    .line 1068
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1069
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->onCameraException()V

    goto :goto_0

    .line 1071
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V

    .line 1073
    :goto_0
    return-void
.end method

.method public notifyZooming(Z)V
    .locals 0

    .line 409
    iput-boolean p1, p0, Lcom/android/camera/module/BaseModule;->isZooming:Z

    .line 410
    return-void
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 0

    .line 671
    return-void
.end method

.method public onBackPressed()Z
    .locals 1

    .line 469
    const/4 v0, 0x0

    return v0
.end method

.method public onBroadcastReceived(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 3
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 1618
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_5

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isFinishing()Z

    move-result v0

    if-nez v0, :cond_5

    if-nez p2, :cond_0

    goto/16 :goto_1

    .line 1621
    :cond_0
    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object p2

    .line 1622
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onReceive: action="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 1623
    const-string v0, "android.intent.action.MEDIA_MOUNTED"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1624
    const-string p2, "BaseModule"

    const-string v0, "SD card available"

    invoke-static {p2, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1625
    invoke-static {p1}, Lcom/android/camera/storage/Storage;->initStorage(Landroid/content/Context;)V

    .line 1626
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getScreenHint()Lcom/android/camera/ui/ScreenHint;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/ui/ScreenHint;->updateHint()V

    goto :goto_0

    .line 1627
    :cond_1
    const-string p1, "android.intent.action.MEDIA_UNMOUNTED"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 1628
    const-string p1, "BaseModule"

    const-string p2, "SD card unavailable"

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1629
    invoke-static {}, Lcom/android/camera/FileCompat;->updateSDPath()V

    .line 1630
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getScreenHint()Lcom/android/camera/ui/ScreenHint;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/ui/ScreenHint;->updateHint()V

    .line 1631
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getThumbnailUpdater()Lcom/android/camera/ThumbnailUpdater;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/ThumbnailUpdater;->getLastThumbnail()V

    goto :goto_0

    .line 1632
    :cond_2
    const-string p1, "android.intent.action.MEDIA_SCANNER_STARTED"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 1633
    const-string p1, "BaseModule"

    const-string p2, "media scanner started"

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 1634
    :cond_3
    const-string p1, "android.intent.action.MEDIA_SCANNER_FINISHED"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 1635
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getThumbnailUpdater()Lcom/android/camera/ThumbnailUpdater;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/ThumbnailUpdater;->getLastThumbnail()V

    .line 1636
    const-string p1, "BaseModule"

    const-string p2, "media scanner finisheded"

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1638
    :cond_4
    :goto_0
    return-void

    .line 1619
    :cond_5
    :goto_1
    return-void
.end method

.method protected onCameraException()V
    .locals 5

    .line 585
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->currentIsMainThread()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 586
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onCameraException: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, " | "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mBogusCameraId:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 606
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mOpenCameraFail:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mCameraHardwareError:Z

    if-eqz v0, :cond_4

    .line 607
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mOpenCameraFail:Z

    if-eqz v0, :cond_4

    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    .line 608
    invoke-virtual {v0}, Lcom/android/camera/Camera;->couldShowErrorDialog()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 612
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    .line 613
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v1}, Lcom/android/camera/Util;->isInVideoCall(Landroid/app/Activity;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 614
    const v1, 0x7f0901b6

    goto :goto_0

    .line 615
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraSettings;->updateOpenCameraFailTimes()J

    move-result-wide v1

    const-wide/16 v3, 0x1

    cmp-long v1, v1, v3

    if-lez v1, :cond_3

    .line 616
    const v1, 0x7f090007

    goto :goto_0

    .line 617
    :cond_3
    const v1, 0x7f090006

    .line 612
    :goto_0
    invoke-static {v0, v1}, Lcom/android/camera/Util;->showErrorAndFinish(Landroid/app/Activity;I)V

    .line 618
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->showErrorDialog()V

    .line 622
    :cond_4
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mCameraDisabled:Z

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->couldShowErrorDialog()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 625
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    const v1, 0x7f090008

    invoke-static {v0, v1}, Lcom/android/camera/Util;->showErrorAndFinish(Landroid/app/Activity;I)V

    .line 627
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->showErrorDialog()V

    goto :goto_1

    .line 630
    :cond_5
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->sendOpenFailMessage()V

    .line 632
    :cond_6
    :goto_1
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/BaseModule;->enableCameraControls(Z)V

    .line 633
    return-void
.end method

.method protected onCameraOpened()V
    .locals 2

    .line 1191
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->initializeCapabilities()V

    .line 1196
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p0}, Lcom/android/camera2/Camera2Proxy;->setMetaDataCallback(Lcom/android/camera2/Camera2Proxy$CameraMetaDataCallback;)V

    .line 1197
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isScreenSlideOff()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1198
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setAELock(Z)V

    .line 1200
    :cond_0
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/BaseModule;->updateLensDirtyDetect(Z)V

    .line 1201
    return-void
.end method

.method public onCreate(II)V
    .locals 4

    .line 257
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onCreate moduleIndex->"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", cameraId->"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 258
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    .line 259
    iput p2, p0, Lcom/android/camera/module/BaseModule;->mBogusCameraId:I

    .line 260
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p1

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mContentResolver:Landroid/content/ContentResolver;

    .line 261
    new-instance p1, Lcom/android/camera/CameraErrorCallbackImpl;

    iget-object p2, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-direct {p1, p2}, Lcom/android/camera/CameraErrorCallbackImpl;-><init>(Lcom/android/camera/ActivityBase;)V

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mErrorCallback:Lcom/android/camera/CameraErrorCallbackImpl;

    .line 263
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    .line 264
    const/16 p2, 0xa6

    invoke-virtual {p1, p2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    .line 266
    new-instance p1, Lcom/android/camera/MutexModeManager;

    invoke-direct {p1, p0}, Lcom/android/camera/MutexModeManager;-><init>(Lcom/android/camera/MutexModeManager$MutexCallBack;)V

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    .line 268
    new-instance p1, Lcom/android/camera/module/BaseModule$1;

    invoke-direct {p1, p0}, Lcom/android/camera/module/BaseModule$1;-><init>(Lcom/android/camera/module/BaseModule;)V

    invoke-static {p1}, Lio/reactivex/Observable;->create(Lio/reactivex/ObservableOnSubscribe;)Lio/reactivex/Observable;

    move-result-object p1

    sget-object p2, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 273
    invoke-virtual {p1, p2}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p1

    invoke-virtual {p1, p0}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    move-result-object p1

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    .line 275
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object p1

    invoke-virtual {p1}, Lcom/mi/config/a;->fJ()Z

    move-result p1

    const/4 p2, 0x1

    if-eqz p1, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->isLensDirtyDetectEnabled()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 276
    invoke-static {}, Lio/reactivex/Completable;->complete()Lio/reactivex/Completable;

    move-result-object p1

    const-wide/16 v0, 0x3a98

    sget-object v2, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    sget-object v3, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 277
    invoke-virtual {p1, v0, v1, v2, v3}, Lio/reactivex/Completable;->delay(JLjava/util/concurrent/TimeUnit;Lio/reactivex/Scheduler;)Lio/reactivex/Completable;

    move-result-object p1

    new-instance v0, Lcom/android/camera/module/loader/ActionUpdateLensDirtyDetect;

    invoke-direct {v0, p0, p2}, Lcom/android/camera/module/loader/ActionUpdateLensDirtyDetect;-><init>(Lcom/android/camera/module/BaseModule;Z)V

    .line 278
    invoke-virtual {p1, v0}, Lio/reactivex/Completable;->doOnComplete(Lio/reactivex/functions/Action;)Lio/reactivex/Completable;

    move-result-object p1

    .line 279
    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    move-result-object p1

    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    .line 282
    :cond_0
    invoke-direct {p0, p2}, Lcom/android/camera/module/BaseModule;->setCreated(Z)V

    .line 284
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mIsDeparted:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 285
    invoke-static {}, Lcom/android/camera/Util;->isShowPreviewDebugInfo()Z

    move-result p1

    iput-boolean p1, p0, Lcom/android/camera/module/BaseModule;->isShowPreviewDebugInfo:Z

    .line 286
    return-void
.end method

.method public onDestroy()V
    .locals 2
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 308
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_0

    .line 309
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera/SensorStateManager;->setSensorStateListener(Lcom/android/camera/SensorStateManager$SensorStateListener;)V

    .line 311
    :cond_0
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/android/camera/module/BaseModule;->setCreated(Z)V

    .line 312
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/storage/ImageSaver;->onModuleDestroy()V

    .line 313
    const-string v0, "BaseModule"

    const-string v1, "onDestroy"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 314
    return-void
.end method

.method public onEvChanged(II)V
    .locals 1

    .line 363
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 364
    return-void

    .line 366
    :cond_0
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mEvValue:I

    .line 367
    iput p2, p0, Lcom/android/camera/module/BaseModule;->mEvState:I

    .line 369
    const/4 v0, 0x1

    if-ne p2, v0, :cond_1

    .line 370
    invoke-static {p1}, Lcom/android/camera/CameraSettings;->writeExposure(I)V

    .line 371
    invoke-virtual {p0, v0}, Lcom/android/camera/module/BaseModule;->updateStatusBar(I)V

    .line 374
    :cond_1
    new-array p1, v0, [I

    const/4 p2, 0x0

    const/16 v0, 0xc

    aput v0, p1, p2

    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->updatePreferenceInWorkThread([I)V

    .line 376
    return-void
.end method

.method public onGestureTrack(Landroid/graphics/RectF;Z)Z
    .locals 0

    .line 1126
    const/4 p1, 0x1

    return p1
.end method

.method public onHostStopAndNotifyActionStop()V
    .locals 0

    .line 1643
    return-void
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 4

    .line 318
    const/16 v0, 0x2bd

    const/4 v1, 0x0

    if-eq p1, v0, :cond_1

    const/16 v2, 0x2bc

    if-ne p1, v2, :cond_0

    goto :goto_0

    .line 336
    :cond_0
    return v1

    .line 319
    :cond_1
    :goto_0
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isUnInterruptable()Z

    move-result v2

    const/4 v3, 0x1

    if-eqz v2, :cond_3

    .line 320
    if-ne p1, v0, :cond_2

    iget-object p2, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    .line 321
    invoke-virtual {p2}, Lcom/android/camera/Camera;->getCameraIntentManager()Lcom/android/camera/CameraIntentManager;

    move-result-object p2

    invoke-virtual {p2}, Lcom/android/camera/CameraIntentManager;->isFromScreenSlide()Ljava/lang/Boolean;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-eqz p2, :cond_2

    iget-object p2, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p2}, Lcom/android/camera/Camera;->isModeSwitched()Z

    move-result p2

    if-nez p2, :cond_2

    .line 322
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1, v1}, Lcom/android/camera/Camera;->moveTaskToBack(Z)Z

    .line 323
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    const p2, 0x7f050002

    const v0, 0x7f050003

    invoke-virtual {p1, p2, v0}, Lcom/android/camera/Camera;->overridePendingTransition(II)V

    .line 324
    return v3

    .line 326
    :cond_2
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    .line 327
    const-string p2, "BaseModule"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "pending screen slide: "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", reason: "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getUnInterruptableReason()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p2, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 328
    return v1

    .line 331
    :cond_3
    iput v1, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    .line 332
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, p1, p2}, Lcom/android/camera/Camera;->handleScreenSlideKeyEvent(ILandroid/view/KeyEvent;)Z

    .line 333
    return v3
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 0

    .line 498
    const/16 p2, 0x52

    if-ne p1, p2, :cond_0

    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->startFromSecureKeyguard()Z

    move-result p1

    if-nez p1, :cond_0

    .line 499
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->openSettingActivity()V

    .line 501
    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method public onLongPress(II)V
    .locals 0

    .line 651
    return-void
.end method

.method public onNewIntent()V
    .locals 0

    .line 303
    return-void
.end method

.method public onNewUriArrived(Landroid/net/Uri;Ljava/lang/String;)V
    .locals 0

    .line 1648
    return-void
.end method

.method public onOrientationChanged(II)V
    .locals 2

    .line 678
    const/4 v0, -0x1

    if-ne p1, v0, :cond_0

    .line 679
    return-void

    .line 682
    :cond_0
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mOrientation:I

    .line 683
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object p1

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    iget v1, p0, Lcom/android/camera/module/BaseModule;->mOrientation:I

    .line 684
    invoke-static {v0, v1}, Lcom/android/camera/Util;->getShootOrientation(Landroid/app/Activity;I)I

    move-result v0

    .line 683
    invoke-virtual {p1, v0}, Lcom/android/camera/effect/EffectController;->setOrientation(I)V

    .line 685
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->checkActivityOrientation()V

    .line 689
    iget p1, p0, Lcom/android/camera/module/BaseModule;->mOrientationCompensation:I

    if-eq p1, p2, :cond_1

    .line 690
    iput p2, p0, Lcom/android/camera/module/BaseModule;->mOrientationCompensation:I

    .line 692
    :cond_1
    return-void
.end method

.method public onPause()V
    .locals 2
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 445
    const-string v0, "BaseModule"

    const-string v1, "onPause"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 446
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mPaused:Z

    .line 447
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/camera/module/BaseModule;->mPendingScreenSlideKeyCode:I

    .line 448
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 449
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_0

    .line 450
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 452
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectHintDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_1

    .line 453
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectHintDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 455
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_2

    .line 456
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->hideLensDirtyDetectedHint()V

    .line 458
    :cond_2
    return-void
.end method

.method public onPreviewLayoutChanged(Landroid/graphics/Rect;)V
    .locals 0

    .line 1473
    return-void
.end method

.method public onPreviewMetaDataUpdate(Landroid/hardware/camera2/CaptureResult;)V
    .locals 2
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 1206
    invoke-direct {p0, p1}, Lcom/android/camera/module/BaseModule;->detectLensDirty(Landroid/hardware/camera2/CaptureResult;)V

    .line 1207
    iget-boolean v0, p0, Lcom/android/camera/module/BaseModule;->isShowPreviewDebugInfo:Z

    if-eqz v0, :cond_1

    .line 1208
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_0

    iget v0, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    const/16 v1, 0xa6

    if-eq v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-direct {p0, p1, v0}, Lcom/android/camera/module/BaseModule;->showDebug(Landroid/hardware/camera2/CaptureResult;Z)V

    .line 1210
    :cond_1
    return-void
.end method

.method public onPreviewPixelsRead([BII)V
    .locals 0

    .line 656
    return-void
.end method

.method public onPreviewSizeChanged(II)V
    .locals 0

    .line 1477
    return-void
.end method

.method public onResume()V
    .locals 2
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 438
    const-string v0, "BaseModule"

    const-string v1, "onResume"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 439
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/BaseModule;->mPaused:Z

    .line 440
    return-void
.end method

.method public onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2

    .line 1157
    const-string v0, "killed-moduleIndex"

    invoke-static {}, Lcom/android/camera/module/ModuleManager;->getActiveModuleIndex()I

    move-result v1

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    .line 1158
    return-void
.end method

.method public onScale(FFF)Z
    .locals 0

    .line 526
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isZoomEnabled()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 527
    iget p1, p0, Lcom/android/camera/module/BaseModule;->mZoomScaled:F

    const/high16 p2, 0x3f800000    # 1.0f

    sub-float/2addr p3, p2

    const/high16 p2, 0x40800000    # 4.0f

    div-float/2addr p3, p2

    add-float/2addr p1, p3

    iput p1, p0, Lcom/android/camera/module/BaseModule;->mZoomScaled:F

    .line 528
    iget p1, p0, Lcom/android/camera/module/BaseModule;->mZoomScaled:F

    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->scaleZoomValue(F)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 529
    const/4 p1, 0x1

    iput p1, p0, Lcom/android/camera/module/BaseModule;->mZoomScaled:F

    .line 530
    const/4 p1, 0x1

    return p1

    .line 534
    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method public onScaleBegin(FF)Z
    .locals 0

    .line 520
    const/4 p1, 0x1

    iput p1, p0, Lcom/android/camera/module/BaseModule;->mZoomScaled:F

    .line 521
    const/4 p1, 0x1

    return p1
.end method

.method public onScaleEnd()V
    .locals 0

    .line 540
    return-void
.end method

.method public onSharedPreferenceChanged()V
    .locals 0

    .line 724
    return-void
.end method

.method public onSingleTapUp(II)V
    .locals 0

    .line 1123
    return-void
.end method

.method public onStop()V
    .locals 0

    .line 720
    return-void
.end method

.method public onSurfaceTextureReleased()V
    .locals 0

    .line 666
    return-void
.end method

.method public onSurfaceTextureUpdated(Lcom/android/camera/effect/draw_mode/DrawExtTexAttribute;)V
    .locals 0

    .line 661
    return-void
.end method

.method public onSwitchUltraWide()V
    .locals 3

    .line 1718
    invoke-static {}, Lcom/android/camera/data/DataRepository;->provider()Lcom/android/camera/data/provider/DataProvider;

    move-result-object v0

    invoke-interface {v0}, Lcom/android/camera/data/provider/DataProvider;->dataGlobal()Lcom/android/camera/data/provider/DataProvider$ProviderEvent;

    move-result-object v0

    check-cast v0, Lcom/android/camera/data/data/global/DataItemGlobal;

    .line 1719
    invoke-virtual {v0}, Lcom/android/camera/data/data/global/DataItemGlobal;->getCurrentMode()I

    move-result v1

    .line 1720
    const/4 v2, 0x0

    invoke-virtual {v0, v2}, Lcom/android/camera/data/data/global/DataItemGlobal;->setCameraId(I)V

    .line 1721
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v1}, Lcom/android/camera/module/loader/StartControl;->create(I)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1722
    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setStartDelay(I)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1723
    const/4 v2, 0x5

    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setResetType(I)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1724
    const/4 v2, 0x2

    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setViewConfigType(I)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1725
    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setNeedBlurAnimation(Z)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1721
    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->onModeSelected(Lcom/android/camera/module/loader/StartControl;)V

    .line 1726
    return-void
.end method

.method public onUserInteraction()V
    .locals 0

    .line 715
    return-void
.end method

.method public onWindowFocusChanged(Z)V
    .locals 0

    .line 462
    if-eqz p1, :cond_0

    .line 463
    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/android/camera/module/BaseModule;->mIgnoreFocusChanged:Z

    .line 465
    :cond_0
    return-void
.end method

.method public onZoomRatioChanged(FZ)V
    .locals 0

    .line 424
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/BaseModule;->onZoomValueChanged(FZ)Z

    .line 425
    return-void
.end method

.method public onZoomSwitchCamera()V
    .locals 1

    .line 429
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isAlive()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->isSupportedOpticalZoom()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 430
    invoke-static {}, Lcom/android/camera/CameraSettings;->isSwitchCameraZoomMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 431
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->disableSwitchAnimationOnce()V

    .line 433
    :cond_0
    return-void
.end method

.method protected openCamera()V
    .locals 0

    .line 178
    return-void
.end method

.method protected abstract openSettingActivity()V
.end method

.method protected performVolumeKeyClicked(Ljava/lang/String;IZ)V
    .locals 0

    .line 1115
    return-void
.end method

.method protected final playCameraSound(I)V
    .locals 1

    .line 854
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 858
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isNeedMute()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 859
    return-void

    .line 862
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->isCameraSoundOpen()Z

    move-result v0

    if-nez v0, :cond_2

    .line 863
    return-void

    .line 866
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, p1}, Lcom/android/camera/Camera;->playCameraSound(I)V

    .line 867
    return-void

    .line 855
    :cond_3
    :goto_0
    return-void
.end method

.method public preTransferOrientation(II)V
    .locals 0

    .line 728
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mOrientation:I

    .line 729
    iput p2, p0, Lcom/android/camera/module/BaseModule;->mOrientationCompensation:I

    .line 730
    return-void
.end method

.method public registerProtocol()V
    .locals 2

    .line 343
    const-string v0, "BaseModule"

    const-string v1, "registerProtocol"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 344
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xaa

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 345
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc8

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 346
    return-void
.end method

.method public requestRender()V
    .locals 0

    .line 646
    return-void
.end method

.method public resetEvValue()V
    .locals 2

    .line 396
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 397
    return-void

    .line 399
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setExposureCompensation(I)V

    .line 400
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setAWBLock(Z)V

    .line 401
    invoke-static {}, Lcom/android/camera/CameraSettings;->resetExposure()V

    .line 404
    new-array v0, v1, [I

    invoke-virtual {p0, v0}, Lcom/android/camera/module/BaseModule;->updatePreferenceInWorkThread([I)V

    .line 405
    return-void
.end method

.method public resetMutexModeManually()V
    .locals 1
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 1348
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->resetMutexMode()V

    .line 1349
    return-void
.end method

.method public final restartModule()V
    .locals 3

    .line 1671
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1672
    return-void

    .line 1674
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    iget v1, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    invoke-static {v1}, Lcom/android/camera/module/loader/StartControl;->create(I)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    const/4 v2, 0x3

    .line 1675
    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setViewConfigType(I)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    const/4 v2, 0x0

    .line 1676
    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setNeedReConfigureData(Z)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1677
    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setNeedBlurAnimation(Z)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1678
    invoke-virtual {v1, v2}, Lcom/android/camera/module/loader/StartControl;->setNeedReConfigureCamera(Z)Lcom/android/camera/module/loader/StartControl;

    move-result-object v1

    .line 1674
    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->onModeSelected(Lcom/android/camera/module/loader/StartControl;)V

    .line 1679
    return-void
.end method

.method protected restoreBottom()V
    .locals 3
    .annotation build Landroid/support/annotation/CallSuper;
    .end annotation

    .line 1335
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 1336
    const/16 v1, 0xa0

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 1337
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    .line 1338
    const/16 v2, 0xab

    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$BackStack;

    .line 1339
    invoke-interface {v1}, Lcom/android/camera/protocol/ModeProtocol$BackStack;->handleBackStackFromShutter()V

    .line 1340
    const v1, 0x7f0d001b

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->getActiveFragment(I)I

    move-result v1

    const/16 v2, 0xf1

    if-eq v1, v2, :cond_0

    .line 1342
    const/4 v1, 0x7

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->delegateEvent(I)V

    .line 1344
    :cond_0
    return-void
.end method

.method protected retryOnceIfCameraError(Landroid/os/Handler;)Z
    .locals 2

    .line 565
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/global/DataItemGlobal;->isRetriedIfCameraError()Z

    move-result v0

    .line 566
    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 569
    :cond_0
    const-string v0, "BaseModule"

    const-string v1, "onCameraException: retry1"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 570
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/global/DataItemGlobal;->setRetriedIfCameraError(Z)V

    .line 571
    new-instance v0, Lcom/android/camera/module/BaseModule$2;

    invoke-direct {v0, p0}, Lcom/android/camera/module/BaseModule$2;-><init>(Lcom/android/camera/module/BaseModule;)V

    invoke-virtual {p1, v0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 581
    return v1

    .line 567
    :cond_1
    :goto_0
    const/4 p1, 0x0

    return p1
.end method

.method public scaleZoomValue(F)Z
    .locals 2

    .line 557
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mZoomMaxRatio:F

    mul-float/2addr v0, p1

    .line 558
    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result p1

    const v1, 0x3c23d70a    # 0.01f

    cmpg-float p1, p1, v1

    if-gez p1, :cond_0

    .line 559
    const/4 p1, 0x0

    return p1

    .line 561
    :cond_0
    iget p1, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    add-float/2addr p1, v0

    invoke-direct {p0, p1}, Lcom/android/camera/module/BaseModule;->onZoomValueChanged(F)Z

    move-result p1

    return p1
.end method

.method protected sendOpenFailMessage()V
    .locals 0

    .line 637
    return-void
.end method

.method protected setAWBMode(Ljava/lang/String;)V
    .locals 2

    .line 1546
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1547
    return-void

    .line 1549
    :cond_0
    const-string v0, "manual"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1550
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setAWBMode(I)V

    .line 1551
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-static {}, Lcom/android/camera/CameraSettings;->getCustomWB()I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setCustomAWB(I)V

    goto :goto_0

    .line 1553
    :cond_1
    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result p1

    .line 1554
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v1}, Lcom/android/camera2/CameraCapabilities;->getSupportedAWBModes()[I

    move-result-object v1

    invoke-static {p1, v1}, Lcom/android/camera/Util;->isSupported(I[I)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1555
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setAWBMode(I)V

    goto :goto_0

    .line 1557
    :cond_2
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setAWBMode(I)V

    .line 1560
    :goto_0
    return-void
.end method

.method public setActivity(Lcom/android/camera/Camera;)V
    .locals 0

    .line 247
    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    .line 248
    return-void
.end method

.method public setCameraDevice(Lcom/android/camera2/Camera2Proxy;)V
    .locals 1

    .line 1399
    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 1400
    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->getCapabilities()Lcom/android/camera2/CameraCapabilities;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 1401
    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->getId()I

    move-result p1

    iput p1, p0, Lcom/android/camera/module/BaseModule;->mActualCameraId:I

    .line 1402
    return-void
.end method

.method protected setCameraState(I)V
    .locals 3

    .line 1485
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "setCameraState: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1486
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mCameraState:I

    .line 1487
    return-void
.end method

.method protected setColorEffect(Ljava/lang/String;)V
    .locals 4

    .line 1511
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1512
    return-void

    .line 1514
    :cond_0
    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v0

    .line 1515
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v1}, Lcom/android/camera2/CameraCapabilities;->getSupportedColorEffects()[I

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/Util;->isSupported(I[I)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1516
    const-string v1, "BaseModule"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "colorEffect: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1517
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setColorEffect(I)V

    .line 1519
    :cond_1
    return-void
.end method

.method public setDeparted()V
    .locals 2

    .line 153
    const-string v0, "BaseModule"

    const-string v1, "setDeparted"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 154
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_0

    .line 155
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 157
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_1

    .line 158
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 160
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectHintDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_2

    .line 161
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectHintDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 163
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_3

    .line 164
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->hideLensDirtyDetectedHint()V

    .line 167
    :cond_3
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsDeparted:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 168
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsFrameAvailable:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 169
    return-void
.end method

.method public setDisplayRectAndUIStyle(Landroid/graphics/Rect;I)V
    .locals 0

    .line 1467
    iput-object p1, p0, Lcom/android/camera/module/BaseModule;->mDisplayRect:Landroid/graphics/Rect;

    .line 1468
    iput p2, p0, Lcom/android/camera/module/BaseModule;->mUIStyle:I

    .line 1469
    return-void
.end method

.method protected final setEvValue()V
    .locals 3
    .annotation build Landroid/support/annotation/WorkerThread;
    .end annotation

    .line 380
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 381
    return-void

    .line 384
    :cond_0
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mEvState:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    .line 385
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setAWBLock(Z)V

    goto :goto_0

    .line 386
    :cond_1
    iget v0, p0, Lcom/android/camera/module/BaseModule;->mEvState:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_2

    .line 387
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v2, p0, Lcom/android/camera/module/BaseModule;->mEvValue:I

    invoke-virtual {v0, v2}, Lcom/android/camera2/Camera2Proxy;->setExposureCompensation(I)V

    .line 388
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setAWBLock(Z)V

    .line 391
    :cond_2
    :goto_0
    return-void
.end method

.method protected setFlashMode(Ljava/lang/String;)V
    .locals 3

    .line 1522
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1523
    return-void

    .line 1525
    :cond_0
    const-string v0, "BaseModule"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "flashMode: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1526
    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result p1

    .line 1527
    invoke-static {}, Lcom/android/camera/ThermalDetector;->getInstance()Lcom/android/camera/ThermalDetector;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/ThermalDetector;->thermalConstrained()Z

    move-result v1

    if-nez v1, :cond_1

    .line 1528
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isFrontCamera()Z

    move-result v1

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->isScreenSlideOff()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1529
    :cond_1
    nop

    .line 1531
    move p1, v0

    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-static {}, Lcom/android/camera/CameraSettings;->isOptimizedFlashEnable()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setOptimizedFlash(Z)V

    .line 1532
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setFlashMode(I)V

    .line 1533
    return-void
.end method

.method protected setFocusMode(Ljava/lang/String;)V
    .locals 1

    .line 1536
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1537
    return-void

    .line 1539
    :cond_0
    invoke-static {p1}, Lcom/android/camera/constant/AutoFocus;->convertToFocusMode(Ljava/lang/String;)I

    move-result p1

    .line 1540
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getSupportedFocusModes()[I

    move-result-object v0

    invoke-static {p1, v0}, Lcom/android/camera/Util;->isSupported(I[I)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1541
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setFocusMode(I)V

    .line 1543
    :cond_1
    return-void
.end method

.method public setFrameAvailable(Z)V
    .locals 1

    .line 143
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mIsFrameAvailable:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 144
    return-void
.end method

.method public setModuleIndex(I)V
    .locals 0

    .line 293
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    .line 294
    return-void
.end method

.method public setRestoring(Z)V
    .locals 0

    .line 1161
    iput-boolean p1, p0, Lcom/android/camera/module/BaseModule;->mRestoring:Z

    .line 1162
    return-void
.end method

.method protected setTriggerMode(I)V
    .locals 0

    .line 1378
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mTriggerMode:I

    .line 1379
    return-void
.end method

.method protected setZoomRatio(F)V
    .locals 1

    .line 1563
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setZoomRatio(F)V

    .line 1564
    return-void
.end method

.method protected setZoomValue(F)V
    .locals 0
    .annotation build Landroid/support/annotation/WorkerThread;
    .end annotation

    .line 1048
    iput p1, p0, Lcom/android/camera/module/BaseModule;->mZoomValue:F

    .line 1049
    invoke-static {p1}, Lcom/android/camera/CameraSettings;->writeZoom(F)V

    .line 1050
    return-void
.end method

.method public shouldReleaseLater()Z
    .locals 1

    .line 484
    const/4 v0, 0x0

    return v0
.end method

.method trackGeneralInfo(IZ)V
    .locals 7

    .line 1352
    iget v2, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->getTriggerMode()I

    move-result v3

    .line 1353
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isFrontCamera()Z

    move-result v4

    iget-object v5, p0, Lcom/android/camera/module/BaseModule;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    iget-object v6, p0, Lcom/android/camera/module/BaseModule;->mFlashAutoModeState:Ljava/lang/String;

    .line 1352
    move v0, p1

    move v1, p2

    invoke-static/range {v0 .. v6}, Lcom/android/camera/statistic/CameraStatUtil;->trackGeneralInfo(IZIIZLcom/android/camera/MutexModeManager;Ljava/lang/String;)V

    .line 1354
    return-void
.end method

.method protected trackModeCustomInfo(I)V
    .locals 0

    .line 1370
    return-void
.end method

.method trackPictureTaken(IZZ)V
    .locals 7

    .line 1357
    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v0, p0

    move v1, p1

    move v2, p2

    move v3, p3

    invoke-virtual/range {v0 .. v6}, Lcom/android/camera/module/BaseModule;->trackPictureTaken(IZZLjava/lang/String;ZZ)V

    .line 1358
    return-void
.end method

.method trackPictureTaken(IZZLjava/lang/String;ZZ)V
    .locals 6

    .line 1361
    iget v2, p0, Lcom/android/camera/module/BaseModule;->mModuleIndex:I

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isFrontCamera()Z

    move-result v3

    move v0, p1

    move v1, p2

    move v4, p3

    move-object v5, p4

    invoke-static/range {v0 .. v5}, Lcom/android/camera/statistic/CameraStatUtil;->trackPictureTaken(IZIZZLjava/lang/String;)V

    .line 1362
    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->trackModeCustomInfo(I)V

    .line 1363
    invoke-static {}, Lcom/android/camera/statistic/CameraStatUtil;->trackBeautyBodyCapture()V

    .line 1364
    invoke-static {p5, p6}, Lcom/android/camera/statistic/CameraStatUtil;->trackMoonMode(ZZ)V

    .line 1366
    return-void
.end method

.method public tryRemoveCountDownMessage()V
    .locals 0

    .line 1119
    return-void
.end method

.method public unRegisterModulePersistProtocol()V
    .locals 2

    .line 357
    const-string v0, "BaseModule"

    const-string v1, "unRegisterModulePersist"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 358
    return-void
.end method

.method public unRegisterProtocol()V
    .locals 2

    .line 350
    const-string v0, "BaseModule"

    const-string v1, "unRegisterProtocol"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 351
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xaa

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 352
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc8

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 353
    return-void
.end method

.method protected updateAntiBanding(Ljava/lang/String;)V
    .locals 4

    .line 1305
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1306
    return-void

    .line 1308
    :cond_0
    const/4 v0, 0x3

    invoke-static {p1, v0}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v0

    .line 1309
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 1310
    invoke-virtual {v1}, Lcom/android/camera2/CameraCapabilities;->getSupportedAntiBandingModes()[I

    move-result-object v1

    .line 1309
    invoke-static {v0, v1}, Lcom/android/camera/Util;->isSupported(I[I)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1311
    const-string v1, "BaseModule"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "antiBanding: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1312
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setAntiBanding(I)V

    .line 1314
    :cond_1
    return-void
.end method

.method protected updateCameraScreenNailSize(II)V
    .locals 3

    .line 1171
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "updateCameraScreenNailSize: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "x"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1174
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getWidth()I

    move-result v0

    if-ne v0, p1, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    .line 1175
    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getHeight()I

    move-result v0

    if-ne v0, p2, :cond_0

    .line 1176
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isSquareModeChange()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1178
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0, p1, p2}, Lcom/android/camera/CameraScreenNail;->setPreviewSize(II)V

    .line 1187
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0, p1, p2}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setPreviewSize(II)V

    .line 1188
    return-void
.end method

.method protected final updateExposureMeteringMode()V
    .locals 2

    .line 1317
    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1318
    return-void

    .line 1320
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->getExposureMeteringMode()I

    move-result v0

    .line 1321
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setExposureMeteringMode(I)V

    .line 1322
    return-void
.end method

.method protected updateFlashPreference()V
    .locals 0

    .line 1326
    return-void
.end method

.method protected updateHDRPreference()V
    .locals 0

    .line 1330
    return-void
.end method

.method public updateLensDirtyDetect(Z)V
    .locals 2

    .line 1491
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-nez v0, :cond_0

    .line 1492
    const-string p1, "BaseModule"

    const-string v0, "updateLensDirtyDetect: mCamera2Device is null..."

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1493
    return-void

    .line 1496
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLensDirtyDetectEnabled()Z

    move-result v0

    .line 1497
    if-nez v0, :cond_1

    .line 1498
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v1, :cond_1

    .line 1499
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 1500
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/android/camera/module/BaseModule;->mLensDirtyDetectDisposable:Lio/reactivex/disposables/Disposable;

    .line 1503
    :cond_1
    iget-object v1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setLensDirtyDetect(Z)V

    .line 1505
    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isFrameAvailable()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isDoingAction()Z

    move-result p1

    if-nez p1, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/BaseModule;->isPanoramaDoing()Z

    move-result p1

    if-nez p1, :cond_2

    .line 1506
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->resumePreview()V

    .line 1508
    :cond_2
    return-void
.end method

.method public final varargs updatePreferenceInWorkThread([I)V
    .locals 1
    .param p1    # [I
        .annotation build Lcom/android/camera/constant/UpdateConstant$UpdateType;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/WorkerThread;
    .end annotation

    .line 1440
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->isDisposed()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1449
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/BaseModule;->mUpdateWorkThreadEmitter:Lio/reactivex/ObservableEmitter;

    invoke-interface {v0, p1}, Lio/reactivex/ObservableEmitter;->onNext(Ljava/lang/Object;)V

    .line 1450
    return-void

    .line 1441
    :cond_1
    :goto_0
    return-void
.end method

.method public final varargs updatePreferenceTrampoline([I)V
    .locals 0
    .param p1    # [I
        .annotation build Lcom/android/camera/constant/UpdateConstant$UpdateType;
        .end annotation
    .end param

    .line 1459
    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->consumePreference([I)V

    .line 1460
    return-void
.end method

.method public updatePreviewSurface()V
    .locals 0

    .line 1168
    return-void
.end method

.method public updateScreenSlide(Z)V
    .locals 1

    .line 1694
    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz p1, :cond_0

    .line 1695
    iget-object p1, p0, Lcom/android/camera/module/BaseModule;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setAELock(Z)V

    .line 1696
    const/4 p1, 0x2

    new-array p1, p1, [I

    fill-array-data p1, :array_0

    invoke-virtual {p0, p1}, Lcom/android/camera/module/BaseModule;->updatePreferenceInWorkThread([I)V

    .line 1698
    :cond_0
    return-void

    :array_0
    .array-data 4
        0xa
        0x24
    .end array-data
.end method

.method protected updateStatusBar(I)V
    .locals 2

    .line 1042
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xac

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 1043
    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertUpdateValue(I)V

    .line 1044
    return-void
.end method

.method protected updateTipMessage(III)V
    .locals 2
    .param p2    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param

    .line 1289
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xaf

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;

    .line 1290
    if-eqz v0, :cond_0

    .line 1291
    invoke-interface {v0, p1, p2, p3}, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;->showTips(III)V

    .line 1293
    :cond_0
    return-void
.end method
