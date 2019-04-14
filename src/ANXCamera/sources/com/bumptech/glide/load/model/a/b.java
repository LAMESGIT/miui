package com.bumptech.glide.load.model.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.volley.DefaultRetryPolicy;
import com.bumptech.glide.load.a.j;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.g;
import com.bumptech.glide.load.model.m;
import com.bumptech.glide.load.model.n;
import com.bumptech.glide.load.model.q;
import java.io.InputStream;

/* compiled from: HttpGlideUrlLoader */
public class b implements m<g, InputStream> {
    public static final e<Integer> jO = e.a("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", Integer.valueOf(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS));
    @Nullable
    private final ModelCache<g, g> jN;

    /* compiled from: HttpGlideUrlLoader */
    public static class a implements n<g, InputStream> {
        private final ModelCache<g, g> jN = new ModelCache(500);

        @NonNull
        public m<g, InputStream> a(q qVar) {
            return new b(this.jN);
        }

        public void bR() {
        }
    }

    public b() {
        this(null);
    }

    public b(@Nullable ModelCache<g, g> modelCache) {
        this.jN = modelCache;
    }

    /* renamed from: a */
    public com.bumptech.glide.load.model.m.a<InputStream> b(@NonNull g gVar, int i, int i2, @NonNull f fVar) {
        if (this.jN != null) {
            g gVar2 = (g) this.jN.c(gVar, 0, 0);
            if (gVar2 == null) {
                this.jN.a(gVar, 0, 0, gVar);
            } else {
                gVar = gVar2;
            }
        }
        return new com.bumptech.glide.load.model.m.a(gVar, new j(gVar, ((Integer) fVar.a(jO)).intValue()));
    }

    /* renamed from: a */
    public boolean o(@NonNull g gVar) {
        return true;
    }
}
