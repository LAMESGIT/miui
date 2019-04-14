package com.bumptech.glide.load.a.a;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/* compiled from: ThumbnailStreamOpener */
class e {
    private static final String TAG = "ThumbStreamOpener";
    private static final a dJ = new a();
    private final a dK;
    private final d dL;
    private final List<ImageHeaderParser> dM;
    private final b dv;
    private final ContentResolver dw;

    e(List<ImageHeaderParser> list, d dVar, b bVar, ContentResolver contentResolver) {
        this(list, dJ, dVar, bVar, contentResolver);
    }

    e(List<ImageHeaderParser> list, a aVar, d dVar, b bVar, ContentResolver contentResolver) {
        this.dK = aVar;
        this.dL = dVar;
        this.dv = bVar;
        this.dw = contentResolver;
        this.dM = list;
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d A:{SYNTHETIC, Splitter:B:27:0x004d} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002a A:{Catch:{ all -> 0x004a }} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A:{SYNTHETIC, Splitter:B:20:0x0042} */
    public int h(android.net.Uri r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = r6.dw;	 Catch:{ IOException | NullPointerException -> 0x001d, IOException | NullPointerException -> 0x001d, all -> 0x001a }
        r1 = r1.openInputStream(r7);	 Catch:{ IOException | NullPointerException -> 0x001d, IOException | NullPointerException -> 0x001d, all -> 0x001a }
        r0 = r6.dM;	 Catch:{ IOException | NullPointerException -> 0x0018, IOException | NullPointerException -> 0x0018 }
        r2 = r6.dv;	 Catch:{ IOException | NullPointerException -> 0x0018, IOException | NullPointerException -> 0x0018 }
        r0 = com.bumptech.glide.load.b.b(r0, r1, r2);	 Catch:{ IOException | NullPointerException -> 0x0018, IOException | NullPointerException -> 0x0018 }
        if (r1 == 0) goto L_0x0017;
    L_0x0012:
        r1.close();	 Catch:{ IOException -> 0x0016 }
        goto L_0x0017;
    L_0x0016:
        r7 = move-exception;
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = move-exception;
        goto L_0x0021;
    L_0x001a:
        r7 = move-exception;
        r1 = r0;
        goto L_0x004b;
    L_0x001d:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0021:
        r2 = "ThumbStreamOpener";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);	 Catch:{ all -> 0x004a }
        if (r2 == 0) goto L_0x0040;
    L_0x002a:
        r2 = "ThumbStreamOpener";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004a }
        r3.<init>();	 Catch:{ all -> 0x004a }
        r4 = "Failed to open uri: ";
        r3.append(r4);	 Catch:{ all -> 0x004a }
        r3.append(r7);	 Catch:{ all -> 0x004a }
        r7 = r3.toString();	 Catch:{ all -> 0x004a }
        android.util.Log.d(r2, r7, r0);	 Catch:{ all -> 0x004a }
    L_0x0040:
        if (r1 == 0) goto L_0x0048;
    L_0x0042:
        r1.close();	 Catch:{ IOException -> 0x0046 }
    L_0x0045:
        goto L_0x0048;
    L_0x0046:
        r7 = move-exception;
        goto L_0x0045;
    L_0x0048:
        r7 = -1;
        return r7;
    L_0x004a:
        r7 = move-exception;
    L_0x004b:
        if (r1 == 0) goto L_0x0052;
    L_0x004d:
        r1.close();	 Catch:{ IOException -> 0x0051 }
        goto L_0x0052;
    L_0x0051:
        r0 = move-exception;
    L_0x0052:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.a.a.e.h(android.net.Uri):int");
    }

    public InputStream i(Uri uri) throws FileNotFoundException {
        String j = j(uri);
        if (TextUtils.isEmpty(j)) {
            return null;
        }
        File h = this.dK.h(j);
        if (!e(h)) {
            return null;
        }
        Uri fromFile = Uri.fromFile(h);
        try {
            return this.dw.openInputStream(fromFile);
        } catch (NullPointerException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("NPE opening uri: ");
            stringBuilder.append(uri);
            stringBuilder.append(" -> ");
            stringBuilder.append(fromFile);
            throw ((FileNotFoundException) new FileNotFoundException(stringBuilder.toString()).initCause(e));
        }
    }

    @Nullable
    private String j(@NonNull Uri uri) {
        Cursor g = this.dL.g(uri);
        if (g != null) {
            try {
                if (g.moveToFirst()) {
                    String string = g.getString(0);
                    return string;
                }
            } finally {
                if (g != null) {
                    g.close();
                }
            }
        }
        if (g != null) {
            g.close();
        }
        return null;
    }

    private boolean e(File file) {
        return this.dK.exists(file) && 0 < this.dK.d(file);
    }
}
