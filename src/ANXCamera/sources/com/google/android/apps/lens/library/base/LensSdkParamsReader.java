package com.google.android.apps.lens.library.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.apps.lens.library.base.proto.nano.LensSdkParamsProto.LensSdkParams;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class LensSdkParamsReader {
    public static final String AGSA_AUTHORITY = "com.google.android.googlequicksearchbox.GsaPublicContentProvider";
    private static final LensSdkParams DEFAULT_PARAMS = new LensSdkParams();
    public static final String LENS_AR_STICKERS_ACTIVITY = "com.google.vr.apps.ornament.app.MainActivity";
    public static final String LENS_AR_STICKERS_PACKAGE = "com.google.ar.lens";
    public static final String LENS_AVAILABILITY_PROVIDER_URI = String.format("content://%s/publicvalue/lens_oem_availability", new Object[]{AGSA_AUTHORITY});
    private static final String LENS_SDK_VERSION = "0.1.0";
    private static final int MIN_AR_CORE_VERSION = 24;
    private static final String TAG = "LensSdkParamsReader";
    private final List<LensSdkParamsCallback> callbacks;
    private final Context context;
    private LensSdkParams lensSdkParams;
    private boolean lensSdkParamsReady;
    private final PackageManager packageManager;

    public interface LensSdkParamsCallback {
        void onLensSdkParamsAvailable(LensSdkParams lensSdkParams);
    }

    private static class QueryGsaTask extends AsyncTask<Void, Void, Integer> {
        SoftReference<LensSdkParamsReader> mLensSdkParamsReaderRef;

        private QueryGsaTask(LensSdkParamsReader lensSdkParamsReader) {
            this.mLensSdkParamsReaderRef = new SoftReference(lensSdkParamsReader);
        }

        /* Access modifiers changed, original: protected|varargs */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0076  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0070  */
        public java.lang.Integer doInBackground(java.lang.Void... r9) {
            /*
            r8 = this;
            r9 = 4;
            r0 = 0;
            r1 = r8.mLensSdkParamsReaderRef;	 Catch:{ Exception -> 0x0069 }
            r1 = r1.get();	 Catch:{ Exception -> 0x0069 }
            r1 = (com.google.android.apps.lens.library.base.LensSdkParamsReader) r1;	 Catch:{ Exception -> 0x0069 }
            if (r1 != 0) goto L_0x0014;
        L_0x000d:
            r1 = -1;
            r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0069 }
            return r1;
        L_0x0014:
            r1 = r1.context;	 Catch:{ Exception -> 0x0069 }
            r2 = r1.getContentResolver();	 Catch:{ Exception -> 0x0069 }
            r1 = com.google.android.apps.lens.library.base.LensSdkParamsReader.LENS_AVAILABILITY_PROVIDER_URI;	 Catch:{ Exception -> 0x0069 }
            r3 = android.net.Uri.parse(r1);	 Catch:{ Exception -> 0x0069 }
            r4 = r0;
            r4 = (java.lang.String[]) r4;	 Catch:{ Exception -> 0x0069 }
            r5 = r0;
            r5 = (java.lang.String) r5;	 Catch:{ Exception -> 0x0069 }
            r6 = r0;
            r6 = (java.lang.String[]) r6;	 Catch:{ Exception -> 0x0069 }
            r7 = r0;
            r7 = (java.lang.String) r7;	 Catch:{ Exception -> 0x0069 }
            r1 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0069 }
            if (r1 == 0) goto L_0x005c;
        L_0x0034:
            r0 = r1.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x0056 }
            if (r0 == 0) goto L_0x005c;
        L_0x003a:
            r1.moveToFirst();	 Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r0 = 0;
            r0 = r1.getString(r0);	 Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r2 = 6;
            if (r0 <= r2) goto L_0x004b;
            r0 = r2;
        L_0x004b:
            r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0059, all -> 0x0056 }
            if (r1 == 0) goto L_0x0055;
        L_0x0052:
            r1.close();
        L_0x0055:
            return r0;
        L_0x0056:
            r9 = move-exception;
            r0 = r1;
            goto L_0x0074;
        L_0x0059:
            r0 = move-exception;
            r0 = r1;
            goto L_0x006a;
        L_0x005c:
            r0 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x0059, all -> 0x0056 }
            if (r1 == 0) goto L_0x0065;
        L_0x0062:
            r1.close();
        L_0x0065:
            r9 = r0;
            goto L_0x0073;
        L_0x0067:
            r9 = move-exception;
            goto L_0x0074;
        L_0x0069:
            r1 = move-exception;
        L_0x006a:
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0067 }
            if (r0 == 0) goto L_0x0073;
        L_0x0070:
            r0.close();
        L_0x0073:
            return r9;
        L_0x0074:
            if (r0 == 0) goto L_0x0079;
        L_0x0076:
            r0.close();
        L_0x0079:
            throw r9;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.lens.library.base.LensSdkParamsReader$QueryGsaTask.doInBackground(java.lang.Void[]):java.lang.Integer");
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Integer num) {
            String valueOf = String.valueOf(num);
            LensSdkParamsReader lensSdkParamsReader = (LensSdkParamsReader) this.mLensSdkParamsReaderRef.get();
            if (lensSdkParamsReader != null) {
                String str = LensSdkParamsReader.TAG;
                StringBuilder stringBuilder = new StringBuilder(25 + valueOf.length());
                stringBuilder.append("Lens availability result:");
                stringBuilder.append(valueOf);
                Log.i(str, stringBuilder.toString());
                lensSdkParamsReader.lensSdkParams.lensAvailabilityStatus = num.intValue();
                lensSdkParamsReader.lensSdkParamsReady = true;
                for (LensSdkParamsCallback onLensSdkParamsAvailable : lensSdkParamsReader.callbacks) {
                    onLensSdkParamsAvailable.onLensSdkParamsAvailable(lensSdkParamsReader.lensSdkParams);
                }
                lensSdkParamsReader.callbacks.clear();
            }
        }
    }

    static {
        DEFAULT_PARAMS.lensSdkVersion = LENS_SDK_VERSION;
        DEFAULT_PARAMS.agsaVersionName = "";
        DEFAULT_PARAMS.lensAvailabilityStatus = -1;
        DEFAULT_PARAMS.arStickersAvailabilityStatus = -1;
    }

    public LensSdkParamsReader(@NonNull Context context) {
        this(context, context.getPackageManager());
    }

    LensSdkParamsReader(@NonNull Context context, @NonNull PackageManager packageManager) {
        this.callbacks = new ArrayList();
        this.context = context;
        this.packageManager = packageManager;
        updateParams();
    }

    public String getLensSdkVersion() {
        return this.lensSdkParams.lensSdkVersion;
    }

    public String getAgsaVersionName() {
        return this.lensSdkParams.agsaVersionName;
    }

    public int getArStickersAvailability() {
        return this.lensSdkParams.arStickersAvailabilityStatus;
    }

    public void getParams(@NonNull LensSdkParamsCallback lensSdkParamsCallback) {
        if (this.lensSdkParamsReady) {
            lensSdkParamsCallback.onLensSdkParamsAvailable(this.lensSdkParams);
        } else {
            this.callbacks.add(lensSdkParamsCallback);
        }
    }

    private void updateParams() {
        this.lensSdkParamsReady = false;
        this.lensSdkParams = DEFAULT_PARAMS.clone();
        try {
            PackageInfo packageInfo = this.packageManager.getPackageInfo("com.google.android.googlequicksearchbox", 0);
            if (packageInfo != null) {
                this.lensSdkParams.agsaVersionName = packageInfo.versionName;
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Unable to find agsa package: com.google.android.googlequicksearchbox");
        }
        this.lensSdkParams.arStickersAvailabilityStatus = 1;
        if (VERSION.SDK_INT >= 24) {
            Intent intent = new Intent();
            intent.setClassName("com.google.ar.lens", LENS_AR_STICKERS_ACTIVITY);
            if (this.packageManager.resolveActivity(intent, 0) != null) {
                this.lensSdkParams.arStickersAvailabilityStatus = 0;
            }
        }
        new QueryGsaTask().execute(new Void[0]);
    }
}
