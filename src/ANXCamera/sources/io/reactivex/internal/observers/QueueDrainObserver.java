package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.util.ObservableQueueDrain;
import io.reactivex.internal.util.QueueDrainHelper;

public abstract class QueueDrainObserver<T, U, V> extends QueueDrainSubscriberPad2 implements Observer<T>, ObservableQueueDrain<U, V> {
    protected final Observer<? super V> actual;
    protected volatile boolean cancelled;
    protected volatile boolean done;
    protected Throwable error;
    protected final SimplePlainQueue<U> queue;

    public QueueDrainObserver(Observer<? super V> observer, SimplePlainQueue<U> simplePlainQueue) {
        this.actual = observer;
        this.queue = simplePlainQueue;
    }

    public final boolean cancelled() {
        return this.cancelled;
    }

    public final boolean done() {
        return this.done;
    }

    public final boolean enter() {
        return this.wip.getAndIncrement() == 0;
    }

    public final boolean fastEnter() {
        return this.wip.get() == 0 && this.wip.compareAndSet(0, 1);
    }

    /* Access modifiers changed, original: protected|final */
    public final void fastPathEmit(U u, boolean z, Disposable disposable) {
        Observer observer = this.actual;
        SimplePlainQueue simplePlainQueue = this.queue;
        if (this.wip.get() == 0 && this.wip.compareAndSet(0, 1)) {
            accept(observer, u);
            if (leave(-1) == 0) {
                return;
            }
        }
        simplePlainQueue.offer(u);
        if (!enter()) {
            return;
        }
        QueueDrainHelper.drainLoop(simplePlainQueue, observer, z, disposable, this);
    }

    /* Access modifiers changed, original: protected|final */
    public final void fastPathOrderedEmit(U u, boolean z, Disposable disposable) {
        Observer observer = this.actual;
        SimplePlainQueue simplePlainQueue = this.queue;
        if (this.wip.get() != 0 || !this.wip.compareAndSet(0, 1)) {
            simplePlainQueue.offer(u);
            if (!enter()) {
                return;
            }
        } else if (simplePlainQueue.isEmpty()) {
            accept(observer, u);
            if (leave(-1) == 0) {
                return;
            }
        } else {
            simplePlainQueue.offer(u);
        }
        QueueDrainHelper.drainLoop(simplePlainQueue, observer, z, disposable, this);
    }

    public final Throwable error() {
        return this.error;
    }

    public final int leave(int i) {
        return this.wip.addAndGet(i);
    }

    public void accept(Observer<? super V> observer, U u) {
    }
}
