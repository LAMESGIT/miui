package com.android.camera.data.data.config;

import android.support.annotation.NonNull;
import com.android.camera.CameraSettings;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera.data.provider.DataProvider.ProviderEditor;
import java.util.List;

public class ComponentConfigBeautyBody extends ComponentData {
    private static final int DEF_BEAUTY_BODY_VALUE = 0;
    private boolean mIsClosed;

    public ComponentConfigBeautyBody(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    public int getDisplayTitleString() {
        return 0;
    }

    public String getKey(int i) {
        switch (i) {
            case 161:
            case 162:
                return "_video";
            default:
                return null;
        }
    }

    @NonNull
    public String getDefaultValue(int i) {
        return null;
    }

    public List<ComponentDataItem> getItems() {
        return null;
    }

    public void setBeautyBodyValue(String str, int i) {
        setClosed(false);
        this.mParentDataItem.putInt(str, i).apply();
    }

    public int getBeautyBodyValue(String str, int i) {
        if (isClosed()) {
            return i;
        }
        return this.mParentDataItem.getInt(str, i);
    }

    public boolean isClosed() {
        return this.mIsClosed;
    }

    public void setClosed(boolean z) {
        this.mIsClosed = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0052 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0052 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0052 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0052 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050 A:{RETURN} */
    public boolean isBeautyBodyKey(java.lang.String r6) {
        /*
        r5 = this;
        r0 = android.text.TextUtils.isEmpty(r6);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = -1;
        r2 = r6.hashCode();
        r3 = -1735290593; // 0xffffffff9891911f float:-3.7628156E-24 double:NaN;
        r4 = 1;
        if (r2 == r3) goto L_0x0041;
    L_0x0013:
        r3 = -146567779; // 0xfffffffff7438d9d float:-3.9662896E33 double:NaN;
        if (r2 == r3) goto L_0x0037;
    L_0x0018:
        r3 = 1709402593; // 0x65e369e1 float:1.3424129E23 double:8.44557096E-315;
        if (r2 == r3) goto L_0x002d;
    L_0x001d:
        r3 = 1945143841; // 0x73f08a21 float:3.8115016E31 double:9.61028748E-315;
        if (r2 == r3) goto L_0x0023;
    L_0x0022:
        goto L_0x004b;
    L_0x0023:
        r2 = "pref_beauty_head_slim_ratio";
        r6 = r6.equals(r2);
        if (r6 == 0) goto L_0x004b;
    L_0x002b:
        r6 = r1;
        goto L_0x004c;
    L_0x002d:
        r2 = "pref_beauty_shoulder_slim_ratio";
        r6 = r6.equals(r2);
        if (r6 == 0) goto L_0x004b;
    L_0x0035:
        r6 = 2;
        goto L_0x004c;
    L_0x0037:
        r2 = "key_beauty_leg_slim_ratio";
        r6 = r6.equals(r2);
        if (r6 == 0) goto L_0x004b;
    L_0x003f:
        r6 = 3;
        goto L_0x004c;
    L_0x0041:
        r2 = "pref_beauty_body_slim_ratio";
        r6 = r6.equals(r2);
        if (r6 == 0) goto L_0x004b;
    L_0x0049:
        r6 = r4;
        goto L_0x004c;
    L_0x004b:
        r6 = r0;
    L_0x004c:
        switch(r6) {
            case 0: goto L_0x0053;
            case 1: goto L_0x0052;
            case 2: goto L_0x0051;
            case 3: goto L_0x0050;
            default: goto L_0x004f;
        };
    L_0x004f:
        return r1;
    L_0x0050:
        return r4;
    L_0x0051:
        return r4;
    L_0x0052:
        return r4;
    L_0x0053:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.data.data.config.ComponentConfigBeautyBody.isBeautyBodyKey(java.lang.String):boolean");
    }

    public void resetBeautyBody(int i, ProviderEditor providerEditor) {
        String str = CameraSettings.KEY_BEAUTY_HEAD_SLIM_RATIO;
        String str2 = CameraSettings.KEY_BEAUTY_BODY_SLIM_RATIO;
        String str3 = CameraSettings.KEY_BEAUTY_SHOULDER_SLIM_RATIO;
        String str4 = CameraSettings.KEY_BEAUTY_LEG_SLIM_RATIO;
        if (this.mParentDataItem.getInt(str, 0) > 0) {
            providerEditor.putInt(str, 0);
        }
        if (this.mParentDataItem.getInt(str2, 0) > 0) {
            providerEditor.putInt(str2, 0);
        }
        if (this.mParentDataItem.getInt(str3, 0) > 0) {
            providerEditor.putInt(str3, 0);
        }
        if (this.mParentDataItem.getInt(str4, 0) > 0) {
            providerEditor.putInt(str4, 0);
        }
    }
}
