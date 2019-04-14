package com.android.camera2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CaptureRequest.Builder;
import android.media.Image;
import android.os.Handler;
import com.android.camera.Thumbnail;
import com.android.camera.log.Log;
import com.android.camera2.Camera2Proxy.PictureCallback;
import com.xiaomi.camera.core.ParallelTaskData;

public abstract class MiCamera2Shot<T> {
    protected Handler mCameraHandler;
    protected boolean mDeparted;
    protected MiCamera2 mMiCamera;
    protected int mPreviewThumbnailHash = -1;

    public abstract CaptureCallback generateCaptureCallback();

    public abstract Builder generateRequestBuilder() throws CameraAccessException, IllegalStateException;

    public abstract void notifyResultData(T t);

    public abstract void onImageReceived(Image image, int i);

    public abstract void prepare();

    public abstract void startSessionCapture();

    public MiCamera2Shot(MiCamera2 miCamera2) {
        this.mMiCamera = miCamera2;
        this.mCameraHandler = miCamera2.getCameraHandler();
    }

    /* Access modifiers changed, original: protected|final */
    public final void startShot() {
        prepare();
        startSessionCapture();
    }

    /* Access modifiers changed, original: protected|final */
    public final void onPreviewThumbnailReceived(Thumbnail thumbnail) {
        this.mPreviewThumbnailHash = thumbnail.hashCode();
    }

    /* Access modifiers changed, original: protected|final */
    public final void makeClobber() {
        this.mDeparted = true;
    }

    /* Access modifiers changed, original: protected|final */
    public final ParallelTaskData generateParallelTaskData(long j) {
        PictureCallback pictureCallback = this.mMiCamera.getPictureCallback();
        if (pictureCallback != null) {
            return pictureCallback.onCaptureStart(new ParallelTaskData(j, this.mMiCamera.getCameraConfigs().getShotType(), this.mMiCamera.getCameraConfigs().getShotPath()), this.mMiCamera.getPictureSize());
        }
        Log.e(getClass().getSimpleName(), "null callback is not allowed!");
        return null;
    }

    /* Access modifiers changed, original: protected */
    public boolean isInQcfaMode() {
        return this.mMiCamera.getCapabilities().isSupportedQcfa() && (this.mMiCamera.getCapabilities().getOperatingMode() == 32775 || this.mMiCamera.getCapabilities().getOperatingMode() == CameraCapabilities.SESSION_OPERATION_MODE_PROFESSIONAL_ULTRA_PIXEL_PHOTOGRAPHY);
    }
}
