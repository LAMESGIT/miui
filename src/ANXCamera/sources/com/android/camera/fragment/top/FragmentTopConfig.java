package com.android.camera.fragment.top;

import android.animation.ObjectAnimator;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.android.camera.ActivityBase;
import com.android.camera.Camera;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.ToastUtils;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.constant.DurationConstant;
import com.android.camera.constant.EyeLightConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.config.ComponentConfigFlash;
import com.android.camera.data.data.config.ComponentConfigHdr;
import com.android.camera.data.data.config.DataItemConfig;
import com.android.camera.data.data.config.SupportedConfigFactory;
import com.android.camera.data.data.config.SupportedConfigs;
import com.android.camera.data.data.config.TopConfigItem;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.FragmentUtils;
import com.android.camera.fragment.music.FragmentLiveMusic;
import com.android.camera.fragment.top.ExpandAdapter.ExpandListener;
import com.android.camera.log.Log;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.HandleBeautyRecording;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import io.reactivex.Completable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import miui.view.animation.CubicEaseInOutInterpolator;

public class FragmentTopConfig extends BaseFragment implements OnClickListener, ExpandListener, HandleBackTrace, HandleBeautyRecording, TopAlert {
    private static final int EXPAND_STATE_CENTER = 2;
    private static final int EXPAND_STATE_LEFT = 0;
    private static final int EXPAND_STATE_LEFT_FROM_SIBLING = 1;
    private static final int EXPAND_STATE_RIGHT = 4;
    private static final int EXPAND_STATE_RIGHT_FROM_SIBLING = 3;
    private static final String TAG = "FragmentTopConfig";
    private int[] mAiSceneResources;
    private int[] mAutoZoomResources;
    private List<ImageView> mConfigViews;
    private int mCurrentAiSceneLevel = CameraSettings.getAiSceneOpen();
    private Set<Integer> mDisabledFunctionMenu;
    private int mDisplayRectTopMargin;
    private RecyclerView mExpandView;
    private int[] mFilterResources;
    private FragmentTopAlert mFragmentTopAlert;
    private FragmentTopConfigExtra mFragmentTopConfigExtra;
    private boolean mIsRTL;
    private LastAnimationComponent mLastAnimationComponent;
    private int[] mLightingResource;
    private int[] mLiveMusicSelectResources;
    private ObjectAnimator mLiveShotAnimator;
    private int[] mLiveShotResource;
    private SupportedConfigs mSupportedConfigs;
    private View mTopConfigMenu;
    private int mTopDrawableWidth;
    private ViewGroup mTopExtraParent;
    private int mTotalWidth;
    private int[] mUltraPixelPhotographyResources;
    private int[] mUltraWideBokehResources;
    private int[] mUltraWideResource;
    private int[] mVideoBokehResource;
    private boolean mVideoRecordingStarted;
    private int mViewPadding;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mAiSceneResources = getAiSceneResources();
        this.mAutoZoomResources = getAutoZoomResources();
        this.mUltraWideResource = getUltraWideResources();
        this.mUltraWideBokehResources = getUltraWideBokehResources();
        this.mUltraPixelPhotographyResources = getUltraPixelPhotographyResources();
        this.mLiveShotResource = getLiveShotResources();
        this.mLightingResource = getLightingResources();
        this.mVideoBokehResource = getVideoBokehResources();
        this.mFilterResources = getFilterResources();
        this.mLiveMusicSelectResources = getMusicSelectResources();
        this.mIsRTL = Util.isLayoutRTL(getContext());
        this.mLastAnimationComponent = new LastAnimationComponent();
        this.mDisabledFunctionMenu = new HashSet();
        this.mTopExtraParent = (ViewGroup) view.findViewById(2131558583);
        this.mTopConfigMenu = view.findViewById(2131558570);
        if (Util.isNotchDevice) {
            ((MarginLayoutParams) this.mTopConfigMenu.getLayoutParams()).topMargin = Util.sStatusBarHeight;
        }
        initTopView();
        this.mExpandView = (RecyclerView) view.findViewById(2131558582);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.mExpandView.setLayoutManager(linearLayoutManager);
        this.mViewPadding = getResources().getDimensionPixelSize(2131361825);
        this.mTopDrawableWidth = getResources().getDrawable(2130837807).getIntrinsicWidth();
        this.mTotalWidth = getResources().getDisplayMetrics().widthPixels;
        if (((ActivityBase) getContext()).getCameraIntentManager().isFromScreenSlide().booleanValue()) {
            Util.startScreenSlideAlphaInAnimation(this.mTopConfigMenu);
        }
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    private void initTopView() {
        ImageView imageView = (ImageView) this.mTopConfigMenu.findViewById(2131558571);
        ImageView imageView2 = (ImageView) this.mTopConfigMenu.findViewById(2131558572);
        ImageView imageView3 = (ImageView) this.mTopConfigMenu.findViewById(2131558573);
        ImageView imageView4 = (ImageView) this.mTopConfigMenu.findViewById(2131558574);
        ImageView imageView5 = (ImageView) this.mTopConfigMenu.findViewById(2131558575);
        ImageView imageView6 = (ImageView) this.mTopConfigMenu.findViewById(2131558576);
        ImageView imageView7 = (ImageView) this.mTopConfigMenu.findViewById(2131558577);
        ImageView imageView8 = (ImageView) this.mTopConfigMenu.findViewById(2131558578);
        ImageView imageView9 = (ImageView) this.mTopConfigMenu.findViewById(2131558579);
        ImageView imageView10 = (ImageView) this.mTopConfigMenu.findViewById(2131558580);
        ImageView imageView11 = (ImageView) this.mTopConfigMenu.findViewById(2131558581);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
        imageView10.setOnClickListener(this);
        imageView11.setOnClickListener(this);
        this.mConfigViews = new ArrayList();
        this.mConfigViews.add(imageView);
        this.mConfigViews.add(imageView2);
        this.mConfigViews.add(imageView3);
        this.mConfigViews.add(imageView4);
        this.mConfigViews.add(imageView5);
        this.mConfigViews.add(imageView6);
        this.mConfigViews.add(imageView7);
        this.mConfigViews.add(imageView8);
        this.mConfigViews.add(imageView9);
        this.mConfigViews.add(imageView10);
        this.mConfigViews.add(imageView11);
    }

    private int getInitialMargin(int i) {
        if (this.mSupportedConfigs == null || this.mSupportedConfigs.getConfigsSize() <= 0) {
            return 0;
        }
        int configsSize = this.mSupportedConfigs.getConfigsSize();
        int findConfigPositionFromType = this.mSupportedConfigs.findConfigPositionFromType(i);
        TopConfigItem findConfigItem = this.mSupportedConfigs.findConfigItem(i);
        ImageView topImage = getTopImage(i);
        LayoutParams layoutParams = (LayoutParams) topImage.getLayoutParams();
        layoutParams.gravity = 0;
        int i2 = 8388611;
        int i3 = 8388613;
        switch (configsSize) {
            case 1:
                layoutParams.leftMargin = 0;
                if (findConfigItem.gravity != 0) {
                    i3 = findConfigItem.gravity;
                }
                layoutParams.gravity = i3;
                topImage.setLayoutParams(layoutParams);
                return 0;
            case 2:
                if (findConfigPositionFromType == 0) {
                    layoutParams.leftMargin = 0;
                    if (findConfigItem.gravity != 0) {
                        i2 = findConfigItem.gravity;
                    }
                    layoutParams.gravity = i2;
                } else if (findConfigPositionFromType == 1) {
                    layoutParams.leftMargin = 0;
                    if (findConfigItem.gravity != 0) {
                        i3 = findConfigItem.gravity;
                    }
                    layoutParams.gravity = i3;
                }
                topImage.setLayoutParams(layoutParams);
                return 0;
            default:
                if (findConfigPositionFromType == 0) {
                    layoutParams.leftMargin = 0;
                    layoutParams.gravity = 8388611;
                    topImage.setLayoutParams(layoutParams);
                    return 0;
                }
                configsSize--;
                if (findConfigPositionFromType != configsSize) {
                    return (((this.mTotalWidth - (this.mViewPadding * 2)) / configsSize) * findConfigPositionFromType) + this.mViewPadding;
                }
                layoutParams.leftMargin = 0;
                layoutParams.gravity = 8388613;
                topImage.setLayoutParams(layoutParams);
                return 0;
        }
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968626;
    }

    public int getFragmentInto() {
        return 244;
    }

    public void setClickEnable(boolean z) {
        super.setClickEnable(z);
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.setClickEnable(z);
        }
    }

    public void onClick(View view) {
        Log.d(TAG, "top config onclick");
        if (isEnableClick()) {
            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
                if (cameraAction != null && cameraAction.isDoingAction()) {
                    return;
                }
                if (!CameraSettings.isFrontCamera() || !((Camera) getContext()).isScreenSlideOff()) {
                    int intValue = ((Integer) view.getTag()).intValue();
                    if (this.mDisabledFunctionMenu.isEmpty() || !this.mDisabledFunctionMenu.contains(Integer.valueOf(intValue))) {
                        if (intValue == 199) {
                            configChanges.onConfigChanged(199);
                            ((ImageView) view).setImageResource(getFocusPeakImageResource());
                        } else if (intValue == 209) {
                            configChanges.onConfigChanged(209);
                        } else if (intValue == 225) {
                            configChanges.showSetting();
                        } else if (intValue == 243) {
                            configChanges.onConfigChanged(243);
                        } else if (intValue == 245) {
                            Fragment fragmentByTag = FragmentUtils.getFragmentByTag(getFragmentManager(), FragmentLiveMusic.TAG);
                            CameraStatUtil.trackLiveMusicClick();
                            if (fragmentByTag == null) {
                                FragmentLiveMusic fragmentLiveMusic = new FragmentLiveMusic();
                                fragmentLiveMusic.setStyle(2, 2131492896);
                                getFragmentManager().beginTransaction().add(fragmentLiveMusic, FragmentLiveMusic.TAG).commitAllowingStateLoss();
                            }
                        } else if (intValue != 253) {
                            switch (intValue) {
                                case 193:
                                    ComponentConfigFlash componentFlash = ((DataItemConfig) DataRepository.provider().dataConfig()).getComponentFlash();
                                    if (!componentFlash.disableUpdate()) {
                                        expandExtra(componentFlash, view, intValue);
                                        break;
                                    }
                                    int disableReasonString = componentFlash.getDisableReasonString();
                                    if (disableReasonString != 0) {
                                        ToastUtils.showToast(CameraAppImpl.getAndroidContext(), disableReasonString);
                                    }
                                    Log.w(TAG, "ignore click flash for disable update");
                                    break;
                                case 194:
                                    expandExtra(((DataItemConfig) DataRepository.provider().dataConfig()).getComponentHdr(), view, intValue);
                                    break;
                                case 195:
                                    configChanges.onConfigChanged(195);
                                    break;
                                case 196:
                                    configChanges.onConfigChanged(196);
                                    updateConfigItem(196);
                                    break;
                                case 197:
                                    showMenu();
                                    break;
                                default:
                                    switch (intValue) {
                                        case 201:
                                            configChanges.onConfigChanged(201);
                                            break;
                                        case 202:
                                            configChanges.onConfigChanged(202);
                                            break;
                                        case 203:
                                            configChanges.onConfigChanged(203);
                                            break;
                                        case 204:
                                            configChanges.onConfigChanged(204);
                                            break;
                                        case 205:
                                            configChanges.onConfigChanged(205);
                                            break;
                                        case 206:
                                            configChanges.onConfigChanged(206);
                                            break;
                                        case 207:
                                            configChanges.onConfigChanged(207);
                                            break;
                                    }
                                    break;
                            }
                        } else {
                            configChanges.onConfigChanged(253);
                        }
                    }
                }
            }
        }
    }

    public void alertMusicClose(boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.alertMusicClose(z);
        }
    }

    private void alertTopMusicHint(int i, String str) {
        FragmentTopAlert topAlert = getTopAlert();
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131362061);
        if (topAlert != null) {
            topAlert.alertTopMusicHint(i, str, dimensionPixelSize);
        }
    }

    public void onExpandValueChange(ComponentData componentData, String str) {
        if (isEnableClick()) {
            DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                int displayTitleString = componentData.getDisplayTitleString();
                if (displayTitleString == 2131296351) {
                    CameraStatUtil.trackFlashChanged(this.mCurrentMode, str);
                    updateConfigItem(193);
                    if (dataItemConfig.reConfigHhrIfFlashChanged(this.mCurrentMode, str)) {
                        updateConfigItem(194);
                    }
                    configChanges.configFlash(str);
                } else if (displayTitleString == 2131296561) {
                    CameraStatUtil.trackHdrChanged(this.mCurrentMode, str);
                    updateConfigItem(194);
                    if (dataItemConfig.reConfigFlashIfHdrChanged(this.mCurrentMode, str)) {
                        updateConfigItem(193);
                    }
                    configChanges.configHdr(str);
                }
                this.mLastAnimationComponent.reverse(true);
            }
        }
    }

    public ImageView getTopImage(int i) {
        return (ImageView) this.mConfigViews.get(SupportedConfigFactory.findViewPosition(i));
    }

    public int getTopImagePosition(int i) {
        return SupportedConfigFactory.findViewPosition(i);
    }

    @DrawableRes
    private int getFocusPeakImageResource() {
        boolean isSwitchOn = DataRepository.dataItemRunning().isSwitchOn("pref_camera_peak_key");
        if ("zh".equals(Locale.getDefault().getLanguage())) {
            if (isSwitchOn) {
                return 2130837810;
            }
            return 2130837813;
        } else if (isSwitchOn) {
            return 2130837812;
        } else {
            return 2130837811;
        }
    }

    @DrawableRes
    private int getHFRImageResource(int i) {
        if ((i == 170 ? 1 : null) != null) {
            return 2130837819;
        }
        return 2130837820;
    }

    @StringRes
    private int getHFRContentDesc(int i) {
        if ((i == 170 ? 1 : null) != null) {
            return 2131296510;
        }
        return 2131296511;
    }

    @DrawableRes
    private int getFPS960ImageResource(int i) {
        return DataRepository.dataItemConfig().getComponentConfigSlowMotion().getImageResource();
    }

    private void expandExtra(ComponentData componentData, View view, int i) {
        int i2 = 1;
        if (!this.mLastAnimationComponent.reverse(true)) {
            int i3;
            ExpandAdapter expandAdapter = new ExpandAdapter(componentData, this);
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131361819) * componentData.getItems().size();
            this.mExpandView.getLayoutParams().width = dimensionPixelSize;
            this.mExpandView.setAdapter(expandAdapter);
            int i4 = 0;
            this.mExpandView.setVisibility(0);
            this.mExpandView.setTag(Integer.valueOf(i));
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131361825) * 3;
            int i5 = ((LayoutParams) view.getLayoutParams()).gravity == 8388611 ? 1 : 0;
            if (i5 != 0) {
                i2 = 0;
            }
            if (this.mIsRTL) {
                i2 = 4 - i2;
            }
            switch (i2) {
                case 0:
                    this.mLastAnimationComponent.setExpandGravity(5);
                    dimensionPixelSize2 = view.getRight() - dimensionPixelSize2;
                    i2 = view.getWidth() + 0;
                    break;
                case 1:
                    this.mLastAnimationComponent.setExpandGravity(3);
                    dimensionPixelSize2 = view.getRight();
                    i2 = view.getWidth() + 0;
                    break;
                case 2:
                    i2 = getView().getWidth() - view.getWidth();
                    dimensionPixelSize2 = (view.getLeft() - dimensionPixelSize) - dimensionPixelSize2;
                    i3 = i2 - dimensionPixelSize;
                    break;
                case 3:
                    i2 = getView().getWidth() - view.getWidth();
                    dimensionPixelSize2 = (view.getLeft() - dimensionPixelSize) - dimensionPixelSize2;
                    i3 = i2 - dimensionPixelSize;
                    break;
                case 4:
                    this.mLastAnimationComponent.setExpandGravity(3);
                    i2 = getView().getWidth() - view.getWidth();
                    dimensionPixelSize2 += view.getLeft() - dimensionPixelSize;
                    i3 = i2 - dimensionPixelSize;
                    break;
                default:
                    dimensionPixelSize2 = 0;
                    i2 = dimensionPixelSize2;
                    i3 = i2;
                    break;
            }
            i3 = i2;
            i2 = 0;
            this.mLastAnimationComponent.mRecyclerView = this.mExpandView;
            this.mLastAnimationComponent.mReverseLeft = view.getLeft();
            this.mLastAnimationComponent.mReverseRecyclerViewLeft = dimensionPixelSize2;
            this.mLastAnimationComponent.hideOtherViews(i, this.mConfigViews);
            if (i5 == 0) {
                this.mLastAnimationComponent.mAnchorView = view;
                this.mLastAnimationComponent.translateAnchorView(i2 - view.getLeft());
            }
            if (this.mIsRTL) {
                i4 = getView().getWidth() - dimensionPixelSize;
            }
            this.mLastAnimationComponent.showExtraView(dimensionPixelSize2 - i4, i3 - i4);
        }
    }

    private void showMenu() {
        this.mTopConfigMenu.setVisibility(8);
        hideAlert();
        this.mFragmentTopConfigExtra = new FragmentTopConfigExtra();
        this.mFragmentTopConfigExtra.setDegree(this.mDegree);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mTopExtraParent.getLayoutParams();
        if (Util.isLongRatioScreen) {
            marginLayoutParams.topMargin = 0;
        } else {
            marginLayoutParams.topMargin = this.mDisplayRectTopMargin;
        }
        FragmentUtils.addFragmentWithTag(getChildFragmentManager(), 2131558583, this.mFragmentTopConfigExtra, this.mFragmentTopConfigExtra.getFragmentTag());
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        registerBackStack(modeCoordinator, this);
        modeCoordinator.attachProtocol(172, this);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        unRegisterBackStack(modeCoordinator, this);
        modeCoordinator.detachProtocol(172, this);
    }

    public boolean onBackEvent(int i) {
        if (this.mLastAnimationComponent.reverse(i != 4)) {
            return true;
        }
        FragmentTopConfigExtra topExtra = getTopExtra();
        if (topExtra == null) {
            return false;
        }
        if (i != 6) {
            switch (i) {
                case 1:
                case 2:
                    topExtra.animateOut();
                    Completable.create(new AlphaInOnSubscribe(this.mTopConfigMenu).setStartDelayTime(200)).subscribe();
                    break;
                default:
                    FragmentUtils.removeFragmentByTag(getChildFragmentManager(), String.valueOf(245));
                    this.mTopConfigMenu.setVisibility(0);
                    break;
            }
        }
        topExtra.animateOut();
        Completable.create(new AlphaInOnSubscribe(this.mTopConfigMenu).setStartDelayTime(200)).subscribe();
        if (i != 4) {
            reInitAlert(true);
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:80:0x0193, code skipped:
            r8 = 0;
     */
    private boolean setTopImageResource(int r6, android.widget.ImageView r7, int r8, com.android.camera.data.data.config.DataItemConfig r9, boolean r10) {
        /*
        r5 = this;
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.ActionProcessing) r0;
        r1 = 209; // 0xd1 float:2.93E-43 double:1.033E-321;
        r2 = 0;
        r3 = 1;
        if (r6 == r1) goto L_0x0209;
    L_0x0014:
        r1 = 225; // 0xe1 float:3.15E-43 double:1.11E-321;
        if (r6 == r1) goto L_0x0201;
    L_0x0018:
        r1 = 243; // 0xf3 float:3.4E-43 double:1.2E-321;
        if (r6 == r1) goto L_0x01d0;
    L_0x001c:
        r1 = 245; // 0xf5 float:3.43E-43 double:1.21E-321;
        if (r6 == r1) goto L_0x01b2;
    L_0x0020:
        r1 = 253; // 0xfd float:3.55E-43 double:1.25E-321;
        if (r6 == r1) goto L_0x0196;
    L_0x0024:
        switch(r6) {
            case 193: goto L_0x017a;
            case 194: goto L_0x0161;
            case 195: goto L_0x0158;
            case 196: goto L_0x010f;
            case 197: goto L_0x0106;
            case 198: goto L_0x0105;
            case 199: goto L_0x00fc;
            default: goto L_0x0027;
        };
    L_0x0027:
        switch(r6) {
            case 201: goto L_0x00dc;
            case 202: goto L_0x00d2;
            case 203: goto L_0x00a9;
            case 204: goto L_0x0097;
            case 205: goto L_0x0083;
            case 206: goto L_0x0067;
            case 207: goto L_0x002c;
            default: goto L_0x002a;
        };
    L_0x002a:
        goto L_0x0193;
    L_0x002c:
        if (r10 == 0) goto L_0x0045;
    L_0x002e:
        r9 = com.android.camera.data.DataRepository.getInstance();
        r9 = r9.backUp();
        r10 = "pref_ultra_wide_bokeh_enabled";
        r0 = com.android.camera.data.DataRepository.dataItemGlobal();
        r0 = r0.getCurrentCameraId();
        r8 = r9.getBackupSwitchState(r8, r10, r0);
        goto L_0x004f;
    L_0x0045:
        r8 = com.android.camera.data.DataRepository.dataItemRunning();
        r9 = "pref_ultra_wide_bokeh_enabled";
        r8 = r8.isSwitchOn(r9);
    L_0x004f:
        if (r8 == 0) goto L_0x0057;
    L_0x0051:
        r9 = r5.mUltraWideBokehResources;
        r9 = r9[r3];
    L_0x0055:
        r2 = r9;
        goto L_0x005c;
    L_0x0057:
        r9 = r5.mUltraWideBokehResources;
        r9 = r9[r2];
        goto L_0x0055;
    L_0x005c:
        if (r8 == 0) goto L_0x0062;
    L_0x005e:
        r8 = 2131296849; // 0x7f090251 float:1.8211626E38 double:1.053000554E-314;
        goto L_0x0065;
    L_0x0062:
        r8 = 2131296850; // 0x7f090252 float:1.8211628E38 double:1.0530005547E-314;
    L_0x0065:
        goto L_0x0224;
    L_0x0067:
        r8 = com.android.camera.CameraSettings.isLiveShotOn();
        if (r8 == 0) goto L_0x0073;
    L_0x006d:
        r9 = r5.mLiveShotResource;
        r9 = r9[r3];
    L_0x0071:
        r2 = r9;
        goto L_0x0078;
    L_0x0073:
        r9 = r5.mLiveShotResource;
        r9 = r9[r2];
        goto L_0x0071;
    L_0x0078:
        if (r8 == 0) goto L_0x007e;
    L_0x007a:
        r8 = 2131296836; // 0x7f090244 float:1.82116E38 double:1.053000548E-314;
        goto L_0x0081;
    L_0x007e:
        r8 = 2131296837; // 0x7f090245 float:1.8211602E38 double:1.0530005483E-314;
    L_0x0081:
        goto L_0x0224;
    L_0x0083:
        r9 = r9.getComponentConfigUltraWide();
        r10 = r9.isEmpty();
        if (r10 != 0) goto L_0x0193;
    L_0x008d:
        r2 = r9.getValueSelectedDrawableIgnoreClose(r8);
        r8 = r9.getValueSelectedStringIdIgnoreClose(r8);
        goto L_0x0224;
    L_0x0097:
        r2 = r5.getFPS960ImageResource(r8);
        r8 = com.android.camera.data.DataRepository.dataItemConfig();
        r8 = r8.getComponentConfigSlowMotion();
        r8 = r8.getContentDesc();
        goto L_0x0224;
    L_0x00a9:
        r9 = com.android.camera.data.DataRepository.dataItemRunning();
        r9 = r9.getComponentRunningLighting();
        r8 = r9.isSwitchOn(r8);
        if (r8 == 0) goto L_0x00bc;
    L_0x00b7:
        r8 = r5.mLightingResource;
        r8 = r8[r3];
        goto L_0x00c0;
    L_0x00bc:
        r8 = r5.mLightingResource;
        r8 = r8[r2];
    L_0x00c0:
        if (r0 == 0) goto L_0x01c7;
    L_0x00c2:
        r9 = r0.isShowLightingView();
        if (r9 == 0) goto L_0x00cd;
    L_0x00c8:
        r2 = 2131296516; // 0x7f090104 float:1.821095E38 double:1.0530003897E-314;
        goto L_0x01c7;
    L_0x00cd:
        r2 = 2131296515; // 0x7f090103 float:1.8210949E38 double:1.053000389E-314;
        goto L_0x01c7;
    L_0x00d2:
        r2 = r5.getHFRImageResource(r8);
        r8 = r5.getHFRContentDesc(r8);
        goto L_0x0224;
    L_0x00dc:
        r8 = com.android.camera.CameraSettings.getAiSceneOpen();
        if (r8 == 0) goto L_0x00e8;
    L_0x00e2:
        r8 = r5.mAiSceneResources;
        r8 = r8[r3];
    L_0x00e6:
        r2 = r8;
        goto L_0x00ed;
    L_0x00e8:
        r8 = r5.mAiSceneResources;
        r8 = r8[r2];
        goto L_0x00e6;
    L_0x00ed:
        r8 = com.android.camera.CameraSettings.getAiSceneOpen();
        if (r8 == 0) goto L_0x00f7;
    L_0x00f3:
        r8 = 2131296488; // 0x7f0900e8 float:1.8210894E38 double:1.053000376E-314;
        goto L_0x00fa;
    L_0x00f7:
        r8 = 2131296489; // 0x7f0900e9 float:1.8210896E38 double:1.0530003763E-314;
    L_0x00fa:
        goto L_0x0224;
    L_0x00fc:
        r2 = r5.getFocusPeakImageResource();
        r8 = 2131296493; // 0x7f0900ed float:1.8210904E38 double:1.0530003783E-314;
        goto L_0x0224;
    L_0x0105:
        return r2;
    L_0x0106:
        r2 = r5.getMoreResources();
        r8 = 2131296490; // 0x7f0900ea float:1.8210898E38 double:1.053000377E-314;
        goto L_0x0224;
    L_0x010f:
        if (r10 == 0) goto L_0x0126;
    L_0x0111:
        r9 = com.android.camera.data.DataRepository.getInstance();
        r9 = r9.backUp();
        r10 = com.android.camera.data.DataRepository.dataItemGlobal();
        r10 = r10.getCurrentCameraId();
        r8 = r9.getBackupFilter(r8, r10);
        goto L_0x0132;
    L_0x0126:
        r9 = com.android.camera.data.DataRepository.dataItemRunning();
        r9 = r9.getComponentConfigFilter();
        r8 = r9.getComponentValue(r8);
    L_0x0132:
        r8 = java.lang.Integer.parseInt(r8);
        r9 = com.android.camera.effect.FilterInfo.FILTER_ID_NONE;
        if (r8 == r9) goto L_0x0142;
    L_0x013a:
        if (r8 > 0) goto L_0x013d;
    L_0x013c:
        goto L_0x0142;
    L_0x013d:
        r8 = r5.mFilterResources;
        r8 = r8[r3];
        goto L_0x0146;
    L_0x0142:
        r8 = r5.mFilterResources;
        r8 = r8[r2];
    L_0x0146:
        if (r0 == 0) goto L_0x01c7;
    L_0x0148:
        r9 = r0.isShowFilterView();
        if (r9 == 0) goto L_0x0153;
    L_0x014e:
        r2 = 2131296487; // 0x7f0900e7 float:1.8210892E38 double:1.0530003753E-314;
        goto L_0x01c7;
    L_0x0153:
        r2 = 2131296486; // 0x7f0900e6 float:1.821089E38 double:1.053000375E-314;
        goto L_0x01c7;
    L_0x0158:
        r2 = r5.getPortraitResources();
        r8 = 2131296492; // 0x7f0900ec float:1.8210902E38 double:1.053000378E-314;
        goto L_0x0224;
    L_0x0161:
        r9 = r9.getComponentHdr();
        r0 = r9.isEmpty();
        if (r0 != 0) goto L_0x0193;
    L_0x016b:
        r2 = r9.getValueSelectedDrawableIgnoreClose(r8);
        r8 = r9.getValueSelectedStringIdIgnoreClose(r8);
        if (r10 != 0) goto L_0x0224;
    L_0x0175:
        r5.reConfigTipOfHdr(r3);
        goto L_0x0224;
    L_0x017a:
        r9 = r9.getComponentFlash();
        r0 = r9.isEmpty();
        if (r0 != 0) goto L_0x0193;
    L_0x0184:
        r2 = r9.getValueSelectedDrawableIgnoreClose(r8);
        r8 = r9.getValueSelectedStringIdIgnoreClose(r8);
        if (r10 != 0) goto L_0x0224;
    L_0x018e:
        r5.reConfigTipOfFlash(r3);
        goto L_0x0224;
    L_0x0193:
        r8 = r2;
        goto L_0x0224;
    L_0x0196:
        r8 = com.android.camera.data.DataRepository.dataItemRunning();
        r9 = "pref_camera_auto_zoom";
        r8 = r8.isSwitchOn(r9);
        if (r8 == 0) goto L_0x01a8;
    L_0x01a2:
        r8 = r5.mAutoZoomResources;
        r8 = r8[r3];
    L_0x01a6:
        r2 = r8;
        goto L_0x01ad;
    L_0x01a8:
        r8 = r5.mAutoZoomResources;
        r8 = r8[r2];
        goto L_0x01a6;
    L_0x01ad:
        r8 = 2131296896; // 0x7f090280 float:1.8211722E38 double:1.0530005774E-314;
        goto L_0x0224;
    L_0x01b2:
        r8 = com.android.camera.CameraSettings.getCurrentLiveMusic();
        r9 = r8[r3];
        r9 = r9.isEmpty();
        if (r9 != 0) goto L_0x01cb;
    L_0x01be:
        r8 = r8[r3];
        r5.alertTopMusicHint(r2, r8);
        r8 = r5.mLiveMusicSelectResources;
        r8 = r8[r3];
    L_0x01c7:
        r4 = r2;
        r2 = r8;
        r8 = r4;
        goto L_0x0224;
    L_0x01cb:
        r8 = r5.mLiveMusicSelectResources;
        r8 = r8[r2];
        goto L_0x01c7;
    L_0x01d0:
        r8 = com.android.camera.CameraSettings.isVideoBokehOn();
        r9 = "FragmentTopConfig";
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = "setTopImageResource: VIDEO_BOKEH isSwitchOn = ";
        r10.append(r0);
        r10.append(r8);
        r10 = r10.toString();
        com.android.camera.log.Log.d(r9, r10);
        if (r8 == 0) goto L_0x01f2;
    L_0x01ec:
        r9 = r5.mVideoBokehResource;
        r9 = r9[r3];
    L_0x01f0:
        r2 = r9;
        goto L_0x01f7;
    L_0x01f2:
        r9 = r5.mVideoBokehResource;
        r9 = r9[r2];
        goto L_0x01f0;
    L_0x01f7:
        if (r8 == 0) goto L_0x01fd;
    L_0x01f9:
        r8 = 2131296784; // 0x7f090210 float:1.8211494E38 double:1.053000522E-314;
        goto L_0x0200;
    L_0x01fd:
        r8 = 2131296785; // 0x7f090211 float:1.8211496E38 double:1.0530005226E-314;
    L_0x0200:
        goto L_0x0224;
    L_0x0201:
        r2 = r5.getSettingResources();
        r8 = 2131296491; // 0x7f0900eb float:1.82109E38 double:1.0530003773E-314;
        goto L_0x0224;
    L_0x0209:
        r8 = com.android.camera.CameraSettings.isUltraPixelPhotographyOn();
        if (r8 == 0) goto L_0x0215;
    L_0x020f:
        r9 = r5.mUltraPixelPhotographyResources;
        r9 = r9[r3];
    L_0x0213:
        r2 = r9;
        goto L_0x021a;
    L_0x0215:
        r9 = r5.mUltraPixelPhotographyResources;
        r9 = r9[r2];
        goto L_0x0213;
    L_0x021a:
        if (r8 == 0) goto L_0x0220;
    L_0x021c:
        r8 = 2131296854; // 0x7f090256 float:1.8211636E38 double:1.0530005567E-314;
        goto L_0x0223;
    L_0x0220:
        r8 = 2131296855; // 0x7f090257 float:1.8211638E38 double:1.053000557E-314;
    L_0x0224:
        if (r7 == 0) goto L_0x0282;
    L_0x0226:
        if (r2 <= 0) goto L_0x0282;
    L_0x0228:
        r9 = r5.getResources();
        r9 = r9.getDrawable(r2);
        r6 = r5.getInitialMargin(r6);
        r10 = 2131558407; // 0x7f0d0007 float:1.8742129E38 double:1.053129781E-314;
        r0 = java.lang.Integer.valueOf(r6);
        r7.setTag(r10, r0);
        if (r6 <= 0) goto L_0x026a;
        r10 = r7.getLayoutParams();
        r10 = (android.widget.FrameLayout.LayoutParams) r10;
        r0 = r9.getIntrinsicWidth();
        r0 = r0 / 2;
        r1 = r5.mViewPadding;
        r0 = r0 + r1;
        r6 = r6 - r0;
        r0 = r5.mIsRTL;
        if (r0 == 0) goto L_0x0265;
    L_0x0255:
        r0 = r5.mTotalWidth;
        r0 = r0 - r6;
        r6 = r9.getIntrinsicWidth();
        r0 = r0 - r6;
        r6 = r5.mViewPadding;
        r6 = r6 * 2;
        r0 = r0 - r6;
        r10.leftMargin = r0;
        goto L_0x0267;
    L_0x0265:
        r10.leftMargin = r6;
    L_0x0267:
        r7.setLayoutParams(r10);
    L_0x026a:
        r7.setImageDrawable(r9);
        if (r8 <= 0) goto L_0x0282;
    L_0x026f:
        r6 = com.android.camera.Util.isAccessible();
        if (r6 != 0) goto L_0x027b;
    L_0x0275:
        r6 = com.android.camera.Util.isSetContentDesc();
        if (r6 == 0) goto L_0x0282;
    L_0x027b:
        r6 = r5.getString(r8);
        r7.setContentDescription(r6);
    L_0x0282:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.top.FragmentTopConfig.setTopImageResource(int, android.widget.ImageView, int, com.android.camera.data.data.config.DataItemConfig, boolean):boolean");
    }

    private int[] getFilterResources() {
        return new int[]{2130837821, 2130837822};
    }

    private int[] getVideoBokehResources() {
        return new int[]{2130837826, 2130837827};
    }

    private int getPortraitResources() {
        return 2130837826;
    }

    private int getSettingResources() {
        return 2130837818;
    }

    private int getMoreResources() {
        return 2130837825;
    }

    private int[] getLightingResources() {
        return new int[]{2130837823, 2130837824};
    }

    private int[] getAiSceneResources() {
        return new int[]{2130837804, 2130837805};
    }

    private int[] getAutoZoomResources() {
        return new int[]{2130837692, 2130837693};
    }

    private int[] getUltraWideResources() {
        return new int[]{2130837882, 2130837883};
    }

    private int[] getLiveShotResources() {
        return new int[]{2130837800, 2130837801};
    }

    private int[] getUltraPixelPhotographyResources() {
        return new int[]{2130837854, 2130837855};
    }

    private int[] getUltraWideBokehResources() {
        return new int[]{2130837856, 2130837857};
    }

    private int[] getMusicSelectResources() {
        return new int[]{2130837770, 2130837773};
    }

    /* JADX WARNING: Missing block: B:8:0x001f, code skipped:
            if (r7 == 162) goto L_0x0026;
     */
    /* JADX WARNING: Missing block: B:9:0x0021, code skipped:
            switch(r7) {
                case 168: goto L_0x0026;
                case 169: goto L_0x0026;
                case 170: goto L_0x0026;
                default: goto L_0x0024;
            };
     */
    /* JADX WARNING: Missing block: B:10:0x0024, code skipped:
            r2 = true;
     */
    /* JADX WARNING: Missing block: B:11:0x0026, code skipped:
            r2 = false;
     */
    /* JADX WARNING: Missing block: B:15:0x0030, code skipped:
            r2 = true;
     */
    /* JADX WARNING: Missing block: B:16:0x0031, code skipped:
            if (r2 == false) goto L_0x0037;
     */
    /* JADX WARNING: Missing block: B:17:0x0033, code skipped:
            onBackEvent(4);
     */
    /* JADX WARNING: Missing block: B:18:0x0037, code skipped:
            if (r1 == false) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:19:0x0039, code skipped:
            r6.mDisabledFunctionMenu.clear();
     */
    /* JADX WARNING: Missing block: B:20:0x003e, code skipped:
            r1 = getTopAlert();
     */
    /* JADX WARNING: Missing block: B:21:0x0042, code skipped:
            if (r1 == null) goto L_0x0047;
     */
    /* JADX WARNING: Missing block: B:22:0x0044, code skipped:
            r1.provideAnimateElement(r7, r8, r0);
     */
    /* JADX WARNING: Missing block: B:23:0x0047, code skipped:
            r12 = com.android.camera.data.DataRepository.dataItemConfig();
            r0 = com.android.camera.data.DataRepository.dataItemGlobal().getCurrentCameraId();
            r1 = com.android.camera.module.loader.camera2.Camera2DataContainer.getInstance().getCapabilitiesByBogusCameraId(r0, r6.mCurrentMode);
     */
    /* JADX WARNING: Missing block: B:24:0x005d, code skipped:
            if (r1 != null) goto L_0x0060;
     */
    /* JADX WARNING: Missing block: B:25:0x005f, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:27:0x0066, code skipped:
            if (r6.mTopConfigMenu.getVisibility() == 0) goto L_0x006d;
     */
    /* JADX WARNING: Missing block: B:28:0x0068, code skipped:
            com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r6.mTopConfigMenu);
     */
    /* JADX WARNING: Missing block: B:29:0x006d, code skipped:
            r6.mSupportedConfigs = com.android.camera.data.data.config.SupportedConfigFactory.getSupportedTopConfigs(r6.mCurrentMode, r12, r0, r1, com.android.camera.data.DataRepository.dataItemGlobal().isNormalIntent());
            r13 = 0;
     */
    /* JADX WARNING: Missing block: B:31:0x0084, code skipped:
            if (r13 >= r6.mConfigViews.size()) goto L_0x0149;
     */
    /* JADX WARNING: Missing block: B:32:0x0086, code skipped:
            r14 = (android.widget.ImageView) r6.mConfigViews.get(r13);
            r14.setEnabled(r11);
            r16 = r14.getTag(2131558407);
     */
    /* JADX WARNING: Missing block: B:33:0x0099, code skipped:
            if (r16 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Missing block: B:34:0x009b, code skipped:
            r5 = ((java.lang.Integer) r16).intValue();
     */
    /* JADX WARNING: Missing block: B:35:0x00a5, code skipped:
            r5 = 0;
     */
    /* JADX WARNING: Missing block: B:36:0x00a7, code skipped:
            r4 = r6.mSupportedConfigs.getConfigTypeForViewPosition(r13);
     */
    /* JADX WARNING: Missing block: B:37:0x00ad, code skipped:
            if (r8 == null) goto L_0x00b2;
     */
    /* JADX WARNING: Missing block: B:38:0x00af, code skipped:
            r17 = r11;
     */
    /* JADX WARNING: Missing block: B:39:0x00b2, code skipped:
            r17 = false;
     */
    /* JADX WARNING: Missing block: B:40:0x00b4, code skipped:
            r10 = r4;
            r11 = r5;
            r0 = setTopImageResource(r4, r14, r7, r12, r17);
     */
    /* JADX WARNING: Missing block: B:41:0x00c1, code skipped:
            if (r0 == false) goto L_0x00d2;
     */
    /* JADX WARNING: Missing block: B:43:0x00cd, code skipped:
            if (r6.mDisabledFunctionMenu.contains(java.lang.Integer.valueOf(r10)) == false) goto L_0x00d2;
     */
    /* JADX WARNING: Missing block: B:44:0x00d2, code skipped:
            if (r0 == false) goto L_0x00e5;
     */
    /* JADX WARNING: Missing block: B:45:0x00d4, code skipped:
            if (r16 == null) goto L_0x00e5;
     */
    /* JADX WARNING: Missing block: B:47:0x00e0, code skipped:
            if (r11 != ((java.lang.Integer) r14.getTag(2131558407)).intValue()) goto L_0x00e3;
     */
    /* JADX WARNING: Missing block: B:48:0x00e3, code skipped:
            r11 = null;
     */
    /* JADX WARNING: Missing block: B:49:0x00e5, code skipped:
            r11 = 1;
     */
    /* JADX WARNING: Missing block: B:51:0x00ea, code skipped:
            if (r14.getTag() == null) goto L_0x00fb;
     */
    /* JADX WARNING: Missing block: B:53:0x00f6, code skipped:
            if (((java.lang.Integer) r14.getTag()).intValue() != r10) goto L_0x00fb;
     */
    /* JADX WARNING: Missing block: B:54:0x00f8, code skipped:
            if (r11 == null) goto L_0x00fb;
     */
    /* JADX WARNING: Missing block: B:55:0x00fb, code skipped:
            r14.setTag(java.lang.Integer.valueOf(r10));
     */
    /* JADX WARNING: Missing block: B:56:0x0102, code skipped:
            if (r8 != null) goto L_0x010e;
     */
    /* JADX WARNING: Missing block: B:57:0x0104, code skipped:
            if (r0 == false) goto L_0x010a;
     */
    /* JADX WARNING: Missing block: B:58:0x0106, code skipped:
            com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r14);
     */
    /* JADX WARNING: Missing block: B:59:0x010a, code skipped:
            com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r14);
     */
    /* JADX WARNING: Missing block: B:61:0x0110, code skipped:
            if (r0 == false) goto L_0x0127;
     */
    /* JADX WARNING: Missing block: B:62:0x0112, code skipped:
            r0 = new com.android.camera.animation.type.AlphaInOnSubscribe(r14);
            r0.setStartDelayTime(150).setDurationTime(150);
            r8.add(io.reactivex.Completable.create(r0));
     */
    /* JADX WARNING: Missing block: B:64:0x0129, code skipped:
            if (r9 == 165) goto L_0x0141;
     */
    /* JADX WARNING: Missing block: B:66:0x012d, code skipped:
            if (r6.mCurrentMode != 165) goto L_0x0130;
     */
    /* JADX WARNING: Missing block: B:67:0x0130, code skipped:
            r8.add(io.reactivex.Completable.create(new com.android.camera.animation.type.AlphaOutOnSubscribe(r14).setDurationTime(150)));
     */
    /* JADX WARNING: Missing block: B:68:0x0141, code skipped:
            com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r14);
     */
    /* JADX WARNING: Missing block: B:69:0x0144, code skipped:
            r13 = r13 + 1;
            r11 = true;
     */
    /* JADX WARNING: Missing block: B:70:0x0149, code skipped:
            return;
     */
    public void provideAnimateElement(int r20, java.util.List<io.reactivex.Completable> r21, int r22) {
        /*
        r19 = this;
        r6 = r19;
        r7 = r20;
        r8 = r21;
        r0 = r22;
        r9 = r6.mCurrentMode;
        r11 = 1;
        r1 = 3;
        if (r0 != r1) goto L_0x0010;
    L_0x000e:
        r1 = r11;
        goto L_0x0012;
        r1 = 0;
    L_0x0012:
        super.provideAnimateElement(r20, r21, r22);
        switch(r9) {
            case 161: goto L_0x0029;
            case 162: goto L_0x001d;
            default: goto L_0x0019;
        };
    L_0x0019:
        switch(r9) {
            case 168: goto L_0x001d;
            case 169: goto L_0x001d;
            case 170: goto L_0x001d;
            default: goto L_0x001c;
        };
    L_0x001c:
        goto L_0x0030;
    L_0x001d:
        r2 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        if (r7 == r2) goto L_0x0026;
    L_0x0021:
        switch(r7) {
            case 168: goto L_0x0026;
            case 169: goto L_0x0026;
            case 170: goto L_0x0026;
            default: goto L_0x0024;
        };
    L_0x0024:
        r2 = r11;
        goto L_0x0028;
        r2 = 0;
    L_0x0028:
        goto L_0x0031;
    L_0x0029:
        r2 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        if (r7 != r2) goto L_0x0030;
        r2 = 0;
        goto L_0x0031;
    L_0x0030:
        r2 = r11;
    L_0x0031:
        if (r2 == 0) goto L_0x0037;
    L_0x0033:
        r2 = 4;
        r6.onBackEvent(r2);
    L_0x0037:
        if (r1 == 0) goto L_0x003e;
    L_0x0039:
        r1 = r6.mDisabledFunctionMenu;
        r1.clear();
    L_0x003e:
        r1 = r19.getTopAlert();
        if (r1 == 0) goto L_0x0047;
    L_0x0044:
        r1.provideAnimateElement(r7, r8, r0);
    L_0x0047:
        r12 = com.android.camera.data.DataRepository.dataItemConfig();
        r0 = com.android.camera.data.DataRepository.dataItemGlobal();
        r0 = r0.getCurrentCameraId();
        r1 = com.android.camera.module.loader.camera2.Camera2DataContainer.getInstance();
        r2 = r6.mCurrentMode;
        r1 = r1.getCapabilitiesByBogusCameraId(r0, r2);
        if (r1 != 0) goto L_0x0060;
    L_0x005f:
        return;
    L_0x0060:
        r2 = r6.mTopConfigMenu;
        r2 = r2.getVisibility();
        if (r2 == 0) goto L_0x006d;
    L_0x0068:
        r2 = r6.mTopConfigMenu;
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r2);
    L_0x006d:
        r2 = r6.mCurrentMode;
        r3 = com.android.camera.data.DataRepository.dataItemGlobal();
        r3 = r3.isNormalIntent();
        r0 = com.android.camera.data.data.config.SupportedConfigFactory.getSupportedTopConfigs(r2, r12, r0, r1, r3);
        r6.mSupportedConfigs = r0;
        r13 = 0;
    L_0x007e:
        r0 = r6.mConfigViews;
        r0 = r0.size();
        if (r13 >= r0) goto L_0x0149;
    L_0x0086:
        r0 = r6.mConfigViews;
        r0 = r0.get(r13);
        r14 = r0;
        r14 = (android.widget.ImageView) r14;
        r14.setEnabled(r11);
        r15 = 2131558407; // 0x7f0d0007 float:1.8742129E38 double:1.053129781E-314;
        r16 = r14.getTag(r15);
        if (r16 == 0) goto L_0x00a5;
    L_0x009b:
        r0 = r16;
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r5 = r0;
        goto L_0x00a7;
        r5 = 0;
    L_0x00a7:
        r0 = r6.mSupportedConfigs;
        r4 = r0.getConfigTypeForViewPosition(r13);
        if (r8 == 0) goto L_0x00b2;
    L_0x00af:
        r17 = r11;
        goto L_0x00b4;
    L_0x00b2:
        r17 = 0;
    L_0x00b4:
        r0 = r6;
        r1 = r4;
        r2 = r14;
        r3 = r7;
        r10 = r4;
        r4 = r12;
        r11 = r5;
        r5 = r17;
        r0 = r0.setTopImageResource(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x00d1;
    L_0x00c3:
        r1 = r6.mDisabledFunctionMenu;
        r2 = java.lang.Integer.valueOf(r10);
        r1 = r1.contains(r2);
        if (r1 == 0) goto L_0x00d1;
    L_0x00cf:
        goto L_0x0144;
        if (r0 == 0) goto L_0x00e5;
    L_0x00d4:
        if (r16 == 0) goto L_0x00e5;
    L_0x00d6:
        r1 = r14.getTag(r15);
        r1 = (java.lang.Integer) r1;
        r1 = r1.intValue();
        if (r11 != r1) goto L_0x00e3;
    L_0x00e2:
        goto L_0x00e5;
    L_0x00e3:
        r11 = 0;
        goto L_0x00e6;
    L_0x00e5:
        r11 = 1;
    L_0x00e6:
        r1 = r14.getTag();
        if (r1 == 0) goto L_0x00fb;
    L_0x00ec:
        r1 = r14.getTag();
        r1 = (java.lang.Integer) r1;
        r1 = r1.intValue();
        if (r1 != r10) goto L_0x00fb;
    L_0x00f8:
        if (r11 == 0) goto L_0x00fb;
    L_0x00fa:
        goto L_0x0144;
    L_0x00fb:
        r1 = java.lang.Integer.valueOf(r10);
        r14.setTag(r1);
        if (r8 != 0) goto L_0x010e;
    L_0x0104:
        if (r0 == 0) goto L_0x010a;
    L_0x0106:
        com.android.camera.animation.type.AlphaInOnSubscribe.directSetResult(r14);
        goto L_0x0144;
    L_0x010a:
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r14);
        goto L_0x0144;
    L_0x010e:
        r1 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        if (r0 == 0) goto L_0x0127;
    L_0x0112:
        r0 = new com.android.camera.animation.type.AlphaInOnSubscribe;
        r0.<init>(r14);
        r2 = r0.setStartDelayTime(r1);
        r2.setDurationTime(r1);
        r0 = io.reactivex.Completable.create(r0);
        r8.add(r0);
        goto L_0x0144;
    L_0x0127:
        r0 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r9 == r0) goto L_0x0141;
    L_0x012b:
        r2 = r6.mCurrentMode;
        if (r2 != r0) goto L_0x0130;
    L_0x012f:
        goto L_0x0141;
    L_0x0130:
        r0 = new com.android.camera.animation.type.AlphaOutOnSubscribe;
        r0.<init>(r14);
        r0 = r0.setDurationTime(r1);
        r0 = io.reactivex.Completable.create(r0);
        r8.add(r0);
        goto L_0x0144;
    L_0x0141:
        com.android.camera.animation.type.AlphaOutOnSubscribe.directSetResult(r14);
    L_0x0144:
        r13 = r13 + 1;
        r11 = 1;
        goto L_0x007e;
    L_0x0149:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.top.FragmentTopConfig.provideAnimateElement(int, java.util.List, int):void");
    }

    private void resetImages() {
        DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
        int currentCameraId = DataRepository.dataItemGlobal().getCurrentCameraId();
        this.mSupportedConfigs = SupportedConfigFactory.getSupportedTopConfigs(this.mCurrentMode, dataItemConfig, currentCameraId, Camera2DataContainer.getInstance().getCapabilitiesByBogusCameraId(currentCameraId, this.mCurrentMode), DataRepository.dataItemGlobal().isNormalIntent());
        for (int i = 0; i < this.mConfigViews.size(); i++) {
            ImageView imageView = (ImageView) this.mConfigViews.get(i);
            boolean z = true;
            imageView.setEnabled(true);
            imageView.setColorFilter(null);
            Object tag = imageView.getTag(2131558407);
            int intValue;
            if (tag != null) {
                intValue = ((Integer) tag).intValue();
            } else {
                intValue = 0;
            }
            int configTypeForViewPosition = this.mSupportedConfigs.getConfigTypeForViewPosition(i);
            boolean topImageResource = setTopImageResource(configTypeForViewPosition, imageView, this.mCurrentMode, dataItemConfig, false);
            if (!(!topImageResource || tag == null || intValue == ((Integer) imageView.getTag(2131558407)).intValue())) {
                z = false;
            }
            if (imageView.getTag() == null || ((Integer) imageView.getTag()).intValue() != configTypeForViewPosition || !z) {
                imageView.setTag(Integer.valueOf(configTypeForViewPosition));
                imageView.clearAnimation();
                imageView.setVisibility(0);
                if (topImageResource) {
                    ViewCompat.setAlpha(imageView, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                    ViewCompat.animate(imageView).alpha(1.0f).setDuration(150).setStartDelay(150).start();
                } else {
                    imageView.setVisibility(4);
                }
            }
        }
    }

    public void updateConfigItem(int... iArr) {
        DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
        for (int i : iArr) {
            if (this.mSupportedConfigs.isHasConfigItem(i)) {
                setTopImageResource(i, getTopImage(i), this.mCurrentMode, dataItemConfig, false);
            }
        }
    }

    public void insertConfigItem(int i) {
        resetImages();
    }

    public void removeConfigItem(int i) {
        resetImages();
    }

    public void removeExtraMenu(int i) {
        onBackEvent(i);
    }

    public void hideExtraMenu() {
        onBackEvent(6);
    }

    public void startLiveShotAnimation() {
        ImageView topImage = getTopImage(206);
        if (topImage != null) {
            Drawable drawable = topImage.getDrawable();
            if (drawable instanceof LayerDrawable) {
                RotateDrawable rotateDrawable = (RotateDrawable) ((LayerDrawable) drawable).getDrawable(0);
                if (this.mLiveShotAnimator == null || this.mLiveShotAnimator.getTarget() != rotateDrawable) {
                    this.mLiveShotAnimator = ObjectAnimator.ofInt(rotateDrawable, "level", new int[]{0, DurationConstant.DURATION_VIDEO_RECORDING_CIRCLE});
                    this.mLiveShotAnimator.setDuration(1000);
                    this.mLiveShotAnimator.setInterpolator(new CubicEaseInOutInterpolator());
                }
                if (this.mLiveShotAnimator.isRunning()) {
                    this.mLiveShotAnimator.cancel();
                }
                this.mLiveShotAnimator.start();
            }
        }
    }

    public void refreshExtraMenu() {
        if (this.mFragmentTopConfigExtra != null && this.mFragmentTopConfigExtra.isAdded()) {
            this.mFragmentTopConfigExtra.reFresh();
        }
    }

    public void hideConfigMenu() {
        Completable.create(new AlphaOutOnSubscribe(this.mTopConfigMenu)).subscribe();
    }

    public void showConfigMenu() {
        Completable.create(new AlphaInOnSubscribe(this.mTopConfigMenu)).subscribe();
    }

    public void disableMenuItem(int... iArr) {
        if (iArr != null) {
            for (int i : iArr) {
                this.mDisabledFunctionMenu.add(Integer.valueOf(i));
                AlphaOutOnSubscribe.directSetResult(getTopImage(i));
            }
        }
    }

    public void enableMenuItem(int... iArr) {
        if (!this.mDisabledFunctionMenu.isEmpty()) {
            for (int i : iArr) {
                this.mDisabledFunctionMenu.remove(Integer.valueOf(i));
                AlphaInOnSubscribe.directSetResult(getTopImage(i));
            }
        }
    }

    public void setRecordingTimeState(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.setRecordingTimeState(i);
        } else {
            FragmentTopAlert.setPendingRecordingState(i);
        }
    }

    public void updateRecordingTime(String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.updateRecordingTime(str);
        }
    }

    private void alertHDR(int i, boolean z, boolean z2, boolean z3) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            if (z3) {
                if (i != 0) {
                    this.mLastAnimationComponent.reverse(true);
                } else if (z2) {
                    getTopImage(194).performClick();
                }
            }
            topAlert.alertHDR(i, getAlertTopMargin(), z);
        }
    }

    public void alertHDR(int i, boolean z, boolean z2) {
        alertHDR(i, z, z2, true);
    }

    public void alertFlash(int i, boolean z, boolean z2, boolean z3) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            if (z3) {
                if (i != 0) {
                    this.mLastAnimationComponent.reverse(true);
                } else if (z2) {
                    getTopImage(193).performClick();
                }
            }
            topAlert.alertFlash(i, getAlertTopMargin(), z);
        }
    }

    public void alertFlash(int i, boolean z, boolean z2) {
        alertFlash(i, z, z2, true);
    }

    public void alertUpdateValue(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertUpdateValue(i, getAlertTopMargin());
        }
    }

    public void alertAiSceneSelector(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertAiSceneSelector(i, getAlertTopMargin());
        }
    }

    public void alertMoonModeSelector(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertMoonSelector(getResources().getString(2131296842), getResources().getString(2131296851), i, getAlertTopMargin());
        }
    }

    public void setAiSceneImageLevel(int i) {
        if (i == 25) {
            i = 23;
        }
        Drawable aiSceneDrawable = getAiSceneDrawable(i);
        ImageView topImage = getTopImage(201);
        if (aiSceneDrawable != null && topImage != null) {
            topImage.setImageDrawable(aiSceneDrawable);
            this.mCurrentAiSceneLevel = i;
        }
    }

    public void alertSwitchHint(int i, @StringRes int i2) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertSwitchHint(i, i2, getAlertTopMargin());
        }
    }

    public void alertSwitchHint(int i, String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertSwitchHint(i, str, getAlertTopMargin());
        }
    }

    public void alertTopHint(int i, @StringRes int i2) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertTopHint(i, i2, getAlertTopMargin());
        }
    }

    public void alertTopHint(int i, String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertTopHint(i, getAlertTopMargin(), str);
        }
    }

    private FragmentTopAlert getTopAlert() {
        if (this.mFragmentTopAlert == null) {
            Log.d(TAG, "getTopAlert(): fragment is null");
            return null;
        } else if (this.mFragmentTopAlert.isAdded()) {
            return this.mFragmentTopAlert;
        } else {
            Log.d(TAG, "getTopAlert(): fragment is not added yet");
            return null;
        }
    }

    public void setShow(boolean z) {
        if (getTopAlert() != null) {
            getTopAlert().setShow(z);
        }
    }

    private FragmentTopConfigExtra getTopExtra() {
        return (FragmentTopConfigExtra) getChildFragmentManager().findFragmentByTag(String.valueOf(245));
    }

    private int getAlertTopMargin() {
        if (this.mCurrentMode == 165 && this.mDisplayRectTopMargin == 0) {
            float f = (float) getResources().getDisplayMetrics().widthPixels;
            return (((int) (((f / 0.75f) - f) / 2.0f)) - this.mDisplayRectTopMargin) + getResources().getDimensionPixelSize(2131361825);
        } else if (Util.isNotchDevice && this.mDisplayRectTopMargin == Util.sStatusBarHeight) {
            return this.mDisplayRectTopMargin + this.mTopConfigMenu.getHeight();
        } else {
            if (this.mDisplayRectTopMargin <= 0) {
                return this.mTopConfigMenu.getHeight() + getResources().getDimensionPixelSize(2131361825);
            }
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131361984);
            if (!Util.isLongRatioScreen || dimensionPixelSize <= 0) {
                return this.mDisplayRectTopMargin + getResources().getDimensionPixelSize(2131361825);
            }
            return dimensionPixelSize;
        }
    }

    public void rotate() {
    }

    public void onAngleChanged(float f) {
    }

    public void onBeautyRecordingStart() {
        onBackEvent(5);
        ViewCompat.animate(this.mTopConfigMenu).alpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).start();
    }

    public void onBeautyRecordingStop() {
        ViewCompat.animate(this.mTopConfigMenu).alpha(1.0f).start();
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        this.mDisplayRectTopMargin = Util.getDisplayRect(getContext()).top;
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        if (this.mFragmentTopAlert == null) {
            this.mFragmentTopAlert = new FragmentTopAlert();
            this.mFragmentTopAlert.setShow(true);
            this.mFragmentTopAlert.setDegree(this.mDegree);
            this.mFragmentTopAlert.setAlertTopMargin(getAlertTopMargin());
            FragmentUtils.addFragmentWithTag(getChildFragmentManager(), 2131558584, this.mFragmentTopAlert, this.mFragmentTopAlert.getFragmentTag());
        }
        ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
        if (configChanges != null) {
            configChanges.reCheckMutexConfigs(this.mCurrentMode);
            configChanges.reCheckUltraPixelPhotoGraphy();
            configChanges.reCheckLiveShot();
            configChanges.reCheckHandGesture();
        }
    }

    private void reConfigCommonTip() {
        if (CameraSettings.isHangGestureOpen()) {
            alertTopHint(0, 2131296900);
        } else if (CameraSettings.isRearMenuUltraPixelPhotographyOn() || CameraSettings.isUltraPixelPhotographyOn()) {
            alertTopHint(0, DataRepository.dataItemConfig().getRearComponentConfigUltraPixel().getUltraPixelOpenTip());
        } else if (CameraSettings.isFrontMenuUltraPixelPhotographyOn()) {
            alertTopHint(0, DataRepository.dataItemConfig().getFrontComponentConfigUltraPixel().getUltraPixelOpenTip());
        } else if (!EyeLightConstant.OFF.equals(CameraSettings.getEyeLightType())) {
            alertTopHint(0, 2131296789);
        }
    }

    private void reConfigTipOfFlash(boolean z) {
        ComponentConfigFlash componentFlash = DataRepository.dataItemConfig().getComponentFlash();
        if (!componentFlash.isEmpty()) {
            String componentValue = componentFlash.getComponentValue(this.mCurrentMode);
            if ("1".equals(componentValue) || ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_ON.equals(componentValue)) {
                alertFlash(0, false, false, z);
            } else if ("2".equals(componentValue)) {
                alertFlash(0, true, false, z);
            } else {
                alertFlash(8, false, false, z);
            }
        }
    }

    private void reConfigTipOfHdr(boolean z) {
        ComponentConfigHdr componentHdr = DataRepository.dataItemConfig().getComponentHdr();
        if (!componentHdr.isEmpty()) {
            String componentValue = componentHdr.getComponentValue(this.mCurrentMode);
            if ("on".equals(componentValue) || "normal".equals(componentValue)) {
                alertHDR(0, false, false, z);
            } else if (ComponentConfigHdr.HDR_VALUE_LIVE.equals(componentValue)) {
                alertHDR(0, true, false, z);
            } else {
                alertHDR(8, false, false, z);
            }
        }
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        FragmentTopConfigExtra topExtra = getTopExtra();
        if (topExtra != null) {
            topExtra.provideRotateItem(list, i);
        }
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.provideRotateItem(list, i);
        }
    }

    private Drawable getAiSceneDrawable(int i) {
        Drawable drawable;
        TypedArray obtainTypedArray = getResources().obtainTypedArray(2131623937);
        if (i < 0 || i >= obtainTypedArray.length()) {
            drawable = null;
        } else {
            drawable = obtainTypedArray.getDrawable(i);
        }
        obtainTypedArray.recycle();
        return drawable;
    }

    public void updateContentDescription() {
        ImageView topImage = getTopImage(196);
        if (topImage != null) {
            topImage.setContentDescription(getString(2131296486));
        }
    }

    public void alertLightingTitle(boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertLightingTitle(z, getAlertTopMargin());
        }
    }

    public void alertLightingHint(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertLightingHint(i, getAlertTopMargin());
        }
    }

    public boolean needViewClear() {
        return true;
    }

    public void alertAiDetectTipHint(int i, int i2, long j) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertAiDetectTipHint(i, i2, getAlertTopMargin(), j);
        }
    }

    public void hideAlert() {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.clear();
            topAlert.setShow(false);
        }
    }

    public void clearAlertStatus() {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.clearAlertStatus();
        }
    }

    public void reInitAlert(boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.setShow(true);
            reConfigCommonTip();
            reConfigTipOfFlash(z);
            reConfigTipOfHdr(z);
            topAlert.updateMusicHint();
            alertUpdateValue(4);
        }
    }
}
