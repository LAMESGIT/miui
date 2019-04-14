package com.android.camera.effect.renders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.location.Location;
import android.net.Uri;
import android.opengl.GLES20;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import com.android.camera.ActivityBase;
import com.android.camera.CameraSettings;
import com.android.camera.Thumbnail;
import com.android.camera.Util;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.runing.ComponentRunningTiltValue;
import com.android.camera.effect.FilterInfo;
import com.android.camera.effect.FrameBuffer;
import com.android.camera.effect.ShaderNativeUtil;
import com.android.camera.effect.SnapshotCanvas;
import com.android.camera.effect.draw_mode.DrawBasicTexAttribute;
import com.android.camera.effect.draw_mode.DrawIntTexAttribute;
import com.android.camera.effect.draw_mode.DrawJPEGAttribute;
import com.android.camera.log.Log;
import com.android.camera.sticker.glutils.OpenGLUtils;
import com.android.camera.storage.ImageSaver;
import com.android.camera.storage.Storage;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.android.gallery3d.exif.ExifInterface;
import com.android.gallery3d.ui.BaseGLCanvas;
import com.mi.config.b;
import com.ss.android.ttve.common.TEDefine;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class SnapshotEffectRender {
    private static final int[] CONFIG_SPEC = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12344};
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final int MAX_QUALITY = 97;
    private static final int QUEUE_LIMIT = 7;
    private static final String TAG = SnapshotEffectRender.class.getSimpleName();
    private Bitmap m48MCameraWaterMarkBitmap;
    private ActivityBase mActivity;
    private String mCurrentCustomWaterMarkText;
    private Bitmap mDualCameraWaterMarkBitmap;
    private float mDualCameraWaterMarkPaddingXRatio;
    private float mDualCameraWaterMarkPaddingYRatio;
    private float mDualCameraWaterMarkSizeRatio;
    private EGL10 mEgl;
    private EGLConfig mEglConfig;
    private EGLContext mEglContext;
    private EGLDisplay mEglDisplay;
    private EGLHandler mEglHandler;
    private EGLSurface mEglSurface;
    private HandlerThread mEglThread;
    private ConditionVariable mEglThreadBlockVar = new ConditionVariable();
    private boolean mExifNeeded = true;
    private ImageSaver mImageSaver;
    private boolean mIsImageCaptureIntent;
    private volatile int mJpegQueueSize = 0;
    private final Object mLock = new Object();
    private int mQuality = 97;
    private boolean mRelease;
    private boolean mReleasePending;
    private int mSquareModeExtraMargin;
    private Map<String, String> mTitleMap = new HashMap(7);

    private class EGLHandler extends Handler {
        public static final int MSG_DRAW_MAIN_ASYNC = 1;
        public static final int MSG_DRAW_MAIN_SYNC = 2;
        public static final int MSG_DRAW_THUMB = 4;
        public static final int MSG_GET_DRAW_THUMB = 3;
        public static final int MSG_INIT_EGL_SYNC = 0;
        public static final int MSG_PREPARE_EFFECT_RENDER = 6;
        public static final int MSG_RELEASE = 5;
        private FrameBuffer mFrameBuffer;
        private SnapshotCanvas mGLCanvas;

        public EGLHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (SnapshotEffectRender.this.mActivity != null) {
                boolean z = false;
                boolean z2 = true;
                DrawJPEGAttribute drawJPEGAttribute;
                switch (message.what) {
                    case 0:
                        initEGL();
                        this.mGLCanvas = new SnapshotCanvas();
                        this.mGLCanvas.setSize(SnapshotEffectRender.this.mActivity.getCameraScreenNail().getWidth(), SnapshotEffectRender.this.mActivity.getCameraScreenNail().getHeight());
                        SnapshotEffectRender.this.mEglThreadBlockVar.open();
                        break;
                    case 1:
                        drawJPEGAttribute = (DrawJPEGAttribute) message.obj;
                        if (message.arg1 > 0) {
                            z = true;
                        }
                        drawMainJpeg(drawJPEGAttribute, true, z);
                        this.mGLCanvas.recycledResources();
                        if (SnapshotEffectRender.this.mReleasePending && !hasMessages(1)) {
                            release();
                        }
                        synchronized (SnapshotEffectRender.this.mLock) {
                            
/*
Method generation error in method: com.android.camera.effect.renders.SnapshotEffectRender.EGLHandler.handleMessage(android.os.Message):void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0092: INVOKE  (wrap: com.android.camera.effect.renders.SnapshotEffectRender
  0x0090: IGET  (r0_8 com.android.camera.effect.renders.SnapshotEffectRender) = (r4_0 'this' com.android.camera.effect.renders.SnapshotEffectRender$EGLHandler A:{THIS}) com.android.camera.effect.renders.SnapshotEffectRender.EGLHandler.this$0 com.android.camera.effect.renders.SnapshotEffectRender) com.android.camera.effect.renders.SnapshotEffectRender.access$410(com.android.camera.effect.renders.SnapshotEffectRender):int type: STATIC in method: com.android.camera.effect.renders.SnapshotEffectRender.EGLHandler.handleMessage(android.os.Message):void, dex: 
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
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:120)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
	... 38 more
Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Unknown Source)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:60)
	... 56 more

*/

        private void initEGL() {
            SnapshotEffectRender.this.mEgl = (EGL10) EGLContext.getEGL();
            SnapshotEffectRender.this.mEglDisplay = SnapshotEffectRender.this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (SnapshotEffectRender.this.mEglDisplay != EGL10.EGL_NO_DISPLAY) {
                int[] iArr = new int[2];
                if (SnapshotEffectRender.this.mEgl.eglInitialize(SnapshotEffectRender.this.mEglDisplay, iArr)) {
                    String access$700 = SnapshotEffectRender.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("EGL version: ");
                    stringBuilder.append(iArr[0]);
                    stringBuilder.append('.');
                    stringBuilder.append(iArr[1]);
                    Log.v(access$700, stringBuilder.toString());
                    int[] iArr2 = new int[]{SnapshotEffectRender.EGL_CONTEXT_CLIENT_VERSION, 2, 12344};
                    SnapshotEffectRender.this.mEglConfig = SnapshotEffectRender.chooseConfig(SnapshotEffectRender.this.mEgl, SnapshotEffectRender.this.mEglDisplay);
                    SnapshotEffectRender.this.mEglContext = SnapshotEffectRender.this.mEgl.eglCreateContext(SnapshotEffectRender.this.mEglDisplay, SnapshotEffectRender.this.mEglConfig, EGL10.EGL_NO_CONTEXT, iArr2);
                    if (SnapshotEffectRender.this.mEglContext == null || SnapshotEffectRender.this.mEglContext == EGL10.EGL_NO_CONTEXT) {
                        throw new RuntimeException("failed to createContext");
                    }
                    SnapshotEffectRender.this.mEglSurface = SnapshotEffectRender.this.mEgl.eglCreatePbufferSurface(SnapshotEffectRender.this.mEglDisplay, SnapshotEffectRender.this.mEglConfig, new int[]{12375, Util.sWindowWidth, 12374, Util.sWindowHeight, 12344});
                    if (SnapshotEffectRender.this.mEglSurface == null || SnapshotEffectRender.this.mEglSurface == EGL10.EGL_NO_SURFACE) {
                        throw new RuntimeException("failed to createWindowSurface");
                    } else if (!SnapshotEffectRender.this.mEgl.eglMakeCurrent(SnapshotEffectRender.this.mEglDisplay, SnapshotEffectRender.this.mEglSurface, SnapshotEffectRender.this.mEglSurface, SnapshotEffectRender.this.mEglContext)) {
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
        }

        private void drawWaterMark(DrawJPEGAttribute drawJPEGAttribute, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            DrawJPEGAttribute drawJPEGAttribute2 = drawJPEGAttribute;
            int i8 = i;
            int i9 = i2;
            int i10 = i3;
            int i11 = i4;
            int i12 = i7;
            if (b.hj()) {
                if (drawJPEGAttribute2.mTimeWaterMarkText != null) {
                    WaterMark newStyleTextWaterMark;
                    String str = drawJPEGAttribute2.mTimeWaterMarkText;
                    if (b.gr()) {
                        newStyleTextWaterMark = new NewStyleTextWaterMark(str, i10, i11, i12);
                    } else {
                        newStyleTextWaterMark = new TextWaterMark(str, i10, i11, i12);
                    }
                    drawWaterMark(newStyleTextWaterMark, i8, i9, i12);
                }
                if (drawJPEGAttribute2.mDualCameraWaterMarkEnabled && SnapshotEffectRender.this.mDualCameraWaterMarkBitmap != null) {
                    if (!SnapshotEffectRender.this.mCurrentCustomWaterMarkText.equals(CameraSettings.getCustomWatermark())) {
                        SnapshotEffectRender.this.mCurrentCustomWaterMarkText = CameraSettings.getCustomWatermark();
                        SnapshotEffectRender.this.mDualCameraWaterMarkBitmap = SnapshotEffectRender.this.loadCameraWatermark(SnapshotEffectRender.this.mActivity);
                    }
                    Bitmap access$1200 = SnapshotEffectRender.this.mDualCameraWaterMarkBitmap;
                    boolean equals = CameraSettings.getCustomWatermark().equals(CameraSettings.getDefaultWatermarkStr());
                    Object obj = (CameraSettings.isUltraPixelPhotographyOn() || CameraSettings.isRearMenuUltraPixelPhotographyOn()) ? 1 : null;
                    if (!(obj == null || DataRepository.dataItemFeature().fQ() || !equals)) {
                        if (SnapshotEffectRender.this.m48MCameraWaterMarkBitmap == null) {
                            SnapshotEffectRender.this.m48MCameraWaterMarkBitmap = SnapshotEffectRender.this.load48MWatermark(SnapshotEffectRender.this.mActivity);
                        }
                        if (SnapshotEffectRender.this.m48MCameraWaterMarkBitmap != null) {
                            access$1200 = SnapshotEffectRender.this.m48MCameraWaterMarkBitmap;
                        }
                    }
                    drawWaterMark(new ImageWaterMark(access$1200, i10, i11, i12, SnapshotEffectRender.this.mDualCameraWaterMarkSizeRatio, SnapshotEffectRender.this.mDualCameraWaterMarkPaddingXRatio, SnapshotEffectRender.this.mDualCameraWaterMarkPaddingYRatio), i8, i9, i12);
                }
            }
        }

        /* JADX WARNING: Missing block: B:10:0x0049, code skipped:
            return;
     */
        private void drawAgeGenderAndMagicMirrorWater(java.util.List<com.android.camera.watermark.WaterMarkData> r13, int r14, int r15, int r16, int r17, int r18, boolean r19) {
            /*
            r12 = this;
            r0 = com.mi.config.b.hj();
            if (r0 == 0) goto L_0x0049;
        L_0x0006:
            if (r19 == 0) goto L_0x0009;
        L_0x0008:
            goto L_0x0049;
        L_0x0009:
            r0 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
            if (r0 == 0) goto L_0x0048;
        L_0x000f:
            r0 = new com.android.camera.watermark.WaterMarkBitmap;
            r1 = r13;
            r0.<init>(r1);
            r1 = r0.getWaterMarkData();
            if (r1 == 0) goto L_0x003a;
        L_0x001b:
            r11 = new com.android.camera.effect.renders.AgeGenderAndMagicMirrorWaterMark;
            r3 = r1.getImage();
            r9 = 0;
            r10 = 0;
            r2 = r11;
            r4 = r14;
            r5 = r15;
            r6 = r18;
            r7 = r16;
            r8 = r17;
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10);
            r1 = r1.getOrientation();
            r1 = r18 - r1;
            r2 = 0;
            r3 = r12;
            r3.drawWaterMark(r11, r2, r2, r1);
        L_0x003a:
            r0.releaseBitmap();
            r0 = com.android.camera.watermark.WaterMarkBitmap.class;
            r0 = r0.getSimpleName();
            r1 = "Draw age_gender_and_magic_mirror water mark";
            com.android.camera.log.Log.d(r0, r1);
        L_0x0048:
            return;
        L_0x0049:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.camera.effect.renders.SnapshotEffectRender$EGLHandler.drawAgeGenderAndMagicMirrorWater(java.util.List, int, int, int, int, int, boolean):void");
        }

        private byte[] applyEffectOnlyWatermarkRange(DrawJPEGAttribute drawJPEGAttribute, Size size, Size size2) {
            DrawJPEGAttribute drawJPEGAttribute2 = drawJPEGAttribute;
            Size size3 = size2;
            if (!drawJPEGAttribute2.mApplyWaterMark) {
                return drawJPEGAttribute2.mData;
            }
            long currentTimeMillis = System.currentTimeMillis();
            byte[] decompressPicture = ShaderNativeUtil.decompressPicture(drawJPEGAttribute2.mData, 1);
            String access$700 = SnapshotEffectRender.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("jpeg decompress total time =");
            stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
            Log.d(access$700, stringBuilder.toString());
            int[] watermarkRange = Util.getWatermarkRange(drawJPEGAttribute2.mWidth, drawJPEGAttribute2.mHeight, (drawJPEGAttribute2.mJpegOrientation + 270) % 360, drawJPEGAttribute2.mDualCameraWaterMarkEnabled, drawJPEGAttribute2.mTimeWaterMarkText != null, 0.1f);
            int i = watermarkRange[2];
            int i2 = watermarkRange[3];
            int i3 = drawJPEGAttribute2.mPreviewWidth;
            int i4 = drawJPEGAttribute2.mPreviewHeight;
            int i5 = drawJPEGAttribute2.mWidth;
            int i6 = drawJPEGAttribute2.mHeight;
            int[] iArr = new int[1];
            long currentTimeMillis2 = System.currentTimeMillis();
            iArr[0] = OpenGLUtils.loadRGBTexture(ByteBuffer.wrap(Util.getPixels(decompressPicture, drawJPEGAttribute2.mWidth, 3, watermarkRange)), i, i2);
            String access$7002 = SnapshotEffectRender.TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("get pixel and upload total time =");
            stringBuilder2.append(System.currentTimeMillis() - currentTimeMillis2);
            Log.d(access$7002, stringBuilder2.toString());
            Render effectRender = getEffectRender(drawJPEGAttribute2.mEffectIndex);
            if (effectRender == null) {
                Log.w(SnapshotEffectRender.TAG, "init render failed");
                return drawJPEGAttribute2.mData;
            }
            if (effectRender instanceof PipeRender) {
                ((PipeRender) effectRender).setFrameBufferSize(i, i2);
            }
            effectRender.setPreviewSize(i3, i4);
            effectRender.setEffectRangeAttribute(drawJPEGAttribute2.mAttribute);
            effectRender.setMirror(drawJPEGAttribute2.mMirror);
            effectRender.setSnapshotSize(size3.width, size3.height);
            effectRender.setOrientation(drawJPEGAttribute2.mOrientation);
            effectRender.setShootRotation(drawJPEGAttribute2.mShootRotation);
            effectRender.setJpegOrientation(drawJPEGAttribute2.mJpegOrientation);
            checkFrameBuffer(i, i2);
            this.mGLCanvas.beginBindFrameBuffer(this.mFrameBuffer);
            long currentTimeMillis3 = System.currentTimeMillis();
            effectRender.setParentFrameBufferId(this.mFrameBuffer.getId());
            int[] iArr2 = iArr;
            int i7 = i6;
            effectRender.draw(new DrawIntTexAttribute(iArr[0], 0, 0, i, i2, true));
            effectRender.deleteBuffer();
            i6 = i5;
            int i8 = i2;
            i2 = i7;
            int i9 = i7;
            i7 = i;
            int i10 = 0;
            drawWaterMark(drawJPEGAttribute2, -watermarkRange[0], -watermarkRange[1], i6, i2, i3, i4, drawJPEGAttribute2.mJpegOrientation);
            String access$7003 = SnapshotEffectRender.TAG;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("drawTime=");
            stringBuilder3.append(System.currentTimeMillis() - currentTimeMillis3);
            Log.d(access$7003, stringBuilder3.toString());
            long currentTimeMillis4 = System.currentTimeMillis();
            GLES20.glPixelStorei(3333, 1);
            Buffer allocate = ByteBuffer.allocate((i7 * i8) * 4);
            i3 = i8;
            i4 = i6;
            GLES20.glReadPixels(0, 0, i7, i3, 6408, 5121, allocate);
            byte[] bArr = new byte[allocate.remaining()];
            allocate.get(bArr, i10, bArr.length);
            String access$7004 = SnapshotEffectRender.TAG;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("glReadPixels=");
            stringBuilder2.append(System.currentTimeMillis() - currentTimeMillis4);
            Log.d(access$7004, stringBuilder2.toString());
            currentTimeMillis4 = System.currentTimeMillis();
            Util.setPixels(decompressPicture, i4, 3, Util.RGBA2RGB(bArr, i7, i3), watermarkRange);
            access$7004 = SnapshotEffectRender.TAG;
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("RGBA2RGB=");
            stringBuilder4.append(System.currentTimeMillis() - currentTimeMillis4);
            Log.d(access$7004, stringBuilder4.toString());
            currentTimeMillis4 = System.currentTimeMillis();
            i3 = i10;
            byte[] compressPicture = ShaderNativeUtil.compressPicture(decompressPicture, i4, i9, SnapshotEffectRender.this.mQuality);
            String access$7005 = SnapshotEffectRender.TAG;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("compress=");
            stringBuilder2.append(System.currentTimeMillis() - currentTimeMillis4);
            Log.d(access$7005, stringBuilder2.toString());
            int[] iArr3 = iArr2;
            if (GLES20.glIsTexture(iArr3[i3])) {
                GLES20.glDeleteTextures(1, iArr3, i3);
            }
            this.mGLCanvas.endBindFrameBuffer();
            return compressPicture;
        }

        /* JADX WARNING: Removed duplicated region for block: B:62:0x01b9  */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x018e  */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x01d7  */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x01c1  */
        /* JADX WARNING: Removed duplicated region for block: B:69:0x020a  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x0248  */
        private byte[] applyEffect(com.android.camera.effect.draw_mode.DrawJPEGAttribute r27, int r28, boolean r29, com.android.camera.effect.renders.SnapshotEffectRender.Size r30, com.android.camera.effect.renders.SnapshotEffectRender.Size r31) {
            /*
            r26 = this;
            r9 = r26;
            r8 = r27;
            r10 = r29;
            r11 = r30;
            r0 = r31;
            r1 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            r3 = "applyEffect: applyToThumb = ";
            r2.append(r3);
            r2.append(r10);
            r2 = r2.toString();
            com.android.camera.log.Log.d(r1, r2);
            if (r10 == 0) goto L_0x002b;
        L_0x0024:
            r1 = r8.mExif;
            r1 = r1.getThumbnailBytes();
            goto L_0x002d;
        L_0x002b:
            r1 = r8.mData;
        L_0x002d:
            if (r1 != 0) goto L_0x0050;
        L_0x002f:
            r0 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Null ";
            r1.append(r2);
            if (r10 == 0) goto L_0x0042;
        L_0x003f:
            r2 = "thumb!";
            goto L_0x0044;
        L_0x0042:
            r2 = "jpeg!";
        L_0x0044:
            r1.append(r2);
            r1 = r1.toString();
            com.android.camera.log.Log.w(r0, r1);
            r0 = 0;
            return r0;
        L_0x0050:
            if (r10 != 0) goto L_0x0075;
        L_0x0052:
            r2 = r8.mEffectIndex;
            r3 = com.android.camera.effect.FilterInfo.FILTER_ID_NONE;
            if (r2 != r3) goto L_0x0075;
        L_0x0058:
            r2 = com.android.camera.CameraSettings.isAgeGenderAndMagicMirrorWaterOpen();
            if (r2 != 0) goto L_0x0075;
        L_0x005e:
            r2 = com.android.camera.module.ModuleManager.isSquareModule();
            if (r2 != 0) goto L_0x0075;
        L_0x0064:
            r2 = com.android.camera.CameraSettings.isGradienterOn();
            if (r2 != 0) goto L_0x0075;
        L_0x006a:
            r2 = com.android.camera.CameraSettings.isTiltShiftOn();
            if (r2 != 0) goto L_0x0075;
        L_0x0070:
            r0 = r9.applyEffectOnlyWatermarkRange(r8, r11, r0);
            return r0;
        L_0x0075:
            r2 = java.lang.System.currentTimeMillis();
            r12 = 1;
            r13 = new int[r12];
            r14 = 0;
            android.opengl.GLES20.glGenTextures(r12, r13, r14);
            r4 = r13[r14];
            r5 = r28;
            r4 = com.android.camera.effect.ShaderNativeUtil.initTexture(r1, r4, r5);
            android.opengl.GLES20.glFlush();
            r5 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r6 = new java.lang.StringBuilder;
            r6.<init>();
            r7 = "initTime=";
            r6.append(r7);
            r15 = java.lang.System.currentTimeMillis();
            r2 = r15 - r2;
            r6.append(r2);
            r2 = r6.toString();
            com.android.camera.log.Log.d(r5, r2);
            if (r10 == 0) goto L_0x00af;
        L_0x00ab:
            r2 = r4[r14];
        L_0x00ad:
            r7 = r2;
            goto L_0x00b2;
        L_0x00af:
            r2 = r8.mWidth;
            goto L_0x00ad;
        L_0x00b2:
            if (r10 == 0) goto L_0x00b8;
        L_0x00b4:
            r2 = r4[r12];
        L_0x00b6:
            r6 = r2;
            goto L_0x00bb;
        L_0x00b8:
            r2 = r8.mHeight;
            goto L_0x00b6;
        L_0x00bb:
            if (r10 == 0) goto L_0x00c1;
        L_0x00bd:
            r2 = r4[r14];
        L_0x00bf:
            r5 = r2;
            goto L_0x00c4;
        L_0x00c1:
            r2 = r8.mPreviewWidth;
            goto L_0x00bf;
        L_0x00c4:
            if (r10 == 0) goto L_0x00ca;
        L_0x00c6:
            r2 = r4[r12];
        L_0x00c8:
            r4 = r2;
            goto L_0x00cd;
        L_0x00ca:
            r2 = r8.mPreviewHeight;
            goto L_0x00c8;
        L_0x00cd:
            r2 = r8.mEffectIndex;
            r2 = r9.getEffectRender(r2);
            if (r2 != 0) goto L_0x00df;
        L_0x00d5:
            r0 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r2 = "init render failed";
            com.android.camera.log.Log.w(r0, r2);
            return r1;
        L_0x00df:
            r1 = r2 instanceof com.android.camera.effect.renders.PipeRender;
            if (r1 == 0) goto L_0x00e9;
        L_0x00e3:
            r1 = r2;
            r1 = (com.android.camera.effect.renders.PipeRender) r1;
            r1.setFrameBufferSize(r7, r6);
        L_0x00e9:
            r2.setPreviewSize(r5, r4);
            r1 = r8.mAttribute;
            r2.setEffectRangeAttribute(r1);
            r1 = r8.mMirror;
            r2.setMirror(r1);
            if (r10 == 0) goto L_0x00fc;
        L_0x00f8:
            r2.setSnapshotSize(r7, r6);
            goto L_0x0103;
        L_0x00fc:
            r1 = r0.width;
            r0 = r0.height;
            r2.setSnapshotSize(r1, r0);
        L_0x0103:
            r0 = r8.mOrientation;
            r2.setOrientation(r0);
            r0 = r8.mShootRotation;
            r2.setShootRotation(r0);
            r0 = r8.mJpegOrientation;
            r2.setJpegOrientation(r0);
            r9.checkFrameBuffer(r7, r6);
            r0 = r9.mGLCanvas;
            r1 = r9.mFrameBuffer;
            r0.beginBindFrameBuffer(r1);
            r22 = java.lang.System.currentTimeMillis();
            r0 = r9.mFrameBuffer;
            r0 = r0.getId();
            r2.setParentFrameBufferId(r0);
            r0 = new com.android.camera.effect.draw_mode.DrawIntTexAttribute;
            r16 = r13[r14];
            r17 = 0;
            r18 = 0;
            r21 = 1;
            r15 = r0;
            r19 = r7;
            r20 = r6;
            r15.<init>(r16, r17, r18, r19, r20, r21);
            r2.draw(r0);
            r2.deleteBuffer();
            r1 = r8.mWaterInfos;
            r15 = r8.mJpegOrientation;
            r3 = r8.mIsPortraitRawData;
            r0 = r9;
            r2 = r7;
            r16 = r3;
            r3 = r6;
            r17 = r4;
            r4 = r5;
            r18 = r5;
            r5 = r17;
            r14 = r6;
            r6 = r15;
            r15 = r7;
            r7 = r16;
            r0.drawAgeGenderAndMagicMirrorWater(r1, r2, r3, r4, r5, r6, r7);
            r0 = r8.mRequestModuleIdx;
            r1 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
            if (r0 != r1) goto L_0x018a;
        L_0x0163:
            if (r15 <= r14) goto L_0x0178;
        L_0x0165:
            r7 = r15 - r14;
            r7 = r7 / 2;
            r0 = com.android.camera.effect.renders.SnapshotEffectRender.this;
            r0 = r0.mSquareModeExtraMargin;
            r0 = r0 * r14;
            r1 = com.android.camera.Util.sWindowWidth;
            r0 = r0 / r1;
            r7 = r7 - r0;
            r15 = r14;
            r6 = 0;
            goto L_0x018c;
        L_0x0178:
            r6 = r14 - r15;
            r6 = r6 / 2;
            r0 = com.android.camera.effect.renders.SnapshotEffectRender.this;
            r0 = r0.mSquareModeExtraMargin;
            r0 = r0 * r15;
            r1 = com.android.camera.Util.sWindowWidth;
            r0 = r0 / r1;
            r6 = r6 - r0;
            r14 = r15;
            goto L_0x018b;
        L_0x018a:
            r6 = 0;
        L_0x018b:
            r7 = 0;
        L_0x018c:
            if (r10 == 0) goto L_0x01b9;
        L_0x018e:
            if (r11 == 0) goto L_0x01bd;
        L_0x0190:
            r11.width = r15;
            r11.height = r14;
            r0 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "thumbSize=";
            r1.append(r2);
            r2 = r11.width;
            r1.append(r2);
            r2 = "*";
            r1.append(r2);
            r2 = r11.height;
            r1.append(r2);
            r1 = r1.toString();
            com.android.camera.log.Log.d(r0, r1);
            goto L_0x01bd;
        L_0x01b9:
            r8.mWidth = r15;
            r8.mHeight = r14;
        L_0x01bd:
            r0 = r8.mApplyWaterMark;
            if (r0 == 0) goto L_0x01d7;
        L_0x01c1:
            r11 = r8.mJpegOrientation;
            r0 = r9;
            r1 = r8;
            r2 = r7;
            r3 = r6;
            r4 = r15;
            r5 = r14;
            r8 = r6;
            r6 = r18;
            r24 = r7;
            r7 = r17;
            r25 = r8;
            r8 = r11;
            r0.drawWaterMark(r1, r2, r3, r4, r5, r6, r7, r8);
            goto L_0x01db;
        L_0x01d7:
            r25 = r6;
            r24 = r7;
        L_0x01db:
            r0 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "drawTime=";
            r1.append(r2);
            r2 = java.lang.System.currentTimeMillis();
            r2 = r2 - r22;
            r1.append(r2);
            r1 = r1.toString();
            com.android.camera.log.Log.d(r0, r1);
            r0 = 3333; // 0xd05 float:4.67E-42 double:1.6467E-320;
            android.opengl.GLES20.glPixelStorei(r0, r12);
            r0 = java.lang.System.currentTimeMillis();
            r2 = com.android.camera.effect.renders.SnapshotEffectRender.this;
            r2 = r2.mQuality;
            if (r10 == 0) goto L_0x021a;
        L_0x020a:
            r2 = com.android.camera.effect.renders.SnapshotEffectRender.this;
            r2 = r2.mQuality;
            r3 = "normal";
            r3 = com.android.camera.JpegEncodingQualityMappings.getQualityNumber(r3);
            r2 = java.lang.Math.min(r2, r3);
        L_0x021a:
            r7 = r24;
            r6 = r25;
            r2 = com.android.camera.effect.ShaderNativeUtil.getPicture(r7, r6, r15, r14, r2);
            r3 = com.android.camera.effect.renders.SnapshotEffectRender.TAG;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "readTime=";
            r4.append(r5);
            r5 = java.lang.System.currentTimeMillis();
            r5 = r5 - r0;
            r4.append(r5);
            r0 = r4.toString();
            com.android.camera.log.Log.d(r3, r0);
            r0 = 0;
            r1 = r13[r0];
            r1 = android.opengl.GLES20.glIsTexture(r1);
            if (r1 == 0) goto L_0x024b;
        L_0x0248:
            android.opengl.GLES20.glDeleteTextures(r12, r13, r0);
        L_0x024b:
            r0 = r9.mGLCanvas;
            r0.endBindFrameBuffer();
            return r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.camera.effect.renders.SnapshotEffectRender$EGLHandler.applyEffect(com.android.camera.effect.draw_mode.DrawJPEGAttribute, int, boolean, com.android.camera.effect.renders.SnapshotEffectRender$Size, com.android.camera.effect.renders.SnapshotEffectRender$Size):byte[]");
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

        private Render getEffectRender(int i) {
            Render fetchRender;
            PipeRender pipeRender = new PipeRender(this.mGLCanvas);
            if (i != FilterInfo.FILTER_ID_NONE) {
                fetchRender = fetchRender(i);
                if (fetchRender != null) {
                    pipeRender.addRender(fetchRender);
                }
            }
            if (CameraSettings.isGradienterOn()) {
                fetchRender = fetchRender(FilterInfo.FILTER_ID_GRADIENTER);
                if (fetchRender != null) {
                    pipeRender.addRender(fetchRender);
                }
            } else if (CameraSettings.isTiltShiftOn()) {
                fetchRender = null;
                String componentValue = DataRepository.dataItemRunning().getComponentRunningTiltValue().getComponentValue(160);
                if (ComponentRunningTiltValue.TILT_CIRCLE.equals(componentValue)) {
                    fetchRender = fetchRender(FilterInfo.FILTER_ID_GAUSSIAN);
                } else if (ComponentRunningTiltValue.TILT_PARALLEL.equals(componentValue)) {
                    fetchRender = fetchRender(FilterInfo.FILTER_ID_TILTSHIFT);
                }
                if (fetchRender != null) {
                    pipeRender.addRender(fetchRender);
                }
            }
            if (pipeRender.getRenderSize() == 0) {
                pipeRender.addRender(fetchRender(i));
            }
            return pipeRender;
        }

        private boolean drawMainJpeg(DrawJPEGAttribute drawJPEGAttribute, boolean z, boolean z2) {
            DrawJPEGAttribute drawJPEGAttribute2 = drawJPEGAttribute;
            Size size = new Size(drawJPEGAttribute2.mWidth, drawJPEGAttribute2.mHeight);
            int i = 1;
            while (true) {
                if (drawJPEGAttribute2.mWidth <= BaseGLCanvas.sMaxTextureSize && drawJPEGAttribute2.mHeight <= BaseGLCanvas.sMaxTextureSize) {
                    break;
                }
                drawJPEGAttribute2.mWidth /= 2;
                drawJPEGAttribute2.mHeight /= 2;
                i *= 2;
            }
            String access$700 = SnapshotEffectRender.TAG;
            r9 = new Object[4];
            boolean z3 = false;
            r9[0] = Integer.valueOf(i);
            r9[1] = Integer.valueOf(drawJPEGAttribute2.mWidth);
            r9[2] = Integer.valueOf(drawJPEGAttribute2.mPreviewWidth);
            r9[3] = Boolean.valueOf(z2);
            Log.d(access$700, String.format(Locale.US, "downScale: %d width: %d %d parallel: %b", r9));
            byte[] applyEffect = applyEffect(drawJPEGAttribute2, i, false, null, size);
            String access$7002 = SnapshotEffectRender.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mainLen=");
            stringBuilder.append(applyEffect == null ? TEDefine.FACE_BEAUTY_NULL : Integer.valueOf(applyEffect.length));
            Log.d(access$7002, stringBuilder.toString());
            if (applyEffect != null) {
                drawJPEGAttribute2.mData = applyEffect;
            }
            if (z) {
                synchronized (SnapshotEffectRender.this) {
                    access$7002 = (String) SnapshotEffectRender.this.mTitleMap.get(drawJPEGAttribute2.mTitle);
                    SnapshotEffectRender.this.mTitleMap.remove(drawJPEGAttribute2.mTitle);
                }
                String str = null;
                ActivityBase access$000;
                if (SnapshotEffectRender.this.mImageSaver != null) {
                    String str2;
                    ImageSaver access$2300 = SnapshotEffectRender.this.mImageSaver;
                    byte[] bArr = drawJPEGAttribute2.mData;
                    boolean z4 = drawJPEGAttribute2.mNeedThumbnail;
                    if (access$7002 == null) {
                        str2 = drawJPEGAttribute2.mTitle;
                    } else {
                        str2 = access$7002;
                    }
                    if (access$7002 != null) {
                        str = drawJPEGAttribute2.mTitle;
                    }
                    String str3 = str;
                    long j = drawJPEGAttribute2.mDate;
                    Uri uri = drawJPEGAttribute2.mUri;
                    Location location = drawJPEGAttribute2.mLoc;
                    int i2 = drawJPEGAttribute2.mWidth;
                    int i3 = drawJPEGAttribute2.mHeight;
                    ExifInterface exifInterface = drawJPEGAttribute2.mExif;
                    int i4 = drawJPEGAttribute2.mJpegOrientation;
                    if (access$7002 == null) {
                        z3 = drawJPEGAttribute2.mFinalImage;
                    }
                    access$2300.addImage(bArr, z4, str2, str3, j, uri, location, i2, i3, exifInterface, i4, false, false, z3, false, z2, drawJPEGAttribute2.mAlgorithmName, drawJPEGAttribute2.mInfo, drawJPEGAttribute2.mPreviewThumbnailHash);
                } else if (drawJPEGAttribute2.mUri == null) {
                    Log.d(SnapshotEffectRender.TAG, "addImageForEffect");
                    access$000 = SnapshotEffectRender.this.mActivity;
                    if (access$7002 == null) {
                        access$7002 = drawJPEGAttribute2.mTitle;
                    }
                    Storage.addImageForEffect(access$000, access$7002, drawJPEGAttribute2.mDate, drawJPEGAttribute2.mLoc, drawJPEGAttribute2.mJpegOrientation, drawJPEGAttribute2.mData, drawJPEGAttribute2.mWidth, drawJPEGAttribute2.mHeight, false, z2, drawJPEGAttribute2.mAlgorithmName, drawJPEGAttribute2.mInfo);
                } else {
                    access$700 = SnapshotEffectRender.TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("updateImage: uri=");
                    stringBuilder2.append(drawJPEGAttribute2.mUri);
                    Log.d(access$700, stringBuilder2.toString());
                    access$000 = SnapshotEffectRender.this.mActivity;
                    byte[] bArr2 = drawJPEGAttribute2.mData;
                    ExifInterface exifInterface2 = drawJPEGAttribute2.mExif;
                    Uri uri2 = drawJPEGAttribute2.mUri;
                    String str4 = access$7002 == null ? drawJPEGAttribute2.mTitle : access$7002;
                    Location location2 = drawJPEGAttribute2.mLoc;
                    int i5 = drawJPEGAttribute2.mJpegOrientation;
                    int i6 = drawJPEGAttribute2.mWidth;
                    int i7 = drawJPEGAttribute2.mHeight;
                    if (access$7002 != null) {
                        str = drawJPEGAttribute2.mTitle;
                    }
                    Storage.updateImage(access$000, bArr2, exifInterface2, uri2, str4, location2, i5, i6, i7, str);
                }
            } else if (drawJPEGAttribute2.mExif != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    drawJPEGAttribute2.mExif.writeExif(drawJPEGAttribute2.mData, byteArrayOutputStream);
                    applyEffect = byteArrayOutputStream.toByteArray();
                    if (applyEffect != null) {
                        drawJPEGAttribute2.mData = applyEffect;
                    }
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    Log.e(SnapshotEffectRender.TAG, e.getMessage(), e);
                }
            }
            return true;
        }

        private boolean drawThumbJpeg(DrawJPEGAttribute drawJPEGAttribute, boolean z) {
            DrawJPEGAttribute drawJPEGAttribute2 = drawJPEGAttribute;
            if (drawJPEGAttribute2.mExif == null) {
                drawJPEGAttribute2.mExif = SnapshotEffectRender.this.getExif(drawJPEGAttribute2);
                if (!TextUtils.isEmpty(drawJPEGAttribute2.mAlgorithmName)) {
                    drawJPEGAttribute2.mExif.addAlgorithmComment(drawJPEGAttribute2.mAlgorithmName);
                }
            }
            Size size = new Size();
            byte[] applyEffect = applyEffect(drawJPEGAttribute2, 1, true, size, null);
            String access$700 = SnapshotEffectRender.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("drawThumbJpeg: thumbLen=");
            stringBuilder.append(applyEffect == null ? TEDefine.FACE_BEAUTY_NULL : Integer.valueOf(applyEffect.length));
            Log.d(access$700, stringBuilder.toString());
            if (applyEffect != null) {
                drawJPEGAttribute2.mExif.setCompressedThumbnail(applyEffect);
            }
            boolean z2 = drawJPEGAttribute2.mJpegOrientation != 0;
            if (z && drawJPEGAttribute2.mExif.getThumbnailBytes() != null) {
                drawJPEGAttribute2.mUri = Storage.addImage(SnapshotEffectRender.this.mActivity, drawJPEGAttribute2.mTitle, drawJPEGAttribute2.mDate, drawJPEGAttribute2.mLoc, drawJPEGAttribute2.mJpegOrientation, drawJPEGAttribute2.mExif.getThumbnailBytes(), size.width, size.height, false, false, false, z2, false, drawJPEGAttribute2.mAlgorithmName, null);
                if (drawJPEGAttribute2.mUri != null) {
                    SnapshotEffectRender.this.mActivity.onNewUriArrived(drawJPEGAttribute2.mUri, drawJPEGAttribute2.mTitle);
                }
            }
            return true;
        }

        private void checkFrameBuffer(int i, int i2) {
            if (this.mFrameBuffer == null || this.mFrameBuffer.getWidth() < i || this.mFrameBuffer.getHeight() < i2) {
                this.mFrameBuffer = null;
                this.mFrameBuffer = new FrameBuffer(this.mGLCanvas, i, i2, 0);
            }
        }

        private void release() {
            this.mFrameBuffer = null;
            this.mGLCanvas.recycledResources();
            this.mGLCanvas = null;
            SnapshotEffectRender.this.destroy();
        }

        public void sendMessageSync(int i) {
            SnapshotEffectRender.this.mEglThreadBlockVar.close();
            sendEmptyMessage(i);
            SnapshotEffectRender.this.mEglThreadBlockVar.block();
        }
    }

    private class Size {
        public int height;
        public int width;

        Size(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public SnapshotEffectRender(ActivityBase activityBase, boolean z) {
        Log.d(TAG, "SnapshotEffectRender: has been created!!!");
        this.mActivity = activityBase;
        this.mIsImageCaptureIntent = z;
        this.mEglThread = new HandlerThread("SnapshotEffectProcessor");
        this.mEglThread.start();
        this.mEglHandler = new EGLHandler(this.mEglThread.getLooper());
        this.mEglHandler.sendMessageSync(0);
        this.mRelease = false;
        if (CameraSettings.isSupportedDualCameraWaterMark()) {
            this.mDualCameraWaterMarkBitmap = loadCameraWatermark(activityBase);
            this.mCurrentCustomWaterMarkText = CameraSettings.getCustomWatermark();
            this.mDualCameraWaterMarkSizeRatio = getResourceFloat(2131361965, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            this.mDualCameraWaterMarkPaddingXRatio = getResourceFloat(2131361966, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            this.mDualCameraWaterMarkPaddingYRatio = getResourceFloat(2131361967, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        }
        this.mSquareModeExtraMargin = this.mActivity.getResources().getDimensionPixelSize(2131361987);
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

    public float getResourceFloat(int i, float f) {
        TypedValue typedValue = new TypedValue();
        try {
            this.mActivity.getResources().getValue(i, typedValue, true);
            return typedValue.getFloat();
        } catch (Exception e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Missing resource ");
            stringBuilder.append(Integer.toHexString(i));
            Log.e(str, stringBuilder.toString());
            return f;
        }
    }

    public void setImageSaver(ImageSaver imageSaver) {
        this.mImageSaver = imageSaver;
    }

    public void setQuality(int i) {
        if (i >= 0 && i <= 97) {
            this.mQuality = i;
        }
    }

    public void setExifNeed(boolean z) {
        this.mExifNeeded = z;
    }

    public boolean processorJpegAsync(DrawJPEGAttribute drawJPEGAttribute, boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("queueSize=");
        stringBuilder.append(this.mJpegQueueSize);
        Log.d(str, stringBuilder.toString());
        if (z || this.mJpegQueueSize < 7) {
            if (!z) {
                boolean z2 = this.mJpegQueueSize == 0;
                boolean processorThumSync;
                if (z2) {
                    processorThumSync = processorThumSync(drawJPEGAttribute);
                } else {
                    processorThumAsync(drawJPEGAttribute);
                    processorThumSync = false;
                }
                if (!this.mIsImageCaptureIntent && z2 && this.mExifNeeded && processorThumSync) {
                    if (drawJPEGAttribute.mNeedThumbnail) {
                        Bitmap thumbnailBitmap = drawJPEGAttribute.mExif.getThumbnailBitmap();
                        if (!(thumbnailBitmap == null || drawJPEGAttribute.mUri == null)) {
                            drawJPEGAttribute.mFinalImage = false;
                            this.mActivity.getThumbnailUpdater().setThumbnail(Thumbnail.createThumbnail(drawJPEGAttribute.mUri, thumbnailBitmap, drawJPEGAttribute.mJpegOrientation, false), true, true);
                        }
                    } else {
                        this.mActivity.getThumbnailUpdater().updatePreviewThumbnailUri(drawJPEGAttribute.mUri);
                    }
                }
            }
            synchronized (this.mLock) {
                this.mJpegQueueSize++;
            }
            this.mEglHandler.obtainMessage(1, z, 0, drawJPEGAttribute).sendToTarget();
            return true;
        }
        String str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("queueSize is full, drop it ");
        stringBuilder.append(drawJPEGAttribute.mTitle);
        Log.d(str2, stringBuilder.toString());
        return false;
    }

    public void processorJpegSync(DrawJPEGAttribute drawJPEGAttribute, boolean z) {
        this.mEglThreadBlockVar.close();
        this.mEglHandler.obtainMessage(2, this.mExifNeeded, z, drawJPEGAttribute).sendToTarget();
        this.mEglThreadBlockVar.block();
    }

    public void changeJpegTitle(String str, String str2) {
        if (str2 != null && str2.length() != 0) {
            synchronized (this) {
                this.mTitleMap.put(str2, str);
            }
        }
    }

    private void processorThumAsync(DrawJPEGAttribute drawJPEGAttribute) {
        if (this.mExifNeeded) {
            this.mEglHandler.obtainMessage(3, drawJPEGAttribute).sendToTarget();
        } else {
            drawJPEGAttribute.mUri = Storage.newImage(this.mActivity, drawJPEGAttribute.mTitle, drawJPEGAttribute.mDate, drawJPEGAttribute.mJpegOrientation, drawJPEGAttribute.mPreviewWidth, drawJPEGAttribute.mPreviewHeight);
        }
    }

    private boolean processorThumSync(DrawJPEGAttribute drawJPEGAttribute) {
        if (this.mExifNeeded) {
            drawJPEGAttribute.mExif = getExif(drawJPEGAttribute);
            if (!TextUtils.isEmpty(drawJPEGAttribute.mAlgorithmName)) {
                drawJPEGAttribute.mExif.addAlgorithmComment(drawJPEGAttribute.mAlgorithmName);
            }
            if (drawJPEGAttribute.mExif.getThumbnailBytes() != null) {
                this.mEglThreadBlockVar.close();
                this.mEglHandler.obtainMessage(4, drawJPEGAttribute).sendToTarget();
                this.mEglThreadBlockVar.block();
                return true;
            }
        }
        drawJPEGAttribute.mUri = Storage.newImage(this.mActivity, drawJPEGAttribute.mTitle, drawJPEGAttribute.mDate, drawJPEGAttribute.mJpegOrientation, drawJPEGAttribute.mPreviewWidth, drawJPEGAttribute.mPreviewHeight);
        return false;
    }

    private ExifInterface getExif(DrawJPEGAttribute drawJPEGAttribute) {
        ExifInterface exifInterface = new ExifInterface();
        try {
            exifInterface.readExif(drawJPEGAttribute.mData);
            if (drawJPEGAttribute.mInfo != null) {
                exifInterface.addXiaomiComment(drawJPEGAttribute.mInfo.toString());
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return exifInterface;
    }

    public boolean isRelease() {
        return this.mReleasePending || this.mRelease;
    }

    public void releaseIfNeeded() {
        if (this.mEglHandler.hasMessages(1)) {
            this.mReleasePending = true;
        } else {
            this.mEglHandler.sendEmptyMessage(5);
        }
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

    public void prepareEffectRender(int i) {
        this.mEglHandler.obtainMessage(6, i, 0).sendToTarget();
    }

    private void destroy() {
        this.mImageSaver = null;
        this.mRelease = true;
        this.mReleasePending = false;
        this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
        this.mEgl.eglDestroyContext(this.mEglDisplay, this.mEglContext);
        this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.mEgl.eglTerminate(this.mEglDisplay);
        this.mEglSurface = null;
        this.mEglContext = null;
        this.mEglDisplay = null;
        this.mActivity = null;
        this.mEglThread.quit();
        if (!(this.mDualCameraWaterMarkBitmap == null || this.mDualCameraWaterMarkBitmap.isRecycled())) {
            this.mDualCameraWaterMarkBitmap.recycle();
            this.mDualCameraWaterMarkBitmap = null;
        }
        System.gc();
        Log.d(TAG, "SnapshotEffectRender: has been released!!!");
    }
}
