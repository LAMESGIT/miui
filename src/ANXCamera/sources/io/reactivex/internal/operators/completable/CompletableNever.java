package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.internal.disposables.EmptyDisposable;

public final class CompletableNever extends Completable {
    public static final Completable INSTANCE = new CompletableNever();

    private CompletableNever() {
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        completableObserver.onSubscribe(EmptyDisposable.NEVER);
    }
}
