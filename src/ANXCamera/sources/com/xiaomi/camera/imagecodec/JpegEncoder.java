package com.xiaomi.camera.imagecodec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.InputConfiguration;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.media.ImageWriter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.view.Surface;
import com.android.camera.data.data.config.ComponentConfigFlash;
import com.xiaomi.camera.imagecodec.ImagePool.ImageFormat;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class JpegEncoder {
    private static String BACK_VT_CAMERA_ID_DEFAULT = "100";
    private static String FRONT_VT_CAMERA_ID_DEFAULT = ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_ON;
    private static final int MAX_IMAGE_BUFFER_SIZE = 2;
    private static final String TAG = JpegEncoder.class.getSimpleName();
    private String mBackVtCameraId;
    private final Object mCameraLock;
    private CameraManager mCameraManager;
    private Handler mCameraOperationHandler;
    private HandlerThread mCameraOperationThread;
    private volatile boolean mCreatingReprocessSession;
    private ReprocessData mCurrentProcessingData;
    private int mCurrentSessionId;
    private final Object mDataLock;
    private String mFrontVtCameraId;
    private boolean mInitialized;
    private InputConfiguration mInputConfiguration;
    private ImageReader mJpegImageReader;
    private OutputConfiguration mJpegOutputConfiguration;
    private boolean mNeedReopenCamera;
    private Handler mReprocessHandler;
    private ImageWriter mReprocessImageWriter;
    private long mReprocessStartTime;
    private HandlerThread mReprocessThread;
    private LinkedList<ReprocessData> mTaskDataList;
    private CameraDevice mVTCameraDevice;
    private CameraCaptureSession mVTCaptureSession;
    private ImageReader mYuvImageReader;
    private OutputConfiguration mYuvOutputConfiguration;

    static class JpegEncoderHolder {
        static JpegEncoder sInstance = new JpegEncoder();

        JpegEncoderHolder() {
        }
    }

    private class ReprocessHandler extends Handler {
        private static final int MSG_CLOSE_VT_CAMERA = 2;
        private static final int MSG_REPROCESS_IMG = 1;

        ReprocessHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Log.d(JpegEncoder.TAG, "recv MSG_REPROCESS_IMG");
                    if (JpegEncoder.this.checkConditionIsReady()) {
                        JpegEncoder.this.reprocessImage();
                        return;
                    }
                    return;
                case 2:
                    Log.d(JpegEncoder.TAG, "recv MSG_CLOSE_VT_CAMERA");
                    synchronized (JpegEncoder.this.mCameraLock) {
                        if (JpegEncoder.this.mVTCameraDevice != null) {
                            String access$100 = JpegEncoder.TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("close current VtCamera: ");
                            stringBuilder.append(JpegEncoder.this.mVTCameraDevice);
                            Log.d(access$100, stringBuilder.toString());
                            JpegEncoder.this.mVTCameraDevice.close();
                            JpegEncoder.this.mVTCameraDevice = null;
                        }
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    /* synthetic */ JpegEncoder(AnonymousClass1 anonymousClass1) {
        this();
    }

    @AnyThread
    public static JpegEncoder instance() {
        return JpegEncoderHolder.sInstance;
    }

    private JpegEncoder() {
        this.mBackVtCameraId = BACK_VT_CAMERA_ID_DEFAULT;
        this.mFrontVtCameraId = FRONT_VT_CAMERA_ID_DEFAULT;
        this.mCurrentSessionId = -1;
        this.mCameraLock = new Object();
        this.mTaskDataList = new LinkedList();
        this.mDataLock = new Object();
        this.mYuvOutputConfiguration = new OutputConfiguration(0, 0, 35);
    }

    @AnyThread
    public void init(Context context) {
        Log.d(TAG, "init>>");
        synchronized (this.mDataLock) {
            if (!this.mInitialized) {
                this.mCameraManager = (CameraManager) context.getSystemService("camera");
                this.mCameraOperationThread = new HandlerThread("CameraOperationThread");
                this.mCameraOperationThread.start();
                this.mCameraOperationHandler = new Handler(this.mCameraOperationThread.getLooper());
                this.mReprocessThread = new HandlerThread("JpegEncoderThread");
                this.mReprocessThread.start();
                this.mReprocessHandler = new ReprocessHandler(this.mReprocessThread.getLooper());
                this.mInitialized = true;
            }
        }
        Log.d(TAG, "init<<");
    }

    /* JADX WARNING: Missing block: B:9:0x0019, code skipped:
            r2 = r3.mCameraLock;
     */
    /* JADX WARNING: Missing block: B:10:0x001b, code skipped:
            monitor-enter(r2);
     */
    /* JADX WARNING: Missing block: B:13:0x001e, code skipped:
            if (r3.mVTCaptureSession == null) goto L_0x002d;
     */
    /* JADX WARNING: Missing block: B:14:0x0020, code skipped:
            r3.mVTCaptureSession.close();
            r3.mVTCaptureSession = null;
            r3.mJpegImageReader = null;
            r3.mYuvImageReader = null;
            r3.mReprocessImageWriter = null;
     */
    /* JADX WARNING: Missing block: B:16:0x002f, code skipped:
            if (r3.mVTCameraDevice == null) goto L_0x0038;
     */
    /* JADX WARNING: Missing block: B:17:0x0031, code skipped:
            r3.mVTCameraDevice.close();
            r3.mVTCameraDevice = null;
     */
    /* JADX WARNING: Missing block: B:18:0x0038, code skipped:
            monitor-exit(r2);
     */
    /* JADX WARNING: Missing block: B:20:0x003b, code skipped:
            if (r3.mCameraOperationThread == null) goto L_0x0050;
     */
    /* JADX WARNING: Missing block: B:21:0x003d, code skipped:
            r3.mCameraOperationThread.quitSafely();
     */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            r3.mCameraOperationThread.join();
            r3.mCameraOperationThread = null;
            r3.mCameraOperationHandler = null;
     */
    /* JADX WARNING: Missing block: B:24:0x004c, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:25:0x004d, code skipped:
            r0.printStackTrace();
     */
    @android.support.annotation.AnyThread
    public void deInit() {
        /*
        r3 = this;
        r0 = TAG;
        r1 = "deInit>>";
        android.util.Log.d(r0, r1);
        r0 = r3.mDataLock;
        monitor-enter(r0);
        r1 = r3.mInitialized;	 Catch:{ all -> 0x0072 }
        if (r1 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r0);	 Catch:{ all -> 0x0072 }
        return;
    L_0x0010:
        r1 = 0;
        r3.mInitialized = r1;	 Catch:{ all -> 0x0072 }
        r1 = 0;
        r3.mCameraManager = r1;	 Catch:{ all -> 0x0072 }
        r3.mCurrentProcessingData = r1;	 Catch:{ all -> 0x0072 }
        monitor-exit(r0);	 Catch:{ all -> 0x0072 }
        r2 = r3.mCameraLock;
        monitor-enter(r2);
        r0 = r3.mVTCaptureSession;	 Catch:{ all -> 0x006f }
        if (r0 == 0) goto L_0x002d;
    L_0x0020:
        r0 = r3.mVTCaptureSession;	 Catch:{ all -> 0x006f }
        r0.close();	 Catch:{ all -> 0x006f }
        r3.mVTCaptureSession = r1;	 Catch:{ all -> 0x006f }
        r3.mJpegImageReader = r1;	 Catch:{ all -> 0x006f }
        r3.mYuvImageReader = r1;	 Catch:{ all -> 0x006f }
        r3.mReprocessImageWriter = r1;	 Catch:{ all -> 0x006f }
    L_0x002d:
        r0 = r3.mVTCameraDevice;	 Catch:{ all -> 0x006f }
        if (r0 == 0) goto L_0x0038;
    L_0x0031:
        r0 = r3.mVTCameraDevice;	 Catch:{ all -> 0x006f }
        r0.close();	 Catch:{ all -> 0x006f }
        r3.mVTCameraDevice = r1;	 Catch:{ all -> 0x006f }
    L_0x0038:
        monitor-exit(r2);	 Catch:{ all -> 0x006f }
        r0 = r3.mCameraOperationThread;
        if (r0 == 0) goto L_0x0050;
    L_0x003d:
        r0 = r3.mCameraOperationThread;
        r0.quitSafely();
        r0 = r3.mCameraOperationThread;	 Catch:{ InterruptedException -> 0x004c }
        r0.join();	 Catch:{ InterruptedException -> 0x004c }
        r3.mCameraOperationThread = r1;	 Catch:{ InterruptedException -> 0x004c }
        r3.mCameraOperationHandler = r1;	 Catch:{ InterruptedException -> 0x004c }
        goto L_0x0050;
    L_0x004c:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0050:
        r0 = r3.mReprocessThread;
        if (r0 == 0) goto L_0x0067;
    L_0x0054:
        r0 = r3.mReprocessThread;
        r0.quitSafely();
        r0 = r3.mReprocessThread;	 Catch:{ InterruptedException -> 0x0063 }
        r0.join();	 Catch:{ InterruptedException -> 0x0063 }
        r3.mReprocessThread = r1;	 Catch:{ InterruptedException -> 0x0063 }
        r3.mReprocessHandler = r1;	 Catch:{ InterruptedException -> 0x0063 }
        goto L_0x0067;
    L_0x0063:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0067:
        r0 = TAG;
        r1 = "deInit<<";
        android.util.Log.d(r0, r1);
        return;
    L_0x006f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x006f }
        throw r0;
    L_0x0072:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0072 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.imagecodec.JpegEncoder.deInit():void");
    }

    public void setJpegOutputSize(int i, int i2) {
        if (this.mJpegOutputConfiguration == null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setJpegOutputSize: ");
            stringBuilder.append(i);
            stringBuilder.append("x");
            stringBuilder.append(i2);
            Log.d(str, stringBuilder.toString());
            this.mJpegOutputConfiguration = new OutputConfiguration(i, i2, 256);
        }
    }

    @AnyThread
    public void setVTCameraIds(@NonNull String str, @NonNull String str2) {
        Log.d(TAG, String.format("setVTCameraIds: backId=%s frontId=%s", new Object[]{str, str2}));
        this.mBackVtCameraId = str;
        this.mFrontVtCameraId = str2;
    }

    @AnyThread
    public void doReprocess(ReprocessData reprocessData) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("doReprocess: ");
        stringBuilder.append(reprocessData.getImageTag());
        Log.d(str, stringBuilder.toString());
        if (reprocessData.getResultListener() == null) {
            Log.d(TAG, "doReprocess: drop this request due to no callback was provided!");
        } else if (this.mInitialized) {
            if (!reprocessData.isImageFromPool()) {
                Image yuvImage = reprocessData.getYuvImage();
                ImageFormat toImageQueueKey = ImagePool.getInstance().toImageQueueKey(yuvImage);
                if (ImagePool.getInstance().isImageQueueFull(toImageQueueKey, 0)) {
                    Log.w(TAG, "doReprocess: wait image pool>>");
                    ImagePool.getInstance().waitIfImageQueueFull(toImageQueueKey, 0, 0);
                    Log.w(TAG, "doReprocess: wait image pool<<");
                }
                long timestamp = yuvImage.getTimestamp();
                ImagePool.getInstance().queueImage(yuvImage);
                yuvImage = ImagePool.getInstance().getImage(timestamp);
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("doReprocess: image: ");
                stringBuilder2.append(yuvImage);
                stringBuilder2.append(" | ");
                stringBuilder2.append(timestamp);
                Log.d(str2, stringBuilder2.toString());
                reprocessData.setYuvImage(yuvImage);
                ImagePool.getInstance().holdImage(yuvImage);
            }
            synchronized (this.mDataLock) {
                this.mTaskDataList.add(reprocessData);
            }
            sendReprocessRequest();
        } else {
            throw new RuntimeException("NOT initialized. Call init() first!");
        }
    }

    /* JADX WARNING: Missing block: B:13:0x0024, code skipped:
            r1 = r5.mDataLock;
     */
    /* JADX WARNING: Missing block: B:14:0x0026, code skipped:
            monitor-enter(r1);
     */
    /* JADX WARNING: Missing block: B:17:0x002e, code skipped:
            if (r5.mTaskDataList.isEmpty() == false) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:18:0x0030, code skipped:
            android.util.Log.d(TAG, "sendReprocessRequest: idle. Try to close device 30s later.");
            r5.mReprocessHandler.sendEmptyMessageDelayed(2, 30000);
     */
    /* JADX WARNING: Missing block: B:19:0x003e, code skipped:
            monitor-exit(r1);
     */
    /* JADX WARNING: Missing block: B:20:0x003f, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:22:0x0046, code skipped:
            if (r5.mReprocessHandler.hasMessages(2) == false) goto L_0x004d;
     */
    /* JADX WARNING: Missing block: B:23:0x0048, code skipped:
            r5.mReprocessHandler.removeMessages(2);
     */
    /* JADX WARNING: Missing block: B:24:0x004d, code skipped:
            monitor-exit(r1);
     */
    /* JADX WARNING: Missing block: B:26:0x0055, code skipped:
            if (r5.mReprocessHandler.hasMessages(1) == false) goto L_0x005f;
     */
    /* JADX WARNING: Missing block: B:27:0x0057, code skipped:
            android.util.Log.d(TAG, "sendReprocessRequest: busy");
     */
    /* JADX WARNING: Missing block: B:28:0x005f, code skipped:
            android.util.Log.d(TAG, "sendReprocessRequest: send MSG_REPROCESS_IMG");
            r5.mReprocessHandler.sendEmptyMessageDelayed(1, 0);
     */
    /* JADX WARNING: Missing block: B:29:0x006d, code skipped:
            return;
     */
    @android.support.annotation.AnyThread
    private void sendReprocessRequest() {
        /*
        r5 = this;
        r0 = TAG;
        r1 = "=============================================================";
        android.util.Log.i(r0, r1);
        r0 = r5.mInitialized;
        if (r0 != 0) goto L_0x0013;
    L_0x000b:
        r0 = TAG;
        r1 = "sendReprocessRequest: NOT initialized!";
        android.util.Log.w(r0, r1);
        return;
    L_0x0013:
        r0 = r5.mCameraLock;
        monitor-enter(r0);
        r1 = r5.mCreatingReprocessSession;	 Catch:{ all -> 0x0071 }
        if (r1 == 0) goto L_0x0023;
    L_0x001a:
        r1 = TAG;	 Catch:{ all -> 0x0071 }
        r2 = "sendReprocessRequest: creating session...";
        android.util.Log.d(r1, r2);	 Catch:{ all -> 0x0071 }
        monitor-exit(r0);	 Catch:{ all -> 0x0071 }
        return;
    L_0x0023:
        monitor-exit(r0);	 Catch:{ all -> 0x0071 }
        r1 = r5.mDataLock;
        monitor-enter(r1);
        r0 = r5.mTaskDataList;	 Catch:{ all -> 0x006e }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x006e }
        r2 = 2;
        if (r0 == 0) goto L_0x0040;
    L_0x0030:
        r0 = TAG;	 Catch:{ all -> 0x006e }
        r3 = "sendReprocessRequest: idle. Try to close device 30s later.";
        android.util.Log.d(r0, r3);	 Catch:{ all -> 0x006e }
        r0 = r5.mReprocessHandler;	 Catch:{ all -> 0x006e }
        r3 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.sendEmptyMessageDelayed(r2, r3);	 Catch:{ all -> 0x006e }
        monitor-exit(r1);	 Catch:{ all -> 0x006e }
        return;
    L_0x0040:
        r0 = r5.mReprocessHandler;	 Catch:{ all -> 0x006e }
        r0 = r0.hasMessages(r2);	 Catch:{ all -> 0x006e }
        if (r0 == 0) goto L_0x004d;
    L_0x0048:
        r0 = r5.mReprocessHandler;	 Catch:{ all -> 0x006e }
        r0.removeMessages(r2);	 Catch:{ all -> 0x006e }
    L_0x004d:
        monitor-exit(r1);	 Catch:{ all -> 0x006e }
        r0 = r5.mReprocessHandler;
        r1 = 1;
        r0 = r0.hasMessages(r1);
        if (r0 == 0) goto L_0x005f;
    L_0x0057:
        r0 = TAG;
        r1 = "sendReprocessRequest: busy";
        android.util.Log.d(r0, r1);
        goto L_0x006d;
    L_0x005f:
        r0 = TAG;
        r2 = "sendReprocessRequest: send MSG_REPROCESS_IMG";
        android.util.Log.d(r0, r2);
        r0 = r5.mReprocessHandler;
        r2 = 0;
        r0.sendEmptyMessageDelayed(r1, r2);
    L_0x006d:
        return;
    L_0x006e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x006e }
        throw r0;
    L_0x0071:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0071 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.imagecodec.JpegEncoder.sendReprocessRequest():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARNING: Missing block: B:27:0x009f, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:76:0x0175, code skipped:
            return r4;
     */
    @android.support.annotation.WorkerThread
    private boolean createCaptureSessionIfNeed(@android.support.annotation.NonNull com.xiaomi.camera.imagecodec.ReprocessData r12) {
        /*
        r11 = this;
        r0 = r12.getYuvImage();
        r1 = new android.hardware.camera2.params.InputConfiguration;
        r2 = r0.getWidth();
        r3 = r0.getHeight();
        r0 = r0.getFormat();
        r1.<init>(r2, r3, r0);
        r0 = new com.xiaomi.camera.imagecodec.OutputConfiguration;
        r2 = r12.getOutputWidth();
        r3 = r12.getOutputHeight();
        r4 = r12.getOutputFormat();
        r0.<init>(r2, r3, r4);
        r12 = r12.isFrontCamera();
        if (r12 == 0) goto L_0x002f;
    L_0x002c:
        r12 = r11.mFrontVtCameraId;
        goto L_0x0031;
    L_0x002f:
        r12 = r11.mBackVtCameraId;
    L_0x0031:
        r2 = r11.mCameraLock;
        monitor-enter(r2);
        r3 = r11.mVTCameraDevice;	 Catch:{ all -> 0x0176 }
        r4 = 0;
        r5 = 1;
        if (r3 == 0) goto L_0x0049;
    L_0x003a:
        r3 = r11.mVTCameraDevice;	 Catch:{ all -> 0x0176 }
        r3 = r3.getId();	 Catch:{ all -> 0x0176 }
        r3 = r12.equals(r3);	 Catch:{ all -> 0x0176 }
        if (r3 != 0) goto L_0x0047;
    L_0x0046:
        goto L_0x0049;
    L_0x0047:
        r3 = r4;
        goto L_0x004b;
        r3 = r5;
    L_0x004b:
        if (r3 == 0) goto L_0x00a0;
    L_0x004d:
        r0 = r11.mCreatingReprocessSession;	 Catch:{ all -> 0x0176 }
        if (r0 == 0) goto L_0x005a;
    L_0x0051:
        r12 = TAG;	 Catch:{ all -> 0x0176 }
        r0 = "creating reprocess session...";
        android.util.Log.d(r12, r0);	 Catch:{ all -> 0x0176 }
        monitor-exit(r2);	 Catch:{ all -> 0x0176 }
        return r5;
    L_0x005a:
        r11.mCreatingReprocessSession = r5;	 Catch:{ all -> 0x0176 }
        r0 = r11.mVTCameraDevice;	 Catch:{ all -> 0x0176 }
        if (r0 != 0) goto L_0x0064;
    L_0x0060:
        r11.openVTCamera(r12);	 Catch:{ all -> 0x0176 }
        goto L_0x009e;
    L_0x0064:
        r0 = r11.mVTCameraDevice;	 Catch:{ all -> 0x0176 }
        r0 = r0.getId();	 Catch:{ all -> 0x0176 }
        r0 = r12.equals(r0);	 Catch:{ all -> 0x0176 }
        if (r0 != 0) goto L_0x009e;
    L_0x0070:
        r0 = TAG;	 Catch:{ all -> 0x0176 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0176 }
        r1.<init>();	 Catch:{ all -> 0x0176 }
        r3 = "createCaptureSessionIfNeed: expected device changed. oldId=";
        r1.append(r3);	 Catch:{ all -> 0x0176 }
        r3 = r11.mVTCameraDevice;	 Catch:{ all -> 0x0176 }
        r3 = r3.getId();	 Catch:{ all -> 0x0176 }
        r1.append(r3);	 Catch:{ all -> 0x0176 }
        r3 = " newId=";
        r1.append(r3);	 Catch:{ all -> 0x0176 }
        r1.append(r12);	 Catch:{ all -> 0x0176 }
        r12 = r1.toString();	 Catch:{ all -> 0x0176 }
        android.util.Log.d(r0, r12);	 Catch:{ all -> 0x0176 }
        r12 = r11.mVTCameraDevice;	 Catch:{ all -> 0x0176 }
        r12.close();	 Catch:{ all -> 0x0176 }
        r12 = 0;
        r11.mVTCameraDevice = r12;	 Catch:{ all -> 0x0176 }
        r11.mNeedReopenCamera = r5;	 Catch:{ all -> 0x0176 }
    L_0x009e:
        monitor-exit(r2);	 Catch:{ all -> 0x0176 }
        return r5;
        r12 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r3 = r0.mFormat;	 Catch:{ all -> 0x0176 }
        if (r12 != r3) goto L_0x00a9;
    L_0x00a7:
        r12 = r5;
        goto L_0x00ab;
        r12 = r4;
    L_0x00ab:
        if (r12 == 0) goto L_0x00b0;
    L_0x00ad:
        r3 = r11.mJpegOutputConfiguration;	 Catch:{ all -> 0x0176 }
        goto L_0x00b2;
    L_0x00b0:
        r3 = r11.mYuvOutputConfiguration;	 Catch:{ all -> 0x0176 }
    L_0x00b2:
        r6 = r11.mVTCaptureSession;	 Catch:{ all -> 0x0176 }
        if (r6 != 0) goto L_0x00ba;
    L_0x00b7:
        r4 = r5;
        goto L_0x0151;
    L_0x00ba:
        r6 = r11.mInputConfiguration;	 Catch:{ all -> 0x0176 }
        r6 = r1.equals(r6);	 Catch:{ all -> 0x0176 }
        if (r6 == 0) goto L_0x00c8;
    L_0x00c2:
        r6 = r0.equals(r3);	 Catch:{ all -> 0x0176 }
        if (r6 != 0) goto L_0x0151;
    L_0x00c8:
        r6 = TAG;	 Catch:{ all -> 0x0176 }
        r7 = "recreate session. in: %dx%d->%dx%d %sOut: %dx%d->%dx%d";
        r8 = 9;
        r8 = new java.lang.Object[r8];	 Catch:{ all -> 0x0176 }
        r9 = r11.mInputConfiguration;	 Catch:{ all -> 0x0176 }
        if (r9 != 0) goto L_0x00d6;
    L_0x00d4:
        r9 = r4;
        goto L_0x00dc;
    L_0x00d6:
        r9 = r11.mInputConfiguration;	 Catch:{ all -> 0x0176 }
        r9 = r9.getWidth();	 Catch:{ all -> 0x0176 }
    L_0x00dc:
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0176 }
        r8[r4] = r9;	 Catch:{ all -> 0x0176 }
        r9 = r11.mInputConfiguration;	 Catch:{ all -> 0x0176 }
        if (r9 != 0) goto L_0x00e8;
    L_0x00e6:
        r9 = r4;
        goto L_0x00ee;
    L_0x00e8:
        r9 = r11.mInputConfiguration;	 Catch:{ all -> 0x0176 }
        r9 = r9.getHeight();	 Catch:{ all -> 0x0176 }
    L_0x00ee:
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0176 }
        r8[r5] = r9;	 Catch:{ all -> 0x0176 }
        r9 = 2;
        r10 = r1.getWidth();	 Catch:{ all -> 0x0176 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0176 }
        r8[r9] = r10;	 Catch:{ all -> 0x0176 }
        r9 = 3;
        r10 = r1.getHeight();	 Catch:{ all -> 0x0176 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0176 }
        r8[r9] = r10;	 Catch:{ all -> 0x0176 }
        r9 = 4;
        if (r12 == 0) goto L_0x0110;
    L_0x010d:
        r10 = "jpeg";
        goto L_0x0112;
    L_0x0110:
        r10 = "yuv";
    L_0x0112:
        r8[r9] = r10;	 Catch:{ all -> 0x0176 }
        r9 = 5;
        if (r3 != 0) goto L_0x0119;
    L_0x0117:
        r10 = r4;
        goto L_0x011d;
    L_0x0119:
        r10 = r3.getWidth();	 Catch:{ all -> 0x0176 }
    L_0x011d:
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0176 }
        r8[r9] = r10;	 Catch:{ all -> 0x0176 }
        r9 = 6;
        if (r3 != 0) goto L_0x0127;
    L_0x0126:
        goto L_0x012b;
    L_0x0127:
        r4 = r3.getHeight();	 Catch:{ all -> 0x0176 }
    L_0x012b:
        r3 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0176 }
        r8[r9] = r3;	 Catch:{ all -> 0x0176 }
        r3 = 7;
        r4 = r0.getWidth();	 Catch:{ all -> 0x0176 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0176 }
        r8[r3] = r4;	 Catch:{ all -> 0x0176 }
        r3 = 8;
        r4 = r0.getHeight();	 Catch:{ all -> 0x0176 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0176 }
        r8[r3] = r4;	 Catch:{ all -> 0x0176 }
        r3 = java.lang.String.format(r7, r8);	 Catch:{ all -> 0x0176 }
        android.util.Log.d(r6, r3);	 Catch:{ all -> 0x0176 }
        goto L_0x00b7;
    L_0x0151:
        if (r4 == 0) goto L_0x0174;
    L_0x0153:
        r3 = r11.mCreatingReprocessSession;	 Catch:{ all -> 0x0176 }
        if (r3 == 0) goto L_0x0160;
    L_0x0157:
        r12 = TAG;	 Catch:{ all -> 0x0176 }
        r0 = "creating reprocess session...";
        android.util.Log.d(r12, r0);	 Catch:{ all -> 0x0176 }
        monitor-exit(r2);	 Catch:{ all -> 0x0176 }
        return r5;
    L_0x0160:
        r11.mCreatingReprocessSession = r5;	 Catch:{ all -> 0x0176 }
        r11.mInputConfiguration = r1;	 Catch:{ all -> 0x0176 }
        if (r12 == 0) goto L_0x0169;
    L_0x0166:
        r11.mJpegOutputConfiguration = r0;	 Catch:{ all -> 0x0176 }
        goto L_0x016b;
    L_0x0169:
        r11.mYuvOutputConfiguration = r0;	 Catch:{ all -> 0x0176 }
    L_0x016b:
        r12 = r11.mInputConfiguration;	 Catch:{ all -> 0x0176 }
        r0 = r11.mYuvOutputConfiguration;	 Catch:{ all -> 0x0176 }
        r1 = r11.mJpegOutputConfiguration;	 Catch:{ all -> 0x0176 }
        r11.createReprocessSession(r12, r0, r1);	 Catch:{ all -> 0x0176 }
    L_0x0174:
        monitor-exit(r2);	 Catch:{ all -> 0x0176 }
        return r4;
    L_0x0176:
        r12 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0176 }
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.imagecodec.JpegEncoder.createCaptureSessionIfNeed(com.xiaomi.camera.imagecodec.ReprocessData):boolean");
    }

    /* JADX WARNING: Missing block: B:10:0x001b, code skipped:
            if (r1 != null) goto L_0x0025;
     */
    /* JADX WARNING: Missing block: B:11:0x001d, code skipped:
            android.util.Log.w(TAG, "checkConditionIsReady: ignore null request!");
     */
    /* JADX WARNING: Missing block: B:12:0x0024, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:14:0x002b, code skipped:
            return createCaptureSessionIfNeed(r1) ^ 1;
     */
    @android.support.annotation.WorkerThread
    private boolean checkConditionIsReady() {
        /*
        r4 = this;
        r0 = r4.mDataLock;
        monitor-enter(r0);
        r1 = r4.mCurrentProcessingData;	 Catch:{ all -> 0x002c }
        r2 = 0;
        if (r1 == 0) goto L_0x0012;
    L_0x0009:
        r1 = TAG;	 Catch:{ all -> 0x002c }
        r3 = "checkConditionIsReady: processor is busy!";
        android.util.Log.d(r1, r3);	 Catch:{ all -> 0x002c }
        monitor-exit(r0);	 Catch:{ all -> 0x002c }
        return r2;
    L_0x0012:
        r1 = r4.mTaskDataList;	 Catch:{ all -> 0x002c }
        r1 = r1.peek();	 Catch:{ all -> 0x002c }
        r1 = (com.xiaomi.camera.imagecodec.ReprocessData) r1;	 Catch:{ all -> 0x002c }
        monitor-exit(r0);	 Catch:{ all -> 0x002c }
        if (r1 != 0) goto L_0x0025;
    L_0x001d:
        r0 = TAG;
        r1 = "checkConditionIsReady: ignore null request!";
        android.util.Log.w(r0, r1);
        return r2;
    L_0x0025:
        r0 = r4.createCaptureSessionIfNeed(r1);
        r0 = r0 ^ 1;
        return r0;
    L_0x002c:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x002c }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.imagecodec.JpegEncoder.checkConditionIsReady():boolean");
    }

    /* JADX WARNING: Missing block: B:10:0x005b, code skipped:
            r8.mReprocessStartTime = java.lang.System.currentTimeMillis();
            r5 = r8.mCameraLock;
     */
    /* JADX WARNING: Missing block: B:11:0x0063, code skipped:
            monitor-enter(r5);
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r0 = r8.mVTCameraDevice.createReprocessCaptureRequest(r1);
     */
    /* JADX WARNING: Missing block: B:14:0x006c, code skipped:
            if (256 != r3) goto L_0x0081;
     */
    /* JADX WARNING: Missing block: B:15:0x006e, code skipped:
            r0.addTarget(r8.mJpegImageReader.getSurface());
            r0.set(android.hardware.camera2.CaptureRequest.JPEG_QUALITY, java.lang.Byte.valueOf(r2));
     */
    /* JADX WARNING: Missing block: B:16:0x0081, code skipped:
            r0.addTarget(r8.mYuvImageReader.getSurface());
     */
    /* JADX WARNING: Missing block: B:17:0x008a, code skipped:
            r1 = TAG;
            r2 = new java.lang.StringBuilder();
            r2.append("reprocessImage: ");
            r2.append(r4);
            r2.append(" | ");
            r2.append(r4.getTimestamp());
            android.util.Log.d(r1, r2.toString());
            r8.mReprocessImageWriter.queueInputImage(r4);
            com.xiaomi.camera.imagecodec.ImagePool.getInstance().releaseImage(r4);
            r8.mVTCaptureSession.capture(r0.build(), new com.xiaomi.camera.imagecodec.JpegEncoder.AnonymousClass1(r8), r8.mCameraOperationHandler);
     */
    /* JADX WARNING: Missing block: B:19:0x00cb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r0.printStackTrace();
     */
    @android.support.annotation.WorkerThread
    private void reprocessImage() {
        /*
        r8 = this;
        r0 = TAG;
        r1 = "reprocessImage>>";
        android.util.Log.d(r0, r1);
        r0 = r8.mDataLock;
        monitor-enter(r0);
        r1 = r8.mTaskDataList;	 Catch:{ all -> 0x00da }
        r1 = r1.poll();	 Catch:{ all -> 0x00da }
        r1 = (com.xiaomi.camera.imagecodec.ReprocessData) r1;	 Catch:{ all -> 0x00da }
        r8.mCurrentProcessingData = r1;	 Catch:{ all -> 0x00da }
        r1 = r8.mCurrentProcessingData;	 Catch:{ all -> 0x00da }
        r1 = r1.getTotalCaptureResult();	 Catch:{ all -> 0x00da }
        r2 = r8.mCurrentSessionId;	 Catch:{ all -> 0x00da }
        r1 = com.xiaomi.protocol.ICustomCaptureResult.toTotalCaptureResult(r1, r2);	 Catch:{ all -> 0x00da }
        if (r1 != 0) goto L_0x002b;
    L_0x0022:
        r1 = TAG;	 Catch:{ all -> 0x00da }
        r2 = "reprocessImage<<null metadata!";
        android.util.Log.wtf(r1, r2);	 Catch:{ all -> 0x00da }
        monitor-exit(r0);	 Catch:{ all -> 0x00da }
        return;
    L_0x002b:
        r2 = TAG;	 Catch:{ all -> 0x00da }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r3.<init>();	 Catch:{ all -> 0x00da }
        r4 = "reprocessImage: tag=";
        r3.append(r4);	 Catch:{ all -> 0x00da }
        r4 = r8.mCurrentProcessingData;	 Catch:{ all -> 0x00da }
        r4 = r4.getImageTag();	 Catch:{ all -> 0x00da }
        r3.append(r4);	 Catch:{ all -> 0x00da }
        r3 = r3.toString();	 Catch:{ all -> 0x00da }
        android.util.Log.d(r2, r3);	 Catch:{ all -> 0x00da }
        r2 = r8.mCurrentProcessingData;	 Catch:{ all -> 0x00da }
        r2 = r2.getJpegQuality();	 Catch:{ all -> 0x00da }
        r2 = (byte) r2;	 Catch:{ all -> 0x00da }
        r3 = r8.mCurrentProcessingData;	 Catch:{ all -> 0x00da }
        r3 = r3.getOutputFormat();	 Catch:{ all -> 0x00da }
        r4 = r8.mCurrentProcessingData;	 Catch:{ all -> 0x00da }
        r4 = r4.getYuvImage();	 Catch:{ all -> 0x00da }
        monitor-exit(r0);	 Catch:{ all -> 0x00da }
        r5 = java.lang.System.currentTimeMillis();
        r8.mReprocessStartTime = r5;
        r5 = r8.mCameraLock;
        monitor-enter(r5);
        r0 = r8.mVTCameraDevice;	 Catch:{ CameraAccessException -> 0x00cb }
        r0 = r0.createReprocessCaptureRequest(r1);	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r1 != r3) goto L_0x0081;
    L_0x006e:
        r1 = r8.mJpegImageReader;	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = r1.getSurface();	 Catch:{ CameraAccessException -> 0x00cb }
        r0.addTarget(r1);	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = android.hardware.camera2.CaptureRequest.JPEG_QUALITY;	 Catch:{ CameraAccessException -> 0x00cb }
        r2 = java.lang.Byte.valueOf(r2);	 Catch:{ CameraAccessException -> 0x00cb }
        r0.set(r1, r2);	 Catch:{ CameraAccessException -> 0x00cb }
        goto L_0x008a;
    L_0x0081:
        r1 = r8.mYuvImageReader;	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = r1.getSurface();	 Catch:{ CameraAccessException -> 0x00cb }
        r0.addTarget(r1);	 Catch:{ CameraAccessException -> 0x00cb }
    L_0x008a:
        r1 = TAG;	 Catch:{ CameraAccessException -> 0x00cb }
        r2 = new java.lang.StringBuilder;	 Catch:{ CameraAccessException -> 0x00cb }
        r2.<init>();	 Catch:{ CameraAccessException -> 0x00cb }
        r3 = "reprocessImage: ";
        r2.append(r3);	 Catch:{ CameraAccessException -> 0x00cb }
        r2.append(r4);	 Catch:{ CameraAccessException -> 0x00cb }
        r3 = " | ";
        r2.append(r3);	 Catch:{ CameraAccessException -> 0x00cb }
        r6 = r4.getTimestamp();	 Catch:{ CameraAccessException -> 0x00cb }
        r2.append(r6);	 Catch:{ CameraAccessException -> 0x00cb }
        r2 = r2.toString();	 Catch:{ CameraAccessException -> 0x00cb }
        android.util.Log.d(r1, r2);	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = r8.mReprocessImageWriter;	 Catch:{ CameraAccessException -> 0x00cb }
        r1.queueInputImage(r4);	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = com.xiaomi.camera.imagecodec.ImagePool.getInstance();	 Catch:{ CameraAccessException -> 0x00cb }
        r1.releaseImage(r4);	 Catch:{ CameraAccessException -> 0x00cb }
        r1 = new com.xiaomi.camera.imagecodec.JpegEncoder$1;	 Catch:{ CameraAccessException -> 0x00cb }
        r1.<init>();	 Catch:{ CameraAccessException -> 0x00cb }
        r2 = r8.mVTCaptureSession;	 Catch:{ CameraAccessException -> 0x00cb }
        r0 = r0.build();	 Catch:{ CameraAccessException -> 0x00cb }
        r3 = r8.mCameraOperationHandler;	 Catch:{ CameraAccessException -> 0x00cb }
        r2.capture(r0, r1, r3);	 Catch:{ CameraAccessException -> 0x00cb }
        goto L_0x00cf;
    L_0x00c9:
        r0 = move-exception;
        goto L_0x00d8;
    L_0x00cb:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00c9 }
    L_0x00cf:
        monitor-exit(r5);	 Catch:{ all -> 0x00c9 }
        r0 = TAG;
        r1 = "reprocessImage<<";
        android.util.Log.d(r0, r1);
        return;
    L_0x00d8:
        monitor-exit(r5);	 Catch:{ all -> 0x00c9 }
        throw r0;
    L_0x00da:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00da }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.imagecodec.JpegEncoder.reprocessImage():void");
    }

    @WorkerThread
    @SuppressLint({"MissingPermission"})
    private void openVTCamera(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("openVTCamera: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        try {
            this.mCameraManager.openCamera(str, new StateCallback() {
                public void onOpened(@NonNull CameraDevice cameraDevice) {
                    synchronized (JpegEncoder.this.mCameraLock) {
                        String access$100 = JpegEncoder.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onOpened>>id=");
                        stringBuilder.append(cameraDevice.getId());
                        Log.d(access$100, stringBuilder.toString());
                        JpegEncoder.this.mVTCameraDevice = cameraDevice;
                        String access$1002 = JpegEncoder.TAG;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("onOpened: ");
                        stringBuilder2.append(JpegEncoder.this.mVTCameraDevice);
                        Log.d(access$1002, stringBuilder2.toString());
                        JpegEncoder.this.mCurrentSessionId = -1;
                        JpegEncoder.this.mVTCaptureSession = null;
                        JpegEncoder.this.mCreatingReprocessSession = false;
                        JpegEncoder.this.sendReprocessRequest();
                        Log.d(JpegEncoder.TAG, "onOpened<<");
                    }
                }

                public void onClosed(@NonNull CameraDevice cameraDevice) {
                    synchronized (JpegEncoder.this.mCameraLock) {
                        String access$100 = JpegEncoder.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onClosed>>id=");
                        stringBuilder.append(cameraDevice.getId());
                        Log.d(access$100, stringBuilder.toString());
                        JpegEncoder.this.mCreatingReprocessSession = false;
                        if (cameraDevice == JpegEncoder.this.mVTCameraDevice) {
                            String access$1002 = JpegEncoder.TAG;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("onClosed: ");
                            stringBuilder2.append(JpegEncoder.this.mVTCameraDevice);
                            Log.d(access$1002, stringBuilder2.toString());
                            JpegEncoder.this.mVTCameraDevice = null;
                        }
                        if (JpegEncoder.this.mNeedReopenCamera) {
                            JpegEncoder.this.sendReprocessRequest();
                            JpegEncoder.this.mNeedReopenCamera = false;
                        }
                        Log.d(JpegEncoder.TAG, "onClosed<<");
                    }
                }

                public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                    synchronized (JpegEncoder.this.mCameraLock) {
                        String access$100 = JpegEncoder.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onDisconnected>>id=");
                        stringBuilder.append(cameraDevice.getId());
                        Log.d(access$100, stringBuilder.toString());
                        cameraDevice.close();
                        JpegEncoder.this.mCreatingReprocessSession = false;
                        if (cameraDevice == JpegEncoder.this.mVTCameraDevice) {
                            String access$1002 = JpegEncoder.TAG;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("onDisconnected: ");
                            stringBuilder2.append(JpegEncoder.this.mVTCameraDevice);
                            Log.d(access$1002, stringBuilder2.toString());
                            JpegEncoder.this.mVTCameraDevice = null;
                        }
                        Log.d(JpegEncoder.TAG, "onDisconnected<<");
                    }
                }

                public void onError(@NonNull CameraDevice cameraDevice, int i) {
                    synchronized (JpegEncoder.this.mCameraLock) {
                        String access$100 = JpegEncoder.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onError>>id=");
                        stringBuilder.append(cameraDevice.getId());
                        Log.e(access$100, stringBuilder.toString());
                        cameraDevice.close();
                        JpegEncoder.this.mCreatingReprocessSession = false;
                        if (cameraDevice == JpegEncoder.this.mVTCameraDevice) {
                            String access$1002 = JpegEncoder.TAG;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("onError: ");
                            stringBuilder2.append(JpegEncoder.this.mVTCameraDevice);
                            Log.d(access$1002, stringBuilder2.toString());
                            JpegEncoder.this.mVTCameraDevice = null;
                        }
                        Log.e(JpegEncoder.TAG, "onError<<");
                    }
                }
            }, this.mCameraOperationHandler);
        } catch (CameraAccessException e) {
            Log.e(TAG, "openVTCamera: open camera failed! No permission Exception.", e);
        }
    }

    @WorkerThread
    private void createReprocessSession(@NonNull InputConfiguration inputConfiguration, @NonNull OutputConfiguration outputConfiguration, @NonNull OutputConfiguration outputConfiguration2) {
        Log.d(TAG, String.format(Locale.ENGLISH, "createReprocessSession>>input[%dx%d] output[%dx%d]", new Object[]{Integer.valueOf(inputConfiguration.getWidth()), Integer.valueOf(inputConfiguration.getHeight()), Integer.valueOf(outputConfiguration2.getWidth()), Integer.valueOf(outputConfiguration2.getHeight())}));
        initYuvImageReader(outputConfiguration.getWidth(), outputConfiguration.getHeight());
        initJpegImageReader(outputConfiguration2.getWidth(), outputConfiguration2.getHeight());
        try {
            List asList;
            if (outputConfiguration.getWidth() <= 0 || this.mYuvImageReader == null) {
                asList = Arrays.asList(new Surface[]{this.mJpegImageReader.getSurface()});
            } else {
                asList = Arrays.asList(new Surface[]{this.mJpegImageReader.getSurface(), this.mYuvImageReader.getSurface()});
            }
            this.mVTCameraDevice.createReprocessableCaptureSession(new InputConfiguration(inputConfiguration.getWidth(), inputConfiguration.getHeight(), inputConfiguration.getFormat()), asList, new CameraCaptureSession.StateCallback() {
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Log.d(JpegEncoder.TAG, "onConfigured>>");
                    synchronized (JpegEncoder.this.mCameraLock) {
                        if (JpegEncoder.this.mVTCameraDevice == null) {
                            Log.e(JpegEncoder.TAG, "onConfigured: null camera device");
                            JpegEncoder.this.mCreatingReprocessSession = false;
                            return;
                        }
                        JpegEncoder.this.mVTCaptureSession = cameraCaptureSession;
                        
/*
Method generation error in method: com.xiaomi.camera.imagecodec.JpegEncoder.3.onConfigured(android.hardware.camera2.CameraCaptureSession):void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0030: INVOKE  (wrap: com.xiaomi.camera.imagecodec.JpegEncoder
  0x002e: IGET  (r1_5 com.xiaomi.camera.imagecodec.JpegEncoder) = (r9_0 'this' com.xiaomi.camera.imagecodec.JpegEncoder$3 A:{THIS}) com.xiaomi.camera.imagecodec.JpegEncoder.3.this$0 com.xiaomi.camera.imagecodec.JpegEncoder) com.xiaomi.camera.imagecodec.JpegEncoder.access$508(com.xiaomi.camera.imagecodec.JpegEncoder):int type: STATIC in method: com.xiaomi.camera.imagecodec.JpegEncoder.3.onConfigured(android.hardware.camera2.CameraCaptureSession):void, dex: 
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:228)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:205)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:102)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:52)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:230)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:67)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:321)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:259)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:221)
	at jadx.core.codegen.InsnGen.inlineAnonymousConstr(InsnGen.java:610)
	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:561)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:336)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:101)
	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:688)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:658)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:340)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:222)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:205)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:102)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:52)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:280)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:321)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:259)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:221)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:111)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:77)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:10)
	at jadx.core.ProcessClass.process(ProcessClass.java:38)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:63)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:317)
	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:298)
	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:21)
	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:6)
	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:763)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:619)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:340)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:222)
	... 50 more
Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Unknown Source)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:60)
	... 68 more

*/

                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Log.d(JpegEncoder.TAG, "onConfigureFailed");
                    synchronized (JpegEncoder.this.mCameraLock) {
                        JpegEncoder.this.mCreatingReprocessSession = false;
                        JpegEncoder.this.mVTCaptureSession = null;
                        JpegEncoder.this.mReprocessImageWriter = null;
                    }
                }
            }, this.mCameraOperationHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "createReprocessSession<<");
    }

    @WorkerThread
    private void initJpegImageReader(int i, int i2) {
        Log.d(TAG, "initJpegImageReader>>");
        if (!(this.mJpegImageReader == null || (this.mJpegImageReader.getWidth() == i && this.mJpegImageReader.getHeight() == i2))) {
            Log.d(TAG, "closing obsolete reprocess reader");
            this.mJpegImageReader.close();
        }
        this.mJpegImageReader = ImageReader.newInstance(i, i2, 256, 2);
        this.mJpegImageReader.setOnImageAvailableListener(new OnImageAvailableListener() {
            public void onImageAvailable(ImageReader imageReader) {
                Image acquireNextImage = imageReader.acquireNextImage();
                String access$100 = JpegEncoder.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onImageAvailable: received reprocessed image");
                stringBuilder.append(acquireNextImage);
                Log.d(access$100, stringBuilder.toString());
                byte[] access$1200 = JpegEncoder.getJpegData(acquireNextImage);
                acquireNextImage.close();
                synchronized (JpegEncoder.this.mDataLock) {
                    JpegEncoder.this.mCurrentProcessingData.getResultListener().onJpegAvailable(access$1200, JpegEncoder.this.mCurrentProcessingData.getImageTag());
                    Log.d(JpegEncoder.TAG, String.format("jpeg return for %s. cost=%d", new Object[]{JpegEncoder.this.mCurrentProcessingData.getImageTag(), Long.valueOf(System.currentTimeMillis() - JpegEncoder.this.mReprocessStartTime)}));
                    JpegEncoder.this.mCurrentProcessingData = null;
                }
                JpegEncoder.this.sendReprocessRequest();
            }
        }, this.mCameraOperationHandler);
        Log.d(TAG, "initJpegImageReader<<");
    }

    @WorkerThread
    private void initYuvImageReader(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("initYuvImageReader>>");
        stringBuilder.append(i);
        stringBuilder.append("x");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        if (!(this.mYuvImageReader == null || (this.mYuvImageReader.getWidth() == i && this.mYuvImageReader.getHeight() == i2))) {
            Log.d(TAG, "closing obsolete yuv reader");
            this.mYuvImageReader.close();
            this.mYuvImageReader = null;
        }
        if (i > 0 && i2 > 0) {
            this.mYuvImageReader = ImageReader.newInstance(i, i2, 35, 2);
            this.mYuvImageReader.setOnImageAvailableListener(new OnImageAvailableListener() {
                public void onImageAvailable(ImageReader imageReader) {
                    Image acquireNextImage = imageReader.acquireNextImage();
                    long timeStamp = JpegEncoder.this.mCurrentProcessingData.getTotalCaptureResult().getTimeStamp();
                    acquireNextImage.setTimestamp(timeStamp);
                    ImagePool.getInstance().queueImage(acquireNextImage);
                    acquireNextImage = ImagePool.getInstance().getImage(timeStamp);
                    String access$100 = JpegEncoder.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("receive yuv image: ");
                    stringBuilder.append(acquireNextImage);
                    stringBuilder.append(" | ");
                    stringBuilder.append(timeStamp);
                    Log.d(access$100, stringBuilder.toString());
                    ImagePool.getInstance().holdImage(acquireNextImage);
                    synchronized (JpegEncoder.this.mDataLock) {
                        JpegEncoder.this.mCurrentProcessingData.getResultListener().onYuvAvailable(acquireNextImage, JpegEncoder.this.mCurrentProcessingData.getImageTag());
                        Log.d(JpegEncoder.TAG, String.format("yuv return for %s. cost=%d", new Object[]{JpegEncoder.this.mCurrentProcessingData.getImageTag(), Long.valueOf(System.currentTimeMillis() - JpegEncoder.this.mReprocessStartTime)}));
                        JpegEncoder.this.mCurrentProcessingData = null;
                    }
                    JpegEncoder.this.sendReprocessRequest();
                }
            }, this.mCameraOperationHandler);
            Log.d(TAG, "initYuvImageReader<<");
        }
    }

    private static int getSessionId(CameraCaptureSession cameraCaptureSession) {
        if (cameraCaptureSession != null) {
            try {
                Field declaredField = Class.forName("android.hardware.camera2.impl.CameraCaptureSessionImpl").getDeclaredField("mId");
                declaredField.setAccessible(true);
                return declaredField.getInt(cameraCaptureSession);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                Log.w(TAG, "getSessionId: failed! ", e);
            }
        }
        return -1;
    }

    private static byte[] getJpegData(Image image) {
        Plane[] planes = image.getPlanes();
        if (planes.length <= 0) {
            return null;
        }
        ByteBuffer buffer = planes[0].getBuffer();
        byte[] bArr = new byte[buffer.remaining()];
        buffer.get(bArr);
        return bArr;
    }
}
