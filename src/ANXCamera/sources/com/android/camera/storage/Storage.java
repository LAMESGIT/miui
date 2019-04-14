package com.android.camera.storage;

import android.app.Activity;
import android.app.usage.StorageStatsManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.FileCompat;
import com.android.camera.LocationManager;
import com.android.camera.Util;
import com.android.camera.lib.compatibility.util.CompatibilityUtils;
import com.android.camera.log.Log;
import com.android.gallery3d.exif.ExifInterface;
import com.mi.config.b;
import com.xiaomi.camera.core.PictureInfo;
import com.xiaomi.camera.parallelservice.util.ParallelUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import miui.os.Build;
import miui.reflect.Method;

public class Storage {
    public static final String AVOID_SCAN_FILE_NAME = ".nomedia";
    public static int BUCKET_ID = DIRECTORY.toLowerCase(Locale.ENGLISH).hashCode();
    private static final String CAMERA_STORAGE_PATH_SUFFIX = "/DCIM/Camera";
    private static final String CAMERA_STORAGE_PATH_TEMP = "/DCIM/Camera/temp";
    public static String CAMERA_TEMP_DIRECTORY = null;
    public static String DIRECTORY = null;
    public static String FIRST_CONSIDER_STORAGE_PATH = (b.pQ ? SECONDARY_STORAGE_PATH : PRIMARY_STORAGE_PATH);
    public static String HIDEDIRECTORY = null;
    private static final String HIDE_CAMERA_STORAGE_PATH_SUFFIX = "/DCIM/Camera/.ubifocus";
    public static final String JPEG_SUFFIX = ".jpg";
    private static final AtomicLong LEFT_SPACE = new AtomicLong(0);
    public static final long LOW_STORAGE_THRESHOLD = 52428800;
    private static final int MAX_WRITE_RETRY = (Build.IS_ALPHA_BUILD ? 1 : 3);
    protected static final String PARALLEL_PROCESS_EXIF_PROCESS_TAG = "processing";
    public static final long PREPARING = -2;
    public static int PRIMARY_BUCKET_ID = 0;
    private static final String PRIMARY_STORAGE_PATH = Environment.getExternalStorageDirectory().toString();
    public static final float QUOTA_RATIO = 0.9f;
    private static final String SAVE_TO_CLOUD_ALBUM_ACTION = "com.miui.gallery.SAVE_TO_CLOUD";
    private static final String SAVE_TO_CLOUD_ALBUM_CACHE_LOCATION_KEY = "extra_cache_location";
    private static final String SAVE_TO_CLOUD_ALBUM_FILE_LENGTH = "extra_file_length";
    private static final String SAVE_TO_CLOUD_ALBUM_PACKAGE = "com.miui.gallery";
    private static final String SAVE_TO_CLOUD_ALBUM_PATH_KAY = "extra_file_path";
    private static final String SAVE_TO_CLOUD_ALBUM_STORE_ID_KAY = "extra_media_store_id";
    private static final String SAVE_TO_CLOUD_ALBUM_TEMP_FILE_KAY = "extra_is_temp_file";
    public static int SECONDARY_BUCKET_ID = 0;
    private static String SECONDARY_STORAGE_PATH = System.getenv("SECONDARY_STORAGE");
    private static final String TAG = Storage.class.getSimpleName();
    public static final String UBIFOCUS_SUFFIX = "_UBIFOCUS_";
    public static final long UNAVAILABLE = -1;
    public static final long UNKNOWN_SIZE = -3;
    private static String sCurrentStoragePath = FIRST_CONSIDER_STORAGE_PATH;
    private static long sQuotaBytes;
    private static boolean sQuotaSupported;
    private static long sReserveBytes;
    private static WeakReference<StorageListener> sStorageListener;

    public interface StorageListener {
        void onStoragePathChanged();
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FIRST_CONSIDER_STORAGE_PATH);
        stringBuilder.append(CAMERA_STORAGE_PATH_SUFFIX);
        DIRECTORY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(FIRST_CONSIDER_STORAGE_PATH);
        stringBuilder.append(HIDE_CAMERA_STORAGE_PATH_SUFFIX);
        HIDEDIRECTORY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(FIRST_CONSIDER_STORAGE_PATH);
        stringBuilder.append(CAMERA_STORAGE_PATH_TEMP);
        CAMERA_TEMP_DIRECTORY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(PRIMARY_STORAGE_PATH);
        stringBuilder.append(CAMERA_STORAGE_PATH_SUFFIX);
        PRIMARY_BUCKET_ID = stringBuilder.toString().toLowerCase(Locale.ENGLISH).hashCode();
        stringBuilder = new StringBuilder();
        stringBuilder.append(SECONDARY_STORAGE_PATH);
        stringBuilder.append(CAMERA_STORAGE_PATH_SUFFIX);
        SECONDARY_BUCKET_ID = stringBuilder.toString().toLowerCase(Locale.ENGLISH).hashCode();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(DIRECTORY);
        stringBuilder2.append(File.separator);
        stringBuilder2.append(AVOID_SCAN_FILE_NAME);
        File file = new File(stringBuilder2.toString());
        if (file.exists()) {
            file.delete();
        }
    }

    public static void initStorage(Context context) {
        initQuota(context);
        if (b.gz()) {
            FileCompat.updateSDPath();
            String sdcardPath = CompatibilityUtils.getSdcardPath(context);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("initStorage sd=");
            stringBuilder.append(sdcardPath);
            Log.v(str, stringBuilder.toString());
            if (sdcardPath != null) {
                SECONDARY_STORAGE_PATH = sdcardPath;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(SECONDARY_STORAGE_PATH);
                stringBuilder2.append(CAMERA_STORAGE_PATH_SUFFIX);
                SECONDARY_BUCKET_ID = stringBuilder2.toString().toLowerCase(Locale.ENGLISH).hashCode();
            } else {
                SECONDARY_STORAGE_PATH = null;
            }
            readSystemPriorityStorage();
        }
    }

    private static void initQuota(Context context) {
        if (VERSION.SDK_INT >= 26) {
            StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService(StorageStatsManager.class);
            Class[] clsArr = new Class[]{StorageStatsManager.class};
            Method method = Util.getMethod(clsArr, "isQuotaSupported", "(Ljava/util/UUID;)Z");
            if (method != null) {
                sQuotaSupported = method.invokeBoolean(clsArr[0], storageStatsManager, new Object[]{StorageManager.UUID_DEFAULT});
                if (sQuotaSupported) {
                    long totalBytes = new StatFs(PRIMARY_STORAGE_PATH).getTotalBytes();
                    sQuotaBytes = (long) (0.9f * ((float) totalBytes));
                    sReserveBytes = totalBytes - sQuotaBytes;
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("quota: ");
                    stringBuilder.append(sQuotaBytes);
                    stringBuilder.append("|");
                    stringBuilder.append(sReserveBytes);
                    Log.d(str, stringBuilder.toString());
                }
            }
        }
    }

    public static boolean isQuotaSupported() {
        return sQuotaSupported && sQuotaBytes > 0;
    }

    public static Uri addImageForEffect(Activity activity, String str, long j, Location location, int i, byte[] bArr, int i2, int i3, boolean z, boolean z2, String str2, PictureInfo pictureInfo) {
        return addImage(activity, str, j, location, i, bArr, i2, i3, z, false, false, false, z2, str2, pictureInfo);
    }

    public static Uri addImageForSnapCamera(Context context, String str, long j, Location location, int i, byte[] bArr, int i2, int i3, boolean z, boolean z2, boolean z3, String str2, PictureInfo pictureInfo) {
        return addImage(context, str, j, location, i, bArr, i2, i3, z, z2, z3, false, false, str2, pictureInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:145:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x015f A:{SYNTHETIC, EDGE_INSN: B:187:0x015f->B:109:0x015f ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x015c A:{SYNTHETIC, Splitter:B:107:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x015c A:{SYNTHETIC, Splitter:B:107:0x015c} */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x015f A:{SYNTHETIC, EDGE_INSN: B:187:0x015f->B:109:0x015f ?: BREAK  } */
    public static android.net.Uri addImage(android.content.Context r24, java.lang.String r25, long r26, android.location.Location r28, int r29, byte[] r30, int r31, int r32, boolean r33, boolean r34, boolean r35, boolean r36, boolean r37, java.lang.String r38, com.xiaomi.camera.core.PictureInfo r39) {
        /*
        r15 = r24;
        r1 = r25;
        r14 = r28;
        r9 = r29;
        r10 = r34;
        r11 = r35;
        r0 = TAG;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "addImage: parallel=";
        r2.append(r3);
        r13 = r37;
        r2.append(r13);
        r3 = " appendExif=";
        r2.append(r3);
        r12 = r36;
        r2.append(r12);
        r2 = r2.toString();
        com.android.camera.log.Log.d(r0, r2);
        r2 = r30;
        r3 = r13;
        r4 = r38;
        r5 = r39;
        r6 = r9;
        r7 = r31;
        r8 = r32;
        r8 = updateExif(r2, r3, r4, r5, r6, r7, r8);
        r2 = generateFilepath(r1, r10, r11);
        r0 = TAG;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "addImage: path=";
        r3.append(r4);
        r3.append(r2);
        r3 = r3.toString();
        com.android.camera.log.Log.d(r0, r3);
        r3 = 0;
    L_0x005a:
        r4 = 1;
        r5 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0171 }
        r0 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0171 }
        r0.<init>(r8);	 Catch:{ Exception -> 0x0171 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x0171 }
        r0 = isUseDocumentMode();	 Catch:{ Throwable -> 0x0165, all -> 0x0160 }
        if (r0 == 0) goto L_0x0078;
    L_0x006c:
        r0 = com.android.camera.FileCompat.getFileOutputStream(r2, r4);	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x0070:
        r4 = r0;
        goto L_0x007e;
    L_0x0072:
        r0 = move-exception;
        goto L_0x0163;
    L_0x0075:
        r0 = move-exception;
        goto L_0x0168;
    L_0x0078:
        r0 = new java.io.FileOutputStream;	 Catch:{ Throwable -> 0x0165, all -> 0x0160 }
        r0.<init>(r2);	 Catch:{ Throwable -> 0x0165, all -> 0x0160 }
        goto L_0x0070;
        r6 = new java.io.BufferedOutputStream;	 Catch:{ Throwable -> 0x0152, all -> 0x014d }
        r6.<init>(r4);	 Catch:{ Throwable -> 0x0152, all -> 0x014d }
        if (r33 == 0) goto L_0x00da;
    L_0x0087:
        r0 = r9 % 180;
        if (r0 != 0) goto L_0x008d;
    L_0x008b:
        r0 = 1;
        goto L_0x008f;
        r0 = 0;
    L_0x008f:
        r7 = r0 ^ 1;
        r7 = flipJpeg(r8, r0, r7);	 Catch:{ Throwable -> 0x00d4, all -> 0x00ce }
        if (r7 == 0) goto L_0x00bb;
    L_0x0097:
        r21 = r12;
        r12 = com.android.camera.Util.getExif(r8);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r13 = r12.getThumbnailBytes();	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        if (r13 == 0) goto L_0x00b4;
    L_0x00a3:
        r10 = r0 ^ 1;
        r0 = flipJpeg(r13, r0, r10);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        if (r0 == 0) goto L_0x00b1;
    L_0x00ab:
        r12.setCompressedThumbnail(r0);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r0.recycle();	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r21 = 0;
    L_0x00b4:
        r12.writeExif(r7, r6);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r7.recycle();	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        goto L_0x00cd;
    L_0x00bb:
        r21 = r12;
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
    L_0x00c1:
        r7 = r5.read(r0);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r10 = -1;
        if (r7 == r10) goto L_0x00cd;
    L_0x00c8:
        r10 = 0;
        r6.write(r0, r10, r7);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        goto L_0x00c1;
    L_0x00cd:
        goto L_0x00ec;
    L_0x00ce:
        r0 = move-exception;
        r21 = r12;
    L_0x00d1:
        r7 = 0;
        goto L_0x0142;
    L_0x00d4:
        r0 = move-exception;
        r21 = r12;
    L_0x00d7:
        r7 = r0;
        goto L_0x013e;
    L_0x00da:
        r21 = r12;
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
    L_0x00e0:
        r7 = r5.read(r0);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        r10 = -1;
        if (r7 == r10) goto L_0x00ec;
    L_0x00e7:
        r12 = 0;
        r6.write(r0, r12, r7);	 Catch:{ Throwable -> 0x013c, all -> 0x0138 }
        goto L_0x00e0;
    L_0x00ec:
        r12 = r21;
        if (r12 == 0) goto L_0x010e;
    L_0x00f0:
        r6.flush();	 Catch:{ Throwable -> 0x0107, all -> 0x0103 }
        r22 = r12;
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        com.android.camera.ExifHelper.writeExifByFilePath(r2, r9, r14, r12);	 Catch:{ Throwable -> 0x0101, all -> 0x00fd }
        goto L_0x0110;
    L_0x00fd:
        r0 = move-exception;
        r12 = r22;
        goto L_0x00d1;
    L_0x0101:
        r0 = move-exception;
        goto L_0x010a;
    L_0x0103:
        r0 = move-exception;
        r22 = r12;
        goto L_0x00d1;
    L_0x0107:
        r0 = move-exception;
        r22 = r12;
    L_0x010a:
        r7 = r0;
        r21 = r22;
        goto L_0x013e;
    L_0x010e:
        r22 = r12;
    L_0x0110:
        r7 = 0;
        $closeResource(r7, r6);	 Catch:{ Throwable -> 0x0133, all -> 0x012f }
        if (r4 == 0) goto L_0x0124;
    L_0x0116:
        $closeResource(r7, r4);	 Catch:{ Throwable -> 0x011f, all -> 0x011a }
        goto L_0x0124;
    L_0x011a:
        r0 = move-exception;
        r6 = r7;
        r12 = r22;
        goto L_0x016b;
    L_0x011f:
        r0 = move-exception;
        r6 = r0;
        r12 = r22;
        goto L_0x0169;
    L_0x0124:
        $closeResource(r7, r5);	 Catch:{ Exception -> 0x012b }
        r0 = 0;
        r4 = 1;
        goto L_0x01b3;
    L_0x012b:
        r0 = move-exception;
        r12 = r22;
        goto L_0x0174;
    L_0x012f:
        r0 = move-exception;
        r12 = r22;
        goto L_0x0150;
    L_0x0133:
        r0 = move-exception;
        r6 = r0;
        r21 = r22;
        goto L_0x0156;
    L_0x0138:
        r0 = move-exception;
        r12 = r21;
        goto L_0x00d1;
    L_0x013c:
        r0 = move-exception;
        goto L_0x00d7;
    L_0x013e:
        throw r7;	 Catch:{ all -> 0x013f }
    L_0x013f:
        r0 = move-exception;
        r12 = r21;
    L_0x0142:
        $closeResource(r7, r6);	 Catch:{ Throwable -> 0x0148, all -> 0x0146 }
        throw r0;	 Catch:{ Throwable -> 0x0148, all -> 0x0146 }
    L_0x0146:
        r0 = move-exception;
        goto L_0x0150;
    L_0x0148:
        r0 = move-exception;
        r6 = r0;
        r21 = r12;
        goto L_0x0156;
    L_0x014d:
        r0 = move-exception;
        r21 = r12;
    L_0x0150:
        r6 = 0;
        goto L_0x015a;
    L_0x0152:
        r0 = move-exception;
        r21 = r12;
        r6 = r0;
    L_0x0156:
        throw r6;	 Catch:{ all -> 0x0157 }
    L_0x0157:
        r0 = move-exception;
        r12 = r21;
    L_0x015a:
        if (r4 == 0) goto L_0x015f;
    L_0x015c:
        $closeResource(r6, r4);	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x015f:
        throw r0;	 Catch:{ Throwable -> 0x0075, all -> 0x0072 }
    L_0x0160:
        r0 = move-exception;
        r21 = r12;
    L_0x0163:
        r6 = 0;
        goto L_0x016b;
    L_0x0165:
        r0 = move-exception;
        r21 = r12;
    L_0x0168:
        r6 = r0;
    L_0x0169:
        throw r6;	 Catch:{ all -> 0x016a }
    L_0x016a:
        r0 = move-exception;
    L_0x016b:
        $closeResource(r6, r5);	 Catch:{ Exception -> 0x016f }
        throw r0;	 Catch:{ Exception -> 0x016f }
    L_0x016f:
        r0 = move-exception;
        goto L_0x0174;
    L_0x0171:
        r0 = move-exception;
        r21 = r12;
    L_0x0174:
        dumpExceptionEnv(r0, r2);
        r4 = TAG;
        r5 = "Failed to write image";
        com.android.camera.log.Log.e(r4, r5, r0);
        r4 = 1;
        r3 = r3 + r4;
        r0 = com.android.camera.Util.isQuotaExceeded(r0);
        if (r0 == 0) goto L_0x01a0;
    L_0x0187:
        r0 = r15 instanceof com.android.camera.ActivityBase;
        if (r0 == 0) goto L_0x01a0;
    L_0x018b:
        r0 = r15;
        r0 = (com.android.camera.ActivityBase) r0;
        r3 = r0.isActivityPaused();
        if (r3 != 0) goto L_0x019c;
    L_0x0194:
        r3 = new com.android.camera.storage.Storage$1;
        r3.<init>(r15);
        r0.runOnUiThread(r3);
    L_0x019c:
        r0 = MAX_WRITE_RETRY;
        r3 = r0;
        goto L_0x01ae;
    L_0x01a0:
        r0 = MAX_WRITE_RETRY;
        if (r3 >= r0) goto L_0x01ae;
    L_0x01a4:
        java.lang.System.gc();
        r5 = 50;
        java.lang.Thread.sleep(r5);	 Catch:{ InterruptedException -> 0x01ad }
        goto L_0x01ae;
    L_0x01ad:
        r0 = move-exception;
    L_0x01ae:
        r0 = MAX_WRITE_RETRY;
        if (r3 < r0) goto L_0x02b1;
    L_0x01b2:
        r0 = r4;
    L_0x01b3:
        if (r0 == 0) goto L_0x01b7;
    L_0x01b5:
        r1 = 0;
        return r1;
    L_0x01b7:
        if (r11 == 0) goto L_0x023c;
    L_0x01b9:
        r0 = com.android.camera.Util.isProduceFocusInfoSuccess(r8);
        r13 = r31;
        r12 = r32;
        r2 = com.android.camera.Util.getCenterFocusDepthIndex(r8, r13, r12);
        if (r0 == 0) goto L_0x01ce;
    L_0x01c7:
        r3 = "_";
    L_0x01c9:
        r3 = r1.lastIndexOf(r3);
        goto L_0x01d1;
    L_0x01ce:
        r3 = "_UBIFOCUS_";
        goto L_0x01c9;
    L_0x01d1:
        r5 = 0;
        r1 = r1.substring(r5, r3);
        r3 = generateFilepath(r1, r5, r5);
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r1);
        if (r0 == 0) goto L_0x01e7;
    L_0x01e4:
        r6 = "_";
        goto L_0x01e9;
    L_0x01e7:
        r6 = "_UBIFOCUS_";
    L_0x01e9:
        r5.append(r6);
        r5.append(r2);
        r2 = r5.toString();
        r5 = r34;
        r7 = 0;
        r2 = generateFilepath(r2, r5, r7);
        if (r3 == 0) goto L_0x020c;
    L_0x01fc:
        if (r2 == 0) goto L_0x020c;
    L_0x01fe:
        r6 = new java.io.File;
        r6.<init>(r2);
        r2 = new java.io.File;
        r2.<init>(r3);
        r6.renameTo(r2);
        goto L_0x0234;
    L_0x020c:
        r6 = TAG;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r4 = "oldPath: ";
        r10.append(r4);
        if (r2 != 0) goto L_0x021c;
    L_0x021a:
        r2 = "null";
    L_0x021c:
        r10.append(r2);
        r2 = " newPath: ";
        r10.append(r2);
        if (r3 != 0) goto L_0x0229;
    L_0x0226:
        r2 = "null";
        goto L_0x022a;
    L_0x0229:
        r2 = r3;
    L_0x022a:
        r10.append(r2);
        r2 = r10.toString();
        com.android.camera.log.Log.e(r6, r2);
    L_0x0234:
        if (r0 != 0) goto L_0x0239;
    L_0x0236:
        deleteImage(r1);
    L_0x0239:
        r2 = r1;
        r0 = r3;
        goto L_0x0245;
    L_0x023c:
        r13 = r31;
        r12 = r32;
        r5 = r34;
        r7 = 0;
        r0 = r2;
        r2 = r1;
    L_0x0245:
        if (r5 == 0) goto L_0x024b;
    L_0x0247:
        if (r11 != 0) goto L_0x024b;
    L_0x0249:
        r6 = 0;
        return r6;
    L_0x024b:
        r6 = 0;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r2);
        r3 = ".jpg";
        r1.append(r3);
        r3 = r1.toString();
        r10 = "image/jpeg";
        r1 = new java.io.File;
        r1.<init>(r0);
        r16 = r1.length();
        r1 = r15;
        r18 = 1;
        r4 = r26;
        r19 = r6;
        r6 = r10;
        r20 = r7;
        r7 = r9;
        r11 = r8;
        r8 = r0;
        r9 = r16;
        r15 = r11;
        r11 = r13;
        r13 = r14;
        r16 = r14;
        r14 = r37;
        r9 = insertToMediaStore(r1, r2, r3, r4, r6, r7, r8, r9, r11, r12, r13, r14);
        if (r9 != 0) goto L_0x029b;
    L_0x0284:
        r1 = TAG;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "failed to insert to DB: ";
        r2.append(r3);
        r2.append(r0);
        r0 = r2.toString();
        com.android.camera.log.Log.e(r1, r0);
        return r19;
    L_0x029b:
        r1 = r15.length;
        r3 = (long) r1;
        r6 = android.content.ContentUris.parseId(r9);
        if (r16 != 0) goto L_0x02a6;
    L_0x02a3:
        r8 = r18;
        goto L_0x02a8;
    L_0x02a6:
        r8 = r20;
    L_0x02a8:
        r1 = r24;
        r2 = r0;
        r5 = r37;
        saveToCloudAlbum(r1, r2, r3, r5, r6, r8);
        return r9;
    L_0x02b1:
        r13 = r31;
        r15 = r24;
        r13 = r37;
        r10 = r34;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.storage.Storage.addImage(android.content.Context, java.lang.String, long, android.location.Location, int, byte[], int, int, boolean, boolean, boolean, boolean, boolean, java.lang.String, com.xiaomi.camera.core.PictureInfo):android.net.Uri");
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

    private static void dumpExceptionEnv(Exception exception, String str) {
        if (exception instanceof FileNotFoundException) {
            boolean createNewFile;
            long maxMemory = Runtime.getRuntime().maxMemory();
            long totalMemory = Runtime.getRuntime().totalMemory();
            long freeMemory = Runtime.getRuntime().freeMemory();
            File file = new File(str);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(".ex");
                File file2 = new File(stringBuilder.toString());
                createNewFile = file2.createNewFile();
                file2.delete();
            } catch (IOException e) {
                createNewFile = false;
            }
            String str2 = TAG;
            Locale locale = Locale.ENGLISH;
            String str3 = "Failed to write image, memory state(max:%d, total:%d, free:%d), file state(%s;%s;%s;%s)";
            Object[] objArr = new Object[7];
            objArr[0] = Long.valueOf(maxMemory);
            objArr[1] = Long.valueOf(totalMemory);
            objArr[2] = Long.valueOf(freeMemory);
            objArr[3] = file.exists() ? "exists" : "not exists";
            objArr[4] = file.isDirectory() ? "isDirectory" : "isNotDirectory";
            objArr[5] = file.canWrite() ? "canWrite" : "canNotWrite";
            objArr[6] = createNewFile ? "testFileCanWrite" : "testFileCannotWrite";
            Log.e(str2, String.format(locale, str3, objArr), exception);
        }
    }

    private static byte[] updateExif(byte[] bArr, boolean z, String str, PictureInfo pictureInfo, int i, int i2, int i3) {
        if (!z && TextUtils.isEmpty(str) && pictureInfo == null) {
            return bArr;
        }
        String str2;
        StringBuilder stringBuilder;
        long currentTimeMillis = System.currentTimeMillis();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.readExif(bArr);
            if (z) {
                exifInterface.addParallelProcessComment("processing", i, i2, i3);
            }
            if (!TextUtils.isEmpty(str)) {
                str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("save algorithm: ");
                stringBuilder2.append(str);
                Log.d(str2, stringBuilder2.toString());
                exifInterface.addAlgorithmComment(str);
            }
            if (pictureInfo != null) {
                str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("save xiaomi comment: ");
                stringBuilder.append(pictureInfo.getInfoString());
                stringBuilder.append(", aiType = ");
                stringBuilder.append(pictureInfo.getAiType());
                Log.d(str2, stringBuilder.toString());
                exifInterface.addAiType(pictureInfo.getAiType());
                if (pictureInfo.isBokehFrontCamera()) {
                    exifInterface.addFrontMirror(pictureInfo.isFrontMirror());
                }
                exifInterface.addXiaomiComment(pictureInfo.getInfoString());
            }
            exifInterface.writeExif(bArr, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            bArr = toByteArray;
        } catch (IOException e) {
            Log.e(ExifInterface.class.getName(), e.getMessage(), e);
        }
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("update exif cost=");
        stringBuilder.append(System.currentTimeMillis() - currentTimeMillis);
        Log.v(str2, stringBuilder.toString());
        return bArr;
    }

    public static Uri addImageForGroupOrPanorama(Context context, String str, int i, long j, Location location, int i2, int i3) {
        Context context2 = context;
        String str2 = str;
        if (context2 == null || str2 == null) {
            return null;
        }
        File file;
        try {
            file = new File(str2);
        } catch (Exception e) {
            String str3 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to open panorama file: ");
            stringBuilder.append(e.getMessage());
            Log.e(str3, stringBuilder.toString(), e);
            file = null;
        }
        if (file == null || !file.exists()) {
            return null;
        }
        String name = file.getName();
        String str4 = str2;
        Uri insertToMediaStore = insertToMediaStore(context2, name, name, j, "image/jpeg", i, str2, file.length(), i2, i3, location, false);
        saveToCloudAlbum(context, str4, -1, location == null);
        return insertToMediaStore;
    }

    private static Uri insertToMediaStore(Context context, String str, String str2, long j, String str3, int i, String str4, long j2, int i2, int i3, Location location, boolean z) {
        Exception e;
        StringBuilder stringBuilder;
        ContentValues contentValues = new ContentValues(11);
        contentValues.put("title", str);
        contentValues.put("_display_name", str2);
        contentValues.put("datetaken", Long.valueOf(j));
        contentValues.put("mime_type", str3);
        contentValues.put("orientation", Integer.valueOf(i));
        contentValues.put("_data", str4);
        contentValues.put("_size", Long.valueOf(j2));
        contentValues.put("width", Integer.valueOf(i2));
        contentValues.put("height", Integer.valueOf(i3));
        if (location != null) {
            contentValues.put("latitude", Double.valueOf(location.getLatitude()));
            contentValues.put("longitude", Double.valueOf(location.getLongitude()));
        }
        Uri insert;
        if (z) {
            insert = context.getContentResolver().insert(Files.getContentUri("external"), contentValues);
            try {
                ParallelUtil.insertImageToParallelService(context, ContentUris.parseId(insert), str4);
                String str5 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("algo insertUri: ");
                stringBuilder2.append(insert.toString());
                Log.d(str5, stringBuilder2.toString());
                return insert;
            } catch (Exception e2) {
                e = e2;
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to write MediaStore:");
                stringBuilder.append(e.getMessage());
                Log.e(str, stringBuilder.toString(), e);
                return insert;
            }
        }
        try {
            return context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (Exception e3) {
            e = e3;
            insert = null;
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to write MediaStore:");
            stringBuilder.append(e.getMessage());
            Log.e(str, stringBuilder.toString(), e);
            return insert;
        }
    }

    public static void deleteImage(String str) {
        File file = new File(HIDEDIRECTORY);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().indexOf(str) != -1) {
                    file2.delete();
                }
            }
        }
    }

    public static Uri newImage(Context context, String str, long j, int i, int i2, int i3) {
        str = generateFilepath(str);
        ContentValues contentValues = new ContentValues(6);
        contentValues.put("datetaken", Long.valueOf(j));
        contentValues.put("orientation", Integer.valueOf(i));
        contentValues.put("_data", str);
        contentValues.put("width", Integer.valueOf(i2));
        contentValues.put("height", Integer.valueOf(i3));
        contentValues.put("mime_type", "image/jpeg");
        try {
            return context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (Exception e) {
            str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to new image");
            stringBuilder.append(e);
            Log.e(str, stringBuilder.toString());
            return null;
        }
    }

    public static boolean updateImageWithExtraExif(Context context, byte[] bArr, ExifInterface exifInterface, Uri uri, String str, Location location, int i, int i2, int i3, String str2, boolean z, boolean z2, String str3, PictureInfo pictureInfo) {
        return updateImage(context, updateExif(bArr, z2, str3, pictureInfo, i, i2, i3), exifInterface, uri, str, location, i, i2, i3, str2);
    }

    public static boolean updateImage(Context context, byte[] bArr, ExifInterface exifInterface, Uri uri, String str, Location location, int i, int i2, int i3, String str2) {
        OutputStream outputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = bArr;
        ExifInterface exifInterface2 = exifInterface;
        String str3 = str;
        String str4 = str2;
        String generateFilepath = generateFilepath(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str4 != null ? generateFilepath(str2) : generateFilepath);
        stringBuilder.append(".tmp");
        String stringBuilder2 = stringBuilder.toString();
        File file = new File(stringBuilder2);
        boolean z = false;
        if (bArr2 != null) {
            BufferedInputStream bufferedInputStream;
            try {
                OutputStream fileOutputStream;
                bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bArr2));
                if (isUseDocumentMode()) {
                    fileOutputStream = FileCompat.getFileOutputStream(stringBuilder2, true);
                } else {
                    fileOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                }
                outputStream = fileOutputStream;
                if (exifInterface2 == null) {
                    byte[] bArr3 = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr3);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(bArr3, 0, read);
                    }
                } else {
                    try {
                        exifInterface2.writeExif(bArr2, outputStream);
                    } catch (IOException e) {
                        try {
                            Log.e(TAG, "Failed to rewrite Exif");
                            outputStream.write(bArr2);
                        } catch (Throwable th3) {
                            th2 = th3;
                        }
                    }
                }
                if (outputStream != null) {
                    $closeResource(null, outputStream);
                }
                $closeResource(null, bufferedInputStream);
            } catch (Exception e2) {
                Log.e(TAG, "Failed to write image", e2);
                return false;
            } catch (Throwable th4) {
                $closeResource(th2, bufferedInputStream);
            }
        } else if (str4 != null) {
            stringBuilder2 = generateFilepath(str2);
        }
        long length = file.length();
        if (isUseDocumentMode()) {
            try {
                FileCompat.renameFile(stringBuilder2, generateFilepath);
            } catch (IOException e3) {
            }
        } else {
            boolean renameTo = file.renameTo(new File(generateFilepath));
            if (!(exifInterface2 == null || str4 == null)) {
                try {
                    new File(generateFilepath(str2)).delete();
                } catch (Exception e22) {
                    String str5 = TAG;
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Exception when delete old file ");
                    stringBuilder3.append(str4);
                    Log.e(str5, stringBuilder3.toString(), e22);
                }
            }
            if (!renameTo) {
                String str6 = TAG;
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("renameTo failed, tmpPath = ");
                stringBuilder4.append(stringBuilder2);
                Log.w(str6, stringBuilder4.toString());
                return false;
            }
        }
        ContentValues contentValues = new ContentValues(10);
        contentValues.put("title", str3);
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str3);
        stringBuilder5.append(JPEG_SUFFIX);
        contentValues.put("_display_name", stringBuilder5.toString());
        if (bArr2 != null) {
            contentValues.put("mime_type", "image/jpeg");
            contentValues.put("orientation", Integer.valueOf(i));
            contentValues.put("_size", Long.valueOf(length));
            contentValues.put("width", Integer.valueOf(i2));
            contentValues.put("height", Integer.valueOf(i3));
            if (location != null) {
                contentValues.put("latitude", Double.valueOf(location.getLatitude()));
                contentValues.put("longitude", Double.valueOf(location.getLongitude()));
            }
            contentValues.put("_data", generateFilepath);
        } else if (str4 != null) {
            contentValues.put("_data", generateFilepath);
        }
        context.getContentResolver().update(uri, contentValues, null, null);
        long length2 = (long) bArr2.length;
        if (location == null) {
            z = true;
        }
        saveToCloudAlbum(context, generateFilepath, length2, z);
        return true;
        if (outputStream != null) {
            $closeResource(th, outputStream);
        }
        throw th2;
    }

    public static void addDNGToDataBase(Activity activity, String str) {
        String generateFilepath = generateFilepath(str, ".dng");
        ContentValues contentValues = new ContentValues(4);
        contentValues.put("title", str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(".dng");
        contentValues.put("_display_name", stringBuilder.toString());
        contentValues.put("media_type", Integer.valueOf(1));
        contentValues.put("_data", generateFilepath);
        try {
            activity.getContentResolver().insert(Files.getContentUri("external"), contentValues);
        } catch (Exception e) {
            str = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to write MediaStore");
            stringBuilder2.append(e);
            Log.e(str, stringBuilder2.toString());
        }
    }

    public static void saveToCloudAlbum(Context context, String str, long j, boolean z) {
        saveToCloudAlbum(context, str, j, false, -1, z);
    }

    public static void saveToCloudAlbum(Context context, String str, long j, boolean z, long j2, boolean z2) {
        context.sendBroadcast(getSaveToCloudIntent(context, str, j, z, j2, z2));
    }

    private static Intent getSaveToCloudIntent(Context context, String str, long j, boolean z, long j2, boolean z2) {
        Intent intent = new Intent(SAVE_TO_CLOUD_ALBUM_ACTION);
        intent.setPackage("com.miui.gallery");
        List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
            intent.setComponent(new ComponentName("com.miui.gallery", ((ResolveInfo) queryBroadcastReceivers.get(0)).activityInfo.name));
        }
        intent.putExtra(SAVE_TO_CLOUD_ALBUM_PATH_KAY, str);
        intent.putExtra(SAVE_TO_CLOUD_ALBUM_FILE_LENGTH, j);
        if (z) {
            intent.putExtra(SAVE_TO_CLOUD_ALBUM_TEMP_FILE_KAY, true);
            intent.putExtra(SAVE_TO_CLOUD_ALBUM_STORE_ID_KAY, j2);
        } else {
            intent.putExtra(SAVE_TO_CLOUD_ALBUM_TEMP_FILE_KAY, false);
        }
        if (z2) {
            intent.putExtra(SAVE_TO_CLOUD_ALBUM_CACHE_LOCATION_KEY, LocationManager.instance().getLastKnownLocation());
            Log.d(TAG, "broadcast last location to gallery");
        }
        return intent;
    }

    public static Bitmap flipJpeg(byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            return null;
        }
        Options options = new Options();
        options.inPurgeable = true;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Matrix matrix = new Matrix();
        float f = 1.0f;
        float f2 = z ? -1.0f : 1.0f;
        if (z2) {
            f = -1.0f;
        }
        matrix.setScale(f2, f, ((float) decodeByteArray.getWidth()) * 0.5f, ((float) decodeByteArray.getHeight()) * 0.5f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
            if (createBitmap != decodeByteArray) {
                decodeByteArray.recycle();
            }
            if (createBitmap.getWidth() == -1 || createBitmap.getHeight() == -1) {
                return null;
            }
            return createBitmap;
        } catch (Exception e) {
            Log.w(TAG, "Failed to rotate thumbnail", e);
            return null;
        }
    }

    public static String generatePrimaryDirectoryPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRIMARY_STORAGE_PATH);
        stringBuilder.append(CAMERA_STORAGE_PATH_SUFFIX);
        return stringBuilder.toString();
    }

    public static String generatePrimaryFilepath(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generatePrimaryDirectoryPath());
        stringBuilder.append('/');
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static String generatePrimaryTempFile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRIMARY_STORAGE_PATH);
        stringBuilder.append(CAMERA_STORAGE_PATH_TEMP);
        return stringBuilder.toString();
    }

    public static String generateFilepath(String str) {
        return generateFilepath(str, JPEG_SUFFIX);
    }

    public static String generateFilepath(String str, boolean z, boolean z2) {
        if (z && isLowStorageSpace(HIDEDIRECTORY)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(z ? HIDEDIRECTORY : DIRECTORY);
        stringBuilder.append('/');
        stringBuilder.append(str);
        stringBuilder.append(z2 ? ".y" : JPEG_SUFFIX);
        return stringBuilder.toString();
    }

    public static String generateFilepath(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DIRECTORY);
        stringBuilder.append('/');
        stringBuilder.append(str);
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public static long getAvailableSpace(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getAvailableSpace path is empty");
            return -1;
        }
        boolean mkdirs;
        File file = new File(str);
        if (isUseDocumentMode()) {
            mkdirs = FileCompat.mkdirs(str);
        } else {
            mkdirs = Util.mkdirs(file, 511, -1, -1);
        }
        if (file.exists() && file.isDirectory()) {
            StringBuilder stringBuilder;
            if (mkdirs && str.endsWith(CAMERA_STORAGE_PATH_SUFFIX)) {
                String str2;
                if ((MediaProviderUtil.insertCameraDirectory(CameraAppImpl.getAndroidContext(), str) != null ? 1 : null) != null) {
                    str2 = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("insertDirectory success, path ");
                    stringBuilder.append(str);
                    Log.d(str2, stringBuilder.toString());
                } else {
                    str2 = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("insertDirectory fail, path ");
                    stringBuilder.append(str);
                    Log.w(str2, stringBuilder.toString());
                }
            }
            try {
                if (HIDEDIRECTORY.equals(str)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(HIDEDIRECTORY);
                    stringBuilder.append(File.separator);
                    stringBuilder.append(AVOID_SCAN_FILE_NAME);
                    Util.createFile(new File(stringBuilder.toString()));
                }
                long availableBytes = new StatFs(str).getAvailableBytes();
                if (DIRECTORY.equals(str)) {
                    if (isUsePhoneStorage() && isQuotaSupported() && availableBytes < sReserveBytes) {
                        Context androidContext = CameraAppImpl.getAndroidContext();
                        ApplicationInfo applicationInfo = androidContext.getApplicationInfo();
                        try {
                            long totalBytes = sQuotaBytes - ((StorageStatsManager) androidContext.getSystemService(StorageStatsManager.class)).queryExternalStatsForUser(applicationInfo.storageUuid, UserHandle.getUserHandleForUid(applicationInfo.uid)).getTotalBytes();
                            availableBytes = 0;
                            if (totalBytes >= 0) {
                                availableBytes = totalBytes;
                            }
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                    }
                    setLeftSpace(availableBytes);
                }
                return availableBytes;
            } catch (Exception e2) {
                Log.i(TAG, "Fail to access external storage", e2);
                return -3;
            }
        }
        String str3 = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("getAvailableSpace path = ");
        stringBuilder2.append(str);
        stringBuilder2.append(", exists = ");
        stringBuilder2.append(file.exists());
        stringBuilder2.append(", isDirectory = ");
        stringBuilder2.append(file.isDirectory());
        stringBuilder2.append(", canWrite = ");
        stringBuilder2.append(file.canWrite());
        Log.w(str3, stringBuilder2.toString());
        return -1;
    }

    public static long getAvailableSpace() {
        return getAvailableSpace(DIRECTORY);
    }

    public static boolean updateImageSize(ContentResolver contentResolver, Uri uri, long j) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("_size", Long.valueOf(j));
        try {
            contentResolver.update(uri, contentValues, null, null);
            return true;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to updateMediaStore");
            stringBuilder.append(e);
            Log.e(str, stringBuilder.toString());
            return false;
        }
    }

    public static boolean isLowStorageSpace(String str) {
        return getAvailableSpace(str) < LOW_STORAGE_THRESHOLD;
    }

    public static boolean hasSecondaryStorage() {
        boolean z = false;
        if (VERSION.SDK_INT == 28) {
            if (UserHandle.myUserId() == 0 && b.gz() && SECONDARY_STORAGE_PATH != null) {
                z = true;
            }
            return z;
        }
        if (b.gz() && SECONDARY_STORAGE_PATH != null) {
            z = true;
        }
        return z;
    }

    public static boolean secondaryStorageMounted() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SECONDARY_STORAGE_PATH);
        stringBuilder.append(File.separator);
        stringBuilder.append(Environment.DIRECTORY_DCIM);
        return hasSecondaryStorage() && getAvailableSpace(stringBuilder.toString()) > 0;
    }

    public static boolean isCurrentStorageIsSecondary() {
        return SECONDARY_STORAGE_PATH != null && SECONDARY_STORAGE_PATH.equals(sCurrentStoragePath);
    }

    public static void switchStoragePathIfNeeded() {
        if (hasSecondaryStorage()) {
            String str = FIRST_CONSIDER_STORAGE_PATH;
            String str2 = SECONDARY_STORAGE_PATH;
            if (FIRST_CONSIDER_STORAGE_PATH.equals(SECONDARY_STORAGE_PATH)) {
                str2 = PRIMARY_STORAGE_PATH;
            }
            String str3 = sCurrentStoragePath;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(CAMERA_STORAGE_PATH_SUFFIX);
            if (isLowStorageSpace(stringBuilder.toString())) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(CAMERA_STORAGE_PATH_SUFFIX);
                if (!isLowStorageSpace(stringBuilder2.toString())) {
                    sCurrentStoragePath = str2;
                } else {
                    return;
                }
            }
            sCurrentStoragePath = str;
            if (!sCurrentStoragePath.equals(str3)) {
                updateDirectory();
                if (!(sStorageListener == null || sStorageListener.get() == null)) {
                    ((StorageListener) sStorageListener.get()).onStoragePathChanged();
                }
            }
            str = TAG;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Storage path is switched path = ");
            stringBuilder3.append(DIRECTORY);
            stringBuilder3.append(", FIRST_CONSIDER_STORAGE_PATH = ");
            stringBuilder3.append(FIRST_CONSIDER_STORAGE_PATH);
            stringBuilder3.append(", SECONDARY_STORAGE_PATH = ");
            stringBuilder3.append(SECONDARY_STORAGE_PATH);
            Log.d(str, stringBuilder3.toString());
        }
    }

    public static void switchToPhoneStorage() {
        FIRST_CONSIDER_STORAGE_PATH = PRIMARY_STORAGE_PATH;
        if (!PRIMARY_STORAGE_PATH.equals(sCurrentStoragePath)) {
            Log.v(TAG, "switchToPhoneStorage");
            sCurrentStoragePath = PRIMARY_STORAGE_PATH;
            updateDirectory();
            if (sStorageListener != null && sStorageListener.get() != null) {
                ((StorageListener) sStorageListener.get()).onStoragePathChanged();
            }
        }
    }

    public static void readSystemPriorityStorage() {
        boolean isPriorityStorage;
        if (hasSecondaryStorage()) {
            isPriorityStorage = PriorityStorageBroadcastReceiver.isPriorityStorage();
            CameraSettings.setPriorityStoragePreference(isPriorityStorage);
        } else {
            isPriorityStorage = false;
        }
        FIRST_CONSIDER_STORAGE_PATH = isPriorityStorage ? SECONDARY_STORAGE_PATH : PRIMARY_STORAGE_PATH;
        sCurrentStoragePath = FIRST_CONSIDER_STORAGE_PATH;
        updateDirectory();
    }

    public static boolean isRelatedStorage(Uri uri) {
        boolean z = false;
        if (uri == null) {
            return false;
        }
        String path = uri.getPath();
        if (path != null && (path.equals(PRIMARY_STORAGE_PATH) || path.equals(SECONDARY_STORAGE_PATH))) {
            z = true;
        }
        return z;
    }

    public static boolean isUsePhoneStorage() {
        return PRIMARY_STORAGE_PATH.equals(sCurrentStoragePath);
    }

    public static boolean isPhoneStoragePriority() {
        return PRIMARY_STORAGE_PATH.equals(FIRST_CONSIDER_STORAGE_PATH);
    }

    public static void setStorageListener(StorageListener storageListener) {
        if (storageListener != null) {
            sStorageListener = new WeakReference(storageListener);
        }
    }

    public static boolean isLowStorageAtLastPoint() {
        return getLeftSpace() < LOW_STORAGE_THRESHOLD;
    }

    public static long getLeftSpace() {
        long j = LEFT_SPACE.get();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getLeftSpace() return ");
        stringBuilder.append(j);
        Log.i(str, stringBuilder.toString());
        return j;
    }

    private static void setLeftSpace(long j) {
        LEFT_SPACE.set(j);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setLeftSpace(");
        stringBuilder.append(j);
        stringBuilder.append(")");
        Log.i(str, stringBuilder.toString());
    }

    private static void updateDirectory() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sCurrentStoragePath);
        stringBuilder.append(CAMERA_STORAGE_PATH_SUFFIX);
        DIRECTORY = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(sCurrentStoragePath);
        stringBuilder.append(HIDE_CAMERA_STORAGE_PATH_SUFFIX);
        HIDEDIRECTORY = stringBuilder.toString();
        BUCKET_ID = DIRECTORY.toLowerCase(Locale.ENGLISH).hashCode();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x009c A:{SYNTHETIC, Splitter:B:27:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0094 A:{SYNTHETIC, Splitter:B:24:0x0094} */
    public static void saveMorphoPanoramaOriginalPic(java.nio.ByteBuffer r2, int r3, java.lang.String r4) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = DIRECTORY;
        r0.append(r1);
        r1 = java.io.File.separator;
        r0.append(r1);
        r0.append(r4);
        r1 = java.io.File.separator;
        r0.append(r1);
        r0 = r0.toString();
        r1 = new java.io.File;
        r1.<init>(r0);
        r0 = r1.exists();
        if (r0 != 0) goto L_0x002a;
    L_0x0027:
        r1.mkdirs();
    L_0x002a:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r4);
        r1 = java.io.File.separator;
        r0.append(r1);
        r0.append(r4);
        r4 = "_";
        r0.append(r4);
        r0.append(r3);
        r3 = r0.toString();
        r3 = generateFilepath(r3);
        r4 = 0;
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0077 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0077 }
        r3 = r0.exists();	 Catch:{ Exception -> 0x0077 }
        if (r3 != 0) goto L_0x0059;
    L_0x0056:
        r0.createNewFile();	 Catch:{ Exception -> 0x0077 }
    L_0x0059:
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0077 }
        r1 = 0;
        r3.<init>(r0, r1);	 Catch:{ Exception -> 0x0077 }
        r3 = r3.getChannel();	 Catch:{ Exception -> 0x0077 }
        r3.write(r2);	 Catch:{ Exception -> 0x0072, all -> 0x006f }
        if (r3 == 0) goto L_0x006e;
    L_0x0068:
        r3.close();	 Catch:{ Exception -> 0x006c }
        goto L_0x006e;
    L_0x006c:
        r2 = move-exception;
        goto L_0x0098;
    L_0x006e:
        goto L_0x0098;
    L_0x006f:
        r2 = move-exception;
        r4 = r3;
        goto L_0x0099;
    L_0x0072:
        r2 = move-exception;
        r4 = r3;
        goto L_0x0078;
    L_0x0075:
        r2 = move-exception;
        goto L_0x0099;
    L_0x0077:
        r2 = move-exception;
    L_0x0078:
        r3 = TAG;	 Catch:{ all -> 0x0075 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0075 }
        r0.<init>();	 Catch:{ all -> 0x0075 }
        r1 = "saveMorphoPanoramaOriginalPic  ";
        r0.append(r1);	 Catch:{ all -> 0x0075 }
        r2 = r2.toString();	 Catch:{ all -> 0x0075 }
        r0.append(r2);	 Catch:{ all -> 0x0075 }
        r2 = r0.toString();	 Catch:{ all -> 0x0075 }
        com.android.camera.log.Log.e(r3, r2);	 Catch:{ all -> 0x0075 }
        if (r4 == 0) goto L_0x006e;
    L_0x0094:
        r4.close();	 Catch:{ Exception -> 0x006c }
        goto L_0x006e;
    L_0x0098:
        return;
        if (r4 == 0) goto L_0x00a2;
    L_0x009c:
        r4.close();	 Catch:{ Exception -> 0x00a0 }
        goto L_0x00a2;
    L_0x00a0:
        r3 = move-exception;
    L_0x00a2:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.storage.Storage.saveMorphoPanoramaOriginalPic(java.nio.ByteBuffer, int, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0089 A:{SYNTHETIC, Splitter:B:26:0x0089} */
    public static void saveOriginalPic(byte[] r2, int r3, java.lang.String r4) {
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = DIRECTORY;
        r0.append(r1);
        r1 = java.io.File.separator;
        r0.append(r1);
        r0.append(r4);
        r1 = java.io.File.separator;
        r0.append(r1);
        r0 = r0.toString();
        r1 = new java.io.File;
        r1.<init>(r0);
        r0 = r1.exists();
        if (r0 != 0) goto L_0x002a;
    L_0x0027:
        r1.mkdirs();
    L_0x002a:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r4);
        r1 = java.io.File.separator;
        r0.append(r1);
        r0.append(r4);
        r4 = "_";
        r0.append(r4);
        r0.append(r3);
        r3 = r0.toString();
        r3 = generateFilepath(r3);
        r4 = 0;
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0071 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0071 }
        r3 = r0.exists();	 Catch:{ Exception -> 0x0071 }
        if (r3 != 0) goto L_0x0059;
    L_0x0056:
        r0.createNewFile();	 Catch:{ Exception -> 0x0071 }
    L_0x0059:
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0071 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0071 }
        r3.write(r2);	 Catch:{ Exception -> 0x006c, all -> 0x0069 }
        r3.flush();	 Catch:{ Exception -> 0x0082 }
        r3.close();	 Catch:{ Exception -> 0x0082 }
        goto L_0x0084;
    L_0x0069:
        r2 = move-exception;
        r4 = r3;
        goto L_0x0086;
    L_0x006c:
        r2 = move-exception;
        r4 = r3;
        goto L_0x0072;
    L_0x006f:
        r2 = move-exception;
        goto L_0x0086;
    L_0x0071:
        r2 = move-exception;
    L_0x0072:
        r3 = TAG;	 Catch:{ all -> 0x006f }
        r0 = "saveMorphoPanoramaOriginalPic exception occurs";
        com.android.camera.log.Log.e(r3, r0, r2);	 Catch:{ all -> 0x006f }
        if (r4 == 0) goto L_0x0084;
    L_0x007b:
        r4.flush();	 Catch:{ Exception -> 0x0082 }
        r4.close();	 Catch:{ Exception -> 0x0082 }
        goto L_0x0084;
    L_0x0082:
        r2 = move-exception;
        goto L_0x0085;
    L_0x0085:
        return;
        if (r4 == 0) goto L_0x0092;
    L_0x0089:
        r4.flush();	 Catch:{ Exception -> 0x0090 }
        r4.close();	 Catch:{ Exception -> 0x0090 }
        goto L_0x0092;
    L_0x0090:
        r3 = move-exception;
    L_0x0092:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.storage.Storage.saveOriginalPic(byte[], int, java.lang.String):void");
    }

    public static boolean isUseDocumentMode() {
        return VERSION.SDK_INT >= 28 && !isPhoneStoragePriority();
    }
}
