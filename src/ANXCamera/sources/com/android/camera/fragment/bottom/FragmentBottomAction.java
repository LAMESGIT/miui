package com.android.camera.fragment.bottom;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.camera.ActivityBase;
import com.android.camera.Camera;
import com.android.camera.CameraSettings;
import com.android.camera.Thumbnail;
import com.android.camera.ThumbnailUpdater;
import com.android.camera.Util;
import com.android.camera.animation.FragmentAnimationFactory;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.global.ComponentModuleList;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.FragmentFilter;
import com.android.camera.fragment.FragmentLighting;
import com.android.camera.fragment.FragmentUtils;
import com.android.camera.fragment.beauty.MenuItem;
import com.android.camera.log.Log;
import com.android.camera.module.LiveModule;
import com.android.camera.module.Module;
import com.android.camera.module.VideoModule;
import com.android.camera.permission.PermissionManager;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.ActionProcessing;
import com.android.camera.protocol.ModeProtocol.BottomMenuProtocol;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.CameraActionTrack;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.HandleBeautyRecording;
import com.android.camera.protocol.ModeProtocol.HandlerSwitcher;
import com.android.camera.protocol.ModeProtocol.ModeChangeController;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.CameraSnapView;
import com.android.camera.ui.CameraSnapView.SnapListener;
import com.android.camera.ui.EdgeHorizonScrollView;
import com.android.camera.ui.ModeSelectView;
import com.android.camera.ui.ModeSelectView.onModeClickedListener;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.mi.config.b;
import io.reactivex.Completable;
import java.util.List;
import miui.view.animation.CubicEaseInInterpolator;
import miui.view.animation.CubicEaseOutInterpolator;
import miui.view.animation.SineEaseOutInterpolator;

public class FragmentBottomAction extends BaseFragment implements OnClickListener, ActionProcessing, BottomMenuProtocol, HandleBackTrace, HandleBeautyRecording, HandlerSwitcher, ModeChangeController, SnapListener, onModeClickedListener {
    public static final int FRAGMENT_INFO = 241;
    private static final int MSG_SHOW_PROGRESS = 1;
    private static final String TAG = "FragmentBottomAction";
    private FrameLayout mBottomActionView;
    private ValueAnimator mBottomAnimator;
    private View mBottomMenuLayout;
    private ImageView mBottomRecordingCameraPicker;
    private TextView mBottomRecordingTime;
    private int mBottomRollDownHeight;
    private boolean mCameraPickEnable;
    private ImageView mCameraPicker;
    private int mCaptureProgressDelay;
    private ComponentModuleList mComponentModuleList;
    private CubicEaseOutInterpolator mCubicEaseOut;
    private int mCurrentBeautyActionMenuType;
    private int mCurrentLiveActionMenuType;
    private EdgeHorizonScrollView mEdgeHorizonScrollView;
    private int mFilterListHeight;
    private FragmentFilter mFragmentFilter;
    private View mFragmentLayoutExtra;
    private View mFragmentLayoutExtra2;
    private FragmentLighting mFragmentLighting;
    @SuppressLint({"HandlerLeak"})
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                FragmentBottomAction.this.mThumbnailProgress.setVisibility(0);
            }
        }
    };
    private boolean mInLoading;
    private boolean mIsBottomRollDown = false;
    private boolean mIsIntentAction;
    private boolean mIsShowFilter = false;
    private boolean mIsShowLighting = false;
    private long mLastPauseTime;
    private boolean mLongPressBurst;
    private BottomActionMenu mModeSelectLayout;
    private ModeSelectView mModeSelectView;
    private ProgressBar mPostProcess;
    private int mRecordProgressDelay;
    private ImageView mRecordingPause;
    private ImageView mRecordingReverse;
    private ImageView mRecordingSnap;
    private AlertDialog mReverseDialog;
    private CameraSnapView mShutterButton;
    private SineEaseOutInterpolator mSineEaseOut;
    private ImageView mThumbnailImage;
    private ViewGroup mThumbnailImageLayout;
    private ProgressBar mThumbnailProgress;
    private RelativeLayout mV9bottomParentLayout;
    private boolean mVideoCaptureEnable;
    private boolean mVideoPauseSupported;
    private boolean mVideoRecordingPaused;
    private boolean mVideoRecordingStarted;
    private boolean mVideoReverseEnable;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mBottomActionView = (FrameLayout) view.findViewById(2131558427);
        ((MarginLayoutParams) this.mBottomActionView.getLayoutParams()).height = Util.getBottomHeight(getResources());
        this.mBottomRollDownHeight = getResources().getDimensionPixelSize(2131361923);
        this.mV9bottomParentLayout = (RelativeLayout) view.findViewById(2131558437);
        ((MarginLayoutParams) this.mV9bottomParentLayout.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(2131361916) + Util.sNavigationBarHeight;
        this.mComponentModuleList = DataRepository.dataItemGlobal().getComponentModuleList();
        if (this.mModeSelectLayout == null) {
            this.mModeSelectLayout = new BottomActionMenu(getContext(), (FrameLayout) view.findViewById(2131558428));
        }
        this.mEdgeHorizonScrollView = this.mModeSelectLayout.getCameraOperateMenuView();
        this.mBottomMenuLayout = view.findViewById(2131558429);
        this.mModeSelectView = this.mModeSelectLayout.getCameraOperateSelectView();
        this.mModeSelectView.init(this.mComponentModuleList, this.mCurrentMode);
        this.mModeSelectView.setOnModeClickedListener(this);
        this.mThumbnailImageLayout = (ViewGroup) view.findViewById(2131558438);
        this.mThumbnailImage = (ImageView) this.mThumbnailImageLayout.findViewById(2131558439);
        this.mThumbnailProgress = (ProgressBar) view.findViewById(2131558441);
        this.mRecordingPause = (ImageView) view.findViewById(2131558440);
        this.mShutterButton = (CameraSnapView) view.findViewById(2131558442);
        this.mCameraPicker = (ImageView) view.findViewById(2131558444);
        this.mRecordingSnap = (ImageView) view.findViewById(2131558445);
        this.mRecordingReverse = (ImageView) view.findViewById(2131558446);
        this.mFragmentLayoutExtra = view.findViewById(2131558433);
        this.mFragmentLayoutExtra2 = view.findViewById(2131558434);
        this.mPostProcess = (ProgressBar) view.findViewById(2131558443);
        this.mBottomRecordingTime = (TextView) view.findViewById(2131558435);
        this.mBottomRecordingCameraPicker = (ImageView) view.findViewById(2131558436);
        this.mBottomRecordingCameraPicker.setOnClickListener(this);
        this.mShutterButton.setSnapListener(this);
        this.mShutterButton.setSnapClickEnable(false);
        this.mCaptureProgressDelay = getResources().getInteger(2131755012);
        this.mRecordProgressDelay = getResources().getInteger(2131755013);
        this.mThumbnailImageLayout.setOnClickListener(this);
        this.mCameraPicker.setOnClickListener(this);
        this.mRecordingPause.setOnClickListener(this);
        this.mRecordingSnap.setOnClickListener(this);
        this.mRecordingReverse.setOnClickListener(this);
        adjustViewBackground(this.mModeSelectLayout.getView(), this.mCurrentMode);
        provideAnimateElement(this.mCurrentMode, null, 2);
        this.mIsIntentAction = DataRepository.dataItemGlobal().isIntentAction();
        this.mCubicEaseOut = new CubicEaseOutInterpolator();
        this.mSineEaseOut = new SineEaseOutInterpolator();
        this.mFilterListHeight = getContext().getResources().getDimensionPixelSize(2131361838);
        if (Util.isAccessible()) {
            Util.setAccessibilityFocusable(this.mV9bottomParentLayout, false);
        }
    }

    public void onResume() {
        super.onResume();
        initThumbLayoutByIntent();
    }

    private void initThumbLayoutByIntent() {
        if (this.mIsIntentAction) {
            this.mThumbnailImage.setBackground(null);
            ((MarginLayoutParams) this.mThumbnailImage.getLayoutParams()).setMargins(0, 0, 0, 0);
            this.mThumbnailImage.setImageResource(2130837971);
        } else {
            ActivityBase activityBase = (ActivityBase) getContext();
            if ((activityBase.startFromSecureKeyguard() || activityBase.isGalleryLocked()) && !activityBase.isJumpBack()) {
                activityBase.getThumbnailUpdater().setThumbnail(null, true, false);
            } else if (PermissionManager.checkStoragePermissions()) {
                activityBase.getThumbnailUpdater().getLastThumbnail();
            }
        }
    }

    public boolean onHandleSwitcher(int i) {
        if (!this.mIsShowFilter && !this.mIsShowLighting) {
            return false;
        }
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        if (cameraAction == null || cameraAction.isDoingAction()) {
            return true;
        }
        if (this.mIsShowFilter) {
            this.mFragmentFilter.switchFilter(i);
        } else if (this.mIsShowLighting) {
            this.mFragmentLighting.switchLighting(i);
        }
        return true;
    }

    public void filterUiChange() {
        this.mFragmentFilter.updateFilterData();
    }

    public boolean isShowFilterView() {
        return this.mIsShowFilter;
    }

    public boolean isShowLightingView() {
        return this.mIsShowLighting;
    }

    public void setLightingViewStatus(boolean z) {
        this.mIsShowLighting = z;
    }

    private void setRecordingTimeState(int i) {
        switch (i) {
            case 1:
                if (this.mCurrentMode == 174) {
                    this.mBottomRecordingTime.setText("00:15");
                }
                Completable.create(new AlphaInOnSubscribe(this.mBottomRecordingTime)).subscribe();
                return;
            case 2:
                if (this.mBottomRecordingTime.getVisibility() == 0) {
                    Completable.create(new AlphaOutOnSubscribe(this.mBottomRecordingTime)).subscribe();
                }
                if (this.mBottomRecordingCameraPicker.getVisibility() == 0) {
                    Completable.create(new AlphaOutOnSubscribe(this.mBottomRecordingCameraPicker)).subscribe();
                    return;
                }
                return;
            case 3:
                Completable.create(new AlphaInOnSubscribe(this.mBottomRecordingCameraPicker)).subscribe();
                return;
            case 4:
                if (this.mBottomRecordingTime.getVisibility() != 0) {
                    this.mBottomRecordingTime.setVisibility(0);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void updateRecordingTime(String str) {
        this.mBottomRecordingTime.setText(str);
    }

    public boolean showOrHideFilterView() {
        if (this.mIsShowFilter) {
            startBottomAnimation(false);
            startExtraLayoutAnimation(this.mFragmentLayoutExtra, false);
            if (Util.isAccessible()) {
                this.mFragmentLayoutExtra.setContentDescription(getString(2131296505));
                this.mFragmentLayoutExtra.sendAccessibilityEvent(128);
            }
        } else {
            if (this.mIsShowLighting) {
                this.mIsShowLighting = false;
                startExtraLayoutExchangeAnimation(this.mFragmentLayoutExtra2);
            }
            if (this.mFragmentFilter.isAdded()) {
                this.mFragmentFilter.reInit();
            }
            startBottomAnimation(true);
            startExtraLayoutAnimation(this.mFragmentLayoutExtra, true);
        }
        this.mIsShowFilter ^= 1;
        return this.mIsShowFilter;
    }

    public boolean showOrHideLightingView() {
        if (this.mIsShowLighting) {
            startBottomAnimation(false);
            startExtraLayoutAnimation(this.mFragmentLayoutExtra2, false);
        } else {
            if (this.mIsShowFilter) {
                this.mIsShowFilter = false;
                startExtraLayoutExchangeAnimation(this.mFragmentLayoutExtra);
            }
            if (this.mFragmentLighting.isAdded()) {
                this.mFragmentLighting.reInit();
            }
            startBottomAnimation(true);
            startExtraLayoutAnimation(this.mFragmentLayoutExtra2, true);
        }
        this.mIsShowLighting ^= 1;
        return this.mIsShowLighting;
    }

    private void startBottomAnimation(boolean z) {
        if (this.mIsBottomRollDown != z) {
            this.mIsBottomRollDown = z;
            if (z) {
                ViewCompat.setAlpha(this.mBottomMenuLayout, 1.0f);
                ViewCompat.animate(this.mBottomMenuLayout).setDuration(150).alpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setInterpolator(this.mCubicEaseOut).start();
                ViewCompat.setTranslationY(this.mBottomMenuLayout, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                ViewCompat.animate(this.mBottomMenuLayout).setDuration(300).translationY((float) this.mFilterListHeight).setInterpolator(this.mCubicEaseOut).start();
                ViewCompat.setTranslationY(this.mV9bottomParentLayout, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                ViewCompat.animate(this.mV9bottomParentLayout).setDuration(300).translationY((float) this.mBottomRollDownHeight).setInterpolator(this.mCubicEaseOut).start();
                ViewCompat.setScaleX(this.mShutterButton, 1.0f);
                ViewCompat.setScaleY(this.mShutterButton, 1.0f);
                ViewCompat.animate(this.mShutterButton).setDuration(300).scaleX(0.9f).scaleY(0.9f).setInterpolator(this.mCubicEaseOut).start();
            } else {
                ViewCompat.setAlpha(this.mBottomMenuLayout, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                ViewCompat.animate(this.mBottomMenuLayout).setStartDelay(50).setDuration(250).alpha(1.0f).setInterpolator(this.mSineEaseOut).start();
                ViewCompat.setTranslationY(this.mBottomMenuLayout, (float) this.mFilterListHeight);
                ViewCompat.animate(this.mBottomMenuLayout).setDuration(300).translationY(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setInterpolator(this.mCubicEaseOut).start();
                ViewCompat.setTranslationY(this.mV9bottomParentLayout, (float) this.mBottomRollDownHeight);
                ViewCompat.animate(this.mV9bottomParentLayout).setDuration(300).translationY(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setInterpolator(this.mCubicEaseOut).start();
                ViewCompat.setScaleX(this.mShutterButton, 0.9f);
                ViewCompat.setScaleY(this.mShutterButton, 0.9f);
                ViewCompat.animate(this.mShutterButton).setDuration(300).scaleX(1.0f).scaleY(1.0f).setInterpolator(this.mCubicEaseOut).start();
            }
        }
    }

    private void startExtraLayoutAnimation(final View view, boolean z) {
        if (z) {
            ViewCompat.setTranslationY(view, (float) (-this.mFilterListHeight));
            ViewCompat.setAlpha(view, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            ViewCompat.animate(view).setDuration(300).translationY(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).alpha(1.0f).setInterpolator(this.mCubicEaseOut).setListener(new ViewPropertyAnimatorListener() {
                public void onAnimationStart(View view) {
                    view.setVisibility(0);
                }

                public void onAnimationEnd(View view) {
                    FragmentBottomAction.this.mModeSelectView.setVisibility(8);
                }

                public void onAnimationCancel(View view) {
                }
            }).start();
            return;
        }
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.animate(view).setDuration(150).alpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setInterpolator(this.mSineEaseOut).start();
        ViewCompat.setTranslationY(view, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        ViewCompat.animate(view).setDuration(300).translationY((float) (-this.mFilterListHeight)).setInterpolator(this.mCubicEaseOut).setListener(new ViewPropertyAnimatorListener() {
            public void onAnimationStart(View view) {
                FragmentBottomAction.this.mModeSelectView.setVisibility(0);
            }

            public void onAnimationEnd(View view) {
                view.setVisibility(8);
            }

            public void onAnimationCancel(View view) {
            }
        }).start();
    }

    private void startExtraLayoutExchangeAnimation(final View view) {
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setTranslationY(view, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
        ViewCompat.animate(view).alpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).translationY((float) this.mFilterListHeight).setDuration(250).setInterpolator(this.mCubicEaseOut).setListener(new ViewPropertyAnimatorListener() {
            public void onAnimationStart(View view) {
            }

            public void onAnimationEnd(View view) {
                view.setVisibility(8);
            }

            public void onAnimationCancel(View view) {
            }
        }).start();
    }

    public void setClickEnable(boolean z) {
        super.setClickEnable(z);
        this.mShutterButton.setSnapClickEnable(z);
    }

    private void adjustViewBackground(View view, int i) {
        if (i == 165) {
            view.setBackgroundResource(2131427390);
            return;
        }
        i = DataRepository.dataItemRunning().getUiStyle();
        if (i == 1 || i == 3) {
            view.setBackgroundResource(2131427401);
        } else {
            view.setBackgroundResource(2131427390);
        }
    }

    public void updateThumbnail(Thumbnail thumbnail, boolean z) {
        if (isAdded()) {
            ThumbnailUpdater thumbnailUpdater = ((ActivityBase) getContext()).getThumbnailUpdater();
            if (!(thumbnailUpdater == null || thumbnailUpdater.getThumbnail() == thumbnail)) {
                thumbnailUpdater.setThumbnail(thumbnail, false, false);
                Log.d(TAG, "inconsistent thumbnail");
            }
            this.mHandler.removeCallbacksAndMessages(null);
            this.mInLoading = false;
            if (this.mThumbnailProgress.getVisibility() != 8) {
                this.mThumbnailProgress.setVisibility(8);
            }
            if (!this.mIsIntentAction) {
                if (thumbnail == null) {
                    this.mThumbnailImage.setImageResource(2130837853);
                    return;
                }
                RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(getResources(), thumbnail.getBitmap());
                create.getPaint().setAntiAlias(true);
                create.setCircular(true);
                this.mThumbnailImage.setImageDrawable(create);
                if (z && !this.mVideoRecordingStarted) {
                    ViewCompat.setAlpha(this.mThumbnailImageLayout, 0.3f);
                    ViewCompat.setScaleX(this.mThumbnailImageLayout, 0.5f);
                    ViewCompat.setScaleY(this.mThumbnailImageLayout, 0.5f);
                    ViewCompat.animate(this.mThumbnailImageLayout).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(80).start();
                }
            }
        }
    }

    private boolean isThumbLoading() {
        return this.mInLoading;
    }

    public void updateLoading(boolean z) {
        if (z) {
            this.mInLoading = false;
            this.mHandler.removeCallbacksAndMessages(null);
            this.mThumbnailProgress.setVisibility(8);
        } else if (!this.mIsIntentAction) {
            this.mInLoading = true;
            int i = this.mCurrentMode;
            if (!(i == 166 || i == 172 || i == 174)) {
                switch (i) {
                    case 161:
                    case 162:
                        break;
                    default:
                        this.mHandler.sendEmptyMessageDelayed(1, (long) this.mCaptureProgressDelay);
                        break;
                }
            }
            this.mHandler.sendEmptyMessageDelayed(1, (long) this.mRecordProgressDelay);
        }
    }

    public void processingPrepare() {
        BottomAnimationConfig configVariables = BottomAnimationConfig.generate(false, this.mCurrentMode, true, isFPS960(), CameraSettings.isVideoBokehOn()).configVariables();
        updateBottomInRecording(true, true);
        this.mShutterButton.prepareRecording(configVariables);
        if (this.mCurrentMode == 174) {
            setRecordingTimeState(1);
        }
    }

    public void processingStart() {
        this.mEdgeHorizonScrollView.setEnabled(false);
        this.mModeSelectView.setEnabled(false);
        this.mVideoRecordingStarted = true;
        this.mShutterButton.triggerAnimation(BottomAnimationConfig.generate(false, this.mCurrentMode, true, isFPS960(), CameraSettings.isVideoBokehOn()).configVariables());
    }

    public void processingPause() {
        this.mVideoRecordingPaused = true;
        this.mShutterButton.pauseRecording();
        this.mRecordingPause.setImageResource(2130837836);
        this.mRecordingPause.setContentDescription(getString(2131296468));
        if (this.mCurrentMode == 174) {
            setRecordingTimeState(3);
            this.mShutterButton.addSegmentNow();
            int i = 8;
            this.mModeSelectView.setVisibility(8);
            Completable.create(new AlphaInOnSubscribe(this.mModeSelectLayout.getView())).subscribe();
            int visibility = this.mRecordingReverse.getVisibility();
            ImageView imageView = this.mRecordingReverse;
            if (visibility != 0) {
                i = 0;
            }
            imageView.setVisibility(i);
        }
    }

    public void processingResume() {
        int i = 0;
        this.mVideoRecordingPaused = false;
        this.mShutterButton.resumeRecording();
        this.mRecordingPause.setImageResource(2130837835);
        this.mRecordingPause.setContentDescription(getString(2131296467));
        if (this.mCurrentMode == 174) {
            setRecordingTimeState(4);
            Completable.create(new AlphaOutOnSubscribe(this.mModeSelectLayout.getView())).subscribe();
            int visibility = this.mRecordingReverse.getVisibility();
            this.mRecordingReverse.setVisibility(visibility == 0 ? 8 : 0);
            ImageView imageView = this.mBottomRecordingCameraPicker;
            if (visibility == 0) {
                i = 8;
            }
            imageView.setVisibility(i);
        }
    }

    public void processingFinish() {
        if (isAdded()) {
            if (this.mShutterButton.getVisibility() != 0) {
                this.mShutterButton.setVisibility(0);
            }
            this.mEdgeHorizonScrollView.setEnabled(true);
            this.mModeSelectView.setEnabled(true);
            this.mVideoRecordingStarted = false;
            this.mVideoRecordingPaused = false;
            setProgressBarVisible(8);
            this.mShutterButton.showRoundPaintItem();
            if (this.mCurrentMode == 174) {
                this.mModeSelectView.setVisibility(0);
                this.mModeSelectLayout.getView().setVisibility(0);
                setRecordingTimeState(2);
            }
            this.mShutterButton.triggerAnimation(BottomAnimationConfig.generate(false, this.mCurrentMode, false, isFPS960(), CameraSettings.isVideoBokehOn()).configVariables());
            updateBottomInRecording(false, false);
        }
    }

    public void processingFailed() {
        updateLoading(true);
    }

    public void processingWorkspace(boolean z) {
        if (z) {
            this.mShutterButton.showRoundPaintItem();
            this.mShutterButton.invalidate();
            this.mBottomRecordingTime.setVisibility(0);
            if (this.mRecordingPause.getVisibility() == 8) {
                this.mRecordingPause.setImageResource(2130837836);
                Completable.create(new AlphaInOnSubscribe(this.mRecordingPause).targetGone()).subscribe();
            }
            if (this.mRecordingReverse.getVisibility() == 8) {
                Completable.create(new AlphaInOnSubscribe(this.mRecordingReverse).targetGone()).subscribe();
            }
            if (this.mBottomRecordingCameraPicker.getVisibility() == 8) {
                Completable.create(new AlphaInOnSubscribe(this.mBottomRecordingCameraPicker).targetGone()).subscribe();
                return;
            }
            return;
        }
        this.mShutterButton.pauseRecording();
        this.mShutterButton.hideRoundPaintItem();
        this.mShutterButton.invalidate();
        this.mShutterButton.addSegmentNow();
        this.mBottomRecordingTime.setVisibility(8);
        if (this.mRecordingPause.getVisibility() == 0) {
            Completable.create(new AlphaOutOnSubscribe(this.mRecordingPause).targetGone()).subscribe();
        }
        if (this.mRecordingReverse.getVisibility() == 0) {
            Completable.create(new AlphaOutOnSubscribe(this.mRecordingReverse).targetGone()).subscribe();
        }
        if (this.mBottomRecordingCameraPicker.getVisibility() == 0) {
            Completable.create(new AlphaOutOnSubscribe(this.mBottomRecordingCameraPicker).targetGone()).subscribe();
        }
    }

    public void processingPostAction() {
        if (this.mShutterButton.getVisibility() != 0) {
            this.mShutterButton.setVisibility(0);
        }
        this.mShutterButton.hideRoundPaintItem();
        this.mShutterButton.triggerAnimation(BottomAnimationConfig.generate(true, this.mCurrentMode, false, isFPS960(), CameraSettings.isVideoBokehOn()).configVariables());
        updateBottomInRecording(false, true);
        setProgressBarVisible(0);
    }

    private void setProgressBarVisible(int i) {
        if (this.mPostProcess.getVisibility() != i) {
            ValueAnimator ofFloat;
            if (i == 0) {
                this.mPostProcess.setAlpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                this.mPostProcess.setVisibility(0);
                ofFloat = ValueAnimator.ofFloat(new float[]{PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f});
                ofFloat.setDuration(300);
                ofFloat.setStartDelay(160);
                ofFloat.setInterpolator(new PathInterpolator(0.25f, 0.1f, 0.25f, 1.0f));
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Float f = (Float) valueAnimator.getAnimatedValue();
                        FragmentBottomAction.this.mPostProcess.setAlpha(f.floatValue());
                        FragmentBottomAction.this.mPostProcess.setScaleX((f.floatValue() * 0.1f) + 0.9f);
                        FragmentBottomAction.this.mPostProcess.setScaleY(0.9f + (0.1f * f.floatValue()));
                    }
                });
                ofFloat.start();
            } else if (this.mPostProcess.getVisibility() != 8) {
                ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO});
                ofFloat.setDuration(300);
                ofFloat.setInterpolator(new CubicEaseInInterpolator());
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        FragmentBottomAction.this.mPostProcess.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                ofFloat.addListener(new AnimatorListener() {
                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        FragmentBottomAction.this.mPostProcess.setVisibility(8);
                    }

                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                ofFloat.start();
            }
        }
    }

    public void processingAudioCapture(boolean z) {
        if (z) {
            this.mShutterButton.startRing();
        } else {
            this.mShutterButton.stopRing();
        }
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(179, this);
        modeCoordinator.attachProtocol(162, this);
        modeCoordinator.attachProtocol(183, this);
        modeCoordinator.attachProtocol(197, this);
        registerBackStack(modeCoordinator, this);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        this.mHandler.removeCallbacksAndMessages(null);
        modeCoordinator.detachProtocol(179, this);
        modeCoordinator.detachProtocol(162, this);
        modeCoordinator.detachProtocol(183, this);
        modeCoordinator.detachProtocol(197, this);
        unRegisterBackStack(modeCoordinator, this);
    }

    /* JADX WARNING: Missing block: B:25:0x005d, code skipped:
            return;
     */
    public void onClick(android.view.View r5) {
        /*
        r4 = this;
        r0 = r4.isEnableClick();
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.CameraAction) r0;
        if (r0 != 0) goto L_0x0016;
    L_0x0015:
        return;
    L_0x0016:
        r1 = r4.getContext();
        r1 = (com.android.camera.ActivityBase) r1;
        r1 = r1.getCurrentModule();
        r1 = r1.isIgnoreTouchEvent();
        if (r1 == 0) goto L_0x002e;
    L_0x0026:
        r5 = "FragmentBottomAction";
        r0 = "onClick: ignore click event, because module isn't ready";
        com.android.camera.log.Log.w(r5, r0);
        return;
    L_0x002e:
        r1 = r5.getId();
        r2 = 2131558436; // 0x7f0d0024 float:1.8742188E38 double:1.0531297953E-314;
        if (r1 == r2) goto L_0x011e;
    L_0x0037:
        r2 = 2131558438; // 0x7f0d0026 float:1.8742192E38 double:1.0531297963E-314;
        if (r1 == r2) goto L_0x0104;
    L_0x003c:
        r2 = 2131558440; // 0x7f0d0028 float:1.8742196E38 double:1.0531297973E-314;
        if (r1 == r2) goto L_0x00a2;
    L_0x0041:
        switch(r1) {
            case 2131558444: goto L_0x008c;
            case 2131558445: goto L_0x005e;
            case 2131558446: goto L_0x0046;
            default: goto L_0x0044;
        };
    L_0x0044:
        goto L_0x012c;
    L_0x0046:
        r5 = r4.mVideoReverseEnable;
        if (r5 == 0) goto L_0x005d;
    L_0x004a:
        r5 = r4.mVideoRecordingStarted;
        if (r5 != 0) goto L_0x004f;
    L_0x004e:
        goto L_0x005d;
    L_0x004f:
        r5 = r4.mShutterButton;
        r5 = r5.hasSegments();
        if (r5 != 0) goto L_0x0058;
    L_0x0057:
        return;
    L_0x0058:
        r4.showReverseConfirmDialog();
        goto L_0x012c;
    L_0x005d:
        return;
    L_0x005e:
        r5 = r4.mVideoCaptureEnable;
        if (r5 == 0) goto L_0x008b;
    L_0x0062:
        r5 = r4.mVideoRecordingStarted;
        if (r5 != 0) goto L_0x0067;
    L_0x0066:
        goto L_0x008b;
    L_0x0067:
        r5 = r4.getContext();
        r5 = (com.android.camera.ActivityBase) r5;
        if (r5 == 0) goto L_0x0083;
    L_0x006f:
        r0 = r5.getCurrentModule();
        r0 = r0 instanceof com.android.camera.module.VideoModule;
        if (r0 != 0) goto L_0x0078;
    L_0x0077:
        goto L_0x0083;
    L_0x0078:
        r5 = r5.getCurrentModule();
        r5 = (com.android.camera.module.VideoModule) r5;
        r5.takeVideoSnapShoot();
        goto L_0x012c;
    L_0x0083:
        r5 = "FragmentBottomAction";
        r0 = "onClick: recording snap is not allowed!!!";
        com.android.camera.log.Log.w(r5, r0);
        return;
    L_0x008b:
        return;
    L_0x008c:
        r0 = r0.isDoingAction();
        if (r0 == 0) goto L_0x0093;
    L_0x0092:
        return;
    L_0x0093:
        r0 = r4.isThumbLoading();
        if (r0 == 0) goto L_0x009a;
    L_0x0099:
        return;
    L_0x009a:
        r4.hideExtra();
        r4.changeCamera(r5);
        goto L_0x012c;
    L_0x00a2:
        r5 = r4.mVideoPauseSupported;
        if (r5 == 0) goto L_0x0103;
    L_0x00a6:
        r5 = r4.mVideoRecordingStarted;
        if (r5 != 0) goto L_0x00ab;
    L_0x00aa:
        goto L_0x0103;
    L_0x00ab:
        r5 = r4.mCurrentMode;
        r0 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        if (r5 == r0) goto L_0x00f2;
    L_0x00b1:
        r0 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        if (r5 == r0) goto L_0x00b9;
    L_0x00b5:
        switch(r5) {
            case 168: goto L_0x00f2;
            case 169: goto L_0x00f2;
            case 170: goto L_0x00f2;
            default: goto L_0x00b8;
        };
    L_0x00b8:
        return;
    L_0x00b9:
        r0 = java.lang.System.currentTimeMillis();
        r2 = r4.mLastPauseTime;
        r0 = r0 - r2;
        r2 = 0;
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 <= 0) goto L_0x00cd;
    L_0x00c6:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r5 >= 0) goto L_0x00cd;
    L_0x00cc:
        return;
    L_0x00cd:
        r5 = r4.mVideoRecordingPaused;
        if (r5 == 0) goto L_0x00d7;
    L_0x00d1:
        r5 = "live录制继续";
        com.android.camera.statistic.CameraStatUtil.trackLiveClick(r5);
        goto L_0x00dc;
    L_0x00d7:
        r5 = "live录制暂停";
        com.android.camera.statistic.CameraStatUtil.trackLiveClick(r5);
    L_0x00dc:
        r0 = java.lang.System.currentTimeMillis();
        r4.mLastPauseTime = r0;
        r5 = r4.getContext();
        r5 = (com.android.camera.ActivityBase) r5;
        r5 = r5.getCurrentModule();
        r5 = (com.android.camera.module.LiveModule) r5;
        r5.onPauseButtonClick();
        goto L_0x0102;
    L_0x00f2:
        r5 = r4.getContext();
        r5 = (com.android.camera.ActivityBase) r5;
        r5 = r5.getCurrentModule();
        r5 = (com.android.camera.module.VideoModule) r5;
        r5.onPauseButtonClick();
    L_0x0102:
        goto L_0x012c;
    L_0x0103:
        return;
    L_0x0104:
        r5 = r4.isThumbLoading();
        if (r5 == 0) goto L_0x010b;
    L_0x010a:
        return;
    L_0x010b:
        r5 = com.android.camera.data.DataRepository.dataItemGlobal();
        r5 = r5.isIntentAction();
        if (r5 != 0) goto L_0x011a;
    L_0x0115:
        r5 = 0;
        r0.onThumbnailClicked(r5);
        goto L_0x012c;
    L_0x011a:
        r0.onReviewCancelClicked();
        goto L_0x012c;
    L_0x011e:
        r0 = r4.mVideoRecordingPaused;
        if (r0 != 0) goto L_0x0123;
    L_0x0122:
        return;
    L_0x0123:
        r0 = "live录制切换";
        com.android.camera.statistic.CameraStatUtil.trackLiveClick(r0);
        r4.changeCamera(r5);
    L_0x012c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.bottom.FragmentBottomAction.onClick(android.view.View):void");
    }

    private void showReverseConfirmDialog() {
        CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_REVERSE);
        Builder builder = new Builder(getContext());
        builder.setMessage(2131296885);
        builder.setCancelable(false);
        builder.setPositiveButton(2131296886, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentBottomAction.this.mReverseDialog = null;
                FragmentBottomAction.this.mShutterButton.removeLastSegment();
                CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_REVERSE_CONFIRM);
                ((LiveModule) ((ActivityBase) FragmentBottomAction.this.getContext()).getCurrentModule()).doReverse();
            }
        });
        builder.setNegativeButton(2131296692, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentBottomAction.this.mReverseDialog = null;
            }
        });
        this.mReverseDialog = builder.show();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f0  */
    private void changeCamera(android.view.View r10) {
        /*
        r9 = this;
        r0 = com.android.camera.data.DataRepository.provider();
        r0 = r0.dataGlobal();
        r0 = (com.android.camera.data.data.global.DataItemGlobal) r0;
        r1 = r0.getCurrentCameraId();
        r2 = 0;
        r3 = 1;
        if (r1 != 0) goto L_0x0015;
        r4 = r3;
        goto L_0x0017;
        r4 = r2;
    L_0x0017:
        r0.setCameraId(r4);
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r4 != r3) goto L_0x0030;
    L_0x001e:
        r10 = android.support.v4.view.ViewCompat.animate(r10);
        r0 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r10 = r10.rotationBy(r0);
        r10 = r10.setDuration(r5);
        r10.start();
        goto L_0x0041;
    L_0x0030:
        r10 = android.support.v4.view.ViewCompat.animate(r10);
        r0 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r10 = r10.rotationBy(r0);
        r10 = r10.setDuration(r5);
        r10.start();
    L_0x0041:
        r10 = "FragmentBottomAction";
        r0 = java.util.Locale.ENGLISH;
        r5 = "switch camera from %d to %d, for module 0x%x";
        r6 = 3;
        r6 = new java.lang.Object[r6];
        r7 = java.lang.Integer.valueOf(r1);
        r6[r2] = r7;
        r7 = java.lang.Integer.valueOf(r4);
        r6[r3] = r7;
        r7 = r9.mCurrentMode;
        r7 = java.lang.Integer.valueOf(r7);
        r8 = 2;
        r6[r8] = r7;
        r0 = java.lang.String.format(r0, r5, r6);
        com.android.camera.log.Log.d(r10, r0);
        if (r1 != r3) goto L_0x006a;
    L_0x0068:
        r10 = r3;
        goto L_0x006b;
    L_0x006a:
        r10 = r2;
    L_0x006b:
        if (r4 != r3) goto L_0x006f;
    L_0x006d:
        r2 = r3;
    L_0x006f:
        r0 = r9.mCurrentMode;
        com.android.camera.statistic.ScenarioTrackUtil.trackSwitchCameraStart(r10, r2, r0);
        r10 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r0 = 172; // 0xac float:2.41E-43 double:8.5E-322;
        r10 = r10.getAttachProtocol(r0);
        r10 = (com.android.camera.protocol.ModeProtocol.TopAlert) r10;
        r0 = 4;
        r10.removeExtraMenu(r0);
        r10 = r9.mCurrentMode;
        r0 = 5;
        r1 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        if (r10 == r1) goto L_0x00c7;
    L_0x008b:
        switch(r10) {
            case 168: goto L_0x00aa;
            case 169: goto L_0x00aa;
            case 170: goto L_0x00aa;
            default: goto L_0x008e;
        };
    L_0x008e:
        r10 = r9.getContext();
        r10 = (com.android.camera.Camera) r10;
        r1 = r9.mCurrentMode;
        r1 = com.android.camera.module.loader.StartControl.create(r1);
        r0 = r1.setResetType(r0);
        r0 = r0.setViewConfigType(r8);
        r0 = r0.setNeedBlurAnimation(r3);
        r10.onModeSelected(r0);
        goto L_0x0110;
    L_0x00aa:
        r10 = com.android.camera.data.DataRepository.dataItemGlobal();
        r10.setCurrentMode(r1);
        r10 = r9.getContext();
        r10 = (com.android.camera.Camera) r10;
        r0 = com.android.camera.module.loader.StartControl.create(r1);
        r0 = r0.setNeedBlurAnimation(r3);
        r0 = r0.setViewConfigType(r8);
        r10.onModeSelected(r0);
        goto L_0x0110;
        if (r4 != 0) goto L_0x00ed;
    L_0x00ca:
        r10 = com.android.camera.data.DataRepository.getInstance();
        r10 = r10.backUp();
        r2 = r10.isLastVideoFastMotion();
        if (r2 == 0) goto L_0x00db;
    L_0x00d8:
        r10 = 169; // 0xa9 float:2.37E-43 double:8.35E-322;
        goto L_0x00ee;
    L_0x00db:
        r2 = r10.isLastVideoSlowMotion();
        if (r2 == 0) goto L_0x00e4;
    L_0x00e1:
        r10 = 168; // 0xa8 float:2.35E-43 double:8.3E-322;
        goto L_0x00ee;
    L_0x00e4:
        r10 = r10.isLastVideoHFRMode();
        if (r10 == 0) goto L_0x00ed;
    L_0x00ea:
        r10 = 170; // 0xaa float:2.38E-43 double:8.4E-322;
        goto L_0x00ee;
    L_0x00ed:
        r10 = r1;
    L_0x00ee:
        if (r10 == r1) goto L_0x00f7;
    L_0x00f0:
        r1 = com.android.camera.data.DataRepository.dataItemGlobal();
        r1.setCurrentMode(r10);
    L_0x00f7:
        r1 = r9.getContext();
        r1 = (com.android.camera.Camera) r1;
        r10 = com.android.camera.module.loader.StartControl.create(r10);
        r10 = r10.setResetType(r0);
        r10 = r10.setNeedBlurAnimation(r3);
        r10 = r10.setViewConfigType(r8);
        r1.onModeSelected(r10);
    L_0x0110:
        r10 = com.android.camera.Util.isAccessible();
        if (r10 == 0) goto L_0x012a;
    L_0x0116:
        r10 = r9.mEdgeHorizonScrollView;
        r0 = 2131296506; // 0x7f0900fa float:1.821093E38 double:1.0530003847E-314;
        r0 = r9.getString(r0);
        r10.setContentDescription(r0);
        r10 = r9.mEdgeHorizonScrollView;
        r0 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        r10.sendAccessibilityEvent(r0);
    L_0x012a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.bottom.FragmentBottomAction.changeCamera(android.view.View):void");
    }

    private boolean isFPS960() {
        if (this.mCurrentMode == 172 && DataRepository.dataItemFeature().fu()) {
            return DataRepository.dataItemConfig().getComponentConfigSlowMotion().isSlowMotionFps960();
        }
        return false;
    }

    private void updateBottomInRecording(final boolean z, boolean z2) {
        boolean z3 = true;
        if (z) {
            this.mHandler.removeMessages(1);
            if (this.mThumbnailProgress.getVisibility() != 8) {
                this.mThumbnailProgress.setVisibility(8);
            }
        } else if (isFPS960()) {
            ((VideoModule) ((ActivityBase) getContext()).getCurrentModule()).showFPS960Hint();
        }
        int i = this.mCurrentMode;
        if (i != 162) {
            if (i != 172) {
                if (i != 174) {
                    switch (i) {
                        case 168:
                        case 169:
                        case 170:
                            break;
                        default:
                            this.mVideoPauseSupported = false;
                            this.mVideoCaptureEnable = false;
                            this.mVideoReverseEnable = false;
                            break;
                    }
                }
                this.mVideoCaptureEnable = false;
                this.mVideoPauseSupported = true;
                this.mVideoReverseEnable = true;
            }
            this.mVideoReverseEnable = false;
            this.mVideoCaptureEnable = false;
            this.mVideoPauseSupported = false;
        } else {
            if (!DataRepository.dataItemGlobal().isIntentAction()) {
                this.mVideoCaptureEnable = CameraSettings.isVideoCaptureVisible();
            }
            if (!b.gt() || CameraSettings.isVideoBokehOn()) {
                z3 = false;
            }
            this.mVideoPauseSupported = z3;
            this.mVideoReverseEnable = false;
        }
        if (z) {
            if (this.mVideoCaptureEnable) {
                this.mRecordingSnap.setImageResource(2130837837);
                this.mRecordingSnap.setSoundEffectsEnabled(false);
                this.mRecordingSnap.setVisibility(0);
                ViewCompat.setAlpha(this.mRecordingSnap, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            }
            if (this.mVideoPauseSupported) {
                this.mRecordingPause.setImageResource(2130837835);
                this.mRecordingPause.setSoundEffectsEnabled(false);
                this.mRecordingPause.setVisibility(0);
                ViewCompat.setAlpha(this.mRecordingPause, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
            }
            if (this.mVideoPauseSupported) {
                this.mRecordingReverse.setImageResource(2130837777);
                this.mRecordingReverse.setSoundEffectsEnabled(false);
                this.mRecordingReverse.setVisibility(8);
            }
        }
        if (this.mBottomAnimator != null && this.mBottomAnimator.isRunning()) {
            this.mBottomAnimator.cancel();
        }
        this.mBottomAnimator = ValueAnimator.ofFloat(new float[]{PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f});
        this.mBottomAnimator.setDuration(z2 ? 250 : 0);
        this.mBottomAnimator.setInterpolator(new DecelerateInterpolator() {
            public float getInterpolation(float f) {
                f = super.getInterpolation(f);
                View view = FragmentBottomAction.this.mModeSelectLayout.getView();
                float f2 = z ? 1.0f - f : FragmentBottomAction.this.mModeSelectLayout.getView().getAlpha() == PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO ? f : 1.0f;
                ViewCompat.setAlpha(view, f2);
                if (FragmentBottomAction.this.mCameraPickEnable) {
                    ImageView access$700 = FragmentBottomAction.this.mCameraPicker;
                    f2 = z ? 1.0f - f : FragmentBottomAction.this.mCameraPicker.getAlpha() == PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO ? f : 1.0f;
                    ViewCompat.setAlpha(access$700, f2);
                }
                ViewGroup access$800 = FragmentBottomAction.this.mThumbnailImageLayout;
                f2 = z ? 1.0f - f : FragmentBottomAction.this.mThumbnailImageLayout.getAlpha() == PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO ? f : 1.0f;
                ViewCompat.setAlpha(access$800, f2);
                if (FragmentBottomAction.this.mVideoPauseSupported) {
                    ViewCompat.setAlpha(FragmentBottomAction.this.mRecordingPause, z ? f : 1.0f - f);
                }
                if (FragmentBottomAction.this.mVideoCaptureEnable) {
                    ViewCompat.setAlpha(FragmentBottomAction.this.mRecordingSnap, z ? f : 1.0f - f);
                }
                if (FragmentBottomAction.this.mVideoReverseEnable) {
                    ViewCompat.setAlpha(FragmentBottomAction.this.mRecordingReverse, z ? f : 1.0f - f);
                }
                return f;
            }
        });
        this.mBottomAnimator.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                if (!z) {
                    if (FragmentBottomAction.this.mCameraPickEnable) {
                        FragmentBottomAction.this.mCameraPicker.setVisibility(0);
                    }
                    FragmentBottomAction.this.mThumbnailImage.setVisibility(0);
                    FragmentBottomAction.this.mThumbnailImageLayout.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (FragmentBottomAction.this.canProvide()) {
                    int i = 0;
                    if (FragmentBottomAction.this.mVideoPauseSupported) {
                        FragmentBottomAction.this.mRecordingPause.setVisibility(z ? 0 : 8);
                    }
                    if (FragmentBottomAction.this.mVideoCaptureEnable) {
                        ImageView access$1200 = FragmentBottomAction.this.mRecordingSnap;
                        if (!z) {
                            i = 8;
                        }
                        access$1200.setVisibility(i);
                    }
                    if (FragmentBottomAction.this.mVideoReverseEnable && !z) {
                        FragmentBottomAction.this.mRecordingReverse.setVisibility(8);
                    }
                    if (z) {
                        FragmentBottomAction.this.mThumbnailImageLayout.setVisibility(8);
                        if (FragmentBottomAction.this.mCameraPickEnable) {
                            FragmentBottomAction.this.mCameraPicker.setVisibility(8);
                        }
                        FragmentBottomAction.this.mThumbnailImage.setVisibility(8);
                    }
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.mBottomAnimator.start();
    }

    public void onModeClicked(int i) {
        if (!isShowFilterView() && !isShowLightingView()) {
            changeMode(i, 0);
        }
    }

    /* JADX WARNING: Missing block: B:9:0x0016, code skipped:
            if (r3 != 174) goto L_0x0021;
     */
    /* JADX WARNING: Missing block: B:11:0x001c, code skipped:
            if (com.android.camera.CameraSettings.isLiveModuleClicked() != false) goto L_0x0021;
     */
    /* JADX WARNING: Missing block: B:12:0x001e, code skipped:
            com.android.camera.CameraSettings.setLiveModuleClicked();
     */
    /* JADX WARNING: Missing block: B:14:0x0025, code skipped:
            if (isThumbLoading() == false) goto L_0x0028;
     */
    /* JADX WARNING: Missing block: B:15:0x0027, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:16:0x0028, code skipped:
            r0 = (com.android.camera.protocol.ModeProtocol.CameraAction) com.android.camera.protocol.ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
     */
    /* JADX WARNING: Missing block: B:17:0x0034, code skipped:
            if (r0 == null) goto L_0x003d;
     */
    /* JADX WARNING: Missing block: B:19:0x003a, code skipped:
            if (r0.isDoingAction() == false) goto L_0x003d;
     */
    /* JADX WARNING: Missing block: B:20:0x003c, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:21:0x003d, code skipped:
            r2.mCurrentMode = r3;
            ((com.android.camera.data.data.global.DataItemGlobal) com.android.camera.data.DataRepository.provider().dataGlobal()).setCurrentMode(r3);
            ((com.android.camera.Camera) getContext()).onModeSelected(com.android.camera.module.loader.StartControl.create(r3).setStartDelay(r4).setResetType(4).setViewConfigType(2).setNeedBlurAnimation(true));
     */
    /* JADX WARNING: Missing block: B:22:0x006c, code skipped:
            return;
     */
    public void changeMode(int r3, int r4) {
        /*
        r2 = this;
        r0 = r2.mCurrentMode;
        if (r3 != r0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r2.mCurrentMode;
        r1 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        if (r0 == r1) goto L_0x000f;
    L_0x000b:
        switch(r0) {
            case 168: goto L_0x000f;
            case 169: goto L_0x000f;
            case 170: goto L_0x000f;
            default: goto L_0x000e;
        };
    L_0x000e:
        goto L_0x0014;
    L_0x000f:
        if (r3 == r1) goto L_0x006d;
    L_0x0011:
        switch(r3) {
            case 168: goto L_0x006d;
            case 169: goto L_0x006d;
            case 170: goto L_0x006d;
            default: goto L_0x0014;
        };
    L_0x0014:
        r0 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        if (r3 != r0) goto L_0x0021;
    L_0x0018:
        r0 = com.android.camera.CameraSettings.isLiveModuleClicked();
        if (r0 != 0) goto L_0x0021;
    L_0x001e:
        com.android.camera.CameraSettings.setLiveModuleClicked();
    L_0x0021:
        r0 = r2.isThumbLoading();
        if (r0 == 0) goto L_0x0028;
    L_0x0027:
        return;
    L_0x0028:
        r0 = com.android.camera.protocol.ModeCoordinatorImpl.getInstance();
        r1 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r0 = r0.getAttachProtocol(r1);
        r0 = (com.android.camera.protocol.ModeProtocol.CameraAction) r0;
        if (r0 == 0) goto L_0x003d;
    L_0x0036:
        r0 = r0.isDoingAction();
        if (r0 == 0) goto L_0x003d;
    L_0x003c:
        return;
    L_0x003d:
        r2.mCurrentMode = r3;
        r0 = com.android.camera.data.DataRepository.provider();
        r0 = r0.dataGlobal();
        r0 = (com.android.camera.data.data.global.DataItemGlobal) r0;
        r0.setCurrentMode(r3);
        r0 = r2.getContext();
        r0 = (com.android.camera.Camera) r0;
        r3 = com.android.camera.module.loader.StartControl.create(r3);
        r3 = r3.setStartDelay(r4);
        r4 = 4;
        r3 = r3.setResetType(r4);
        r4 = 2;
        r3 = r3.setViewConfigType(r4);
        r4 = 1;
        r3 = r3.setNeedBlurAnimation(r4);
        r0.onModeSelected(r3);
        return;
    L_0x006d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.bottom.FragmentBottomAction.changeMode(int, int):void");
    }

    public boolean canSwipeChangeMode() {
        return this.mVideoRecordingStarted ^ 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005c A:{SKIP} */
    /* JADX WARNING: Missing block: B:7:0x001b, code skipped:
            if (r8 == 5) goto L_0x0021;
     */
    /* JADX WARNING: Missing block: B:10:0x0023, code skipped:
            if (r8 == 5) goto L_0x0019;
     */
    public void selectMode(int r8, int r9) {
        /*
        r7 = this;
        r0 = -1;
        if (r8 != r0) goto L_0x0004;
    L_0x0003:
        return;
    L_0x0004:
        r0 = r7.getContext();
        r0 = com.android.camera.Util.isLayoutRTL(r0);
        r1 = 5;
        r2 = 3;
        r3 = 8388611; // 0x800003 float:1.1754948E-38 double:4.1445245E-317;
        r4 = 8388613; // 0x800005 float:1.175495E-38 double:4.1445255E-317;
        if (r0 == 0) goto L_0x001e;
    L_0x0016:
        if (r8 != r2) goto L_0x001b;
    L_0x0019:
        r8 = r4;
        goto L_0x0026;
    L_0x001b:
        if (r8 != r1) goto L_0x0026;
    L_0x001d:
        goto L_0x0021;
    L_0x001e:
        if (r8 != r2) goto L_0x0023;
    L_0x0021:
        r8 = r3;
        goto L_0x0026;
    L_0x0023:
        if (r8 != r1) goto L_0x0026;
    L_0x0025:
        goto L_0x0019;
    L_0x0026:
        r0 = r7.mCurrentMode;
        r1 = r7.mCurrentMode;
        r2 = 169; // 0xa9 float:2.37E-43 double:8.35E-322;
        if (r1 == r2) goto L_0x003a;
    L_0x002e:
        r1 = r7.mCurrentMode;
        r2 = 168; // 0xa8 float:2.35E-43 double:8.3E-322;
        if (r1 == r2) goto L_0x003a;
    L_0x0034:
        r1 = r7.mCurrentMode;
        r2 = 170; // 0xaa float:2.38E-43 double:8.4E-322;
        if (r1 != r2) goto L_0x003c;
    L_0x003a:
        r0 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        r1 = r7.mComponentModuleList;
        r1 = r1.getItems();
        r1 = r1.size();
        r2 = 0;
        r5 = r2;
    L_0x0049:
        if (r5 >= r1) goto L_0x005a;
    L_0x004b:
        r6 = r7.mComponentModuleList;
        r6 = r6.getMode(r5);
        if (r6 != r0) goto L_0x0057;
        r2 = r5;
        goto L_0x005a;
    L_0x0057:
        r5 = r5 + 1;
        goto L_0x0049;
    L_0x005a:
        if (r8 == r3) goto L_0x0066;
    L_0x005c:
        if (r8 == r4) goto L_0x005f;
    L_0x005e:
        goto L_0x006a;
    L_0x005f:
        r1 = r1 + -1;
        if (r2 >= r1) goto L_0x006a;
    L_0x0063:
        r2 = r2 + 1;
        goto L_0x006a;
    L_0x0066:
        if (r2 <= 0) goto L_0x006a;
    L_0x0068:
        r2 = r2 + -1;
    L_0x006a:
        r8 = r7.mComponentModuleList;
        r8 = r8.getMode(r2);
        r7.changeMode(r8, r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.bottom.FragmentBottomAction.selectMode(int, int):void");
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968587;
    }

    public int getFragmentInto() {
        return 241;
    }

    public void provideAnimateElement(int r6, java.util.List<io.reactivex.Completable> r7, int r8) {
        /*
        r5 = this;
        r0 = r5.mCurrentMode;
        r1 = 0;
        r2 = 1;
        r3 = 3;
        if (r8 != r3) goto L_0x0009;
    L_0x0007:
        r3 = r2;
        goto L_0x000b;
        r3 = r1;
    L_0x000b:
        if (r3 != 0) goto L_0x000f;
    L_0x000d:
        if (r0 == r6) goto L_0x0027;
    L_0x000f:
        r4 = r5.mReverseDialog;
        if (r4 == 0) goto L_0x0018;
    L_0x0013:
        r4 = r5.mReverseDialog;
        r4.dismiss();
    L_0x0018:
        r4 = r5.mIsShowFilter;
        if (r4 == 0) goto L_0x0020;
    L_0x001c:
        r5.showOrHideFilterView();
        goto L_0x0027;
    L_0x0020:
        r4 = r5.mIsShowLighting;
        if (r4 == 0) goto L_0x0027;
    L_0x0024:
        r5.showOrHideLightingView();
    L_0x0027:
        r4 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        if (r0 != r4) goto L_0x0035;
    L_0x002b:
        r0 = r5.mVideoRecordingStarted;
        if (r0 == 0) goto L_0x0035;
    L_0x002f:
        if (r3 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r5.processingFinish();
    L_0x0035:
        super.provideAnimateElement(r6, r7, r8);
        r8 = r5.mFragmentFilter;
        if (r8 == 0) goto L_0x0041;
    L_0x003c:
        r8 = r5.mFragmentFilter;
        r8.isShowAnimation(r7);
    L_0x0041:
        r8 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        if (r6 == r8) goto L_0x0046;
    L_0x0045:
        goto L_0x0052;
    L_0x0046:
        r8 = r5.mModeSelectLayout;
        r8 = r8.getView();
        r0 = 2131427390; // 0x7f0b003e float:1.8476395E38 double:1.05306505E-314;
        r8.setBackgroundResource(r0);
    L_0x0052:
        r8 = r5.mShutterButton;
        if (r7 == 0) goto L_0x0058;
    L_0x0056:
        r0 = r2;
        goto L_0x0059;
    L_0x0058:
        r0 = r1;
    L_0x0059:
        r3 = r5.isFPS960();
        r8.setParameters(r6, r0, r3);
        r8 = r5.mModeSelectView;
        r8 = r8.getVisibility();
        if (r8 == 0) goto L_0x006d;
    L_0x0068:
        r8 = r5.mModeSelectView;
        r8.setVisibility(r1);
    L_0x006d:
        r8 = r5.mModeSelectView;
        r8.judgePosition(r6, r7);
        r8 = -1;
        switch(r6) {
            case 166: goto L_0x008a;
            case 167: goto L_0x008a;
            case 168: goto L_0x0076;
            case 169: goto L_0x0076;
            case 170: goto L_0x0076;
            case 171: goto L_0x007c;
            case 172: goto L_0x008a;
            case 173: goto L_0x008a;
            default: goto L_0x0076;
        };
        r5.mCameraPickEnable = r2;
    L_0x007a:
        r8 = r2;
        goto L_0x008d;
    L_0x007c:
        r6 = com.mi.config.b.hz();
        if (r6 == 0) goto L_0x0086;
        r5.mCameraPickEnable = r2;
        goto L_0x007a;
        r5.mCameraPickEnable = r1;
        goto L_0x008d;
        r5.mCameraPickEnable = r1;
    L_0x008d:
        r6 = r5.mCameraPicker;
        r6 = r6.getTag();
        if (r6 == 0) goto L_0x00a4;
    L_0x0095:
        r6 = r5.mCameraPicker;
        r6 = r6.getTag();
        r6 = (java.lang.Integer) r6;
        r6 = r6.intValue();
        if (r6 != r8) goto L_0x00a4;
    L_0x00a3:
        return;
    L_0x00a4:
        r6 = r5.mCameraPicker;
        r0 = java.lang.Integer.valueOf(r8);
        r6.setTag(r0);
        r6 = r5.mCameraPicker;
        r5.animateViews(r8, r7, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.bottom.FragmentBottomAction.provideAnimateElement(int, java.util.List, int):void");
    }

    private void animateViews(int i, List<Completable> list, View view) {
        if (i == 1) {
            if (list == null) {
                AlphaInOnSubscribe.directSetResult(view);
            } else {
                list.add(Completable.create(new AlphaInOnSubscribe(view)));
            }
        } else if (list == null) {
            AlphaOutOnSubscribe.directSetResult(view);
        } else {
            list.add(Completable.create(new AlphaOutOnSubscribe(view)));
        }
    }

    /* Access modifiers changed, original: protected */
    public Animation provideEnterAnimation(int i) {
        if (i == 240) {
            return null;
        }
        Animation wrapperAnimation = FragmentAnimationFactory.wrapperAnimation(161);
        wrapperAnimation.setStartOffset(150);
        return wrapperAnimation;
    }

    /* Access modifiers changed, original: protected */
    public Animation provideExitAnimation() {
        return FragmentAnimationFactory.wrapperAnimation(162);
    }

    public void onAngleChanged(float f) {
    }

    public void onBeautyRecordingStart() {
        ViewCompat.animate(this.mModeSelectView).alpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).start();
    }

    public void onBeautyRecordingStop() {
        ViewCompat.animate(this.mModeSelectView).alpha(1.0f).start();
    }

    public void showOrHideBottomViewWithAnim(boolean z) {
        ViewPropertyAnimatorCompat duration = ViewCompat.animate(this.mModeSelectLayout.getView()).setInterpolator(new CubicEaseInInterpolator()).setDuration(300);
        float f = PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO;
        duration.alpha(z ? 1.0f : PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setListener(null).start();
        ViewCompat.animate(this.mThumbnailImage).setInterpolator(new CubicEaseInInterpolator()).setDuration(300).alpha(z ? 1.0f : PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO).setListener(null).start();
        duration = ViewCompat.animate(this.mCameraPicker).setInterpolator(new CubicEaseInInterpolator()).setDuration(300);
        if (z) {
            f = 1.0f;
        }
        duration.alpha(f).setListener(null).start();
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        list.add(this.mThumbnailImageLayout);
        list.add(this.mShutterButton);
        list.add(this.mCameraPicker);
        list.add(this.mPostProcess);
        list.add(this.mRecordingPause);
        list.add(this.mBottomRecordingCameraPicker);
        list.add(this.mRecordingReverse);
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        boolean isIntentAction = DataRepository.dataItemGlobal().isIntentAction();
        if (isIntentAction != this.mIsIntentAction) {
            this.mIsIntentAction = isIntentAction;
            this.mModeSelectView.init(this.mComponentModuleList, this.mCurrentMode);
            initThumbLayoutByIntent();
        }
        this.mInLoading = false;
        if (this.mFragmentLighting != null && this.mFragmentLighting.isAdded()) {
            this.mFragmentLighting.reInit();
        }
        if (!Util.isAccessible()) {
            return;
        }
        if (162 == this.mCurrentMode) {
            this.mShutterButton.setContentDescription(getString(2131296463));
        } else {
            this.mShutterButton.setContentDescription(getString(2131296462));
        }
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        if (this.mFragmentFilter == null) {
            this.mFragmentFilter = new FragmentFilter();
            FragmentUtils.addFragmentWithTag(getChildFragmentManager(), 2131558433, this.mFragmentFilter, this.mFragmentFilter.getFragmentTag());
            this.mFragmentLighting = new FragmentLighting();
            FragmentUtils.addFragmentWithTag(getChildFragmentManager(), 2131558434, this.mFragmentLighting, this.mFragmentLighting.getFragmentTag());
        } else {
            if (this.mFragmentFilter.isAdded()) {
                this.mFragmentFilter.updateFilterData();
            }
            if (this.mFragmentLighting.isAdded()) {
                this.mFragmentLighting.reInitAdapterBgMode(true);
            }
        }
        this.mModeSelectLayout.initBeautyMenuView();
        adjustViewBackground(this.mModeSelectLayout.getView(), this.mCurrentMode);
    }

    public boolean canSnap() {
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        return (cameraAction == null || cameraAction.isDoingAction()) ? false : true;
    }

    public void onSnapPrepare() {
        if (isEnableClick()) {
            CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
            if (cameraAction != null) {
                Log.d(TAG, "onSnapPrepare");
                cameraAction.onShutterButtonFocus(true, 2);
            }
        }
    }

    public void onTrackSnapMissTaken(long j) {
        if (isEnableClick()) {
            CameraActionTrack cameraActionTrack = (CameraActionTrack) ModeCoordinatorImpl.getInstance().getAttachProtocol(186);
            if (cameraActionTrack != null) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onTrackSnapMissTaken ");
                stringBuilder.append(j);
                stringBuilder.append("ms");
                Log.d(str, stringBuilder.toString());
                cameraActionTrack.onTrackShutterButtonMissTaken(j);
            }
        }
    }

    public void onTrackSnapTaken(long j) {
        if (isEnableClick()) {
            CameraActionTrack cameraActionTrack = (CameraActionTrack) ModeCoordinatorImpl.getInstance().getAttachProtocol(186);
            if (cameraActionTrack != null) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onTrackSnapTaken ");
                stringBuilder.append(j);
                stringBuilder.append("ms");
                Log.d(str, stringBuilder.toString());
                cameraActionTrack.onTrackShutterButtonTaken(j);
            }
        }
    }

    public void onSnapClick() {
        if (isEnableClick() && getContext() != null) {
            Camera camera = (Camera) getContext();
            Module currentModule = camera.getCurrentModule();
            if (currentModule != null && currentModule.isIgnoreTouchEvent()) {
                Log.w(TAG, "onSnapClick: ignore onSnapClick event, because module isn't ready");
            } else if (CameraSettings.isFrontCamera() && camera.isScreenSlideOff()) {
                Log.w(TAG, "onSnapClick: ignore onSnapClick event, because screen slide is off");
            } else {
                CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
                if (cameraAction != null) {
                    Log.d(TAG, "onSnapClick");
                    switch (this.mCurrentMode) {
                        case 161:
                        case 162:
                        case 168:
                        case 169:
                        case 170:
                        case 172:
                        case 174:
                            break;
                        case 166:
                            if (cameraAction.isDoingAction()) {
                                return;
                            }
                            break;
                        default:
                            if (!cameraAction.isDoingAction()) {
                                cameraAction.onShutterButtonClick(10);
                                break;
                            }
                            return;
                    }
                    if (!this.mVideoRecordingStarted) {
                        this.mVideoRecordingStarted = true;
                    }
                    cameraAction.onShutterButtonClick(10);
                    if (Util.isAccessible()) {
                        if (162 != this.mCurrentMode) {
                            this.mEdgeHorizonScrollView.setContentDescription(getString(2131296507));
                        } else if (((Camera) getActivity()).isVideoRecording()) {
                            this.mEdgeHorizonScrollView.setContentDescription(getString(2131296508));
                        } else {
                            this.mEdgeHorizonScrollView.setContentDescription(getString(2131296509));
                        }
                        this.mEdgeHorizonScrollView.sendAccessibilityEvent(32768);
                    }
                }
            }
        }
    }

    public void onSnapLongPress() {
        if (isEnableClick()) {
            CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
            if (cameraAction != null && !cameraAction.isDoingAction()) {
                if (getContext() != null) {
                    Camera camera = (Camera) getContext();
                    if (CameraSettings.isFrontCamera() && camera.isScreenSlideOff()) {
                        return;
                    }
                }
                Log.d(TAG, "onSnapLongPress");
                if (cameraAction.onShutterButtonLongClick()) {
                    this.mLongPressBurst = true;
                }
            }
        }
    }

    public void onSnapLongPressCancelOut() {
        if (isEnableClick()) {
            CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
            if (cameraAction != null) {
                Log.d(TAG, "onSnapLongPressCancelOut");
                cameraAction.onShutterButtonLongClickCancel(false);
                if (this.mLongPressBurst) {
                    this.mLongPressBurst = false;
                }
            }
        }
    }

    public void onSnapLongPressCancelIn() {
        if (isEnableClick()) {
            CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
            if (cameraAction != null) {
                Log.d(TAG, "onSnapLongPressCancelIn");
                cameraAction.onShutterButtonLongClickCancel(true);
                int i = this.mCurrentMode;
                if (i != 163) {
                    if (i == 165 || i == 167 || i == 171) {
                        onSnapClick();
                    }
                } else if (this.mLongPressBurst) {
                    this.mLongPressBurst = false;
                }
            }
        }
    }

    public boolean onBackEvent(int i) {
        boolean z = i == 3 && this.mCurrentMode == 161 && this.mIsShowFilter;
        boolean z2 = i == 1 && (this.mIsShowFilter || this.mIsShowLighting);
        boolean z3 = i == 2 && this.mIsShowFilter && Util.UI_DEBUG();
        if (!z && !z2 && !z3) {
            return false;
        }
        hideExtra();
        if (Util.isAccessible()) {
            ((TopAlert) ModeCoordinatorImpl.getInstance().getAttachProtocol(172)).updateContentDescription();
        }
        return true;
    }

    public void hideExtra() {
        ConfigChanges configChanges;
        if (this.mIsShowFilter) {
            configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                configChanges.showOrHideFilter();
            }
        } else if (this.mIsShowLighting) {
            configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                configChanges.showOrHideLighting(false);
            }
        }
    }

    public void onSwitchCameraActionMenu(int i) {
        if (this.mVideoRecordingStarted && this.mCurrentMode == 174 && this.mBottomRecordingTime.getVisibility() != 0) {
            this.mBottomRecordingTime.setVisibility(0);
        }
        this.mModeSelectLayout.switchMenuMode(i, true);
    }

    public void onSwitchLiveActionMenu(int i) {
        if (this.mBottomRecordingTime.getVisibility() == 0) {
            this.mBottomRecordingTime.setVisibility(8);
        }
        this.mCurrentLiveActionMenuType = i;
        this.mModeSelectLayout.switchMenuMode(2, i, true);
    }

    public void onSwitchBeautyActionMenu(int i) {
        if (this.mBottomRecordingTime.getVisibility() == 0) {
            this.mBottomRecordingTime.setVisibility(8);
        }
        this.mCurrentBeautyActionMenuType = i;
        this.mModeSelectLayout.switchMenuMode(1, i, true);
    }

    public int getBeautyActionMenuType() {
        return this.mCurrentBeautyActionMenuType;
    }

    public SparseArray<MenuItem> getMenuData() {
        return this.mModeSelectLayout.getMenuData();
    }

    public void onBottomMenuAnimate(int i, int i2) {
        this.mModeSelectLayout.bottomMenuAnimate(i, i2);
    }
}
