package com.bumptech.glide.load.a;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: StreamAssetPathFetcher */
public class m extends b<InputStream> {
    public m(AssetManager assetManager, String str) {
        super(assetManager, str);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public InputStream a(AssetManager assetManager, String str) throws IOException {
        return assetManager.open(str);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public void h(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @NonNull
    public Class<InputStream> ad() {
        return InputStream.class;
    }
}
