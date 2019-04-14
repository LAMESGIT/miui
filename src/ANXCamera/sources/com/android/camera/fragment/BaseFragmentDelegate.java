package com.android.camera.fragment;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.camera.Camera;
import com.android.camera.animation.AnimationComposite;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.beauty.FragmentBlankBeauty;
import com.android.camera.fragment.beauty.FragmentPopuEyeLightTip;
import com.android.camera.fragment.beauty.FragmentPopupBeauty;
import com.android.camera.fragment.beauty.FragmentPopupBeautyLevel;
import com.android.camera.fragment.beauty.FragmentPopupMakeup;
import com.android.camera.fragment.bottom.FragmentBottomAction;
import com.android.camera.fragment.dual.FragmentDualCameraAdjust;
import com.android.camera.fragment.dual.FragmentDualCameraBokehAdjust;
import com.android.camera.fragment.dual.FragmentDualStereo;
import com.android.camera.fragment.fullscreen.FragmentFullScreen;
import com.android.camera.fragment.lifeCircle.BaseLifeCircleBindFragment;
import com.android.camera.fragment.lifeCircle.BaseLifecycleListener;
import com.android.camera.fragment.live.FragmentLiveSpeed;
import com.android.camera.fragment.live.FragmentLiveSticker;
import com.android.camera.fragment.manually.FragmentManually;
import com.android.camera.fragment.sticker.FragmentSticker;
import com.android.camera.fragment.top.FragmentTopConfig;
import com.android.camera.module.loader.StartControl;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.mi.config.b;
import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class BaseFragmentDelegate implements BaseDelegate {
    public static final int BEAUTY_FRAGMENT_CONTAINER_ID = 2131558426;
    public static final int EYE_LIGHT_POPU_TIP_FRAGMENT_CONTAINER_ID = 2131558656;
    public static final int FRAGMENT_BEAUTY = 251;
    public static final int FRAGMENT_BLANK_BEAUTY = 4090;
    public static final int FRAGMENT_BOTTOM_ACTION = 241;
    public static final int FRAGMENT_BOTTOM_INTENT_DONE = 4083;
    public static final int FRAGMENT_BOTTOM_POPUP_TIPS = 4081;
    public static final int FRAGMENT_DUAL_CAMERA_ADJUST = 4084;
    public static final int FRAGMENT_DUAL_CAMERA_BOKEH_ADJUST = 4091;
    public static final int FRAGMENT_DUAL_STEREO = 4085;
    public static final int FRAGMENT_EYE_LIGHT_POPU_TIP = 4089;
    public static final int FRAGMENT_FILTER = 250;
    public static final int FRAGMENT_INVALID = 240;
    public static final int FRAGMENT_LIGHTING = 4087;
    public static final int FRAGMENT_MAIN_CONTENT = 243;
    public static final int FRAGMENT_MANUALLY_EXTRA = 254;
    public static final int FRAGMENT_MODE_SELECT = 242;
    public static final int FRAGMENT_PANORAMA = 4080;
    public static final int FRAGMENT_POPUP_BEAUTY = 249;
    public static final int FRAGMENT_POPUP_BEAUTYLEVEL = 4082;
    public static final int FRAGMENT_POPUP_LIVE_SPEED = 4093;
    public static final int FRAGMENT_POPUP_LIVE_STICKER = 4092;
    public static final int FRAGMENT_POPUP_MAKEUP = 252;
    public static final int FRAGMENT_POPUP_MANUALLY = 247;
    public static final int FRAGMENT_SCREEN_LIGHT = 4086;
    public static final int FRAGMENT_STICKER = 255;
    public static final int FRAGMENT_TOP_ALERT = 253;
    public static final int FRAGMENT_TOP_CONFIG = 244;
    public static final int FRAGMENT_TOP_CONFIG_EXTRA = 245;
    public static final int FRAGMENT_VERTICAL = 4088;
    public static final int MAKE_UP_POPU_FRAGMENT_CONTAINER_ID = 2131558655;
    private static final String TAG = BaseFragmentDelegate.class.getSimpleName();
    private AnimationComposite animationComposite = new AnimationComposite();
    private SparseArray<List<Integer>> currentFragments;
    private Camera mActivity;
    private SparseIntArray originalFragments = new SparseIntArray();

    @Retention(RetentionPolicy.SOURCE)
    public @interface FragmentInto {
    }

    public BaseFragmentDelegate(Camera camera) {
        this.mActivity = camera;
    }

    public void registerProtocol() {
        ModeCoordinatorImpl.getInstance().attachProtocol(160, this);
    }

    public void unRegisterProtocol() {
        ModeCoordinatorImpl.getInstance().detachProtocol(160, this);
        this.animationComposite.destroy();
        this.mActivity = null;
    }

    public void init(FragmentManager fragmentManager, int i, BaseLifecycleListener baseLifecycleListener) {
        BaseFragment constructFragment;
        BaseLifecycleListener baseLifecycleListener2 = baseLifecycleListener;
        registerProtocol();
        BaseFragment constructFragment2 = constructFragment(true, 244, 240, baseLifecycleListener2);
        BaseFragment constructFragment3 = constructFragment(true, 247, 240, baseLifecycleListener2);
        BaseFragment constructFragment4 = constructFragment(true, 4081, 240, baseLifecycleListener2);
        BaseFragment constructFragment5 = constructFragment(true, 241, 240, baseLifecycleListener2);
        BaseFragment constructFragment6 = constructFragment(true, 243, 240, baseLifecycleListener2);
        BaseFragment constructFragment7 = constructFragment(true, 4080, 240, baseLifecycleListener2);
        BaseFragment constructFragment8 = constructFragment(true, 4086, 240, baseLifecycleListener2);
        BaseFragment constructFragment9 = constructFragment(true, FRAGMENT_VERTICAL, 240, baseLifecycleListener2);
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(2131558654, constructFragment4, constructFragment4.getFragmentTag());
        beginTransaction.replace(2131558427, constructFragment5, constructFragment5.getFragmentTag());
        beginTransaction.replace(2131558650, constructFragment2, constructFragment2.getFragmentTag());
        beginTransaction.replace(2131558651, constructFragment3, constructFragment3.getFragmentTag());
        beginTransaction.replace(2131558647, constructFragment6, constructFragment6.getFragmentTag());
        beginTransaction.replace(2131558648, constructFragment7, constructFragment7.getFragmentTag());
        beginTransaction.replace(2131558657, constructFragment8, constructFragment8.getFragmentTag());
        beginTransaction.replace(2131558649, constructFragment9, constructFragment9.getFragmentTag());
        if (b.isSupportedOpticalZoom()) {
            constructFragment = constructFragment(true, 4084, 240, baseLifecycleListener2);
        } else if (b.hy()) {
            constructFragment = constructFragment(true, 4085, 240, baseLifecycleListener2);
        } else {
            constructFragment = null;
        }
        if (constructFragment != null) {
            this.originalFragments.put(2131558652, constructFragment.getFragmentInto());
            this.animationComposite.put(constructFragment.getFragmentInto(), constructFragment);
            beginTransaction.replace(2131558652, constructFragment, constructFragment.getFragmentTag());
        } else {
            this.originalFragments.put(2131558652, 240);
        }
        if (DataRepository.dataItemFeature().isSupportBokehAdjust()) {
            BaseFragment constructFragment10 = constructFragment(true, 4091, 240, baseLifecycleListener2);
            this.originalFragments.put(2131558653, constructFragment10.getFragmentInto());
            this.animationComposite.put(constructFragment10.getFragmentInto(), constructFragment10);
            beginTransaction.replace(2131558653, constructFragment10, constructFragment10.getFragmentTag());
        }
        this.originalFragments.put(2131558654, constructFragment4.getFragmentInto());
        this.originalFragments.put(2131558427, constructFragment5.getFragmentInto());
        this.originalFragments.put(2131558650, constructFragment2.getFragmentInto());
        this.originalFragments.put(2131558655, 240);
        this.originalFragments.put(2131558651, constructFragment3.getFragmentInto());
        this.originalFragments.put(2131558647, constructFragment6.getFragmentInto());
        this.originalFragments.put(2131558648, constructFragment7.getFragmentInto());
        this.originalFragments.put(2131558657, constructFragment8.getFragmentInto());
        this.originalFragments.put(2131558426, 240);
        this.originalFragments.put(2131558656, 240);
        this.animationComposite.put(constructFragment4.getFragmentInto(), constructFragment4);
        this.animationComposite.put(constructFragment2.getFragmentInto(), constructFragment2);
        this.animationComposite.put(constructFragment3.getFragmentInto(), constructFragment3);
        this.animationComposite.put(constructFragment6.getFragmentInto(), constructFragment6);
        this.animationComposite.put(constructFragment5.getFragmentInto(), constructFragment5);
        this.animationComposite.put(constructFragment7.getFragmentInto(), constructFragment7);
        this.animationComposite.put(constructFragment8.getFragmentInto(), constructFragment8);
        this.animationComposite.put(constructFragment9.getFragmentInto(), constructFragment9);
        initCurrentFragments(this.originalFragments);
        beginTransaction.commitAllowingStateLoss();
        baseLifecycleListener.onLifeAlive();
    }

    private void initCurrentFragments(SparseIntArray sparseIntArray) {
        int size = sparseIntArray.size();
        this.currentFragments = new SparseArray(size);
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(sparseIntArray.valueAt(i)));
            this.currentFragments.put(sparseIntArray.keyAt(i), arrayList);
        }
    }

    public Disposable delegateMode(@Nullable Completable completable, StartControl startControl, final BaseLifecycleListener baseLifecycleListener) {
        Action action = null;
        if (this.mActivity == null) {
            return null;
        }
        if (baseLifecycleListener != null) {
            action = new Action() {
                public void run() throws Exception {
                    baseLifecycleListener.onLifeAlive();
                }
            };
        }
        return this.animationComposite.dispose(completable, action, startControl);
    }

    private void applyUpdateSet(List<BaseFragmentOperation> list, BaseLifecycleListener baseLifecycleListener) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("need operation info");
        } else if (this.mActivity == null || !this.mActivity.isFinishing()) {
            FragmentManager supportFragmentManager = this.mActivity.getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            for (BaseFragmentOperation baseFragmentOperation : list) {
                int activeFragment = getActiveFragment(baseFragmentOperation.containerViewId);
                int i = baseFragmentOperation.pendingFragmentInfo;
                BaseFragment constructFragment;
                BaseFragment baseFragment;
                BaseFragment baseFragment2;
                switch (baseFragmentOperation.operateType) {
                    case 1:
                        activeFragment = getActiveFragment(baseFragmentOperation.containerViewId);
                        this.animationComposite.remove(activeFragment);
                        constructFragment = constructFragment(false, i, activeFragment, baseLifecycleListener);
                        beginTransaction.replace(baseFragmentOperation.containerViewId, constructFragment, constructFragment.getFragmentTag());
                        this.animationComposite.put(constructFragment.getFragmentInto(), constructFragment);
                        updateCurrentFragments(baseFragmentOperation.containerViewId, i, baseFragmentOperation.operateType);
                        break;
                    case 2:
                        this.animationComposite.remove(i);
                        constructFragment = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(i));
                        if (constructFragment != null) {
                            constructFragment.pendingGone(false);
                            beginTransaction.remove(constructFragment);
                        }
                        updateCurrentFragments(baseFragmentOperation.containerViewId, i, baseFragmentOperation.operateType);
                        break;
                    case 3:
                        this.animationComposite.remove(activeFragment);
                        baseFragment = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(activeFragment));
                        if (baseFragment != null) {
                            baseFragment.pendingGone(false);
                            beginTransaction.remove(baseFragment);
                        }
                        updateCurrentFragments(baseFragmentOperation.containerViewId, activeFragment, baseFragmentOperation.operateType);
                        break;
                    case 4:
                        baseFragment2 = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(activeFragment));
                        if (baseFragment2 != null) {
                            baseFragment2.pendingGone(false);
                            beginTransaction.hide(baseFragment2);
                        }
                        baseFragment2 = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(i));
                        if (baseFragment2 != null) {
                            baseFragment2.pendingShow();
                            beginTransaction.show(baseFragment2);
                        } else {
                            baseFragment2 = constructFragment(false, i, activeFragment, baseLifecycleListener);
                            beginTransaction.add(baseFragmentOperation.containerViewId, baseFragment2, baseFragment2.getFragmentTag());
                        }
                        this.animationComposite.put(baseFragment2.getFragmentInto(), baseFragment2);
                        updateCurrentFragments(baseFragmentOperation.containerViewId, i, baseFragmentOperation.operateType);
                        break;
                    case 5:
                        List list2 = (List) this.currentFragments.get(baseFragmentOperation.containerViewId);
                        for (int i2 = 0; i2 < list2.size(); i2++) {
                            int intValue = ((Integer) list2.get(i2)).intValue();
                            if (intValue != i) {
                                this.animationComposite.remove(intValue);
                                BaseFragment baseFragment3 = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(intValue));
                                if (baseFragment3 != null) {
                                    if (intValue != activeFragment) {
                                        baseFragment3.pendingGone(true);
                                    } else {
                                        baseFragment3.pendingGone(false);
                                    }
                                    beginTransaction.remove(baseFragment3);
                                }
                            }
                        }
                        baseFragment2 = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(i));
                        baseFragment2.setLastFragmentInfo(activeFragment);
                        baseFragment2.pendingShow();
                        beginTransaction.show(baseFragment2);
                        updateCurrentFragments(baseFragmentOperation.containerViewId, i, baseFragmentOperation.operateType);
                        break;
                    case 6:
                        if (activeFragment != i) {
                            baseFragment2 = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(activeFragment));
                            if (baseFragment2 != null) {
                                baseFragment2.pendingGone(true);
                                beginTransaction.hide(baseFragment2);
                            }
                        }
                        baseFragment2 = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(i));
                        baseFragment2.setLastFragmentInfo(activeFragment);
                        baseFragment2.pendingShow();
                        beginTransaction.show(baseFragment2);
                        updateCurrentFragments(baseFragmentOperation.containerViewId, i, baseFragmentOperation.operateType);
                        break;
                    case 7:
                        baseFragment = (BaseFragment) supportFragmentManager.findFragmentByTag(String.valueOf(activeFragment));
                        if (baseFragment != null) {
                            beginTransaction.hide(baseFragment);
                        }
                        updateCurrentFragments(baseFragmentOperation.containerViewId, activeFragment, baseFragmentOperation.operateType);
                        break;
                    default:
                        break;
                }
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    /* JADX WARNING: Missing block: B:3:0x0012, code skipped:
            if (r0 >= r2.size()) goto L_0x009b;
     */
    /* JADX WARNING: Missing block: B:5:0x001e, code skipped:
            if (((java.lang.Integer) r2.get(r0)).intValue() != r3) goto L_0x0025;
     */
    /* JADX WARNING: Missing block: B:6:0x0020, code skipped:
            r2.remove(r0);
     */
    /* JADX WARNING: Missing block: B:7:0x0025, code skipped:
            r0 = r0 + 1;
     */
    /* JADX WARNING: Missing block: B:9:0x002c, code skipped:
            if (r0 >= r2.size()) goto L_0x0041;
     */
    /* JADX WARNING: Missing block: B:11:0x0038, code skipped:
            if (((java.lang.Integer) r2.get(r0)).intValue() != r3) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:12:0x003a, code skipped:
            r2.remove(r0);
     */
    /* JADX WARNING: Missing block: B:13:0x003e, code skipped:
            r0 = r0 + 1;
     */
    /* JADX WARNING: Missing block: B:14:0x0041, code skipped:
            r2.add(java.lang.Integer.valueOf(r3));
     */
    /* JADX WARNING: Missing block: B:18:0x0060, code skipped:
            if (r0 >= r2.size()) goto L_0x009b;
     */
    /* JADX WARNING: Missing block: B:20:0x006c, code skipped:
            if (((java.lang.Integer) r2.get(r0)).intValue() != r3) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:21:0x006e, code skipped:
            r2.remove(r0);
     */
    /* JADX WARNING: Missing block: B:22:0x0072, code skipped:
            r0 = r0 + 1;
     */
    /* JADX WARNING: Missing block: B:24:0x007a, code skipped:
            if (r0 >= r2.size()) goto L_0x009b;
     */
    /* JADX WARNING: Missing block: B:26:0x0086, code skipped:
            if (((java.lang.Integer) r2.get(r0)).intValue() != r3) goto L_0x008c;
     */
    /* JADX WARNING: Missing block: B:27:0x0088, code skipped:
            r2.remove(r0);
     */
    /* JADX WARNING: Missing block: B:28:0x008c, code skipped:
            r0 = r0 + 1;
     */
    /* JADX WARNING: Missing block: B:39:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:40:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:44:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:45:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:46:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:47:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:48:?, code skipped:
            return;
     */
    private void updateCurrentFragments(@android.support.annotation.IdRes int r2, int r3, int r4) {
        /*
        r1 = this;
        r0 = r1.currentFragments;
        r2 = r0.get(r2);
        r2 = (java.util.List) r2;
        r0 = 0;
        switch(r4) {
            case 1: goto L_0x0090;
            case 2: goto L_0x0076;
            case 3: goto L_0x005c;
            case 4: goto L_0x0054;
            case 5: goto L_0x0049;
            case 6: goto L_0x0028;
            case 7: goto L_0x000e;
            default: goto L_0x000c;
        };
    L_0x000c:
        goto L_0x009b;
    L_0x000e:
        r4 = r2.size();
        if (r0 >= r4) goto L_0x009b;
    L_0x0014:
        r4 = r2.get(r0);
        r4 = (java.lang.Integer) r4;
        r4 = r4.intValue();
        if (r4 != r3) goto L_0x0025;
    L_0x0020:
        r2.remove(r0);
        goto L_0x009b;
    L_0x0025:
        r0 = r0 + 1;
        goto L_0x000e;
    L_0x0028:
        r4 = r2.size();
        if (r0 >= r4) goto L_0x0041;
    L_0x002e:
        r4 = r2.get(r0);
        r4 = (java.lang.Integer) r4;
        r4 = r4.intValue();
        if (r4 != r3) goto L_0x003e;
    L_0x003a:
        r2.remove(r0);
        goto L_0x0041;
    L_0x003e:
        r0 = r0 + 1;
        goto L_0x0028;
    L_0x0041:
        r3 = java.lang.Integer.valueOf(r3);
        r2.add(r3);
        goto L_0x009b;
    L_0x0049:
        r2.clear();
        r3 = java.lang.Integer.valueOf(r3);
        r2.add(r3);
        goto L_0x009b;
    L_0x0054:
        r3 = java.lang.Integer.valueOf(r3);
        r2.add(r3);
        goto L_0x009b;
    L_0x005c:
        r4 = r2.size();
        if (r0 >= r4) goto L_0x0075;
    L_0x0062:
        r4 = r2.get(r0);
        r4 = (java.lang.Integer) r4;
        r4 = r4.intValue();
        if (r4 != r3) goto L_0x0072;
    L_0x006e:
        r2.remove(r0);
        goto L_0x0075;
    L_0x0072:
        r0 = r0 + 1;
        goto L_0x005c;
    L_0x0075:
        goto L_0x009b;
    L_0x0076:
        r4 = r2.size();
        if (r0 >= r4) goto L_0x008f;
    L_0x007c:
        r4 = r2.get(r0);
        r4 = (java.lang.Integer) r4;
        r4 = r4.intValue();
        if (r4 != r3) goto L_0x008c;
    L_0x0088:
        r2.remove(r0);
        goto L_0x008f;
    L_0x008c:
        r0 = r0 + 1;
        goto L_0x0076;
    L_0x008f:
        goto L_0x009b;
    L_0x0090:
        r2.clear();
        r3 = java.lang.Integer.valueOf(r3);
        r2.add(r3);
    L_0x009b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.BaseFragmentDelegate.updateCurrentFragments(int, int, int):void");
    }

    private BaseFragment constructFragment(boolean z, int i, int i2, BaseLifecycleListener baseLifecycleListener) {
        BaseFragment baseFragment = null;
        if (i == 247) {
            baseFragment = new FragmentManually();
        } else if (i != 255) {
            switch (i) {
                case 240:
                    return null;
                case 241:
                    baseFragment = new FragmentBottomAction();
                    break;
                default:
                    switch (i) {
                        case 243:
                            baseFragment = new FragmentMainContent();
                            break;
                        case 244:
                            baseFragment = new FragmentTopConfig();
                            break;
                        default:
                            switch (i) {
                                case 249:
                                    baseFragment = new FragmentPopupBeauty();
                                    break;
                                case 250:
                                    baseFragment = new FragmentFilter();
                                    break;
                                case 251:
                                    baseFragment = new FragmentBeauty();
                                    break;
                                case 252:
                                    baseFragment = new FragmentPopupMakeup();
                                    break;
                                default:
                                    switch (i) {
                                        case 4080:
                                            baseFragment = new FragmentPanorama();
                                            break;
                                        case 4081:
                                            baseFragment = new FragmentBottomPopupTips();
                                            break;
                                        case 4082:
                                            baseFragment = new FragmentPopupBeautyLevel();
                                            break;
                                        case 4083:
                                            baseFragment = new FragmentBottomIntentDone();
                                            break;
                                        case 4084:
                                            baseFragment = new FragmentDualCameraAdjust();
                                            break;
                                        case 4085:
                                            baseFragment = new FragmentDualStereo();
                                            break;
                                        case 4086:
                                            baseFragment = new FragmentFullScreen();
                                            break;
                                        default:
                                            switch (i) {
                                                case FRAGMENT_VERTICAL /*4088*/:
                                                    baseFragment = new FragmentVertical();
                                                    break;
                                                case 4089:
                                                    baseFragment = new FragmentPopuEyeLightTip();
                                                    break;
                                                case 4090:
                                                    baseFragment = new FragmentBlankBeauty();
                                                    break;
                                                case 4091:
                                                    baseFragment = new FragmentDualCameraBokehAdjust();
                                                    break;
                                                case FRAGMENT_POPUP_LIVE_STICKER /*4092*/:
                                                    baseFragment = new FragmentLiveSticker();
                                                    break;
                                                case FRAGMENT_POPUP_LIVE_SPEED /*4093*/:
                                                    baseFragment = new FragmentLiveSpeed();
                                                    break;
                                            }
                                            break;
                                    }
                            }
                    }
            }
        } else {
            baseFragment = new FragmentSticker();
        }
        baseFragment.setLastFragmentInfo(i2);
        baseFragment.setLifecycleListener(baseLifecycleListener);
        baseFragment.registerProtocol();
        baseFragment.setEnableClickInitValue(z ^ 1);
        return baseFragment;
    }

    public void delegateEvent(int i) {
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 1:
                if (getActiveFragment(2131558427) != 250) {
                    arrayList.add(BaseFragmentOperation.create(2131558427).push(250));
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558427).popAndClearOthers(241));
                }
                arrayList.add(BaseFragmentOperation.create(2131558655).removeCurrent());
                break;
            case 2:
                if (getActiveFragment(2131558426) == 251) {
                    arrayList.add(BaseFragmentOperation.create(2131558426).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558426).replaceWith(251));
                    break;
                }
            case 3:
                if (getActiveFragment(2131558655) == 252) {
                    arrayList.add(BaseFragmentOperation.create(2131558655).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558655).replaceWith(252));
                    break;
                }
            case 4:
                if (getActiveFragment(2131558427) == 255) {
                    arrayList.add(BaseFragmentOperation.create(2131558427).popAndClearOthers(241));
                    arrayList.add(BaseFragmentOperation.create(2131558654).replaceWith(4081));
                    break;
                }
                arrayList.add(BaseFragmentOperation.create(2131558427).push(255));
                arrayList.add(BaseFragmentOperation.create(2131558655).removeCurrent());
                break;
            case 5:
                if (getActiveFragment(2131558655) == 4082) {
                    arrayList.add(BaseFragmentOperation.create(2131558655).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558655).replaceWith(4082));
                    break;
                }
            case 6:
                if (getActiveFragment(2131558427) == 4083) {
                    arrayList.add(BaseFragmentOperation.create(2131558427).popAndClearOthers(241));
                    arrayList.add(BaseFragmentOperation.create(2131558650).show(getOriginalFragment(2131558650)));
                    arrayList.add(BaseFragmentOperation.create(2131558651).show(getOriginalFragment(2131558651)));
                    if (!this.mActivity.getCameraIntentManager().isVideoCaptureIntent()) {
                        arrayList.add(BaseFragmentOperation.create(2131558647).show(getOriginalFragment(2131558647)));
                    }
                    arrayList.add(BaseFragmentOperation.create(2131558654).show(getOriginalFragment(2131558654)));
                    if (b.isSupportedOpticalZoom() || b.hy()) {
                        arrayList.add(BaseFragmentOperation.create(2131558652).show(getOriginalFragment(2131558652)));
                        break;
                    }
                }
                arrayList.add(BaseFragmentOperation.create(2131558427).push(4083));
                arrayList.add(BaseFragmentOperation.create(2131558650).hideCurrent());
                arrayList.add(BaseFragmentOperation.create(2131558651).hideCurrent());
                if (!this.mActivity.getCameraIntentManager().isVideoCaptureIntent()) {
                    arrayList.add(BaseFragmentOperation.create(2131558647).hideCurrent());
                }
                arrayList.add(BaseFragmentOperation.create(2131558654).hideCurrent());
                arrayList.add(BaseFragmentOperation.create(2131558652).hideCurrent());
                break;
            case 7:
                if (getActiveFragment(2131558427) != 241) {
                    arrayList.add(BaseFragmentOperation.create(2131558427).popAndClearOthers(241));
                    arrayList.add(BaseFragmentOperation.create(2131558655).removeCurrent());
                    arrayList.add(BaseFragmentOperation.create(2131558654).replaceWith(4081));
                    break;
                }
                break;
            case 9:
                if (getActiveFragment(2131558656) != 240) {
                    arrayList.add(BaseFragmentOperation.create(2131558656).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558656).replaceWith(4089));
                    break;
                }
            case 10:
                if (getActiveFragment(2131558426) == 4090) {
                    arrayList.add(BaseFragmentOperation.create(2131558426).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558426).replaceWith(4090));
                    break;
                }
            case 12:
                if (getActiveFragment(2131558426) == FRAGMENT_POPUP_LIVE_STICKER) {
                    arrayList.add(BaseFragmentOperation.create(2131558426).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558426).replaceWith(FRAGMENT_POPUP_LIVE_STICKER));
                    break;
                }
            case 13:
                if (getActiveFragment(2131558426) == FRAGMENT_POPUP_LIVE_SPEED) {
                    arrayList.add(BaseFragmentOperation.create(2131558426).removeCurrent());
                    break;
                } else {
                    arrayList.add(BaseFragmentOperation.create(2131558426).replaceWith(FRAGMENT_POPUP_LIVE_SPEED));
                    break;
                }
        }
        applyUpdateSet(arrayList, null);
    }

    public int getActiveFragment(@IdRes int i) {
        List list = (List) this.currentFragments.get(i);
        if (list == null || list.isEmpty()) {
            return 240;
        }
        return ((Integer) list.get(list.size() - 1)).intValue();
    }

    public int getOriginalFragment(@IdRes int i) {
        return this.originalFragments.get(i, 240);
    }

    public AnimationComposite getAnimationComposite() {
        return this.animationComposite;
    }

    @Deprecated
    public static void bindLifeCircle(Fragment fragment) {
        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
        BaseLifeCircleBindFragment baseLifeCircleBindFragment = new BaseLifeCircleBindFragment();
        baseLifeCircleBindFragment.getLifecycle().addListener(new BaseLifecycleListener() {
            public void setBlockingLifeCycles(List<String> list) {
            }

            public void onLifeStart(String str) {
            }

            public void onLifeAlive() {
            }

            public void onLifeStop(String str) {
            }

            public void onLifeDestroy(String str) {
            }
        }, BaseLifeCircleBindFragment.FRAGMENT_TAG);
        childFragmentManager.beginTransaction().add(baseLifeCircleBindFragment, BaseLifeCircleBindFragment.FRAGMENT_TAG).commitAllowingStateLoss();
    }
}
