package com.miui.extravideo.common;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.Callback;
import android.media.MediaCodec.CodecException;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import java.nio.ByteBuffer;

public class MediaDecoderAsync {
    private static final String TAG = "MediaDecoderAsync";
    private int mDecodeFrameIndex;
    private MediaCodec mDecoder;
    private Handler mHandler;
    private Exception mInitException;
    private DecodeUpdateListener mListener;
    private final MediaExtractor mMediaExtractor;
    private final MediaParamsHolder mMediaParamsHolder;
    private int mSkipFrameTimes;
    private final String mTargetFile;

    private class CustomCallback extends Callback {
        private CustomCallback() {
        }

        /* synthetic */ CustomCallback(MediaDecoderAsync mediaDecoderAsync, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onInputBufferAvailable(@NonNull MediaCodec mediaCodec, int i) {
            try {
                int i2 = 0;
                int readSampleData = MediaDecoderAsync.this.mMediaExtractor.readSampleData(mediaCodec.getInputBuffer(i), 0);
                long sampleTime = MediaDecoderAsync.this.mMediaExtractor.getSampleTime();
                
/*
Method generation error in method: com.miui.extravideo.common.MediaDecoderAsync.CustomCallback.onInputBufferAvailable(android.media.MediaCodec, int):void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001b: INVOKE  (wrap: com.miui.extravideo.common.MediaDecoderAsync
  0x0019: IGET  (r0_3 com.miui.extravideo.common.MediaDecoderAsync) = (r10_0 'this' com.miui.extravideo.common.MediaDecoderAsync$CustomCallback A:{THIS}) com.miui.extravideo.common.MediaDecoderAsync.CustomCallback.this$0 com.miui.extravideo.common.MediaDecoderAsync) com.miui.extravideo.common.MediaDecoderAsync.access$208(com.miui.extravideo.common.MediaDecoderAsync):int type: STATIC in method: com.miui.extravideo.common.MediaDecoderAsync.CustomCallback.onInputBufferAvailable(android.media.MediaCodec, int):void, dex: 
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:228)
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
	... 24 more
Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Unknown Source)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:60)
	... 42 more

*/

        public void onOutputBufferAvailable(@NonNull MediaCodec mediaCodec, int i, @NonNull BufferInfo bufferInfo) {
            try {
                int i2 = (bufferInfo.flags & 4) != 0 ? 1 : 0;
                ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(i);
                if (i2 == 0) {
                    if (MediaDecoderAsync.this.mListener != null) {
                        MediaDecoderAsync.this.mListener.onDecodeBuffer(outputBuffer, bufferInfo);
                        Log.d(MediaDecoderAsync.TAG, String.format("output decode presentation time : %d", new Object[]{Long.valueOf(bufferInfo.presentationTimeUs)}));
                        outputBuffer.clear();
                        mediaCodec.releaseOutputBuffer(i, false);
                    }
                } else if (MediaDecoderAsync.this.mListener != null) {
                    MediaDecoderAsync.this.mListener.onDecodeStop(true);
                    Log.d(MediaDecoderAsync.TAG, "OutputBuffer BUFFER_FLAG_END_OF_STREAM");
                }
            } catch (Exception e) {
                Log.d(MediaDecoderAsync.TAG, "onOutputBufferAvailable exception", e);
            }
        }

        public void onError(@NonNull MediaCodec mediaCodec, @NonNull CodecException codecException) {
            Log.d(MediaDecoderAsync.TAG, "onError", codecException);
            if (MediaDecoderAsync.this.mListener != null) {
                MediaDecoderAsync.this.mListener.onError();
            }
        }

        public void onOutputFormatChanged(@NonNull MediaCodec mediaCodec, @NonNull MediaFormat mediaFormat) {
            Log.d(MediaDecoderAsync.TAG, String.format("onOutputFormatChanged : %s", new Object[]{mediaFormat}));
            if (MediaDecoderAsync.this.mListener != null) {
                MediaDecoderAsync.this.mListener.onOutputFormatChange(mediaFormat);
            }
        }
    }

    public interface DecodeUpdateListener {
        void onDecodeBuffer(ByteBuffer byteBuffer, BufferInfo bufferInfo);

        void onDecodeStop(boolean z);

        void onError();

        void onFrameDecodeBegin(int i, long j);

        void onOutputFormatChange(MediaFormat mediaFormat);
    }

    public MediaDecoderAsync(String str) {
        this(str, null);
    }

    public MediaDecoderAsync(String str, Handler handler) {
        this.mSkipFrameTimes = 1;
        this.mDecodeFrameIndex = 0;
        this.mHandler = handler;
        this.mTargetFile = str;
        this.mMediaParamsHolder = new MediaParamsHolder();
        this.mMediaExtractor = new MediaExtractor();
        try {
            this.mMediaExtractor.setDataSource(this.mTargetFile);
            for (int i = 0; i < this.mMediaExtractor.getTrackCount(); i++) {
                MediaFormat trackFormat = this.mMediaExtractor.getTrackFormat(i);
                String string = trackFormat.getString("mime");
                if (string.startsWith("video/")) {
                    this.mMediaParamsHolder.videoWidth = trackFormat.getInteger("width");
                    this.mMediaParamsHolder.videoHeight = trackFormat.getInteger("height");
                    this.mMediaParamsHolder.videoDegree = trackFormat.getInteger("rotation-degrees");
                    this.mMediaParamsHolder.mimeType = string;
                    this.mMediaExtractor.selectTrack(i);
                    this.mDecoder = MediaCodec.createDecoderByType(string);
                    this.mDecoder.setCallback(new CustomCallback(this, null), handler);
                    this.mDecoder.configure(trackFormat, null, null, 0);
                    return;
                }
            }
        } catch (Exception e) {
            this.mInitException = e;
        }
    }

    public void setListener(DecodeUpdateListener decodeUpdateListener) {
        this.mListener = decodeUpdateListener;
    }

    public void start() throws Exception {
        if (this.mInitException == null) {
            this.mDecoder.start();
            return;
        }
        throw this.mInitException;
    }

    public void stop() {
        this.mDecoder.stop();
        if (this.mListener == null) {
            return;
        }
        if (this.mHandler == null || this.mHandler.getLooper() == Looper.myLooper()) {
            this.mListener.onDecodeStop(false);
        } else {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (MediaDecoderAsync.this.mListener != null) {
                        MediaDecoderAsync.this.mListener.onDecodeStop(false);
                    }
                }
            });
        }
    }

    public void release() {
        if (this.mDecoder != null) {
            this.mDecoder.stop();
            this.mDecoder.release();
            this.mDecoder = null;
        }
        if (this.mMediaExtractor != null) {
            this.mMediaExtractor.release();
        }
    }

    public MediaParamsHolder getMediaParamsHolder() {
        return this.mMediaParamsHolder;
    }

    public void setSkipFrameTimes(int i) {
        this.mSkipFrameTimes = i;
    }
}
