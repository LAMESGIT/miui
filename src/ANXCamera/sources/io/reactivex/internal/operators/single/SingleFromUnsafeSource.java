package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;

public final class SingleFromUnsafeSource<T> extends Single<T> {
    final SingleSource<T> source;

    public SingleFromUnsafeSource(SingleSource<T> singleSource) {
        this.source = singleSource;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(singleObserver);
    }
}
