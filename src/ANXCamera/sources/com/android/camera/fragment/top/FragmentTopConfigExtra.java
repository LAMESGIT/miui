package com.android.camera.fragment.top;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.config.ComponentConfigBeauty;
import com.android.camera.data.data.config.DataItemConfig;
import com.android.camera.data.data.config.SupportedConfigFactory;
import com.android.camera.data.data.config.SupportedConfigs;
import com.android.camera.data.data.runing.ComponentRunningTiltValue;
import com.android.camera.data.data.runing.ComponentRunningTimer;
import com.android.camera.data.data.runing.DataItemRunning;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.FragmentUtils;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.protocol.ModeProtocol.TopConfigProtocol;
import com.android.camera.statistic.CameraStat;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import io.reactivex.Completable;
import java.util.List;
import miui.view.animation.CubicEaseOutInterpolator;
import miui.view.animation.QuarticEaseInOutInterpolator;
import miui.view.animation.QuarticEaseOutInterpolator;
import miui.view.animation.SineEaseInInterpolator;

public class FragmentTopConfigExtra extends BaseFragment implements OnClickListener {
    public static final int FRAGMENT_INFO = 245;
    private View mBackgroundView;
    private int mDisplayRectTopMargin;
    private ExtraAdapter mExtraAdapter;
    private RecyclerView mRecyclerView;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mBackgroundView = view.findViewById(2131558585);
        this.mRecyclerView = (RecyclerView) view.findViewById(2131558586);
        this.mDisplayRectTopMargin = Util.getDisplayRect(getContext()).top;
        int currentCameraId = DataRepository.dataItemGlobal().getCurrentCameraId();
        SupportedConfigs supportedExtraConfigs = SupportedConfigFactory.getSupportedExtraConfigs(this.mCurrentMode, currentCameraId, DataRepository.dataCloudMgr().DataCloudFeature(), Camera2DataContainer.getInstance().getCapabilitiesByBogusCameraId(currentCameraId, this.mCurrentMode), DataRepository.dataItemGlobal().isNormalIntent());
        int i = 4;
        if (supportedExtraConfigs.getLength() <= 4) {
            i = supportedExtraConfigs.getLength();
        }
        int max = Math.max(1, i);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131361909) * ((int) Math.ceil((double) (((float) supportedExtraConfigs.getLength()) / ((float) max))));
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mRecyclerView.getLayoutParams();
        marginLayoutParams.height = dimensionPixelSize;
        dimensionPixelSize = getResources().getDimensionPixelSize(2131362117);
        if (dimensionPixelSize > 0) {
            marginLayoutParams.setMargins(0, dimensionPixelSize, 0, dimensionPixelSize);
        }
        if (Util.isLongRatioScreen) {
            adjustViewBackground(this.mCurrentMode);
            this.mBackgroundView.setPadding(0, this.mDisplayRectTopMargin, 0, 0);
        }
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), max));
        this.mExtraAdapter = new ExtraAdapter(supportedExtraConfigs, this);
        this.mExtraAdapter.setNewDegree(this.mDegree);
        this.mRecyclerView.setAdapter(this.mExtraAdapter);
        this.mRecyclerView.setFocusable(false);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968627;
    }

    public int getFragmentInto() {
        return 245;
    }

    private void adjustViewBackground(int i) {
        if (DataRepository.dataItemRunning().getUiStyle() != 3) {
            this.mBackgroundView.setBackgroundColor(-16777216);
        } else {
            this.mBackgroundView.setBackgroundColor(2131427400);
        }
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
    }

    public void onClick(View view) {
        if (isEnableClick()) {
            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                int intValue = ((Integer) view.getTag()).intValue();
                configChanges.onConfigChanged(intValue);
                TopAlert topAlert = (TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172);
                if (Util.isAccessible()) {
                    this.mExtraAdapter.setOnClictTag(intValue);
                }
                if (intValue == 225) {
                    topAlert.removeExtraMenu(5);
                } else if (intValue != 242) {
                    this.mRecyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    topAlert.hideExtraMenu();
                    ((TopConfigProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(193)).startAiLens();
                    CameraStat.recordCountEvent(CameraStat.CATEGORY_COUNTER, CameraStat.KEY_AI_DETECT_CHANGED);
                }
            }
        }
    }

    public void reFresh() {
        this.mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        animateIn();
    }

    /* Access modifiers changed, original: protected */
    public Animation provideEnterAnimation(int i) {
        return null;
    }

    public void animateIn() {
        if (Util.isLongRatioScreen) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f);
            scaleAnimation.setInterpolator(new QuarticEaseInOutInterpolator());
            scaleAnimation.setDuration(320);
            this.mBackgroundView.startAnimation(scaleAnimation);
            AlphaAnimation alphaAnimation = new AlphaAnimation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f);
            alphaAnimation.setInterpolator(new QuarticEaseOutInterpolator());
            alphaAnimation.setStartOffset(120);
            alphaAnimation.setDuration(280);
            this.mRecyclerView.startAnimation(alphaAnimation);
            return;
        }
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1, -0.1f, 1, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        animationSet.addAnimation(alphaAnimation2);
        animationSet.addAnimation(translateAnimation);
        animationSet.setInterpolator(new CubicEaseOutInterpolator());
        animationSet.setDuration(350);
        getView().startAnimation(animationSet);
    }

    /* Access modifiers changed, original: protected */
    public Animation provideExitAnimation() {
        return null;
    }

    public void animateOut() {
        AnonymousClass1 anonymousClass1 = new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                FragmentTopConfigExtra.this.setClickEnable(false);
            }

            public void onAnimationEnd(Animation animation) {
                if (FragmentTopConfigExtra.this.canProvide()) {
                    FragmentUtils.removeFragmentByTag(FragmentTopConfigExtra.this.getFragmentManager(), FragmentTopConfigExtra.this.getFragmentTag());
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
        if (Util.isLongRatioScreen) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            scaleAnimation.setInterpolator(new QuarticEaseInOutInterpolator());
            scaleAnimation.setDuration(320);
            scaleAnimation.setAnimationListener(anonymousClass1);
            this.mBackgroundView.startAnimation(scaleAnimation);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            alphaAnimation.setInterpolator(new SineEaseInInterpolator());
            alphaAnimation.setDuration(120);
            alphaAnimation.setFillAfter(true);
            this.mRecyclerView.startAnimation(alphaAnimation);
            return;
        }
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1, -0.07f);
        animationSet.addAnimation(alphaAnimation2);
        animationSet.addAnimation(translateAnimation);
        animationSet.setInterpolator(new CubicEaseOutInterpolator());
        animationSet.setDuration(200);
        animationSet.setAnimationListener(anonymousClass1);
        getView().startAnimation(animationSet);
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        this.mExtraAdapter.setNewDegree(i);
        for (i = 0; i < this.mRecyclerView.getChildCount(); i++) {
            list.add(this.mRecyclerView.getChildAt(i));
        }
    }

    private void addContentDescriptionForConfigs(int i) {
        DataItemRunning dataItemRunning = DataRepository.dataItemRunning();
        DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
        StringBuilder stringBuilder = new StringBuilder();
        boolean isSwitchOn;
        switch (i) {
            case 226:
                ComponentRunningTimer componentRunningTimer = dataItemRunning.getComponentRunningTimer();
                boolean isSwitchOn2 = componentRunningTimer.isSwitchOn();
                stringBuilder.append(getString(componentRunningTimer.getValueDisplayString(160)));
                if (!isSwitchOn2) {
                    stringBuilder.append(getString(2131296496));
                    break;
                }
                break;
            case 228:
                ComponentRunningTiltValue componentRunningTiltValue = dataItemRunning.getComponentRunningTiltValue();
                if (!dataItemRunning.isSwitchOn("pref_camera_tilt_shift_mode")) {
                    stringBuilder.append(getString(2131296717));
                    stringBuilder.append(getString(2131296496));
                    break;
                }
                stringBuilder.append(getString(componentRunningTiltValue.getValueDisplayString(160)));
                break;
            case 229:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_gradienter_key");
                stringBuilder.append(getString(2131296714));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 230:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_hand_night_key");
                stringBuilder.append(getString(2131296715));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 231:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_ubifocus_key");
                stringBuilder.append(getString(2131296719));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 232:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_video_speed_slow_key");
                stringBuilder.append(getString(2131296601));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 233:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_video_speed_fast_key");
                stringBuilder.append(getString(2131296600));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 234:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_scenemode_setting_key");
                stringBuilder.append(getString(2131296718));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 235:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_groupshot_mode_key");
                stringBuilder.append(getString(2131296716));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 236:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_magic_mirror_key");
                stringBuilder.append(getString(2131296655));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 238:
                isSwitchOn = dataItemRunning.isSwitchOn("pref_camera_show_gender_age_key");
                stringBuilder.append(getString(2131296586));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 239:
                i = DataRepository.dataItemGlobal().getCurrentMode();
                ComponentConfigBeauty componentConfigBeauty = dataItemConfig.getComponentConfigBeauty();
                boolean isSwitchOn3 = componentConfigBeauty.isSwitchOn(i);
                stringBuilder.append(getString(componentConfigBeauty.getValueDisplayString(i)));
                if (!isSwitchOn3) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
            case 240:
                isSwitchOn = CameraSettings.isDualCameraWaterMarkOpen();
                stringBuilder.append(getString(2131296553));
                if (!isSwitchOn) {
                    stringBuilder.append(getString(2131296496));
                    break;
                } else {
                    stringBuilder.append(getString(2131296495));
                    break;
                }
        }
        if (!TextUtils.isEmpty(stringBuilder) && Util.isAccessible()) {
            this.mRecyclerView.setContentDescription(stringBuilder);
            this.mRecyclerView.sendAccessibilityEvent(4);
        }
    }
}
