package com.android.camera.effect.renders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Size;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.runing.ComponentRunningTiltValue;
import com.android.camera.effect.FilterInfo;
import com.android.camera.effect.FrameBuffer;
import com.android.camera.effect.SnapshotCanvas;
import com.android.camera.effect.draw_mode.DrawBasicTexAttribute;
import com.android.camera.effect.draw_mode.DrawYuvAttribute;
import com.android.camera.log.Log;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.ss.android.ttve.common.TEDefine;
import java.io.File;
import java.io.FileInputStream;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class SnapshotRender {
    private static final int[] CONFIG_SPEC = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12344};
    private static final boolean DUMP_TEXTURE = false;
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final int QUEUE_LIMIT = 7;
    private static final String TAG = SnapshotRender.class.getSimpleName();
    private Bitmap m48MCameraWaterMarkBitmap;
    private String mCurrentCustomWaterMarkText;
    private Bitmap mDualCameraWaterMarkBitmap;
    private DualWatermarkParam mDualCameraWaterMarkParam;
    private EGL10 mEgl;
    private EGLConfig mEglConfig;
    private EGLContext mEglContext;
    private EGLDisplay mEglDisplay;
    private EGLHandler mEglHandler;
    private EGLSurface mEglSurface;
    private HandlerThread mEglThread;
    private ConditionVariable mEglThreadBlockVar = new ConditionVariable();
    private volatile int mImageQueueSize = 0;
    private final Object mLock = new Object();
    private boolean mRelease;
    private boolean mReleasePending;

    private class EGLHandler extends Handler implements WatermarkRender {
        public static final int MSG_DRAW_MAIN_ASYNC = 1;
        public static final int MSG_DRAW_MAIN_SYNC = 2;
        public static final int MSG_INIT_EGL_SYNC = 0;
        public static final int MSG_PREPARE_EFFECT_RENDER = 6;
        public static final int MSG_RELEASE = 5;
        private FrameBuffer mFrameBuffer;
        private SnapshotCanvas mGLCanvas;
        private FrameBuffer mWatermarkFrameBuffer;

        public EGLHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    initEGL();
                    this.mGLCanvas = new SnapshotCanvas();
                    Size size = (Size) message.obj;
                    this.mGLCanvas.setSize(size.getWidth(), size.getHeight());
                    SnapshotRender.this.mEglThreadBlockVar.open();
                    return;
                case 1:
                    drawImage((DrawYuvAttribute) message.obj);
                    this.mGLCanvas.recycledResources();
                    if (SnapshotRender.this.mReleasePending && !hasMessages(1)) {
                        release();
                    }
                    synchronized (SnapshotRender.this.mLock) {
                        
/*
Method generation error in method: com.android.camera.effect.renders.SnapshotRender.EGLHandler.handleMessage(android.os.Message):void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0051: INVOKE  (wrap: com.android.camera.effect.renders.SnapshotRender
  0x004f: IGET  (r3_15 com.android.camera.effect.renders.SnapshotRender) = (r2_0 'this' com.android.camera.effect.renders.SnapshotRender$EGLHandler A:{THIS}) com.android.camera.effect.renders.SnapshotRender.EGLHandler.this$0 com.android.camera.effect.renders.SnapshotRender) com.android.camera.effect.renders.SnapshotRender.access$310(com.android.camera.effect.renders.SnapshotRender):int type: STATIC in method: com.android.camera.effect.renders.SnapshotRender.EGLHandler.handleMessage(android.os.Message):void, dex: 
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:228)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:205)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:102)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:52)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:230)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:67)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:321)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:259)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:221)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:111)
	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:234)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
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
	... 31 more
Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Unknown Source)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:60)
	... 49 more

*/

        private void initEGL() {
            SnapshotRender.this.mEgl = (EGL10) EGLContext.getEGL();
            SnapshotRender.this.mEglDisplay = SnapshotRender.this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (SnapshotRender.this.mEglDisplay != EGL10.EGL_NO_DISPLAY) {
                int[] iArr = new int[2];
                if (SnapshotRender.this.mEgl.eglInitialize(SnapshotRender.this.mEglDisplay, iArr)) {
                    String access$600 = SnapshotRender.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("EGL version: ");
                    stringBuilder.append(iArr[0]);
                    stringBuilder.append('.');
                    stringBuilder.append(iArr[1]);
                    Log.v(access$600, stringBuilder.toString());
                    int[] iArr2 = new int[]{SnapshotRender.EGL_CONTEXT_CLIENT_VERSION, 2, 12344};
                    SnapshotRender.this.mEglConfig = SnapshotRender.chooseConfig(SnapshotRender.this.mEgl, SnapshotRender.this.mEglDisplay);
                    SnapshotRender.this.mEglContext = SnapshotRender.this.mEgl.eglCreateContext(SnapshotRender.this.mEglDisplay, SnapshotRender.this.mEglConfig, EGL10.EGL_NO_CONTEXT, iArr2);
                    if (SnapshotRender.this.mEglContext == null || SnapshotRender.this.mEglContext == EGL10.EGL_NO_CONTEXT) {
                        throw new RuntimeException("failed to createContext");
                    }
                    SnapshotRender.this.mEglSurface = SnapshotRender.this.mEgl.eglCreatePbufferSurface(SnapshotRender.this.mEglDisplay, SnapshotRender.this.mEglConfig, new int[]{12375, Util.sWindowWidth, 12374, Util.sWindowHeight, 12344});
                    if (SnapshotRender.this.mEglSurface == null || SnapshotRender.this.mEglSurface == EGL10.EGL_NO_SURFACE) {
                        throw new RuntimeException("failed to createWindowSurface");
                    } else if (!SnapshotRender.this.mEgl.eglMakeCurrent(SnapshotRender.this.mEglDisplay, SnapshotRender.this.mEglSurface, SnapshotRender.this.mEglSurface, SnapshotRender.this.mEglContext)) {
                        throw new RuntimeException("failed to eglMakeCurrent");
                    } else {
                        return;
                    }
                }
                throw new RuntimeException("eglInitialize failed");
            }
            throw new RuntimeException("eglGetDisplay failed");
        }

        private void drawWaterMark(WaterMark waterMark, int i, int i2, int i3) {
            this.mGLCanvas.getState().pushState();
            if (i3 != 0) {
                this.mGLCanvas.getState().translate((float) (waterMark.getCenterX() + i), (float) (waterMark.getCenterY() + i2));
                this.mGLCanvas.getState().rotate((float) (-i3), PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f);
                this.mGLCanvas.getState().translate((float) ((-i) - waterMark.getCenterX()), (float) ((-i2) - waterMark.getCenterY()));
            }
            this.mGLCanvas.getBasicRender().draw(new DrawBasicTexAttribute(waterMark.getTexture(), i + waterMark.getLeft(), i2 + waterMark.getTop(), waterMark.getWidth(), waterMark.getHeight()));
            this.mGLCanvas.getState().popState();
            this.mGLCanvas.deleteTexture(waterMark.getTexture().getId());
        }

        public void drawWaterMark(int i, int i2, int i3, int i4, int i5, String str) {
            if (str != null) {
                drawWaterMark(new NewStyleTextWaterMark(str, i3, i4, i5), i, i2, i5);
            }
            if (SnapshotRender.this.mDualCameraWaterMarkBitmap != null && SnapshotRender.this.mDualCameraWaterMarkParam.isDualWatermarkEnable()) {
                if (!(SnapshotRender.this.mCurrentCustomWaterMarkText == null || SnapshotRender.this.mCurrentCustomWaterMarkText.equals(CameraSettings.getCustomWatermark()))) {
                    SnapshotRender.this.mCurrentCustomWaterMarkText = CameraSettings.getCustomWatermark();
                    SnapshotRender.this.mDualCameraWaterMarkBitmap = SnapshotRender.this.loadCameraWatermark(CameraAppImpl.getAndroidContext());
                }
                Bitmap access$1100 = SnapshotRender.this.mDualCameraWaterMarkBitmap;
                boolean equals = CameraSettings.getCustomWatermark().equals(CameraSettings.getDefaultWatermarkStr());
                Object obj = (CameraSettings.isUltraPixelPhotographyOn() || CameraSettings.isRearMenuUltraPixelPhotographyOn()) ? 1 : null;
                if (obj != null && equals) {
                    if (SnapshotRender.this.m48MCameraWaterMarkBitmap == null) {
                        SnapshotRender.this.m48MCameraWaterMarkBitmap = SnapshotRender.this.load48MWatermark(CameraAppImpl.getAndroidContext());
                    }
                    if (SnapshotRender.this.m48MCameraWaterMarkBitmap != null) {
                        access$1100 = SnapshotRender.this.m48MCameraWaterMarkBitmap;
                    }
                }
                drawWaterMark(new ImageWaterMark(access$1100, i3, i4, i5, SnapshotRender.this.mDualCameraWaterMarkParam.getSize(), SnapshotRender.this.mDualCameraWaterMarkParam.getPaddingX(), SnapshotRender.this.mDualCameraWaterMarkParam.getPaddingY()), i, i2, i5);
            }
        }

        /* JADX WARNING: Missing block: B:10:0x004f, code skipped:
            return;
     */
        public void drawFaceWaterMarkInfos(android.util.Size r12, android.util.Size r13, int r14, java.util.List<com.android.camera.watermark.WaterMarkData> r15) {
            /*
            r11 = this;
            if (r15 == 0) goto L_0x004f;
        L_0x0002:
            r0 = r15.isEmpty();
            if (r0 == 0) goto L_0x0009;
        L_0x0008:
            goto L_0x004f;
        L_0x0009:
            r0 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
            if (r0 == 0) goto L_0x004e;
        L_0x000f:
            r0 = new com.android.camera.watermark.WaterMarkBitmap;
            r0.<init>(r15);
            r15 = r0.getWaterMarkData();
            if (r15 == 0) goto L_0x0040;
        L_0x001a:
            r10 = new com.android.camera.effect.renders.AgeGenderAndMagicMirrorWaterMark;
            r2 = r15.getImage();
            r3 = r12.getWidth();
            r4 = r12.getHeight();
            r6 = r13.getWidth();
            r7 = r13.getHeight();
            r8 = 0;
            r9 = 0;
            r1 = r10;
            r5 = r14;
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
            r12 = r15.getOrientation();
            r14 = r14 - r12;
            r12 = 0;
            r11.drawWaterMark(r10, r12, r12, r14);
        L_0x0040:
            r0.releaseBitmap();
            r12 = com.android.camera.watermark.WaterMarkBitmap.class;
            r12 = r12.getSimpleName();
            r13 = "Draw age_gender_and_magic_mirror water mark";
            com.android.camera.log.Log.d(r12, r13);
        L_0x004e:
            return;
        L_0x004f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.camera.effect.renders.SnapshotRender$EGLHandler.drawFaceWaterMarkInfos(android.util.Size, android.util.Size, int, java.util.List):void");
        }

        private void updateRenderParameters(Render render, DrawYuvAttribute drawYuvAttribute) {
            if (render instanceof PipeRender) {
                ((PipeRender) render).setFrameBufferSize(drawYuvAttribute.mPictureSize.getWidth(), drawYuvAttribute.mPictureSize.getHeight());
            }
            render.setViewportSize(drawYuvAttribute.mPictureSize.getWidth(), drawYuvAttribute.mPictureSize.getHeight());
            render.setPreviewSize(drawYuvAttribute.mPreviewSize.getWidth(), drawYuvAttribute.mPreviewSize.getHeight());
            render.setEffectRangeAttribute(drawYuvAttribute.mAttribute);
            render.setMirror(drawYuvAttribute.mMirror);
            render.setSnapshotSize(drawYuvAttribute.mPictureSize.getWidth(), drawYuvAttribute.mPictureSize.getHeight());
            render.setOrientation(drawYuvAttribute.mOrientation);
            render.setShootRotation(drawYuvAttribute.mShootRotation);
            render.setJpegOrientation(drawYuvAttribute.mJpegRotation);
        }

        /* JADX WARNING: Removed duplicated region for block: B:40:0x0195  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x0256  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x025f  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x02e9  */
        private byte[] applyEffect(com.android.camera.effect.draw_mode.DrawYuvAttribute r46) {
            /*
            r45 = this;
            r7 = r45;
            r0 = r46;
            r1 = r45.getEffectRender(r46);
            r2 = 0;
            if (r1 != 0) goto L_0x0015;
        L_0x000b:
            r0 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r1 = "init render failed";
            com.android.camera.log.Log.w(r0, r1);
            return r2;
            r3 = r0.mPictureSize;
            r3 = r3.getWidth();
            r4 = r0.mPictureSize;
            r4 = r4.getHeight();
            r5 = java.lang.System.currentTimeMillis();
            r8 = r0.mEffectIndex;
            r9 = com.android.camera.effect.FilterInfo.FILTER_ID_NONE;
            r10 = 3;
            r11 = 2;
            if (r8 != r9) goto L_0x0105;
        L_0x0030:
            r8 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
            if (r8 != 0) goto L_0x0105;
        L_0x0036:
            r8 = com.android.camera.module.ModuleManager.isSquareModule();
            if (r8 != 0) goto L_0x0105;
        L_0x003c:
            r8 = com.android.camera.CameraSettings.isGradienterOn();
            if (r8 != 0) goto L_0x0105;
        L_0x0042:
            r8 = com.android.camera.CameraSettings.isTiltShiftOn();
            if (r8 != 0) goto L_0x0105;
        L_0x0048:
            r8 = r0.mApplyWaterMark;
            if (r8 != 0) goto L_0x0050;
        L_0x004c:
            r8 = r0.mTimeWatermark;
            if (r8 == 0) goto L_0x0105;
            r2 = r0.mPictureSize;
            r14 = r2.getWidth();
            r2 = r0.mPictureSize;
            r15 = r2.getHeight();
            r2 = r0.mJpegRotation;
            r2 = r2 + 270;
            r2 = r2 % 360;
            r8 = r0.mApplyWaterMark;
            r9 = r0.mTimeWatermark;
            if (r9 == 0) goto L_0x006c;
        L_0x0069:
            r18 = 1;
            goto L_0x006e;
        L_0x006c:
            r18 = 0;
        L_0x006e:
            r19 = 1036831949; // 0x3dcccccd float:0.1 double:5.122630465E-315;
            r16 = r2;
            r17 = r8;
            r2 = com.android.camera.Util.getWatermarkRange(r14, r15, r16, r17, r18, r19);
            r8 = r0.mImage;
            r8 = com.xiaomi.camera.base.ImageUtil.getYuvData(r8);
            r9 = com.android.camera.Util.getSubYuvImage(r8, r3, r4, r2);
            r14 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r15 = new java.lang.StringBuilder;
            r15.<init>();
            r12 = "get sub range data spend total tome =";
            r15.append(r12);
            r16 = java.lang.System.currentTimeMillis();
            r5 = r16 - r5;
            r15.append(r5);
            r5 = r15.toString();
            com.android.camera.log.Log.d(r14, r5);
            r5 = new com.android.camera.effect.draw_mode.DrawYuvAttribute;
            r6 = r0.mImage;
            r12 = r0.mPreviewSize;
            r14 = new android.util.Size;
            r15 = r2[r11];
            r13 = r2[r10];
            r14.<init>(r15, r13);
            r13 = r0.mEffectIndex;
            r15 = r0.mOrientation;
            r10 = r0.mJpegRotation;
            r11 = r0.mShootRotation;
            r38 = r2;
            r37 = r3;
            r2 = r0.mDate;
            r39 = r8;
            r8 = r0.mMirror;
            r40 = r4;
            r4 = r0.mApplyWaterMark;
            r41 = r1;
            r1 = r0.mIsGradienterOn;
            r7 = r0.mTiltShiftMode;
            r42 = r9;
            r9 = r0.mTimeWatermark;
            r43 = r9;
            r9 = r0.mAttribute;
            r0 = r0.mWaterInfos;
            r20 = r5;
            r21 = r6;
            r22 = r12;
            r23 = r14;
            r24 = r13;
            r25 = r15;
            r26 = r10;
            r27 = r11;
            r28 = r2;
            r30 = r8;
            r31 = r4;
            r32 = r1;
            r33 = r7;
            r34 = r43;
            r35 = r9;
            r36 = r0;
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r30, r31, r32, r33, r34, r35, r36);
            r0 = r42;
            r5.mYuvImage = r0;
            r7 = r5;
            r9 = r38;
            r10 = r39;
            r8 = 1;
            goto L_0x010f;
        L_0x0105:
            r41 = r1;
            r37 = r3;
            r40 = r4;
            r7 = r0;
            r9 = r2;
            r10 = r9;
            r8 = 0;
        L_0x010f:
            r0 = r41;
            r11 = r45;
            r11.updateRenderParameters(r0, r7);
            if (r8 == 0) goto L_0x011d;
        L_0x0118:
            r1 = 2;
            r3 = r9[r1];
            r12 = r3;
            goto L_0x011f;
        L_0x011d:
            r12 = r37;
        L_0x011f:
            if (r8 == 0) goto L_0x0126;
        L_0x0121:
            r1 = 3;
            r4 = r9[r1];
            r13 = r4;
            goto L_0x0128;
        L_0x0126:
            r13 = r40;
        L_0x0128:
            r11.checkFrameBuffer(r12, r13);
            r1 = r11.mGLCanvas;
            r2 = r11.mFrameBuffer;
            r1.beginBindFrameBuffer(r2);
            r1 = java.lang.System.currentTimeMillis();
            android.opengl.GLES20.glFlush();
            r3 = r11.mFrameBuffer;
            r3 = r3.getId();
            r0.setParentFrameBufferId(r3);
            r0.draw(r7);
            r3 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "drawTime=";
            r4.append(r5);
            r5 = java.lang.System.currentTimeMillis();
            r5 = r5 - r1;
            r4.append(r5);
            r1 = r4.toString();
            com.android.camera.log.Log.d(r3, r1);
            r0.deleteBuffer();
            r0 = new android.util.Size;
            r1 = r37;
            r2 = r40;
            r0.<init>(r1, r2);
            r7.mOriginalSize = r0;
            r0 = com.android.camera.module.ModuleManager.isSquareModule();
            if (r0 == 0) goto L_0x018d;
        L_0x0178:
            if (r1 <= r2) goto L_0x0183;
        L_0x017a:
            r3 = r1 - r2;
            r0 = 2;
            r1 = r3 / 2;
            r14 = r2;
            r15 = r14;
            goto L_0x0190;
        L_0x0183:
            r0 = 2;
            r4 = r2 - r1;
            r2 = r4 / 2;
            r14 = r1;
            r15 = r14;
            r1 = 0;
            goto L_0x0191;
        L_0x018d:
            r14 = r1;
            r15 = r2;
            r1 = 0;
        L_0x0190:
            r2 = 0;
        L_0x0191:
            r0 = r7.mApplyWaterMark;
            if (r0 == 0) goto L_0x0249;
        L_0x0195:
            r16 = java.lang.System.currentTimeMillis();
            if (r8 == 0) goto L_0x01a5;
        L_0x019b:
            r0 = 0;
            r1 = r9[r0];
            r0 = -r1;
            r1 = 1;
            r2 = r9[r1];
            r1 = -r2;
            r2 = r1;
            r1 = r0;
        L_0x01a5:
            r5 = r7.mJpegRotation;
            r6 = r7.mTimeWatermark;
            r0 = r11;
            r3 = r14;
            r4 = r15;
            r0.drawWaterMark(r1, r2, r3, r4, r5, r6);
            r0 = r7.mOriginalSize;
            r1 = r7.mPreviewSize;
            r2 = r7.mJpegRotation;
            r3 = r7.mWaterInfos;
            r11.drawFaceWaterMarkInfos(r0, r1, r2, r3);
            r0 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "watermarkTime=";
            r1.append(r2);
            r2 = java.lang.System.currentTimeMillis();
            r2 = r2 - r16;
            r1.append(r2);
            r1 = r1.toString();
            com.android.camera.log.Log.d(r0, r1);
            r0 = r11.mGLCanvas;
            r0.endBindFrameBuffer();
            if (r8 == 0) goto L_0x01e5;
        L_0x01df:
            r0 = new android.util.Size;
            r0.<init>(r12, r13);
            goto L_0x01e7;
        L_0x01e5:
            r0 = r7.mOriginalSize;
        L_0x01e7:
            r11.checkWatermarkFrameBuffer(r0);
            r1 = r11.mGLCanvas;
            r2 = r11.mWatermarkFrameBuffer;
            r1.beginBindFrameBuffer(r2);
            r1 = java.lang.System.currentTimeMillis();
            r3 = com.android.camera.effect.FilterInfo.FILTER_ID_RGB2YUV;
            r3 = r11.fetchRender(r3);
            r3 = (com.android.camera.effect.renders.RgbToYuvRender) r3;
            r11.updateRenderParameters(r3, r7);
            r4 = r11.mFrameBuffer;
            r4 = r4.getId();
            r3.setParentFrameBufferId(r4);
            r4 = r11.mFrameBuffer;
            r4 = r4.getTexture();
            r21 = r4.getId();
            r22 = 0;
            r23 = 0;
            r4 = r0.getWidth();
            r4 = (float) r4;
            r0 = r0.getHeight();
            r0 = (float) r0;
            r26 = 1;
            r20 = r3;
            r24 = r4;
            r25 = r0;
            r20.drawTexture(r21, r22, r23, r24, r25, r26);
            r0 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "rgb2YuvTime=";
            r3.append(r4);
            r4 = java.lang.System.currentTimeMillis();
            r4 = r4 - r1;
            r3.append(r4);
            r1 = r3.toString();
            com.android.camera.log.Log.d(r0, r1);
        L_0x0249:
            r0 = 3333; // 0xd05 float:4.67E-42 double:1.6467E-320;
            r1 = 1;
            android.opengl.GLES20.glPixelStorei(r0, r1);
            r0 = java.lang.System.currentTimeMillis();
            if (r8 == 0) goto L_0x0256;
        L_0x0255:
            goto L_0x025c;
        L_0x0256:
            r2 = r7.mOriginalSize;
            r12 = r2.getWidth();
        L_0x025c:
            if (r8 == 0) goto L_0x025f;
        L_0x025e:
            goto L_0x0265;
        L_0x025f:
            r2 = r7.mOriginalSize;
            r13 = r2.getHeight();
        L_0x0265:
            r2 = (double) r12;
            r4 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
            r2 = r2 / r4;
            r2 = java.lang.Math.ceil(r2);
            r2 = (int) r2;
            r3 = (double) r13;
            r5 = 4613937818241073152; // 0x4008000000000000 float:0.0 double:3.0;
            r3 = r3 * r5;
            r5 = 4616189618054758400; // 0x4010000000000000 float:0.0 double:4.0;
            r3 = r3 / r5;
            r3 = java.lang.Math.ceil(r3);
            r3 = (int) r3;
            r4 = r2 * r3;
            r5 = 4;
            r4 = r4 * r5;
            r4 = java.nio.ByteBuffer.allocate(r4);
            r20 = 0;
            r21 = 0;
            r24 = 6408; // 0x1908 float:8.98E-42 double:3.166E-320;
            r25 = 5121; // 0x1401 float:7.176E-42 double:2.53E-320;
            r22 = r2;
            r23 = r3;
            r26 = r4;
            android.opengl.GLES20.glReadPixels(r20, r21, r22, r23, r24, r25, r26);
            r4.rewind();
            r6 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r11 = java.util.Locale.ENGLISH;
            r44 = r7;
            r7 = "readSize=%dx%d imageSize=%dx%d";
            r5 = new java.lang.Object[r5];
            r2 = java.lang.Integer.valueOf(r2);
            r16 = 0;
            r5[r16] = r2;
            r2 = java.lang.Integer.valueOf(r3);
            r3 = 1;
            r5[r3] = r2;
            r2 = java.lang.Integer.valueOf(r12);
            r3 = 2;
            r5[r3] = r2;
            r2 = java.lang.Integer.valueOf(r13);
            r3 = 3;
            r5[r3] = r2;
            r2 = java.lang.String.format(r11, r7, r5);
            com.android.camera.log.Log.d(r6, r2);
            r2 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r5 = "readTime=";
            r3.append(r5);
            r5 = java.lang.System.currentTimeMillis();
            r5 = r5 - r0;
            r3.append(r5);
            r0 = r3.toString();
            com.android.camera.log.Log.d(r2, r0);
            r0 = r4.array();
            if (r8 == 0) goto L_0x0313;
        L_0x02e9:
            r0 = java.lang.System.currentTimeMillis();
            r2 = r4.array();
            com.android.camera.Util.coverSubYuvImage(r10, r14, r15, r2, r9);
            r2 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "cover sub range data spend total time =";
            r3.append(r4);
            r4 = java.lang.System.currentTimeMillis();
            r4 = r4 - r0;
            r3.append(r4);
            r0 = r3.toString();
            com.android.camera.log.Log.d(r2, r0);
            r0 = r10;
        L_0x0313:
            r1 = java.lang.System.currentTimeMillis();
            r5 = r44;
            r3 = r5.mImage;
            com.xiaomi.camera.base.ImageUtil.updateYuvImage(r3, r0);
            r3 = com.android.camera.effect.renders.SnapshotRender.TAG;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "updateImageTime=";
            r4.append(r5);
            r5 = java.lang.System.currentTimeMillis();
            r5 = r5 - r1;
            r4.append(r5);
            r1 = r4.toString();
            com.android.camera.log.Log.d(r3, r1);
            r1 = r45;
            r2 = r1.mGLCanvas;
            r2.endBindFrameBuffer();
            r1 = r1.mGLCanvas;
            r1.recycledResources();
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.camera.effect.renders.SnapshotRender$EGLHandler.applyEffect(com.android.camera.effect.draw_mode.DrawYuvAttribute):byte[]");
        }

        private Render fetchRender(int i) {
            RenderGroup effectRenderGroup = this.mGLCanvas.getEffectRenderGroup();
            Render render = effectRenderGroup.getRender(i);
            if (render != null) {
                return render;
            }
            this.mGLCanvas.prepareEffectRenders(false, i);
            return effectRenderGroup.getRender(i);
        }

        private PipeRender getEffectRender(DrawYuvAttribute drawYuvAttribute) {
            Render fetchRender;
            PipeRender pipeRender = new PipeRender(this.mGLCanvas);
            pipeRender.addRender(fetchRender(FilterInfo.FILTER_ID_YUV2RGB));
            if (drawYuvAttribute.mEffectIndex != FilterInfo.FILTER_ID_NONE) {
                fetchRender = fetchRender(drawYuvAttribute.mEffectIndex);
                if (fetchRender != null) {
                    pipeRender.addRender(fetchRender);
                }
            }
            if (drawYuvAttribute.mIsGradienterOn) {
                fetchRender = fetchRender(FilterInfo.FILTER_ID_GRADIENTER);
                if (fetchRender != null) {
                    pipeRender.addRender(fetchRender);
                }
            } else if (drawYuvAttribute.mTiltShiftMode != null) {
                fetchRender = null;
                if (ComponentRunningTiltValue.TILT_CIRCLE.equals(drawYuvAttribute.mTiltShiftMode)) {
                    fetchRender = fetchRender(FilterInfo.FILTER_ID_GAUSSIAN);
                } else if (ComponentRunningTiltValue.TILT_PARALLEL.equals(drawYuvAttribute.mTiltShiftMode)) {
                    fetchRender = fetchRender(FilterInfo.FILTER_ID_TILTSHIFT);
                }
                if (fetchRender != null) {
                    pipeRender.addRender(fetchRender);
                }
            }
            if (!drawYuvAttribute.mApplyWaterMark) {
                pipeRender.addRender(fetchRender(FilterInfo.FILTER_ID_RGB2YUV));
            }
            return pipeRender;
        }

        private boolean drawImage(DrawYuvAttribute drawYuvAttribute) {
            byte[] applyEffect = applyEffect(drawYuvAttribute);
            String access$600 = SnapshotRender.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mainLen=");
            stringBuilder.append(applyEffect == null ? TEDefine.FACE_BEAUTY_NULL : Integer.valueOf(applyEffect.length));
            Log.d(access$600, stringBuilder.toString());
            return true;
        }

        private void checkFrameBuffer(int i, int i2) {
            if (this.mFrameBuffer == null || this.mFrameBuffer.getWidth() != i || this.mFrameBuffer.getHeight() != i2) {
                this.mFrameBuffer = new FrameBuffer(this.mGLCanvas, i, i2, 0);
            }
        }

        private void checkWatermarkFrameBuffer(Size size) {
            if (this.mWatermarkFrameBuffer == null || this.mWatermarkFrameBuffer.getWidth() < size.getWidth() || this.mWatermarkFrameBuffer.getHeight() < size.getHeight()) {
                this.mWatermarkFrameBuffer = new FrameBuffer(this.mGLCanvas, size.getWidth(), size.getHeight(), 0);
            }
        }

        private void release() {
            this.mFrameBuffer = null;
            this.mWatermarkFrameBuffer = null;
            this.mGLCanvas.recycledResources();
            this.mGLCanvas = null;
            SnapshotRender.this.destroy();
        }

        public void sendMessageSync(Message message) {
            SnapshotRender.this.mEglThreadBlockVar.close();
            sendMessage(message);
            SnapshotRender.this.mEglThreadBlockVar.block();
        }
    }

    public SnapshotRender(@NonNull Size size) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SnapshotRender created ");
        stringBuilder.append(this);
        stringBuilder.append("; with size : ");
        stringBuilder.append(size);
        Log.d(str, stringBuilder.toString());
        this.mEglThread = new HandlerThread("SnapshotRender");
        this.mEglThread.start();
        this.mEglHandler = new EGLHandler(this.mEglThread.getLooper());
        this.mEglHandler.sendMessageSync(this.mEglHandler.obtainMessage(0, size));
        this.mRelease = false;
    }

    private Bitmap loadCameraWatermark(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        Options options = new Options();
        options.inScaled = false;
        options.inPurgeable = true;
        options.inPremultiplied = false;
        if (!DataRepository.dataItemFeature().fg()) {
            return BitmapFactory.decodeFile(CameraSettings.getDualCameraWaterMarkFilePathVendor(), options);
        }
        File file = new File(context.getFilesDir(), Util.WATERMARK_FILE_NAME);
        if (!file.exists()) {
            Util.generateWatermark2File();
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                $closeResource(null, fileInputStream);
                return decodeStream;
            } catch (Throwable th22) {
                Throwable th3 = th22;
                th22 = th;
                th = th3;
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to load app camera watermark ", e);
            return null;
        }
        $closeResource(th22, fileInputStream);
        throw th;
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

    private Bitmap load48MWatermark(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        Options options = new Options();
        options.inScaled = false;
        options.inPurgeable = true;
        options.inPremultiplied = false;
        if (DataRepository.dataItemFeature().fg()) {
            File file = new File(context.getFilesDir(), Util.WATERMARK_48M_FILE_NAME);
            if (!file.exists()) {
                return Util.generate48MWatermark2File();
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                    $closeResource(null, fileInputStream);
                    return decodeStream;
                } catch (Throwable th22) {
                    Throwable th3 = th22;
                    th22 = th;
                    th = th3;
                }
            } catch (Exception e) {
                Log.d(TAG, "Failed to load app camera watermark ", e);
            }
        }
        return null;
        $closeResource(th22, fileInputStream);
        throw th;
    }

    public boolean processImageAsync(DrawYuvAttribute drawYuvAttribute) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("queueSize=");
        stringBuilder.append(this.mImageQueueSize);
        Log.d(str, stringBuilder.toString());
        if (this.mImageQueueSize >= 7) {
            Log.d(TAG, "queueSize is full");
            return false;
        }
        synchronized (this.mLock) {
            this.mImageQueueSize++;
        }
        this.mEglHandler.obtainMessage(1, drawYuvAttribute).sendToTarget();
        return true;
    }

    public void processImageSync(DrawYuvAttribute drawYuvAttribute) {
        this.mEglThreadBlockVar.close();
        this.mEglHandler.obtainMessage(2, drawYuvAttribute).sendToTarget();
        this.mEglThreadBlockVar.block();
    }

    public boolean isRelease() {
        return this.mReleasePending || this.mRelease;
    }

    public void release() {
        if (this.mEglHandler.hasMessages(1)) {
            Log.d(TAG, "release: try to release but message is not null, so pending it");
            this.mReleasePending = true;
            return;
        }
        this.mEglHandler.sendEmptyMessage(5);
    }

    private static EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        int[] iArr = new int[1];
        if (egl10.eglChooseConfig(eGLDisplay, CONFIG_SPEC, null, 0, iArr)) {
            int i = iArr[0];
            if (i > 0) {
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, CONFIG_SPEC, eGLConfigArr, i, iArr)) {
                    return eGLConfigArr[0];
                }
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            throw new IllegalArgumentException("No configs match configSpec");
        }
        throw new IllegalArgumentException("eglChooseConfig failed");
    }

    public void prepareEffectRender(DualWatermarkParam dualWatermarkParam, int i) {
        this.mDualCameraWaterMarkParam = dualWatermarkParam;
        if (dualWatermarkParam.isDualWatermarkEnable() && this.mDualCameraWaterMarkBitmap == null) {
            Options options = new Options();
            options.inScaled = false;
            options.inPurgeable = true;
            options.inPremultiplied = false;
            this.mDualCameraWaterMarkBitmap = loadCameraWatermark(CameraAppImpl.getAndroidContext());
            this.mCurrentCustomWaterMarkText = CameraSettings.getCustomWatermark();
        }
        if (i != FilterInfo.FILTER_ID_NONE) {
            this.mEglHandler.obtainMessage(6, i, 0).sendToTarget();
        }
    }

    private void destroy() {
        this.mRelease = true;
        this.mReleasePending = false;
        this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
        this.mEgl.eglDestroyContext(this.mEglDisplay, this.mEglContext);
        this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.mEgl.eglTerminate(this.mEglDisplay);
        this.mEglSurface = null;
        this.mEglContext = null;
        this.mEglDisplay = null;
        this.mEglThread.quit();
        if (!(this.mDualCameraWaterMarkBitmap == null || this.mDualCameraWaterMarkBitmap.isRecycled())) {
            this.mDualCameraWaterMarkBitmap.recycle();
            this.mDualCameraWaterMarkBitmap = null;
        }
        System.gc();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SnapshotRender released ");
        stringBuilder.append(this);
        Log.d(str, stringBuilder.toString());
    }
}
