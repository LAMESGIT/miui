package com.android.camera.module.loader.camera2;

import com.android.camera.data.DataRepository;
import com.android.camera.data.data.global.DataItemGlobal;
import com.android.camera.log.Log;
import com.android.camera.module.ModuleManager;
import com.android.camera.module.loader.SurfaceCreatedCallback;
import com.android.camera.module.loader.SurfaceStateListener;
import com.android.camera.snap.SnapTrigger;
import io.reactivex.Observer;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

public class Camera2OpenOnSubScribe implements SurfaceCreatedCallback, Observer<Camera2Result>, SingleOnSubscribe<Camera2Result> {
    private static final String TAG = "Camera2OpenOnSubScribe";
    private Camera2Result mCamera2Result;
    private SingleEmitter<Camera2Result> mSingleEmitter;
    private SurfaceStateListener mSurfaceStateListener;

    public Camera2OpenOnSubScribe(SurfaceStateListener surfaceStateListener) {
        this.mSurfaceStateListener = surfaceStateListener;
    }

    public void subscribe(SingleEmitter<Camera2Result> singleEmitter) throws Exception {
        this.mCamera2Result = null;
        this.mSingleEmitter = singleEmitter;
        openCamera();
    }

    /* JADX WARNING: Missing block: B:8:0x002f, code skipped:
            return;
     */
    public void onGlSurfaceCreated() {
        /*
        r3 = this;
        r0 = "Camera2OpenOnSubScribe";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "onGlSurfaceCreated: mSingleEmitter = ";
        r1.append(r2);
        r2 = r3.mSingleEmitter;
        r1.append(r2);
        r1 = r1.toString();
        com.android.camera.log.Log.d(r0, r1);
        r0 = r3.mSingleEmitter;
        if (r0 == 0) goto L_0x002f;
    L_0x001c:
        r0 = r3.mSingleEmitter;
        r0 = r0.isDisposed();
        if (r0 == 0) goto L_0x0025;
    L_0x0024:
        goto L_0x002f;
    L_0x0025:
        r0 = r3.mCamera2Result;
        if (r0 == 0) goto L_0x002e;
    L_0x0029:
        r0 = r3.mCamera2Result;
        r3.submitResult(r0);
    L_0x002e:
        return;
    L_0x002f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.loader.camera2.Camera2OpenOnSubScribe.onGlSurfaceCreated():void");
    }

    private void openCamera() {
        if (SnapTrigger.getInstance().isRunning()) {
            SnapTrigger.getInstance();
            SnapTrigger.destroy();
        }
        DataItemGlobal dataItemGlobal = DataRepository.dataItemGlobal();
        Camera2OpenManager.getInstance().openCamera(dataItemGlobal.getCurrentCameraId(), dataItemGlobal.getCurrentMode(), this, false);
    }

    private void submitResult(Camera2Result camera2Result) {
        if (this.mSingleEmitter != null) {
            this.mSingleEmitter.onSuccess(camera2Result);
            this.mSingleEmitter = null;
        }
    }

    public void onSubscribe(Disposable disposable) {
    }

    public void onNext(Camera2Result camera2Result) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onNext: mSurfaceStateListener = ");
        stringBuilder.append(this.mSurfaceStateListener.hasSurface());
        Log.d(str, stringBuilder.toString());
        this.mCamera2Result = camera2Result;
        if (ModuleManager.isCapture() || this.mSurfaceStateListener.hasSurface()) {
            submitResult(camera2Result);
        }
    }

    public void onError(Throwable th) {
        this.mCamera2Result = Camera2Result.create(3);
        submitResult(this.mCamera2Result);
    }

    public void onComplete() {
    }
}
