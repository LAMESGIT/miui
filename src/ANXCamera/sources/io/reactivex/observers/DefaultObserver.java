package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;

public abstract class DefaultObserver<T> implements Observer<T> {
    private Disposable s;

    public final void onSubscribe(@NonNull Disposable disposable) {
        if (EndConsumerHelper.validate(this.s, disposable, getClass())) {
            this.s = disposable;
            onStart();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void cancel() {
        Disposable disposable = this.s;
        this.s = DisposableHelper.DISPOSED;
        disposable.dispose();
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
    }
}
