package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {
    final AtomicReference<Subscription> s = new AtomicReference();

    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.setOnce(this.s, subscription, getClass())) {
            onStart();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        ((Subscription) this.s.get()).request(Long.MAX_VALUE);
    }

    /* Access modifiers changed, original: protected|final */
    public final void request(long j) {
        ((Subscription) this.s.get()).request(j);
    }

    /* Access modifiers changed, original: protected|final */
    public final void cancel() {
        dispose();
    }

    public final boolean isDisposed() {
        return this.s.get() == SubscriptionHelper.CANCELLED;
    }

    public final void dispose() {
        SubscriptionHelper.cancel(this.s);
    }
}
