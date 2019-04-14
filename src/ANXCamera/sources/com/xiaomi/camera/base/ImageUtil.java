package com.xiaomi.camera.base;

import android.media.Image;
import android.media.Image.Plane;
import android.util.Size;
import com.android.camera.log.Log;
import java.nio.ByteBuffer;
import java.util.Locale;
import libcore.io.Memory;

public final class ImageUtil {
    private static final String TAG = ImageUtil.class.getSimpleName();

    private ImageUtil() {
    }

    public static void imageCopy(Image image, Image image2) {
        if (image == null || image2 == null) {
            throw new IllegalArgumentException("Images should be non-null");
        } else if (image.getFormat() != image2.getFormat()) {
            throw new IllegalArgumentException("Src and dst images should have the same format");
        } else if (image.getFormat() == 34 || image2.getFormat() == 34) {
            throw new IllegalArgumentException("PRIVATE format images are not copyable");
        } else if (image.getFormat() != 36) {
            Size size = new Size(image.getWidth(), image.getHeight());
            Size size2 = new Size(image2.getWidth(), image2.getHeight());
            if (size.equals(size2)) {
                Plane[] planes = image.getPlanes();
                Plane[] planes2 = image2.getPlanes();
                int i = 0;
                while (i < planes.length) {
                    int rowStride = planes[i].getRowStride();
                    int rowStride2 = planes2[i].getRowStride();
                    ByteBuffer buffer = planes[i].getBuffer();
                    ByteBuffer buffer2 = planes2[i].getBuffer();
                    if (!buffer.isDirect() || !buffer2.isDirect()) {
                        throw new IllegalArgumentException("Source and destination ByteBuffers must be direct byteBuffer!");
                    } else if (planes[i].getPixelStride() == planes2[i].getPixelStride()) {
                        int position = buffer.position();
                        buffer.rewind();
                        buffer2.rewind();
                        if (rowStride == rowStride2) {
                            buffer2.put(buffer);
                        } else {
                            int position2 = buffer.position();
                            int position3 = buffer2.position();
                            Size effectivePlaneSizeForImage = getEffectivePlaneSizeForImage(image, i);
                            int width = effectivePlaneSizeForImage.getWidth() * planes[i].getPixelStride();
                            int i2 = position3;
                            position3 = position2;
                            for (position2 = 0; position2 < effectivePlaneSizeForImage.getHeight(); position2++) {
                                if (position2 == effectivePlaneSizeForImage.getHeight() - 1) {
                                    int remaining = buffer.remaining() - position3;
                                    if (width > remaining) {
                                        width = remaining;
                                    }
                                }
                                directByteBufferCopy(buffer, position3, buffer2, i2, width);
                                position3 += rowStride;
                                i2 += rowStride2;
                            }
                        }
                        buffer.position(position);
                        buffer2.rewind();
                        i++;
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Source plane image pixel stride ");
                        stringBuilder.append(planes[i].getPixelStride());
                        stringBuilder.append(" must be same as destination image pixel stride ");
                        stringBuilder.append(planes2[i].getPixelStride());
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("source image size ");
            stringBuilder2.append(size);
            stringBuilder2.append(" is different with destination image size ");
            stringBuilder2.append(size2);
            throw new IllegalArgumentException(stringBuilder2.toString());
        } else {
            throw new IllegalArgumentException("Copy of RAW_OPAQUE format has not been implemented");
        }
    }

    private static Size getEffectivePlaneSizeForImage(Image image, int i) {
        switch (image.getFormat()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 20:
            case 32:
            case 37:
            case 38:
            case 256:
                return new Size(image.getWidth(), image.getHeight());
            case 16:
                if (i == 0) {
                    return new Size(image.getWidth(), image.getHeight());
                }
                return new Size(image.getWidth(), image.getHeight() / 2);
            case 17:
            case 35:
            case 842094169:
                if (i == 0) {
                    return new Size(image.getWidth(), image.getHeight());
                }
                return new Size(image.getWidth() / 2, image.getHeight() / 2);
            case 34:
                return new Size(0, 0);
            default:
                throw new UnsupportedOperationException(String.format("Invalid image format %d", new Object[]{Integer.valueOf(image.getFormat())}));
        }
    }

    private static void directByteBufferCopy(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        Memory.memmove(byteBuffer2, i2, byteBuffer, i, (long) i3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5 A:{SYNTHETIC, Splitter:B:23:0x00e5} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f0 A:{SYNTHETIC, Splitter:B:27:0x00f0} */
    public static boolean dumpYuvImage(android.media.Image r9, java.lang.String r10) {
        /*
        r0 = TAG;
        r1 = "dumpYuvImage start";
        com.android.camera.log.Log.d(r0, r1);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "sdcard/DCIM/Camera/";
        r0.append(r1);
        r0.append(r10);
        r10 = "_";
        r0.append(r10);
        r10 = r9.getWidth();
        r0.append(r10);
        r10 = "x";
        r0.append(r10);
        r10 = r9.getHeight();
        r0.append(r10);
        r10 = r0.toString();
        r0 = r9.getFormat();
        r1 = 17;
        r2 = 0;
        if (r0 == r1) goto L_0x0041;
    L_0x003b:
        r1 = 35;
        if (r0 == r1) goto L_0x0041;
    L_0x003f:
        goto L_0x00ec;
    L_0x0041:
        r0 = 0;
        r1 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00db }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00db }
        r3.<init>();	 Catch:{ Exception -> 0x00db }
        r3.append(r10);	 Catch:{ Exception -> 0x00db }
        r10 = ".yuv";
        r3.append(r10);	 Catch:{ Exception -> 0x00db }
        r10 = r3.toString();	 Catch:{ Exception -> 0x00db }
        r1.<init>(r10);	 Catch:{ Exception -> 0x00db }
        r10 = r9.getPlanes();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r10 = r10[r2];	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r10 = r10.getBuffer();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r0 = r9.getPlanes();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r3 = 2;
        r0 = r0[r3];	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r0 = r0.getBuffer();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r4 = r10.limit();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r5 = r0.limit();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r6 = TAG;	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r7.<init>();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r8 = "dumpingYuvImage: size=";
        r7.append(r8);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r8 = r9.getWidth();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r7.append(r8);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r8 = "x";
        r7.append(r8);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r8 = r9.getHeight();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r7.append(r8);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r8 = " stride=";
        r7.append(r8);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r9 = r9.getPlanes();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r9 = r9[r3];	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r9 = r9.getRowStride();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r7.append(r9);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r9 = r7.toString();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        com.android.camera.log.Log.d(r6, r9);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r9 = r4 + r5;
        r9 = new byte[r9];	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r10.get(r9, r2, r4);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r0.get(r9, r4, r5);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r1.write(r9);	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r10.rewind();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r0.rewind();	 Catch:{ Exception -> 0x00d5, all -> 0x00d3 }
        r2 = 1;
        r1.flush();	 Catch:{ Exception -> 0x00c9 }
        r1.close();	 Catch:{ Exception -> 0x00c9 }
        goto L_0x00d2;
    L_0x00c9:
        r9 = move-exception;
        r10 = TAG;
        r0 = "Failed to flush/close stream";
        com.android.camera.log.Log.e(r10, r0, r9);
        goto L_0x00ec;
    L_0x00d2:
        goto L_0x00ec;
    L_0x00d3:
        r9 = move-exception;
        goto L_0x00ed;
    L_0x00d5:
        r9 = move-exception;
        r0 = r1;
        goto L_0x00dc;
    L_0x00d8:
        r9 = move-exception;
        r1 = r0;
        goto L_0x00ed;
    L_0x00db:
        r9 = move-exception;
    L_0x00dc:
        r10 = TAG;	 Catch:{ all -> 0x00d8 }
        r1 = "Failed to write image";
        com.android.camera.log.Log.e(r10, r1, r9);	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x00d2;
    L_0x00e5:
        r0.flush();	 Catch:{ Exception -> 0x00c9 }
        r0.close();	 Catch:{ Exception -> 0x00c9 }
        goto L_0x00d2;
    L_0x00ec:
        return r2;
        if (r1 == 0) goto L_0x0100;
    L_0x00f0:
        r1.flush();	 Catch:{ Exception -> 0x00f7 }
        r1.close();	 Catch:{ Exception -> 0x00f7 }
        goto L_0x0100;
    L_0x00f7:
        r10 = move-exception;
        r0 = TAG;
        r1 = "Failed to flush/close stream";
        com.android.camera.log.Log.e(r0, r1, r10);
    L_0x0100:
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.camera.base.ImageUtil.dumpYuvImage(android.media.Image, java.lang.String):boolean");
    }

    public static byte[] getFirstPlane(Image image) {
        Plane[] planes = image.getPlanes();
        if (planes.length <= 0) {
            return null;
        }
        ByteBuffer buffer = planes[0].getBuffer();
        byte[] bArr = new byte[buffer.remaining()];
        buffer.get(bArr);
        return bArr;
    }

    public static ByteBuffer removePadding(Plane plane, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        ByteBuffer buffer = plane.getBuffer();
        int rowStride = plane.getRowStride();
        int pixelStride = plane.getPixelStride();
        String str = TAG;
        r7 = new Object[4];
        int i3 = 0;
        r7[0] = Integer.valueOf(rowStride);
        r7[1] = Integer.valueOf(pixelStride);
        r7[2] = Integer.valueOf(i);
        r7[3] = Integer.valueOf(i2);
        Log.d(str, String.format(Locale.ENGLISH, "removePadding: rowStride=%d pixelStride=%d size=%dx%d", r7));
        i *= pixelStride;
        if (rowStride == i) {
            return buffer;
        }
        pixelStride = i * i2;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(pixelStride);
        int position = buffer.position();
        int position2 = allocateDirect.position();
        while (i3 < i2) {
            if (i3 == i2 - 1) {
                int remaining = buffer.remaining() - position;
                if (i > remaining) {
                    String str2 = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("removePadding: ");
                    stringBuilder.append(remaining);
                    stringBuilder.append("/");
                    stringBuilder.append(i);
                    Log.d(str2, stringBuilder.toString());
                    i = remaining;
                }
            }
            directByteBufferCopy(buffer, position, allocateDirect, position2, i);
            position += rowStride;
            position2 += i;
            i3++;
        }
        if (position2 < pixelStride) {
            String str3 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("removePadding: add data: ");
            stringBuilder2.append(position2);
            stringBuilder2.append("|");
            stringBuilder2.append(pixelStride);
            Log.d(str3, stringBuilder2.toString());
            while (position2 < pixelStride) {
                allocateDirect.put(position2, allocateDirect.get(position2 - 2));
                position2++;
            }
        }
        String str4 = TAG;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("removePadding: cost=");
        stringBuilder3.append(System.currentTimeMillis() - currentTimeMillis);
        Log.v(str4, stringBuilder3.toString());
        return allocateDirect;
    }

    private static void updateImagePlane(Plane plane, int i, int i2, byte[] bArr, int i3) {
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        int rowStride = plane.getRowStride();
        int pixelStride = plane.getPixelStride();
        int i4 = i * pixelStride;
        String str = TAG;
        r6 = new Object[6];
        int i5 = 0;
        r6[0] = Integer.valueOf(i);
        r6[1] = Integer.valueOf(i2);
        r6[2] = Integer.valueOf(i3);
        r6[3] = Integer.valueOf(bArr.length);
        r6[4] = Integer.valueOf(rowStride);
        r6[5] = Integer.valueOf(pixelStride);
        Log.d(str, String.format(Locale.ENGLISH, "updateImagePlane: size=%dx%d offset=%d length=%d rowStride=%d pixelStride=%d", r6));
        pixelStride = i4 * i2;
        if (bArr.length - i3 >= pixelStride) {
            if (rowStride == i4) {
                pixelStride = Math.min(buffer.remaining(), pixelStride);
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("updateImagePlane: ");
                stringBuilder.append(pixelStride);
                Log.d(str2, stringBuilder.toString());
                buffer.put(bArr, i3, pixelStride);
            } else {
                pixelStride = buffer.position();
                i = i4;
                while (i5 < i2) {
                    buffer.position(pixelStride);
                    if (i5 == i2 - 1) {
                        i = Math.min(buffer.remaining(), i4);
                        if (i < i4) {
                            str = TAG;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("updateImagePlane: ");
                            stringBuilder2.append(i);
                            stringBuilder2.append("/");
                            stringBuilder2.append(i4);
                            Log.d(str, stringBuilder2.toString());
                            i = buffer.remaining();
                        }
                    }
                    buffer.put(bArr, i3, i);
                    i3 += i4;
                    pixelStride += rowStride;
                    i5++;
                }
            }
            buffer.rewind();
            return;
        }
        throw new RuntimeException(String.format(Locale.ENGLISH, "buffer size should be at least %d but actual size is %d", new Object[]{Integer.valueOf(pixelStride), Integer.valueOf(i)}));
    }

    public static void updateYuvImage(Image image, byte[] bArr) {
        if (image == null || 35 != image.getFormat()) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("updateYuvImage: ");
            stringBuilder.append(image);
            Log.e(str, stringBuilder.toString());
            return;
        }
        Plane[] planes = image.getPlanes();
        updateImagePlane(planes[0], image.getWidth(), image.getHeight(), bArr, 0);
        updateImagePlane(planes[1], image.getWidth() / 2, image.getHeight() / 2, bArr, image.getWidth() * image.getHeight());
    }

    public static byte[] getYuvData(Image image) {
        if (image == null || 35 != image.getFormat()) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getYuvData: ");
            stringBuilder.append(image);
            Log.e(str, stringBuilder.toString());
            return null;
        }
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        ByteBuffer buffer2 = image.getPlanes()[1].getBuffer();
        int limit = buffer.limit();
        int limit2 = buffer2.limit();
        byte[] bArr = new byte[((limit + limit2) + 1)];
        buffer.get(bArr, 0, limit);
        buffer2.get(bArr, limit, limit2);
        bArr[bArr.length - 1] = bArr[bArr.length - 3];
        return bArr;
    }
}
