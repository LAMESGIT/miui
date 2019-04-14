package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import org.reactivestreams.Subscription;

public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {
    private Subscription s;

    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.validate(this.s, subscription, getClass())) {
            this.s = subscription;
            onStart();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void request(long j) {
        Subscription subscription = this.s;
        if (subscription != null) {
            subscription.request(j);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void cancel() {
        Subscription subscription = this.s;
        this.s = SubscriptionHelper.CANCELLED;
        subscription.cancel();
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        request(Long.MAX_VALUE);
    }
}
