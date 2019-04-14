package com.android.zxing;

import android.annotation.SuppressLint;
import android.media.Image;
import com.android.camera.CameraSettings;
import com.android.camera.handgesture.HandGesture;
import com.android.camera.log.Log;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HandGestureDecoder extends Decoder {
    private static boolean DEBUG = false;
    public static final String TAG = "HandGestureDecoder";
    int mCameraId;
    HandGesture mHandGesture = new HandGesture();
    boolean mTriggeringPhoto = false;

    @SuppressLint({"CheckResult"})
    HandGestureDecoder() {
        DECODE_MAX_COUNT = 600;
        DECODE_AUTO_INTERVAL = 500;
        this.mEnable = true;
        Flowable.create(new FlowableOnSubscribe<PreviewImage>() {
            public void subscribe(FlowableEmitter<PreviewImage> flowableEmitter) throws Exception {
                HandGestureDecoder.this.mDecodeFlowableEmitter = flowableEmitter;
            }
        }, BackpressureStrategy.LATEST).observeOn(Schedulers.single()).map(new Function<PreviewImage, Boolean>() {
            public Boolean apply(PreviewImage previewImage) {
                switch (previewImage.getPreviewStatus()) {
                    case 1:
                        HandGestureDecoder.this.mHandGesture.init(previewImage.getCameraId());
                        break;
                    case 2:
                        if (HandGestureDecoder.DEBUG) {
                            HandGestureDecoder.this.dumpPreviewImage(previewImage);
                        }
                        return HandGestureDecoder.this.decode(previewImage);
                    case 3:
                        HandGestureDecoder.this.mHandGesture.unInit();
                        break;
                }
                return Boolean.valueOf(false);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                String str = HandGestureDecoder.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onSuccess: result = ");
                stringBuilder.append(bool);
                Log.d(str, stringBuilder.toString());
                if (bool.booleanValue() && !HandGestureDecoder.this.mTriggeringPhoto) {
                    Log.d(HandGestureDecoder.TAG, "Triggering countdown...");
                    CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
                    if (!(cameraAction == null || cameraAction.isDoingAction())) {
                        cameraAction.onShutterButtonClick(100);
                    }
                    HandGestureDecoder.this.mTriggeringPhoto = true;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Log.d(HandGestureDecoder.TAG, "onError: ", th);
            }
        });
    }

    public boolean needPreviewFrame() {
        return super.needPreviewFrame() && CameraSettings.isHangGestureOpen();
    }

    public void init(int i) {
        reset();
        this.mCameraId = i;
        this.mDecodeFlowableEmitter.onNext(new PreviewImage(1, i));
    }

    public void startDecode() {
        this.mDecoding = true;
        this.mDecodingCount.set(0);
    }

    /* Access modifiers changed, original: protected */
    public Boolean decode(PreviewImage previewImage) {
        int orientation = previewImage.getOrientation();
        boolean z = true;
        if (this.mCameraId == 1) {
            orientation = 270 - orientation;
        } else {
            orientation = (90 + orientation) % 360;
        }
        if (this.mHandGesture.detectGesture(previewImage.getData(), previewImage.getWidth(), previewImage.getHeight(), orientation) <= 0) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public void reset() {
        Log.d(TAG, "Reset");
        this.mTriggeringPhoto = false;
        this.mDecodingCount.set(0);
    }

    public void quit() {
        super.quit();
        this.mDecodeFlowableEmitter.onNext(new PreviewImage(3, this.mCameraId));
        this.mDecodeFlowableEmitter.onComplete();
    }

    public void onPreviewFrame(Image image, int i, int i2, int i3) {
        if (needPreviewFrame() && this.mDecodeFlowableEmitter != null) {
            this.mDecodeFlowableEmitter.onNext(new PreviewImage(image, i3));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0092 A:{SYNTHETIC, Splitter:B:21:0x0092} */
    private void dumpPreviewImage(com.android.zxing.PreviewImage r13) {
        /*
        r12 = this;
        r0 = 0;
        r1 = r13.getTimestamp();	 Catch:{ IOException -> 0x0076 }
        r9 = r13.getWidth();	 Catch:{ IOException -> 0x0076 }
        r10 = r13.getHeight();	 Catch:{ IOException -> 0x0076 }
        r11 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0076 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0076 }
        r3.<init>();	 Catch:{ IOException -> 0x0076 }
        r4 = miui.os.Environment.getExternalStorageDirectory();	 Catch:{ IOException -> 0x0076 }
        r3.append(r4);	 Catch:{ IOException -> 0x0076 }
        r4 = "/DCIM/Camera/hand_";
        r3.append(r4);	 Catch:{ IOException -> 0x0076 }
        r4 = java.lang.String.valueOf(r1);	 Catch:{ IOException -> 0x0076 }
        r3.append(r4);	 Catch:{ IOException -> 0x0076 }
        r4 = ".jpg";
        r3.append(r4);	 Catch:{ IOException -> 0x0076 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0076 }
        r11.<init>(r3);	 Catch:{ IOException -> 0x0076 }
        r0 = "HandGestureDecoder";
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r3.<init>();	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r4 = "PreviewImage timestamp: [";
        r3.append(r4);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r3.append(r1);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r1 = "]";
        r3.append(r1);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r1 = r3.toString();	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        com.android.camera.log.Log.d(r0, r1);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r0 = new android.graphics.YuvImage;	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r4 = r13.getData();	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r5 = 17;
        r8 = 0;
        r3 = r0;
        r6 = r9;
        r7 = r10;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r13 = new android.graphics.Rect;	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r1 = 0;
        r13.<init>(r1, r1, r9, r10);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r1 = 100;
        r0.compressToJpeg(r13, r1, r11);	 Catch:{ IOException -> 0x0071, all -> 0x006e }
        r11.close();	 Catch:{ IOException -> 0x0084 }
        goto L_0x008d;
    L_0x006e:
        r13 = move-exception;
        r0 = r11;
        goto L_0x008f;
    L_0x0071:
        r13 = move-exception;
        r0 = r11;
        goto L_0x0077;
    L_0x0074:
        r13 = move-exception;
        goto L_0x008f;
    L_0x0076:
        r13 = move-exception;
    L_0x0077:
        r1 = "HandGestureDecoder";
        r2 = "Dump preview Image failed!";
        com.android.camera.log.Log.e(r1, r2, r13);	 Catch:{ all -> 0x0074 }
        if (r0 == 0) goto L_0x008d;
    L_0x0080:
        r0.close();	 Catch:{ IOException -> 0x0084 }
        goto L_0x008d;
    L_0x0084:
        r13 = move-exception;
        r0 = "HandGestureDecoder";
        r1 = "Close stream failed!";
        com.android.camera.log.Log.e(r0, r1, r13);
        goto L_0x008e;
    L_0x008e:
        return;
        if (r0 == 0) goto L_0x009f;
    L_0x0092:
        r0.close();	 Catch:{ IOException -> 0x0096 }
        goto L_0x009f;
    L_0x0096:
        r0 = move-exception;
        r1 = "HandGestureDecoder";
        r2 = "Close stream failed!";
        com.android.camera.log.Log.e(r1, r2, r0);
    L_0x009f:
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.zxing.HandGestureDecoder.dumpPreviewImage(com.android.zxing.PreviewImage):void");
    }
}
