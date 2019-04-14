package com.xiaomi.camera.base;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraCharacteristics.Key;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureResult;
import android.os.Parcelable;
import com.android.camera.log.Log;
import com.xiaomi.protocol.ICustomCaptureResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class CameraDeviceUtil {
    private static final String TAG = CameraDeviceUtil.class.getSimpleName();

    private CameraDeviceUtil() {
    }

    public static void prepareCalibrationDataForAlgo(Context context, String str) {
        try {
            CameraCharacteristics cameraCharacteristics = ((CameraManager) context.getSystemService("camera")).getCameraCharacteristics(str);
            Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (num != null) {
                boolean z = num.intValue() == 0;
                try {
                    byte[] bArr = (byte[]) cameraCharacteristics.get((Key) Class.forName("android.hardware.camera2.CameraCharacteristics$Key").getDeclaredConstructor(new Class[]{String.class, Class.class}).newInstance(new Object[]{"com.xiaomi.camera.algoup.dualCalibrationData", byte[].class}));
                    if (bArr != null) {
                        CommonUtil.saveCameraCalibrationToFile(context, bArr, z);
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    Log.e(TAG, "prepareCalibrationDataForAlgo: call reflect method failed!", e);
                    throw new RuntimeException("getCameraCharacteristics's dualCalibrationData failed");
                }
            }
        } catch (CameraAccessException e2) {
            Log.e(TAG, "prepareCalibrationDataForAlgo: get getCameraCharacteristics failed!", e2);
        }
    }

    public static ICustomCaptureResult getCustomCaptureResult(CaptureResult captureResult) {
        try {
            Method method = captureResult.getClass().getMethod("getNativeCopy", new Class[0]);
            method.setAccessible(true);
            Parcelable parcelable = (Parcelable) method.invoke(captureResult, new Object[0]);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getCustomCaptureResult: cameraMetadataNative =");
            stringBuilder.append(parcelable);
            Log.d(str, stringBuilder.toString());
            ICustomCaptureResult iCustomCaptureResult = new ICustomCaptureResult();
            iCustomCaptureResult.setFrameNumber(captureResult.getFrameNumber());
            iCustomCaptureResult.setRequest(captureResult.getRequest());
            iCustomCaptureResult.setSequenceId(captureResult.getSequenceId());
            iCustomCaptureResult.setResults(parcelable);
            Long l = (Long) captureResult.get(CaptureResult.SENSOR_TIMESTAMP);
            if (l != null) {
                iCustomCaptureResult.setTimeStamp(l.longValue());
            }
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("getCustomCaptureResult: ");
            stringBuilder2.append(iCustomCaptureResult);
            Log.d(str2, stringBuilder2.toString());
            return iCustomCaptureResult;
        } catch (Exception e) {
            Log.e(TAG, "getCustomCaptureResult: getCustomCaptureResult", e);
            return null;
        }
    }

    /* JADX WARNING: Missing block: B:19:0x0022, code skipped:
            return com.xiaomi.engine.CameraCombinationMode.CAM_COMBINATION_MODE_REAR_BOKEH_WT;
     */
    /* JADX WARNING: Missing block: B:21:0x0025, code skipped:
            return com.xiaomi.engine.CameraCombinationMode.CAM_COMBINATION_MODE_REAR_SAT_WT;
     */
    /* JADX WARNING: Missing block: B:23:0x0027, code skipped:
            return 2;
     */
    public static int getCameraCombinationMode(int r1) {
        /*
        r0 = 40;
        if (r1 == r0) goto L_0x002d;
    L_0x0004:
        switch(r1) {
            case 0: goto L_0x002b;
            case 1: goto L_0x0028;
            case 2: goto L_0x0026;
            case 3: goto L_0x0023;
            case 4: goto L_0x0020;
            default: goto L_0x0007;
        };
    L_0x0007:
        switch(r1) {
            case 20: goto L_0x0026;
            case 21: goto L_0x001e;
            default: goto L_0x000a;
        };
    L_0x000a:
        switch(r1) {
            case 60: goto L_0x0023;
            case 61: goto L_0x0020;
            case 62: goto L_0x001b;
            case 63: goto L_0x0018;
            default: goto L_0x000d;
        };
    L_0x000d:
        switch(r1) {
            case 80: goto L_0x0015;
            case 81: goto L_0x0012;
            default: goto L_0x0010;
        };
    L_0x0010:
        r1 = 0;
        return r1;
    L_0x0012:
        r1 = 771; // 0x303 float:1.08E-42 double:3.81E-321;
        return r1;
    L_0x0015:
        r1 = 515; // 0x203 float:7.22E-43 double:2.544E-321;
        return r1;
    L_0x0018:
        r1 = 770; // 0x302 float:1.079E-42 double:3.804E-321;
        return r1;
    L_0x001b:
        r1 = 514; // 0x202 float:7.2E-43 double:2.54E-321;
        return r1;
    L_0x001e:
        r1 = 3;
        return r1;
    L_0x0020:
        r1 = 769; // 0x301 float:1.078E-42 double:3.8E-321;
        return r1;
    L_0x0023:
        r1 = 513; // 0x201 float:7.19E-43 double:2.535E-321;
        return r1;
    L_0x0026:
        r1 = 2;
        return r1;
    L_0x0028:
        r1 = 17;
        return r1;
    L_0x002b:
        r1 = 1;
        return r1;
    L_0x002d:
        r1 = 18;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.base.CameraDeviceUtil.getCameraCombinationMode(int):int");
    }
}
