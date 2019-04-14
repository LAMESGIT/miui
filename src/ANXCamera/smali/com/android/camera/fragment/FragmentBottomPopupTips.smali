.class public Lcom/android/camera/fragment/FragmentBottomPopupTips;
.super Lcom/android/camera/fragment/BaseFragment;
.source "FragmentBottomPopupTips.java"

# interfaces
.implements Landroid/view/View$OnClickListener;
.implements Lcom/android/camera/protocol/ModeProtocol$BottomPopupTips;
.implements Lcom/android/camera/protocol/ModeProtocol$HandleBackTrace;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/camera/fragment/FragmentBottomPopupTips$TipImageType;
    }
.end annotation


# static fields
.field private static final ANIM_DELAY_SHOW:I = 0x3

.field private static final ANIM_DIRECT_HIDE:I = 0x1

.field private static final ANIM_DIRECT_SHOW:I = 0x0

.field private static final ANIM_HIDE:I = 0x4

.field private static final ANIM_SHOW:I = 0x2

.field private static final CALL_TYPE_NOTIFY:I = 0x1

.field private static final CALL_TYPE_PROVIDE:I = 0x0

.field private static final CENTER_TIP_IMAGE_SPEED:I = 0x21

.field public static final FRAGMENT_INFO:I = 0xff1

.field private static final LEFT_TIP_IMAGE_CLOSE:I = 0x14

.field private static final LEFT_TIP_IMAGE_LIGHTING:I = 0x13

.field private static final LEFT_TIP_IMAGE_STICKER:I = 0x12

.field private static final LEFT_TIP_IMAGE_ULTRA_WIDE:I = 0x15

.field private static final MSG_HIDE_TIP:I = 0x1

.field private static final TIP_BACK_FACING_IMAGE_BEAUTY:I = 0x4

.field private static final TIP_IMAGE_BEAUTY:I = 0x3

.field private static final TIP_IMAGE_INVALID:I = -0x1

.field private static final TIP_IMAGE_STICKER:I = 0x2


# instance fields
.field private mBottomTipHeight:I

.field private mCenterTipImage:Landroid/widget/ImageView;

.field private mCloseIv:Landroid/widget/ImageView;

.field private mCloseType:I

.field private mCurrentTipMessage:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field private mCurrentTipType:I

.field private mHandler:Landroid/os/Handler;

.field private mIsShowLeftImageIntro:Z

.field private mLastTipMessage:I
    .annotation build Landroid/support/annotation/StringRes;
    .end annotation
.end field

.field private mLastTipType:I

.field private mLeftImageIntro:Landroid/widget/FrameLayout;

.field private mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

.field private mLeftImageIntroContent:Landroid/widget/TextView;

.field private mLeftImageIntroRadius:I

.field private mLeftImageIntroWidth:I

.field private mLeftTipImage:Landroid/widget/ImageView;

.field private mLightingPattern:Landroid/widget/TextView;

.field private mPortraitSuccessHint:Landroid/view/View;

.field private mQrCodeButton:Landroid/widget/TextView;

.field private mRootView:Landroid/view/View;

.field private mSpeedTipImage:Landroid/view/ViewGroup;

.field private mTipImage:Landroid/widget/ImageView;

.field private mTipMessage:Landroid/widget/TextView;

.field private stringLightingRes:I


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 71
    invoke-direct {p0}, Lcom/android/camera/fragment/BaseFragment;-><init>()V

    .line 132
    const/4 v0, 0x0

    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    .line 137
    new-instance v0, Lcom/android/camera/fragment/FragmentBottomPopupTips$1;

    invoke-direct {v0, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips$1;-><init>(Lcom/android/camera/fragment/FragmentBottomPopupTips;)V

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    return-void
.end method

.method static synthetic access$000(Lcom/android/camera/fragment/FragmentBottomPopupTips;)Landroid/widget/TextView;
    .locals 0

    .line 71
    iget-object p0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$100(Lcom/android/camera/fragment/FragmentBottomPopupTips;)I
    .locals 0

    .line 71
    iget p0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    return p0
.end method

.method static synthetic access$102(Lcom/android/camera/fragment/FragmentBottomPopupTips;I)I
    .locals 0

    .line 71
    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    return p1
.end method

.method static synthetic access$200(Lcom/android/camera/fragment/FragmentBottomPopupTips;)I
    .locals 0

    .line 71
    iget p0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    return p0
.end method

.method static synthetic access$300(Lcom/android/camera/fragment/FragmentBottomPopupTips;)I
    .locals 0

    .line 71
    iget p0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipMessage:I

    return p0
.end method

.method static synthetic access$400(Lcom/android/camera/fragment/FragmentBottomPopupTips;)Landroid/widget/TextView;
    .locals 0

    .line 71
    iget-object p0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    return-object p0
.end method

.method static synthetic access$500(Lcom/android/camera/fragment/FragmentBottomPopupTips;)Landroid/widget/FrameLayout;
    .locals 0

    .line 71
    iget-object p0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method static synthetic access$600(Lcom/android/camera/fragment/FragmentBottomPopupTips;Landroid/view/View;I)V
    .locals 0

    .line 71
    invoke-direct {p0, p1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->setBeautyIntroButtonWidth(Landroid/view/View;I)V

    return-void
.end method

.method static synthetic access$700(Lcom/android/camera/fragment/FragmentBottomPopupTips;)V
    .locals 0

    .line 71
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directHideLeftImageIntro()V

    return-void
.end method

.method private adjustViewBackground(I)V
    .locals 1

    .line 1509
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/data/data/runing/DataItemRunning;->getUiStyle()I

    move-result p1

    .line 1510
    const/4 v0, 0x1

    if-eq p1, v0, :cond_0

    const/4 v0, 0x3

    if-eq p1, v0, :cond_0

    .line 1513
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    const v0, 0x7f020019

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setBackgroundResource(I)V

    .line 1514
    goto :goto_0

    .line 1518
    :cond_0
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    const v0, 0x7f02001a

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setBackgroundResource(I)V

    .line 1521
    :goto_0
    return-void
.end method

.method private checkLeftImageTipClose(I)I
    .locals 2

    .line 1216
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa2

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;

    .line 1217
    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;->isShowLightingView()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1218
    const/16 p1, 0x14

    .line 1220
    :cond_0
    return p1
.end method

.method private closeFilter()V
    .locals 2

    .line 460
    const/4 v0, 0x1

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showCloseTip(IZ)V

    .line 461
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa4

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 462
    if-eqz v0, :cond_0

    .line 463
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->showOrHideFilter()V

    .line 465
    :cond_0
    return-void
.end method

.method private closeLight()V
    .locals 3

    .line 448
    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-virtual {p0, v1, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showCloseTip(IZ)V

    .line 449
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xa4

    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 450
    if-eqz v1, :cond_0

    .line 451
    invoke-interface {v1, v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->showOrHideLighting(Z)V

    .line 454
    :cond_0
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 455
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateLeftTipImage()V

    .line 457
    :cond_1
    return-void
.end method

.method private directHideLeftImageIntro()V
    .locals 1

    .line 1692
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mIsShowLeftImageIntro:Z

    .line 1693
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 1694
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    invoke-static {v0}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1696
    :cond_0
    return-void
.end method

.method private getLeftImageIntroWide()I
    .locals 4

    .line 217
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroContent:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1, v1}, Landroid/widget/TextView;->measure(II)V

    .line 218
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroContent:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getMeasuredWidth()I

    move-result v0

    .line 219
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0a009f

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 220
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f0a00a0

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    .line 221
    add-int/2addr v0, v1

    add-int/2addr v0, v2

    iget v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroRadius:I

    sub-int/2addr v2, v1

    mul-int/lit8 v2, v2, 0x2

    add-int/2addr v0, v2

    return v0
.end method

.method private hideAllTipImage()V
    .locals 1

    .line 468
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTipImage()V

    .line 469
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 470
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideLeftTipImage()V

    .line 471
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideSpeedTipImage()V

    .line 472
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideZoomTipImage(I)V

    .line 473
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideCenterTipImage()V

    .line 475
    :cond_0
    return-void
.end method

.method private hideTip(Landroid/view/View;)Z
    .locals 2

    .line 827
    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_0

    .line 828
    invoke-virtual {p1, v1}, Landroid/view/View;->setVisibility(I)V

    .line 829
    const/4 p1, 0x1

    return p1

    .line 831
    :cond_0
    const/4 p1, 0x0

    return p1
.end method

.method private hideZoomTipImage(I)V
    .locals 1

    .line 395
    const/16 v0, 0xa3

    if-eq p1, v0, :cond_0

    const/16 v0, 0xad

    if-eq p1, v0, :cond_0

    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 397
    :pswitch_0
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object p1

    invoke-virtual {p1}, Lcom/mi/config/a;->fM()Z

    move-result p1

    if-nez p1, :cond_0

    .line 398
    return-void

    .line 403
    :cond_0
    :pswitch_1
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xb6

    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$DualController;

    .line 404
    if-eqz p1, :cond_2

    .line 405
    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$DualController;->hideZoomButton()V

    .line 407
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xac

    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    .line 409
    if-eqz p1, :cond_1

    .line 410
    const/4 v0, 0x2

    invoke-interface {p1, v0}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->alertUpdateValue(I)V

    .line 412
    :cond_1
    nop

    .line 417
    :cond_2
    :goto_0
    return-void

    :pswitch_data_0
    .packed-switch 0xa5
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private isPortraitSuccessHintVisible()Z
    .locals 1

    .line 602
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private onLeftImageClick(Landroid/view/View;)V
    .locals 2

    .line 351
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 353
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa4

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 355
    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 374
    :pswitch_0
    if-eqz v0, :cond_0

    .line 375
    const/16 p1, 0xcd

    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->onConfigChanged(I)V

    goto :goto_0

    .line 357
    :pswitch_1
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    packed-switch p1, :pswitch_data_1

    .line 365
    goto :goto_0

    .line 362
    :pswitch_2
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->closeLight()V

    .line 363
    goto :goto_0

    .line 359
    :pswitch_3
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->closeFilter()V

    .line 360
    goto :goto_0

    .line 369
    :pswitch_4
    if-eqz v0, :cond_0

    .line 370
    const/4 p1, 0x1

    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->showOrHideLighting(Z)V

    .line 379
    :cond_0
    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x13
        :pswitch_4
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_3
        :pswitch_2
    .end packed-switch
.end method

.method private reIntTipViewMarginBottom(Landroid/view/View;I)V
    .locals 9

    .line 611
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 613
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xb6

    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$DualController;

    .line 615
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v2

    const/16 v3, 0xb5

    invoke-virtual {v2, v3}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v2

    check-cast v2, Lcom/android/camera/protocol/ModeProtocol$ManuallyAdjust;

    .line 616
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v3

    const/16 v4, 0xc2

    invoke-virtual {v3, v4}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v3

    check-cast v3, Lcom/android/camera/protocol/ModeProtocol$MiBeautyProtocol;

    .line 618
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v4

    const/16 v5, 0xd2

    invoke-virtual {v4, v5}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v4

    check-cast v4, Lcom/android/camera/protocol/ModeProtocol$BokehFNumberController;

    .line 619
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v5

    const v6, 0x7f0a013d

    invoke-virtual {v5, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v5

    .line 620
    nop

    .line 621
    iget v6, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const v7, 0x7f0a0096

    const/16 v8, 0xa5

    if-ne v6, v8, :cond_1

    .line 623
    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-static {v1}, Lcom/android/camera/CameraSettings;->isUltraWideConfigOpen(I)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    .line 624
    invoke-virtual {v1}, Landroid/widget/ImageView;->getVisibility()I

    move-result v1

    if-eqz v1, :cond_0

    .line 625
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1, v7}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 626
    div-int/lit8 v1, v1, 0x2

    div-int/lit8 p2, p2, 0x2

    sub-int/2addr v1, p2

    .line 627
    goto/16 :goto_1

    .line 628
    :cond_0
    sget p2, Lcom/android/camera/Util;->sWindowWidth:I

    int-to-float p2, p2

    const/high16 v1, 0x3f400000    # 0.75f

    div-float/2addr p2, v1

    float-to-int p2, p2

    sget v1, Lcom/android/camera/Util;->sWindowWidth:I

    sub-int/2addr p2, v1

    div-int/lit8 p2, p2, 0x2

    .line 629
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0a009a

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    add-int/2addr v1, p2

    goto/16 :goto_1

    .line 631
    :cond_1
    if-eqz v2, :cond_2

    invoke-interface {v2}, Lcom/android/camera/protocol/ModeProtocol$ManuallyAdjust;->visibleHeight()I

    move-result v6

    if-lez v6, :cond_2

    .line 632
    invoke-interface {v2}, Lcom/android/camera/protocol/ModeProtocol$ManuallyAdjust;->visibleHeight()I

    move-result v1

    goto/16 :goto_1

    .line 633
    :cond_2
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v2

    const v6, 0x7f0a00d5

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {v2}, Landroid/widget/ImageView;->getVisibility()I

    move-result v2

    if-nez v2, :cond_5

    goto :goto_0

    :cond_3
    iget-object v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v2}, Landroid/widget/ImageView;->getVisibility()I

    move-result v2

    if-nez v2, :cond_5

    .line 636
    :goto_0
    if-eqz v3, :cond_4

    invoke-interface {v3}, Lcom/android/camera/protocol/ModeProtocol$MiBeautyProtocol;->isBeautyPanelShow()Z

    move-result p2

    if-eqz p2, :cond_4

    .line 637
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    invoke-virtual {p2, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    add-int v1, p2, v5

    goto/16 :goto_1

    .line 639
    :cond_4
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getHeight()I

    move-result v1

    goto/16 :goto_1

    .line 641
    :cond_5
    if-eqz v1, :cond_6

    invoke-interface {v1}, Lcom/android/camera/protocol/ModeProtocol$DualController;->isZoomVisible()Z

    move-result v2

    if-eqz v2, :cond_6

    .line 642
    invoke-interface {v1}, Lcom/android/camera/protocol/ModeProtocol$DualController;->visibleHeight()I

    move-result v1

    goto :goto_1

    .line 643
    :cond_6
    if-eqz v4, :cond_7

    invoke-interface {v4}, Lcom/android/camera/protocol/ModeProtocol$BokehFNumberController;->isFNumberVisible()Z

    move-result v1

    if-eqz v1, :cond_7

    .line 644
    invoke-interface {v4}, Lcom/android/camera/protocol/ModeProtocol$BokehFNumberController;->visibleHeight()I

    move-result p2

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getTranslationY()F

    move-result v1

    float-to-int v1, v1

    add-int/2addr p2, v1

    add-int v1, p2, v5

    goto :goto_1

    .line 647
    :cond_7
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v1

    const/16 v2, 0xa0

    invoke-virtual {v1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v1

    check-cast v1, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 649
    if-eqz v1, :cond_8

    const v2, 0x7f0d00ff

    invoke-interface {v1, v2}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->getActiveFragment(I)I

    move-result v1

    const/16 v2, 0xfc

    if-ne v1, v2, :cond_8

    .line 651
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    invoke-virtual {p2, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    .line 652
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0a006d

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    add-int/2addr v1, p2

    .line 653
    goto :goto_1

    :cond_8
    if-eqz v3, :cond_9

    invoke-interface {v3}, Lcom/android/camera/protocol/ModeProtocol$MiBeautyProtocol;->isBeautyPanelShow()Z

    move-result v1

    if-eqz v1, :cond_9

    .line 655
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object p2

    invoke-virtual {p2, v6}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p2

    add-int v1, p2, v5

    goto :goto_1

    .line 657
    :cond_9
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1, v7}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 659
    div-int/lit8 v1, v1, 0x2

    div-int/lit8 p2, p2, 0x2

    sub-int/2addr v1, p2

    .line 663
    :goto_1
    iget p2, v0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    if-eq p2, v1, :cond_a

    .line 664
    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    .line 665
    invoke-virtual {p1, v0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 667
    :cond_a
    return-void
.end method

.method private setBeautyIntroButtonWidth(Landroid/view/View;I)V
    .locals 1

    .line 866
    if-nez p1, :cond_0

    .line 867
    return-void

    .line 869
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 870
    iput p2, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 871
    invoke-virtual {p1, v0}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 872
    return-void
.end method

.method private showBeauty(I)V
    .locals 1

    .line 382
    invoke-static {p1}, Lcom/android/camera/fragment/beauty/MiBeauty;->showBeautyActionFromMode(I)V

    .line 384
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/16 v0, 0xab

    if-ne p1, v0, :cond_0

    .line 386
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xd2

    .line 387
    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$BokehFNumberController;

    .line 388
    if-eqz p1, :cond_0

    .line 389
    const/4 v0, 0x0

    invoke-interface {p1, v0, v0}, Lcom/android/camera/protocol/ModeProtocol$BokehFNumberController;->hideFNumberPanel(ZZ)V

    .line 392
    :cond_0
    return-void
.end method

.method private showLiveSpeed()V
    .locals 2

    .line 434
    const-string v0, "live_speed"

    invoke-static {v0}, Lcom/android/camera/statistic/CameraStatUtil;->trackLiveClick(Ljava/lang/String;)V

    .line 435
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc5

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomMenuProtocol;

    .line 437
    if-eqz v0, :cond_0

    .line 438
    const/16 v1, 0xa5

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BottomMenuProtocol;->onSwitchLiveActionMenu(I)V

    .line 440
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa0

    .line 441
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 442
    if-eqz v0, :cond_1

    .line 443
    const/16 v1, 0xd

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->delegateEvent(I)V

    .line 445
    :cond_1
    return-void
.end method

.method private showLiveSticker()V
    .locals 2

    .line 420
    const-string v0, "live_sticker"

    invoke-static {v0}, Lcom/android/camera/statistic/CameraStatUtil;->trackLiveClick(Ljava/lang/String;)V

    .line 421
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc5

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BottomMenuProtocol;

    .line 423
    if-eqz v0, :cond_0

    .line 424
    const/16 v1, 0xa4

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BottomMenuProtocol;->onSwitchLiveActionMenu(I)V

    .line 426
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa0

    .line 427
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 428
    if-eqz v0, :cond_1

    .line 429
    const/16 v1, 0xc

    invoke-interface {v0, v1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->delegateEvent(I)V

    .line 431
    :cond_1
    return-void
.end method

.method private startLeftImageIntroAnim(I)V
    .locals 6

    .line 1634
    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    .line 1635
    return-void

    .line 1637
    :cond_0
    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directShowOrHideLeftTipImage(Z)V

    .line 1638
    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    invoke-virtual {v1, p1}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 1639
    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    if-nez v1, :cond_1

    .line 1640
    const/4 v1, 0x2

    new-array v2, v1, [I

    iget v3, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroWidth:I

    aput v3, v2, p1

    iget v3, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroRadius:I

    mul-int/2addr v3, v1

    aput v3, v2, v0

    invoke-static {v2}, Landroid/animation/ValueAnimator;->ofInt([I)Landroid/animation/ValueAnimator;

    move-result-object v2

    .line 1641
    const-wide/16 v3, 0x12c

    invoke-virtual {v2, v3, v4}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 1642
    iget-object v3, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroContent:Landroid/widget/TextView;

    const-string v4, "alpha"

    new-array v5, v1, [F

    fill-array-data v5, :array_0

    invoke-static {v3, v4, v5}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v3

    .line 1643
    const-wide/16 v4, 0xfa

    invoke-virtual {v3, v4, v5}, Landroid/animation/ObjectAnimator;->setDuration(J)Landroid/animation/ObjectAnimator;

    .line 1645
    new-instance v4, Lcom/android/camera/fragment/FragmentBottomPopupTips$5;

    invoke-direct {v4, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips$5;-><init>(Lcom/android/camera/fragment/FragmentBottomPopupTips;)V

    .line 1652
    invoke-virtual {v2, v4}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 1653
    new-instance v4, Landroid/animation/AnimatorSet;

    invoke-direct {v4}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v4, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    .line 1654
    iget-object v4, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    new-array v1, v1, [Landroid/animation/Animator;

    aput-object v2, v1, p1

    aput-object v3, v1, v0

    invoke-virtual {v4, v1}, Landroid/animation/AnimatorSet;->playTogether([Landroid/animation/Animator;)V

    .line 1655
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    new-instance v0, Landroid/view/animation/PathInterpolator;

    const v1, 0x3dcccccd    # 0.1f

    const/high16 v2, 0x3e800000    # 0.25f

    invoke-direct {v0, v2, v1, v2, v1}, Landroid/view/animation/PathInterpolator;-><init>(FFFF)V

    invoke-virtual {p1, v0}, Landroid/animation/AnimatorSet;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 1656
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    const-wide/16 v0, 0x7d0

    invoke-virtual {p1, v0, v1}, Landroid/animation/AnimatorSet;->setStartDelay(J)V

    .line 1657
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    new-instance v0, Lcom/android/camera/fragment/FragmentBottomPopupTips$6;

    invoke-direct {v0, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips$6;-><init>(Lcom/android/camera/fragment/FragmentBottomPopupTips;)V

    invoke-virtual {p1, v0}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 1681
    goto :goto_0

    .line 1682
    :cond_1
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    const/high16 v0, 0x3f800000    # 1.0f

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->setAlpha(F)V

    .line 1683
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroContent:Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/widget/TextView;->clearAnimation()V

    .line 1684
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    invoke-virtual {p1}, Landroid/animation/AnimatorSet;->cancel()V

    .line 1686
    :goto_0
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroAnimator:Landroid/animation/AnimatorSet;

    invoke-virtual {p1}, Landroid/animation/AnimatorSet;->start()V

    .line 1687
    invoke-static {}, Lcom/android/camera/CameraSettings;->addPopupUltraWideIntroShowTimes()V

    .line 1688
    return-void

    nop

    :array_0
    .array-data 4
        0x3f800000    # 1.0f
        0x0
    .end array-data
.end method

.method private updateCenterTipImage(IILjava/util/List;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(II",
            "Ljava/util/List<",
            "Lio/reactivex/Completable;",
            ">;)V"
        }
    .end annotation

    .line 1230
    nop

    .line 1231
    const/16 v0, 0x12

    const/4 v1, -0x1

    const/16 v2, 0xae

    if-eq p1, v2, :cond_0

    .line 1237
    move v2, v1

    goto :goto_0

    .line 1233
    :cond_0
    nop

    .line 1237
    move v2, v0

    .line 1238
    :goto_0
    nop

    .line 1239
    const/4 v3, 0x1

    const/4 v4, 0x0

    if-eq v2, v1, :cond_3

    if-eq v2, v0, :cond_1

    .line 1252
    move v1, v3

    move v0, v4

    goto :goto_2

    .line 1244
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->getCurrentLiveSticker()Ljava/lang/String;

    move-result-object v0

    .line 1245
    const-string v1, ""

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1246
    const v0, 0x7f020121

    .line 1252
    :goto_1
    move v1, v3

    goto :goto_2

    .line 1248
    :cond_2
    const v0, 0x7f020122

    goto :goto_1

    .line 1241
    :cond_3
    nop

    .line 1242
    nop

    .line 1252
    move v0, v4

    move v1, v0

    :goto_2
    if-lez v0, :cond_4

    .line 1253
    iget-object v5, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {v5, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1256
    :cond_4
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-direct {p0, p1, v0, v3}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateImageBgColor(ILandroid/view/View;Z)V

    .line 1257
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getTag()Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_5

    .line 1258
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 1259
    if-ne p1, v2, :cond_5

    .line 1260
    return-void

    .line 1264
    :cond_5
    if-eqz v1, :cond_6

    .line 1265
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mDegree:I

    int-to-float v0, v0

    invoke-static {p1, v0}, Landroid/support/v4/view/ViewCompat;->setRotation(Landroid/view/View;F)V

    .line 1268
    :cond_6
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1271
    if-nez p3, :cond_8

    .line 1272
    if-eqz v1, :cond_7

    .line 1273
    nop

    .line 1298
    :goto_3
    move v3, v4

    goto :goto_5

    .line 1275
    :cond_7
    goto :goto_5

    .line 1278
    :cond_8
    const/16 p1, 0xa5

    if-eqz v1, :cond_b

    .line 1279
    if-ne p2, p1, :cond_a

    .line 1281
    invoke-static {}, Lcom/mi/config/b;->isSupportedOpticalZoom()Z

    move-result p1

    if-eqz p1, :cond_9

    .line 1282
    goto :goto_3

    .line 1284
    :cond_9
    const/4 v3, 0x3

    goto :goto_5

    .line 1287
    :cond_a
    const/4 v3, 0x2

    goto :goto_5

    .line 1290
    :cond_b
    if-eq p2, p1, :cond_d

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-ne p2, p1, :cond_c

    goto :goto_4

    .line 1293
    :cond_c
    const/4 v3, 0x4

    goto :goto_5

    .line 1291
    :cond_d
    :goto_4
    nop

    .line 1298
    :goto_5
    packed-switch v3, :pswitch_data_0

    goto :goto_6

    .line 1309
    :pswitch_0
    new-instance p1, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1310
    goto :goto_6

    .line 1312
    :pswitch_1
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    .line 1313
    const/16 p2, 0x96

    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->setStartDelayTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1314
    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/BaseOnSubScribe;->setDurationTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1312
    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_6

    .line 1306
    :pswitch_2
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1307
    goto :goto_6

    .line 1303
    :pswitch_3
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1304
    goto :goto_6

    .line 1300
    :pswitch_4
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1301
    nop

    .line 1317
    :goto_6
    return-void

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private updateImageBgColor(ILandroid/view/View;Z)V
    .locals 2

    .line 1036
    if-eqz p3, :cond_0

    .line 1037
    const p3, 0x7f020007

    .line 1038
    const v0, 0x7f0201cd

    goto :goto_0

    .line 1040
    :cond_0
    const p3, 0x7f02000a

    .line 1041
    const v0, 0x7f0201ce

    .line 1043
    :goto_0
    const/16 v1, 0xa5

    if-eq p1, v1, :cond_1

    .line 1048
    invoke-virtual {p2, p3}, Landroid/view/View;->setBackgroundResource(I)V

    goto :goto_1

    .line 1045
    :cond_1
    invoke-virtual {p2, v0}, Landroid/view/View;->setBackgroundResource(I)V

    .line 1046
    nop

    .line 1051
    :goto_1
    return-void
.end method

.method private updateLeftTipImage(IIILjava/util/List;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(III",
            "Ljava/util/List<",
            "Lio/reactivex/Completable;",
            ">;)V"
        }
    .end annotation

    .line 1060
    nop

    .line 1061
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/global/DataItemGlobal;->getCurrentCameraId()I

    move-result v0

    .line 1062
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemGlobal()Lcom/android/camera/data/data/global/DataItemGlobal;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera/data/data/global/DataItemGlobal;->isNormalIntent()Z

    move-result v1

    .line 1063
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v2

    invoke-virtual {v2}, Lcom/mi/config/a;->isSupportUltraWide()Z

    move-result v2

    .line 1064
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v3

    iget v4, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-virtual {v3, v0, v4}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getCapabilitiesByBogusCameraId(II)Lcom/android/camera2/CameraCapabilities;

    move-result-object v3

    .line 1065
    const/4 v4, -0x1

    const/16 v5, 0x15

    packed-switch p2, :pswitch_data_0

    :pswitch_0
    goto :goto_3

    .line 1067
    :pswitch_1
    if-eqz v1, :cond_4

    .line 1068
    const/16 p1, 0x13

    packed-switch v0, :pswitch_data_1

    goto :goto_0

    .line 1075
    :pswitch_2
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fb()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1076
    goto :goto_1

    .line 1070
    :pswitch_3
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fa()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1071
    goto :goto_1

    .line 1078
    :cond_0
    :goto_0
    move p1, v4

    :goto_1
    goto :goto_4

    .line 1085
    :pswitch_4
    invoke-static {v3}, Lcom/android/camera/CameraSettings;->isSupportedUltraPixelPhotography(Lcom/android/camera2/CameraCapabilities;)Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1086
    goto :goto_3

    .line 1103
    :pswitch_5
    if-nez v0, :cond_4

    if-eqz v1, :cond_4

    if-eqz v2, :cond_4

    .line 1104
    iget-boolean v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mIsShowLeftImageIntro:Z

    if-eqz v0, :cond_1

    .line 1105
    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->startLeftImageIntroAnim(I)V

    .line 1106
    return-void

    .line 1108
    :cond_1
    goto :goto_2

    .line 1090
    :cond_2
    :pswitch_6
    if-eqz v2, :cond_4

    if-nez v0, :cond_4

    .line 1091
    iget-boolean v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mIsShowLeftImageIntro:Z

    if-eqz v0, :cond_3

    .line 1092
    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->startLeftImageIntroAnim(I)V

    .line 1093
    return-void

    .line 1095
    :cond_3
    nop

    .line 1113
    :goto_2
    move p1, v5

    goto :goto_4

    :cond_4
    :goto_3
    move p1, v4

    :goto_4
    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->checkLeftImageTipClose(I)I

    move-result p1

    .line 1114
    nop

    .line 1115
    nop

    .line 1116
    nop

    .line 1117
    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eq p1, v4, :cond_9

    if-eq p1, v5, :cond_6

    packed-switch p1, :pswitch_data_2

    .line 1139
    move v4, v0

    move v2, v1

    move v3, v2

    goto :goto_8

    .line 1130
    :pswitch_7
    const v2, 0x7f0200f9

    .line 1131
    const v3, 0x7f090266

    .line 1132
    nop

    .line 1139
    move v4, v0

    goto :goto_8

    .line 1122
    :pswitch_8
    invoke-static {}, Lcom/android/camera/CameraSettings;->getCurrentLiveSticker()Ljava/lang/String;

    move-result-object v2

    .line 1123
    const-string v3, ""

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 1124
    const v2, 0x7f020121

    .line 1139
    :goto_5
    move v4, v0

    move v3, v1

    goto :goto_8

    .line 1126
    :cond_5
    const v2, 0x7f020122

    .line 1128
    goto :goto_5

    .line 1134
    :cond_6
    iget v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-static {v2}, Lcom/android/camera/CameraSettings;->isUltraWideConfigOpen(I)Z

    move-result v2

    .line 1135
    if-eqz v2, :cond_7

    const v3, 0x7f02017b

    goto :goto_6

    :cond_7
    const v3, 0x7f02017a

    .line 1136
    :goto_6
    if-eqz v2, :cond_8

    const v2, 0x7f090248

    goto :goto_7

    :cond_8
    const v2, 0x7f090249

    .line 1139
    :goto_7
    move v4, v0

    move v6, v3

    move v3, v2

    move v2, v6

    goto :goto_8

    .line 1119
    :cond_9
    nop

    .line 1120
    nop

    .line 1139
    move v2, v1

    move v3, v2

    move v4, v3

    :goto_8
    if-lez v2, :cond_a

    .line 1140
    iget-object v5, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {v5, v2}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1142
    :cond_a
    if-lez v3, :cond_b

    invoke-static {}, Lcom/android/camera/Util;->isAccessible()Z

    move-result v2

    if-eqz v2, :cond_b

    .line 1143
    iget-object v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {p0, v3}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/ImageView;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 1146
    :cond_b
    iget-object v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p0, p2, v2, v1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateImageBgColor(ILandroid/view/View;Z)V

    .line 1147
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getTag()Ljava/lang/Object;

    move-result-object p2

    if-eqz p2, :cond_c

    .line 1148
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getTag()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Integer;

    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p2

    .line 1149
    if-ne p2, p1, :cond_c

    .line 1150
    return-void

    .line 1154
    :cond_c
    if-eqz v4, :cond_d

    .line 1155
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    iget v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mDegree:I

    int-to-float v2, v2

    invoke-static {p2, v2}, Landroid/support/v4/view/ViewCompat;->setRotation(Landroid/view/View;F)V

    .line 1158
    :cond_d
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p2, p1}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1161
    if-nez p4, :cond_f

    .line 1162
    if-eqz v4, :cond_e

    .line 1163
    nop

    .line 1189
    move v0, v1

    goto :goto_c

    .line 1165
    :cond_e
    goto :goto_c

    .line 1168
    :cond_f
    const/16 p1, 0xa5

    if-eqz v4, :cond_12

    .line 1169
    if-ne p3, p1, :cond_11

    .line 1171
    invoke-static {}, Lcom/mi/config/b;->isSupportedOpticalZoom()Z

    move-result p1

    if-eqz p1, :cond_10

    .line 1172
    nop

    .line 1179
    move v0, v1

    goto :goto_a

    .line 1174
    :cond_10
    const/4 p1, 0x3

    goto :goto_9

    .line 1177
    :cond_11
    const/4 p1, 0x2

    .line 1179
    :goto_9
    move v0, p1

    :goto_a
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directHideLeftImageIntro()V

    goto :goto_c

    .line 1181
    :cond_12
    if-eq p3, p1, :cond_14

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-ne p2, p1, :cond_13

    goto :goto_b

    .line 1184
    :cond_13
    const/4 v0, 0x4

    goto :goto_c

    .line 1182
    :cond_14
    :goto_b
    nop

    .line 1189
    :goto_c
    packed-switch v0, :pswitch_data_3

    goto :goto_d

    .line 1200
    :pswitch_9
    new-instance p1, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p4, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1201
    goto :goto_d

    .line 1203
    :pswitch_a
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    .line 1204
    const/16 p2, 0x96

    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->setStartDelayTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1205
    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/BaseOnSubScribe;->setDurationTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1203
    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p4, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_d

    .line 1197
    :pswitch_b
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p4, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1198
    goto :goto_d

    .line 1194
    :pswitch_c
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1195
    goto :goto_d

    .line 1191
    :pswitch_d
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1192
    nop

    .line 1208
    :goto_d
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0xa1
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_0
        :pswitch_6
        :pswitch_0
        :pswitch_0
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_4
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x0
        :pswitch_3
        :pswitch_2
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x12
        :pswitch_8
        :pswitch_7
    .end packed-switch

    :pswitch_data_3
    .packed-switch 0x0
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
    .end packed-switch
.end method

.method private updateLightingPattern(ZZ)V
    .locals 1

    .line 1466
    if-eqz p1, :cond_0

    .line 1467
    const/4 p1, -0x2

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 1469
    :cond_0
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/camera/data/data/runing/DataItemRunning;->getComponentRunningLighting()Lcom/android/camera/data/data/runing/ComponentRunningLighting;

    move-result-object p1

    const/16 v0, 0xab

    .line 1470
    invoke-virtual {p1, v0}, Lcom/android/camera/data/data/runing/ComponentRunningLighting;->getComponentValue(I)Ljava/lang/String;

    move-result-object p1

    .line 1471
    const-string v0, "0"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 1472
    return-void

    .line 1474
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isLandScape()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 1475
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    invoke-virtual {p0, p1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->starAnimatetViewGone(Landroid/view/View;Z)V

    goto :goto_0

    .line 1476
    :cond_2
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    if-lez p1, :cond_3

    .line 1477
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    invoke-virtual {p0, p1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->startAnimateViewVisible(Landroid/view/View;Z)V

    .line 1479
    :cond_3
    :goto_0
    return-void
.end method

.method private updateSpeedTipImage(IILjava/util/List;)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(II",
            "Ljava/util/List<",
            "Lio/reactivex/Completable;",
            ">;)V"
        }
    .end annotation

    .line 1327
    nop

    .line 1328
    nop

    .line 1329
    const/4 v0, -0x1

    const/16 v1, 0x21

    const/16 v2, 0xae

    if-eq p1, v2, :cond_0

    .line 1335
    move p1, v0

    goto :goto_0

    .line 1331
    :cond_0
    nop

    .line 1335
    move p1, v1

    .line 1336
    :goto_0
    nop

    .line 1337
    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eq p1, v0, :cond_2

    if-eq p1, v1, :cond_1

    .line 1347
    move v4, v2

    move v5, v4

    move v0, v3

    goto :goto_1

    .line 1342
    :cond_1
    const v0, 0x7f040003

    .line 1343
    nop

    .line 1347
    move v4, v2

    move v5, v3

    goto :goto_1

    .line 1339
    :cond_2
    nop

    .line 1340
    nop

    .line 1347
    move v5, v2

    move v0, v3

    move v4, v0

    .line 1348
    :goto_1
    iget-object v6, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {v6}, Landroid/view/ViewGroup;->getTag()Ljava/lang/Object;

    move-result-object v6

    if-eqz v6, :cond_3

    .line 1349
    iget-object v6, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {v6}, Landroid/view/ViewGroup;->getTag()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Integer;

    invoke-virtual {v6}, Ljava/lang/Integer;->intValue()I

    move-result v6

    .line 1350
    if-ne v6, p1, :cond_3

    .line 1351
    nop

    .line 1355
    move v6, v3

    goto :goto_2

    :cond_3
    move v6, v2

    :goto_2
    if-eqz v6, :cond_4

    .line 1356
    iget-object v7, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {v7}, Landroid/view/ViewGroup;->removeAllViews()V

    .line 1357
    if-lez v0, :cond_4

    .line 1358
    iget-object v7, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getContext()Landroid/content/Context;

    move-result-object v8

    invoke-static {v8}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v8

    const/4 v9, 0x0

    invoke-virtual {v8, v0, v9}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {v7, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;)V

    .line 1361
    :cond_4
    if-ne p1, v1, :cond_5

    .line 1362
    invoke-static {}, Lcom/android/camera/CameraSettings;->getCurrentLiveSpeedText()Ljava/lang/String;

    move-result-object v0

    .line 1363
    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    const v7, 0x7f0d0010

    invoke-virtual {v1, v7}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 1364
    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 1366
    :cond_5
    if-nez v6, :cond_6

    .line 1367
    return-void

    .line 1370
    :cond_6
    if-eqz v4, :cond_7

    if-eqz v5, :cond_7

    .line 1371
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mDegree:I

    int-to-float v1, v1

    invoke-static {v0, v1}, Landroid/support/v4/view/ViewCompat;->setRotation(Landroid/view/View;F)V

    goto :goto_3

    .line 1373
    :cond_7
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Landroid/support/v4/view/ViewCompat;->setRotation(Landroid/view/View;F)V

    .line 1376
    :goto_3
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/view/ViewGroup;->setTag(Ljava/lang/Object;)V

    .line 1379
    if-nez p3, :cond_9

    .line 1380
    if-eqz v4, :cond_8

    .line 1381
    nop

    .line 1401
    :goto_4
    move v2, v3

    goto :goto_5

    .line 1383
    :cond_8
    goto :goto_5

    .line 1386
    :cond_9
    if-eqz v4, :cond_c

    .line 1387
    const/16 p1, 0xa3

    if-ne p2, p1, :cond_b

    .line 1388
    invoke-static {}, Lcom/mi/config/b;->isSupportedOpticalZoom()Z

    move-result p1

    if-eqz p1, :cond_a

    .line 1389
    goto :goto_4

    .line 1391
    :cond_a
    const/4 v2, 0x3

    goto :goto_5

    .line 1394
    :cond_b
    const/4 v2, 0x2

    goto :goto_5

    .line 1397
    :cond_c
    const/4 v2, 0x4

    .line 1401
    :goto_5
    packed-switch v2, :pswitch_data_0

    goto :goto_6

    .line 1412
    :pswitch_0
    new-instance p1, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1413
    goto :goto_6

    .line 1415
    :pswitch_1
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    .line 1416
    const/16 p2, 0x96

    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->setStartDelayTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1417
    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/BaseOnSubScribe;->setDurationTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1415
    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_6

    .line 1409
    :pswitch_2
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1410
    goto :goto_6

    .line 1406
    :pswitch_3
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1407
    goto :goto_6

    .line 1403
    :pswitch_4
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1404
    nop

    .line 1420
    :goto_6
    return-void

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private updateTipImage(IILjava/util/List;)V
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(II",
            "Ljava/util/List<",
            "Lio/reactivex/Completable;",
            ">;)V"
        }
    .end annotation

    .line 875
    nop

    .line 876
    const/16 v0, 0xae

    const/4 v1, -0x1

    const/4 v2, 0x4

    const/4 v3, 0x3

    const/16 v4, 0xa5

    if-eq p1, v4, :cond_6

    const/16 v5, 0xab

    if-eq p1, v5, :cond_2

    if-eq p1, v0, :cond_0

    packed-switch p1, :pswitch_data_0

    goto/16 :goto_2

    .line 879
    :pswitch_0
    invoke-static {}, Lcom/android/camera/CameraSettings;->isRearMenuUltraPixelPhotographyOn()Z

    move-result v5

    if-eqz v5, :cond_6

    .line 880
    goto/16 :goto_2

    .line 900
    :pswitch_1
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v5

    const-string v6, "pref_camera_auto_zoom"

    invoke-virtual {v5, v6}, Lcom/android/camera/data/data/runing/DataItemRunning;->isSwitchOn(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_0

    .line 901
    goto :goto_2

    .line 905
    :cond_0
    :pswitch_2
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v5

    .line 906
    invoke-static {}, Lcom/android/camera/CameraSettings;->getBogusCameraId()I

    move-result v6

    invoke-virtual {v5, v6, p1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getCapabilitiesByBogusCameraId(II)Lcom/android/camera2/CameraCapabilities;

    move-result-object v5

    .line 907
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontCamera()Z

    move-result v6

    if-eqz v6, :cond_1

    .line 908
    if-eqz v5, :cond_8

    invoke-virtual {v5}, Lcom/android/camera2/CameraCapabilities;->isSupportVideoBeauty()Z

    move-result v5

    if-eqz v5, :cond_8

    .line 909
    goto :goto_0

    .line 911
    :cond_1
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v6

    if-eqz v6, :cond_8

    .line 913
    if-eqz v5, :cond_8

    invoke-virtual {v5}, Lcom/android/camera2/CameraCapabilities;->isSupportVideoBeauty()Z

    move-result v5

    if-eqz v5, :cond_8

    .line 914
    goto :goto_1

    .line 891
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontCamera()Z

    move-result v5

    if-eqz v5, :cond_3

    invoke-static {}, Lcom/mi/config/b;->hz()Z

    move-result v5

    if-nez v5, :cond_4

    :cond_3
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v5

    if-eqz v5, :cond_5

    .line 892
    :cond_4
    goto :goto_0

    .line 893
    :cond_5
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v5

    if-eqz v5, :cond_8

    .line 895
    goto :goto_1

    .line 883
    :cond_6
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontCamera()Z

    move-result v5

    if-eqz v5, :cond_7

    .line 884
    nop

    .line 920
    :goto_0
    move v5, v3

    goto :goto_3

    .line 885
    :cond_7
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v5

    if-eqz v5, :cond_8

    .line 887
    nop

    .line 920
    :goto_1
    move v5, v2

    goto :goto_3

    :cond_8
    :goto_2
    move v5, v1

    .line 921
    :goto_3
    nop

    .line 922
    nop

    .line 923
    const/4 v6, 0x1

    const/4 v7, 0x0

    if-eq v5, v1, :cond_c

    packed-switch v5, :pswitch_data_1

    .line 955
    move v9, v6

    move v0, v7

    move v8, v0

    goto :goto_6

    .line 933
    :pswitch_3
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v8

    const v9, 0x7f0200bf

    if-nez v8, :cond_9

    .line 934
    goto :goto_5

    .line 937
    :cond_9
    iget v8, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-eq v8, v0, :cond_a

    .line 942
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemConfig()Lcom/android/camera/data/data/config/DataItemConfig;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/config/DataItemConfig;->getComponentConfigBeauty()Lcom/android/camera/data/data/config/ComponentConfigBeauty;

    move-result-object v0

    iget v8, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-virtual {v0, v8}, Lcom/android/camera/data/data/config/ComponentConfigBeauty;->getComponentValue(I)Ljava/lang/String;

    move-result-object v0

    .line 943
    invoke-static {v0}, Lcom/android/camera/CameraSettings;->isFaceBeautyOn(Ljava/lang/String;)Z

    move-result v0

    goto :goto_4

    .line 939
    :cond_a
    invoke-static {}, Lcom/android/camera/CameraSettings;->isLiveBeautyOpen()Z

    move-result v0

    .line 940
    nop

    .line 946
    :goto_4
    if-eqz v0, :cond_b

    .line 947
    const v0, 0x7f0200c0

    .line 952
    move v9, v0

    goto :goto_5

    .line 949
    :cond_b
    nop

    .line 952
    :goto_5
    const v0, 0x7f0900f4

    .line 955
    move v8, v0

    move v0, v9

    move v9, v6

    goto :goto_6

    .line 928
    :pswitch_4
    const v0, 0x7f0200c2

    .line 929
    nop

    .line 955
    move v9, v6

    move v8, v7

    goto :goto_6

    .line 925
    :cond_c
    nop

    .line 926
    nop

    .line 955
    move v0, v7

    move v8, v0

    move v9, v8

    :goto_6
    if-lez v0, :cond_d

    .line 956
    iget-object v10, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v10, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 958
    :cond_d
    if-lez v8, :cond_e

    invoke-static {}, Lcom/android/camera/Util;->isAccessible()Z

    move-result v0

    if-eqz v0, :cond_e

    .line 959
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {p0, v8}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v0, v8}, Landroid/widget/ImageView;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 962
    :cond_e
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-direct {p0, p1, v0, v7}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateImageBgColor(ILandroid/view/View;Z)V

    .line 963
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getTag()Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_f

    .line 964
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 965
    if-ne p1, v5, :cond_f

    .line 966
    return-void

    .line 970
    :cond_f
    if-eqz v9, :cond_10

    .line 971
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mDegree:I

    int-to-float v0, v0

    invoke-static {p1, v0}, Landroid/support/v4/view/ViewCompat;->setRotation(Landroid/view/View;F)V

    .line 974
    :cond_10
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 977
    if-nez p3, :cond_12

    .line 978
    if-eqz v9, :cond_11

    .line 979
    nop

    .line 1004
    :goto_7
    move v2, v7

    goto :goto_a

    .line 981
    :cond_11
    nop

    .line 1004
    :goto_8
    move v2, v6

    goto :goto_a

    .line 984
    :cond_12
    if-eqz v9, :cond_15

    .line 985
    if-ne p2, v4, :cond_14

    .line 987
    invoke-static {}, Lcom/mi/config/b;->isSupportedOpticalZoom()Z

    move-result p1

    if-eqz p1, :cond_13

    .line 988
    goto :goto_7

    .line 990
    :cond_13
    nop

    .line 1004
    move v2, v3

    goto :goto_a

    .line 993
    :cond_14
    const/4 v2, 0x2

    goto :goto_a

    .line 996
    :cond_15
    if-eq p2, v4, :cond_17

    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-ne p1, v4, :cond_16

    goto :goto_9

    .line 999
    :cond_16
    goto :goto_a

    .line 997
    :cond_17
    :goto_9
    goto :goto_8

    .line 1004
    :goto_a
    packed-switch v2, :pswitch_data_2

    goto :goto_c

    .line 1023
    :pswitch_5
    new-instance p1, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1024
    goto :goto_c

    .line 1026
    :pswitch_6
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    .line 1027
    const/16 p2, 0x96

    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->setStartDelayTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1028
    invoke-virtual {p1, p2}, Lcom/android/camera/animation/type/BaseOnSubScribe;->setDurationTime(I)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 1026
    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_c

    .line 1020
    :pswitch_7
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, p2}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-interface {p3, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1021
    goto :goto_c

    .line 1017
    :pswitch_8
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1018
    goto :goto_c

    .line 1006
    :pswitch_9
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 p2, 0xa2

    .line 1007
    invoke-virtual {p1, p2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;

    .line 1008
    if-eqz p1, :cond_18

    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;->isShowLightingView()Z

    move-result p1

    if-eqz p1, :cond_18

    goto :goto_b

    .line 1009
    :cond_18
    move v6, v7

    :goto_b
    if-eqz v6, :cond_19

    .line 1010
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1012
    :cond_19
    if-nez v6, :cond_1a

    .line 1013
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 1031
    :cond_1a
    :goto_c
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0xa1
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x2
        :pswitch_4
        :pswitch_3
        :pswitch_3
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x0
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
    .end packed-switch
.end method


# virtual methods
.method public containTips(I)Z
    .locals 1
    .param p1    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param

    .line 687
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 688
    invoke-virtual {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getString(I)Ljava/lang/String;

    move-result-object p1

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getText()Ljava/lang/CharSequence;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    .line 687
    :goto_0
    return p1
.end method

.method public directHideTipImage()V
    .locals 3

    .line 489
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getVisibility()I

    move-result v0

    const/4 v1, 0x4

    if-eq v0, v1, :cond_0

    .line 490
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    const/4 v2, -0x1

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 491
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 493
    :cond_0
    return-void
.end method

.method public directShowOrHideLeftTipImage(Z)V
    .locals 1

    .line 1591
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    if-nez v0, :cond_0

    .line 1592
    return-void

    .line 1594
    :cond_0
    if-eqz p1, :cond_1

    .line 1595
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateLeftTipImage()V

    .line 1596
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_0

    .line 1598
    :cond_1
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const/4 v0, -0x1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1599
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const/4 v0, 0x4

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1601
    :goto_0
    return-void
.end method

.method public directlyHideTips()V
    .locals 3

    .line 671
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-static {v0}, Landroid/support/v4/view/ViewCompat;->animate(Landroid/view/View;)Landroid/support/v4/view/ViewPropertyAnimatorCompat;

    move-result-object v0

    invoke-virtual {v0}, Landroid/support/v4/view/ViewPropertyAnimatorCompat;->cancel()V

    .line 672
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 673
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    .line 674
    return-void

    .line 676
    :cond_0
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 678
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    const/4 v1, 0x6

    const/4 v2, 0x4

    if-ne v0, v1, :cond_1

    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isPortraitHintVisible()Z

    move-result v0

    if-nez v0, :cond_1

    .line 679
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipMessage:I

    invoke-virtual {p0, v0, v1, v2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showTips(III)V

    .line 682
    :cond_1
    iput v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    .line 683
    return-void
.end method

.method public getFragmentInto()I
    .locals 1

    .line 231
    const/16 v0, 0xff1

    return v0
.end method

.method protected getLayoutResourceId()I
    .locals 1

    .line 226
    const v0, 0x7f04000d

    return v0
.end method

.method public hideCenterTipImage()V
    .locals 2

    .line 1573
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getVisibility()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 1576
    :cond_0
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    const/4 v1, -0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1577
    new-instance v0, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-direct {v0, v1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {v0}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object v0

    invoke-virtual {v0}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    .line 1578
    return-void

    .line 1574
    :cond_1
    :goto_0
    return-void
.end method

.method public hideLeftTipImage()V
    .locals 2

    .line 1555
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getVisibility()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 1558
    :cond_0
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const/4 v1, -0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1559
    new-instance v0, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {v0, v1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {v0}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object v0

    invoke-virtual {v0}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    .line 1560
    return-void

    .line 1556
    :cond_1
    :goto_0
    return-void
.end method

.method public hideQrCodeTip()V
    .locals 2

    .line 788
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-eq v0, v1, :cond_0

    .line 789
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 791
    :cond_0
    return-void
.end method

.method public hideSpeedTipImage()V
    .locals 2

    .line 1564
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getVisibility()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 1567
    :cond_0
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    const/4 v1, -0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->setTag(Ljava/lang/Object;)V

    .line 1568
    new-instance v0, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-direct {v0, v1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {v0}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object v0

    invoke-virtual {v0}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    .line 1569
    return-void

    .line 1565
    :cond_1
    :goto_0
    return-void
.end method

.method public hideTipImage()V
    .locals 2

    .line 479
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getVisibility()I

    move-result v0

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 482
    :cond_0
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    const/4 v1, -0x1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 483
    new-instance v0, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-direct {v0, v1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {v0}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object v0

    invoke-virtual {v0}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    .line 485
    return-void

    .line 480
    :cond_1
    :goto_0
    return-void
.end method

.method protected initView(Landroid/view/View;)V
    .locals 3

    .line 158
    iput-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    .line 160
    const v0, 0x7f0d0031

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    .line 163
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 164
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 166
    const v1, 0x800055

    iput v1, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 168
    const v0, 0x7f0d0032

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    .line 169
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 170
    const v1, 0x800053

    iput v1, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 172
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const v1, 0x7f02013d

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 173
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 176
    const v0, 0x7f0d0036

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    .line 177
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {v0, p0}, Landroid/view/ViewGroup;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 179
    const v0, 0x7f0d0033

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    .line 180
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout$LayoutParams;

    .line 181
    const/16 v1, 0x51

    iput v1, v0, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 182
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 184
    const v0, 0x7f0d0034

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    .line 185
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntro:Landroid/widget/FrameLayout;

    invoke-virtual {v0, p0}, Landroid/widget/FrameLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 186
    const v0, 0x7f0d0035

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroContent:Landroid/widget/TextView;

    .line 187
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f0a009e

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroRadius:I

    .line 188
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getLeftImageIntroWide()I

    move-result v0

    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftImageIntroWidth:I

    .line 190
    :cond_0
    const v0, 0x7f0d0038

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    .line 191
    const v0, 0x7f0d0037

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    .line 192
    const v0, 0x7f0d0039

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    .line 193
    const v0, 0x7f0d003a

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    .line 195
    nop

    .line 196
    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 197
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-static {v1}, Lcom/android/camera/Util;->getBottomHeight(Landroid/content/res/Resources;)I

    move-result v1

    iput v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    .line 199
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 200
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-virtual {v0, p0}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 201
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->adjustViewBackground(I)V

    .line 202
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/4 v1, 0x2

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->provideAnimateElement(ILjava/util/List;I)V

    .line 203
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getContext()Landroid/content/Context;

    move-result-object v0

    check-cast v0, Lcom/android/camera/ActivityBase;

    .line 204
    invoke-virtual {v0}, Lcom/android/camera/ActivityBase;->getCameraIntentManager()Lcom/android/camera/CameraIntentManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/CameraIntentManager;->isFromScreenSlide()Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 205
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-static {v0}, Lcom/android/camera/Util;->startScreenSlideAlphaInAnimation(Landroid/view/View;)V

    .line 207
    :cond_1
    const v0, 0x7f0d003b

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseIv:Landroid/widget/ImageView;

    .line 208
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseIv:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 210
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result p1

    if-nez p1, :cond_2

    .line 211
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 213
    :cond_2
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const v0, 0x7f0a0039

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mBottomTipHeight:I

    .line 214
    return-void
.end method

.method public isLightingHintVisible()Z
    .locals 2

    .line 711
    nop

    .line 712
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xc6

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$VerticalProtocol;

    .line 713
    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 714
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$VerticalProtocol;->isAnyViewVisible()Z

    move-result v0

    goto :goto_0

    .line 716
    :cond_0
    move v0, v1

    :goto_0
    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    goto :goto_1

    :cond_1
    goto :goto_2

    :cond_2
    :goto_1
    const/4 v1, 0x1

    :goto_2
    return v1
.end method

.method public isPortraitHintVisible()Z
    .locals 1

    .line 706
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public notifyAfterFrameAvailable(I)V
    .locals 0

    .line 1505
    invoke-super {p0, p1}, Lcom/android/camera/fragment/BaseFragment;->notifyAfterFrameAvailable(I)V

    .line 1506
    return-void
.end method

.method public notifyDataChanged(II)V
    .locals 2

    .line 1484
    invoke-super {p0, p1, p2}, Lcom/android/camera/fragment/BaseFragment;->notifyDataChanged(II)V

    .line 1486
    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 1488
    :pswitch_0
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->adjustViewBackground(I)V

    .line 1489
    goto :goto_0

    .line 1491
    :pswitch_1
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directlyHideTips()V

    .line 1494
    :goto_0
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateTipImage(IILjava/util/List;)V

    .line 1496
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 1497
    const/4 p1, 0x1

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, p1, p2, v1, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateLeftTipImage(IIILjava/util/List;)V

    .line 1498
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, p1, p2, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateSpeedTipImage(IILjava/util/List;)V

    .line 1499
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, p1, p2, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateCenterTipImage(IILjava/util/List;)V

    .line 1501
    :cond_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public onBackEvent(I)Z
    .locals 3

    .line 796
    const/16 v0, 0x9

    const/4 v1, 0x0

    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 798
    :pswitch_0
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    const/4 v2, 0x6

    if-eq p1, v2, :cond_0

    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    const/4 v2, 0x7

    if-eq p1, v2, :cond_0

    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    const/16 v2, 0xb

    if-eq p1, v2, :cond_0

    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    if-eq p1, v0, :cond_0

    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    const/16 v2, 0xc

    if-ne p1, v2, :cond_1

    .line 803
    :cond_0
    return v1

    .line 807
    :cond_1
    :pswitch_1
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    if-ne p1, v0, :cond_2

    .line 808
    return v1

    .line 811
    :cond_2
    :goto_0
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 812
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 813
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 814
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 815
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 816
    nop

    .line 823
    :pswitch_2
    return v1

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_2
        :pswitch_0
    .end packed-switch
.end method

.method public onClick(Landroid/view/View;)V
    .locals 2

    .line 255
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isEnableClick()Z

    move-result v0

    if-nez v0, :cond_0

    .line 256
    return-void

    .line 260
    :cond_0
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa1

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$CameraAction;

    .line 261
    if-eqz v0, :cond_1

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$CameraAction;->isDoingAction()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 262
    return-void

    .line 265
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getContext()Landroid/content/Context;

    move-result-object v0

    check-cast v0, Lcom/android/camera/Camera;

    invoke-virtual {v0}, Lcom/android/camera/Camera;->isScreenSlideOff()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 266
    return-void

    .line 269
    :cond_2
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v0

    packed-switch v0, :pswitch_data_0

    :pswitch_0
    goto/16 :goto_3

    .line 310
    :pswitch_1
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    packed-switch p1, :pswitch_data_1

    .line 318
    goto/16 :goto_3

    .line 315
    :pswitch_2
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->closeLight()V

    .line 316
    goto/16 :goto_3

    .line 312
    :pswitch_3
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->closeFilter()V

    .line 313
    goto/16 :goto_3

    .line 271
    :pswitch_4
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideQrCodeTip()V

    .line 272
    const-string p1, "counter"

    const-string v0, "qrcode_detected"

    invoke-static {p1, v0}, Lcom/android/camera/statistic/CameraStat;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    .line 274
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xc3

    .line 275
    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$CameraModuleSpecial;

    .line 276
    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$CameraModuleSpecial;->showQRCodeResult()V

    .line 277
    goto/16 :goto_3

    .line 330
    :pswitch_5
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 331
    const/16 v1, 0x21

    if-ne v0, v1, :cond_3

    .line 332
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideAllTipImage()V

    .line 333
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showLiveSpeed()V

    goto :goto_0

    .line 322
    :pswitch_6
    const/16 v0, 0x15

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    .line 323
    invoke-static {}, Lcom/android/camera/CameraSettings;->setPopupUltraWideIntroClicked()V

    .line 324
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directHideLeftImageIntro()V

    goto :goto_1

    .line 337
    :cond_3
    :goto_0
    :pswitch_7
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 338
    const/16 v0, 0x12

    if-eq p1, v0, :cond_4

    goto :goto_3

    .line 340
    :cond_4
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideAllTipImage()V

    .line 341
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showLiveSticker()V

    goto :goto_3

    .line 326
    :goto_1
    :pswitch_8
    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->onLeftImageClick(Landroid/view/View;)V

    .line 327
    goto :goto_3

    .line 280
    :pswitch_9
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 281
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideAllTipImage()V

    .line 282
    invoke-static {}, Lcom/android/camera/CameraSettings;->setPopupTipBeautyIntroClicked()V

    .line 284
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa0

    .line 285
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;

    .line 286
    packed-switch p1, :pswitch_data_2

    goto :goto_2

    .line 292
    :pswitch_a
    const-string p1, "counter"

    const-string v0, "beauty_button"

    invoke-static {p1, v0}, Lcom/android/camera/statistic/CameraStat;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    .line 293
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showBeauty(I)V

    .line 295
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xa2

    .line 296
    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;

    .line 298
    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa4

    .line 299
    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;

    .line 300
    if-eqz p1, :cond_5

    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$ActionProcessing;->isShowFilterView()Z

    move-result p1

    if-eqz p1, :cond_5

    if-eqz v0, :cond_5

    .line 301
    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$ConfigChanges;->showOrHideFilter()V

    .line 304
    :cond_5
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideQrCodeTip()V

    goto :goto_2

    .line 288
    :pswitch_b
    const/4 p1, 0x4

    invoke-interface {v0, p1}, Lcom/android/camera/protocol/ModeProtocol$BaseDelegate;->delegateEvent(I)V

    .line 289
    nop

    .line 307
    :goto_2
    nop

    .line 348
    :goto_3
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x7f0d0031
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_0
        :pswitch_5
        :pswitch_0
        :pswitch_4
        :pswitch_0
        :pswitch_0
        :pswitch_1
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_3
        :pswitch_2
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x2
        :pswitch_b
        :pswitch_a
        :pswitch_a
    .end packed-switch
.end method

.method public provideAnimateElement(ILjava/util/List;I)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/util/List<",
            "Lio/reactivex/Completable;",
            ">;I)V"
        }
    .end annotation

    .line 839
    const/4 v0, 0x0

    const/4 v1, 0x3

    if-eq p3, v1, :cond_0

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-eq v1, p1, :cond_1

    .line 840
    :cond_0
    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseIv:Landroid/widget/ImageView;

    const/16 v2, 0x8

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 841
    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    .line 844
    :cond_1
    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    .line 845
    invoke-super {p0, p1, p2, p3}, Lcom/android/camera/fragment/BaseFragment;->provideAnimateElement(ILjava/util/List;I)V

    .line 847
    const/4 p3, 0x4

    invoke-virtual {p0, p3}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->onBackEvent(I)Z

    .line 850
    nop

    .line 851
    invoke-virtual {p0, v0, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateTipBottomMargin(IZ)V

    .line 853
    invoke-direct {p0, p1, v1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateTipImage(IILjava/util/List;)V

    .line 855
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result p3

    if-eqz p3, :cond_2

    .line 856
    invoke-direct {p0, v0, p1, v1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateLeftTipImage(IIILjava/util/List;)V

    .line 857
    invoke-direct {p0, p1, v1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateSpeedTipImage(IILjava/util/List;)V

    .line 858
    invoke-direct {p0, p1, v1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateCenterTipImage(IILjava/util/List;)V

    .line 863
    :cond_2
    return-void
.end method

.method protected provideEnterAnimation(I)Landroid/view/animation/Animation;
    .locals 2

    .line 1426
    const/16 v0, 0xf0

    if-eq p1, v0, :cond_1

    .line 1427
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getFragmentInto()I

    move-result v0

    if-ne p1, v0, :cond_0

    goto :goto_0

    .line 1430
    :cond_0
    const/4 p1, 0x1

    new-array p1, p1, [I

    const/4 v0, 0x0

    const/16 v1, 0xa1

    aput v1, p1, v0

    invoke-static {p1}, Lcom/android/camera/animation/FragmentAnimationFactory;->wrapperAnimation([I)Landroid/view/animation/Animation;

    move-result-object p1

    return-object p1

    .line 1428
    :cond_1
    :goto_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public provideRotateItem(Ljava/util/List;I)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroid/view/View;",
            ">;I)V"
        }
    .end annotation

    .line 1435
    invoke-super {p0, p1, p2}, Lcom/android/camera/fragment/BaseFragment;->provideRotateItem(Ljava/util/List;I)V

    .line 1436
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getVisibility()I

    move-result p2

    if-nez p2, :cond_0

    .line 1437
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    invoke-interface {p1, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1440
    :cond_0
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result p2

    if-eqz p2, :cond_4

    .line 1441
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    if-eqz p2, :cond_1

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getVisibility()I

    move-result p2

    if-nez p2, :cond_1

    .line 1442
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-interface {p1, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1444
    :cond_1
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    if-eqz p2, :cond_2

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getVisibility()I

    move-result p2

    if-nez p2, :cond_2

    .line 1445
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-interface {p1, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1447
    :cond_2
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    if-eqz p2, :cond_3

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getVisibility()I

    move-result p2

    if-nez p2, :cond_3

    .line 1448
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getTag()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Integer;

    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p2

    .line 1449
    const/16 v0, 0x21

    if-eq p2, v0, :cond_3

    .line 1450
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mSpeedTipImage:Landroid/view/ViewGroup;

    invoke-interface {p1, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1453
    :cond_3
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    if-eqz p2, :cond_4

    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-virtual {p2}, Landroid/widget/ImageView;->getVisibility()I

    move-result p2

    if-nez p2, :cond_4

    .line 1454
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCenterTipImage:Landroid/widget/ImageView;

    invoke-interface {p1, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1457
    :cond_4
    const/4 p1, 0x0

    const/4 p2, 0x1

    invoke-direct {p0, p1, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateLightingPattern(ZZ)V

    .line 1458
    return-void
.end method

.method public reInitTipImage()V
    .locals 3

    .line 497
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-virtual {p0, v0, v1, v2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->provideAnimateElement(ILjava/util/List;I)V

    .line 498
    return-void
.end method

.method protected register(Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;)V
    .locals 1

    .line 236
    invoke-super {p0, p1}, Lcom/android/camera/fragment/BaseFragment;->register(Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;)V

    .line 237
    const/16 v0, 0xaf

    invoke-interface {p1, v0, p0}, Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;->attachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 238
    invoke-virtual {p0, p1, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->registerBackStack(Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;Lcom/android/camera/protocol/ModeProtocol$HandleBackTrace;)V

    .line 239
    invoke-static {}, Lcom/android/camera/CameraSettings;->isShowUltraWideIntro()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 240
    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mIsShowLeftImageIntro:Z

    .line 242
    :cond_0
    return-void
.end method

.method public selectBeautyTipImage(Z)V
    .locals 1

    .line 1582
    if-eqz p1, :cond_0

    .line 1583
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    const v0, 0x7f0200c0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    .line 1585
    :cond_0
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipImage:Landroid/widget/ImageView;

    const v0, 0x7f0200bf

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1587
    :goto_0
    return-void
.end method

.method public setLightingPattern(Ljava/lang/String;)V
    .locals 2

    .line 721
    const/4 v0, -0x2

    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 722
    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    goto :goto_0

    :pswitch_0
    const-string v1, "8"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/16 p1, 0x8

    goto :goto_1

    :pswitch_1
    const-string v1, "7"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x7

    goto :goto_1

    :pswitch_2
    const-string v1, "6"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x6

    goto :goto_1

    :pswitch_3
    const-string v1, "5"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x5

    goto :goto_1

    :pswitch_4
    const-string v1, "4"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x4

    goto :goto_1

    :pswitch_5
    const-string v1, "3"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x3

    goto :goto_1

    :pswitch_6
    const-string v1, "2"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x2

    goto :goto_1

    :pswitch_7
    const-string v1, "1"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    goto :goto_1

    :pswitch_8
    const-string v1, "0"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    goto :goto_1

    :cond_0
    :goto_0
    const/4 p1, -0x1

    :goto_1
    packed-switch p1, :pswitch_data_1

    goto :goto_2

    .line 748
    :pswitch_9
    const p1, 0x7f09020f

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    goto :goto_2

    .line 745
    :pswitch_a
    const p1, 0x7f09020c

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 746
    goto :goto_2

    .line 742
    :pswitch_b
    const p1, 0x7f09020d

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 743
    goto :goto_2

    .line 739
    :pswitch_c
    const p1, 0x7f09020e

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 740
    goto :goto_2

    .line 736
    :pswitch_d
    const p1, 0x7f09020b

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 737
    goto :goto_2

    .line 733
    :pswitch_e
    const p1, 0x7f09020a

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 734
    goto :goto_2

    .line 730
    :pswitch_f
    const p1, 0x7f090209

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 731
    goto :goto_2

    .line 727
    :pswitch_10
    const p1, 0x7f090208

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 728
    goto :goto_2

    .line 724
    :pswitch_11
    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    .line 725
    nop

    .line 752
    :goto_2
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    if-ne p1, v0, :cond_1

    .line 753
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;->directSetResult(Landroid/view/View;)V

    goto :goto_3

    .line 755
    :cond_1
    const/16 p1, 0xc

    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    .line 756
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 757
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 758
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directHideTipImage()V

    .line 760
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mBottomTipHeight:I

    invoke-direct {p0, p1, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->reIntTipViewMarginBottom(Landroid/view/View;I)V

    .line 761
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->stringLightingRes:I

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(I)V

    .line 762
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isLandScape()Z

    move-result p1

    if-nez p1, :cond_2

    .line 763
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLightingPattern:Landroid/widget/TextView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 767
    :cond_2
    :goto_3
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x30
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x0
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
    .end packed-switch
.end method

.method public setPortraitHintVisible(I)V
    .locals 2

    .line 693
    if-nez p1, :cond_0

    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isLightingHintVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 694
    return-void

    .line 696
    :cond_0
    if-nez p1, :cond_1

    const/4 v0, 0x7

    goto :goto_0

    :cond_1
    const/4 v0, 0x4

    :goto_0
    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    .line 697
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->directlyHideTips()V

    .line 698
    if-nez p1, :cond_2

    .line 699
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mBottomTipHeight:I

    invoke-direct {p0, v0, v1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->reIntTipViewMarginBottom(Landroid/view/View;I)V

    .line 701
    :cond_2
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    .line 702
    return-void
.end method

.method public showCloseTip(IZ)V
    .locals 3

    .line 1526
    invoke-static {}, Lcom/android/camera/Util;->UI_DEBUG()Z

    move-result v0

    const/16 v1, 0xa7

    const/4 v2, 0x0

    if-nez v0, :cond_2

    .line 1527
    if-eqz p2, :cond_1

    .line 1529
    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-ne p2, v1, :cond_0

    .line 1530
    return-void

    .line 1532
    :cond_0
    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    .line 1533
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_1

    .line 1535
    :cond_1
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseIv:Landroid/widget/ImageView;

    const/16 p2, 0x8

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1536
    iput v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    goto :goto_1

    .line 1539
    :cond_2
    if-eqz p2, :cond_4

    .line 1541
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    if-ne v0, v1, :cond_3

    .line 1542
    return-void

    .line 1544
    :cond_3
    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    goto :goto_0

    .line 1546
    :cond_4
    iput v2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    .line 1548
    :goto_0
    invoke-virtual {p0, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->showOrHideCloseImage(Z)V

    .line 1550
    :goto_1
    return-void
.end method

.method public showOrHideCloseImage(Z)V
    .locals 2

    .line 1605
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    if-nez v0, :cond_0

    .line 1606
    return-void

    .line 1608
    :cond_0
    const v0, 0x7f020125

    .line 1609
    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-virtual {v1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1610
    if-eqz p1, :cond_3

    .line 1611
    invoke-static {}, Lcom/android/camera/Util;->isAccessible()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 1612
    iget p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCloseType:I

    const/4 v0, 0x2

    if-eq p1, v0, :cond_1

    goto :goto_0

    .line 1614
    :cond_1
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const v0, 0x7f090267

    invoke-virtual {p0, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 1615
    nop

    .line 1620
    :cond_2
    :goto_0
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const/16 v0, 0x14

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1621
    new-instance p1, Lcom/android/camera/animation/type/AlphaInOnSubscribe;

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, v0}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    goto :goto_1

    .line 1623
    :cond_3
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    const/4 v0, -0x1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setTag(Ljava/lang/Object;)V

    .line 1624
    new-instance p1, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLeftTipImage:Landroid/widget/ImageView;

    invoke-direct {p1, v0}, Lcom/android/camera/animation/type/AlphaOutOnSubscribe;-><init>(Landroid/view/View;)V

    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    .line 1626
    :goto_1
    return-void
.end method

.method public showQrCodeTip()V
    .locals 4

    .line 771
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-virtual {v0}, Landroid/widget/TextView;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    .line 772
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-direct {p0, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 773
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-virtual {v1}, Landroid/widget/TextView;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v1

    invoke-direct {p0, v0, v1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->reIntTipViewMarginBottom(Landroid/view/View;I)V

    .line 774
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-static {v0}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 775
    invoke-static {}, Lcom/android/camera/Util;->isAccessible()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 776
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    new-instance v1, Lcom/android/camera/fragment/FragmentBottomPopupTips$4;

    invoke-direct {v1, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips$4;-><init>(Lcom/android/camera/fragment/FragmentBottomPopupTips;)V

    const-wide/16 v2, 0x64

    invoke-virtual {v0, v1, v2, v3}, Landroid/widget/TextView;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 784
    :cond_0
    return-void
.end method

.method public showTips(III)V
    .locals 2
    .param p2    # I
        .annotation build Landroid/support/annotation/StringRes;
        .end annotation
    .end param

    .line 523
    const/4 v0, 0x6

    if-ne p1, v0, :cond_0

    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/16 v1, 0xab

    if-eq v0, v1, :cond_0

    .line 524
    return-void

    .line 528
    :cond_0
    const/16 v0, 0xa

    if-ne p1, v0, :cond_1

    .line 529
    invoke-static {}, Lcom/android/camera/CameraSettings;->getBogusCameraId()I

    move-result v0

    if-nez v0, :cond_1

    .line 530
    return-void

    .line 533
    :cond_1
    invoke-virtual {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isLightingHintVisible()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 534
    return-void

    .line 538
    :cond_2
    invoke-direct {p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->isPortraitSuccessHintVisible()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 539
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mPortraitSuccessHint:Landroid/view/View;

    invoke-direct {p0, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 542
    :cond_3
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipType:I

    .line 543
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipMessage:I

    iput v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mLastTipMessage:I

    .line 545
    iput p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipType:I

    .line 546
    iput p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipMessage:I

    .line 548
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mQrCodeButton:Landroid/widget/TextView;

    invoke-direct {p0, p1}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->hideTip(Landroid/view/View;)Z

    .line 549
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mBottomTipHeight:I

    invoke-direct {p0, p1, v0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->reIntTipViewMarginBottom(Landroid/view/View;I)V

    .line 550
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-static {p1}, Lcom/android/camera/animation/type/AlphaInOnSubscribe;->directSetResult(Landroid/view/View;)V

    .line 551
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(I)V

    .line 552
    invoke-static {}, Lcom/android/camera/Util;->isAccessible()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 553
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    iget p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentTipMessage:I

    invoke-virtual {p0, p2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 554
    iget-object p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mTipMessage:Landroid/widget/TextView;

    new-instance p2, Lcom/android/camera/fragment/FragmentBottomPopupTips$2;

    invoke-direct {p2, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips$2;-><init>(Lcom/android/camera/fragment/FragmentBottomPopupTips;)V

    const-wide/16 v0, 0xbb8

    invoke-virtual {p1, p2, v0, v1}, Landroid/widget/TextView;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 563
    :cond_4
    const/4 p1, 0x0

    .line 564
    packed-switch p3, :pswitch_data_0

    goto :goto_0

    .line 580
    :pswitch_0
    const/16 p1, 0xbb8

    goto :goto_0

    .line 577
    :pswitch_1
    const/16 p1, 0x7d0

    .line 578
    goto :goto_0

    .line 575
    :pswitch_2
    goto :goto_0

    .line 572
    :pswitch_3
    const/16 p1, 0x3a98

    .line 573
    goto :goto_0

    .line 569
    :pswitch_4
    const/16 p1, 0x1388

    .line 570
    goto :goto_0

    .line 566
    :pswitch_5
    const/16 p1, 0x3e8

    .line 567
    nop

    .line 584
    :goto_0
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    const/4 p3, 0x0

    invoke-virtual {p2, p3}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 585
    if-lez p1, :cond_5

    .line 586
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    const/4 p3, 0x1

    int-to-long v0, p1

    invoke-virtual {p2, p3, v0, v1}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    .line 589
    :cond_5
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public showTips(IIII)V
    .locals 2

    .line 593
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    new-instance v1, Lcom/android/camera/fragment/FragmentBottomPopupTips$3;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/android/camera/fragment/FragmentBottomPopupTips$3;-><init>(Lcom/android/camera/fragment/FragmentBottomPopupTips;III)V

    int-to-long p1, p4

    invoke-virtual {v0, v1, p1, p2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 599
    return-void
.end method

.method protected unRegister(Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;)V
    .locals 2

    .line 246
    invoke-super {p0, p1}, Lcom/android/camera/fragment/BaseFragment;->unRegister(Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;)V

    .line 247
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 248
    const/16 v0, 0xaf

    invoke-interface {p1, v0, p0}, Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;->detachProtocol(ILcom/android/camera/protocol/ModeProtocol$BaseProtocol;)V

    .line 249
    invoke-virtual {p0, p1, p0}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->unRegisterBackStack(Lcom/android/camera/protocol/ModeProtocol$ModeCoordinator;Lcom/android/camera/protocol/ModeProtocol$HandleBackTrace;)V

    .line 250
    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mIsShowLeftImageIntro:Z

    .line 251
    return-void
.end method

.method public updateLeftTipImage()V
    .locals 4

    .line 1630
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    invoke-direct {p0, v2, v0, v1, v3}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateLeftTipImage(IIILjava/util/List;)V

    .line 1631
    return-void
.end method

.method public updateTipBottomMargin(IZ)V
    .locals 3

    .line 502
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    .line 503
    if-ge v0, p1, :cond_0

    .line 504
    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    int-to-float v1, p1

    const v2, 0x3f99999a    # 1.2f

    mul-float/2addr v1, v2

    float-to-int v1, v1

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1, v2, v2}, Landroid/view/View;->setPadding(IIII)V

    .line 506
    :cond_0
    if-eqz p2, :cond_2

    .line 507
    int-to-float p2, p1

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    invoke-static {v0}, Landroid/support/v4/view/ViewCompat;->getTranslationY(Landroid/view/View;)F

    move-result v0

    cmpg-float p2, p2, v0

    if-gez p2, :cond_1

    .line 508
    new-instance p2, Lcom/android/camera/animation/type/TranslateYOnSubscribe;

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    neg-int p1, p1

    invoke-direct {p2, v0, p1}, Lcom/android/camera/animation/type/TranslateYOnSubscribe;-><init>(Landroid/view/View;I)V

    new-instance p1, Landroid/view/animation/OvershootInterpolator;

    invoke-direct {p1}, Landroid/view/animation/OvershootInterpolator;-><init>()V

    .line 509
    invoke-virtual {p2, p1}, Lcom/android/camera/animation/type/TranslateYOnSubscribe;->setInterpolator(Landroid/view/animation/Interpolator;)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 508
    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    .line 509
    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    goto :goto_0

    .line 511
    :cond_1
    new-instance p2, Lcom/android/camera/animation/type/TranslateYOnSubscribe;

    iget-object v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    neg-int p1, p1

    invoke-direct {p2, v0, p1}, Lcom/android/camera/animation/type/TranslateYOnSubscribe;-><init>(Landroid/view/View;I)V

    new-instance p1, Lmiui/view/animation/BackEaseOutInterpolator;

    invoke-direct {p1}, Lmiui/view/animation/BackEaseOutInterpolator;-><init>()V

    .line 512
    invoke-virtual {p2, p1}, Lcom/android/camera/animation/type/TranslateYOnSubscribe;->setInterpolator(Landroid/view/animation/Interpolator;)Lcom/android/camera/animation/type/BaseOnSubScribe;

    move-result-object p1

    .line 511
    invoke-static {p1}, Lio/reactivex/Completable;->create(Lio/reactivex/CompletableOnSubscribe;)Lio/reactivex/Completable;

    move-result-object p1

    .line 512
    invoke-virtual {p1}, Lio/reactivex/Completable;->subscribe()Lio/reactivex/disposables/Disposable;

    goto :goto_0

    .line 515
    :cond_2
    iget-object p2, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mRootView:Landroid/view/View;

    neg-int p1, p1

    invoke-static {p2, p1}, Lcom/android/camera/animation/type/TranslateYOnSubscribe;->directSetResult(Landroid/view/View;I)V

    .line 517
    :goto_0
    return-void
.end method

.method public updateTipImage()V
    .locals 3

    .line 1700
    iget v0, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    iget v1, p0, Lcom/android/camera/fragment/FragmentBottomPopupTips;->mCurrentMode:I

    const/4 v2, 0x0

    invoke-direct {p0, v0, v1, v2}, Lcom/android/camera/fragment/FragmentBottomPopupTips;->updateTipImage(IILjava/util/List;)V

    .line 1701
    return-void
.end method
