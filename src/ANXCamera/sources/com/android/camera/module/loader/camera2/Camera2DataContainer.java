package com.android.camera.module.loader.camera2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.SparseArray;
import com.android.camera.CameraAppImpl;
import com.android.camera.data.DataRepository;
import com.android.camera.log.Log;
import com.android.camera2.CameraCapabilities;
import java.util.Arrays;

@SuppressLint({"MissingPermission"})
@TargetApi(21)
public class Camera2DataContainer {
    public static final int BOGUS_CAMERA_ID_BACK = 0;
    public static final int BOGUS_CAMERA_ID_FRONT = 1;
    private static final int INDEX_AUX = 1;
    private static final int INDEX_BOKEH = 3;
    private static final int INDEX_INFRARED = 5;
    private static final int INDEX_MAIN = 0;
    private static final int INDEX_MUX = 2;
    private static final int INDEX_VIRTUAL = 4;
    private static final int INVALID_CAMERA_ID = -1;
    private static final int MAX_TYPES_OF_CAMERAS_OF_EACH_FACING_DIRECTION = 6;
    private static final String TAG = Camera2DataContainer.class.getSimpleName();
    private static final int ULTRA_WIDE_BOKEH = 63;
    private static final int ULTRA_WIDE_ID = 21;
    private static final Camera2DataContainer sInstance = new Camera2DataContainer();
    private volatile SparseArray<CameraCapabilities> mCapabilities = null;
    private volatile int mCurrentOpenedCameraId = -1;
    private volatile int[] mOrderedCameraIds = null;

    public static Camera2DataContainer getInstance() {
        synchronized (sInstance) {
            if (!sInstance.isInitialized()) {
                sInstance.init((CameraManager) CameraAppImpl.getAndroidContext().getSystemService("camera"));
            }
        }
        return sInstance;
    }

    public static Camera2DataContainer getInstance(CameraManager cameraManager) {
        synchronized (sInstance) {
            if (!sInstance.isInitialized()) {
                sInstance.init(cameraManager);
            }
        }
        return sInstance;
    }

    private Camera2DataContainer() {
    }

    private void init(CameraManager cameraManager) {
        Log.d(TAG, "E: init()");
        try {
            reset();
            String[] cameraIdList = cameraManager.getCameraIdList();
            int max = Math.max(6, cameraIdList.length);
            this.mOrderedCameraIds = new int[(max * 2)];
            Arrays.fill(this.mOrderedCameraIds, -1);
            this.mCapabilities = new SparseArray(cameraIdList.length);
            int length = cameraIdList.length;
            int i = 0;
            int i2 = max;
            max = 0;
            while (i < length) {
                String str = cameraIdList[i];
                StringBuilder stringBuilder;
                try {
                    int parseInt = Integer.parseInt(str);
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    this.mCapabilities.put(parseInt, new CameraCapabilities(cameraCharacteristics, parseInt));
                    if (DataRepository.dataItemFeature().isSupportUltraWide()) {
                        if (21 != parseInt) {
                            if (63 == parseInt) {
                            }
                        }
                        i++;
                    }
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    int i3;
                    if (num == null) {
                        str = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Unknown facing direction of camera ");
                        stringBuilder.append(parseInt);
                        Log.d(str, stringBuilder.toString());
                    } else if (num.intValue() == 1) {
                        i3 = max + 1;
                        this.mOrderedCameraIds[max] = parseInt;
                        max = i3;
                    } else if (num.intValue() == 0) {
                        i3 = i2 + 1;
                        this.mOrderedCameraIds[i2] = parseInt;
                        i2 = i3;
                    }
                } catch (NumberFormatException e) {
                    String str2 = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("non-integer camera id: ");
                    stringBuilder.append(str);
                    Log.e(str2, stringBuilder.toString());
                }
                i++;
            }
            dumpCameraIds();
        } catch (Exception e2) {
            String str3 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to init Camera2DataContainer: ");
            stringBuilder2.append(e2);
            Log.e(str3, stringBuilder2.toString());
            reset();
        }
        Log.d(TAG, "X: init()");
    }

    private boolean isInitialized() {
        return (this.mCapabilities == null || this.mOrderedCameraIds == null) ? false : true;
    }

    public synchronized void setCurrentOpenedCameraId(int i) {
        this.mCurrentOpenedCameraId = i;
    }

    public synchronized void reset() {
        Log.d(TAG, "E: reset()");
        this.mCurrentOpenedCameraId = -1;
        this.mCapabilities = null;
        this.mOrderedCameraIds = null;
        Log.d(TAG, "X: reset()");
    }

    private void dumpCameraIds() {
        int[] iArr = new int[6];
        int[] iArr2 = new int[6];
        for (int i = 0; i < 6; i++) {
            iArr[i] = this.mOrderedCameraIds[i];
            iArr2[i] = this.mOrderedCameraIds[(this.mOrderedCameraIds.length / 2) + i];
        }
        Log.d(TAG, "====================================================================");
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BACK: [main, aux, mux, bokeh, virtual, infrared] = ");
        stringBuilder.append(Arrays.toString(iArr));
        Log.d(str, stringBuilder.toString());
        str = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("FRONT: [main, aux, mux, bokeh, virtual, infrared] = ");
        stringBuilder2.append(Arrays.toString(iArr2));
        Log.d(str, stringBuilder2.toString());
        Log.d(TAG, "====================================================================");
    }

    public synchronized int getMainBackCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[0];
        }
        Log.d(TAG, "Warning: getMainBackCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getAuxCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[1];
        }
        Log.d(TAG, "Warning: getAuxCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getMuxCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[2];
        }
        Log.d(TAG, "Warning: getMuxCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getBokehCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[3];
        }
        Log.d(TAG, "Warning: getBokehCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getUltraWideBokehCameraId() {
        if (isInitialized()) {
            return 63;
        }
        Log.d(TAG, "Warning: getUltraWideBokehCameraId(): #init() failed.");
        return -1;
    }

    /* JADX WARNING: Missing block: B:13:0x001c, code skipped:
            return r1;
     */
    public synchronized boolean hasMuxCamera() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.isInitialized();	 Catch:{ all -> 0x001d }
        r1 = 0;
        if (r0 != 0) goto L_0x0011;
    L_0x0008:
        r0 = TAG;	 Catch:{ all -> 0x001d }
        r2 = "Warning: hasMuxCamera(): #init() failed.";
        com.android.camera.log.Log.d(r0, r2);	 Catch:{ all -> 0x001d }
        monitor-exit(r3);
        return r1;
    L_0x0011:
        r0 = r3.mOrderedCameraIds;	 Catch:{ all -> 0x001d }
        r2 = 2;
        r0 = r0[r2];	 Catch:{ all -> 0x001d }
        r2 = -1;
        if (r0 == r2) goto L_0x001b;
    L_0x0019:
        r1 = 1;
    L_0x001b:
        monitor-exit(r3);
        return r1;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.loader.camera2.Camera2DataContainer.hasMuxCamera():boolean");
    }

    public synchronized int getFrontCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[(this.mOrderedCameraIds.length / 2) + 0];
        }
        Log.d(TAG, "Warning: getFrontCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getAuxFrontCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[(this.mOrderedCameraIds.length / 2) + 1];
        }
        Log.d(TAG, "Warning: getAuxFrontCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getMuxFrontCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[(this.mOrderedCameraIds.length / 2) + 2];
        }
        Log.d(TAG, "Warning: getMuxFrontCameraId(): #init() failed.");
        return -1;
    }

    public synchronized int getBokehFrontCameraId() {
        if (isInitialized()) {
            return this.mOrderedCameraIds[(this.mOrderedCameraIds.length / 2) + 3];
        }
        Log.d(TAG, "Warning: getBokehFrontCameraId(): #init() failed.");
        return -1;
    }

    /* JADX WARNING: Missing block: B:12:0x001a, code skipped:
            return r0;
     */
    public synchronized boolean isFrontCameraId(int r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r3 = r2.getCapabilities(r3);	 Catch:{ all -> 0x001b }
        r0 = 0;
        if (r3 != 0) goto L_0x0011;
    L_0x0008:
        r3 = TAG;	 Catch:{ all -> 0x001b }
        r1 = "Warning: isFrontCameraId(): #init() failed.";
        com.android.camera.log.Log.d(r3, r1);	 Catch:{ all -> 0x001b }
        monitor-exit(r2);
        return r0;
    L_0x0011:
        r3 = r3.getFacing();	 Catch:{ all -> 0x001b }
        if (r3 != 0) goto L_0x0019;
    L_0x0017:
        r0 = 1;
    L_0x0019:
        monitor-exit(r2);
        return r0;
    L_0x001b:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.loader.camera2.Camera2DataContainer.isFrontCameraId(int):boolean");
    }

    /* JADX WARNING: Missing block: B:13:0x0032, code skipped:
            return r0;
     */
    public synchronized com.android.camera2.CameraCapabilities getCapabilities(int r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.isInitialized();	 Catch:{ all -> 0x0033 }
        if (r0 != 0) goto L_0x0011;
    L_0x0007:
        r5 = TAG;	 Catch:{ all -> 0x0033 }
        r0 = "Warning: getCapabilities(): #init() failed.";
        com.android.camera.log.Log.d(r5, r0);	 Catch:{ all -> 0x0033 }
        r5 = 0;
        monitor-exit(r4);
        return r5;
    L_0x0011:
        r0 = r4.mCapabilities;	 Catch:{ all -> 0x0033 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0033 }
        r0 = (com.android.camera2.CameraCapabilities) r0;	 Catch:{ all -> 0x0033 }
        if (r0 != 0) goto L_0x0031;
    L_0x001b:
        r1 = TAG;	 Catch:{ all -> 0x0033 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0033 }
        r2.<init>();	 Catch:{ all -> 0x0033 }
        r3 = "Warning: getCapabilities(): return null for camera: ";
        r2.append(r3);	 Catch:{ all -> 0x0033 }
        r2.append(r5);	 Catch:{ all -> 0x0033 }
        r5 = r2.toString();	 Catch:{ all -> 0x0033 }
        com.android.camera.log.Log.d(r1, r5);	 Catch:{ all -> 0x0033 }
    L_0x0031:
        monitor-exit(r4);
        return r0;
    L_0x0033:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.loader.camera2.Camera2DataContainer.getCapabilities(int):com.android.camera2.CameraCapabilities");
    }

    public synchronized CameraCapabilities getCurrentCameraCapabilities() {
        if (this.mCurrentOpenedCameraId == -1) {
            Log.d(TAG, "Warning: getCurrentCameraCapabilities(): mCurrentOpenedCameraId is invalid.");
        }
        return getCapabilities(this.mCurrentOpenedCameraId);
    }

    public synchronized CameraCapabilities getCapabilitiesByBogusCameraId(int i, int i2) {
        return getCapabilities(getActualOpenCameraId(i, i2));
    }

    /* JADX WARNING: Missing block: B:64:0x00d7, code skipped:
            r2 = r8;
     */
    public synchronized int getActualOpenCameraId(int r8, int r9) {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.isInitialized();	 Catch:{ all -> 0x0125 }
        if (r0 != 0) goto L_0x0010;
    L_0x0007:
        r9 = TAG;	 Catch:{ all -> 0x0125 }
        r0 = "Warning: getActualOpenCameraId(): #init() failed.";
        com.android.camera.log.Log.d(r9, r0);	 Catch:{ all -> 0x0125 }
        monitor-exit(r7);
        return r8;
        r0 = 0;
        r1 = 1;
        r2 = -1;
        if (r8 != 0) goto L_0x00d9;
    L_0x0016:
        r3 = com.android.camera.CameraSettings.isDualCameraEnable();	 Catch:{ all -> 0x0125 }
        if (r3 == 0) goto L_0x002a;
    L_0x001c:
        r3 = com.android.camera.CameraSettings.isSupportedOpticalZoom();	 Catch:{ all -> 0x0125 }
        if (r3 != 0) goto L_0x0028;
    L_0x0022:
        r3 = com.android.camera.CameraSettings.isSupportedPortrait();	 Catch:{ all -> 0x0125 }
        if (r3 == 0) goto L_0x002a;
    L_0x0028:
        r3 = r1;
        goto L_0x002c;
        r3 = r0;
    L_0x002c:
        if (r3 != 0) goto L_0x0030;
    L_0x002e:
        monitor-exit(r7);
        return r8;
    L_0x0030:
        r3 = com.android.camera.data.DataRepository.dataItemConfig();	 Catch:{ all -> 0x0125 }
        r3 = r3.getComponentConfigUltraWide();	 Catch:{ all -> 0x0125 }
        switch(r9) {
            case 161: goto L_0x00cc;
            case 162: goto L_0x00cc;
            case 163: goto L_0x009a;
            case 164: goto L_0x003b;
            case 165: goto L_0x00a5;
            case 166: goto L_0x0067;
            case 167: goto L_0x0067;
            case 168: goto L_0x003b;
            case 169: goto L_0x003b;
            case 170: goto L_0x003b;
            case 171: goto L_0x003d;
            case 172: goto L_0x003b;
            case 173: goto L_0x00cc;
            case 174: goto L_0x00cc;
            default: goto L_0x003b;
        };	 Catch:{ all -> 0x0125 }
    L_0x003b:
        goto L_0x00d7;
    L_0x003d:
        r3 = com.android.camera.data.DataRepository.dataItemRunning();	 Catch:{ all -> 0x0125 }
        r4 = "pref_ultra_wide_bokeh_enabled";
        r3 = r3.isSwitchOn(r4);	 Catch:{ all -> 0x0125 }
        if (r3 == 0) goto L_0x0055;
    L_0x0049:
        r3 = r7.getUltraWideBokehCameraId();	 Catch:{ all -> 0x0125 }
        if (r3 == r2) goto L_0x0055;
    L_0x004f:
        r2 = r7.getUltraWideBokehCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x0055:
        r3 = r7.getBokehCameraId();	 Catch:{ all -> 0x0125 }
        if (r3 == r2) goto L_0x0061;
    L_0x005b:
        r2 = r7.getBokehCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x0061:
        r2 = r7.getMuxCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x0067:
        r2 = com.android.camera.CameraSettings.isSwitchCameraZoomMode();	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00d7;
    L_0x006d:
        r2 = com.android.camera.CameraSettings.getCameraZoomMode(r9);	 Catch:{ all -> 0x0125 }
        r3 = "wide";
        r3 = r3.equals(r2);	 Catch:{ all -> 0x0125 }
        if (r3 == 0) goto L_0x007e;
    L_0x0079:
        r2 = r7.getMainBackCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x0099;
    L_0x007e:
        r3 = "tele";
        r3 = r3.equals(r2);	 Catch:{ all -> 0x0125 }
        if (r3 == 0) goto L_0x008b;
    L_0x0086:
        r2 = r7.getAuxCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x0099;
    L_0x008b:
        r3 = "ultra";
        r2 = r3.equals(r2);	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x0098;
    L_0x0093:
        r2 = r7.getUltraWideCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x0099;
    L_0x0098:
        r2 = r8;
    L_0x0099:
        goto L_0x00d8;
    L_0x009a:
        r2 = com.android.camera.CameraSettings.isRearMenuUltraPixelPhotographyOn();	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00a5;
    L_0x00a0:
        r2 = r7.getMainBackCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x00a5:
        r2 = com.android.camera.CameraSettings.isDualCameraSatEnable();	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00c1;
    L_0x00ab:
        r2 = com.mi.config.b.isSupportedOpticalZoom();	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00c1;
    L_0x00b1:
        r2 = r3.isUltraWideOnInMode(r9);	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00bc;
    L_0x00b7:
        r2 = r7.getUltraWideCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x00bc:
        r2 = r7.getMuxCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x00c1:
        r2 = r3.isUltraWideOnInMode(r9);	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00d7;
    L_0x00c7:
        r2 = r7.getUltraWideCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x00cc:
        r2 = r3.isUltraWideOnInMode(r9);	 Catch:{ all -> 0x0125 }
        if (r2 == 0) goto L_0x00d7;
    L_0x00d2:
        r2 = r7.getUltraWideCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x00d8;
    L_0x00d7:
        r2 = r8;
    L_0x00d8:
        goto L_0x0100;
    L_0x00d9:
        if (r8 != r1) goto L_0x00ff;
    L_0x00db:
        r3 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        if (r9 == r3) goto L_0x00f4;
    L_0x00df:
        switch(r9) {
            case 161: goto L_0x00e3;
            case 162: goto L_0x00e3;
            default: goto L_0x00e2;
        };	 Catch:{ all -> 0x0125 }
    L_0x00e2:
        goto L_0x00ff;
    L_0x00e3:
        r3 = com.android.camera.CameraSettings.isVideoBokehOn();	 Catch:{ all -> 0x0125 }
        if (r3 == 0) goto L_0x00ff;
    L_0x00e9:
        r3 = r7.getBokehFrontCameraId();	 Catch:{ all -> 0x0125 }
        if (r3 == r2) goto L_0x00ff;
    L_0x00ef:
        r2 = r7.getBokehFrontCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x0100;
    L_0x00f4:
        r3 = r7.getBokehFrontCameraId();	 Catch:{ all -> 0x0125 }
        if (r3 == r2) goto L_0x00ff;
    L_0x00fa:
        r2 = r7.getBokehFrontCameraId();	 Catch:{ all -> 0x0125 }
        goto L_0x0100;
    L_0x00ff:
        r2 = r8;
    L_0x0100:
        r3 = TAG;	 Catch:{ all -> 0x0125 }
        r4 = java.util.Locale.US;	 Catch:{ all -> 0x0125 }
        r5 = "getActualOpenCameraId: mode=%x, id=%d->%d";
        r6 = 3;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x0125 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0125 }
        r6[r0] = r9;	 Catch:{ all -> 0x0125 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0125 }
        r6[r1] = r8;	 Catch:{ all -> 0x0125 }
        r8 = 2;
        r9 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x0125 }
        r6[r8] = r9;	 Catch:{ all -> 0x0125 }
        r8 = java.lang.String.format(r4, r5, r6);	 Catch:{ all -> 0x0125 }
        com.android.camera.log.Log.d(r3, r8);	 Catch:{ all -> 0x0125 }
        monitor-exit(r7);
        return r2;
    L_0x0125:
        r8 = move-exception;
        monitor-exit(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.loader.camera2.Camera2DataContainer.getActualOpenCameraId(int, int):int");
    }

    public synchronized int getUltraWideCameraId() {
        if (isInitialized()) {
            return 21;
        }
        Log.d(TAG, "Warning: getUltraWideCameraId(): #init() failed.");
        return -1;
    }
}
