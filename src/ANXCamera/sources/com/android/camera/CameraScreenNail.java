package com.android.camera;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.android.camera.SurfaceTextureScreenNail.SurfaceTextureScreenNailCallback;
import com.android.camera.effect.FrameBuffer;
import com.android.camera.effect.draw_mode.DrawBasicTexAttribute;
import com.android.camera.effect.draw_mode.DrawBlurTexAttribute;
import com.android.camera.effect.draw_mode.DrawExtTexAttribute;
import com.android.camera.log.Log;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.android.gallery3d.ui.BitmapTexture;
import com.android.gallery3d.ui.GLCanvas;
import com.android.gallery3d.ui.RawTexture;
import com.mi.config.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class CameraScreenNail extends SurfaceTextureScreenNail {
    private static final int ANIM_CAPTURE_RUNNING = 12;
    private static final int ANIM_CAPTURE_START = 11;
    private static final int ANIM_MODULE_COPY_TEXTURE = 31;
    private static final int ANIM_MODULE_COPY_TEXTURE_WITH_ALPHA = 37;
    private static final int ANIM_MODULE_DRAW_PREVIEW = 32;
    private static final int ANIM_MODULE_RESUME = 35;
    private static final int ANIM_MODULE_RUNNING = 33;
    private static final int ANIM_MODULE_WAITING_FIRST_FRAME = 34;
    private static final int ANIM_NONE = 0;
    private static final int ANIM_READ_LAST_FRAME_GAUSSIAN = 36;
    private static final int ANIM_SWITCH_COPY_TEXTURE = 21;
    private static final int ANIM_SWITCH_DRAW_PREVIEW = 22;
    private static final int ANIM_SWITCH_RESUME = 25;
    private static final int ANIM_SWITCH_RUNNING = 23;
    private static final int ANIM_SWITCH_WAITING_FIRST_FRAME = 24;
    public static final int FRAME_AVAILABLE_AFTER_CATCH = 4;
    public static final int FRAME_AVAILABLE_ON_CREATE = 1;
    private static final int STATE_HIBERNATE = 14;
    private static final int STATE_READ_PIXELS = 13;
    private static final String TAG = CameraScreenNail.class.getSimpleName();
    private int mAnimState = 0;
    private CaptureAnimManager mCaptureAnimManager = new CaptureAnimManager();
    private boolean mDisableSwitchAnimationOnce;
    private boolean mFirstFrameArrived;
    private AtomicBoolean mFrameAvailableNotified = new AtomicBoolean(false);
    private int mFrameNumber = 0;
    private Bitmap mLastFrameGaussianBitmap;
    private final Object mLock = new Object();
    private SwitchAnimManager mModuleAnimManager = new SwitchAnimManager();
    private NailListener mNailListener;
    private List<RequestRenderListener> mRequestRenderListeners;
    private SwitchAnimManager mSwitchAnimManager = new SwitchAnimManager();
    private final float[] mTextureTransformMatrix = new float[16];
    private boolean mVisible;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrivedType {
    }

    public interface RequestRenderListener {
        void requestRender();
    }

    public interface NailListener extends SurfaceTextureScreenNailCallback {
        int getOrientation();

        boolean isKeptBitmapTexture();

        void onFrameAvailable(int i);

        void onPreviewPixelsRead(byte[] bArr, int i, int i2);

        void onPreviewTextureCopied();
    }

    public CameraScreenNail(NailListener nailListener, RequestRenderListener requestRenderListener) {
        super(nailListener);
        this.mNailListener = nailListener;
        this.mRequestRenderListeners = new ArrayList();
        addRequestListener(requestRenderListener);
    }

    public void addRequestListener(RequestRenderListener requestRenderListener) {
        synchronized (this.mLock) {
            this.mRequestRenderListeners.add(requestRenderListener);
        }
    }

    public void removeRequestListener(RequestRenderListener requestRenderListener) {
        synchronized (this.mLock) {
            this.mRequestRenderListeners.remove(requestRenderListener);
        }
    }

    private void postRequestListener() {
        for (RequestRenderListener requestRender : this.mRequestRenderListeners) {
            requestRender.requestRender();
        }
    }

    public void acquireSurfaceTexture() {
        Log.v(TAG, "acquireSurfaceTexture");
        synchronized (this.mLock) {
            this.mFirstFrameArrived = false;
            this.mFrameNumber = 0;
            this.mDisableSwitchAnimationOnce = false;
            super.acquireSurfaceTexture();
        }
    }

    public void releaseSurfaceTexture() {
        synchronized (this.mLock) {
            super.releaseSurfaceTexture();
            this.mAnimState = 0;
            Log.v(TAG, "release: state=NONE");
            this.mFirstFrameArrived = false;
            this.mFrameNumber = 0;
            this.mModuleSwitching = false;
        }
    }

    public void disableSwitchAnimationOnce() {
        this.mDisableSwitchAnimationOnce = true;
    }

    public void animateModuleCopyTexture(boolean z) {
        synchronized (this.mLock) {
            if (this.mAnimTexture == null || this.mFrameAvailableNotified.get()) {
                if (z) {
                    this.mAnimState = 37;
                    Log.v(TAG, "state=MODULE_COPY_TEXTURE_WITH_ALPHA");
                } else {
                    this.mAnimState = 31;
                    Log.v(TAG, "state=MODULE_COPY_TEXTURE");
                }
                postRequestListener();
                return;
            }
        }
    }

    public void readLastFrameGaussian() {
        Log.d(TAG, "readLastFrameGaussian: state=ANIM_READ_LAST_FRAME_GAUSSIAN start");
        synchronized (this.mLock) {
            if (this.mFirstFrameArrived) {
                if (getSurfaceTexture() != null) {
                    if (this.mAnimTexture == null || this.mFrameAvailableNotified.get()) {
                        this.mAnimState = 36;
                        postRequestListener();
                        Log.d(TAG, "readLastFrameGaussian: state=ANIM_READ_LAST_FRAME_GAUSSIAN end");
                        return;
                    }
                    Log.w(TAG, "readLastFrameGaussian: not start preview return!!!");
                    return;
                }
            }
            Log.w(TAG, "readLastFrameGaussian: not start preview return!!!");
        }
    }

    public Bitmap getLastFrameGaussianBitmap() {
        return this.mLastFrameGaussianBitmap;
    }

    public void clearAnimation() {
        if (this.mAnimState != 0) {
            this.mAnimState = 0;
            this.mSwitchAnimManager.clearAnimation();
            this.mCaptureAnimManager.clearAnimation();
            this.mModuleAnimManager.clearAnimation();
        }
    }

    public void animateSwitchCopyTexture() {
        synchronized (this.mLock) {
            postRequestListener();
            this.mAnimState = 21;
            Log.v(TAG, "state=SWITCH_COPY_TEXTURE");
        }
    }

    public void animateSwitchCameraBefore() {
        synchronized (this.mLock) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("switchBefore: state=");
            stringBuilder.append(this.mAnimState);
            Log.v(str, stringBuilder.toString());
            if (this.mAnimState == 22) {
                this.mAnimState = 23;
                this.mSwitchAnimManager.startAnimation(false);
                postRequestListener();
            }
        }
    }

    public void switchCameraDone() {
        synchronized (this.mLock) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("switchDone: state=");
            stringBuilder.append(this.mAnimState);
            Log.v(str, stringBuilder.toString());
            if (this.mAnimState == 23) {
                this.mAnimState = 24;
            }
        }
    }

    public void animateCapture(int i) {
        synchronized (this.mLock) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("animateCapture: state=");
            stringBuilder.append(this.mAnimState);
            Log.v(str, stringBuilder.toString());
            if (this.mAnimState == 0) {
                this.mCaptureAnimManager.animateHoldAndSlide();
                postRequestListener();
                this.mAnimState = 11;
            }
        }
    }

    public void requestHibernate() {
        synchronized (this.mLock) {
            if (this.mAnimState == 0) {
                this.mAnimState = 14;
                postRequestListener();
            }
        }
    }

    public void requestAwaken() {
        synchronized (this.mLock) {
            if (this.mAnimState == 14) {
                this.mAnimState = 0;
                this.mFirstFrameArrived = false;
                this.mFrameNumber = 0;
            }
        }
    }

    public void requestReadPixels() {
        synchronized (this.mLock) {
            if (this.mAnimState == 0) {
                this.mAnimState = 13;
                postRequestListener();
            }
        }
    }

    public boolean isAnimationRunning() {
        return this.mAnimState != 0;
    }

    public void animateHold(int i) {
        synchronized (this.mLock) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("animateHold: state=");
            stringBuilder.append(this.mAnimState);
            Log.v(str, stringBuilder.toString());
            if (this.mAnimState == 0) {
                this.mCaptureAnimManager.animateHold();
                postRequestListener();
                this.mAnimState = 11;
            }
        }
    }

    public void animateSlide() {
        synchronized (this.mLock) {
            if (this.mAnimState != 12) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Cannot animateSlide outside of animateCapture! Animation state = ");
                stringBuilder.append(this.mAnimState);
                Log.w(str, stringBuilder.toString());
            }
            this.mCaptureAnimManager.animateSlide();
            postRequestListener();
        }
    }

    public void directDraw(GLCanvas gLCanvas, int i, int i2, int i3, int i4) {
        super.draw(gLCanvas, i, i2, i3, i4);
    }

    /* JADX WARNING: Missing block: B:27:0x005d, code skipped:
            r15 = 0;
     */
    /* JADX WARNING: Missing block: B:35:0x00b9, code skipped:
            r8.updateTexImage();
            r15 = 0;
            r0.mSwitchAnimManager.drawPreview(r9, r10, r11, r12, r13, r0.mAnimTexture);
     */
    /* JADX WARNING: Missing block: B:45:0x0126, code skipped:
            if (r0.mAnimState == 23) goto L_0x0184;
     */
    /* JADX WARNING: Missing block: B:47:0x012c, code skipped:
            if (r0.mAnimState == 24) goto L_0x0184;
     */
    /* JADX WARNING: Missing block: B:49:0x0130, code skipped:
            if (r0.mAnimState != 25) goto L_0x0133;
     */
    /* JADX WARNING: Missing block: B:52:0x0137, code skipped:
            if (r0.mAnimState != 12) goto L_0x014e;
     */
    /* JADX WARNING: Missing block: B:54:0x0141, code skipped:
            if (r0.mCaptureAnimManager.drawAnimation(r9, r0.mCaptureAnimTexture) == false) goto L_0x0147;
     */
    /* JADX WARNING: Missing block: B:55:0x0143, code skipped:
            postRequestListener();
     */
    /* JADX WARNING: Missing block: B:56:0x0147, code skipped:
            r0.mAnimState = r15;
            super.draw(r19, r20, r21, r22, r23);
     */
    /* JADX WARNING: Missing block: B:59:0x0154, code skipped:
            if (r0.mAnimState == 33) goto L_0x0160;
     */
    /* JADX WARNING: Missing block: B:61:0x015a, code skipped:
            if (r0.mAnimState == 34) goto L_0x0160;
     */
    /* JADX WARNING: Missing block: B:63:0x015e, code skipped:
            if (r0.mAnimState != 35) goto L_0x01bf;
     */
    /* JADX WARNING: Missing block: B:64:0x0160, code skipped:
            r8.updateTexImage();
            r15 = 35;
     */
    /* JADX WARNING: Missing block: B:65:0x0172, code skipped:
            if (r0.mModuleAnimManager.drawAnimation(r9, r10, r11, r12, r13, r0, r0.mAnimTexture) != false) goto L_0x0180;
     */
    /* JADX WARNING: Missing block: B:67:0x0176, code skipped:
            if (r0.mAnimState == r15) goto L_0x0179;
     */
    /* JADX WARNING: Missing block: B:69:0x0179, code skipped:
            r0.mAnimState = 0;
            super.draw(r19, r20, r21, r22, r23);
     */
    /* JADX WARNING: Missing block: B:70:0x0180, code skipped:
            postRequestListener();
     */
    /* JADX WARNING: Missing block: B:71:0x0184, code skipped:
            r8.updateTexImage();
     */
    /* JADX WARNING: Missing block: B:72:0x018a, code skipped:
            if (r0.mDisableSwitchAnimationOnce == false) goto L_0x019c;
     */
    /* JADX WARNING: Missing block: B:73:0x018c, code skipped:
            r15 = 25;
            r0.mSwitchAnimManager.drawPreview(r9, r10, r11, r12, r13, r0.mAnimTexture);
            r6 = false;
     */
    /* JADX WARNING: Missing block: B:74:0x019c, code skipped:
            r15 = 25;
            r6 = r0.mSwitchAnimManager.drawAnimation(r9, r10, r11, r12, r13, r0, r0.mAnimTexture);
     */
    /* JADX WARNING: Missing block: B:75:0x01ab, code skipped:
            if (r6 != false) goto L_0x01bb;
     */
    /* JADX WARNING: Missing block: B:77:0x01af, code skipped:
            if (r0.mAnimState == r15) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:79:0x01b2, code skipped:
            r0.mAnimState = 0;
            r0.mDisableSwitchAnimationOnce = false;
            super.draw(r19, r20, r21, r22, r23);
     */
    /* JADX WARNING: Missing block: B:80:0x01bb, code skipped:
            postRequestListener();
     */
    /* JADX WARNING: Missing block: B:83:0x01c0, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:88:0x01e7, code skipped:
            return;
     */
    public void draw(com.android.gallery3d.ui.GLCanvas r19, int r20, int r21, int r22, int r23) {
        /*
        r18 = this;
        r0 = r18;
        r9 = r19;
        r10 = r20;
        r11 = r21;
        r12 = r22;
        r13 = r23;
        r14 = r0.mLock;
        monitor-enter(r14);
        r1 = r0.mVisible;	 Catch:{ all -> 0x01e8 }
        r2 = 1;
        if (r1 != 0) goto L_0x0016;
    L_0x0014:
        r0.mVisible = r2;	 Catch:{ all -> 0x01e8 }
    L_0x0016:
        r1 = r0.mBitmapTexture;	 Catch:{ all -> 0x01e8 }
        if (r1 == 0) goto L_0x0045;
        r1 = com.android.camera.module.ModuleManager.isSquareModule();	 Catch:{ all -> 0x01e8 }
        if (r1 == 0) goto L_0x0039;
    L_0x0022:
        if (r12 <= r13) goto L_0x002f;
    L_0x0024:
        r1 = r12 - r13;
        r1 = r1 / 2;
        r1 = r1 + r10;
        r2 = r1;
        r3 = r11;
        r4 = r13;
    L_0x002d:
        r5 = r4;
        goto L_0x003d;
    L_0x002f:
        r1 = r13 - r12;
        r1 = r1 / 2;
        r1 = r1 + r11;
        r3 = r1;
        r2 = r10;
        r4 = r12;
        goto L_0x002d;
    L_0x0039:
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r5 = r13;
    L_0x003d:
        r0 = r0.mBitmapTexture;	 Catch:{ all -> 0x01e8 }
        r1 = r9;
        r0.draw(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x01e8 }
        monitor-exit(r14);	 Catch:{ all -> 0x01e8 }
        return;
    L_0x0045:
        r8 = r18.getSurfaceTexture();	 Catch:{ all -> 0x01e8 }
        if (r8 == 0) goto L_0x01c1;
    L_0x004b:
        r1 = r0.mFirstFrameArrived;	 Catch:{ all -> 0x01e8 }
        if (r1 != 0) goto L_0x0055;
    L_0x004f:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        if (r1 != 0) goto L_0x0055;
    L_0x0053:
        goto L_0x01c1;
    L_0x0055:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r7 = 33;
        r6 = 0;
        switch(r1) {
            case 0: goto L_0x011b;
            case 11: goto L_0x00ff;
            case 13: goto L_0x00d6;
            case 14: goto L_0x00ce;
            case 21: goto L_0x009d;
            case 22: goto L_0x00b9;
            case 31: goto L_0x0077;
            case 36: goto L_0x0060;
            case 37: goto L_0x0077;
            default: goto L_0x005d;
        };	 Catch:{ all -> 0x01e8 }
    L_0x005d:
        r15 = r6;
        goto L_0x0120;
    L_0x0060:
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
        r1 = TAG;	 Catch:{ all -> 0x01e8 }
        r2 = "draw: state=ANIM_READ_LAST_FRAME_GAUSSIAN";
        com.android.camera.log.Log.v(r1, r2);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r0.mFrameBuffer;	 Catch:{ all -> 0x01e8 }
        r0.copyPreviewTexture(r9, r1, r2);	 Catch:{ all -> 0x01e8 }
        r18.drawGaussianBitmap(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
        r0.mAnimState = r6;	 Catch:{ all -> 0x01e8 }
        goto L_0x005d;
    L_0x0077:
        r1 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r3 = r0.mFrameBuffer;	 Catch:{ all -> 0x01e8 }
        r0.copyPreviewTexture(r9, r1, r3);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mModuleAnimManager;	 Catch:{ all -> 0x01e8 }
        r1.setReviewDrawingSize(r10, r11, r12, r13);	 Catch:{ all -> 0x01e8 }
        r1 = TAG;	 Catch:{ all -> 0x01e8 }
        r3 = "draw: state=MODULE_DRAW_PREVIEW";
        com.android.camera.log.Log.v(r1, r3);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r3 = 37;
        if (r1 != r3) goto L_0x0091;
    L_0x0090:
        goto L_0x0092;
    L_0x0091:
        r2 = r6;
    L_0x0092:
        r0.mAnimState = r7;	 Catch:{ all -> 0x01e8 }
        r1 = r0.mModuleAnimManager;	 Catch:{ all -> 0x01e8 }
        r1.startAnimation(r2);	 Catch:{ all -> 0x01e8 }
        r18.postRequestListener();	 Catch:{ all -> 0x01e8 }
        goto L_0x005d;
    L_0x009d:
        r1 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r0.mFrameBuffer;	 Catch:{ all -> 0x01e8 }
        r0.copyPreviewTexture(r9, r1, r2);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mSwitchAnimManager;	 Catch:{ all -> 0x01e8 }
        r1.setReviewDrawingSize(r10, r11, r12, r13);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mNailListener;	 Catch:{ all -> 0x01e8 }
        r1.onPreviewTextureCopied();	 Catch:{ all -> 0x01e8 }
        r1 = 22;
        r0.mAnimState = r1;	 Catch:{ all -> 0x01e8 }
        r1 = TAG;	 Catch:{ all -> 0x01e8 }
        r2 = "draw: state=SWITCH_DRAW_PREVIEW";
        com.android.camera.log.Log.v(r1, r2);	 Catch:{ all -> 0x01e8 }
    L_0x00b9:
        r8.updateTexImage();	 Catch:{ all -> 0x01e8 }
        r1 = r0.mSwitchAnimManager;	 Catch:{ all -> 0x01e8 }
        r5 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r9;
        r3 = r10;
        r4 = r11;
        r16 = r5;
        r5 = r12;
        r15 = r6;
        r6 = r13;
        r7 = r16;
        r1.drawPreview(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x01e8 }
        goto L_0x0120;
    L_0x00ce:
        r15 = r6;
        r8.updateTexImage();	 Catch:{ all -> 0x01e8 }
        r19.clearBuffer();	 Catch:{ all -> 0x01e8 }
        goto L_0x0120;
    L_0x00d6:
        r15 = r6;
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mCaptureAnimTexture;	 Catch:{ all -> 0x01e8 }
        r1 = r1.getWidth();	 Catch:{ all -> 0x01e8 }
        r2 = r0.mCaptureAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r2.getHeight();	 Catch:{ all -> 0x01e8 }
        r3 = r12 * r2;
        r4 = r13 * r1;
        if (r3 <= r4) goto L_0x00f0;
        r2 = r4 / r12;
        goto L_0x00f3;
    L_0x00f0:
        r1 = r3 / r13;
    L_0x00f3:
        r3 = r0.readPreviewPixels(r9, r1, r2);	 Catch:{ all -> 0x01e8 }
        r0.mAnimState = r15;	 Catch:{ all -> 0x01e8 }
        r4 = r0.mNailListener;	 Catch:{ all -> 0x01e8 }
        r4.onPreviewPixelsRead(r3, r1, r2);	 Catch:{ all -> 0x01e8 }
        goto L_0x0120;
    L_0x00ff:
        r15 = r6;
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mCaptureAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r0.mCaptureAnimFrameBuffer;	 Catch:{ all -> 0x01e8 }
        r0.copyPreviewTexture(r9, r1, r2);	 Catch:{ all -> 0x01e8 }
        r1 = r0.mCaptureAnimManager;	 Catch:{ all -> 0x01e8 }
        r1.startAnimation(r10, r11, r12, r13);	 Catch:{ all -> 0x01e8 }
        r1 = 12;
        r0.mAnimState = r1;	 Catch:{ all -> 0x01e8 }
        r1 = TAG;	 Catch:{ all -> 0x01e8 }
        r2 = "draw: state=CAPTURE_RUNNING";
        com.android.camera.log.Log.v(r1, r2);	 Catch:{ all -> 0x01e8 }
        goto L_0x0120;
    L_0x011b:
        r15 = r6;
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
    L_0x0120:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r2 = 23;
        r7 = 25;
        if (r1 == r2) goto L_0x0184;
    L_0x0128:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r2 = 24;
        if (r1 == r2) goto L_0x0184;
    L_0x012e:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        if (r1 != r7) goto L_0x0133;
    L_0x0132:
        goto L_0x0184;
    L_0x0133:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r2 = 12;
        if (r1 != r2) goto L_0x014e;
    L_0x0139:
        r1 = r0.mCaptureAnimManager;	 Catch:{ all -> 0x01e8 }
        r2 = r0.mCaptureAnimTexture;	 Catch:{ all -> 0x01e8 }
        r1 = r1.drawAnimation(r9, r2);	 Catch:{ all -> 0x01e8 }
        if (r1 == 0) goto L_0x0147;
    L_0x0143:
        r18.postRequestListener();	 Catch:{ all -> 0x01e8 }
        goto L_0x014c;
    L_0x0147:
        r0.mAnimState = r15;	 Catch:{ all -> 0x01e8 }
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
    L_0x014c:
        goto L_0x01bf;
    L_0x014e:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r7 = 35;
        r2 = 33;
        if (r1 == r2) goto L_0x0160;
    L_0x0156:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        r2 = 34;
        if (r1 == r2) goto L_0x0160;
    L_0x015c:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        if (r1 != r7) goto L_0x01bf;
    L_0x0160:
        r8.updateTexImage();	 Catch:{ all -> 0x01e8 }
        r1 = r0.mModuleAnimManager;	 Catch:{ all -> 0x01e8 }
        r8 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r9;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        r6 = r13;
        r15 = r7;
        r7 = r0;
        r1 = r1.drawAnimation(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x01e8 }
        if (r1 != 0) goto L_0x0180;
    L_0x0174:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        if (r1 == r15) goto L_0x0179;
    L_0x0178:
        goto L_0x0180;
    L_0x0179:
        r1 = 0;
        r0.mAnimState = r1;	 Catch:{ all -> 0x01e8 }
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
        goto L_0x01bf;
    L_0x0180:
        r18.postRequestListener();	 Catch:{ all -> 0x01e8 }
        goto L_0x01bf;
    L_0x0184:
        r8.updateTexImage();	 Catch:{ all -> 0x01e8 }
        r1 = r0.mDisableSwitchAnimationOnce;	 Catch:{ all -> 0x01e8 }
        if (r1 == 0) goto L_0x019c;
    L_0x018c:
        r1 = r0.mSwitchAnimManager;	 Catch:{ all -> 0x01e8 }
        r8 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r9;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        r6 = r13;
        r15 = r7;
        r7 = r8;
        r1.drawPreview(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x01e8 }
        r6 = 0;
        goto L_0x01ab;
    L_0x019c:
        r15 = r7;
        r1 = r0.mSwitchAnimManager;	 Catch:{ all -> 0x01e8 }
        r8 = r0.mAnimTexture;	 Catch:{ all -> 0x01e8 }
        r2 = r9;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        r6 = r13;
        r7 = r0;
        r6 = r1.drawAnimation(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x01e8 }
    L_0x01ab:
        if (r6 != 0) goto L_0x01bb;
    L_0x01ad:
        r1 = r0.mAnimState;	 Catch:{ all -> 0x01e8 }
        if (r1 == r15) goto L_0x01b2;
    L_0x01b1:
        goto L_0x01bb;
    L_0x01b2:
        r1 = 0;
        r0.mAnimState = r1;	 Catch:{ all -> 0x01e8 }
        r0.mDisableSwitchAnimationOnce = r1;	 Catch:{ all -> 0x01e8 }
        super.draw(r19, r20, r21, r22, r23);	 Catch:{ all -> 0x01e8 }
        goto L_0x01be;
    L_0x01bb:
        r18.postRequestListener();	 Catch:{ all -> 0x01e8 }
    L_0x01bf:
        monitor-exit(r14);	 Catch:{ all -> 0x01e8 }
        return;
    L_0x01c1:
        r1 = TAG;	 Catch:{ all -> 0x01e8 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01e8 }
        r2.<init>();	 Catch:{ all -> 0x01e8 }
        r3 = "draw: firstFrame=";
        r2.append(r3);	 Catch:{ all -> 0x01e8 }
        r0 = r0.mFirstFrameArrived;	 Catch:{ all -> 0x01e8 }
        r2.append(r0);	 Catch:{ all -> 0x01e8 }
        r0 = " surface=";
        r2.append(r0);	 Catch:{ all -> 0x01e8 }
        r2.append(r8);	 Catch:{ all -> 0x01e8 }
        r0 = r2.toString();	 Catch:{ all -> 0x01e8 }
        com.android.camera.log.Log.w(r1, r0);	 Catch:{ all -> 0x01e8 }
        if (r8 == 0) goto L_0x01e6;
    L_0x01e3:
        r8.updateTexImage();	 Catch:{ all -> 0x01e8 }
    L_0x01e6:
        monitor-exit(r14);	 Catch:{ all -> 0x01e8 }
        return;
    L_0x01e8:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x01e8 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.CameraScreenNail.draw(com.android.gallery3d.ui.GLCanvas, int, int, int, int):void");
    }

    private byte[] readPreviewPixels(GLCanvas gLCanvas, int i, int i2) {
        Buffer allocate = ByteBuffer.allocate((i * i2) * 4);
        getSurfaceTexture().getTransformMatrix(this.mTextureTransformMatrix);
        updateTransformMatrix(this.mTextureTransformMatrix);
        if (this.mCaptureAnimFrameBuffer == null) {
            this.mCaptureAnimFrameBuffer = new FrameBuffer(gLCanvas, this.mCaptureAnimTexture, 0);
        }
        gLCanvas.beginBindFrameBuffer(this.mCaptureAnimFrameBuffer);
        gLCanvas.draw(new DrawExtTexAttribute(this.mExtTexture, this.mTextureTransformMatrix, 0, 0, i, i2));
        GLES20.glReadPixels(0, 0, i, i2, 6408, 5121, allocate);
        gLCanvas.endBindFrameBuffer();
        return allocate.array();
    }

    private void copyPreviewTexture(GLCanvas gLCanvas, RawTexture rawTexture, FrameBuffer frameBuffer) {
        int width = rawTexture.getWidth();
        int height = rawTexture.getHeight();
        getSurfaceTexture().getTransformMatrix(this.mTextureTransformMatrix);
        updateTransformMatrix(this.mTextureTransformMatrix);
        if (frameBuffer == null) {
            frameBuffer = new FrameBuffer(gLCanvas, rawTexture, 0);
        }
        gLCanvas.beginBindFrameBuffer(frameBuffer);
        gLCanvas.draw(new DrawExtTexAttribute(this.mExtTexture, this.mTextureTransformMatrix, 0, 0, width, height));
        gLCanvas.endBindFrameBuffer();
    }

    public void drawGaussianBitmap(GLCanvas gLCanvas, int i, int i2, int i3, int i4) {
        long currentTimeMillis = System.currentTimeMillis();
        i3 = this.mAnimTexture.getWidth();
        i4 = this.mAnimTexture.getHeight();
        for (int i5 = 0; i5 < 8; i5++) {
            renderBlurTexture(gLCanvas);
        }
        GLES20.glFlush();
        Buffer allocate = ByteBuffer.allocate((i3 * i4) * 4);
        if (this.mFrameBuffer == null) {
            this.mFrameBuffer = new FrameBuffer(gLCanvas, this.mAnimTexture, 0);
        }
        gLCanvas.beginBindFrameBuffer(this.mFrameBuffer);
        gLCanvas.draw(new DrawBasicTexAttribute(this.mAnimTexture, 0, 0, i3, i4));
        GLES20.glReadPixels(0, 0, i3, i4, 6408, 5121, allocate);
        gLCanvas.endBindFrameBuffer();
        byte[] array = allocate.array();
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(array));
        this.mLastFrameGaussianBitmap = createBitmap;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("readLastFrameGaussian end... bitmap = ");
        stringBuilder.append(this.mLastFrameGaussianBitmap);
        stringBuilder.append(", cost time = ");
        stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
        stringBuilder.append("ms");
        Log.d(str, stringBuilder.toString());
    }

    public void drawBlurTexture(GLCanvas gLCanvas, int i, int i2, int i3, int i4) {
        gLCanvas.draw(new DrawBasicTexAttribute(this.mAnimTexture, i, i2, i3, i4));
    }

    public void renderBlurTexture(GLCanvas gLCanvas) {
        renderBlurTexture(gLCanvas, this.mAnimTexture);
    }

    private void renderBlurTexture(GLCanvas gLCanvas, RawTexture rawTexture) {
        int width = rawTexture.getWidth();
        int height = rawTexture.getHeight();
        if (this.mFrameBuffer == null) {
            this.mFrameBuffer = new FrameBuffer(gLCanvas, rawTexture, 0);
        }
        gLCanvas.prepareBlurRenders();
        gLCanvas.beginBindFrameBuffer(this.mFrameBuffer);
        gLCanvas.draw(new DrawBlurTexAttribute(rawTexture, 0, 0, width, height));
        gLCanvas.endBindFrameBuffer();
    }

    public void renderBitmapToCanvas(Bitmap bitmap) {
        this.mVisible = false;
        this.mBitmapTexture = new BitmapTexture(bitmap);
        postRequestListener();
    }

    public void releaseBitmapIfNeeded() {
        if (this.mBitmapTexture != null && !this.mNailListener.isKeptBitmapTexture()) {
            this.mBitmapTexture = null;
            postRequestListener();
        }
    }

    public void noDraw() {
        synchronized (this.mLock) {
            this.mVisible = false;
        }
    }

    public void recycle() {
        synchronized (this.mLock) {
            this.mVisible = false;
        }
    }

    /* JADX WARNING: Missing block: B:25:0x0075, code skipped:
            return;
     */
    public void onFrameAvailable(android.graphics.SurfaceTexture r4) {
        /*
        r3 = this;
        r0 = r3.getSurfaceTexture();
        if (r0 == r4) goto L_0x000e;
    L_0x0006:
        r4 = TAG;
        r0 = "onFrameAvailable: surface changed";
        com.android.camera.log.Log.e(r4, r0);
        return;
    L_0x000e:
        r4 = r3.mLock;
        monitor-enter(r4);
        r0 = r3.mFirstFrameArrived;	 Catch:{ all -> 0x0076 }
        if (r0 != 0) goto L_0x003c;
    L_0x0015:
        r0 = TAG;	 Catch:{ all -> 0x0076 }
        r1 = "onFrameAvailable first frame arrived.";
        com.android.camera.log.Log.d(r0, r1);	 Catch:{ all -> 0x0076 }
        r0 = r3.mFrameNumber;	 Catch:{ all -> 0x0076 }
        r1 = com.android.camera.CameraSettings.getSkipFrameNumber();	 Catch:{ all -> 0x0076 }
        r2 = 1;
        if (r0 >= r1) goto L_0x002f;
    L_0x0025:
        r0 = r3.mFrameNumber;	 Catch:{ all -> 0x0076 }
        r0 = r0 + r2;
        r3.mFrameNumber = r0;	 Catch:{ all -> 0x0076 }
        r3.postRequestListener();	 Catch:{ all -> 0x0076 }
        monitor-exit(r4);	 Catch:{ all -> 0x0076 }
        return;
    L_0x002f:
        com.android.camera.statistic.ScenarioTrackUtil.trackSwitchCameraEnd();	 Catch:{ all -> 0x0076 }
        com.android.camera.statistic.ScenarioTrackUtil.trackSwitchModeEnd();	 Catch:{ all -> 0x0076 }
        r3.notifyFrameAvailable(r2);	 Catch:{ all -> 0x0076 }
        r3.mVisible = r2;	 Catch:{ all -> 0x0076 }
        r3.mFirstFrameArrived = r2;	 Catch:{ all -> 0x0076 }
    L_0x003c:
        r0 = r3.mVisible;	 Catch:{ all -> 0x0076 }
        if (r0 == 0) goto L_0x0074;
    L_0x0040:
        r0 = r3.mAnimState;	 Catch:{ all -> 0x0076 }
        r1 = 24;
        if (r0 != r1) goto L_0x0057;
    L_0x0046:
        r0 = 25;
        r3.mAnimState = r0;	 Catch:{ all -> 0x0076 }
        r0 = TAG;	 Catch:{ all -> 0x0076 }
        r1 = "SWITCH_WAITING_FIRST_FRAME->SWITCH_RESUME";
        com.android.camera.log.Log.v(r0, r1);	 Catch:{ all -> 0x0076 }
        r0 = r3.mSwitchAnimManager;	 Catch:{ all -> 0x0076 }
        r0.startResume();	 Catch:{ all -> 0x0076 }
        goto L_0x006d;
    L_0x0057:
        r0 = r3.mAnimState;	 Catch:{ all -> 0x0076 }
        r1 = 34;
        if (r0 != r1) goto L_0x006d;
    L_0x005d:
        r0 = 35;
        r3.mAnimState = r0;	 Catch:{ all -> 0x0076 }
        r0 = TAG;	 Catch:{ all -> 0x0076 }
        r1 = "MODULE_WAITING_FIRST_FRAME->MODULE_RESUME";
        com.android.camera.log.Log.v(r0, r1);	 Catch:{ all -> 0x0076 }
        r0 = r3.mModuleAnimManager;	 Catch:{ all -> 0x0076 }
        r0.startResume();	 Catch:{ all -> 0x0076 }
    L_0x006d:
        r3.postRequestListener();	 Catch:{ all -> 0x0076 }
        r0 = 4;
        r3.notifyFrameAvailable(r0);	 Catch:{ all -> 0x0076 }
    L_0x0074:
        monitor-exit(r4);	 Catch:{ all -> 0x0076 }
        return;
    L_0x0076:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0076 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.CameraScreenNail.onFrameAvailable(android.graphics.SurfaceTexture):void");
    }

    public void setPreviewFrameLayoutSize(int i, int i2) {
        synchronized (this.mLock) {
            Log.d(TAG, String.format(Locale.ENGLISH, "setPreviewFrameLayoutSize: %dx%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            this.mSurfaceWidth = !b.hc() ? i : Util.LIMIT_SURFACE_WIDTH;
            if (b.hc()) {
                i2 = (Util.LIMIT_SURFACE_WIDTH * i2) / i;
            }
            this.mSurfaceHeight = i2;
            this.mSwitchAnimManager.setPreviewFrameLayoutSize(this.mSurfaceWidth, this.mSurfaceHeight);
            updateRenderRect();
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateExtraTransformMatrix(float[] fArr) {
        float extScaleX;
        float extScaleY;
        if (this.mAnimState == 23 || this.mAnimState == 24 || this.mAnimState == 25) {
            extScaleX = this.mSwitchAnimManager.getExtScaleX();
            extScaleY = this.mSwitchAnimManager.getExtScaleY();
        } else {
            extScaleX = 1.0f;
            extScaleY = extScaleX;
        }
        if (extScaleX != 1.0f || extScaleY != 1.0f) {
            Matrix.translateM(fArr, 0, 0.5f, 0.5f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            Matrix.scaleM(fArr, 0, extScaleX, extScaleY, 1.0f);
            Matrix.translateM(fArr, 0, -0.5f, -0.5f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        }
    }

    public Rect getRenderRect() {
        return this.mRenderLayoutRect;
    }

    public void resetFrameAvailableFlag() {
        this.mFrameAvailableNotified.set(false);
        synchronized (this.mLock) {
            this.mFirstFrameArrived = false;
            this.mFrameNumber = 0;
        }
    }

    public boolean getFrameAvailableFlag() {
        return this.mFrameAvailableNotified.get();
    }

    private void notifyFrameAvailable(int i) {
        if (!this.mFrameAvailableNotified.get()) {
            this.mFrameAvailableNotified.set(true);
            this.mNailListener.onFrameAvailable(i);
        }
    }
}
