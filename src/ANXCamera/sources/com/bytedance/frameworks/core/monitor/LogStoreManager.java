package com.bytedance.frameworks.core.monitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.frameworks.core.monitor.config.MonitorConfigure;
import com.bytedance.frameworks.core.monitor.model.LocalLog;
import com.bytedance.frameworks.core.monitor.model.LocalVersionInfo;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LogStoreManager {
    static final String[] LOCAL_MONITOR_COLS = new String[]{"_id", DBHelper.COL_VERSION_ID, "data"};
    static final String[] LOCAL_VERSION_COLS = new String[]{"_id", "version_code", DBHelper.COL_VERSION_NAME, DBHelper.COL_MANIFEST_VERSION_CODE, DBHelper.COL_UPDATE_VERSION_CODE};
    private static final String ORDER_BY_ID_ASC = "_id ASC ";
    static final String[] SAMPLE_LOG_COLS = new String[]{"_id", "type", DBHelper.COL_VERSION_ID, "data"};
    private static String SQL_GET_SAMPLED_COUNT = "SELECT count(*) from local_monitor_log WHERE is_sampled = 1";
    private static String SQL_GET_TOTAL_COUNT = "SELECT count(*) FROM local_monitor_log";
    private static final String TAG = "LogStoreManager";
    static final int WEED_OUT_ROWS_SINGLE_TIME = 1000;
    private static final Object mLock = new Object();
    private static HashMap<String, LogStoreManager> sLogStoreManagerMap = new HashMap();
    static long sMaxLogSaveCount = 40000;
    private String mAid;
    private SQLiteDatabase mDb;
    private volatile long totalRowCount = -1;

    public LogStoreManager(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LogStoreManager: ");
        stringBuilder.append(str);
        stringBuilder.append(" , threadName: ");
        stringBuilder.append(Thread.currentThread().getName());
        Log.d("monitorlib", stringBuilder.toString());
        try {
            DBHelper dBHelper = new DBHelper(context, str);
            this.mAid = str;
            this.mDb = dBHelper.getWritableDatabase();
        } catch (Throwable th) {
            Log.i(TAG, "monitor DB build failed");
        }
    }

    public String getAid() {
        return this.mAid;
    }

    public int getDBSizeInMB() {
        File file = new File(this.mDb.getPath());
        if (file.exists()) {
            return (int) ((file.length() / 1024) / 1024);
        }
        return -1;
    }

    public int getDBJournalSizeInKB() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mDb.getPath());
        stringBuilder.append("-journal");
        File file = new File(stringBuilder.toString());
        if (file.exists()) {
            return ((int) file.length()) / 1024;
        }
        return -1;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* JADX WARNING: Missing block: B:12:0x005d, code skipped:
            return r5;
     */
    /* JADX WARNING: Missing block: B:17:0x0062, code skipped:
            return -1;
     */
    public synchronized long insertLocalLog(com.bytedance.frameworks.core.monitor.model.LocalLog r10) {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r9.mDb;	 Catch:{ all -> 0x0063 }
        r1 = -1;
        if (r0 == 0) goto L_0x0061;
    L_0x0007:
        if (r10 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0061;
    L_0x000a:
        r3 = 1;
        r9.weedOutLogIfNeed(r3);	 Catch:{ Exception -> 0x005e }
        r0 = new android.content.ContentValues;	 Catch:{ Exception -> 0x005e }
        r0.<init>();	 Catch:{ Exception -> 0x005e }
        r5 = "type";
        r6 = r10.type;	 Catch:{ Exception -> 0x005e }
        r0.put(r5, r6);	 Catch:{ Exception -> 0x005e }
        r5 = "type2";
        r6 = r10.type2;	 Catch:{ Exception -> 0x005e }
        r0.put(r5, r6);	 Catch:{ Exception -> 0x005e }
        r5 = "create_time";
        r6 = r10.createTime;	 Catch:{ Exception -> 0x005e }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x005e }
        r0.put(r5, r6);	 Catch:{ Exception -> 0x005e }
        r5 = "version_id";
        r6 = r10.versionId;	 Catch:{ Exception -> 0x005e }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x005e }
        r0.put(r5, r6);	 Catch:{ Exception -> 0x005e }
        r5 = "data";
        r6 = r10.data;	 Catch:{ Exception -> 0x005e }
        r0.put(r5, r6);	 Catch:{ Exception -> 0x005e }
        r5 = "is_sampled";
        r10 = r10.isSampled;	 Catch:{ Exception -> 0x005e }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x005e }
        r0.put(r5, r10);	 Catch:{ Exception -> 0x005e }
        r10 = r9.mDb;	 Catch:{ Exception -> 0x005e }
        r5 = "local_monitor_log";
        r6 = 0;
        r5 = r10.insert(r5, r6, r0);	 Catch:{ Exception -> 0x005e }
        r10 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r10 == 0) goto L_0x005c;
    L_0x0057:
        r7 = r9.totalRowCount;	 Catch:{ Exception -> 0x005e }
        r7 = r7 + r3;
        r9.totalRowCount = r7;	 Catch:{ Exception -> 0x005e }
    L_0x005c:
        monitor-exit(r9);
        return r5;
    L_0x005e:
        r10 = move-exception;
        monitor-exit(r9);
        return r1;
    L_0x0061:
        monitor-exit(r9);
        return r1;
    L_0x0063:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.insertLocalLog(com.bytedance.frameworks.core.monitor.model.LocalLog):long");
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* JADX WARNING: Missing block: B:47:0x00a7, code skipped:
            return;
     */
    public synchronized void insertLocalLogBatch(java.util.List<com.bytedance.frameworks.core.monitor.model.LocalLog> r7) {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.mDb;	 Catch:{ all -> 0x00a8 }
        if (r0 == 0) goto L_0x00a6;
    L_0x0005:
        if (r7 == 0) goto L_0x00a6;
    L_0x0007:
        r0 = r7.size();	 Catch:{ all -> 0x00a8 }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        goto L_0x00a6;
    L_0x000f:
        r0 = r7.size();	 Catch:{ all -> 0x00a8 }
        r0 = (long) r0;	 Catch:{ all -> 0x00a8 }
        r6.weedOutLogIfNeed(r0);	 Catch:{ all -> 0x00a8 }
        r0 = r6.mDb;	 Catch:{ all -> 0x00a8 }
        r0.beginTransaction();	 Catch:{ all -> 0x00a8 }
        r0 = r7.iterator();	 Catch:{ Exception -> 0x008f }
        r1 = "INSERT INTO local_monitor_log(type, type2, create_time, version_id, data, is_sampled) VALUES ( ?, ?, ?, ?, ?, ?)";
        r2 = r6.mDb;	 Catch:{ Exception -> 0x008f }
        r1 = r2.compileStatement(r1);	 Catch:{ Exception -> 0x008f }
    L_0x0028:
        r2 = r0.hasNext();	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x0078;
    L_0x002e:
        r2 = r0.next();	 Catch:{ Exception -> 0x008f }
        r2 = (com.bytedance.frameworks.core.monitor.model.LocalLog) r2;	 Catch:{ Exception -> 0x008f }
        r3 = 1;
        r4 = r2.type;	 Catch:{ Exception -> 0x008f }
        if (r4 != 0) goto L_0x003c;
    L_0x0039:
        r4 = "";
        goto L_0x003e;
    L_0x003c:
        r4 = r2.type;	 Catch:{ Exception -> 0x008f }
    L_0x003e:
        r1.bindString(r3, r4);	 Catch:{ Exception -> 0x008f }
        r3 = 2;
        r4 = r2.type2;	 Catch:{ Exception -> 0x008f }
        if (r4 != 0) goto L_0x0049;
    L_0x0046:
        r4 = "";
        goto L_0x004b;
    L_0x0049:
        r4 = r2.type2;	 Catch:{ Exception -> 0x008f }
    L_0x004b:
        r1.bindString(r3, r4);	 Catch:{ Exception -> 0x008f }
        r3 = 3;
        r4 = r2.createTime;	 Catch:{ Exception -> 0x008f }
        r1.bindLong(r3, r4);	 Catch:{ Exception -> 0x008f }
        r3 = 4;
        r4 = r2.versionId;	 Catch:{ Exception -> 0x008f }
        r1.bindLong(r3, r4);	 Catch:{ Exception -> 0x008f }
        r3 = 5;
        r4 = r2.data;	 Catch:{ Exception -> 0x008f }
        if (r4 != 0) goto L_0x0062;
    L_0x005f:
        r4 = "";
        goto L_0x0064;
    L_0x0062:
        r4 = r2.data;	 Catch:{ Exception -> 0x008f }
    L_0x0064:
        r1.bindString(r3, r4);	 Catch:{ Exception -> 0x008f }
        r3 = 6;
        r2 = r2.isSampled;	 Catch:{ Exception -> 0x008f }
        if (r2 == 0) goto L_0x006f;
    L_0x006c:
        r4 = 1;
        goto L_0x0071;
    L_0x006f:
        r4 = 0;
    L_0x0071:
        r1.bindLong(r3, r4);	 Catch:{ Exception -> 0x008f }
        r1.executeInsert();	 Catch:{ Exception -> 0x008f }
        goto L_0x0028;
    L_0x0078:
        r0 = r6.mDb;	 Catch:{ Exception -> 0x008f }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x008f }
        r0 = r6.totalRowCount;	 Catch:{ Exception -> 0x008f }
        r7 = r7.size();	 Catch:{ Exception -> 0x008f }
        r2 = (long) r7;	 Catch:{ Exception -> 0x008f }
        r0 = r0 + r2;
        r6.totalRowCount = r0;	 Catch:{ Exception -> 0x008f }
        r7 = r6.mDb;	 Catch:{ Exception -> 0x0099 }
        r7.endTransaction();	 Catch:{ Exception -> 0x0099 }
        goto L_0x0098;
    L_0x008d:
        r7 = move-exception;
        goto L_0x009d;
    L_0x008f:
        r7 = move-exception;
        r7.printStackTrace();	 Catch:{ all -> 0x008d }
        r7 = r6.mDb;	 Catch:{ Exception -> 0x0099 }
        r7.endTransaction();	 Catch:{ Exception -> 0x0099 }
    L_0x0098:
        goto L_0x009b;
    L_0x0099:
        r7 = move-exception;
    L_0x009b:
        monitor-exit(r6);
        return;
        r0 = r6.mDb;	 Catch:{ Exception -> 0x00a4 }
        r0.endTransaction();	 Catch:{ Exception -> 0x00a4 }
        goto L_0x00a5;
    L_0x00a4:
        r0 = move-exception;
    L_0x00a5:
        throw r7;	 Catch:{ all -> 0x00a8 }
    L_0x00a6:
        monitor-exit(r6);
        return;
    L_0x00a8:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.insertLocalLogBatch(java.util.List):void");
    }

    public synchronized List<LocalLog> getLocalLog(long j, long j2, List<String> list, String str) {
        Throwable th;
        StringBuilder stringBuilder;
        List<String> list2 = list;
        synchronized (this) {
            List emptyList;
            if (this.mDb == null) {
                emptyList = Collections.emptyList();
                return emptyList;
            }
            Cursor cursor = null;
            String str2;
            try {
                String[] strArr;
                str2 = "create_time >=? AND create_time <=? ";
                if (list2 == null || list.size() <= 0) {
                    strArr = new String[]{String.valueOf(j), String.valueOf(j2)};
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    stringBuilder2.append(" AND type2 IN ( ");
                    stringBuilder2.append(TextUtils.join(",", Collections.nCopies(list.size(), "?")));
                    stringBuilder2.append(" )");
                    str2 = stringBuilder2.toString();
                    strArr = new String[(list.size() + 2)];
                    strArr[0] = String.valueOf(j);
                    strArr[1] = String.valueOf(j2);
                    for (int i = 0; i < list.size(); i++) {
                        strArr[i + 2] = (String) list2.get(i);
                    }
                }
                Cursor query = this.mDb.query("local_monitor_log", LOCAL_MONITOR_COLS, str2, strArr, null, null, ORDER_BY_ID_ASC, str);
                try {
                    if (query.getCount() == 0) {
                        emptyList = Collections.emptyList();
                        safeCloseCursor(query);
                        return emptyList;
                    }
                    LinkedList linkedList = new LinkedList();
                    while (query.moveToNext()) {
                        long j3 = query.getLong(0);
                        long j4 = query.getLong(1);
                        linkedList.add(new LocalLog().setVersionId(j4).setData(query.getString(2)).setId(j3));
                    }
                    safeCloseCursor(query);
                    return linkedList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    safeCloseCursor(cursor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("obtain monitor logs failed:");
                stringBuilder.append(th.toString());
                Log.e(str2, stringBuilder.toString());
                safeCloseCursor(cursor);
                emptyList = Collections.emptyList();
                return emptyList;
            }
        }
    }

    public synchronized List<LocalLog> getLogSampled(List<String> list, int i) {
        Throwable th;
        StringBuilder stringBuilder;
        List<String> list2 = list;
        synchronized (this) {
            List emptyList;
            if (this.mDb == null) {
                emptyList = Collections.emptyList();
                return emptyList;
            }
            Cursor cursor = null;
            String str;
            try {
                String[] strArr;
                str = "is_sampled = ? ";
                if (list2 == null || list.size() <= 0) {
                    strArr = new String[]{String.valueOf(1)};
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str);
                    stringBuilder2.append(" AND type IN ( ");
                    stringBuilder2.append(TextUtils.join(",", Collections.nCopies(list.size(), "?")));
                    stringBuilder2.append(" ) ");
                    str = stringBuilder2.toString();
                    strArr = new String[(list.size() + 1)];
                    strArr[0] = String.valueOf(1);
                    int i2 = 0;
                    while (i2 < list.size()) {
                        int i3 = i2 + 1;
                        strArr[i3] = (String) list2.get(i2);
                        i2 = i3;
                    }
                }
                String str2 = str;
                String[] strArr2 = strArr;
                String[] strArr3 = SAMPLE_LOG_COLS;
                String str3 = ORDER_BY_ID_ASC;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(i);
                stringBuilder3.append("");
                Cursor query = this.mDb.query("local_monitor_log", strArr3, str2, strArr2, null, null, str3, stringBuilder3.toString());
                try {
                    if (query.getCount() == 0) {
                        emptyList = Collections.emptyList();
                        safeCloseCursor(query);
                        return emptyList;
                    }
                    LinkedList linkedList = new LinkedList();
                    while (query.moveToNext()) {
                        linkedList.add(new LocalLog(query.getLong(0), query.getString(1), query.getLong(2), query.getString(3)));
                    }
                    safeCloseCursor(query);
                    return linkedList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    safeCloseCursor(cursor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                th.printStackTrace();
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("obtain monitor logs failed:");
                stringBuilder.append(th.toString());
                Log.e(str, stringBuilder.toString());
                safeCloseCursor(cursor);
                emptyList = Collections.emptyList();
                return emptyList;
            }
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* JADX WARNING: Missing block: B:28:0x003f, code skipped:
            return -1;
     */
    public synchronized long getLogSampledCount() {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.mDb;	 Catch:{ all -> 0x0040 }
        r1 = -1;
        if (r0 == 0) goto L_0x003e;
    L_0x0007:
        r0 = r6.mDb;	 Catch:{ all -> 0x0040 }
        r0 = r0.isOpen();	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        goto L_0x003e;
        r0 = 0;
        r3 = r6.mDb;	 Catch:{ Exception -> 0x0037, all -> 0x0032 }
        r4 = SQL_GET_SAMPLED_COUNT;	 Catch:{ Exception -> 0x0037, all -> 0x0032 }
        r3 = r3.rawQuery(r4, r0);	 Catch:{ Exception -> 0x0037, all -> 0x0032 }
        r0 = r3.moveToNext();	 Catch:{ Exception -> 0x002f, all -> 0x002b }
        if (r0 == 0) goto L_0x0027;
    L_0x0021:
        r0 = 0;
        r4 = r3.getLong(r0);	 Catch:{ Exception -> 0x002f, all -> 0x002b }
        r1 = r4;
    L_0x0027:
        safeCloseCursor(r3);	 Catch:{ all -> 0x0040 }
        goto L_0x003b;
    L_0x002b:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
        goto L_0x0033;
    L_0x002f:
        r0 = move-exception;
        r0 = r3;
        goto L_0x0038;
    L_0x0032:
        r1 = move-exception;
    L_0x0033:
        safeCloseCursor(r0);	 Catch:{ all -> 0x0040 }
        throw r1;	 Catch:{ all -> 0x0040 }
    L_0x0037:
        r3 = move-exception;
    L_0x0038:
        safeCloseCursor(r0);	 Catch:{ all -> 0x0040 }
        monitor-exit(r6);
        return r1;
    L_0x003e:
        monitor-exit(r6);
        return r1;
    L_0x0040:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.getLogSampledCount():long");
    }

    /* JADX WARNING: Missing block: B:31:0x004f, code skipped:
            return -1;
     */
    public synchronized long getLogCountInTime(long r6, long r8) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.mDb;	 Catch:{ all -> 0x0050 }
        r1 = -1;
        if (r0 == 0) goto L_0x004e;
    L_0x0007:
        r0 = r5.mDb;	 Catch:{ all -> 0x0050 }
        r0 = r0.isOpen();	 Catch:{ all -> 0x0050 }
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        goto L_0x004e;
        r0 = 0;
        r3 = "SELECT count(*) FROM local_monitor_log WHERE create_time >= ? AND create_time <= ?";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r7 = 0;
        r4[r7] = r6;	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r6 = 1;
        r8 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r4[r6] = r8;	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r6 = r5.mDb;	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r6 = r6.rawQuery(r3, r4);	 Catch:{ Exception -> 0x0047, all -> 0x0042 }
        r8 = r6.moveToNext();	 Catch:{ Exception -> 0x003f, all -> 0x003c }
        if (r8 == 0) goto L_0x0036;
    L_0x0031:
        r7 = r6.getLong(r7);	 Catch:{ Exception -> 0x003f, all -> 0x003c }
        goto L_0x0037;
    L_0x0036:
        r7 = r1;
    L_0x0037:
        safeCloseCursor(r6);	 Catch:{ all -> 0x0050 }
        monitor-exit(r5);
        return r7;
    L_0x003c:
        r7 = move-exception;
        r0 = r6;
        goto L_0x0043;
    L_0x003f:
        r7 = move-exception;
        r0 = r6;
        goto L_0x0048;
    L_0x0042:
        r7 = move-exception;
    L_0x0043:
        safeCloseCursor(r0);	 Catch:{ all -> 0x0050 }
        throw r7;	 Catch:{ all -> 0x0050 }
    L_0x0047:
        r6 = move-exception;
    L_0x0048:
        safeCloseCursor(r0);	 Catch:{ all -> 0x0050 }
        monitor-exit(r5);
        return r1;
    L_0x004e:
        monitor-exit(r5);
        return r1;
    L_0x0050:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.getLogCountInTime(long, long):long");
    }

    /* JADX WARNING: Missing block: B:31:0x0040, code skipped:
            return -1;
     */
    public synchronized long getLogTotalCount() {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.mDb;	 Catch:{ all -> 0x0041 }
        r1 = -1;
        if (r0 == 0) goto L_0x003f;
    L_0x0007:
        r0 = r6.mDb;	 Catch:{ all -> 0x0041 }
        r0 = r0.isOpen();	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        goto L_0x003f;
        r0 = 0;
        r3 = r6.mDb;	 Catch:{ Exception -> 0x0038, all -> 0x0033 }
        r4 = SQL_GET_TOTAL_COUNT;	 Catch:{ Exception -> 0x0038, all -> 0x0033 }
        r3 = r3.rawQuery(r4, r0);	 Catch:{ Exception -> 0x0038, all -> 0x0033 }
        r0 = r3.moveToNext();	 Catch:{ Exception -> 0x0030, all -> 0x002c }
        if (r0 == 0) goto L_0x0027;
    L_0x0021:
        r0 = 0;
        r4 = r3.getLong(r0);	 Catch:{ Exception -> 0x0030, all -> 0x002c }
        r1 = r4;
    L_0x0027:
        safeCloseCursor(r3);	 Catch:{ all -> 0x0041 }
        monitor-exit(r6);
        return r1;
    L_0x002c:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
        goto L_0x0034;
    L_0x0030:
        r0 = move-exception;
        r0 = r3;
        goto L_0x0039;
    L_0x0033:
        r1 = move-exception;
    L_0x0034:
        safeCloseCursor(r0);	 Catch:{ all -> 0x0041 }
        throw r1;	 Catch:{ all -> 0x0041 }
    L_0x0038:
        r3 = move-exception;
    L_0x0039:
        safeCloseCursor(r0);	 Catch:{ all -> 0x0041 }
        monitor-exit(r6);
        return r1;
    L_0x003f:
        monitor-exit(r6);
        return r1;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.getLogTotalCount():long");
    }

    private synchronized void weedOutLogIfNeed(long j) {
        if (this.totalRowCount <= 0) {
            this.totalRowCount = getLogTotalCount();
        }
        if (this.totalRowCount + j >= sMaxLogSaveCount) {
            weedOutOldLogs(1000);
        }
    }

    /* JADX WARNING: Missing block: B:14:0x0037, code skipped:
            return;
     */
    public synchronized void weedOutOldLogs(long r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.mDb;	 Catch:{ all -> 0x0038 }
        if (r0 == 0) goto L_0x0036;
    L_0x0005:
        r0 = 0;
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 > 0) goto L_0x000c;
    L_0x000b:
        goto L_0x0036;
    L_0x000c:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0030 }
        r0.<init>();	 Catch:{ Exception -> 0x0030 }
        r1 = " DELETE FROM local_monitor_log WHERE _id IN (SELECT _id FROM local_monitor_log ORDER BY _id ASC LIMIT ";
        r0.append(r1);	 Catch:{ Exception -> 0x0030 }
        r0.append(r3);	 Catch:{ Exception -> 0x0030 }
        r1 = ")";
        r0.append(r1);	 Catch:{ Exception -> 0x0030 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0030 }
        r1 = r2.mDb;	 Catch:{ Exception -> 0x0030 }
        r1.execSQL(r0);	 Catch:{ Exception -> 0x0030 }
        r0 = r2.totalRowCount;	 Catch:{ Exception -> 0x0030 }
        r0 = r0 - r3;
        r2.totalRowCount = r0;	 Catch:{ Exception -> 0x0030 }
        r2.weedOutVersionTableIfNeed();	 Catch:{ Exception -> 0x0030 }
        goto L_0x0034;
    L_0x0030:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ all -> 0x0038 }
    L_0x0034:
        monitor-exit(r2);
        return;
    L_0x0036:
        monitor-exit(r2);
        return;
    L_0x0038:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.weedOutOldLogs(long):void");
    }

    /* JADX WARNING: Missing block: B:14:0x0027, code skipped:
            return;
     */
    public synchronized void cleanExpiredLog(long r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.mDb;	 Catch:{ all -> 0x0028 }
        if (r0 == 0) goto L_0x0026;
    L_0x0005:
        r0 = 0;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x000c;
    L_0x000b:
        goto L_0x0026;
    L_0x000c:
        r0 = "create_time< ? ";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0020 }
        r2 = 0;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0020 }
        r1[r2] = r4;	 Catch:{ Exception -> 0x0020 }
        r4 = r3.mDb;	 Catch:{ Exception -> 0x0020 }
        r5 = "local_monitor_log";
        r4.delete(r5, r0, r1);	 Catch:{ Exception -> 0x0020 }
        goto L_0x0024;
    L_0x0020:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x0028 }
    L_0x0024:
        monitor-exit(r3);
        return;
    L_0x0026:
        monitor-exit(r3);
        return;
    L_0x0028:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.cleanExpiredLog(long):void");
    }

    /* JADX WARNING: Missing block: B:13:0x002e, code skipped:
            return;
     */
    private synchronized void weedOutVersionTableIfNeed() {
        /*
        r10 = this;
        monitor-enter(r10);
        r0 = r10.mDb;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r10);
        return;
    L_0x0007:
        r1 = r10.mDb;	 Catch:{ all -> 0x002f }
        r2 = "local_monitor_log";
        r0 = "version_id";
        r3 = new java.lang.String[]{r0};	 Catch:{ all -> 0x002f }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = "_id ASC ";
        r9 = "1";
        r0 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x002d;
    L_0x001f:
        r1 = r0.moveToNext();	 Catch:{ all -> 0x002f }
        if (r1 == 0) goto L_0x002d;
    L_0x0025:
        r1 = 0;
        r0 = r0.getLong(r1);	 Catch:{ all -> 0x002f }
        r10.weedOutVersionTable(r0);	 Catch:{ all -> 0x002f }
    L_0x002d:
        monitor-exit(r10);
        return;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.weedOutVersionTableIfNeed():void");
    }

    /* JADX WARNING: Missing block: B:12:0x0024, code skipped:
            return;
     */
    public synchronized void weedOutVersionTable(long r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.mDb;	 Catch:{ all -> 0x0025 }
        if (r0 == 0) goto L_0x0023;
    L_0x0005:
        r0 = 0;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 >= 0) goto L_0x000c;
    L_0x000b:
        goto L_0x0023;
    L_0x000c:
        r0 = "_id< ? ";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0020 }
        r2 = 0;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0020 }
        r1[r2] = r4;	 Catch:{ Exception -> 0x0020 }
        r4 = r3.mDb;	 Catch:{ Exception -> 0x0020 }
        r5 = "local_monitor_version";
        r4.delete(r5, r0, r1);	 Catch:{ Exception -> 0x0020 }
        goto L_0x0021;
    L_0x0020:
        r4 = move-exception;
    L_0x0021:
        monitor-exit(r3);
        return;
    L_0x0023:
        monitor-exit(r3);
        return;
    L_0x0025:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.weedOutVersionTable(long):void");
    }

    private synchronized void closeDatabase() {
        try {
            if (this.mDb != null && this.mDb.isOpen()) {
                this.mDb.close();
                this.mDb = null;
            }
        } catch (Throwable th) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mDb close error:");
            stringBuilder.append(th);
            Log.e(str, stringBuilder.toString());
        }
        return;
    }

    public void closeDB() {
        synchronized (mLock) {
            closeDatabase();
        }
    }

    protected static void safeCloseCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("cursor close error:");
                stringBuilder.append(e);
                Log.e(str, stringBuilder.toString());
            }
        }
    }

    public synchronized long saveLocalVersion(String str, String str2, String str3, String str4) {
        if (this.mDb == null) {
            return -1;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("version_code", str);
            contentValues.put(DBHelper.COL_VERSION_NAME, str2);
            contentValues.put(DBHelper.COL_MANIFEST_VERSION_CODE, str3);
            contentValues.put(DBHelper.COL_UPDATE_VERSION_CODE, str4);
            return this.mDb.insert("local_monitor_version", null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public synchronized long saveLocalVersion(LocalVersionInfo localVersionInfo) {
        if (this.mDb != null) {
            if (localVersionInfo != null) {
                return saveLocalVersion(localVersionInfo.versionCode, localVersionInfo.versionName, localVersionInfo.manifestVersionCode, localVersionInfo.updateVersionCode);
            }
        }
        return -1;
    }

    /* JADX WARNING: Missing block: B:15:0x0032, code skipped:
            return -1;
     */
    public synchronized int deleteLocalLogByIds(java.lang.String r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.mDb;	 Catch:{ all -> 0x0033 }
        r1 = -1;
        if (r0 == 0) goto L_0x0031;
    L_0x0006:
        r0 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        goto L_0x0031;
    L_0x000d:
        r0 = r4.mDb;	 Catch:{ Exception -> 0x002a }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002a }
        r2.<init>();	 Catch:{ Exception -> 0x002a }
        r3 = "delete from local_monitor_log where _id in ( ";
        r2.append(r3);	 Catch:{ Exception -> 0x002a }
        r2.append(r5);	 Catch:{ Exception -> 0x002a }
        r5 = " )";
        r2.append(r5);	 Catch:{ Exception -> 0x002a }
        r5 = r2.toString();	 Catch:{ Exception -> 0x002a }
        r0.execSQL(r5);	 Catch:{ Exception -> 0x002a }
        r1 = 1;
        goto L_0x002f;
    L_0x002a:
        r5 = move-exception;
        r5.printStackTrace();	 Catch:{ all -> 0x0033 }
    L_0x002f:
        monitor-exit(r4);
        return r1;
    L_0x0031:
        monitor-exit(r4);
        return r1;
    L_0x0033:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogStoreManager.deleteLocalLogByIds(java.lang.String):int");
    }

    public synchronized LocalVersionInfo getLatestLocalVersion() {
        Throwable th;
        Cursor query;
        try {
            query = this.mDb.query("local_monitor_version", LOCAL_VERSION_COLS, null, null, null, null, "_id DESC", String.valueOf(1));
            try {
                if (query.getCount() == 0) {
                    safeCloseCursor(query);
                    return null;
                } else if (query.moveToNext()) {
                    LocalVersionInfo localVersionInfo = new LocalVersionInfo(query.getLong(0), query.getString(1), query.getString(2), query.getString(3), query.getString(4));
                    safeCloseCursor(query);
                    return localVersionInfo;
                } else {
                    safeCloseCursor(query);
                }
            } catch (Throwable th2) {
                th = th2;
                safeCloseCursor(query);
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            query = null;
            th = th4;
            safeCloseCursor(query);
            throw th;
        }
    }

    public synchronized LocalVersionInfo getLocalVersionById(long j) {
        Throwable th;
        Cursor cursor = null;
        Cursor query;
        try {
            query = this.mDb.query("local_monitor_version", LOCAL_VERSION_COLS, " _id = ?", new String[]{String.valueOf(j)}, null, null, "_id DESC", String.valueOf(1));
            try {
                if (query.getCount() == 0) {
                    safeCloseCursor(query);
                    return null;
                } else if (query.moveToNext()) {
                    LocalVersionInfo localVersionInfo = new LocalVersionInfo(query.getLong(0), query.getString(1), query.getString(2), query.getString(3), query.getString(4));
                    safeCloseCursor(query);
                    return localVersionInfo;
                } else {
                    safeCloseCursor(query);
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                safeCloseCursor(cursor);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            safeCloseCursor(cursor);
            throw th;
        }
    }

    public void updateConfig() {
        setMaxLogSaveCount(MonitorConfigure.getMaxMonitorLogSaveCount(this.mAid));
    }

    private void setMaxLogSaveCount(long j) {
        if (j <= 0) {
            sMaxLogSaveCount = 40000;
        } else {
            sMaxLogSaveCount = j;
        }
    }
}
