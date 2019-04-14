package com.xiaomi.camera.liveshot.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.os.Handler;
import android.view.Surface;
import com.android.camera.log.Log;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.xiaomi.camera.liveshot.encoder.CircularMediaEncoder.Snapshot;
import com.xiaomi.camera.liveshot.gles.RenderThread;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CircularVideoEncoder extends CircularMediaEncoder {
    private static final boolean DEBUG_FPS = true;
    private static final String TAG = CircularVideoEncoder.class.getSimpleName();
    protected long mFirstPresentationTimeUs;
    private int mFpsOutputInterval = 500;
    private long mFrameStartTimestampNs = 0;
    private int mFramesRendered = 0;
    private Surface mInputSurface;
    protected long mLastPresentationTimeUs;
    private long mMinFrameRenderPeriodNs;
    private long mNextFrameTimestampNs;
    private final int mPreviewHeight;
    private final int mPreviewWidth;
    private RenderThread mRenderThread;
    private EGLContext mSharedEGLContext;

    public CircularVideoEncoder(MediaFormat mediaFormat, EGLContext eGLContext, long j, long j2) {
        super(mediaFormat, j, j2);
        float f = this.mDesiredMediaFormat.getFloat("i-frame-interval");
        j = TimeUnit.MICROSECONDS.toMillis(this.mBufferingDurationUs);
        f = (f * 1000.0f) * 2.0f;
        if (((float) j) < f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Requested time span is too short: ");
            stringBuilder.append(j);
            stringBuilder.append(" vs. ");
            stringBuilder.append(f);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (eGLContext != null) {
            this.mSharedEGLContext = eGLContext;
            int integer = this.mDesiredMediaFormat.getInteger("width");
            int integer2 = this.mDesiredMediaFormat.getInteger("height");
            this.mPreviewWidth = Math.min(integer, integer2);
            this.mPreviewHeight = Math.max(integer, integer2);
            try {
                this.mMediaCodec = MediaCodec.createEncoderByType(this.mDesiredMediaFormat.getString("mime"));
                this.mIsInitialized = true;
            } catch (IOException e) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Failed to configure MediaCodec: ");
                stringBuilder2.append(e);
                throw new IllegalStateException(stringBuilder2.toString());
            }
        } else {
            throw new IllegalArgumentException("The shared EGLContext must not be null");
        }
    }

    public void setFpsReduction(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFpsReduction: ");
        stringBuilder.append(f);
        Log.d(str, stringBuilder.toString());
        if (f <= PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO) {
            this.mMinFrameRenderPeriodNs = Long.MAX_VALUE;
        } else {
            this.mMinFrameRenderPeriodNs = (long) (((float) TimeUnit.SECONDS.toNanos(1)) / f);
        }
    }

    /* Access modifiers changed, original: protected */
    public long getNextPresentationTimeUs(long j) {
        if (this.mFirstPresentationTimeUs == 0) {
            this.mFirstPresentationTimeUs = j;
            return 0;
        }
        j -= this.mFirstPresentationTimeUs;
        if (this.mLastPresentationTimeUs >= j) {
            this.mLastPresentationTimeUs += 9643;
            return this.mLastPresentationTimeUs;
        }
        this.mLastPresentationTimeUs = j;
        return j;
    }

    public void doStart() {
        Log.d(TAG, "start(): E");
        if (!this.mIsInitialized) {
            Log.d(TAG, "start(): not initialized yet");
        } else if (this.mIsBuffering) {
            Log.d(TAG, "start(): encoder is already running");
        } else {
            this.mCyclicBuffer.clear();
            this.mMediaCodec.configure(this.mDesiredMediaFormat, null, null, 1);
            this.mInputSurface = this.mMediaCodec.createInputSurface();
            this.mRenderThread = new RenderThread(TAG, this.mPreviewWidth, this.mPreviewHeight, this.mSharedEGLContext, this.mInputSurface, true);
            this.mRenderThread.start();
            this.mRenderThread.waitUntilReady();
            this.mMediaCodec.setCallback(this, new Handler(this.mEncodingThread.getLooper()));
            this.mCurrentPresentationTimeUs = 0;
            this.mFirstPresentationTimeUs = 0;
            this.mLastPresentationTimeUs = 0;
            super.doStart();
            this.mIsBuffering = true;
            Log.d(TAG, "start(): X");
        }
    }

    public synchronized void doStop() {
        Log.d(TAG, "stop(): E");
        if (!this.mIsInitialized) {
            return;
        }
        if (this.mIsBuffering) {
            this.mIsBuffering = false;
            if (this.mRenderThread != null) {
                this.mRenderThread.quit();
                this.mRenderThread = null;
            }
            if (this.mInputSurface != null) {
                this.mInputSurface.release();
                this.mInputSurface = null;
            }
            super.doStop();
            Log.d(TAG, "clear pending snapshot requests: E");
            ArrayList<Snapshot> arrayList = new ArrayList();
            synchronized (this.mSnapshots) {
                arrayList.addAll(this.mSnapshots);
                this.mSnapshots.clear();
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("cleared ");
            stringBuilder.append(arrayList.size());
            stringBuilder.append(" snapshot requests.");
            Log.d(str, stringBuilder.toString());
            for (Snapshot putEos : arrayList) {
                try {
                    putEos.putEos();
                } catch (InterruptedException e) {
                    String str2 = TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Failed to putEos: ");
                    stringBuilder2.append(e);
                    Log.d(str2, stringBuilder2.toString());
                }
            }
            Log.d(TAG, "clear pending snapshot requests: X");
            Log.d(TAG, "stop(): X");
        }
    }

    public void doRelease() {
        if (this.mIsInitialized) {
            super.doRelease();
            this.mIsInitialized = false;
        }
    }

    public synchronized void setFilterId(int i) {
        if (!this.mIsInitialized) {
            return;
        }
        if (this.mIsBuffering) {
            this.mRenderThread.setFilterId(i);
        }
    }

    /* JADX WARNING: Missing block: B:29:0x0083, code skipped:
            return;
     */
    public synchronized void onSurfaceTextureUpdated(com.android.camera.effect.draw_mode.DrawExtTexAttribute r9) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r8.mIsInitialized;	 Catch:{ all -> 0x0084 }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r8);
        return;
    L_0x0007:
        r0 = r8.mIsBuffering;	 Catch:{ all -> 0x0084 }
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r8);
        return;
    L_0x000d:
        r0 = r8.mMinFrameRenderPeriodNs;	 Catch:{ all -> 0x0084 }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0037;
    L_0x0015:
        r0 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0084 }
        r4 = r8.mNextFrameTimestampNs;	 Catch:{ all -> 0x0084 }
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 >= 0) goto L_0x0028;
    L_0x001f:
        r9 = TAG;	 Catch:{ all -> 0x0084 }
        r0 = "Dropping frame - fps reduction is active.";
        com.android.camera.log.Log.d(r9, r0);	 Catch:{ all -> 0x0084 }
        monitor-exit(r8);
        return;
    L_0x0028:
        r4 = r8.mNextFrameTimestampNs;	 Catch:{ all -> 0x0084 }
        r6 = r8.mMinFrameRenderPeriodNs;	 Catch:{ all -> 0x0084 }
        r4 = r4 + r6;
        r8.mNextFrameTimestampNs = r4;	 Catch:{ all -> 0x0084 }
        r4 = r8.mNextFrameTimestampNs;	 Catch:{ all -> 0x0084 }
        r0 = java.lang.Math.max(r4, r0);	 Catch:{ all -> 0x0084 }
        r8.mNextFrameTimestampNs = r0;	 Catch:{ all -> 0x0084 }
    L_0x0037:
        r0 = r8.mRenderThread;	 Catch:{ all -> 0x0084 }
        r0.draw(r9);	 Catch:{ all -> 0x0084 }
        r9 = java.util.concurrent.TimeUnit.NANOSECONDS;	 Catch:{ all -> 0x0084 }
        r0 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0084 }
        r0 = r9.toMillis(r0);	 Catch:{ all -> 0x0084 }
        r4 = r8.mFrameStartTimestampNs;	 Catch:{ all -> 0x0084 }
        r9 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r9 <= 0) goto L_0x0080;
    L_0x004c:
        r2 = r8.mFrameStartTimestampNs;	 Catch:{ all -> 0x0084 }
        r2 = r0 - r2;
        r9 = r8.mFramesRendered;	 Catch:{ all -> 0x0084 }
        r9 = r9 + 1;
        r8.mFramesRendered = r9;	 Catch:{ all -> 0x0084 }
        r9 = r8.mFpsOutputInterval;	 Catch:{ all -> 0x0084 }
        r4 = (long) r9;	 Catch:{ all -> 0x0084 }
        r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r9 <= 0) goto L_0x007f;
    L_0x005d:
        r9 = r8.mFramesRendered;	 Catch:{ all -> 0x0084 }
        r9 = r9 * 1000;
        r4 = (double) r9;	 Catch:{ all -> 0x0084 }
        r2 = (double) r2;	 Catch:{ all -> 0x0084 }
        r4 = r4 / r2;
        r9 = TAG;	 Catch:{ all -> 0x0084 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0084 }
        r2.<init>();	 Catch:{ all -> 0x0084 }
        r3 = "onSurfaceTextureUpdated(): ";
        r2.append(r3);	 Catch:{ all -> 0x0084 }
        r2.append(r4);	 Catch:{ all -> 0x0084 }
        r2 = r2.toString();	 Catch:{ all -> 0x0084 }
        com.android.camera.log.Log.d(r9, r2);	 Catch:{ all -> 0x0084 }
        r8.mFrameStartTimestampNs = r0;	 Catch:{ all -> 0x0084 }
        r9 = 0;
        r8.mFramesRendered = r9;	 Catch:{ all -> 0x0084 }
    L_0x007f:
        goto L_0x0082;
    L_0x0080:
        r8.mFrameStartTimestampNs = r0;	 Catch:{ all -> 0x0084 }
    L_0x0082:
        monitor-exit(r8);
        return;
    L_0x0084:
        r9 = move-exception;
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.liveshot.encoder.CircularVideoEncoder.onSurfaceTextureUpdated(com.android.camera.effect.draw_mode.DrawExtTexAttribute):void");
    }
}
