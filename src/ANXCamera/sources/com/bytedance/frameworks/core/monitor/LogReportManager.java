package com.bytedance.frameworks.core.monitor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.frameworks.core.monitor.config.MonitorConfigure;
import com.bytedance.frameworks.core.monitor.model.LocalVersionInfo;
import com.bytedance.frameworks.core.monitor.net.DefaultLogSendImpl;
import com.bytedance.frameworks.core.monitor.net.MonitorLogSender;
import com.bytedance.frameworks.core.monitor.util.JsonUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class LogReportManager {
    private static final String TAG = "LogReportManager";
    private String mAid;
    private volatile boolean mCollectLogSwitch = true;
    private JSONObject mHeaderInfo;
    private int mInterval = 120;
    private long mLastPackTime = 0;
    private LogStoreManager mLogStoreManager;
    private int mReportCount = 100;

    protected LogReportManager(Context context, String str, LogStoreManager logStoreManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LogReportManager: ");
        stringBuilder.append(logStoreManager.getAid());
        stringBuilder.append(" , threadName: ");
        stringBuilder.append(Thread.currentThread().getName());
        Log.d("monitorlib", stringBuilder.toString());
        this.mAid = str;
        this.mLogStoreManager = logStoreManager;
        MonitorLogSender.setImpl(str, "monitor", new DefaultLogSendImpl(context, str, "monitor"));
    }

    private LogStoreManager getLocalStore() {
        return this.mLogStoreManager;
    }

    /* Access modifiers changed, original: protected */
    public void packAndSendLog() {
        if (this.mCollectLogSwitch) {
            long currentTimeMillis = System.currentTimeMillis();
            if (getLocalStore().getLogSampledCount() > ((long) this.mReportCount) || (currentTimeMillis - this.mLastPackTime) / 1000 > ((long) this.mInterval)) {
                this.mLastPackTime = currentTimeMillis;
                List<String> reportTypeList = MonitorConfigure.getReportTypeList(this.mAid);
                if (reportTypeList != null) {
                    for (String str : reportTypeList) {
                        packOneReportType(str, MonitorConfigure.getUploadTypeByReportType(this.mAid, str), 100);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bf A:{Catch:{ Exception -> 0x00d1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c6 A:{Catch:{ Exception -> 0x00d1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c2 A:{Catch:{ Exception -> 0x00d1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bf A:{Catch:{ Exception -> 0x00d1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c6 A:{Catch:{ Exception -> 0x00d1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c2 A:{Catch:{ Exception -> 0x00d1 }} */
    public void packOneReportType(java.lang.String r20, java.util.List<java.lang.String> r21, int r22) {
        /*
        r19 = this;
        r0 = r19.getLocalStore();
        r1 = r21;
        r2 = r22;
        r0 = r0.getLogSampled(r1, r2);
        if (r0 == 0) goto L_0x0114;
    L_0x000e:
        r1 = r0.size();
        if (r1 <= 0) goto L_0x0114;
        r1 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00f9 }
        r1.<init>();	 Catch:{ Exception -> 0x00f9 }
        r2 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00f9 }
        r2.<init>();	 Catch:{ Exception -> 0x00f9 }
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00f9 }
        r3.<init>();	 Catch:{ Exception -> 0x00f9 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f9 }
        r4.<init>();	 Catch:{ Exception -> 0x00f9 }
        r5 = r0.iterator();	 Catch:{ Exception -> 0x00f9 }
        r6 = -1;
        r8 = 0;
        r9 = 1;
        r12 = r1;
        r13 = r2;
        r14 = r3;
        r15 = r6;
        r1 = r8;
        r0 = r9;
    L_0x0039:
        r2 = r5.hasNext();	 Catch:{ Exception -> 0x00f9 }
        if (r2 == 0) goto L_0x00da;
    L_0x003f:
        r2 = r5.next();	 Catch:{ Exception -> 0x00f9 }
        r2 = (com.bytedance.frameworks.core.monitor.model.LocalLog) r2;	 Catch:{ Exception -> 0x00f9 }
        r3 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1));
        if (r3 != 0) goto L_0x004e;
    L_0x0049:
        r10 = r2.versionId;	 Catch:{ Exception -> 0x00f9 }
        r3 = r0;
        r15 = r10;
        goto L_0x007c;
    L_0x004e:
        r10 = r2.versionId;	 Catch:{ Exception -> 0x00f9 }
        r3 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1));
        if (r3 == 0) goto L_0x007b;
    L_0x0054:
        r17 = 0;
        r10 = r19;
        r11 = r20;
        r3 = r10.sendLog(r11, r12, r13, r14, r15, r17);	 Catch:{ Exception -> 0x00f9 }
        if (r3 == 0) goto L_0x0064;
    L_0x0060:
        if (r0 == 0) goto L_0x0064;
    L_0x0062:
        r0 = r9;
        goto L_0x0066;
        r0 = r8;
    L_0x0066:
        r10 = r2.versionId;	 Catch:{ Exception -> 0x00f9 }
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00f9 }
        r3.<init>();	 Catch:{ Exception -> 0x00f9 }
        r12 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00f9 }
        r12.<init>();	 Catch:{ Exception -> 0x00f9 }
        r13 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00f9 }
        r13.<init>();	 Catch:{ Exception -> 0x00f9 }
        r15 = r10;
        r14 = r13;
        r13 = r12;
        r12 = r3;
    L_0x007b:
        r3 = r0;
    L_0x007c:
        r10 = r2.id;	 Catch:{ Exception -> 0x00f9 }
        r0 = r2.type;	 Catch:{ Exception -> 0x00f9 }
        r2 = r2.data;	 Catch:{ Exception -> 0x00f9 }
        if (r1 != 0) goto L_0x0088;
    L_0x0084:
        r4.append(r10);	 Catch:{ Exception -> 0x00f9 }
        goto L_0x0090;
    L_0x0088:
        r6 = ",";
        r4.append(r6);	 Catch:{ Exception -> 0x00f9 }
        r4.append(r10);	 Catch:{ Exception -> 0x00f9 }
    L_0x0090:
        r6 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00d1 }
        r6.<init>(r2);	 Catch:{ Exception -> 0x00d1 }
        r7 = r0.hashCode();	 Catch:{ Exception -> 0x00d1 }
        r2 = 94851343; // 0x5a7510f float:1.5734381E-35 double:4.686279E-316;
        if (r7 == r2) goto L_0x00af;
    L_0x009e:
        r2 = 110364485; // 0x6940745 float:5.5682094E-35 double:5.45273006E-316;
        if (r7 == r2) goto L_0x00a4;
    L_0x00a3:
        goto L_0x00ba;
    L_0x00a4:
        r2 = "timer";
        r0 = r0.equals(r2);	 Catch:{ Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00ba;
    L_0x00ac:
        r18 = r9;
        goto L_0x00bc;
    L_0x00af:
        r2 = "count";
        r0 = r0.equals(r2);	 Catch:{ Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00ba;
    L_0x00b7:
        r18 = r8;
        goto L_0x00bc;
    L_0x00ba:
        r18 = -1;
    L_0x00bc:
        switch(r18) {
            case 0: goto L_0x00c6;
            case 1: goto L_0x00c2;
            default: goto L_0x00bf;
        };	 Catch:{ Exception -> 0x00d1 }
    L_0x00bf:
        r0 = "log_id";
        goto L_0x00ca;
    L_0x00c2:
        r14.put(r6);	 Catch:{ Exception -> 0x00d1 }
        goto L_0x00d0;
    L_0x00c6:
        r13.put(r6);	 Catch:{ Exception -> 0x00d1 }
        goto L_0x00d0;
    L_0x00ca:
        r6.put(r0, r10);	 Catch:{ Exception -> 0x00d1 }
        r12.put(r6);	 Catch:{ Exception -> 0x00d1 }
    L_0x00d0:
        goto L_0x00d2;
    L_0x00d1:
        r0 = move-exception;
    L_0x00d2:
        r1 = r1 + 1;
        r0 = r3;
        r6 = -1;
        goto L_0x0039;
    L_0x00da:
        r17 = 0;
        r10 = r19;
        r11 = r20;
        r1 = r10.sendLog(r11, r12, r13, r14, r15, r17);	 Catch:{ Exception -> 0x00f9 }
        if (r1 == 0) goto L_0x00ea;
    L_0x00e6:
        if (r0 == 0) goto L_0x00ea;
    L_0x00e8:
        r8 = r9;
        goto L_0x00eb;
    L_0x00eb:
        if (r8 == 0) goto L_0x00f8;
    L_0x00ed:
        r0 = r4.toString();	 Catch:{ Exception -> 0x00f9 }
        r1 = r19.getLocalStore();	 Catch:{ Exception -> 0x00f9 }
        r1.deleteLocalLogByIds(r0);	 Catch:{ Exception -> 0x00f9 }
    L_0x00f8:
        goto L_0x0114;
    L_0x00f9:
        r0 = move-exception;
        r1 = "LogReportManager";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "json resolve failed:";
        r2.append(r3);
        r0 = android.util.Log.getStackTraceString(r0);
        r2.append(r0);
        r0 = r2.toString();
        android.util.Log.e(r1, r0);
    L_0x0114:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogReportManager.packOneReportType(java.lang.String, java.util.List, int):void");
    }

    private boolean sendLog(String str, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, long j, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (JsonUtil.hasData(jSONArray)) {
                jSONObject.put("data", jSONArray);
            }
            if (JsonUtil.hasData(jSONArray2)) {
                jSONObject.put(MonitorCommonConstants.COUNT_TYPE, jSONArray2);
            }
            if (JsonUtil.hasData(jSONArray3)) {
                jSONObject.put(MonitorCommonConstants.TIMER_TYPE, jSONArray3);
            }
            if (!JsonUtil.hasData(jSONObject) || this.mHeaderInfo == null) {
                return true;
            }
            JSONObject jSONObject2 = new JSONObject(this.mHeaderInfo.toString());
            LocalVersionInfo localVersionById = getLocalStore().getLocalVersionById(j);
            if (localVersionById != null) {
                if (!TextUtils.isEmpty(localVersionById.versionCode)) {
                    jSONObject2.put("version_code", localVersionById.versionCode);
                }
                if (!TextUtils.isEmpty(localVersionById.versionName)) {
                    jSONObject2.put(DBHelper.COL_VERSION_NAME, localVersionById.versionName);
                }
                if (!TextUtils.isEmpty(localVersionById.manifestVersionCode)) {
                    jSONObject2.put(DBHelper.COL_MANIFEST_VERSION_CODE, localVersionById.manifestVersionCode);
                }
                if (!TextUtils.isEmpty(localVersionById.updateVersionCode)) {
                    jSONObject2.put(DBHelper.COL_UPDATE_VERSION_CODE, localVersionById.updateVersionCode);
                }
            }
            jSONObject2.put("debug_fetch", z);
            jSONObject.put("header", jSONObject2);
            return MonitorLogSender.send(this.mAid, str, jSONObject.toString());
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0091 A:{Catch:{ Throwable -> 0x01e5 }} */
    public void uploadLegacyLog(com.bytedance.frameworks.core.monitor.model.UploadLogLegacyCommand r33) {
        /*
        r32 = this;
        r9 = r32;
        r0 = r33;
        r10 = r0.startTime;	 Catch:{ Throwable -> 0x01e5 }
        r12 = r0.endTime;	 Catch:{ Throwable -> 0x01e5 }
        r0 = r33.getUploadTypeList();	 Catch:{ Throwable -> 0x01e5 }
        r1 = new java.util.HashMap;	 Catch:{ Throwable -> 0x01e5 }
        r1.<init>();	 Catch:{ Throwable -> 0x01e5 }
        if (r0 == 0) goto L_0x0059;
    L_0x0016:
        r2 = r0.size();	 Catch:{ Throwable -> 0x01e5 }
        if (r2 != 0) goto L_0x001d;
    L_0x001c:
        goto L_0x0059;
    L_0x001d:
        r0 = r0.iterator();	 Catch:{ Throwable -> 0x01e5 }
    L_0x0021:
        r2 = r0.hasNext();	 Catch:{ Throwable -> 0x01e5 }
        if (r2 == 0) goto L_0x007d;
    L_0x0027:
        r2 = r0.next();	 Catch:{ Throwable -> 0x01e5 }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x01e5 }
        r3 = r9.mAid;	 Catch:{ Throwable -> 0x01e5 }
        r3 = com.bytedance.frameworks.core.monitor.config.MonitorConfigure.getReportTypeByUploadType(r3, r2);	 Catch:{ Throwable -> 0x01e5 }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x01e5 }
        if (r4 == 0) goto L_0x003a;
    L_0x0039:
        goto L_0x0021;
    L_0x003a:
        r4 = r1.containsKey(r3);	 Catch:{ Throwable -> 0x01e5 }
        if (r4 == 0) goto L_0x004d;
    L_0x0040:
        r4 = r1.get(r3);	 Catch:{ Throwable -> 0x01e5 }
        r4 = (java.util.List) r4;	 Catch:{ Throwable -> 0x01e5 }
        r4.add(r2);	 Catch:{ Throwable -> 0x01e5 }
        r1.put(r3, r4);	 Catch:{ Throwable -> 0x01e5 }
        goto L_0x0058;
    L_0x004d:
        r4 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x01e5 }
        r4.<init>();	 Catch:{ Throwable -> 0x01e5 }
        r4.add(r2);	 Catch:{ Throwable -> 0x01e5 }
        r1.put(r3, r4);	 Catch:{ Throwable -> 0x01e5 }
    L_0x0058:
        goto L_0x0021;
    L_0x0059:
        r0 = r9.mAid;	 Catch:{ Throwable -> 0x01e5 }
        r0 = com.bytedance.frameworks.core.monitor.config.MonitorConfigure.getReportTypeList(r0);	 Catch:{ Throwable -> 0x01e5 }
        if (r0 != 0) goto L_0x0062;
    L_0x0061:
        return;
    L_0x0062:
        r0 = r0.iterator();	 Catch:{ Throwable -> 0x01e5 }
    L_0x0066:
        r2 = r0.hasNext();	 Catch:{ Throwable -> 0x01e5 }
        if (r2 == 0) goto L_0x007c;
    L_0x006c:
        r2 = r0.next();	 Catch:{ Throwable -> 0x01e5 }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x01e5 }
        r3 = r9.mAid;	 Catch:{ Throwable -> 0x01e5 }
        r3 = com.bytedance.frameworks.core.monitor.config.MonitorConfigure.getUploadTypeByReportType(r3, r2);	 Catch:{ Throwable -> 0x01e5 }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x01e5 }
        goto L_0x0066;
    L_0x007d:
        r0 = r1.entrySet();	 Catch:{ Throwable -> 0x01e5 }
        r14 = r0.iterator();	 Catch:{ Throwable -> 0x01e5 }
        r16 = 1;
        r2 = r16;
        r0 = 0;
        r1 = 0;
    L_0x008b:
        r3 = r14.hasNext();	 Catch:{ Throwable -> 0x01e5 }
        if (r3 == 0) goto L_0x01e4;
    L_0x0091:
        r3 = r14.next();	 Catch:{ Throwable -> 0x01e5 }
        r3 = (java.util.Map.Entry) r3;	 Catch:{ Throwable -> 0x01e5 }
        r4 = r3.getValue();	 Catch:{ Throwable -> 0x01e5 }
        r17 = r4;
        r17 = (java.util.List) r17;	 Catch:{ Throwable -> 0x01e5 }
        r3 = r3.getKey();	 Catch:{ Throwable -> 0x01e5 }
        r18 = r3;
        r18 = (java.lang.String) r18;	 Catch:{ Throwable -> 0x01e5 }
        r8 = r0;
        r19 = r1;
        r7 = r2;
    L_0x00ab:
        r0 = r32.getLocalStore();	 Catch:{ Throwable -> 0x01e5 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01e5 }
        r1.<init>();	 Catch:{ Throwable -> 0x01e5 }
        r1.append(r8);	 Catch:{ Throwable -> 0x01e5 }
        r2 = ",";
        r1.append(r2);	 Catch:{ Throwable -> 0x01e5 }
        r6 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r1.append(r6);	 Catch:{ Throwable -> 0x01e5 }
        r20 = r1.toString();	 Catch:{ Throwable -> 0x01e5 }
        r1 = r10;
        r3 = r12;
        r5 = r17;
        r15 = r6;
        r6 = r20;
        r0 = r0.getLocalLog(r1, r3, r5, r6);	 Catch:{ Throwable -> 0x01e5 }
        if (r0 == 0) goto L_0x01ce;
    L_0x00d3:
        r1 = r0.size();	 Catch:{ Throwable -> 0x01e5 }
        if (r1 <= 0) goto L_0x01ce;
    L_0x00d9:
        r20 = r0.size();	 Catch:{ Throwable -> 0x01e5 }
        r1 = new org.json.JSONArray;	 Catch:{ Throwable -> 0x01e5 }
        r1.<init>();	 Catch:{ Throwable -> 0x01e5 }
        r6 = r0.iterator();	 Catch:{ Throwable -> 0x01e5 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01e5 }
        r5.<init>();	 Catch:{ Throwable -> 0x01e5 }
        r21 = -1;
        r23 = r1;
        r26 = r7;
        r24 = r21;
        r27 = 0;
    L_0x00f7:
        r0 = r6.hasNext();	 Catch:{ Throwable -> 0x01e5 }
        if (r0 == 0) goto L_0x0191;
    L_0x00fd:
        r0 = r6.next();	 Catch:{ Exception -> 0x017f }
        r0 = (com.bytedance.frameworks.core.monitor.model.LocalLog) r0;	 Catch:{ Exception -> 0x017f }
        r1 = (r24 > r21 ? 1 : (r24 == r21 ? 0 : -1));
        if (r1 != 0) goto L_0x0114;
    L_0x0107:
        r1 = r0.versionId;	 Catch:{ Exception -> 0x017f }
        r24 = r1;
        r15 = r5;
        r29 = r6;
        r30 = r10;
        r4 = r23;
        r10 = r8;
        goto L_0x015b;
    L_0x0114:
        r1 = r0.versionId;	 Catch:{ Exception -> 0x017f }
        r1 = (r1 > r24 ? 1 : (r1 == r24 ? 0 : -1));
        if (r1 == 0) goto L_0x0153;
    L_0x011a:
        r4 = 0;
        r7 = 0;
        r28 = 1;
        r1 = r9;
        r2 = r18;
        r3 = r23;
        r15 = r5;
        r5 = r7;
        r29 = r6;
        r6 = r24;
        r30 = r10;
        r10 = r8;
        r8 = r28;
        r1 = r1.sendLog(r2, r3, r4, r5, r6, r8);	 Catch:{ Exception -> 0x0151 }
        if (r1 == 0) goto L_0x0139;
    L_0x0134:
        if (r26 == 0) goto L_0x0139;
    L_0x0136:
        r1 = r16;
        goto L_0x013b;
        r1 = 0;
    L_0x013b:
        r2 = r0.versionId;	 Catch:{ Exception -> 0x014d }
        r4 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0147 }
        r4.<init>();	 Catch:{ Exception -> 0x0147 }
        r26 = r1;
        r24 = r2;
        goto L_0x015b;
    L_0x0147:
        r0 = move-exception;
        r26 = r1;
        r24 = r2;
        goto L_0x0186;
    L_0x014d:
        r0 = move-exception;
        r26 = r1;
        goto L_0x0186;
    L_0x0151:
        r0 = move-exception;
        goto L_0x0186;
    L_0x0153:
        r15 = r5;
        r29 = r6;
        r30 = r10;
        r10 = r8;
        r4 = r23;
    L_0x015b:
        r1 = r0.id;	 Catch:{ Exception -> 0x017b }
        if (r27 != 0) goto L_0x0163;
    L_0x015f:
        r15.append(r1);	 Catch:{ Exception -> 0x017b }
        goto L_0x016b;
    L_0x0163:
        r3 = ",";
        r15.append(r3);	 Catch:{ Exception -> 0x017b }
        r15.append(r1);	 Catch:{ Exception -> 0x017b }
    L_0x016b:
        r27 = r27 + 1;
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x017b }
        r0 = r0.data;	 Catch:{ Exception -> 0x017b }
        r1.<init>(r0);	 Catch:{ Exception -> 0x017b }
        r4.put(r1);	 Catch:{ Exception -> 0x017b }
        r23 = r4;
        goto L_0x0187;
    L_0x017b:
        r0 = move-exception;
        r23 = r4;
        goto L_0x0186;
    L_0x017f:
        r0 = move-exception;
        r15 = r5;
        r29 = r6;
        r30 = r10;
        r10 = r8;
    L_0x0187:
        r8 = r10;
        r5 = r15;
        r6 = r29;
        r10 = r30;
        r15 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        goto L_0x00f7;
    L_0x0191:
        r15 = r5;
        r30 = r10;
        r10 = r8;
        r4 = 0;
        r5 = 0;
        r8 = 1;
        r1 = r9;
        r2 = r18;
        r3 = r23;
        r6 = r24;
        r0 = r1.sendLog(r2, r3, r4, r5, r6, r8);	 Catch:{ Throwable -> 0x01e5 }
        if (r0 == 0) goto L_0x01aa;
    L_0x01a5:
        if (r26 == 0) goto L_0x01aa;
    L_0x01a7:
        r0 = r16;
        goto L_0x01ac;
        r0 = 0;
    L_0x01ac:
        if (r0 == 0) goto L_0x01c0;
    L_0x01ae:
        r1 = r15.toString();	 Catch:{ Throwable -> 0x01e5 }
        r2 = r32.getLocalStore();	 Catch:{ Throwable -> 0x01e5 }
        r1 = r2.deleteLocalLogByIds(r1);	 Catch:{ Throwable -> 0x01e5 }
        if (r1 > 0) goto L_0x01bf;
        r19 = r16;
    L_0x01bf:
        goto L_0x01c3;
        r19 = r16;
    L_0x01c3:
        if (r19 == 0) goto L_0x01c9;
    L_0x01c5:
        r8 = r10 + 400;
        r7 = r0;
        goto L_0x01cb;
    L_0x01c9:
        r7 = r0;
        r8 = r10;
    L_0x01cb:
        r0 = r20;
        goto L_0x01d3;
    L_0x01ce:
        r30 = r10;
        r10 = r8;
        r8 = r10;
        r0 = 0;
    L_0x01d3:
        r1 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r0 == r1) goto L_0x01e0;
        r2 = r7;
        r0 = r8;
        r1 = r19;
        r10 = r30;
        goto L_0x008b;
    L_0x01e0:
        r10 = r30;
        goto L_0x00ab;
    L_0x01e4:
        goto L_0x01e9;
    L_0x01e5:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x01e9:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.core.monitor.LogReportManager.uploadLegacyLog(com.bytedance.frameworks.core.monitor.model.UploadLogLegacyCommand):void");
    }

    /* Access modifiers changed, original: protected */
    public void updateConfig() {
        setReportInterval(MonitorConfigure.getReportInterval(this.mAid));
        setReportCount(MonitorConfigure.getReportCount(this.mAid));
        setHeaderInfo(MonitorConfigure.getReportJsonHeaderInfo(this.mAid));
    }

    private void setReportInterval(int i) {
        if (i > 0) {
            this.mInterval = i;
        }
    }

    private void setReportCount(int i) {
        if (i > 0) {
            this.mReportCount = i;
        }
    }

    private void setHeaderInfo(JSONObject jSONObject) {
        this.mHeaderInfo = jSONObject;
    }

    public void setCollectLogSwitch(boolean z) {
        this.mCollectLogSwitch = z;
    }
}
