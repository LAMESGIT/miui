package com.android.camera.fragment.fullscreen;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.camera.ActivityBase;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.animation.type.SlideOutOnSubscribe;
import com.android.camera.constant.GlobalConstant;
import com.android.camera.constant.ShareConstant;
import com.android.camera.data.DataRepository;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.log.Log;
import com.android.camera.module.LiveModule;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.FullScreenProtocol;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.LiveConfigChanges;
import com.android.camera.protocol.ModeProtocol.LiveVideoEditor;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.statistic.CameraStat;
import com.android.camera.statistic.CameraStatUtil;
import com.android.camera.ui.CameraSnapView;
import com.android.camera.ui.drawable.PanoramaArrowAnimateDrawable;
import com.ss.android.vesdk.VECommonCallback;
import com.ss.android.vesdk.VECommonCallbackInfo;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.List;
import miui.view.animation.CubicEaseInInterpolator;
import miui.view.animation.QuarticEaseInInterpolator;

public class FragmentFullScreen extends BaseFragment implements OnClickListener, FullScreenProtocol, HandleBackTrace, Consumer<Pair<String, String>> {
    public static final int FRAGMENT_INFO = 4086;
    private static final String TAG = "FullScreen";
    private ViewGroup mBottomActionView;
    private ViewGroup mBottomLayout;
    private CameraSnapView mCameraSnapView;
    private VECommonCallback mCombineListener;
    private ProgressBar mCombineProgress;
    private boolean mCombineReady;
    private Disposable mConcatDisposable;
    private ProgressBar mConcatProgress;
    private boolean mConcatReady;
    private VECommonCallback mErrorListener;
    private AlertDialog mExitDialog;
    private Handler mHandler;
    private boolean mIsIntentAction;
    private boolean mIsPlaying;
    private View mLiveViewLayout;
    private ViewStub mLiveViewStub;
    private boolean mPaused;
    private boolean mPendingSaveFinish;
    private boolean mPendingShare;
    private ImageView mPreviewBack;
    private ImageView mPreviewCombine;
    private FrameLayout mPreviewLayout;
    private ImageView mPreviewShare;
    private ImageView mPreviewStart;
    private TextureView mPreviewTextureView;
    private ContentValues mSaveContentValues;
    private Uri mSavedUri;
    private View mScreenLightIndicator;
    private View mScreenLightRootView;
    private ShareAdapter mShareAdapter;
    private View mShareCancel;
    private ViewGroup mShareLayout;
    private ProgressBar mShareProgress;
    private RecyclerView mShareRecyclerView;
    private TextView mTimeView;
    private ViewGroup mTopLayout;

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        this.mScreenLightRootView = view.findViewById(2131558487);
        this.mScreenLightIndicator = view.findViewById(2131558488);
        this.mLiveViewStub = (ViewStub) view.findViewById(2131558486);
    }

    /* Access modifiers changed, original: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        modeCoordinator.attachProtocol(196, this);
        registerBackStack(modeCoordinator, this);
    }

    /* Access modifiers changed, original: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        modeCoordinator.detachProtocol(196, this);
        unRegisterBackStack(modeCoordinator, this);
    }

    public void showScreenLight() {
        if (this.mScreenLightIndicator.getVisibility() != 0) {
            this.mScreenLightIndicator.setVisibility(0);
            Completable.create(new AlphaInOnSubscribe(this.mScreenLightRootView).setDurationTime(100)).subscribe();
        }
    }

    public void hideScreenLight() {
        if (this.mScreenLightIndicator.getVisibility() != 8) {
            Completable.create(new AlphaOutOnSubscribe(this.mScreenLightRootView).setDurationTime(200)).subscribe(new Action() {
                public void run() throws Exception {
                    FragmentFullScreen.this.mScreenLightIndicator.setVisibility(8);
                    FragmentFullScreen.this.mScreenLightRootView.setVisibility(8);
                }
            });
        }
    }

    public void setScreenLightColor(int i) {
        this.mScreenLightRootView.setBackgroundColor(i);
    }

    /* Access modifiers changed, original: protected */
    public Animation provideEnterAnimation(int i) {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public Animation provideExitAnimation() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        return 2130968602;
    }

    public int getFragmentInto() {
        return 4086;
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        super.provideAnimateElement(i, list, i2);
        if (this.mLiveViewLayout != null && this.mLiveViewLayout.getVisibility() == 0) {
            if (i2 == 3) {
                if (this.mExitDialog != null) {
                    this.mExitDialog.dismiss();
                }
                this.mLiveViewLayout.setVisibility(8);
                return;
            }
            this.mConcatReady = false;
        }
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        if (this.mLiveViewLayout == null || this.mLiveViewLayout.getVisibility() != 0 || !this.mCombineReady) {
            return;
        }
        if (this.mPendingSaveFinish) {
            onCombineSuccess();
        } else if (this.mPendingShare) {
            this.mPendingShare = false;
            this.mPreviewShare.setVisibility(0);
            this.mShareProgress.setVisibility(8);
        }
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        if (this.mLiveViewLayout != null && this.mLiveViewLayout.getVisibility() == 0) {
            list.add(this.mPreviewStart);
            list.add(this.mCameraSnapView);
            list.add(this.mPreviewCombine);
            list.add(this.mPreviewBack);
            list.add(this.mPreviewShare);
        }
    }

    public void startLiveRecordPreview(ContentValues contentValues) {
        this.mSavedUri = null;
        this.mSaveContentValues = contentValues;
        initHandler();
        if (this.mLiveViewLayout == null) {
            this.mLiveViewLayout = this.mLiveViewStub.inflate();
            initLiveView(this.mLiveViewLayout);
        }
        this.mIsIntentAction = DataRepository.dataItemGlobal().isIntentAction();
        this.mPreviewLayout.setVisibility(8);
        this.mPreviewLayout.removeAllViews();
        this.mLiveViewLayout.setVisibility(0);
        this.mCombineProgress.setVisibility(8);
        this.mShareProgress.setVisibility(8);
        this.mShareLayout.setVisibility(4);
        ViewCompat.setRotation(this.mPreviewStart, (float) this.mDegree);
        ViewCompat.setRotation(this.mCameraSnapView, (float) this.mDegree);
        ViewCompat.setRotation(this.mPreviewCombine, (float) this.mDegree);
        ViewCompat.setRotation(this.mPreviewBack, (float) this.mDegree);
        ViewCompat.setRotation(this.mPreviewShare, (float) this.mDegree);
        Completable.create(new AlphaInOnSubscribe(this.mCameraSnapView)).subscribe();
        Completable.create(new AlphaInOnSubscribe(this.mPreviewCombine)).subscribe();
        Completable.create(new AlphaInOnSubscribe(this.mPreviewBack)).subscribe();
        Completable.create(new AlphaInOnSubscribe(this.mPreviewStart)).subscribe();
        if (((ActivityBase) getContext()).startFromKeyguard()) {
            this.mPreviewShare.setVisibility(8);
        } else {
            Completable.create(new AlphaInOnSubscribe(this.mPreviewShare)).subscribe();
        }
        this.mTimeView.setText(((LiveConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(201)).getTimeValue());
        this.mTimeView.setVisibility(0);
        this.mPreviewStart.setVisibility(8);
        this.mConcatProgress.setVisibility(0);
        this.mConcatReady = false;
        this.mCombineReady = false;
        this.mPendingShare = false;
        this.mPendingSaveFinish = false;
        this.mPendingShare = false;
        startConcatVideoIfNeed();
    }

    private void initHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler() {
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                }
            };
        }
    }

    private void startConcatVideoIfNeed() {
        if (this.mConcatDisposable == null || this.mConcatDisposable.isDisposed()) {
            Log.d(TAG, "startConcatVideo");
            this.mConcatReady = false;
            this.mIsPlaying = false;
            this.mConcatDisposable = Single.create(new SingleOnSubscribe<Pair<String, String>>() {
                public void subscribe(SingleEmitter<Pair<String, String>> singleEmitter) throws Exception {
                    LiveConfigChanges liveConfigChanges = (LiveConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(201);
                    if (liveConfigChanges.onRecordConcat()) {
                        singleEmitter.onSuccess(liveConfigChanges.getConcatResult());
                        return;
                    }
                    Log.d(FragmentFullScreen.TAG, "concat error");
                    singleEmitter.onSuccess(new Pair("", ""));
                }
            }).subscribeOn(GlobalConstant.sCameraSetupScheduler).observeOn(AndroidSchedulers.mainThread()).subscribe((Consumer) this);
        }
    }

    public void accept(Pair<String, String> pair) throws Exception {
        if (canProvide()) {
            String str = (String) pair.first;
            String str2 = (String) pair.second;
            if (str.isEmpty() && str2.isEmpty()) {
                onCombineError();
                return;
            }
            this.mConcatReady = true;
            String str3 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("concat: ");
            stringBuilder.append(str);
            stringBuilder.append(" | ");
            stringBuilder.append(str2);
            Log.d(str3, stringBuilder.toString());
            LiveVideoEditor liveVideoEditor = (LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209);
            initLiveListener();
            this.mPreviewTextureView = new TextureView(getContext());
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.topMargin = Util.getDisplayRect(getActivity()).top;
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(2131361916) + Util.sNavigationBarHeight;
            this.mPreviewLayout.addView(this.mPreviewTextureView, layoutParams);
            if (liveVideoEditor.init(this.mPreviewTextureView, str, str2, this.mCombineListener, this.mErrorListener)) {
                if (this.mConcatProgress.getVisibility() == 0) {
                    Log.d(TAG, "concat finish and start preview");
                    this.mConcatProgress.setVisibility(8);
                    startPlay();
                } else if (this.mCombineProgress.getVisibility() == 0) {
                    Log.d(TAG, "concat finish and start save");
                    this.mPreviewCombine.setVisibility(8);
                    setProgressBarVisible(0);
                    startCombine();
                } else if (this.mShareProgress.getVisibility() == 0) {
                    Log.d(TAG, "concat finish and pending share");
                    startCombine();
                }
                return;
            }
            onCombineError();
        }
    }

    public void onPause() {
        super.onPause();
        this.mPaused = true;
        pausePlay();
    }

    public void onResume() {
        super.onResume();
        this.mPaused = false;
    }

    private void showExitConfirm() {
        CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_PLAY_EXIT);
        Builder builder = new Builder(getContext());
        builder.setMessage(2131296882);
        builder.setCancelable(false);
        builder.setPositiveButton(2131296883, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_PLAY_EXIT_CONFIRM);
                FragmentFullScreen.this.mExitDialog = null;
                FragmentFullScreen.this.quitLiveRecordPreview(false);
            }
        });
        builder.setNegativeButton(2131296692, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentFullScreen.this.mExitDialog = null;
            }
        });
        this.mExitDialog = builder.show();
    }

    public void quitLiveRecordPreview(boolean z) {
        CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
        if (cameraAction != null) {
            this.mLiveViewLayout.setVisibility(8);
            this.mPendingSaveFinish = false;
            if (z) {
                cameraAction.onReviewDoneClicked();
            } else {
                cameraAction.onReviewCancelClicked();
            }
            LiveVideoEditor liveVideoEditor = (LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209);
            if (liveVideoEditor != null) {
                liveVideoEditor.onDestory();
            }
            this.mCombineListener = null;
            this.mErrorListener = null;
        }
    }

    private void initLiveView(View view) {
        this.mTopLayout = (ViewGroup) view.findViewById(2131558495);
        if (Util.isNotchDevice) {
            ((MarginLayoutParams) this.mTopLayout.getLayoutParams()).topMargin = Util.sStatusBarHeight;
        }
        this.mPreviewLayout = (FrameLayout) view.findViewById(2131558496);
        this.mConcatProgress = (ProgressBar) view.findViewById(2131558498);
        this.mCombineProgress = (ProgressBar) view.findViewById(2131558505);
        this.mShareProgress = (ProgressBar) view.findViewById(2131558507);
        this.mTimeView = (TextView) view.findViewById(2131558500);
        this.mCameraSnapView = (CameraSnapView) view.findViewById(2131558503);
        this.mCameraSnapView.setParameters(this.mCurrentMode, false, false);
        this.mCameraSnapView.hideRoundPaintItem();
        this.mCameraSnapView.setSnapClickEnable(false);
        this.mPreviewCombine = (ImageView) view.findViewById(2131558504);
        this.mPreviewBack = (ImageView) view.findViewById(2131558502);
        this.mPreviewShare = (ImageView) view.findViewById(2131558506);
        this.mPreviewStart = (ImageView) view.findViewById(2131558497);
        this.mShareLayout = (ViewGroup) view.findViewById(2131558508);
        this.mShareRecyclerView = (RecyclerView) this.mShareLayout.findViewById(2131558509);
        this.mShareCancel = this.mShareLayout.findViewById(2131558510);
        this.mShareCancel.setOnClickListener(this);
        this.mPreviewLayout.setOnClickListener(this);
        this.mCameraSnapView.setOnClickListener(this);
        this.mPreviewCombine.setOnClickListener(this);
        this.mPreviewBack.setOnClickListener(this);
        this.mPreviewShare.setOnClickListener(this);
        this.mPreviewStart.setOnClickListener(this);
        this.mBottomActionView = (FrameLayout) view.findViewById(2131558499);
        ((MarginLayoutParams) this.mBottomActionView.getLayoutParams()).height = Util.getBottomHeight(getResources());
        this.mBottomActionView.setOnClickListener(this);
        this.mBottomLayout = (RelativeLayout) view.findViewById(2131558501);
        ((MarginLayoutParams) this.mBottomLayout.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(2131361916) + Util.sNavigationBarHeight;
        this.mBottomActionView.setBackgroundResource(2131427401);
    }

    private void animateIn() {
    }

    private void initLiveListener() {
        this.mCombineListener = new VECommonCallback() {
            public void onCallback(int i, int i2, float f, String str) {
                if (i == 4098) {
                    Log.d(FragmentFullScreen.TAG, "play finished and loop");
                } else if (i == 4101) {
                } else {
                    if (i == 4103) {
                        FragmentFullScreen.this.onCombineSuccess();
                    } else if (i == VECommonCallbackInfo.TE_INFO_COMPILE_PROGRESS) {
                    } else {
                        if (i != 4112) {
                            String str2 = FragmentFullScreen.TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("CombineCallback: ");
                            stringBuilder.append(i);
                            Log.d(str2, stringBuilder.toString());
                            return;
                        }
                        Log.d(FragmentFullScreen.TAG, "CombineStart");
                    }
                }
            }
        };
        this.mErrorListener = new VECommonCallback() {
            public void onCallback(int i, int i2, float f, String str) {
                String str2 = FragmentFullScreen.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("CombineError: ");
                stringBuilder.append(i);
                stringBuilder.append(" | ");
                stringBuilder.append(i2);
                stringBuilder.append(" | ");
                stringBuilder.append(f);
                stringBuilder.append(" | ");
                stringBuilder.append(str);
                Log.e(str2, stringBuilder.toString());
                FragmentFullScreen.this.onCombineError();
            }
        };
    }

    private void onCombineError() {
        this.mHandler.post(new Runnable() {
            public void run() {
                FragmentFullScreen.this.quitLiveRecordPreview(false);
            }
        });
    }

    private void onCombineSuccess() {
        Log.d(TAG, "combineSuccess");
        this.mCombineReady = true;
        if (!this.mPaused) {
            if (this.mPendingShare) {
                Log.d(TAG, "combineSuccess and share");
                ((LiveModule) ((ActivityBase) getContext()).getCurrentModule()).startSaveToLocal();
                return;
            }
            Log.d(TAG, "combineSuccess and finish");
            this.mHandler.post(new Runnable() {
                public void run() {
                    FragmentFullScreen.this.quitLiveRecordPreview(true);
                }
            });
        }
    }

    private void onPlayCompleted() {
        this.mHandler.post(new Runnable() {
            public void run() {
                FragmentFullScreen.this.mIsPlaying = false;
                FragmentFullScreen.this.mPreviewStart.setVisibility(0);
            }
        });
    }

    public void onClick(View view) {
        if (this.mConcatProgress.getVisibility() != 0 && this.mCombineProgress.getVisibility() != 0 && this.mShareProgress.getVisibility() != 0) {
            switch (view.getId()) {
                case 2131558496:
                    pausePlay();
                    break;
                case 2131558497:
                    if (!this.mConcatReady) {
                        Log.d(TAG, "concat not finished, show play progress");
                        this.mPreviewStart.setVisibility(8);
                        this.mConcatProgress.setVisibility(0);
                        startConcatVideoIfNeed();
                        break;
                    }
                    hideShareSheet();
                    startPlay();
                    break;
                case 2131558502:
                    showExitConfirm();
                    break;
                case 2131558503:
                case 2131558504:
                    CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_PLAY_SAVE);
                    if (this.mSavedUri == null) {
                        if (!this.mConcatReady) {
                            Log.d(TAG, "concat not finished, show save progress and wait to save");
                            this.mPendingSaveFinish = true;
                            this.mPreviewCombine.setVisibility(8);
                            setProgressBarVisible(0);
                            startConcatVideoIfNeed();
                            break;
                        }
                        pausePlay();
                        this.mPreviewStart.setVisibility(8);
                        this.mPreviewCombine.setVisibility(8);
                        setProgressBarVisible(0);
                        this.mPendingSaveFinish = true;
                        startCombine();
                        break;
                    }
                    onCombineSuccess();
                    break;
                case 2131558506:
                    CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_PLAY_SHARE);
                    if (!checkAndShare()) {
                        this.mPendingShare = true;
                        this.mPreviewShare.setVisibility(8);
                        this.mShareProgress.setVisibility(0);
                        if (!this.mConcatReady) {
                            Log.d(TAG, "concat not finished, show share progress and wait to share");
                            startConcatVideoIfNeed();
                            break;
                        }
                        pausePlay();
                        startCombine();
                        break;
                    }
                    break;
                case 2131558510:
                    hideShareSheet();
                    break;
                case 2131558511:
                    ShareInfo shareInfo = (ShareInfo) view.getTag();
                    hideShareSheet();
                    if (!shareInfo.className.equals("more")) {
                        startShare(shareInfo.packageName, shareInfo.className);
                        break;
                    } else {
                        shareMore();
                        break;
                    }
            }
        }
    }

    private void startPlay() {
        if (!this.mPaused && !this.mIsPlaying) {
            this.mIsPlaying = true;
            this.mPreviewStart.setVisibility(8);
            this.mPreviewLayout.setVisibility(0);
            ((LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209)).startPlay();
        }
    }

    private void pausePlay() {
        if (this.mIsPlaying) {
            this.mIsPlaying = false;
            this.mPreviewStart.setVisibility(0);
            LiveVideoEditor liveVideoEditor = (LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209);
            if (liveVideoEditor != null) {
                liveVideoEditor.pausePlay();
            }
        }
    }

    private void resumePlay() {
        if (!this.mIsPlaying) {
            this.mIsPlaying = true;
            this.mPreviewStart.setVisibility(8);
            LiveVideoEditor liveVideoEditor = (LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209);
            if (liveVideoEditor != null) {
                liveVideoEditor.resumePlay();
            }
        }
    }

    private void startCombine() {
        this.mCombineReady = false;
        ((LiveVideoEditor) ModeCoordinatorImpl.getInstance().getAttachProtocol(209)).combineVideoAudio(this.mSaveContentValues.getAsString("_data"), this.mCombineListener, this.mErrorListener);
    }

    private void setProgressBarVisible(int i) {
        if (this.mCombineProgress.getVisibility() != i) {
            ValueAnimator ofFloat;
            if (i == 0) {
                this.mCombineProgress.setAlpha(PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO);
                this.mCombineProgress.setVisibility(0);
                ofFloat = ValueAnimator.ofFloat(new float[]{PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO, 1.0f});
                ofFloat.setDuration(300);
                ofFloat.setStartDelay(160);
                ofFloat.setInterpolator(new PathInterpolator(0.25f, 0.1f, 0.25f, 1.0f));
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Float f = (Float) valueAnimator.getAnimatedValue();
                        FragmentFullScreen.this.mCombineProgress.setAlpha(f.floatValue());
                        FragmentFullScreen.this.mCombineProgress.setScaleX((f.floatValue() * 0.1f) + 0.9f);
                        FragmentFullScreen.this.mCombineProgress.setScaleY(0.9f + (0.1f * f.floatValue()));
                    }
                });
                ofFloat.start();
            } else {
                ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, PanoramaArrowAnimateDrawable.LEFT_ARROW_RATIO});
                ofFloat.setDuration(300);
                ofFloat.setInterpolator(new CubicEaseInInterpolator());
                ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        FragmentFullScreen.this.mCombineProgress.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
                ofFloat.addListener(new AnimatorListener() {
                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        FragmentFullScreen.this.mCombineProgress.setVisibility(8);
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

    public boolean onBackEvent(int i) {
        if (i != 1 || this.mLiveViewLayout == null || this.mLiveViewLayout.getVisibility() != 0) {
            return false;
        }
        if (this.mShareLayout.getVisibility() == 0) {
            hideShareSheet();
        } else {
            showExitConfirm();
        }
        return true;
    }

    public boolean isLiveRecordPreviewShown() {
        return this.mLiveViewLayout != null && this.mLiveViewLayout.getVisibility() == 0;
    }

    public void onLiveSaveToLocalFinished(Uri uri) {
        this.mSavedUri = uri;
        if (this.mPendingShare) {
            this.mPreviewShare.setVisibility(0);
            this.mShareProgress.setVisibility(8);
            startShare();
        }
    }

    public ContentValues getSaveContentValues() {
        return this.mSaveContentValues;
    }

    private boolean checkAndShare() {
        if (this.mSavedUri == null) {
            return false;
        }
        startShare();
        return true;
    }

    private void startShare() {
        this.mPendingShare = false;
        showShareSheet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0099  */
    private void showShareSheet() {
        /*
        r12 = this;
        r0 = r12.getShareIntent();
        r1 = r12.getContext();
        r1 = r1.getPackageManager();
        r2 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r0 = r1.queryIntentActivities(r0, r2);
        if (r0 == 0) goto L_0x015e;
    L_0x0014:
        r1 = r0.isEmpty();
        if (r1 == 0) goto L_0x001c;
    L_0x001a:
        goto L_0x015e;
    L_0x001c:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = com.android.camera.constant.ShareConstant.DEFAULT_SHARE_LIST;
        r2 = r2.length;
        r0 = r0.iterator();
    L_0x0028:
        r3 = r0.hasNext();
        r4 = 0;
        if (r3 == 0) goto L_0x006b;
    L_0x002f:
        r3 = r0.next();
        r3 = (android.content.pm.ResolveInfo) r3;
        r5 = r1.size();
        if (r5 != r2) goto L_0x003c;
    L_0x003b:
        goto L_0x006b;
    L_0x003c:
        r7 = r4;
    L_0x003d:
        if (r7 >= r2) goto L_0x006a;
    L_0x003f:
        r4 = com.android.camera.constant.ShareConstant.DEFAULT_SHARE_LIST;
        r4 = r4[r7];
        r5 = r3.activityInfo;
        r5 = r5.name;
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0067;
    L_0x004d:
        r4 = new com.android.camera.fragment.fullscreen.ShareInfo;
        r5 = r3.activityInfo;
        r8 = r5.packageName;
        r3 = r3.activityInfo;
        r9 = r3.name;
        r3 = com.android.camera.constant.ShareConstant.DEFAULT_SHARE_LIST_ICON;
        r10 = r3[r7];
        r3 = com.android.camera.constant.ShareConstant.DEFAULT_SHARE_LIST_NAME;
        r11 = r3[r7];
        r6 = r4;
        r6.<init>(r7, r8, r9, r10, r11);
        r1.add(r4);
        goto L_0x006a;
    L_0x0067:
        r7 = r7 + 1;
        goto L_0x003d;
    L_0x006a:
        goto L_0x0028;
        r0 = r1.isEmpty();
        r3 = 1;
        if (r0 == 0) goto L_0x007d;
    L_0x0073:
        r0 = "FullScreen";
        r5 = "no default share entry";
        com.android.camera.log.Log.d(r0, r5);
    L_0x007b:
        r0 = r3;
        goto L_0x0097;
    L_0x007d:
        r0 = r1.get(r4);
        r0 = (com.android.camera.fragment.fullscreen.ShareInfo) r0;
        r0 = r0.index;
        if (r0 <= r3) goto L_0x0096;
    L_0x0087:
        r0 = r1.size();
        r5 = 2;
        if (r0 >= r5) goto L_0x0096;
    L_0x008e:
        r0 = "FullScreen";
        r5 = "not match default share strategy";
        com.android.camera.log.Log.d(r0, r5);
        goto L_0x007b;
    L_0x0096:
        r0 = r4;
    L_0x0097:
        if (r0 == 0) goto L_0x009d;
    L_0x0099:
        r12.shareMore();
        return;
    L_0x009d:
        r0 = new com.android.camera.fragment.fullscreen.ShareInfo;
        r6 = r2 + 1;
        r7 = "com.android.camera";
        r8 = "more";
        r9 = 2130837785; // 0x7f020119 float:1.7280534E38 double:1.0527737464E-314;
        r10 = 2131296490; // 0x7f0900ea float:1.8210898E38 double:1.053000377E-314;
        r5 = r0;
        r5.<init>(r6, r7, r8, r9, r10);
        r1.add(r0);
        r0 = new com.android.camera.fragment.fullscreen.FragmentFullScreen$14;
        r0.<init>();
        java.util.Collections.sort(r1, r0);
        r0 = r12.getResources();
        r2 = 2131362116; // 0x7f0a0144 float:1.8344003E38 double:1.0530328004E-314;
        r0 = r0.getDimensionPixelSize(r2);
        r2 = com.android.camera.Util.sWindowWidth;
        r2 = r2 - r0;
        r2 = (float) r2;
        r3 = 1085276160; // 0x40b00000 float:5.5 double:5.36197667E-315;
        r2 = r2 / r3;
        r2 = (int) r2;
        r3 = com.android.camera.Util.sWindowWidth;
        r5 = r0 * 2;
        r3 = r3 - r5;
        r5 = r1.size();
        r3 = r3 / r5;
        r2 = java.lang.Math.max(r3, r2);
        r3 = r12.mShareAdapter;
        if (r3 == 0) goto L_0x00f7;
    L_0x00df:
        r3 = r12.mShareAdapter;
        r3 = r3.getItemCount();
        r5 = r1.size();
        if (r3 == r5) goto L_0x00ec;
    L_0x00eb:
        goto L_0x00f7;
    L_0x00ec:
        r0 = r12.mShareAdapter;
        r0.setShareInfoList(r1);
        r0 = r12.mShareAdapter;
        r0.notifyDataSetChanged();
        goto L_0x012e;
    L_0x00f7:
        r3 = new com.android.camera.fragment.fullscreen.ShareAdapter;
        r3.<init>(r1, r12, r2);
        r12.mShareAdapter = r3;
        r1 = new android.support.v7.widget.LinearLayoutManager;
        r2 = r12.getContext();
        r1.<init>(r2);
        r1.setOrientation(r4);
        r2 = r12.mShareRecyclerView;
        r2.setLayoutManager(r1);
        r1 = new com.android.camera.fragment.RecyclerAdapterWrapper;
        r2 = r12.mShareAdapter;
        r1.<init>(r2);
        r2 = new android.widget.Space;
        r3 = r12.getContext();
        r2.<init>(r3);
        r2.setMinimumWidth(r0);
        r1.addHeader(r2);
        r1.addFooter(r2);
        r0 = r12.mShareRecyclerView;
        r0.setAdapter(r1);
    L_0x012e:
        r0 = com.android.camera.Util.sNavigationBarHeight;
        if (r0 <= 0) goto L_0x013e;
    L_0x0132:
        r0 = r12.mShareLayout;
        r0 = r0.getLayoutParams();
        r0 = (android.view.ViewGroup.MarginLayoutParams) r0;
        r1 = com.android.camera.Util.sNavigationBarHeight;
        r0.bottomMargin = r1;
    L_0x013e:
        r0 = new com.android.camera.animation.type.SlideInOnSubscribe;
        r1 = r12.mShareLayout;
        r2 = 80;
        r0.<init>(r1, r2);
        r1 = new miui.view.animation.QuarticEaseOutInterpolator;
        r1.<init>();
        r0 = r0.setInterpolator(r1);
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0 = r0.setDurationTime(r1);
        r0 = io.reactivex.Completable.create(r0);
        r0.subscribe();
        return;
    L_0x015e:
        r0 = "FullScreen";
        r1 = "no IntentActivities";
        com.android.camera.log.Log.d(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.camera.fragment.fullscreen.FragmentFullScreen.showShareSheet():void");
    }

    private void hideShareSheet() {
        if (this.mShareLayout.getVisibility() != 4) {
            Completable.create(new SlideOutOnSubscribe(this.mShareLayout, 80).setInterpolator(new QuarticEaseInInterpolator()).setDurationTime(200)).subscribe();
        }
    }

    private void startShare(String str, String str2) {
        if (str2.equals(ShareConstant.DEFAULT_SHARE_LIST[0]) || str2.equals(ShareConstant.DEFAULT_SHARE_LIST[1])) {
            CameraStatUtil.trackLiveClick(CameraStat.PARAM_LIVE_CLICK_PLAY_SHARE_DEFAULT);
        }
        ComponentName componentName = new ComponentName(str, str2);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setComponent(componentName);
        intent.putExtra("android.intent.extra.STREAM", this.mSavedUri);
        intent.setType(Util.convertOutputFormatToMimeType(2));
        intent.addFlags(1);
        try {
            getContext().startActivity(Intent.createChooser(intent, getString(2131296884)));
        } catch (ActivityNotFoundException e) {
            str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("failed to share video ");
            stringBuilder.append(this.mSavedUri);
            Log.e(str2, stringBuilder.toString(), e);
        }
    }

    private void shareMore() {
        try {
            getContext().startActivity(Intent.createChooser(getShareIntent(), getString(2131296884)));
        } catch (ActivityNotFoundException e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("failed to share video ");
            stringBuilder.append(this.mSavedUri);
            Log.e(str, stringBuilder.toString(), e);
        }
    }

    private Intent getShareIntent() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", this.mSavedUri);
        intent.setType(Util.convertOutputFormatToMimeType(2));
        intent.addFlags(1);
        return intent;
    }
}
