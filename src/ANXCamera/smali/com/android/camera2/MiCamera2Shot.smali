.class public abstract Lcom/android/camera2/MiCamera2Shot;
.super Ljava/lang/Object;
.source "MiCamera2Shot.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field protected mCameraHandler:Landroid/os/Handler;

.field protected mDeparted:Z

.field protected mMiCamera:Lcom/android/camera2/MiCamera2;

.field protected mPreviewThumbnailHash:I


# direct methods
.method public constructor <init>(Lcom/android/camera2/MiCamera2;)V
    .locals 1

    .line 32
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 30
    const/4 v0, -0x1

    iput v0, p0, Lcom/android/camera2/MiCamera2Shot;->mPreviewThumbnailHash:I

    .line 33
    iput-object p1, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    .line 34
    invoke-virtual {p1}, Lcom/android/camera2/MiCamera2;->getCameraHandler()Landroid/os/Handler;

    move-result-object p1

    iput-object p1, p0, Lcom/android/camera2/MiCamera2Shot;->mCameraHandler:Landroid/os/Handler;

    .line 35
    return-void
.end method


# virtual methods
.method protected abstract generateCaptureCallback()Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;
.end method

.method protected final generateParallelTaskData(J)Lcom/xiaomi/camera/core/ParallelTaskData;
    .locals 4

    .line 63
    iget-object v0, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    invoke-virtual {v0}, Lcom/android/camera2/MiCamera2;->getPictureCallback()Lcom/android/camera2/Camera2Proxy$PictureCallback;

    move-result-object v0

    .line 64
    if-nez v0, :cond_0

    .line 65
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object p1

    const-string p2, "null callback is not allowed!"

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 66
    const/4 p1, 0x0

    return-object p1

    .line 69
    :cond_0
    new-instance v1, Lcom/xiaomi/camera/core/ParallelTaskData;

    iget-object v2, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    .line 70
    invoke-virtual {v2}, Lcom/android/camera2/MiCamera2;->getCameraConfigs()Lcom/android/camera2/CameraConfigs;

    move-result-object v2

    invoke-virtual {v2}, Lcom/android/camera2/CameraConfigs;->getShotType()I

    move-result v2

    iget-object v3, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    .line 71
    invoke-virtual {v3}, Lcom/android/camera2/MiCamera2;->getCameraConfigs()Lcom/android/camera2/CameraConfigs;

    move-result-object v3

    invoke-virtual {v3}, Lcom/android/camera2/CameraConfigs;->getShotPath()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, p1, p2, v2, v3}, Lcom/xiaomi/camera/core/ParallelTaskData;-><init>(JILjava/lang/String;)V

    .line 72
    iget-object p1, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    .line 73
    invoke-virtual {p1}, Lcom/android/camera2/MiCamera2;->getPictureSize()Lcom/android/camera/CameraSize;

    move-result-object p1

    .line 72
    invoke-interface {v0, v1, p1}, Lcom/android/camera2/Camera2Proxy$PictureCallback;->onCaptureStart(Lcom/xiaomi/camera/core/ParallelTaskData;Lcom/android/camera/CameraSize;)Lcom/xiaomi/camera/core/ParallelTaskData;

    move-result-object p1

    .line 75
    return-object p1
.end method

.method protected abstract generateRequestBuilder()Landroid/hardware/camera2/CaptureRequest$Builder;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/hardware/camera2/CameraAccessException;,
            Ljava/lang/IllegalStateException;
        }
    .end annotation
.end method

.method protected isInQcfaMode()Z
    .locals 2

    .line 79
    iget-object v0, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    invoke-virtual {v0}, Lcom/android/camera2/MiCamera2;->getCapabilities()Lcom/android/camera2/CameraCapabilities;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->isSupportedQcfa()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    .line 80
    invoke-virtual {v0}, Lcom/android/camera2/MiCamera2;->getCapabilities()Lcom/android/camera2/CameraCapabilities;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getOperatingMode()I

    move-result v0

    const v1, 0x8007

    if-eq v0, v1, :cond_0

    iget-object v0, p0, Lcom/android/camera2/MiCamera2Shot;->mMiCamera:Lcom/android/camera2/MiCamera2;

    .line 81
    invoke-virtual {v0}, Lcom/android/camera2/MiCamera2;->getCapabilities()Lcom/android/camera2/CameraCapabilities;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera2/CameraCapabilities;->getOperatingMode()I

    move-result v0

    const v1, 0x80f5

    if-ne v0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 79
    :goto_0
    return v0
.end method

.method protected final makeClobber()V
    .locals 1

    .line 59
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/android/camera2/MiCamera2Shot;->mDeparted:Z

    .line 60
    return-void
.end method

.method protected abstract notifyResultData(Ljava/lang/Object;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation
.end method

.method protected abstract onImageReceived(Landroid/media/Image;I)V
.end method

.method protected final onPreviewThumbnailReceived(Lcom/android/camera/Thumbnail;)V
    .locals 0

    .line 43
    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result p1

    iput p1, p0, Lcom/android/camera2/MiCamera2Shot;->mPreviewThumbnailHash:I

    .line 44
    return-void
.end method

.method protected abstract prepare()V
.end method

.method protected abstract startSessionCapture()V
.end method

.method protected final startShot()V
    .locals 0

    .line 38
    invoke-virtual {p0}, Lcom/android/camera2/MiCamera2Shot;->prepare()V

    .line 39
    invoke-virtual {p0}, Lcom/android/camera2/MiCamera2Shot;->startSessionCapture()V

    .line 40
    return-void
.end method