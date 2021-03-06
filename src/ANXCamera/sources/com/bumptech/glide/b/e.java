package com.bumptech.glide.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.b.a.a;
import com.sensetime.stmobile.STMobileHumanActionNative;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: StandardGifDecoder */
public class e implements a {
    private static final int MAX_STACK_SIZE = 4096;
    private static final String TAG = e.class.getSimpleName();
    private static final int bF = 255;
    private static final int ca = -1;
    private static final int cb = -1;
    private static final int cc = 4;
    @ColorInt
    private static final int cd = 0;
    @ColorInt
    private int[] act;
    private ByteBuffer bY;
    private c bZ;
    private byte[] block;
    @ColorInt
    private final int[] ce;
    private final a cf;
    private d cg;
    private byte[] ci;
    @ColorInt
    private int[] cj;
    private int ck;
    private Bitmap cl;
    private boolean cm;
    private int cn;
    private int cp;
    @Nullable
    private Boolean cq;
    @NonNull
    private Config cr;
    private byte[] pixelStack;
    private short[] prefix;
    private int sampleSize;
    private int status;
    private byte[] suffix;

    public e(@NonNull a aVar, c cVar, ByteBuffer byteBuffer) {
        this(aVar, cVar, byteBuffer, 1);
    }

    public e(@NonNull a aVar, c cVar, ByteBuffer byteBuffer, int i) {
        this(aVar);
        a(cVar, byteBuffer, i);
    }

    public e(@NonNull a aVar) {
        this.ce = new int[256];
        this.cr = Config.ARGB_8888;
        this.cf = aVar;
        this.bZ = new c();
    }

    public int getWidth() {
        return this.bZ.width;
    }

    public int getHeight() {
        return this.bZ.height;
    }

    @NonNull
    public ByteBuffer getData() {
        return this.bY;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.ck = (this.ck + 1) % this.bZ.bC;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.bZ.bC) {
            return -1;
        }
        return ((b) this.bZ.bE.get(i)).delay;
    }

    public int O() {
        if (this.bZ.bC <= 0 || this.ck < 0) {
            return 0;
        }
        return getDelay(this.ck);
    }

    public int getFrameCount() {
        return this.bZ.bC;
    }

    public int P() {
        return this.ck;
    }

    public void Q() {
        this.ck = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.bZ.loopCount == -1) {
            return 1;
        }
        return this.bZ.loopCount;
    }

    public int R() {
        return this.bZ.loopCount;
    }

    public int S() {
        if (this.bZ.loopCount == -1) {
            return 1;
        }
        if (this.bZ.loopCount == 0) {
            return 0;
        }
        return this.bZ.loopCount + 1;
    }

    public int T() {
        return (this.bY.limit() + this.ci.length) + (this.cj.length * 4);
    }

    /* JADX WARNING: Missing block: B:46:0x00ea, code skipped:
            return null;
     */
    @android.support.annotation.Nullable
    public synchronized android.graphics.Bitmap U() {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.bZ;	 Catch:{ all -> 0x00eb }
        r0 = r0.bC;	 Catch:{ all -> 0x00eb }
        r1 = 3;
        r2 = 1;
        if (r0 <= 0) goto L_0x000d;
    L_0x0009:
        r0 = r7.ck;	 Catch:{ all -> 0x00eb }
        if (r0 >= 0) goto L_0x003b;
    L_0x000d:
        r0 = TAG;	 Catch:{ all -> 0x00eb }
        r0 = android.util.Log.isLoggable(r0, r1);	 Catch:{ all -> 0x00eb }
        if (r0 == 0) goto L_0x0039;
    L_0x0015:
        r0 = TAG;	 Catch:{ all -> 0x00eb }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00eb }
        r3.<init>();	 Catch:{ all -> 0x00eb }
        r4 = "Unable to decode frame, frameCount=";
        r3.append(r4);	 Catch:{ all -> 0x00eb }
        r4 = r7.bZ;	 Catch:{ all -> 0x00eb }
        r4 = r4.bC;	 Catch:{ all -> 0x00eb }
        r3.append(r4);	 Catch:{ all -> 0x00eb }
        r4 = ", framePointer=";
        r3.append(r4);	 Catch:{ all -> 0x00eb }
        r4 = r7.ck;	 Catch:{ all -> 0x00eb }
        r3.append(r4);	 Catch:{ all -> 0x00eb }
        r3 = r3.toString();	 Catch:{ all -> 0x00eb }
        android.util.Log.d(r0, r3);	 Catch:{ all -> 0x00eb }
    L_0x0039:
        r7.status = r2;	 Catch:{ all -> 0x00eb }
    L_0x003b:
        r0 = r7.status;	 Catch:{ all -> 0x00eb }
        r3 = 0;
        if (r0 == r2) goto L_0x00c9;
    L_0x0040:
        r0 = r7.status;	 Catch:{ all -> 0x00eb }
        r4 = 2;
        if (r0 != r4) goto L_0x0047;
    L_0x0045:
        goto L_0x00c9;
    L_0x0047:
        r0 = 0;
        r7.status = r0;	 Catch:{ all -> 0x00eb }
        r4 = r7.block;	 Catch:{ all -> 0x00eb }
        if (r4 != 0) goto L_0x0058;
    L_0x004e:
        r4 = r7.cf;	 Catch:{ all -> 0x00eb }
        r5 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r4 = r4.i(r5);	 Catch:{ all -> 0x00eb }
        r7.block = r4;	 Catch:{ all -> 0x00eb }
    L_0x0058:
        r4 = r7.bZ;	 Catch:{ all -> 0x00eb }
        r4 = r4.bE;	 Catch:{ all -> 0x00eb }
        r5 = r7.ck;	 Catch:{ all -> 0x00eb }
        r4 = r4.get(r5);	 Catch:{ all -> 0x00eb }
        r4 = (com.bumptech.glide.b.b) r4;	 Catch:{ all -> 0x00eb }
        r5 = r7.ck;	 Catch:{ all -> 0x00eb }
        r5 = r5 - r2;
        if (r5 < 0) goto L_0x0075;
    L_0x006a:
        r6 = r7.bZ;	 Catch:{ all -> 0x00eb }
        r6 = r6.bE;	 Catch:{ all -> 0x00eb }
        r5 = r6.get(r5);	 Catch:{ all -> 0x00eb }
        r5 = (com.bumptech.glide.b.b) r5;	 Catch:{ all -> 0x00eb }
        goto L_0x0076;
    L_0x0075:
        r5 = r3;
    L_0x0076:
        r6 = r4.lct;	 Catch:{ all -> 0x00eb }
        if (r6 == 0) goto L_0x007d;
    L_0x007a:
        r6 = r4.lct;	 Catch:{ all -> 0x00eb }
        goto L_0x0081;
    L_0x007d:
        r6 = r7.bZ;	 Catch:{ all -> 0x00eb }
        r6 = r6.gct;	 Catch:{ all -> 0x00eb }
    L_0x0081:
        r7.act = r6;	 Catch:{ all -> 0x00eb }
        r6 = r7.act;	 Catch:{ all -> 0x00eb }
        if (r6 != 0) goto L_0x00ab;
    L_0x0087:
        r0 = TAG;	 Catch:{ all -> 0x00eb }
        r0 = android.util.Log.isLoggable(r0, r1);	 Catch:{ all -> 0x00eb }
        if (r0 == 0) goto L_0x00a7;
    L_0x008f:
        r0 = TAG;	 Catch:{ all -> 0x00eb }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00eb }
        r1.<init>();	 Catch:{ all -> 0x00eb }
        r4 = "No valid color table found for frame #";
        r1.append(r4);	 Catch:{ all -> 0x00eb }
        r4 = r7.ck;	 Catch:{ all -> 0x00eb }
        r1.append(r4);	 Catch:{ all -> 0x00eb }
        r1 = r1.toString();	 Catch:{ all -> 0x00eb }
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x00eb }
    L_0x00a7:
        r7.status = r2;	 Catch:{ all -> 0x00eb }
        monitor-exit(r7);
        return r3;
    L_0x00ab:
        r1 = r4.transparency;	 Catch:{ all -> 0x00eb }
        if (r1 == 0) goto L_0x00c3;
    L_0x00af:
        r1 = r7.act;	 Catch:{ all -> 0x00eb }
        r2 = r7.ce;	 Catch:{ all -> 0x00eb }
        r3 = r7.act;	 Catch:{ all -> 0x00eb }
        r3 = r3.length;	 Catch:{ all -> 0x00eb }
        java.lang.System.arraycopy(r1, r0, r2, r0, r3);	 Catch:{ all -> 0x00eb }
        r1 = r7.ce;	 Catch:{ all -> 0x00eb }
        r7.act = r1;	 Catch:{ all -> 0x00eb }
        r1 = r7.act;	 Catch:{ all -> 0x00eb }
        r2 = r4.transIndex;	 Catch:{ all -> 0x00eb }
        r1[r2] = r0;	 Catch:{ all -> 0x00eb }
    L_0x00c3:
        r0 = r7.a(r4, r5);	 Catch:{ all -> 0x00eb }
        monitor-exit(r7);
        return r0;
    L_0x00c9:
        r0 = TAG;	 Catch:{ all -> 0x00eb }
        r0 = android.util.Log.isLoggable(r0, r1);	 Catch:{ all -> 0x00eb }
        if (r0 == 0) goto L_0x00e9;
    L_0x00d1:
        r0 = TAG;	 Catch:{ all -> 0x00eb }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00eb }
        r1.<init>();	 Catch:{ all -> 0x00eb }
        r2 = "Unable to decode frame, status=";
        r1.append(r2);	 Catch:{ all -> 0x00eb }
        r2 = r7.status;	 Catch:{ all -> 0x00eb }
        r1.append(r2);	 Catch:{ all -> 0x00eb }
        r1 = r1.toString();	 Catch:{ all -> 0x00eb }
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x00eb }
    L_0x00e9:
        monitor-exit(r7);
        return r3;
    L_0x00eb:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.e.U():android.graphics.Bitmap");
    }

    public int a(@Nullable InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : STMobileHumanActionNative.ST_MOBILE_HAND_LOVE);
                byte[] bArr = new byte[STMobileHumanActionNative.ST_MOBILE_HAND_LOVE];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.bZ = null;
        if (this.ci != null) {
            this.cf.c(this.ci);
        }
        if (this.cj != null) {
            this.cf.a(this.cj);
        }
        if (this.cl != null) {
            this.cf.c(this.cl);
        }
        this.cl = null;
        this.bY = null;
        this.cq = null;
        if (this.block != null) {
            this.cf.c(this.block);
        }
    }

    public synchronized void a(@NonNull c cVar, @NonNull byte[] bArr) {
        a(cVar, ByteBuffer.wrap(bArr));
    }

    public synchronized void a(@NonNull c cVar, @NonNull ByteBuffer byteBuffer) {
        a(cVar, byteBuffer, 1);
    }

    public synchronized void a(@NonNull c cVar, @NonNull ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            i = Integer.highestOneBit(i);
            this.status = 0;
            this.bZ = cVar;
            this.ck = -1;
            this.bY = byteBuffer.asReadOnlyBuffer();
            this.bY.position(0);
            this.bY.order(ByteOrder.LITTLE_ENDIAN);
            this.cm = false;
            for (b bVar : cVar.bE) {
                if (bVar.dispose == 3) {
                    this.cm = true;
                    break;
                }
            }
            this.sampleSize = i;
            this.cp = cVar.width / i;
            this.cn = cVar.height / i;
            this.ci = this.cf.i(cVar.width * cVar.height);
            this.cj = this.cf.j(this.cp * this.cn);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Sample size must be >=0, not: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    @NonNull
    private d Z() {
        if (this.cg == null) {
            this.cg = new d();
        }
        return this.cg;
    }

    public synchronized int read(@Nullable byte[] bArr) {
        this.bZ = Z().d(bArr).W();
        if (bArr != null) {
            a(this.bZ, bArr);
        }
        return this.status;
    }

    public void a(@NonNull Config config) {
        if (config == Config.ARGB_8888 || config == Config.RGB_565) {
            this.cr = config;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported format: ");
        stringBuilder.append(config);
        stringBuilder.append(", must be one of ");
        stringBuilder.append(Config.ARGB_8888);
        stringBuilder.append(" or ");
        stringBuilder.append(Config.RGB_565);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private Bitmap a(b bVar, b bVar2) {
        int[] iArr = this.cj;
        int i = 0;
        if (bVar2 == null) {
            if (this.cl != null) {
                this.cf.c(this.cl);
            }
            this.cl = null;
            Arrays.fill(iArr, 0);
        }
        if (bVar2 != null && bVar2.dispose == 3 && this.cl == null) {
            Arrays.fill(iArr, 0);
        }
        if (bVar2 != null && bVar2.dispose > 0) {
            if (bVar2.dispose == 2) {
                int i2;
                if (!bVar.transparency) {
                    i2 = this.bZ.bgColor;
                    if (bVar.lct == null || this.bZ.bgIndex != bVar.transIndex) {
                        i = i2;
                    }
                } else if (this.ck == 0) {
                    this.cq = Boolean.valueOf(true);
                }
                i2 = bVar2.ih / this.sampleSize;
                int i3 = bVar2.iy / this.sampleSize;
                int i4 = bVar2.iw / this.sampleSize;
                i3 = (i3 * this.cp) + (bVar2.ix / this.sampleSize);
                i2 = (i2 * this.cp) + i3;
                while (i3 < i2) {
                    int i5 = i3 + i4;
                    for (int i6 = i3; i6 < i5; i6++) {
                        iArr[i6] = i;
                    }
                    i3 += this.cp;
                }
            } else if (bVar2.dispose == 3 && this.cl != null) {
                this.cl.getPixels(iArr, 0, this.cp, 0, 0, this.cp, this.cn);
            }
        }
        c(bVar);
        if (bVar.interlace || this.sampleSize != 1) {
            b(bVar);
        } else {
            a(bVar);
        }
        if (this.cm && (bVar.dispose == 0 || bVar.dispose == 1)) {
            if (this.cl == null) {
                this.cl = aa();
            }
            this.cl.setPixels(iArr, 0, this.cp, 0, 0, this.cp, this.cn);
        }
        Bitmap aa = aa();
        aa.setPixels(iArr, 0, this.cp, 0, 0, this.cp, this.cn);
        return aa;
    }

    private void a(b bVar) {
        b bVar2 = bVar;
        int[] iArr = this.cj;
        int i = bVar2.ih;
        int i2 = bVar2.iy;
        int i3 = bVar2.iw;
        int i4 = bVar2.ix;
        Object obj = this.ck == 0 ? 1 : null;
        int i5 = this.cp;
        byte[] bArr = this.ci;
        int[] iArr2 = this.act;
        int i6 = 0;
        int i7 = -1;
        while (i6 < i) {
            int i8;
            int i9 = (i6 + i2) * i5;
            int i10 = i9 + i4;
            int i11 = i10 + i3;
            int i12 = i9 + i5;
            if (i12 < i11) {
                i11 = i12;
            }
            int i13 = i7;
            i7 = bVar2.iw * i6;
            i12 = i10;
            while (i12 < i11) {
                byte b = bArr[i7];
                i8 = i;
                i = b & 255;
                if (i != i13) {
                    i = iArr2[i];
                    if (i != 0) {
                        iArr[i12] = i;
                    } else {
                        i13 = b;
                    }
                }
                i7++;
                i12++;
                i = i8;
                bVar2 = bVar;
            }
            i8 = i;
            i6++;
            i7 = i13;
            bVar2 = bVar;
        }
        boolean z = (this.cq != null || obj == null || i7 == -1) ? false : true;
        this.cq = Boolean.valueOf(z);
    }

    private void b(b bVar) {
        b bVar2 = bVar;
        int[] iArr = this.cj;
        int i = bVar2.ih / this.sampleSize;
        int i2 = bVar2.iy / this.sampleSize;
        int i3 = bVar2.iw / this.sampleSize;
        int i4 = bVar2.ix / this.sampleSize;
        Object obj = this.ck == 0 ? 1 : null;
        int i5 = this.sampleSize;
        int i6 = this.cp;
        int i7 = this.cn;
        byte[] bArr = this.ci;
        int[] iArr2 = this.act;
        Boolean bool = this.cq;
        int i8 = 8;
        int i9 = 0;
        int i10 = 0;
        int i11 = 1;
        while (i10 < i) {
            int i12;
            int i13;
            int i14;
            if (bVar2.interlace) {
                if (i9 >= i) {
                    i11++;
                    switch (i11) {
                        case 2:
                            i9 = 4;
                            break;
                        case 3:
                            i8 = 4;
                            i9 = 2;
                            break;
                        case 4:
                            i8 = 2;
                            i9 = 1;
                            break;
                    }
                }
                i12 = i9 + i8;
            } else {
                i12 = i9;
                i9 = i10;
            }
            i9 += i2;
            int i15 = i;
            Object obj2 = i5 == 1 ? 1 : null;
            if (i9 < i7) {
                i9 *= i6;
                int i16 = i9 + i4;
                i13 = i2;
                i2 = i16 + i3;
                i9 += i6;
                if (i9 < i2) {
                    i2 = i9;
                }
                i14 = i3;
                i9 = (i10 * i5) * bVar2.iw;
                if (obj2 != null) {
                    for (i = i16; i < i2; i++) {
                        i3 = iArr2[bArr[i9] & 255];
                        if (i3 != 0) {
                            iArr[i] = i3;
                        } else if (obj != null && bool == null) {
                            bool = Boolean.valueOf(true);
                        }
                        i9 += i5;
                    }
                } else {
                    i = ((i2 - i16) * i5) + i9;
                    i3 = i16;
                    while (i3 < i2) {
                        int i17 = i2;
                        i2 = a(i9, i, bVar2.iw);
                        if (i2 != 0) {
                            iArr[i3] = i2;
                        } else if (obj != null && bool == null) {
                            bool = Boolean.valueOf(true);
                            i9 += i5;
                            i3++;
                            i2 = i17;
                        }
                        i9 += i5;
                        i3++;
                        i2 = i17;
                    }
                }
            } else {
                i13 = i2;
                i14 = i3;
            }
            i10++;
            i9 = i12;
            i = i15;
            i2 = i13;
            i3 = i14;
        }
        if (this.cq == null) {
            boolean z;
            Boolean bool2 = bool;
            if (bool2 == null) {
                z = false;
            } else {
                z = bool2.booleanValue();
            }
            this.cq = Boolean.valueOf(z);
        }
    }

    @ColorInt
    private int a(int i, int i2, int i3) {
        int i4 = i;
        int i5 = 0;
        int i6 = i5;
        int i7 = i6;
        int i8 = i7;
        int i9 = i8;
        while (i4 < this.sampleSize + i && i4 < this.ci.length && i4 < i2) {
            int i10 = this.act[this.ci[i4] & 255];
            if (i10 != 0) {
                i5 += (i10 >> 24) & 255;
                i6 += (i10 >> 16) & 255;
                i7 += (i10 >> 8) & 255;
                i8 += i10 & 255;
                i9++;
            }
            i4++;
        }
        i += i3;
        i3 = i;
        while (i3 < this.sampleSize + i && i3 < this.ci.length && i3 < i2) {
            i4 = this.act[this.ci[i3] & 255];
            if (i4 != 0) {
                i5 += (i4 >> 24) & 255;
                i6 += (i4 >> 16) & 255;
                i7 += (i4 >> 8) & 255;
                i8 += i4 & 255;
                i9++;
            }
            i3++;
        }
        if (i9 == 0) {
            return 0;
        }
        return ((((i5 / i9) << 24) | ((i6 / i9) << 16)) | ((i7 / i9) << 8)) | (i8 / i9);
    }

    private void c(b bVar) {
        short s;
        e eVar = this;
        b bVar2 = bVar;
        if (bVar2 != null) {
            eVar.bY.position(bVar2.bz);
        }
        if (bVar2 == null) {
            s = eVar.bZ.width * eVar.bZ.height;
        } else {
            s = bVar2.ih * bVar2.iw;
        }
        if (eVar.ci == null || eVar.ci.length < s) {
            eVar.ci = eVar.cf.i(s);
        }
        byte[] bArr = eVar.ci;
        if (eVar.prefix == null) {
            eVar.prefix = new short[4096];
        }
        short[] sArr = eVar.prefix;
        if (eVar.suffix == null) {
            eVar.suffix = new byte[4096];
        }
        byte[] bArr2 = eVar.suffix;
        if (eVar.pixelStack == null) {
            eVar.pixelStack = new byte[4097];
        }
        byte[] bArr3 = eVar.pixelStack;
        int readByte = readByte();
        int i = 1 << readByte;
        int i2 = i + 1;
        int i3 = i + 2;
        readByte++;
        int i4 = (1 << readByte) - 1;
        short s2 = (short) 0;
        for (int i5 = 0; i5 < i; i5++) {
            sArr[i5] = (short) 0;
            bArr2[i5] = (byte) i5;
        }
        byte[] bArr4 = eVar.block;
        int i6 = readByte;
        int i7 = i3;
        int i8 = i4;
        int i9 = 0;
        int i10 = i9;
        int i11 = i10;
        int i12 = i11;
        int i13 = i12;
        int i14 = i13;
        int i15 = i14;
        int i16 = -1;
        while (s2 < s) {
            if (i9 == 0) {
                i9 = readBlock();
                if (i9 <= 0) {
                    eVar.status = 3;
                    break;
                }
                i13 = 0;
            }
            i12 += (bArr4[i13] & 255) << i11;
            i13++;
            i9--;
            int i17 = i11 + 8;
            int i18 = i16;
            int i19 = i14;
            int i20 = i7;
            i11 = i10;
            short s3 = s2;
            int i21 = i6;
            while (i17 >= i21) {
                int i22 = i12 & i8;
                i12 >>= i21;
                i17 -= i21;
                if (i22 == i) {
                    i21 = readByte;
                    i20 = i3;
                    i8 = i4;
                    i18 = -1;
                } else if (i22 == i2) {
                    i16 = i18;
                    i6 = i21;
                    s2 = s3;
                    i10 = i11;
                    i7 = i20;
                    i14 = i19;
                    i11 = i17;
                    break;
                } else if (i18 == -1) {
                    bArr[i11] = bArr2[i22];
                    i11++;
                    s3++;
                    i18 = i22;
                    i19 = i18;
                    i22 = -1;
                    eVar = this;
                } else {
                    int i23;
                    int i24 = i20;
                    if (i22 >= i24) {
                        i23 = i17;
                        bArr3[i15] = (byte) i19;
                        i15++;
                        i17 = i18;
                    } else {
                        i23 = i17;
                        i17 = i22;
                    }
                    while (i17 >= i) {
                        bArr3[i15] = bArr2[i17];
                        i15++;
                        i17 = sArr[i17];
                    }
                    i17 = bArr2[i17] & 255;
                    int i25 = readByte;
                    byte b = (byte) i17;
                    bArr[i11] = b;
                    i11++;
                    s3++;
                    while (i15 > 0) {
                        i15--;
                        bArr[i11] = bArr3[i15];
                        i11++;
                        s3++;
                    }
                    int i26 = i17;
                    if (i24 < 4096) {
                        sArr[i24] = (short) i18;
                        bArr2[i24] = b;
                        i24++;
                        if ((i24 & i8) == 0) {
                            if (i24 < 4096) {
                                i21++;
                                i8 += i24;
                            }
                        }
                    } else {
                        readByte = 4096;
                    }
                    i20 = i24;
                    i18 = i22;
                    i17 = i23;
                    readByte = i25;
                    i19 = i26;
                    eVar = this;
                }
            }
            i7 = i20;
            i14 = i19;
            i16 = i18;
            i6 = i21;
            s2 = s3;
            i10 = i11;
            i11 = i17;
            eVar = this;
        }
        Arrays.fill(bArr, i10, s, (byte) 0);
    }

    private int readByte() {
        return this.bY.get() & 255;
    }

    private int readBlock() {
        int readByte = readByte();
        if (readByte <= 0) {
            return readByte;
        }
        this.bY.get(this.block, 0, Math.min(readByte, this.bY.remaining()));
        return readByte;
    }

    private Bitmap aa() {
        Config config = (this.cq == null || this.cq.booleanValue()) ? Config.ARGB_8888 : this.cr;
        Bitmap a = this.cf.a(this.cp, this.cn, config);
        a.setHasAlpha(true);
        return a;
    }
}
