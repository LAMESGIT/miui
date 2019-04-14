package com.bytedance.frameworks.baselib.log;

import android.content.Context;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class LogQueue {
    static final boolean DEBUG = false;
    private static final int MAX_QUEUE_SIZE = 2000;
    static final String TAG = "LogQueue";
    private static LogQueue sLogQueue;
    private final Context mContext;
    private final Map<String, LogHandler> mLogHandlers;
    private final LogSender mLogSender;
    private final LinkedList<LogItem> mPendingQueue = new LinkedList();
    private final AtomicBoolean mStopFlag = new AtomicBoolean(false);

    public static LogQueue getInstance(Context context) {
        if (sLogQueue == null) {
            synchronized (LogQueue.class) {
                if (sLogQueue == null) {
                    sLogQueue = new LogQueue(context);
                }
            }
        }
        return sLogQueue;
    }

    public static void quit() {
        synchronized (LogQueue.class) {
            if (sLogQueue != null) {
                sLogQueue.stop();
            }
        }
    }

    static void log(String str) {
    }

    static void log(String str, String str2) {
    }

    private LogQueue(Context context) {
        this.mContext = context.getApplicationContext();
        this.mLogHandlers = new ConcurrentHashMap();
        this.mLogSender = new LogSender(this.mContext, this, this.mPendingQueue, this.mStopFlag);
        this.mLogSender.start();
    }

    public void registerLogHandler(String str, LogHandler logHandler) {
        if (!isStop() && logHandler != null) {
            this.mLogHandlers.put(str, logHandler);
        }
    }

    public void unregisterLogHandler(LogHandler logHandler) {
        if (!isStop() && logHandler != null) {
            this.mLogHandlers.remove(logHandler.getType());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public LogHandler getLogHandler(String str) {
        return (LogHandler) this.mLogHandlers.get(str);
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, LogHandler> getAllLogHandler() {
        return this.mLogHandlers;
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Missing block: B:24:0x0045, code skipped:
            return false;
     */
    public boolean enqueue(java.lang.String r4, byte[] r5) {
        /*
        r3 = this;
        r0 = r3.isStop();
        r1 = 0;
        if (r0 != 0) goto L_0x0045;
    L_0x0007:
        if (r5 == 0) goto L_0x0045;
    L_0x0009:
        r0 = r5.length;
        if (r0 > 0) goto L_0x000d;
    L_0x000c:
        goto L_0x0045;
    L_0x000d:
        r0 = r3.getLogHandler(r4);
        if (r0 != 0) goto L_0x0014;
    L_0x0013:
        return r1;
    L_0x0014:
        r0 = r3.mPendingQueue;
        monitor-enter(r0);
        r2 = r3.mStopFlag;	 Catch:{ all -> 0x0042 }
        r2 = r2.get();	 Catch:{ all -> 0x0042 }
        if (r2 == 0) goto L_0x0021;
    L_0x001f:
        monitor-exit(r0);	 Catch:{ all -> 0x0042 }
        return r1;
    L_0x0021:
        r1 = r3.mPendingQueue;	 Catch:{ all -> 0x0042 }
        r1 = r1.size();	 Catch:{ all -> 0x0042 }
        r2 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        if (r1 < r2) goto L_0x0030;
    L_0x002b:
        r1 = r3.mPendingQueue;	 Catch:{ all -> 0x0042 }
        r1.poll();	 Catch:{ all -> 0x0042 }
    L_0x0030:
        r1 = r3.mPendingQueue;	 Catch:{ all -> 0x0042 }
        r2 = new com.bytedance.frameworks.baselib.log.LogItem;	 Catch:{ all -> 0x0042 }
        r2.<init>(r4, r5);	 Catch:{ all -> 0x0042 }
        r4 = r1.add(r2);	 Catch:{ all -> 0x0042 }
        r5 = r3.mLogSender;	 Catch:{ all -> 0x0042 }
        r5.awaken();	 Catch:{ all -> 0x0042 }
        monitor-exit(r0);	 Catch:{ all -> 0x0042 }
        return r4;
    L_0x0042:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0042 }
        throw r4;
    L_0x0045:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.log.LogQueue.enqueue(java.lang.String, byte[]):boolean");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isStop() {
        return this.mStopFlag.get();
    }

    /* Access modifiers changed, original: 0000 */
    public void stop() {
        synchronized (this.mPendingQueue) {
            this.mPendingQueue.clear();
        }
        this.mStopFlag.set(true);
        this.mLogSender.quit();
    }
}
