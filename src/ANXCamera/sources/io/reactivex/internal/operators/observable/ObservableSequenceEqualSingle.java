package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSequenceEqualSingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {
    final int bufferSize;
    final BiPredicate<? super T, ? super T> comparer;
    final ObservableSource<? extends T> first;
    final ObservableSource<? extends T> second;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -6178010334400373240L;
        final SingleObserver<? super Boolean> actual;
        volatile boolean cancelled;
        final BiPredicate<? super T, ? super T> comparer;
        final ObservableSource<? extends T> first;
        final EqualObserver<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final ObservableSource<? extends T> second;
        T v1;
        T v2;

        EqualCoordinator(SingleObserver<? super Boolean> singleObserver, int i, ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, BiPredicate<? super T, ? super T> biPredicate) {
            this.actual = singleObserver;
            this.first = observableSource;
            this.second = observableSource2;
            this.comparer = biPredicate;
            r3 = new EqualObserver[2];
            this.observers = r3;
            r3[0] = new EqualObserver(this, 0, i);
            r3[1] = new EqualObserver(this, 1, i);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean setDisposable(Disposable disposable, int i) {
            return this.resources.setResource(i, disposable);
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribe() {
            EqualObserver[] equalObserverArr = this.observers;
            this.first.subscribe(equalObserverArr[0]);
            this.second.subscribe(equalObserverArr[1]);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    EqualObserver[] equalObserverArr = this.observers;
                    equalObserverArr[0].queue.clear();
                    equalObserverArr[1].queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancel(SpscLinkedArrayQueue<T> spscLinkedArrayQueue, SpscLinkedArrayQueue<T> spscLinkedArrayQueue2) {
            this.cancelled = true;
            spscLinkedArrayQueue.clear();
            spscLinkedArrayQueue2.clear();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                EqualObserver[] equalObserverArr = this.observers;
                EqualObserver equalObserver = equalObserverArr[0];
                SpscLinkedArrayQueue spscLinkedArrayQueue = equalObserver.queue;
                EqualObserver equalObserver2 = equalObserverArr[1];
                SpscLinkedArrayQueue spscLinkedArrayQueue2 = equalObserver2.queue;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = equalObserver.done;
                    if (z) {
                        Throwable th = equalObserver.error;
                        if (th != null) {
                            cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                            this.actual.onError(th);
                            return;
                        }
                    }
                    boolean z2 = equalObserver2.done;
                    if (z2) {
                        Throwable th2 = equalObserver2.error;
                        if (th2 != null) {
                            cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                            this.actual.onError(th2);
                            return;
                        }
                    }
                    if (this.v1 == null) {
                        this.v1 = spscLinkedArrayQueue.poll();
                    }
                    int i2 = this.v1 == null ? 1 : 0;
                    if (this.v2 == null) {
                        this.v2 = spscLinkedArrayQueue2.poll();
                    }
                    int i3 = this.v2 == null ? 1 : 0;
                    if (z && z2 && i2 != 0 && i3 != 0) {
                        this.actual.onSuccess(Boolean.valueOf(true));
                        return;
                    } else if (z && z2 && i2 != i3) {
                        cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                        this.actual.onSuccess(Boolean.valueOf(false));
                        return;
                    } else {
                        if (i2 == 0 && i3 == 0) {
                            try {
                                if (this.comparer.test(this.v1, this.v2)) {
                                    this.v1 = null;
                                    this.v2 = null;
                                } else {
                                    cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                                    this.actual.onSuccess(Boolean.valueOf(false));
                                    return;
                                }
                            } catch (Throwable th3) {
                                Exceptions.throwIfFatal(th3);
                                cancel(spscLinkedArrayQueue, spscLinkedArrayQueue2);
                                this.actual.onError(th3);
                                return;
                            }
                        }
                        if (i2 != 0 || i3 != 0) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    }
                }
                spscLinkedArrayQueue.clear();
                spscLinkedArrayQueue2.clear();
            }
        }
    }

    static final class EqualObserver<T> implements Observer<T> {
        volatile boolean done;
        Throwable error;
        final int index;
        final EqualCoordinator<T> parent;
        final SpscLinkedArrayQueue<T> queue;

        EqualObserver(EqualCoordinator<T> equalCoordinator, int i, int i2) {
            this.parent = equalCoordinator;
            this.index = i;
            this.queue = new SpscLinkedArrayQueue(i2);
        }

        public void onSubscribe(Disposable disposable) {
            this.parent.setDisposable(disposable, this.index);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            this.parent.drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            this.parent.drain();
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }
    }

    public ObservableSequenceEqualSingle(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, BiPredicate<? super T, ? super T> biPredicate, int i) {
        this.first = observableSource;
        this.second = observableSource2;
        this.comparer = biPredicate;
        this.bufferSize = i;
    }

    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(singleObserver, this.bufferSize, this.first, this.second, this.comparer);
        singleObserver.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe();
    }

    public Observable<Boolean> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableSequenceEqual(this.first, this.second, this.comparer, this.bufferSize));
    }
}
