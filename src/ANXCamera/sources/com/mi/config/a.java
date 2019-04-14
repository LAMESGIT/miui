package com.mi.config;

import android.os.Build.VERSION;
import android.os.SystemProperties;
import android.support.v4.util.SimpleArrayMap;
import com.android.camera.Util;
import com.android.camera.constant.BeautyConstant;
import com.android.camera.data.data.DataItemBase;
import java.util.Iterator;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DataItemFeature */
public class a extends DataItemBase implements c {
    private static final String TAG = "DataFeature";
    private String pD;

    public a() {
        eO();
    }

    public String provideKey() {
        return null;
    }

    public boolean isTransient() {
        return true;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x0071=Splitter:B:28:0x0071, B:22:0x0067=Splitter:B:22:0x0067} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A:{SYNTHETIC, Splitter:B:31:0x0076} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c A:{SYNTHETIC, Splitter:B:25:0x006c} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0082 A:{SYNTHETIC, Splitter:B:37:0x0082} */
    public void eO() {
        /*
        r6 = this;
        r0 = com.android.camera.CameraAppImpl.getAndroidContext();
        r1 = r0.getResources();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "feature_";
        r2.append(r3);
        r3 = com.mi.config.b.pE;
        r2.append(r3);
        r2 = r2.toString();
        r3 = "raw";
        r4 = "com.android.camera";
        r1 = r1.getIdentifier(r2, r3, r4);
        if (r1 > 0) goto L_0x002d;
    L_0x0025:
        r0 = "DataFeature";
        r1 = "feature list default";
        com.android.camera.log.Log.e(r0, r1);
        return;
    L_0x002d:
        r2 = 0;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0070, JSONException -> 0x0066 }
        r5 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0070, JSONException -> 0x0066 }
        r0 = r0.getResources();	 Catch:{ IOException -> 0x0070, JSONException -> 0x0066 }
        r0 = r0.openRawResource(r1);	 Catch:{ IOException -> 0x0070, JSONException -> 0x0066 }
        r5.<init>(r0);	 Catch:{ IOException -> 0x0070, JSONException -> 0x0066 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x0070, JSONException -> 0x0066 }
    L_0x0045:
        r0 = r4.readLine();	 Catch:{ IOException -> 0x0061, JSONException -> 0x005e, all -> 0x005b }
        if (r0 == 0) goto L_0x004f;
    L_0x004b:
        r3.append(r0);	 Catch:{ IOException -> 0x0061, JSONException -> 0x005e, all -> 0x005b }
        goto L_0x0045;
    L_0x004f:
        r0 = r3.toString();	 Catch:{ IOException -> 0x0061, JSONException -> 0x005e, all -> 0x005b }
        r6.A(r0);	 Catch:{ IOException -> 0x0061, JSONException -> 0x005e, all -> 0x005b }
        r4.close();	 Catch:{ IOException -> 0x007a }
        goto L_0x0079;
    L_0x005b:
        r0 = move-exception;
        r2 = r4;
        goto L_0x0080;
    L_0x005e:
        r0 = move-exception;
        r2 = r4;
        goto L_0x0067;
    L_0x0061:
        r0 = move-exception;
        r2 = r4;
        goto L_0x0071;
    L_0x0064:
        r0 = move-exception;
        goto L_0x0080;
    L_0x0066:
        r0 = move-exception;
    L_0x0067:
        r0.printStackTrace();	 Catch:{ all -> 0x0064 }
        if (r2 == 0) goto L_0x007f;
    L_0x006c:
        r2.close();	 Catch:{ IOException -> 0x007a }
        goto L_0x0079;
    L_0x0070:
        r0 = move-exception;
    L_0x0071:
        r0.printStackTrace();	 Catch:{ all -> 0x0064 }
        if (r2 == 0) goto L_0x007f;
    L_0x0076:
        r2.close();	 Catch:{ IOException -> 0x007a }
    L_0x0079:
        goto L_0x007f;
    L_0x007a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0079;
    L_0x007f:
        return;
    L_0x0080:
        if (r2 == 0) goto L_0x008a;
    L_0x0082:
        r2.close();	 Catch:{ IOException -> 0x0086 }
        goto L_0x008a;
    L_0x0086:
        r1 = move-exception;
        r1.printStackTrace();
    L_0x008a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.config.a.eO():void");
    }

    private void A(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        Iterator keys = jSONObject.keys();
        SimpleArrayMap values = getValues();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            values.put(str2, jSONObject.opt(str2));
        }
    }

    public boolean B(String str) {
        return getValues().containsKey(str);
    }

    public boolean eP() {
        return getBoolean(c.rf, false);
    }

    public int eQ() {
        return getInt(c.rg, 20);
    }

    public boolean eR() {
        if (this.pD == null) {
            this.pD = SystemProperties.get("ro.boot.hwc");
        }
        return "india".equalsIgnoreCase(this.pD);
    }

    public boolean eS() {
        return Build.getRegion().endsWith("IN");
    }

    public boolean eT() {
        if (this.pD == null) {
            this.pD = SystemProperties.get("ro.boot.hwc");
        }
        return "cn".equalsIgnoreCase(this.pD);
    }

    public boolean eU() {
        return getBoolean(c.rh, false) && eR();
    }

    public boolean eV() {
        return getBoolean(c.sc, false) && eR();
    }

    public boolean eW() {
        return getBoolean(c.ri, false);
    }

    public boolean eX() {
        return getBoolean(c.rj, false);
    }

    public boolean eY() {
        return getBoolean(c.rk, false);
    }

    public boolean eZ() {
        return getBoolean(c.rL, false);
    }

    public boolean fa() {
        return getBoolean(c.rl, false);
    }

    public boolean fb() {
        return getBoolean(c.rm, false);
    }

    public boolean fc() {
        return (eR() || eS()) && getBoolean(c.rn, false);
    }

    public boolean fd() {
        return getBoolean(c.ro, false);
    }

    public boolean fe() {
        return getBoolean(c.rp, false);
    }

    public boolean ff() {
        return getBoolean(c.rq, false);
    }

    public boolean fg() {
        return getBoolean(c.rr, false);
    }

    public boolean fh() {
        return getBoolean(c.rs, false);
    }

    public boolean fi() {
        return getBoolean(c.rt, false);
    }

    public boolean fj() {
        return getBoolean(c.ru, false);
    }

    public boolean fk() {
        if (VERSION.SDK_INT == 28 && getBoolean(c.rB, false)) {
            return true;
        }
        return false;
    }

    public boolean fl() {
        return getBoolean(c.rC, false);
    }

    public boolean fm() {
        return getBoolean(c.rI, true);
    }

    public boolean fn() {
        return getBoolean(c.rJ, false);
    }

    public boolean fo() {
        return getBoolean(c.rD, false);
    }

    public boolean fp() {
        return getBoolean(c.rv, false);
    }

    public int fq() {
        return getInt(c.sb, 180);
    }

    public boolean fr() {
        return getBoolean(c.rw, false);
    }

    public boolean fs() {
        return getBoolean(c.rA, false);
    }

    public boolean ft() {
        return getBoolean(c.rz, false);
    }

    public boolean fu() {
        return getBoolean(c.rx, false);
    }

    public boolean fv() {
        return getBoolean(c.ry, false) || fu();
    }

    public boolean fw() {
        return getBoolean(c.rE, false);
    }

    public String fx() {
        return getString(c.rF, "");
    }

    public int fy() {
        return getInt(c.rG, 350);
    }

    public int fz() {
        return getInt(c.rH, 300);
    }

    public boolean isSupportUltraWide() {
        return getBoolean(c.rK, false);
    }

    public boolean fA() {
        return getBoolean(c.rN, true);
    }

    public boolean isSupportBeautyBody() {
        return getBoolean(c.rM, false);
    }

    public boolean fB() {
        return getBoolean(c.rT, false);
    }

    public boolean fC() {
        return getBoolean(c.so, false);
    }

    public boolean fD() {
        return getBoolean(c.sq, true);
    }

    public boolean fE() {
        if (Util.isGlobalVersion()) {
            return false;
        }
        return getBoolean(c.rP, false);
    }

    public int fF() {
        return getInt(c.rQ, 280);
    }

    public float fG() {
        return (float) getDoubleFromValues(c.rR, 0.8766000270843506d);
    }

    public boolean fH() {
        if (Util.isGlobalVersion()) {
            return false;
        }
        return getBoolean(c.rS, false);
    }

    public boolean fI() {
        return getBoolean(c.rO, false);
    }

    public boolean isSupportNormalWideLDC() {
        return getBoolean(c.rU, false);
    }

    public boolean isSupportUltraWideLDC() {
        return getBoolean(c.rV, false);
    }

    public boolean isSupport4KUHDEIS() {
        return getBoolean(c.rW, false);
    }

    public boolean fJ() {
        return getBoolean(c.rX, false);
    }

    public boolean fK() {
        return getBoolean(c.rY, false);
    }

    public boolean fL() {
        return getBoolean(c.rZ, false);
    }

    public boolean fM() {
        return getBoolean(c.sh, true);
    }

    public boolean fN() {
        return getBoolean(c.si, false);
    }

    public int fO() {
        return getInt(c.sk, 0);
    }

    /* Access modifiers changed, original: protected */
    public boolean isMutable() {
        return false;
    }

    public boolean fP() {
        return getBoolean(c.sf, false);
    }

    public boolean fQ() {
        return fR() <= 0 || getBoolean(c.sg, false);
    }

    public int fR() {
        return getInt(c.se, 0);
    }

    public int fS() {
        return getInt(c.sj, 0);
    }

    public boolean fT() {
        return getBoolean(c.sl, false);
    }

    public String fU() {
        return getString(c.sm, BeautyConstant.LEVEL_CLOSE);
    }

    public boolean fV() {
        return getBoolean(c.sn, false);
    }

    public boolean fW() {
        if (b.ie()) {
            return false;
        }
        return true;
    }

    public boolean fX() {
        return getBoolean(c.sr, true);
    }

    public boolean fY() {
        return getBoolean(c.ss, false);
    }

    public boolean isSupportBokehAdjust() {
        return getBoolean(c.st, false);
    }

    public boolean fZ() {
        if (VERSION.SDK_INT < 28) {
            return false;
        }
        return getBoolean(c.su, false);
    }

    public boolean ga() {
        return getBoolean(c.sv, false);
    }

    public boolean gb() {
        return getBoolean(c.sw, false);
    }

    public boolean gd() {
        return getBoolean(c.sx, false);
    }

    public boolean ge() {
        return getBoolean(c.sy, false);
    }
}
