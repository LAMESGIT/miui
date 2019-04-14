package com.xiaomi.camera.core;

import com.android.camera.log.Log;
import com.xiaomi.camera.core.ParallelTaskDataParameter.Builder;
import com.xiaomi.protocol.ICustomCaptureResult;

public class ParallelTaskData {
    private static final String GROUPSHOT_ORIGINAL_SUFFIX = "_ORG";
    private static final String TAG = ParallelTaskData.class.getSimpleName();
    private int currentModuleIndex = -1;
    private boolean isNeedThumbnail;
    private int mAlgoType;
    private int mBurstNum;
    private ICustomCaptureResult mCaptureResult;
    private long mCoverFrameTimestamp;
    private ParallelTaskDataParameter mDataParameter;
    private boolean mDeparted;
    private byte[] mJpegImageData;
    private int mParallelType;
    private byte[] mPortraitDepthData;
    private byte[] mPortraitRawData;
    private String mSavePath;
    private long mTimestamp;
    private byte[] mVideoRawData = null;
    private int previewThumbnailHash;

    public ParallelTaskData(long j, int i, String str) {
        this.mTimestamp = j;
        this.mParallelType = i;
        this.mSavePath = str;
    }

    public ParallelTaskData(ParallelTaskData parallelTaskData) {
        this.mParallelType = parallelTaskData.mParallelType;
        this.mTimestamp = parallelTaskData.mTimestamp;
        this.mCaptureResult = parallelTaskData.mCaptureResult;
        this.mJpegImageData = parallelTaskData.mJpegImageData;
        this.mPortraitRawData = parallelTaskData.mPortraitRawData;
        this.mPortraitDepthData = parallelTaskData.mPortraitDepthData;
        this.mSavePath = parallelTaskData.mSavePath;
        this.mDeparted = parallelTaskData.mDeparted;
        this.mDataParameter = parallelTaskData.mDataParameter;
        this.isNeedThumbnail = parallelTaskData.isNeedThumbnail;
        this.mVideoRawData = parallelTaskData.mVideoRawData;
        this.mCoverFrameTimestamp = parallelTaskData.mCoverFrameTimestamp;
    }

    public void setCaptureResult(ICustomCaptureResult iCustomCaptureResult) {
        this.mCaptureResult = iCustomCaptureResult;
    }

    public void checkThread() {
    }

    public synchronized void fillMp4Data(byte[] bArr, long j) {
        checkThread();
        if (this.mVideoRawData == null) {
            this.mVideoRawData = bArr;
            this.mCoverFrameTimestamp = j;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("fillMp4Data: video = ");
            stringBuilder.append(bArr);
            stringBuilder.append("; timestamp = ");
            stringBuilder.append(j);
            Log.d(str, stringBuilder.toString());
        } else {
            throw new IllegalStateException("algo fillMp4Data: microvideo already set");
        }
    }

    public void refillJpegData(byte[] bArr) {
        this.mJpegImageData = bArr;
    }

    public synchronized void fillJpegData(byte[] bArr, int i) {
        checkThread();
        switch (i) {
            case 0:
                if (this.mJpegImageData == null) {
                    this.mJpegImageData = bArr;
                    break;
                }
                throw new RuntimeException("algo fillJpegData: jpeg already set");
            case 1:
                if (this.mPortraitRawData == null) {
                    this.mPortraitRawData = bArr;
                    break;
                }
                throw new RuntimeException("algo fillJpegData: raw already set");
            case 2:
                if (this.mPortraitDepthData == null) {
                    this.mPortraitDepthData = new byte[bArr.length];
                    System.arraycopy(bArr, 0, this.mPortraitDepthData, 0, bArr.length);
                    break;
                }
                throw new RuntimeException("algo fillJpegData: depth already set");
            default:
                break;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("fillJpegData: jpegData=");
        stringBuilder.append(bArr);
        stringBuilder.append("; imageType=");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
    }

    /* JADX WARNING: Missing block: B:35:0x00a4, code skipped:
            return r1;
     */
    public synchronized boolean isJpegDataReady() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.mDeparted;	 Catch:{ all -> 0x00a5 }
        r1 = 0;
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r4);
        return r1;
    L_0x0009:
        r0 = r4.mParallelType;	 Catch:{ all -> 0x00a5 }
        r2 = 1;
        switch(r0) {
            case -3: goto L_0x0024;
            case -2: goto L_0x001c;
            case -1: goto L_0x001c;
            case 0: goto L_0x001c;
            case 1: goto L_0x0010;
            case 2: goto L_0x0024;
            case 3: goto L_0x000f;
            case 4: goto L_0x000f;
            case 5: goto L_0x001c;
            case 6: goto L_0x0024;
            case 7: goto L_0x0024;
            case 8: goto L_0x001c;
            case 9: goto L_0x001c;
            default: goto L_0x000f;
        };	 Catch:{ all -> 0x00a5 }
    L_0x000f:
        goto L_0x0033;
    L_0x0010:
        r0 = r4.mJpegImageData;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r4.mVideoRawData;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x001a;
    L_0x0018:
        r1 = r2;
        goto L_0x001b;
    L_0x001b:
        goto L_0x0033;
    L_0x001c:
        r0 = r4.mJpegImageData;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0022;
    L_0x0020:
        r1 = r2;
        goto L_0x0023;
    L_0x0023:
        goto L_0x0033;
    L_0x0024:
        r0 = r4.mJpegImageData;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0032;
    L_0x0028:
        r0 = r4.mPortraitRawData;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0032;
    L_0x002c:
        r0 = r4.mPortraitDepthData;	 Catch:{ all -> 0x00a5 }
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        r1 = r2;
        goto L_0x0033;
    L_0x0033:
        if (r1 == 0) goto L_0x0056;
    L_0x0035:
        r0 = TAG;	 Catch:{ all -> 0x00a5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a5 }
        r2.<init>();	 Catch:{ all -> 0x00a5 }
        r3 = "isJpegDataReady: object = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r2.append(r4);	 Catch:{ all -> 0x00a5 }
        r3 = "; mParallelType = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = r4.mParallelType;	 Catch:{ all -> 0x00a5 }
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r2 = r2.toString();	 Catch:{ all -> 0x00a5 }
        com.android.camera.log.Log.d(r0, r2);	 Catch:{ all -> 0x00a5 }
        goto L_0x00a3;
    L_0x0056:
        r0 = TAG;	 Catch:{ all -> 0x00a5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a5 }
        r2.<init>();	 Catch:{ all -> 0x00a5 }
        r3 = "isJpegDataReady: object = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r2.append(r4);	 Catch:{ all -> 0x00a5 }
        r3 = "; mParallelType = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = r4.mParallelType;	 Catch:{ all -> 0x00a5 }
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = "; mJpegImageData = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = r4.mJpegImageData;	 Catch:{ all -> 0x00a5 }
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = "; mPortraitRawData = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = r4.mPortraitRawData;	 Catch:{ all -> 0x00a5 }
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = "; mPortraitDepthData = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = r4.mPortraitDepthData;	 Catch:{ all -> 0x00a5 }
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = "; mVideoRawData = ";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = r4.mVideoRawData;	 Catch:{ all -> 0x00a5 }
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r3 = "; result = false";
        r2.append(r3);	 Catch:{ all -> 0x00a5 }
        r2 = r2.toString();	 Catch:{ all -> 0x00a5 }
        com.android.camera.log.Log.d(r0, r2);	 Catch:{ all -> 0x00a5 }
    L_0x00a3:
        monitor-exit(r4);
        return r1;
    L_0x00a5:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.core.ParallelTaskData.isJpegDataReady():boolean");
    }

    public void fillParameter(ParallelTaskDataParameter parallelTaskDataParameter) {
        this.mDataParameter = parallelTaskDataParameter;
    }

    public void setDeparted() {
        this.mDeparted = true;
        this.mJpegImageData = null;
        this.mPortraitRawData = null;
        this.mPortraitDepthData = null;
    }

    public ParallelTaskData cloneTaskData(int i) {
        String stringBuilder;
        ParallelTaskData parallelTaskData = new ParallelTaskData(this);
        String savePath = getSavePath();
        String str = GROUPSHOT_ORIGINAL_SUFFIX;
        if (i > 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("_");
            stringBuilder2.append(i);
            str = stringBuilder2.toString();
        }
        i = savePath.lastIndexOf(".");
        if (i > 0) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(savePath.substring(0, i));
            stringBuilder3.append(str);
            stringBuilder3.append(savePath.substring(i));
            stringBuilder = stringBuilder3.toString();
        } else {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(savePath);
            stringBuilder4.append(str);
            stringBuilder = stringBuilder4.toString();
        }
        savePath = TAG;
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("[1] cloneTaskData: path=");
        stringBuilder5.append(stringBuilder);
        Log.d(savePath, stringBuilder5.toString());
        parallelTaskData.setSavePath(stringBuilder);
        parallelTaskData.setNeedThumbnail(false);
        Builder builder = new Builder(getDataParameter());
        builder.setHasDualWaterMark(false);
        builder.setTimeWaterMarkString(null);
        builder.setSaveGroupshotPrimitive(false);
        parallelTaskData.fillParameter(builder.build());
        return parallelTaskData;
    }

    public synchronized byte[] getMicroVideoData() {
        if (this.mVideoRawData != null) {
        } else {
            throw new IllegalStateException("algo fillMp4Data: microvideo not set yet");
        }
        return this.mVideoRawData;
    }

    public synchronized long getCoverFrameTimestamp() {
        return this.mCoverFrameTimestamp;
    }

    public byte[] getJpegImageData() {
        return this.mJpegImageData;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public byte[] getPortraitRawData() {
        return this.mPortraitRawData;
    }

    public byte[] getPortraitDepthData() {
        return this.mPortraitDepthData;
    }

    public ICustomCaptureResult getCaptureResult() {
        return this.mCaptureResult;
    }

    public int getParallelType() {
        return this.mParallelType;
    }

    public String getSavePath() {
        return this.mSavePath;
    }

    public ParallelTaskDataParameter getDataParameter() {
        return this.mDataParameter;
    }

    public boolean isNeedThumbnail() {
        return this.isNeedThumbnail;
    }

    public int getAlgoType() {
        return this.mAlgoType;
    }

    public int getBurstNum() {
        return this.mBurstNum;
    }

    public void setSavePath(String str) {
        this.mSavePath = str;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public void setNeedThumbnail(boolean z) {
        this.isNeedThumbnail = z;
    }

    public void setAlgoType(int i) {
        this.mAlgoType = i;
    }

    public void setBurstNum(int i) {
        this.mBurstNum = i;
    }

    public int getPreviewThumbnailHash() {
        return this.previewThumbnailHash;
    }

    public void setPreviewThumbnailHash(int i) {
        this.previewThumbnailHash = i;
    }

    public void setCurrentModuleIndex(int i) {
        this.currentModuleIndex = i;
    }

    public int getCurrentModuleIndex() {
        return this.currentModuleIndex;
    }
}
