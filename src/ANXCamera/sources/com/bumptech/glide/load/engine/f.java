package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.load.g;
import com.bumptech.glide.load.resource.d.e;
import com.bumptech.glide.util.i;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DecodePath */
public class f<DataType, ResourceType, Transcode> {
    private static final String TAG = "DecodePath";
    private final Class<DataType> dataClass;
    private final List<? extends g<DataType, ResourceType>> fg;
    private final e<ResourceType, Transcode> fh;
    private final Pool<List<Throwable>> fi;
    private final String fj;

    /* compiled from: DecodePath */
    interface a<ResourceType> {
        @NonNull
        p<ResourceType> c(@NonNull p<ResourceType> pVar);
    }

    public f(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends g<DataType, ResourceType>> list, e<ResourceType, Transcode> eVar, Pool<List<Throwable>> pool) {
        this.dataClass = cls;
        this.fg = list;
        this.fh = eVar;
        this.fi = pool;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed DecodePath{");
        stringBuilder.append(cls.getSimpleName());
        stringBuilder.append("->");
        stringBuilder.append(cls2.getSimpleName());
        stringBuilder.append("->");
        stringBuilder.append(cls3.getSimpleName());
        stringBuilder.append("}");
        this.fj = stringBuilder.toString();
    }

    public p<Transcode> a(com.bumptech.glide.load.a.e<DataType> eVar, int i, int i2, @NonNull com.bumptech.glide.load.f fVar, a<ResourceType> aVar) throws GlideException {
        return this.fh.a(aVar.c(a(eVar, i, i2, fVar)), fVar);
    }

    @NonNull
    private p<ResourceType> a(com.bumptech.glide.load.a.e<DataType> eVar, int i, int i2, @NonNull com.bumptech.glide.load.f fVar) throws GlideException {
        List list = (List) i.checkNotNull(this.fi.acquire());
        try {
            p<ResourceType> a = a((com.bumptech.glide.load.a.e) eVar, i, i2, fVar, list);
            return a;
        } finally {
            this.fi.release(list);
        }
    }

    @NonNull
    private p<ResourceType> a(com.bumptech.glide.load.a.e<DataType> eVar, int i, int i2, @NonNull com.bumptech.glide.load.f fVar, List<Throwable> list) throws GlideException {
        int size = this.fg.size();
        p<ResourceType> pVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) this.fg.get(i3);
            try {
                if (gVar.a(eVar.ag(), fVar)) {
                    pVar = gVar.a(eVar.ag(), i, i2, fVar);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                if (Log.isLoggable(TAG, 2)) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to decode data for ");
                    stringBuilder.append(gVar);
                    Log.v(str, stringBuilder.toString(), e);
                }
                list.add(e);
            }
            if (pVar != null) {
                break;
            }
        }
        if (pVar != null) {
            return pVar;
        }
        throw new GlideException(this.fj, new ArrayList(list));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DecodePath{ dataClass=");
        stringBuilder.append(this.dataClass);
        stringBuilder.append(", decoders=");
        stringBuilder.append(this.fg);
        stringBuilder.append(", transcoder=");
        stringBuilder.append(this.fh);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
