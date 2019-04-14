.class public Lcom/android/camera/module/Camera2Module;
.super Lcom/android/camera/module/BaseModule;
.source "Camera2Module.java"

# interfaces
.implements Lcom/android/camera/module/loader/camera2/FocusManager2$Listener;
.implements Lcom/android/camera/protocol/ModeProtocol$CameraAction;
.implements Lcom/android/camera/protocol/ModeProtocol$CameraModuleSpecial;
.implements Lcom/android/camera/protocol/ModeProtocol$FaceBeautyProtocol;
.implements Lcom/android/camera/protocol/ModeProtocol$FilterProtocol;
.implements Lcom/android/camera/protocol/ModeProtocol$OnFaceBeautyChangedProtocol;
.implements Lcom/android/camera/protocol/ModeProtocol$TopConfigProtocol;
.implements Lcom/android/camera/ui/ObjectView$ObjectViewListener;
.implements Lcom/android/camera2/Camera2Proxy$BeautyBodySlimCountCallback;
.implements Lcom/android/camera2/Camera2Proxy$CameraMetaDataCallback;
.implements Lcom/android/camera2/Camera2Proxy$CameraPreviewCallback;
.implements Lcom/android/camera2/Camera2Proxy$FaceDetectionCallback;
.implements Lcom/android/camera2/Camera2Proxy$FocusCallback;
.implements Lcom/android/camera2/Camera2Proxy$HDRCheckerCallback;
.implements Lcom/android/camera2/Camera2Proxy$PictureCallback;
.implements Lcom/android/camera2/Camera2Proxy$ScreenLightCallback;
.implements Lcom/android/camera2/Camera2Proxy$UltraWideCheckCallback;


# annotations
.annotation build Landroid/annotation/TargetApi;
    value = 0x15
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/camera/module/Camera2Module$MainHandler;,
        Lcom/android/camera/module/Camera2Module$JpegQuickPictureCallback;,
        Lcom/android/camera/module/Camera2Module$JpegRepeatingCaptureCallback;
    }
.end annotation


# static fields
.field private static final BURST_SHOOTING_DELAY:I = 0x0

.field private static final CAPTURE_DURATION_THRESHOLD:J = 0x2ee0L

.field private static final REQUEST_CROP:I = 0x3e8

.field private static final TAG:Ljava/lang/String;

.field private static mIsBeautyFrontOn:Z = false

.field private static final sTempCropFilename:Ljava/lang/String; = "crop-temp"


# instance fields
.field private volatile isDetectedInHDR:Z

.field private volatile isResetFromMutex:Z

.field private isSilhouette:Z

.field private m3ALocked:Z

.field private mAFEndLogTimes:I

.field private mAiSceneDisposable:Lio/reactivex/disposables/Disposable;

.field private mAiSceneEnabled:Z

.field private mAiSceneFlowableEmitter:Lio/reactivex/FlowableEmitter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/FlowableEmitter<",
            "Landroid/hardware/camera2/CaptureResult;",
            ">;"
        }
    .end annotation
.end field

.field private mAlgorithmName:Ljava/lang/String;

.field private mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

.field private mBroadcastIntent:Landroid/content/Intent;

.field private mBurstDisposable:Lio/reactivex/disposables/Disposable;

.field private mBurstEmitter:Lio/reactivex/ObservableEmitter;

.field private mBurstNextDelayTime:J

.field private mBurstStartTime:J

.field private mCaptureStartTime:J

.field private mCaptureWaterMarkStr:Ljava/lang/String;

.field private mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

.field private final mCircularMediaRecorderStateLock:Ljava/lang/Object;

.field private mCountdownDisposable:Lio/reactivex/disposables/Disposable;

.field private mCropValue:Ljava/lang/String;

.field private mCurrentAiScene:I

.field private mCurrentAsdScene:I

.field private mCurrentDetectedScene:I

.field private mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

.field private mEnableParallelSession:Z

.field private mEnabledPreviewThumbnail:Z

.field private mEnteringMoonMode:Z

.field private mFaceDetectionEnabled:Z

.field private mFaceDetectionStarted:Z

.field private mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

.field private mFunctionParseAiScene:Lcom/android/camera/module/loader/FunctionParseAiScene;

.field private mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

.field private mHasAiSceneFilterEffect:Z

.field private mHdrCheckEnabled:Z

.field private mIsBeautyBodySlimOn:Z

.field private mIsCurrentTaskIsParallel:Z

.field private mIsGenderAgeOn:Z

.field private mIsGradienterOn:Z

.field private mIsImageCaptureIntent:Z

.field private mIsLensServiceBound:Z

.field private mIsMagicMirrorOn:Z

.field private mIsMicrophoneEnabled:Z

.field private mIsMoonMode:Z

.field private mIsPortraitLightingOn:Z

.field private mIsSaveCaptureImage:Z

.field private mJpegRotation:I

.field private mKeepBitmapTexture:Z

.field private mLastCaptureTime:J

.field private mLastChangeSceneTime:J

.field private mLastFlashMode:Ljava/lang/String;

.field private mLensApi:Lcom/google/lens/sdk/LensApi;

.field private mLensStatus:I

.field private mLocation:Landroid/location/Location;

.field private mLongPressedAutoFocus:Z

.field private mMetaDataDisposable:Lio/reactivex/disposables/Disposable;

.field private mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lio/reactivex/FlowableEmitter<",
            "Landroid/hardware/camera2/CaptureResult;",
            ">;"
        }
    .end annotation
.end field

.field private mMultiSnapStatus:Z

.field private mMultiSnapStopRequest:Z

.field private mNeedAutoFocus:Z

.field private mOnResumeTime:J

.field private mOperatingMode:I

.field private mPendingMultiCapture:Z

.field private mQuickCapture:Z

.field private mReceivedJpegCallbackNum:I

.field private mSaveUri:Landroid/net/Uri;

.field private mSceneMode:Ljava/lang/String;

.field private mSensorStateListener:Lcom/android/camera/SensorStateManager$SensorStateListener;

.field private mServiceStatusListener:Lcom/android/camera/LocalParallelService$ServiceStatusListener;

.field private mShootOrientation:I

.field private mShootRotation:F

.field private mShouldDoMFNR:Z

.field private mShutterCallbackTime:J

.field private mShutterLag:J

.field private mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

.field private mTotalJpegCallbackNum:I

.field private mUpdateImageTitle:Z

.field private mVideoSize:Lcom/android/camera/CameraSize;

.field private mVolumeLongPress:Z

.field private mWaitingSuperNightResult:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 200
    const-class v0, Lcom/android/camera/module/Camera2Module;

    invoke-virtual {v0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 5

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/BaseModule;-><init>()V

    .line 239
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    .line 240
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStopRequest:Z

    .line 241
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    .line 242
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mUpdateImageTitle:Z

    .line 245
    const-wide/16 v1, 0x0

    iput-wide v1, p0, Lcom/android/camera/module/Camera2Module;->mBurstNextDelayTime:J

    .line 247
    const/4 v3, 0x1

    iput v3, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    .line 248
    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    .line 268
    const/4 v4, -0x1

    iput v4, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    .line 299
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsLensServiceBound:Z

    .line 300
    iput v3, p0, Lcom/android/camera/module/Camera2Module;->mLensStatus:I

    .line 322
    new-instance v4, Ljava/lang/Object;

    invoke-direct {v4}, Ljava/lang/Object;-><init>()V

    iput-object v4, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorderStateLock:Ljava/lang/Object;

    .line 323
    const/4 v4, 0x0

    iput-object v4, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    .line 324
    iput-boolean v3, p0, Lcom/android/camera/module/Camera2Module;->mIsMicrophoneEnabled:Z

    .line 4703
    new-instance v3, Lcom/android/camera/module/Camera2Module$23;

    invoke-direct {v3, p0}, Lcom/android/camera/module/Camera2Module$23;-><init>(Lcom/android/camera/module/Camera2Module;)V

    iput-object v3, p0, Lcom/android/camera/module/Camera2Module;->mSensorStateListener:Lcom/android/camera/SensorStateManager$SensorStateListener;

    .line 5312
    iput-wide v1, p0, Lcom/android/camera/module/Camera2Module;->mLastChangeSceneTime:J

    .line 5328
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isResetFromMutex:Z

    return-void
.end method

.method static synthetic access$000(Lcom/android/camera/module/Camera2Module;)Lio/reactivex/ObservableEmitter;
    .locals 0

    .line 180
    iget-object p0, p0, Lcom/android/camera/module/Camera2Module;->mBurstEmitter:Lio/reactivex/ObservableEmitter;

    return-object p0
.end method

.method static synthetic access$002(Lcom/android/camera/module/Camera2Module;Lio/reactivex/ObservableEmitter;)Lio/reactivex/ObservableEmitter;
    .locals 0

    .line 180
    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mBurstEmitter:Lio/reactivex/ObservableEmitter;

    return-object p1
.end method

.method static synthetic access$1000(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    return p0
.end method

.method static synthetic access$102(Lcom/android/camera/module/Camera2Module;J)J
    .locals 0

    .line 180
    iput-wide p1, p0, Lcom/android/camera/module/Camera2Module;->mBurstStartTime:J

    return-wide p1
.end method

.method static synthetic access$1100(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStopRequest:Z

    return p0
.end method

.method static synthetic access$1200(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->is3ALocked()Z

    move-result p0

    return p0
.end method

.method static synthetic access$1300(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/module/loader/camera2/FocusManager2;
    .locals 0

    .line 180
    iget-object p0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    return-object p0
.end method

.method static synthetic access$1400()Ljava/lang/String;
    .locals 1

    .line 180
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$1500(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/effect/renders/DualWatermarkParam;
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getDualWaterMarkParam()Lcom/android/camera/effect/renders/DualWatermarkParam;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1600()Ljava/lang/String;
    .locals 1

    .line 180
    invoke-static {}, Lcom/android/camera/module/Camera2Module;->getTiltShiftMode()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic access$1700(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mIsGradienterOn:Z

    return p0
.end method

.method static synthetic access$1800(Lcom/android/camera/module/Camera2Module;)Ljava/lang/String;
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getSuffix()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$1900(Lcom/android/camera/module/Camera2Module;)Lcom/xiaomi/camera/core/PictureInfo;
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getPictureInfo()Lcom/xiaomi/camera/core/PictureInfo;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$2000(Lcom/android/camera/module/Camera2Module;)Ljava/lang/String;
    .locals 0

    .line 180
    iget-object p0, p0, Lcom/android/camera/module/Camera2Module;->mAlgorithmName:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$202(Lcom/android/camera/module/Camera2Module;Lio/reactivex/disposables/Disposable;)Lio/reactivex/disposables/Disposable;
    .locals 0

    .line 180
    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mBurstDisposable:Lio/reactivex/disposables/Disposable;

    return-object p1
.end method

.method static synthetic access$2100(Lcom/android/camera/module/Camera2Module;)Landroid/location/Location;
    .locals 0

    .line 180
    iget-object p0, p0, Lcom/android/camera/module/Camera2Module;->mLocation:Landroid/location/Location;

    return-object p0
.end method

.method static synthetic access$2200(Lcom/android/camera/module/Camera2Module;)I
    .locals 0

    .line 180
    iget p0, p0, Lcom/android/camera/module/Camera2Module;->mShootOrientation:I

    return p0
.end method

.method static synthetic access$2300(Lcom/android/camera/module/Camera2Module;)F
    .locals 0

    .line 180
    iget p0, p0, Lcom/android/camera/module/Camera2Module;->mShootRotation:F

    return p0
.end method

.method static synthetic access$2400(Lcom/android/camera/module/Camera2Module;)I
    .locals 0

    .line 180
    iget p0, p0, Lcom/android/camera/module/Camera2Module;->mJpegRotation:I

    return p0
.end method

.method static synthetic access$2500(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isFrontMirror()Z

    move-result p0

    return p0
.end method

.method static synthetic access$2600(Lcom/android/camera/module/Camera2Module;Lcom/xiaomi/camera/core/ParallelTaskData;Z)V
    .locals 0

    .line 180
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->beginParallelProcess(Lcom/xiaomi/camera/core/ParallelTaskData;Z)V

    return-void
.end method

.method static synthetic access$2700(Lcom/android/camera/module/Camera2Module;)V
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->stopMultiSnap()V

    return-void
.end method

.method static synthetic access$2800(Lcom/android/camera/module/Camera2Module;)Ljava/lang/String;
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getCurrentAiSceneName()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static synthetic access$2900(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mEnteringMoonMode:Z

    return p0
.end method

.method static synthetic access$3000(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    return p0
.end method

.method static synthetic access$3102(Lcom/android/camera/module/Camera2Module;Lio/reactivex/disposables/Disposable;)Lio/reactivex/disposables/Disposable;
    .locals 0

    .line 180
    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    return-object p1
.end method

.method static synthetic access$3200(Lcom/android/camera/module/Camera2Module;I)V
    .locals 0

    .line 180
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->startNormalCapture(I)V

    return-void
.end method

.method static synthetic access$3300(Lcom/android/camera/module/Camera2Module;)Lcom/google/lens/sdk/LensApi;
    .locals 0

    .line 180
    iget-object p0, p0, Lcom/android/camera/module/Camera2Module;->mLensApi:Lcom/google/lens/sdk/LensApi;

    return-object p0
.end method

.method static synthetic access$3400(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mIsLensServiceBound:Z

    return p0
.end method

.method static synthetic access$3402(Lcom/android/camera/module/Camera2Module;Z)Z
    .locals 0

    .line 180
    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mIsLensServiceBound:Z

    return p1
.end method

.method static synthetic access$3500(Lcom/android/camera/module/Camera2Module;)J
    .locals 2

    .line 180
    iget-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mOnResumeTime:J

    return-wide v0
.end method

.method static synthetic access$3600(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/module/Camera2Module$MainHandler;
    .locals 0

    .line 180
    iget-object p0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    return-object p0
.end method

.method static synthetic access$3700(Lcom/android/camera/module/Camera2Module;II)V
    .locals 0

    .line 180
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->setOrientation(II)V

    return-void
.end method

.method static synthetic access$3800(Lcom/android/camera/module/Camera2Module;)V
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->setOrientationParameter()V

    return-void
.end method

.method static synthetic access$3900(Lcom/android/camera/module/Camera2Module;ZZ)V
    .locals 0

    .line 180
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->handleUpdateFaceView(ZZ)V

    return-void
.end method

.method static synthetic access$4000(Lcom/android/camera/module/Camera2Module;)I
    .locals 0

    .line 180
    iget p0, p0, Lcom/android/camera/module/Camera2Module;->mLensStatus:I

    return p0
.end method

.method static synthetic access$4002(Lcom/android/camera/module/Camera2Module;I)I
    .locals 0

    .line 180
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mLensStatus:I

    return p1
.end method

.method static synthetic access$402(Lcom/android/camera/module/Camera2Module;Z)Z
    .locals 0

    .line 180
    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mWaitingSuperNightResult:Z

    return p1
.end method

.method static synthetic access$4100(Lcom/android/camera/module/Camera2Module;)V
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSceneModeUI()V

    return-void
.end method

.method static synthetic access$4200(Lcom/android/camera/module/Camera2Module;)V
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->startLensActivity()V

    return-void
.end method

.method static synthetic access$4300(Lcom/android/camera/module/Camera2Module;I)V
    .locals 0

    .line 180
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->consumeAsdSceneResult(I)V

    return-void
.end method

.method static synthetic access$4402(Lcom/android/camera/module/Camera2Module;Lio/reactivex/FlowableEmitter;)Lio/reactivex/FlowableEmitter;
    .locals 0

    .line 180
    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;

    return-object p1
.end method

.method static synthetic access$4500(Lcom/android/camera/module/Camera2Module;IZ)V
    .locals 0

    .line 180
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->consumeAiSceneResult(IZ)V

    return-void
.end method

.method static synthetic access$4602(Lcom/android/camera/module/Camera2Module;Lio/reactivex/FlowableEmitter;)Lio/reactivex/FlowableEmitter;
    .locals 0

    .line 180
    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneFlowableEmitter:Lio/reactivex/FlowableEmitter;

    return-object p1
.end method

.method static synthetic access$4702(Lcom/android/camera/module/Camera2Module;Z)Z
    .locals 0

    .line 180
    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->isResetFromMutex:Z

    return p1
.end method

.method static synthetic access$4800(Lcom/android/camera/module/Camera2Module;II)V
    .locals 0

    .line 180
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->trackAISceneChanged(II)V

    return-void
.end method

.method static synthetic access$500(Lcom/android/camera/module/Camera2Module;)V
    .locals 0

    .line 180
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->animateCapture()V

    return-void
.end method

.method static synthetic access$600(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mUpdateImageTitle:Z

    return p0
.end method

.method static synthetic access$700(Lcom/android/camera/module/Camera2Module;)I
    .locals 0

    .line 180
    iget p0, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    return p0
.end method

.method static synthetic access$704(Lcom/android/camera/module/Camera2Module;)I
    .locals 1

    .line 180
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    return v0
.end method

.method static synthetic access$800(Lcom/android/camera/module/Camera2Module;)Z
    .locals 0

    .line 180
    iget-boolean p0, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    return p0
.end method

.method static synthetic access$900(Lcom/android/camera/module/Camera2Module;)I
    .locals 0

    .line 180
    iget p0, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    return p0
.end method

.method private animateCapture()V
    .locals 2

    .line 1589
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-nez v0, :cond_0

    .line 1590
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraRotation()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/android/camera/CameraScreenNail;->animateCapture(I)V

    .line 1592
    :cond_0
    return-void
.end method

.method private applyBacklightEffect()V
    .locals 2

    .line 5690
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0x17

    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->trackAISceneChanged(II)V

    .line 5691
    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->setAiSceneEffect(I)V

    .line 5693
    const-string v0, "normal"

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->updateHDR(Ljava/lang/String;)V

    .line 5694
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->resetEvValue()V

    .line 5695
    return-void
.end method

.method private beginParallelProcess(Lcom/xiaomi/camera/core/ParallelTaskData;Z)V
    .locals 3

    .line 2711
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "algo begin: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/xiaomi/camera/core/ParallelTaskData;->getSavePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " | "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 2713
    if-eqz p2, :cond_0

    .line 2715
    invoke-static {}, Lcom/android/camera/db/DbRepository;->dbItemSaveTask()Lcom/android/camera/db/item/DbItemSaveTask;

    move-result-object p2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-virtual {p2, v0, v1}, Lcom/android/camera/db/item/DbItemSaveTask;->generateItem(J)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/android/camera/db/element/SaveTask;

    .line 2716
    invoke-virtual {p1}, Lcom/xiaomi/camera/core/ParallelTaskData;->getSavePath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Lcom/android/camera/db/element/SaveTask;->setPath(Ljava/lang/String;)V

    .line 2717
    invoke-static {}, Lcom/android/camera/db/DbRepository;->dbItemSaveTask()Lcom/android/camera/db/item/DbItemSaveTask;

    move-result-object p1

    const-wide/16 v0, 0x0

    invoke-virtual {p1, p2, v0, v1}, Lcom/android/camera/db/item/DbItemSaveTask;->endItemAndInsert(Ljava/lang/Object;J)J

    .line 2720
    :cond_0
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mServiceStatusListener:Lcom/android/camera/LocalParallelService$ServiceStatusListener;

    if-nez p1, :cond_1

    .line 2721
    new-instance p1, Lcom/android/camera/module/Camera2Module$16;

    invoke-direct {p1, p0}, Lcom/android/camera/module/Camera2Module$16;-><init>(Lcom/android/camera/module/Camera2Module;)V

    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mServiceStatusListener:Lcom/android/camera/LocalParallelService$ServiceStatusListener;

    .line 2732
    invoke-static {}, Lcom/android/camera/parallel/AlgoConnector;->getInstance()Lcom/android/camera/parallel/AlgoConnector;

    move-result-object p1

    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mServiceStatusListener:Lcom/android/camera/LocalParallelService$ServiceStatusListener;

    invoke-virtual {p1, p2}, Lcom/android/camera/parallel/AlgoConnector;->setServiceStatusListener(Lcom/android/camera/LocalParallelService$ServiceStatusListener;)V

    .line 2734
    :cond_1
    return-void
.end method

.method private calculateTimeout(I)J
    .locals 6

    .line 1170
    const-wide/16 v0, 0x2ee0

    const/16 v2, 0xa7

    if-ne p1, v2, :cond_0

    .line 1171
    const-string p1, "pref_qc_camera_exposuretime_key"

    const v2, 0x7f090136

    .line 1172
    invoke-virtual {p0, v2}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 1171
    invoke-direct {p0, p1, v2}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 1173
    invoke-static {p1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v2

    .line 1174
    const-wide/32 v4, 0xf4240

    div-long/2addr v2, v4

    .line 1176
    add-long/2addr v2, v0

    return-wide v2

    .line 1178
    :cond_0
    const/16 v2, 0xad

    if-ne p1, v2, :cond_1

    const-wide/16 v0, 0x5dc0

    nop

    :cond_1
    return-wide v0
.end method

.method private checkCloseMoonMode(II)V
    .locals 2

    .line 5543
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnteringMoonMode:Z

    if-nez v0, :cond_0

    .line 5545
    return-void

    .line 5548
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    const/16 v1, 0xa

    if-eq v0, v1, :cond_1

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    const/16 v1, 0x23

    if-eq v0, v1, :cond_1

    .line 5551
    return-void

    .line 5554
    :cond_1
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    if-ne p1, v0, :cond_2

    .line 5556
    return-void

    .line 5558
    :cond_2
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xac

    .line 5559
    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 5560
    const/4 v0, 0x0

    if-eqz p1, :cond_3

    .line 5561
    invoke-interface {p1, p2}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertMoonModeSelector(I)V

    .line 5562
    if-eqz p2, :cond_3

    .line 5563
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnteringMoonMode:Z

    .line 5566
    :cond_3
    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updateMoon(Z)V

    .line 5567
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {p1}, Lcom/android/camera/MutexModeManager;->isSuperResolution()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 5568
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Lcom/android/camera2/Camera2Proxy;->setSuperResolution(Z)V

    .line 5570
    :cond_4
    return-void
.end method

.method private checkShutterCondition()Z
    .locals 2

    .line 1057
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_4

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isIgnoreTouchEvent()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1060
    :cond_0
    invoke-static {}, Lcom/android/camera/storage/Storage;->isLowStorageAtLastPoint()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1061
    return v1

    .line 1063
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isScreenSlideOff()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1064
    return v1

    .line 1067
    :cond_2
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xab

    .line 1068
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BackStack;

    .line 1069
    if-eqz v0, :cond_3

    .line 1070
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$BackStack;->handleBackStackFromShutter()V

    .line 1073
    :cond_3
    const/4 v0, 0x1

    return v0

    .line 1058
    :cond_4
    :goto_0
    return v1
.end method

.method private configParallelSession()V
    .locals 6

    .line 2414
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    .line 2415
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualFrontCamera()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 2416
    :cond_0
    move v0, v1

    goto :goto_1

    .line 2415
    :cond_1
    :goto_0
    const/4 v0, 0x2

    .line 2416
    :goto_1
    sget-object v2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "configParallelSession: inputStreamNum = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2417
    new-instance v2, Lcom/xiaomi/engine/GraphDescriptorBean;

    const v3, 0x8002

    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mActualCameraId:I

    .line 2418
    invoke-static {v4}, Lcom/xiaomi/camera/base/CameraDeviceUtil;->getCameraCombinationMode(I)I

    move-result v4

    invoke-direct {v2, v3, v0, v1, v4}, Lcom/xiaomi/engine/GraphDescriptorBean;-><init>(IIZI)V

    .line 2419
    goto :goto_2

    .line 2420
    :cond_2
    new-instance v2, Lcom/xiaomi/engine/GraphDescriptorBean;

    const/4 v0, 0x0

    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mActualCameraId:I

    .line 2421
    invoke-static {v3}, Lcom/xiaomi/camera/base/CameraDeviceUtil;->getCameraCombinationMode(I)I

    move-result v3

    invoke-direct {v2, v0, v1, v1, v3}, Lcom/xiaomi/engine/GraphDescriptorBean;-><init>(IIZI)V

    .line 2424
    :goto_2
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "[QCFA] configParallelSession: mPictureSize = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2425
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "[QCFA] configParallelSession: mOutPutSize = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2426
    new-instance v0, Lcom/xiaomi/engine/BufferFormat;

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    iget v3, v3, Lcom/android/camera/CameraSize;->width:I

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    iget v4, v4, Lcom/android/camera/CameraSize;->height:I

    const/16 v5, 0x23

    invoke-direct {v0, v3, v4, v5, v2}, Lcom/xiaomi/engine/BufferFormat;-><init>(IIILcom/xiaomi/engine/GraphDescriptorBean;)V

    .line 2428
    invoke-static {}, Lcom/android/camera/parallel/AlgoConnector;->getInstance()Lcom/android/camera/parallel/AlgoConnector;

    move-result-object v2

    invoke-virtual {v2, v1}, Lcom/android/camera/parallel/AlgoConnector;->getLocalBinder(Z)Lcom/android/camera/LocalParallelService$LocalBinder;

    move-result-object v1

    .line 2429
    invoke-virtual {v1, v0}, Lcom/android/camera/LocalParallelService$LocalBinder;->configCaptureSession(Lcom/xiaomi/engine/BufferFormat;)V

    .line 2430
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/android/camera/LocalParallelService$LocalBinder;->setImageSaver(Lcom/android/camera/storage/ImageSaver;)V

    .line 2431
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    iget v0, v0, Lcom/android/camera/CameraSize;->width:I

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    iget v2, v2, Lcom/android/camera/CameraSize;->height:I

    invoke-virtual {v1, v0, v2}, Lcom/android/camera/LocalParallelService$LocalBinder;->setJpegOutputSize(II)V

    .line 2432
    return-void
.end method

.method private consumeAiSceneResult(IZ)V
    .locals 9

    .line 5360
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    if-ne v0, p1, :cond_0

    .line 5361
    return-void

    .line 5364
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_11

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-eqz v0, :cond_11

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_1

    goto/16 :goto_3

    .line 5368
    :cond_1
    if-eqz p2, :cond_2

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isResetFromMutex:Z

    if-eqz v0, :cond_2

    .line 5369
    return-void

    .line 5372
    :cond_2
    const/4 v0, 0x0

    if-nez p2, :cond_3

    .line 5373
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isResetFromMutex:Z

    .line 5375
    :cond_3
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "consumeAiSceneResult: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, "; isReset: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 5376
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xac

    .line 5377
    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 5379
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v2

    const/16 v3, 0xa4

    .line 5380
    invoke-virtual {v2, v3}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v2

    check-cast v2, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 5381
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v3

    const/16 v4, 0x19

    const/4 v5, 0x1

    if-nez v3, :cond_5

    .line 5382
    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-ne p1, v4, :cond_4

    move v6, v5

    goto :goto_0

    :cond_4
    move v6, v0

    :goto_0
    invoke-virtual {v3, v6}, Lcom/android/camera2/Camera2Proxy;->setCameraAI30(Z)V

    .line 5385
    :cond_5
    const/16 v3, 0x8

    invoke-direct {p0, p1, v3}, Lcom/android/camera/module/Camera2Module;->checkCloseMoonMode(II)V

    .line 5386
    nop

    .line 5387
    const/4 v6, -0x1

    const/16 v7, 0xc1

    if-eq p1, v6, :cond_10

    const/4 v6, 0x6

    if-eq p1, v5, :cond_b

    const/4 v8, 0x4

    if-eq p1, v8, :cond_a

    const/16 v8, 0xa

    if-eq p1, v8, :cond_9

    const/16 v7, 0xf

    if-eq p1, v7, :cond_7

    const/16 v7, 0x13

    if-eq p1, v7, :cond_7

    packed-switch p1, :pswitch_data_0

    packed-switch p1, :pswitch_data_1

    packed-switch p1, :pswitch_data_2

    .line 5499
    const-string v0, "e"

    invoke-interface {v2, v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5500
    sget-object v0, Lcom/android/camera/constant/UpdateConstant;->AI_SCENE_CONFIG:[I

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    goto/16 :goto_1

    .line 5463
    :pswitch_0
    nop

    .line 5464
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->showMoonMode()Z

    move-result v2

    .line 5465
    if-eqz v2, :cond_d

    .line 5466
    invoke-interface {v1, p1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->setAiSceneImageLevel(I)V

    .line 5467
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p2, v5}, Lcom/android/camera2/Camera2Proxy;->setASD(Z)V

    .line 5468
    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-direct {p0, p2, p1}, Lcom/android/camera/module/Camera2Module;->trackAISceneChanged(II)V

    .line 5469
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5470
    return-void

    .line 5475
    :pswitch_1
    nop

    .line 5476
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5477
    goto/16 :goto_2

    .line 5485
    :pswitch_2
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v4

    invoke-virtual {v4}, Lcom/mi/config/a;->eU()Z

    move-result v4

    if-eqz v4, :cond_6

    .line 5486
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5487
    const-string v0, "e"

    invoke-interface {v2, v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    goto/16 :goto_1

    .line 5490
    :cond_6
    nop

    .line 5491
    const-string p1, "e"

    invoke-interface {v2, p1}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5492
    sget-object p1, Lcom/android/camera/constant/UpdateConstant;->AI_SCENE_CONFIG:[I

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 5495
    nop

    .line 5504
    move p1, v0

    goto/16 :goto_1

    .line 5405
    :pswitch_3
    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-direct {p0, p2, v4}, Lcom/android/camera/module/Camera2Module;->trackAISceneChanged(II)V

    .line 5406
    invoke-interface {v1, v4}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->setAiSceneImageLevel(I)V

    .line 5407
    invoke-interface {v1, v3}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertAiSceneSelector(I)V

    .line 5408
    invoke-direct {p0, v4}, Lcom/android/camera/module/Camera2Module;->setAiSceneEffect(I)V

    .line 5409
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5410
    const-string p1, "e"

    invoke-interface {v2, p1}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5412
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setASD(Z)V

    .line 5413
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    .line 5414
    return-void

    .line 5458
    :pswitch_4
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5459
    const-string v0, "e"

    invoke-interface {v2, v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5461
    goto :goto_1

    .line 5448
    :cond_7
    invoke-static {}, Lcom/android/camera/CameraSettings;->getSharpness()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 5449
    if-ge v0, v6, :cond_8

    .line 5450
    add-int/lit8 v0, v0, 0x1

    .line 5452
    :cond_8
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v4, v0}, Lcom/android/camera2/Camera2Proxy;->setSharpness(I)V

    .line 5453
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5454
    const-string v0, "e"

    invoke-interface {v2, v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5455
    goto :goto_1

    .line 5417
    :cond_9
    const-string v4, "e"

    new-array v6, v5, [I

    aput v7, v6, v0

    invoke-interface {v2, v4, v6}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->closeMutexElement(Ljava/lang/String;[I)V

    .line 5426
    const-string v0, "0"

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setFlashMode(Ljava/lang/String;)V

    .line 5427
    invoke-direct {p0, v5}, Lcom/android/camera/module/Camera2Module;->updateMfnr(Z)V

    .line 5428
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateOIS()V

    .line 5429
    goto :goto_1

    .line 5440
    :cond_a
    invoke-static {}, Lcom/android/camera/CameraSettings;->getContrast()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 5441
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v4, v0}, Lcom/android/camera2/Camera2Proxy;->setContrast(I)V

    .line 5442
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5443
    const-string v0, "e"

    invoke-interface {v2, v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5444
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSuperResolution()V

    .line 5445
    goto :goto_1

    .line 5431
    :cond_b
    invoke-static {}, Lcom/android/camera/CameraSettings;->getSharpness()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 5432
    if-ge v0, v6, :cond_c

    .line 5433
    add-int/lit8 v0, v0, 0x1

    .line 5435
    :cond_c
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5436
    const-string v4, "e"

    invoke-interface {v2, v4}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->restoreAllMutexElement(Ljava/lang/String;)V

    .line 5437
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v2, v0}, Lcom/android/camera2/Camera2Proxy;->setSharpness(I)V

    .line 5438
    nop

    .line 5504
    :goto_1
    move v0, v5

    :cond_d
    :goto_2
    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-direct {p0, v2, p1}, Lcom/android/camera/module/Camera2Module;->trackAISceneChanged(II)V

    .line 5505
    invoke-interface {v1, p1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->setAiSceneImageLevel(I)V

    .line 5506
    invoke-interface {v1, v3}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertAiSceneSelector(I)V

    .line 5507
    if-eqz v0, :cond_e

    .line 5508
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->setAiSceneEffect(I)V

    .line 5510
    :cond_e
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v5}, Lcom/android/camera2/Camera2Proxy;->setASD(Z)V

    .line 5511
    if-nez p2, :cond_f

    .line 5512
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5514
    :cond_f
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateBeauty()V

    .line 5515
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    .line 5516
    return-void

    .line 5389
    :cond_10
    const-string p2, "e"

    new-array v4, v5, [I

    aput v7, v4, v0

    invoke-interface {v2, p2, v4}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->closeMutexElement(Ljava/lang/String;[I)V

    .line 5396
    const/16 p2, 0x17

    invoke-interface {v1, p2}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->setAiSceneImageLevel(I)V

    .line 5397
    invoke-interface {v1, v3, v0, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertHDR(IZZ)V

    .line 5398
    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertAiSceneSelector(I)V

    .line 5399
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->applyBacklightEffect()V

    .line 5400
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p2, v0}, Lcom/android/camera2/Camera2Proxy;->setASD(Z)V

    .line 5401
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    .line 5402
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5403
    return-void

    .line 5365
    :cond_11
    :goto_3
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x7
        :pswitch_4
        :pswitch_4
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x19
        :pswitch_3
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x22
        :pswitch_1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method private consumeAsdSceneResult(I)V
    .locals 1

    .line 5196
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    if-ne v0, p1, :cond_0

    .line 5197
    return-void

    .line 5200
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    .line 5204
    :cond_1
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->exitAsdScene(I)V

    .line 5205
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->enterAsdScene(I)V

    .line 5206
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    .line 5207
    return-void

    .line 5201
    :cond_2
    :goto_0
    return-void
.end method

.method private doAttach()V
    .locals 6

    .line 3145
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-eqz v0, :cond_0

    .line 3146
    return-void

    .line 3148
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/storage/ImageSaver;->getStoredJpegData()[B

    move-result-object v0

    .line 3149
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsSaveCaptureImage:Z

    if-eqz v1, :cond_1

    .line 3150
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/storage/ImageSaver;->saveStoredData()V

    .line 3152
    :cond_1
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCropValue:Ljava/lang/String;

    const/4 v2, 0x0

    if-nez v1, :cond_3

    .line 3157
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mSaveUri:Landroid/net/Uri;

    const/4 v3, -0x1

    if-eqz v1, :cond_2

    .line 3158
    nop

    .line 3160
    :try_start_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mContentResolver:Landroid/content/ContentResolver;

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mSaveUri:Landroid/net/Uri;

    invoke-virtual {v1, v4}, Landroid/content/ContentResolver;->openOutputStream(Landroid/net/Uri;)Ljava/io/OutputStream;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 3161
    :try_start_1
    invoke-virtual {v1, v0}, Ljava/io/OutputStream;->write([B)V

    .line 3162
    invoke-virtual {v1}, Ljava/io/OutputStream;->close()V

    .line 3164
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, v3}, Lcom/android/camera/Camera;->setResult(I)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 3169
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V

    .line 3170
    invoke-static {v1}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    goto :goto_1

    .line 3169
    :catchall_0
    move-exception v0

    move-object v2, v1

    goto :goto_2

    .line 3165
    :catch_0
    move-exception v0

    move-object v2, v1

    goto :goto_0

    .line 3169
    :catchall_1
    move-exception v0

    goto :goto_2

    .line 3165
    :catch_1
    move-exception v0

    .line 3167
    :goto_0
    :try_start_2
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v3, "Exception when doAttach: "

    invoke-static {v1, v3, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 3169
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V

    .line 3170
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 3171
    :goto_1
    nop

    .line 3172
    goto/16 :goto_4

    .line 3169
    :goto_2
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->finish()V

    .line 3170
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    throw v0

    .line 3173
    :cond_2
    const v1, 0xc800

    invoke-static {v0, v1}, Lcom/android/camera/Util;->makeBitmap([BI)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 3174
    invoke-static {v0}, Lcom/android/camera/Exif;->getOrientation([B)I

    move-result v0

    .line 3175
    invoke-static {v1, v0}, Lcom/android/camera/Util;->rotate(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 3176
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    new-instance v2, Landroid/content/Intent;

    const-string v4, "inline-data"

    invoke-direct {v2, v4}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v4, "data"

    .line 3177
    invoke-virtual {v2, v4, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    move-result-object v0

    .line 3176
    invoke-virtual {v1, v3, v0}, Lcom/android/camera/Camera;->setResult(ILandroid/content/Intent;)V

    .line 3178
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V

    .line 3179
    goto :goto_4

    .line 3182
    :cond_3
    nop

    .line 3183
    nop

    .line 3185
    const/4 v1, 0x0

    :try_start_3
    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const-string v4, "crop-temp"

    invoke-virtual {v3, v4}, Lcom/android/camera/Camera;->getFileStreamPath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v3

    .line 3186
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    .line 3187
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const-string v5, "crop-temp"

    invoke-virtual {v4, v5, v1}, Lcom/android/camera/Camera;->openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;

    move-result-object v4
    :try_end_3
    .catch Ljava/io/FileNotFoundException; {:try_start_3 .. :try_end_3} :catch_5
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_4
    .catchall {:try_start_3 .. :try_end_3} :catchall_3

    .line 3188
    :try_start_4
    invoke-virtual {v4, v0}, Ljava/io/FileOutputStream;->write([B)V

    .line 3189
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->close()V
    :try_end_4
    .catch Ljava/io/FileNotFoundException; {:try_start_4 .. :try_end_4} :catch_3
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_2
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    .line 3190
    nop

    .line 3191
    :try_start_5
    invoke-static {v3}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object v0
    :try_end_5
    .catch Ljava/io/FileNotFoundException; {:try_start_5 .. :try_end_5} :catch_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_4
    .catchall {:try_start_5 .. :try_end_5} :catchall_3

    .line 3201
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 3202
    nop

    .line 3203
    new-instance v1, Landroid/os/Bundle;

    invoke-direct {v1}, Landroid/os/Bundle;-><init>()V

    .line 3204
    const-string v2, "circle"

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mCropValue:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 3205
    const-string v2, "circleCrop"

    const-string v3, "true"

    invoke-virtual {v1, v2, v3}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    .line 3207
    :cond_4
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mSaveUri:Landroid/net/Uri;

    if-eqz v2, :cond_5

    .line 3208
    const-string v2, "output"

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mSaveUri:Landroid/net/Uri;

    invoke-virtual {v1, v2, v3}, Landroid/os/Bundle;->putParcelable(Ljava/lang/String;Landroid/os/Parcelable;)V

    goto :goto_3

    .line 3210
    :cond_5
    const-string v2, "return-data"

    const/4 v3, 0x1

    invoke-virtual {v1, v2, v3}, Landroid/os/Bundle;->putBoolean(Ljava/lang/String;Z)V

    .line 3213
    :goto_3
    new-instance v2, Landroid/content/Intent;

    const-string v3, "com.android.camera.action.CROP"

    invoke-direct {v2, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 3215
    invoke-virtual {v2, v0}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    .line 3216
    invoke-virtual {v2, v1}, Landroid/content/Intent;->putExtras(Landroid/os/Bundle;)Landroid/content/Intent;

    .line 3218
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/16 v1, 0x3e8

    invoke-virtual {v0, v2, v1}, Lcom/android/camera/Camera;->startActivityForResult(Landroid/content/Intent;I)V

    .line 3220
    :goto_4
    return-void

    .line 3201
    :catchall_2
    move-exception v0

    move-object v2, v4

    goto :goto_7

    .line 3196
    :catch_2
    move-exception v0

    move-object v2, v4

    goto :goto_5

    .line 3192
    :catch_3
    move-exception v0

    move-object v2, v4

    goto :goto_6

    .line 3201
    :catchall_3
    move-exception v0

    goto :goto_7

    .line 3196
    :catch_4
    move-exception v0

    .line 3197
    :goto_5
    :try_start_6
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->setResult(I)V

    .line 3198
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_3

    .line 3201
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 3199
    return-void

    .line 3192
    :catch_5
    move-exception v0

    .line 3193
    :goto_6
    :try_start_7
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->setResult(I)V

    .line 3194
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_3

    .line 3201
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 3195
    return-void

    .line 3201
    :goto_7
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    throw v0
.end method

.method private doLaterReleaseIfNeed()V
    .locals 4

    .line 2058
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    if-nez v0, :cond_0

    .line 2059
    return-void

    .line 2062
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v0, :cond_1

    .line 2063
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x32

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 2066
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 2067
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x1

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isSessionReady()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 2068
    move v0, v1

    goto :goto_0

    .line 2067
    :cond_2
    const/4 v0, 0x0

    .line 2068
    :goto_0
    if-eqz v0, :cond_3

    .line 2069
    sget-object v2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v3, "doLaterRelease"

    invoke-static {v2, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 2071
    :cond_3
    sget-object v2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v3, "doLaterRelease but session is closed"

    invoke-static {v2, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2073
    :goto_1
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v2, v1, v0}, Lcom/android/camera/Camera;->releaseAll(ZZ)V

    .line 2074
    return-void

    .line 2077
    :cond_4
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDeparted()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 2078
    return-void

    .line 2081
    :cond_5
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$13;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$13;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 2088
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isTextureExpired()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 2090
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "surfaceTexture expired, restartModule"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2091
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$14;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$14;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 2097
    return-void

    .line 2100
    :cond_6
    return-void
.end method

.method private enablePreviewAsThumbnail()Z
    .locals 4

    .line 2755
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 2756
    return v1

    .line 2760
    :cond_0
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 2761
    return v2

    .line 2765
    :cond_1
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsPortraitLightingOn:Z

    if-eqz v0, :cond_2

    .line 2766
    return v1

    .line 2769
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveShotOn()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 2770
    return v2

    .line 2774
    :cond_3
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGradienterOn()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 2775
    return v1

    .line 2779
    :cond_4
    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 2780
    return v2

    .line 2783
    :cond_5
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v3, 0xa7

    if-ne v0, v3, :cond_6

    .line 2784
    return v1

    .line 2787
    :cond_6
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v3, 0xad

    if-ne v0, v3, :cond_7

    .line 2788
    return v1

    .line 2792
    :cond_7
    invoke-static {}, Lcom/android/camera/CameraSettings;->showGenderAge()Z

    move-result v0

    if-nez v0, :cond_c

    invoke-static {}, Lcom/android/camera/CameraSettings;->isMagicMirrorOn()Z

    move-result v0

    if-eqz v0, :cond_8

    goto :goto_0

    .line 2797
    :cond_8
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    if-eqz v0, :cond_9

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 2798
    invoke-static {v0}, Lcom/android/camera/CameraSettings;->isSupportedUltraPixelPhotography(Lcom/android/camera2/CameraCapabilities;)Z

    move-result v0

    if-eqz v0, :cond_9

    .line 2799
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_9

    .line 2800
    return v1

    .line 2804
    :cond_9
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_a

    .line 2805
    return v1

    .line 2809
    :cond_a
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_b

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isNeedPreviewThumbnail()Z

    move-result v0

    if-eqz v0, :cond_b

    move v1, v2

    nop

    :cond_b
    return v1

    .line 2793
    :cond_c
    :goto_0
    return v1
.end method

.method private enterAsdScene(I)V
    .locals 5

    .line 5248
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 5249
    const/16 v1, 0xac

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 5250
    const/4 v1, 0x0

    if-eqz p1, :cond_2

    const/16 v2, 0x9

    if-eq p1, v2, :cond_0

    const/4 v0, 0x4

    const/4 v2, 0x6

    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 5264
    :pswitch_0
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez p1, :cond_3

    .line 5265
    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->setPortraitSuccessHintVisible(I)V

    goto :goto_0

    .line 5283
    :pswitch_1
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez p1, :cond_3

    .line 5284
    const p1, 0x7f09019c

    invoke-virtual {p0, v2, p1, v0}, Lcom/android/camera/module/Camera2Module;->updateTipMessage(III)V

    goto :goto_0

    .line 5276
    :pswitch_2
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez p1, :cond_3

    .line 5277
    const p1, 0x7f09019e

    invoke-virtual {p0, v2, p1, v0}, Lcom/android/camera/module/Camera2Module;->updateTipMessage(III)V

    goto :goto_0

    .line 5269
    :pswitch_3
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez p1, :cond_3

    .line 5270
    const p1, 0x7f09019d

    invoke-virtual {p0, v2, p1, v0}, Lcom/android/camera/module/Camera2Module;->updateTipMessage(III)V

    goto :goto_0

    .line 5255
    :cond_0
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentFlash()Lcom/android/camera/data/data/config/ComponentConfigFlash;

    move-result-object p1

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {p1, v2}, Lcom/android/camera/data/data/config/ComponentConfigFlash;->getComponentValue(I)Ljava/lang/String;

    move-result-object p1

    .line 5256
    const-string v2, "3"

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    const/16 v3, 0xa

    const/4 v4, 0x1

    if-eqz v2, :cond_1

    .line 5257
    new-array p1, v4, [I

    aput v3, p1, v1

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    goto :goto_0

    .line 5258
    :cond_1
    const-string v2, "103"

    invoke-virtual {v2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 5259
    invoke-interface {v0, v1, v1, v1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertFlash(IZZ)V

    .line 5260
    new-array p1, v4, [I

    aput v3, p1, v1

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    goto :goto_0

    .line 5252
    :cond_2
    invoke-interface {v0, v1, v1, v1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertFlash(IZZ)V

    .line 5253
    nop

    .line 5290
    :cond_3
    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private exitAsdScene(I)V
    .locals 5

    .line 5211
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 5212
    const/16 v1, 0xac

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 5213
    const/16 v1, 0x8

    const/4 v2, 0x0

    if-eqz p1, :cond_2

    const/16 v3, 0x9

    if-eq p1, v3, :cond_0

    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 5238
    :pswitch_0
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez p1, :cond_3

    .line 5239
    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->setPortraitSuccessHintVisible(I)V

    goto :goto_0

    .line 5233
    :pswitch_1
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez p1, :cond_3

    .line 5234
    invoke-virtual {p0, v2}, Lcom/android/camera/module/Camera2Module;->hideTipMessage(I)V

    goto :goto_0

    .line 5224
    :cond_0
    const-string p1, "103"

    .line 5225
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v3

    invoke-virtual {v3}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentFlash()Lcom/android/camera/data/data/config/ComponentConfigFlash;

    move-result-object v3

    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v3, v4}, Lcom/android/camera/data/data/config/ComponentConfigFlash;->getComponentValue(I)Ljava/lang/String;

    move-result-object v3

    .line 5224
    invoke-virtual {p1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 5226
    invoke-interface {v0, v1, v2, v2}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertFlash(IZZ)V

    .line 5228
    :cond_1
    const/4 p1, 0x1

    new-array p1, p1, [I

    const/16 v0, 0xa

    aput v0, p1, v2

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 5229
    goto :goto_0

    .line 5216
    :cond_2
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentFlash()Lcom/android/camera/data/data/config/ComponentConfigFlash;

    move-result-object p1

    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {p1, v3}, Lcom/android/camera/data/data/config/ComponentConfigFlash;->getComponentValue(I)Ljava/lang/String;

    move-result-object p1

    .line 5217
    const-string v3, "1"

    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    const-string v3, "101"

    .line 5218
    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    const-string v3, "2"

    .line 5219
    invoke-virtual {v3, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_3

    .line 5220
    invoke-interface {v0, v1, v2, v2}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertFlash(IZZ)V

    .line 5244
    :cond_3
    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x4
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private getCalibrationDataFileName(I)Ljava/lang/String;
    .locals 1

    .line 3281
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3282
    const-string p1, "front_dual_camera_caldata.bin"

    return-object p1

    .line 3284
    :cond_0
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getUltraWideBokehCameraId()I

    move-result v0

    if-ne p1, v0, :cond_1

    .line 3285
    const-string p1, "back_dual_camera_caldata_wu.bin"

    return-object p1

    .line 3287
    :cond_1
    const-string p1, "back_dual_camera_caldata.bin"

    return-object p1
.end method

.method private getCountDownTimes(I)I
    .locals 3

    .line 1028
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBroadcastIntent:Landroid/content/Intent;

    if-eqz v0, :cond_0

    .line 1029
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBroadcastIntent:Landroid/content/Intent;

    invoke-static {v0}, Lcom/android/camera/CameraIntentManager;->getInstance(Landroid/content/Intent;)Lcom/android/camera/CameraIntentManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->getTimerDurationSeconds()I

    move-result v0

    goto :goto_0

    .line 1031
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraIntentManager()Lcom/android/camera/CameraIntentManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->getTimerDurationSeconds()I

    move-result v0

    .line 1033
    :goto_0
    const/4 v1, -0x1

    const/4 v2, 0x3

    if-eq v0, v1, :cond_4

    .line 1035
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mBroadcastIntent:Landroid/content/Intent;

    if-eqz p1, :cond_1

    .line 1036
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mBroadcastIntent:Landroid/content/Intent;

    const-string v1, "android.intent.extra.TIMER_DURATION_SECONDS"

    invoke-virtual {p1, v1}, Landroid/content/Intent;->removeExtra(Ljava/lang/String;)V

    goto :goto_1

    .line 1038
    :cond_1
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v1, "android.intent.extra.TIMER_DURATION_SECONDS"

    invoke-virtual {p1, v1}, Landroid/content/Intent;->removeExtra(Ljava/lang/String;)V

    .line 1040
    :goto_1
    if-eqz v0, :cond_3

    const/4 p1, 0x5

    if-eq v0, p1, :cond_2

    .line 1046
    return v2

    .line 1044
    :cond_2
    return p1

    .line 1042
    :cond_3
    const/4 p1, 0x0

    return p1

    .line 1048
    :cond_4
    const/16 v0, 0x64

    if-ne p1, v0, :cond_6

    invoke-static {}, Lcom/android/camera/CameraSettings;->isHangGestureOpen()Z

    move-result p1

    if-eqz p1, :cond_6

    .line 1049
    invoke-static {}, Lcom/android/camera/CameraSettings;->getCountDownTimes()I

    move-result p1

    .line 1050
    if-eqz p1, :cond_5

    goto :goto_2

    :cond_5
    move p1, v2

    :goto_2
    return p1

    .line 1052
    :cond_6
    invoke-static {}, Lcom/android/camera/CameraSettings;->getCountDownTimes()I

    move-result p1

    return p1
.end method

.method private getCurrentAiSceneName()Ljava/lang/String;
    .locals 4

    .line 5813
    nop

    .line 5814
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5815
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v2, 0xa3

    if-eq v1, v2, :cond_1

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v2, 0xa7

    if-ne v1, v2, :cond_0

    goto :goto_0

    .line 5829
    :cond_0
    const/4 v0, 0x0

    goto :goto_3

    .line 5816
    :cond_1
    :goto_0
    const-string v1, "off"

    .line 5817
    invoke-static {}, Lcom/android/camera/CameraSettings;->getAiSceneOpen()Z

    move-result v2

    if-eqz v2, :cond_5

    .line 5818
    const-string v1, "\u672a\u77e5"

    .line 5819
    const/4 v2, -0x1

    if-ne v0, v2, :cond_3

    .line 5820
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isSilhouette:Z

    if-eqz v0, :cond_2

    const/16 v0, 0x18

    goto :goto_1

    :cond_2
    const/16 v0, 0x17

    .line 5822
    :cond_3
    :goto_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const/high16 v3, 0x7f0e0000

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->obtainTypedArray(I)Landroid/content/res/TypedArray;

    move-result-object v2

    .line 5823
    if-ltz v0, :cond_4

    invoke-virtual {v2}, Landroid/content/res/TypedArray;->length()I

    move-result v3

    if-ge v0, v3, :cond_4

    .line 5824
    invoke-virtual {v2, v0}, Landroid/content/res/TypedArray;->getString(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_2

    .line 5826
    :cond_4
    move-object v0, v1

    :goto_2
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->recycle()V

    goto :goto_3

    .line 5829
    :cond_5
    move-object v0, v1

    :goto_3
    return-object v0
.end method

.method private getDualWaterMarkParam()Lcom/android/camera/effect/renders/DualWatermarkParam;
    .locals 7

    .line 1431
    new-instance v6, Lcom/android/camera/effect/renders/DualWatermarkParam;

    .line 1432
    invoke-static {}, Lcom/android/camera/CameraSettings;->isDualCameraWaterMarkOpen()Z

    move-result v1

    .line 1433
    invoke-static {}, Lcom/android/camera/CameraSettings;->getDualCameraWaterMarkFilePathVendor()Ljava/lang/String;

    move-result-object v2

    .line 1434
    const/4 v0, 0x0

    const v3, 0x7f0a00ad

    invoke-static {v3, v0}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v3

    .line 1435
    const v4, 0x7f0a00ae

    invoke-static {v4, v0}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v4

    .line 1436
    const v5, 0x7f0a00af

    invoke-static {v5, v0}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v5

    move-object v0, v6

    invoke-direct/range {v0 .. v5}, Lcom/android/camera/effect/renders/DualWatermarkParam;-><init>(ZLjava/lang/String;FFF)V

    .line 1431
    return-object v6
.end method

.method private getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 4911
    invoke-static {}, Lcom/android/camera/preferences/CameraSettingPreferences;->instance()Lcom/android/camera/preferences/CameraSettingPreferences;

    move-result-object v0

    .line 4912
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isManualModule()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 4913
    invoke-virtual {v0, p1, p2}, Lcom/android/camera/preferences/CameraSettingPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    .line 4912
    :cond_0
    return-object p2
.end method

.method private getMaxPictureSize(II)Lcom/android/camera/CameraSize;
    .locals 1

    .line 3661
    nop

    .line 3662
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getCapabilities(I)Lcom/android/camera2/CameraCapabilities;

    move-result-object p1

    .line 3663
    if-eqz p1, :cond_0

    .line 3664
    invoke-virtual {p1, p2}, Lcom/android/camera2/CameraCapabilities;->getSupportedOutputSize(I)Ljava/util/List;

    move-result-object p1

    .line 3665
    invoke-static {p1}, Lcom/android/camera/PictureSizeManager;->getBestPictureSize(Ljava/util/List;)Lcom/android/camera/CameraSize;

    move-result-object p1

    goto :goto_0

    .line 3667
    :cond_0
    const/4 p1, 0x0

    :goto_0
    return-object p1
.end method

.method private getPictureInfo()Lcom/xiaomi/camera/core/PictureInfo;
    .locals 2

    .line 5799
    new-instance v0, Lcom/xiaomi/camera/core/PictureInfo;

    invoke-direct {v0}, Lcom/xiaomi/camera/core/PictureInfo;-><init>()V

    .line 5800
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isFrontMirror()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/camera/core/PictureInfo;->setFrontMirror(Z)Lcom/xiaomi/camera/core/PictureInfo;

    .line 5801
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/camera/core/PictureInfo;->setSensorType(Z)Lcom/xiaomi/camera/core/PictureInfo;

    .line 5802
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBokehFrontCamera()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/camera/core/PictureInfo;->setBokehFrontCamera(Z)Lcom/xiaomi/camera/core/PictureInfo;

    .line 5804
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 5805
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    invoke-virtual {v0, v1}, Lcom/xiaomi/camera/core/PictureInfo;->setAiEnabled(Z)Lcom/xiaomi/camera/core/PictureInfo;

    .line 5806
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    invoke-virtual {v0, v1}, Lcom/xiaomi/camera/core/PictureInfo;->setAiType(I)Lcom/xiaomi/camera/core/PictureInfo;

    .line 5808
    :cond_0
    invoke-virtual {v0}, Lcom/xiaomi/camera/core/PictureInfo;->end()V

    .line 5809
    return-object v0
.end method

.method private getRequestFlashMode()Ljava/lang/String;
    .locals 3

    .line 4457
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isSupportSceneMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 4458
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    invoke-static {v0}, Lcom/android/camera/CameraSettings;->getFlashModeByScene(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 4459
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 4460
    return-object v0

    .line 4464
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isSupportedFlashOn()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isSupportedTorch()Z

    move-result v0

    if-nez v0, :cond_1

    .line 4465
    const-string v0, "0"

    return-object v0

    .line 4467
    :cond_1
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentFlash()Lcom/android/camera/data/data/config/ComponentConfigFlash;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/config/ComponentConfigFlash;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    .line 4468
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    const/16 v2, 0x9

    if-ne v1, v2, :cond_3

    .line 4469
    const-string v1, "3"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 4470
    const-string v0, "2"

    return-object v0

    .line 4471
    :cond_2
    const-string v1, "103"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 4472
    const-string v0, "101"

    return-object v0

    .line 4475
    :cond_3
    return-object v0
.end method

.method private getSubPictureSize(IZ)Lcom/android/camera/CameraSize;
    .locals 2

    .line 3653
    if-eqz p2, :cond_0

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getAuxFrontCameraId()I

    move-result v0

    goto :goto_0

    .line 3654
    :cond_0
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getAuxCameraId()I

    move-result v0

    .line 3655
    :goto_0
    invoke-direct {p0, v0, p1}, Lcom/android/camera/module/Camera2Module;->getMaxPictureSize(II)Lcom/android/camera/CameraSize;

    move-result-object p1

    .line 3656
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    if-eqz p2, :cond_1

    const-string p2, "frontSubSize: "

    goto :goto_1

    :cond_1
    const-string p2, "backSubSize: "

    :goto_1
    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3657
    return-object p1
.end method

.method private getSuffix()Ljava/lang/String;
    .locals 1

    .line 4449
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isNormal()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4450
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->getSuffix()Ljava/lang/String;

    move-result-object v0

    return-object v0

    .line 4452
    :cond_0
    const-string v0, ""

    return-object v0
.end method

.method private static getTiltShiftMode()Ljava/lang/String;
    .locals 2

    .line 1440
    nop

    .line 1441
    invoke-static {}, Lcom/android/camera/CameraSettings;->isTiltShiftOn()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1443
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/runing/DataItemRunning;->getComponentRunningTiltValue()Lcom/android/camera/data/data/runing/ComponentRunningTiltValue;

    move-result-object v0

    .line 1444
    const/16 v1, 0xa0

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/ComponentRunningTiltValue;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 1446
    :cond_0
    const/4 v0, 0x0

    :goto_0
    return-object v0
.end method

.method private handleUpdateFaceView(ZZ)V
    .locals 6

    .line 972
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v3

    .line 973
    if-eqz p1, :cond_1

    .line 975
    iget-boolean p2, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    if-nez p2, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFaceBeautyMode()Z

    move-result p2

    if-eqz p2, :cond_2

    :cond_0
    const/4 p2, 0x1

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 976
    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->getFocusMode()I

    move-result v0

    if-eq p2, v0, :cond_2

    .line 977
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    const/4 v2, 0x1

    const/4 v4, 0x1

    iget v5, p0, Lcom/android/camera/module/Camera2Module;->mCameraDisplayOrientation:I

    move v1, p1

    invoke-interface/range {v0 .. v5}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->updateFaceView(ZZZZI)V

    goto :goto_0

    .line 981
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    const/4 v4, 0x0

    const/4 v5, -0x1

    move v1, p1

    move v2, p2

    invoke-interface/range {v0 .. v5}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->updateFaceView(ZZZZI)V

    .line 983
    :cond_2
    :goto_0
    return-void
.end method

.method private hidePostCaptureAlert()V
    .locals 2

    .line 3128
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->enableCameraControls(Z)V

    .line 3129
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1}, Lcom/android/camera2/Camera2Proxy;->isSessionReady()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 3130
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->resumePreview()V

    goto :goto_0

    .line 3132
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->startPreview()V

    .line 3134
    :goto_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setEffectViewVisible(Z)V

    .line 3138
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa0

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 3140
    const/4 v1, 0x6

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->delegateEvent(I)V

    .line 3142
    return-void
.end method

.method private hideSceneSelector()V
    .locals 2

    .line 800
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    sget-object v1, Lcom/android/camera/module/-$$Lambda$Camera2Module$6MhBAG8t9gKD6JetAb6jENHe1rY;->INSTANCE:Lcom/android/camera/module/-$$Lambda$Camera2Module$6MhBAG8t9gKD6JetAb6jENHe1rY;

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 807
    return-void
.end method

.method private initAiSceneParser()V
    .locals 2

    .line 5335
    new-instance v0, Lcom/android/camera/module/loader/FunctionParseAiScene;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-direct {v0, v1}, Lcom/android/camera/module/loader/FunctionParseAiScene;-><init>(I)V

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFunctionParseAiScene:Lcom/android/camera/module/loader/FunctionParseAiScene;

    .line 5336
    new-instance v0, Lcom/android/camera/module/Camera2Module$28;

    invoke-direct {v0, p0}, Lcom/android/camera/module/Camera2Module$28;-><init>(Lcom/android/camera/module/Camera2Module;)V

    sget-object v1, Lio/reactivex/BackpressureStrategy;->DROP:Lio/reactivex/BackpressureStrategy;

    .line 5337
    invoke-static {v0, v1}, Lio/reactivex/Flowable;->create(Lio/reactivex/FlowableOnSubscribe;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;

    move-result-object v0

    sget-object v1, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 5344
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Flowable;

    move-result-object v0

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFunctionParseAiScene:Lcom/android/camera/module/loader/FunctionParseAiScene;

    .line 5347
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/loader/PredicateFilterAiScene;

    invoke-direct {v1, p0}, Lcom/android/camera/module/loader/PredicateFilterAiScene;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 5348
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->filter(Lio/reactivex/functions/Predicate;)Lio/reactivex/Flowable;

    move-result-object v0

    .line 5349
    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v1

    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/Camera2Module$27;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$27;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 5350
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneDisposable:Lio/reactivex/disposables/Disposable;

    .line 5356
    return-void
.end method

.method private initFlashAutoStateForTrack(Z)V
    .locals 2

    .line 5785
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFlashAutoModeState:Ljava/lang/String;

    .line 5786
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentFlash()Lcom/android/camera/data/data/config/ComponentConfigFlash;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/config/ComponentConfigFlash;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    .line 5787
    const-string v1, "3"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    const-string v1, "103"

    .line 5788
    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 5789
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    const/16 v1, 0x9

    if-eq v0, v1, :cond_2

    if-eqz p1, :cond_1

    goto :goto_0

    .line 5793
    :cond_1
    const-string p1, "auto-off"

    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFlashAutoModeState:Ljava/lang/String;

    goto :goto_1

    .line 5791
    :cond_2
    :goto_0
    const-string p1, "auto-on"

    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFlashAutoModeState:Ljava/lang/String;

    .line 5796
    :cond_3
    :goto_1
    return-void
.end method

.method private initMetaParser()V
    .locals 4

    .line 5150
    new-instance v0, Lcom/android/camera/module/Camera2Module$26;

    invoke-direct {v0, p0}, Lcom/android/camera/module/Camera2Module$26;-><init>(Lcom/android/camera/module/Camera2Module;)V

    sget-object v1, Lio/reactivex/BackpressureStrategy;->DROP:Lio/reactivex/BackpressureStrategy;

    .line 5151
    invoke-static {v0, v1}, Lio/reactivex/Flowable;->create(Lio/reactivex/FlowableOnSubscribe;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;

    move-result-object v0

    sget-object v1, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 5157
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/loader/FunctionParseAsdFace;

    .line 5159
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v2

    invoke-direct {v1, p0, v2}, Lcom/android/camera/module/loader/FunctionParseAsdFace;-><init>(Lcom/android/camera2/Camera2Proxy$FaceDetectionCallback;Z)V

    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/loader/FunctionParseAsdHdr;

    invoke-direct {v1, p0}, Lcom/android/camera/module/loader/FunctionParseAsdHdr;-><init>(Lcom/android/camera2/Camera2Proxy$HDRCheckerCallback;)V

    .line 5161
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/loader/FunctionParseAsdUltraWide;

    invoke-direct {v1, p0}, Lcom/android/camera/module/loader/FunctionParseAsdUltraWide;-><init>(Lcom/android/camera2/Camera2Proxy$UltraWideCheckCallback;)V

    .line 5163
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object v0

    sget-object v1, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    .line 5167
    const-wide/16 v2, 0x1f4

    invoke-virtual {v0, v2, v3, v1}, Lio/reactivex/Flowable;->sample(JLjava/util/concurrent/TimeUnit;)Lio/reactivex/Flowable;

    move-result-object v0

    sget-object v1, Lcom/android/camera/constant/GlobalConstant;->sCameraSetupScheduler:Lio/reactivex/Scheduler;

    .line 5168
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/loader/FunctionParseAsdScene;

    invoke-direct {v1, p0}, Lcom/android/camera/module/loader/FunctionParseAsdScene;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 5183
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Flowable;

    move-result-object v0

    .line 5185
    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v1

    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Flowable;

    move-result-object v0

    .line 5186
    invoke-virtual {v0}, Lio/reactivex/Flowable;->onTerminateDetach()Lio/reactivex/Flowable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/Camera2Module$25;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$25;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 5187
    invoke-virtual {v0, v1}, Lio/reactivex/Flowable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataDisposable:Lio/reactivex/disposables/Disposable;

    .line 5193
    return-void
.end method

.method private initializeFocusManager()V
    .locals 4

    .line 3305
    new-instance v0, Lcom/android/camera/module/loader/camera2/FocusManager2;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 3308
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v2

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 3309
    invoke-virtual {v3}, Lcom/android/camera/Camera;->getMainLooper()Landroid/os/Looper;

    move-result-object v3

    invoke-direct {v0, v1, p0, v2, v3}, Lcom/android/camera/module/loader/camera2/FocusManager2;-><init>(Lcom/android/camera2/CameraCapabilities;Lcom/android/camera/module/loader/camera2/FocusManager2$Listener;ZLandroid/os/Looper;)V

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    .line 3312
    nop

    .line 3313
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 3314
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getRenderRect()Landroid/graphics/Rect;

    move-result-object v0

    goto :goto_0

    .line 3316
    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v1

    if-lez v1, :cond_1

    .line 3317
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v2}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v2

    invoke-virtual {v2}, Lcom/android/camera/CameraScreenNail;->getRenderWidth()I

    move-result v2

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 3318
    invoke-virtual {v3}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v3

    invoke-virtual {v3}, Lcom/android/camera/CameraScreenNail;->getRenderHeight()I

    move-result v3

    .line 3317
    invoke-virtual {v1, v2, v3}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setRenderSize(II)V

    .line 3319
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0}, Landroid/graphics/Rect;->width()I

    move-result v2

    invoke-virtual {v0}, Landroid/graphics/Rect;->height()I

    move-result v0

    invoke-virtual {v1, v2, v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setPreviewSize(II)V

    goto :goto_1

    .line 3324
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    sget v1, Lcom/android/camera/Util;->sWindowWidth:I

    sget v2, Lcom/android/camera/Util;->sWindowHeight:I

    invoke-virtual {v0, v1, v2}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setRenderSize(II)V

    .line 3325
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    sget v1, Lcom/android/camera/Util;->sWindowWidth:I

    sget v2, Lcom/android/camera/Util;->sWindowHeight:I

    invoke-virtual {v0, v1, v2}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setPreviewSize(II)V

    .line 3327
    :goto_1
    return-void
.end method

.method private is3ALocked()Z
    .locals 1

    .line 4674
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    return v0
.end method

.method private isEnableQcfa()Z
    .locals 2

    .line 2452
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isSupportedQcfa()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2453
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2454
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    const/16 v1, 0xa3

    if-eq v0, v1, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    const/16 v1, 0xa5

    if-ne v0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 2452
    :goto_0
    return v0
.end method

.method private isFaceBeautyOn(Lcom/android/camera/fragment/beauty/BeautyValues;)Z
    .locals 3

    .line 5761
    const/4 v0, 0x0

    if-nez p1, :cond_0

    .line 5762
    return v0

    .line 5765
    :cond_0
    invoke-static {}, Lcom/mi/config/b;->hM()Z

    move-result v1

    const/4 v2, 0x1

    if-eqz v1, :cond_4

    .line 5766
    invoke-static {}, Lcom/android/camera/CameraSettings;->isAdvancedBeautyOn()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 5767
    iget v1, p1, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautySkinSmooth:I

    if-gtz v1, :cond_2

    iget v1, p1, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautySkinColor:I

    if-gtz v1, :cond_2

    iget p1, p1, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautySlimFace:I

    if-lez p1, :cond_1

    goto :goto_0

    :cond_1
    goto :goto_1

    :cond_2
    :goto_0
    move v0, v2

    :goto_1
    return v0

    .line 5771
    :cond_3
    sget-object v0, Lcom/android/camera/constant/BeautyConstant;->LEVEL_CLOSE:Ljava/lang/String;

    iget-object p1, p1, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    xor-int/2addr p1, v2

    return p1

    .line 5774
    :cond_4
    sget-object v0, Lcom/android/camera/constant/BeautyConstant;->LEVEL_CLOSE:Ljava/lang/String;

    iget-object p1, p1, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    xor-int/2addr p1, v2

    return p1
.end method

.method private isFrontMirror()Z
    .locals 1

    .line 3095
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-nez v0, :cond_0

    .line 3096
    const/4 v0, 0x0

    return v0

    .line 3100
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveShotOn()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 3101
    const/4 v0, 0x1

    return v0

    .line 3103
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontMirror()Z

    move-result v0

    return v0
.end method

.method private isInCountDown()Z
    .locals 1

    .line 1785
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->isDisposed()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private isLaunchedByMainIntent()Z
    .locals 2

    .line 2014
    nop

    .line 2015
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_0

    .line 2016
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getIntent()Landroid/content/Intent;

    move-result-object v0

    .line 2017
    if-eqz v0, :cond_0

    .line 2018
    invoke-virtual {v0}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 2021
    :cond_0
    const/4 v0, 0x0

    :goto_0
    const-string v1, "android.intent.action.MAIN"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method private isNeedRawStream()Z
    .locals 4

    .line 2436
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isManualModule()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/android/camera/CameraSettings;->isEnableDNG()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2437
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    const/16 v1, 0x25

    .line 2438
    invoke-virtual {v0, v1}, Lcom/android/camera2/CameraCapabilities;->getSupportedOutputSize(I)Ljava/util/List;

    move-result-object v0

    .line 2439
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/android/camera/CameraSize;

    .line 2440
    iget v2, v1, Lcom/android/camera/CameraSize;->width:I

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    iget v3, v3, Lcom/android/camera/CameraSize;->width:I

    if-ne v2, v3, :cond_0

    iget v1, v1, Lcom/android/camera/CameraSize;->height:I

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    iget v2, v2, Lcom/android/camera/CameraSize;->height:I

    if-ne v1, v2, :cond_0

    .line 2441
    const/4 v0, 0x1

    return v0

    .line 2443
    :cond_0
    goto :goto_0

    .line 2445
    :cond_1
    const/4 v0, 0x0

    return v0
.end method

.method private isParallelQueueFull()Z
    .locals 3

    .line 2683
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 2684
    return v1

    .line 2687
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/storage/ImageSaver;->isSaveQueueFull()Z

    move-result v0

    .line 2688
    if-eqz v0, :cond_1

    .line 2689
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "isParallelQueueFull: ImageSaver queue is full"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2690
    const/4 v0, 0x1

    return v0

    .line 2693
    :cond_1
    nop

    .line 2694
    invoke-static {}, Lcom/android/camera/parallel/AlgoConnector;->getInstance()Lcom/android/camera/parallel/AlgoConnector;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/parallel/AlgoConnector;->getLocalBinder()Lcom/android/camera/LocalParallelService$LocalBinder;

    move-result-object v0

    .line 2695
    if-eqz v0, :cond_2

    .line 2696
    invoke-virtual {v0}, Lcom/android/camera/LocalParallelService$LocalBinder;->needWaitProcess()Z

    move-result v1

    goto :goto_0

    .line 2698
    :cond_2
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v2, "isParallelQueueFull: NOTICE: CHECK WHY BINDER IS NULL!"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 2701
    :goto_0
    return v1
.end method

.method private isParallelSessionEnable()Z
    .locals 2

    .line 3590
    invoke-static {}, Lcom/android/camera/CameraSettings;->isCameraParallelProcessEnable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3591
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    const/16 v1, 0xad

    if-eq v0, v1, :cond_0

    .line 3592
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    const/16 v1, 0xa7

    if-eq v0, v1, :cond_0

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 3590
    :goto_0
    return v0
.end method

.method private isPortraitSuccessHintShowing()Z
    .locals 2

    .line 5294
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 5295
    const/16 v1, 0xaf

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;

    .line 5296
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;->isPortraitHintVisible()Z

    move-result v0

    return v0
.end method

.method private isTriggerQcfaModeChange(ZZ)Z
    .locals 5

    .line 685
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isSupportedQcfa()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 686
    return v1

    .line 688
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v2, 0xab

    if-ne v0, v2, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBokehFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 689
    return v1

    .line 692
    :cond_1
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fS()I

    move-result v0

    if-lez v0, :cond_2

    .line 693
    return v1

    .line 695
    :cond_2
    invoke-static {}, Lcom/android/camera/fragment/beauty/BeautyParameters;->isFaceBeautyOn()Z

    move-result v0

    .line 696
    const v2, 0x8005

    const v3, 0x8007

    const/4 v4, 0x1

    if-eqz p1, :cond_5

    .line 697
    sget-boolean p1, Lcom/android/camera/module/Camera2Module;->mIsBeautyFrontOn:Z

    if-eq p1, v0, :cond_8

    .line 700
    if-eqz v0, :cond_3

    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    if-ne p1, v3, :cond_3

    .line 701
    return v4

    .line 703
    :cond_3
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentHdr()Lcom/android/camera/data/data/config/ComponentConfigHdr;

    move-result-object p1

    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {p1, p2}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->getComponentValue(I)Ljava/lang/String;

    move-result-object p1

    .line 705
    if-nez v0, :cond_4

    const-string p2, "off"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    if-ne p1, v2, :cond_4

    .line 707
    return v4

    .line 709
    :cond_4
    goto :goto_0

    .line 710
    :cond_5
    if-eqz p2, :cond_8

    if-nez v0, :cond_8

    .line 711
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentHdr()Lcom/android/camera/data/data/config/ComponentConfigHdr;

    move-result-object p1

    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {p1, p2}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->getComponentValue(I)Ljava/lang/String;

    move-result-object p1

    .line 712
    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    if-ne p2, v2, :cond_6

    const-string p2, "off"

    .line 713
    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_7

    :cond_6
    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    if-ne p2, v3, :cond_8

    const-string p2, "off"

    .line 715
    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_8

    .line 716
    :cond_7
    return v4

    .line 719
    :cond_8
    :goto_0
    return v1
.end method

.method private isUseSwMfnr()Z
    .locals 6

    .line 4189
    invoke-static {}, Lcom/android/camera/CameraSettings;->isMfnrSatEnable()Z

    move-result v0

    .line 4190
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v1

    invoke-virtual {v1}, Lcom/mi/config/a;->eY()Z

    move-result v1

    .line 4191
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result v2

    const/4 v3, 0x0

    if-eqz v2, :cond_0

    .line 4192
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "GroupShot is on"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4193
    return v3

    .line 4197
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isUltraWideBackCamera()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 4198
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "SwMfnr force off for ultra wide camera"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4199
    return v3

    .line 4201
    :cond_1
    if-nez v0, :cond_2

    .line 4202
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "Mfnr not enabled"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4203
    return v3

    .line 4205
    :cond_2
    if-nez v1, :cond_3

    .line 4206
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "SwMfnr is not supported"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4207
    return v3

    .line 4209
    :cond_3
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v2}, Lcom/android/camera/MutexModeManager;->isNormal()Z

    move-result v2

    if-nez v2, :cond_4

    .line 4210
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "Mutex mode is not normal"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4211
    return v3

    .line 4214
    :cond_4
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v2

    invoke-virtual {v2}, Lcom/mi/config/a;->eZ()Z

    move-result v2

    const/4 v4, 0x1

    if-eqz v2, :cond_5

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v5, 0xa7

    if-eq v2, v5, :cond_5

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v5, 0xad

    if-eq v2, v5, :cond_5

    .line 4217
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "For the devices does not have hardware MFNR, use software MFNR"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4218
    return v4

    .line 4220
    :cond_5
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v2

    if-eqz v2, :cond_7

    .line 4221
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualFrontCamera()Z

    move-result v2

    if-nez v2, :cond_7

    .line 4222
    invoke-static {}, Lcom/mi/config/b;->hT()Z

    move-result v2

    if-eqz v2, :cond_7

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    const v5, 0x8005

    if-eq v2, v5, :cond_6

    .line 4226
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v2

    if-eqz v2, :cond_7

    .line 4227
    :cond_6
    move v3, v4

    goto :goto_0

    .line 4226
    :cond_7
    nop

    .line 4227
    :goto_0
    sget-object v2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "isUseSwMfnr: supportSwMfnr = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", isFrontCamera = "

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 4228
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v1

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", isMfnrEnabled = "

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", result = "

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 4227
    invoke-static {v2, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4231
    return v3
.end method

.method private keepScreenOnAwhile()V
    .locals 4

    .line 4930
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x11

    const-wide/16 v2, 0x3e8

    invoke-virtual {v0, v1, v2, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    .line 4931
    return-void
.end method

.method static synthetic lambda$hideSceneSelector$0()V
    .locals 2

    .line 801
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 802
    const/16 v1, 0xac

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 803
    if-eqz v0, :cond_0

    .line 804
    const/16 v1, 0x8

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertAiSceneSelector(I)V

    .line 806
    :cond_0
    return-void
.end method

.method private lockAEAF()V
    .locals 2

    .line 4665
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "lockAEAF"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4666
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    .line 4667
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setAeAwbLock(Z)V

    .line 4670
    :cond_0
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    .line 4671
    return-void
.end method

.method private needShowThumbProgressImmediately()Z
    .locals 4

    .line 5049
    const-string v0, "pref_qc_camera_exposuretime_key"

    .line 5050
    const v1, 0x7f090136

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 5049
    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    const-wide/32 v2, 0xee6b280

    cmp-long v0, v0, v2

    if-lez v0, :cond_0

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xad

    if-eq v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private onBeautyParameterChanged()V
    .locals 3

    .line 723
    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-direct {p0, v1, v0}, Lcom/android/camera/module/Camera2Module;->isTriggerQcfaModeChange(ZZ)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 724
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->restartModule()V

    goto :goto_0

    .line 725
    :cond_0
    invoke-static {}, Lcom/mi/config/b;->hT()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 726
    const/4 v0, 0x3

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    goto :goto_0

    .line 728
    :cond_1
    new-array v1, v1, [I

    const/16 v2, 0xd

    aput v2, v1, v0

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 730
    :goto_0
    invoke-static {}, Lcom/android/camera/fragment/beauty/BeautyParameters;->isFaceBeautyOn()Z

    move-result v0

    sput-boolean v0, Lcom/android/camera/module/Camera2Module;->mIsBeautyFrontOn:Z

    .line 731
    invoke-static {}, Lcom/android/camera/CameraSettings;->isBeautyBodySlimOn()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsBeautyBodySlimOn:Z

    .line 732
    return-void

    :array_0
    .array-data 4
        0xd
        0x22
        0x2a
    .end array-data
.end method

.method private onFilterChanged()V
    .locals 4

    .line 580
    const/4 v0, 0x1

    new-array v1, v0, [I

    const/4 v2, 0x0

    const/4 v3, 0x2

    aput v3, v1, v2

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceTrampoline([I)V

    .line 582
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v3, 0xac

    invoke-virtual {v1, v3}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 583
    new-array v0, v0, [I

    const/16 v3, 0xc4

    aput v3, v0, v2

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->updateConfigItem([I)V

    .line 584
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->updateEffectViewVisible()V

    .line 585
    return-void
.end method

.method private onShutter()V
    .locals 4

    .line 2992
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-nez v0, :cond_0

    .line 2993
    return-void

    .line 2995
    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mShutterCallbackTime:J

    .line 2996
    iget-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mShutterCallbackTime:J

    iget-wide v2, p0, Lcom/android/camera/module/Camera2Module;->mCaptureStartTime:J

    sub-long/2addr v0, v2

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mShutterLag:J

    .line 2997
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "mShutterLag = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lcom/android/camera/module/Camera2Module;->mShutterLag:J

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, "ms"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 2999
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    if-nez v0, :cond_2

    .line 3000
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->enablePreviewAsThumbnail()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 3001
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnabledPreviewThumbnail:Z

    .line 3002
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->requestReadPixels()V

    goto :goto_0

    .line 3005
    :cond_1
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xad

    if-eq v0, v1, :cond_2

    .line 3006
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->updateThumbProgress(Z)V

    .line 3007
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->animateCapture()V

    .line 3008
    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->playCameraSound(I)V

    .line 3012
    :cond_2
    :goto_0
    return-void
.end method

.method private parseIntent()V
    .locals 2

    .line 2256
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraIntentManager()Lcom/android/camera/CameraIntentManager;

    move-result-object v0

    .line 2257
    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->isImageCaptureIntent()Z

    move-result v1

    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    .line 2259
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-nez v1, :cond_0

    .line 2260
    return-void

    .line 2262
    :cond_0
    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->getExtraSavedUri()Landroid/net/Uri;

    move-result-object v1

    iput-object v1, p0, Lcom/android/camera/module/Camera2Module;->mSaveUri:Landroid/net/Uri;

    .line 2263
    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->getExtraCropValue()Ljava/lang/String;

    move-result-object v1

    iput-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCropValue:Ljava/lang/String;

    .line 2264
    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->getExtraShouldSaveCapture()Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsSaveCaptureImage:Z

    .line 2265
    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->isQuickCapture()Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mQuickCapture:Z

    .line 2266
    return-void
.end method

.method private prepareEffectProcessor(I)V
    .locals 3

    .line 3754
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    if-nez v0, :cond_0

    .line 3755
    new-instance v0, Lcom/android/camera/effect/renders/SnapshotEffectRender;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    invoke-direct {v0, v1, v2}, Lcom/android/camera/effect/renders/SnapshotEffectRender;-><init>(Lcom/android/camera/ActivityBase;Z)V

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    .line 3756
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/android/camera/effect/renders/SnapshotEffectRender;->setImageSaver(Lcom/android/camera/storage/ImageSaver;)V

    .line 3757
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    invoke-virtual {v0, p1}, Lcom/android/camera/effect/renders/SnapshotEffectRender;->prepareEffectRender(I)V

    .line 3758
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    invoke-static {v0}, Lcom/android/camera/module/Camera2Module;->getJpegQuality(Z)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/android/camera/effect/renders/SnapshotEffectRender;->setQuality(I)V

    .line 3760
    :cond_0
    return-void
.end method

.method private prepareMultiCapture()V
    .locals 2

    .line 1182
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    .line 1183
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStopRequest:Z

    .line 1184
    invoke-static {}, Lcom/android/camera/Util;->clearMemoryLimit()V

    .line 1185
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->prepareNormalCapture()V

    .line 1186
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-static {}, Lcom/android/camera2/CameraCapabilities;->getBurstShootCount()I

    move-result v0

    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    .line 1187
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x31

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 1189
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->is3ALocked()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1190
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->onShutter()V

    .line 1192
    :cond_0
    return-void
.end method

.method private prepareNormalCapture()V
    .locals 5

    .line 1103
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "prepareNormalCapture"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1104
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isNeedFlashOn()Z

    move-result v0

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->initFlashAutoStateForTrack(Z)V

    .line 1105
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnabledPreviewThumbnail:Z

    .line 1106
    const/4 v1, 0x1

    iput v1, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    .line 1107
    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    .line 1108
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    iput-wide v1, p0, Lcom/android/camera/module/Camera2Module;->mCaptureStartTime:J

    .line 1109
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v1

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {v1, v2}, Lcom/android/camera/statistic/ScenarioTrackUtil;->trackCaptureTimeStart(ZI)V

    .line 1110
    iget-wide v1, p0, Lcom/android/camera/module/Camera2Module;->mCaptureStartTime:J

    iput-wide v1, p0, Lcom/android/camera/module/Camera2Module;->mLastCaptureTime:J

    .line 1111
    const/4 v1, 0x3

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 1114
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mBogusCameraId:I

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    invoke-static {v1, v2}, Lcom/android/camera/Util;->getJpegRotation(II)I

    move-result v1

    iput v1, p0, Lcom/android/camera/module/Camera2Module;->mJpegRotation:I

    .line 1115
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "prepareNormalCapture: mOrientation = "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ", mJpegRotation = "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mJpegRotation:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1116
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mJpegRotation:I

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setJpegRotation(I)V

    .line 1119
    invoke-static {}, Lcom/android/camera/LocationManager;->instance()Lcom/android/camera/LocationManager;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/LocationManager;->getCurrentLocation()Landroid/location/Location;

    move-result-object v1

    .line 1120
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v2, v1}, Lcom/android/camera2/Camera2Proxy;->setGpsLocation(Landroid/location/Location;)V

    .line 1121
    iput-object v1, p0, Lcom/android/camera/module/Camera2Module;->mLocation:Landroid/location/Location;

    .line 1124
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFrontMirror()V

    .line 1128
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateBeauty()V

    .line 1130
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateShotDetermine()V

    .line 1132
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    if-eqz v1, :cond_0

    .line 1133
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    .line 1134
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-static {v3, v4}, Lcom/android/camera/Util;->createJpegName(J)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getSuffix()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/android/camera/storage/Storage;->generateFilepath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 1133
    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setShotSavePath(Ljava/lang/String;)V

    .line 1138
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->setWaterMark()V

    .line 1139
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->setPictureOrientation()V

    .line 1142
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateJpegQuality()V

    .line 1145
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateAlgorithmName()V

    .line 1148
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->needShowThumbProgressImmediately()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1149
    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->updateThumbProgress(Z)V

    .line 1153
    :cond_1
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->prepareSuperNight()V

    .line 1154
    return-void
.end method

.method private prepareSuperNight()V
    .locals 2

    .line 1195
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xad

    if-eq v0, v1, :cond_0

    .line 1196
    return-void

    .line 1199
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xd4

    .line 1200
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$RecordState;

    .line 1201
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$RecordState;->onPrepare()V

    .line 1202
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$RecordState;->onStart()V

    .line 1204
    const/16 v0, 0x12c

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const/16 v1, 0x7d0

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-static {v0, v1}, Lio/reactivex/Observable;->just(Ljava/lang/Object;Ljava/lang/Object;)Lio/reactivex/Observable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/Camera2Module$7;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$7;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 1205
    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->flatMap(Lio/reactivex/functions/Function;)Lio/reactivex/Observable;

    move-result-object v0

    .line 1211
    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v1

    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/Camera2Module$6;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$6;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 1212
    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    .line 1246
    return-void
.end method

.method private previewWhenSessionSuccess()V
    .locals 1

    .line 2577
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 2579
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    .line 2580
    sget-object v0, Lcom/android/camera/constant/UpdateConstant;->CAMERA_TYPES_ON_PREVIEW_SUCCESS:[I

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 2581
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isManualModule()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2582
    sget-object v0, Lcom/android/camera/constant/UpdateConstant;->CAMERA_TYPES_MANUALLY:[I

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 2584
    :cond_0
    return-void
.end method

.method private releaseEffectProcessor()V
    .locals 1

    .line 4942
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    if-eqz v0, :cond_0

    .line 4943
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    invoke-virtual {v0}, Lcom/android/camera/effect/renders/SnapshotEffectRender;->releaseIfNeeded()V

    .line 4944
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mEffectProcessor:Lcom/android/camera/effect/renders/SnapshotEffectRender;

    .line 4946
    :cond_0
    return-void
.end method

.method private resetAiSceneInHdrOrFlashOn()V
    .locals 2

    .line 5648
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isResetFromMutex:Z

    if-nez v0, :cond_3

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    if-nez v0, :cond_0

    goto :goto_0

    .line 5653
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    const/16 v1, 0xa

    if-ne v0, v1, :cond_2

    .line 5658
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$29;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$29;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 5666
    :cond_2
    return-void

    .line 5650
    :cond_3
    :goto_0
    return-void
.end method

.method private resetAsdSceneInHdrOrFlashChange()V
    .locals 2

    .line 5669
    invoke-static {}, Lcom/mi/config/b;->hR()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_2

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 5674
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAsdScene:I

    const/16 v1, 0x9

    if-ne v0, v1, :cond_1

    .line 5675
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$30;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$30;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 5682
    :cond_1
    return-void

    .line 5671
    :cond_2
    :goto_0
    return-void
.end method

.method private resetGradienter()V
    .locals 2

    .line 4917
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGradienterOn()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 4918
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera/SensorStateManager;->setGradienterEnabled(Z)V

    .line 4920
    :cond_0
    return-void
.end method

.method private resetScreenOn()V
    .locals 2

    .line 4923
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v0, :cond_0

    .line 4924
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x11

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 4925
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 4927
    :cond_0
    return-void
.end method

.method private resumePreviewInWorkThread()V
    .locals 1

    .line 2674
    const/4 v0, 0x0

    new-array v0, v0, [I

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 2675
    return-void
.end method

.method private setAiSceneEffect(I)V
    .locals 4

    .line 3689
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "setAiSceneEffect: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3691
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fj()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->isBackCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    const/16 v0, 0x19

    if-ne p1, v0, :cond_0

    .line 3693
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "supportAi30: AI 3.0 back camera in HUMAN SCENE not apply filter!"

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3694
    return-void

    .line 3696
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontCamera()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 3697
    :cond_1
    if-eqz p1, :cond_2

    .line 3698
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "setAiSceneEffect: front camera or portrait mode nonsupport!"

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3699
    return-void

    .line 3700
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraSettings;->getPortraitLightingPattern()I

    move-result v0

    if-eqz v0, :cond_3

    .line 3702
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "setAiSceneEffect: scene = 0 but portrait lighting is on..."

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3703
    return-void

    .line 3708
    :cond_3
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Lcom/android/camera/effect/EffectController;->getFilterInfo(I)Ljava/util/ArrayList;

    move-result-object v0

    .line 3709
    if-ltz p1, :cond_8

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-le p1, v2, :cond_4

    goto :goto_1

    .line 3715
    :cond_4
    invoke-static {}, Lcom/android/camera/CameraSettings;->getShaderEffect()I

    move-result v2

    .line 3716
    invoke-static {v2}, Lcom/android/camera/effect/FilterInfo;->getCategory(I)I

    move-result v3

    .line 3717
    if-eq v3, v1, :cond_5

    sget v1, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    if-eq v2, v1, :cond_5

    .line 3718
    return-void

    .line 3720
    :cond_5
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/android/camera/effect/FilterInfo;

    invoke-virtual {p1}, Lcom/android/camera/effect/FilterInfo;->getId()I

    move-result p1

    .line 3722
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/android/camera/effect/EffectController;->setAiSceneEffect(I)V

    .line 3723
    sget v0, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    if-eq p1, v0, :cond_6

    const/4 v0, 0x1

    goto :goto_0

    :cond_6
    const/4 v0, 0x0

    :goto_0
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mHasAiSceneFilterEffect:Z

    .line 3725
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/effect/EffectController;->hasEffect()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 3726
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->prepareEffectProcessor(I)V

    .line 3728
    :cond_7
    return-void

    .line 3710
    :cond_8
    :goto_1
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "setAiSceneEffect: scene unknown!"

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 3711
    return-void
.end method

.method private setEffectFilter(I)V
    .locals 3

    .line 3676
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "setEffectFilter: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3677
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/android/camera/effect/EffectController;->setEffect(I)V

    .line 3679
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    if-eqz v0, :cond_0

    .line 3680
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    invoke-virtual {v0, p1}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->setFilterId(I)V

    .line 3683
    :cond_0
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/effect/EffectController;->hasEffect()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 3684
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->prepareEffectProcessor(I)V

    .line 3686
    :cond_1
    return-void
.end method

.method private setLightingEffect()V
    .locals 4

    .line 3732
    invoke-static {}, Lcom/android/camera/CameraSettings;->getShaderEffect()I

    move-result v0

    .line 3733
    invoke-static {v0}, Lcom/android/camera/effect/FilterInfo;->getCategory(I)I

    move-result v1

    .line 3734
    const/4 v2, 0x5

    if-eq v1, v2, :cond_0

    sget v1, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    if-eq v0, v1, :cond_0

    .line 3735
    return-void

    .line 3738
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->getPortraitLightingPattern()I

    move-result v0

    .line 3740
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "setLightingEffect: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3743
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v1

    const/4 v2, 0x6

    invoke-virtual {v1, v2}, Lcom/android/camera/effect/EffectController;->getFilterInfo(I)Ljava/util/ArrayList;

    move-result-object v1

    .line 3745
    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/android/camera/effect/FilterInfo;

    invoke-virtual {v0}, Lcom/android/camera/effect/FilterInfo;->getId()I

    move-result v0

    .line 3746
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/android/camera/effect/EffectController;->setLightingEffect(I)V

    .line 3748
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/effect/EffectController;->hasEffect()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 3749
    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->prepareEffectProcessor(I)V

    .line 3751
    :cond_1
    return-void
.end method

.method private setOrientation(II)V
    .locals 2

    .line 4539
    const/4 v0, -0x1

    if-ne p1, v0, :cond_0

    .line 4540
    return-void

    .line 4543
    :cond_0
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    .line 4544
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object p1

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    .line 4545
    invoke-static {v0, v1}, Lcom/android/camera/Util;->getShootOrientation(Landroid/app/Activity;I)I

    move-result v0

    .line 4544
    invoke-virtual {p1, v0}, Lcom/android/camera/effect/EffectController;->setOrientation(I)V

    .line 4546
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->checkActivityOrientation()V

    .line 4550
    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mOrientationCompensation:I

    if-eq p1, p2, :cond_1

    .line 4551
    iput p2, p0, Lcom/android/camera/module/Camera2Module;->mOrientationCompensation:I

    .line 4552
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->setOrientationParameter()V

    .line 4554
    :cond_1
    return-void
.end method

.method private setOrientationParameter()V
    .locals 3

    .line 4557
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDeparted()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 4558
    return-void

    .line 4560
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_2

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_2

    .line 4561
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_1

    .line 4562
    new-array v0, v1, [I

    const/4 v1, 0x0

    const/16 v2, 0x23

    aput v2, v0, v1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    goto :goto_0

    .line 4564
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setDeviceOrientation(I)V

    .line 4567
    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    if-eqz v0, :cond_3

    .line 4568
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mOrientationCompensation:I

    invoke-virtual {v0, v1}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->setOrientationHint(I)V

    .line 4570
    :cond_3
    return-void
.end method

.method private setPictureOrientation()V
    .locals 2

    .line 4494
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/SensorStateManager;->isDeviceLying()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, -0x40800000    # -1.0f

    goto :goto_0

    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mDeviceRotation:F

    :goto_0
    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mShootRotation:F

    .line 4495
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_1

    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    :goto_1
    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mShootOrientation:I

    .line 4496
    return-void
.end method

.method private setPortraitSuccessHintVisible(I)V
    .locals 2

    .line 5301
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 5302
    const/16 v1, 0xaf

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;

    .line 5303
    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;->setPortraitHintVisible(I)V

    .line 5304
    return-void
.end method

.method private setSceneMode(Ljava/lang/String;)Z
    .locals 4

    .line 4439
    const/4 v0, -0x1

    invoke-static {p1, v0}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v0

    .line 4440
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v1}, Lcom/android/camera2/CameraCapabilities;->getSupportedSceneModes()[I

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/Util;->isSupported(I[I)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 4441
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "sceneMode="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4442
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setSceneMode(I)V

    .line 4443
    const/4 p1, 0x1

    return p1

    .line 4445
    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method private setVideoSize(II)V
    .locals 1

    .line 659
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraDisplayOrientation:I

    rem-int/lit16 v0, v0, 0xb4

    if-nez v0, :cond_0

    .line 660
    new-instance v0, Lcom/android/camera/CameraSize;

    invoke-direct {v0, p1, p2}, Lcom/android/camera/CameraSize;-><init>(II)V

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mVideoSize:Lcom/android/camera/CameraSize;

    goto :goto_0

    .line 662
    :cond_0
    new-instance v0, Lcom/android/camera/CameraSize;

    invoke-direct {v0, p2, p1}, Lcom/android/camera/CameraSize;-><init>(II)V

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mVideoSize:Lcom/android/camera/CameraSize;

    .line 664
    :goto_0
    return-void
.end method

.method private setWaterMark()V
    .locals 3

    .line 4500
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    const/4 v1, 0x0

    if-nez v0, :cond_3

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v2, 0xa5

    if-eq v0, v2, :cond_3

    .line 4502
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGradienterOn()Z

    move-result v0

    if-nez v0, :cond_3

    .line 4503
    invoke-static {}, Lcom/android/camera/CameraSettings;->getShaderEffect()I

    move-result v0

    sget v2, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    if-ne v0, v2, :cond_3

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mHasAiSceneFilterEffect:Z

    if-nez v0, :cond_3

    .line 4505
    invoke-static {}, Lcom/android/camera/CameraSettings;->isTiltShiftOn()Z

    move-result v0

    if-nez v0, :cond_3

    .line 4506
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fg()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    .line 4511
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isDualCameraWaterMarkOpen()Z

    move-result v0

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 4512
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v2}, Lcom/android/camera2/Camera2Proxy;->setDualCamWaterMarkEnable(Z)V

    goto :goto_0

    .line 4514
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setDualCamWaterMarkEnable(Z)V

    .line 4516
    :goto_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isTimeWaterMarkOpen()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4517
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v2}, Lcom/android/camera2/Camera2Proxy;->setTimeWaterMarkEnable(Z)V

    .line 4518
    invoke-static {}, Lcom/android/camera/Util;->getTimeWatermark()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCaptureWaterMarkStr:Ljava/lang/String;

    .line 4519
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCaptureWaterMarkStr:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setTimeWatermarkValue(Ljava/lang/String;)V

    goto :goto_1

    .line 4521
    :cond_2
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCaptureWaterMarkStr:Ljava/lang/String;

    .line 4522
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setTimeWaterMarkEnable(Z)V

    .line 4524
    :goto_1
    return-void

    .line 4507
    :cond_3
    :goto_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setDualCamWaterMarkEnable(Z)V

    .line 4508
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setTimeWaterMarkEnable(Z)V

    .line 4509
    return-void
.end method

.method private shouldApplyNormalWideLDC()Z
    .locals 3

    .line 4356
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {v0}, Lcom/android/camera/CameraSettings;->shouldNormalWideLDCBeVisibleInMode(I)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 4357
    return v1

    .line 4359
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mActualCameraId:I

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v2

    invoke-virtual {v2}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getUltraWideCameraId()I

    move-result v2

    if-ne v0, v2, :cond_1

    .line 4361
    return v1

    .line 4363
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4364
    return v1

    .line 4366
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraSettings;->isUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 4367
    return v1

    .line 4369
    :cond_3
    invoke-static {}, Lcom/android/camera/CameraSettings;->isNormalWideLDCEnabled()Z

    move-result v0

    return v0
.end method

.method private shouldApplyUltraWideLDC()Z
    .locals 3

    .line 4380
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {v0}, Lcom/android/camera/CameraSettings;->shouldUltraWideLDCBeVisibleInMode(I)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 4381
    return v1

    .line 4383
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mActualCameraId:I

    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v2

    invoke-virtual {v2}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getUltraWideCameraId()I

    move-result v2

    if-eq v0, v2, :cond_1

    .line 4385
    return v1

    .line 4387
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->isUltraWideLDCEnabled()Z

    move-result v0

    return v0
.end method

.method private shouldChangeAiScene(I)Z
    .locals 6

    .line 5633
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 5638
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentDetectedScene:I

    if-eq v0, p1, :cond_1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    iget-wide v4, p0, Lcom/android/camera/module/Camera2Module;->mLastChangeSceneTime:J

    sub-long/2addr v2, v4

    const-wide/16 v4, 0x12c

    cmp-long v0, v2, v4

    if-lez v0, :cond_1

    .line 5639
    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentDetectedScene:I

    .line 5640
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mLastChangeSceneTime:J

    .line 5641
    const/4 p1, 0x1

    return p1

    .line 5644
    :cond_1
    return v1

    .line 5634
    :cond_2
    :goto_0
    return v1
.end method

.method private shouldDoMultiFrameCapture()Z
    .locals 4

    .line 3597
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isHdr()Z

    move-result v0

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mShouldDoMFNR:Z

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    .line 3599
    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isSuperResolution()Z

    move-result v0

    if-nez v0, :cond_1

    .line 3600
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    .line 3601
    :goto_1
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "shouldDoMultiFrameCapture: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3602
    return v0
.end method

.method private showMoonMode()Z
    .locals 3

    .line 5574
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 5575
    const/16 v1, 0xac

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 5576
    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 5577
    const/4 v2, 0x1

    iput-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mEnteringMoonMode:Z

    .line 5578
    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertMoonModeSelector(I)V

    .line 5580
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateMoonNight()V

    .line 5581
    return v2

    .line 5583
    :cond_0
    return v1
.end method

.method private showPostCaptureAlert()V
    .locals 2

    .line 3107
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->enableCameraControls(Z)V

    .line 3108
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->removeMessages()V

    .line 3110
    const/4 v1, 0x1

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->stopFaceDetection(Z)V

    .line 3111
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->pausePreview()V

    .line 3113
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setEffectViewVisible(Z)V

    .line 3117
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa0

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 3119
    const/4 v1, 0x6

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->delegateEvent(I)V

    .line 3124
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->resetMetaDataManager()V

    .line 3125
    return-void
.end method

.method private startCount(II)V
    .locals 2

    .line 1730
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->checkShutterCondition()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1731
    return-void

    .line 1733
    :cond_0
    invoke-virtual {p0, p2}, Lcom/android/camera/module/Camera2Module;->setTriggerMode(I)V

    .line 1735
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->tryRemoveCountDownMessage()V

    .line 1736
    sget-object p2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "startCount: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p2, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1738
    const-wide/16 v0, 0x1

    sget-object p2, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-static {v0, v1, p2}, Lio/reactivex/Observable;->interval(JLjava/util/concurrent/TimeUnit;)Lio/reactivex/Observable;

    move-result-object p2

    int-to-long v0, p1

    invoke-virtual {p2, v0, v1}, Lio/reactivex/Observable;->take(J)Lio/reactivex/Observable;

    move-result-object p2

    .line 1739
    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v0

    invoke-virtual {p2, v0}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p2

    new-instance v0, Lcom/android/camera/module/Camera2Module$9;

    invoke-direct {v0, p0, p1}, Lcom/android/camera/module/Camera2Module$9;-><init>(Lcom/android/camera/module/Camera2Module;I)V

    .line 1740
    invoke-virtual {p2, v0}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/Observer;)V

    .line 1782
    return-void
.end method

.method private startLensActivity()V
    .locals 3

    .line 5074
    invoke-static {}, Lcom/android/camera/Util;->isGlobalVersion()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 5075
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mLensApi:Lcom/google/lens/sdk/LensApi;

    if-nez v0, :cond_0

    .line 5076
    return-void

    .line 5078
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mLensStatus:I

    if-eqz v0, :cond_1

    .line 5079
    return-void

    .line 5081
    :cond_1
    const/4 v0, 0x0

    .line 5082
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mLensApi:Lcom/google/lens/sdk/LensApi;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1, v2, v0}, Lcom/google/lens/sdk/LensApi;->launchLensActivity(Landroid/app/Activity;I)V

    .line 5083
    goto :goto_0

    .line 5084
    :cond_2
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "start ai lens"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 5086
    :try_start_0
    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    .line 5087
    const-string v1, "android.media.action.XIAOAI_CONTROL"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setAction(Ljava/lang/String;)Landroid/content/Intent;

    .line 5088
    const-string v1, "com.xiaomi.lens"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 5089
    const-string v1, "preview_width"

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v2, v2, Lcom/android/camera/CameraSize;->width:I

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    .line 5090
    const-string v1, "preview_height"

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v2, v2, Lcom/android/camera/CameraSize;->height:I

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    .line 5091
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/android/camera/Camera;->startActivity(Landroid/content/Intent;)V

    .line 5092
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v0

    const/high16 v1, 0x7f050000

    const v2, 0x7f050001

    invoke-virtual {v0, v1, v2}, Lcom/android/camera/Camera;->overridePendingTransition(II)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 5095
    goto :goto_0

    .line 5093
    :catch_0
    move-exception v0

    .line 5094
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v2, "onClick: occur a exception"

    invoke-static {v1, v2, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 5097
    :goto_0
    return-void
.end method

.method private startLiveShotAnimation()V
    .locals 3

    .line 667
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorderStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 668
    :try_start_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v1, :cond_0

    .line 669
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v2, Lcom/android/camera/module/Camera2Module$4;

    invoke-direct {v2, p0}, Lcom/android/camera/module/Camera2Module$4;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v1, v2}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 680
    :cond_0
    monitor-exit v0

    .line 681
    return-void

    .line 680
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private startNormalCapture(I)V
    .locals 3

    .line 1157
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "startNormalCapture mode -> "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1158
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->prepareNormalCapture()V

    .line 1159
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isParallelSessionEnable()Z

    move-result p1

    if-nez p1, :cond_0

    .line 1160
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1, p0, v0, v1, v2}, Lcom/android/camera2/Camera2Proxy;->captureGroupShotPictures(Lcom/android/camera2/Camera2Proxy$PictureCallback;Lcom/xiaomi/camera/core/ParallelCallback;ILandroid/content/Context;)V

    goto :goto_0

    .line 1164
    :cond_0
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v0, 0x32

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->calculateTimeout(I)J

    move-result-wide v1

    invoke-virtual {p1, v0, v1, v2}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    .line 1165
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Lcom/android/camera2/Camera2Proxy;->takePicture(Lcom/android/camera2/Camera2Proxy$PictureCallback;Lcom/xiaomi/camera/core/ParallelCallback;)V

    .line 1167
    :goto_0
    return-void
.end method

.method private stopMultiSnap()V
    .locals 10

    .line 555
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "stopMultiSnap: start"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 556
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x31

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 557
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez v0, :cond_0

    .line 558
    return-void

    .line 560
    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mLastCaptureTime:J

    .line 561
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    .line 564
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1}, Lcom/android/camera2/Camera2Proxy;->captureAbortBurst()V

    .line 568
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v1}, Lcom/android/camera/MutexModeManager;->isUbiFocus()Z

    move-result v1

    const/4 v2, 0x1

    if-nez v1, :cond_1

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mReceivedJpegCallbackNum:I

    .line 569
    move v4, v1

    goto :goto_0

    .line 568
    :cond_1
    nop

    .line 569
    move v4, v2

    :goto_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v1}, Lcom/android/camera/MutexModeManager;->isUbiFocus()Z

    move-result v1

    .line 570
    xor-int/lit8 v5, v1, 0x1

    invoke-virtual {p0, v4, v5}, Lcom/android/camera/module/Camera2Module;->trackGeneralInfo(IZ)V

    .line 571
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mLocation:Landroid/location/Location;

    if-eqz v1, :cond_2

    move v6, v2

    goto :goto_1

    :cond_2
    move v6, v0

    :goto_1
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getCurrentAiSceneName()Ljava/lang/String;

    move-result-object v7

    iget-boolean v8, p0, Lcom/android/camera/module/Camera2Module;->mEnteringMoonMode:Z

    iget-boolean v9, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    move-object v3, p0

    invoke-virtual/range {v3 .. v9}, Lcom/android/camera/module/Camera2Module;->trackPictureTaken(IZZLjava/lang/String;ZZ)V

    .line 573
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->animateCapture()V

    .line 575
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mUpdateImageTitle:Z

    .line 576
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x30

    const-wide/16 v2, 0x320

    invoke-virtual {v0, v1, v2, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    .line 577
    return-void
.end method

.method private trackAISceneChanged(II)V
    .locals 1

    .line 5686
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-static {p1, p2, v0}, Lcom/android/camera/statistic/CameraStatUtil;->trackAISceneChanged(IILandroid/content/res/Resources;)V

    .line 5687
    return-void
.end method

.method private trackBeautyInfo(IZLcom/android/camera/fragment/beauty/BeautyValues;)V
    .locals 0

    .line 5779
    nop

    .line 5780
    if-eqz p2, :cond_0

    const-string p2, "\u524d\u6444"

    goto :goto_0

    :cond_0
    const-string p2, "\u540e\u6444"

    .line 5779
    :goto_0
    invoke-static {p1, p2, p3}, Lcom/android/camera/statistic/CameraStatUtil;->trackBeautyInfo(ILjava/lang/String;Lcom/android/camera/fragment/beauty/BeautyValues;)V

    .line 5782
    return-void
.end method

.method private trackManualInfo(I)V
    .locals 4

    .line 5750
    const-string v0, "pref_camera_whitebalance_key"

    .line 5751
    const v1, 0x7f090064

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 5750
    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 5752
    const-string v1, "pref_qc_camera_exposuretime_key"

    .line 5753
    const v2, 0x7f090136

    invoke-virtual {p0, v2}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 5752
    invoke-direct {p0, v1, v2}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 5754
    const-string v2, "pref_qc_camera_iso_key"

    .line 5755
    const v3, 0x7f0900aa

    invoke-virtual {p0, v3}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 5754
    invoke-direct {p0, v2, v3}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 5756
    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {p1, v0, v1, v2, v3}, Lcom/android/camera/statistic/CameraStatUtil;->trackPictureTakenInManual(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V

    .line 5757
    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {p1}, Lcom/android/camera/statistic/CameraStatUtil;->trackUltraWideManualTaken(I)V

    .line 5758
    return-void
.end method

.method private unlockAEAF()V
    .locals 2

    .line 4654
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "unlockAEAF"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4655
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    .line 4656
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mAeLockSupported:Z

    if-eqz v1, :cond_0

    .line 4657
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1}, Lcom/android/camera2/Camera2Proxy;->unlockExposure()V

    .line 4659
    :cond_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    if-eqz v1, :cond_1

    .line 4660
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v1, v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setAeAwbLock(Z)V

    .line 4662
    :cond_1
    return-void
.end method

.method private updateAiScene()V
    .locals 3

    .line 735
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFunctionParseAiScene:Lcom/android/camera/module/loader/FunctionParseAiScene;

    if-eqz v0, :cond_0

    .line 736
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFunctionParseAiScene:Lcom/android/camera/module/loader/FunctionParseAiScene;

    invoke-virtual {v0}, Lcom/android/camera/module/loader/FunctionParseAiScene;->resetScene()V

    .line 738
    :cond_0
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 739
    invoke-static {}, Lcom/android/camera/CameraSettings;->getAiSceneOpen()Z

    move-result v1

    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    .line 740
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->isScreenSlideOff()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 741
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    .line 744
    :cond_1
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setASD(Z)V

    .line 746
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isCapture()Z

    move-result v1

    if-nez v1, :cond_3

    :cond_2
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    if-nez v1, :cond_4

    .line 747
    :cond_3
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setCameraAI30(Z)V

    .line 749
    :cond_4
    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->setAiSceneEffect(I)V

    .line 750
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    if-eqz v0, :cond_5

    .line 751
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/16 v1, 0x12c

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setASDPeriod(I)V

    goto :goto_0

    .line 753
    :cond_5
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->hideSceneSelector()V

    .line 754
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateHDRPreference()V

    .line 755
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateFlashPreference()V

    .line 756
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateBeauty()V

    .line 758
    :goto_0
    return-void
.end method

.method private updateAlgorithmName()V
    .locals 1

    .line 2903
    nop

    .line 2904
    invoke-static {}, Lcom/mi/config/b;->hZ()Z

    move-result v0

    if-nez v0, :cond_3

    .line 2905
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isBokehEnabled()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2906
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fO()I

    move-result v0

    .line 2907
    if-lez v0, :cond_0

    .line 2908
    const-string v0, "soft-portrait-enc"

    goto :goto_0

    .line 2910
    :cond_0
    const-string v0, "soft-portrait"

    .line 2912
    :goto_0
    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 2913
    const-string v0, "portrait"

    goto :goto_1

    .line 2915
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->getAlgorithmName()Ljava/lang/String;

    move-result-object v0

    goto :goto_1

    .line 2918
    :cond_3
    const/4 v0, 0x0

    :goto_1
    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mAlgorithmName:Ljava/lang/String;

    .line 2919
    return-void
.end method

.method private updateBeauty()V
    .locals 6

    .line 3989
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa3

    if-eq v0, v1, :cond_0

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa5

    if-eq v0, v1, :cond_0

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xab

    if-eq v0, v1, :cond_0

    .line 3992
    return-void

    .line 3995
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    if-nez v0, :cond_1

    .line 3996
    new-instance v0, Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-direct {v0}, Lcom/android/camera/fragment/beauty/BeautyValues;-><init>()V

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    .line 4001
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-static {}, Lcom/android/camera/CameraSettings;->getFaceBeautyCloseValue()Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    .line 4003
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentConfigBeauty()Lcom/android/camera/data/data/config/ComponentConfigBeauty;

    move-result-object v0

    .line 4005
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/config/ComponentConfigBeauty;->isClosed(I)Z

    move-result v0

    .line 4006
    if-nez v0, :cond_2

    .line 4007
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-static {}, Lcom/mi/config/b;->hM()Z

    move-result v1

    invoke-static {v0, v1}, Lcom/android/camera/CameraSettings;->initBeautyValues(Lcom/android/camera/fragment/beauty/BeautyValues;Z)V

    .line 4009
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    const/16 v1, 0x19

    if-ne v0, v1, :cond_2

    sget-object v0, Lcom/android/camera/constant/BeautyConstant;->LEVEL_CLOSE:Ljava/lang/String;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    iget-object v1, v1, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    .line 4011
    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4012
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    sget-object v1, Lcom/android/camera/constant/BeautyConstant;->LEVEL_LOW:Ljava/lang/String;

    iput-object v1, v0, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    .line 4013
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v2, "Human scene mode detected, auto set beauty level from %s to %s"

    const/4 v3, 0x2

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    sget-object v5, Lcom/android/camera/constant/BeautyConstant;->LEVEL_CLOSE:Ljava/lang/String;

    aput-object v5, v3, v4

    const/4 v4, 0x1

    iget-object v5, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    iget-object v5, v5, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    aput-object v5, v3, v4

    invoke-static {v1, v2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4020
    :cond_2
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->isSupportBeautyBody()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 4021
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-static {v0}, Lcom/android/camera/CameraSettings;->initBeautyBody(Lcom/android/camera/fragment/beauty/BeautyValues;)V

    .line 4024
    :cond_3
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "updateBeauty(): "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4025
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setBeautyValues(Lcom/android/camera/fragment/beauty/BeautyValues;)V

    .line 4026
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFaceAgeAnalyze()V

    .line 4027
    return-void
.end method

.method private updateBokeh()V
    .locals 2

    .line 761
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBokehFrontCamera()Z

    move-result v0

    if-nez v0, :cond_0

    .line 762
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isPortraitModule()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 763
    invoke-static {}, Lcom/mi/config/b;->hz()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 764
    :goto_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setBokeh(Z)V

    .line 765
    return-void
.end method

.method private updateContrast()V
    .locals 2

    .line 4394
    invoke-static {}, Lcom/android/camera/CameraSettings;->getContrast()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 4395
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setContrast(I)V

    .line 4396
    return-void
.end method

.method private updateDecodePreview()V
    .locals 3

    .line 4415
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->scanQRCodeEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-static {}, Lcom/mi/config/b;->fT()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4417
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->scanQRCodeEnabled()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 4418
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v0

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v1, v1, Lcom/android/camera/CameraSize;->width:I

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v2, v2, Lcom/android/camera/CameraSize;->height:I

    invoke-virtual {v0, v1, v2}, Lcom/android/zxing/PreviewDecodeManager;->setPreviewSize(II)V

    .line 4420
    :cond_1
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "updateDecodePreview: PreviewDecodeManager mPreviewSize = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4422
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/zxing/PreviewDecodeManager;->getPreviewCallback()Lcom/android/camera2/Camera2Proxy$PreviewCallback;

    move-result-object v0

    .line 4423
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->startPreviewCallback(Lcom/android/camera2/Camera2Proxy$PreviewCallback;)V

    .line 4424
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/zxing/PreviewDecodeManager;->startDecode()V

    .line 4426
    :cond_2
    return-void
.end method

.method private updateDeviceOrientation()V
    .locals 2

    .line 4435
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setDeviceOrientation(I)V

    .line 4436
    return-void
.end method

.method private updateExposureTime()V
    .locals 4

    .line 4303
    const-string v0, "pref_qc_camera_exposuretime_key"

    .line 4304
    const v1, 0x7f090136

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 4303
    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 4305
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Lcom/android/camera2/Camera2Proxy;->setExposureTime(J)V

    .line 4306
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateManualEvAdjust()V

    .line 4307
    return-void
.end method

.method private updateEyeLight()V
    .locals 4

    .line 3860
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fw()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 3861
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->isFaceBeautyOn(Lcom/android/camera/fragment/beauty/BeautyValues;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->getEyeLightType()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    const-string v0, "-1"

    .line 3863
    :goto_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xac

    .line 3864
    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 3865
    if-eqz v1, :cond_2

    .line 3866
    const-string v2, "-1"

    invoke-virtual {v2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 3867
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v3, Lcom/android/camera/module/Camera2Module$18;

    invoke-direct {v3, p0, v1}, Lcom/android/camera/module/Camera2Module$18;-><init>(Lcom/android/camera/module/Camera2Module;Lcom/android/camera/protocol/ModeProtocol$TopAlert;)V

    invoke-virtual {v2, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    goto :goto_1

    .line 3874
    :cond_1
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v3, Lcom/android/camera/module/Camera2Module$19;

    invoke-direct {v3, p0, v1}, Lcom/android/camera/module/Camera2Module$19;-><init>(Lcom/android/camera/module/Camera2Module;Lcom/android/camera/protocol/ModeProtocol$TopAlert;)V

    invoke-virtual {v2, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 3883
    :cond_2
    :goto_1
    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 3884
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setEyeLight(I)V

    .line 3886
    :cond_3
    return-void
.end method

.method private updateFNumber()V
    .locals 2

    .line 3889
    invoke-static {}, Lcom/android/camera/CameraSettings;->readFNumber()Ljava/lang/String;

    move-result-object v0

    .line 3890
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setFNumber(Ljava/lang/String;)V

    .line 3891
    return-void
.end method

.method private updateFace()V
    .locals 6

    .line 4096
    nop

    .line 4097
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-nez v0, :cond_4

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isUbiFocus()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    .line 4099
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isMagicMirrorOn()Z

    move-result v0

    if-nez v0, :cond_3

    .line 4100
    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v0

    if-nez v0, :cond_3

    .line 4101
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result v0

    if-nez v0, :cond_3

    .line 4102
    invoke-static {}, Lcom/android/camera/CameraSettings;->showGenderAge()Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    .line 4105
    :cond_1
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    const-string v3, "pref_camera_facedetection_key"

    .line 4107
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    const v5, 0x7f0f0006

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v4

    .line 4105
    invoke-virtual {v0, v3, v4}, Lcom/android/camera/data/data/global/DataItemGlobal;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 4108
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGradienterOn()Z

    move-result v3

    if-nez v3, :cond_2

    invoke-static {}, Lcom/android/camera/CameraSettings;->isTiltShiftOn()Z

    move-result v3

    if-eqz v3, :cond_5

    .line 4109
    :cond_2
    nop

    .line 4113
    move v3, v1

    goto :goto_2

    .line 4103
    :cond_3
    :goto_0
    nop

    .line 4113
    move v0, v2

    move v3, v0

    goto :goto_2

    .line 4098
    :cond_4
    :goto_1
    nop

    .line 4113
    move v0, v1

    :cond_5
    move v3, v2

    :goto_2
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    if-eqz v4, :cond_8

    .line 4114
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    if-eqz v0, :cond_7

    if-nez v3, :cond_6

    goto :goto_3

    :cond_6
    move v3, v1

    goto :goto_4

    :cond_7
    :goto_3
    move v3, v2

    :goto_4
    invoke-interface {v4, v3}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setSkipDrawFace(Z)V

    .line 4116
    :cond_8
    if-eqz v0, :cond_9

    .line 4117
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    if-nez v0, :cond_a

    .line 4118
    iput-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    .line 4119
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->startFaceDetection()V

    goto :goto_5

    .line 4122
    :cond_9
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    if-eqz v0, :cond_a

    .line 4123
    invoke-virtual {p0, v2}, Lcom/android/camera/module/Camera2Module;->stopFaceDetection(Z)V

    .line 4124
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    .line 4127
    :cond_a
    :goto_5
    return-void
.end method

.method private updateFaceAgeAnalyze()V
    .locals 3

    .line 776
    nop

    .line 777
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    const-string v1, "pref_camera_show_gender_age_key"

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsGenderAgeOn:Z

    .line 778
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsGenderAgeOn:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    .line 779
    goto :goto_0

    .line 780
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    if-eqz v0, :cond_1

    .line 781
    invoke-static {}, Lcom/android/camera/CameraSettings;->getFaceBeautyCloseValue()Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    iget-object v2, v2, Lcom/android/camera/fragment/beauty/BeautyValues;->mBeautyLevel:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    .line 783
    xor-int/2addr v1, v0

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    :goto_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setFaceAgeAnalyze(Z)V

    .line 784
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/effect/EffectController;->hasEffect()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 785
    sget v0, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->prepareEffectProcessor(I)V

    .line 787
    :cond_2
    return-void
.end method

.method private updateFaceScore()V
    .locals 2

    .line 768
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    const-string v1, "pref_camera_magic_mirror_key"

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMagicMirrorOn:Z

    .line 769
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsMagicMirrorOn:Z

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setFaceScore(Z)V

    .line 770
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/effect/EffectController;->hasEffect()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 771
    sget v0, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->prepareEffectProcessor(I)V

    .line 773
    :cond_0
    return-void
.end method

.method private updateFilter()V
    .locals 1

    .line 3671
    invoke-static {}, Lcom/android/camera/CameraSettings;->getShaderEffect()I

    move-result v0

    .line 3672
    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->setEffectFilter(I)V

    .line 3673
    return-void
.end method

.method private updateFocusArea()V
    .locals 4

    .line 3895
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-nez v0, :cond_3

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 3916
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCropRegion()Landroid/graphics/Rect;

    move-result-object v0

    .line 3917
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActiveArraySize()Landroid/graphics/Rect;

    move-result-object v1

    .line 3918
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v2}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v2

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    .line 3919
    invoke-virtual {v3, v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->getMeteringAreas(Landroid/graphics/Rect;Landroid/graphics/Rect;)[Landroid/hardware/camera2/params/MeteringRectangle;

    move-result-object v3

    if-eqz v3, :cond_1

    const/4 v3, 0x1

    goto :goto_0

    :cond_1
    const/4 v3, 0x0

    .line 3918
    :goto_0
    invoke-virtual {v2, v3}, Lcom/android/camera/SensorStateManager;->setFocusSensorEnabled(Z)V

    .line 3921
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v3, v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->getMeteringAreas(Landroid/graphics/Rect;Landroid/graphics/Rect;)[Landroid/hardware/camera2/params/MeteringRectangle;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/android/camera2/Camera2Proxy;->setAERegions([Landroid/hardware/camera2/params/MeteringRectangle;)V

    .line 3923
    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mFocusAreaSupported:Z

    if-eqz v2, :cond_2

    .line 3924
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v3, v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->getFocusAreas(Landroid/graphics/Rect;Landroid/graphics/Rect;)[Landroid/hardware/camera2/params/MeteringRectangle;

    move-result-object v0

    invoke-virtual {v2, v0}, Lcom/android/camera2/Camera2Proxy;->setAFRegions([Landroid/hardware/camera2/params/MeteringRectangle;)V

    goto :goto_1

    .line 3926
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->resumePreview()V

    .line 3928
    :goto_1
    return-void

    .line 3896
    :cond_3
    :goto_2
    return-void
.end method

.method private updateFocusMode()V
    .locals 2

    .line 4035
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    if-eqz v0, :cond_0

    .line 4037
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    const-string v1, "manual"

    invoke-virtual {v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setFocusMode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 4039
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-static {}, Lcom/android/camera/CameraSettings;->getFocusMode()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setFocusMode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 4041
    :goto_0
    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setFocusMode(Ljava/lang/String;)V

    .line 4042
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFocusModeSwitching()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBackCamera()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 4043
    const/4 v1, 0x0

    invoke-static {v1}, Lcom/android/camera/CameraSettings;->setFocusModeSwitching(Z)V

    .line 4044
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->resetFocusStateIfNeeded()V

    .line 4051
    :cond_1
    const-string v1, "manual"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 4052
    invoke-static {}, Lcom/android/camera/CameraSettings;->getFocusPosition()I

    move-result v0

    .line 4053
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v1}, Lcom/android/camera2/CameraCapabilities;->getMinimumFocusDistance()F

    move-result v1

    .line 4055
    int-to-float v0, v0

    mul-float/2addr v1, v0

    const/high16 v0, 0x447a0000    # 1000.0f

    div-float/2addr v1, v0

    .line 4057
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    if-eqz v0, :cond_2

    .line 4058
    const/4 v1, 0x0

    .line 4060
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setFocusDistance(F)V

    .line 4065
    :cond_3
    return-void
.end method

.method private updateFpsRange()V
    .locals 7

    .line 4074
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getSupportedFpsRange()[Landroid/util/Range;

    move-result-object v0

    .line 4075
    const/4 v1, 0x0

    aget-object v2, v0, v1

    .line 4076
    array-length v3, v0

    :goto_0
    if-ge v1, v3, :cond_2

    aget-object v4, v0, v1

    .line 4077
    invoke-virtual {v2}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    invoke-virtual {v4}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v6

    check-cast v6, Ljava/lang/Integer;

    invoke-virtual {v6}, Ljava/lang/Integer;->intValue()I

    move-result v6

    if-ge v5, v6, :cond_0

    .line 4078
    goto :goto_1

    .line 4079
    :cond_0
    invoke-virtual {v2}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v5

    invoke-virtual {v4}, Landroid/util/Range;->getUpper()Ljava/lang/Comparable;

    move-result-object v6

    if-ne v5, v6, :cond_1

    .line 4080
    invoke-virtual {v2}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v5

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    invoke-virtual {v4}, Landroid/util/Range;->getLower()Ljava/lang/Comparable;

    move-result-object v6

    check-cast v6, Ljava/lang/Integer;

    invoke-virtual {v6}, Ljava/lang/Integer;->intValue()I

    move-result v6

    if-ge v5, v6, :cond_1

    .line 4081
    nop

    .line 4076
    :goto_1
    move-object v2, v4

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 4085
    :cond_2
    sget-boolean v0, Lcom/mi/config/b;->qU:Z

    if-eqz v0, :cond_3

    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 4086
    new-instance v0, Landroid/util/Range;

    const/16 v1, 0x1e

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-direct {v0, v2, v1}, Landroid/util/Range;-><init>(Ljava/lang/Comparable;Ljava/lang/Comparable;)V

    .line 4090
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setFpsRange(Landroid/util/Range;)V

    .line 4091
    return-void

    .line 4088
    :cond_3
    return-void
.end method

.method private updateFrontMirror()V
    .locals 2

    .line 791
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-nez v0, :cond_0

    .line 792
    const/4 v0, 0x0

    goto :goto_0

    .line 794
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isFrontMirror()Z

    move-result v0

    .line 796
    :goto_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setFrontMirror(Z)V

    .line 797
    return-void
.end method

.method private updateHDR(Ljava/lang/String;)V
    .locals 3

    .line 3831
    const-string v0, "auto"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 3833
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->isDetectedInHDR:Z

    .line 3835
    :cond_0
    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->getMutexHdrMode(Ljava/lang/String;)I

    move-result p1

    .line 3836
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->stopObjectTracking(Z)V

    .line 3838
    if-eqz p1, :cond_1

    .line 3839
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v2, p1}, Lcom/android/camera/MutexModeManager;->setMutexMode(I)V

    goto :goto_0

    .line 3840
    :cond_1
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {p1}, Lcom/android/camera/MutexModeManager;->isHdr()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 3841
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->resetMutexModeManually()V

    .line 3845
    :cond_2
    :goto_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-direct {p0, v1, v0}, Lcom/android/camera/module/Camera2Module;->isTriggerQcfaModeChange(ZZ)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 3846
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v0, 0x2c

    invoke-virtual {p1, v0}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 3848
    :cond_3
    return-void
.end method

.method private updateISO()V
    .locals 3

    .line 4290
    const v0, 0x7f0900aa

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 4291
    const-string v1, "pref_qc_camera_iso_key"

    invoke-direct {p0, v1, v0}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 4293
    const/4 v2, 0x0

    if-eqz v1, :cond_0

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 4295
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-static {v1, v2}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v1

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v2}, Lcom/android/camera2/CameraCapabilities;->getMaxIso()I

    move-result v2

    invoke-static {v1, v2}, Ljava/lang/Math;->min(II)I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setISO(I)V

    goto :goto_0

    .line 4297
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v2}, Lcom/android/camera2/Camera2Proxy;->setISO(I)V

    .line 4299
    :goto_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateManualEvAdjust()V

    .line 4300
    return-void
.end method

.method private updateJpegQuality()V
    .locals 2

    .line 4130
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    invoke-static {v0}, Lcom/android/camera/module/Camera2Module;->getJpegQuality(Z)I

    move-result v0

    .line 4131
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setJpegQuality(I)V

    .line 4132
    return-void
.end method

.method private updateJpegThumbnailSize()V
    .locals 4

    .line 4429
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getJpegThumbnailSize()Lcom/android/camera/CameraSize;

    move-result-object v0

    .line 4430
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setJpegThumbnailSize(Lcom/android/camera/CameraSize;)V

    .line 4431
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "thumbnailSize="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4432
    return-void
.end method

.method private updateLiveShot()V
    .locals 2

    .line 648
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fE()Z

    move-result v0

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa3

    if-ne v0, v1, :cond_1

    .line 650
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveShotOn()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 651
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->startLiveShot()V

    goto :goto_0

    .line 653
    :cond_0
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->stopLiveShot(Z)V

    .line 656
    :cond_1
    :goto_0
    return-void
.end method

.method private updateMfnr(Z)V
    .locals 3

    .line 4168
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/4 v1, 0x0

    const/16 v2, 0xa7

    if-eq v0, v2, :cond_3

    sget-boolean v0, Lcom/mi/config/b;->qT:Z

    if-eqz v0, :cond_0

    .line 4169
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualCamera()Z

    move-result v0

    if-eqz v0, :cond_3

    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    .line 4170
    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isNormal()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 4171
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result v0

    if-nez v0, :cond_3

    if-eqz p1, :cond_3

    .line 4173
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 4174
    invoke-static {}, Lcom/mi/config/b;->hT()Z

    move-result p1

    if-eqz p1, :cond_3

    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    const v0, 0x8005

    if-ne p1, v0, :cond_3

    .line 4176
    :cond_1
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object p1

    invoke-virtual {p1}, Lcom/mi/config/a;->eW()Z

    move-result p1

    if-nez p1, :cond_2

    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    const/high16 v0, 0x3f800000    # 1.0f

    cmpl-float p1, p1, v0

    if-eqz p1, :cond_2

    .line 4178
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isUltraWideBackCamera()Z

    move-result p1

    if-eqz p1, :cond_3

    :cond_2
    const/4 p1, 0x1

    goto :goto_0

    .line 4179
    :cond_3
    move p1, v1

    :goto_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isUseSwMfnr()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 4180
    nop

    .line 4182
    move p1, v1

    :cond_4
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_5

    .line 4183
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "setMfnr to "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4184
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setMfnr(Z)V

    .line 4186
    :cond_5
    return-void
.end method

.method private updateMute()V
    .locals 0

    .line 4160
    return-void
.end method

.method private updateNormalWideLDC()V
    .locals 2

    .line 4352
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->shouldApplyNormalWideLDC()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setNormalWideLDC(Z)V

    .line 4353
    return-void
.end method

.method private updateOIS()V
    .locals 8

    .line 4135
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualCamera()Z

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_0

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    const/high16 v3, 0x3f800000    # 1.0f

    cmpl-float v0, v0, v3

    if-lez v0, :cond_0

    .line 4138
    move v0, v2

    goto :goto_0

    .line 4135
    :cond_0
    nop

    .line 4138
    move v0, v1

    :goto_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v3

    .line 4139
    nop

    .line 4141
    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v5, 0xa7

    if-ne v4, v5, :cond_1

    .line 4142
    const-string v4, "pref_qc_camera_exposuretime_key"

    const v5, 0x7f090136

    .line 4143
    invoke-virtual {p0, v5}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v5

    .line 4142
    invoke-direct {p0, v4, v5}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 4144
    invoke-static {v4}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v4

    const-wide/32 v6, 0x3b9aca00

    cmp-long v4, v4, v6

    if-lez v4, :cond_1

    .line 4145
    nop

    .line 4149
    move v4, v2

    goto :goto_1

    :cond_1
    move v4, v1

    :goto_1
    if-nez v0, :cond_3

    if-nez v3, :cond_3

    if-eqz v4, :cond_2

    goto :goto_2

    .line 4154
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v2}, Lcom/android/camera2/Camera2Proxy;->setEnableOIS(Z)V

    goto :goto_3

    .line 4152
    :cond_3
    :goto_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setEnableOIS(Z)V

    .line 4156
    :goto_3
    return-void
.end method

.method private updatePictureAndPreviewSize()V
    .locals 9

    .line 3607
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    const/16 v1, 0x100

    const/16 v2, 0x23

    if-eqz v0, :cond_0

    .line 3608
    nop

    .line 3613
    move v0, v2

    goto :goto_0

    .line 3610
    :cond_0
    nop

    .line 3613
    move v0, v1

    :goto_0
    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 3614
    invoke-virtual {v3, v0}, Lcom/android/camera2/CameraCapabilities;->getSupportedOutputSize(I)Ljava/util/List;

    move-result-object v0

    .line 3615
    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->getBestPictureSize(Ljava/util/List;)Lcom/android/camera/CameraSize;

    move-result-object v0

    .line 3616
    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    .line 3618
    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    const-class v4, Landroid/graphics/SurfaceTexture;

    .line 3619
    invoke-virtual {v3, v4}, Lcom/android/camera2/CameraCapabilities;->getSupportedOutputSize(Ljava/lang/Class;)Ljava/util/List;

    move-result-object v3

    .line 3620
    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    iget v5, p0, Lcom/android/camera/module/Camera2Module;->mBogusCameraId:I

    iget v6, v0, Lcom/android/camera/CameraSize;->width:I

    iget v7, v0, Lcom/android/camera/CameraSize;->height:I

    .line 3621
    invoke-static {v6, v7}, Lcom/android/camera/CameraSettings;->getPreviewAspectRatio(II)F

    move-result v6

    float-to-double v6, v6

    .line 3620
    invoke-static {v4, v5, v3, v6, v7}, Lcom/android/camera/Util;->getOptimalPreviewSize(IILjava/util/List;D)Lcom/android/camera/CameraSize;

    move-result-object v3

    iput-object v3, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    .line 3622
    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v3, v4}, Lcom/android/camera2/Camera2Proxy;->setPreviewSize(Lcom/android/camera/CameraSize;)V

    .line 3623
    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v3, v2}, Lcom/android/camera2/Camera2Proxy;->setPreviewFormat(I)V

    .line 3625
    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    const/4 v3, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x2

    if-eqz v2, :cond_4

    .line 3627
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 3628
    invoke-virtual {v2, v1}, Lcom/android/camera2/CameraCapabilities;->getSupportedOutputSize(I)Ljava/util/List;

    move-result-object v1

    .line 3629
    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v6, 0xa5

    if-ne v2, v6, :cond_2

    .line 3630
    invoke-static {v1}, Lcom/android/camera/PictureSizeManager;->getBestSquareSize(Ljava/util/List;)Lcom/android/camera/CameraSize;

    move-result-object v1

    iput-object v1, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    .line 3631
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v1}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v1

    if-nez v1, :cond_3

    .line 3632
    iget v1, v0, Lcom/android/camera/CameraSize;->width:I

    iget v2, v0, Lcom/android/camera/CameraSize;->height:I

    if-le v1, v2, :cond_1

    iget v0, v0, Lcom/android/camera/CameraSize;->height:I

    goto :goto_1

    :cond_1
    iget v0, v0, Lcom/android/camera/CameraSize;->width:I

    .line 3633
    :goto_1
    new-instance v1, Ljava/lang/RuntimeException;

    sget-object v2, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    new-array v5, v5, [Ljava/lang/Object;

    .line 3634
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v4

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v5, v3

    .line 3633
    const-string v0, "size %dx%d is not supported!"

    invoke-static {v2, v0, v5}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v1, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v1

    .line 3637
    :cond_2
    invoke-static {v1}, Lcom/android/camera/PictureSizeManager;->getBestPictureSize(Ljava/util/List;)Lcom/android/camera/CameraSize;

    move-result-object v1

    iput-object v1, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    .line 3639
    :cond_3
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "outputSize: "

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v6, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v2, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 3642
    :cond_4
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    sget-object v2, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v6, "updateSize: %dx%d %dx%d"

    const/4 v7, 0x4

    new-array v7, v7, [Ljava/lang/Object;

    iget v8, v0, Lcom/android/camera/CameraSize;->width:I

    .line 3643
    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    aput-object v8, v7, v4

    iget v0, v0, Lcom/android/camera/CameraSize;->height:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v7, v3

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v0, v0, Lcom/android/camera/CameraSize;->width:I

    .line 3644
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v7, v5

    const/4 v0, 0x3

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v3, v3, Lcom/android/camera/CameraSize;->height:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v7, v0

    .line 3642
    invoke-static {v2, v6, v7}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3646
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v0, v0, Lcom/android/camera/CameraSize;->width:I

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v1, v1, Lcom/android/camera/CameraSize;->height:I

    invoke-virtual {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->updateCameraScreenNailSize(II)V

    .line 3648
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->checkDisplayOrientation()V

    .line 3649
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v0, v0, Lcom/android/camera/CameraSize;->width:I

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v1, v1, Lcom/android/camera/CameraSize;->height:I

    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->setVideoSize(II)V

    .line 3650
    return-void
.end method

.method private updatePortraitLighting()V
    .locals 2

    .line 3851
    invoke-static {}, Lcom/android/camera/CameraSettings;->getPortraitLightingPattern()I

    move-result v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    .line 3852
    const-string v1, "0"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    xor-int/lit8 v1, v1, 0x1

    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsPortraitLightingOn:Z

    .line 3853
    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 3854
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setPortraitLighting(I)V

    .line 3856
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->setLightingEffect()V

    .line 3857
    return-void
.end method

.method private updateSaturation()V
    .locals 2

    .line 4402
    invoke-static {}, Lcom/android/camera/CameraSettings;->getSaturation()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 4403
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setSaturation(I)V

    .line 4404
    return-void
.end method

.method private updateScene()V
    .locals 3

    .line 3931
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    .line 3932
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v1}, Lcom/android/camera/MutexModeManager;->isSceneHdr()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 3933
    const-string v0, "18"

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    goto :goto_0

    .line 3934
    :cond_0
    const-string v1, "pref_camera_scenemode_setting_key"

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 3935
    const-string v0, "0"

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    goto :goto_0

    .line 3937
    :cond_1
    invoke-virtual {v0}, Lcom/android/camera/data/data/runing/DataItemRunning;->getComponentRunningSceneValue()Lcom/android/camera/data/data/runing/ComponentRunningSceneValue;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/ComponentRunningSceneValue;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    .line 3939
    :goto_0
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "sceneMode="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " mutexMode="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v2}, Lcom/android/camera/MutexModeManager;->getMutexMode()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3940
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->setSceneMode(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 3941
    const-string v0, "0"

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    .line 3943
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$20;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$20;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 3950
    const-string v0, "0"

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    const-string v0, "18"

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    .line 3951
    return-void

    .line 3953
    :cond_3
    return-void
.end method

.method private updateSceneModeUI()V
    .locals 7

    .line 3956
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    const-string v1, "pref_camera_scenemode_setting_key"

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v0

    .line 3957
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xac

    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 3958
    const/4 v2, 0x2

    if-eqz v0, :cond_2

    .line 3960
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentHdr()Lcom/android/camera/data/data/config/ComponentConfigHdr;

    move-result-object v0

    const/16 v3, 0xa3

    const-string v4, "off"

    .line 3961
    invoke-virtual {v0, v3, v4}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->setComponentValue(ILjava/lang/String;)V

    .line 3963
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSceneMode:Ljava/lang/String;

    invoke-static {v0}, Lcom/android/camera/CameraSettings;->getFlashModeByScene(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 3965
    if-eqz v1, :cond_1

    .line 3966
    const/4 v3, 0x1

    new-array v4, v3, [I

    const/16 v5, 0xc2

    const/4 v6, 0x0

    aput v5, v4, v6

    invoke-interface {v1, v4}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->disableMenuItem([I)V

    .line 3967
    const/16 v4, 0xc1

    if-eqz v0, :cond_0

    .line 3968
    new-array v0, v3, [I

    aput v4, v0, v6

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->disableMenuItem([I)V

    goto :goto_0

    .line 3970
    :cond_0
    new-array v0, v3, [I

    aput v4, v0, v6

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->enableMenuItem([I)V

    .line 3972
    :goto_0
    invoke-interface {v1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->hideExtraMenu()V

    .line 3974
    :cond_1
    goto :goto_1

    .line 3975
    :cond_2
    if-eqz v1, :cond_3

    .line 3976
    new-array v0, v2, [I

    fill-array-data v0, :array_0

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->enableMenuItem([I)V

    .line 3979
    :cond_3
    :goto_1
    new-array v0, v2, [I

    fill-array-data v0, :array_1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceInWorkThread([I)V

    .line 3980
    return-void

    nop

    :array_0
    .array-data 4
        0xc1
        0xc2
    .end array-data

    :array_1
    .array-data 4
        0xb
        0xa
    .end array-data
.end method

.method private updateSharpness()V
    .locals 2

    .line 4410
    invoke-static {}, Lcom/android/camera/CameraSettings;->getSharpness()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    .line 4411
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setSharpness(I)V

    .line 4412
    return-void
.end method

.method private updateShotDetermine()V
    .locals 7

    .line 3530
    nop

    .line 3531
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xab

    const/4 v2, 0x0

    if-ne v0, v1, :cond_3

    .line 3532
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBackCamera()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 3534
    invoke-static {}, Lcom/mi/config/b;->hS()Z

    move-result v0

    if-nez v0, :cond_1

    .line 3535
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fa()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    goto :goto_2

    .line 3537
    :cond_2
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fb()Z

    move-result v0

    goto :goto_2

    .line 3542
    :cond_3
    :goto_1
    move v0, v2

    :goto_2
    iget-boolean v3, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    const/16 v4, 0x8

    if-eqz v3, :cond_5

    .line 3543
    if-eqz v0, :cond_4

    const/4 v0, -0x3

    .line 3581
    :goto_3
    move v4, v0

    goto/16 :goto_7

    .line 3543
    :cond_4
    const/4 v0, -0x2

    goto :goto_3

    .line 3546
    :cond_5
    iput-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    .line 3548
    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v5, 0xa3

    const/4 v6, 0x5

    if-eq v3, v5, :cond_e

    const/16 v5, 0xa5

    if-eq v3, v5, :cond_e

    if-eq v3, v1, :cond_6

    .line 3550
    return-void

    .line 3563
    :cond_6
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isParallelSessionEnable()Z

    move-result v1

    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    .line 3564
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    if-eqz v1, :cond_c

    .line 3566
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->shouldDoMultiFrameCapture()Z

    move-result v1

    if-eqz v1, :cond_7

    .line 3567
    goto :goto_7

    .line 3569
    :cond_7
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualFrontCamera()Z

    move-result v1

    if-nez v1, :cond_a

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualCamera()Z

    move-result v1

    if-eqz v1, :cond_8

    goto :goto_5

    .line 3572
    :cond_8
    if-eqz v0, :cond_9

    const/4 v0, 0x7

    goto :goto_3

    .line 3581
    :cond_9
    :goto_4
    move v4, v6

    goto :goto_7

    .line 3570
    :cond_a
    :goto_5
    if-eqz v0, :cond_b

    const/4 v0, 0x6

    goto :goto_3

    :cond_b
    goto :goto_4

    .line 3576
    :cond_c
    if-eqz v0, :cond_d

    const/4 v2, 0x2

    .line 3581
    :goto_6
    move v4, v2

    goto :goto_7

    .line 3576
    :cond_d
    goto :goto_6

    .line 3554
    :cond_e
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isParallelSessionEnable()Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    .line 3555
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    if-nez v0, :cond_f

    .line 3556
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveShotOn()Z

    move-result v4

    goto :goto_7

    .line 3559
    :cond_f
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->shouldDoMultiFrameCapture()Z

    move-result v0

    if-eqz v0, :cond_10

    goto :goto_7

    .line 3561
    :cond_10
    move v4, v6

    .line 3581
    :goto_7
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "enableParallel="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v2, " shotType="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3582
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v4}, Lcom/android/camera2/Camera2Proxy;->setShotType(I)V

    .line 3583
    invoke-static {v4}, Lcom/xiaomi/camera/base/Constants;->isParallelEnabled(I)Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    .line 3584
    return-void
.end method

.method private updateSuperResolution()V
    .locals 2

    .line 4241
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 4242
    return-void

    .line 4246
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xad

    if-ne v0, v1, :cond_1

    .line 4247
    return-void

    .line 4252
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isUltraWideBackCamera()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4253
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "SR force off for ultra wide camera"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4254
    return-void

    .line 4257
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraSettings;->isSREnable()Z

    move-result v0

    if-nez v0, :cond_3

    .line 4258
    return-void

    .line 4261
    :cond_3
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    const/high16 v1, 0x3f800000    # 1.0f

    cmpl-float v0, v0, v1

    if-nez v0, :cond_5

    .line 4262
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    const-string v1, "pref_camera_super_resolution_key"

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_7

    .line 4263
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isSuperResolution()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 4264
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->resetMutexMode()V

    goto :goto_0

    .line 4266
    :cond_4
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_7

    .line 4267
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setSuperResolution(Z)V

    goto :goto_0

    .line 4272
    :cond_5
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 4273
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isSuperResolution()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 4274
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->resetMutexMode()V

    goto :goto_0

    .line 4277
    :cond_6
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isNormal()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 4278
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    const/16 v1, 0xa

    invoke-virtual {v0, v1}, Lcom/android/camera/MutexModeManager;->setMutexMode(I)V

    .line 4282
    :cond_7
    :goto_0
    return-void
.end method

.method private updateSwMfnr()V
    .locals 4

    .line 4235
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isUseSwMfnr()Z

    move-result v0

    .line 4236
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "setSwMfnr to "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4237
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setSwMfnr(Z)V

    .line 4238
    return-void
.end method

.method private updateThumbProgress(Z)V
    .locals 2

    .line 5056
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    .line 5057
    const/16 v1, 0xa2

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;

    .line 5058
    if-eqz v0, :cond_0

    .line 5059
    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;->updateLoading(Z)V

    .line 5061
    :cond_0
    return-void
.end method

.method private updateUltraWideLDC()V
    .locals 2

    .line 4376
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->shouldApplyUltraWideLDC()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setUltraWideLDC(Z)V

    .line 4377
    return-void
.end method

.method private updateWhiteBalance()V
    .locals 2

    .line 4285
    const-string v0, "pref_camera_whitebalance_key"

    const-string v1, "1"

    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 4286
    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setAWBMode(Ljava/lang/String;)V

    .line 4287
    return-void
.end method

.method private updateZsl()V
    .locals 2

    .line 4163
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa7

    if-eq v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 4164
    :goto_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setEnableZsl(Z)V

    .line 4165
    return-void
.end method


# virtual methods
.method public cancelFocus(Z)V
    .locals 1

    .line 403
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 404
    return-void

    .line 407
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrameAvailable()Z

    move-result v0

    if-nez v0, :cond_1

    .line 408
    return-void

    .line 411
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-nez v0, :cond_2

    .line 412
    return-void

    .line 415
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_4

    .line 416
    if-eqz p1, :cond_3

    .line 417
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v0, 0x4

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setFocusMode(I)V

    .line 419
    :cond_3
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->cancelFocus(I)V

    .line 422
    :cond_4
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result p1

    const/4 v0, 0x3

    if-eq p1, v0, :cond_5

    .line 423
    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 425
    :cond_5
    return-void
.end method

.method public checkDisplayOrientation()V
    .locals 2

    .line 4574
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isCreated()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4575
    return-void

    .line 4577
    :cond_0
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->checkDisplayOrientation()V

    .line 4578
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    if-eqz v0, :cond_1

    .line 4579
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mCameraDisplayOrientation:I

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setCameraDisplayOrientation(I)V

    .line 4582
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    if-eqz v0, :cond_2

    .line 4583
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mCameraDisplayOrientation:I

    invoke-virtual {v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setDisplayOrientation(I)V

    .line 4585
    :cond_2
    return-void
.end method

.method public closeCamera()V
    .locals 4

    .line 2273
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 2274
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->scanQRCodeEnabled()Z

    move-result v1

    if-nez v1, :cond_0

    invoke-static {}, Lcom/mi/config/b;->fT()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 2275
    :cond_0
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/zxing/PreviewDecodeManager;->quit()V

    .line 2277
    :cond_1
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v1, :cond_b

    .line 2279
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mBurstDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v1, :cond_2

    .line 2280
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mBurstDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 2283
    :cond_2
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;

    if-eqz v1, :cond_3

    .line 2284
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;

    invoke-interface {v1}, Lio/reactivex/FlowableEmitter;->onComplete()V

    .line 2287
    :cond_3
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v1, :cond_4

    .line 2288
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 2291
    :cond_4
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneFlowableEmitter:Lio/reactivex/FlowableEmitter;

    if-eqz v1, :cond_5

    .line 2292
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneFlowableEmitter:Lio/reactivex/FlowableEmitter;

    invoke-interface {v1}, Lio/reactivex/FlowableEmitter;->onComplete()V

    .line 2294
    :cond_5
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v1, :cond_6

    .line 2295
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 2297
    :cond_6
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v1, :cond_7

    .line 2298
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 2300
    :cond_7
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setScreenLightCallback(Lcom/android/camera2/Camera2Proxy$ScreenLightCallback;)V

    .line 2301
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setMetaDataCallback(Lcom/android/camera2/Camera2Proxy$CameraMetaDataCallback;)V

    .line 2304
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setErrorCallback(Lcom/android/camera2/Camera2Proxy$CameraErrorCallback;)V

    .line 2308
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->releaseCameraPreviewCallback(Lcom/android/camera2/Camera2Proxy$CameraPreviewCallback;)V

    .line 2309
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v2}, Lcom/android/camera2/Camera2Proxy;->setFocusCallback(Lcom/android/camera2/Camera2Proxy$FocusCallback;)V

    .line 2310
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setASD(Z)V

    .line 2311
    iput-object v2, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;

    .line 2312
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->scanQRCodeEnabled()Z

    move-result v1

    if-nez v1, :cond_8

    invoke-static {}, Lcom/mi/config/b;->fT()Z

    move-result v1

    if-eqz v1, :cond_9

    .line 2313
    :cond_8
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v3, 0x1

    invoke-virtual {v1, v3}, Lcom/android/camera2/Camera2Proxy;->stopPreviewCallback(Z)V

    .line 2316
    :cond_9
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    if-eqz v1, :cond_a

    .line 2317
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    .line 2340
    :cond_a
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    .line 2342
    iput-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 2345
    :cond_b
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    if-eqz v1, :cond_c

    .line 2346
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v1, v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setAeAwbLock(Z)V

    .line 2347
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->destroy()V

    .line 2349
    :cond_c
    return-void
.end method

.method public varargs consumePreference([I)V
    .locals 3
    .param p1    # [I
        .annotation build Lcom/android/camera/constant/UpdateConstant$UpdateType;
        .end annotation
    .end param

    .line 3339
    array-length v0, p1

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    aget v2, p1, v1

    .line 3340
    packed-switch v2, :pswitch_data_0

    .line 3518
    :pswitch_0
    new-instance p1, Ljava/lang/RuntimeException;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "no consumer for this updateType: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 3516
    :pswitch_1
    goto/16 :goto_1

    .line 3508
    :pswitch_2
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateLiveShot()V

    .line 3509
    goto/16 :goto_1

    .line 3512
    :pswitch_3
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFNumber()V

    .line 3513
    goto/16 :goto_1

    .line 3456
    :pswitch_4
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateUltraWideLDC()V

    .line 3457
    goto/16 :goto_1

    .line 3452
    :pswitch_5
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateNormalWideLDC()V

    .line 3453
    goto/16 :goto_1

    .line 3504
    :pswitch_6
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateEyeLight()V

    .line 3505
    goto/16 :goto_1

    .line 3343
    :pswitch_7
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateShotDetermine()V

    .line 3344
    goto/16 :goto_1

    .line 3500
    :pswitch_8
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updatePortraitLighting()V

    .line 3501
    goto/16 :goto_1

    .line 3420
    :pswitch_9
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSwMfnr()V

    .line 3421
    goto/16 :goto_1

    .line 3496
    :pswitch_a
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFrontMirror()V

    .line 3497
    goto/16 :goto_1

    .line 3488
    :pswitch_b
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFaceScore()V

    .line 3489
    goto/16 :goto_1

    .line 3484
    :pswitch_c
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFaceAgeAnalyze()V

    .line 3485
    goto/16 :goto_1

    .line 3480
    :pswitch_d
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateBokeh()V

    .line 3481
    goto/16 :goto_1

    .line 3476
    :pswitch_e
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateAiScene()V

    .line 3477
    goto/16 :goto_1

    .line 3472
    :pswitch_f
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateDeviceOrientation()V

    .line 3473
    goto/16 :goto_1

    .line 3416
    :pswitch_10
    invoke-static {}, Lcom/android/camera/CameraSettings;->isMfnrSatEnable()Z

    move-result v2

    invoke-direct {p0, v2}, Lcom/android/camera/module/Camera2Module;->updateMfnr(Z)V

    .line 3417
    goto/16 :goto_1

    .line 3424
    :pswitch_11
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSuperResolution()V

    .line 3425
    goto/16 :goto_1

    .line 3468
    :pswitch_12
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateExposureMeteringMode()V

    .line 3469
    goto/16 :goto_1

    .line 3464
    :pswitch_13
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSharpness()V

    .line 3465
    goto/16 :goto_1

    .line 3460
    :pswitch_14
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSaturation()V

    .line 3461
    goto/16 :goto_1

    .line 3448
    :pswitch_15
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateContrast()V

    .line 3449
    goto/16 :goto_1

    .line 3444
    :pswitch_16
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->focusCenter()V

    .line 3445
    goto/16 :goto_1

    .line 3440
    :pswitch_17
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getZoomValue()F

    move-result v2

    invoke-virtual {p0, v2}, Lcom/android/camera/module/Camera2Module;->setZoomRatio(F)V

    .line 3441
    goto/16 :goto_1

    .line 3400
    :pswitch_18
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateDecodePreview()V

    .line 3401
    goto :goto_1

    .line 3412
    :pswitch_19
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateZsl()V

    .line 3413
    goto :goto_1

    .line 3408
    :pswitch_1a
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateMute()V

    .line 3409
    goto :goto_1

    .line 3404
    :pswitch_1b
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateOIS()V

    .line 3405
    goto :goto_1

    .line 3388
    :pswitch_1c
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFpsRange()V

    .line 3389
    goto :goto_1

    .line 3436
    :pswitch_1d
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateExposureTime()V

    .line 3437
    goto :goto_1

    .line 3432
    :pswitch_1e
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateISO()V

    .line 3433
    goto :goto_1

    .line 3384
    :pswitch_1f
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFocusMode()V

    .line 3385
    goto :goto_1

    .line 3379
    :pswitch_20
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateBeauty()V

    .line 3380
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateEyeLight()V

    .line 3381
    goto :goto_1

    .line 3492
    :pswitch_21
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->setEvValue()V

    .line 3493
    goto :goto_1

    .line 3367
    :pswitch_22
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateHDRPreference()V

    .line 3368
    goto :goto_1

    .line 3363
    :pswitch_23
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->updateFlashPreference()V

    .line 3364
    goto :goto_1

    .line 3396
    :pswitch_24
    invoke-static {}, Lcom/android/camera/CameraSettings;->getAntiBanding()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0, v2}, Lcom/android/camera/module/Camera2Module;->updateAntiBanding(Ljava/lang/String;)V

    .line 3397
    goto :goto_1

    .line 3359
    :pswitch_25
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateJpegThumbnailSize()V

    .line 3360
    goto :goto_1

    .line 3355
    :pswitch_26
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateJpegQuality()V

    .line 3356
    goto :goto_1

    .line 3428
    :pswitch_27
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateWhiteBalance()V

    .line 3429
    goto :goto_1

    .line 3392
    :pswitch_28
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFace()V

    .line 3393
    goto :goto_1

    .line 3376
    :pswitch_29
    goto :goto_1

    .line 3371
    :pswitch_2a
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFocusArea()V

    .line 3372
    goto :goto_1

    .line 3351
    :pswitch_2b
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFilter()V

    .line 3352
    goto :goto_1

    .line 3347
    :pswitch_2c
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updatePictureAndPreviewSize()V

    .line 3348
    nop

    .line 3339
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto/16 :goto_0

    .line 3521
    :cond_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2c
        :pswitch_2b
        :pswitch_2a
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_1d
        :pswitch_0
        :pswitch_0
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_0
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
    .end packed-switch
.end method

.method public enterMutexMode(I)V
    .locals 2

    .line 5006
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-nez v0, :cond_0

    .line 5007
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "enterMutexMode error, mCamera2Device is null"

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 5008
    return-void

    .line 5010
    :cond_0
    const/4 v0, 0x1

    if-eq p1, v0, :cond_3

    const/4 v1, 0x3

    if-eq p1, v1, :cond_2

    const/16 v1, 0xa

    if-eq p1, v1, :cond_1

    goto :goto_0

    .line 5020
    :cond_1
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setSuperResolution(Z)V

    goto :goto_0

    .line 5016
    :cond_2
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setHHT(Z)V

    .line 5017
    goto :goto_0

    .line 5012
    :cond_3
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setHDR(Z)V

    .line 5013
    nop

    .line 5023
    :goto_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isMfnrSatEnable()Z

    move-result p1

    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->updateMfnr(Z)V

    .line 5024
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSwMfnr()V

    .line 5025
    return-void
.end method

.method public exitMutexMode(I)V
    .locals 2

    .line 5029
    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eq p1, v0, :cond_2

    const/4 v0, 0x3

    if-eq p1, v0, :cond_1

    const/16 v0, 0xa

    if-eq p1, v0, :cond_0

    goto :goto_0

    .line 5040
    :cond_0
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v1}, Lcom/android/camera2/Camera2Proxy;->setSuperResolution(Z)V

    goto :goto_0

    .line 5036
    :cond_1
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v1}, Lcom/android/camera2/Camera2Proxy;->setHHT(Z)V

    .line 5037
    goto :goto_0

    .line 5031
    :cond_2
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v1}, Lcom/android/camera2/Camera2Proxy;->setHDR(Z)V

    .line 5032
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSuperResolution()V

    .line 5033
    nop

    .line 5043
    :goto_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isMfnrSatEnable()Z

    move-result p1

    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->updateMfnr(Z)V

    .line 5044
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateSwMfnr()V

    .line 5045
    return-void
.end method

.method protected focusCenter()V
    .locals 0

    .line 4070
    return-void
.end method

.method protected getBestPictureSize(Ljava/util/List;)Lcom/android/camera/CameraSize;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/android/camera/CameraSize;",
            ">;)",
            "Lcom/android/camera/CameraSize;"
        }
    .end annotation

    .line 4483
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getMaxPictureSize()I

    move-result v0

    invoke-static {p1, v0}, Lcom/android/camera/PictureSizeManager;->initialize(Ljava/util/List;I)V

    .line 4485
    invoke-static {}, Lcom/android/camera/PictureSizeManager;->getBestPictureSize()Lcom/android/camera/CameraSize;

    move-result-object p1

    return-object p1
.end method

.method public getCircularMediaRecorder()Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;
    .locals 2

    .line 614
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorderStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 615
    :try_start_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    .line 616
    monitor-exit v0

    .line 617
    return-object v1

    .line 616
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method protected getMaxPictureSize()I
    .locals 1

    .line 4489
    const/4 v0, 0x0

    return v0
.end method

.method protected getMutexHdrMode(Ljava/lang/String;)I
    .locals 1

    .line 4962
    const-string v0, "normal"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4963
    invoke-static {}, Lcom/mi/config/b;->isMTKPlatform()Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-static {}, Lcom/mi/config/b;->gJ()Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    .line 4965
    :cond_0
    const/4 p1, 0x5

    goto :goto_1

    .line 4964
    :cond_1
    :goto_0
    const/4 p1, 0x1

    .line 4963
    :goto_1
    return p1

    .line 4966
    :cond_2
    invoke-static {}, Lcom/mi/config/b;->gB()Z

    move-result v0

    if-eqz v0, :cond_3

    const-string v0, "live"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 4967
    const/4 p1, 0x2

    return p1

    .line 4969
    :cond_3
    const/4 p1, 0x0

    return p1
.end method

.method protected getOperatingMode()I
    .locals 7

    .line 2459
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isParallelSessionEnable()Z

    move-result v0

    const/16 v1, 0xab

    if-eqz v0, :cond_3

    .line 2461
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isEnableQcfa()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2462
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_QCFA"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2463
    const v0, 0x9001

    return v0

    .line 2465
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    if-ne v1, v0, :cond_2

    .line 2467
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDualFrontCamera()Z

    move-result v0

    if-nez v0, :cond_1

    .line 2468
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_SINGLE_BOKEH"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2469
    const v0, 0x9003

    return v0

    .line 2471
    :cond_1
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_DUAL_BOKEH"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2472
    const v0, 0x9000

    return v0

    .line 2475
    :cond_2
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "getOperatingMode: SESSION_OPERATION_MODE_ALGO_UP_SAT"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2476
    const v0, 0x9002

    return v0

    .line 2482
    :cond_3
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    const v2, 0x8001

    const/16 v3, 0xa3

    const v4, 0x8007

    const v5, 0x8002

    if-eqz v0, :cond_8

    .line 2483
    invoke-static {}, Lcom/android/camera/fragment/beauty/BeautyParameters;->isFaceBeautyOn()Z

    move-result v0

    sput-boolean v0, Lcom/android/camera/module/Camera2Module;->mIsBeautyFrontOn:Z

    .line 2484
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 2485
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fb()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 2486
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBokehFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 2487
    nop

    .line 2504
    :goto_0
    move v2, v5

    goto :goto_2

    .line 2489
    :cond_4
    const v0, 0x80f1

    .line 2504
    :goto_1
    move v2, v0

    goto :goto_2

    .line 2491
    :cond_5
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBokehFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 2493
    goto :goto_0

    .line 2494
    :cond_6
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isSupportedQcfa()Z

    move-result v0

    if-eqz v0, :cond_7

    sget-boolean v0, Lcom/android/camera/module/Camera2Module;->mIsBeautyFrontOn:Z

    if-nez v0, :cond_7

    const-string v0, "off"

    .line 2495
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v1

    .line 2496
    invoke-virtual {v1}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentHdr()Lcom/android/camera/data/data/config/ComponentConfigHdr;

    move-result-object v1

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v1, v2}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->getComponentValue(I)Ljava/lang/String;

    move-result-object v1

    .line 2495
    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_7

    .line 2497
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fS()I

    move-result v0

    if-gtz v0, :cond_7

    .line 2499
    nop

    .line 2504
    move v2, v4

    goto :goto_2

    .line 2501
    :cond_7
    const v0, 0x8005

    goto :goto_1

    .line 2504
    :goto_2
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    if-ne v0, v3, :cond_f

    .line 2505
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_f

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 2506
    invoke-static {v0}, Lcom/android/camera/CameraSettings;->isFrontSupportedUltraPixelPhotography(Lcom/android/camera2/CameraCapabilities;)Z

    move-result v0

    if-eqz v0, :cond_f

    .line 2507
    nop

    .line 2539
    move v2, v4

    goto :goto_3

    .line 2510
    :cond_8
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    .line 2511
    if-eq v0, v3, :cond_d

    const/16 v3, 0xa7

    if-eq v0, v3, :cond_b

    if-eq v0, v1, :cond_a

    const/16 v1, 0xad

    if-eq v0, v1, :cond_9

    .line 2535
    goto :goto_3

    .line 2524
    :cond_9
    const v2, 0x800a

    .line 2525
    goto :goto_3

    .line 2513
    :cond_a
    nop

    .line 2514
    nop

    .line 2539
    move v2, v5

    goto :goto_3

    .line 2516
    :cond_b
    invoke-static {}, Lcom/android/camera/CameraSettings;->isUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_c

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 2517
    invoke-static {v0}, Lcom/android/camera/CameraSettings;->isSupportedUltraPixelPhotography(Lcom/android/camera2/CameraCapabilities;)Z

    move-result v0

    if-eqz v0, :cond_c

    .line 2518
    const v2, 0x80f5

    goto :goto_3

    .line 2520
    :cond_c
    const v2, 0x8003

    .line 2522
    goto :goto_3

    .line 2527
    :cond_d
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-eqz v0, :cond_e

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 2528
    invoke-static {v0}, Lcom/android/camera/CameraSettings;->isSupportedUltraPixelPhotography(Lcom/android/camera2/CameraCapabilities;)Z

    move-result v0

    if-eqz v0, :cond_e

    .line 2529
    const v2, 0x80f3

    goto :goto_3

    .line 2531
    :cond_e
    nop

    .line 2533
    nop

    .line 2539
    :cond_f
    :goto_3
    iput v2, p0, Lcom/android/camera/module/Camera2Module;->mOperatingMode:I

    .line 2540
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "getOperatingMode: "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "operatingMode = 0x%x"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v4, v5

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2541
    return v2
.end method

.method public initializeCapabilities()V
    .locals 2

    .line 3294
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->initializeCapabilities()V

    .line 3295
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    .line 3297
    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getSupportedFocusModes()[I

    move-result-object v0

    .line 3295
    const/4 v1, 0x4

    invoke-static {v1, v0}, Lcom/android/camera/Util;->isSupported(I[I)Z

    move-result v0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mContinuousFocusSupported:Z

    .line 3298
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getMaxFaceCount()I

    move-result v0

    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mMaxFaceCount:I

    .line 3299
    return-void
.end method

.method protected isAutoRestartInNonZSL()Z
    .locals 1

    .line 2899
    const/4 v0, 0x0

    return v0
.end method

.method public isBeautyBodySlimCountDetectStarted()Z
    .locals 1

    .line 1268
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsBeautyBodySlimOn:Z

    return v0
.end method

.method protected isDetectedHHT()Z
    .locals 1

    .line 4957
    const/4 v0, 0x0

    return v0
.end method

.method public isDoingAction()Z
    .locals 2

    .line 1698
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isZooming:Z

    if-nez v0, :cond_1

    .line 1700
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isKeptBitmapTexture()Z

    move-result v0

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez v0, :cond_1

    .line 1702
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-eqz v0, :cond_1

    .line 1703
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_1

    .line 1704
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isParallelQueueFull()Z

    move-result v0

    if-nez v0, :cond_1

    .line 1705
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isInCountDown()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    .line 1698
    :goto_1
    return v0
.end method

.method protected isFaceBeautyMode()Z
    .locals 1

    .line 986
    const/4 v0, 0x0

    return v0
.end method

.method public isFaceDetectStarted()Z
    .locals 1

    .line 955
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    return v0
.end method

.method public isHdrDetectStarted()Z
    .locals 1

    .line 857
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mHdrCheckEnabled:Z

    return v0
.end method

.method public isKeptBitmapTexture()Z
    .locals 1

    .line 1806
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mKeepBitmapTexture:Z

    return v0
.end method

.method public isMeteringAreaOnly()Z
    .locals 3

    .line 4994
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 4995
    return v1

    .line 4998
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->getFocusMode()I

    move-result v0

    .line 4999
    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mFocusAreaSupported:Z

    if-nez v2, :cond_1

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mMeteringAreaSupported:Z

    if-nez v2, :cond_3

    :cond_1
    const/4 v2, 0x5

    if-eq v2, v0, :cond_3

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    goto :goto_1

    :cond_3
    :goto_0
    const/4 v1, 0x1

    :goto_1
    return v1
.end method

.method public isNeedMute()Z
    .locals 1

    .line 1008
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveShotOn()Z

    move-result v0

    return v0
.end method

.method public isSelectingCapturedResult()Z
    .locals 3

    .line 1830
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 1831
    return v1

    .line 1835
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v2, 0xa0

    invoke-virtual {v0, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 1836
    if-eqz v0, :cond_1

    const v2, 0x7f0d001b

    invoke-interface {v0, v2}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->getActiveFragment(I)I

    move-result v0

    const/16 v2, 0xff3

    if-ne v0, v2, :cond_1

    const/4 v1, 0x1

    nop

    :cond_1
    return v1
.end method

.method public isShowAeAfLockIndicator()Z
    .locals 1

    .line 4982
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    return v0
.end method

.method public isShowCaptureButton()Z
    .locals 1

    .line 4977
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isBurstShoot()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isSupportFocusShoot()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isSupportFocusShoot()Z
    .locals 2

    .line 4987
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    const-string v1, "pref_camera_focus_shoot_key"

    .line 4988
    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/global/DataItemGlobal;->isGlobalSwitchOn(Ljava/lang/String;)Z

    move-result v0

    .line 4987
    return v0
.end method

.method protected isSupportSceneMode()Z
    .locals 1

    .line 4479
    const/4 v0, 0x0

    return v0
.end method

.method public isUltraWideDetectStarted()Z
    .locals 1

    .line 5872
    const/4 v0, 0x1

    return v0
.end method

.method public isUnInterruptable()Z
    .locals 2

    .line 1710
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mUnInterruptableReason:Ljava/lang/String;

    .line 1711
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isKeptBitmapTexture()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1712
    const-string v0, "bitmap cover"

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mUnInterruptableReason:Ljava/lang/String;

    goto :goto_0

    .line 1713
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-ne v0, v1, :cond_1

    .line 1714
    const-string v0, "snapshot"

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mUnInterruptableReason:Ljava/lang/String;

    .line 1716
    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mUnInterruptableReason:Ljava/lang/String;

    if-eqz v0, :cond_2

    const/4 v0, 0x1

    goto :goto_1

    :cond_2
    const/4 v0, 0x0

    :goto_1
    return v0
.end method

.method public isUseFaceInfo()Z
    .locals 1

    .line 960
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsGenderAgeOn:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMagicMirrorOn:Z

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

.method protected isZoomEnabled()Z
    .locals 2

    .line 4887
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    .line 4888
    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isUbiFocus()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4889
    invoke-static {}, Lcom/android/camera/CameraSettings;->isStereoModeOn()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4890
    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4891
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4892
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4893
    invoke-static {}, Lcom/android/camera/CameraSettings;->isUltraPixelPhotographyOn()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 4887
    :goto_0
    return v0
.end method

.method public multiCapture()Z
    .locals 5

    .line 443
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 444
    return v1

    .line 448
    :cond_0
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPendingMultiCapture:Z

    if-nez v0, :cond_1

    .line 449
    return v1

    .line 451
    :cond_1
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mPendingMultiCapture:Z

    .line 453
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getScreenHint()Lcom/android/camera/ui/ScreenHint;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/ui/ScreenHint;->updateHint()V

    .line 454
    invoke-static {}, Lcom/android/camera/storage/Storage;->isLowStorageAtLastPoint()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 455
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Not enough space or storage not ready. remaining="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 456
    invoke-static {}, Lcom/android/camera/storage/Storage;->getLeftSpace()J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 455
    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 457
    return v1

    .line 460
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/storage/ImageSaver;->isBusy()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 461
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v2, "ImageSaver is busy, wait for a moment!"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 462
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v0}, Lcom/android/camera/ui/RotateTextToast;->getInstance(Landroid/app/Activity;)Lcom/android/camera/ui/RotateTextToast;

    move-result-object v0

    const v2, 0x7f0901b5

    invoke-virtual {v0, v2, v1}, Lcom/android/camera/ui/RotateTextToast;->show(II)V

    .line 463
    return v1

    .line 468
    :cond_3
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa4

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 469
    const-string v1, "d"

    const/4 v2, 0x6

    new-array v2, v2, [I

    fill-array-data v2, :array_0

    invoke-interface {v0, v1, v2}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->closeMutexElement(Ljava/lang/String;[I)V

    .line 477
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->prepareMultiCapture()V

    .line 479
    new-instance v0, Lcom/android/camera/module/Camera2Module$1;

    invoke-direct {v0, p0}, Lcom/android/camera/module/Camera2Module$1;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-static {v0}, Lio/reactivex/Observable;->create(Lio/reactivex/ObservableOnSubscribe;)Lio/reactivex/Observable;

    move-result-object v0

    .line 484
    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v1

    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object v0

    .line 488
    new-instance v1, Lcom/android/camera/module/Camera2Module$3;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$3;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Observable;

    move-result-object v0

    new-instance v1, Lcom/android/camera/module/Camera2Module$2;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$2;-><init>(Lcom/android/camera/module/Camera2Module;)V

    .line 507
    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/Observer;)V

    .line 538
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mBurstNextDelayTime:J

    .line 539
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isParallelSessionEnable()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 540
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/16 v1, 0x9

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setShotType(I)V

    .line 541
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    new-instance v2, Lcom/android/camera/module/Camera2Module$JpegRepeatingCaptureCallback;

    const/4 v3, 0x0

    invoke-direct {v2, p0, v3}, Lcom/android/camera/module/Camera2Module$JpegRepeatingCaptureCallback;-><init>(Lcom/android/camera/module/Camera2Module;Lcom/android/camera/module/Camera2Module$1;)V

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 543
    invoke-virtual {v3}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v3

    .line 541
    invoke-virtual {v0, v1, v2, v3}, Lcom/android/camera2/Camera2Proxy;->captureBurstPictures(ILcom/android/camera2/Camera2Proxy$PictureCallback;Lcom/xiaomi/camera/core/ParallelCallback;)V

    goto :goto_0

    .line 545
    :cond_4
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setShotType(I)V

    .line 546
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mTotalJpegCallbackNum:I

    new-instance v2, Lcom/android/camera/module/Camera2Module$JpegQuickPictureCallback;

    .line 547
    invoke-static {}, Lcom/android/camera/LocationManager;->instance()Lcom/android/camera/LocationManager;

    move-result-object v3

    invoke-virtual {v3}, Lcom/android/camera/LocationManager;->getCurrentLocation()Landroid/location/Location;

    move-result-object v3

    invoke-direct {v2, p0, v3}, Lcom/android/camera/module/Camera2Module$JpegQuickPictureCallback;-><init>(Lcom/android/camera/module/Camera2Module;Landroid/location/Location;)V

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 548
    invoke-virtual {v3}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object v3

    .line 546
    invoke-virtual {v0, v1, v2, v3}, Lcom/android/camera2/Camera2Proxy;->captureBurstPictures(ILcom/android/camera2/Camera2Proxy$PictureCallback;Lcom/xiaomi/camera/core/ParallelCallback;)V

    .line 551
    :goto_0
    const/4 v0, 0x1

    return v0

    :array_0
    .array-data 4
        0xc1
        0xc2
        0xc4
        0xef
        0xc9
        0xce
    .end array-data
.end method

.method public notifyFocusAreaUpdate()V
    .locals 3

    .line 997
    const/4 v0, 0x1

    new-array v0, v0, [I

    const/4 v1, 0x0

    const/4 v2, 0x3

    aput v2, v0, v1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceTrampoline([I)V

    .line 998
    return-void
.end method

.method public onBackPressed()Z
    .locals 7

    .line 1870
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrameAvailable()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 1871
    return v1

    .line 1874
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->tryRemoveCountDownMessage()V

    .line 1876
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    .line 1877
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonLongClickCancel(Z)V

    .line 1878
    return v2

    .line 1881
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-ne v0, v1, :cond_3

    .line 1882
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 1883
    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v4, 0xad

    if-ne v3, v4, :cond_2

    .line 1884
    iget-wide v3, p0, Lcom/android/camera/module/Camera2Module;->mLastBackPressedTime:J

    sub-long v3, v0, v3

    const-wide/16 v5, 0xbb8

    cmp-long v3, v3, v5

    if-lez v3, :cond_3

    .line 1885
    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mLastBackPressedTime:J

    .line 1886
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const v1, 0x7f090123

    invoke-static {v0, v1}, Lcom/android/camera/ToastUtils;->showToast(Landroid/content/Context;I)V

    .line 1887
    return v2

    .line 1890
    :cond_2
    iget-wide v3, p0, Lcom/android/camera/module/Camera2Module;->mLastCaptureTime:J

    sub-long/2addr v0, v3

    const-wide/16 v3, 0x2ee0

    cmp-long v0, v0, v3

    if-gez v0, :cond_3

    .line 1891
    return v2

    .line 1896
    :cond_3
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onBackPressed()Z

    move-result v0

    return v0
.end method

.method public onBeautyBodySlimCountChange(Z)V
    .locals 2

    .line 1250
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$8;

    invoke-direct {v1, p0, p1}, Lcom/android/camera/module/Camera2Module$8;-><init>(Lcom/android/camera/module/Camera2Module;Z)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 1264
    return-void
.end method

.method public onBeautyChanged()V
    .locals 0

    .line 871
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->onBeautyParameterChanged()V

    .line 872
    return-void
.end method

.method public onBroadcastReceived(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 4

    .line 589
    if-eqz p2, :cond_5

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 592
    :cond_0
    const-string v0, "android.media.action.VOICE_COMMAND"

    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 593
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "on Receive voice control broadcast action intent"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 594
    invoke-static {p2}, Lcom/android/camera/CameraIntentManager;->getInstance(Landroid/content/Intent;)Lcom/android/camera/CameraIntentManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->getVoiceControlAction()Ljava/lang/String;

    move-result-object v0

    .line 595
    iput-object p2, p0, Lcom/android/camera/module/Camera2Module;->mBroadcastIntent:Landroid/content/Intent;

    .line 596
    const/4 v1, -0x1

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v2

    const v3, 0x4bbb5326    # 2.4553036E7f

    if-eq v2, v3, :cond_1

    goto :goto_0

    :cond_1
    const-string v2, "CAPTURE"

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 v1, 0x0

    :cond_2
    :goto_0
    if-eqz v1, :cond_3

    goto :goto_1

    .line 598
    :cond_3
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getTriggerMode()I

    move-result v0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    .line 599
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBroadcastIntent:Landroid/content/Intent;

    .line 600
    nop

    .line 605
    :goto_1
    invoke-static {p2}, Lcom/android/camera/CameraIntentManager;->removeInstance(Landroid/content/Intent;)V

    .line 607
    :cond_4
    invoke-super {p0, p1, p2}, Lcom/android/camera/module/BaseModule;->onBroadcastReceived(Landroid/content/Context;Landroid/content/Intent;)V

    .line 608
    return-void

    .line 590
    :cond_5
    :goto_2
    return-void
.end method

.method protected onCameraOpened()V
    .locals 2

    .line 3253
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onCameraOpened()V

    .line 3254
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->initializeFocusManager()V

    .line 3256
    sget-object v0, Lcom/android/camera/constant/UpdateConstant;->CAMERA_TYPES_INIT:[I

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceTrampoline([I)V

    .line 3261
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isPortraitMode()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 3263
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCameraCapabilities:Lcom/android/camera2/CameraCapabilities;

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getCameraCalibrationData()[B

    move-result-object v0

    .line 3264
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mActualCameraId:I

    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->getCalibrationDataFileName(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/Util;->saveCameraCalibrationToFile([BLjava/lang/String;)Z

    .line 3267
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isKeptBitmapTexture()Z

    move-result v0

    if-nez v0, :cond_1

    .line 3268
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->startPreview()V

    .line 3270
    :cond_1
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->initMetaParser()V

    .line 3271
    invoke-static {}, Lcom/mi/config/b;->gL()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 3272
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->initAiSceneParser()V

    .line 3275
    :cond_2
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mOnResumeTime:J

    .line 3276
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 3277
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x1f

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 3278
    return-void
.end method

.method public onCaptureStart(Lcom/xiaomi/camera/core/ParallelTaskData;Lcom/android/camera/CameraSize;)Lcom/xiaomi/camera/core/ParallelTaskData;
    .locals 5

    .line 2923
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveShotOn()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2924
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->startLiveShotAnimation()V

    .line 2927
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->onShutter()V

    .line 2929
    nop

    .line 2930
    invoke-static {}, Lcom/android/camera/CameraSettings;->isAgeGenderAndMagicMirrorWaterOpen()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 2931
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v2, 0xa6

    .line 2932
    invoke-virtual {v0, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    .line 2933
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->getFaceWaterMarkInfos()Ljava/util/List;

    move-result-object v0

    .line 2934
    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_1

    .line 2935
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    goto :goto_0

    .line 2938
    :cond_1
    move-object v2, v1

    :goto_0
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "onCaptureStart: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v4, p2, Lcom/android/camera/CameraSize;->width:I

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, "x"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v4, p2, Lcom/android/camera/CameraSize;->height:I

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2941
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    if-nez v0, :cond_2

    .line 2942
    invoke-virtual {p2}, Lcom/android/camera/CameraSize;->toSizeObject()Landroid/util/Size;

    move-result-object v0

    goto :goto_1

    .line 2944
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mOutPutSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v0}, Lcom/android/camera/CameraSize;->toSizeObject()Landroid/util/Size;

    move-result-object v0

    .line 2947
    :goto_1
    new-instance v3, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    .line 2948
    invoke-virtual {v4}, Lcom/android/camera/CameraSize;->toSizeObject()Landroid/util/Size;

    move-result-object v4

    invoke-virtual {p2}, Lcom/android/camera/CameraSize;->toSizeObject()Landroid/util/Size;

    move-result-object p2

    invoke-direct {v3, v4, p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;-><init>(Landroid/util/Size;Landroid/util/Size;Landroid/util/Size;)V

    .line 2949
    invoke-static {}, Lcom/android/camera/CameraSettings;->isDualCameraWaterMarkOpen()Z

    move-result p2

    invoke-virtual {v3, p2}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setHasDualWaterMark(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2950
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isFrontMirror()Z

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setMirror(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2951
    invoke-static {}, Lcom/android/camera/CameraSettings;->getPortraitLightingPattern()I

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setLightingPattern(I)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2952
    invoke-static {}, Lcom/android/camera/effect/EffectController;->getInstance()Lcom/android/camera/effect/EffectController;

    move-result-object v0

    const/4 v3, 0x0

    invoke-virtual {v0, v3}, Lcom/android/camera/effect/EffectController;->getEffectForSaving(Z)I

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setFilterId(I)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2953
    const/4 v0, -0x1

    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    if-ne v0, v4, :cond_3

    move v0, v3

    goto :goto_2

    :cond_3
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mOrientation:I

    :goto_2
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setOrientation(I)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mJpegRotation:I

    .line 2954
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setJpegRotation(I)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2955
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGradienterOn()Z

    move-result v0

    if-eqz v0, :cond_4

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mShootRotation:F

    const/high16 v4, -0x40800000    # -1.0f

    cmpl-float v0, v0, v4

    if-nez v0, :cond_4

    const/4 v0, 0x0

    goto :goto_3

    :cond_4
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mShootRotation:F

    :goto_3
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setShootRotation(F)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mShootOrientation:I

    .line 2956
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setShootOrientation(I)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mLocation:Landroid/location/Location;

    .line 2957
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setLocation(Landroid/location/Location;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2958
    invoke-static {}, Lcom/android/camera/CameraSettings;->isTimeWaterMarkOpen()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-static {}, Lcom/android/camera/Util;->getTimeWatermark()Ljava/lang/String;

    move-result-object v1

    nop

    :cond_5
    invoke-virtual {p2, v1}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setTimeWaterMarkString(Ljava/lang/String;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2959
    invoke-virtual {p2, v2}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setFaceWaterMarkList(Ljava/util/List;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2960
    invoke-static {}, Lcom/android/camera/CameraSettings;->isAgeGenderAndMagicMirrorWaterOpen()Z

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setAgeGenderAndMagicMirrorWater(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2961
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setFrontCamera(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2962
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBokehFrontCamera()Z

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setBokehFrontCamera(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mAlgorithmName:Ljava/lang/String;

    .line 2963
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setAlgorithmName(Ljava/lang/String;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2964
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getPictureInfo()Lcom/xiaomi/camera/core/PictureInfo;

    move-result-object v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setPictureInfo(Lcom/xiaomi/camera/core/PictureInfo;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2965
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getSuffix()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setSuffix(Ljava/lang/String;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsGradienterOn:Z

    .line 2966
    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setGradienterOn(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2967
    invoke-static {}, Lcom/android/camera/module/Camera2Module;->getTiltShiftMode()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setTiltShiftMode(Ljava/lang/String;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2968
    invoke-static {}, Lcom/android/camera/CameraSettings;->isSaveGroushotPrimitiveOn()Z

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setSaveGroupshotPrimitive(Z)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2969
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getDualWaterMarkParam()Lcom/android/camera/effect/renders/DualWatermarkParam;

    move-result-object v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setDualWatermarkParam(Lcom/android/camera/effect/renders/DualWatermarkParam;)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2970
    invoke-static {v3}, Lcom/android/camera/module/Camera2Module;->getJpegQuality(Z)I

    move-result v0

    invoke-virtual {p2, v0}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->setJpegQuality(I)Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;

    move-result-object p2

    .line 2971
    invoke-virtual {p2}, Lcom/xiaomi/camera/core/ParallelTaskDataParameter$Builder;->build()Lcom/xiaomi/camera/core/ParallelTaskDataParameter;

    move-result-object p2

    .line 2973
    invoke-virtual {p1, p2}, Lcom/xiaomi/camera/core/ParallelTaskData;->fillParameter(Lcom/xiaomi/camera/core/ParallelTaskDataParameter;)V

    .line 2974
    iget-boolean p2, p0, Lcom/android/camera/module/Camera2Module;->mEnabledPreviewThumbnail:Z

    const/4 v0, 0x1

    xor-int/2addr p2, v0

    invoke-virtual {p1, p2}, Lcom/xiaomi/camera/core/ParallelTaskData;->setNeedThumbnail(Z)V

    .line 2975
    iget p2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {p1, p2}, Lcom/xiaomi/camera/core/ParallelTaskData;->setCurrentModuleIndex(I)V

    .line 2977
    sget-object p2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onCaptureStart: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {p2, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2978
    iget-boolean p2, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    if-eqz p2, :cond_6

    .line 2979
    invoke-direct {p0, p1, v0}, Lcom/android/camera/module/Camera2Module;->beginParallelProcess(Lcom/xiaomi/camera/core/ParallelTaskData;Z)V

    .line 2982
    :cond_6
    invoke-static {}, Lcom/android/camera/CameraSettings;->isHangGestureOpen()Z

    move-result p2

    if-eqz p2, :cond_7

    .line 2983
    sget-object p2, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "send msg: reset hand gesture"

    invoke-static {p2, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2984
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v0, 0x39

    invoke-virtual {p2, v0}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 2985
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const-wide/16 v1, 0x7d0

    invoke-virtual {p2, v0, v1, v2}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    .line 2987
    :cond_7
    return-object p1
.end method

.method public onCreate(II)V
    .locals 0

    .line 2235
    invoke-super {p0, p1, p2}, Lcom/android/camera/module/BaseModule;->onCreate(II)V

    .line 2237
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->parseIntent()V

    .line 2239
    new-instance p1, Lcom/android/camera/module/Camera2Module$MainHandler;

    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p2}, Lcom/android/camera/Camera;->getMainLooper()Landroid/os/Looper;

    move-result-object p2

    invoke-direct {p1, p0, p2}, Lcom/android/camera/module/Camera2Module$MainHandler;-><init>(Lcom/android/camera/module/Camera2Module;Landroid/os/Looper;)V

    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    .line 2240
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object p1

    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mSensorStateListener:Lcom/android/camera/SensorStateManager$SensorStateListener;

    invoke-virtual {p1, p2}, Lcom/android/camera/SensorStateManager;->setSensorStateListener(Lcom/android/camera/SensorStateManager$SensorStateListener;)V

    .line 2242
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->onCameraOpened()V

    .line 2244
    new-instance p1, Lcom/google/lens/sdk/LensApi;

    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-direct {p1, p2}, Lcom/google/lens/sdk/LensApi;-><init>(Landroid/content/Context;)V

    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mLensApi:Lcom/google/lens/sdk/LensApi;

    .line 2245
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mLensApi:Lcom/google/lens/sdk/LensApi;

    new-instance p2, Lcom/android/camera/module/Camera2Module$15;

    invoke-direct {p2, p0}, Lcom/android/camera/module/Camera2Module$15;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {p1, p2}, Lcom/google/lens/sdk/LensApi;->checkLensAvailability(Lcom/google/lens/sdk/LensApi$LensAvailabilityCallback;)V

    .line 2253
    return-void
.end method

.method public onDestroy()V
    .locals 2

    .line 2007
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onDestroy()V

    .line 2008
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v0, :cond_0

    .line 2009
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x2d

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 2011
    :cond_0
    return-void
.end method

.method public onFaceBeautySwitched(Z)V
    .locals 0

    .line 863
    if-eqz p1, :cond_0

    const-string p1, "pref_camera_face_beauty_advanced_key"

    goto :goto_0

    :cond_0
    const-string p1, "pref_camera_face_beauty_key"

    .line 862
    :goto_0
    invoke-static {p1}, Lcom/android/camera/CameraSettings;->setFaceBeautySwitch(Ljava/lang/String;)V

    .line 864
    invoke-static {}, Lcom/mi/config/b;->hM()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 865
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->onBeautyChanged()V

    .line 867
    :cond_1
    return-void
.end method

.method public onFaceDetected([Lcom/android/camera2/CameraHardwareFace;Lcom/android/camera/effect/FaceAnalyzeInfo;)V
    .locals 3

    .line 912
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result p2

    if-eqz p2, :cond_7

    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p2}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object p2

    invoke-virtual {p2}, Lcom/android/camera/CameraScreenNail;->getFrameAvailableFlag()Z

    move-result p2

    if-nez p2, :cond_0

    goto :goto_1

    .line 916
    :cond_0
    if-nez p1, :cond_1

    .line 917
    return-void

    .line 921
    :cond_1
    invoke-static {}, Lcom/mi/config/b;->gE()Z

    move-result p2

    if-eqz p2, :cond_2

    array-length p2, p1

    if-lez p2, :cond_2

    const/4 p2, 0x0

    aget-object p2, p1, p2

    iget p2, p2, Lcom/android/camera2/CameraHardwareFace;->faceType:I

    const v0, 0xface

    if-ne p2, v0, :cond_2

    .line 923
    iget-boolean p2, p0, Lcom/android/camera/module/Camera2Module;->mObjectTrackingStarted:Z

    if-eqz p2, :cond_6

    .line 924
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    const/4 v0, 0x3

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActiveArraySize()Landroid/graphics/Rect;

    move-result-object v1

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    invoke-interface {p2, v0, p1, v1, v2}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setFaces(I[Lcom/android/camera2/CameraHardwareFace;Landroid/graphics/Rect;F)Z

    goto :goto_0

    .line 928
    :cond_2
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActiveArraySize()Landroid/graphics/Rect;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    const/4 v2, 0x1

    invoke-interface {p2, v2, p1, v0, v1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setFaces(I[Lcom/android/camera2/CameraHardwareFace;Landroid/graphics/Rect;F)Z

    move-result p2

    if-nez p2, :cond_3

    .line 929
    return-void

    .line 932
    :cond_3
    iget-boolean p2, p0, Lcom/android/camera/module/Camera2Module;->mIsPortraitLightingOn:Z

    if-eqz p2, :cond_4

    .line 933
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {p2, p1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->lightingDetectFace([Lcom/android/camera2/CameraHardwareFace;)V

    .line 942
    :cond_4
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {p1, v2}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->isFaceExists(I)Z

    move-result p1

    const/16 p2, 0x38

    if-eqz p1, :cond_5

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    .line 943
    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->isFocusViewVisible()Z

    move-result p1

    if-eqz p1, :cond_5

    .line 944
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    invoke-virtual {p1, p2}, Lcom/android/camera/module/Camera2Module$MainHandler;->hasMessages(I)Z

    move-result p1

    if-nez p1, :cond_6

    .line 945
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    invoke-virtual {p1, p2}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    goto :goto_0

    .line 948
    :cond_5
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    invoke-virtual {p1, p2}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 951
    :cond_6
    :goto_0
    return-void

    .line 913
    :cond_7
    :goto_1
    return-void
.end method

.method public onFilterChanged(II)V
    .locals 3

    .line 1863
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onFilterChanged: category = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", newIndex = "

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1864
    const/4 p1, 0x1

    new-array p1, p1, [I

    const/4 p2, 0x0

    const/4 v0, 0x2

    aput v0, p1, p2

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceTrampoline([I)V

    .line 1865
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->updateEffectViewVisible()V

    .line 1866
    return-void
.end method

.method public onFocusStateChanged(Lcom/android/camera/module/loader/camera2/FocusTask;)V
    .locals 9

    .line 2607
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrameAvailable()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2608
    return-void

    .line 2611
    :cond_0
    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->getFocusTrigger()I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x3

    const/4 v3, 0x1

    packed-switch v0, :pswitch_data_0

    goto/16 :goto_1

    .line 2632
    :pswitch_0
    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->isFocusing()Z

    move-result v0

    .line 2633
    nop

    .line 2634
    const/4 v4, 0x0

    .line 2635
    if-eqz v0, :cond_1

    .line 2636
    const-string v4, "onAutoFocusMoving start"

    .line 2637
    iput v1, p0, Lcom/android/camera/module/Camera2Module;->mAFEndLogTimes:I

    goto :goto_0

    .line 2638
    :cond_1
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mAFEndLogTimes:I

    if-nez v0, :cond_2

    .line 2639
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onAutoFocusMoving end. result="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->isSuccess()Z

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 2640
    nop

    .line 2641
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mAFEndLogTimes:I

    add-int/2addr v0, v3

    iput v0, p0, Lcom/android/camera/module/Camera2Module;->mAFEndLogTimes:I

    .line 2643
    :cond_2
    :goto_0
    sget-boolean v0, Lcom/android/camera/Util;->sIsDumpLog:Z

    if-eqz v0, :cond_3

    if-eqz v4, :cond_3

    .line 2644
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    invoke-static {v0, v4}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 2648
    :cond_3
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-ne v0, v2, :cond_4

    .line 2649
    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->getFocusTrigger()I

    move-result v0

    if-ne v0, v2, :cond_6

    .line 2650
    :cond_4
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-nez v0, :cond_6

    .line 2651
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0, p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->onFocusResult(Lcom/android/camera/module/loader/camera2/FocusTask;)V

    goto :goto_1

    .line 2613
    :pswitch_1
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    sget-object v4, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v5, "FocusTime=%1$dms focused=%2$b"

    const/4 v6, 0x2

    new-array v6, v6, [Ljava/lang/Object;

    .line 2614
    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->getElapsedTime()J

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v7

    aput-object v7, v6, v1

    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->isSuccess()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    aput-object v1, v6, v3

    .line 2613
    invoke-static {v4, v5, v6}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 2616
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->isFocusingSnapOnFinish()Z

    move-result v0

    if-nez v0, :cond_5

    .line 2617
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-eq v0, v2, :cond_5

    .line 2618
    invoke-virtual {p0, v3}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 2622
    :cond_5
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0, p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->onFocusResult(Lcom/android/camera/module/loader/camera2/FocusTask;)V

    .line 2623
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/SensorStateManager;->reset()V

    .line 2625
    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusTask;->isSuccess()Z

    move-result p1

    if-eqz p1, :cond_6

    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-eqz p1, :cond_6

    .line 2626
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v3}, Lcom/android/camera2/Camera2Proxy;->lockExposure(Z)V

    .line 2656
    :cond_6
    :goto_1
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method

.method public onGradienterSwitched(Z)V
    .locals 1

    .line 4678
    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mIsGradienterOn:Z

    .line 4679
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/android/camera/SensorStateManager;->setGradienterEnabled(Z)V

    .line 4680
    const/4 p1, 0x2

    new-array p1, p1, [I

    fill-array-data p1, :array_0

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->updatePreferenceTrampoline([I)V

    .line 4681
    return-void

    nop

    :array_0
    .array-data 4
        0x2
        0x5
    .end array-data
.end method

.method public onHDRSceneChanged(Z)V
    .locals 3

    .line 811
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isDetectedInHDR:Z

    if-eq v0, p1, :cond_7

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-eqz v0, :cond_0

    goto/16 :goto_1

    .line 815
    :cond_0
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentHdr()Lcom/android/camera/data/data/config/ComponentConfigHdr;

    move-result-object v0

    .line 816
    invoke-virtual {v0}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 817
    return-void

    .line 819
    :cond_1
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    .line 820
    const-string v1, "auto"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 821
    return-void

    .line 827
    :cond_2
    if-eqz p1, :cond_4

    .line 828
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    const/high16 v1, 0x3f800000    # 1.0f

    cmpl-float v0, v0, v1

    if-gtz v0, :cond_3

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_3

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 830
    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isNeedFlashOn()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 831
    :cond_3
    return-void

    .line 834
    :cond_4
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$5;

    invoke-direct {v1, p0, p1}, Lcom/android/camera/module/Camera2Module$5;-><init>(Lcom/android/camera/module/Camera2Module;Z)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 843
    if-eqz p1, :cond_5

    .line 844
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isNormal()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 845
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/android/camera/MutexModeManager;->setMutexMode(I)V

    goto :goto_0

    .line 847
    :cond_5
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isMorphoHdr()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 848
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->resetMutexMode()V

    .line 851
    :cond_6
    :goto_0
    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->isDetectedInHDR:Z

    .line 852
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onHDRSceneChanged: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 853
    return-void

    .line 812
    :cond_7
    :goto_1
    return-void
.end method

.method public onHanGestureSwitched(Z)V
    .locals 2

    .line 4694
    const/4 v0, 0x1

    if-eqz p1, :cond_0

    .line 4695
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object p1

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mBogusCameraId:I

    invoke-virtual {p1, v1, v0}, Lcom/android/zxing/PreviewDecodeManager;->init(II)V

    .line 4696
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/zxing/PreviewDecodeManager;->startDecode()V

    goto :goto_0

    .line 4698
    :cond_0
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object p1

    invoke-virtual {p1, v0}, Lcom/android/zxing/PreviewDecodeManager;->stopDecode(I)V

    .line 4700
    :goto_0
    return-void
.end method

.method public onHostStopAndNotifyActionStop()V
    .locals 3

    .line 2041
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onHostStopAndNotifyActionStop()V

    .line 2042
    nop

    .line 2043
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->isDisposed()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2044
    const/4 v0, 0x1

    .line 2045
    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v2}, Lio/reactivex/disposables/Disposable;->dispose()V

    goto :goto_0

    .line 2047
    :cond_0
    move v0, v1

    :goto_0
    if-nez v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mWaitingSuperNightResult:Z

    if-eqz v0, :cond_2

    .line 2048
    :cond_1
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mWaitingSuperNightResult:Z

    .line 2049
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xd4

    .line 2050
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$RecordState;

    .line 2051
    if-eqz v0, :cond_2

    .line 2052
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$RecordState;->onPostSavingFinish()V

    .line 2055
    :cond_2
    return-void
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 3

    .line 4784
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrameAvailable()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 4785
    return v1

    .line 4788
    :cond_0
    const/4 v0, 0x1

    sparse-switch p1, :sswitch_data_0

    goto :goto_3

    .line 4790
    :sswitch_0
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getRepeatCount()I

    move-result p1

    if-nez p1, :cond_1

    .line 4791
    invoke-virtual {p0, v0, v0}, Lcom/android/camera/module/Camera2Module;->onShutterButtonFocus(ZI)V

    .line 4793
    :cond_1
    return v0

    .line 4796
    :sswitch_1
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getRepeatCount()I

    move-result p1

    if-nez p1, :cond_3

    .line 4797
    invoke-static {p2}, Lcom/android/camera/Util;->isFingerPrintKeyEvent(Landroid/view/KeyEvent;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 4798
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFingerprintCaptureEnable()Z

    move-result p1

    if-eqz p1, :cond_3

    .line 4799
    const/16 p1, 0x1e

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    goto :goto_0

    .line 4802
    :cond_2
    const/16 p1, 0x28

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    .line 4805
    :cond_3
    :goto_0
    return v0

    .line 4810
    :sswitch_2
    const/16 v2, 0x18

    if-eq p1, v2, :cond_5

    const/16 v2, 0x58

    if-ne p1, v2, :cond_4

    goto :goto_1

    :cond_4
    goto :goto_2

    :cond_5
    :goto_1
    move v1, v0

    .line 4813
    :goto_2
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getRepeatCount()I

    move-result v2

    .line 4810
    invoke-virtual {p0, v1, v0, v2}, Lcom/android/camera/module/Camera2Module;->handleVolumeKeyEvent(ZZI)Z

    move-result v1

    if-eqz v1, :cond_6

    .line 4814
    return v0

    .line 4819
    :cond_6
    :goto_3
    invoke-super {p0, p1, p2}, Lcom/android/camera/module/BaseModule;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result p1

    return p1

    :sswitch_data_0
    .sparse-switch
        0x18 -> :sswitch_2
        0x19 -> :sswitch_2
        0x1b -> :sswitch_1
        0x42 -> :sswitch_1
        0x50 -> :sswitch_0
        0x57 -> :sswitch_2
        0x58 -> :sswitch_2
    .end sparse-switch
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 4

    .line 4824
    const/4 v0, 0x1

    sparse-switch p1, :sswitch_data_0

    goto :goto_2

    .line 4829
    :sswitch_0
    const/16 v1, 0x18

    const/4 v2, 0x0

    if-eq p1, v1, :cond_1

    const/16 v1, 0x58

    if-ne p1, v1, :cond_0

    goto :goto_0

    :cond_0
    move v1, v2

    goto :goto_1

    :cond_1
    :goto_0
    move v1, v0

    .line 4832
    :goto_1
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getRepeatCount()I

    move-result v3

    .line 4829
    invoke-virtual {p0, v1, v2, v3}, Lcom/android/camera/module/Camera2Module;->handleVolumeKeyEvent(ZZI)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 4833
    return v0

    .line 4839
    :sswitch_1
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xab

    .line 4840
    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$BackStack;

    .line 4841
    invoke-interface {v1}, Lcom/android/camera/protocol/ModeProtocol$BackStack;->handleBackStackFromKeyBack()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 4842
    return v0

    .line 4846
    :cond_2
    :goto_2
    invoke-super {p0, p1, p2}, Lcom/android/camera/module/BaseModule;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result p1

    return p1

    nop

    :sswitch_data_0
    .sparse-switch
        0x4 -> :sswitch_1
        0x18 -> :sswitch_0
        0x19 -> :sswitch_0
        0x57 -> :sswitch_0
        0x58 -> :sswitch_0
    .end sparse-switch
.end method

.method public onLongPress(II)V
    .locals 1

    .line 1601
    invoke-virtual {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->isInTapableRect(II)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1602
    return-void

    .line 1604
    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->onSingleTapUp(II)V

    .line 1607
    invoke-static {}, Lcom/android/camera/CameraSettings;->isAEAFLockSupport()Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBackCamera()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 1608
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->lockAEAF()V

    .line 1610
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isSupportFocusShoot()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->is3ALocked()Z

    move-result p1

    if-nez p1, :cond_2

    .line 1611
    const/16 p1, 0x50

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->setTriggerMode(I)V

    .line 1612
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getTriggerMode()I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    .line 1614
    :cond_2
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    const/4 p2, 0x0

    invoke-interface {p1, p2}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->performHapticFeedback(I)V

    .line 1615
    return-void
.end method

.method public onObjectStable()V
    .locals 0

    .line 1019
    return-void
.end method

.method public onOrientationChanged(II)V
    .locals 1

    .line 4530
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsGradienterOn:Z

    if-nez v0, :cond_0

    .line 4531
    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->setOrientation(II)V

    .line 4533
    :cond_0
    return-void
.end method

.method public onPause()V
    .locals 2

    .line 1949
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onPause()V

    .line 1951
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->stopLiveShot(Z)V

    .line 1957
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    if-eqz v0, :cond_0

    .line 1958
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->removeMessages()V

    .line 1961
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resetGradienter()V

    .line 1962
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->tryRemoveCountDownMessage()V

    .line 1963
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/SensorStateManager;->reset()V

    .line 1966
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resetScreenOn()V

    .line 1968
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->closeCamera()V

    .line 1969
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->releaseEffectProcessor()V

    .line 1971
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    new-instance v1, Lcom/android/camera/module/Camera2Module$12;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$12;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 1992
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v0, :cond_1

    .line 1993
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 1995
    :cond_1
    return-void
.end method

.method public onPictureTaken([B)V
    .locals 0

    .line 3022
    return-void
.end method

.method public onPictureTakenFinished(Z)V
    .locals 9

    .line 3027
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onPictureTakenFinished: succeed = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3028
    const/4 v0, 0x0

    const/4 v1, 0x1

    if-eqz p1, :cond_4

    .line 3030
    invoke-virtual {p0, v1, v0}, Lcom/android/camera/module/Camera2Module;->trackGeneralInfo(IZ)V

    .line 3031
    const/4 v3, 0x1

    const/4 v4, 0x0

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mLocation:Landroid/location/Location;

    if-eqz p1, :cond_0

    move v5, v1

    goto :goto_0

    :cond_0
    move v5, v0

    :goto_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getCurrentAiSceneName()Ljava/lang/String;

    move-result-object v6

    iget-boolean v7, p0, Lcom/android/camera/module/Camera2Module;->mEnteringMoonMode:Z

    iget-boolean v8, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    move-object v2, p0

    invoke-virtual/range {v2 .. v8}, Lcom/android/camera/module/Camera2Module;->trackPictureTaken(IZZLjava/lang/String;ZZ)V

    .line 3033
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 3036
    iget-wide v4, p0, Lcom/android/camera/module/Camera2Module;->mCaptureStartTime:J

    sub-long/2addr v2, v4

    .line 3037
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result p1

    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {v2, v3, p1, v4}, Lcom/android/camera/statistic/CameraStatUtil;->trackTakePictureCost(JZI)V

    .line 3039
    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v4, 0xab

    if-ne p1, v4, :cond_1

    .line 3040
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object p1

    invoke-virtual {p1}, Lcom/mi/config/a;->isSupportBokehAdjust()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 3041
    invoke-static {}, Lcom/android/camera/statistic/CameraStatUtil;->trackBokehTaken()V

    .line 3044
    :cond_1
    invoke-static {}, Lcom/android/camera/statistic/ScenarioTrackUtil;->trackCaptureTimeEnd()V

    .line 3046
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "mCaptureStartTime(from onShutterButtonClick start to jpegCallback finished) = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, "ms"

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {p1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3049
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-eqz p1, :cond_3

    .line 3050
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mQuickCapture:Z

    if-nez p1, :cond_2

    .line 3051
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 3052
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mKeepBitmapTexture:Z

    .line 3053
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->showPostCaptureAlert()V

    goto :goto_1

    .line 3056
    :cond_2
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->doAttach()V

    goto :goto_1

    .line 3059
    :cond_3
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mLongPressedAutoFocus:Z

    if-eqz p1, :cond_4

    .line 3060
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mLongPressedAutoFocus:Z

    .line 3061
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->cancelLongPressedAutoFocus()V

    .line 3067
    :cond_4
    :goto_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isKeptBitmapTexture()Z

    move-result p1

    if-nez p1, :cond_5

    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez p1, :cond_5

    .line 3068
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 3069
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->enableCameraControls(Z)V

    .line 3072
    :cond_5
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x32

    invoke-virtual {p1, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 3074
    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xad

    if-ne p1, v1, :cond_7

    .line 3075
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mWaitingSuperNightResult:Z

    .line 3076
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz p1, :cond_6

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {p1}, Lio/reactivex/disposables/Disposable;->isDisposed()Z

    move-result p1

    if-nez p1, :cond_6

    .line 3077
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mSuperNightDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {p1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 3079
    :cond_6
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v0, Lcom/android/camera/module/Camera2Module$17;

    invoke-direct {v0, p0}, Lcom/android/camera/module/Camera2Module$17;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {p1, v0}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 3091
    :cond_7
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->doLaterReleaseIfNeed()V

    .line 3092
    return-void
.end method

.method public onPictureTakenImageConsumed(Landroid/media/Image;)Z
    .locals 0

    .line 3016
    const/4 p1, 0x0

    return p1
.end method

.method public onPreviewLayoutChanged(Landroid/graphics/Rect;)V
    .locals 3

    .line 1844
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0, p1}, Lcom/android/camera/Camera;->onLayoutChange(Landroid/graphics/Rect;)V

    .line 1845
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 1846
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/CameraScreenNail;->getRenderWidth()I

    move-result v1

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 1847
    invoke-virtual {v2}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v2

    invoke-virtual {v2}, Lcom/android/camera/CameraScreenNail;->getRenderHeight()I

    move-result v2

    .line 1846
    invoke-virtual {v0, v1, v2}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setRenderSize(II)V

    .line 1848
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {p1}, Landroid/graphics/Rect;->width()I

    move-result v1

    invoke-virtual {p1}, Landroid/graphics/Rect;->height()I

    move-result p1

    invoke-virtual {v0, v1, p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setPreviewSize(II)V

    .line 1850
    :cond_0
    return-void
.end method

.method public onPreviewMetaDataUpdate(Landroid/hardware/camera2/CaptureResult;)V
    .locals 4

    .line 5129
    if-nez p1, :cond_0

    .line 5130
    return-void

    .line 5133
    :cond_0
    invoke-super {p0, p1}, Lcom/android/camera/module/BaseModule;->onPreviewMetaDataUpdate(Landroid/hardware/camera2/CaptureResult;)V

    .line 5136
    sget-object v0, Landroid/hardware/camera2/CaptureResult;->SENSOR_SENSITIVITY:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p1, v0}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 5137
    sget-object v1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "onPreviewMetaDataUpdate: ISO="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->c(Ljava/lang/String;Ljava/lang/String;)I

    .line 5138
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/16 v1, 0x320

    if-lt v0, v1, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mShouldDoMFNR:Z

    .line 5140
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;

    if-eqz v0, :cond_2

    .line 5141
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMetaDataFlowableEmitter:Lio/reactivex/FlowableEmitter;

    invoke-interface {v0, p1}, Lio/reactivex/FlowableEmitter;->onNext(Ljava/lang/Object;)V

    .line 5144
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneFlowableEmitter:Lio/reactivex/FlowableEmitter;

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_3

    .line 5145
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneFlowableEmitter:Lio/reactivex/FlowableEmitter;

    invoke-interface {v0, p1}, Lio/reactivex/FlowableEmitter;->onNext(Ljava/lang/Object;)V

    .line 5147
    :cond_3
    return-void
.end method

.method public onPreviewPixelsRead([BII)V
    .locals 5

    .line 2818
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->animateCapture()V

    .line 2819
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->playCameraSound(I)V

    .line 2821
    sget-object v1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {p2, p3, v1}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p2

    .line 2822
    invoke-static {p1}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object p1

    invoke-virtual {p2, p1}, Landroid/graphics/Bitmap;->copyPixelsFromBuffer(Ljava/nio/Buffer;)V

    .line 2824
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result p1

    const/4 p3, 0x1

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isFrontMirror()Z

    move-result p1

    if-nez p1, :cond_0

    .line 2825
    move p1, p3

    goto :goto_0

    .line 2824
    :cond_0
    nop

    .line 2825
    move p1, v0

    .line 2827
    :goto_0
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    .line 2828
    if-eqz p1, :cond_1

    .line 2829
    invoke-static {p2}, Lcom/android/camera/Util;->flipBitmap(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p2

    .line 2830
    nop

    .line 2833
    move p1, v0

    :cond_1
    invoke-static {p2}, Lcom/android/camera/Util;->getBitmapData(Landroid/graphics/Bitmap;)[B

    move-result-object v1

    goto :goto_1

    .line 2836
    :cond_2
    move-object v1, v2

    :goto_1
    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mShootOrientation:I

    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mDisplayRotation:I

    sub-int/2addr v3, v4

    .line 2837
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v4

    if-eqz v4, :cond_3

    .line 2838
    invoke-static {}, Lcom/mi/config/b;->hI()Z

    move-result v4

    if-eqz v4, :cond_3

    .line 2840
    rem-int/lit16 v4, v3, 0xb4

    if-nez v4, :cond_3

    .line 2841
    nop

    .line 2846
    move v3, v0

    :cond_3
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v4

    if-eqz v4, :cond_6

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDeviceAlive()Z

    move-result v4

    if-nez v4, :cond_4

    goto :goto_2

    .line 2849
    :cond_4
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v4}, Lcom/android/camera2/Camera2Proxy;->getShotSavePath()Ljava/lang/String;

    move-result-object v4

    .line 2850
    invoke-static {v2, p2, v3, p1}, Lcom/android/camera/Thumbnail;->createThumbnail(Landroid/net/Uri;Landroid/graphics/Bitmap;IZ)Lcom/android/camera/Thumbnail;

    move-result-object p1

    .line 2851
    invoke-virtual {p1}, Lcom/android/camera/Thumbnail;->startWaitingForUri()V

    .line 2852
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p2}, Lcom/android/camera/Camera;->getThumbnailUpdater()Lcom/android/camera/ThumbnailUpdater;

    move-result-object p2

    invoke-virtual {p2, p1, p3, p3}, Lcom/android/camera/ThumbnailUpdater;->setThumbnail(Lcom/android/camera/Thumbnail;ZZ)V

    .line 2854
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p2, p1}, Lcom/android/camera2/Camera2Proxy;->onPreviewThumbnailReceived(Lcom/android/camera/Thumbnail;)V

    .line 2857
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "onPreviewPixelsRead: "

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p3, "--"

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean p3, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2858
    if-eqz v1, :cond_5

    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mIsCurrentTaskIsParallel:Z

    if-eqz p1, :cond_5

    .line 2859
    new-instance p1, Lcom/xiaomi/camera/core/ParallelTaskData;

    .line 2860
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p2

    const/4 v2, -0x1

    invoke-direct {p1, p2, p3, v2, v4}, Lcom/xiaomi/camera/core/ParallelTaskData;-><init>(JILjava/lang/String;)V

    .line 2861
    invoke-virtual {p1, v1, v0}, Lcom/xiaomi/camera/core/ParallelTaskData;->fillJpegData([BI)V

    .line 2862
    iget-object p2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p2}, Lcom/android/camera/Camera;->getImageSaver()Lcom/android/camera/storage/ImageSaver;

    move-result-object p2

    invoke-virtual {p2, p1}, Lcom/android/camera/storage/ImageSaver;->onParallelProcessFinish(Lcom/xiaomi/camera/core/ParallelTaskData;)V

    .line 2864
    :cond_5
    return-void

    .line 2847
    :cond_6
    :goto_2
    return-void
.end method

.method public onPreviewSessionClosed(Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 1

    .line 2599
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "onPreviewSessionClosed: "

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2602
    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 2603
    return-void
.end method

.method public onPreviewSessionFailed(Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 1

    .line 2590
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isTextureExpired()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->retryOnceIfCameraError(Landroid/os/Handler;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 2591
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v0, "sessionFailed due to surfaceTexture expired, retry"

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2592
    return-void

    .line 2594
    :cond_0
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v0, 0x33

    invoke-virtual {p1, v0}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 2595
    return-void
.end method

.method public onPreviewSessionSuccess(Landroid/hardware/camera2/CameraCaptureSession;)V
    .locals 3

    .line 2546
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onPreviewSessionSuccess: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2547
    if-nez p1, :cond_0

    .line 2548
    return-void

    .line 2550
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result p1

    if-nez p1, :cond_1

    .line 2551
    return-void

    .line 2554
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isKeptBitmapTexture()Z

    move-result p1

    if-nez p1, :cond_2

    .line 2555
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v0, 0x9

    invoke-virtual {p1, v0}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 2558
    :cond_2
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    if-eqz p1, :cond_3

    .line 2559
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->configParallelSession()V

    .line 2562
    :cond_3
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->previewWhenSessionSuccess()V

    .line 2563
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getCameraIntentManager()Lcom/android/camera/CameraIntentManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/CameraIntentManager;->checkCallerLegality()Z

    move-result p1

    if-eqz p1, :cond_5

    .line 2565
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getCameraIntentManager()Lcom/android/camera/CameraIntentManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/CameraIntentManager;->isOpenOnly()Z

    move-result p1

    if-nez p1, :cond_4

    .line 2567
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v0, "android.intent.extra.CAMERA_OPEN_ONLY"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->removeExtra(Ljava/lang/String;)V

    .line 2569
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v0, 0x34

    const-wide/16 v1, 0x3e8

    invoke-virtual {p1, v0, v1, v2}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    goto :goto_0

    .line 2571
    :cond_4
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getIntent()Landroid/content/Intent;

    move-result-object p1

    const-string v0, "android.intent.extra.TIMER_DURATION_SECONDS"

    invoke-virtual {p1, v0}, Landroid/content/Intent;->removeExtra(Ljava/lang/String;)V

    .line 2574
    :cond_5
    :goto_0
    return-void
.end method

.method public onPreviewSizeChanged(II)V
    .locals 1

    .line 1854
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    if-eqz v0, :cond_0

    .line 1855
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0, p1, p2}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setPreviewSize(II)V

    .line 1857
    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 2

    .line 1903
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onResume()V

    .line 1904
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x32

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 1905
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isSelectingCapturedResult()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1906
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mKeepBitmapTexture:Z

    .line 1907
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->releaseBitmapIfNeeded()V

    .line 1910
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->keepScreenOnAwhile()V

    .line 1912
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    new-instance v1, Lcom/android/camera/module/Camera2Module$11;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$11;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 1928
    return-void
.end method

.method public onReviewCancelClicked()V
    .locals 3

    .line 1816
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mKeepBitmapTexture:Z

    .line 1817
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isSelectingCapturedResult()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 1818
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->releaseBitmapIfNeeded()V

    .line 1819
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->hidePostCaptureAlert()V

    goto :goto_0

    .line 1823
    :cond_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    new-instance v2, Landroid/content/Intent;

    invoke-direct {v2}, Landroid/content/Intent;-><init>()V

    invoke-virtual {v1, v0, v2}, Lcom/android/camera/Camera;->setResult(ILandroid/content/Intent;)V

    .line 1824
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->finish()V

    .line 1826
    :goto_0
    return-void
.end method

.method public onReviewDoneClicked()V
    .locals 0

    .line 1811
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->doAttach()V

    .line 1812
    return-void
.end method

.method public onScaleEnd()V
    .locals 4

    .line 4898
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onScaleEnd()V

    .line 4900
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    .line 4901
    invoke-static {}, Lcom/android/camera/CameraSettings;->isUltraPixelPhotographyOn()Z

    move-result v1

    .line 4902
    if-nez v0, :cond_0

    if-eqz v1, :cond_1

    .line 4903
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xaf

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;

    .line 4904
    if-eqz v0, :cond_1

    .line 4905
    const/16 v1, 0xf

    const v2, 0x7f09027f

    const/4 v3, 0x1

    invoke-interface {v0, v1, v2, v3}, Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;->showTips(III)V

    .line 4908
    :cond_1
    return-void
.end method

.method public onShutterButtonClick(I)V
    .locals 2

    .line 1078
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->getCountDownTimes(I)I

    move-result v0

    .line 1079
    if-lez v0, :cond_0

    .line 1080
    invoke-direct {p0, v0, p1}, Lcom/android/camera/module/Camera2Module;->startCount(II)V

    .line 1081
    return-void

    .line 1084
    :cond_0
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->checkShutterCondition()Z

    move-result v0

    if-nez v0, :cond_1

    .line 1085
    return-void

    .line 1088
    :cond_1
    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->setTriggerMode(I)V

    .line 1089
    sget-object p1, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onShutterButtonClick "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1095
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mNeedAutoFocus:Z

    const/4 v1, 0x2

    invoke-virtual {p1, v0, v1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->prepareCapture(ZI)V

    .line 1096
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->doSnap()V

    .line 1097
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->isFocusingSnapOnFinish()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 1098
    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->enableCameraControls(Z)V

    .line 1100
    :cond_2
    return-void
.end method

.method public onShutterButtonFocus(ZI)V
    .locals 0

    .line 1597
    return-void
.end method

.method public onShutterButtonLongClick()Z
    .locals 4

    .line 1619
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 1620
    return v1

    .line 1623
    :cond_0
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-eqz v0, :cond_1

    .line 1624
    return v1

    .line 1627
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->isBurstShootingEnable()Z

    move-result v0

    const/4 v2, 0x1

    if-eqz v0, :cond_4

    .line 1628
    invoke-static {}, Lcom/android/camera/module/ModuleManager;->isCameraModule()Z

    move-result v0

    if-eqz v0, :cond_4

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-nez v0, :cond_4

    .line 1631
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGroupShotOn()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1632
    invoke-static {}, Lcom/android/camera/CameraSettings;->isGradienterOn()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1633
    invoke-static {}, Lcom/android/camera/CameraSettings;->isTiltShiftOn()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1634
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    const-string v3, "pref_camera_hand_night_key"

    invoke-virtual {v0, v3}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_4

    .line 1635
    invoke-static {}, Lcom/android/camera/CameraSettings;->isStereoModeOn()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1636
    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1637
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isBackCamera()Z

    move-result v0

    if-eqz v0, :cond_4

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez v0, :cond_4

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v3, 0x18

    .line 1639
    invoke-virtual {v0, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->hasMessages(I)Z

    move-result v0

    if-nez v0, :cond_4

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPendingMultiCapture:Z

    if-nez v0, :cond_4

    .line 1641
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isUltraWideBackCamera()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1642
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-nez v0, :cond_4

    .line 1645
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xab

    .line 1646
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BackStack;

    .line 1647
    if-eqz v0, :cond_2

    .line 1648
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$BackStack;->handleBackStackFromShutter()V

    .line 1651
    :cond_2
    invoke-static {}, Lcom/mi/config/b;->gS()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 1652
    iput-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mUpdateImageTitle:Z

    .line 1655
    :cond_3
    iput-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mPendingMultiCapture:Z

    .line 1656
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0, v2}, Lcom/android/camera/module/loader/camera2/FocusManager2;->doMultiSnap(Z)V

    .line 1658
    return v2

    .line 1662
    :cond_4
    iput-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mLongPressedAutoFocus:Z

    .line 1663
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setFocusViewType(Z)V

    .line 1664
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->requestAutoFocus()V

    .line 1665
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getScreenHint()Lcom/android/camera/ui/ScreenHint;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/ui/ScreenHint;->updateHint()V

    .line 1666
    return v1
.end method

.method public onShutterButtonLongClickCancel(Z)V
    .locals 5

    .line 1672
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "onShutterButtonLongClickCancel: start"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1678
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPendingMultiCapture:Z

    .line 1680
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-eqz v1, :cond_0

    .line 1681
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v2, 0x31

    const-wide/16 v3, 0x7d0

    invoke-virtual {v1, v2, v3, v4}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    .line 1684
    :cond_0
    const/4 v1, 0x1

    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStopRequest:Z

    .line 1686
    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mLongPressedAutoFocus:Z

    if-eqz v1, :cond_2

    .line 1687
    if-eqz p1, :cond_1

    .line 1688
    const/16 p1, 0xa

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    goto :goto_0

    .line 1690
    :cond_1
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mLongPressedAutoFocus:Z

    .line 1691
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-virtual {p1}, Lcom/android/camera/module/loader/camera2/FocusManager2;->cancelLongPressedAutoFocus()V

    .line 1694
    :cond_2
    :goto_0
    return-void
.end method

.method public onSingleTapUp(II)V
    .locals 3

    .line 4590
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "onSingleTapUp mPaused: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v2, "; mCamera2Device: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, "; isInCountDown: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 4591
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isInCountDown()Z

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v2, "; getCameraState: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 4592
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "; mMultiSnapStatus: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v2, "; Camera2Module: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 4590
    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 4596
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-nez v0, :cond_8

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_8

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 4598
    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isSessionReady()Z

    move-result v0

    if-eqz v0, :cond_8

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 4599
    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->isPreviewReady()Z

    move-result v0

    if-eqz v0, :cond_8

    .line 4600
    invoke-virtual {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->isInTapableRect(II)Z

    move-result v0

    if-eqz v0, :cond_8

    .line 4601
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_8

    .line 4602
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x4

    if-eq v0, v1, :cond_8

    .line 4603
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-eqz v0, :cond_8

    .line 4604
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isInCountDown()Z

    move-result v0

    if-nez v0, :cond_8

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez v0, :cond_8

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    if-eqz v0, :cond_0

    goto/16 :goto_0

    .line 4611
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrameAvailable()Z

    move-result v0

    if-nez v0, :cond_1

    .line 4612
    return-void

    .line 4615
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isScreenSlideOff()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 4616
    return-void

    .line 4620
    :cond_2
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xab

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BackStack;

    .line 4621
    invoke-interface {v0, p1, p2}, Lcom/android/camera/protocol/ModeProtocol$BackStack;->handleBackStackFromTapDown(II)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 4622
    return-void

    .line 4625
    :cond_3
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->tryRemoveCountDownMessage()V

    .line 4627
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusAreaSupported:Z

    if-nez v0, :cond_4

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMeteringAreaSupported:Z

    if-nez v0, :cond_4

    .line 4628
    return-void

    .line 4632
    :cond_4
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v0}, Lcom/android/camera/MutexModeManager;->isUbiFocus()Z

    move-result v0

    if-eqz v0, :cond_5

    .line 4633
    return-void

    .line 4635
    :cond_5
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mObjectTrackingStarted:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_6

    .line 4636
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->stopObjectTracking(Z)V

    .line 4638
    :cond_6
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setFocusViewType(Z)V

    .line 4640
    new-instance v0, Landroid/graphics/Point;

    invoke-direct {v0, p1, p2}, Landroid/graphics/Point;-><init>(II)V

    .line 4641
    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->mapTapCoordinate(Ljava/lang/Object;)V

    .line 4643
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->unlockAEAF()V

    .line 4644
    const/4 p1, 0x2

    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 4645
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    iget p2, v0, Landroid/graphics/Point;->x:I

    iget v0, v0, Landroid/graphics/Point;->y:I

    invoke-virtual {p1, p2, v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->onSingleTapUp(II)V

    .line 4648
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusAreaSupported:Z

    if-nez p1, :cond_7

    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mMeteringAreaSupported:Z

    if-eqz p1, :cond_7

    .line 4649
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getSensorStateManager()Lcom/android/camera/SensorStateManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/SensorStateManager;->reset()V

    .line 4651
    :cond_7
    return-void

    .line 4607
    :cond_8
    :goto_0
    return-void
.end method

.method public onStop()V
    .locals 2

    .line 1999
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onStop()V

    .line 2000
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v0, :cond_0

    .line 2001
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 2003
    :cond_0
    return-void
.end method

.method public onSurfaceTextureReleased()V
    .locals 2

    .line 2889
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "onSurfaceTextureReleased: no further preview frame will be available"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2895
    return-void
.end method

.method public onSurfaceTextureUpdated(Lcom/android/camera/effect/draw_mode/DrawExtTexAttribute;)V
    .locals 1

    .line 2877
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    if-eqz v0, :cond_0

    .line 2878
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    invoke-virtual {v0, p1}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->onSurfaceTextureUpdated(Lcom/android/camera/effect/draw_mode/DrawExtTexAttribute;)V

    .line 2881
    :cond_0
    return-void
.end method

.method public onThumbnailClicked(Landroid/view/View;)V
    .locals 0

    .line 1721
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 1722
    return-void

    .line 1724
    :cond_0
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->getThumbnailUpdater()Lcom/android/camera/ThumbnailUpdater;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/ThumbnailUpdater;->getThumbnail()Lcom/android/camera/Thumbnail;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 1725
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->gotoGallery()V

    .line 1727
    :cond_1
    return-void
.end method

.method public onTiltShiftSwitched(Z)V
    .locals 1

    .line 4684
    if-eqz p1, :cond_0

    .line 4685
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->resetEvValue()V

    .line 4687
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->initEffectCropView()V

    .line 4688
    const/4 v0, 0x2

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->updatePreferenceTrampoline([I)V

    .line 4689
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->updateEffectViewVisible()V

    .line 4690
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    xor-int/lit8 p1, p1, 0x1

    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setEvAdjustable(Z)V

    .line 4691
    return-void

    :array_0
    .array-data 4
        0x2
        0x5
    .end array-data
.end method

.method public onUltraWideChanged(Z)V
    .locals 2

    .line 5834
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$33;

    invoke-direct {v1, p0, p1}, Lcom/android/camera/module/Camera2Module$33;-><init>(Lcom/android/camera/module/Camera2Module;Z)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 5868
    return-void
.end method

.method public onUserInteraction()V
    .locals 1

    .line 4935
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->onUserInteraction()V

    .line 4936
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_0

    .line 4937
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->keepScreenOnAwhile()V

    .line 4939
    :cond_0
    return-void
.end method

.method public onWaitingFocusFinished()Z
    .locals 2

    .line 429
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 430
    return v1

    .line 432
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_1

    .line 433
    return v1

    .line 436
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getTriggerMode()I

    move-result v0

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->startNormalCapture(I)V

    .line 437
    const/4 v0, 0x1

    return v0
.end method

.method protected openSettingActivity()V
    .locals 4

    .line 3234
    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    .line 3235
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const-class v2, Lcom/android/camera/CameraPreferenceActivity;

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->setClass(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;

    .line 3236
    const-string v1, "from_where"

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    .line 3237
    const-string v1, "IsCaptureIntent"

    iget-boolean v2, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    .line 3239
    const-string v1, ":miui:starting_window_label"

    .line 3240
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f090045

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 3239
    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 3242
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1}, Lcom/android/camera/Camera;->startFromKeyguard()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 3243
    const-string v1, "StartActivityWhenLocked"

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    .line 3245
    :cond_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v1, v0}, Lcom/android/camera/Camera;->startActivity(Landroid/content/Intent;)V

    .line 3246
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->setJumpFlag(I)V

    .line 3248
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-static {v0}, Lcom/android/camera/statistic/CameraStatUtil;->trackGotoSettings(I)V

    .line 3249
    return-void
.end method

.method public pausePreview()V
    .locals 2

    .line 2661
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "pausePreview"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 2662
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->pausePreview()V

    .line 2663
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 2664
    return-void
.end method

.method protected performVolumeKeyClicked(Ljava/lang/String;IZ)V
    .locals 1

    .line 4851
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-nez v0, :cond_6

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 4854
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_1

    .line 4855
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->restoreBottom()V

    .line 4857
    :cond_1
    const/4 v0, 0x1

    if-nez p2, :cond_4

    .line 4858
    if-eqz p3, :cond_3

    .line 4859
    invoke-virtual {p0, v0, v0}, Lcom/android/camera/module/Camera2Module;->onShutterButtonFocus(ZI)V

    .line 4860
    const p2, 0x7f09011d

    invoke-virtual {p0, p2}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    const/16 p2, 0x14

    if-eqz p1, :cond_2

    .line 4861
    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lcom/android/camera/module/Camera2Module;->startCount(II)V

    goto :goto_0

    .line 4863
    :cond_2
    invoke-virtual {p0, p2}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    goto :goto_0

    .line 4867
    :cond_3
    const/4 p1, 0x0

    invoke-virtual {p0, p1, p1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonFocus(ZI)V

    .line 4868
    iget-boolean p2, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    if-eqz p2, :cond_5

    .line 4869
    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    .line 4870
    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->onShutterButtonLongClickCancel(Z)V

    goto :goto_0

    .line 4874
    :cond_4
    if-eqz p3, :cond_5

    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    if-nez p1, :cond_5

    .line 4875
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mUpdateImageTitle:Z

    .line 4876
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->onShutterButtonLongClick()Z

    move-result p1

    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    .line 4877
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    if-nez p1, :cond_5

    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mLongPressedAutoFocus:Z

    if-eqz p1, :cond_5

    .line 4878
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mVolumeLongPress:Z

    .line 4882
    :cond_5
    :goto_0
    return-void

    .line 4852
    :cond_6
    :goto_1
    return-void
.end method

.method public playFocusSound(I)V
    .locals 0

    .line 1002
    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->playCameraSound(I)V

    .line 1003
    return-void
.end method

.method public registerProtocol()V
    .locals 5

    .line 332
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->registerProtocol()V

    .line 333
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa1

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 334
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa9

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 335
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa5

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 336
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc1

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 337
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc3

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 338
    invoke-static {}, Lcom/mi/config/b;->hM()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 339
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xb9

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 341
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc7

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 342
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImplFactory()Lcom/android/camera/module/impl/ImplFactory;

    move-result-object v0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v1

    const/4 v2, 0x2

    new-array v2, v2, [I

    fill-array-data v2, :array_0

    invoke-virtual {v0, v1, v2}, Lcom/android/camera/module/impl/ImplFactory;->initAdditional(Lcom/android/camera/ActivityBase;[I)V

    .line 345
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getModuleIndex()I

    move-result v0

    const/16 v1, 0xad

    if-ne v0, v1, :cond_1

    .line 346
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImplFactory()Lcom/android/camera/module/impl/ImplFactory;

    move-result-object v0

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v1

    const/4 v2, 0x1

    new-array v2, v2, [I

    const/4 v3, 0x0

    const/16 v4, 0xd4

    aput v4, v2, v3

    invoke-virtual {v0, v1, v2}, Lcom/android/camera/module/impl/ImplFactory;->initAdditional(Lcom/android/camera/ActivityBase;[I)V

    .line 349
    :cond_1
    return-void

    nop

    :array_0
    .array-data 4
        0xa4
        0xae
    .end array-data
.end method

.method protected resetMetaDataManager()V
    .locals 0

    .line 3224
    invoke-static {}, Lcom/android/camera/CameraSettings;->isSupportedMetadata()Z

    .line 3227
    return-void
.end method

.method public resumePreview()V
    .locals 2

    .line 2668
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "resumePreview"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 2669
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->previewWhenSessionSuccess()V

    .line 2670
    return-void
.end method

.method public scanQRCodeEnabled()Z
    .locals 2

    .line 375
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-static {v0}, Lcom/android/camera/CameraSettings;->isScanQRCode(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa3

    if-ne v0, v1, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-nez v0, :cond_1

    .line 378
    invoke-static {}, Lcom/android/camera/CameraSettings;->isBackCamera()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez v0, :cond_1

    .line 380
    invoke-static {}, Lcom/android/camera/CameraSettings;->isStereoModeOn()Z

    move-result v0

    if-nez v0, :cond_1

    .line 381
    invoke-static {}, Lcom/android/camera/CameraSettings;->isPortraitModeBackOn()Z

    move-result v0

    if-nez v0, :cond_1

    .line 383
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->gb()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v0

    if-nez v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 375
    :goto_0
    return v0
.end method

.method protected sendOpenFailMessage()V
    .locals 2

    .line 5122
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0xa

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessage(I)Z

    .line 5123
    return-void
.end method

.method public setFrameAvailable(Z)V
    .locals 2

    .line 1932
    invoke-super {p0, p1}, Lcom/android/camera/module/BaseModule;->setFrameAvailable(Z)V

    .line 1934
    if-eqz p1, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->isCameraSoundOpen()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1935
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->loadCameraSound(I)V

    .line 1936
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->loadCameraSound(I)V

    .line 1937
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->loadCameraSound(I)V

    .line 1938
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->loadCameraSound(I)V

    .line 1939
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    const/4 v1, 0x7

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->loadCameraSound(I)V

    .line 1942
    :cond_0
    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz p1, :cond_1

    .line 1943
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->releaseFakeSurfaceIfNeed()V

    .line 1945
    :cond_1
    return-void
.end method

.method public shouldReleaseLater()Z
    .locals 3

    .line 2026
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsImageCaptureIntent:Z

    if-nez v0, :cond_2

    .line 2027
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-ne v0, v1, :cond_2

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mMultiSnapStatus:Z

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x30

    .line 2030
    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->hasMessages(I)Z

    move-result v0

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x31

    .line 2031
    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->hasMessages(I)Z

    move-result v0

    if-nez v0, :cond_2

    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    .line 2033
    invoke-virtual {v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->isFocusing()Z

    move-result v0

    if-nez v0, :cond_2

    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa7

    if-ne v0, v1, :cond_1

    const-string v0, "pref_qc_camera_exposuretime_key"

    .line 2036
    const v1, 0x7f090136

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 2035
    invoke-direct {p0, v0, v2}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 2036
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    :cond_1
    const/4 v0, 0x1

    goto :goto_0

    :cond_2
    const/4 v0, 0x0

    .line 2026
    :goto_0
    return v0
.end method

.method public showQRCodeResult()V
    .locals 4

    .line 5101
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-eqz v0, :cond_0

    .line 5102
    return-void

    .line 5104
    :cond_0
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/zxing/PreviewDecodeManager;->getScanResult()Ljava/lang/String;

    move-result-object v0

    .line 5105
    if-eqz v0, :cond_2

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    .line 5109
    :cond_1
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 5110
    invoke-virtual {v1}, Lcom/android/camera/ActivityBase;->dismissKeyguard()V

    .line 5111
    new-instance v2, Landroid/content/Intent;

    const-string v3, "com.xiaomi.scanner.receiver.senderbarcodescanner"

    invoke-direct {v2, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 5112
    const/16 v3, 0x20

    invoke-virtual {v2, v3}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 5113
    const-string v3, "com.xiaomi.scanner"

    invoke-virtual {v2, v3}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 5114
    const-string v3, "result"

    invoke-virtual {v2, v3, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 5115
    invoke-virtual {v1, v2}, Lcom/android/camera/ActivityBase;->sendBroadcast(Landroid/content/Intent;)V

    .line 5116
    const/4 v0, 0x3

    invoke-virtual {v1, v0}, Lcom/android/camera/ActivityBase;->setJumpFlag(I)V

    .line 5117
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/zxing/PreviewDecodeManager;->resetScanResult()V

    .line 5118
    return-void

    .line 5106
    :cond_2
    :goto_0
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v1, "showQRCodeResult: get a null result!"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 5107
    return-void
.end method

.method public startAiLens()V
    .locals 4

    .line 5065
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$24;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$24;-><init>(Lcom/android/camera/module/Camera2Module;)V

    const-wide/16 v2, 0x12c

    invoke-virtual {v0, v1, v2, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 5071
    return-void
.end method

.method public startFaceDetection()V
    .locals 2

    .line 880
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    .line 881
    invoke-virtual {v0}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 884
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mMaxFaceCount:I

    if-lez v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_1

    .line 885
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    .line 886
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setActiveIndicator(I)V

    .line 888
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1}, Lcom/android/camera2/Camera2Proxy;->startFaceDetection()V

    .line 889
    invoke-virtual {p0, v0, v0}, Lcom/android/camera/module/Camera2Module;->updateFaceView(ZZ)V

    .line 891
    :cond_1
    return-void

    .line 882
    :cond_2
    :goto_0
    return-void
.end method

.method public startFocus()V
    .locals 3

    .line 388
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDeviceAlive()Z

    move-result v0

    if-nez v0, :cond_0

    .line 389
    return-void

    .line 391
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrameAvailable()Z

    move-result v0

    if-nez v0, :cond_1

    .line 392
    return-void

    .line 394
    :cond_1
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFocusAreaSupported:Z

    if-eqz v0, :cond_2

    .line 395
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/4 v1, 0x1

    invoke-static {v1}, Lcom/android/camera/module/loader/camera2/FocusTask;->create(I)Lcom/android/camera/module/loader/camera2/FocusTask;

    move-result-object v1

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1, v2}, Lcom/android/camera2/Camera2Proxy;->startFocus(Lcom/android/camera/module/loader/camera2/FocusTask;I)V

    goto :goto_0

    .line 397
    :cond_2
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->resumePreview()V

    .line 399
    :goto_0
    return-void
.end method

.method public startLiveShot()V
    .locals 6

    .line 621
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorderStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 622
    :try_start_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    if-nez v1, :cond_0

    .line 623
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/Camera;->getGLView()Lcom/android/camera/ui/V6CameraGLSurfaceView;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/ui/V6CameraGLSurfaceView;->getEGLContext14()Landroid/opengl/EGLContext;

    move-result-object v1

    .line 624
    new-instance v2, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    iget-object v3, p0, Lcom/android/camera/module/Camera2Module;->mVideoSize:Lcom/android/camera/CameraSize;

    iget v3, v3, Lcom/android/camera/CameraSize;->width:I

    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mVideoSize:Lcom/android/camera/CameraSize;

    iget v4, v4, Lcom/android/camera/CameraSize;->height:I

    iget-boolean v5, p0, Lcom/android/camera/module/Camera2Module;->mIsMicrophoneEnabled:Z

    invoke-direct {v2, v3, v4, v1, v5}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;-><init>(IILandroid/opengl/EGLContext;Z)V

    iput-object v2, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    .line 628
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    const/high16 v2, 0x41700000    # 15.0f

    invoke-virtual {v1, v2}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->setFpsReduction(F)V

    .line 630
    :cond_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    iget v2, p0, Lcom/android/camera/module/Camera2Module;->mOrientationCompensation:I

    invoke-virtual {v1, v2}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->setOrientationHint(I)V

    .line 631
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    invoke-virtual {v1}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->start()V

    .line 632
    monitor-exit v0

    .line 633
    return-void

    .line 632
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public startObjectTracking()V
    .locals 0

    .line 1014
    return-void
.end method

.method public startPreview()V
    .locals 14

    .line 2368
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_9

    .line 2369
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p0}, Lcom/android/camera2/Camera2Proxy;->setFocusCallback(Lcom/android/camera2/Camera2Proxy$FocusCallback;)V

    .line 2370
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p0}, Lcom/android/camera2/Camera2Proxy;->setMetaDataCallback(Lcom/android/camera2/Camera2Proxy$CameraMetaDataCallback;)V

    .line 2371
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p0}, Lcom/android/camera2/Camera2Proxy;->setScreenLightCallback(Lcom/android/camera2/Camera2Proxy$ScreenLightCallback;)V

    .line 2372
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mErrorCallback:Lcom/android/camera/CameraErrorCallbackImpl;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setErrorCallback(Lcom/android/camera2/Camera2Proxy$CameraErrorCallback;)V

    .line 2373
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "startPreview: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2374
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mPictureSize:Lcom/android/camera/CameraSize;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setPictureSize(Lcom/android/camera/CameraSize;)V

    .line 2375
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    if-eqz v0, :cond_0

    .line 2376
    const/16 v0, 0x23

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->getSubPictureSize(IZ)Lcom/android/camera/CameraSize;

    move-result-object v0

    .line 2377
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v1, v0}, Lcom/android/camera2/Camera2Proxy;->setSubPictureSize(Lcom/android/camera/CameraSize;)V

    .line 2379
    :cond_0
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_1

    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isEnableQcfa()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2380
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v3, "[QCFA] startPreview: set qcfa enable"

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2381
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v2}, Lcom/android/camera2/Camera2Proxy;->setQcfaEnable(Z)V

    goto :goto_0

    .line 2383
    :cond_1
    sget-object v0, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    const-string v3, "[QCFA] startPreview: set qcfa disable"

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2384
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setQcfaEnable(Z)V

    .line 2387
    :goto_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getSurfaceCreatedTimestamp()J

    move-result-wide v3

    iput-wide v3, p0, Lcom/android/camera/module/Camera2Module;->mSurfaceCreatedTimestamp:J

    .line 2389
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->scanQRCodeEnabled()Z

    move-result v0

    .line 2390
    if-eqz v0, :cond_2

    .line 2391
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v3

    iget v4, p0, Lcom/android/camera/module/Camera2Module;->mBogusCameraId:I

    invoke-virtual {v3, v4, v1}, Lcom/android/zxing/PreviewDecodeManager;->init(II)V

    .line 2393
    :cond_2
    invoke-static {}, Lcom/mi/config/b;->fT()Z

    move-result v3

    if-eqz v3, :cond_4

    .line 2394
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v3

    if-eqz v3, :cond_4

    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v4, 0xa3

    if-eq v3, v4, :cond_3

    iget v3, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v4, 0xab

    if-ne v3, v4, :cond_4

    .line 2396
    :cond_3
    move v3, v2

    goto :goto_1

    .line 2394
    :cond_4
    nop

    .line 2396
    move v3, v1

    :goto_1
    if-eqz v3, :cond_5

    .line 2397
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v4

    iget v5, p0, Lcom/android/camera/module/Camera2Module;->mBogusCameraId:I

    invoke-virtual {v4, v5, v2}, Lcom/android/zxing/PreviewDecodeManager;->init(II)V

    .line 2399
    :cond_5
    if-nez v0, :cond_7

    if-eqz v3, :cond_6

    goto :goto_2

    .line 2400
    :cond_6
    move v8, v1

    goto :goto_3

    .line 2399
    :cond_7
    :goto_2
    nop

    .line 2400
    move v8, v2

    :goto_3
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getSurfaceTexture()Landroid/graphics/SurfaceTexture;

    move-result-object v0

    .line 2401
    if-eqz v0, :cond_8

    new-instance v1, Landroid/view/Surface;

    invoke-direct {v1, v0}, Landroid/view/Surface;-><init>(Landroid/graphics/SurfaceTexture;)V

    .line 2402
    move-object v7, v1

    goto :goto_4

    .line 2401
    :cond_8
    const/4 v0, 0x0

    .line 2402
    move-object v7, v0

    :goto_4
    iget-object v6, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 2404
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->isNeedRawStream()Z

    move-result v9

    .line 2405
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getOperatingMode()I

    move-result v10

    iget-boolean v11, p0, Lcom/android/camera/module/Camera2Module;->mEnableParallelSession:Z

    .line 2408
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/zxing/PreviewDecodeManager;->getHandler()Landroid/os/Handler;

    move-result-object v13

    .line 2402
    move-object v12, p0

    invoke-virtual/range {v6 .. v13}, Lcom/android/camera2/Camera2Proxy;->startPreviewSession(Landroid/view/Surface;ZZIZLcom/android/camera2/Camera2Proxy$CameraPreviewCallback;Landroid/os/Handler;)V

    .line 2410
    :cond_9
    return-void
.end method

.method public startScreenLight(II)V
    .locals 2

    .line 5699
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mPaused:Z

    if-eqz v0, :cond_0

    .line 5700
    return-void

    .line 5702
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$31;

    invoke-direct {v1, p0, p2, p1}, Lcom/android/camera/module/Camera2Module$31;-><init>(Lcom/android/camera/module/Camera2Module;II)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 5716
    return-void
.end method

.method public stopFaceDetection(Z)V
    .locals 3

    .line 895
    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionEnabled:Z

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    if-nez v0, :cond_0

    goto :goto_0

    .line 899
    :cond_0
    invoke-static {}, Lcom/mi/config/b;->isMTKPlatform()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 900
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    const/4 v1, 0x3

    if-eq v0, v1, :cond_2

    .line 901
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getCameraState()I

    move-result v0

    if-eqz v0, :cond_2

    .line 902
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0}, Lcom/android/camera2/Camera2Proxy;->stopFaceDetection()V

    .line 904
    :cond_2
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mFaceDetectionStarted:Z

    .line 905
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    const/4 v2, 0x2

    invoke-interface {v1, v2}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setActiveIndicator(I)V

    .line 906
    invoke-virtual {p0, v0, p1}, Lcom/android/camera/module/Camera2Module;->updateFaceView(ZZ)V

    .line 907
    return-void

    .line 896
    :cond_3
    :goto_0
    return-void
.end method

.method public stopLiveShot(Z)V
    .locals 2

    .line 636
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorderStateLock:Ljava/lang/Object;

    monitor-enter v0

    .line 637
    :try_start_0
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    if-eqz v1, :cond_0

    .line 638
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    invoke-virtual {v1}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->stop()V

    .line 639
    if-eqz p1, :cond_0

    .line 640
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    invoke-virtual {p1}, Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;->release()V

    .line 641
    const/4 p1, 0x0

    iput-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCircularMediaRecorder:Lcom/xiaomi/camera/liveshot/CircularMediaRecorder;

    .line 644
    :cond_0
    monitor-exit v0

    .line 645
    return-void

    .line 644
    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public stopObjectTracking(Z)V
    .locals 0

    .line 993
    return-void
.end method

.method public stopScreenLight()V
    .locals 2

    .line 5719
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$32;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$32;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 5733
    return-void
.end method

.method protected trackModeCustomInfo(I)V
    .locals 3

    .line 5737
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa7

    if-ne v0, v1, :cond_0

    .line 5738
    invoke-direct {p0, p1}, Lcom/android/camera/module/Camera2Module;->trackManualInfo(I)V

    goto :goto_0

    .line 5739
    :cond_0
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa3

    if-ne v0, v1, :cond_2

    .line 5740
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->isFaceBeautyOn(Lcom/android/camera/fragment/beauty/BeautyValues;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 5741
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isFrontCamera()Z

    move-result v0

    new-instance v1, Lcom/android/camera/fragment/beauty/BeautyValues;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mBeautyValues:Lcom/android/camera/fragment/beauty/BeautyValues;

    invoke-direct {v1, v2}, Lcom/android/camera/fragment/beauty/BeautyValues;-><init>(Lcom/android/camera/fragment/beauty/BeautyValues;)V

    invoke-direct {p0, p1, v0, v1}, Lcom/android/camera/module/Camera2Module;->trackBeautyInfo(IZLcom/android/camera/fragment/beauty/BeautyValues;)V

    .line 5743
    :cond_1
    invoke-static {}, Lcom/android/camera/statistic/CameraStatUtil;->trackUltraWidePictureTaken()V

    goto :goto_0

    .line 5744
    :cond_2
    iget p1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v0, 0xa5

    if-ne p1, v0, :cond_3

    .line 5745
    invoke-static {}, Lcom/android/camera/statistic/CameraStatUtil;->trackUltraWidePictureTaken()V

    .line 5747
    :cond_3
    :goto_0
    return-void
.end method

.method public tryRemoveCountDownMessage()V
    .locals 2

    .line 1790
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->isDisposed()Z

    move-result v0

    if-nez v0, :cond_0

    .line 1791
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 1792
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCountdownDisposable:Lio/reactivex/disposables/Disposable;

    .line 1794
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v1, Lcom/android/camera/module/Camera2Module$10;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$10;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 1802
    :cond_0
    return-void
.end method

.method public unRegisterProtocol()V
    .locals 2

    .line 353
    invoke-super {p0}, Lcom/android/camera/module/BaseModule;->unRegisterProtocol()V

    .line 354
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa1

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 355
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa9

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 356
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa5

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 357
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc1

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 358
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc3

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 359
    invoke-static {}, Lcom/mi/config/b;->hM()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 360
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xb9

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 362
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc7

    invoke-virtual {v0, v1, p0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 363
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getImplFactory()Lcom/android/camera/module/impl/ImplFactory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/module/impl/ImplFactory;->detachAdditional()V

    .line 364
    return-void
.end method

.method public updateBacklight()V
    .locals 1

    .line 5519
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 5522
    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isSilhouette:Z

    .line 5523
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->applyBacklightEffect()V

    .line 5524
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    .line 5525
    return-void

    .line 5520
    :cond_1
    :goto_0
    return-void
.end method

.method protected updateFaceView(ZZ)V
    .locals 2

    .line 964
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    const/16 v1, 0x23

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->hasMessages(I)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 965
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    invoke-virtual {v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 967
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    .line 968
    nop

    .line 967
    invoke-virtual {v0, v1, p1, p2}, Lcom/android/camera/module/Camera2Module$MainHandler;->obtainMessage(III)Landroid/os/Message;

    move-result-object p1

    .line 968
    invoke-virtual {p1}, Landroid/os/Message;->sendToTarget()V

    .line 969
    return-void
.end method

.method public updateFlashPreference()V
    .locals 4

    .line 3764
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentFlash()Lcom/android/camera/data/data/config/ComponentConfigFlash;

    move-result-object v0

    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/config/ComponentConfigFlash;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    .line 3766
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->getRequestFlashMode()Ljava/lang/String;

    move-result-object v1

    .line 3768
    const/4 v2, 0x0

    invoke-static {v1, v2}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v3

    if-eqz v3, :cond_0

    .line 3769
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resetAiSceneInHdrOrFlashOn()V

    .line 3771
    :cond_0
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->setFlashMode(Ljava/lang/String;)V

    .line 3774
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mLastFlashMode:Ljava/lang/String;

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_2

    .line 3775
    invoke-static {v0, v2}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v1

    const/16 v3, 0x67

    if-eq v1, v3, :cond_1

    .line 3776
    invoke-static {v0, v2}, Lcom/android/camera/Util;->parseInt(Ljava/lang/String;I)I

    move-result v1

    if-nez v1, :cond_2

    .line 3777
    :cond_1
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resetAsdSceneInHdrOrFlashChange()V

    .line 3780
    :cond_2
    iput-object v0, p0, Lcom/android/camera/module/Camera2Module;->mLastFlashMode:Ljava/lang/String;

    .line 3792
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->stopObjectTracking(Z)V

    .line 3793
    return-void
.end method

.method public updateHDRPreference()V
    .locals 4

    .line 3798
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentHdr()Lcom/android/camera/data/data/config/ComponentConfigHdr;

    move-result-object v0

    .line 3799
    invoke-virtual {v0}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 3800
    return-void

    .line 3802
    :cond_0
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    invoke-virtual {v0, v1}, Lcom/android/camera/data/data/config/ComponentConfigHdr;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    .line 3804
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    const/high16 v2, 0x3f800000    # 1.0f

    cmpl-float v1, v1, v2

    const/4 v3, 0x0

    if-lez v1, :cond_1

    .line 3805
    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mMutexModePicker:Lcom/android/camera/MutexModeManager;

    invoke-virtual {v1}, Lcom/android/camera/MutexModeManager;->isHdr()Z

    move-result v1

    if-eqz v1, :cond_1

    const-string v1, "auto"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 3806
    invoke-virtual {p0, v3}, Lcom/android/camera/module/Camera2Module;->onHDRSceneChanged(Z)V

    .line 3811
    :cond_1
    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->updateHDR(Ljava/lang/String;)V

    .line 3813
    const-string v1, "off"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    iget-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mAiSceneEnabled:Z

    if-eqz v1, :cond_3

    :cond_2
    iget v1, p0, Lcom/android/camera/module/Camera2Module;->mZoomValue:F

    cmpl-float v1, v1, v2

    if-lez v1, :cond_4

    .line 3815
    :cond_3
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v3}, Lcom/android/camera2/Camera2Proxy;->setHDRCheckerEnable(Z)V

    .line 3816
    iput-boolean v3, p0, Lcom/android/camera/module/Camera2Module;->mHdrCheckEnabled:Z

    goto :goto_1

    .line 3818
    :cond_4
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resetAiSceneInHdrOrFlashOn()V

    .line 3819
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resetAsdSceneInHdrOrFlashChange()V

    .line 3820
    const-string v1, "auto"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_5

    .line 3821
    iput-boolean v1, p0, Lcom/android/camera/module/Camera2Module;->mHdrCheckEnabled:Z

    goto :goto_0

    .line 3824
    :cond_5
    iput-boolean v3, p0, Lcom/android/camera/module/Camera2Module;->mHdrCheckEnabled:Z

    .line 3826
    :goto_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->setHDRCheckerEnable(Z)V

    .line 3828
    :goto_1
    return-void
.end method

.method public updateManualEvAdjust()V
    .locals 8

    .line 4313
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0xa7

    if-ne v0, v1, :cond_5

    .line 4315
    const-string v0, "pref_qc_camera_exposuretime_key"

    .line 4316
    const v1, 0x7f090136

    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 4315
    invoke-direct {p0, v0, v2}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 4317
    const-string v2, "pref_qc_camera_iso_key"

    .line 4318
    const v3, 0x7f0900aa

    invoke-virtual {p0, v3}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 4317
    invoke-direct {p0, v2, v4}, Lcom/android/camera/module/Camera2Module;->getManualValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 4319
    sget-object v4, Lcom/android/camera/module/Camera2Module;->TAG:Ljava/lang/String;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "MODE_MANUAL: exposureTime = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "iso = "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 4320
    invoke-static {}, Lcom/mi/config/b;->ia()Z

    move-result v4

    const/4 v5, 0x1

    if-eqz v4, :cond_0

    .line 4321
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    goto :goto_1

    .line 4322
    :cond_0
    invoke-virtual {p0, v3}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_2

    .line 4323
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    goto :goto_0

    :cond_1
    const/4 v4, 0x0

    goto :goto_1

    .line 4324
    :cond_2
    :goto_0
    move v4, v5

    :goto_1
    iget-object v6, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    if-eqz v6, :cond_3

    .line 4325
    iget-object v6, p0, Lcom/android/camera/module/Camera2Module;->mHandler:Lcom/android/camera/module/Camera2Module$MainHandler;

    new-instance v7, Lcom/android/camera/module/Camera2Module$21;

    invoke-direct {v7, p0, v4}, Lcom/android/camera/module/Camera2Module$21;-><init>(Lcom/android/camera/module/Camera2Module;Z)V

    invoke-virtual {v6, v7}, Lcom/android/camera/module/Camera2Module$MainHandler;->post(Ljava/lang/Runnable;)Z

    .line 4333
    :cond_3
    iget-object v4, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v4}, Lcom/android/camera2/Camera2Proxy;->getFocusMode()I

    move-result v4

    if-ne v5, v4, :cond_5

    iget-boolean v4, p0, Lcom/android/camera/module/Camera2Module;->m3ALocked:Z

    if-eqz v4, :cond_5

    .line 4335
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 4336
    invoke-virtual {p0, v3}, Lcom/android/camera/module/Camera2Module;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5

    .line 4337
    :cond_4
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    new-instance v1, Lcom/android/camera/module/Camera2Module$22;

    invoke-direct {v1, p0}, Lcom/android/camera/module/Camera2Module$22;-><init>(Lcom/android/camera/module/Camera2Module;)V

    invoke-virtual {v0, v1}, Lcom/android/camera/Camera;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 4343
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->unlockAEAF()V

    .line 4346
    :cond_5
    return-void
.end method

.method public updateMoon(Z)V
    .locals 1

    .line 5610
    const/4 v0, 0x0

    if-eqz p1, :cond_0

    .line 5612
    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    .line 5613
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setSuperResolution(Z)V

    .line 5615
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateFocusMode()V

    .line 5616
    const/16 p1, 0x23

    iput p1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5618
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    invoke-virtual {v0, p1}, Lcom/android/camera2/Camera2Proxy;->setASDScene(I)V

    .line 5619
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    goto :goto_0

    .line 5622
    :cond_0
    iget-boolean p1, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    if-eqz p1, :cond_1

    .line 5623
    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    .line 5624
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mFocusManager:Lcom/android/camera/module/loader/camera2/FocusManager2;

    invoke-static {}, Lcom/android/camera/CameraSettings;->getFocusMode()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/android/camera/module/loader/camera2/FocusManager2;->setFocusMode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 5625
    invoke-virtual {p0, p1}, Lcom/android/camera/module/Camera2Module;->setFocusMode(Ljava/lang/String;)V

    .line 5627
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    const/16 v0, -0x23

    invoke-virtual {p1, v0}, Lcom/android/camera2/Camera2Proxy;->setASDScene(I)V

    .line 5630
    :cond_1
    :goto_0
    return-void
.end method

.method public updateMoonNight()V
    .locals 7

    .line 5587
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->mIsMoonMode:Z

    .line 5588
    const/16 v1, 0xa

    invoke-direct {p0, v1, v0}, Lcom/android/camera/module/Camera2Module;->checkCloseMoonMode(II)V

    .line 5589
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v2

    .line 5590
    const/16 v3, 0xa4

    invoke-virtual {v2, v3}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v2

    check-cast v2, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 5592
    const-string v3, "e"

    const/4 v4, 0x1

    new-array v5, v4, [I

    const/16 v6, 0xc1

    aput v6, v5, v0

    invoke-interface {v2, v3, v5}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->closeMutexElement(Ljava/lang/String;[I)V

    .line 5601
    const-string v0, "0"

    invoke-virtual {p0, v0}, Lcom/android/camera/module/Camera2Module;->setFlashMode(Ljava/lang/String;)V

    .line 5602
    invoke-direct {p0, v4}, Lcom/android/camera/module/Camera2Module;->updateMfnr(Z)V

    .line 5603
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->updateOIS()V

    .line 5604
    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->setAiSceneEffect(I)V

    .line 5605
    iput v1, p0, Lcom/android/camera/module/Camera2Module;->mCurrentAiScene:I

    .line 5606
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    .line 5607
    return-void
.end method

.method public updatePreviewSurface()V
    .locals 3

    .line 2353
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    if-eqz v0, :cond_0

    .line 2354
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->initEffectCropView()V

    .line 2356
    :cond_0
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->checkDisplayOrientation()V

    .line 2357
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraScreenNail;->getSurfaceCreatedTimestamp()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/android/camera/module/Camera2Module;->mSurfaceCreatedTimestamp:J

    .line 2358
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    if-eqz v0, :cond_1

    .line 2359
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v0, v0, Lcom/android/camera/CameraSize;->width:I

    iget-object v1, p0, Lcom/android/camera/module/Camera2Module;->mPreviewSize:Lcom/android/camera/CameraSize;

    iget v1, v1, Lcom/android/camera/CameraSize;->height:I

    invoke-virtual {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->updateCameraScreenNailSize(II)V

    .line 2361
    :cond_1
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    if-eqz v0, :cond_2

    .line 2362
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    new-instance v1, Landroid/view/Surface;

    iget-object v2, p0, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {v2}, Lcom/android/camera/Camera;->getCameraScreenNail()Lcom/android/camera/CameraScreenNail;

    move-result-object v2

    invoke-virtual {v2}, Lcom/android/camera/CameraScreenNail;->getSurfaceTexture()Landroid/graphics/SurfaceTexture;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/view/Surface;-><init>(Landroid/graphics/SurfaceTexture;)V

    invoke-virtual {v0, v1}, Lcom/android/camera2/Camera2Proxy;->updateDeferPreviewSession(Landroid/view/Surface;)Z

    .line 2364
    :cond_2
    return-void
.end method

.method public updateSilhouette()V
    .locals 2

    .line 5528
    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isDoingAction()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 5531
    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera/module/Camera2Module;->isSilhouette:Z

    .line 5532
    iget v0, p0, Lcom/android/camera/module/Camera2Module;->mModuleIndex:I

    const/16 v1, 0x18

    invoke-direct {p0, v0, v1}, Lcom/android/camera/module/Camera2Module;->trackAISceneChanged(II)V

    .line 5533
    invoke-direct {p0, v1}, Lcom/android/camera/module/Camera2Module;->setAiSceneEffect(I)V

    .line 5534
    const-string v0, "off"

    invoke-direct {p0, v0}, Lcom/android/camera/module/Camera2Module;->updateHDR(Ljava/lang/String;)V

    .line 5538
    invoke-direct {p0}, Lcom/android/camera/module/Camera2Module;->resumePreviewInWorkThread()V

    .line 5539
    return-void

    .line 5529
    :cond_1
    :goto_0
    return-void
.end method
