package com.android.camera.module;

import android.location.Location;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import com.android.camera.ExifHelper;
import com.android.camera.FileCompat;
import com.android.camera.LocationManager;
import com.android.camera.Thumbnail;
import com.android.camera.groupshot.GroupShot;
import com.android.camera.log.Log;
import com.android.camera.storage.SaverCallback;
import com.android.camera.storage.Storage;
import java.lang.ref.WeakReference;

public class SaveOutputImageTask extends AsyncTask<Void, Integer, Thumbnail> {
    private static final String TAG = "SaveOutputImageTask";
    private GroupShot mGroupShotInternal;
    private int mHeight;
    private Location mLocation;
    private int mOrientation;
    private WeakReference<SaverCallback> mSaverCallbackWeakReference;
    private volatile long mStartTime;
    private long mTimeTaken;
    private String mTitle;
    private int mWidth;

    public SaveOutputImageTask(SaverCallback saverCallback, long j, Location location, int i, int i2, int i3, String str, GroupShot groupShot) {
        this.mSaverCallbackWeakReference = new WeakReference(saverCallback);
        this.mTimeTaken = j;
        this.mLocation = location;
        this.mWidth = i;
        this.mHeight = i2;
        this.mOrientation = i3;
        this.mTitle = str;
        this.mGroupShotInternal = groupShot;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0169  */
    public com.android.camera.Thumbnail doInBackground(java.lang.Void... r15) {
        /*
        r14 = this;
        r15 = "SaveOutputImageTask";
        r0 = "doInBackground start";
        com.android.camera.log.Log.v(r15, r0);
        r15 = 0;
        r0 = r14.mGroupShotInternal;	 Catch:{ Exception -> 0x014b }
        r0 = r0.attach_end();	 Catch:{ Exception -> 0x014b }
        r1 = "SaveOutputImageTask";
        r2 = "attach_end() = 0x%08x";
        r3 = 1;
        r4 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x014b }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x014b }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ Exception -> 0x014b }
        r0 = java.lang.String.format(r2, r4);	 Catch:{ Exception -> 0x014b }
        com.android.camera.log.Log.v(r1, r0);	 Catch:{ Exception -> 0x014b }
        r0 = r14.isCancelled();	 Catch:{ Exception -> 0x014b }
        if (r0 == 0) goto L_0x002b;
    L_0x002a:
        return r15;
    L_0x002b:
        r0 = r14.mGroupShotInternal;	 Catch:{ Exception -> 0x014b }
        r0 = r0.setBaseImage(r5);	 Catch:{ Exception -> 0x014b }
        r1 = "SaveOutputImageTask";
        r2 = "setBaseImage() = 0x%08x";
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x014b }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x014b }
        r3[r5] = r0;	 Catch:{ Exception -> 0x014b }
        r0 = java.lang.String.format(r2, r3);	 Catch:{ Exception -> 0x014b }
        com.android.camera.log.Log.v(r1, r0);	 Catch:{ Exception -> 0x014b }
        r0 = r14.mGroupShotInternal;	 Catch:{ Exception -> 0x014b }
        r0.setBestFace();	 Catch:{ Exception -> 0x014b }
        r0 = "SaveOutputImageTask";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x014b }
        r1.<init>();	 Catch:{ Exception -> 0x014b }
        r2 = "groupshot attach end & setbestface cost ";
        r1.append(r2);	 Catch:{ Exception -> 0x014b }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x014b }
        r6 = r14.mStartTime;	 Catch:{ Exception -> 0x014b }
        r2 = r2 - r6;
        r1.append(r2);	 Catch:{ Exception -> 0x014b }
        r1 = r1.toString();	 Catch:{ Exception -> 0x014b }
        com.android.camera.log.Log.v(r0, r1);	 Catch:{ Exception -> 0x014b }
        r0 = r14.mTitle;	 Catch:{ Exception -> 0x014b }
        r7 = com.android.camera.storage.Storage.generateFilepath(r0);	 Catch:{ Exception -> 0x014b }
        r14.saveGroupShotImage(r7);	 Catch:{ Exception -> 0x0149 }
        r0 = "SaveOutputImageTask";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0149 }
        r1.<init>();	 Catch:{ Exception -> 0x0149 }
        r2 = "groupshot finish group cost ";
        r1.append(r2);	 Catch:{ Exception -> 0x0149 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0149 }
        r8 = r14.mStartTime;	 Catch:{ Exception -> 0x0149 }
        r2 = r2 - r8;
        r1.append(r2);	 Catch:{ Exception -> 0x0149 }
        r2 = ", path = ";
        r1.append(r2);	 Catch:{ Exception -> 0x0149 }
        r1.append(r7);	 Catch:{ Exception -> 0x0149 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0149 }
        com.android.camera.log.Log.v(r0, r1);	 Catch:{ Exception -> 0x0149 }
        r0 = r14.isCancelled();	 Catch:{ Exception -> 0x0149 }
        if (r0 == 0) goto L_0x009b;
    L_0x009a:
        return r15;
    L_0x009b:
        r0 = com.android.camera.Util.sIsDumpOrigJpg;	 Catch:{ Exception -> 0x0149 }
        if (r0 == 0) goto L_0x00c7;
    L_0x009f:
        r0 = ".jpg";
        r0 = r7.lastIndexOf(r0);	 Catch:{ Exception -> 0x0149 }
        r0 = r7.substring(r5, r0);	 Catch:{ Exception -> 0x0149 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0149 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0149 }
        r1.mkdirs();	 Catch:{ Exception -> 0x0149 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0149 }
        r1.<init>();	 Catch:{ Exception -> 0x0149 }
        r1.append(r0);	 Catch:{ Exception -> 0x0149 }
        r0 = java.io.File.separator;	 Catch:{ Exception -> 0x0149 }
        r1.append(r0);	 Catch:{ Exception -> 0x0149 }
        r0 = r1.toString();	 Catch:{ Exception -> 0x0149 }
        r1 = r14.mGroupShotInternal;	 Catch:{ Exception -> 0x0149 }
        r1.saveInputImages(r0);	 Catch:{ Exception -> 0x0149 }
    L_0x00c7:
        r0 = r14.isCancelled();	 Catch:{ Exception -> 0x0149 }
        if (r0 == 0) goto L_0x00ce;
    L_0x00cd:
        return r15;
        r0 = r14.isCancelled();
        if (r0 == 0) goto L_0x00d6;
    L_0x00d5:
        return r15;
    L_0x00d6:
        r0 = com.android.camera.CameraApplicationDelegate.getAndroidContext();
        r8 = r14.mOrientation;
        r9 = r14.mTimeTaken;
        r11 = r14.mLocation;
        r12 = r14.mWidth;
        r13 = r14.mHeight;
        r6 = r0;
        r1 = com.android.camera.storage.Storage.addImageForGroupOrPanorama(r6, r7, r8, r9, r11, r12, r13);
        r2 = "SaveOutputImageTask";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "groupshot insert db cost ";
        r3.append(r4);
        r6 = java.lang.System.currentTimeMillis();
        r8 = r14.mStartTime;
        r6 = r6 - r8;
        r3.append(r6);
        r4 = ", uri = ";
        r3.append(r4);
        r3.append(r1);
        r3 = r3.toString();
        com.android.camera.log.Log.v(r2, r3);
        r2 = r14.mSaverCallbackWeakReference;
        r2 = r2.get();
        r2 = (com.android.camera.storage.SaverCallback) r2;
        if (r2 == 0) goto L_0x0148;
    L_0x0118:
        if (r1 != 0) goto L_0x011b;
    L_0x011a:
        goto L_0x0148;
    L_0x011b:
        r15 = r14.mTitle;
        r3 = 31;
        r2.notifyNewMediaData(r1, r15, r3);
        r15 = r0.getContentResolver();
        r15 = com.android.camera.Thumbnail.createThumbnailFromUri(r15, r1, r5);
        r0 = "SaveOutputImageTask";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "groupshot asynctask cost ";
        r1.append(r2);
        r2 = java.lang.System.currentTimeMillis();
        r4 = r14.mStartTime;
        r2 = r2 - r4;
        r1.append(r2);
        r1 = r1.toString();
        com.android.camera.log.Log.v(r0, r1);
        return r15;
    L_0x0148:
        return r15;
    L_0x0149:
        r0 = move-exception;
        goto L_0x014d;
    L_0x014b:
        r0 = move-exception;
        r7 = r15;
    L_0x014d:
        r1 = "SaveOutputImageTask";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "SaveOutputImageTask exception occurs, ";
        r2.append(r3);
        r0 = r0.getMessage();
        r2.append(r0);
        r0 = r2.toString();
        com.android.camera.log.Log.e(r1, r0);
        if (r7 == 0) goto L_0x0171;
    L_0x0169:
        r0 = new java.io.File;
        r0.<init>(r7);
        r0.delete();
    L_0x0171:
        return r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.module.SaveOutputImageTask.doInBackground(java.lang.Void[]):com.android.camera.Thumbnail");
    }

    private void saveGroupShotImage(String str) {
        if (Storage.isUseDocumentMode()) {
            ParcelFileDescriptor parcelFileDescriptor;
            try {
                parcelFileDescriptor = FileCompat.getParcelFileDescriptor(str, true);
                this.mGroupShotInternal.getImageAndSaveJpeg(parcelFileDescriptor.getFileDescriptor());
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            } catch (Exception e) {
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("open file failed, filePath ");
                stringBuilder.append(str);
                Log.e(str2, stringBuilder.toString(), e);
            } catch (Throwable th) {
                r1.addSuppressed(th);
            }
            return;
        }
        this.mGroupShotInternal.getImageAndSaveJpeg(str);
        ExifHelper.writeExifByFilePath(str, this.mOrientation, LocationManager.instance().getCurrentLocation(), this.mTimeTaken);
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        this.mStartTime = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled() {
        Log.v(TAG, "SaveOutputImageTask onCancelled");
        finishGroupShot();
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Thumbnail thumbnail) {
        Log.v(TAG, "SaveOutputImageTask onPostExecute");
        SaverCallback saverCallback = (SaverCallback) this.mSaverCallbackWeakReference.get();
        if (saverCallback != null) {
            if (thumbnail == null) {
                Log.e(TAG, "onPostExecute thumbnail is null");
                saverCallback.postHideThumbnailProgressing();
            } else {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onPostExecute thumbnail = ");
                stringBuilder.append(thumbnail);
                Log.v(str, stringBuilder.toString());
                saverCallback.postUpdateThumbnail(thumbnail, false);
            }
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("groupshot image process cost ");
            stringBuilder2.append(System.currentTimeMillis() - this.mStartTime);
            Log.v(str2, stringBuilder2.toString());
            finishGroupShot();
        }
    }

    private void finishGroupShot() {
        this.mGroupShotInternal.clearImages();
        this.mGroupShotInternal.finish();
        this.mGroupShotInternal = null;
    }
}
