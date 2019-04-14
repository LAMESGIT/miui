package com.android.camera2;

import android.graphics.Point;
import com.android.camera.log.Log;
import com.android.gallery3d.exif.ExifInterface;

public class ArcsoftDepthMap {
    private static final int BLUR_LEVEL_START_ADDR = 16;
    private static final int DAPTH_MAP_START_ADDR = 152;
    private static final int DATA_LENGTH_4 = 4;
    private static final int DATA_LENGTH_START_ADDR = 148;
    private static final int HEADER_LENGTH_START_ADDR = 4;
    private static final int HEADER_START_ADDR = 0;
    private static final int POINT_X_START_ADDR = 8;
    private static final int POINT_Y_START_ADDR = 12;
    private static final String TAG = ArcsoftDepthMap.class.getSimpleName();
    @Deprecated
    public static final int TAG_DEPTH_MAP_BLUR_LEVEL = ExifInterface.defineTag(2, (short) -30575);
    @Deprecated
    public static final int TAG_DEPTH_MAP_FOCUS_POINT = ExifInterface.defineTag(2, (short) -30576);
    private byte[] mDepthMapHeader;
    private byte[] mDepthMapOriginalData;

    public ArcsoftDepthMap(byte[] bArr) {
        if (bArr != null) {
            int headerTag = getHeaderTag(bArr);
            if (headerTag == 128) {
                this.mDepthMapOriginalData = bArr;
                this.mDepthMapHeader = getDepthMapHeader();
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal depth format! 0x80 != ");
            stringBuilder.append(headerTag);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        throw new IllegalArgumentException("Null depth data!");
    }

    public static boolean isDepthMapData(byte[] bArr) {
        boolean z = bArr != null && bArr.length > 4 && getHeaderTag(bArr) == 128;
        if (!z) {
            Log.e(TAG, "Illegal depth format");
        }
        return z;
    }

    public byte[] getDepthMapHeader() {
        return getBytes(this.mDepthMapOriginalData, 0, getInteger(getBytes(this.mDepthMapOriginalData, 4, 4)));
    }

    public Point getFocusPoint() {
        return new Point(getInteger(getBytes(this.mDepthMapHeader, 8, 4)), getInteger(getBytes(this.mDepthMapHeader, 12, 4)));
    }

    public int getBlurLevel() {
        return getInteger(getBytes(this.mDepthMapHeader, 16, 4));
    }

    public int getDepthMapLength() {
        return getInteger(getBytes(this.mDepthMapHeader, 148, 4));
    }

    public byte[] getDepthMapData() {
        return getBytes(this.mDepthMapOriginalData, 152, getDepthMapLength());
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0255  */
    public byte[] writePortraitExif(int r19, byte[] r20, byte[] r21, @android.support.annotation.NonNull int[] r22, byte[] r23, @android.support.annotation.NonNull int[] r24, int r25, boolean r26, boolean r27, com.xiaomi.camera.core.PictureInfo r28, int r29, int r30) {
        /*
        r18 = this;
        r1 = r19;
        r2 = r20;
        r3 = r21;
        r5 = r23;
        r0 = r25;
        r9 = r18.getFocusPoint();
        r10 = r18.getBlurLevel();
        r11 = r28.isFrontCamera();
        r12 = -1;
        r13 = 10;
        r14 = 5;
        if (r1 <= 0) goto L_0x0046;
    L_0x001c:
        if (r11 == 0) goto L_0x0032;
    L_0x001e:
        r11 = r28.isAiEnabled();
        if (r11 == 0) goto L_0x002e;
    L_0x0024:
        r11 = r28.getAiType();
        if (r11 != r13) goto L_0x002e;
        r12 = 70;
        goto L_0x0049;
        r12 = 40;
        goto L_0x0049;
    L_0x0032:
        r11 = r28.isAiEnabled();
        if (r11 == 0) goto L_0x0042;
    L_0x0038:
        r11 = r28.getAiType();
        if (r11 != r13) goto L_0x0042;
        r12 = 30;
        goto L_0x0049;
        r12 = r13;
        goto L_0x0049;
        r14 = r12;
    L_0x0049:
        r11 = TAG;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r15 = "writePortraitExif: focusPoint: ";
        r13.append(r15);
        r13.append(r9);
        r13 = r13.toString();
        com.android.camera.log.Log.d(r11, r13);
        r11 = TAG;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r15 = "writePortraitExif: blurLevel: ";
        r13.append(r15);
        r13.append(r10);
        r13 = r13.toString();
        com.android.camera.log.Log.d(r11, r13);
        r11 = TAG;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r15 = "writePortraitExif: shineThreshold: ";
        r13.append(r15);
        r13.append(r14);
        r13 = r13.toString();
        com.android.camera.log.Log.d(r11, r13);
        r11 = TAG;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r15 = "writePortraitExif: shineLevel: ";
        r13.append(r15);
        r13.append(r12);
        r13 = r13.toString();
        com.android.camera.log.Log.d(r11, r13);
        r11 = TAG;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r15 = "writePortraitExif: lightingPattern: ";
        r13.append(r15);
        r13.append(r0);
        r13 = r13.toString();
        com.android.camera.log.Log.d(r11, r13);
        r11 = 0;
        r13 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x00ee }
        r13.<init>();	 Catch:{ IOException -> 0x00ee }
        r15 = new com.android.gallery3d.exif.ExifInterface;	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        r15.<init>();	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        r15.readExif(r2);	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        r15.addXiaomiDepthmapVersion(r1);	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        r15.addDepthMapBlurLevel(r10);	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        r15.addPortraitLighting(r0);	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        if (r27 == 0) goto L_0x00d6;
    L_0x00d1:
        r0 = r26;
        r15.addFrontMirror(r0);	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
    L_0x00d6:
        r15.writeExif(r2, r13);	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        r15 = r13.toByteArray();	 Catch:{ Throwable -> 0x00e6, all -> 0x00e3 }
        $closeResource(r11, r13);	 Catch:{ IOException -> 0x00e1 }
        goto L_0x00f7;
    L_0x00e1:
        r0 = move-exception;
        goto L_0x00f0;
    L_0x00e3:
        r0 = move-exception;
        r15 = r11;
        goto L_0x00ea;
    L_0x00e6:
        r0 = move-exception;
        r15 = r0;
        throw r15;	 Catch:{ all -> 0x00e9 }
    L_0x00e9:
        r0 = move-exception;
    L_0x00ea:
        $closeResource(r15, r13);	 Catch:{ IOException -> 0x00ee }
        throw r0;	 Catch:{ IOException -> 0x00ee }
    L_0x00ee:
        r0 = move-exception;
        r15 = r11;
    L_0x00f0:
        r0 = TAG;
        r13 = "writePortraitExif(): Failed to write depthmap associated exif metadata";
        com.android.camera.log.Log.e(r0, r13);
    L_0x00f7:
        if (r15 == 0) goto L_0x02dc;
    L_0x00f9:
        r0 = r15.length;
        r13 = r2.length;
        if (r0 > r13) goto L_0x00ff;
    L_0x00fd:
        goto L_0x02dc;
        r0 = android.util.Xml.newSerializer();	 Catch:{ IOException -> 0x023e }
        r13 = new java.io.StringWriter;	 Catch:{ IOException -> 0x023e }
        r13.<init>();	 Catch:{ IOException -> 0x023e }
        r0.setOutput(r13);	 Catch:{ IOException -> 0x023e }
        r11 = "UTF-8";
        r16 = r15;
        r15 = 1;
        r2 = java.lang.Boolean.valueOf(r15);	 Catch:{ IOException -> 0x023c }
        r0.startDocument(r11, r2);	 Catch:{ IOException -> 0x023c }
        r2 = "depthmap";
        r11 = 0;
        r0.startTag(r11, r2);	 Catch:{ IOException -> 0x023c }
        r2 = "version";
        r1 = java.lang.String.valueOf(r19);	 Catch:{ IOException -> 0x023c }
        r0.attribute(r11, r2, r1);	 Catch:{ IOException -> 0x023c }
        r1 = "focuspoint";
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x023c }
        r2.<init>();	 Catch:{ IOException -> 0x023c }
        r11 = r9.x;	 Catch:{ IOException -> 0x023c }
        r2.append(r11);	 Catch:{ IOException -> 0x023c }
        r11 = ",";
        r2.append(r11);	 Catch:{ IOException -> 0x023c }
        r9 = r9.y;	 Catch:{ IOException -> 0x023c }
        r2.append(r9);	 Catch:{ IOException -> 0x023c }
        r2 = r2.toString();	 Catch:{ IOException -> 0x023c }
        r9 = 0;
        r0.attribute(r9, r1, r2);	 Catch:{ IOException -> 0x023c }
        r1 = "blurlevel";
        r2 = java.lang.String.valueOf(r10);	 Catch:{ IOException -> 0x023c }
        r0.attribute(r9, r1, r2);	 Catch:{ IOException -> 0x023c }
        r1 = "shinethreshold";
        r2 = java.lang.String.valueOf(r14);	 Catch:{ IOException -> 0x023c }
        r0.attribute(r9, r1, r2);	 Catch:{ IOException -> 0x023c }
        r1 = "shinelevel";
        r2 = java.lang.String.valueOf(r12);	 Catch:{ IOException -> 0x023c }
        r0.attribute(r9, r1, r2);	 Catch:{ IOException -> 0x023c }
        r1 = "rawlength";
        r2 = java.lang.String.valueOf(r29);	 Catch:{ IOException -> 0x023c }
        r0.attribute(r9, r1, r2);	 Catch:{ IOException -> 0x023c }
        r1 = "depthlength";
        r2 = java.lang.String.valueOf(r30);	 Catch:{ IOException -> 0x023c }
        r0.attribute(r9, r1, r2);	 Catch:{ IOException -> 0x023c }
        r1 = "depthmap";
        r0.endTag(r9, r1);	 Catch:{ IOException -> 0x023c }
        r1 = 3;
        r2 = 2;
        r9 = 0;
        if (r3 == 0) goto L_0x01da;
    L_0x017c:
        r10 = r3.length;	 Catch:{ IOException -> 0x023c }
        if (r10 <= 0) goto L_0x01da;
    L_0x017f:
        r10 = "lenswatermark";
        r11 = 0;
        r0.startTag(r11, r10);	 Catch:{ IOException -> 0x023c }
        r10 = "offset";
        r11 = r3.length;	 Catch:{ IOException -> 0x023c }
        if (r5 == 0) goto L_0x018c;
    L_0x018a:
        r12 = r5.length;	 Catch:{ IOException -> 0x023c }
        goto L_0x018d;
    L_0x018c:
        r12 = r9;
    L_0x018d:
        r11 = r11 + r12;
        r11 = r11 + r29;
        r11 = r11 + r30;
        r11 = java.lang.String.valueOf(r11);	 Catch:{ IOException -> 0x023c }
        r12 = 0;
        r0.attribute(r12, r10, r11);	 Catch:{ IOException -> 0x023c }
        r10 = "length";
        r11 = r3.length;	 Catch:{ IOException -> 0x023c }
        r11 = java.lang.String.valueOf(r11);	 Catch:{ IOException -> 0x023c }
        r12 = 0;
        r0.attribute(r12, r10, r11);	 Catch:{ IOException -> 0x023c }
        r10 = "width";
        r11 = r22[r9];	 Catch:{ IOException -> 0x023c }
        r11 = java.lang.String.valueOf(r11);	 Catch:{ IOException -> 0x023c }
        r12 = 0;
        r0.attribute(r12, r10, r11);	 Catch:{ IOException -> 0x023c }
        r10 = "height";
        r11 = r22[r15];	 Catch:{ IOException -> 0x023c }
        r11 = java.lang.String.valueOf(r11);	 Catch:{ IOException -> 0x023c }
        r12 = 0;
        r0.attribute(r12, r10, r11);	 Catch:{ IOException -> 0x023c }
        r10 = "paddingx";
        r11 = r22[r2];	 Catch:{ IOException -> 0x023c }
        r11 = java.lang.String.valueOf(r11);	 Catch:{ IOException -> 0x023c }
        r12 = 0;
        r0.attribute(r12, r10, r11);	 Catch:{ IOException -> 0x023c }
        r10 = "paddingy";
        r4 = r22[r1];	 Catch:{ IOException -> 0x023c }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x023c }
        r11 = 0;
        r0.attribute(r11, r10, r4);	 Catch:{ IOException -> 0x023c }
        r4 = "lenswatermark";
        r0.endTag(r11, r4);	 Catch:{ IOException -> 0x023c }
    L_0x01da:
        if (r5 == 0) goto L_0x0234;
    L_0x01dc:
        r4 = r5.length;	 Catch:{ IOException -> 0x023c }
        if (r4 <= 0) goto L_0x0234;
    L_0x01df:
        r4 = "timewatermark";
        r10 = 0;
        r0.startTag(r10, r4);	 Catch:{ IOException -> 0x023c }
        r4 = "offset";
        r10 = r5.length;	 Catch:{ IOException -> 0x023c }
        r10 = r10 + r29;
        r10 = r10 + r30;
        r7 = java.lang.String.valueOf(r10);	 Catch:{ IOException -> 0x023c }
        r8 = 0;
        r0.attribute(r8, r4, r7);	 Catch:{ IOException -> 0x023c }
        r4 = "length";
        r7 = r5.length;	 Catch:{ IOException -> 0x023c }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IOException -> 0x023c }
        r8 = 0;
        r0.attribute(r8, r4, r7);	 Catch:{ IOException -> 0x023c }
        r4 = "width";
        r7 = r24[r9];	 Catch:{ IOException -> 0x023c }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IOException -> 0x023c }
        r8 = 0;
        r0.attribute(r8, r4, r7);	 Catch:{ IOException -> 0x023c }
        r4 = "height";
        r7 = r24[r15];	 Catch:{ IOException -> 0x023c }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IOException -> 0x023c }
        r8 = 0;
        r0.attribute(r8, r4, r7);	 Catch:{ IOException -> 0x023c }
        r4 = "paddingx";
        r2 = r24[r2];	 Catch:{ IOException -> 0x023c }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x023c }
        r7 = 0;
        r0.attribute(r7, r4, r2);	 Catch:{ IOException -> 0x023c }
        r2 = "paddingy";
        r1 = r24[r1];	 Catch:{ IOException -> 0x023c }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ IOException -> 0x023c }
        r4 = 0;
        r0.attribute(r4, r2, r1);	 Catch:{ IOException -> 0x023c }
        r1 = "timewatermark";
        r0.endTag(r4, r1);	 Catch:{ IOException -> 0x023c }
    L_0x0234:
        r0.endDocument();	 Catch:{ IOException -> 0x023c }
        r11 = r13.toString();	 Catch:{ IOException -> 0x023c }
        goto L_0x0249;
    L_0x023c:
        r0 = move-exception;
        goto L_0x0241;
    L_0x023e:
        r0 = move-exception;
        r16 = r15;
    L_0x0241:
        r0 = TAG;
        r1 = "writePortraitExif(): Failed to generate depthmap associated xmp metadata";
        com.android.camera.log.Log.e(r0, r1);
        r11 = 0;
    L_0x0249:
        if (r11 != 0) goto L_0x0255;
    L_0x024b:
        r0 = TAG;
        r1 = "writePortraitExif(): #2: return original jpeg";
        com.android.camera.log.Log.e(r0, r1);
        r1 = r20;
        return r1;
    L_0x0255:
        r1 = r20;
        r2 = new java.io.ByteArrayInputStream;	 Catch:{ XMPException | IOException -> 0x02c0, XMPException | IOException -> 0x02c0 }
        r15 = r16;
        r2.<init>(r15);	 Catch:{ XMPException | IOException -> 0x02be, XMPException | IOException -> 0x02be }
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x02ae, all -> 0x02aa }
        r4.<init>();	 Catch:{ Throwable -> 0x02ae, all -> 0x02aa }
        r0 = com.android.camera.XmpHelper.createXMPMeta();	 Catch:{ Throwable -> 0x029d, all -> 0x0299 }
        r6 = "http://ns.xiaomi.com/photos/1.0/camera/";
        r7 = "XMPMeta";
        r0.setProperty(r6, r7, r11);	 Catch:{ Throwable -> 0x029d, all -> 0x0299 }
        com.android.camera.XmpHelper.writeXMPMeta(r2, r4, r0);	 Catch:{ Throwable -> 0x029d, all -> 0x0299 }
        if (r3 == 0) goto L_0x0280;
    L_0x0274:
        r4.write(r3);	 Catch:{ Throwable -> 0x027c, all -> 0x0278 }
        goto L_0x0280;
    L_0x0278:
        r0 = move-exception;
        r3 = 0;
        r11 = 0;
        goto L_0x02a2;
    L_0x027c:
        r0 = move-exception;
        r11 = r0;
        r3 = 0;
        goto L_0x02a0;
    L_0x0280:
        if (r5 == 0) goto L_0x0285;
    L_0x0282:
        r4.write(r5);	 Catch:{ Throwable -> 0x027c, all -> 0x0278 }
    L_0x0285:
        r4.flush();	 Catch:{ Throwable -> 0x029d, all -> 0x0299 }
        r11 = r4.toByteArray();	 Catch:{ Throwable -> 0x029d, all -> 0x0299 }
        r3 = 0;
        $closeResource(r3, r4);	 Catch:{ Throwable -> 0x0296, all -> 0x0294 }
        $closeResource(r3, r2);	 Catch:{ XMPException | IOException -> 0x02bc, XMPException | IOException -> 0x02bc }
        goto L_0x02cc;
    L_0x0294:
        r0 = move-exception;
        goto L_0x02b8;
    L_0x0296:
        r0 = move-exception;
        r3 = r11;
        goto L_0x02b0;
    L_0x0299:
        r0 = move-exception;
        r3 = 0;
        r11 = r3;
        goto L_0x02a2;
    L_0x029d:
        r0 = move-exception;
        r3 = 0;
        r11 = r0;
    L_0x02a0:
        throw r11;	 Catch:{ all -> 0x02a1 }
    L_0x02a1:
        r0 = move-exception;
    L_0x02a2:
        $closeResource(r11, r4);	 Catch:{ Throwable -> 0x02a8, all -> 0x02a6 }
        throw r0;	 Catch:{ Throwable -> 0x02a8, all -> 0x02a6 }
    L_0x02a6:
        r0 = move-exception;
        goto L_0x02ac;
    L_0x02a8:
        r0 = move-exception;
        goto L_0x02b0;
    L_0x02aa:
        r0 = move-exception;
        r3 = 0;
    L_0x02ac:
        r11 = r3;
        goto L_0x02b8;
    L_0x02ae:
        r0 = move-exception;
        r3 = 0;
    L_0x02b0:
        r11 = r0;
        throw r11;	 Catch:{ all -> 0x02b2 }
    L_0x02b2:
        r0 = move-exception;
        r17 = r11;
        r11 = r3;
        r3 = r17;
    L_0x02b8:
        $closeResource(r3, r2);	 Catch:{ XMPException | IOException -> 0x02bc, XMPException | IOException -> 0x02bc }
        throw r0;	 Catch:{ XMPException | IOException -> 0x02bc, XMPException | IOException -> 0x02bc }
    L_0x02bc:
        r0 = move-exception;
        goto L_0x02c5;
    L_0x02be:
        r0 = move-exception;
        goto L_0x02c3;
    L_0x02c0:
        r0 = move-exception;
        r15 = r16;
    L_0x02c3:
        r3 = 0;
        r11 = r3;
    L_0x02c5:
        r0 = TAG;
        r2 = "writePortraitExif(): Failed to insert depthmap associated xmp metadata";
        com.android.camera.log.Log.d(r0, r2);
    L_0x02cc:
        if (r11 == 0) goto L_0x02d4;
    L_0x02ce:
        r0 = r11.length;
        r2 = r15.length;
        if (r0 > r2) goto L_0x02d3;
    L_0x02d2:
        goto L_0x02d4;
    L_0x02d3:
        return r11;
    L_0x02d4:
        r0 = TAG;
        r2 = "writePortraitExif(): #3: return original jpeg";
        com.android.camera.log.Log.e(r0, r2);
        return r1;
    L_0x02dc:
        r1 = r2;
        r0 = TAG;
        r2 = "writePortraitExif(): #1: return original jpeg";
        com.android.camera.log.Log.e(r0, r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera2.ArcsoftDepthMap.writePortraitExif(int, byte[], byte[], int[], byte[], int[], int, boolean, boolean, com.xiaomi.camera.core.PictureInfo, int, int):byte[]");
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

    private static int getHeaderTag(byte[] bArr) {
        return getInteger(getBytes(bArr, 0, 4));
    }

    private static int getInteger(byte[] bArr) {
        if (bArr.length == 4) {
            int i = 0;
            int i2 = 0;
            while (i < 4) {
                i2 += (bArr[i] & 255) << (i * 8);
                i++;
            }
            return i2;
        }
        throw new IllegalArgumentException("bytes can not covert to a integer value!");
    }

    private static byte[] getBytes(byte[] bArr, int i, int i2) {
        if (i2 <= 0 || i < 0 || i2 > bArr.length - i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("WRONG ARGUMENT: from =");
            stringBuilder.append(i);
            stringBuilder.append(", length = ");
            stringBuilder.append(i2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
