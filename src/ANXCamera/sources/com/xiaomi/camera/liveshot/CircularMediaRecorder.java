package com.xiaomi.camera.liveshot;

import android.media.CamcorderProfile;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.effect.draw_mode.DrawExtTexAttribute;
import com.android.camera.log.Log;
import com.xiaomi.camera.liveshot.encoder.CircularAudioEncoder;
import com.xiaomi.camera.liveshot.encoder.CircularMediaEncoder.Snapshot;
import com.xiaomi.camera.liveshot.encoder.CircularVideoEncoder;
import com.xiaomi.camera.liveshot.util.BackgroundTaskScheduler;
import com.xiaomi.camera.liveshot.util.BackgroundTaskScheduler.CancellableTask;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircularMediaRecorder {
    private static final int AUDIO_BIT_RATE = 64000;
    private static final int AUDIO_CHANNELS = 1;
    private static final int AUDIO_FORMAT = 2;
    private static final String AUDIO_MIME_TYPE = "audio/mp4a-latm";
    private static final int AUDIO_SAMPLE_RATE = 44100;
    private static final int BYTES_COPY_BUFFER_LENGTH = 2048;
    private static final long CAPTURE_DURATION_IN_MICROSECOND = 3000000;
    private static final boolean DEBUG = true;
    private static final int MOVIE_FILE_FORMAT = 0;
    private static final long PRE_CAPTURE_DURATION_IN_MICROSECOND = 1500000;
    private static final boolean SAVE_MICRO_VIDEO_IN_SDCARD = Util.saveLiveShotMicroVideoInSdcard();
    private static final String TAG = CircularMediaRecorder.class.getSimpleName();
    private static final int VIDEO_BIT_RATE = 20000000;
    private static final int VIDEO_FRAME_RATE = 15;
    private static final float VIDEO_I_FRAME_INTERVAL = 0.2f;
    private final CircularAudioEncoder mCircularAudioEncoder;
    private final CircularVideoEncoder mCircularVideoEncoder;
    private int mOrientationHint = 0;
    private final BackgroundTaskScheduler mSnapshotRequestScheduler;

    public interface VideoClipSavingCallback {
        @WorkerThread
        void onVideoClipSavingCancelled();

        @WorkerThread
        void onVideoClipSavingCompleted(@NonNull byte[] bArr, long j);

        @WorkerThread
        void onVideoClipSavingException(@NonNull Throwable th);
    }

    private static final class SnapshotRequest extends CancellableTask {
        private final Snapshot mAudioSnapshot;
        private final int mOrientationHint;
        private final ExecutorService mSampleWriterExecutor;
        private final VideoClipSavingCallback mVideoClipSavingCallback;
        private final Snapshot mVideoSnapshot;

        private SnapshotRequest(Snapshot snapshot, Snapshot snapshot2, int i, VideoClipSavingCallback videoClipSavingCallback) {
            if (snapshot2 == null && snapshot == null) {
                throw new IllegalStateException("At least one non-null snapshot should be provided");
            }
            this.mAudioSnapshot = snapshot;
            this.mVideoSnapshot = snapshot2;
            this.mOrientationHint = i;
            this.mVideoClipSavingCallback = videoClipSavingCallback;
            this.mSampleWriterExecutor = Executors.newFixedThreadPool(2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:73:0x016f A:{Catch:{ all -> 0x01d9 }} */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x0176 A:{SYNTHETIC, Splitter:B:75:0x0176} */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x01b2 A:{SKIP} */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x0199  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x016f A:{Catch:{ all -> 0x01d9 }} */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x0176 A:{SYNTHETIC, Splitter:B:75:0x0176} */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x0199  */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x01b2 A:{SKIP} */
        /* JADX WARNING: Removed duplicated region for block: B:92:0x01dc A:{SYNTHETIC, Splitter:B:92:0x01dc} */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x0220  */
        /* JADX WARNING: Removed duplicated region for block: B:98:0x01ff A:{SKIP} */
        /* JADX WARNING: Removed duplicated region for block: B:92:0x01dc A:{SYNTHETIC, Splitter:B:92:0x01dc} */
        /* JADX WARNING: Removed duplicated region for block: B:98:0x01ff A:{SKIP} */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x0220  */
        public void run() {
            /*
            r11 = this;
            r0 = r11.isCancelled();
            if (r0 == 0) goto L_0x001e;
        L_0x0006:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r1 = "Saving request is cancelled before executing";
            com.android.camera.log.Log.d(r0, r1);
            r0 = r11.mSampleWriterExecutor;
            r0.shutdown();
            r0 = r11.mVideoClipSavingCallback;
            if (r0 == 0) goto L_0x001d;
        L_0x0018:
            r0 = r11.mVideoClipSavingCallback;
            r0.onVideoClipSavingCancelled();
        L_0x001d:
            return;
            r0 = 0;
            r1 = com.xiaomi.camera.liveshot.CircularMediaRecorder.SAVE_MICRO_VIDEO_IN_SDCARD;	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
            if (r1 == 0) goto L_0x0033;
        L_0x0027:
            r1 = new java.io.File;	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
            r2 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
            r3 = "microvideo.mp4";
            r1.<init>(r2, r3);	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
            goto L_0x003b;
        L_0x0033:
            r1 = "microvideo";
            r2 = ".mp4";
            r1 = java.io.File.createTempFile(r1, r2);	 Catch:{ Exception -> 0x014f, all -> 0x0149 }
        L_0x003b:
            r2 = new android.media.MediaMuxer;	 Catch:{ Exception -> 0x0144, all -> 0x013e }
            r3 = r1.getPath();	 Catch:{ Exception -> 0x0144, all -> 0x013e }
            r4 = 0;
            r2.<init>(r3, r4);	 Catch:{ Exception -> 0x0144, all -> 0x013e }
            r3 = r11.mOrientationHint;	 Catch:{ Exception -> 0x013c }
            r2.setOrientationHint(r3);	 Catch:{ Exception -> 0x013c }
            r3 = r11.mVideoSnapshot;	 Catch:{ Exception -> 0x013c }
            r4 = -1;
            if (r3 == 0) goto L_0x0059;
        L_0x0050:
            r3 = r11.mVideoSnapshot;	 Catch:{ Exception -> 0x013c }
            r3 = r3.format;	 Catch:{ Exception -> 0x013c }
            r3 = r2.addTrack(r3);	 Catch:{ Exception -> 0x013c }
            goto L_0x005a;
        L_0x0059:
            r3 = r4;
        L_0x005a:
            r5 = r11.mAudioSnapshot;	 Catch:{ Exception -> 0x013c }
            if (r5 == 0) goto L_0x0067;
        L_0x005e:
            r5 = r11.mAudioSnapshot;	 Catch:{ Exception -> 0x013c }
            r5 = r5.format;	 Catch:{ Exception -> 0x013c }
            r5 = r2.addTrack(r5);	 Catch:{ Exception -> 0x013c }
            goto L_0x0068;
        L_0x0067:
            r5 = r4;
        L_0x0068:
            r2.start();	 Catch:{ Exception -> 0x013c }
            r6 = new java.util.ArrayList;	 Catch:{ Exception -> 0x013c }
            r7 = 2;
            r6.<init>(r7);	 Catch:{ Exception -> 0x013c }
            r7 = r11.mVideoSnapshot;	 Catch:{ Exception -> 0x013c }
            if (r7 == 0) goto L_0x0093;
        L_0x0076:
            if (r3 == r4) goto L_0x0093;
        L_0x0078:
            r0 = new com.xiaomi.camera.liveshot.writer.SampleWriter$StatusNotifier;	 Catch:{ Exception -> 0x013c }
            r7 = 0;
            r7 = java.lang.Long.valueOf(r7);	 Catch:{ Exception -> 0x013c }
            r0.<init>(r7);	 Catch:{ Exception -> 0x013c }
            r7 = r11.mSampleWriterExecutor;	 Catch:{ Exception -> 0x013c }
            r8 = new com.xiaomi.camera.liveshot.writer.VideoSampleWriter;	 Catch:{ Exception -> 0x013c }
            r9 = r11.mVideoSnapshot;	 Catch:{ Exception -> 0x013c }
            r8.<init>(r2, r9, r3, r0);	 Catch:{ Exception -> 0x013c }
            r3 = r7.submit(r8);	 Catch:{ Exception -> 0x013c }
            r6.add(r3);	 Catch:{ Exception -> 0x013c }
        L_0x0093:
            r3 = r11.mAudioSnapshot;	 Catch:{ Exception -> 0x013c }
            if (r3 == 0) goto L_0x00a9;
        L_0x0097:
            if (r5 == r4) goto L_0x00a9;
        L_0x0099:
            r3 = r11.mSampleWriterExecutor;	 Catch:{ Exception -> 0x013c }
            r4 = new com.xiaomi.camera.liveshot.writer.AudioSampleWriter;	 Catch:{ Exception -> 0x013c }
            r7 = r11.mAudioSnapshot;	 Catch:{ Exception -> 0x013c }
            r4.<init>(r2, r7, r5, r0);	 Catch:{ Exception -> 0x013c }
            r0 = r3.submit(r4);	 Catch:{ Exception -> 0x013c }
            r6.add(r0);	 Catch:{ Exception -> 0x013c }
        L_0x00a9:
            r0 = r6.iterator();	 Catch:{ Exception -> 0x013c }
        L_0x00ad:
            r3 = r0.hasNext();	 Catch:{ Exception -> 0x013c }
            if (r3 == 0) goto L_0x00db;
        L_0x00b3:
            r3 = r0.next();	 Catch:{ Exception -> 0x013c }
            r3 = (java.util.concurrent.Future) r3;	 Catch:{ Exception -> 0x013c }
            if (r3 == 0) goto L_0x00d9;
        L_0x00bb:
            r3.get();	 Catch:{ InterruptedException -> 0x00bf }
            goto L_0x00d9;
        L_0x00bf:
            r3 = move-exception;
            r4 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;	 Catch:{ Exception -> 0x013c }
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x013c }
            r5.<init>();	 Catch:{ Exception -> 0x013c }
            r6 = "Writing is interrupted and the generated video may be corrupted: ";
            r5.append(r6);	 Catch:{ Exception -> 0x013c }
            r5.append(r3);	 Catch:{ Exception -> 0x013c }
            r3 = r5.toString();	 Catch:{ Exception -> 0x013c }
            com.android.camera.log.Log.d(r4, r3);	 Catch:{ Exception -> 0x013c }
            goto L_0x00da;
        L_0x00da:
            goto L_0x00ad;
        L_0x00db:
            r2.stop();	 Catch:{ Exception -> 0x013c }
            r0 = r11.mVideoClipSavingCallback;	 Catch:{ Exception -> 0x013c }
            if (r0 == 0) goto L_0x00fa;
        L_0x00e2:
            r0 = r1.getPath();	 Catch:{ Exception -> 0x013c }
            r0 = readFully(r0);	 Catch:{ Exception -> 0x013c }
            r3 = r11.mVideoClipSavingCallback;	 Catch:{ Exception -> 0x013c }
            r4 = r11.mVideoSnapshot;	 Catch:{ Exception -> 0x013c }
            if (r4 != 0) goto L_0x00f3;
        L_0x00f0:
            r4 = -1;
            goto L_0x00f7;
        L_0x00f3:
            r4 = r11.mVideoSnapshot;	 Catch:{ Exception -> 0x013c }
            r4 = r4.time;	 Catch:{ Exception -> 0x013c }
        L_0x00f7:
            r3.onVideoClipSavingCompleted(r0, r4);	 Catch:{ Exception -> 0x013c }
            r2.release();	 Catch:{ Exception -> 0x00ff }
            goto L_0x0118;
        L_0x00ff:
            r0 = move-exception;
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Failed to release the media muxer: ";
            r3.append(r4);
            r3.append(r2);
            r2 = r3.toString();
            com.android.camera.log.Log.d(r0, r2);
        L_0x0118:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.SAVE_MICRO_VIDEO_IN_SDCARD;
            if (r0 == 0) goto L_0x0129;
        L_0x011e:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            goto L_0x01a2;
        L_0x0129:
            if (r1 == 0) goto L_0x01d2;
        L_0x012b:
            r0 = r1.delete();
            if (r0 != 0) goto L_0x01d2;
        L_0x0131:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            goto L_0x01c3;
        L_0x013c:
            r0 = move-exception;
            goto L_0x0153;
        L_0x013e:
            r2 = move-exception;
            r10 = r2;
            r2 = r0;
            r0 = r10;
            goto L_0x01da;
        L_0x0144:
            r2 = move-exception;
            r10 = r2;
            r2 = r0;
            r0 = r10;
            goto L_0x0153;
        L_0x0149:
            r1 = move-exception;
            r2 = r0;
            r0 = r1;
            r1 = r2;
            goto L_0x01da;
        L_0x014f:
            r1 = move-exception;
            r2 = r0;
            r0 = r1;
            r1 = r2;
        L_0x0153:
            r3 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;	 Catch:{ all -> 0x01d9 }
            r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d9 }
            r4.<init>();	 Catch:{ all -> 0x01d9 }
            r5 = "Failed to save the videoclip as an mp4 file: ";
            r4.append(r5);	 Catch:{ all -> 0x01d9 }
            r4.append(r0);	 Catch:{ all -> 0x01d9 }
            r4 = r4.toString();	 Catch:{ all -> 0x01d9 }
            com.android.camera.log.Log.d(r3, r4);	 Catch:{ all -> 0x01d9 }
            r3 = r11.mVideoClipSavingCallback;	 Catch:{ all -> 0x01d9 }
            if (r3 == 0) goto L_0x0174;
        L_0x016f:
            r3 = r11.mVideoClipSavingCallback;	 Catch:{ all -> 0x01d9 }
            r3.onVideoClipSavingException(r0);	 Catch:{ all -> 0x01d9 }
        L_0x0174:
            if (r2 == 0) goto L_0x0193;
        L_0x0176:
            r2.release();	 Catch:{ Exception -> 0x017a }
            goto L_0x0193;
        L_0x017a:
            r0 = move-exception;
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Failed to release the media muxer: ";
            r3.append(r4);
            r3.append(r2);
            r2 = r3.toString();
            com.android.camera.log.Log.d(r0, r2);
        L_0x0193:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.SAVE_MICRO_VIDEO_IN_SDCARD;
            if (r0 == 0) goto L_0x01b2;
        L_0x0199:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
        L_0x01a2:
            r3 = "Ignore deleting the temporary mp4 file: ";
            r2.append(r3);
            r2.append(r1);
            r1 = r2.toString();
            com.android.camera.log.Log.d(r0, r1);
            goto L_0x01d2;
        L_0x01b2:
            if (r1 == 0) goto L_0x01d2;
        L_0x01b4:
            r0 = r1.delete();
            if (r0 != 0) goto L_0x01d2;
        L_0x01ba:
            r0 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
        L_0x01c3:
            r3 = "Failed to delete the temporary mp4 file: ";
            r2.append(r3);
            r2.append(r1);
            r1 = r2.toString();
            com.android.camera.log.Log.d(r0, r1);
        L_0x01d2:
            r0 = r11.mSampleWriterExecutor;
            r0.shutdown();
            return;
        L_0x01d9:
            r0 = move-exception;
        L_0x01da:
            if (r2 == 0) goto L_0x01f9;
        L_0x01dc:
            r2.release();	 Catch:{ Exception -> 0x01e0 }
            goto L_0x01f9;
        L_0x01e0:
            r3 = move-exception;
            r3 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "Failed to release the media muxer: ";
            r4.append(r5);
            r4.append(r2);
            r2 = r4.toString();
            com.android.camera.log.Log.d(r3, r2);
        L_0x01f9:
            r2 = com.xiaomi.camera.liveshot.CircularMediaRecorder.SAVE_MICRO_VIDEO_IN_SDCARD;
            if (r2 != 0) goto L_0x0220;
        L_0x01ff:
            if (r1 == 0) goto L_0x0238;
        L_0x0201:
            r2 = r1.delete();
            if (r2 != 0) goto L_0x0238;
        L_0x0207:
            r2 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Failed to delete the temporary mp4 file: ";
            r3.append(r4);
            r3.append(r1);
            r1 = r3.toString();
            com.android.camera.log.Log.d(r2, r1);
            goto L_0x0238;
        L_0x0220:
            r2 = com.xiaomi.camera.liveshot.CircularMediaRecorder.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Ignore deleting the temporary mp4 file: ";
            r3.append(r4);
            r3.append(r1);
            r1 = r3.toString();
            com.android.camera.log.Log.d(r2, r1);
        L_0x0238:
            r1 = r11.mSampleWriterExecutor;
            r1.shutdown();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.liveshot.CircularMediaRecorder$SnapshotRequest.run():void");
        }

        private static byte[] readFully(String str) {
            Throwable th;
            Throwable th2;
            BufferedInputStream bufferedInputStream;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read < 0) {
                            bArr = byteArrayOutputStream.toByteArray();
                            $closeResource(null, byteArrayOutputStream);
                            $closeResource(null, bufferedInputStream);
                            return bArr;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                } catch (Throwable th22) {
                    Throwable th3 = th22;
                    th22 = th;
                    th = th3;
                }
                $closeResource(th22, byteArrayOutputStream);
                throw th;
            } catch (IOException e) {
                String access$100 = CircularMediaRecorder.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to load the mp4 file content into memory: ");
                stringBuilder.append(e);
                Log.d(access$100, stringBuilder.toString());
                return new byte[0];
            } catch (Throwable th4) {
                $closeResource(r6, bufferedInputStream);
            }
        }

        private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
            if (th != null) {
                try {
                    autoCloseable.close();
                    return;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    return;
                }
            }
            autoCloseable.close();
        }
    }

    static {
        CamcorderProfile camcorderProfile = CamcorderProfile.get(6);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    audioBitRate: ");
        stringBuilder.append(camcorderProfile.audioBitRate);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("   audioChannels: ");
        stringBuilder.append(camcorderProfile.audioChannels);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(" audioSampleRate: ");
        stringBuilder.append(camcorderProfile.audioSampleRate);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("      audioCodec: ");
        stringBuilder.append(camcorderProfile.audioCodec);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(" videoFrameWidth: ");
        stringBuilder.append(camcorderProfile.videoFrameWidth);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("videoFrameHeight: ");
        stringBuilder.append(camcorderProfile.videoFrameHeight);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("    videoBitRate: ");
        stringBuilder.append(camcorderProfile.videoBitRate);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("  videoFrameRate: ");
        stringBuilder.append(camcorderProfile.videoFrameRate);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("      videoCodec: ");
        stringBuilder.append(camcorderProfile.videoCodec);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("        duration: ");
        stringBuilder.append(camcorderProfile.duration);
        Log.d("QUALITY_1080P_PROFILE", stringBuilder.toString());
    }

    public CircularMediaRecorder(int i, int i2, EGLContext eGLContext, boolean z) {
        this.mCircularVideoEncoder = new CircularVideoEncoder(createVideoFormat(i, i2), eGLContext, CAPTURE_DURATION_IN_MICROSECOND, PRE_CAPTURE_DURATION_IN_MICROSECOND);
        if (z) {
            this.mCircularAudioEncoder = new CircularAudioEncoder(createAudioFormat(AUDIO_SAMPLE_RATE, 1), CAPTURE_DURATION_IN_MICROSECOND, PRE_CAPTURE_DURATION_IN_MICROSECOND);
        } else {
            this.mCircularAudioEncoder = null;
        }
        this.mSnapshotRequestScheduler = new BackgroundTaskScheduler(Executors.newSingleThreadExecutor());
    }

    public void setFpsReduction(float f) {
        if (this.mCircularVideoEncoder != null) {
            this.mCircularVideoEncoder.setFpsReduction(f);
        }
    }

    public void start() {
        Log.d(TAG, "start(): E");
        if (this.mCircularVideoEncoder != null) {
            this.mCircularVideoEncoder.start();
        }
        if (this.mCircularAudioEncoder != null) {
            this.mCircularAudioEncoder.start();
        }
        Log.d(TAG, "start(): X");
    }

    public void stop() {
        Log.d(TAG, "stop(): E");
        this.mSnapshotRequestScheduler.abortRemainingTasks();
        if (this.mCircularVideoEncoder != null) {
            this.mCircularVideoEncoder.stop();
        }
        if (this.mCircularAudioEncoder != null) {
            this.mCircularAudioEncoder.stop();
        }
        Log.d(TAG, "stop(): X");
    }

    public void release() {
        Log.d(TAG, "release(): E");
        this.mSnapshotRequestScheduler.shutdown();
        if (this.mCircularVideoEncoder != null) {
            this.mCircularVideoEncoder.release();
        }
        if (this.mCircularAudioEncoder != null) {
            this.mCircularAudioEncoder.release();
        }
        Log.d(TAG, "release(): X");
    }

    public void onSurfaceTextureUpdated(DrawExtTexAttribute drawExtTexAttribute) {
        if (this.mCircularVideoEncoder != null) {
            this.mCircularVideoEncoder.onSurfaceTextureUpdated(drawExtTexAttribute);
        }
    }

    public void setFilterId(int i) {
        if (this.mCircularVideoEncoder != null) {
            this.mCircularVideoEncoder.setFilterId(i);
        }
    }

    public void setOrientationHint(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setOrientationHint(): ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        this.mOrientationHint = i;
    }

    public void snapshot(int i, VideoClipSavingCallback videoClipSavingCallback) {
        Snapshot snapshot;
        Snapshot snapshot2;
        if (this.mCircularAudioEncoder == null) {
            snapshot = null;
        } else {
            snapshot = this.mCircularAudioEncoder.snapshot();
        }
        if (this.mCircularVideoEncoder == null) {
            snapshot2 = null;
        } else {
            snapshot2 = this.mCircularVideoEncoder.snapshot();
        }
        if (i == -1) {
            i = this.mOrientationHint;
        }
        this.mSnapshotRequestScheduler.execute(new SnapshotRequest(snapshot, snapshot2, i, videoClipSavingCallback));
    }

    private static MediaFormat createAudioFormat(int i, int i2) {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(AUDIO_MIME_TYPE, i, i2);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("bitrate", AUDIO_BIT_RATE);
        createAudioFormat.setInteger("channel-count", i2);
        createAudioFormat.setInteger("pcm-encoding", 2);
        return createAudioFormat;
    }

    private static MediaFormat createVideoFormat(int i, int i2) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(isH265EncodingPreferred() ? "video/hevc" : "video/avc", i, i2);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", VIDEO_BIT_RATE);
        createVideoFormat.setInteger("frame-rate", 15);
        createVideoFormat.setFloat("i-frame-interval", VIDEO_I_FRAME_INTERVAL);
        return createVideoFormat;
    }

    private static boolean isH265EncodingPreferred() {
        return CameraSettings.getVideoEncoder() == 5 && MediaCodecCapability.isH265EncodingSupported();
    }
}
