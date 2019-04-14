package com.android.camera.snap;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.ViewConfiguration;
import com.android.camera.ProximitySensorLock;
import com.android.camera.Util;
import com.android.camera.lib.compatibility.util.CompatibilityUtils;
import com.android.camera.log.Log;
import com.android.camera.snap.SnapCamera.SnapStatusListener;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.storage.Storage;

public class SnapTrigger implements SnapStatusListener {
    private static final int INTERVAL_DELAY = 200;
    private static final int MAX_BURST_COUNT = 100;
    public static final int MAX_VIDEO_DURATION = 300000;
    public static final String STREET_SNAP_CHANNEL_ID = "com.android.camera.streetsnap";
    private static final String TAG = SnapTrigger.class.getSimpleName();
    private static final int TRIGGER_KEY = 25;
    private static SnapTrigger sInstance;
    private int mBurstCount = 0;
    private SnapCamera mCamera = null;
    private boolean mCameraOpened;
    private Context mContext;
    private Handler mHandler;
    private final Runnable mLongPressRunnable = new Runnable() {
        public void run() {
            SnapTrigger.this.initCamera();
        }
    };
    private PowerManager mPowerManager = null;
    private ProximitySensorLock mProximityLock;
    private final Runnable mSnapRunnable = new Runnable() {
        public void run() {
            if (SnapTrigger.this.mCamera != null) {
                if (SnapTrigger.this.mPowerManager != null && SnapTrigger.this.mPowerManager.isScreenOn()) {
                    Log.d(SnapTrigger.TAG, "isScreenOn is true, stop take snap");
                    SnapTrigger.this.mHandler.removeMessages(101);
                } else if (!SnapTrigger.this.shouldQuitSnap() && Storage.getAvailableSpace() >= Storage.LOW_STORAGE_THRESHOLD) {
                    if (SnapTrigger.this.mCamera.isCamcorder()) {
                        SnapTrigger.this.shutdownWatchDog();
                        SnapTrigger.this.vibratorShort();
                        SnapTrigger.this.mCamera.startCamcorder();
                        Log.d(SnapTrigger.TAG, "take movie");
                        CameraStatUtil.trackSnapInfo(true);
                    } else {
                        SnapTrigger.this.triggerWatchdog(false);
                        SnapTrigger.this.mCamera.takeSnap();
                        
/*
Method generation error in method: com.android.camera.snap.SnapTrigger.1.run():void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0085: INVOKE  (wrap: com.android.camera.snap.SnapTrigger
  0x0083: IGET  (r0_26 com.android.camera.snap.SnapTrigger) = (r4_0 'this' com.android.camera.snap.SnapTrigger$1 A:{THIS}) com.android.camera.snap.SnapTrigger.1.this$0 com.android.camera.snap.SnapTrigger) com.android.camera.snap.SnapTrigger.access$808(com.android.camera.snap.SnapTrigger):int type: STATIC in method: com.android.camera.snap.SnapTrigger.1.run():void, dex: 
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:228)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:205)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:102)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:52)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:130)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:89)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:95)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:120)
	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:145)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
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
	at jadx.core.codegen.InsnGen.inlineAnonymousConstr(InsnGen.java:610)
	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:561)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:336)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:101)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:91)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:441)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
	at jadx.core.codegen.ClassGen.addInsnBody(ClassGen.java:435)
	at jadx.core.codegen.ClassGen.addFields(ClassGen.java:375)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:219)
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
	... 46 more
Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Unknown Source)
	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:60)
	... 64 more

*/
    };

    public static SnapTrigger getInstance() {
        if (sInstance == null) {
            sInstance = new SnapTrigger();
        }
        return sInstance;
    }

    public synchronized boolean init(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        if (ProximitySensorLock.enabled() && !Util.isNonUIEnabled()) {
            this.mProximityLock = new ProximitySensorLock(this.mContext);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("init, create a new instance -> ");
            stringBuilder.append(this.mProximityLock);
            Log.d(str, stringBuilder.toString());
            this.mProximityLock.startWatching();
        }
        return isRunning();
    }

    private void onDestroy() {
        if (this.mProximityLock != null) {
            this.mProximityLock.destroy();
        }
        this.mProximityLock = null;
    }

    public synchronized boolean isRunning() {
        boolean z;
        z = (this.mContext == null || this.mHandler == null) ? false : true;
        return z;
    }

    public static synchronized void destroy() {
        synchronized (SnapTrigger.class) {
            if (sInstance != null) {
                sInstance.onDestroy();
                sInstance.mBurstCount = 0;
                if (sInstance.mCamera != null) {
                    sInstance.mCamera.release();
                    sInstance.mCamera = null;
                }
                sInstance.mHandler = null;
                sInstance.mContext = null;
                sInstance = null;
            }
        }
    }

    public void handleKeyEvent(int i, int i2, long j) {
        if (isRunning()) {
            boolean z = true;
            if (i == 25) {
                if (i2 == 0) {
                    this.mHandler.postDelayed(this.mLongPressRunnable, ViewConfiguration.getGlobalActionKeyTimeout());
                } else if (i2 == 1) {
                    this.mHandler.removeCallbacks(this.mLongPressRunnable);
                    this.mHandler.removeCallbacks(this.mSnapRunnable);
                    triggerWatchdog(z);
                }
            } else if (i == 26) {
                this.mHandler.removeCallbacks(this.mLongPressRunnable);
                this.mHandler.removeCallbacks(this.mSnapRunnable);
                triggerWatchdog(z);
            }
            z = false;
            triggerWatchdog(z);
        }
    }

    private boolean shouldQuitSnap() {
        boolean isNonUI;
        if (ProximitySensorLock.enabled() && Util.isNonUIEnabled()) {
            isNonUI = Util.isNonUI();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("shouldQuitSnap isNonUI = ");
            stringBuilder.append(isNonUI);
            Log.d(str, stringBuilder.toString());
            if (isNonUI) {
                CameraStatUtil.track(CameraStat.CATEGORY_COUNTER, CameraStat.KEY_POCKET_MODE_ENTER, CameraStat.PARAM_COMMON_MODE, CameraStat.POCKET_MODE_NONUI_ENTER_SNAP);
            }
            return isNonUI;
        }
        isNonUI = this.mProximityLock != null && this.mProximityLock.shouldQuitSnap();
        return isNonUI;
    }

    private void triggerWatchdog(boolean z) {
        if (this.mHandler != null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("watch dog On -");
            stringBuilder.append(z);
            Log.d(str, stringBuilder.toString());
            this.mHandler.removeMessages(101);
            this.mHandler.sendEmptyMessageDelayed(101, z ? 0 : 5000);
        }
    }

    private void shutdownWatchDog() {
        if (this.mHandler != null) {
            Log.d(TAG, "watch dog Off");
            this.mHandler.removeMessages(101);
        }
    }

    private void initCamera() {
        if (this.mCamera == null && this.mContext != null) {
            this.mCameraOpened = false;
            this.mCamera = new SnapCamera(this.mContext, this);
        }
    }

    public void onDone(Uri uri) {
        if (isRunning()) {
            triggerWatchdog(false);
            vibratorShort();
            if (!this.mCamera.isCamcorder() && this.mBurstCount < 100) {
                this.mHandler.postDelayed(this.mSnapRunnable, 200);
            }
            if (uri != null) {
                notifyForDetail(this.mContext, uri, this.mContext.getString(2131296641), this.mContext.getString(2131296642), this.mCamera.isCamcorder());
            }
        }
    }

    public void onSkipCapture() {
        if (isRunning()) {
            Log.d(TAG, "onSkipCapture");
            this.mBurstCount--;
            if (this.mHandler != null) {
                this.mHandler.postDelayed(this.mSnapRunnable, 200);
            }
            return;
        }
        Log.w(TAG, "onSkipCapture: exit");
    }

    public void onCameraOpened() {
        if (isRunning()) {
            Log.d(TAG, "onCameraOpened");
            this.mCameraOpened = true;
            if (this.mHandler != null) {
                this.mHandler.postDelayed(this.mSnapRunnable, this.mCamera.isCamcorder() ? 100 : 0);
            }
            return;
        }
        Log.w(TAG, "onCameraOpened: exit");
    }

    private void vibratorShort() {
        vibrator(new long[]{10, 20});
    }

    private void vibrator(long[] jArr) {
        try {
            Log.d(TAG, "call vibrate to notify");
            ((Vibrator) this.mContext.getSystemService("vibrator")).vibrate(jArr, -1);
        } catch (Exception e) {
            Log.e(TAG, "vibrator exception", e);
        }
    }

    public static void notifyForDetail(Context context, Uri uri, String str, String str2, boolean z) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(uri, z ? "video/*" : "image/*");
            Builder when = new Builder(context).setContentTitle(str).setContentText(str2).setContentIntent(PendingIntent.getActivity(context, 0, intent, 0)).setSmallIcon(17301569).setWhen(System.currentTimeMillis());
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            CompatibilityUtils.addChannelForNotificationBuilder(notificationManager, STREET_SNAP_CHANNEL_ID, context.getResources().getString(2131296265), when);
            Notification build = when.build();
            build.flags |= 16;
            build.extraNotification.setMessageCount(0);
            notificationManager.notify(0, build);
        } catch (NullPointerException e) {
        }
    }
}
