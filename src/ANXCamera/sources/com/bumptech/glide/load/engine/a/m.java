package com.bumptech.glide.load.engine.a;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.load.c;
import com.bumptech.glide.util.f;
import com.bumptech.glide.util.i;
import com.bumptech.glide.util.k;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: SafeKeyGenerator */
public class m {
    private final f<c, String> hN = new f(1000);
    private final Pool<a> hO = com.bumptech.glide.util.a.a.b(10, new com.bumptech.glide.util.a.a.a<a>() {
        /* renamed from: bG */
        public a create() {
            try {
                return new a(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });

    /* compiled from: SafeKeyGenerator */
    private static final class a implements com.bumptech.glide.util.a.a.c {
        private final com.bumptech.glide.util.a.c eu = com.bumptech.glide.util.a.c.eM();
        final MessageDigest messageDigest;

        a(MessageDigest messageDigest) {
            this.messageDigest = messageDigest;
        }

        @NonNull
        public com.bumptech.glide.util.a.c aK() {
            return this.eu;
        }
    }

    public String h(c cVar) {
        Object obj;
        synchronized (this.hN) {
            obj = (String) this.hN.get(cVar);
        }
        if (obj == null) {
            obj = i(cVar);
        }
        synchronized (this.hN) {
            this.hN.put(cVar, obj);
        }
        return obj;
    }

    private String i(c cVar) {
        a aVar = (a) i.checkNotNull(this.hO.acquire());
        try {
            cVar.updateDiskCacheKey(aVar.messageDigest);
            String j = k.j(aVar.messageDigest.digest());
            return j;
        } finally {
            this.hO.release(aVar);
        }
    }
}
