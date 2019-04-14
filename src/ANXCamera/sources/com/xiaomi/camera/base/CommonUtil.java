package com.xiaomi.camera.base;

import android.content.Context;

public final class CommonUtil {
    private static final String TAG = "CommonUtil";

    private CommonUtil() {
    }

    public static boolean saveCameraCalibrationToFile(Context context, byte[] bArr, boolean z) {
        String str;
        if (z) {
            str = "front_dual_camera_caldata.bin";
        } else {
            str = "back_dual_camera_caldata.bin";
        }
        return saveCameraCalibrationToFile(context, bArr, str);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:26:0x003a=Splitter:B:26:0x003a, B:21:0x002b=Splitter:B:21:0x002b} */
    private static boolean saveCameraCalibrationToFile(android.content.Context r2, byte[] r3, java.lang.String r4) {
        /*
        r0 = 0;
        if (r3 == 0) goto L_0x0059;
    L_0x0004:
        if (r2 == 0) goto L_0x0059;
    L_0x0006:
        r1 = 0;
        r2 = r2.openFileOutput(r4, r0);	 Catch:{ FileNotFoundException -> 0x0039, IOException -> 0x002a }
        r2.write(r3);	 Catch:{ FileNotFoundException -> 0x0025, IOException -> 0x0022, all -> 0x001f }
        r0 = 1;
        r2.flush();	 Catch:{ Exception -> 0x0016 }
        r2.close();	 Catch:{ Exception -> 0x0016 }
    L_0x0015:
        goto L_0x0059;
    L_0x0016:
        r2 = move-exception;
        r3 = "CommonUtil";
        r4 = "saveCameraCalibrationToFile: failed!";
        com.android.camera.log.Log.w(r3, r4, r2);
        goto L_0x0059;
    L_0x001f:
        r3 = move-exception;
        r1 = r2;
        goto L_0x0048;
    L_0x0022:
        r3 = move-exception;
        r1 = r2;
        goto L_0x002b;
    L_0x0025:
        r3 = move-exception;
        r1 = r2;
        goto L_0x003a;
    L_0x0028:
        r3 = move-exception;
        goto L_0x0048;
    L_0x002a:
        r3 = move-exception;
    L_0x002b:
        r2 = "CommonUtil";
        r4 = "saveCameraCalibrationToFile: IOException";
        com.android.camera.log.Log.w(r2, r4, r3);	 Catch:{ all -> 0x0028 }
        r1.flush();	 Catch:{ Exception -> 0x0016 }
        r1.close();	 Catch:{ Exception -> 0x0016 }
        goto L_0x0015;
    L_0x0039:
        r3 = move-exception;
    L_0x003a:
        r2 = "CommonUtil";
        r4 = "saveCameraCalibrationToFile: FileNotFoundException";
        com.android.camera.log.Log.w(r2, r4, r3);	 Catch:{ all -> 0x0028 }
        r1.flush();	 Catch:{ Exception -> 0x0016 }
        r1.close();	 Catch:{ Exception -> 0x0016 }
        goto L_0x0015;
        r1.flush();	 Catch:{ Exception -> 0x0050 }
        r1.close();	 Catch:{ Exception -> 0x0050 }
        goto L_0x0058;
    L_0x0050:
        r2 = move-exception;
        r4 = "CommonUtil";
        r0 = "saveCameraCalibrationToFile: failed!";
        com.android.camera.log.Log.w(r4, r0, r2);
    L_0x0058:
        throw r3;
    L_0x0059:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.base.CommonUtil.saveCameraCalibrationToFile(android.content.Context, byte[], java.lang.String):boolean");
    }
}
