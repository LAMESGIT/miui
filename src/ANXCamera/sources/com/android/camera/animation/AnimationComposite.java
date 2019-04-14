package com.android.camera.animation;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.android.camera.animation.AnimationDelegate.AnimationResource;
import com.android.camera.data.DataRepository;
import com.ss.android.vesdk.VEResult;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

public class AnimationComposite implements Consumer<Integer> {
    private Disposable mAfterFrameArrivedDisposable = Observable.create(new ObservableOnSubscribe<Integer>() {
        public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
            AnimationComposite.this.mAfterFrameArrivedEmitter = observableEmitter;
        }
    }).observeOn(AndroidSchedulers.mainThread()).subscribe((Consumer) this);
    private ObservableEmitter<Integer> mAfterFrameArrivedEmitter;
    private Disposable mAnimationDisposable;
    private int mCurrentDegree = 0;
    private int mOrientation = -1;
    private SparseArray<AnimationResource> mResourceSparseArray = new SparseArray();
    private ValueAnimator mRotationAnimator;
    private int mStartDegree = 0;
    private int mTargetDegree = 0;

    public void put(int i, AnimationResource animationResource) {
        this.mResourceSparseArray.put(i, animationResource);
    }

    public void remove(int i) {
        this.mResourceSparseArray.remove(i);
    }

    public io.reactivex.disposables.Disposable dispose(@android.support.annotation.Nullable io.reactivex.Completable r6, @android.support.annotation.Nullable io.reactivex.functions.Action r7, com.android.camera.module.loader.StartControl r8) {
        /*
        r5 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        if (r6 == 0) goto L_0x000a;
    L_0x0007:
        r0.add(r6);
    L_0x000a:
        r6 = r8.mTargetMode;
        r1 = r8.mResetType;
        r8 = r8.mViewConfigType;
        r2 = 0;
        r3 = 0;
        switch(r8) {
            case 1: goto L_0x0057;
            case 2: goto L_0x0039;
            case 3: goto L_0x0016;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0075;
    L_0x0016:
        r8 = r5.mResourceSparseArray;
        r8 = r8.size();
        if (r3 >= r8) goto L_0x0075;
    L_0x001e:
        r8 = r5.mResourceSparseArray;
        r8 = r8.valueAt(r3);
        r8 = (com.android.camera.animation.AnimationDelegate.AnimationResource) r8;
        r4 = r8.canProvide();
        if (r4 == 0) goto L_0x0036;
    L_0x002c:
        r4 = r8.needViewClear();
        if (r4 != 0) goto L_0x0033;
    L_0x0032:
        goto L_0x0036;
    L_0x0033:
        r8.provideAnimateElement(r6, r2, r1);
    L_0x0036:
        r3 = r3 + 1;
        goto L_0x0016;
    L_0x0039:
        r8 = r5.mResourceSparseArray;
        r8 = r8.size();
        if (r3 >= r8) goto L_0x0056;
    L_0x0041:
        r8 = r5.mResourceSparseArray;
        r8 = r8.valueAt(r3);
        r8 = (com.android.camera.animation.AnimationDelegate.AnimationResource) r8;
        r2 = r8.canProvide();
        if (r2 != 0) goto L_0x0050;
    L_0x004f:
        goto L_0x0053;
    L_0x0050:
        r8.provideAnimateElement(r6, r0, r1);
    L_0x0053:
        r3 = r3 + 1;
        goto L_0x0039;
    L_0x0056:
        goto L_0x0075;
    L_0x0057:
        r8 = r5.mResourceSparseArray;
        r8 = r8.size();
        if (r3 >= r8) goto L_0x0074;
    L_0x005f:
        r8 = r5.mResourceSparseArray;
        r8 = r8.valueAt(r3);
        r8 = (com.android.camera.animation.AnimationDelegate.AnimationResource) r8;
        r4 = r8.canProvide();
        if (r4 != 0) goto L_0x006e;
    L_0x006d:
        goto L_0x0071;
    L_0x006e:
        r8.provideAnimateElement(r6, r2, r1);
    L_0x0071:
        r3 = r3 + 1;
        goto L_0x0057;
    L_0x0075:
        r6 = r5.mAnimationDisposable;
        if (r6 == 0) goto L_0x0086;
    L_0x0079:
        r6 = r5.mAnimationDisposable;
        r6 = r6.isDisposed();
        if (r6 != 0) goto L_0x0086;
    L_0x0081:
        r6 = r5.mAnimationDisposable;
        r6.dispose();
    L_0x0086:
        if (r7 == 0) goto L_0x0093;
    L_0x0088:
        r6 = io.reactivex.Completable.merge(r0);
        r6 = r6.subscribe(r7);
        r5.mAnimationDisposable = r6;
        goto L_0x009d;
    L_0x0093:
        r6 = io.reactivex.Completable.merge(r0);
        r6 = r6.subscribe();
        r5.mAnimationDisposable = r6;
    L_0x009d:
        r6 = r5.mAnimationDisposable;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.animation.AnimationComposite.dispose(io.reactivex.Completable, io.reactivex.functions.Action, com.android.camera.module.loader.StartControl):io.reactivex.disposables.Disposable");
    }

    public void disposeRotation(int i) {
        int i2 = 360;
        i = i >= 0 ? i % 360 : (i % 360) + 360;
        if (this.mOrientation != i) {
            int i3 = this.mOrientation != -1 ? 1 : 0;
            int i4 = i - this.mOrientation;
            if (i4 < 0) {
                i4 += 360;
            }
            if (i4 > 180) {
                i4 += VEResult.TER_EGL_BAD_MATCH;
            }
            i4 = i4 <= 0 ? 1 : 0;
            this.mOrientation = i;
            if (this.mOrientation != 0 || this.mCurrentDegree != 0) {
                this.mTargetDegree = (360 - i) % 360;
                final ArrayList<View> arrayList = new ArrayList();
                for (int i5 = 0; i5 < this.mResourceSparseArray.size(); i5++) {
                    AnimationResource animationResource = (AnimationResource) this.mResourceSparseArray.valueAt(i5);
                    if (animationResource.canProvide()) {
                        animationResource.provideRotateItem(arrayList, this.mTargetDegree);
                    }
                }
                if (i3 == 0) {
                    this.mCurrentDegree = this.mTargetDegree;
                    for (View rotation : arrayList) {
                        ViewCompat.setRotation(rotation, (float) this.mTargetDegree);
                    }
                    return;
                }
                if (this.mRotationAnimator != null) {
                    this.mRotationAnimator.cancel();
                }
                this.mStartDegree = this.mCurrentDegree;
                if (i4 != 0) {
                    if (this.mStartDegree == 360) {
                        i3 = 0;
                    } else {
                        i3 = this.mStartDegree;
                    }
                    if (this.mTargetDegree != 0) {
                        i2 = this.mTargetDegree;
                    }
                } else {
                    if (this.mStartDegree == 0) {
                        i3 = 360;
                    } else {
                        i3 = this.mStartDegree;
                    }
                    if (this.mTargetDegree == 360) {
                        i2 = 0;
                    } else {
                        i2 = this.mTargetDegree;
                    }
                }
                this.mRotationAnimator = ValueAnimator.ofInt(new int[]{i3, i2});
                this.mRotationAnimator.setInterpolator(new LinearInterpolator());
                this.mRotationAnimator.removeAllUpdateListeners();
                this.mRotationAnimator.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        AnimationComposite.this.mCurrentDegree = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        for (View rotation : arrayList) {
                            ViewCompat.setRotation(rotation, (float) AnimationComposite.this.mCurrentDegree);
                        }
                    }
                });
                this.mRotationAnimator.start();
            }
        }
    }

    public void setClickEnable(boolean z) {
        for (int i = 0; i < this.mResourceSparseArray.size(); i++) {
            AnimationResource animationResource = (AnimationResource) this.mResourceSparseArray.valueAt(i);
            if (animationResource.canProvide() && animationResource.isEnableClick() != z) {
                animationResource.setClickEnable(z);
            }
        }
    }

    public void notifyDataChanged(int i, int i2) {
        for (int i3 = 0; i3 < this.mResourceSparseArray.size(); i3++) {
            AnimationResource animationResource = (AnimationResource) this.mResourceSparseArray.valueAt(i3);
            if (animationResource.canProvide()) {
                animationResource.notifyDataChanged(i, i2);
            }
        }
    }

    public void notifyAfterFirstFrameArrived(int i) {
        if (this.mAfterFrameArrivedDisposable != null && !this.mAfterFrameArrivedDisposable.isDisposed()) {
            this.mAfterFrameArrivedEmitter.onNext(Integer.valueOf(i));
        }
    }

    public void destroy() {
        if (this.mResourceSparseArray != null) {
            this.mResourceSparseArray.clear();
            this.mResourceSparseArray = null;
        }
        if (this.mAfterFrameArrivedDisposable != null && !this.mAfterFrameArrivedDisposable.isDisposed()) {
            this.mAfterFrameArrivedEmitter.onComplete();
            this.mAfterFrameArrivedDisposable.dispose();
            this.mAfterFrameArrivedDisposable = null;
        }
    }

    public void accept(@NonNull Integer num) throws Exception {
        int i = 0;
        DataRepository.dataItemGlobal().setRetriedIfCameraError(false);
        while (i < this.mResourceSparseArray.size()) {
            AnimationResource animationResource = (AnimationResource) this.mResourceSparseArray.valueAt(i);
            if (animationResource.canProvide()) {
                if (!animationResource.isEnableClick()) {
                    animationResource.setClickEnable(true);
                }
                animationResource.notifyAfterFrameAvailable(num.intValue());
            }
            i++;
        }
    }
}
