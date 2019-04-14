package com.bytedance.frameworks.baselib.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

class LogDBHelper {
    static final String COL_ID = "_id";
    static final String COL_RETRY_COUNT = "retry_count";
    static final String COL_RETRY_TIME = "retry_time";
    static final String COL_TIMESTAMP = "timestamp";
    static final String COL_TYPE = "type";
    static final String COL_VALUE = "value";
    static final String DB_NAME = "lib_log_queue.db";
    static final int DB_VERSION = 1;
    static final String[] QUEUE_COLS = new String[]{COL_ID, COL_VALUE, "type", COL_TIMESTAMP, COL_RETRY_COUNT, COL_RETRY_TIME};
    static final String TABLE_QUEUE = "queue";
    static final String createTableQueue = "CREATE TABLE queue ( _id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, type TEXT, timestamp INTEGER, retry_count INTEGER, retry_time INTEGER )";
    private static LogDBHelper sInstance = null;
    private static final String whereId = "_id = ?";
    private SQLiteDatabase mDb;

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, LogDBHelper.DB_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(LogDBHelper.createTableQueue);
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("create db exception ");
                stringBuilder.append(e);
                LogQueue.log(stringBuilder.toString());
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    static LogDBHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LogDBHelper.class) {
                if (sInstance == null) {
                    sInstance = new LogDBHelper(context);
                }
            }
        }
        return sInstance;
    }

    private LogDBHelper(Context context) {
        if (context != null) {
            try {
                this.mDb = new OpenHelper(context.getApplicationContext()).getWritableDatabase();
            } catch (Throwable th) {
            }
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized boolean isOpen() {
        if (this.mDb != null && this.mDb.isOpen()) {
            return true;
        }
        LogQueue.log("db not establish and open");
        return false;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized long getEventCount(String str) {
        Throwable th;
        long j = 0;
        if (!isOpen()) {
            return 0;
        }
        Cursor cursor = null;
        try {
            String str2 = "select count(*) from queue";
            if (!TextUtils.isEmpty(str)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                str2 = stringBuilder.toString();
            }
            Cursor rawQuery = this.mDb.rawQuery(str2, null);
            try {
                if (rawQuery.moveToNext()) {
                    j = rawQuery.getLong(0);
                }
                safeCloseCursor(rawQuery);
            } catch (Throwable th2) {
                Throwable th3 = th2;
                cursor = rawQuery;
                th = th3;
                safeCloseCursor(cursor);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            safeCloseCursor(cursor);
            throw th;
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void closeDatabase() {
        try {
            if (isOpen()) {
                this.mDb.close();
                this.mDb = null;
            }
        } catch (Throwable th) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("closeDatabase error: ");
            stringBuilder.append(th);
            LogQueue.log(stringBuilder.toString());
        }
        return;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized long insertLog(String str, byte[] bArr) {
        if (isOpen() && bArr != null) {
            if (bArr.length > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_VALUE, bArr);
                contentValues.put("type", str);
                contentValues.put(COL_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
                contentValues.put(COL_RETRY_COUNT, Integer.valueOf(0));
                contentValues.put(COL_RETRY_TIME, Long.valueOf(0));
                return this.mDb.insert(TABLE_QUEUE, null, contentValues);
            }
        }
        return -1;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void recreateTableQueue() {
        if (isOpen()) {
            try {
                this.mDb.execSQL("DROP TABLE IF EXISTS queue");
                this.mDb.execSQL(createTableQueue);
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("recreateTableQueue db exception ");
                stringBuilder.append(e);
                LogQueue.log(stringBuilder.toString());
            }
        } else {
            return;
        }
        return;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void cleanExpireLog(String str, int i, long j) {
        if (isOpen()) {
            String[] strArr;
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (TextUtils.isEmpty(str)) {
                str = "timestamp <= ? ";
                strArr = new String[]{String.valueOf(currentTimeMillis)};
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(timestamp <= ? OR retry_count > ");
                stringBuilder.append(i);
                stringBuilder.append(") and ");
                stringBuilder.append("type");
                stringBuilder.append(" = ?");
                String stringBuilder2 = stringBuilder.toString();
                String[] strArr2 = new String[]{String.valueOf(currentTimeMillis), str};
                str = stringBuilder2;
                strArr = strArr2;
            }
            try {
                this.mDb.delete(TABLE_QUEUE, str, strArr);
            } catch (Exception e) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("delete expire log error:");
                stringBuilder3.append(e);
                LogQueue.log(stringBuilder3.toString());
            }
        } else {
            return;
        }
        return;
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x0028} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:16:0x005d, code skipped:
            r13 = e;
     */
    /* JADX WARNING: Missing block: B:19:0x0063, code skipped:
            r13 = th;
     */
    /* JADX WARNING: Missing block: B:20:0x0064, code skipped:
            r1 = r12;
     */
    public synchronized com.bytedance.frameworks.baselib.log.LogItem getLog(long r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = r11.isOpen();	 Catch:{ all -> 0x008d }
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r11);
        return r1;
        r9 = "_id ASC";
        r10 = "1";
        r5 = "_id > ?";
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ Exception -> 0x006c }
        r12 = java.lang.String.valueOf(r12);	 Catch:{ Exception -> 0x006c }
        r13 = 0;
        r6[r13] = r12;	 Catch:{ Exception -> 0x006c }
        r2 = r11.mDb;	 Catch:{ Exception -> 0x006c }
        r3 = "queue";
        r4 = QUEUE_COLS;	 Catch:{ Exception -> 0x006c }
        r7 = 0;
        r8 = 0;
        r12 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x006c }
        r2 = r12.moveToNext();	 Catch:{ Exception -> 0x0066, all -> 0x0063 }
        if (r2 == 0) goto L_0x005f;
    L_0x002e:
        r2 = new com.bytedance.frameworks.baselib.log.LogItem;	 Catch:{ Exception -> 0x0066, all -> 0x0063 }
        r2.<init>();	 Catch:{ Exception -> 0x0066, all -> 0x0063 }
        r3 = r12.getLong(r13);	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r2.id = r3;	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r13 = r12.getBlob(r0);	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r2.value = r13;	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r13 = 2;
        r13 = r12.getString(r13);	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r2.type = r13;	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r13 = 3;
        r0 = r12.getLong(r13);	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r2.timestamp = r0;	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r13 = 4;
        r13 = r12.getInt(r13);	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r2.retry_count = r13;	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r13 = 5;
        r0 = r12.getLong(r13);	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r2.retry_time = r0;	 Catch:{ Exception -> 0x005d, all -> 0x0063 }
        r1 = r2;
        goto L_0x005f;
    L_0x005d:
        r13 = move-exception;
        goto L_0x0068;
    L_0x005f:
        safeCloseCursor(r12);	 Catch:{ all -> 0x008d }
        goto L_0x0087;
    L_0x0063:
        r13 = move-exception;
        r1 = r12;
        goto L_0x0089;
    L_0x0066:
        r13 = move-exception;
        r2 = r1;
    L_0x0068:
        r1 = r12;
        goto L_0x006e;
    L_0x006a:
        r13 = move-exception;
        goto L_0x0089;
    L_0x006c:
        r13 = move-exception;
        r2 = r1;
    L_0x006e:
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006a }
        r12.<init>();	 Catch:{ all -> 0x006a }
        r0 = "getLog exception ";
        r12.append(r0);	 Catch:{ all -> 0x006a }
        r12.append(r13);	 Catch:{ all -> 0x006a }
        r12 = r12.toString();	 Catch:{ all -> 0x006a }
        com.bytedance.frameworks.baselib.log.LogQueue.log(r12);	 Catch:{ all -> 0x006a }
        safeCloseCursor(r1);	 Catch:{ all -> 0x008d }
        r1 = r2;
    L_0x0087:
        monitor-exit(r11);
        return r1;
    L_0x0089:
        safeCloseCursor(r1);	 Catch:{ all -> 0x008d }
        throw r13;	 Catch:{ all -> 0x008d }
    L_0x008d:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.log.LogDBHelper.getLog(long):com.bytedance.frameworks.baselib.log.LogItem");
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00cf A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ae A:{SYNTHETIC, Splitter:B:46:0x00ae} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ae A:{SYNTHETIC, Splitter:B:46:0x00ae} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00cf A:{SKIP} */
    /* JADX WARNING: Missing block: B:56:0x00d2, code skipped:
            return false;
     */
    public synchronized boolean onLogSent(long r16, boolean r18, long r19, int r21) {
        /*
        r15 = this;
        r1 = r15;
        r2 = r16;
        monitor-enter(r15);
        r0 = r1.isOpen();	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        if (r0 == 0) goto L_0x00d1;
    L_0x000b:
        r5 = 0;
        r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1));
        if (r0 > 0) goto L_0x0013;
    L_0x0011:
        goto L_0x00d1;
    L_0x0013:
        r0 = 1;
        r13 = new java.lang.String[r0];	 Catch:{ all -> 0x00d3 }
        r5 = java.lang.String.valueOf(r16);	 Catch:{ all -> 0x00d3 }
        r13[r4] = r5;	 Catch:{ all -> 0x00d3 }
        if (r18 != 0) goto L_0x00ab;
    L_0x001f:
        r14 = 0;
        r5 = "timestamp";
        r6 = "retry_count";
        r7 = new java.lang.String[]{r5, r6};	 Catch:{ Exception -> 0x008c }
        r5 = r1.mDb;	 Catch:{ Exception -> 0x008c }
        r6 = "queue";
        r8 = "_id = ?";
        r10 = 0;
        r11 = 0;
        r12 = 0;
        r9 = r13;
        r5 = r5.query(r6, r7, r8, r9, r10, r11, r12);	 Catch:{ Exception -> 0x008c }
        r6 = r5.moveToNext();	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        if (r6 != 0) goto L_0x0042;
        safeCloseCursor(r5);	 Catch:{ all -> 0x00d3 }
        monitor-exit(r15);
        return r4;
    L_0x0042:
        r6 = r5.getLong(r4);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r8 = r5.getInt(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r9 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r6 = r9 - r6;
        r6 = (r6 > r19 ? 1 : (r6 == r19 ? 0 : -1));
        if (r6 >= 0) goto L_0x007f;
    L_0x0054:
        r6 = r21;
        if (r8 >= r6) goto L_0x007f;
    L_0x0058:
        r6 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r6.<init>();	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r7 = "retry_count";
        r8 = r8 + r0;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r6.put(r7, r8);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r7 = "retry_time";
        r8 = java.lang.Long.valueOf(r9);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r6.put(r7, r8);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r7 = r1.mDb;	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        r8 = "queue";
        r9 = "_id = ?";
        r7.update(r8, r6, r9, r13);	 Catch:{ Exception -> 0x0086, all -> 0x0084 }
        safeCloseCursor(r5);	 Catch:{ all -> 0x00d3 }
        monitor-exit(r15);
        return r0;
        safeCloseCursor(r5);	 Catch:{ all -> 0x00d3 }
        goto L_0x00a6;
    L_0x0084:
        r0 = move-exception;
        goto L_0x00a7;
    L_0x0086:
        r0 = move-exception;
        r14 = r5;
        goto L_0x008d;
    L_0x0089:
        r0 = move-exception;
        r5 = r14;
        goto L_0x00a7;
    L_0x008c:
        r0 = move-exception;
    L_0x008d:
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0089 }
        r5.<init>();	 Catch:{ all -> 0x0089 }
        r6 = "onLogSent exception: ";
        r5.append(r6);	 Catch:{ all -> 0x0089 }
        r5.append(r0);	 Catch:{ all -> 0x0089 }
        r0 = r5.toString();	 Catch:{ all -> 0x0089 }
        com.bytedance.frameworks.baselib.log.LogQueue.log(r0);	 Catch:{ all -> 0x0089 }
        safeCloseCursor(r14);	 Catch:{ all -> 0x00d3 }
        r0 = r4;
    L_0x00a6:
        goto L_0x00ac;
    L_0x00a7:
        safeCloseCursor(r5);	 Catch:{ all -> 0x00d3 }
        throw r0;	 Catch:{ all -> 0x00d3 }
    L_0x00ac:
        if (r0 == 0) goto L_0x00cf;
    L_0x00ae:
        r0 = r1.mDb;	 Catch:{ Throwable -> 0x00b8 }
        r5 = "queue";
        r6 = "_id = ?";
        r0.delete(r5, r6, r13);	 Catch:{ Throwable -> 0x00b8 }
        goto L_0x00b9;
    L_0x00b8:
        r0 = move-exception;
    L_0x00b9:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d3 }
        r0.<init>();	 Catch:{ all -> 0x00d3 }
        r5 = "delete app_log: ";
        r0.append(r5);	 Catch:{ all -> 0x00d3 }
        r0.append(r2);	 Catch:{ all -> 0x00d3 }
        r0 = r0.toString();	 Catch:{ all -> 0x00d3 }
        com.bytedance.frameworks.baselib.log.LogQueue.log(r0);	 Catch:{ all -> 0x00d3 }
        monitor-exit(r15);
        return r4;
    L_0x00cf:
        monitor-exit(r15);
        return r4;
    L_0x00d1:
        monitor-exit(r15);
        return r4;
    L_0x00d3:
        r0 = move-exception;
        monitor-exit(r15);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.log.LogDBHelper.onLogSent(long, boolean, long, int):boolean");
    }

    static void safeCloseCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
