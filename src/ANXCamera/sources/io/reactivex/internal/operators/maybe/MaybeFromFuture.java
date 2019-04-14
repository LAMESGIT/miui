package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class MaybeFromFuture<T> extends Maybe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public MaybeFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.future = future;
        this.timeout = j;
        this.unit = timeUnit;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        Disposable empty = Disposables.empty();
        maybeObserver.onSubscribe(empty);
        if (!empty.isDisposed()) {
            try {
                Object obj;
                if (this.timeout <= 0) {
                    obj = this.future.get();
                } else {
                    obj = this.future.get(this.timeout, this.unit);
                }
                if (!empty.isDisposed()) {
                    if (obj == null) {
                        maybeObserver.onComplete();
                    } else {
                        maybeObserver.onSuccess(obj);
                    }
                }
            } catch (InterruptedException e) {
                if (!empty.isDisposed()) {
                    maybeObserver.onError(e);
                }
            } catch (ExecutionException e2) {
                if (!empty.isDisposed()) {
                    maybeObserver.onError(e2.getCause());
                }
            } catch (TimeoutException e3) {
                if (!empty.isDisposed()) {
                    maybeObserver.onError(e3);
                }
            }
        }
    }
}
