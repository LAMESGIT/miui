package com.bumptech.glide.load.engine;

import android.support.annotation.VisibleForTesting;
import com.bumptech.glide.load.c;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Jobs */
final class m {
    private final Map<c, EngineJob<?>> ge = new HashMap();
    private final Map<c, EngineJob<?>> gf = new HashMap();

    m() {
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public Map<c, EngineJob<?>> getAll() {
        return Collections.unmodifiableMap(this.ge);
    }

    /* Access modifiers changed, original: 0000 */
    public EngineJob<?> c(c cVar, boolean z) {
        return (EngineJob) e(z).get(cVar);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar, EngineJob<?> engineJob) {
        e(engineJob.aS()).put(cVar, engineJob);
    }

    /* Access modifiers changed, original: 0000 */
    public void b(c cVar, EngineJob<?> engineJob) {
        Map e = e(engineJob.aS());
        if (engineJob.equals(e.get(cVar))) {
            e.remove(cVar);
        }
    }

    private Map<c, EngineJob<?>> e(boolean z) {
        return z ? this.gf : this.ge;
    }
}
