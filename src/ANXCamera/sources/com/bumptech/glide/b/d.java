package com.bumptech.glide.b;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: GifHeaderParser */
public class d {
    private static final String TAG = "GifHeaderParser";
    private static final int bF = 255;
    private static final int bG = 44;
    private static final int bH = 33;
    private static final int bI = 59;
    private static final int bJ = 249;
    private static final int bK = 255;
    private static final int bL = 254;
    private static final int bM = 1;
    private static final int bN = 28;
    private static final int bO = 2;
    private static final int bP = 1;
    private static final int bQ = 128;
    private static final int bR = 64;
    private static final int bS = 7;
    private static final int bT = 128;
    private static final int bU = 7;
    static final int bV = 2;
    static final int bW = 10;
    private static final int bX = 256;
    private ByteBuffer bY;
    private c bZ;
    private final byte[] block = new byte[256];
    private int blockSize = 0;

    public d a(@NonNull ByteBuffer byteBuffer) {
        reset();
        this.bY = byteBuffer.asReadOnlyBuffer();
        this.bY.position(0);
        this.bY.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public d d(@Nullable byte[] bArr) {
        if (bArr != null) {
            a(ByteBuffer.wrap(bArr));
        } else {
            this.bY = null;
            this.bZ.status = 2;
        }
        return this;
    }

    public void clear() {
        this.bY = null;
        this.bZ = null;
    }

    private void reset() {
        this.bY = null;
        Arrays.fill(this.block, (byte) 0);
        this.bZ = new c();
        this.blockSize = 0;
    }

    @NonNull
    public c W() {
        if (this.bY == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (err()) {
            return this.bZ;
        } else {
            readHeader();
            if (!err()) {
                readContents();
                if (this.bZ.bC < 0) {
                    this.bZ.status = 1;
                }
            }
            return this.bZ;
        }
    }

    public boolean isAnimated() {
        readHeader();
        if (!err()) {
            k(2);
        }
        return this.bZ.bC > 1;
    }

    private void readContents() {
        k(Integer.MAX_VALUE);
    }

    private void k(int i) {
        int i2 = 0;
        while (i2 == 0 && !err() && this.bZ.bC <= i) {
            int read = read();
            if (read == 33) {
                read = read();
                if (read == 1) {
                    skip();
                } else if (read != 249) {
                    switch (read) {
                        case 254:
                            skip();
                            break;
                        case 255:
                            Y();
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i3 = 0; i3 < 11; i3++) {
                                stringBuilder.append((char) this.block[i3]);
                            }
                            if (!stringBuilder.toString().equals("NETSCAPE2.0")) {
                                skip();
                                break;
                            } else {
                                readNetscapeExt();
                                break;
                            }
                        default:
                            skip();
                            break;
                    }
                } else {
                    this.bZ.bD = new b();
                    readGraphicControlExt();
                }
            } else if (read == 44) {
                if (this.bZ.bD == null) {
                    this.bZ.bD = new b();
                }
                readBitmap();
            } else if (read != 59) {
                this.bZ.status = 1;
            } else {
                i2 = 1;
            }
        }
    }

    private void readGraphicControlExt() {
        read();
        int read = read();
        this.bZ.bD.dispose = (read & 28) >> 2;
        boolean z = true;
        if (this.bZ.bD.dispose == 0) {
            this.bZ.bD.dispose = 1;
        }
        b bVar = this.bZ.bD;
        if ((read & 1) == 0) {
            z = false;
        }
        bVar.transparency = z;
        read = readShort();
        if (read < 2) {
            read = 10;
        }
        this.bZ.bD.delay = read * 10;
        this.bZ.bD.transIndex = read();
        read();
    }

    private void readBitmap() {
        this.bZ.bD.ix = readShort();
        this.bZ.bD.iy = readShort();
        this.bZ.bD.iw = readShort();
        this.bZ.bD.ih = readShort();
        int read = read();
        boolean z = false;
        int i = (read & 128) != 0 ? 1 : false;
        int pow = (int) Math.pow(2.0d, (double) ((read & 7) + 1));
        b bVar = this.bZ.bD;
        if ((read & 64) != 0) {
            z = true;
        }
        bVar.interlace = z;
        if (i != 0) {
            this.bZ.bD.lct = readColorTable(pow);
        } else {
            this.bZ.bD.lct = null;
        }
        this.bZ.bD.bz = this.bY.position();
        X();
        if (!err()) {
            c cVar = this.bZ;
            cVar.bC++;
            this.bZ.bE.add(this.bZ.bD);
        }
    }

    private void readNetscapeExt() {
        do {
            Y();
            if (this.block[0] == (byte) 1) {
                this.bZ.loopCount = (this.block[1] & 255) | ((this.block[2] & 255) << 8);
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    private void readHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append((char) read());
        }
        if (stringBuilder.toString().startsWith("GIF")) {
            readLSD();
            if (this.bZ.gctFlag && !err()) {
                this.bZ.gct = readColorTable(this.bZ.gctSize);
                this.bZ.bgColor = this.bZ.gct[this.bZ.bgIndex];
            }
            return;
        }
        this.bZ.status = 1;
    }

    private void readLSD() {
        this.bZ.width = readShort();
        this.bZ.height = readShort();
        int read = read();
        this.bZ.gctFlag = (read & 128) != 0;
        this.bZ.gctSize = (int) Math.pow(2.0d, (double) ((read & 7) + 1));
        this.bZ.bgIndex = read();
        this.bZ.pixelAspect = read();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    @android.support.annotation.Nullable
    private int[] readColorTable(int r11) {
        /*
        r10 = this;
        r0 = 3;
        r1 = r0 * r11;
        r1 = new byte[r1];
        r2 = 0;
        r3 = r10.bY;	 Catch:{ BufferUnderflowException -> 0x003c }
        r3.get(r1);	 Catch:{ BufferUnderflowException -> 0x003c }
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r3 = new int[r3];	 Catch:{ BufferUnderflowException -> 0x003c }
        r2 = 0;
        r4 = r2;
    L_0x0014:
        if (r2 >= r11) goto L_0x003b;
    L_0x0016:
        r5 = r4 + 1;
        r4 = r1[r4];	 Catch:{ BufferUnderflowException -> 0x0039 }
        r4 = r4 & 255;
        r6 = r5 + 1;
        r5 = r1[r5];	 Catch:{ BufferUnderflowException -> 0x0039 }
        r5 = r5 & 255;
        r7 = r6 + 1;
        r6 = r1[r6];	 Catch:{ BufferUnderflowException -> 0x0039 }
        r6 = r6 & 255;
        r8 = r2 + 1;
        r9 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r4 = r4 << 16;
        r4 = r4 | r9;
        r5 = r5 << 8;
        r4 = r4 | r5;
        r4 = r4 | r6;
        r3[r2] = r4;	 Catch:{ BufferUnderflowException -> 0x0039 }
        r4 = r7;
        r2 = r8;
        goto L_0x0014;
    L_0x0039:
        r11 = move-exception;
        goto L_0x003e;
    L_0x003b:
        goto L_0x0052;
    L_0x003c:
        r11 = move-exception;
        r3 = r2;
    L_0x003e:
        r1 = "GifHeaderParser";
        r0 = android.util.Log.isLoggable(r1, r0);
        if (r0 == 0) goto L_0x004d;
    L_0x0046:
        r0 = "GifHeaderParser";
        r1 = "Format Error Reading Color Table";
        android.util.Log.d(r0, r1, r11);
    L_0x004d:
        r11 = r10.bZ;
        r0 = 1;
        r11.status = r0;
    L_0x0052:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.d.readColorTable(int):int[]");
    }

    private void X() {
        read();
        skip();
    }

    private void skip() {
        int read;
        do {
            read = read();
            this.bY.position(Math.min(this.bY.position() + read, this.bY.limit()));
        } while (read > 0);
    }

    private void Y() {
        this.blockSize = read();
        if (this.blockSize > 0) {
            int i = 0;
            int i2 = 0;
            while (i < this.blockSize) {
                try {
                    i2 = this.blockSize - i;
                    this.bY.get(this.block, i, i2);
                    i += i2;
                } catch (Exception e) {
                    if (Log.isLoggable(TAG, 3)) {
                        String str = TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Error Reading Block n: ");
                        stringBuilder.append(i);
                        stringBuilder.append(" count: ");
                        stringBuilder.append(i2);
                        stringBuilder.append(" blockSize: ");
                        stringBuilder.append(this.blockSize);
                        Log.d(str, stringBuilder.toString(), e);
                    }
                    this.bZ.status = 1;
                    return;
                }
            }
        }
    }

    private int read() {
        try {
            return this.bY.get() & 255;
        } catch (Exception e) {
            this.bZ.status = 1;
            return 0;
        }
    }

    private int readShort() {
        return this.bY.getShort();
    }

    private boolean err() {
        return this.bZ.status != 0;
    }
}
