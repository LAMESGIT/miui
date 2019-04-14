package com.android.camera2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.utils.SurfaceUtils;
import android.support.annotation.NonNull;
import android.util.Size;
import android.view.Surface;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.CameraSize;
import com.android.camera.Util;
import com.android.camera.log.Log;
import com.android.camera.parallel.AlgoConnector;
import com.android.camera2.Camera2Proxy.PictureCallback;
import com.android.camera2.compat.MiCameraCompat;
import com.xiaomi.camera.core.ParallelTaskData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MiCamera2ShotParallelBurst extends MiCamera2ShotParallel<ParallelTaskData> {
    private static final String TAG = "ShotParallelBurst";
    private int mAlgoType = 0;
    private int mCompletedNum;
    private boolean mFirstNum;
    private int[] mHdrCheckerEvValue;
    private CaptureResult mPreviewCaptureResult;
    private int mSequenceNum;
    private boolean mShouldDoMFNR;
    private boolean mShouldDoQcfaCapture;
    private boolean mShouldDoSR;
    private List<CaptureRequest> requests = new ArrayList();

    public MiCamera2ShotParallelBurst(MiCamera2 miCamera2, CaptureResult captureResult) {
        super(miCamera2);
        this.mPreviewCaptureResult = captureResult;
    }

    /* Access modifiers changed, original: protected */
    public void prepare() {
        this.mFirstNum = true;
        this.mShouldDoQcfaCapture = false;
        this.mShouldDoSR = this.mMiCamera.getCameraConfigs().isSuperResolutionEnabled();
        Integer num = (Integer) this.mPreviewCaptureResult.get(CaptureResult.SENSOR_SENSITIVITY);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("prepare: preview iso = ");
        stringBuilder.append(num);
        Log.d(str, stringBuilder.toString());
        if (!this.mShouldDoSR) {
            boolean z = num != null && num.intValue() >= 800;
            this.mShouldDoMFNR = z;
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("prepare: ISO=");
            stringBuilder.append(num);
            Log.d(str, stringBuilder.toString());
        }
        if (this.mMiCamera.getCameraConfigs().isHDREnabled()) {
            this.mAlgoType = 1;
            prepareHdr();
        } else if (CameraSettings.isGroupShotOn()) {
            this.mAlgoType = 5;
            prepareGroupShot();
        } else if (this.mShouldDoSR) {
            this.mAlgoType = 3;
            this.mSequenceNum = 5;
        } else if (this.mShouldDoMFNR) {
            this.mAlgoType = 2;
            prepareClearShot(num.intValue());
        } else {
            this.mAlgoType = 0;
            this.mSequenceNum = 1;
        }
        Log.d(TAG, String.format(Locale.ENGLISH, "prepare: algo=%d captureNum=%d doMFNR=%b doSR=%b", new Object[]{Integer.valueOf(this.mAlgoType), Integer.valueOf(this.mSequenceNum), Boolean.valueOf(this.mShouldDoMFNR), Boolean.valueOf(this.mShouldDoSR)}));
    }

    private void prepareHdr() {
        Log.d(TAG, "prepareHdr: start");
        Byte[] hdrCheckerValues = CaptureResultParser.getHdrCheckerValues(this.mPreviewCaptureResult);
        if (hdrCheckerValues != null && hdrCheckerValues.length >= 1) {
            int i = 0;
            if (hdrCheckerValues[0].byteValue() != (byte) 0) {
                this.mSequenceNum = hdrCheckerValues[0].byteValue();
                if (this.mSequenceNum <= 6) {
                    this.mHdrCheckerEvValue = new int[this.mSequenceNum];
                    if (this.mSequenceNum > 0 && this.mSequenceNum < 6) {
                        while (i < this.mSequenceNum) {
                            int i2 = i + 1;
                            this.mHdrCheckerEvValue[i] = hdrCheckerValues[i2 * 4].byteValue();
                            String str = TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("prepareHdr: evValue[");
                            stringBuilder.append(i);
                            stringBuilder.append("]=");
                            stringBuilder.append(this.mHdrCheckerEvValue[i]);
                            Log.d(str, stringBuilder.toString());
                            i = i2;
                        }
                    }
                    return;
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("wrong sequenceNum ");
                stringBuilder2.append(this.mSequenceNum);
                throw new RuntimeException(stringBuilder2.toString());
            }
        }
        this.mSequenceNum = 3;
        this.mHdrCheckerEvValue = new int[]{-6, 0, 6};
    }

    private void prepareGroupShot() {
        this.mSequenceNum = getGroupShotNum();
    }

    private void prepareClearShot(int i) {
        this.mSequenceNum = 5;
    }

    private int getGroupShotNum() {
        if (Util.isMemoryRich(CameraAppImpl.getAndroidContext())) {
            return getGroupShotMaxImage();
        }
        Log.w(TAG, "getGroupShotNum: low memory");
        return 2;
    }

    private int getGroupShotMaxImage() {
        Face[] faceArr = (Face[]) this.mPreviewCaptureResult.get(CaptureResult.STATISTICS_FACES);
        return Util.clamp((faceArr != null ? faceArr.length : 0) + 1, 2, 4);
    }

    private void applyAlgoParameter(Builder builder, int i, int i2) {
        switch (i2) {
            case 1:
                applyHdrParameter(builder, i);
                return;
            case 2:
                MiCameraCompat.applySwMfnrEnable(builder, this.mShouldDoMFNR);
                return;
            case 3:
                MiCameraCompat.applyMultiFrameInputNum(builder, this.mSequenceNum);
                return;
            default:
                return;
        }
    }

    private void applyHdrParameter(Builder builder, int i) {
        if (i <= this.mSequenceNum) {
            MiCameraCompat.applyHdrBracketMode(builder, (byte) 1);
            MiCameraCompat.applyMultiFrameInputNum(builder, this.mSequenceNum);
            builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.mHdrCheckerEvValue[i]));
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("wrong sequenceNum ");
        stringBuilder.append(i);
        throw new RuntimeException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void startSessionCapture() {
        try {
            CaptureCallback generateCaptureCallback = generateCaptureCallback();
            for (int i = 0; i < this.mSequenceNum; i++) {
                Builder generateRequestBuilder = generateRequestBuilder();
                applyAlgoParameter(generateRequestBuilder, i, this.mAlgoType);
                this.requests.add(generateRequestBuilder.build());
            }
            this.mMiCamera.getCaptureSession().captureBurst(this.requests, generateCaptureCallback, this.mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "Cannot captureBurst");
            this.mMiCamera.notifyOnError(e.getReason());
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to captureBurst, IllegalState", e2);
            this.mMiCamera.notifyOnError(256);
        }
    }

    /* Access modifiers changed, original: protected */
    public CaptureCallback generateCaptureCallback() {
        return new CaptureCallback() {
            long captureTimestamp = -1;

            public void onCaptureStarted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, long j, long j2) {
                String str = MiCamera2ShotParallelBurst.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onCaptureStarted: frameNumber=");
                stringBuilder.append(j2);
                stringBuilder.append(" isFirst=");
                stringBuilder.append(MiCamera2ShotParallelBurst.this.mFirstNum);
                Log.d(str, stringBuilder.toString());
                super.onCaptureStarted(cameraCaptureSession, captureRequest, j, j2);
                if (MiCamera2ShotParallelBurst.this.mFirstNum) {
                    PictureCallback pictureCallback = MiCamera2ShotParallelBurst.this.mMiCamera.getPictureCallback();
                    if (pictureCallback != null) {
                        ParallelTaskData onCaptureStart = pictureCallback.onCaptureStart(new ParallelTaskData(j, MiCamera2ShotParallelBurst.this.mMiCamera.getCameraConfigs().getShotType(), MiCamera2ShotParallelBurst.this.mMiCamera.getCameraConfigs().getShotPath()), MiCamera2ShotParallelBurst.this.mCapturedImageSize);
                        if (onCaptureStart != null) {
                            onCaptureStart.setAlgoType(MiCamera2ShotParallelBurst.this.mAlgoType);
                            onCaptureStart.setBurstNum(MiCamera2ShotParallelBurst.this.mSequenceNum);
                            this.captureTimestamp = j;
                            AlgoConnector.getInstance().getLocalBinder().onCaptureStarted(onCaptureStart);
                        }
                    }
                    MiCamera2ShotParallelBurst.this.mFirstNum = false;
                }
            }

            public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
                boolean z;
                
/*
Method generation error in method: com.android.camera2.MiCamera2ShotParallelBurst.1.onCaptureCompleted(android.hardware.camera2.CameraCaptureSession, android.hardware.camera2.CaptureRequest, android.hardware.camera2.TotalCaptureResult):void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.android.camera2.MiCamera2ShotParallelBurst
  0x0000: IGET  (r2_1 com.android.camera2.MiCamera2ShotParallelBurst) = (r1_0 'this' com.android.camera2.MiCamera2ShotParallelBurst$1 A:{THIS}) com.android.camera2.MiCamera2ShotParallelBurst.1.this$0 com.android.camera2.MiCamera2ShotParallelBurst) com.android.camera2.MiCamera2ShotParallelBurst.access$308(com.android.camera2.MiCamera2ShotParallelBurst):int type: STATIC in method: com.android.camera2.MiCamera2ShotParallelBurst.1.onCaptureCompleted(android.hardware.camera2.CameraCaptureSession, android.hardware.camera2.CaptureRequest, android.hardware.camera2.TotalCaptureResult):void, dex: 
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:228)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:205)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:102)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:52)
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
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:286)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:222)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:205)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:102)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:52)
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
	... 32 more
Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Unknown Source)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:60)
	... 50 more

*/

            public void onCaptureFailed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureFailure captureFailure) {
                super.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
                String str = MiCamera2ShotParallelBurst.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onCaptureFailed: ");
                stringBuilder.append(captureFailure.getReason());
                Log.e(str, stringBuilder.toString());
                MiCamera2ShotParallelBurst.this.mMiCamera.onCapturePictureFinished(false);
                if (this.captureTimestamp != -1) {
                    AlgoConnector.getInstance().getLocalBinder().onCaptureFailed(this.captureTimestamp, captureFailure.getReason());
                }
            }
        };
    }

    /* Access modifiers changed, original: protected */
    public Builder generateRequestBuilder() throws CameraAccessException, IllegalStateException {
        Builder createCaptureRequest = this.mMiCamera.getCameraDevice().createCaptureRequest(2);
        Surface surface;
        String str;
        StringBuilder stringBuilder;
        if (this.mMiCamera.isQcfaEnable()) {
            int size = this.mMiCamera.getRemoteSurfaceList().size();
            CameraSize pictureSize = this.mMiCamera.getPictureSize();
            Surface surface2 = null;
            int i = size;
            surface = null;
            for (Surface surface3 : this.mMiCamera.getRemoteSurfaceList()) {
                Size surfaceSize = SurfaceUtils.getSurfaceSize(surface3);
                if (pictureSize.getWidth() == surfaceSize.getWidth() && pictureSize.getHeight() == surfaceSize.getHeight()) {
                    i--;
                    surface2 = surface3;
                } else if (pictureSize.getWidth() / 2 == surfaceSize.getWidth() && pictureSize.getHeight() / 2 == surfaceSize.getHeight()) {
                    i--;
                    surface = surface3;
                }
            }
            if (surface2 == null || surface == null || i != 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("There is some wrong when QCFA opened! \n");
                stringBuilder2.append(String.format(Locale.ENGLISH, "qcfaLargerSurface = %s; qcfaSmallerSurface = %s; surfaceList size = %s", new Object[]{surface2, surface, Integer.valueOf(i)}));
                throw new RuntimeException(stringBuilder2.toString());
            }
            if (this.mShouldDoQcfaCapture) {
                surface = surface2;
            }
            Size surfaceSize2 = SurfaceUtils.getSurfaceSize(surface);
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("[QCFA] generateRequestBuilder: surface: ");
            stringBuilder.append(surfaceSize2);
            Log.e(str, stringBuilder.toString());
            createCaptureRequest.addTarget(surface);
            configParallelSession(surfaceSize2);
        } else {
            for (Surface surface4 : this.mMiCamera.getRemoteSurfaceList()) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("add surface ");
                stringBuilder.append(surface4);
                stringBuilder.append(" to capture request");
                Log.d(str, stringBuilder.toString());
                createCaptureRequest.addTarget(surface4);
            }
            this.mCapturedImageSize = this.mMiCamera.getPictureSize();
            createCaptureRequest.addTarget(this.mMiCamera.getPreviewSurface());
        }
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
        this.mMiCamera.applySettingsForCapture(createCaptureRequest, 3);
        createCaptureRequest.set(CaptureRequest.CONTROL_ENABLE_ZSL, Boolean.valueOf(false));
        return createCaptureRequest;
    }
}
