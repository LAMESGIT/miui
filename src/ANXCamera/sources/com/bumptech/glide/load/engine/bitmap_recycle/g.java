package com.bumptech.glide.load.engine.bitmap_recycle;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: GroupedLinkedMap */
class g<K extends l, V> {
    private final Map<K, a<K, V>> gA = new HashMap();
    private final a<K, V> gz = new a();

    /* compiled from: GroupedLinkedMap */
    private static class a<K, V> {
        a<K, V> gB;
        a<K, V> gC;
        final K key;
        private List<V> values;

        a() {
            this(null);
        }

        a(K k) {
            this.gC = this;
            this.gB = this;
            this.key = k;
        }

        @Nullable
        public V removeLast() {
            int size = size();
            return size > 0 ? this.values.remove(size - 1) : null;
        }

        public int size() {
            return this.values != null ? this.values.size() : 0;
        }

        public void add(V v) {
            if (this.values == null) {
                this.values = new ArrayList();
            }
            this.values.add(v);
        }
    }

    g() {
    }

    public void a(K k, V v) {
        a aVar = (a) this.gA.get(k);
        if (aVar == null) {
            aVar = new a(k);
            b(aVar);
            this.gA.put(k, aVar);
        } else {
            k.bg();
        }
        aVar.add(v);
    }

    @Nullable
    public V b(K k) {
        a aVar = (a) this.gA.get(k);
        if (aVar == null) {
            aVar = new a(k);
            this.gA.put(k, aVar);
        } else {
            k.bg();
        }
        a(aVar);
        return aVar.removeLast();
    }

    @Nullable
    public V removeLast() {
        for (Object obj = this.gz.gC; !obj.equals(this.gz); obj = obj.gC) {
            Object removeLast = obj.removeLast();
            if (removeLast != null) {
                return removeLast;
            }
            d(obj);
            this.gA.remove(obj.key);
            ((l) obj.key).bg();
        }
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GroupedLinkedMap( ");
        Object obj = null;
        for (Object obj2 = this.gz.gB; !obj2.equals(this.gz); obj2 = obj2.gB) {
            obj = 1;
            stringBuilder.append('{');
            stringBuilder.append(obj2.key);
            stringBuilder.append(':');
            stringBuilder.append(obj2.size());
            stringBuilder.append("}, ");
        }
        if (obj != null) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(" )");
        return stringBuilder.toString();
    }

    private void a(a<K, V> aVar) {
        d(aVar);
        aVar.gC = this.gz;
        aVar.gB = this.gz.gB;
        c(aVar);
    }

    private void b(a<K, V> aVar) {
        d(aVar);
        aVar.gC = this.gz.gC;
        aVar.gB = this.gz;
        c(aVar);
    }

    private static <K, V> void c(a<K, V> aVar) {
        aVar.gB.gC = aVar;
        aVar.gC.gB = aVar;
    }

    private static <K, V> void d(a<K, V> aVar) {
        aVar.gC.gB = aVar.gB;
        aVar.gB.gC = aVar.gC;
    }
}
