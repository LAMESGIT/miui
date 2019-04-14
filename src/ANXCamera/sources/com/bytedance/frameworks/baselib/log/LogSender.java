package com.bytedance.frameworks.baselib.log;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.frameworks.baselib.log.LogHandler.IConfig;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

final class LogSender extends Thread {
    private static final long CLEAN_LOG_INTERVAL = 600000;
    private static final long DEFAULT_SCAN_LOG_INTERVAL = 120000;
    static final String KEY_MAGIC_TAG = "magic_tag";
    static final String KEY_MESSAGE = "message";
    static final String MAGIC_TAG = "log_queue";
    private static final long MAX_EXPIRE_TIME = 864000000;
    static final String STATUS_OK = "success";
    private static final String TAG = "LogSender";
    private final Context mContext;
    private final LogDBHelper mDbHelper;
    private long mLastCleanTime = 0;
    private final Object mLock = new Object();
    private LogQueue mLogQueue;
    private long mMinLog = -1;
    private final LinkedList<LogItem> mPendingQueue;
    private long mScanLogInterval = DEFAULT_SCAN_LOG_INTERVAL;
    private final AtomicBoolean mStopFlag;

    LogSender(Context context, LogQueue logQueue, LinkedList<LogItem> linkedList, AtomicBoolean atomicBoolean) {
        super(TAG);
        this.mLogQueue = logQueue;
        this.mContext = context;
        this.mPendingQueue = linkedList;
        this.mStopFlag = atomicBoolean;
        this.mDbHelper = LogDBHelper.getInstance(this.mContext);
    }

    private void cleanLog() {
        if (!isStop()) {
            Map allLogHandler = this.mLogQueue.getAllLogHandler();
            if (allLogHandler != null && !allLogHandler.isEmpty()) {
                for (String str : allLogHandler.keySet()) {
                    if (isStop()) {
                        break;
                    }
                    LogHandler logHandler = (LogHandler) allLogHandler.get(str);
                    if (logHandler != null) {
                        IConfig config = logHandler.getConfig();
                        if (config != null) {
                            this.mDbHelper.cleanExpireLog(str, config.getMaxRetryCount(), config.getLogExpireTime());
                        }
                    }
                }
            }
            this.mDbHelper.cleanExpireLog(null, -1, MAX_EXPIRE_TIME);
        }
    }

    /* JADX WARNING: Missing block: B:15:0x002d, code skipped:
            if (r0 == null) goto L_0x004e;
     */
    /* JADX WARNING: Missing block: B:18:0x0040, code skipped:
            if (r6.mDbHelper.insertLog(r0.type, r0.value) < Long.MAX_VALUE) goto L_0x004e;
     */
    /* JADX WARNING: Missing block: B:19:0x0042, code skipped:
            r6.mDbHelper.recreateTableQueue();
     */
    /* JADX WARNING: Missing block: B:21:0x0049, code skipped:
            r6.mDbHelper.recreateTableQueue();
     */
    private boolean processPendingQueue() {
        /*
        r6 = this;
        r0 = r6.isStop();
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = 0;
        r2 = r6.mPendingQueue;
        monitor-enter(r2);
        r3 = r6.isStop();	 Catch:{ all -> 0x004f }
        if (r3 == 0) goto L_0x0014;
    L_0x0012:
        monitor-exit(r2);	 Catch:{ all -> 0x004f }
        return r1;
    L_0x0014:
        r1 = r6.mPendingQueue;	 Catch:{ all -> 0x004f }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x004f }
        if (r1 != 0) goto L_0x0024;
    L_0x001c:
        r0 = r6.mPendingQueue;	 Catch:{ all -> 0x004f }
        r0 = r0.poll();	 Catch:{ all -> 0x004f }
        r0 = (com.bytedance.frameworks.baselib.log.LogItem) r0;	 Catch:{ all -> 0x004f }
    L_0x0024:
        r1 = r6.mPendingQueue;	 Catch:{ all -> 0x004f }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x004f }
        r1 = r1 ^ 1;
        monitor-exit(r2);	 Catch:{ all -> 0x004f }
        if (r0 == 0) goto L_0x004e;
    L_0x002f:
        r2 = r6.mDbHelper;	 Catch:{ SQLiteFullException -> 0x0048 }
        r3 = r0.type;	 Catch:{ SQLiteFullException -> 0x0048 }
        r0 = r0.value;	 Catch:{ SQLiteFullException -> 0x0048 }
        r2 = r2.insertLog(r3, r0);	 Catch:{ SQLiteFullException -> 0x0048 }
        r4 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x0047;
    L_0x0042:
        r0 = r6.mDbHelper;	 Catch:{ SQLiteFullException -> 0x0048 }
        r0.recreateTableQueue();	 Catch:{ SQLiteFullException -> 0x0048 }
    L_0x0047:
        goto L_0x004e;
    L_0x0048:
        r0 = move-exception;
        r0 = r6.mDbHelper;
        r0.recreateTableQueue();
    L_0x004e:
        return r1;
    L_0x004f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x004f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.log.LogSender.processPendingQueue():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:148:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x022b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x022b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x022b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x022b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x022b A:{RETURN} */
    private boolean scanAndSendLog() {
        /*
        r24 = this;
        r1 = r24;
        r0 = r24.isStop();
        r2 = 0;
        if (r0 == 0) goto L_0x000a;
    L_0x0009:
        return r2;
    L_0x000a:
        r3 = r1.mMinLog;
        r5 = 0;
        r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r0 >= 0) goto L_0x002b;
    L_0x0012:
        r3 = java.lang.System.currentTimeMillis();
        r7 = r1.mLastCleanTime;
        r3 = r3 - r7;
        r7 = 600000; // 0x927c0 float:8.40779E-40 double:2.964394E-318;
        r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r0 <= 0) goto L_0x002b;
    L_0x0020:
        r1.mMinLog = r5;
        r24.cleanLog();
        r3 = java.lang.System.currentTimeMillis();
        r1.mLastCleanTime = r3;
    L_0x002b:
        r0 = r1.mContext;
        r0 = com.bytedance.frameworks.baselib.log.LogLib.isNetworkAvailable(r0);
        r3 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        if (r0 != 0) goto L_0x0039;
    L_0x0036:
        r1.mScanLogInterval = r3;
        return r2;
    L_0x0039:
        r0 = r1.mDbHelper;
        r7 = r1.mMinLog;
        r7 = r0.getLog(r7);
        r0 = 0;
        if (r7 != 0) goto L_0x0064;
    L_0x0044:
        r7 = r1.mMinLog;
        r7 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1));
        if (r7 != 0) goto L_0x0057;
    L_0x004a:
        r7 = r1.mDbHelper;
        r7 = r7.getEventCount(r0);
        r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1));
        if (r0 != 0) goto L_0x0057;
    L_0x0054:
        r1.mScanLogInterval = r5;
        return r2;
    L_0x0057:
        r5 = r1.mMinLog;
        r7 = -1;
        r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r0 != 0) goto L_0x0061;
    L_0x005f:
        r1.mScanLogInterval = r3;
    L_0x0061:
        r1.mMinLog = r7;
        return r2;
    L_0x0064:
        r8 = r1.mMinLog;
        r10 = r7.id;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 >= 0) goto L_0x0071;
    L_0x006c:
        r8 = r7.id;
        r1.mMinLog = r8;
        goto L_0x0078;
    L_0x0071:
        r8 = r1.mMinLog;
        r10 = 1;
        r8 = r8 + r10;
        r1.mMinLog = r8;
        r8 = r7.value;
        r9 = 1;
        if (r8 == 0) goto L_0x021e;
    L_0x0083:
        r8 = r7.value;
        r8 = r8.length;
        if (r8 > 0) goto L_0x008a;
    L_0x0088:
        goto L_0x021e;
    L_0x008a:
        r8 = r1.mLogQueue;
        r10 = r7.type;
        r8 = r8.getLogHandler(r10);
        if (r8 != 0) goto L_0x0095;
    L_0x0094:
        return r9;
    L_0x0095:
        r10 = r8.getConfig();
        r11 = r8.getResponseConfig();
        r12 = java.lang.System.currentTimeMillis();
        r14 = r10.getRetryInterval();
        if (r11 == 0) goto L_0x0198;
    L_0x00a7:
        r16 = r11.getRemoveSwitch();
        if (r16 == 0) goto L_0x00b4;
        r22 = r2;
        r2 = r9;
        r3 = r10;
        goto L_0x0225;
    L_0x00b4:
        r16 = r11.getStopInterval();
        r18 = r8.getStopMoreChannelInterval();
        r0 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1));
        if (r0 <= 0) goto L_0x00ca;
    L_0x00c0:
        r20 = r8.getLastStopTime();
        r20 = r12 - r20;
        r0 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1));
        if (r0 < 0) goto L_0x00d8;
    L_0x00ca:
        r0 = (r18 > r5 ? 1 : (r18 == r5 ? 0 : -1));
        if (r0 <= 0) goto L_0x00d9;
    L_0x00ce:
        r16 = r8.getLastStopTime();
        r16 = r12 - r16;
        r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1));
        if (r0 >= 0) goto L_0x00d9;
    L_0x00d8:
        return r9;
    L_0x00d9:
        r2 = java.lang.System.currentTimeMillis();
        r8.setLastStopTime(r2);
        r0 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1));
        if (r0 <= 0) goto L_0x00f4;
    L_0x00e4:
        r0 = r7.retry_count;
        if (r0 <= 0) goto L_0x00f4;
    L_0x00e8:
        r2 = r7.retry_time;
        r12 = r12 - r2;
        r0 = r7.retry_count;
        r2 = (long) r0;
        r14 = r14 * r2;
        r0 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r0 >= 0) goto L_0x00f4;
    L_0x00f3:
        return r9;
    L_0x00f4:
        r2 = r8.getLastSuccessChannel();
        r0 = r10.getChannels();
        if (r0 != 0) goto L_0x00ff;
    L_0x00fe:
        return r9;
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x017c }
        if (r3 != 0) goto L_0x010f;
    L_0x0106:
        r3 = r7.value;	 Catch:{ Throwable -> 0x017c }
        r3 = r1.sendLog(r8, r2, r3);	 Catch:{ Throwable -> 0x017c }
        r4 = r9;
        goto L_0x0111;
    L_0x010f:
        r3 = 0;
        r4 = 0;
    L_0x0111:
        if (r3 != 0) goto L_0x0177;
        r12 = r0.iterator();	 Catch:{ Throwable -> 0x0174 }
        r13 = r3;
        r3 = 0;
    L_0x011a:
        r14 = r12.hasNext();	 Catch:{ Throwable -> 0x0172 }
        if (r14 == 0) goto L_0x015a;
    L_0x0120:
        r14 = r12.next();	 Catch:{ Throwable -> 0x0172 }
        r14 = (java.lang.String) r14;	 Catch:{ Throwable -> 0x0172 }
        r15 = r11.getMoreChannelSwitch();	 Catch:{ Throwable -> 0x0172 }
        if (r15 != 0) goto L_0x012f;
    L_0x012c:
        if (r4 == 0) goto L_0x012f;
    L_0x012e:
        goto L_0x015a;
    L_0x012f:
        r15 = r24.isStop();	 Catch:{ Throwable -> 0x0172 }
        if (r15 == 0) goto L_0x0136;
    L_0x0135:
        return r9;
    L_0x0136:
        r15 = android.text.TextUtils.isEmpty(r14);	 Catch:{ Throwable -> 0x0172 }
        if (r15 != 0) goto L_0x0157;
    L_0x013c:
        r15 = r14.equals(r2);	 Catch:{ Throwable -> 0x0172 }
        if (r15 == 0) goto L_0x0143;
    L_0x0142:
        goto L_0x0157;
    L_0x0143:
        r4 = r7.value;	 Catch:{ Throwable -> 0x0172 }
        r4 = r1.sendLog(r8, r14, r4);	 Catch:{ Throwable -> 0x0172 }
        if (r4 == 0) goto L_0x0151;
        r13 = r4;
        r2 = r14;
        goto L_0x015a;
    L_0x0151:
        r3 = r3 + 1;
        r13 = r4;
        r4 = r9;
        goto L_0x011a;
    L_0x0157:
        r3 = r3 + 1;
        goto L_0x011a;
    L_0x015a:
        r4 = r0.size();	 Catch:{ Throwable -> 0x0172 }
        if (r3 != r4) goto L_0x016e;
    L_0x0160:
        r0 = r0.size();	 Catch:{ Throwable -> 0x0172 }
        if (r0 <= r9) goto L_0x016e;
    L_0x0166:
        r3 = r11.getStopMoreChannelInterval();	 Catch:{ Throwable -> 0x0172 }
        r8.setStopMoreChannelInterval(r3);	 Catch:{ Throwable -> 0x0172 }
        goto L_0x0171;
    L_0x016e:
        r8.setStopMoreChannelInterval(r5);	 Catch:{ Throwable -> 0x0172 }
    L_0x0171:
        goto L_0x017b;
    L_0x0172:
        r0 = move-exception;
        goto L_0x017e;
    L_0x0174:
        r0 = move-exception;
        r13 = r3;
        goto L_0x017e;
    L_0x0177:
        r8.setStopMoreChannelInterval(r5);	 Catch:{ Throwable -> 0x0174 }
        r13 = r3;
    L_0x017b:
        goto L_0x0194;
    L_0x017c:
        r0 = move-exception;
        r13 = 0;
    L_0x017e:
        r3 = "LogSender";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r11 = "send log exception: ";
        r4.append(r11);
        r4.append(r0);
        r0 = r4.toString();
        com.bytedance.frameworks.baselib.log.LogQueue.log(r3, r0);
    L_0x0194:
        r0 = r2;
        r2 = r13;
        goto L_0x021a;
    L_0x0198:
        r0 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1));
        if (r0 <= 0) goto L_0x01ac;
    L_0x019c:
        r0 = r7.retry_count;
        if (r0 <= 0) goto L_0x01ac;
    L_0x01a0:
        r2 = r7.retry_time;
        r12 = r12 - r2;
        r0 = r7.retry_count;
        r2 = (long) r0;
        r14 = r14 * r2;
        r0 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r0 >= 0) goto L_0x01ac;
    L_0x01ab:
        return r9;
    L_0x01ac:
        r2 = r8.getLastSuccessChannel();
        r0 = r10.getChannels();
        if (r0 != 0) goto L_0x01b7;
    L_0x01b6:
        return r9;
    L_0x01b7:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0200 }
        if (r3 != 0) goto L_0x01c4;
    L_0x01bd:
        r3 = r7.value;	 Catch:{ Throwable -> 0x0200 }
        r3 = r1.sendLog(r8, r2, r3);	 Catch:{ Throwable -> 0x0200 }
        goto L_0x01c5;
    L_0x01c4:
        r3 = 0;
    L_0x01c5:
        if (r3 != 0) goto L_0x01fd;
    L_0x01c7:
        r0 = r0.iterator();	 Catch:{ Throwable -> 0x01fb }
    L_0x01cb:
        r4 = r0.hasNext();	 Catch:{ Throwable -> 0x01fb }
        if (r4 == 0) goto L_0x01fd;
    L_0x01d1:
        r4 = r0.next();	 Catch:{ Throwable -> 0x01fb }
        r4 = (java.lang.String) r4;	 Catch:{ Throwable -> 0x01fb }
        r11 = r24.isStop();	 Catch:{ Throwable -> 0x01fb }
        if (r11 == 0) goto L_0x01de;
    L_0x01dd:
        return r9;
    L_0x01de:
        r11 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Throwable -> 0x01fb }
        if (r11 != 0) goto L_0x01cb;
    L_0x01e4:
        r11 = r4.equals(r2);	 Catch:{ Throwable -> 0x01fb }
        if (r11 == 0) goto L_0x01eb;
    L_0x01ea:
        goto L_0x01cb;
    L_0x01eb:
        r11 = r7.value;	 Catch:{ Throwable -> 0x01fb }
        r11 = r1.sendLog(r8, r4, r11);	 Catch:{ Throwable -> 0x01fb }
        if (r11 == 0) goto L_0x01f8;
        r0 = r4;
        r2 = r11;
        goto L_0x01ff;
        r3 = r11;
        goto L_0x01cb;
    L_0x01fb:
        r0 = move-exception;
        goto L_0x0202;
    L_0x01fd:
        r0 = r2;
        r2 = r3;
    L_0x01ff:
        goto L_0x021a;
    L_0x0200:
        r0 = move-exception;
        r3 = 0;
    L_0x0202:
        r4 = "LogSender";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "send log exception: ";
        r11.append(r12);
        r11.append(r0);
        r0 = r11.toString();
        com.bytedance.frameworks.baselib.log.LogQueue.log(r4, r0);
        r0 = r2;
        r2 = r3;
    L_0x021a:
        r3 = r10;
        r22 = 0;
        goto L_0x0225;
        r3 = r0;
        r8 = r3;
        r22 = r9;
        r2 = 0;
    L_0x0225:
        r4 = r24.isStop();
        if (r4 == 0) goto L_0x022c;
    L_0x022b:
        return r9;
    L_0x022c:
        if (r22 == 0) goto L_0x023b;
    L_0x022e:
        r10 = r1.mDbHelper;
        r11 = r7.id;
        r13 = 1;
        r14 = 0;
        r16 = 0;
        r10.onLogSent(r11, r13, r14, r16);
        goto L_0x0278;
    L_0x023b:
        if (r2 == 0) goto L_0x0240;
    L_0x023d:
        r8.setLastSuccessChannel(r0);
    L_0x0240:
        r10 = r1.mDbHelper;
        r11 = r7.id;
        r14 = r3.getLogExpireTime();
        r16 = r3.getMaxRetryCount();
        r13 = r2;
        r0 = r10.onLogSent(r11, r13, r14, r16);
        if (r0 == 0) goto L_0x026e;
    L_0x0253:
        r3 = r3.getRetryInterval();
        r0 = r7.retry_count;
        r0 = r0 + r9;
        r10 = (long) r0;
        r3 = r3 * r10;
        r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r0 <= 0) goto L_0x0262;
    L_0x0260:
        r1.mScanLogInterval = r3;
    L_0x0262:
        r3 = r1.mScanLogInterval;
        r5 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r3 = java.lang.Math.min(r5, r3);
        r1.mScanLogInterval = r3;
        goto L_0x0273;
    L_0x026e:
        r5 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r1.mScanLogInterval = r5;
    L_0x0273:
        r0 = r7.value;
        r8.onLogSent(r0, r2);
    L_0x0278:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.log.LogSender.scanAndSendLog():boolean");
    }

    private boolean sendLog(LogHandler logHandler, String str, byte[] bArr) throws Throwable {
        if (bArr == null || bArr.length <= 0 || logHandler == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return logHandler.send(str, bArr);
    }

    public void run() {
        LogQueue.log(TAG, "LogSender start");
        while (!isStop()) {
            boolean processPendingQueue = processPendingQueue();
            if (isStop()) {
                break;
            }
            Object obj = (scanAndSendLog() || processPendingQueue) ? 1 : null;
            if (isStop()) {
                break;
            } else if (obj == null) {
                synchronized (this.mLock) {
                    try {
                        if (this.mScanLogInterval == 0) {
                            this.mLock.wait();
                        } else {
                            this.mLock.wait(this.mScanLogInterval);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        LogQueue.log(TAG, "LogSender quit");
    }

    private boolean isStop() {
        return this.mStopFlag.get();
    }

    /* Access modifiers changed, original: 0000 */
    public void awaken() {
        synchronized (this.mLock) {
            this.mLock.notify();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void quit() {
        awaken();
        this.mDbHelper.closeDatabase();
    }
}
