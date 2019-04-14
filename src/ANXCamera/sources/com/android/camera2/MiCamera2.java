package com.android.camera2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCaptureSession.StateCallback;
import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureRequest.Key;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.impl.CameraDeviceImpl;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.utils.SurfaceUtils;
import android.location.Location;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.camera.CameraSettings;
import com.android.camera.CameraSize;
import com.android.camera.LocalParallelService.LocalBinder;
import com.android.camera.Thumbnail;
import com.android.camera.Util;
import com.android.camera.constant.BeautyConstant;
import com.android.camera.constant.MiCaptureRequest;
import com.android.camera.constant.MiCaptureResult;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.beauty.BeautyValues;
import com.android.camera.lib.compatibility.util.CompatibilityUtils;
import com.android.camera.log.Log;
import com.android.camera.module.ModuleManager;
import com.android.camera.module.loader.camera2.FocusTask;
import com.android.camera.parallel.AlgoConnector;
import com.android.camera.statistic.CameraStat;
import com.android.camera2.Camera2Proxy.CameraMetaDataCallback;
import com.android.camera2.Camera2Proxy.CameraPreviewCallback;
import com.android.camera2.Camera2Proxy.FocusCallback;
import com.android.camera2.Camera2Proxy.PictureCallback;
import com.android.camera2.Camera2Proxy.PreviewCallback;
import com.android.camera2.Camera2Proxy.VideoRecordStateCallback;
import com.android.camera2.autozoom.AutoZoomTags.TAG;
import com.android.camera2.compat.MiCameraCompat;
import com.mi.config.b;
import com.xiaomi.camera.core.ParallelCallback;
import com.xiaomi.protocol.IImageReaderParameterSets;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@TargetApi(21)
public class MiCamera2 extends Camera2Proxy {
    private static final int MAX_IMAGE_BUFFER_SIZE = 10;
    private static final int MSG_CHECK_CAMERA_ALIVE = 3;
    private static final int MSG_WAITING_AF_LOCK_TIMEOUT = 1;
    private static final int MSG_WAITING_LOCAL_PARALLER_SERVICE_READY = 2;
    private static final String TAG = MiCamera2.class.getSimpleName();
    static final MeteringRectangle[] ZERO_WEIGHT_3A_REGION = new MeteringRectangle[]{new MeteringRectangle(0, 0, 0, 0, 0)};
    private CameraDevice mCameraDevice;
    private Handler mCameraHandler;
    private Handler mCameraMainThreadHandler;
    private final CameraCapabilities mCapabilities;
    private PictureCaptureCallback mCaptureCallback;
    private CameraCaptureSession mCaptureSession;
    private CaptureSessionStateCallback mCaptureSessionStateCallback;
    private CameraConfigs mConfigs;
    private long mCurrentFrameNum = -1;
    private List<OutputConfiguration> mDeferOutputConfigurations = new ArrayList();
    private ImageReader mDepthReader;
    private int mDisplayOrientation;
    private boolean mEnableParallelSession;
    private SurfaceTexture mFakeOutputTexture;
    private int mFocusLockRequestHashCode;
    private Handler mHelperHandler;
    private Range<Integer> mHighSpeedFpsRange;
    private boolean mIsPreviewCallbackEnabled;
    private boolean mIsPreviewCallbackStarted;
    private long mLastFrameNum = -1;
    private MiCamera2Shot mMiCamera2Shot;
    private List<Surface> mParallelCaptureSurfaceList;
    private final Object mParallelSessionLock = new Object();
    private volatile boolean mPendingNotifyVideoEnd;
    private ImageReader mPhotoImageReader;
    private ImageReader mPortraitRawImageReader;
    private int mPreCaptureRequestHashCode;
    private ImageReader mPreviewImageReader;
    private CaptureRequest mPreviewRequest;
    private Builder mPreviewRequestBuilder;
    private Surface mPreviewSurface;
    private ImageReader mRawImageReader;
    private Surface mRecordSurface;
    private List<ImageReader> mRemoteImageReaderList = new ArrayList();
    private int mScreenLightColorTemperature;
    private int mSessionId;
    private boolean mSetRepeatingEarly = DataRepository.dataItemFeature().ga();
    private boolean mToTele;
    private VideoRecordStateCallback mVideoRecordStateCallback;
    private final Object mVideoRecordStateLock = new Object();
    private int mVideoSessionId;
    private ImageReader mVideoSnapshotImageReader;

    private class CaptureSessionStateCallback extends StateCallback {
        private WeakReference<CameraPreviewCallback> mClientCb;
        private int mId;

        public CaptureSessionStateCallback(int i, CameraPreviewCallback cameraPreviewCallback) {
            this.mId = i;
            this.mClientCb = new WeakReference(cameraPreviewCallback);
        }

        public void setClientCb(CameraPreviewCallback cameraPreviewCallback) {
            this.mClientCb = new WeakReference(cameraPreviewCallback);
        }

        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            if (this.mId == MiCamera2.this.mSessionId) {
                String access$000 = MiCamera2.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onConfigured: id=");
                stringBuilder.append(this.mId);
                stringBuilder.append(" session=");
                stringBuilder.append(cameraCaptureSession);
                Log.d(access$000, stringBuilder.toString());
                MiCamera2.this.mCaptureSession = cameraCaptureSession;
                if (MiCamera2.this.mPendingNotifyVideoEnd && this.mId == MiCamera2.this.mVideoSessionId) {
                    MiCamera2.this.notifyVideoStreamEnd();
                    MiCamera2.this.mPendingNotifyVideoEnd = false;
                }
                synchronized (MiCamera2.this.mParallelSessionLock) {
                    if (!MiCamera2.this.mDeferOutputConfigurations.isEmpty() && MiCamera2.this.mSetRepeatingEarly) {
                        MiCamera2.this.resumePreview();
                    }
                }
                if (MiCamera2.this.updateDeferPreviewSession(MiCamera2.this.mPreviewSurface)) {
                    onPreviewSessionSuccess();
                }
            }
        }

        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
            String access$000 = MiCamera2.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onConfigureFailed: id=");
            stringBuilder.append(this.mId);
            stringBuilder.append(" sessionId=");
            stringBuilder.append(MiCamera2.this.mSessionId);
            Log.e(access$000, stringBuilder.toString());
            if (this.mClientCb != null) {
                CameraPreviewCallback cameraPreviewCallback = (CameraPreviewCallback) this.mClientCb.get();
                if (cameraPreviewCallback != null) {
                    cameraPreviewCallback.onPreviewSessionFailed(cameraCaptureSession);
                }
            }
        }

        public void onClosed(@NonNull CameraCaptureSession cameraCaptureSession) {
            String access$000 = MiCamera2.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onClosed: id=");
            stringBuilder.append(this.mId);
            stringBuilder.append(" sessionId=");
            stringBuilder.append(MiCamera2.this.mSessionId);
            Log.e(access$000, stringBuilder.toString());
            if (this.mClientCb != null) {
                CameraPreviewCallback cameraPreviewCallback = (CameraPreviewCallback) this.mClientCb.get();
                if (cameraPreviewCallback != null) {
                    cameraPreviewCallback.onPreviewSessionClosed(cameraCaptureSession);
                }
            }
        }

        public void onPreviewSessionSuccess() {
            boolean isEmpty;
            synchronized (MiCamera2.this.mParallelSessionLock) {
                isEmpty = MiCamera2.this.mDeferOutputConfigurations.isEmpty();
            }
            if (isEmpty && this.mId == MiCamera2.this.mSessionId && this.mClientCb != null) {
                CameraPreviewCallback cameraPreviewCallback = (CameraPreviewCallback) this.mClientCb.get();
                if (cameraPreviewCallback != null) {
                    cameraPreviewCallback.onPreviewSessionSuccess(MiCamera2.this.mCaptureSession);
                }
            }
        }
    }

    private class HighSpeedCaptureSessionStateCallback extends StateCallback {
        private CameraPreviewCallback mClientCb;
        private int mId;

        public HighSpeedCaptureSessionStateCallback(int i, CameraPreviewCallback cameraPreviewCallback) {
            this.mId = i;
            this.mClientCb = cameraPreviewCallback;
        }

        public void setClientCb(CameraPreviewCallback cameraPreviewCallback) {
            this.mClientCb = cameraPreviewCallback;
        }

        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            if (this.mId == MiCamera2.this.mSessionId) {
                String access$000 = MiCamera2.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onHighSpeedConfigured: id=");
                stringBuilder.append(this.mId);
                stringBuilder.append(" highSpeedSession=");
                stringBuilder.append(cameraCaptureSession);
                Log.d(access$000, stringBuilder.toString());
                MiCamera2.this.mCaptureSession = cameraCaptureSession;
                MiCamera2.this.applySettingsForVideo(MiCamera2.this.mPreviewRequestBuilder);
                MiCameraCompat.applyIsHfrPreview(MiCamera2.this.mPreviewRequestBuilder, true);
                if (this.mClientCb != null) {
                    this.mClientCb.onPreviewSessionSuccess(cameraCaptureSession);
                }
            }
        }

        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
            String access$000 = MiCamera2.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onHighSpeedConfigureFailed: id=");
            stringBuilder.append(this.mId);
            stringBuilder.append(" sessionId=");
            stringBuilder.append(MiCamera2.this.mSessionId);
            Log.e(access$000, stringBuilder.toString());
            if (this.mClientCb != null) {
                this.mClientCb.onPreviewSessionFailed(cameraCaptureSession);
            }
        }

        public void onClosed(@NonNull CameraCaptureSession cameraCaptureSession) {
            String access$000 = MiCamera2.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onHighSpeedClosed: ");
            stringBuilder.append(cameraCaptureSession);
            Log.d(access$000, stringBuilder.toString());
            if (MiCamera2.this.mCaptureSession != null && MiCamera2.this.mCaptureSession.equals(cameraCaptureSession)) {
                MiCamera2.this.mCaptureSession = null;
            }
            if (this.mClientCb != null) {
                this.mClientCb.onPreviewSessionClosed(cameraCaptureSession);
            }
        }
    }

    private class PictureCaptureCallback extends CaptureCallback {
        private FocusTask mAutoFocusTask;
        private int mLastResultAFState = -1;
        private FocusTask mManuallyFocusTask;
        private boolean mPartialResultSupported;
        private CaptureResult mPreviewCaptureResult;
        private final Object mPreviewCaptureResultLock = new Object();
        private int mState = 0;
        private final Object mStateLock = new Object();
        private FocusTask mTorchFocusTask;

        PictureCaptureCallback() {
            this.mPartialResultSupported = MiCamera2.this.mCapabilities.isPartialMetadataSupported();
        }

        /* Access modifiers changed, original: 0000 */
        public void setState(int i) {
            synchronized (this.mStateLock) {
                String access$000 = MiCamera2.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("setState: ");
                stringBuilder.append(i);
                Log.d(access$000, stringBuilder.toString());
                this.mState = i;
            }
        }

        public int getState() {
            int i;
            synchronized (this.mStateLock) {
                i = this.mState;
            }
            return i;
        }

        /* Access modifiers changed, original: 0000 */
        public Integer getCurrentAEState() {
            if (getPreviewCaptureResult() == null) {
                return null;
            }
            return (Integer) getPreviewCaptureResult().get(CaptureResult.CONTROL_AE_STATE);
        }

        /* Access modifiers changed, original: 0000 */
        public int getCurrentColorTemperature() {
            CaptureResult previewCaptureResult = getPreviewCaptureResult();
            int i = 0;
            if (previewCaptureResult == null) {
                return 0;
            }
            AWBFrameControl aWBFrameControl = CaptureResultParser.getAWBFrameControl(previewCaptureResult);
            if (aWBFrameControl != null) {
                i = aWBFrameControl.getColorTemperature();
            }
            return i;
        }

        /* Access modifiers changed, original: 0000 */
        public CaptureResult getPreviewCaptureResult() {
            CaptureResult captureResult;
            synchronized (this.mPreviewCaptureResultLock) {
                if (this.mPreviewCaptureResult == null) {
                    String access$000 = MiCamera2.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("returned a null PreviewCaptureResult, mState is ");
                    stringBuilder.append(this.mState);
                    Log.w(access$000, stringBuilder.toString());
                }
                captureResult = this.mPreviewCaptureResult;
            }
            return captureResult;
        }

        /* Access modifiers changed, original: 0000 */
        public void setFocusTask(FocusTask focusTask) {
            this.mManuallyFocusTask = focusTask;
        }

        public void onCaptureProgressed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureResult captureResult) {
            processPartial(captureResult);
        }

        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
            if (totalCaptureResult.getFrameNumber() == 0) {
                String access$000 = MiCamera2.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onCaptureCompleted Sequence: ");
                stringBuilder.append(totalCaptureResult.getSequenceId());
                stringBuilder.append(" first frame received");
                Log.d(access$000, stringBuilder.toString());
                MiCamera2.this.triggerDeviceChecking(false);
            }
            if (getState() == 0) {
                setState(1);
            }
            if (!this.mPartialResultSupported) {
                processPartial(totalCaptureResult);
            }
            process(totalCaptureResult);
            MiCamera2.this.updateFrameNumber(totalCaptureResult.getFrameNumber());
        }

        private void processPartial(@NonNull CaptureResult captureResult) {
            processAfResult(captureResult);
            Integer num;
            Integer num2;
            Integer num3;
            StringBuilder stringBuilder;
            String access$000;
            switch (getState()) {
                case 3:
                    num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                    if (num != null) {
                        if (4 == num.intValue() || 5 == num.intValue() || 2 == num.intValue() || 6 == num.intValue() || (MiCamera2.this.mFocusLockRequestHashCode == captureResult.getRequest().hashCode() && num.intValue() == 0)) {
                            if (MiCamera2.this.mFocusLockRequestHashCode == captureResult.getRequest().hashCode() || MiCamera2.this.mFocusLockRequestHashCode == 0) {
                                if (MiCamera2.this.mHelperHandler != null) {
                                    MiCamera2.this.mHelperHandler.removeMessages(1);
                                }
                                MiCamera2.this.runCaptureSequence();
                                return;
                            }
                            return;
                        } else if (MiCamera2.this.mFocusLockRequestHashCode == captureResult.getRequest().hashCode()) {
                            MiCamera2.this.mFocusLockRequestHashCode = 0;
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                case 4:
                    num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                    num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AWB_STATE);
                    String access$0002 = MiCamera2.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("STATE_WAITING_AE_LOCK:  AF = ");
                    stringBuilder.append(Util.controlAFStateToString(num));
                    Log.d(access$0002, stringBuilder.toString());
                    access$000 = MiCamera2.TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("STATE_WAITING_AE_LOCK:  AE = ");
                    stringBuilder2.append(Util.controlAEStateToString(num2));
                    Log.d(access$000, stringBuilder2.toString());
                    access$000 = MiCamera2.TAG;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("STATE_WAITING_AE_LOCK: AWB = ");
                    stringBuilder2.append(Util.controlAWBStateToString(num3));
                    Log.d(access$000, stringBuilder2.toString());
                    if (num2 == null || num2.intValue() == 3) {
                        Log.d(MiCamera2.TAG, "STATE_WAITING_AE_LOCK: runCaptureSequence()");
                        MiCamera2.this.runCaptureSequence();
                        return;
                    }
                    Log.d(MiCamera2.TAG, "STATE_WAITING_AE_LOCK: keep stay in STATE_WAITING_AE_LOCK");
                    return;
                case 5:
                    num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                    Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    Integer num5 = (Integer) captureResult.get(CaptureResult.CONTROL_AWB_STATE);
                    String access$0003 = MiCamera2.TAG;
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("STATE_WAITING_PRECAPTURE:  AF = ");
                    stringBuilder3.append(Util.controlAFStateToString(num));
                    Log.d(access$0003, stringBuilder3.toString());
                    access$000 = MiCamera2.TAG;
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("STATE_WAITING_PRECAPTURE:  AE = ");
                    stringBuilder4.append(Util.controlAEStateToString(num4));
                    Log.d(access$000, stringBuilder4.toString());
                    access$000 = MiCamera2.TAG;
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("STATE_WAITING_PRECAPTURE: AWB = ");
                    stringBuilder4.append(Util.controlAWBStateToString(num5));
                    Log.d(access$000, stringBuilder4.toString());
                    if (num4 == null || num4.intValue() == 5 || num4.intValue() == 4) {
                        Log.d(MiCamera2.TAG, "STATE_WAITING_PRECAPTURE: switch to STATE_WAITING_NON_PRECAPTURE(1)");
                        setState(6);
                        return;
                    } else if (MiCamera2.this.mPreCaptureRequestHashCode == captureResult.getRequest().hashCode()) {
                        Log.d(MiCamera2.TAG, "STATE_WAITING_PRECAPTURE: switch to STATE_WAITING_NON_PRECAPTURE(2)");
                        setState(6);
                        return;
                    } else {
                        Log.d(MiCamera2.TAG, "STATE_WAITING_PRECAPTURE: keep stay in STATE_WAITING_PRECAPTURE");
                        return;
                    }
                case 6:
                    num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                    num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AWB_STATE);
                    String access$0004 = MiCamera2.TAG;
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("STATE_WAITING_NON_PRECAPTURE:  AF = ");
                    stringBuilder5.append(Util.controlAFStateToString(num));
                    Log.d(access$0004, stringBuilder5.toString());
                    access$000 = MiCamera2.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("STATE_WAITING_NON_PRECAPTURE:  AE = ");
                    stringBuilder.append(Util.controlAEStateToString(num2));
                    Log.d(access$000, stringBuilder.toString());
                    access$000 = MiCamera2.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("STATE_WAITING_NON_PRECAPTURE: AWB = ");
                    stringBuilder.append(Util.controlAWBStateToString(num3));
                    Log.d(access$000, stringBuilder.toString());
                    if (num2 == null || num2.intValue() != 5) {
                        Log.d(MiCamera2.TAG, "STATE_WAITING_NON_PRECAPTURE: lockExposure()");
                        MiCamera2.this.lockExposure(false);
                        return;
                    }
                    Log.d(MiCamera2.TAG, "STATE_WAITING_NON_PRECAPTURE: keep stay in STATE_WAITING_NON_PRECAPTURE");
                    return;
                default:
                    return;
            }
        }

        private void process(@NonNull CaptureResult captureResult) {
            synchronized (this.mPreviewCaptureResultLock) {
                this.mPreviewCaptureResult = captureResult;
            }
            processVideoRecordStatus(captureResult);
            if (MiCamera2.this.mToTele && CaptureResultParser.getFastZoomResult(captureResult)) {
                Log.d(MiCamera2.TAG, "process: CaptureResultParser fast zoom...");
                MiCamera2.this.setOpticalZoomToTele(false);
                MiCamera2.this.resumePreview();
            }
            CameraMetaDataCallback metadataCallback = MiCamera2.this.getMetadataCallback();
            int state = getState();
            switch (state) {
                case 1:
                    if (metadataCallback != null) {
                        metadataCallback.onPreviewMetaDataUpdate(captureResult);
                        return;
                    }
                    return;
                case 2:
                    if (metadataCallback != null) {
                        metadataCallback.onPreviewMetaDataUpdate(captureResult);
                        return;
                    }
                    return;
                default:
                    Integer num;
                    switch (state) {
                        case 8:
                            num = (Integer) captureResult.get(CaptureResult.FLASH_STATE);
                            if (num == null || num.intValue() == 2) {
                                setState(7);
                                MiCamera2.this.captureStillPicture();
                                return;
                            }
                            return;
                        case 9:
                            num = (Integer) captureResult.get(CaptureResult.FLASH_STATE);
                            if (num != null && 3 == num.intValue()) {
                                MiCamera2.this.triggerPrecapture();
                                return;
                            }
                            return;
                        case 10:
                            num = (Integer) captureResult.get(CaptureResult.FLASH_STATE);
                            if (num == null || num.intValue() == 2) {
                                setState(0);
                                MiCamera2.this.pausePreview();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
            }
        }

        private void processVideoRecordStatus(CaptureResult captureResult) {
            synchronized (MiCamera2.this.mVideoRecordStateLock) {
                if (MiCamera2.this.mVideoRecordStateCallback != null) {
                    Integer num = (Integer) captureResult.get(MiCaptureResult.VIDEO_RECORD_STATE);
                    if (num != null && 2 == num.intValue()) {
                        MiCamera2.this.mVideoRecordStateCallback.onVideoRecordStopped();
                        MiCamera2.this.mVideoRecordStateCallback = null;
                    }
                }
            }
        }

        private void processAfResult(CaptureResult captureResult) {
            Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
            if (num != null) {
                FocusCallback focusCallback = MiCamera2.this.getFocusCallback();
                if (focusCallback != null && num.intValue() != this.mLastResultAFState) {
                    Log.d(MiCamera2.TAG, String.format(Locale.ENGLISH, "afState changed from %d to %d", new Object[]{Integer.valueOf(this.mLastResultAFState), Integer.valueOf(num.intValue())}));
                    this.mLastResultAFState = num.intValue();
                    Boolean isFocusLocked;
                    if (this.mManuallyFocusTask == null) {
                        if (isAutoFocusing(num).booleanValue()) {
                            this.mAutoFocusTask = FocusTask.create(2);
                            focusCallback.onFocusStateChanged(this.mAutoFocusTask);
                        } else {
                            isFocusLocked = isFocusLocked(num);
                            if (!(isFocusLocked == null || this.mAutoFocusTask == null)) {
                                this.mAutoFocusTask.setResult(isFocusLocked.booleanValue());
                                focusCallback.onFocusStateChanged(this.mAutoFocusTask);
                                this.mAutoFocusTask = null;
                            }
                        }
                    } else if (this.mAutoFocusTask != null) {
                        this.mAutoFocusTask = null;
                    } else {
                        isFocusLocked = isFocusLocked(num);
                        if (isFocusLocked != null) {
                            this.mManuallyFocusTask.setResult(isFocusLocked.booleanValue());
                            focusCallback.onFocusStateChanged(this.mManuallyFocusTask);
                            this.mManuallyFocusTask = null;
                            setState(1);
                        }
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void showAutoFocusStart() {
            FocusCallback focusCallback = MiCamera2.this.getFocusCallback();
            if (focusCallback != null) {
                this.mTorchFocusTask = FocusTask.create(3);
                focusCallback.onFocusStateChanged(this.mTorchFocusTask);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void showAutoFocusFinish(boolean z) {
            if (this.mTorchFocusTask != null) {
                FocusCallback focusCallback = MiCamera2.this.getFocusCallback();
                if (focusCallback != null) {
                    this.mTorchFocusTask.setResult(z);
                    focusCallback.onFocusStateChanged(this.mTorchFocusTask);
                    this.mTorchFocusTask = null;
                }
            }
        }

        private Boolean isFocusLocked(Integer num) {
            int intValue = num.intValue();
            if (intValue != 2) {
                switch (intValue) {
                    case 4:
                        break;
                    case 5:
                    case 6:
                        return Boolean.FALSE;
                    default:
                        return null;
                }
            }
            return Boolean.TRUE;
        }

        private Boolean isAutoFocusing(Integer num) {
            int intValue = num.intValue();
            if (intValue == 1 || intValue == 3) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    public MiCamera2(CameraDevice cameraDevice, int i, CameraCapabilities cameraCapabilities, @NonNull Handler handler, @NonNull Handler handler2) {
        super(i);
        this.mCameraDevice = cameraDevice;
        this.mCapabilities = cameraCapabilities;
        this.mConfigs = new CameraConfigs();
        this.mCameraHandler = handler;
        this.mCameraMainThreadHandler = handler2;
        initHelperHandler(this.mCameraHandler.getLooper());
        this.mCaptureCallback = new PictureCaptureCallback();
    }

    private void initHelperHandler(Looper looper) {
        this.mHelperHandler = new Handler(looper) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        Log.e(MiCamera2.TAG, "waiting af lock timeOut");
                        MiCamera2.this.runCaptureSequence();
                        return;
                    case 2:
                        MiCamera2.this.updateDeferPreviewSession(MiCamera2.this.mPreviewSurface);
                        return;
                    case 3:
                        if (message.arg1 == 1 && ModuleManager.isManualModule() && MiCamera2.this.mConfigs.getExposureTime() / 1000000 >= 5000) {
                            removeMessages(3);
                            sendEmptyMessageDelayed(3, 5000 + (MiCamera2.this.mConfigs.getExposureTime() / 1000000));
                            return;
                        } else if (message.arg1 != 0) {
                            return;
                        } else {
                            if (MiCamera2.this.isDeviceAlive()) {
                                sendEmptyMessageDelayed(3, 5000);
                                return;
                            } else {
                                MiCamera2.this.notifyOnError(237);
                                return;
                            }
                        }
                    default:
                        return;
                }
            }
        };
    }

    public CameraCapabilities getCapabilities() {
        return this.mCapabilities;
    }

    /* Access modifiers changed, original: protected */
    public CameraDevice getCameraDevice() {
        return this.mCameraDevice;
    }

    /* Access modifiers changed, original: protected */
    public CameraCaptureSession getCaptureSession() {
        return this.mCaptureSession;
    }

    /* Access modifiers changed, original: protected */
    public CameraConfigs getCameraConfigs() {
        return this.mConfigs;
    }

    /* Access modifiers changed, original: protected */
    public Surface getPreviewSurface() {
        return this.mPreviewSurface;
    }

    /* Access modifiers changed, original: protected */
    public Surface getRecordSurface() {
        return this.mRecordSurface;
    }

    /* Access modifiers changed, original: protected */
    public ImageReader getPhotoImageReader() {
        return this.mPhotoImageReader;
    }

    /* Access modifiers changed, original: protected */
    public ImageReader getDepthImageReader() {
        return this.mDepthReader;
    }

    /* Access modifiers changed, original: protected */
    public ImageReader getPortraitRawImageReader() {
        return this.mPortraitRawImageReader;
    }

    /* Access modifiers changed, original: protected */
    public ImageReader getVideoSnapshotImageReader() {
        return this.mVideoSnapshotImageReader;
    }

    /* Access modifiers changed, original: protected */
    public Builder getPreviewRequestBuilder() {
        return this.mPreviewRequestBuilder;
    }

    /* Access modifiers changed, original: protected */
    public List<Surface> getRemoteSurfaceList() {
        return this.mParallelCaptureSurfaceList;
    }

    private void preparePhotoImageReader(@NonNull CameraSize cameraSize, int i, int i2) {
        if (this.mPhotoImageReader != null) {
            this.mPhotoImageReader.close();
        }
        this.mPhotoImageReader = ImageReader.newInstance(cameraSize.getWidth(), cameraSize.getHeight(), i, i2);
        this.mPhotoImageReader.setOnImageAvailableListener(new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Log.d(MiCamera2.TAG, "onImageAvailable: main");
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    if (MiCamera2.this.mMiCamera2Shot == null) {
                        Log.w(MiCamera2.TAG, "onImageAvailable: NO main image processor!");
                        acquireNextImage.close();
                        return;
                    }
                    MiCamera2.this.mMiCamera2Shot.onImageReceived(acquireNextImage, 0);
                }
            }
        }, this.mCameraHandler);
    }

    private void preparePhotoImageReader() {
        preparePhotoImageReader(this.mConfigs.getPhotoSize(), this.mConfigs.getPhotoFormat(), this.mConfigs.getPhotoMaxImages());
    }

    private void preparePreviewImageReader() {
        preparePreviewImageReader(this.mConfigs.getPreviewSize(), this.mConfigs.getPreviewFormat(), this.mConfigs.getPreviewMaxImages(), null);
    }

    private void preparePreviewImageReader(@NonNull CameraSize cameraSize, int i, int i2, Handler handler) {
        if (this.mPreviewImageReader != null) {
            this.mPreviewImageReader.close();
        }
        this.mPreviewImageReader = ImageReader.newInstance(cameraSize.getWidth(), cameraSize.getHeight(), i, i2);
        ImageReader imageReader = this.mPreviewImageReader;
        AnonymousClass3 anonymousClass3 = new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    PreviewCallback previewCallback = MiCamera2.this.getPreviewCallback();
                    if (previewCallback != null) {
                        previewCallback.onPreviewFrame(acquireNextImage, MiCamera2.this, MiCamera2.this.mConfigs.getDeviceOrientation());
                    }
                    acquireNextImage.close();
                }
            }
        };
        if (handler == null) {
            handler = this.mCameraMainThreadHandler;
        }
        imageReader.setOnImageAvailableListener(anonymousClass3, handler);
    }

    private void prepareRawImageReader(@NonNull CameraSize cameraSize) {
        if (this.mRawImageReader != null) {
            this.mRawImageReader.close();
        }
        AnonymousClass4 anonymousClass4 = new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                PictureCallback rawCallBack = MiCamera2.this.getRawCallBack();
                if (rawCallBack != null) {
                    byte[] firstPlane = Util.getFirstPlane(acquireNextImage);
                    if (firstPlane != null) {
                        rawCallBack.onPictureTaken(firstPlane);
                    }
                }
                acquireNextImage.close();
            }
        };
        this.mRawImageReader = ImageReader.newInstance(cameraSize.getWidth(), cameraSize.getHeight(), 37, 2);
        this.mRawImageReader.setOnImageAvailableListener(anonymousClass4, this.mCameraHandler);
    }

    private void prepareVideoSnapshotImageReader(CameraSize cameraSize) {
        if (this.mVideoSnapshotImageReader != null) {
            this.mVideoSnapshotImageReader.close();
        }
        AnonymousClass5 anonymousClass5 = new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    if (MiCamera2.this.mMiCamera2Shot == null) {
                        Log.w(MiCamera2.TAG, "onImageAvailable: NO video image processor!");
                        acquireNextImage.close();
                        return;
                    }
                    MiCamera2.this.mMiCamera2Shot.onImageReceived(acquireNextImage, 0);
                }
            }
        };
        this.mVideoSnapshotImageReader = ImageReader.newInstance(cameraSize.getWidth(), cameraSize.getHeight(), 256, 2);
        this.mVideoSnapshotImageReader.setOnImageAvailableListener(anonymousClass5, this.mCameraHandler);
    }

    private void prepareDepthImageReader(CameraSize cameraSize) {
        if (this.mDepthReader != null) {
            this.mDepthReader.close();
        }
        AnonymousClass6 anonymousClass6 = new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    if (MiCamera2.this.mMiCamera2Shot == null) {
                        Log.w(MiCamera2.TAG, "onImageAvailable: NO depth image processor!");
                        acquireNextImage.close();
                        return;
                    }
                    MiCamera2.this.mMiCamera2Shot.onImageReceived(acquireNextImage, 2);
                }
            }
        };
        this.mDepthReader = ImageReader.newInstance(cameraSize.getWidth() / 2, cameraSize.getHeight() / 2, 540422489, 2);
        this.mDepthReader.setOnImageAvailableListener(anonymousClass6, this.mCameraHandler);
    }

    private void preparePortraitRawImageReader(CameraSize cameraSize) {
        if (this.mPortraitRawImageReader != null) {
            this.mPortraitRawImageReader.close();
        }
        AnonymousClass7 anonymousClass7 = new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    if (MiCamera2.this.mMiCamera2Shot == null) {
                        Log.w(MiCamera2.TAG, "onImageAvailable: NO portrait image processor!");
                        acquireNextImage.close();
                        return;
                    }
                    MiCamera2.this.mMiCamera2Shot.onImageReceived(acquireNextImage, 1);
                }
            }
        };
        this.mPortraitRawImageReader = ImageReader.newInstance(cameraSize.getWidth(), cameraSize.getHeight(), 256, 2);
        this.mPortraitRawImageReader.setOnImageAvailableListener(anonymousClass7, this.mCameraHandler);
    }

    public void close() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("E: close: cameraId = ");
        stringBuilder.append(getId());
        Log.d(str, stringBuilder.toString());
        abortCaptures();
        if (this.mCameraDevice != null) {
            if (DataRepository.dataItemFeature().fV()) {
                try {
                    ((CameraDeviceImpl) this.mCameraDevice).flush();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            this.mCameraDevice.close();
        }
        if (this.mPhotoImageReader != null) {
            this.mPhotoImageReader.close();
        }
        if (this.mPreviewImageReader != null) {
            this.mPreviewImageReader.close();
        }
        if (this.mRawImageReader != null) {
            this.mRawImageReader.close();
        }
        if (this.mVideoSnapshotImageReader != null) {
            this.mVideoSnapshotImageReader.close();
        }
        if (this.mMiCamera2Shot != null) {
            this.mMiCamera2Shot.makeClobber();
            this.mMiCamera2Shot = null;
        }
        reset();
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("X: close: cameraId = ");
        stringBuilder.append(getId());
        Log.d(str, stringBuilder.toString());
    }

    private void reset() {
        Log.v(TAG, "E: reset");
        this.mCameraDevice = null;
        this.mPreviewSurface = null;
        this.mRecordSurface = null;
        this.mCaptureSession = null;
        this.mSessionId = 0;
        this.mPhotoImageReader = null;
        this.mRawImageReader = null;
        this.mPreviewImageReader = null;
        this.mVideoSnapshotImageReader = null;
        this.mDepthReader = null;
        this.mPortraitRawImageReader = null;
        releaseCameraPreviewCallback(null);
        Log.v(TAG, "X: reset");
    }

    private List<Surface> prepareRemoteImageReader() {
        IImageReaderParameterSets iImageReaderParameterSets;
        ArrayList<IImageReaderParameterSets> arrayList = new ArrayList();
        arrayList.add(new IImageReaderParameterSets(this.mConfigs.getPhotoSize().getWidth(), this.mConfigs.getPhotoSize().getHeight(), 35, 10, 0));
        if (this.mConfigs.getShotType() == 6) {
            arrayList.add(new IImageReaderParameterSets(this.mConfigs.getSubPhotoSize().getWidth(), this.mConfigs.getSubPhotoSize().getHeight(), 35, 10, 1));
        } else if (isQcfaEnable()) {
            int width = this.mConfigs.getPhotoSize().getWidth() / 2;
            int height = this.mConfigs.getPhotoSize().getHeight() / 2;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[QCFA] prepareRemoteImageReader: ");
            stringBuilder.append(width);
            stringBuilder.append("x");
            stringBuilder.append(height);
            Log.d(str, stringBuilder.toString());
            iImageReaderParameterSets = new IImageReaderParameterSets(width, height, 35, 10, 0);
            iImageReaderParameterSets.setShouldHoldImages(false);
            arrayList.add(iImageReaderParameterSets);
        }
        LocalBinder localBinder = AlgoConnector.getInstance().getLocalBinder();
        if (localBinder == null) {
            Log.d(TAG, "prepareRemoteImageReader: ParallelService is not ready");
            ArrayList arrayList2 = new ArrayList();
            for (IImageReaderParameterSets iImageReaderParameterSets2 : arrayList) {
                ImageReader newInstance = ImageReader.newInstance(iImageReaderParameterSets2.width, iImageReaderParameterSets2.height, iImageReaderParameterSets2.format, iImageReaderParameterSets2.maxImages);
                arrayList2.add(newInstance.getSurface());
                this.mRemoteImageReaderList.add(newInstance);
            }
            return arrayList2;
        }
        try {
            List configCaptureOutputBuffer = localBinder.configCaptureOutputBuffer(arrayList);
            if (configCaptureOutputBuffer != null) {
                return configCaptureOutputBuffer;
            }
            throw new RemoteException("Config capture output buffer failed!");
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isLocalParallelServiceReady() {
        return (this.mEnableParallelSession && AlgoConnector.getInstance().getLocalBinder() == null) ? false : true;
    }

    /* JADX WARNING: Missing block: B:47:0x00df, code skipped:
            return false;
     */
    public boolean updateDeferPreviewSession(android.view.Surface r6) {
        /*
        r5 = this;
        r0 = r5.mParallelSessionLock;
        monitor-enter(r0);
        r1 = r5.mPreviewSurface;	 Catch:{ all -> 0x00e3 }
        if (r1 != 0) goto L_0x0009;
    L_0x0007:
        r5.mPreviewSurface = r6;	 Catch:{ all -> 0x00e3 }
    L_0x0009:
        r6 = r5.mDeferOutputConfigurations;	 Catch:{ all -> 0x00e3 }
        r6 = r6.isEmpty();	 Catch:{ all -> 0x00e3 }
        if (r6 != 0) goto L_0x00e0;
    L_0x0011:
        r6 = r5.mCaptureSession;	 Catch:{ all -> 0x00e3 }
        r1 = 0;
        if (r6 == 0) goto L_0x00de;
    L_0x0016:
        r6 = r5.mPreviewSurface;	 Catch:{ all -> 0x00e3 }
        if (r6 == 0) goto L_0x00de;
    L_0x001a:
        r6 = r5.isLocalParallelServiceReady();	 Catch:{ all -> 0x00e3 }
        if (r6 != 0) goto L_0x0036;
    L_0x0020:
        r6 = TAG;	 Catch:{ all -> 0x00e3 }
        r2 = "updateDeferPreviewSession: ParallelService is not ready";
        com.android.camera.log.Log.d(r6, r2);	 Catch:{ all -> 0x00e3 }
        r6 = r5.mHelperHandler;	 Catch:{ all -> 0x00e3 }
        r2 = 2;
        r6.removeMessages(r2);	 Catch:{ all -> 0x00e3 }
        r6 = r5.mHelperHandler;	 Catch:{ all -> 0x00e3 }
        r3 = 10;
        r6.sendEmptyMessageDelayed(r2, r3);	 Catch:{ all -> 0x00e3 }
        monitor-exit(r0);	 Catch:{ all -> 0x00e3 }
        return r1;
    L_0x0036:
        r6 = r5.mPreviewRequestBuilder;	 Catch:{ all -> 0x00e3 }
        r2 = r5.mPreviewSurface;	 Catch:{ all -> 0x00e3 }
        r6.addTarget(r2);	 Catch:{ all -> 0x00e3 }
        r6 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00ad }
        r6.<init>();	 Catch:{ Exception -> 0x00ad }
        r2 = r5.mFakeOutputTexture;	 Catch:{ Exception -> 0x00ad }
        if (r2 == 0) goto L_0x0068;
    L_0x0046:
        r2 = r5.mDeferOutputConfigurations;	 Catch:{ Exception -> 0x00ad }
        r2 = r2.get(r1);	 Catch:{ Exception -> 0x00ad }
        r2 = (android.hardware.camera2.params.OutputConfiguration) r2;	 Catch:{ Exception -> 0x00ad }
        r3 = r5.mDeferOutputConfigurations;	 Catch:{ Exception -> 0x00ad }
        r3.remove(r1);	 Catch:{ Exception -> 0x00ad }
        r3 = r5.mSetRepeatingEarly;	 Catch:{ Exception -> 0x00ad }
        if (r3 == 0) goto L_0x0060;
    L_0x0057:
        r3 = r5.mPreviewRequestBuilder;	 Catch:{ Exception -> 0x00ad }
        r4 = r2.getSurface();	 Catch:{ Exception -> 0x00ad }
        r3.removeTarget(r4);	 Catch:{ Exception -> 0x00ad }
    L_0x0060:
        r3 = r5.mPreviewSurface;	 Catch:{ Exception -> 0x00ad }
        r2.addSurface(r3);	 Catch:{ Exception -> 0x00ad }
        r6.add(r2);	 Catch:{ Exception -> 0x00ad }
    L_0x0068:
        r2 = r5.mEnableParallelSession;	 Catch:{ Exception -> 0x00ad }
        if (r2 == 0) goto L_0x00a0;
    L_0x006c:
        r2 = r5.mRemoteImageReaderList;	 Catch:{ Exception -> 0x00ad }
        r2 = r2.isEmpty();	 Catch:{ Exception -> 0x00ad }
        if (r2 != 0) goto L_0x00a0;
    L_0x0074:
        r2 = r5.prepareRemoteImageReader();	 Catch:{ Exception -> 0x00ad }
        r5.mParallelCaptureSurfaceList = r2;	 Catch:{ Exception -> 0x00ad }
        r2 = r5.mParallelCaptureSurfaceList;	 Catch:{ Exception -> 0x00ad }
        if (r2 == 0) goto L_0x00a0;
    L_0x007e:
        r2 = r1;
    L_0x007f:
        r3 = r5.mDeferOutputConfigurations;	 Catch:{ Exception -> 0x00ad }
        r3 = r3.size();	 Catch:{ Exception -> 0x00ad }
        if (r2 >= r3) goto L_0x00a0;
    L_0x0087:
        r3 = r5.mDeferOutputConfigurations;	 Catch:{ Exception -> 0x00ad }
        r3 = r3.get(r2);	 Catch:{ Exception -> 0x00ad }
        r3 = (android.hardware.camera2.params.OutputConfiguration) r3;	 Catch:{ Exception -> 0x00ad }
        r4 = r5.mParallelCaptureSurfaceList;	 Catch:{ Exception -> 0x00ad }
        r4 = r4.get(r2);	 Catch:{ Exception -> 0x00ad }
        r4 = (android.view.Surface) r4;	 Catch:{ Exception -> 0x00ad }
        r3.addSurface(r4);	 Catch:{ Exception -> 0x00ad }
        r6.add(r3);	 Catch:{ Exception -> 0x00ad }
        r2 = r2 + 1;
        goto L_0x007f;
    L_0x00a0:
        r2 = r5.mCaptureSession;	 Catch:{ Exception -> 0x00ad }
        r2.finalizeOutputConfigurations(r6);	 Catch:{ Exception -> 0x00ad }
        r6 = TAG;	 Catch:{ Exception -> 0x00ad }
        r2 = "updateDeferPreviewSession: finalizeOutputConfigurations success";
        com.android.camera.log.Log.d(r6, r2);	 Catch:{ Exception -> 0x00ad }
        goto L_0x00b5;
    L_0x00ad:
        r6 = move-exception;
        r2 = TAG;	 Catch:{ all -> 0x00e3 }
        r3 = "updateDeferPreviewSession: finalizeOutputConfigurations failed";
        com.android.camera.log.Log.e(r2, r3, r6);	 Catch:{ all -> 0x00e3 }
    L_0x00b5:
        r6 = r5.mRemoteImageReaderList;	 Catch:{ all -> 0x00e3 }
        r6 = r6.iterator();	 Catch:{ all -> 0x00e3 }
    L_0x00bb:
        r2 = r6.hasNext();	 Catch:{ all -> 0x00e3 }
        if (r2 == 0) goto L_0x00cb;
    L_0x00c1:
        r2 = r6.next();	 Catch:{ all -> 0x00e3 }
        r2 = (android.media.ImageReader) r2;	 Catch:{ all -> 0x00e3 }
        r2.close();	 Catch:{ all -> 0x00e3 }
        goto L_0x00bb;
    L_0x00cb:
        r6 = r5.mRemoteImageReaderList;	 Catch:{ all -> 0x00e3 }
        r6.clear();	 Catch:{ all -> 0x00e3 }
        r6 = r5.mDeferOutputConfigurations;	 Catch:{ all -> 0x00e3 }
        r6.clear();	 Catch:{ all -> 0x00e3 }
        r6 = r5.mCaptureSessionStateCallback;	 Catch:{ all -> 0x00e3 }
        if (r6 == 0) goto L_0x00de;
    L_0x00d9:
        r6 = r5.mCaptureSessionStateCallback;	 Catch:{ all -> 0x00e3 }
        r6.onPreviewSessionSuccess();	 Catch:{ all -> 0x00e3 }
    L_0x00de:
        monitor-exit(r0);	 Catch:{ all -> 0x00e3 }
        return r1;
    L_0x00e0:
        monitor-exit(r0);	 Catch:{ all -> 0x00e3 }
        r6 = 1;
        return r6;
    L_0x00e3:
        r6 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00e3 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera2.MiCamera2.updateDeferPreviewSession(android.view.Surface):boolean");
    }

    public void startPreviewSession(Surface surface, boolean z, boolean z2, int i, boolean z3, CameraPreviewCallback cameraPreviewCallback) {
        startPreviewSession(surface, z, z2, i, z3, cameraPreviewCallback, null);
    }

    public void startPreviewSession(Surface surface, boolean z, boolean z2, int i, boolean z3, CameraPreviewCallback cameraPreviewCallback, Handler handler) {
        boolean z4 = z;
        boolean z5 = z3;
        if (checkCameraDevice("startPreviewSession")) {
            Log.d(TAG, String.format(Locale.ENGLISH, "startPreviewSession: opMode=0x%x previewCallback=%b rawCallback=%b", new Object[]{Integer.valueOf(i), Boolean.valueOf(z), Boolean.valueOf(z2)}));
            synchronized (this.mParallelSessionLock) {
                this.mEnableParallelSession = z5;
                this.mPreviewSurface = surface;
                this.mIsPreviewCallbackEnabled = z4;
                this.mSessionId = genSessionId();
                this.mDeferOutputConfigurations.clear();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (z5) {
                    this.mParallelCaptureSurfaceList = prepareRemoteImageReader();
                    if (isLocalParallelServiceReady()) {
                        arrayList.addAll(this.mParallelCaptureSurfaceList);
                    } else {
                        for (Surface outputConfiguration : this.mParallelCaptureSurfaceList) {
                            OutputConfiguration outputConfiguration2 = new OutputConfiguration(outputConfiguration);
                            outputConfiguration2.enableSurfaceSharing();
                            this.mDeferOutputConfigurations.add(outputConfiguration2);
                        }
                    }
                } else {
                    try {
                        preparePhotoImageReader();
                        arrayList.add(this.mPhotoImageReader.getSurface());
                        if (this.mConfigs.getShotType() == 2 || this.mConfigs.getShotType() == -3) {
                            prepareDepthImageReader(this.mConfigs.getPhotoSize());
                            arrayList.add(this.mDepthReader.getSurface());
                            preparePortraitRawImageReader(this.mConfigs.getPhotoSize());
                            arrayList.add(this.mPortraitRawImageReader.getSurface());
                        }
                    } catch (CameraAccessException e) {
                        Log.e(TAG, "Failed to start preview session", e);
                        notifyOnError(e.getReason());
                    } catch (IllegalStateException e2) {
                        Log.e(TAG, "Failed to start preview session, IllegalState", e2);
                        notifyOnError(256);
                    } catch (IllegalArgumentException e3) {
                        Log.e(TAG, "Failed to start preview session, IllegalArgument", e3);
                        notifyOnError(256);
                    }
                }
                this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(1);
                this.mCaptureSession = null;
                if (z4) {
                    preparePreviewImageReader(this.mConfigs.getPreviewSize(), this.mConfigs.getPreviewFormat(), this.mConfigs.getPreviewMaxImages(), handler);
                    arrayList.add(this.mPreviewImageReader.getSurface());
                }
                if (z2) {
                    prepareRawImageReader(this.mConfigs.getPhotoSize());
                    arrayList.add(this.mRawImageReader.getSurface());
                }
                this.mCaptureSessionStateCallback = new CaptureSessionStateCallback(this.mSessionId, cameraPreviewCallback);
                if (this.mPreviewSurface == null) {
                    Object outputConfiguration3;
                    CameraSize previewSize = getPreviewSize();
                    this.mFakeOutputTexture = new SurfaceTexture(false);
                    if (this.mSetRepeatingEarly) {
                        this.mFakeOutputTexture.setDefaultBufferSize(previewSize.width, previewSize.height);
                        Surface surface2 = new Surface(this.mFakeOutputTexture);
                        outputConfiguration3 = new OutputConfiguration(surface2);
                        outputConfiguration3.enableSurfaceSharing();
                        this.mPreviewRequestBuilder.addTarget(surface2);
                    } else {
                        outputConfiguration3 = new OutputConfiguration(new Size(previewSize.width, previewSize.height), SurfaceHolder.class);
                    }
                    this.mDeferOutputConfigurations.add(0, outputConfiguration3);
                } else {
                    arrayList2.add(new OutputConfiguration(this.mPreviewSurface));
                    this.mPreviewRequestBuilder.addTarget(this.mPreviewSurface);
                }
                for (OutputConfiguration outputConfiguration4 : this.mDeferOutputConfigurations) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("add surface from deferOutputConfig: ");
                    stringBuilder.append(outputConfiguration4.getSurface());
                    Log.d(str, stringBuilder.toString());
                    arrayList2.add(outputConfiguration4);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Surface surface3 = (Surface) it.next();
                    String str2 = TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("startPreviewSession: add surface to configurations: ");
                    stringBuilder2.append(surface3);
                    Log.d(str2, stringBuilder2.toString());
                    arrayList2.add(new OutputConfiguration(surface3));
                }
                Log.d(TAG, String.format(Locale.ENGLISH, "startPreviewSession: operationMode = 0x%x", new Object[]{Integer.valueOf(i)}));
                this.mCameraDevice.createCustomCaptureSession(null, arrayList2, i, this.mCaptureSessionStateCallback, this.mCameraHandler);
            }
        }
    }

    public void startRecordSession(@NonNull Surface surface, @NonNull Surface surface2, boolean z, int i, CameraPreviewCallback cameraPreviewCallback) {
        if (checkCameraDevice("startRecordSession")) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startRecordSession: previewSurface=");
            stringBuilder.append(surface);
            stringBuilder.append(" recordSurface=");
            stringBuilder.append(surface2);
            Log.d(str, stringBuilder.toString());
            this.mPreviewSurface = surface;
            this.mRecordSurface = surface2;
            this.mSessionId = genSessionId();
            this.mVideoSessionId = this.mSessionId;
            try {
                this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(3);
                this.mPreviewRequestBuilder.addTarget(this.mPreviewSurface);
                if (this.mConfigs.isEISEnabled()) {
                    this.mPreviewRequestBuilder.set(MiCaptureRequest.VIDEO_RECORD_CONTROL, Integer.valueOf(0));
                }
                this.mCaptureSession = null;
                List asList;
                if (z) {
                    prepareVideoSnapshotImageReader(this.mConfigs.getVideoSnapshotSize());
                    asList = Arrays.asList(new Surface[]{this.mPreviewSurface, this.mRecordSurface, this.mVideoSnapshotImageReader.getSurface()});
                } else {
                    asList = Arrays.asList(new Surface[]{this.mPreviewSurface, this.mRecordSurface});
                }
                ArrayList arrayList = new ArrayList(asList.size());
                for (Surface surface22 : asList) {
                    arrayList.add(new OutputConfiguration(surface22));
                }
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("startRecordSession operationMode is ");
                stringBuilder2.append(Integer.toHexString(i));
                Log.d(str2, stringBuilder2.toString());
                this.mCameraDevice.createCustomCaptureSession(null, arrayList, i, new CaptureSessionStateCallback(this.mSessionId, cameraPreviewCallback), this.mCameraHandler);
            } catch (CameraAccessException e) {
                Log.e(TAG, "Failed to start recording session", e);
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to start recording session, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    public void startHighSpeedRecordSession(@NonNull Surface surface, @NonNull Surface surface2, Range<Integer> range, CameraPreviewCallback cameraPreviewCallback) {
        if (checkCameraDevice("startHighSpeedRecordSession")) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startHighSpeedRecordSession: previewSurface=");
            stringBuilder.append(surface);
            stringBuilder.append(" recordSurface=");
            stringBuilder.append(surface2);
            Log.d(str, stringBuilder.toString());
            this.mPreviewSurface = surface;
            this.mRecordSurface = surface2;
            this.mHighSpeedFpsRange = range;
            this.mSessionId = genSessionId();
            try {
                this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(3);
                this.mPreviewRequestBuilder.addTarget(this.mPreviewSurface);
                this.mPreviewRequestBuilder.addTarget(this.mRecordSurface);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.mHighSpeedFpsRange);
                this.mCaptureSession = null;
                List<Surface> asList = Arrays.asList(new Surface[]{this.mPreviewSurface, this.mRecordSurface});
                if (((Integer) this.mHighSpeedFpsRange.getUpper()).intValue() != 120 || DataRepository.dataItemFeature().fN()) {
                    if (DataRepository.dataItemFeature().fZ()) {
                        CompatibilityUtils.createCaptureSessionWithSessionConfiguration(this.mCameraDevice, asList, this.mPreviewRequestBuilder.build(), new HighSpeedCaptureSessionStateCallback(this.mSessionId, cameraPreviewCallback), this.mCameraHandler);
                    } else {
                        this.mCameraDevice.createConstrainedHighSpeedCaptureSession(asList, new HighSpeedCaptureSessionStateCallback(this.mSessionId, cameraPreviewCallback), this.mCameraHandler);
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (Surface surface22 : asList) {
                    arrayList.add(new OutputConfiguration(surface22));
                }
                this.mCameraDevice.createCustomCaptureSession(null, arrayList, 32888, new HighSpeedCaptureSessionStateCallback(this.mSessionId, cameraPreviewCallback), this.mCameraHandler);
            } catch (Exception e) {
                notifyOnError(-1);
                Log.e(TAG, "Failed to start high speed record session", e);
            }
        }
    }

    public void notifyVideoStreamEnd() {
        if (this.mCaptureSession == null) {
            Log.w(TAG, "notifyVideoStreamEnd: session is not ready");
            this.mPendingNotifyVideoEnd = true;
            return;
        }
        try {
            this.mCaptureSession.stopRepeating();
            Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(3);
            createCaptureRequest.addTarget(this.mRecordSurface);
            applySettingsForVideo(createCaptureRequest);
            MiCameraCompat.applyVideoStreamState(createCaptureRequest, false);
            int capture = capture(createCaptureRequest.build(), this.mCaptureCallback, this.mCameraHandler);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("notifyVideoStreamEnd: requestId=");
            stringBuilder.append(capture);
            Log.v(str, stringBuilder.toString());
        } catch (CameraAccessException e) {
            Log.e(TAG, e.getMessage(), e);
            notifyOnError(e.getReason());
        }
    }

    public void startRecording() {
        if (checkCaptureSession("startRecording")) {
            try {
                Log.d(TAG, "E: startRecording");
                if (this.mConfigs.isEISEnabled()) {
                    setVideoRecordControl(1);
                }
                this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(3);
                this.mPreviewRequestBuilder.addTarget(this.mPreviewSurface);
                this.mPreviewRequestBuilder.addTarget(this.mRecordSurface);
                applySettingsForVideo(this.mPreviewRequestBuilder);
                resumePreview();
                Log.d(TAG, "X: startRecording");
            } catch (CameraAccessException e) {
                Log.e(TAG, "Failed to start recording", e);
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to start recording, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    public void stopRecording(VideoRecordStateCallback videoRecordStateCallback) {
        if (checkCaptureSession("stopRecording")) {
            Log.d(TAG, "stopRecording");
            if (this.mConfigs.isEISEnabled()) {
                synchronized (this.mVideoRecordStateLock) {
                    this.mVideoRecordStateCallback = videoRecordStateCallback;
                }
                try {
                    setVideoRecordControl(2);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Failed to stop recording");
                    notifyOnError(e.getReason());
                } catch (IllegalStateException e2) {
                    Log.e(TAG, "Failed to stop recording, IllegalState", e2);
                    notifyOnError(256);
                }
            }
        }
    }

    private void setVideoRecordControl(int i) throws CameraAccessException {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoRecordControl: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(3);
        if (1 == i) {
            createCaptureRequest.addTarget(this.mPreviewSurface);
        } else {
            createCaptureRequest.addTarget(this.mRecordSurface);
        }
        applySettingsForVideo(createCaptureRequest);
        createCaptureRequest.set(MiCaptureRequest.VIDEO_RECORD_CONTROL, Integer.valueOf(i));
        capture(createCaptureRequest.build(), this.mCaptureCallback, this.mCameraHandler);
    }

    public void startHighSpeedRecording() {
        if (checkCaptureSession("startHighSpeedRecording")) {
            Log.d(TAG, "startHighSpeedRecording");
            MiCameraCompat.applyIsHfrPreview(this.mPreviewRequestBuilder, false);
            resumePreview();
        }
    }

    public void startRecordPreview() {
        if (checkCameraDevice("startRecordPreview")) {
            Log.d(TAG, "startRecordPreview");
            synchronized (this.mVideoRecordStateLock) {
                this.mVideoRecordStateCallback = null;
            }
            try {
                this.mPreviewRequestBuilder = this.mCameraDevice.createCaptureRequest(3);
                this.mPreviewRequestBuilder.addTarget(this.mPreviewSurface);
                if (this.mConfigs.isEISEnabled()) {
                    this.mPreviewRequestBuilder.set(MiCaptureRequest.VIDEO_RECORD_CONTROL, Integer.valueOf(0));
                }
                applySettingsForVideo(this.mPreviewRequestBuilder);
                resumePreview();
            } catch (CameraAccessException e) {
                Log.e(TAG, "Failed to start record preview", e);
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to start record preview, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    public void startHighSpeedRecordPreview() {
        if (checkCameraDevice("startHighSpeedRecordPreview")) {
            Log.d(TAG, "startHighSpeedRecordPreview");
            applySettingsForVideo(this.mPreviewRequestBuilder);
            MiCameraCompat.applyIsHfrPreview(this.mPreviewRequestBuilder, true);
            resumePreview();
        }
    }

    private void cancelSession() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cancelSession: id=");
        stringBuilder.append(getId());
        Log.d(str, stringBuilder.toString());
        try {
            this.mSessionId = genSessionId();
            if (this.mCaptureSession != null) {
                this.mCaptureSession.stopRepeating();
                abortCaptures();
                this.mCaptureSession.close();
                this.mCaptureSession = null;
            }
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to stop repeating session", e);
            notifyOnError(e.getReason());
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to stop repeating, IllegalState", e2);
            notifyOnError(256);
        }
    }

    private void abortCaptures() {
        if (this.mCaptureSession != null && DataRepository.dataItemFeature().ge()) {
            try {
                Log.d(TAG, "abortCaptures E");
                this.mCaptureSession.abortCaptures();
                Log.d(TAG, "abortCaptures X");
            } catch (CameraAccessException e) {
                Log.e(TAG, "abortCaptures(): failed", e);
            } catch (IllegalStateException e2) {
                Log.e(TAG, "abortCaptures, IllegalState", e2);
            }
        }
    }

    public void resumePreview() {
        if (checkCaptureSession("resumePreview")) {
            boolean z = this.mCaptureSession instanceof CameraConstrainedHighSpeedCaptureSession;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("resumePreview: cameraId=");
            stringBuilder.append(getId());
            stringBuilder.append(" highSpeed=");
            stringBuilder.append(z);
            Log.v(str, stringBuilder.toString());
            try {
                this.mPreviewRequest = this.mPreviewRequestBuilder.build();
                if (z) {
                    this.mCaptureSession.setRepeatingBurst(createHighSpeedRequestList(this.mPreviewRequest), this.mCaptureCallback, this.mCameraHandler);
                } else {
                    int repeatingRequest = this.mCaptureSession.setRepeatingRequest(this.mPreviewRequest, this.mCaptureCallback, this.mCameraHandler);
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("repeating sequenceId: ");
                    stringBuilder.append(repeatingRequest);
                    Log.d(str, stringBuilder.toString());
                }
            } catch (CameraAccessException e) {
                Log.e(TAG, "Failed to resume preview", e);
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to resume preview, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    public void forceTurnFlashOffAndPausePreview() {
        this.mConfigs.setFlashMode(0);
        applyFlashMode(this.mPreviewRequestBuilder, 1);
        resumePreview();
        this.mCaptureCallback.setState(10);
    }

    public void updateFrameNumber(long j) {
        this.mCurrentFrameNum = j;
    }

    public void pausePreview() {
        if (checkCaptureSession("pausePreview")) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pausePreview: cameraId=");
            stringBuilder.append(getId());
            Log.v(str, stringBuilder.toString());
            try {
                this.mCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to pause preview");
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to pause preview, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    public void releasePreview() {
        if (this.mCaptureSession != null) {
            if (this.mHelperHandler != null) {
                this.mHelperHandler.removeCallbacksAndMessages(null);
                this.mHelperHandler = null;
            }
            try {
                Log.v(TAG, "E: releasePreview");
                this.mCaptureSession.stopRepeating();
                abortCaptures();
                this.mCaptureSession.close();
                this.mCaptureSession = null;
                Log.v(TAG, "X: releasePreview");
            } catch (CameraAccessException e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to release preview");
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to release preview, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    public void resetConfigs() {
        Log.v(TAG, "E: resetConfigs");
        if (this.mConfigs != null) {
            this.mConfigs = new CameraConfigs();
        }
        this.mSessionId = 0;
        releaseCameraPreviewCallback(null);
        Log.v(TAG, "X: resetConfigs");
    }

    public void startPreviewCallback(@NonNull PreviewCallback previewCallback) {
        if (checkCaptureSession("startPreviewCallback")) {
            Log.v(TAG, "startPreviewCallback");
            if (this.mIsPreviewCallbackEnabled) {
                setPreviewCallback(previewCallback);
                if (!this.mIsPreviewCallbackStarted) {
                    this.mIsPreviewCallbackStarted = true;
                    this.mPreviewRequestBuilder.addTarget(this.mPreviewImageReader.getSurface());
                }
            }
        }
    }

    public void releaseCameraPreviewCallback(@Nullable CameraPreviewCallback cameraPreviewCallback) {
        if (this.mCaptureSessionStateCallback != null) {
            this.mCaptureSessionStateCallback.setClientCb(cameraPreviewCallback);
        }
    }

    public void releasePictureCallback() {
        setPictureCallback(null);
    }

    public void stopPreviewCallback(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stopPreviewCallback(): isRelease = ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        if (this.mIsPreviewCallbackEnabled && this.mIsPreviewCallbackStarted && this.mPreviewImageReader != null) {
            this.mIsPreviewCallbackStarted = false;
            setPreviewCallback(null);
            Surface surface = this.mPreviewImageReader.getSurface();
            this.mPreviewRequestBuilder.removeTarget(surface);
            surface.release();
            if (!z && checkCaptureSession("stopPreviewCallback")) {
                resumePreview();
            }
        }
    }

    public void setShotType(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setShotType: algo=");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        this.mConfigs.setShotType(i);
    }

    public void setShotSavePath(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setShotSavePath: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        this.mConfigs.setShotPath(str);
    }

    public String getShotSavePath() {
        return this.mConfigs.getShotPath();
    }

    public void takePicture(@NonNull PictureCallback pictureCallback, @NonNull ParallelCallback parallelCallback) {
        Log.v(TAG, "takePicture");
        setPictureCallback(pictureCallback);
        setParallelCallback(parallelCallback);
        triggerCapture();
    }

    public void captureGroupShotPictures(@NonNull PictureCallback pictureCallback, @NonNull ParallelCallback parallelCallback, int i, Context context) {
        setPictureCallback(pictureCallback);
        setParallelCallback(parallelCallback);
        this.mMiCamera2Shot = new MiCamera2ShotGroup(this, i, context, this.mCaptureCallback.getPreviewCaptureResult());
        this.mMiCamera2Shot.startShot();
    }

    public void captureBurstPictures(int i, @NonNull PictureCallback pictureCallback, @NonNull ParallelCallback parallelCallback) {
        setPictureCallback(pictureCallback);
        setParallelCallback(parallelCallback);
        if (this.mConfigs.getShotType() == 9) {
            this.mMiCamera2Shot = new MiCamera2ShotParallelRepeating(this, i);
            this.mMiCamera2Shot.startShot();
            return;
        }
        this.mMiCamera2Shot = new MiCamera2ShotBurst(this, i);
        this.mMiCamera2Shot.startShot();
    }

    public void captureAbortBurst() {
        Log.d(TAG, "captureAbortBurst");
        try {
            this.mCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            e.printStackTrace();
            notifyOnError(e.getReason());
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to abort burst, IllegalState", e2);
            notifyOnError(256);
        }
    }

    private void setAFModeToPreview(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAFModeToPreview: focusMode=");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(i));
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        CaptureRequestBuilder.applyAFRegions(this.mPreviewRequestBuilder, this.mConfigs);
        CaptureRequestBuilder.applyAERegions(this.mPreviewRequestBuilder, this.mConfigs);
        resumePreview();
    }

    public void startFaceDetection() {
        Log.v(TAG, "startFaceDetection");
        this.mConfigs.setFaceDetectionEnabled(true);
        CaptureRequestBuilder.applyFaceDetection(this.mPreviewRequestBuilder, this.mConfigs);
    }

    public void stopFaceDetection() {
        Log.v(TAG, "stopFaceDetection");
        this.mConfigs.setFaceDetectionEnabled(false);
        CaptureRequestBuilder.applyFaceDetection(this.mPreviewRequestBuilder, this.mConfigs);
    }

    public void startObjectTrack(RectF rectF) {
    }

    public void stopObjectTrack() {
    }

    private Builder initFocusRequestBuilder(int i) throws CameraAccessException {
        if (i == 160) {
            throw new IllegalArgumentException("Module index is error!");
        } else if (i == 166) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("initFocusRequestBuilder: error caller for ");
            stringBuilder.append(i);
            Log.e(str, stringBuilder.toString());
            return null;
        } else {
            Builder createCaptureRequest;
            if (!(i == 162 || i == 172)) {
                switch (i) {
                    case 168:
                    case 169:
                    case 170:
                        break;
                    default:
                        createCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
                        break;
                }
            }
            createCaptureRequest = this.mCameraDevice.createCaptureRequest(3);
            createCaptureRequest.addTarget(this.mPreviewSurface);
            if (this.mCaptureSession instanceof CameraConstrainedHighSpeedCaptureSession) {
                createCaptureRequest.addTarget(this.mRecordSurface);
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.mHighSpeedFpsRange);
            }
            return createCaptureRequest;
        }
    }

    public boolean isSessionReady() {
        return this.mCaptureSession != null;
    }

    public boolean isPreviewReady() {
        return (this.mCaptureCallback.getPreviewCaptureResult() == null || this.mCaptureCallback.getState() == 0) ? false : true;
    }

    private void triggerDeviceChecking(boolean z) {
        if (b.qY && DataRepository.dataItemFeature().fY() && this.mHelperHandler != null) {
            Log.d(TAG, "triggerDeviceChecking");
            this.mHelperHandler.sendMessage(this.mHelperHandler.obtainMessage(3, z, 0));
        }
    }

    private boolean isDeviceAlive() {
        if (0 > this.mCurrentFrameNum || this.mCurrentFrameNum != this.mLastFrameNum) {
            this.mLastFrameNum = this.mCurrentFrameNum;
            return true;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("camera device maybe dead, current framenum is ");
        stringBuilder.append(this.mLastFrameNum);
        Log.e(str, stringBuilder.toString());
        return false;
    }

    public void setPreviewSize(CameraSize cameraSize) {
        if (!Objects.equals(this.mConfigs.getPreviewSize(), cameraSize)) {
            this.mConfigs.setPreviewSize(cameraSize);
            if (this.mIsPreviewCallbackEnabled) {
                preparePreviewImageReader();
            }
        }
    }

    public CameraSize getPreviewSize() {
        return this.mConfigs.getPreviewSize();
    }

    public void setPreviewFormat(int i) {
        if (i != this.mConfigs.getPreviewFormat()) {
            this.mConfigs.setPreviewFormat(i);
            if (this.mIsPreviewCallbackEnabled) {
                preparePreviewImageReader();
            }
        }
    }

    public int getPreviewFormat() {
        return this.mConfigs.getPreviewFormat();
    }

    public void setPreviewMaxImages(int i) {
        if (i > this.mConfigs.getPreviewMaxImages()) {
            this.mConfigs.setPreviewMaxImages(i);
            if (this.mIsPreviewCallbackEnabled) {
                preparePreviewImageReader();
            }
        }
    }

    public int getPreviewMaxImages() {
        return this.mConfigs.getPreviewMaxImages();
    }

    public void setPictureSize(CameraSize cameraSize) {
        if (!Objects.equals(this.mConfigs.getPhotoSize(), cameraSize)) {
            this.mConfigs.setPhotoSize(cameraSize);
            preparePhotoImageReader();
        }
    }

    public CameraSize getPictureSize() {
        return this.mConfigs.getPhotoSize();
    }

    public void setSubPictureSize(CameraSize cameraSize) {
        if (!Objects.equals(this.mConfigs.getSubPhotoSize(), cameraSize)) {
            this.mConfigs.setSubPhotoSize(cameraSize);
        }
    }

    public void setPictureFormat(int i) {
        if (this.mConfigs.getPhotoFormat() != i) {
            this.mConfigs.setPhotoFormat(i);
            preparePhotoImageReader();
        }
    }

    public Handler getCameraHandler() {
        return this.mCameraHandler;
    }

    public int getPictureFormat() {
        return this.mConfigs.getPhotoFormat();
    }

    public void setPictureMaxImages(int i) {
        if (i > this.mConfigs.getPhotoMaxImages()) {
            this.mConfigs.setPhotoMaxImages(i);
            preparePhotoImageReader();
        }
    }

    public int getPictureMaxImages() {
        return this.mConfigs.getPhotoMaxImages();
    }

    public void setJpegThumbnailSize(CameraSize cameraSize) {
        this.mConfigs.setThumbnailSize(cameraSize);
    }

    public void setVideoSnapshotSize(CameraSize cameraSize) {
        this.mConfigs.setVideoSnapshotSize(cameraSize);
    }

    public void setJpegQuality(int i) {
        this.mConfigs.setJpegQuality(i);
    }

    public void setJpegRotation(int i) {
        this.mConfigs.setJpegRotation(i);
    }

    public void setZoomRatio(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setZoomRatio ");
        stringBuilder.append(f);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setZoomRatio(f)) {
            CaptureRequestBuilder.applyZoomRatio(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public float getZoomRatio() {
        return this.mConfigs.getZoomRatio();
    }

    public void setEnableZsl(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setEnableZsl ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        this.mConfigs.setEnableZsl(z);
        CaptureRequestBuilder.applyZsl(this.mPreviewRequestBuilder, this.mConfigs);
    }

    public void setEnableOIS(boolean z) {
        if (this.mCapabilities.isSupportOIS()) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setEnableOIS ");
            stringBuilder.append(z);
            Log.v(str, stringBuilder.toString());
            this.mConfigs.setEnableOIS(z);
            CaptureRequestBuilder.applyAntiShake(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public boolean isFacingFront() {
        return this.mCapabilities.getFacing() == 0;
    }

    public void setColorEffect(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setColorEffect: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setColorEffect(i)) {
            CaptureRequestBuilder.applyColorEffect(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setSceneMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSceneMode: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setSceneMode(i)) {
            CaptureRequestBuilder.applySceneMode(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setNormalWideLDC(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setNormalWideLDC: ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setNormalWideLDCEnabled(z)) {
            CaptureRequestBuilder.applyNormalWideLDC(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setUltraWideLDC(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setUltraWideLDC: ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setUltraWideLDCEnabled(z)) {
            CaptureRequestBuilder.applyUltraWideLDC(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setContrast(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setContrast: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setContrastLevel(i)) {
            CaptureRequestBuilder.applyContrast(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setSaturation(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSaturation: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setSaturationLevel(i)) {
            CaptureRequestBuilder.applySaturation(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setSharpness(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSharpness: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setSharpnessLevel(i)) {
            CaptureRequestBuilder.applySharpness(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setExposureMeteringMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setExposureMeteringMode: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setExposureMeteringMode(i)) {
            CaptureRequestBuilder.applyExposureMeteringMode(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public int getSceneMode() {
        return this.mConfigs.getSceneMode();
    }

    public void setAntiBanding(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAntiBanding: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setAntiBanding(i)) {
            CaptureRequestBuilder.applyAntiBanding(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setOpticalZoomToTele(boolean z) {
        if (DataRepository.dataItemFeature().fs() && this.mCapabilities.isSupportFastZoomIn()) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setOpticalZoomToTele: toTele = ");
            stringBuilder.append(z);
            Log.d(str, stringBuilder.toString());
            this.mToTele = z;
        }
        MiCameraCompat.applyStFastZoomIn(this.mPreviewRequestBuilder, z);
    }

    public void setEnableEIS(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setEnableEIS: ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setEnableEIS(z)) {
            CaptureRequestBuilder.applyAntiShake(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setASD(boolean z) {
        if (this.mConfigs.setAiSceneDetectEnable(z)) {
            CaptureRequestBuilder.applyAiSceneDetectEnable(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setCameraAI30(boolean z) {
        if (this.mConfigs.setCameraAi30Enable(z)) {
            CaptureRequestBuilder.applyCameraAi30Enable(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setASDPeriod(int i) {
        if (this.mConfigs.setAiSceneDetectPeriod(i)) {
            CaptureRequestBuilder.applyAiSceneDetectPeriod(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setHHT(boolean z) {
        if (this.mConfigs.setHHTEnabled(z)) {
            CaptureRequestBuilder.applyHHT(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setHDR(boolean z) {
        if (this.mConfigs.setHDREnabled(z)) {
            CaptureRequestBuilder.applyHDR(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setHDRCheckerEnable(boolean z) {
        if (this.mConfigs.setHDRCheckerEnabled(z)) {
            CaptureRequestBuilder.applyHDRCheckerEnable(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setSuperResolution(boolean z) {
        if (this.mConfigs.setSuperResolutionEnabled(z)) {
            CaptureRequestBuilder.applySuperResolution(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setMfnr(boolean z) {
        if (this.mConfigs.setMfnrEnabled(z)) {
            CaptureRequestBuilder.applyHwMfnr(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setSwMfnr(boolean z) {
        if (this.mConfigs.setSwMfnrEnabled(z)) {
            CaptureRequestBuilder.applySwMfnr(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setBokeh(boolean z) {
        if (this.mConfigs.setBokehEnabled(z)) {
            CaptureRequestBuilder.applyBokeh(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public boolean isBokehEnabled() {
        return this.mConfigs.isBokehEnabled();
    }

    public void setLensDirtyDetect(boolean z) {
        if (this.mConfigs.setLensDirtyDetectEnabled(z)) {
            CaptureRequestBuilder.applyLensDirtyDetect(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setPortraitLighting(int i) {
        if (this.mConfigs.setPortraitLightingPattern(i)) {
            CaptureRequestBuilder.applyPortraitLighting(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public void setFaceAgeAnalyze(boolean z) {
        if (this.mConfigs.setFaceAgeAnalyzeEnabled(z)) {
            CaptureRequestBuilder.applyFaceAgeAnalyze(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setFaceScore(boolean z) {
        if (this.mConfigs.setFaceScoreEnabled(z)) {
            CaptureRequestBuilder.applyFaceScore(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setDualCamWaterMarkEnable(boolean z) {
        this.mConfigs.setDualCamWaterMarkEnable(z);
    }

    public void setTimeWaterMarkEnable(boolean z) {
        this.mConfigs.setTimeWaterMarkEnable(z);
    }

    public void setTimeWatermarkValue(String str) {
        this.mConfigs.setTimeWaterMarkValue(str);
    }

    public void setFaceWaterMarkEnable(boolean z) {
        this.mConfigs.setFaceWaterMarkEnable(z);
    }

    public void setFaceWaterMarkFormat(String str) {
        this.mConfigs.setFaceWaterMarkFormat(str);
    }

    public void setFpsRange(Range<Integer> range) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFpsRange: ");
        stringBuilder.append(range);
        Log.v(str, stringBuilder.toString());
        this.mConfigs.setPreviewFpsRange(range);
        CaptureRequestBuilder.applyFpsRange(this.mPreviewRequestBuilder, this.mConfigs);
    }

    public void setVideoFpsRange(Range<Integer> range) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoFpsRange: ");
        stringBuilder.append(range);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setVideoFpsRange(range)) {
            CaptureRequestBuilder.applyVideoFpsRange(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    private int genSessionId() {
        int i = this.mSessionId + 1;
        this.mSessionId = i;
        if (i == Integer.MAX_VALUE) {
            this.mSessionId = 0;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("generateSessionId: id=");
        stringBuilder.append(this.mSessionId);
        Log.v(str, stringBuilder.toString());
        return this.mSessionId;
    }

    public void setDisplayOrientation(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setDisplayOrientation: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        this.mDisplayOrientation = i;
    }

    public void setDeviceOrientation(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setDeviceOrientation: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setDeviceOrientation(i)) {
            CaptureRequestBuilder.applyDeviceOrientation(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public <T> void setVendorSetting(Key<T> key, T t) {
        if (this.mPreviewRequestBuilder != null) {
            this.mPreviewRequestBuilder.set(key, t);
        }
    }

    public void setExposureCompensation(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setExposureCompensation: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setExposureCompensationIndex(i)) {
            CaptureRequestBuilder.applyExposureCompensation(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
        }
    }

    public int getExposureCompensation() {
        return this.mConfigs.getExposureCompensationIndex();
    }

    public void setExposureTime(long j) {
        if (this.mConfigs.setExposureTime(j)) {
            CaptureRequestBuilder.applySceneMode(this.mPreviewRequestBuilder, this.mConfigs);
            CaptureRequestBuilder.applyExposureCompensation(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyIso(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyExposureTime(this.mPreviewRequestBuilder, 1, this.mConfigs);
        }
    }

    private long getExposureTime() {
        return this.mConfigs.getExposureTime();
    }

    public void setGpsLocation(Location location) {
        this.mConfigs.setGpsLocation(location);
    }

    public void setAERegions(MeteringRectangle[] meteringRectangleArr) {
        Log.v(TAG, "setAERegions");
        if (this.mConfigs.setAERegions(meteringRectangleArr)) {
            CaptureRequestBuilder.applyAERegions(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setAFRegions(MeteringRectangle[] meteringRectangleArr) {
        Log.v(TAG, "setAFRegions");
        if (this.mConfigs.setAFRegions(meteringRectangleArr)) {
            CaptureRequestBuilder.applyAFRegions(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setAELock(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAELock: ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setAELock(z)) {
            CaptureRequestBuilder.applyAELock(this.mPreviewRequestBuilder, z);
        }
    }

    public void setAWBLock(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAWBLock: ");
        stringBuilder.append(z);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setAWBLock(z)) {
            CaptureRequestBuilder.applyAWBLock(this.mPreviewRequestBuilder, z);
        }
    }

    public void setAWBMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAWBMode: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setAWBMode(i)) {
            CaptureRequestBuilder.applyAWBMode(this.mPreviewRequestBuilder, this.mConfigs.getAWBMode());
        }
    }

    public void setCustomAWB(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setCustomAWB: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setCustomAWB(i)) {
            CaptureRequestBuilder.applyCustomAWB(this.mPreviewRequestBuilder, this.mConfigs.getAwbCustomValue());
        }
    }

    public void setISO(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setISO: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setISO(i)) {
            CaptureRequestBuilder.applyExposureCompensation(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyIso(this.mPreviewRequestBuilder, 1, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyExposureTime(this.mPreviewRequestBuilder, 1, this.mConfigs);
        }
    }

    public void cancelContinuousShot() {
    }

    public void setBurstShotSpeed(int i) {
    }

    public void setFocusMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFocusMode: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setFocusMode(i)) {
            CaptureRequestBuilder.applyFocusMode(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public int getFocusMode() {
        return this.mConfigs.getFocusMode();
    }

    public void setFocusDistance(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFocusDistance: ");
        stringBuilder.append(f);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setFocusDistance(f)) {
            CaptureRequestBuilder.applyFocusDistance(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void setFlashMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFlashMode: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        if (this.mConfigs.setFlashMode(i)) {
            applyFlashMode(this.mPreviewRequestBuilder, 1);
        }
    }

    public void setOptimizedFlash(boolean z) {
        this.mConfigs.setOptimizedFlash(z);
    }

    public int getFlashMode() {
        return this.mConfigs.getFlashMode();
    }

    private void triggerCapture() {
        if (isNeedFlashOn()) {
            this.mConfigs.setNeedFlash(true);
            if (needOptimizedFlash()) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
                this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, Integer.valueOf(2));
                CaptureRequestBuilder.applyAELock(this.mPreviewRequestBuilder, false);
                if (this.mCapabilities.isSupportSnapShotTorch()) {
                    MiCameraCompat.applySnapshotTorch(this.mPreviewRequestBuilder, true);
                }
                resumePreview();
                this.mCaptureCallback.setState(9);
                return;
            } else if (needScreenLight()) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
                resumePreview();
                triggerPrecapture();
                return;
            } else {
                triggerPrecapture();
                return;
            }
        }
        this.mConfigs.setNeedFlash(false);
        captureStillPicture();
    }

    private void triggerPrecapture() {
        if (!this.mCapabilities.isAutoFocusSupported() || this.mConfigs.getFocusMode() == 0) {
            runPreCaptureSequence();
        } else {
            lockFocus();
        }
    }

    public boolean isNeedFlashOn() {
        boolean z = true;
        if (this.mConfigs.getFlashMode() == 1 || this.mConfigs.getFlashMode() == 101) {
            return true;
        }
        if (this.mConfigs.getFlashMode() != 3) {
            return false;
        }
        Integer currentAEState = this.mCaptureCallback.getCurrentAEState();
        if (currentAEState == null || currentAEState.intValue() != 4) {
            z = false;
        }
        return z;
    }

    public boolean isNeedPreviewThumbnail() {
        return !this.mConfigs.isHDREnabled() && (this.mConfigs.isMfnrEnabled() || this.mConfigs.isSwMfnrEnabled() || this.mConfigs.isSuperResolutionEnabled());
    }

    public void setBeautyValues(BeautyValues beautyValues) {
        this.mConfigs.setBeautyValues(beautyValues);
        CaptureRequestBuilder.applyBeautyValues(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
    }

    public void setAutoZoomMode(int i) {
        this.mConfigs.setAutoZoomMode(i);
        CaptureRequestBuilder.applyAutoZoomMode(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
    }

    public void setAutoZoomScaleOffset(float f) {
        this.mConfigs.setAutoZoomScaleOffset(f);
        CaptureRequestBuilder.applyAutoZoomScaleOffset(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
    }

    public void releaseFakeSurfaceIfNeed() {
        if (this.mFakeOutputTexture != null) {
            this.mFakeOutputTexture = null;
        }
    }

    public void setAutoZoomStopCapture(int i) {
        try {
            Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(3);
            createCaptureRequest.addTarget(this.mPreviewSurface);
            createCaptureRequest.addTarget(this.mRecordSurface);
            applySettingsForVideo(createCaptureRequest);
            createCaptureRequest.set(TAG.STOP.toCaptureRequestKey(), Integer.valueOf(i));
            capture(createCaptureRequest.build(), this.mCaptureCallback, this.mCameraHandler);
        } catch (CameraAccessException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void setAutoZoomStartCapture(float[] fArr) {
        try {
            Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(3);
            createCaptureRequest.addTarget(this.mPreviewSurface);
            createCaptureRequest.addTarget(this.mRecordSurface);
            applySettingsForVideo(createCaptureRequest);
            createCaptureRequest.set(TAG.START.toCaptureRequestKey(), fArr);
            capture(createCaptureRequest.build(), this.mCaptureCallback, this.mCameraHandler);
        } catch (CameraAccessException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void setEyeLight(int i) {
        if (this.mConfigs.setEyeLight(i)) {
            CaptureRequestBuilder.applyEyeLight(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setNeedPausePreview(boolean z) {
        this.mConfigs.setPausePreview(z);
    }

    public void setFrontMirror(boolean z) {
        this.mConfigs.setFrontMirror(z);
    }

    public void setFNumber(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFNumber ");
        stringBuilder.append(str);
        stringBuilder.append(" for ");
        stringBuilder.append(this.mPreviewRequestBuilder);
        Log.d(str2, stringBuilder.toString());
        this.mConfigs.setFNumber(str);
        if (Util.UI_DEBUG()) {
            CaptureRequestBuilder.applyFNumber(this.mPreviewRequestBuilder, this.mCapabilities, this.mConfigs);
        }
    }

    public void setQcfaEnable(boolean z) {
        this.mConfigs.setQcfaEnable(z);
    }

    public boolean isQcfaEnable() {
        return this.mConfigs.isQcfaEnable();
    }

    public void lockExposure(boolean z) {
        if (checkCaptureSession("lockExposure")) {
            this.mCaptureCallback.setState(4);
            if (z) {
                setAELock(true);
            }
            CaptureRequestBuilder.applyAELock(this.mPreviewRequestBuilder, true);
            resumePreview();
        }
    }

    public void unlockExposure() {
        if (checkCaptureSession("unlockExposure")) {
            this.mCaptureCallback.setState(1);
            setAELock(false);
            CaptureRequestBuilder.applyAELock(this.mPreviewRequestBuilder, false);
            resumePreview();
        }
    }

    private void runCaptureSequence() {
        this.mCaptureCallback.showAutoFocusFinish(true);
        if (getExposureTime() > 0) {
            waitFlashClosed();
            return;
        }
        this.mCaptureCallback.setState(7);
        captureStillPicture();
    }

    private void runPreCaptureSequence() {
        Log.v(TAG, "runPreCaptureSequence");
        try {
            Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
            createCaptureRequest.addTarget(this.mPreviewSurface);
            applySettingsForPreCapture(createCaptureRequest);
            CaptureRequest build = createCaptureRequest.build();
            this.mPreCaptureRequestHashCode = build.hashCode();
            this.mCaptureCallback.setState(5);
            capture(build, this.mCaptureCallback, this.mCameraHandler);
        } catch (CameraAccessException | IllegalStateException e) {
            e.printStackTrace();
            notifyOnError(-1);
        }
    }

    private void waitFlashClosed() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
        this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, Integer.valueOf(0));
        resumePreview();
        this.mCaptureCallback.setState(8);
    }

    private void captureStillPicture() {
        if (checkCaptureSession(CameraStat.CATEGORY_CAMERA)) {
            MiCamera2Shot miCamera2Shot = null;
            switch (this.mConfigs.getShotType()) {
                case -3:
                case -2:
                case 0:
                case 2:
                    miCamera2Shot = new MiCamera2ShotStill(this);
                    break;
                case 1:
                    miCamera2Shot = new MiCamera2ShotLive(this);
                    break;
                case 5:
                case 6:
                case 7:
                    miCamera2Shot = new MiCamera2ShotParallelStill(this, this.mCaptureCallback.getPreviewCaptureResult());
                    break;
                case 8:
                    miCamera2Shot = new MiCamera2ShotParallelBurst(this, this.mCaptureCallback.getPreviewCaptureResult());
                    break;
            }
            this.mMiCamera2Shot = miCamera2Shot;
            if (this.mMiCamera2Shot != null) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("startShot holder: ");
                stringBuilder.append(this.mMiCamera2Shot.hashCode());
                Log.d(str, stringBuilder.toString());
                this.mMiCamera2Shot.startShot();
                triggerDeviceChecking(true);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCapturePictureFinished(boolean z) {
        boolean z2 = this.mConfigs.isNeedFlash() || ModuleManager.isSuperNightScene();
        this.mConfigs.setNeedFlash(false);
        this.mCaptureCallback.setState(1);
        applySettingsForPreview(this.mPreviewRequestBuilder);
        if (z2) {
            resumePreview();
        }
        PictureCallback pictureCallback = getPictureCallback();
        if (!z && pictureCallback != null) {
            pictureCallback.onPictureTakenFinished(false);
        }
    }

    public void captureVideoSnapshot(PictureCallback pictureCallback) {
        setPictureCallback(pictureCallback);
        this.mMiCamera2Shot = new MiCamera2ShotVideo(this);
        this.mMiCamera2Shot.startShot();
    }

    private boolean needOptimizedFlash() {
        if (this.mConfigs.isNeedFlash() && (this.mConfigs.getFlashMode() == 1 || this.mConfigs.getFlashMode() == 3 || getExposureTime() > 0)) {
            return true;
        }
        return false;
    }

    private boolean needScreenLight() {
        return this.mConfigs.isNeedFlash() && this.mConfigs.getFlashMode() == 101;
    }

    public void startFocus(FocusTask focusTask, int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startFocus: ");
        stringBuilder.append(i);
        Log.v(str, stringBuilder.toString());
        try {
            this.mCaptureCallback.setFocusTask(focusTask);
            this.mCaptureCallback.setState(2);
            Builder initFocusRequestBuilder = initFocusRequestBuilder(i);
            initFocusRequestBuilder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
            applySettingsForFocusCapture(initFocusRequestBuilder);
            initFocusRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
            capture(initFocusRequestBuilder.build(), this.mCaptureCallback, this.mCameraHandler);
            this.mConfigs.setFocusMode(1);
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
            if (!(ModuleManager.isVideoModule() || ModuleManager.isFastMotionModule() || ModuleManager.isSlowMotionModule() || ModuleManager.isFunModule() || ModuleManager.isLiveModule())) {
                if (!ModuleManager.isVideoNewSlowMotion()) {
                    applySettingsForPreview(this.mPreviewRequestBuilder);
                    resumePreview();
                }
            }
            applySettingsForVideo(this.mPreviewRequestBuilder);
            resumePreview();
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "Failed to start focus");
            notifyOnError(e.getReason());
        }
    }

    private void lockFocus() {
        if (checkCaptureSession("lockFocus")) {
            Log.v(TAG, "lockFocus");
            try {
                Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
                createCaptureRequest.addTarget(this.mPreviewSurface);
                applySettingsForLockFocus(createCaptureRequest);
                CaptureRequest build = createCaptureRequest.build();
                this.mFocusLockRequestHashCode = build.hashCode();
                this.mCaptureCallback.setState(3);
                this.mCaptureCallback.showAutoFocusStart();
                capture(build, this.mCaptureCallback, this.mCameraHandler);
                setAFModeToPreview(1);
                if (this.mHelperHandler != null) {
                    this.mHelperHandler.removeMessages(1);
                    this.mHelperHandler.sendEmptyMessageDelayed(1, 3000);
                }
            } catch (CameraAccessException | IllegalStateException e) {
                e.printStackTrace();
                notifyOnError(-1);
            }
        }
    }

    public void cancelFocus(int i) {
        if (checkCaptureSession("cancelFocus")) {
            if (2 == this.mCaptureCallback.getState()) {
                this.mCaptureCallback.setState(1);
            }
            try {
                Builder initFocusRequestBuilder = initFocusRequestBuilder(i);
                initFocusRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(1));
                initFocusRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
                CaptureRequestBuilder.applyZoomRatio(initFocusRequestBuilder, this.mCapabilities, this.mConfigs);
                applyFlashMode(initFocusRequestBuilder, 1);
                CaptureRequestBuilder.applyAWBMode(initFocusRequestBuilder, this.mConfigs.getAWBMode());
                CaptureRequestBuilder.applyCustomAWB(initFocusRequestBuilder, this.mConfigs.getAwbCustomValue());
                CaptureRequestBuilder.applyExposureCompensation(initFocusRequestBuilder, 1, this.mCapabilities, this.mConfigs);
                CaptureRequestBuilder.applyAntiShake(initFocusRequestBuilder, this.mCapabilities, this.mConfigs);
                if (ModuleManager.isCapture()) {
                    CaptureRequestBuilder.applyContrast(initFocusRequestBuilder, this.mCapabilities, this.mConfigs);
                    CaptureRequestBuilder.applySaturation(initFocusRequestBuilder, this.mConfigs);
                    CaptureRequestBuilder.applySharpness(initFocusRequestBuilder, this.mConfigs);
                }
                if (ModuleManager.isManualModule()) {
                    CaptureRequestBuilder.applyIso(initFocusRequestBuilder, 1, this.mCapabilities, this.mConfigs);
                    CaptureRequestBuilder.applyExposureTime(initFocusRequestBuilder, 1, this.mConfigs);
                }
                CaptureRequestBuilder.applyFpsRange(initFocusRequestBuilder, this.mConfigs);
                capture(initFocusRequestBuilder.build(), this.mCaptureCallback, this.mCameraHandler);
                this.mConfigs.setAERegions(null);
                this.mConfigs.setAFRegions(null);
                setAFModeToPreview(this.mConfigs.getFocusMode());
            } catch (CameraAccessException e) {
                e.printStackTrace();
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("cancelFocus: ");
                stringBuilder.append(e.getMessage());
                Log.e(str, stringBuilder.toString());
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to cancel focus, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    private void unlockFocus() {
        if (checkCaptureSession("unlockFocus")) {
            Log.d(TAG, "unlockFocus");
            try {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
                capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mCameraHandler);
                CaptureRequestBuilder.applyFocusMode(this.mPreviewRequestBuilder, this.mConfigs);
                applyFlashMode(this.mPreviewRequestBuilder, 1);
                CaptureRequestBuilder.applyAELock(this.mPreviewRequestBuilder, this.mConfigs.isAELocked());
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(0));
                this.mCaptureCallback.setState(1);
                resumePreview();
            } catch (CameraAccessException e) {
                e.printStackTrace();
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unlockFocus: ");
                stringBuilder.append(e.getMessage());
                Log.e(str, stringBuilder.toString());
                notifyOnError(e.getReason());
            } catch (IllegalStateException e2) {
                Log.e(TAG, "Failed to unlock focus, IllegalState", e2);
                notifyOnError(256);
            }
        }
    }

    private int capture(CaptureRequest captureRequest, CaptureCallback captureCallback, Handler handler) throws CameraAccessException {
        if (!(this.mCaptureSession instanceof CameraConstrainedHighSpeedCaptureSession)) {
            return this.mCaptureSession.capture(captureRequest, captureCallback, handler);
        }
        return this.mCaptureSession.captureBurst(createHighSpeedRequestList(captureRequest), captureCallback, handler);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0118  */
    /* JADX WARNING: Missing block: B:9:0x003d, code skipped:
            if (com.android.camera.module.ModuleManager.isCapture() != false) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:11:0x0043, code skipped:
            r0 = 2;
     */
    /* JADX WARNING: Missing block: B:18:0x005e, code skipped:
            if (com.android.camera.module.ModuleManager.isCapture() != false) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:19:0x0061, code skipped:
            if (r0 == 3) goto L_0x0056;
     */
    private void applyFlashMode(android.hardware.camera2.CaptureRequest.Builder r12, int r13) {
        /*
        r11 = this;
        r0 = TAG;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "applyFlashMode: request = ";
        r1.append(r2);
        r1.append(r12);
        r2 = ", applyType = ";
        r1.append(r2);
        r1.append(r13);
        r1 = r1.toString();
        com.android.camera.log.Log.d(r0, r1);
        if (r12 != 0) goto L_0x0021;
    L_0x0020:
        return;
    L_0x0021:
        r0 = r11.mConfigs;
        r0 = r0.getFlashMode();
        r1 = 6;
        r2 = 2;
        r3 = 3;
        r4 = 0;
        r5 = 1;
        if (r13 == r3) goto L_0x0045;
    L_0x002f:
        if (r13 == r1) goto L_0x0032;
    L_0x0031:
        goto L_0x0064;
    L_0x0032:
        r6 = r11.needOptimizedFlash();
        if (r6 == 0) goto L_0x0064;
        r0 = com.android.camera.module.ModuleManager.isCapture();
        if (r0 == 0) goto L_0x0043;
    L_0x0040:
        r0 = r2;
        r6 = r5;
        goto L_0x0065;
    L_0x0043:
        r0 = r2;
        goto L_0x0064;
    L_0x0045:
        r6 = r11.needOptimizedFlash();
        if (r6 == 0) goto L_0x0061;
    L_0x004b:
        r6 = r11.getExposureTime();
        r8 = 0;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x0059;
    L_0x0056:
        r0 = r4;
        r6 = r0;
        goto L_0x0065;
        r0 = com.android.camera.module.ModuleManager.isCapture();
        if (r0 == 0) goto L_0x0043;
    L_0x0060:
        goto L_0x0040;
    L_0x0061:
        if (r0 != r3) goto L_0x0064;
    L_0x0063:
        goto L_0x0056;
    L_0x0064:
        r6 = r4;
    L_0x0065:
        r7 = r11.getScreenLightCallback();
        r8 = TAG;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "applyFlashMode: flashMode = ";
        r9.append(r10);
        r9.append(r0);
        r10 = ", mScreenLightCallback = ";
        r9.append(r10);
        r9.append(r7);
        r9 = r9.toString();
        com.android.camera.log.Log.d(r8, r9);
        r8 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r0 == r8) goto L_0x0118;
    L_0x008b:
        r1 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r0 == r1) goto L_0x00fb;
    L_0x008f:
        switch(r0) {
            case 0: goto L_0x00e7;
            case 1: goto L_0x00d3;
            case 2: goto L_0x00b4;
            case 3: goto L_0x00a0;
            case 4: goto L_0x0094;
            default: goto L_0x0092;
        };
    L_0x0092:
        goto L_0x018f;
    L_0x0094:
        r13 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;
        r0 = 4;
        r0 = java.lang.Integer.valueOf(r0);
        r12.set(r13, r0);
        goto L_0x018f;
    L_0x00a0:
        r13 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;
        r0 = java.lang.Integer.valueOf(r2);
        r12.set(r13, r0);
        r13 = android.hardware.camera2.CaptureRequest.FLASH_MODE;
        r0 = java.lang.Integer.valueOf(r5);
        r12.set(r13, r0);
        goto L_0x018f;
    L_0x00b4:
        r13 = r11.mCapabilities;
        r13 = r13.isSupportSnapShotTorch();
        if (r13 == 0) goto L_0x00bf;
    L_0x00bc:
        com.android.camera2.compat.MiCameraCompat.applySnapshotTorch(r12, r6);
    L_0x00bf:
        r13 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;
        r0 = java.lang.Integer.valueOf(r5);
        r12.set(r13, r0);
        r13 = android.hardware.camera2.CaptureRequest.FLASH_MODE;
        r0 = java.lang.Integer.valueOf(r2);
        r12.set(r13, r0);
        goto L_0x018f;
    L_0x00d3:
        r13 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;
        r0 = java.lang.Integer.valueOf(r3);
        r12.set(r13, r0);
        r13 = android.hardware.camera2.CaptureRequest.FLASH_MODE;
        r0 = java.lang.Integer.valueOf(r5);
        r12.set(r13, r0);
        goto L_0x018f;
    L_0x00e7:
        r13 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;
        r0 = java.lang.Integer.valueOf(r5);
        r12.set(r13, r0);
        r13 = android.hardware.camera2.CaptureRequest.FLASH_MODE;
        r0 = java.lang.Integer.valueOf(r4);
        r12.set(r13, r0);
        goto L_0x018f;
    L_0x00fb:
        r12 = TAG;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "applyFlashMode: FLASH_MODE_SCREEN_LIGHT_AUTO applyType = ";
        r0.append(r1);
        r0.append(r13);
        r13 = r0.toString();
        com.android.camera.log.Log.d(r12, r13);
        if (r7 == 0) goto L_0x018f;
    L_0x0113:
        r7.stopScreenLight();
        goto L_0x018f;
    L_0x0118:
        if (r7 == 0) goto L_0x018f;
    L_0x011a:
        if (r13 != r1) goto L_0x0124;
    L_0x011c:
        r12 = r11.mCaptureCallback;
        r12 = r12.getCurrentColorTemperature();
        r11.mScreenLightColorTemperature = r12;
    L_0x0124:
        r12 = "camera_screen_light_wb";
        r0 = r11.mScreenLightColorTemperature;
        r12 = android.os.SystemProperties.getInt(r12, r0);
        r12 = com.android.camera.Util.getScreenLightColor(r12);
        r0 = "camera_screen_light_brightness";
        r2 = com.android.camera.data.DataRepository.dataItemFeature();
        r2 = r2.fq();
        r0 = android.os.SystemProperties.getInt(r0, r2);
        r2 = "camera_screen_light_delay";
        r2 = android.os.SystemProperties.getInt(r2, r4);
        r4 = TAG;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "applyFlashMode: FLASH_MODE_SCREEN_LIGHT_ON color = ";
        r5.append(r6);
        r5.append(r12);
        r6 = ", brightness = ";
        r5.append(r6);
        r5.append(r0);
        r6 = ", delay = ";
        r5.append(r6);
        r5.append(r2);
        r6 = ", mCameraHandler = ";
        r5.append(r6);
        r6 = r11.mCameraHandler;
        r5.append(r6);
        r5 = r5.toString();
        com.android.camera.log.Log.d(r4, r5);
        if (r13 == r1) goto L_0x018b;
    L_0x0176:
        if (r13 != r3) goto L_0x0179;
    L_0x0178:
        goto L_0x018b;
    L_0x0179:
        if (r2 != 0) goto L_0x017f;
    L_0x017b:
        r7.stopScreenLight();
        goto L_0x018e;
    L_0x017f:
        r12 = r11.mCameraHandler;
        r13 = new com.android.camera2.MiCamera2$8;
        r13.<init>(r7);
        r0 = (long) r2;
        r12.postDelayed(r13, r0);
        goto L_0x018e;
    L_0x018b:
        r7.startScreenLight(r12, r0);
    L_0x018f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera2.MiCamera2.applyFlashMode(android.hardware.camera2.CaptureRequest$Builder, int):void");
    }

    private void applySettingsForFocusCapture(Builder builder) {
        CaptureRequestBuilder.applyAFRegions(builder, this.mConfigs);
        CaptureRequestBuilder.applyAERegions(builder, this.mConfigs);
        CaptureRequestBuilder.applyZoomRatio(builder, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyAWBMode(builder, this.mConfigs.getAWBMode());
        CaptureRequestBuilder.applyCustomAWB(builder, this.mConfigs.getAwbCustomValue());
        CaptureRequestBuilder.applyExposureCompensation(builder, 1, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyAntiShake(builder, this.mCapabilities, this.mConfigs);
        if (ModuleManager.isCapture()) {
            CaptureRequestBuilder.applyContrast(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applySaturation(builder, this.mConfigs);
            CaptureRequestBuilder.applySharpness(builder, this.mConfigs);
        }
        if (ModuleManager.isManualModule()) {
            CaptureRequestBuilder.applyIso(builder, 1, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyExposureTime(builder, 1, this.mConfigs);
        }
        builder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(1));
        builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        applyFlashMode(builder, 1);
        CaptureRequestBuilder.applyFpsRange(builder, this.mConfigs);
    }

    private void applySettingsForLockFocus(Builder builder) {
        builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
        CaptureRequestBuilder.applyAFRegions(builder, this.mConfigs);
        CaptureRequestBuilder.applyAERegions(builder, this.mConfigs);
        applyCommonSettings(builder, 1);
        builder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(1));
        if (needOptimizedFlash() || needScreenLight()) {
            applyFlashMode(builder, 6);
        }
    }

    private void applySettingsForPreCapture(Builder builder) {
        applyCommonSettings(builder, 1);
        applyFlashMode(builder, 6);
        builder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(1));
    }

    private void applyCommonSettings(Builder builder, int i) {
        builder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
        CaptureRequestBuilder.applyFocusMode(builder, this.mConfigs);
        CaptureRequestBuilder.applyFaceDetection(builder, this.mConfigs);
        CaptureRequestBuilder.applyAntiBanding(builder, this.mConfigs);
        CaptureRequestBuilder.applyExposureCompensation(builder, i, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyZoomRatio(builder, this.mCapabilities, this.mConfigs);
        if (ModuleManager.isCapture()) {
            CaptureRequestBuilder.applyContrast(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applySaturation(builder, this.mConfigs);
            CaptureRequestBuilder.applySharpness(builder, this.mConfigs);
            CaptureRequestBuilder.applyExposureMeteringMode(builder, this.mConfigs);
            CaptureRequestBuilder.applySceneMode(builder, this.mConfigs);
            if (ModuleManager.isSuperNightScene()) {
                CaptureRequestBuilder.applySuperNightScene(builder, i);
            }
            CaptureRequestBuilder.applyHHT(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyHDR(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applySuperResolution(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyHwMfnr(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applySwMfnr(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyBokeh(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyFaceAgeAnalyze(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyFaceScore(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyFrontMirror(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyCameraAi30Enable(builder, this.mCapabilities, this.mConfigs);
        }
        if (ModuleManager.isPortraitModule()) {
            CaptureRequestBuilder.applyContrast(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applySaturation(builder, this.mConfigs);
            CaptureRequestBuilder.applySharpness(builder, this.mConfigs);
            if (CameraSettings.isFrontCamera()) {
                CaptureRequestBuilder.applyBokeh(builder, this.mCapabilities, this.mConfigs);
                CaptureRequestBuilder.applyFrontMirror(builder, i, this.mCapabilities, this.mConfigs);
                CaptureRequestBuilder.applyFaceAgeAnalyze(builder, this.mCapabilities, this.mConfigs);
                CaptureRequestBuilder.applyFaceScore(builder, this.mCapabilities, this.mConfigs);
                CaptureRequestBuilder.applyHwMfnr(builder, i, this.mCapabilities, this.mConfigs);
                CaptureRequestBuilder.applySwMfnr(builder, i, this.mCapabilities, this.mConfigs);
            }
            CaptureRequestBuilder.applyPortraitLighting(builder, i, this.mCapabilities, this.mConfigs);
            if (Util.UI_DEBUG()) {
                CaptureRequestBuilder.applyFNumber(builder, this.mCapabilities, this.mConfigs);
            }
        }
        if (ModuleManager.isPortraitModule() || ModuleManager.isCapture()) {
            CaptureRequestBuilder.applyBeautyValues(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyEyeLight(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyWaterMark(builder, i, this.mCapabilities, this.mConfigs);
        }
        if (ModuleManager.isPanoramaModule() || ModuleManager.isCapture()) {
            CaptureRequestBuilder.applyAELock(builder, this.mConfigs.isAELocked());
        }
        if (ModuleManager.isManualModule()) {
            CaptureRequestBuilder.applyAWBMode(builder, this.mConfigs.getAWBMode());
            CaptureRequestBuilder.applyCustomAWB(builder, this.mConfigs.getAwbCustomValue());
            CaptureRequestBuilder.applyIso(builder, i, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyExposureTime(builder, i, this.mConfigs);
        }
        CaptureRequestBuilder.applyFocusDistance(builder, this.mConfigs);
        CaptureRequestBuilder.applyNormalWideLDC(builder, i, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyUltraWideLDC(builder, this.mCapabilities, this.mConfigs);
    }

    private void applySettingsForPreview(Builder builder) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("applySettingsForPreview: ");
        stringBuilder.append(builder);
        Log.d(str, stringBuilder.toString());
        if (builder != null) {
            applyFlashMode(builder, 1);
            applyCommonSettings(builder, 1);
            CaptureRequestBuilder.applyLensDirtyDetect(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyAELock(builder, this.mConfigs.isAELocked());
            CaptureRequestBuilder.applyAWBLock(builder, this.mConfigs.isAWBLocked());
            CaptureRequestBuilder.applyAntiShake(builder, this.mCapabilities, this.mConfigs);
            builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
        }
    }

    private void applySettingsForJpeg(Builder builder) {
        if (builder != null) {
            Location gpsLocation = this.mConfigs.getGpsLocation();
            if (gpsLocation != null) {
                builder.set(CaptureRequest.JPEG_GPS_LOCATION, new Location(gpsLocation));
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("jpegRotation=");
            stringBuilder.append(this.mConfigs.getJpegRotation());
            Log.d(str, stringBuilder.toString());
            builder.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(this.mConfigs.getJpegRotation()));
            CameraSize thumbnailSize = this.mConfigs.getThumbnailSize();
            if (thumbnailSize != null) {
                builder.set(CaptureRequest.JPEG_THUMBNAIL_SIZE, new Size(thumbnailSize.getWidth(), thumbnailSize.getHeight()));
            }
            byte jpegQuality = (byte) this.mConfigs.getJpegQuality();
            builder.set(CaptureRequest.JPEG_THUMBNAIL_QUALITY, Byte.valueOf(jpegQuality));
            builder.set(CaptureRequest.JPEG_QUALITY, Byte.valueOf(jpegQuality));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void applySettingsForCapture(Builder builder, int i) {
        if (builder != null) {
            boolean z = false;
            builder.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
            applyFlashMode(builder, i);
            applyCommonSettings(builder, i);
            applySettingsForJpeg(builder);
            CaptureRequestBuilder.applyZsl(builder, this.mConfigs);
            CaptureRequestBuilder.applyAntiShake(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyFpsRange(builder, this.mConfigs);
            CameraCapabilities cameraCapabilities = this.mCapabilities;
            if (i != 4) {
                z = true;
            }
            CaptureRequestBuilder.applyDepurpleEnable(builder, cameraCapabilities, z);
            CaptureRequestBuilder.applyBackwardCaptureHint(this.mCapabilities, builder, needScreenLight());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void applySettingsForVideoShot(Builder builder, int i) {
        applySettingsForJpeg(builder);
        CaptureRequestBuilder.applyVideoFlash(builder, this.mConfigs);
        CaptureRequestBuilder.applyExposureCompensation(builder, i, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyZoomRatio(builder, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyAntiShake(builder, this.mCapabilities, this.mConfigs);
        if (ModuleManager.isVideoModule() || ModuleManager.isFunModule()) {
            CaptureRequestBuilder.applyBeautyValues(builder, this.mCapabilities, this.mConfigs);
        }
        CaptureRequestBuilder.applyFrontMirror(builder, i, this.mCapabilities, this.mConfigs);
        CaptureRequestBuilder.applyFpsRange(builder, this.mConfigs);
        CaptureRequestBuilder.applyUltraWideLDC(builder, this.mCapabilities, this.mConfigs);
    }

    private void applySettingsForVideo(Builder builder) {
        if (builder != null) {
            builder.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(1));
            applyFlashMode(builder, 1);
            CaptureRequestBuilder.applyLensDirtyDetect(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyFocusMode(builder, this.mConfigs);
            CaptureRequestBuilder.applyFaceDetection(builder, this.mConfigs);
            CaptureRequestBuilder.applyAntiBanding(builder, this.mConfigs);
            CaptureRequestBuilder.applyExposureMeteringMode(builder, this.mConfigs);
            CaptureRequestBuilder.applyExposureCompensation(builder, 1, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyZoomRatio(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyAntiShake(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyAELock(builder, this.mConfigs.isAELocked());
            if (ModuleManager.isVideoModule() || ModuleManager.isFunModule()) {
                CaptureRequestBuilder.applyBeautyValues(builder, this.mCapabilities, this.mConfigs);
            }
            CaptureRequestBuilder.applyVideoFpsRange(builder, this.mConfigs);
            CaptureRequestBuilder.applyFrontMirror(builder, 3, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyDeviceOrientation(builder, this.mCapabilities, this.mConfigs);
            CaptureRequestBuilder.applyUltraWideLDC(builder, this.mCapabilities, this.mConfigs);
        }
    }

    private boolean checkCaptureSession(String str) {
        if (this.mCaptureSession != null) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("session for camera ");
        stringBuilder.append(getId());
        stringBuilder.append(" is closed when ");
        stringBuilder.append(str);
        throw new RuntimeException(stringBuilder.toString());
    }

    private boolean checkCameraDevice(String str) {
        if (this.mCameraDevice != null) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("camera ");
        stringBuilder.append(getId());
        stringBuilder.append(" is closed when ");
        stringBuilder.append(str);
        throw new RuntimeException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public boolean isFrontBeautyOn() {
        if (this.mCameraId == 1) {
            BeautyValues beautyValues = this.mConfigs.getBeautyValues();
            if (beautyValues == null) {
                Log.d(TAG, "Assume front beauty is off in case beautyValues is unavailable.");
                return false;
            } else if (b.hM()) {
                if (!BeautyConstant.LEVEL_CLOSE.equals(beautyValues.mBeautyLevel) || beautyValues.mBeautySkinColor > 0 || beautyValues.mBeautySlimFace > 0 || beautyValues.mBeautySkinSmooth > 0) {
                    return true;
                }
            } else if (!BeautyConstant.LEVEL_CLOSE.equals(beautyValues.mBeautyLevel)) {
                return true;
            }
        }
        return false;
    }

    private List<CaptureRequest> createHighSpeedRequestList(CaptureRequest captureRequest) throws CameraAccessException {
        if (captureRequest != null) {
            Collection targets = captureRequest.getTargets();
            Range range = (Range) captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("createHighSpeedRequestList() fpsRange = ");
            stringBuilder.append(range);
            Log.d(str, stringBuilder.toString());
            int intValue = ((Integer) range.getUpper()).intValue() / 30;
            ArrayList arrayList = new ArrayList();
            int i = 0;
            Builder constructCaptureRequestBuilder = CompatibilityUtils.constructCaptureRequestBuilder(new CameraMetadataNative(captureRequest.getNativeCopy()), false, -1, captureRequest);
            Iterator it = targets.iterator();
            Surface surface = (Surface) it.next();
            if (targets.size() != 1 || SurfaceUtils.isSurfaceForHwVideoEncoder(surface)) {
                constructCaptureRequestBuilder.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(3));
            } else {
                constructCaptureRequestBuilder.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(1));
            }
            constructCaptureRequestBuilder.setPartOfCHSRequestList(true);
            Builder builder = null;
            if (targets.size() == 2) {
                builder = CompatibilityUtils.constructCaptureRequestBuilder(new CameraMetadataNative(captureRequest.getNativeCopy()), false, -1, captureRequest);
                builder.set(CaptureRequest.CONTROL_CAPTURE_INTENT, Integer.valueOf(3));
                builder.addTarget(surface);
                Surface surface2 = (Surface) it.next();
                builder.addTarget(surface2);
                builder.setPartOfCHSRequestList(true);
                if (SurfaceUtils.isSurfaceForHwVideoEncoder(surface)) {
                    surface2 = surface;
                }
                constructCaptureRequestBuilder.addTarget(surface2);
            } else {
                constructCaptureRequestBuilder.addTarget(surface);
            }
            while (i < intValue) {
                if (i != 0 || builder == null) {
                    arrayList.add(constructCaptureRequestBuilder.build());
                } else {
                    arrayList.add(builder.build());
                }
                i++;
            }
            return Collections.unmodifiableList(arrayList);
        }
        throw new IllegalArgumentException("Input capture request must not be null");
    }

    public void setASDScene(int i) {
        if (this.mConfigs.setASDScene(i)) {
            CaptureRequestBuilder.applyASDScene(this.mPreviewRequestBuilder, this.mConfigs);
        }
    }

    public void onPreviewThumbnailReceived(Thumbnail thumbnail) {
        if (this.mMiCamera2Shot != null) {
            this.mMiCamera2Shot.onPreviewThumbnailReceived(thumbnail);
        }
    }
}