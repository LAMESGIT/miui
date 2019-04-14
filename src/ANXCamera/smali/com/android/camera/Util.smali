.class public final Lcom/android/camera/Util;
.super Ljava/lang/Object;
.source "Util.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/camera/Util$ImageFileNamer;
    }
.end annotation


# static fields
.field public static final ACTION_BIND_GALLERY_SERVICE:Ljava/lang/String; = "com.miui.gallery.action.BIND_SERVICE"

.field public static final ACTION_DISMISS_KEY_GUARD:Ljava/lang/String; = "xiaomi.intent.action.SHOW_SECURE_KEYGUARD"

.field public static final ACTION_KILL_CAMERA_SERVICE:Ljava/lang/String; = "com.android.camera.action.KILL_CAMERA_SERVICE"

.field public static final ALGORITHM_NAME_PORTRAIT:Ljava/lang/String; = "portrait"

.field public static final ALGORITHM_NAME_SOFT_PORTRAIT:Ljava/lang/String; = "soft-portrait"

.field public static final ALGORITHM_NAME_SOFT_PORTRAIT_ENCRYPTED:Ljava/lang/String; = "soft-portrait-enc"

.field private static ANTIBANDING_60_COUNTRY:Ljava/util/HashSet; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashSet<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field public static final ASPECT_TOLERANCE:D = 0.02

.field public static final BLUR_DURATION:I = 0x64

.field private static final COLOR_TEMPERATURE_LIST:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final COLOR_TEMPERATURE_MAP:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field public static final DEBUG:Z

.field public static final EXTRAS_SKIP_LOCK:Ljava/lang/String; = "skip_interception"

.field private static final EXTRAS_START_WITH_EFFECT_RENDER:Ljava/lang/String; = "android.intent.extras.START_WITH_EFFECT_RENDER"

.field public static final FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS:I = -0x80000000

.field private static final FORCE_CAMERA_0_FILE:Ljava/lang/String; = "force_camera_0"

.field private static final FORCE_NAME_SUFFIX_FILE:Ljava/lang/String; = "force_name_suffix"

.field public static final GOING_TO_CROP:I = 0x5

.field public static final GOING_TO_DETAIL:I = 0x3

.field public static final GOING_TO_GALLERY:I = 0x1

.field public static final GOING_TO_PLAYBACK:I = 0x4

.field public static final GOING_TO_SETTING:I = 0x2

.field private static final INTERNAL_STORAGE_DIRECTORY:Ljava/io/File;

.field public static final KEYCODE_SLIDE_OFF:I = 0x2bd

.field public static final KEYCODE_SLIDE_ON:I = 0x2bc

.field public static final KEY_CAMERA_BRIGHTNESS:Ljava/lang/String; = "camera-brightness"

.field public static final KEY_CAMERA_BRIGHTNESS_AUTO:Ljava/lang/String; = "camera-brightness-auto"

.field public static final KEY_CAMERA_BRIGHTNESS_MANUAL:Ljava/lang/String; = "camera-brightness-manual"

.field public static final KEY_KILLED_MODULE_INDEX:Ljava/lang/String; = "killed-moduleIndex"

.field public static final KEY_REVIEW_FROM_MIUICAMERA:Ljava/lang/String; = "from_MiuiCamera"

.field public static final KEY_SECURE_ITEMS:Ljava/lang/String; = "SecureUri"

.field private static final LAB_OPTIONS_VISIBLE_FILE:Ljava/lang/String; = "lab_options_visible"

.field public static final LAST_FRAME_GAUSSIAN_FILE_NAME:Ljava/lang/String; = "blur.jpg"

.field public static final LIMIT_SURFACE_WIDTH:I = 0x2d0

.field public static final MAX_SECURE_SIZE:I = 0x64

.field private static final NONUI_MODE_PROPERTY:Ljava/lang/String; = "sys.power.nonui"

.field public static final ORIENTATION_HYSTERESIS:I = 0x5

.field public static final QRCODE_RECEIVER_ACTION:Ljava/lang/String; = "com.xiaomi.scanner.receiver.senderbarcodescanner"

.field public static final RATIO_16_9:F = 1.77f

.field public static final RATIO_18_9:F = 2.0f

.field public static final RATIO_19P5_9:F = 2.16f

.field public static final RATIO_1_1:F = 1.0f

.field public static final RATIO_4_3:F = 1.33f

.field public static final REVIEW_ACTION:Ljava/lang/String; = "com.android.camera.action.REVIEW"

.field public static final REVIEW_ACTIVITY_PACKAGE:Ljava/lang/String; = "com.miui.gallery"

.field public static final REVIEW_SCAN_RESULT_PACKAGE:Ljava/lang/String; = "com.xiaomi.scanner"

.field public static final SCREEN_EFFECT_CAMERA_STATE:I = 0xe

.field public static final SCREEN_SLIDE_STATUS_SETTING_URI:Landroid/net/Uri;

.field private static final SCREEN_VENDOR:Ljava/lang/String;

.field private static final TAG:Ljava/lang/String; = "CameraUtil"

.field private static final TEMP_SUFFIX:Ljava/lang/String; = ".tmp"

.field public static final WATERMARK_48M_FILE_NAME:Ljava/lang/String;

.field public static final WATERMARK_FILE_NAME:Ljava/lang/String;

.field private static final ZOOM_ANIMATION_PROPERTY:Ljava/lang/String; = "camera_zoom_animation"

.field public static isLongRatioScreen:Z

.field public static isNotchDevice:Z

.field private static mCountryIso:Ljava/lang/String;

.field private static mLockedOrientation:I

.field private static sClearMemoryLimit:Z

.field public static sFullScreenExtraMargin:I

.field private static sHasNavigationBar:Z

.field private static sImageFileNamer:Lcom/android/camera/Util$ImageFileNamer;

.field private static sIsAccessibilityEnable:Z

.field public static sIsDumpLog:Z

.field public static sIsDumpOrigJpg:Z

.field private static sIsForceNameSuffix:Ljava/lang/Boolean;

.field public static sIsFullScreenNavBarHidden:Z

.field public static sIsKillCameraService:Z

.field private static sIsLabOptionsVisible:Ljava/lang/Boolean;

.field public static sNavigationBarHeight:I

.field private static sPixelDensity:F

.field public static sStatusBarHeight:I

.field private static sTypefaces:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Landroid/graphics/Typeface;",
            ">;"
        }
    .end annotation
.end field

.field public static sWindowHeight:I

.field private static sWindowManager:Landroid/view/IWindowManager;

.field public static sWindowWidth:I


# direct methods
.method private static synthetic $closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    .locals 0

    .line 2680
    if-eqz p0, :cond_0

    :try_start_0
    invoke-interface {p1}, Ljava/lang/AutoCloseable;->close()V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    goto :goto_0

    :cond_0
    invoke-interface {p1}, Ljava/lang/AutoCloseable;->close()V

    :goto_0
    return-void
.end method

.method static constructor <clinit>()V
    .locals 10

    .line 212
    const-string v0, "sys.panel.display"

    invoke-static {v0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/android/camera/Util;->SCREEN_VENDOR:Ljava/lang/String;

    .line 213
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    .line 214
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_MAP:Ljava/util/List;

    .line 222
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v1, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "_custom_watermark.png"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/android/camera/Util;->WATERMARK_FILE_NAME:Ljava/lang/String;

    .line 223
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v1, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "_48m_custom_watermark.png"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/android/camera/Util;->WATERMARK_48M_FILE_NAME:Ljava/lang/String;

    .line 226
    const/high16 v0, 0x3f800000    # 1.0f

    sput v0, Lcom/android/camera/Util;->sPixelDensity:F

    .line 227
    const/16 v0, 0x2d0

    sput v0, Lcom/android/camera/Util;->sWindowWidth:I

    .line 228
    const/16 v0, 0x438

    sput v0, Lcom/android/camera/Util;->sWindowHeight:I

    .line 236
    new-instance v0, Ljava/io/File;

    const-string v1, "/data/sdcard"

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/android/camera/Util;->INTERNAL_STORAGE_DIRECTORY:Ljava/io/File;

    .line 238
    const-string v0, "sc_status"

    invoke-static {v0}, Landroid/provider/Settings$System;->getUriFor(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    sput-object v0, Lcom/android/camera/Util;->SCREEN_SLIDE_STATUS_SETTING_URI:Landroid/net/Uri;

    .line 241
    sget-boolean v0, Lmiui/os/Build;->IS_STABLE_VERSION:Z

    xor-int/lit8 v0, v0, 0x1

    sput-boolean v0, Lcom/android/camera/Util;->DEBUG:Z

    .line 245
    new-instance v0, Ljava/util/HashSet;

    const-string v1, "TW"

    const-string v2, "KR"

    const-string v3, "SA"

    const-string v4, "US"

    const-string v5, "CA"

    const-string v6, "BR"

    const-string v7, "CO"

    const-string v8, "MX"

    const-string v9, "PH"

    filled-new-array/range {v1 .. v9}, [Ljava/lang/String;

    move-result-object v1

    .line 246
    invoke-static {v1}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    sput-object v0, Lcom/android/camera/Util;->ANTIBANDING_60_COUNTRY:Ljava/util/HashSet;

    .line 247
    const/4 v0, 0x0

    sput-object v0, Lcom/android/camera/Util;->mCountryIso:Ljava/lang/String;

    .line 248
    const/4 v0, -0x1

    sput v0, Lcom/android/camera/Util;->mLockedOrientation:I

    .line 1413
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/android/camera/Util;->sTypefaces:Ljava/util/HashMap;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 262
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 263
    return-void
.end method

.method public static Assert(Z)V
    .locals 0

    .line 605
    if-eqz p0, :cond_0

    .line 608
    return-void

    .line 606
    :cond_0
    new-instance p0, Ljava/lang/AssertionError;

    invoke-direct {p0}, Ljava/lang/AssertionError;-><init>()V

    throw p0
.end method

.method public static RGBA2RGB([BII)[B
    .locals 5

    .line 3544
    if-nez p0, :cond_0

    .line 3545
    const/4 p0, 0x0

    return-object p0

    .line 3547
    :cond_0
    mul-int/2addr p1, p2

    mul-int/lit8 p2, p1, 0x3

    new-array p2, p2, [B

    .line 3548
    nop

    .line 3549
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v0, p1, :cond_1

    .line 3550
    add-int/lit8 v2, v1, 0x1

    mul-int/lit8 v3, v0, 0x4

    aget-byte v4, p0, v3

    aput-byte v4, p2, v1

    .line 3551
    add-int/lit8 v1, v2, 0x1

    add-int/lit8 v4, v3, 0x1

    aget-byte v4, p0, v4

    aput-byte v4, p2, v2

    .line 3552
    add-int/lit8 v2, v1, 0x1

    add-int/lit8 v3, v3, 0x2

    aget-byte v3, p0, v3

    aput-byte v3, p2, v1

    .line 3549
    add-int/lit8 v0, v0, 0x1

    move v1, v2

    goto :goto_0

    .line 3554
    :cond_1
    return-object p2
.end method

.method public static UI_DEBUG()Z
    .locals 1

    .line 3098
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->isSupportUltraWide()Z

    move-result v0

    .line 3099
    if-nez v0, :cond_1

    sget-boolean v0, Lcom/mi/config/b;->qP:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method private static addDebugInfo(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 1717
    const-string v0, ""

    .line 1718
    if-eqz p0, :cond_0

    .line 1719
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "\t "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1720
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\n"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1722
    :cond_0
    return-object v0
.end method

.method private static addProperties(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 1708
    const-string v0, ""

    .line 1709
    invoke-static {p0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 1710
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "\t "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1711
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\n"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1713
    :cond_0
    return-object v0
.end method

.method public static appendInApi26(Landroid/text/SpannableStringBuilder;Ljava/lang/CharSequence;Ljava/lang/Object;I)Landroid/text/SpannableStringBuilder;
    .locals 1

    .line 2050
    invoke-virtual {p0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result v0

    .line 2051
    invoke-virtual {p0, p1}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 2052
    invoke-virtual {p0}, Landroid/text/SpannableStringBuilder;->length()I

    move-result p1

    invoke-virtual {p0, p2, v0, p1, p3}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 2053
    return-object p0
.end method

.method public static binarySearchRightMost(Ljava/util/List;Ljava/lang/Object;)I
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/util/List<",
            "+",
            "Ljava/lang/Comparable<",
            "-TT;>;>;TT;)I"
        }
    .end annotation

    .line 1857
    nop

    .line 1858
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    .line 1860
    const/4 v1, 0x0

    :goto_0
    if-gt v1, v0, :cond_1

    .line 1861
    add-int v2, v1, v0

    div-int/lit8 v2, v2, 0x2

    .line 1862
    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Comparable;

    invoke-interface {v3, p1}, Ljava/lang/Comparable;->compareTo(Ljava/lang/Object;)I

    move-result v3

    .line 1863
    if-ltz v3, :cond_0

    .line 1864
    add-int/lit8 v2, v2, -0x1

    .line 1868
    move v0, v2

    goto :goto_1

    .line 1866
    :cond_0
    add-int/lit8 v2, v2, 0x1

    .line 1868
    move v1, v2

    :goto_1
    goto :goto_0

    .line 1869
    :cond_1
    return v1
.end method

.method public static broadcastKillService(Landroid/content/Context;)V
    .locals 6

    .line 1217
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    .line 1218
    invoke-static {v0, v1}, Lcom/android/camera/CameraSettings;->setBroadcastKillServiceTime(J)V

    .line 1219
    new-instance v2, Landroid/content/Intent;

    const-string v3, "com.android.camera.action.KILL_CAMERA_SERVICE"

    invoke-direct {v2, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 1220
    const-string v3, "time"

    const-wide/16 v4, 0x7d0

    add-long/2addr v0, v4

    invoke-virtual {v2, v3, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    .line 1221
    const-string v0, "process_name"

    const-string v1, "android.hardware.camera.provider@2.4-service"

    const-string v3, "android.hardware.camera.provider@2.4-service_64"

    filled-new-array {v1, v3}, [Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/Intent;

    .line 1225
    invoke-virtual {p0, v2}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    .line 1226
    invoke-static {}, Lcom/android/camera/statistic/CameraStatUtil;->trackBroadcastKillService()V

    .line 1227
    return-void
.end method

.method public static broadcastNewPicture(Landroid/content/Context;Landroid/net/Uri;)V
    .locals 2

    .line 1209
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x18

    if-ge v0, v1, :cond_0

    .line 1210
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.hardware.action.NEW_PICTURE"

    invoke-direct {v0, v1, p1}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    invoke-virtual {p0, v0}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    .line 1212
    new-instance v0, Landroid/content/Intent;

    const-string v1, "com.android.camera.NEW_PICTURE"

    invoke-direct {v0, v1, p1}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    invoke-virtual {p0, v0}, Landroid/content/Context;->sendBroadcast(Landroid/content/Intent;)V

    .line 1214
    :cond_0
    return-void
.end method

.method public static calcDualCameraWatermarkLocation(IIIIFFF)[I
    .locals 2

    .line 3153
    invoke-static {p0, p1}, Ljava/lang/Math;->min(II)I

    move-result p0

    .line 3154
    int-to-float p0, p0

    const/high16 p1, 0x44870000    # 1080.0f

    div-float/2addr p0, p1

    .line 3156
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object p1

    invoke-virtual {p1}, Lcom/mi/config/a;->fI()Z

    move-result p1

    .line 3157
    const/high16 v0, 0x3f800000    # 1.0f

    if-eqz p1, :cond_0

    .line 3158
    const v1, 0x7f0a012e

    invoke-static {v1, v0}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v1

    goto :goto_0

    .line 3159
    :cond_0
    nop

    .line 3160
    move v1, v0

    :goto_0
    mul-float/2addr p4, p0

    mul-float/2addr p4, v1

    invoke-static {p4}, Ljava/lang/Math;->round(F)I

    move-result p4

    and-int/lit8 p4, p4, -0x2

    .line 3161
    mul-int/2addr p2, p4

    div-int/2addr p2, p3

    and-int/lit8 p2, p2, -0x2

    .line 3162
    mul-float/2addr p5, p0

    invoke-static {p5}, Ljava/lang/Math;->round(F)I

    move-result p3

    and-int/lit8 p3, p3, -0x2

    .line 3164
    if-eqz p1, :cond_1

    .line 3165
    const p1, 0x7f0a012f

    invoke-static {p1, v0}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v0

    goto :goto_1

    .line 3166
    :cond_1
    nop

    .line 3167
    :goto_1
    mul-float/2addr p6, p0

    mul-float/2addr p6, v0

    invoke-static {p6}, Ljava/lang/Math;->round(F)I

    move-result p0

    and-int/lit8 p0, p0, -0x2

    .line 3169
    const/4 p1, 0x4

    new-array p1, p1, [I

    const/4 p5, 0x0

    aput p2, p1, p5

    const/4 p2, 0x1

    aput p4, p1, p2

    const/4 p2, 0x2

    aput p3, p1, p2

    const/4 p2, 0x3

    aput p0, p1, p2

    return-object p1
.end method

.method public static calcNavigationBarHeight(Landroid/content/Context;)I
    .locals 3

    .line 1799
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 1800
    const v0, 0x7f0a0055

    invoke-virtual {p0, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p0

    .line 1801
    sget v0, Lcom/android/camera/Util;->sWindowHeight:I

    sget v1, Lcom/android/camera/Util;->sWindowWidth:I

    mul-int/lit8 v1, v1, 0x10

    div-int/lit8 v1, v1, 0x9

    sub-int/2addr v0, v1

    .line 1802
    if-lez v0, :cond_0

    .line 1803
    sub-int/2addr v0, p0

    goto :goto_0

    .line 1805
    :cond_0
    const/4 v0, 0x0

    .line 1807
    :goto_0
    const-string p0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "calculate navBarHeight="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {p0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1808
    return v0
.end method

.method public static final calculateDefaultPreviewEdgeSlop(Landroid/content/Context;)I
    .locals 3

    .line 2022
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    .line 2023
    iget v1, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    int-to-float v1, v1

    iget v2, v0, Landroid/util/DisplayMetrics;->xdpi:F

    div-float/2addr v1, v2

    .line 2024
    iget v2, v0, Landroid/util/DisplayMetrics;->heightPixels:I

    int-to-float v2, v2

    iget v0, v0, Landroid/util/DisplayMetrics;->ydpi:F

    div-float/2addr v2, v0

    .line 2025
    mul-float/2addr v1, v1

    mul-float/2addr v2, v2

    add-float/2addr v1, v2

    float-to-double v0, v1

    invoke-static {v0, v1}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v0

    double-to-float v0, v0

    .line 2027
    const/high16 v1, 0x40a00000    # 5.0f

    cmpg-float v0, v0, v1

    if-gez v0, :cond_0

    .line 2029
    const v0, 0x7f0a00a8

    goto :goto_0

    .line 2031
    :cond_0
    const v0, 0x7f0a00a9

    .line 2033
    :goto_0
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p0

    return p0
.end method

.method public static checkDeviceHasNavigationBar(Landroid/content/Context;)Z
    .locals 1

    .line 1822
    sget-object p0, Lcom/android/camera/Util;->sWindowManager:Landroid/view/IWindowManager;

    if-nez p0, :cond_0

    .line 1823
    const-string p0, "window"

    .line 1824
    invoke-static {p0}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    .line 1823
    invoke-static {p0}, Landroid/view/IWindowManager$Stub;->asInterface(Landroid/os/IBinder;)Landroid/view/IWindowManager;

    move-result-object p0

    sput-object p0, Lcom/android/camera/Util;->sWindowManager:Landroid/view/IWindowManager;

    .line 1826
    :try_start_0
    sget-object p0, Lcom/android/camera/Util;->sWindowManager:Landroid/view/IWindowManager;

    invoke-interface {p0}, Landroid/view/IWindowManager;->hasNavigationBar()Z

    move-result p0

    sput-boolean p0, Lcom/android/camera/Util;->sHasNavigationBar:Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 1829
    goto :goto_0

    .line 1827
    :catch_0
    move-exception p0

    .line 1828
    const-string p0, "CameraUtil"

    const-string v0, "checkDeviceHasNavigationBar exception"

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1831
    :cond_0
    :goto_0
    sget-boolean p0, Lcom/android/camera/Util;->sHasNavigationBar:Z

    return p0
.end method

.method public static checkLockedOrientation(Landroid/app/Activity;)V
    .locals 2

    .line 733
    :try_start_0
    invoke-virtual {p0}, Landroid/app/Activity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const-string v1, "accelerometer_rotation"

    invoke-static {v0, v1}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;)I

    move-result v0

    .line 735
    if-nez v0, :cond_0

    .line 736
    invoke-virtual {p0}, Landroid/app/Activity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    const-string v0, "user_rotation"

    invoke-static {p0, v0}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;)I

    move-result p0

    sput p0, Lcom/android/camera/Util;->mLockedOrientation:I

    goto :goto_0

    .line 739
    :cond_0
    const/4 p0, -0x1

    sput p0, Lcom/android/camera/Util;->mLockedOrientation:I
    :try_end_0
    .catch Landroid/provider/Settings$SettingNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 743
    :goto_0
    goto :goto_1

    .line 741
    :catch_0
    move-exception p0

    .line 742
    const-string p0, "CameraUtil"

    const-string v0, "user rotation cannot found"

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 744
    :goto_1
    return-void
.end method

.method public static checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;)TT;"
        }
    .end annotation

    .line 686
    if-eqz p0, :cond_0

    .line 689
    return-object p0

    .line 687
    :cond_0
    new-instance p0, Ljava/lang/NullPointerException;

    invoke-direct {p0}, Ljava/lang/NullPointerException;-><init>()V

    throw p0
.end method

.method public static clamp(FFF)F
    .locals 1

    .line 719
    cmpl-float v0, p0, p2

    if-lez v0, :cond_0

    return p2

    .line 720
    :cond_0
    cmpg-float p2, p0, p1

    if-gez p2, :cond_1

    return p1

    .line 721
    :cond_1
    return p0
.end method

.method public static clamp(III)I
    .locals 0

    .line 713
    if-le p0, p2, :cond_0

    return p2

    .line 714
    :cond_0
    if-ge p0, p1, :cond_1

    return p1

    .line 715
    :cond_1
    return p0
.end method

.method public static clamp(JJJ)J
    .locals 1

    .line 725
    cmp-long v0, p0, p4

    if-lez v0, :cond_0

    return-wide p4

    .line 726
    :cond_0
    cmp-long p4, p0, p2

    if-gez p4, :cond_1

    return-wide p2

    .line 727
    :cond_1
    return-wide p0
.end method

.method public static clearMemoryLimit()V
    .locals 7

    .line 356
    sget-boolean v0, Lcom/android/camera/Util;->sClearMemoryLimit:Z

    if-nez v0, :cond_0

    .line 357
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 358
    invoke-static {}, Ldalvik/system/VMRuntime;->getRuntime()Ldalvik/system/VMRuntime;

    move-result-object v2

    invoke-virtual {v2}, Ldalvik/system/VMRuntime;->clearGrowthLimit()V

    .line 359
    const/4 v2, 0x1

    sput-boolean v2, Lcom/android/camera/Util;->sClearMemoryLimit:Z

    .line 360
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 361
    const-string v4, "CameraUtil"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "clearMemoryLimit() consume:"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sub-long/2addr v2, v0

    invoke-virtual {v5, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v4, v0}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 363
    :cond_0
    return-void
.end method

.method public static closeSafely(Ljava/io/Closeable;)V
    .locals 0

    .line 2289
    if-eqz p0, :cond_0

    .line 2291
    :try_start_0
    invoke-interface {p0}, Ljava/io/Closeable;->close()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 2294
    goto :goto_0

    .line 2292
    :catch_0
    move-exception p0

    .line 2293
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 2296
    :cond_0
    :goto_0
    return-void
.end method

.method public static closeSilently(Ljava/io/Closeable;)V
    .locals 0

    .line 594
    if-nez p0, :cond_0

    .line 595
    return-void

    .line 598
    :cond_0
    :try_start_0
    invoke-interface {p0}, Ljava/io/Closeable;->close()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 601
    goto :goto_0

    .line 599
    :catch_0
    move-exception p0

    .line 602
    :goto_0
    return-void
.end method

.method public static composeDepthMapPicture([B[B[BZILjava/lang/String;IIZZLcom/xiaomi/camera/core/PictureInfo;)[B
    .locals 19

    move-object/from16 v0, p2

    move-object/from16 v1, p5

    move/from16 v2, p6

    move/from16 v3, p7

    .line 2958
    const-string v4, "CameraUtil"

    const-string v5, "composeDepthMapPicture: process in portrait depth map picture"

    invoke-static {v4, v5}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2959
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    .line 2960
    new-instance v6, Lcom/android/camera2/ArcsoftDepthMap;

    move-object/from16 v7, p1

    invoke-direct {v6, v7}, Lcom/android/camera2/ArcsoftDepthMap;-><init>([B)V

    .line 2962
    nop

    .line 2963
    const/4 v7, 0x4

    new-array v10, v7, [I

    .line 2964
    const/4 v8, 0x0

    if-eqz p3, :cond_0

    .line 2965
    invoke-static {v2, v3, v10}, Lcom/android/camera/Util;->getDualCameraWatermarkData(II[I)[B

    move-result-object v9

    goto :goto_0

    .line 2968
    :cond_0
    move-object v9, v8

    .line 2969
    :goto_0
    new-array v12, v7, [I

    .line 2970
    if-eqz v1, :cond_1

    .line 2971
    const-string v7, "CameraUtil"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "generate a TimeWaterMarkData with :"

    invoke-virtual {v8, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v11, "x"

    invoke-virtual {v8, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2972
    invoke-static {v2, v3, v1, v12}, Lcom/android/camera/Util;->getTimeWaterMarkData(IILjava/lang/String;[I)[B

    move-result-object v1

    .line 2975
    move-object v11, v1

    goto :goto_1

    :cond_1
    move-object v11, v8

    :goto_1
    invoke-virtual {v6}, Lcom/android/camera2/ArcsoftDepthMap;->getDepthMapData()[B

    move-result-object v1

    .line 2976
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v2

    invoke-virtual {v2}, Lcom/mi/config/a;->fO()I

    move-result v7

    .line 2977
    array-length v2, v0

    array-length v3, v1

    move-object/from16 v8, p0

    move/from16 v13, p4

    move/from16 v14, p8

    move/from16 v15, p9

    move-object/from16 v16, p10

    move/from16 v17, v2

    move/from16 v18, v3

    invoke-virtual/range {v6 .. v18}, Lcom/android/camera2/ArcsoftDepthMap;->writePortraitExif(I[B[B[I[B[IIZZLcom/xiaomi/camera/core/PictureInfo;II)[B

    move-result-object v2

    .line 2981
    array-length v3, v2

    array-length v6, v0

    add-int/2addr v3, v6

    array-length v6, v1

    add-int/2addr v3, v6

    new-array v3, v3, [B

    .line 2983
    array-length v6, v2

    const/4 v7, 0x0

    invoke-static {v2, v7, v3, v7, v6}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 2985
    array-length v6, v2

    array-length v8, v0

    invoke-static {v0, v7, v3, v6, v8}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 2988
    array-length v2, v2

    array-length v0, v0

    add-int/2addr v2, v0

    array-length v0, v1

    invoke-static {v1, v7, v3, v2, v0}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 2990
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "composeDepthMapPicture: compose portrait picture cost: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2991
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v6

    sub-long/2addr v6, v4

    invoke-virtual {v1, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 2990
    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2993
    return-object v3
.end method

.method public static composeLiveShotPicture([BII[BJZLjava/lang/String;)[B
    .locals 17

    move-object/from16 v1, p0

    move/from16 v2, p1

    move/from16 v3, p2

    move-object/from16 v4, p3

    .line 2806
    move-object/from16 v5, p7

    const-string v0, "CameraUtil"

    const-string v6, "composeLiveShotPicture(): E"

    invoke-static {v0, v6}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2807
    const/4 v6, 0x0

    if-eqz v1, :cond_10

    array-length v0, v1

    if-nez v0, :cond_0

    goto/16 :goto_f

    .line 2812
    :cond_0
    if-eqz v4, :cond_f

    array-length v0, v4

    if-nez v0, :cond_1

    goto/16 :goto_e

    .line 2817
    :cond_1
    nop

    .line 2818
    const/4 v0, 0x4

    new-array v7, v0, [I

    .line 2820
    nop

    .line 2821
    new-array v8, v0, [I

    .line 2824
    nop

    .line 2825
    const/4 v9, 0x1

    const/4 v10, 0x0

    :try_start_0
    new-instance v11, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v11}, Ljava/io/ByteArrayOutputStream;-><init>()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2

    .line 2826
    :try_start_1
    new-instance v0, Lcom/android/gallery3d/exif/ExifInterface;

    invoke-direct {v0}, Lcom/android/gallery3d/exif/ExifInterface;-><init>()V

    .line 2827
    invoke-virtual {v0, v1}, Lcom/android/gallery3d/exif/ExifInterface;->readExif([B)V

    .line 2830
    invoke-virtual {v0, v9}, Lcom/android/gallery3d/exif/ExifInterface;->addFileTypeLiveShot(Z)Z

    .line 2832
    invoke-virtual {v0, v1, v11}, Lcom/android/gallery3d/exif/ExifInterface;->writeExif([BLjava/io/OutputStream;)V

    .line 2833
    invoke-virtual {v11}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v12
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 2834
    :try_start_2
    invoke-static {v10, v11}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 2836
    goto :goto_2

    .line 2834
    :catch_0
    move-exception v0

    goto :goto_1

    :catchall_0
    move-exception v0

    move-object v12, v10

    goto :goto_0

    .line 2825
    :catch_1
    move-exception v0

    move-object v12, v0

    :try_start_3
    throw v12
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 2834
    :catchall_1
    move-exception v0

    :goto_0
    :try_start_4
    invoke-static {v12, v11}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw v0
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_2

    :catch_2
    move-exception v0

    move-object v12, v10

    .line 2835
    :goto_1
    const-string v0, "CameraUtil"

    const-string v11, "composeLiveShotPicture(): Failed to insert xiaomi specific metadata"

    invoke-static {v0, v11}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2838
    :goto_2
    if-eqz v12, :cond_e

    array-length v0, v12

    array-length v11, v1

    if-gt v0, v11, :cond_2

    goto/16 :goto_d

    .line 2843
    :cond_2
    if-eqz p6, :cond_3

    .line 2844
    invoke-static {v2, v3, v7}, Lcom/android/camera/Util;->getDualCameraWatermarkData(II[I)[B

    move-result-object v0

    .line 2846
    move-object v11, v0

    goto :goto_3

    :cond_3
    move-object v11, v10

    :goto_3
    if-eqz v5, :cond_4

    invoke-virtual/range {p7 .. p7}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_4

    .line 2847
    invoke-static {v2, v3, v5, v8}, Lcom/android/camera/Util;->getTimeWaterMarkData(IILjava/lang/String;[I)[B

    move-result-object v0

    .line 2850
    move-object v2, v0

    goto :goto_4

    :cond_4
    move-object v2, v10

    .line 2852
    :goto_4
    :try_start_5
    invoke-static {}, Landroid/util/Xml;->newSerializer()Lorg/xmlpull/v1/XmlSerializer;

    move-result-object v0

    .line 2853
    new-instance v3, Ljava/io/StringWriter;

    invoke-direct {v3}, Ljava/io/StringWriter;-><init>()V

    .line 2854
    invoke-interface {v0, v3}, Lorg/xmlpull/v1/XmlSerializer;->setOutput(Ljava/io/Writer;)V

    .line 2855
    const-string v5, "UTF-8"

    invoke-static {v9}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v13

    invoke-interface {v0, v5, v13}, Lorg/xmlpull/v1/XmlSerializer;->startDocument(Ljava/lang/String;Ljava/lang/Boolean;)V

    .line 2857
    const/4 v13, 0x2

    if-eqz v11, :cond_6

    array-length v14, v11

    if-lez v14, :cond_6

    .line 2858
    const-string v14, "lenswatermark"

    invoke-interface {v0, v10, v14}, Lorg/xmlpull/v1/XmlSerializer;->startTag(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2859
    const-string v14, "offset"

    array-length v15, v11

    .line 2860
    if-eqz v2, :cond_5

    array-length v5, v2

    goto :goto_5

    :cond_5
    move v5, v6

    :goto_5
    add-int/2addr v15, v5

    array-length v5, v4

    add-int/2addr v15, v5

    invoke-static {v15}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v5

    .line 2859
    invoke-interface {v0, v10, v14, v5}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2861
    const-string v5, "length"

    array-length v14, v11

    invoke-static {v14}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v14

    invoke-interface {v0, v10, v5, v14}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2862
    const-string v5, "width"

    aget v14, v7, v6

    invoke-static {v14}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v14

    invoke-interface {v0, v10, v5, v14}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2863
    const-string v5, "height"

    aget v14, v7, v9

    invoke-static {v14}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v14

    invoke-interface {v0, v10, v5, v14}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2864
    const-string v5, "paddingx"

    aget v14, v7, v13

    invoke-static {v14}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v14

    invoke-interface {v0, v10, v5, v14}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2865
    const-string v5, "paddingy"

    const/4 v14, 0x3

    aget v7, v7, v14

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2866
    const-string v5, "lenswatermark"

    invoke-interface {v0, v10, v5}, Lorg/xmlpull/v1/XmlSerializer;->endTag(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2869
    :cond_6
    if-eqz v2, :cond_7

    array-length v5, v2

    if-lez v5, :cond_7

    .line 2870
    const-string v5, "timewatermark"

    invoke-interface {v0, v10, v5}, Lorg/xmlpull/v1/XmlSerializer;->startTag(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2871
    const-string v5, "offset"

    array-length v7, v2

    array-length v14, v4

    add-int/2addr v7, v14

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2872
    const-string v5, "length"

    array-length v7, v2

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2873
    const-string v5, "width"

    aget v7, v8, v6

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2874
    const-string v5, "height"

    aget v7, v8, v9

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2875
    const-string v5, "paddingx"

    aget v7, v8, v13

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2876
    const-string v5, "paddingy"

    const/4 v7, 0x3

    aget v7, v8, v7

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v0, v10, v5, v7}, Lorg/xmlpull/v1/XmlSerializer;->attribute(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2877
    const-string v5, "timewatermark"

    invoke-interface {v0, v10, v5}, Lorg/xmlpull/v1/XmlSerializer;->endTag(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;

    .line 2880
    :cond_7
    invoke-interface {v0}, Lorg/xmlpull/v1/XmlSerializer;->endDocument()V

    .line 2881
    invoke-virtual {v3}, Ljava/io/StringWriter;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_3

    .line 2884
    goto :goto_6

    .line 2882
    :catch_3
    move-exception v0

    .line 2883
    const-string v0, "CameraUtil"

    const-string v3, "composeLiveShotPicture(): Failed to generate xiaomi xmp metadata"

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2886
    move-object v0, v10

    :goto_6
    if-nez v0, :cond_8

    .line 2887
    const-string v0, "CameraUtil"

    const-string v2, "composeLiveShotPicture(): #2: return original jpeg"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2888
    return-object v1

    .line 2892
    :cond_8
    nop

    .line 2893
    :try_start_6
    new-instance v3, Ljava/io/ByteArrayInputStream;

    invoke-direct {v3, v12}, Ljava/io/ByteArrayInputStream;-><init>([B)V
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_8

    .line 2894
    :try_start_7
    new-instance v5, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v5}, Ljava/io/ByteArrayOutputStream;-><init>()V
    :try_end_7
    .catch Ljava/lang/Throwable; {:try_start_7 .. :try_end_7} :catch_6
    .catchall {:try_start_7 .. :try_end_7} :catchall_4

    .line 2893
    nop

    .line 2895
    :try_start_8
    invoke-static {}, Lcom/android/camera/XmpHelper;->createXMPMeta()Lcom/adobe/xmp/XMPMeta;

    move-result-object v7

    .line 2896
    const-string v8, "http://ns.google.com/photos/1.0/camera/"

    const-string v13, "MicroVideoVersion"

    invoke-interface {v7, v8, v13, v9}, Lcom/adobe/xmp/XMPMeta;->setPropertyInteger(Ljava/lang/String;Ljava/lang/String;I)V

    .line 2899
    const-string v8, "http://ns.google.com/photos/1.0/camera/"

    const-string v13, "MicroVideo"

    invoke-interface {v7, v8, v13, v9}, Lcom/adobe/xmp/XMPMeta;->setPropertyInteger(Ljava/lang/String;Ljava/lang/String;I)V

    .line 2902
    const-string v8, "http://ns.google.com/photos/1.0/camera/"

    const-string v9, "MicroVideoOffset"

    array-length v13, v4

    invoke-interface {v7, v8, v9, v13}, Lcom/adobe/xmp/XMPMeta;->setPropertyInteger(Ljava/lang/String;Ljava/lang/String;I)V

    .line 2905
    const-string v8, "http://ns.google.com/photos/1.0/camera/"

    const-string v9, "MicroVideoPresentationTimestampUs"

    move-wide/from16 v13, p4

    invoke-interface {v7, v8, v9, v13, v14}, Lcom/adobe/xmp/XMPMeta;->setPropertyLong(Ljava/lang/String;Ljava/lang/String;J)V

    .line 2908
    if-eqz v0, :cond_9

    .line 2909
    const-string v8, "http://ns.xiaomi.com/photos/1.0/camera/"

    const-string v9, "XMPMeta"

    invoke-interface {v7, v8, v9, v0}, Lcom/adobe/xmp/XMPMeta;->setProperty(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    .line 2914
    :cond_9
    invoke-static {v3, v5, v7}, Lcom/android/camera/XmpHelper;->writeXMPMeta(Ljava/io/InputStream;Ljava/io/OutputStream;Lcom/adobe/xmp/XMPMeta;)Z

    .line 2917
    if-eqz v11, :cond_a

    array-length v0, v11

    if-lez v0, :cond_a

    .line 2918
    invoke-virtual {v5, v11}, Ljava/io/ByteArrayOutputStream;->write([B)V

    .line 2922
    :cond_a
    if-eqz v2, :cond_b

    array-length v0, v2

    if-lez v0, :cond_b

    .line 2923
    invoke-virtual {v5, v2}, Ljava/io/ByteArrayOutputStream;->write([B)V

    .line 2925
    :cond_b
    invoke-virtual {v5}, Ljava/io/ByteArrayOutputStream;->flush()V

    .line 2926
    invoke-virtual {v5}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v2
    :try_end_8
    .catch Ljava/lang/Throwable; {:try_start_8 .. :try_end_8} :catch_5
    .catchall {:try_start_8 .. :try_end_8} :catchall_2

    .line 2927
    :try_start_9
    invoke-static {v10, v5}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_9
    .catch Ljava/lang/Throwable; {:try_start_9 .. :try_end_9} :catch_4
    .catchall {:try_start_9 .. :try_end_9} :catchall_5

    :try_start_a
    invoke-static {v10, v3}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_a
    .catch Ljava/lang/Exception; {:try_start_a .. :try_end_a} :catch_7

    .line 2929
    goto :goto_b

    .line 2893
    :catch_4
    move-exception v0

    goto :goto_8

    .line 2927
    :catchall_2
    move-exception v0

    move-object v2, v10

    goto :goto_7

    .line 2893
    :catch_5
    move-exception v0

    move-object v2, v0

    :try_start_b
    throw v2
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_3

    .line 2927
    :catchall_3
    move-exception v0

    :goto_7
    :try_start_c
    invoke-static {v2, v5}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw v0
    :try_end_c
    .catch Ljava/lang/Throwable; {:try_start_c .. :try_end_c} :catch_6
    .catchall {:try_start_c .. :try_end_c} :catchall_4

    :catchall_4
    move-exception v0

    move-object v2, v10

    goto :goto_9

    .line 2893
    :catch_6
    move-exception v0

    move-object v2, v10

    :goto_8
    move-object v10, v0

    :try_start_d
    throw v10
    :try_end_d
    .catchall {:try_start_d .. :try_end_d} :catchall_5

    .line 2927
    :catchall_5
    move-exception v0

    :goto_9
    :try_start_e
    invoke-static {v10, v3}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw v0
    :try_end_e
    .catch Ljava/lang/Exception; {:try_start_e .. :try_end_e} :catch_7

    :catch_7
    move-exception v0

    goto :goto_a

    :catch_8
    move-exception v0

    move-object v2, v10

    .line 2928
    :goto_a
    const-string v0, "CameraUtil"

    const-string v3, "composeLiveShotPicture(): failed to insert xmp metadata"

    invoke-static {v0, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2931
    :goto_b
    if-eqz v2, :cond_d

    array-length v0, v2

    array-length v3, v12

    if-gt v0, v3, :cond_c

    goto :goto_c

    .line 2937
    :cond_c
    array-length v0, v2

    array-length v1, v4

    add-int/2addr v0, v1

    .line 2938
    const-string v1, "CameraUtil"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "composeLiveShotPicture(): file size = "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v1, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2939
    new-array v0, v0, [B

    .line 2940
    array-length v1, v2

    invoke-static {v2, v6, v0, v6, v1}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 2941
    array-length v1, v2

    array-length v2, v4

    invoke-static {v4, v6, v0, v1, v2}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 2943
    const-string v1, "CameraUtil"

    const-string v2, "composeLiveShotPicture(): X"

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2944
    return-object v0

    .line 2932
    :cond_d
    :goto_c
    const-string v0, "CameraUtil"

    const-string v2, "composeLiveShotPicture(): #3: return original jpeg"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2933
    return-object v1

    .line 2839
    :cond_e
    :goto_d
    const-string v0, "CameraUtil"

    const-string v2, "composeLiveShotPicture(): #1: return original jpeg"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2840
    return-object v1

    .line 2813
    :cond_f
    :goto_e
    const-string v0, "CameraUtil"

    const-string v2, "composeLiveShotPicture(): The corresponding movie of LiveShot is empty"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2814
    return-object v1

    .line 2808
    :cond_10
    :goto_f
    const-string v0, "CameraUtil"

    const-string v1, "composeLiveShotPicture(): The primary photo of LiveShot is empty"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2809
    new-array v0, v6, [B

    return-object v0
.end method

.method private static computeInitialSampleSize(Landroid/graphics/BitmapFactory$Options;II)I
    .locals 8

    .line 450
    iget v0, p0, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    int-to-double v0, v0

    .line 451
    iget p0, p0, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    int-to-double v2, p0

    .line 453
    const/4 p0, 0x1

    if-gez p2, :cond_0

    .line 455
    move v4, p0

    goto :goto_0

    .line 454
    :cond_0
    mul-double v4, v0, v2

    int-to-double v6, p2

    div-double/2addr v4, v6

    invoke-static {v4, v5}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v4

    double-to-int v4, v4

    .line 455
    :goto_0
    if-gez p1, :cond_1

    const/16 v0, 0x80

    goto :goto_1

    .line 456
    :cond_1
    int-to-double v5, p1

    div-double/2addr v0, v5

    invoke-static {v0, v1}, Ljava/lang/Math;->floor(D)D

    move-result-wide v0

    div-double/2addr v2, v5

    .line 457
    invoke-static {v2, v3}, Ljava/lang/Math;->floor(D)D

    move-result-wide v2

    .line 456
    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->min(DD)D

    move-result-wide v0

    double-to-int v0, v0

    .line 459
    :goto_1
    if-ge v0, v4, :cond_2

    .line 461
    return v4

    .line 464
    :cond_2
    if-gez p2, :cond_3

    if-gez p1, :cond_3

    .line 465
    return p0

    .line 466
    :cond_3
    if-gez p1, :cond_4

    .line 467
    return v4

    .line 469
    :cond_4
    return v0
.end method

.method public static computeSampleSize(Landroid/graphics/BitmapFactory$Options;II)I
    .locals 0

    .line 432
    invoke-static {p0, p1, p2}, Lcom/android/camera/Util;->computeInitialSampleSize(Landroid/graphics/BitmapFactory$Options;II)I

    move-result p0

    .line 436
    const/16 p1, 0x8

    if-gt p0, p1, :cond_0

    .line 437
    nop

    .line 438
    const/4 p1, 0x1

    :goto_0
    if-ge p1, p0, :cond_1

    .line 439
    shl-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 442
    :cond_0
    add-int/lit8 p0, p0, 0x7

    div-int/2addr p0, p1

    mul-int/2addr p1, p0

    .line 445
    :cond_1
    return p1
.end method

.method public static controlAEStateToString(Ljava/lang/Integer;)Ljava/lang/String;
    .locals 0

    .line 3396
    if-nez p0, :cond_0

    .line 3397
    const-string p0, "null"

    return-object p0

    .line 3399
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    packed-switch p0, :pswitch_data_0

    .line 3413
    const-string p0, "unknown"

    return-object p0

    .line 3411
    :pswitch_0
    const-string p0, "precapture"

    return-object p0

    .line 3409
    :pswitch_1
    const-string p0, "flash_required"

    return-object p0

    .line 3407
    :pswitch_2
    const-string p0, "locked"

    return-object p0

    .line 3405
    :pswitch_3
    const-string p0, "converged"

    return-object p0

    .line 3403
    :pswitch_4
    const-string p0, "searching"

    return-object p0

    .line 3401
    :pswitch_5
    const-string p0, "inactive"

    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static controlAFStateToString(Ljava/lang/Integer;)Ljava/lang/String;
    .locals 0

    .line 3369
    if-nez p0, :cond_0

    .line 3370
    const-string p0, "null"

    return-object p0

    .line 3372
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    packed-switch p0, :pswitch_data_0

    .line 3388
    const-string p0, "unknown"

    return-object p0

    .line 3386
    :pswitch_0
    const-string p0, "passive_unfocused"

    return-object p0

    .line 3384
    :pswitch_1
    const-string p0, "not_focus_locked"

    return-object p0

    .line 3382
    :pswitch_2
    const-string p0, "focused_locked"

    return-object p0

    .line 3380
    :pswitch_3
    const-string p0, "active_scan"

    return-object p0

    .line 3378
    :pswitch_4
    const-string p0, "passive_focused"

    return-object p0

    .line 3376
    :pswitch_5
    const-string p0, "passive_scan"

    return-object p0

    .line 3374
    :pswitch_6
    const-string p0, "inactive"

    return-object p0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static controlAWBStateToString(Ljava/lang/Integer;)Ljava/lang/String;
    .locals 0

    .line 3421
    if-nez p0, :cond_0

    .line 3422
    const-string p0, "null"

    return-object p0

    .line 3424
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    packed-switch p0, :pswitch_data_0

    .line 3434
    const-string p0, "unknown"

    return-object p0

    .line 3432
    :pswitch_0
    const-string p0, "locked"

    return-object p0

    .line 3430
    :pswitch_1
    const-string p0, "converged"

    return-object p0

    .line 3428
    :pswitch_2
    const-string p0, "searching"

    return-object p0

    .line 3426
    :pswitch_3
    const-string p0, "inactive"

    return-object p0

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static convertOutputFormatToFileExt(I)Ljava/lang/String;
    .locals 1

    .line 3196
    const/4 v0, 0x2

    if-ne p0, v0, :cond_0

    .line 3197
    const-string p0, ".mp4"

    return-object p0

    .line 3199
    :cond_0
    const-string p0, ".3gp"

    return-object p0
.end method

.method public static final convertOutputFormatToMimeType(I)Ljava/lang/String;
    .locals 1

    .line 3189
    const/4 v0, 0x2

    if-ne p0, v0, :cond_0

    .line 3190
    const-string p0, "video/mp4"

    return-object p0

    .line 3192
    :cond_0
    const-string p0, "video/3gpp"

    return-object p0
.end method

.method public static convertSizeToQuality(Lcom/android/camera/CameraSize;)I
    .locals 2

    .line 2520
    iget v0, p0, Lcom/android/camera/CameraSize;->width:I

    .line 2521
    iget v1, p0, Lcom/android/camera/CameraSize;->height:I

    .line 2522
    if-ge v0, v1, :cond_0

    .line 2523
    iget v0, p0, Lcom/android/camera/CameraSize;->height:I

    .line 2524
    iget v1, p0, Lcom/android/camera/CameraSize;->width:I

    .line 2527
    :cond_0
    const/16 p0, 0x780

    if-ne v0, p0, :cond_1

    const/16 p0, 0x438

    if-ne v1, p0, :cond_1

    .line 2528
    const/4 p0, 0x6

    return p0

    .line 2529
    :cond_1
    const/16 p0, 0xf00

    if-ne v0, p0, :cond_2

    const/16 p0, 0x870

    if-ne v1, p0, :cond_2

    .line 2530
    const/16 p0, 0x8

    return p0

    .line 2531
    :cond_2
    const/16 p0, 0x500

    if-ne v0, p0, :cond_3

    const/16 p0, 0x2d0

    if-ne v1, p0, :cond_3

    .line 2532
    const/4 p0, 0x5

    return p0

    .line 2533
    :cond_3
    const/16 p0, 0x280

    if-lt v0, p0, :cond_4

    const/16 p0, 0x1e0

    if-ne v1, p0, :cond_4

    .line 2534
    const/4 p0, 0x4

    return p0

    .line 2537
    :cond_4
    const/4 p0, -0x1

    return p0
.end method

.method public static coverSubYuvImage([BII[B[I)V
    .locals 8

    .line 3640
    const/4 v0, 0x1

    aget v1, p4, v0

    mul-int/2addr v1, p1

    const/4 v2, 0x0

    aget v3, p4, v2

    add-int/2addr v1, v3

    .line 3641
    nop

    .line 3643
    move v4, v1

    move v1, v2

    move v3, v1

    :goto_0
    const/4 v5, 0x3

    aget v6, p4, v5

    const/4 v7, 0x2

    if-ge v1, v6, :cond_0

    .line 3644
    aget v5, p4, v7

    invoke-static {p3, v3, p0, v4, v5}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 3645
    aget v5, p4, v7

    add-int/2addr v3, v5

    .line 3646
    add-int/2addr v4, p1

    .line 3643
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 3650
    :cond_0
    mul-int/2addr p2, p1

    aget v0, p4, v0

    div-int/2addr v0, v7

    mul-int/2addr v0, p1

    add-int/2addr p2, v0

    aget v0, p4, v2

    add-int/2addr p2, v0

    .line 3651
    :goto_1
    aget v0, p4, v5

    div-int/2addr v0, v7

    if-ge v2, v0, :cond_1

    .line 3652
    aget v0, p4, v7

    invoke-static {p3, v3, p0, p2, v0}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 3653
    add-int/2addr p2, p1

    .line 3654
    aget v0, p4, v7

    add-int/2addr v3, v0

    .line 3651
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 3656
    :cond_1
    return-void
.end method

.method public static createFile(Ljava/io/File;)Z
    .locals 3

    .line 1392
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1393
    const/4 p0, 0x0

    return p0

    .line 1396
    :cond_0
    invoke-virtual {p0}, Ljava/io/File;->getParent()Ljava/lang/String;

    move-result-object v0

    .line 1397
    if-eqz v0, :cond_1

    .line 1398
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    const/16 v0, 0x1ff

    const/4 v2, -0x1

    invoke-static {v1, v0, v2, v2}, Lcom/android/camera/Util;->mkdirs(Ljava/io/File;III)Z

    .line 1401
    :cond_1
    :try_start_0
    invoke-virtual {p0}, Ljava/io/File;->createNewFile()Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1404
    goto :goto_0

    .line 1402
    :catch_0
    move-exception p0

    .line 1405
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public static createGooglePhotosCompatibleLiveShot()Z
    .locals 2

    .line 3085
    const-string v0, "liveshotgpc"

    const/4 v1, 0x3

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    return v0
.end method

.method public static createJpegName(J)Ljava/lang/String;
    .locals 2

    .line 1184
    sget-object v0, Lcom/android/camera/Util;->sImageFileNamer:Lcom/android/camera/Util$ImageFileNamer;

    monitor-enter v0

    .line 1185
    :try_start_0
    sget-object v1, Lcom/android/camera/Util;->sImageFileNamer:Lcom/android/camera/Util$ImageFileNamer;

    invoke-virtual {v1, p0, p1}, Lcom/android/camera/Util$ImageFileNamer;->generateName(J)Ljava/lang/String;

    move-result-object p0

    monitor-exit v0

    return-object p0

    .line 1186
    :catchall_0
    move-exception p0

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public static distance(FFFF)F
    .locals 0

    .line 707
    sub-float/2addr p0, p2

    .line 708
    sub-float/2addr p1, p3

    .line 709
    mul-float/2addr p0, p0

    mul-float/2addr p1, p1

    add-float/2addr p0, p1

    float-to-double p0, p0

    invoke-static {p0, p1}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide p0

    double-to-float p0, p0

    return p0
.end method

.method public static dpToPixel(F)I
    .locals 1

    .line 366
    sget v0, Lcom/android/camera/Util;->sPixelDensity:F

    mul-float/2addr v0, p0

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result p0

    return p0
.end method

.method public static dumpBackTrace(Ljava/lang/String;)V
    .locals 7

    .line 294
    new-instance v0, Ljava/lang/RuntimeException;

    invoke-direct {v0}, Ljava/lang/RuntimeException;-><init>()V

    .line 296
    const-string v1, "CameraUtil"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "["

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "]\n"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 297
    const-string v1, "CameraUtil"

    const-string v2, "**********print backtrace start *************"

    invoke-static {v1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 298
    invoke-virtual {v0}, Ljava/lang/RuntimeException;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    aget-object v3, v0, v2

    .line 299
    const-string v4, "CameraUtil"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "["

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "]:backtrace: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 300
    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getClassName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getMethodName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StackTraceElement;->getLineNumber()I

    move-result v3

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 299
    invoke-static {v4, v3}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 298
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 302
    :cond_0
    const-string v0, "CameraUtil"

    const-string v1, "**********print backtrace end *************"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 303
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "["

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "]\n"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 304
    return-void
.end method

.method public static dumpImageInfo(Ljava/lang/String;Landroid/media/Image;)V
    .locals 4

    .line 3049
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 3050
    invoke-virtual {p1}, Landroid/media/Image;->getPlanes()[Landroid/media/Image$Plane;

    move-result-object p1

    .line 3051
    const/4 v1, 0x0

    :goto_0
    array-length v2, p1

    if-ge v1, v2, :cond_0

    .line 3052
    aget-object v2, p1, v1

    .line 3053
    const-string v3, "plane_"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ": "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 3054
    invoke-virtual {v2}, Landroid/media/Image$Plane;->getPixelStride()I

    move-result v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, "|"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 3055
    invoke-virtual {v2}, Landroid/media/Image$Plane;->getRowStride()I

    move-result v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, "|"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 3056
    invoke-virtual {v2}, Landroid/media/Image$Plane;->getBuffer()Ljava/nio/ByteBuffer;

    move-result-object v2

    invoke-virtual {v2}, Ljava/nio/ByteBuffer;->remaining()I

    move-result v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "\n"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 3051
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 3059
    :cond_0
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3060
    return-void
.end method

.method public static dumpMatrix([F)Ljava/lang/String;
    .locals 7

    .line 1158
    array-length v0, p0

    .line 1159
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "["

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 1160
    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v0, :cond_1

    .line 1161
    const-string v4, "%f"

    const/4 v5, 0x1

    new-array v5, v5, [Ljava/lang/Object;

    aget v6, p0, v3

    invoke-static {v6}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v6

    aput-object v6, v5, v2

    invoke-static {v4, v5}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1162
    add-int/lit8 v4, v0, -0x1

    if-eq v3, v4, :cond_0

    .line 1163
    const-string v4, " "

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1160
    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 1166
    :cond_1
    const-string p0, "]"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1167
    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static dumpRect(Landroid/graphics/RectF;Ljava/lang/String;)V
    .locals 2

    .line 1113
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "=("

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Landroid/graphics/RectF;->left:F

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p1, ","

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Landroid/graphics/RectF;->top:F

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p1, ","

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Landroid/graphics/RectF;->right:F

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p1, ","

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 1115
    return-void
.end method

.method public static dumpToBitmap(IIIILjava/lang/String;)Ljava/nio/ByteBuffer;
    .locals 8

    .line 1171
    mul-int v0, p2, p3

    mul-int/lit8 v0, v0, 0x4

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocate(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    .line 1172
    const/16 v5, 0x1908

    const/16 v6, 0x1401

    move v1, p0

    move v2, p1

    move v3, p2

    move v4, p3

    move-object v7, v0

    invoke-static/range {v1 .. v7}, Landroid/opengl/GLES20;->glReadPixels(IIIIIILjava/nio/Buffer;)V

    .line 1173
    if-eqz v0, :cond_0

    .line 1174
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string p1, "tex_"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-static {v1, v2}, Lcom/android/camera/Util;->createJpegName(J)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1175
    const-string p1, ".jpg"

    invoke-static {p0, p1}, Lcom/android/camera/storage/Storage;->generateFilepath(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 1176
    sget-object p1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, p2, p3, p1, p0}, Lcom/android/camera/Util;->saveBitmap(Ljava/nio/Buffer;IILandroid/graphics/Bitmap$Config;Ljava/lang/String;)Z

    .line 1177
    const-string p1, "CameraUtil"

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "dump to "

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1179
    :cond_0
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->rewind()Ljava/nio/Buffer;

    .line 1180
    return-object v0
.end method

.method public static enterLightsOutMode(Landroid/view/Window;)V
    .locals 2

    .line 1276
    invoke-virtual {p0}, Landroid/view/Window;->getAttributes()Landroid/view/WindowManager$LayoutParams;

    move-result-object v0

    .line 1277
    iget v1, v0, Landroid/view/WindowManager$LayoutParams;->systemUiVisibility:I

    or-int/lit8 v1, v1, 0x1

    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->systemUiVisibility:I

    .line 1278
    invoke-virtual {p0, v0}, Landroid/view/Window;->setAttributes(Landroid/view/WindowManager$LayoutParams;)V

    .line 1279
    return-void
.end method

.method public static equals(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 0

    .line 693
    if-eq p0, p1, :cond_1

    if-eqz p0, :cond_0

    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method public static execCommand(Ljava/lang/String;Z)Ljava/lang/String;
    .locals 6

    .line 2737
    const/4 v0, 0x3

    new-array v0, v0, [Ljava/lang/String;

    const-string v1, "sh"

    const/4 v2, 0x0

    aput-object v1, v0, v2

    const-string v1, "-c"

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const/4 v1, 0x2

    aput-object p0, v0, v1

    .line 2738
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    .line 2739
    const-string p0, ""

    .line 2740
    nop

    .line 2742
    :try_start_0
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v3

    invoke-virtual {v3, v0}, Ljava/lang/Runtime;->exec([Ljava/lang/String;)Ljava/lang/Process;

    move-result-object v0

    .line 2743
    invoke-virtual {v0}, Ljava/lang/Process;->waitFor()I

    move-result v3

    if-eqz v3, :cond_0

    .line 2744
    const-string p1, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "exit value = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/Process;->exitValue()I

    move-result v0

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2745
    return-object p0

    .line 2747
    :cond_0
    new-instance v3, Ljava/io/BufferedReader;

    new-instance v4, Ljava/io/InputStreamReader;

    .line 2748
    invoke-virtual {v0}, Ljava/lang/Process;->getInputStream()Ljava/io/InputStream;

    move-result-object v0

    invoke-direct {v4, v0}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v3, v4}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 2749
    new-instance v0, Ljava/lang/StringBuffer;

    invoke-direct {v0}, Ljava/lang/StringBuffer;-><init>()V

    .line 2750
    nop

    .line 2751
    if-eqz p1, :cond_1

    .line 2752
    :goto_0
    invoke-virtual {v3}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 2753
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\r\n"

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    goto :goto_0

    .line 2756
    :cond_1
    :goto_1
    invoke-virtual {v3}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 2757
    invoke-virtual {v0, p1}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    goto :goto_1

    .line 2760
    :cond_2
    invoke-virtual {v3}, Ljava/io/BufferedReader;->close()V

    .line 2761
    invoke-virtual {v0}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2

    .line 2762
    :try_start_1
    const-string p0, "CameraUtil"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "execCommand value="

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " cost="

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sub-long/2addr v3, v1

    invoke-virtual {v0, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    .line 2769
    nop

    .line 2770
    move-object p0, p1

    goto :goto_4

    .line 2766
    :catch_0
    move-exception p0

    move-object v5, p1

    move-object p1, p0

    move-object p0, v5

    goto :goto_2

    .line 2763
    :catch_1
    move-exception p0

    move-object v5, p1

    move-object p1, p0

    move-object p0, v5

    goto :goto_3

    .line 2766
    :catch_2
    move-exception p1

    .line 2767
    :goto_2
    const-string v0, "CameraUtil"

    const-string v1, "execCommand IOException"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2768
    invoke-virtual {p1}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_4

    .line 2763
    :catch_3
    move-exception p1

    .line 2764
    :goto_3
    const-string v0, "CameraUtil"

    const-string v1, "execCommand InterruptedException"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2765
    invoke-virtual {p1}, Ljava/lang/InterruptedException;->printStackTrace()V

    .line 2769
    nop

    .line 2770
    :goto_4
    return-object p0
.end method

.method public static expandViewTouchDelegate(Landroid/view/View;)V
    .locals 3

    .line 1494
    invoke-virtual {p0}, Landroid/view/View;->isShown()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1495
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    .line 1496
    invoke-virtual {p0, v0}, Landroid/view/View;->getHitRect(Landroid/graphics/Rect;)V

    .line 1497
    const/high16 v1, 0x41200000    # 10.0f

    invoke-static {v1}, Lcom/android/camera/Util;->dpToPixel(F)I

    move-result v1

    .line 1499
    iget v2, v0, Landroid/graphics/Rect;->top:I

    sub-int/2addr v2, v1

    iput v2, v0, Landroid/graphics/Rect;->top:I

    .line 1500
    iget v2, v0, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v2, v1

    iput v2, v0, Landroid/graphics/Rect;->bottom:I

    .line 1501
    iget v2, v0, Landroid/graphics/Rect;->left:I

    sub-int/2addr v2, v1

    iput v2, v0, Landroid/graphics/Rect;->left:I

    .line 1502
    iget v2, v0, Landroid/graphics/Rect;->right:I

    add-int/2addr v2, v1

    iput v2, v0, Landroid/graphics/Rect;->right:I

    .line 1504
    new-instance v1, Landroid/view/TouchDelegate;

    invoke-direct {v1, v0, p0}, Landroid/view/TouchDelegate;-><init>(Landroid/graphics/Rect;Landroid/view/View;)V

    .line 1505
    const-class v0, Landroid/view/View;

    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1506
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p0

    check-cast p0, Landroid/view/View;

    invoke-virtual {p0, v1}, Landroid/view/View;->setTouchDelegate(Landroid/view/TouchDelegate;)V

    .line 1508
    :cond_0
    goto :goto_0

    .line 1509
    :cond_1
    const-class v0, Landroid/view/View;

    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Class;->isInstance(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1510
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p0

    check-cast p0, Landroid/view/View;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Landroid/view/View;->setTouchDelegate(Landroid/view/TouchDelegate;)V

    .line 1513
    :cond_2
    :goto_0
    return-void
.end method

.method public static fadeIn(Landroid/view/View;)V
    .locals 1

    .line 1242
    const/16 v0, 0x190

    invoke-static {p0, v0}, Lcom/android/camera/Util;->fadeIn(Landroid/view/View;I)V

    .line 1243
    return-void
.end method

.method public static fadeIn(Landroid/view/View;I)V
    .locals 3

    .line 1230
    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 1234
    :cond_0
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Landroid/view/View;->setVisibility(I)V

    .line 1235
    new-instance v0, Landroid/view/animation/AlphaAnimation;

    const/4 v1, 0x0

    const/high16 v2, 0x3f800000    # 1.0f

    invoke-direct {v0, v1, v2}, Landroid/view/animation/AlphaAnimation;-><init>(FF)V

    .line 1236
    int-to-long v1, p1

    invoke-virtual {v0, v1, v2}, Landroid/view/animation/Animation;->setDuration(J)V

    .line 1237
    invoke-virtual {p0}, Landroid/view/View;->clearAnimation()V

    .line 1238
    invoke-virtual {p0, v0}, Landroid/view/View;->startAnimation(Landroid/view/animation/Animation;)V

    .line 1239
    return-void

    .line 1231
    :cond_1
    :goto_0
    return-void
.end method

.method public static fadeOut(Landroid/view/View;)V
    .locals 1

    .line 1255
    const/16 v0, 0x190

    invoke-static {p0, v0}, Lcom/android/camera/Util;->fadeOut(Landroid/view/View;I)V

    .line 1256
    return-void
.end method

.method public static fadeOut(Landroid/view/View;I)V
    .locals 3

    .line 1246
    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 1247
    :cond_0
    new-instance v0, Landroid/view/animation/AlphaAnimation;

    const/high16 v1, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Landroid/view/animation/AlphaAnimation;-><init>(FF)V

    .line 1248
    int-to-long v1, p1

    invoke-virtual {v0, v1, v2}, Landroid/view/animation/Animation;->setDuration(J)V

    .line 1249
    invoke-virtual {p0}, Landroid/view/View;->clearAnimation()V

    .line 1250
    invoke-virtual {p0, v0}, Landroid/view/View;->startAnimation(Landroid/view/animation/Animation;)V

    .line 1251
    const/16 p1, 0x8

    invoke-virtual {p0, p1}, Landroid/view/View;->setVisibility(I)V

    .line 1252
    return-void

    .line 1246
    :cond_1
    :goto_0
    return-void
.end method

.method public static flipBitmap(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 8

    .line 2547
    const/4 v0, 0x0

    if-nez p0, :cond_0

    .line 2548
    return-object v0

    .line 2550
    :cond_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    .line 2551
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    .line 2552
    new-instance v3, Landroid/graphics/Matrix;

    invoke-direct {v3}, Landroid/graphics/Matrix;-><init>()V

    .line 2553
    const/high16 v4, -0x40800000    # -1.0f

    const/high16 v5, 0x3f800000    # 1.0f

    div-int/lit8 v6, v1, 0x2

    int-to-float v6, v6

    div-int/lit8 v7, v2, 0x2

    int-to-float v7, v7

    invoke-virtual {v3, v4, v5, v6, v7}, Landroid/graphics/Matrix;->postScale(FFFF)Z

    .line 2554
    nop

    .line 2556
    :try_start_0
    sget-object v4, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v1, v2, v4}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    .line 2563
    nop

    .line 2565
    move-object v0, v1

    goto :goto_0

    .line 2560
    :catch_0
    move-exception v1

    .line 2561
    nop

    .line 2562
    invoke-virtual {v1}, Ljava/lang/NullPointerException;->printStackTrace()V

    goto :goto_0

    .line 2557
    :catch_1
    move-exception v1

    .line 2558
    nop

    .line 2559
    invoke-virtual {v1}, Ljava/lang/OutOfMemoryError;->printStackTrace()V

    .line 2563
    nop

    .line 2565
    :goto_0
    if-nez v0, :cond_1

    .line 2566
    return-object p0

    .line 2568
    :cond_1
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 2569
    new-instance v2, Landroid/graphics/PaintFlagsDrawFilter;

    const/4 v4, 0x0

    const/4 v5, 0x3

    invoke-direct {v2, v4, v5}, Landroid/graphics/PaintFlagsDrawFilter;-><init>(II)V

    invoke-virtual {v1, v2}, Landroid/graphics/Canvas;->setDrawFilter(Landroid/graphics/DrawFilter;)V

    .line 2570
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2}, Landroid/graphics/Paint;-><init>()V

    .line 2571
    const/4 v4, 0x1

    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 2572
    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 2573
    invoke-virtual {v1, p0, v3, v2}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V

    .line 2574
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->recycle()V

    .line 2575
    return-object v0
.end method

.method public static flipYBitmap(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 8

    .line 2579
    const/4 v0, 0x0

    if-nez p0, :cond_0

    .line 2580
    return-object v0

    .line 2582
    :cond_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    .line 2583
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    .line 2584
    new-instance v3, Landroid/graphics/Matrix;

    invoke-direct {v3}, Landroid/graphics/Matrix;-><init>()V

    .line 2585
    const/high16 v4, 0x3f800000    # 1.0f

    const/high16 v5, -0x40800000    # -1.0f

    div-int/lit8 v6, v1, 0x2

    int-to-float v6, v6

    div-int/lit8 v7, v2, 0x2

    int-to-float v7, v7

    invoke-virtual {v3, v4, v5, v6, v7}, Landroid/graphics/Matrix;->postScale(FFFF)Z

    .line 2586
    nop

    .line 2588
    :try_start_0
    sget-object v4, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v1, v2, v4}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    .line 2592
    nop

    .line 2594
    move-object v0, v1

    goto :goto_0

    .line 2589
    :catch_0
    move-exception v1

    .line 2590
    nop

    .line 2591
    const-string v2, "CameraUtil"

    const-string v4, "flip_y_bitmp:"

    invoke-static {v2, v4, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2594
    :goto_0
    if-nez v0, :cond_1

    .line 2595
    return-object p0

    .line 2597
    :cond_1
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 2598
    new-instance v2, Landroid/graphics/PaintFlagsDrawFilter;

    const/4 v4, 0x0

    const/4 v5, 0x3

    invoke-direct {v2, v4, v5}, Landroid/graphics/PaintFlagsDrawFilter;-><init>(II)V

    invoke-virtual {v1, v2}, Landroid/graphics/Canvas;->setDrawFilter(Landroid/graphics/DrawFilter;)V

    .line 2599
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2}, Landroid/graphics/Paint;-><init>()V

    .line 2600
    const/4 v4, 0x1

    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 2601
    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 2602
    invoke-virtual {v1, p0, v3, v2}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V

    .line 2603
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->recycle()V

    .line 2604
    return-object v0
.end method

.method public static generate48MWatermark2File()Landroid/graphics/Bitmap;
    .locals 5

    .line 2638
    new-instance v0, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v0}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 2639
    const/4 v1, 0x0

    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inScaled:Z

    .line 2640
    const/4 v1, 0x1

    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inPurgeable:Z

    .line 2641
    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inPremultiplied:Z

    .line 2643
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v2

    invoke-virtual {v2}, Lcom/mi/config/a;->fI()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 2644
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v2

    sget-object v3, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-static {v2, v0, v3}, Lcom/android/camera/Util;->loadAppCameraWatermark(Landroid/content/Context;Landroid/graphics/BitmapFactory$Options;Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v2

    .line 2645
    if-nez v2, :cond_0

    .line 2646
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v2

    const-string v3, "common"

    invoke-static {v2, v0, v3}, Lcom/android/camera/Util;->loadAppCameraWatermark(Landroid/content/Context;Landroid/graphics/BitmapFactory$Options;Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v2

    .line 2648
    :cond_0
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v3, 0x7f100008

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v0

    .line 2649
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    const v4, 0x7f100009

    invoke-virtual {v3, v4}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v3

    .line 2650
    int-to-float v0, v0

    int-to-float v3, v3

    const v4, 0x7f09028a

    invoke-static {v4}, Lcom/android/camera/CameraSettings;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-static {v2, v0, v3, v4}, Lcom/android/camera/effect/renders/CustomTextWaterMark;->newInstance(Landroid/graphics/Bitmap;FFLjava/lang/String;)Lcom/android/camera/effect/renders/CustomTextWaterMark;

    move-result-object v0

    .line 2651
    invoke-virtual {v0}, Lcom/android/camera/effect/renders/CustomTextWaterMark;->drawToBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    .line 2652
    goto :goto_0

    .line 2653
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v4, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "_48m"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v0, v3}, Lcom/android/camera/Util;->loadAppCameraWatermark(Landroid/content/Context;Landroid/graphics/BitmapFactory$Options;Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 2655
    :goto_0
    invoke-static {v0, v1}, Lcom/android/camera/Util;->saveCustomWatermark2File(Landroid/graphics/Bitmap;Z)V

    .line 2656
    return-object v0
.end method

.method public static generateWatermark2File()Landroid/graphics/Bitmap;
    .locals 7

    .line 2608
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 2609
    new-instance v2, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v2}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 2610
    const/4 v3, 0x0

    iput-boolean v3, v2, Landroid/graphics/BitmapFactory$Options;->inScaled:Z

    .line 2611
    const/4 v4, 0x1

    iput-boolean v4, v2, Landroid/graphics/BitmapFactory$Options;->inPurgeable:Z

    .line 2612
    iput-boolean v4, v2, Landroid/graphics/BitmapFactory$Options;->inPremultiplied:Z

    .line 2613
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v4

    invoke-virtual {v4}, Lcom/mi/config/a;->fg()Z

    move-result v4

    .line 2614
    if-eqz v4, :cond_2

    .line 2616
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v4

    sget-object v5, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-static {v4, v2, v5}, Lcom/android/camera/Util;->loadAppCameraWatermark(Landroid/content/Context;Landroid/graphics/BitmapFactory$Options;Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 2617
    if-nez v4, :cond_0

    .line 2618
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v4

    const-string v5, "common"

    invoke-static {v4, v2, v5}, Lcom/android/camera/Util;->loadAppCameraWatermark(Landroid/content/Context;Landroid/graphics/BitmapFactory$Options;Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 2622
    :cond_0
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v2

    invoke-virtual {v2}, Lcom/mi/config/a;->fI()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 2623
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v5, 0x7f100008

    invoke-virtual {v2, v5}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v2

    .line 2624
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v5

    invoke-virtual {v5}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v5

    const v6, 0x7f100009

    invoke-virtual {v5, v6}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v5

    .line 2625
    int-to-float v2, v2

    int-to-float v5, v5

    invoke-static {}, Lcom/android/camera/CameraSettings;->getCustomWatermark()Ljava/lang/String;

    move-result-object v6

    invoke-static {v4, v2, v5, v6}, Lcom/android/camera/effect/renders/CustomTextWaterMark;->newInstance(Landroid/graphics/Bitmap;FFLjava/lang/String;)Lcom/android/camera/effect/renders/CustomTextWaterMark;

    move-result-object v2

    .line 2626
    invoke-virtual {v2}, Lcom/android/camera/effect/renders/CustomTextWaterMark;->drawToBitmap()Landroid/graphics/Bitmap;

    move-result-object v4

    .line 2627
    goto :goto_0

    .line 2628
    :cond_1
    nop

    .line 2630
    :goto_0
    invoke-static {v4, v3}, Lcom/android/camera/Util;->saveCustomWatermark2File(Landroid/graphics/Bitmap;Z)V

    .line 2631
    const-string v2, "CameraUtil"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "generateWatermark2File cost time = "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    sub-long/2addr v5, v0

    invoke-virtual {v3, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, "ms"

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v2, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2632
    return-object v4

    .line 2634
    :cond_2
    const/4 v0, 0x0

    return-object v0
.end method

.method public static getArrayIndex([II)I
    .locals 3

    .line 1306
    const/4 v0, -0x1

    if-nez p0, :cond_0

    .line 1307
    return v0

    .line 1309
    :cond_0
    const/4 v1, 0x0

    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_2

    .line 1310
    aget v2, p0, v1

    if-ne v2, p1, :cond_1

    .line 1311
    return v1

    .line 1309
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 1314
    :cond_2
    return v0
.end method

.method public static getArrayIndex([Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">([TT;TT;)I"
        }
    .end annotation

    .line 1290
    const/4 v0, -0x1

    if-nez p0, :cond_0

    .line 1291
    return v0

    .line 1294
    :cond_0
    nop

    .line 1295
    array-length v1, p0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v2, v1, :cond_2

    aget-object v4, p0, v2

    .line 1296
    invoke-static {v4, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 1297
    return v3

    .line 1299
    :cond_1
    add-int/lit8 v3, v3, 0x1

    .line 1295
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 1302
    :cond_2
    return v0
.end method

.method private static getAttributeIntValue(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)I
    .locals 3

    .line 2299
    const/4 v0, 0x0

    invoke-interface {p0, v0, p1}, Lorg/xmlpull/v1/XmlPullParser;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 2300
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2301
    return p2

    .line 2304
    :cond_0
    :try_start_0
    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    .line 2305
    :catch_0
    move-exception p0

    .line 2306
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "get attribute "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " failed"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1, p0}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2309
    return p2
.end method

.method public static getBitmapData(Landroid/graphics/Bitmap;)[B
    .locals 3

    .line 2541
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 2542
    sget-object v1, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    const/16 v2, 0x57

    invoke-virtual {p0, v1, v2, v0}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    .line 2543
    invoke-virtual {v0}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object p0

    return-object p0
.end method

.method public static getBottomHeight(Landroid/content/res/Resources;)I
    .locals 1

    .line 1937
    const v0, 0x7f0a0020

    invoke-virtual {p0, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p0

    .line 1939
    sget-boolean v0, Lcom/android/camera/Util;->isNotchDevice:Z

    if-eqz v0, :cond_0

    sget-boolean v0, Lcom/android/camera/Util;->sIsFullScreenNavBarHidden:Z

    if-eqz v0, :cond_0

    sget-boolean v0, Lcom/android/camera/Util;->isLongRatioScreen:Z

    if-nez v0, :cond_0

    .line 1940
    sget v0, Lcom/android/camera/Util;->sFullScreenExtraMargin:I

    sub-int/2addr p0, v0

    .line 1942
    :cond_0
    return p0
.end method

.method private static getCaller([Ljava/lang/StackTraceElement;I)Ljava/lang/String;
    .locals 1

    .line 2350
    const/4 v0, 0x4

    add-int/2addr v0, p1

    array-length p1, p0

    if-lt v0, p1, :cond_0

    .line 2351
    const-string p0, "<bottom of call stack>"

    return-object p0

    .line 2353
    :cond_0
    aget-object p0, p0, v0

    .line 2354
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0}, Ljava/lang/StackTraceElement;->getClassName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "."

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StackTraceElement;->getMethodName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ":"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StackTraceElement;->getLineNumber()I

    move-result p0

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static getCallers(I)Ljava/lang/String;
    .locals 4

    .line 2358
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Thread;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object v0

    .line 2359
    new-instance v1, Ljava/lang/StringBuffer;

    invoke-direct {v1}, Ljava/lang/StringBuffer;-><init>()V

    .line 2360
    const/4 v2, 0x0

    :goto_0
    if-ge v2, p0, :cond_0

    .line 2361
    invoke-static {v0, v2}, Lcom/android/camera/Util;->getCaller([Ljava/lang/StackTraceElement;I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    const-string v3, " "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 2360
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 2363
    :cond_0
    invoke-virtual {v1}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static getCenterFocusDepthIndex([BII)I
    .locals 11

    .line 1447
    const/4 v0, 0x1

    if-eqz p0, :cond_6

    array-length v1, p0

    const/16 v2, 0x19

    if-ge v1, v2, :cond_0

    goto/16 :goto_3

    .line 1454
    :cond_0
    array-length v1, p0

    sub-int/2addr v1, v2

    .line 1456
    add-int/lit8 v2, v1, 0x1

    aget-byte v1, p0, v1

    if-eqz v1, :cond_1

    .line 1457
    return v0

    .line 1459
    :cond_1
    add-int/lit8 v1, v2, 0x1

    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    shl-int/lit8 v2, v2, 0x18

    add-int/lit8 v3, v1, 0x1

    aget-byte v1, p0, v1

    and-int/lit16 v1, v1, 0xff

    shl-int/lit8 v1, v1, 0x10

    or-int/2addr v1, v2

    add-int/lit8 v2, v3, 0x1

    aget-byte v3, p0, v3

    and-int/lit16 v3, v3, 0xff

    shl-int/lit8 v3, v3, 0x8

    or-int/2addr v1, v3

    add-int/lit8 v3, v2, 0x1

    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    or-int/2addr v1, v2

    .line 1463
    add-int/lit8 v2, v3, 0x1

    aget-byte v3, p0, v3

    and-int/lit16 v3, v3, 0xff

    shl-int/lit8 v3, v3, 0x18

    add-int/lit8 v4, v2, 0x1

    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    shl-int/lit8 v2, v2, 0x10

    or-int/2addr v2, v3

    add-int/lit8 v3, v4, 0x1

    aget-byte v4, p0, v4

    and-int/lit16 v4, v4, 0xff

    shl-int/lit8 v4, v4, 0x8

    or-int/2addr v2, v4

    aget-byte v3, p0, v3

    and-int/lit16 v3, v3, 0xff

    or-int/2addr v2, v3

    .line 1467
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    .line 1468
    const v4, 0x7f0a0008

    invoke-virtual {v3, v4}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    .line 1469
    const v5, 0x7f0a0009

    invoke-virtual {v3, v5}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    .line 1470
    mul-int/2addr v4, v1

    sget v5, Lcom/android/camera/Util;->sWindowWidth:I

    div-int/2addr v4, v5

    .line 1471
    mul-int/2addr v3, v2

    int-to-float v3, v3

    sget v5, Lcom/android/camera/Util;->sWindowWidth:I

    int-to-float v5, v5

    int-to-float p2, p2

    mul-float/2addr v5, p2

    int-to-float p1, p1

    div-float/2addr v5, p1

    div-float/2addr v3, v5

    float-to-int p1, v3

    .line 1472
    sub-int/2addr v2, p1

    div-int/lit8 v2, v2, 0x2

    .line 1473
    const/4 p2, 0x5

    new-array v3, p2, [I

    .line 1474
    const/4 v5, 0x0

    move v6, v2

    move v2, v5

    :goto_0
    if-ge v2, p1, :cond_3

    .line 1475
    add-int/lit8 v7, v6, 0x1

    mul-int/2addr v6, v1

    sub-int v8, v1, v4

    div-int/lit8 v8, v8, 0x2

    add-int/2addr v6, v8

    .line 1476
    move v8, v6

    move v6, v5

    :goto_1
    if-ge v6, v4, :cond_2

    .line 1477
    add-int/lit8 v9, v8, 0x1

    aget-byte v8, p0, v8

    aget v10, v3, v8

    add-int/2addr v10, v0

    aput v10, v3, v8

    .line 1476
    add-int/lit8 v6, v6, 0x1

    move v8, v9

    goto :goto_1

    .line 1474
    :cond_2
    add-int/lit8 v2, v2, 0x1

    move v6, v7

    goto :goto_0

    .line 1481
    :cond_3
    nop

    .line 1482
    :goto_2
    if-ge v0, p2, :cond_5

    .line 1483
    aget p0, v3, v5

    aget p1, v3, v0

    if-ge p0, p1, :cond_4

    .line 1484
    nop

    .line 1482
    move v5, v0

    :cond_4
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 1487
    :cond_5
    return v5

    .line 1449
    :cond_6
    :goto_3
    return v0
.end method

.method public static getChildMeasureWidth(Landroid/view/View;)I
    .locals 2

    .line 2130
    invoke-virtual {p0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup$MarginLayoutParams;

    .line 2131
    iget v1, v0, Landroid/view/ViewGroup$MarginLayoutParams;->leftMargin:I

    iget v0, v0, Landroid/view/ViewGroup$MarginLayoutParams;->rightMargin:I

    add-int/2addr v1, v0

    .line 2133
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    .line 2134
    if-lez v0, :cond_0

    .line 2135
    add-int/2addr v0, v1

    return v0

    .line 2137
    :cond_0
    const/4 v0, 0x0

    invoke-static {v0, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 2138
    invoke-virtual {p0, v0, v0}, Landroid/view/View;->measure(II)V

    .line 2139
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result p0

    add-int/2addr p0, v1

    return p0
.end method

.method private static getColorMapXmlMapFile()Ljava/io/File;
    .locals 2

    .line 2198
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    if-lt v0, v1, :cond_1

    .line 2199
    new-instance v0, Ljava/io/File;

    const-string v1, "/vendor/etc/screen_light.xml"

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 2200
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2201
    return-object v0

    .line 2203
    :cond_0
    const-string v0, "CameraUtil"

    const-string v1, "screen_light.xml do not found under /vendor/etc, roll back to /system/etc"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2207
    :cond_1
    new-instance v0, Ljava/io/File;

    const-string v1, "/system/etc/screen_light.xml"

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 2209
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 2210
    return-object v0

    .line 2212
    :cond_2
    const-string v0, "CameraUtil"

    const-string v1, "screen_light.xml do not found under /system/etc"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2213
    const/4 v0, 0x0

    return-object v0
.end method

.method public static getDebugInfo()Ljava/lang/String;
    .locals 3

    .line 1653
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1657
    const-string v1, "1"

    const-string v2, "persist.camera.debug.show_af"

    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    const-string v1, "1"

    const-string v2, "persist.camera.debug.enable"

    .line 1658
    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1659
    :cond_0
    const-string v1, "persist.camera.debug.param0"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1660
    const-string v1, "persist.camera.debug.param1"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1661
    const-string v1, "persist.camera.debug.param2"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1662
    const-string v1, "persist.camera.debug.param3"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1663
    const-string v1, "persist.camera.debug.param4"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1664
    const-string v1, "persist.camera.debug.param5"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1665
    const-string v1, "persist.camera.debug.param6"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1666
    const-string v1, "persist.camera.debug.param7"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1667
    const-string v1, "persist.camera.debug.param8"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1668
    const-string v1, "persist.camera.debug.param9"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1671
    :cond_1
    const-string v1, "1"

    const-string v2, "persist.camera.debug.show_awb"

    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1672
    const-string v1, "persist.camera.debug.param10"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1673
    const-string v1, "persist.camera.debug.param11"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1674
    const-string v1, "persist.camera.debug.param12"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1675
    const-string v1, "persist.camera.debug.param13"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1676
    const-string v1, "persist.camera.debug.param14"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1677
    const-string v1, "persist.camera.debug.param15"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1678
    const-string v1, "persist.camera.debug.param16"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1679
    const-string v1, "persist.camera.debug.param17"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1680
    const-string v1, "persist.camera.debug.param18"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1681
    const-string v1, "persist.camera.debug.param19"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1684
    :cond_2
    const-string v1, "1"

    const-string v2, "persist.camera.debug.show_aec"

    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 1685
    const-string v1, "persist.camera.debug.param20"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1686
    const-string v1, "persist.camera.debug.param21"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1687
    const-string v1, "persist.camera.debug.param22"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1688
    const-string v1, "persist.camera.debug.param23"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1689
    const-string v1, "persist.camera.debug.param24"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1690
    const-string v1, "persist.camera.debug.param25"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1691
    const-string v1, "persist.camera.debug.param26"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1692
    const-string v1, "persist.camera.debug.param27"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1693
    const-string v1, "persist.camera.debug.param28"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1694
    const-string v1, "persist.camera.debug.param29"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1696
    :cond_3
    const-string v1, "persist.camera.debug.checkerf"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1697
    const-string v1, "persist.camera.debug.fc"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1698
    const-string v1, "1"

    const-string v2, "persist.camera.debug.hht"

    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 1699
    const-string v1, "camera.debug.hht.luma"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1701
    :cond_4
    const-string v1, "1"

    const-string v2, "persist.camera.debug.autoscene"

    invoke-static {v2}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 1702
    const-string v1, "camera.debug.hht.iso"

    invoke-static {v1}, Lcom/android/camera/Util;->addProperties(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1704
    :cond_5
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static getDebugInformation(Landroid/hardware/camera2/CaptureResult;)Ljava/lang/String;
    .locals 7

    .line 1586
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1587
    invoke-static {p0}, Lcom/android/camera2/CaptureResultParser;->getAECFrameControl(Landroid/hardware/camera2/CaptureResult;)Lcom/android/camera2/AECFrameControl;

    move-result-object v1

    .line 1588
    invoke-static {p0}, Lcom/android/camera2/CaptureResultParser;->getAFFrameControl(Landroid/hardware/camera2/CaptureResult;)Lcom/android/camera2/AFFrameControl;

    move-result-object v2

    .line 1589
    const-string v3, "1"

    const-string v4, "camera.preview.debug.show_shortGain"

    invoke-static {v4}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_0

    .line 1590
    if-eqz v1, :cond_0

    invoke-virtual {v1}, Lcom/android/camera2/AECFrameControl;->getAecExposureDatas()[Lcom/android/camera2/AECFrameControl$AECExposureData;

    move-result-object v3

    if-eqz v3, :cond_0

    .line 1591
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "short gain : "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Lcom/android/camera2/AECFrameControl;->getAecExposureDatas()[Lcom/android/camera2/AECFrameControl$AECExposureData;

    move-result-object v5

    aget-object v5, v5, v4

    invoke-virtual {v5}, Lcom/android/camera2/AECFrameControl$AECExposureData;->getLinearGain()F

    move-result v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1594
    :cond_0
    const-string v3, "1"

    const-string v5, "camera.preview.debug.show_adrcGain"

    invoke-static {v5}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1595
    if-eqz v1, :cond_1

    invoke-virtual {v1}, Lcom/android/camera2/AECFrameControl;->getAecExposureDatas()[Lcom/android/camera2/AECFrameControl$AECExposureData;

    move-result-object v3

    if-eqz v3, :cond_1

    .line 1596
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "adrc gain : "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Lcom/android/camera2/AECFrameControl;->getAecExposureDatas()[Lcom/android/camera2/AECFrameControl$AECExposureData;

    move-result-object v5

    const/4 v6, 0x2

    aget-object v5, v5, v6

    invoke-virtual {v5}, Lcom/android/camera2/AECFrameControl$AECExposureData;->getSensitivity()F

    move-result v5

    .line 1597
    invoke-virtual {v1}, Lcom/android/camera2/AECFrameControl;->getAecExposureDatas()[Lcom/android/camera2/AECFrameControl$AECExposureData;

    move-result-object v1

    aget-object v1, v1, v4

    invoke-virtual {v1}, Lcom/android/camera2/AECFrameControl$AECExposureData;->getSensitivity()F

    move-result v1

    div-float/2addr v5, v1

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 1596
    invoke-static {v1}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1600
    :cond_1
    const-string v1, "1"

    const-string v3, "camera.preview.debug.show_afRegion"

    invoke-static {v3}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 1601
    sget-object v1, Landroid/hardware/camera2/CaptureResult;->CONTROL_AF_REGIONS:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p0, v1}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/hardware/camera2/params/MeteringRectangle;

    .line 1602
    if-eqz v1, :cond_2

    .line 1603
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "af region : "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget-object v1, v1, v4

    invoke-virtual {v1}, Landroid/hardware/camera2/params/MeteringRectangle;->getRect()Landroid/graphics/Rect;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/Rect;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1606
    :cond_2
    const-string v1, "1"

    const-string v3, "camera.preview.debug.show_afMode"

    invoke-static {v3}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 1607
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "af mode : "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v3, Landroid/hardware/camera2/CaptureResult;->CONTROL_AF_MODE:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p0, v3}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1609
    :cond_3
    const-string v1, "1"

    const-string v3, "camera.preview.debug.show_afStatus"

    invoke-static {v3}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_4

    .line 1610
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "af state : "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v3, Landroid/hardware/camera2/CaptureResult;->CONTROL_AF_STATE:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p0, v3}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1612
    :cond_4
    const-string v1, "1"

    const-string v3, "camera.preview.debug.show_afLensPosition"

    invoke-static {v3}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_6

    .line 1613
    if-eqz v2, :cond_6

    .line 1614
    const-string v1, ""

    .line 1615
    invoke-virtual {v2}, Lcom/android/camera2/AFFrameControl;->getUseDACValue()I

    move-result v3

    if-nez v3, :cond_5

    .line 1616
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2}, Lcom/android/camera2/AFFrameControl;->getTargetLensPosition()I

    move-result v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ""

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 1618
    :cond_5
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "af lens position : "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1621
    :cond_6
    const-string v1, "1"

    const-string v3, "camera.preview.debug.show_distance"

    invoke-static {v3}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_7

    .line 1622
    sget-object v1, Landroid/hardware/camera2/CaptureResult;->LENS_FOCUS_DISTANCE:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p0, v1}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_7

    .line 1623
    sget-object v1, Landroid/hardware/camera2/CaptureResult;->LENS_FOCUS_DISTANCE:Landroid/hardware/camera2/CaptureResult$Key;

    invoke-virtual {p0, v1}, Landroid/hardware/camera2/CaptureResult;->get(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Float;

    invoke-virtual {p0}, Ljava/lang/Float;->floatValue()F

    move-result p0

    .line 1624
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "distance : "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1625
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "distance(m) : "

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/high16 v3, 0x3f800000    # 1.0f

    div-float/2addr v3, p0

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1629
    :cond_7
    const-string p0, "1"

    const-string v1, "camera.preview.debug.show_gyro"

    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_8

    .line 1630
    if-eqz v2, :cond_8

    .line 1631
    :goto_0
    invoke-virtual {v2}, Lcom/android/camera2/AFFrameControl;->getAFGyroData()Lcom/android/camera2/AFFrameControl$AFGyroData;

    move-result-object p0

    invoke-virtual {p0}, Lcom/android/camera2/AFFrameControl$AFGyroData;->getSampleCount()I

    move-result p0

    if-ge v4, p0, :cond_8

    .line 1632
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "gyro : x: "

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1633
    invoke-virtual {v2}, Lcom/android/camera2/AFFrameControl;->getAFGyroData()Lcom/android/camera2/AFFrameControl$AFGyroData;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera2/AFFrameControl$AFGyroData;->getpAngularVelocityX()[F

    move-result-object v1

    aget v1, v1, v4

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v1, ", y: "

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1634
    invoke-virtual {v2}, Lcom/android/camera2/AFFrameControl;->getAFGyroData()Lcom/android/camera2/AFFrameControl$AFGyroData;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera2/AFFrameControl$AFGyroData;->getpAngularVelocityY()[F

    move-result-object v1

    aget v1, v1, v4

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v1, ", z: "

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1635
    invoke-virtual {v2}, Lcom/android/camera2/AFFrameControl;->getAFGyroData()Lcom/android/camera2/AFFrameControl$AFGyroData;

    move-result-object v1

    invoke-virtual {v1}, Lcom/android/camera2/AFFrameControl$AFGyroData;->getpAngularVelocityZ()[F

    move-result-object v1

    aget v1, v1, v4

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1632
    invoke-static {p0}, Lcom/android/camera/Util;->addDebugInfo(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1631
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 1640
    :cond_8
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static getDialogTopMargin(I)I
    .locals 1

    .line 1946
    sget-boolean v0, Lcom/android/camera/Util;->isNotchDevice:Z

    if-eqz v0, :cond_0

    .line 1947
    sget v0, Lcom/android/camera/Util;->sStatusBarHeight:I

    sub-int/2addr p0, v0

    return p0

    .line 1949
    :cond_0
    return p0
.end method

.method public static getDisplayOrientation(II)I
    .locals 1

    .line 791
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getCapabilities(I)Lcom/android/camera2/CameraCapabilities;

    move-result-object p1

    .line 792
    if-eqz p1, :cond_1

    .line 793
    invoke-virtual {p1}, Lcom/android/camera2/CameraCapabilities;->getSensorOrientation()I

    move-result v0

    .line 795
    invoke-virtual {p1}, Lcom/android/camera2/CameraCapabilities;->getFacing()I

    move-result p1

    if-nez p1, :cond_0

    .line 796
    add-int/2addr v0, p0

    rem-int/lit16 v0, v0, 0x168

    .line 797
    rsub-int p0, v0, 0x168

    rem-int/lit16 p0, p0, 0x168

    goto :goto_0

    .line 799
    :cond_0
    sub-int/2addr v0, p0

    add-int/lit16 v0, v0, 0x168

    rem-int/lit16 p0, v0, 0x168

    .line 801
    :goto_0
    return p0

    .line 803
    :cond_1
    const/16 p0, 0x5a

    return p0
.end method

.method public static getDisplayRect(Landroid/content/Context;)Landroid/graphics/Rect;
    .locals 1

    .line 1891
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object v0

    invoke-virtual {v0}, Lcom/android/camera/data/data/runing/DataItemRunning;->getUiStyle()I

    move-result v0

    .line 1892
    invoke-static {p0, v0}, Lcom/android/camera/Util;->getDisplayRect(Landroid/content/Context;I)Landroid/graphics/Rect;

    move-result-object p0

    return-object p0
.end method

.method public static getDisplayRect(Landroid/content/Context;I)Landroid/graphics/Rect;
    .locals 3

    .line 1902
    nop

    .line 1903
    nop

    .line 1905
    const/high16 v0, 0x40400000    # 3.0f

    if-eqz p1, :cond_2

    const/4 v1, 0x3

    if-eq p1, v1, :cond_1

    .line 1908
    sget p1, Lcom/android/camera/Util;->sWindowWidth:I

    mul-int/lit8 p1, p1, 0x10

    int-to-float p1, p1

    const/high16 v1, 0x41100000    # 9.0f

    div-float/2addr p1, v1

    float-to-int p1, p1

    .line 1909
    sget-boolean v1, Lcom/android/camera/Util;->isLongRatioScreen:Z

    if-eqz v1, :cond_0

    .line 1910
    sget v1, Lcom/android/camera/Util;->sWindowWidth:I

    mul-int/lit8 v1, v1, 0x4

    int-to-float v1, v1

    div-float/2addr v1, v0

    float-to-int v0, v1

    .line 1911
    sget v1, Lcom/android/camera/Util;->sWindowHeight:I

    sub-int/2addr v1, v0

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-static {p0}, Lcom/android/camera/Util;->getBottomHeight(Landroid/content/res/Resources;)I

    move-result p0

    sub-int/2addr v1, p0

    .line 1912
    goto :goto_0

    .line 1913
    :cond_0
    sget p0, Lcom/android/camera/Util;->sWindowHeight:I

    sub-int/2addr p0, p1

    sget v0, Lcom/android/camera/Util;->sNavigationBarHeight:I

    sub-int v1, p0, v0

    .line 1915
    goto :goto_0

    .line 1918
    :cond_1
    sget p1, Lcom/android/camera/Util;->sWindowHeight:I

    .line 1919
    sget v1, Lcom/android/camera/Util;->sStatusBarHeight:I

    .line 1920
    goto :goto_0

    .line 1923
    :cond_2
    sget p1, Lcom/android/camera/Util;->sWindowWidth:I

    mul-int/lit8 p1, p1, 0x4

    int-to-float p1, p1

    div-float/2addr p1, v0

    float-to-int p1, p1

    .line 1924
    sget v0, Lcom/android/camera/Util;->sWindowHeight:I

    sub-int/2addr v0, p1

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-static {p0}, Lcom/android/camera/Util;->getBottomHeight(Landroid/content/res/Resources;)I

    move-result p0

    sub-int v1, v0, p0

    .line 1928
    :goto_0
    const/4 p0, 0x2

    const/4 v0, 0x0

    if-gt v1, p0, :cond_3

    .line 1929
    nop

    .line 1932
    move v1, v0

    :cond_3
    new-instance p0, Landroid/graphics/Rect;

    sget v2, Lcom/android/camera/Util;->sWindowWidth:I

    add-int/2addr p1, v1

    invoke-direct {p0, v0, v1, v2, p1}, Landroid/graphics/Rect;-><init>(IIII)V

    return-object p0
.end method

.method public static getDisplayRotation(Landroid/app/Activity;)I
    .locals 3

    .line 763
    nop

    .line 765
    invoke-static {}, Lcom/mi/config/b;->hI()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/android/camera/CameraSettings;->isFrontCamera()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 766
    invoke-virtual {p0}, Landroid/app/Activity;->getRequestedOrientation()I

    move-result v0

    const/4 v2, 0x7

    if-ne v0, v2, :cond_0

    .line 767
    invoke-virtual {p0}, Landroid/app/Activity;->getWindowManager()Landroid/view/WindowManager;

    move-result-object p0

    invoke-interface {p0}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/Display;->getRotation()I

    move-result p0

    goto :goto_1

    .line 768
    :cond_0
    sget p0, Lcom/android/camera/Util;->mLockedOrientation:I

    if-eqz p0, :cond_2

    sget p0, Lcom/android/camera/Util;->mLockedOrientation:I

    const/4 v0, 0x2

    if-ne p0, v0, :cond_1

    goto :goto_0

    .line 772
    :cond_1
    move p0, v1

    goto :goto_1

    .line 769
    :cond_2
    :goto_0
    sget p0, Lcom/android/camera/Util;->mLockedOrientation:I

    .line 772
    :goto_1
    packed-switch p0, :pswitch_data_0

    .line 782
    return v1

    .line 780
    :pswitch_0
    const/16 p0, 0x10e

    return p0

    .line 778
    :pswitch_1
    const/16 p0, 0xb4

    return p0

    .line 776
    :pswitch_2
    const/16 p0, 0x5a

    return p0

    .line 774
    :pswitch_3
    return v1

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private static getDualCameraWatermarkData(II[I)[B
    .locals 13

    .line 3103
    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->fg()Z

    move-result v0

    .line 3105
    if-eqz v0, :cond_1

    .line 3106
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v1

    sget-object v2, Lcom/android/camera/Util;->WATERMARK_FILE_NAME:Ljava/lang/String;

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v0

    .line 3107
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 3108
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_0

    .line 3109
    invoke-static {}, Lcom/android/camera/Util;->generateWatermark2File()Landroid/graphics/Bitmap;

    .line 3111
    :cond_0
    goto :goto_0

    .line 3112
    :cond_1
    invoke-static {}, Lcom/android/camera/CameraSettings;->getDualCameraWaterMarkFilePathVendor()Ljava/lang/String;

    move-result-object v0

    .line 3115
    :goto_0
    nop

    .line 3116
    const/4 v1, 0x0

    :try_start_0
    new-instance v2, Ljava/io/FileInputStream;

    invoke-direct {v2, v0}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2

    .line 3117
    :try_start_1
    invoke-static {v2}, Lmiui/util/IOUtils;->toByteArray(Ljava/io/InputStream;)[B

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 3118
    :try_start_2
    invoke-static {v1, v2}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 3120
    goto :goto_3

    .line 3118
    :catch_0
    move-exception v1

    move-object v12, v1

    move-object v1, v0

    move-object v0, v12

    goto :goto_2

    :catchall_0
    move-exception v0

    move-object v3, v1

    goto :goto_1

    .line 3116
    :catch_1
    move-exception v0

    :try_start_3
    throw v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 3118
    :catchall_1
    move-exception v3

    move-object v12, v3

    move-object v3, v0

    move-object v0, v12

    :goto_1
    :try_start_4
    invoke-static {v3, v2}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw v0
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_2

    :catch_2
    move-exception v0

    .line 3119
    :goto_2
    const-string v2, "CameraUtil"

    const-string v3, "Failed to load dual camera water mark"

    invoke-static {v2, v3, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 3122
    move-object v0, v1

    :goto_3
    if-eqz v0, :cond_2

    if-eqz p2, :cond_2

    array-length v1, p2

    const/4 v2, 0x4

    if-lt v1, v2, :cond_2

    .line 3124
    array-length v1, v0

    const/4 v2, 0x0

    invoke-static {v0, v2, v1}, Landroid/graphics/BitmapFactory;->decodeByteArray([BII)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 3125
    if-eqz v1, :cond_2

    .line 3126
    const v3, 0x7f0a00ad

    const/high16 v4, 0x3f800000    # 1.0f

    invoke-static {v3, v4}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v9

    .line 3127
    const v3, 0x7f0a00ae

    invoke-static {v3, v4}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v10

    .line 3128
    const v3, 0x7f0a00af

    invoke-static {v3, v4}, Lcom/android/camera/CameraSettings;->getResourceFloat(IF)F

    move-result v11

    .line 3129
    nop

    .line 3131
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v7

    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v8

    .line 3129
    move v5, p0

    move v6, p1

    invoke-static/range {v5 .. v11}, Lcom/android/camera/Util;->calcDualCameraWatermarkLocation(IIIIFFF)[I

    move-result-object p0

    .line 3133
    array-length p1, p0

    invoke-static {p0, v2, p2, v2, p1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 3137
    :cond_2
    return-object v0
.end method

.method public static getDuration(Ljava/io/FileDescriptor;)J
    .locals 3

    .line 2400
    new-instance v0, Landroid/media/MediaMetadataRetriever;

    invoke-direct {v0}, Landroid/media/MediaMetadataRetriever;-><init>()V

    .line 2402
    :try_start_0
    invoke-virtual {v0, p0}, Landroid/media/MediaMetadataRetriever;->setDataSource(Ljava/io/FileDescriptor;)V

    .line 2403
    const/16 p0, 0x9

    invoke-virtual {v0, p0}, Landroid/media/MediaMetadataRetriever;->extractMetadata(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v1
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 2408
    invoke-virtual {v0}, Landroid/media/MediaMetadataRetriever;->release()V

    .line 2403
    return-wide v1

    .line 2408
    :catchall_0
    move-exception p0

    goto :goto_0

    .line 2405
    :catch_0
    move-exception p0

    .line 2406
    :try_start_1
    const-string v1, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/IllegalArgumentException;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 2408
    invoke-virtual {v0}, Landroid/media/MediaMetadataRetriever;->release()V

    .line 2409
    nop

    .line 2410
    const-wide/16 v0, 0x0

    return-wide v0

    .line 2408
    :goto_0
    invoke-virtual {v0}, Landroid/media/MediaMetadataRetriever;->release()V

    throw p0
.end method

.method public static getDuration(Ljava/lang/String;)J
    .locals 3

    .line 2386
    new-instance v0, Landroid/media/MediaMetadataRetriever;

    invoke-direct {v0}, Landroid/media/MediaMetadataRetriever;-><init>()V

    .line 2388
    :try_start_0
    invoke-virtual {v0, p0}, Landroid/media/MediaMetadataRetriever;->setDataSource(Ljava/lang/String;)V

    .line 2389
    const/16 p0, 0x9

    invoke-virtual {v0, p0}, Landroid/media/MediaMetadataRetriever;->extractMetadata(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v1
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 2394
    invoke-virtual {v0}, Landroid/media/MediaMetadataRetriever;->release()V

    .line 2389
    return-wide v1

    .line 2394
    :catchall_0
    move-exception p0

    goto :goto_0

    .line 2391
    :catch_0
    move-exception p0

    .line 2392
    :try_start_1
    const-string v1, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/IllegalArgumentException;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 2394
    invoke-virtual {v0}, Landroid/media/MediaMetadataRetriever;->release()V

    .line 2395
    nop

    .line 2396
    const-wide/16 v0, 0x0

    return-wide v0

    .line 2394
    :goto_0
    invoke-virtual {v0}, Landroid/media/MediaMetadataRetriever;->release()V

    throw p0
.end method

.method public static getExif(Ljava/lang/String;)Lcom/android/gallery3d/exif/ExifInterface;
    .locals 2

    .line 2107
    new-instance v0, Lcom/android/gallery3d/exif/ExifInterface;

    invoke-direct {v0}, Lcom/android/gallery3d/exif/ExifInterface;-><init>()V

    .line 2109
    :try_start_0
    invoke-virtual {v0, p0}, Lcom/android/gallery3d/exif/ExifInterface;->readExif(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 2112
    goto :goto_0

    .line 2110
    :catch_0
    move-exception p0

    .line 2111
    const-string v1, "CameraUtil"

    invoke-virtual {p0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object p0

    invoke-static {v1, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2113
    :goto_0
    return-object v0
.end method

.method public static getExif([B)Lcom/android/gallery3d/exif/ExifInterface;
    .locals 2

    .line 2097
    new-instance v0, Lcom/android/gallery3d/exif/ExifInterface;

    invoke-direct {v0}, Lcom/android/gallery3d/exif/ExifInterface;-><init>()V

    .line 2099
    :try_start_0
    invoke-virtual {v0, p0}, Lcom/android/gallery3d/exif/ExifInterface;->readExif([B)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 2102
    goto :goto_0

    .line 2100
    :catch_0
    move-exception p0

    .line 2101
    const-string v1, "CameraUtil"

    invoke-virtual {p0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object p0

    invoke-static {v1, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2103
    :goto_0
    return-object v0
.end method

.method public static getFileTitleFromPath(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 1191
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 1192
    return-object v1

    .line 1195
    :cond_0
    const-string v0, "/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v0

    .line 1196
    if-ltz v0, :cond_4

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-lt v0, v2, :cond_1

    goto :goto_1

    .line 1200
    :cond_1
    add-int/lit8 v0, v0, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    .line 1201
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1202
    return-object v1

    .line 1204
    :cond_2
    const-string v0, "."

    invoke-virtual {p0, v0}, Ljava/lang/String;->indexOf(Ljava/lang/String;)I

    move-result v0

    .line 1205
    if-gez v0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v1, 0x0

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    :goto_0
    return-object p0

    .line 1198
    :cond_4
    :goto_1
    return-object v1
.end method

.method public static getFirstPlane(Landroid/media/Image;)[B
    .locals 1

    .line 2508
    invoke-virtual {p0}, Landroid/media/Image;->getPlanes()[Landroid/media/Image$Plane;

    move-result-object p0

    .line 2509
    array-length v0, p0

    if-lez v0, :cond_0

    .line 2510
    const/4 v0, 0x0

    aget-object p0, p0, v0

    invoke-virtual {p0}, Landroid/media/Image$Plane;->getBuffer()Ljava/nio/ByteBuffer;

    move-result-object p0

    .line 2511
    invoke-virtual {p0}, Ljava/nio/ByteBuffer;->remaining()I

    move-result v0

    new-array v0, v0, [B

    .line 2512
    invoke-virtual {p0, v0}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    .line 2513
    return-object v0

    .line 2515
    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public static getIntField(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)I
    .locals 1

    .line 1727
    :try_start_0
    invoke-static {p0, p2, p3}, Lmiui/reflect/Field;->of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object p2

    .line 1728
    invoke-virtual {p2, p1}, Lmiui/reflect/Field;->getInt(Ljava/lang/Object;)I

    move-result p1
    :try_end_0
    .catch Lmiui/reflect/NoSuchClassException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lmiui/reflect/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    return p1

    .line 1731
    :catch_0
    move-exception p0

    .line 1732
    const-string p1, "CameraUtil"

    const-string p2, "no field "

    invoke-static {p1, p2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0

    .line 1729
    :catch_1
    move-exception p1

    .line 1730
    const-string p2, "CameraUtil"

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "no class "

    invoke-virtual {p3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p2, p0, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 1733
    nop

    .line 1734
    :goto_0
    const/high16 p0, -0x80000000

    return p0
.end method

.method public static getJpegRotation(II)I
    .locals 2

    .line 1259
    nop

    .line 1260
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getCapabilities(I)Lcom/android/camera2/CameraCapabilities;

    move-result-object p0

    .line 1261
    invoke-virtual {p0}, Lcom/android/camera2/CameraCapabilities;->getSensorOrientation()I

    move-result v0

    .line 1262
    const/4 v1, -0x1

    if-eq p1, v1, :cond_1

    .line 1263
    invoke-virtual {p0}, Lcom/android/camera2/CameraCapabilities;->getFacing()I

    move-result p0

    if-nez p0, :cond_0

    .line 1264
    sub-int/2addr v0, p1

    add-int/lit16 v0, v0, 0x168

    rem-int/lit16 v0, v0, 0x168

    goto :goto_0

    .line 1266
    :cond_0
    add-int/2addr v0, p1

    rem-int/lit16 v0, v0, 0x168

    goto :goto_0

    .line 1269
    :cond_1
    nop

    .line 1270
    const-string p0, "CameraUtil"

    const-string p1, "getJpegRotation: orientation UNKNOWN!!! return sensorOrientation..."

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 1272
    :goto_0
    return v0
.end method

.method public static getLanTineGBTypeface(Landroid/content/Context;)Landroid/graphics/Typeface;
    .locals 1

    .line 1428
    const-string v0, "fonts/MI+LanTing_GB+Outside+YS_V2.3_20160322.ttf"

    invoke-static {p0, v0}, Lcom/android/camera/Util;->getTypeface(Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p0

    return-object p0
.end method

.method public static getMFYueYuanTypeface(Landroid/content/Context;)Landroid/graphics/Typeface;
    .locals 1

    .line 1424
    const-string v0, "fonts/MFYueYuan-Regular.ttf"

    invoke-static {p0, v0}, Lcom/android/camera/Util;->getTypeface(Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p0

    return-object p0
.end method

.method public static getMethod([Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")",
            "Lmiui/reflect/Method;"
        }
    .end annotation

    .line 1541
    nop

    .line 1543
    const/4 v0, 0x0

    if-eqz p0, :cond_0

    const/4 v1, 0x0

    :try_start_0
    array-length v2, p0

    const/4 v3, 0x1

    if-ne v2, v3, :cond_0

    .line 1544
    aget-object v2, p0, v1

    invoke-static {v2, p1, p2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v2
    :try_end_0
    .catch Lmiui/reflect/NoSuchMethodException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1552
    move-object v0, v2

    goto :goto_0

    .line 1546
    :catch_0
    move-exception v2

    .line 1547
    aget-object v2, p0, v1

    invoke-virtual {v2}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object v2

    if-eqz v2, :cond_1

    .line 1548
    aget-object v0, p0, v1

    invoke-virtual {v0}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object v0

    aput-object v0, p0, v1

    .line 1549
    invoke-static {p0, p1, p2}, Lcom/android/camera/Util;->getMethod([Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    goto :goto_1

    .line 1552
    :cond_0
    :goto_0
    nop

    .line 1553
    :cond_1
    :goto_1
    if-nez v0, :cond_2

    .line 1554
    const-string p0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "getMethod fail, "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "["

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "]"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1556
    :cond_2
    return-object v0
.end method

.method public static getMiuiBoldTypeface(Landroid/content/Context;)Landroid/graphics/Typeface;
    .locals 1

    .line 1420
    const-string v0, "fonts/MIUI_Bold.ttf"

    invoke-static {p0, v0}, Lcom/android/camera/Util;->getTypeface(Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p0

    return-object p0
.end method

.method public static getMiuiTimeTypeface(Landroid/content/Context;)Landroid/graphics/Typeface;
    .locals 1

    .line 1432
    const-string v0, "fonts/MIUI_Time.ttf"

    invoke-static {p0, v0}, Lcom/android/camera/Util;->getTypeface(Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p0

    return-object p0
.end method

.method public static getMiuiTypeface(Landroid/content/Context;)Landroid/graphics/Typeface;
    .locals 1

    .line 1416
    const-string v0, "fonts/MIUI_Normal.ttf"

    invoke-static {p0, v0}, Lcom/android/camera/Util;->getTypeface(Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p0

    return-object p0
.end method

.method public static getNavigationBarHeight(Landroid/content/Context;)I
    .locals 3

    .line 1791
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    .line 1792
    const-string v0, "navigation_bar_height"

    const-string v1, "dimen"

    const-string v2, "android"

    invoke-virtual {p0, v0, v1, v2}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 1793
    invoke-virtual {p0, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p0

    .line 1794
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "navBarHeight="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 1795
    return p0
.end method

.method public static getOptimalJpegThumbnailSize(Ljava/util/List;D)Lcom/android/camera/CameraSize;
    .locals 13
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/android/camera/CameraSize;",
            ">;D)",
            "Lcom/android/camera/CameraSize;"
        }
    .end annotation

    .line 957
    nop

    .line 958
    const/4 v0, 0x0

    if-nez p0, :cond_0

    .line 959
    const-string p0, "CameraUtil"

    const-string p1, "null thumbnail size list"

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 960
    return-object v0

    .line 963
    :cond_0
    nop

    .line 965
    const-wide/16 v1, 0x0

    .line 967
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_1
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_6

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/android/camera/CameraSize;

    .line 968
    invoke-virtual {v4}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v5

    if-eqz v5, :cond_1

    invoke-virtual {v4}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v5

    if-nez v5, :cond_2

    .line 969
    goto :goto_0

    .line 971
    :cond_2
    invoke-virtual {v4}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v5

    int-to-double v5, v5

    invoke-virtual {v4}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v7

    int-to-double v7, v7

    div-double/2addr v5, v7

    .line 972
    sub-double v7, v5, p1

    invoke-static {v7, v8}, Ljava/lang/Math;->abs(D)D

    move-result-wide v7

    .line 973
    sub-double v9, v1, p1

    invoke-static {v9, v10}, Ljava/lang/Math;->abs(D)D

    move-result-wide v11

    cmpl-double v11, v7, v11

    if-lez v11, :cond_3

    const-wide v11, 0x3f50624dd2f1a9fcL    # 0.001

    cmpl-double v11, v7, v11

    if-lez v11, :cond_3

    .line 974
    goto :goto_0

    .line 976
    :cond_3
    if-eqz v0, :cond_4

    invoke-static {v9, v10}, Ljava/lang/Math;->abs(D)D

    move-result-wide v9

    cmpg-double v7, v7, v9

    if-ltz v7, :cond_4

    .line 977
    invoke-virtual {v4}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v7

    invoke-virtual {v0}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v8

    if-le v7, v8, :cond_5

    .line 978
    :cond_4
    nop

    .line 979
    nop

    .line 981
    move-object v0, v4

    move-wide v1, v5

    :cond_5
    goto :goto_0

    .line 985
    :cond_6
    if-nez v0, :cond_9

    .line 986
    const-string p1, "CameraUtil"

    const-string p2, "No thumbnail size match the aspect ratio"

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 987
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_9

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/android/camera/CameraSize;

    .line 988
    if-eqz v0, :cond_7

    invoke-virtual {p1}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result p2

    invoke-virtual {v0}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v1

    if-le p2, v1, :cond_8

    .line 989
    :cond_7
    nop

    .line 991
    move-object v0, p1

    :cond_8
    goto :goto_1

    .line 993
    :cond_9
    return-object v0
.end method

.method public static getOptimalPreviewSize(IILjava/util/List;D)Lcom/android/camera/CameraSize;
    .locals 21
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(II",
            "Ljava/util/List<",
            "Lcom/android/camera/CameraSize;",
            ">;D)",
            "Lcom/android/camera/CameraSize;"
        }
    .end annotation

    move/from16 v0, p0

    .line 843
    const/4 v1, 0x0

    if-nez p2, :cond_0

    .line 844
    const-string v0, "CameraUtil"

    const-string v2, "null preview size list"

    invoke-static {v0, v2}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 845
    return-object v1

    .line 848
    :cond_0
    nop

    .line 849
    nop

    .line 850
    nop

    .line 851
    nop

    .line 852
    nop

    .line 859
    const-string v3, "camera_reduce_preview_flag"

    const/4 v4, 0x0

    invoke-static {v3, v4}, Lcom/mi/config/d;->getInteger(Ljava/lang/String;I)I

    move-result v3

    .line 865
    const/16 v5, 0x438

    const/4 v7, 0x1

    if-eqz v3, :cond_6

    .line 866
    nop

    .line 867
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v8

    invoke-virtual {v8}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getFrontCameraId()I

    move-result v8

    move/from16 v9, p1

    if-ne v9, v8, :cond_1

    .line 870
    move v8, v7

    goto :goto_0

    .line 867
    :cond_1
    nop

    .line 870
    move v8, v4

    :goto_0
    sget v9, Lcom/android/camera/Util;->sWindowWidth:I

    if-ge v9, v5, :cond_2

    .line 871
    and-int/lit8 v3, v3, -0xf

    .line 873
    :cond_2
    if-eqz v8, :cond_3

    const/4 v8, 0x2

    goto :goto_1

    :cond_3
    move v8, v7

    :goto_1
    const/16 v9, 0xa2

    if-eq v0, v9, :cond_4

    const/16 v9, 0xa9

    if-eq v0, v9, :cond_4

    const/16 v9, 0xa8

    if-eq v0, v9, :cond_4

    const/16 v9, 0xaa

    if-eq v0, v9, :cond_4

    const/16 v9, 0xae

    if-eq v0, v9, :cond_4

    .line 877
    move v0, v4

    goto :goto_2

    :cond_4
    const/4 v0, 0x2

    :goto_2
    shl-int v0, v8, v0

    or-int/2addr v0, v4

    .line 878
    and-int/2addr v0, v3

    if-eqz v0, :cond_5

    .line 886
    move v0, v7

    goto :goto_3

    .line 878
    :cond_5
    nop

    .line 886
    :cond_6
    move v0, v4

    :goto_3
    new-instance v3, Landroid/graphics/Point;

    sget v8, Lcom/android/camera/Util;->sWindowWidth:I

    if-eqz v0, :cond_7

    sget v9, Lcom/android/camera/Util;->sWindowHeight:I

    const/16 v10, 0x780

    invoke-static {v9, v10}, Ljava/lang/Math;->min(II)I

    move-result v9

    goto :goto_4

    :cond_7
    sget v9, Lcom/android/camera/Util;->sWindowHeight:I

    :goto_4
    invoke-direct {v3, v8, v9}, Landroid/graphics/Point;-><init>(II)V

    .line 889
    invoke-static {}, Lcom/mi/config/b;->hi()Z

    move-result v8

    if-nez v8, :cond_8

    .line 890
    invoke-static {}, Lcom/mi/config/b;->hc()Z

    move-result v8

    if-eqz v8, :cond_8

    const/16 v5, 0x2d0

    nop

    .line 891
    :cond_8
    iget v8, v3, Landroid/graphics/Point;->x:I

    if-le v8, v5, :cond_9

    .line 892
    iget v8, v3, Landroid/graphics/Point;->y:I

    mul-int/2addr v8, v5

    iget v9, v3, Landroid/graphics/Point;->x:I

    div-int/2addr v8, v9

    iput v8, v3, Landroid/graphics/Point;->y:I

    .line 893
    iput v5, v3, Landroid/graphics/Point;->x:I

    .line 897
    :cond_9
    invoke-interface/range {p2 .. p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v5

    move-object v10, v1

    const-wide v11, 0x7fefffffffffffffL    # Double.MAX_VALUE

    const-wide v13, 0x7fefffffffffffffL    # Double.MAX_VALUE

    :goto_5
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v15

    if-eqz v15, :cond_10

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v15

    check-cast v15, Lcom/android/camera/CameraSize;

    .line 898
    iget v8, v15, Lcom/android/camera/CameraSize;->width:I

    int-to-double v8, v8

    iget v6, v15, Lcom/android/camera/CameraSize;->height:I

    move-object/from16 v18, v5

    int-to-double v4, v6

    div-double/2addr v8, v4

    .line 900
    sub-double v8, v8, p3

    invoke-static {v8, v9}, Ljava/lang/Math;->abs(D)D

    move-result-wide v8

    const-wide v19, 0x3f947ae147ae147bL    # 0.02

    cmpl-double v6, v8, v19

    if-lez v6, :cond_a

    .line 901
    goto :goto_6

    .line 903
    :cond_a
    if-eqz v0, :cond_c

    iget v6, v3, Landroid/graphics/Point;->x:I

    iget v8, v15, Lcom/android/camera/CameraSize;->height:I

    if-le v6, v8, :cond_b

    iget v6, v3, Landroid/graphics/Point;->y:I

    iget v8, v15, Lcom/android/camera/CameraSize;->width:I

    if-gt v6, v8, :cond_c

    .line 904
    :cond_b
    const-string v6, "CameraUtil"

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "getOptimalPreviewSize: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v15}, Lcom/android/camera/CameraSize;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v9, " | "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Landroid/graphics/Point;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v6, v8}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 905
    nop

    .line 897
    :goto_6
    move-object/from16 v5, v18

    const/4 v4, 0x0

    goto :goto_5

    .line 907
    :cond_c
    iget v6, v3, Landroid/graphics/Point;->x:I

    iget v8, v15, Lcom/android/camera/CameraSize;->height:I

    sub-int/2addr v6, v8

    invoke-static {v6}, Ljava/lang/Math;->abs(I)I

    move-result v6

    iget v8, v3, Landroid/graphics/Point;->y:I

    iget v9, v15, Lcom/android/camera/CameraSize;->width:I

    sub-int/2addr v8, v9

    invoke-static {v8}, Ljava/lang/Math;->abs(I)I

    move-result v8

    add-int/2addr v6, v8

    .line 908
    if-nez v6, :cond_d

    .line 909
    nop

    .line 910
    nop

    .line 911
    nop

    .line 927
    move-object v10, v15

    goto :goto_7

    .line 913
    :cond_d
    iget v8, v15, Lcom/android/camera/CameraSize;->height:I

    iget v9, v3, Landroid/graphics/Point;->x:I

    if-gt v8, v9, :cond_e

    iget v8, v15, Lcom/android/camera/CameraSize;->width:I

    iget v9, v3, Landroid/graphics/Point;->y:I

    if-gt v8, v9, :cond_e

    .line 914
    int-to-double v8, v6

    cmpg-double v19, v8, v11

    if-gez v19, :cond_e

    .line 915
    nop

    .line 916
    nop

    .line 919
    move-wide v11, v8

    move-object v10, v15

    :cond_e
    int-to-double v8, v6

    cmpg-double v6, v8, v13

    if-gez v6, :cond_f

    .line 920
    nop

    .line 921
    nop

    .line 924
    move-wide v13, v8

    move-object v1, v15

    :cond_f
    goto :goto_6

    .line 927
    :cond_10
    move-object v15, v1

    :goto_7
    if-eqz v10, :cond_11

    .line 928
    goto :goto_8

    .line 933
    :cond_11
    move-object v10, v15

    :goto_8
    if-nez v10, :cond_13

    .line 934
    const-string v0, "CameraUtil"

    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v6, "no preview size match the aspect ratio: %.2f"

    new-array v8, v7, [Ljava/lang/Object;

    .line 935
    invoke-static/range {p3 .. p4}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v4

    const/4 v5, 0x0

    aput-object v4, v8, v5

    .line 934
    invoke-static {v1, v6, v8}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 936
    nop

    .line 937
    invoke-interface/range {p2 .. p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const-wide v16, 0x7fefffffffffffffL    # Double.MAX_VALUE

    :goto_9
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_13

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/android/camera/CameraSize;

    .line 938
    iget v2, v3, Landroid/graphics/Point;->x:I

    invoke-virtual {v1}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v4

    sub-int/2addr v2, v4

    invoke-static {v2}, Ljava/lang/Math;->abs(I)I

    move-result v2

    iget v4, v3, Landroid/graphics/Point;->y:I

    invoke-virtual {v1}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v5

    sub-int/2addr v4, v5

    invoke-static {v4}, Ljava/lang/Math;->abs(I)I

    move-result v4

    add-int/2addr v2, v4

    .line 939
    int-to-double v4, v2

    cmpg-double v2, v4, v16

    if-gez v2, :cond_12

    .line 940
    nop

    .line 941
    nop

    .line 943
    move-object v10, v1

    move-wide/from16 v16, v4

    :cond_12
    goto :goto_9

    .line 946
    :cond_13
    if-eqz v10, :cond_14

    .line 947
    const-string v0, "CameraUtil"

    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v2, "best preview size: %dx%d"

    const/4 v3, 0x2

    new-array v3, v3, [Ljava/lang/Object;

    .line 948
    invoke-virtual {v10}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const/4 v5, 0x0

    aput-object v4, v3, v5

    invoke-virtual {v10}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v3, v7

    .line 947
    invoke-static {v1, v2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 950
    :cond_14
    return-object v10
.end method

.method public static getOptimalVideoSnapshotPictureSize(Ljava/util/List;DII)Lcom/android/camera/CameraSize;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/android/camera/CameraSize;",
            ">;DII)",
            "Lcom/android/camera/CameraSize;"
        }
    .end annotation

    .line 1000
    const/4 v0, 0x0

    if-nez p0, :cond_0

    .line 1001
    const-string p0, "CameraUtil"

    const-string p1, "null size list"

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1002
    return-object v0

    .line 1005
    :cond_0
    nop

    .line 1008
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/android/camera/CameraSize;

    .line 1009
    invoke-virtual {v2}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v3

    int-to-double v3, v3

    invoke-virtual {v2}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v5

    int-to-double v5, v5

    div-double/2addr v3, v5

    .line 1010
    sub-double/2addr v3, p1

    invoke-static {v3, v4}, Ljava/lang/Math;->abs(D)D

    move-result-wide v3

    const-wide v5, 0x3f947ae147ae147bL    # 0.02

    cmpl-double v3, v3, v5

    if-lez v3, :cond_1

    .line 1011
    goto :goto_0

    .line 1013
    :cond_1
    if-eqz v0, :cond_2

    invoke-virtual {v2}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v3

    invoke-virtual {v0}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v4

    if-le v3, v4, :cond_3

    .line 1014
    :cond_2
    invoke-virtual {v2}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result v3

    if-gt v3, p3, :cond_3

    invoke-virtual {v2}, Lcom/android/camera/CameraSize;->getHeight()I

    move-result v3

    if-gt v3, p4, :cond_3

    .line 1015
    nop

    .line 1017
    move-object v0, v2

    :cond_3
    goto :goto_0

    .line 1021
    :cond_4
    if-nez v0, :cond_7

    .line 1022
    const-string p1, "CameraUtil"

    const-string p2, "No picture size match the aspect ratio"

    invoke-static {p1, p2}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 1023
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_7

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/android/camera/CameraSize;

    .line 1024
    if-eqz v0, :cond_5

    invoke-virtual {p1}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result p2

    invoke-virtual {v0}, Lcom/android/camera/CameraSize;->getWidth()I

    move-result p3

    if-le p2, p3, :cond_6

    .line 1025
    :cond_5
    nop

    .line 1027
    move-object v0, p1

    :cond_6
    goto :goto_1

    .line 1029
    :cond_7
    return-object v0
.end method

.method public static getPixels([BII[I)[B
    .locals 7

    .line 3566
    if-nez p0, :cond_0

    .line 3567
    const/4 p0, 0x0

    return-object p0

    .line 3569
    :cond_0
    const/4 v0, 0x2

    aget v1, p3, v0

    const/4 v2, 0x3

    aget v3, p3, v2

    mul-int/2addr v1, v3

    mul-int/2addr v1, p2

    new-array v1, v1, [B

    .line 3570
    nop

    .line 3571
    const/4 v3, 0x1

    aget v3, p3, v3

    mul-int/2addr v3, p1

    const/4 v4, 0x0

    aget v5, p3, v4

    add-int/2addr v3, v5

    mul-int/2addr v3, p2

    .line 3572
    move v5, v4

    :goto_0
    aget v6, p3, v2

    if-ge v4, v6, :cond_1

    .line 3573
    aget v6, p3, v0

    mul-int/2addr v6, p2

    invoke-static {p0, v3, v1, v5, v6}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 3574
    mul-int v6, p1, p2

    add-int/2addr v3, v6

    .line 3575
    aget v6, p3, v0

    mul-int/2addr v6, p2

    add-int/2addr v5, v6

    .line 3572
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 3577
    :cond_1
    return-object v1
.end method

.method public static getRatio(Ljava/lang/String;)F
    .locals 1

    .line 2783
    invoke-virtual {p0}, Ljava/lang/String;->hashCode()I

    move-result v0

    sparse-switch v0, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string v0, "19.5x9"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x4

    goto :goto_1

    :sswitch_1
    const-string v0, "18x9"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x3

    goto :goto_1

    :sswitch_2
    const-string v0, "16x9"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_1

    :sswitch_3
    const-string v0, "4x3"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x0

    goto :goto_1

    :sswitch_4
    const-string v0, "1x1"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x2

    goto :goto_1

    :cond_0
    :goto_0
    const/4 p0, -0x1

    :goto_1
    const v0, 0x3faa3d71    # 1.33f

    packed-switch p0, :pswitch_data_0

    .line 2795
    return v0

    .line 2793
    :pswitch_0
    const p0, 0x400a3d71    # 2.16f

    return p0

    .line 2791
    :pswitch_1
    const/high16 p0, 0x40000000    # 2.0f

    return p0

    .line 2789
    :pswitch_2
    const/high16 p0, 0x3f800000    # 1.0f

    return p0

    .line 2787
    :pswitch_3
    const p0, 0x3fe28f5c    # 1.77f

    return p0

    .line 2785
    :pswitch_4
    return v0

    nop

    :sswitch_data_0
    .sparse-switch
        0xc6aa -> :sswitch_4
        0xd1ef -> :sswitch_3
        0x171fa6 -> :sswitch_2
        0x172728 -> :sswitch_1
        0x56d670f0 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static getRelativeLocation(Landroid/view/View;Landroid/view/View;)[I
    .locals 4

    .line 1068
    const/4 v0, 0x2

    new-array v0, v0, [I

    .line 1069
    invoke-virtual {p0, v0}, Landroid/view/View;->getLocationInWindow([I)V

    .line 1070
    const/4 p0, 0x0

    aget v1, v0, p0

    .line 1071
    const/4 v2, 0x1

    aget v3, v0, v2

    .line 1072
    invoke-virtual {p1, v0}, Landroid/view/View;->getLocationInWindow([I)V

    .line 1073
    aget p1, v0, p0

    sub-int/2addr p1, v1

    aput p1, v0, p0

    .line 1074
    aget p0, v0, v2

    sub-int/2addr p0, v3

    aput p0, v0, v2

    .line 1075
    return-object v0
.end method

.method public static getScreenInches(Landroid/content/Context;)D
    .locals 7

    .line 1738
    new-instance v0, Landroid/util/DisplayMetrics;

    invoke-direct {v0}, Landroid/util/DisplayMetrics;-><init>()V

    .line 1739
    const-string v1, "window"

    .line 1740
    invoke-virtual {p0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/view/WindowManager;

    .line 1741
    invoke-interface {p0}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/view/Display;->getMetrics(Landroid/util/DisplayMetrics;)V

    .line 1743
    sget p0, Lcom/android/camera/Util;->sWindowWidth:I

    int-to-float p0, p0

    iget v1, v0, Landroid/util/DisplayMetrics;->xdpi:F

    div-float/2addr p0, v1

    float-to-double v1, p0

    const-wide/high16 v3, 0x4000000000000000L    # 2.0

    invoke-static {v1, v2, v3, v4}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v1

    .line 1744
    sget p0, Lcom/android/camera/Util;->sWindowHeight:I

    int-to-float p0, p0

    iget v0, v0, Landroid/util/DisplayMetrics;->ydpi:F

    div-float/2addr p0, v0

    float-to-double v5, p0

    invoke-static {v5, v6, v3, v4}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v3

    .line 1745
    add-double/2addr v1, v3

    invoke-static {v1, v2}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v0

    .line 1746
    const-string p0, "CameraUtil"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "getScreenInches="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0, v1}, Ljava/lang/StringBuilder;->append(D)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {p0, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1747
    return-wide v0
.end method

.method public static getScreenLightColor(I)I
    .locals 4

    .line 2330
    invoke-static {}, Lcom/android/camera/Util;->initScreenLightColorMap()V

    .line 2331
    sget-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-eqz v0, :cond_3

    sget-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_MAP:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 2335
    :cond_0
    sget-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/Util;->binarySearchRightMost(Ljava/util/List;Ljava/lang/Object;)I

    move-result v0

    .line 2336
    sget-object v1, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-lt v0, v1, :cond_1

    .line 2337
    sget-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    .line 2338
    :cond_1
    if-lez v0, :cond_2

    .line 2339
    sget-object v1, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    sub-int/2addr v1, p0

    sget-object v2, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    add-int/lit8 v3, v0, -0x1

    .line 2340
    invoke-interface {v2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    sub-int v2, p0, v2

    if-le v1, v2, :cond_2

    .line 2341
    nop

    .line 2344
    move v0, v3

    :cond_2
    :goto_0
    const-string v1, "CameraUtil"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "getScreenLightColor "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "K -> "

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object p0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, "K"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v1, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2345
    sget-object p0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_MAP:Ljava/util/List;

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    .line 2332
    :cond_3
    :goto_1
    const-string p0, "CameraUtil"

    const-string v0, "color temperature list empty!"

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2333
    const/4 p0, -0x1

    return p0
.end method

.method public static getSensorOrientation(I)I
    .locals 1

    .line 807
    invoke-static {}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getInstance()Lcom/android/camera/module/loader/camera2/Camera2DataContainer;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/android/camera/module/loader/camera2/Camera2DataContainer;->getCapabilities(I)Lcom/android/camera2/CameraCapabilities;

    move-result-object p0

    .line 808
    invoke-virtual {p0}, Lcom/android/camera2/CameraCapabilities;->getSensorOrientation()I

    move-result p0

    return p0
.end method

.method public static getShootOrientation(Landroid/app/Activity;I)I
    .locals 0

    .line 747
    invoke-static {p0}, Lcom/android/camera/Util;->getDisplayRotation(Landroid/app/Activity;)I

    move-result p0

    sub-int/2addr p1, p0

    add-int/lit16 p1, p1, 0x168

    rem-int/lit16 p1, p1, 0x168

    return p1
.end method

.method public static getShootRotation(Landroid/app/Activity;F)F
    .locals 1

    .line 751
    invoke-static {p0}, Lcom/android/camera/Util;->getDisplayRotation(Landroid/app/Activity;)I

    move-result p0

    int-to-float p0, p0

    sub-float/2addr p1, p0

    .line 752
    :goto_0
    const/4 p0, 0x0

    cmpg-float p0, p1, p0

    const/high16 v0, 0x43b40000    # 360.0f

    if-gez p0, :cond_0

    .line 753
    add-float/2addr p1, v0

    goto :goto_0

    .line 755
    :cond_0
    :goto_1
    cmpl-float p0, p1, v0

    if-lez p0, :cond_1

    .line 756
    sub-float/2addr p1, v0

    goto :goto_1

    .line 758
    :cond_1
    return p1
.end method

.method private static getStaticObjectField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Object;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/NoSuchFieldException;,
            Ljava/lang/SecurityException;,
            Ljava/lang/IllegalArgumentException;,
            Ljava/lang/IllegalAccessException;
        }
    .end annotation

    .line 2493
    invoke-virtual {p0, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p0

    .line 2494
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Ljava/lang/reflect/Field;->setAccessible(Z)V

    .line 2495
    invoke-virtual {p0, p1}, Ljava/lang/reflect/Field;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static getStatusBarHeight(Landroid/content/Context;)I
    .locals 4

    .line 1812
    nop

    .line 1813
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v1, "status_bar_height"

    const-string v2, "dimen"

    const-string v3, "android"

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 1814
    if-lez v0, :cond_0

    .line 1815
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p0

    goto :goto_0

    .line 1817
    :cond_0
    const/4 p0, 0x0

    :goto_0
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "StatusBarHeight="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 1818
    return p0
.end method

.method public static getStorageDirectory()Ljava/io/File;
    .locals 1

    .line 1372
    invoke-static {}, Lcom/android/camera/Util;->isExternalStorageMounted()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1373
    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    goto :goto_0

    :cond_0
    sget-object v0, Lcom/android/camera/Util;->INTERNAL_STORAGE_DIRECTORY:Ljava/io/File;

    .line 1372
    :goto_0
    return-object v0
.end method

.method public static getSubYuvImage([BII[I)Lcom/android/camera/effect/MiYuvImage;
    .locals 9

    .line 3610
    const/4 v0, 0x2

    aget v1, p3, v0

    const/4 v2, 0x3

    aget v3, p3, v2

    mul-int/2addr v1, v3

    mul-int/2addr v1, v2

    div-int/2addr v1, v0

    new-array v1, v1, [B

    .line 3613
    const/4 v3, 0x1

    aget v4, p3, v3

    mul-int/2addr v4, p1

    const/4 v5, 0x0

    aget v6, p3, v5

    add-int/2addr v4, v6

    .line 3614
    nop

    .line 3615
    move v6, v4

    move v4, v5

    move v7, v4

    :goto_0
    aget v8, p3, v2

    if-ge v4, v8, :cond_0

    .line 3616
    aget v8, p3, v0

    invoke-static {p0, v6, v1, v7, v8}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 3617
    add-int/2addr v6, p1

    .line 3618
    aget v8, p3, v0

    add-int/2addr v7, v8

    .line 3615
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 3621
    :cond_0
    mul-int/2addr p2, p1

    aget v3, p3, v3

    div-int/2addr v3, v0

    mul-int/2addr v3, p1

    add-int/2addr p2, v3

    aget v3, p3, v5

    add-int/2addr p2, v3

    .line 3622
    :goto_1
    aget v3, p3, v2

    div-int/2addr v3, v0

    if-ge v5, v3, :cond_1

    .line 3623
    aget v3, p3, v0

    invoke-static {p0, p2, v1, v7, v3}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 3624
    add-int/2addr p2, p1

    .line 3625
    aget v3, p3, v0

    add-int/2addr v7, v3

    .line 3622
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    .line 3627
    :cond_1
    new-instance p0, Lcom/android/camera/effect/MiYuvImage;

    aget p1, p3, v0

    aget p2, p3, v2

    const/16 p3, 0x23

    invoke-direct {p0, v1, p1, p2, p3}, Lcom/android/camera/effect/MiYuvImage;-><init>([BIII)V

    .line 3628
    return-object p0
.end method

.method public static getTimeWaterMarkData(IILjava/lang/String;[I)[B
    .locals 2

    .line 2374
    new-instance v0, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;

    const/4 v1, 0x0

    invoke-direct {v0, p2, p0, p1, v1}, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;-><init>(Ljava/lang/String;III)V

    .line 2375
    if-eqz p3, :cond_0

    array-length p0, p3

    const/4 p1, 0x4

    if-lt p0, p1, :cond_0

    .line 2376
    invoke-virtual {v0}, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;->getWidth()I

    move-result p0

    aput p0, p3, v1

    .line 2377
    const/4 p0, 0x1

    invoke-virtual {v0}, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;->getHeight()I

    move-result p1

    aput p1, p3, p0

    .line 2378
    const/4 p0, 0x2

    invoke-virtual {v0}, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;->getPaddingX()I

    move-result p1

    aput p1, p3, p0

    .line 2379
    const/4 p0, 0x3

    invoke-virtual {v0}, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;->getPaddingY()I

    move-result p1

    aput p1, p3, p0

    .line 2381
    :cond_0
    invoke-virtual {v0}, Lcom/android/camera/effect/renders/NewStyleTextWaterMark;->getTexture()Lcom/android/gallery3d/ui/BasicTexture;

    move-result-object p0

    check-cast p0, Lcom/android/gallery3d/ui/StringTexture;

    sget-object p1, Landroid/graphics/Bitmap$CompressFormat;->PNG:Landroid/graphics/Bitmap$CompressFormat;

    invoke-virtual {p0, p1}, Lcom/android/gallery3d/ui/StringTexture;->getBitmapData(Landroid/graphics/Bitmap$CompressFormat;)[B

    move-result-object p0

    .line 2382
    return-object p0
.end method

.method public static getTimeWatermark()Ljava/lang/String;
    .locals 1

    .line 1516
    invoke-static {}, Lcom/mi/config/b;->gr()Z

    move-result v0

    invoke-static {v0}, Lcom/android/camera/Util;->getTimeWatermark(Z)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static getTimeWatermark(Z)Ljava/lang/String;
    .locals 7

    .line 1520
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 1521
    if-eqz p0, :cond_0

    .line 1522
    new-instance p0, Ljava/text/SimpleDateFormat;

    const-string v1, "yyyy/M/d"

    sget-object v2, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    invoke-direct {p0, v1, v2}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    new-instance v1, Ljava/util/Date;

    invoke-direct {v1}, Ljava/util/Date;-><init>()V

    .line 1523
    invoke-virtual {p0, v1}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->toCharArray()[C

    move-result-object p0

    .line 1524
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append([C)Ljava/lang/StringBuilder;

    .line 1525
    goto :goto_0

    .line 1526
    :cond_0
    new-instance p0, Ljava/text/SimpleDateFormat;

    const-string v1, "yyyy-M-d"

    sget-object v2, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    invoke-direct {p0, v1, v2}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;Ljava/util/Locale;)V

    new-instance v1, Ljava/util/Date;

    invoke-direct {v1}, Ljava/util/Date;-><init>()V

    .line 1527
    invoke-virtual {p0, v1}, Ljava/text/SimpleDateFormat;->format(Ljava/util/Date;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->toCharArray()[C

    move-result-object p0

    .line 1528
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append([C)Ljava/lang/StringBuilder;

    .line 1530
    :goto_0
    const-string p0, " "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1531
    new-instance p0, Landroid/text/format/Time;

    invoke-direct {p0}, Landroid/text/format/Time;-><init>()V

    .line 1532
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-virtual {p0, v1, v2}, Landroid/text/format/Time;->set(J)V

    .line 1533
    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v2, "%02d"

    const/4 v3, 0x1

    new-array v4, v3, [Ljava/lang/Object;

    iget v5, p0, Landroid/text/format/Time;->hour:I

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    const/4 v6, 0x0

    aput-object v5, v4, v6

    invoke-static {v1, v2, v4}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1534
    const-string v1, ":"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1535
    sget-object v1, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v2, "%02d"

    new-array v3, v3, [Ljava/lang/Object;

    iget p0, p0, Landroid/text/format/Time;->minute:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    aput-object p0, v3, v6

    invoke-static {v1, v2, v3}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1537
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static declared-synchronized getTypeface(Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;
    .locals 2

    const-class v0, Lcom/android/camera/Util;

    monitor-enter v0

    .line 1436
    :try_start_0
    sget-object v1, Lcom/android/camera/Util;->sTypefaces:Ljava/util/HashMap;

    invoke-virtual {v1, p1}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 1437
    sget-object v1, Lcom/android/camera/Util;->sTypefaces:Ljava/util/HashMap;

    invoke-virtual {p0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    invoke-static {p0, p1}, Landroid/graphics/Typeface;->createFromAsset(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;

    move-result-object p0

    invoke-virtual {v1, p1, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 1439
    :cond_0
    sget-object p0, Lcom/android/camera/Util;->sTypefaces:Ljava/util/HashMap;

    invoke-virtual {p0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/graphics/Typeface;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object p0

    .line 1435
    :catchall_0
    move-exception p0

    monitor-exit v0

    throw p0
.end method

.method public static getWatermarkRange(IIIZZF)[I
    .locals 7

    .line 3450
    const/4 v0, 0x4

    new-array v1, v0, [I

    .line 3451
    const/4 v2, 0x3

    const/4 v3, 0x1

    const/4 v4, 0x2

    const/4 v5, 0x0

    if-eqz p2, :cond_9

    const/16 v6, 0x5a

    if-eq p2, v6, :cond_6

    const/16 v6, 0xb4

    if-eq p2, v6, :cond_3

    const/16 v6, 0x10e

    if-eq p2, v6, :cond_0

    goto/16 :goto_0

    .line 3453
    :cond_0
    if-eqz p3, :cond_1

    if-eqz p4, :cond_1

    .line 3454
    aput v5, v1, v5

    .line 3455
    int-to-float p2, p1

    mul-float/2addr p2, p5

    float-to-int p2, p2

    sub-int/2addr p1, p2

    aput p1, v1, v3

    .line 3456
    aput p0, v1, v4

    .line 3457
    aput p2, v1, v2

    goto/16 :goto_0

    .line 3458
    :cond_1
    if-eqz p3, :cond_2

    .line 3459
    aput v5, v1, v5

    .line 3460
    int-to-float p2, p1

    mul-float/2addr p2, p5

    float-to-int p2, p2

    sub-int/2addr p1, p2

    aput p1, v1, v3

    .line 3461
    div-int/2addr p0, v4

    aput p0, v1, v4

    .line 3462
    aput p2, v1, v2

    goto/16 :goto_0

    .line 3464
    :cond_2
    div-int/2addr p0, v4

    aput p0, v1, v5

    .line 3465
    int-to-float p2, p1

    mul-float/2addr p2, p5

    float-to-int p2, p2

    sub-int/2addr p1, p2

    aput p1, v1, v3

    .line 3466
    aput p0, v1, v4

    .line 3467
    aput p2, v1, v2

    .line 3469
    goto/16 :goto_0

    .line 3507
    :cond_3
    if-eqz p3, :cond_4

    if-eqz p4, :cond_4

    .line 3508
    aput v5, v1, v5

    .line 3509
    aput v5, v1, v3

    .line 3510
    int-to-float p0, p0

    mul-float/2addr p0, p5

    float-to-int p0, p0

    aput p0, v1, v4

    .line 3511
    aput p1, v1, v2

    goto/16 :goto_0

    .line 3512
    :cond_4
    if-eqz p3, :cond_5

    .line 3513
    aput v5, v1, v5

    .line 3514
    aput v5, v1, v3

    .line 3515
    int-to-float p0, p0

    mul-float/2addr p0, p5

    float-to-int p0, p0

    aput p0, v1, v4

    .line 3516
    div-int/2addr p1, v4

    aput p1, v1, v2

    goto :goto_0

    .line 3518
    :cond_5
    aput v5, v1, v5

    .line 3519
    div-int/2addr p1, v4

    aput p1, v1, v3

    .line 3520
    int-to-float p0, p0

    mul-float/2addr p0, p5

    float-to-int p0, p0

    aput p0, v1, v4

    .line 3521
    aput p1, v1, v2

    .line 3523
    goto :goto_0

    .line 3489
    :cond_6
    if-eqz p3, :cond_7

    if-eqz p4, :cond_7

    .line 3490
    aput v5, v1, v5

    .line 3491
    aput v5, v1, v3

    .line 3492
    aput p0, v1, v4

    .line 3493
    int-to-float p0, p1

    mul-float/2addr p0, p5

    float-to-int p0, p0

    aput p0, v1, v2

    goto :goto_0

    .line 3494
    :cond_7
    if-eqz p3, :cond_8

    .line 3495
    div-int/2addr p0, v4

    aput p0, v1, v5

    .line 3496
    aput v5, v1, v3

    .line 3497
    aput p0, v1, v4

    .line 3498
    int-to-float p0, p1

    mul-float/2addr p0, p5

    float-to-int p0, p0

    aput p0, v1, v2

    goto :goto_0

    .line 3500
    :cond_8
    aput v5, v1, v5

    .line 3501
    aput v5, v1, v3

    .line 3502
    div-int/2addr p0, v4

    aput p0, v1, v4

    .line 3503
    int-to-float p0, p1

    mul-float/2addr p0, p5

    float-to-int p0, p0

    aput p0, v1, v2

    .line 3505
    goto :goto_0

    .line 3471
    :cond_9
    if-eqz p3, :cond_a

    if-eqz p4, :cond_a

    .line 3472
    int-to-float p2, p0

    mul-float/2addr p2, p5

    float-to-int p2, p2

    sub-int/2addr p0, p2

    aput p0, v1, v5

    .line 3473
    aput v5, v1, v3

    .line 3474
    aput p2, v1, v4

    .line 3475
    aput p1, v1, v2

    goto :goto_0

    .line 3476
    :cond_a
    if-eqz p3, :cond_b

    .line 3477
    int-to-float p2, p0

    mul-float/2addr p2, p5

    float-to-int p2, p2

    sub-int/2addr p0, p2

    aput p0, v1, v5

    .line 3478
    div-int/2addr p1, v4

    aput p1, v1, v3

    .line 3479
    aput p2, v1, v4

    .line 3480
    aput p1, v1, v2

    goto :goto_0

    .line 3482
    :cond_b
    int-to-float p2, p0

    mul-float/2addr p2, p5

    float-to-int p2, p2

    sub-int/2addr p0, p2

    aput p0, v1, v5

    .line 3483
    aput v5, v1, v3

    .line 3484
    aput p2, v1, v4

    .line 3485
    div-int/2addr p1, v4

    aput p1, v1, v2

    .line 3487
    nop

    .line 3529
    :goto_0
    aget p0, v1, v5

    div-int/2addr p0, v4

    mul-int/2addr p0, v4

    aput p0, v1, v5

    .line 3530
    aget p0, v1, v3

    div-int/2addr p0, v4

    mul-int/2addr p0, v4

    aput p0, v1, v3

    .line 3531
    aget p0, v1, v4

    div-int/2addr p0, v0

    mul-int/2addr p0, v0

    aput p0, v1, v4

    .line 3532
    aget p0, v1, v2

    div-int/2addr p0, v0

    mul-int/2addr p0, v0

    aput p0, v1, v2

    .line 3533
    return-object v1
.end method

.method private static initScreenLightColorMap()V
    .locals 9

    .line 2218
    sget-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-gtz v0, :cond_b

    sget-object v0, Lcom/android/camera/Util;->COLOR_TEMPERATURE_MAP:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-lez v0, :cond_0

    goto/16 :goto_6

    .line 2222
    :cond_0
    invoke-static {}, Lcom/android/camera/Util;->getColorMapXmlMapFile()Ljava/io/File;

    move-result-object v0

    .line 2223
    nop

    .line 2224
    nop

    .line 2225
    const/4 v1, 0x0

    const/4 v2, 0x0

    if-eqz v0, :cond_1

    .line 2227
    :try_start_0
    new-instance v3, Ljava/io/FileReader;

    invoke-direct {v3, v0}, Ljava/io/FileReader;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_2

    .line 2228
    :try_start_1
    invoke-static {}, Lorg/xmlpull/v1/XmlPullParserFactory;->newInstance()Lorg/xmlpull/v1/XmlPullParserFactory;

    move-result-object v0

    .line 2229
    invoke-virtual {v0, v2}, Lorg/xmlpull/v1/XmlPullParserFactory;->setNamespaceAware(Z)V

    .line 2230
    invoke-virtual {v0}, Lorg/xmlpull/v1/XmlPullParserFactory;->newPullParser()Lorg/xmlpull/v1/XmlPullParser;

    move-result-object v0
    :try_end_1
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/io/FileNotFoundException; {:try_start_1 .. :try_end_1} :catch_1

    .line 2231
    :try_start_2
    invoke-interface {v0, v3}, Lorg/xmlpull/v1/XmlPullParser;->setInput(Ljava/io/Reader;)V
    :try_end_2
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_2 .. :try_end_2} :catch_0
    .catch Ljava/io/FileNotFoundException; {:try_start_2 .. :try_end_2} :catch_0

    .line 2234
    goto :goto_1

    .line 2232
    :catch_0
    move-exception v4

    goto :goto_0

    :catch_1
    move-exception v4

    move-object v0, v1

    goto :goto_0

    :catch_2
    move-exception v4

    move-object v0, v1

    move-object v3, v0

    .line 2233
    :goto_0
    invoke-virtual {v4}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_1

    .line 2237
    :cond_1
    move-object v0, v1

    move-object v3, v0

    :goto_1
    if-nez v0, :cond_3

    .line 2238
    const-string v0, "CameraUtil"

    const-string v4, "Cannot find screen color map in system, try local resource."

    invoke-static {v0, v4}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2239
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const-string v4, "screen_light"

    const-string v5, "xml"

    .line 2240
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v6

    invoke-virtual {v6}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v6

    .line 2239
    invoke-virtual {v0, v4, v5, v6}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 2241
    if-gtz v0, :cond_2

    .line 2242
    const-string v0, "CameraUtil"

    const-string v1, "res/xml/screen_light.xml not found!"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2243
    return-void

    .line 2245
    :cond_2
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4, v0}, Landroid/content/res/Resources;->getXml(I)Landroid/content/res/XmlResourceParser;

    move-result-object v0

    .line 2249
    :cond_3
    :goto_2
    :try_start_3
    invoke-interface {v0}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v4

    const/4 v5, 0x3

    if-eq v4, v5, :cond_8

    .line 2250
    invoke-interface {v0}, Lorg/xmlpull/v1/XmlPullParser;->getEventType()I

    move-result v4

    const/4 v5, 0x2

    if-eq v4, v5, :cond_4

    .line 2251
    goto :goto_2

    .line 2254
    :cond_4
    const-string v4, "screen"

    invoke-interface {v0}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v4, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 2255
    sget-object v4, Lcom/android/camera/Util;->SCREEN_VENDOR:Ljava/lang/String;

    const-string v6, "vendor"

    invoke-interface {v0, v1, v6}, Lorg/xmlpull/v1/XmlPullParser;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v4, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_5

    .line 2256
    invoke-static {v0}, Lcom/android/camera/Util;->skip(Lorg/xmlpull/v1/XmlPullParser;)V

    goto :goto_2

    .line 2258
    :cond_5
    const-string v1, "CameraUtil"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "load screen light parameters for "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/android/camera/Util;->SCREEN_VENDOR:Ljava/lang/String;

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v1, v4}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2259
    :goto_3
    invoke-interface {v0}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v1

    const/4 v4, 0x1

    if-eq v1, v4, :cond_8

    .line 2260
    invoke-interface {v0}, Lorg/xmlpull/v1/XmlPullParser;->getEventType()I

    move-result v1

    if-eq v1, v5, :cond_6

    .line 2261
    goto :goto_3

    .line 2263
    :cond_6
    const-string v1, "light"

    invoke-interface {v0}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_7

    .line 2264
    goto :goto_4

    .line 2266
    :cond_7
    const-string v1, "CCT"

    invoke-static {v0, v1, v2}, Lcom/android/camera/Util;->getAttributeIntValue(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)I

    move-result v1

    .line 2267
    const-string v4, "R"

    invoke-static {v0, v4, v2}, Lcom/android/camera/Util;->getAttributeIntValue(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)I

    move-result v4

    .line 2268
    const-string v6, "G"

    invoke-static {v0, v6, v2}, Lcom/android/camera/Util;->getAttributeIntValue(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)I

    move-result v6

    .line 2269
    const-string v7, "B"

    invoke-static {v0, v7, v2}, Lcom/android/camera/Util;->getAttributeIntValue(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)I

    move-result v7

    .line 2270
    sget-object v8, Lcom/android/camera/Util;->COLOR_TEMPERATURE_LIST:Ljava/util/List;

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v8, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 2271
    sget-object v1, Lcom/android/camera/Util;->COLOR_TEMPERATURE_MAP:Ljava/util/List;

    invoke-static {v4, v6, v7}, Landroid/graphics/Color;->rgb(III)I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {v1, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_3
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_3 .. :try_end_3} :catch_4
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 2272
    goto :goto_3

    .line 2281
    :cond_8
    :goto_4
    invoke-static {v3}, Lcom/android/camera/Util;->closeSafely(Ljava/io/Closeable;)V

    .line 2282
    instance-of v1, v0, Landroid/content/res/XmlResourceParser;

    if-eqz v1, :cond_a

    goto :goto_5

    .line 2281
    :catchall_0
    move-exception v1

    invoke-static {v3}, Lcom/android/camera/Util;->closeSafely(Ljava/io/Closeable;)V

    .line 2282
    instance-of v2, v0, Landroid/content/res/XmlResourceParser;

    if-eqz v2, :cond_9

    .line 2283
    check-cast v0, Landroid/content/res/XmlResourceParser;

    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    :cond_9
    throw v1

    .line 2279
    :catch_3
    move-exception v1

    .line 2281
    invoke-static {v3}, Lcom/android/camera/Util;->closeSafely(Ljava/io/Closeable;)V

    .line 2282
    instance-of v1, v0, Landroid/content/res/XmlResourceParser;

    if-eqz v1, :cond_a

    goto :goto_5

    .line 2278
    :catch_4
    move-exception v1

    .line 2281
    invoke-static {v3}, Lcom/android/camera/Util;->closeSafely(Ljava/io/Closeable;)V

    .line 2282
    instance-of v1, v0, Landroid/content/res/XmlResourceParser;

    if-eqz v1, :cond_a

    .line 2283
    :goto_5
    check-cast v0, Landroid/content/res/XmlResourceParser;

    invoke-interface {v0}, Landroid/content/res/XmlResourceParser;->close()V

    .line 2286
    :cond_a
    return-void

    .line 2219
    :cond_b
    :goto_6
    return-void
.end method

.method public static initialize(Landroid/content/Context;)V
    .locals 7

    .line 307
    const-string v0, "ro.miui.notch"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Landroid/os/SystemProperties;->getInt(Ljava/lang/String;I)I

    move-result v0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    sput-boolean v0, Lcom/android/camera/Util;->isNotchDevice:Z

    .line 308
    invoke-static {p0}, Lcom/android/camera/Util;->isFullScreenNavBarHidden(Landroid/content/Context;)Z

    move-result v0

    sput-boolean v0, Lcom/android/camera/Util;->sIsFullScreenNavBarHidden:Z

    .line 309
    new-instance v0, Landroid/util/DisplayMetrics;

    invoke-direct {v0}, Landroid/util/DisplayMetrics;-><init>()V

    .line 310
    const-string v3, "window"

    .line 311
    invoke-virtual {p0, v3}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/view/WindowManager;

    .line 312
    invoke-interface {v3}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object v4

    invoke-virtual {v4, v0}, Landroid/view/Display;->getMetrics(Landroid/util/DisplayMetrics;)V

    .line 313
    iget v0, v0, Landroid/util/DisplayMetrics;->noncompatDensity:F

    sput v0, Lcom/android/camera/Util;->sPixelDensity:F

    .line 314
    new-instance v0, Lcom/android/camera/Util$ImageFileNamer;

    const v4, 0x7f0900c8

    .line 315
    invoke-virtual {p0, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-direct {v0, v4}, Lcom/android/camera/Util$ImageFileNamer;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/android/camera/Util;->sImageFileNamer:Lcom/android/camera/Util$ImageFileNamer;

    .line 317
    const-string v0, "accessibility"

    .line 318
    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/accessibility/AccessibilityManager;

    .line 319
    invoke-virtual {v0}, Landroid/view/accessibility/AccessibilityManager;->isEnabled()Z

    move-result v0

    sput-boolean v0, Lcom/android/camera/Util;->sIsAccessibilityEnable:Z

    .line 321
    new-instance v0, Landroid/graphics/Point;

    invoke-direct {v0}, Landroid/graphics/Point;-><init>()V

    .line 322
    invoke-interface {v3}, Landroid/view/WindowManager;->getDefaultDisplay()Landroid/view/Display;

    move-result-object v3

    invoke-virtual {v3, v0}, Landroid/view/Display;->getRealSize(Landroid/graphics/Point;)V

    .line 326
    iget v3, v0, Landroid/graphics/Point;->x:I

    iget v4, v0, Landroid/graphics/Point;->y:I

    if-ge v3, v4, :cond_1

    .line 327
    iget v3, v0, Landroid/graphics/Point;->x:I

    sput v3, Lcom/android/camera/Util;->sWindowWidth:I

    .line 328
    iget v0, v0, Landroid/graphics/Point;->y:I

    sput v0, Lcom/android/camera/Util;->sWindowHeight:I

    goto :goto_1

    .line 330
    :cond_1
    iget v3, v0, Landroid/graphics/Point;->y:I

    sput v3, Lcom/android/camera/Util;->sWindowWidth:I

    .line 331
    iget v0, v0, Landroid/graphics/Point;->x:I

    sput v0, Lcom/android/camera/Util;->sWindowHeight:I

    .line 333
    :goto_1
    sget v0, Lcom/android/camera/Util;->sWindowWidth:I

    sget v3, Lcom/android/camera/Util;->sWindowHeight:I

    invoke-static {v0, v3}, Lcom/android/camera/Util;->isFind19_5_9LongRatioScreen(II)Z

    move-result v0

    sput-boolean v0, Lcom/android/camera/Util;->isLongRatioScreen:Z

    .line 334
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v3, 0x7f0a00be

    invoke-virtual {v0, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    sput v0, Lcom/android/camera/Util;->sFullScreenExtraMargin:I

    .line 335
    invoke-static {p0}, Lcom/android/camera/Util;->checkDeviceHasNavigationBar(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 336
    invoke-static {p0}, Lcom/android/camera/Util;->getNavigationBarHeight(Landroid/content/Context;)I

    move-result v0

    goto :goto_2

    :cond_2
    invoke-static {p0}, Lcom/android/camera/Util;->calcNavigationBarHeight(Landroid/content/Context;)I

    move-result v0

    :goto_2
    sput v0, Lcom/android/camera/Util;->sNavigationBarHeight:I

    .line 337
    sget-boolean v0, Lcom/android/camera/Util;->isNotchDevice:Z

    const/4 v3, 0x2

    if-eqz v0, :cond_4

    .line 338
    sget-boolean v0, Lcom/android/camera/Util;->isLongRatioScreen:Z

    if-eqz v0, :cond_3

    .line 339
    invoke-static {p0}, Lcom/android/camera/Util;->getStatusBarHeight(Landroid/content/Context;)I

    move-result v0

    sput v0, Lcom/android/camera/Util;->sStatusBarHeight:I

    goto :goto_3

    .line 341
    :cond_3
    sget v0, Lcom/android/camera/Util;->sWindowHeight:I

    sget v4, Lcom/android/camera/Util;->sWindowWidth:I

    mul-int/2addr v4, v3

    sub-int/2addr v0, v4

    sput v0, Lcom/android/camera/Util;->sStatusBarHeight:I

    .line 344
    :goto_3
    sget-boolean v0, Lcom/android/camera/Util;->sIsFullScreenNavBarHidden:Z

    if-eqz v0, :cond_4

    sget-boolean v0, Lcom/android/camera/Util;->isLongRatioScreen:Z

    if-nez v0, :cond_4

    .line 345
    sget v0, Lcom/android/camera/Util;->sNavigationBarHeight:I

    sget v4, Lcom/android/camera/Util;->sFullScreenExtraMargin:I

    sub-int/2addr v0, v4

    sput v0, Lcom/android/camera/Util;->sNavigationBarHeight:I

    .line 346
    sget v0, Lcom/android/camera/Util;->sStatusBarHeight:I

    sget v4, Lcom/android/camera/Util;->sFullScreenExtraMargin:I

    div-int/2addr v4, v3

    add-int/2addr v0, v4

    sput v0, Lcom/android/camera/Util;->sStatusBarHeight:I

    .line 349
    :cond_4
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-static {p0}, Lcom/android/camera/Util;->getBottomHeight(Landroid/content/res/Resources;)I

    move-result p0

    sput p0, Lcom/android/camera/CameraSettings;->BOTTOM_CONTROL_HEIGHT:I

    .line 351
    const-string p0, "CameraUtil"

    sget-object v0, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    const-string v4, "windowSize=%dx%d density=%.2f"

    const/4 v5, 0x3

    new-array v5, v5, [Ljava/lang/Object;

    sget v6, Lcom/android/camera/Util;->sWindowWidth:I

    .line 352
    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v1

    sget v1, Lcom/android/camera/Util;->sWindowHeight:I

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    aput-object v1, v5, v2

    sget v1, Lcom/android/camera/Util;->sPixelDensity:F

    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    aput-object v1, v5, v3

    .line 351
    invoke-static {v0, v4, v5}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 353
    return-void
.end method

.method public static installPackage(Landroid/content/Context;Ljava/lang/String;Lcom/android/camera/lib/compatibility/util/CompatibilityUtils$PackageInstallerListener;ZZ)V
    .locals 7

    .line 1990
    if-eqz p0, :cond_4

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    .line 1996
    :cond_0
    const/4 v0, 0x0

    :try_start_0
    invoke-static {p2}, Lcom/android/camera/lib/compatibility/util/CompatibilityUtils;->getPackageInstallObserver(Lcom/android/camera/lib/compatibility/util/CompatibilityUtils$PackageInstallerListener;)Ljava/lang/Object;

    move-result-object v1

    .line 1998
    const-string v2, "miui.content.pm.PreloadedAppPolicy"

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 1999
    const-string v3, "installPreloadedDataApp"

    .line 2000
    invoke-static {}, Lcom/android/camera/lib/compatibility/util/CompatibilityUtils;->getInstallMethodDescription()Ljava/lang/String;

    move-result-object v4

    .line 1999
    invoke-static {v2, v3, v4}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v3

    .line 2002
    nop

    .line 2003
    const/4 v4, 0x2

    const/4 v5, 0x1

    if-eqz p3, :cond_1

    .line 2005
    nop

    .line 2010
    move p3, v5

    goto :goto_0

    .line 2006
    :cond_1
    if-eqz p4, :cond_2

    .line 2008
    nop

    .line 2010
    move p3, v4

    goto :goto_0

    :cond_2
    move p3, v0

    :goto_0
    const/4 p4, 0x0

    const/4 v6, 0x4

    new-array v6, v6, [Ljava/lang/Object;

    aput-object p0, v6, v0

    aput-object p1, v6, v5

    aput-object v1, v6, v4

    const/4 p0, 0x3

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    aput-object p3, v6, p0

    invoke-virtual {v3, v2, p4, v6}, Lmiui/reflect/Method;->invokeBoolean(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result p0

    .line 2011
    const-string p3, "CameraUtil"

    new-instance p4, Ljava/lang/StringBuilder;

    invoke-direct {p4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "installPackage: result="

    invoke-virtual {p4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, p0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p3, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 2017
    goto :goto_1

    .line 2012
    :catch_0
    move-exception p0

    .line 2013
    const-string p3, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p4

    invoke-static {p3, p4, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2014
    if-eqz p2, :cond_3

    .line 2015
    invoke-interface {p2, p1, v0}, Lcom/android/camera/lib/compatibility/util/CompatibilityUtils$PackageInstallerListener;->onPackageInstalled(Ljava/lang/String;Z)V

    .line 2018
    :cond_3
    :goto_1
    return-void

    .line 1991
    :cond_4
    :goto_2
    const-string p0, "CameraUtil"

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "invalid params. pkgName="

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 1992
    return-void
.end method

.method private static isAccessibilityEnable()Z
    .locals 1

    .line 2426
    sget-boolean v0, Lcom/android/camera/Util;->sIsAccessibilityEnable:Z

    return v0
.end method

.method public static isAccessible()Z
    .locals 2

    .line 2418
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xe

    if-lt v0, v1, :cond_0

    invoke-static {}, Lcom/android/camera/Util;->isAccessibilityEnable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2419
    const/4 v0, 0x1

    return v0

    .line 2421
    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public static isActivityInvert(Landroid/app/Activity;)Z
    .locals 1

    .line 786
    invoke-static {p0}, Lcom/android/camera/Util;->getDisplayRotation(Landroid/app/Activity;)I

    move-result p0

    const/16 v0, 0xb4

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isAntibanding60()Z
    .locals 2

    .line 266
    sget-object v0, Lcom/android/camera/Util;->ANTIBANDING_60_COUNTRY:Ljava/util/HashSet;

    sget-object v1, Lcom/android/camera/Util;->mCountryIso:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static final isAppLocked(Landroid/content/Context;Ljava/lang/String;)Z
    .locals 3

    .line 2037
    nop

    .line 2038
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    const-string v1, "access_control_lock_enabled"

    const/4 v2, -0x1

    invoke-static {v0, v1, v2}, Landroid/provider/Settings$Secure;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    .line 2040
    move v0, v2

    goto :goto_0

    .line 2038
    :cond_0
    nop

    .line 2040
    move v0, v1

    :goto_0
    if-eqz v0, :cond_2

    .line 2041
    const-string v0, "security"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lmiui/security/SecurityManager;

    .line 2042
    invoke-virtual {p0, p1}, Lmiui/security/SecurityManager;->getApplicationAccessControlEnabled(Ljava/lang/String;)Z

    move-result v0

    .line 2043
    invoke-virtual {p0, p1}, Lmiui/security/SecurityManager;->checkAccessControlPass(Ljava/lang/String;)Z

    move-result p0

    .line 2044
    if-eqz v0, :cond_1

    if-nez p0, :cond_1

    move v1, v2

    nop

    :cond_1
    return v1

    .line 2046
    :cond_2
    return v1
.end method

.method public static isContains(Landroid/graphics/RectF;Landroid/graphics/RectF;)Z
    .locals 3

    .line 1752
    const/4 v0, 0x0

    if-eqz p0, :cond_2

    if-nez p1, :cond_0

    goto :goto_0

    .line 1755
    :cond_0
    iget v1, p0, Landroid/graphics/RectF;->left:F

    iget v2, p0, Landroid/graphics/RectF;->right:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_1

    iget v1, p0, Landroid/graphics/RectF;->top:F

    iget v2, p0, Landroid/graphics/RectF;->bottom:F

    cmpg-float v1, v1, v2

    if-gez v1, :cond_1

    iget v1, p0, Landroid/graphics/RectF;->left:F

    iget v2, p1, Landroid/graphics/RectF;->left:F

    cmpg-float v1, v1, v2

    if-gtz v1, :cond_1

    iget v1, p0, Landroid/graphics/RectF;->top:F

    iget v2, p1, Landroid/graphics/RectF;->top:F

    cmpg-float v1, v1, v2

    if-gtz v1, :cond_1

    iget v1, p0, Landroid/graphics/RectF;->right:F

    iget v2, p1, Landroid/graphics/RectF;->right:F

    cmpl-float v1, v1, v2

    if-ltz v1, :cond_1

    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    iget p1, p1, Landroid/graphics/RectF;->bottom:F

    cmpl-float p0, p0, p1

    if-ltz p0, :cond_1

    const/4 v0, 0x1

    nop

    :cond_1
    return v0

    .line 1753
    :cond_2
    :goto_0
    return v0
.end method

.method public static isDebugOsBuild()Z
    .locals 2

    .line 2070
    const-string v0, "userdebug"

    sget-object v1, Landroid/os/Build;->TYPE:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "eng"

    sget-object v1, Landroid/os/Build;->TYPE:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    sget-boolean v0, Lcom/android/camera/Util;->sIsDumpLog:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method private static isDevices(Ljava/lang/String;)Z
    .locals 3

    .line 2476
    const/4 v0, 0x0

    :try_start_0
    const-string v1, "miui.os.Build"

    invoke-static {v1}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v1

    .line 2477
    if-eqz v1, :cond_1

    .line 2478
    invoke-static {v1, p0}, Lcom/android/camera/Util;->getStaticObjectField(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    .line 2479
    if-nez p0, :cond_0

    .line 2480
    return v0

    .line 2482
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    .line 2486
    :cond_1
    goto :goto_0

    .line 2484
    :catch_0
    move-exception p0

    .line 2485
    const-string v1, "CameraUtil"

    const-string v2, "getClass error"

    invoke-static {v1, v2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2487
    :goto_0
    return v0
.end method

.method private static isEqual([BLjava/io/File;)Z
    .locals 6

    .line 557
    const/4 v0, 0x0

    if-eqz p0, :cond_5

    array-length v1, p0

    if-nez v1, :cond_0

    goto/16 :goto_7

    .line 561
    :cond_0
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_1

    .line 562
    return v0

    .line 565
    :cond_1
    const/4 v1, 0x0

    .line 566
    const/16 v2, 0x200

    new-array v3, v2, [B

    .line 569
    :try_start_0
    const-string v4, "MD5"

    invoke-static {v4}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v4

    .line 570
    new-instance v5, Ljava/io/FileInputStream;

    invoke-direct {v5, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 571
    :goto_0
    :try_start_1
    invoke-virtual {v5, v3, v0, v2}, Ljava/io/FileInputStream;->read([BII)I

    move-result p1

    const/4 v1, -0x1

    if-eq p1, v1, :cond_2

    .line 572
    invoke-virtual {v4, v3, v0, p1}, Ljava/security/MessageDigest;->update([BII)V

    goto :goto_0

    .line 574
    :cond_2
    new-instance p1, Ljava/lang/String;

    invoke-virtual {v4}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v1

    invoke-direct {p1, v1}, Ljava/lang/String;-><init>([B)V

    .line 575
    invoke-virtual {v4}, Ljava/security/MessageDigest;->reset()V

    .line 576
    new-instance v1, Ljava/lang/String;

    invoke-virtual {v4, p0}, Ljava/security/MessageDigest;->digest([B)[B

    move-result-object p0

    invoke-direct {v1, p0}, Ljava/lang/String;-><init>([B)V

    .line 577
    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 581
    nop

    .line 583
    :try_start_2
    invoke-virtual {v5}, Ljava/io/FileInputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0

    .line 586
    goto :goto_1

    .line 584
    :catch_0
    move-exception p1

    .line 585
    const-string v0, "CameraUtil"

    invoke-virtual {p1}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 577
    :goto_1
    return p0

    .line 581
    :catchall_0
    move-exception p0

    goto :goto_5

    .line 578
    :catch_1
    move-exception p0

    move-object v1, v5

    goto :goto_2

    .line 581
    :catchall_1
    move-exception p0

    move-object v5, v1

    goto :goto_5

    .line 578
    :catch_2
    move-exception p0

    .line 579
    :goto_2
    :try_start_3
    const-string p1, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-static {p1, v2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 581
    if-eqz v1, :cond_3

    .line 583
    :try_start_4
    invoke-virtual {v1}, Ljava/io/FileInputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_3

    .line 586
    :goto_3
    goto :goto_4

    .line 584
    :catch_3
    move-exception p0

    .line 585
    const-string p1, "CameraUtil"

    invoke-virtual {p0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_3

    .line 590
    :cond_3
    :goto_4
    return v0

    .line 581
    :goto_5
    if-eqz v5, :cond_4

    .line 583
    :try_start_5
    invoke-virtual {v5}, Ljava/io/FileInputStream;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_4

    .line 586
    goto :goto_6

    .line 584
    :catch_4
    move-exception p1

    .line 585
    invoke-virtual {p1}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v0

    const-string v1, "CameraUtil"

    invoke-static {v1, v0, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 586
    :cond_4
    :goto_6
    throw p0

    .line 558
    :cond_5
    :goto_7
    return v0
.end method

.method public static isEqualsZero(D)Z
    .locals 2

    .line 3077
    invoke-static {p0, p1}, Ljava/lang/Math;->abs(D)D

    move-result-wide p0

    const-wide v0, 0x3e45798ee2308c3aL    # 1.0E-8

    cmpg-double p0, p0, v0

    if-gez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isExternalStorageMounted()Z
    .locals 2

    .line 1368
    const-string v0, "mounted"

    invoke-static {}, Landroid/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method private static isFind19_5_9LongRatioScreen(II)Z
    .locals 2

    .line 3180
    int-to-float p1, p1

    int-to-float p0, p0

    div-float/2addr p1, p0

    .line 3181
    const p0, 0x400a3d71    # 2.16f

    sub-float/2addr p1, p0

    invoke-static {p1}, Ljava/lang/Math;->abs(F)F

    move-result p0

    float-to-double p0, p0

    const-wide v0, 0x3f947ae147ae147bL    # 0.02

    cmpg-double p0, p0, v0

    if-gez p0, :cond_0

    .line 3182
    const/4 p0, 0x1

    return p0

    .line 3184
    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static isFingerPrintKeyEvent(Landroid/view/KeyEvent;)Z
    .locals 2

    .line 1842
    if-eqz p0, :cond_0

    const/16 v0, 0x1b

    invoke-virtual {p0}, Landroid/view/KeyEvent;->getKeyCode()I

    move-result v1

    if-ne v0, v1, :cond_0

    .line 1843
    invoke-virtual {p0}, Landroid/view/KeyEvent;->getDevice()Landroid/view/InputDevice;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 1844
    invoke-static {}, Lcom/mi/config/b;->hB()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {p0}, Landroid/view/KeyEvent;->getDevice()Landroid/view/InputDevice;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/InputDevice;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    .line 1842
    :goto_0
    return p0
.end method

.method public static isForceNameSuffix()Z
    .locals 2

    .line 1877
    sget-object v0, Lcom/android/camera/Util;->sIsForceNameSuffix:Ljava/lang/Boolean;

    if-nez v0, :cond_0

    .line 1878
    new-instance v0, Ljava/io/File;

    const-string v1, "force_name_suffix"

    invoke-static {v1}, Lcom/android/camera/storage/Storage;->generatePrimaryFilepath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    sput-object v0, Lcom/android/camera/Util;->sIsForceNameSuffix:Ljava/lang/Boolean;

    .line 1880
    :cond_0
    sget-object v0, Lcom/android/camera/Util;->sIsForceNameSuffix:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static isFullScreenNavBarHidden(Landroid/content/Context;)Z
    .locals 1

    .line 1954
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    const-string v0, "force_fsg_nav_bar"

    invoke-static {p0, v0}, Landroid/provider/MiuiSettings$Global;->getBoolean(Landroid/content/ContentResolver;Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public static isGlobalVersion()Z
    .locals 2

    .line 2455
    const-string v0, "ro.product.mod_device"

    const-string v1, ""

    invoke-static {v0, v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "_global"

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    return v0
.end method

.method public static isInVideoCall(Landroid/app/Activity;)Z
    .locals 1

    .line 1835
    invoke-static {}, Lcom/mi/config/b;->isMTKPlatform()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p0}, Lcom/android/camera/permission/PermissionManager;->checkPhoneStatePermission(Landroid/app/Activity;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1836
    invoke-static {p0}, Lcom/android/camera/lib/compatibility/util/CompatibilityUtils;->isInVideoCall(Landroid/content/Context;)Z

    move-result p0

    return p0

    .line 1838
    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static isInternationalBuild()Z
    .locals 2

    .line 2467
    const-string v0, "ro.product.mod_device"

    const-string v1, ""

    invoke-static {v0, v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "_global"

    invoke-virtual {v0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public static isLabOptionsVisible()Z
    .locals 2

    .line 1884
    sget-object v0, Lcom/android/camera/Util;->sIsLabOptionsVisible:Ljava/lang/Boolean;

    if-nez v0, :cond_0

    .line 1885
    new-instance v0, Ljava/io/File;

    const-string v1, "lab_options_visible"

    invoke-static {v1}, Lcom/android/camera/storage/Storage;->generatePrimaryFilepath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    sput-object v0, Lcom/android/camera/Util;->sIsLabOptionsVisible:Ljava/lang/Boolean;

    .line 1887
    :cond_0
    sget-object v0, Lcom/android/camera/Util;->sIsLabOptionsVisible:Ljava/lang/Boolean;

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    return v0
.end method

.method public static isLayoutRTL(Landroid/content/Context;)Z
    .locals 2

    .line 1330
    const/4 v0, 0x0

    if-nez p0, :cond_0

    .line 1331
    return v0

    .line 1333
    :cond_0
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object p0

    invoke-virtual {p0}, Landroid/content/res/Configuration;->getLayoutDirection()I

    move-result p0

    const/4 v1, 0x1

    if-ne p0, v1, :cond_1

    move v0, v1

    nop

    :cond_1
    return v0
.end method

.method public static isMemoryRich(Landroid/content/Context;)Z
    .locals 4

    .line 1849
    const-string v0, "activity"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/app/ActivityManager;

    .line 1850
    new-instance v0, Landroid/app/ActivityManager$MemoryInfo;

    invoke-direct {v0}, Landroid/app/ActivityManager$MemoryInfo;-><init>()V

    .line 1851
    invoke-virtual {p0, v0}, Landroid/app/ActivityManager;->getMemoryInfo(Landroid/app/ActivityManager$MemoryInfo;)V

    .line 1853
    iget-wide v0, v0, Landroid/app/ActivityManager$MemoryInfo;->availMem:J

    const-wide/32 v2, 0x19000000

    cmp-long p0, v0, v2

    if-lez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isNonUI()Z
    .locals 2

    .line 2122
    const-string v0, "sys.power.nonui"

    const/4 v1, 0x0

    invoke-static {v0, v1}, Landroid/os/SystemProperties;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public static isNonUIEnabled()Z
    .locals 2

    .line 2126
    const-string v0, "sys.power.nonui"

    invoke-static {v0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, ""

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public static isPackageAvailable(Landroid/content/Context;Ljava/lang/String;)Z
    .locals 3

    .line 1958
    const/4 v0, 0x0

    if-eqz p0, :cond_3

    if-eqz p1, :cond_3

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_2

    .line 1963
    :cond_0
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    .line 1965
    :try_start_0
    invoke-virtual {p0, p1}, Landroid/content/pm/PackageManager;->getApplicationEnabledSetting(Ljava/lang/String;)I

    move-result p0
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/NullPointerException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1966
    const/4 p1, 0x1

    if-eqz p0, :cond_2

    if-ne p0, p1, :cond_1

    goto :goto_0

    :cond_1
    move p1, v0

    nop

    :cond_2
    :goto_0
    return p1

    .line 1970
    :catch_0
    move-exception p0

    .line 1971
    const-string p1, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/NullPointerException;->getMessage()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 1968
    :catch_1
    move-exception p0

    .line 1969
    const-string p1, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/IllegalArgumentException;->getMessage()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1972
    nop

    .line 1973
    :goto_1
    return v0

    .line 1959
    :cond_3
    :goto_2
    const-string p0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "invalid params. packageName="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 1960
    return v0
.end method

.method public static isPathExist(Ljava/lang/String;)Z
    .locals 1

    .line 2093
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isProduceFocusInfoSuccess([B)Z
    .locals 2

    .line 1443
    if-eqz p0, :cond_0

    array-length v0, p0

    const/16 v1, 0x19

    if-ge v1, v0, :cond_0

    array-length v0, p0

    sub-int/2addr v0, v1

    aget-byte p0, p0, v0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isQuotaExceeded(Ljava/lang/Exception;)Z
    .locals 3

    .line 2440
    if-eqz p0, :cond_0

    instance-of v0, p0, Ljava/io/FileNotFoundException;

    if-eqz v0, :cond_0

    .line 2441
    invoke-virtual {p0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p0

    .line 2442
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "isQuotaExceeded: msg="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 2443
    if-eqz p0, :cond_0

    .line 2444
    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    const-string v0, "quota exceeded"

    invoke-virtual {p0, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    return p0

    .line 2448
    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static isScreenSlideOff(Landroid/content/Context;)Z
    .locals 2

    .line 3073
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object p0

    const-string v0, "sc_status"

    const/4 v1, -0x1

    invoke-static {p0, v0, v1}, Landroid/provider/Settings$System;->getInt(Landroid/content/ContentResolver;Ljava/lang/String;I)I

    move-result p0

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public static isSetContentDesc()Z
    .locals 2

    .line 2414
    const-string v0, "1"

    const-string v1, "camera.content.description.debug"

    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static isShowAfRegionView()Z
    .locals 2

    .line 1582
    const-string v0, "1"

    const-string v1, "camera.preview.debug.afRegion_view"

    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static isShowDebugInfo()Z
    .locals 2

    .line 1644
    const-string v0, "1"

    const-string v1, "persist.camera.enable.log"

    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "1"

    const-string v1, "persist.camera.debug.show_af"

    .line 1645
    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "1"

    const-string v1, "persist.camera.debug.show_awb"

    .line 1646
    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "1"

    const-string v1, "persist.camera.debug.show_aec"

    .line 1647
    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "1"

    const-string v1, "persist.camera.debug.autoscene"

    .line 1648
    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "1"

    const-string v1, "persist.camera.debug.hht"

    .line 1649
    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    .line 1644
    :goto_1
    return v0
.end method

.method public static isShowDebugInfoView()Z
    .locals 2

    .line 1578
    const-string v0, "1"

    const-string v1, "camera.preview.debug.debugInfo_view"

    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static isShowPreviewDebugInfo()Z
    .locals 2

    .line 1574
    const-string v0, "1"

    const-string v1, "camera.preview.enable.log"

    invoke-static {v1}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static isStringValueContained(Ljava/lang/Object;I)Z
    .locals 1

    .line 1762
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object p1

    .line 1763
    invoke-static {p0, p1}, Lcom/android/camera/Util;->isStringValueContained(Ljava/lang/Object;[Ljava/lang/CharSequence;)Z

    move-result p0

    return p0
.end method

.method public static isStringValueContained(Ljava/lang/Object;Ljava/util/List;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Ljava/util/List<",
            "+",
            "Ljava/lang/CharSequence;",
            ">;)Z"
        }
    .end annotation

    .line 1779
    const/4 v0, 0x0

    if-eqz p1, :cond_3

    if-nez p0, :cond_0

    goto :goto_1

    .line 1782
    :cond_0
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/CharSequence;

    .line 1783
    invoke-virtual {v1, p0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1784
    const/4 p0, 0x1

    return p0

    .line 1786
    :cond_1
    goto :goto_0

    .line 1787
    :cond_2
    return v0

    .line 1780
    :cond_3
    :goto_1
    return v0
.end method

.method public static isStringValueContained(Ljava/lang/Object;[Ljava/lang/CharSequence;)Z
    .locals 4

    .line 1767
    const/4 v0, 0x0

    if-eqz p1, :cond_3

    if-nez p0, :cond_0

    goto :goto_1

    .line 1770
    :cond_0
    array-length v1, p1

    move v2, v0

    :goto_0
    if-ge v2, v1, :cond_2

    aget-object v3, p1, v2

    .line 1771
    invoke-virtual {v3, p0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 1772
    const/4 p0, 0x1

    return p0

    .line 1770
    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 1775
    :cond_2
    return v0

    .line 1768
    :cond_3
    :goto_1
    return v0
.end method

.method public static isSupported(I[I)Z
    .locals 0

    .line 1326
    invoke-static {p1, p0}, Lcom/android/camera/Util;->getArrayIndex([II)I

    move-result p0

    const/4 p1, -0x1

    if-eq p0, p1, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isSupported(Ljava/lang/Object;[Ljava/lang/Object;)Z
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;[TT;)Z"
        }
    .end annotation

    .line 1322
    invoke-static {p1, p0}, Lcom/android/camera/Util;->getArrayIndex([Ljava/lang/Object;Ljava/lang/Object;)I

    move-result p0

    const/4 p1, -0x1

    if-eq p0, p1, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isSupported(Ljava/lang/String;Ljava/util/List;)Z
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)Z"
        }
    .end annotation

    .line 1318
    if-eqz p1, :cond_0

    invoke-interface {p1, p0}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result p0

    if-ltz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isTimeout(JJJ)Z
    .locals 1

    .line 1410
    cmp-long v0, p0, p2

    if-ltz v0, :cond_1

    sub-long/2addr p0, p2

    cmp-long p0, p0, p4

    if-lez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p0, 0x1

    :goto_1
    return p0
.end method

.method public static isUriValid(Landroid/net/Uri;Landroid/content/ContentResolver;)Z
    .locals 3

    .line 1079
    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 1082
    :cond_0
    :try_start_0
    const-string v1, "r"

    invoke-virtual {p1, p0, v1}, Landroid/content/ContentResolver;->openFileDescriptor(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;

    move-result-object p1

    .line 1083
    if-nez p1, :cond_1

    .line 1084
    const-string p1, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Fail to open URI. URI="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1085
    return v0

    .line 1087
    :cond_1
    invoke-virtual {p1}, Landroid/os/ParcelFileDescriptor;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1091
    nop

    .line 1092
    const/4 p0, 0x1

    return p0

    .line 1088
    :catch_0
    move-exception p0

    .line 1089
    const-string p1, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "IOException occurs when opening URI: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 1090
    return v0
.end method

.method public static isValidValue(Ljava/lang/String;)Z
    .locals 1

    .line 3069
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "^[0-9]+$"

    invoke-virtual {p0, v0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static isViewIntersectWindow(Landroid/view/View;)Z
    .locals 5

    .line 1059
    const/4 v0, 0x2

    new-array v0, v0, [I

    .line 1060
    invoke-virtual {p0, v0}, Landroid/view/View;->getLocationInWindow([I)V

    .line 1061
    const/4 v1, 0x0

    aget v2, v0, v1

    sget v3, Lcom/android/camera/Util;->sWindowWidth:I

    const/4 v4, 0x1

    if-ge v2, v3, :cond_0

    aget v2, v0, v1

    .line 1062
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v3

    add-int/2addr v2, v3

    if-ltz v2, :cond_0

    aget v2, v0, v4

    sget v3, Lcom/android/camera/Util;->sWindowHeight:I

    if-ge v2, v3, :cond_0

    aget v0, v0, v4

    .line 1064
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result p0

    add-int/2addr v0, p0

    if-ltz v0, :cond_0

    .line 1061
    move v1, v4

    goto :goto_0

    .line 1064
    :cond_0
    nop

    .line 1061
    :goto_0
    return v1
.end method

.method public static isZoomAnimationEnabled()Z
    .locals 2

    .line 2118
    const-string v0, "camera_zoom_animation"

    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v1

    invoke-virtual {v1}, Lcom/mi/config/a;->fs()Z

    move-result v1

    xor-int/lit8 v1, v1, 0x1

    invoke-static {v0, v1}, Landroid/os/SystemProperties;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public static join(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 2143
    new-instance v0, Ljava/lang/StringBuffer;

    invoke-direct {v0}, Ljava/lang/StringBuffer;-><init>()V

    .line 2144
    const/4 v1, 0x0

    :goto_0
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    if-ge v1, v2, :cond_1

    .line 2145
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-ne v1, v2, :cond_0

    .line 2146
    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    goto :goto_1

    .line 2148
    :cond_0
    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    .line 2144
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 2152
    :cond_1
    invoke-virtual {v0}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method protected static loadAppCameraWatermark(Landroid/content/Context;Landroid/graphics/BitmapFactory$Options;Ljava/lang/String;)Landroid/graphics/Bitmap;
    .locals 4

    .line 2663
    const/4 v0, 0x0

    if-nez p2, :cond_0

    .line 2664
    return-object v0

    .line 2668
    :cond_0
    const-string v1, "common"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 2669
    const-string p2, "common.png"

    goto :goto_0

    .line 2673
    :cond_1
    invoke-static {}, Lcom/mi/config/b;->getGivenName()Ljava/lang/String;

    move-result-object v1

    .line 2674
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, ".png"

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 2677
    :goto_0
    invoke-virtual {p0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    .line 2678
    :try_start_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "watermarks/"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p2}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 2679
    :try_start_1
    invoke-static {p0, v0, p1}, Landroid/graphics/BitmapFactory;->decodeStream(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p1
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 2680
    if-eqz p0, :cond_2

    :try_start_2
    invoke-static {v0, p0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    .line 2679
    :cond_2
    return-object p1

    .line 2680
    :catchall_0
    move-exception p1

    move-object p2, v0

    goto :goto_1

    .line 2678
    :catch_0
    move-exception p1

    :try_start_3
    throw p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 2680
    :catchall_1
    move-exception p2

    move-object v3, p2

    move-object p2, p1

    move-object p1, v3

    :goto_1
    if-eqz p0, :cond_3

    :try_start_4
    invoke-static {p2, p0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    :cond_3
    throw p1
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_1

    :catch_1
    move-exception p0

    .line 2681
    const-string p1, "CameraUtil"

    const-string p2, "Failed to load app camera watermark "

    invoke-static {p1, p2, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2684
    return-object v0
.end method

.method public static makeBitmap([BI)Landroid/graphics/Bitmap;
    .locals 5

    .line 475
    const/4 v0, 0x0

    :try_start_0
    new-instance v1, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v1}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 476
    const/4 v2, 0x1

    iput-boolean v2, v1, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 477
    array-length v2, p0

    const/4 v3, 0x0

    invoke-static {p0, v3, v2, v1}, Landroid/graphics/BitmapFactory;->decodeByteArray([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    .line 478
    iget-boolean v2, v1, Landroid/graphics/BitmapFactory$Options;->mCancel:Z

    if-nez v2, :cond_1

    iget v2, v1, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    const/4 v4, -0x1

    if-eq v2, v4, :cond_1

    iget v2, v1, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    if-ne v2, v4, :cond_0

    goto :goto_0

    .line 483
    :cond_0
    invoke-static {v1, v4, p1}, Lcom/android/camera/Util;->computeSampleSize(Landroid/graphics/BitmapFactory$Options;II)I

    move-result p1

    iput p1, v1, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 484
    iput-boolean v3, v1, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 485
    iput-boolean v3, v1, Landroid/graphics/BitmapFactory$Options;->inDither:Z

    .line 486
    sget-object p1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    iput-object p1, v1, Landroid/graphics/BitmapFactory$Options;->inPreferredConfig:Landroid/graphics/Bitmap$Config;

    .line 487
    array-length p1, p0

    invoke-static {p0, v3, p1, v1}, Landroid/graphics/BitmapFactory;->decodeByteArray([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 480
    :cond_1
    :goto_0
    return-object v0

    .line 488
    :catch_0
    move-exception p0

    .line 489
    const-string p1, "CameraUtil"

    const-string v1, "Got oom exception "

    invoke-static {p1, v1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 490
    return-object v0
.end method

.method public static md5(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    .line 3245
    nop

    .line 3248
    :try_start_0
    const-string v0, "MD5"

    invoke-static {v0}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v0

    .line 3249
    const-string v1, "UTF8"

    invoke-virtual {p0, v1}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/security/MessageDigest;->update([B)V

    .line 3250
    invoke-virtual {v0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object p0

    .line 3251
    const-string v0, ""

    .line 3252
    const/4 v1, 0x0

    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_0

    .line 3253
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v0, 0xff

    aget-byte v3, p0, v1

    and-int/2addr v0, v3

    or-int/lit16 v0, v0, -0x100

    invoke-static {v0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v3, 0x6

    invoke-virtual {v0, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 3252
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 3255
    :cond_0
    return-object v0

    .line 3256
    :catch_0
    move-exception p0

    .line 3257
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 3259
    const/4 p0, 0x0

    return-object p0
.end method

.method public static final millisecondToTimeString(JZ)Ljava/lang/String;
    .locals 18

    .line 3203
    const-wide/16 v2, 0x3e8

    div-long v4, p0, v2

    .line 3204
    const-wide/16 v6, 0x3c

    div-long v8, v4, v6

    .line 3205
    div-long v10, v8, v6

    .line 3206
    mul-long v12, v10, v6

    sub-long v12, v8, v12

    .line 3207
    mul-long/2addr v8, v6

    sub-long v6, v4, v8

    .line 3209
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    .line 3212
    const-wide/16 v14, 0x0

    cmp-long v9, v10, v14

    const/16 v14, 0x3a

    const/16 v15, 0x30

    const-wide/16 v16, 0xa

    if-lez v9, :cond_1

    .line 3213
    cmp-long v9, v10, v16

    if-gez v9, :cond_0

    .line 3214
    invoke-virtual {v8, v15}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3216
    :cond_0
    invoke-virtual {v8, v10, v11}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v14}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3220
    :cond_1
    cmp-long v9, v12, v16

    if-gez v9, :cond_2

    .line 3221
    invoke-virtual {v8, v15}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3223
    :cond_2
    invoke-virtual {v8, v12, v13}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v14}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3226
    cmp-long v9, v6, v16

    if-gez v9, :cond_3

    .line 3227
    invoke-virtual {v8, v15}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3229
    :cond_3
    invoke-virtual {v8, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 3232
    if-eqz p2, :cond_5

    .line 3233
    const/16 v6, 0x2e

    invoke-virtual {v8, v6}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3234
    mul-long/2addr v4, v2

    sub-long v0, p0, v4

    div-long v0, v0, v16

    .line 3235
    cmp-long v2, v0, v16

    if-gez v2, :cond_4

    .line 3236
    invoke-virtual {v8, v15}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 3238
    :cond_4
    invoke-virtual {v8, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 3241
    :cond_5
    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static mkdirs(Ljava/io/File;III)Z
    .locals 2

    .line 1378
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1379
    const/4 p0, 0x0

    return p0

    .line 1383
    :cond_0
    invoke-virtual {p0}, Ljava/io/File;->getParent()Ljava/lang/String;

    move-result-object v0

    .line 1384
    if-eqz v0, :cond_1

    .line 1385
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v1, p1, p2, p3}, Lcom/android/camera/Util;->mkdirs(Ljava/io/File;III)Z

    .line 1388
    :cond_1
    invoke-virtual {p0}, Ljava/io/File;->mkdir()Z

    move-result p0

    return p0
.end method

.method public static nextPowerOf2(I)I
    .locals 1

    .line 697
    add-int/lit8 p0, p0, -0x1

    .line 698
    ushr-int/lit8 v0, p0, 0x10

    or-int/2addr p0, v0

    .line 699
    ushr-int/lit8 v0, p0, 0x8

    or-int/2addr p0, v0

    .line 700
    ushr-int/lit8 v0, p0, 0x4

    or-int/2addr p0, v0

    .line 701
    ushr-int/lit8 v0, p0, 0x2

    or-int/2addr p0, v0

    .line 702
    ushr-int/lit8 v0, p0, 0x1

    or-int/2addr p0, v0

    .line 703
    add-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public static parseInt(Ljava/lang/String;I)I
    .locals 2

    .line 2157
    :try_start_0
    invoke-static {p0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    .line 2158
    :catch_0
    move-exception p0

    .line 2159
    const-string v0, "CameraUtil"

    invoke-virtual {p0}, Ljava/lang/NumberFormatException;->getMessage()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2160
    invoke-static {}, Lcom/android/camera/Util;->isDebugOsBuild()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2165
    return p1

    .line 2161
    :cond_0
    throw p0
.end method

.method public static pointInView(FFLandroid/view/View;)Z
    .locals 5

    .line 1051
    const/4 v0, 0x0

    if-nez p2, :cond_0

    return v0

    .line 1052
    :cond_0
    const/4 v1, 0x2

    new-array v1, v1, [I

    .line 1053
    invoke-virtual {p2, v1}, Landroid/view/View;->getLocationInWindow([I)V

    .line 1054
    aget v2, v1, v0

    int-to-float v2, v2

    cmpl-float v2, p0, v2

    const/4 v3, 0x1

    if-ltz v2, :cond_1

    aget v2, v1, v0

    invoke-virtual {p2}, Landroid/view/View;->getWidth()I

    move-result v4

    add-int/2addr v2, v4

    int-to-float v2, v2

    cmpg-float p0, p0, v2

    if-gez p0, :cond_1

    aget p0, v1, v3

    int-to-float p0, p0

    cmpl-float p0, p1, p0

    if-ltz p0, :cond_1

    aget p0, v1, v3

    .line 1055
    invoke-virtual {p2}, Landroid/view/View;->getHeight()I

    move-result p2

    add-int/2addr p0, p2

    int-to-float p0, p0

    cmpg-float p0, p1, p0

    if-gez p0, :cond_1

    .line 1054
    move v0, v3

    goto :goto_0

    .line 1055
    :cond_1
    nop

    .line 1054
    :goto_0
    return v0
.end method

.method public static prepareMatrix(Landroid/graphics/Matrix;ZIIIIIII)V
    .locals 1

    .line 1141
    const/high16 v0, 0x3f800000    # 1.0f

    if-eqz p1, :cond_0

    const/high16 p1, -0x40800000    # -1.0f

    goto :goto_0

    :cond_0
    move p1, v0

    :goto_0
    invoke-virtual {p0, p1, v0}, Landroid/graphics/Matrix;->setScale(FF)V

    .line 1143
    int-to-float p1, p2

    invoke-virtual {p0, p1}, Landroid/graphics/Matrix;->postRotate(F)Z

    .line 1144
    const/16 p1, 0x5a

    if-eq p2, p1, :cond_2

    const/16 p1, 0x10e

    if-ne p2, p1, :cond_1

    goto :goto_1

    .line 1147
    :cond_1
    int-to-float p1, p3

    int-to-float p2, p7

    div-float/2addr p1, p2

    int-to-float p2, p4

    int-to-float p3, p8

    div-float/2addr p2, p3

    invoke-virtual {p0, p1, p2}, Landroid/graphics/Matrix;->postScale(FF)Z

    goto :goto_2

    .line 1145
    :cond_2
    :goto_1
    int-to-float p1, p3

    int-to-float p2, p8

    div-float/2addr p1, p2

    int-to-float p2, p4

    int-to-float p3, p7

    div-float/2addr p2, p3

    invoke-virtual {p0, p1, p2}, Landroid/graphics/Matrix;->postScale(FF)Z

    .line 1149
    :goto_2
    int-to-float p1, p5

    int-to-float p2, p6

    invoke-virtual {p0, p1, p2}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    .line 1150
    return-void
.end method

.method public static varargs printLog(Ljava/lang/String;[Ljava/lang/Object;)V
    .locals 3

    .line 812
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 813
    const/4 v1, 0x0

    :goto_0
    array-length v2, p1

    if-ge v1, v2, :cond_0

    .line 814
    aget-object v2, p1, v1

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 815
    const-string v2, " = "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 816
    add-int/lit8 v2, v1, 0x1

    aget-object v2, p1, v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 817
    const-string v2, " "

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 813
    add-int/lit8 v1, v1, 0x2

    goto :goto_0

    .line 819
    :cond_0
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 820
    return-void
.end method

.method public static rectFToRect(Landroid/graphics/RectF;Landroid/graphics/Rect;)V
    .locals 1

    .line 1118
    iget v0, p0, Landroid/graphics/RectF;->left:F

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->left:I

    .line 1119
    iget v0, p0, Landroid/graphics/RectF;->top:F

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->top:I

    .line 1120
    iget v0, p0, Landroid/graphics/RectF;->right:F

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    iput v0, p1, Landroid/graphics/Rect;->right:I

    .line 1121
    iget p0, p0, Landroid/graphics/RectF;->bottom:F

    invoke-static {p0}, Ljava/lang/Math;->round(F)I

    move-result p0

    iput p0, p1, Landroid/graphics/Rect;->bottom:I

    .line 1122
    return-void
.end method

.method public static replaceStartEffectRender(Landroid/app/Activity;)I
    .locals 4

    .line 1034
    invoke-static {}, Lcom/mi/config/b;->gi()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1035
    nop

    .line 1036
    invoke-virtual {p0}, Landroid/app/Activity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "android.intent.extras.START_WITH_EFFECT_RENDER"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1037
    if-eqz v0, :cond_0

    .line 1038
    invoke-virtual {p0}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const-string v2, "integer"

    .line 1039
    invoke-virtual {p0}, Landroid/app/Activity;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v0, v2, v3}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 1040
    if-eqz v0, :cond_0

    .line 1041
    invoke-virtual {p0}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, v0}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p0

    .line 1042
    invoke-static {p0}, Lcom/android/camera/CameraSettings;->setShaderEffect(I)V

    .line 1043
    return p0

    .line 1047
    :cond_0
    sget p0, Lcom/android/camera/effect/FilterInfo;->FILTER_ID_NONE:I

    return p0
.end method

.method public static reverseAnimatorSet(Landroid/animation/AnimatorSet;)V
    .locals 2

    .line 2059
    invoke-virtual {p0}, Landroid/animation/AnimatorSet;->getChildAnimations()Ljava/util/ArrayList;

    move-result-object p0

    .line 2060
    invoke-virtual {p0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/animation/Animator;

    .line 2061
    instance-of v1, v0, Landroid/animation/ValueAnimator;

    if-eqz v1, :cond_0

    .line 2062
    check-cast v0, Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->reverse()V

    goto :goto_1

    .line 2063
    :cond_0
    instance-of v1, v0, Landroid/animation/AnimatorSet;

    if-eqz v1, :cond_1

    .line 2064
    check-cast v0, Landroid/animation/AnimatorSet;

    invoke-static {v0}, Lcom/android/camera/Util;->reverseAnimatorSet(Landroid/animation/AnimatorSet;)V

    .line 2066
    :cond_1
    :goto_1
    goto :goto_0

    .line 2067
    :cond_2
    return-void
.end method

.method public static rotate(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
    .locals 1

    .line 372
    const/4 v0, 0x0

    invoke-static {p0, p1, v0}, Lcom/android/camera/Util;->rotateAndMirror(Landroid/graphics/Bitmap;IZ)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method public static rotateAndMirror(Landroid/graphics/Bitmap;IZ)Landroid/graphics/Bitmap;
    .locals 7

    .line 378
    if-nez p1, :cond_0

    if-eqz p2, :cond_8

    :cond_0
    if-eqz p0, :cond_8

    .line 379
    new-instance v5, Landroid/graphics/Matrix;

    invoke-direct {v5}, Landroid/graphics/Matrix;-><init>()V

    .line 382
    if-eqz p2, :cond_5

    .line 383
    const/high16 p2, -0x40800000    # -1.0f

    const/high16 v0, 0x3f800000    # 1.0f

    invoke-virtual {v5, p2, v0}, Landroid/graphics/Matrix;->postScale(FF)Z

    .line 384
    add-int/lit16 p1, p1, 0x168

    rem-int/lit16 p1, p1, 0x168

    .line 385
    const/4 p2, 0x0

    if-eqz p1, :cond_4

    const/16 v0, 0xb4

    if-ne p1, v0, :cond_1

    goto :goto_1

    .line 387
    :cond_1
    const/16 v0, 0x5a

    if-eq p1, v0, :cond_3

    const/16 v0, 0x10e

    if-ne p1, v0, :cond_2

    goto :goto_0

    .line 390
    :cond_2
    new-instance p0, Ljava/lang/IllegalArgumentException;

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "Invalid degrees="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 388
    :cond_3
    :goto_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {v5, v0, p2}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    goto :goto_2

    .line 386
    :cond_4
    :goto_1
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {v5, v0, p2}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    .line 393
    :cond_5
    :goto_2
    if-eqz p1, :cond_6

    .line 395
    int-to-float p1, p1

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result p2

    int-to-float p2, p2

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p2, v0

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v1, v0

    invoke-virtual {v5, p1, p2, v1}, Landroid/graphics/Matrix;->postRotate(FFF)Z

    .line 399
    :cond_6
    const/4 v1, 0x0

    const/4 v2, 0x0

    :try_start_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    const/4 v6, 0x1

    move-object v0, p0

    invoke-static/range {v0 .. v6}, Landroid/graphics/Bitmap;->createBitmap(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;

    move-result-object p1

    .line 400
    if-eq p0, p1, :cond_7

    .line 401
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->recycle()V
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    .line 402
    nop

    .line 406
    move-object p0, p1

    :cond_7
    goto :goto_3

    .line 404
    :catch_0
    move-exception p1

    .line 408
    :cond_8
    :goto_3
    return-object p0
.end method

.method public static roundOrientation(II)I
    .locals 3

    .line 824
    const/4 v0, 0x1

    const/4 v1, -0x1

    if-ne p1, v1, :cond_0

    .line 825
    goto :goto_0

    .line 827
    :cond_0
    sub-int v1, p0, p1

    invoke-static {v1}, Ljava/lang/Math;->abs(I)I

    move-result v1

    .line 828
    rsub-int v2, v1, 0x168

    invoke-static {v1, v2}, Ljava/lang/Math;->min(II)I

    move-result v1

    .line 829
    const/16 v2, 0x32

    if-lt v1, v2, :cond_1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 831
    :goto_0
    if-eqz v0, :cond_2

    .line 832
    add-int/lit8 p0, p0, 0x2d

    div-int/lit8 p0, p0, 0x5a

    mul-int/lit8 p0, p0, 0x5a

    rem-int/lit16 p0, p0, 0x168

    .line 833
    const-string p1, "CameraUtil"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "onOrientationChanged: orientation = "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 834
    return p0

    .line 836
    :cond_2
    return p1
.end method

.method public static safeDelete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 4

    .line 1560
    nop

    .line 1562
    const/4 v0, -0x1

    :try_start_0
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    .line 1563
    invoke-virtual {v1, p0, p1, p2}, Landroid/content/ContentResolver;->delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 1564
    :try_start_1
    const-string v0, "CameraUtil"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "safeDelete url="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, " where="

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " selectionArgs="

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, " result="

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 1569
    goto :goto_1

    .line 1567
    :catch_0
    move-exception p0

    goto :goto_0

    :catch_1
    move-exception p0

    move v1, v0

    .line 1568
    :goto_0
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 1570
    :goto_1
    return v1
.end method

.method public static saveBitmap(Ljava/nio/Buffer;IILandroid/graphics/Bitmap$Config;Ljava/lang/String;)Z
    .locals 1

    .line 495
    nop

    .line 496
    if-eqz p0, :cond_2

    .line 497
    invoke-static {p1, p2, p3}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p1

    .line 498
    invoke-virtual {p1, p0}, Landroid/graphics/Bitmap;->copyPixelsFromBuffer(Ljava/nio/Buffer;)V

    .line 500
    const/4 p0, 0x0

    .line 502
    :try_start_0
    new-instance p2, Ljava/io/FileOutputStream;

    new-instance p3, Ljava/io/File;

    invoke-direct {p3, p4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-direct {p2, p3}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 503
    :try_start_1
    sget-object p0, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    const/16 p3, 0x64

    invoke-virtual {p1, p0, p3, p2}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    :try_end_1
    .catch Ljava/io/FileNotFoundException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 504
    const/4 p0, 0x1

    .line 508
    nop

    .line 510
    :try_start_2
    invoke-virtual {p2}, Ljava/io/FileOutputStream;->flush()V

    .line 511
    invoke-virtual {p2}, Ljava/io/FileOutputStream;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 514
    goto :goto_0

    .line 512
    :catch_0
    move-exception p2

    .line 513
    invoke-virtual {p2}, Ljava/lang/Exception;->printStackTrace()V

    .line 516
    :goto_0
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->recycle()V

    .line 517
    goto :goto_6

    .line 508
    :catchall_0
    move-exception p0

    move-object v0, p2

    move-object p2, p0

    move-object p0, v0

    goto :goto_3

    .line 505
    :catch_1
    move-exception p0

    move-object v0, p2

    move-object p2, p0

    move-object p0, v0

    goto :goto_1

    .line 508
    :catchall_1
    move-exception p2

    goto :goto_3

    .line 505
    :catch_2
    move-exception p2

    .line 506
    :goto_1
    :try_start_3
    const-string p3, "CameraUtil"

    const-string p4, "saveBitmap failed!"

    invoke-static {p3, p4, p2}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 508
    if-eqz p0, :cond_0

    .line 510
    :try_start_4
    invoke-virtual {p0}, Ljava/io/FileOutputStream;->flush()V

    .line 511
    invoke-virtual {p0}, Ljava/io/FileOutputStream;->close()V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_3

    .line 514
    goto :goto_2

    .line 512
    :catch_3
    move-exception p0

    .line 513
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 516
    :cond_0
    :goto_2
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->recycle()V

    .line 517
    goto :goto_5

    .line 508
    :goto_3
    if-eqz p0, :cond_1

    .line 510
    :try_start_5
    invoke-virtual {p0}, Ljava/io/FileOutputStream;->flush()V

    .line 511
    invoke-virtual {p0}, Ljava/io/FileOutputStream;->close()V
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_4

    .line 514
    goto :goto_4

    .line 512
    :catch_4
    move-exception p0

    .line 513
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 516
    :cond_1
    :goto_4
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->recycle()V

    throw p2

    .line 519
    :cond_2
    :goto_5
    const/4 p0, 0x0

    :goto_6
    return p0
.end method

.method public static saveCameraCalibrationToFile([BLjava/lang/String;)Z
    .locals 4

    .line 523
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v0

    .line 524
    nop

    .line 525
    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz p0, :cond_2

    if-eqz v0, :cond_2

    .line 526
    invoke-virtual {v0, p1}, Landroid/content/Context;->getFileStreamPath(Ljava/lang/String;)Ljava/io/File;

    move-result-object v3

    .line 527
    invoke-static {p0, v3}, Lcom/android/camera/Util;->isEqual([BLjava/io/File;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 529
    return v1

    .line 532
    :cond_0
    const/4 v3, 0x0

    .line 534
    :try_start_0
    invoke-virtual {v0, p1, v2}, Landroid/content/Context;->openFileOutput(Ljava/lang/String;I)Ljava/io/FileOutputStream;

    move-result-object p1
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_4
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_3
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 535
    :try_start_1
    invoke-virtual {p1, p0}, Ljava/io/FileOutputStream;->write([B)V
    :try_end_1
    .catch Ljava/io/FileNotFoundException; {:try_start_1 .. :try_end_1} :catch_2
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 536
    nop

    .line 542
    if-eqz p1, :cond_3

    .line 544
    :try_start_2
    invoke-virtual {p1}, Ljava/io/FileOutputStream;->flush()V

    .line 545
    invoke-virtual {p1}, Ljava/io/FileOutputStream;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_0

    .line 546
    :catch_0
    move-exception p0

    .line 547
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 548
    :goto_0
    goto :goto_7

    .line 542
    :catchall_0
    move-exception p0

    move-object v3, p1

    goto :goto_4

    .line 539
    :catch_1
    move-exception p0

    move-object v3, p1

    goto :goto_1

    .line 537
    :catch_2
    move-exception p0

    move-object v3, p1

    goto :goto_2

    .line 542
    :catchall_1
    move-exception p0

    goto :goto_4

    .line 539
    :catch_3
    move-exception p0

    .line 540
    :goto_1
    :try_start_3
    const-string p1, "CameraUtil"

    const-string v0, "saveCameraCalibrationToFile: IOException"

    invoke-static {p1, v0, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 542
    if-eqz v3, :cond_2

    .line 544
    :try_start_4
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->flush()V

    .line 545
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->close()V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_5

    goto :goto_3

    .line 537
    :catch_4
    move-exception p0

    .line 538
    :goto_2
    :try_start_5
    const-string p1, "CameraUtil"

    const-string v0, "saveCameraCalibrationToFile: FileNotFoundException"

    invoke-static {p1, v0, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 542
    if-eqz v3, :cond_2

    .line 544
    :try_start_6
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->flush()V

    .line 545
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->close()V
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_5

    goto :goto_3

    .line 546
    :catch_5
    move-exception p0

    .line 547
    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    .line 548
    :goto_3
    goto :goto_6

    .line 542
    :goto_4
    if-eqz v3, :cond_1

    .line 544
    :try_start_7
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->flush()V

    .line 545
    invoke-virtual {v3}, Ljava/io/FileOutputStream;->close()V
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_6

    .line 548
    goto :goto_5

    .line 546
    :catch_6
    move-exception p1

    .line 547
    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    .line 548
    :cond_1
    :goto_5
    throw p0

    .line 553
    :cond_2
    :goto_6
    move v1, v2

    :cond_3
    :goto_7
    return v1
.end method

.method protected static saveCustomWatermark2File(Landroid/graphics/Bitmap;Z)V
    .locals 7

    .line 2688
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "saveLastFrameGaussian2File: start... watermarkBitmap = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2689
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 2690
    nop

    .line 2691
    if-eqz p1, :cond_0

    sget-object p1, Lcom/android/camera/Util;->WATERMARK_48M_FILE_NAME:Ljava/lang/String;

    goto :goto_0

    :cond_0
    sget-object p1, Lcom/android/camera/Util;->WATERMARK_FILE_NAME:Ljava/lang/String;

    .line 2692
    :goto_0
    if-eqz p0, :cond_1

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->isRecycled()Z

    move-result v2

    if-nez v2, :cond_1

    .line 2693
    const/4 v2, 0x0

    .line 2695
    :try_start_0
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v3

    .line 2696
    new-instance v4, Ljava/io/File;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, ".tmp"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v4, v3, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 2697
    new-instance v5, Ljava/io/FileOutputStream;

    invoke-direct {v5, v4}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 2698
    :try_start_1
    sget-object v2, Landroid/graphics/Bitmap$CompressFormat;->PNG:Landroid/graphics/Bitmap$CompressFormat;

    const/16 v6, 0x5a

    invoke-virtual {p0, v2, v6, v5}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    .line 2699
    invoke-virtual {v5}, Ljava/io/FileOutputStream;->flush()V

    .line 2700
    new-instance v2, Ljava/io/File;

    invoke-direct {v2, v3, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v4, v2}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result p1
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 2704
    invoke-static {v5}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 2705
    goto :goto_4

    .line 2704
    :catchall_0
    move-exception p0

    move-object v2, v5

    goto :goto_2

    .line 2701
    :catch_0
    move-exception p1

    move-object v2, v5

    goto :goto_1

    .line 2704
    :catchall_1
    move-exception p0

    goto :goto_2

    .line 2701
    :catch_1
    move-exception p1

    .line 2702
    :goto_1
    :try_start_2
    const-string v3, "CameraUtil"

    const-string v4, "saveCustomWatermark2File Failed to write image"

    invoke-static {v3, v4, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 2704
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 2705
    goto :goto_3

    .line 2704
    :goto_2
    invoke-static {v2}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    throw p0

    .line 2707
    :cond_1
    :goto_3
    const/4 p1, 0x0

    :goto_4
    const-string v2, "CameraUtil"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "saveCustomWatermark2File: watermarkBitmap = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ", save result = "

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p0, ", cost time = "

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2709
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0

    sub-long/2addr p0, v0

    invoke-virtual {v3, p0, p1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, "ms"

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 2707
    invoke-static {v2, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2710
    return-void
.end method

.method public static saveImageToJpeg(Landroid/media/Image;)V
    .locals 11

    .line 3035
    invoke-virtual {p0}, Landroid/media/Image;->getPlanes()[Landroid/media/Image$Plane;

    move-result-object v0

    .line 3036
    const/4 v1, 0x0

    aget-object v2, v0, v1

    invoke-virtual {v2}, Landroid/media/Image$Plane;->getBuffer()Ljava/nio/ByteBuffer;

    move-result-object v2

    .line 3037
    const/4 v3, 0x2

    aget-object v4, v0, v3

    invoke-virtual {v4}, Landroid/media/Image$Plane;->getBuffer()Ljava/nio/ByteBuffer;

    move-result-object v4

    .line 3038
    new-array v8, v3, [I

    aget-object v5, v0, v1

    invoke-virtual {v5}, Landroid/media/Image$Plane;->getRowStride()I

    move-result v5

    aput v5, v8, v1

    aget-object v0, v0, v3

    invoke-virtual {v0}, Landroid/media/Image$Plane;->getRowStride()I

    move-result v0

    const/4 v3, 0x1

    aput v0, v8, v3

    .line 3039
    invoke-virtual {v2}, Ljava/nio/ByteBuffer;->limit()I

    move-result v0

    .line 3040
    invoke-virtual {v4}, Ljava/nio/ByteBuffer;->limit()I

    move-result v3

    .line 3041
    add-int v5, v0, v3

    new-array v5, v5, [B

    .line 3042
    invoke-virtual {v2, v5, v1, v0}, Ljava/nio/ByteBuffer;->get([BII)Ljava/nio/ByteBuffer;

    .line 3043
    invoke-virtual {v4, v5, v0, v3}, Ljava/nio/ByteBuffer;->get([BII)Ljava/nio/ByteBuffer;

    .line 3044
    invoke-virtual {p0}, Landroid/media/Image;->getWidth()I

    move-result v6

    invoke-virtual {p0}, Landroid/media/Image;->getHeight()I

    move-result v7

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v9

    invoke-static/range {v5 .. v10}, Lcom/android/camera/Util;->saveYuvToJpg([BII[IJ)V

    .line 3045
    const-string p0, "CameraUtil"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "saveImageToJpeg: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/nio/ByteBuffer;->remaining()I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/nio/ByteBuffer;->remaining()I

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3046
    return-void
.end method

.method public static saveLastFrameGaussian2File(Landroid/graphics/Bitmap;)V
    .locals 7

    .line 2713
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "saveLastFrameGaussian2File: start... blurBitmap = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2714
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 2715
    nop

    .line 2716
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->isRecycled()Z

    move-result v2

    if-nez v2, :cond_0

    .line 2717
    const/4 v2, 0x0

    .line 2719
    :try_start_0
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-virtual {v3}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v3

    .line 2720
    new-instance v4, Ljava/io/File;

    const-string v5, "blur.jpg.tmp"

    invoke-direct {v4, v3, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 2721
    new-instance v5, Ljava/io/FileOutputStream;

    invoke-direct {v5, v4}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 2722
    :try_start_1
    sget-object v2, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    const/16 v6, 0x5a

    invoke-virtual {p0, v2, v6, v5}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    .line 2723
    invoke-virtual {v5}, Ljava/io/FileOutputStream;->flush()V

    .line 2724
    new-instance v2, Ljava/io/File;

    const-string v6, "blur.jpg"

    invoke-direct {v2, v3, v6}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v4, v2}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result v2
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 2728
    invoke-static {v5}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 2729
    goto :goto_3

    .line 2725
    :catch_0
    move-exception v2

    goto :goto_0

    .line 2728
    :catchall_0
    move-exception p0

    move-object v5, v2

    goto :goto_1

    .line 2725
    :catch_1
    move-exception v3

    move-object v5, v2

    move-object v2, v3

    .line 2726
    :goto_0
    :try_start_2
    const-string v3, "CameraUtil"

    const-string v4, "saveLastFrameGaussian2File Failed to write image"

    invoke-static {v3, v4, v2}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 2728
    invoke-static {v5}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    .line 2729
    goto :goto_2

    .line 2728
    :catchall_1
    move-exception p0

    :goto_1
    invoke-static {v5}, Lcom/android/camera/Util;->closeSilently(Ljava/io/Closeable;)V

    throw p0

    .line 2731
    :cond_0
    :goto_2
    const/4 v2, 0x0

    :goto_3
    const-string v3, "CameraUtil"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "saveLastFrameGaussian2File: blurBitmap = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ", save result = "

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p0, ", cost time = "

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 2733
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v5

    sub-long/2addr v5, v0

    invoke-virtual {v4, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, "ms"

    invoke-virtual {v4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 2731
    invoke-static {v3, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 2734
    return-void
.end method

.method public static saveLiveShotMicroVideoInSdcard()Z
    .locals 2

    .line 3093
    const-string v0, "liveshotsmv"

    const/4 v1, 0x3

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    return v0
.end method

.method public static saveYuv([BJ)V
    .locals 3

    .line 2997
    nop

    .line 2999
    const/4 v0, 0x0

    :try_start_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "sdcard/DCIM/Camera/dump_"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ".yuv"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 3000
    new-instance p2, Ljava/io/FileOutputStream;

    invoke-direct {p2, p1}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 3001
    :try_start_1
    invoke-virtual {p2, p0}, Ljava/io/FileOutputStream;->write([B)V

    .line 3002
    const-string p0, "CameraUtil"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "saveYuv: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 3007
    nop

    .line 3008
    :try_start_2
    invoke-virtual {p2}, Ljava/io/FileOutputStream;->flush()V

    .line 3009
    invoke-virtual {p2}, Ljava/io/FileOutputStream;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    goto :goto_1

    .line 3006
    :catchall_0
    move-exception p0

    move-object v0, p2

    goto :goto_3

    .line 3003
    :catch_0
    move-exception p0

    move-object v0, p2

    goto :goto_0

    .line 3006
    :catchall_1
    move-exception p0

    goto :goto_3

    .line 3003
    :catch_1
    move-exception p0

    .line 3004
    :goto_0
    :try_start_3
    const-string p1, "CameraUtil"

    const-string p2, "Failed to write image"

    invoke-static {p1, p2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 3007
    if-eqz v0, :cond_0

    .line 3008
    :try_start_4
    invoke-virtual {v0}, Ljava/io/FileOutputStream;->flush()V

    .line 3009
    invoke-virtual {v0}, Ljava/io/FileOutputStream;->close()V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_2

    goto :goto_1

    .line 3011
    :catch_2
    move-exception p0

    .line 3012
    const-string p1, "CameraUtil"

    const-string p2, "Failed to flush/close stream"

    invoke-static {p1, p2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 3014
    goto :goto_2

    .line 3013
    :cond_0
    :goto_1
    nop

    .line 3015
    :goto_2
    return-void

    .line 3006
    :goto_3
    nop

    .line 3007
    if-eqz v0, :cond_1

    .line 3008
    :try_start_5
    invoke-virtual {v0}, Ljava/io/FileOutputStream;->flush()V

    .line 3009
    invoke-virtual {v0}, Ljava/io/FileOutputStream;->close()V
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_3

    goto :goto_4

    .line 3011
    :catch_3
    move-exception p1

    .line 3012
    const-string p2, "CameraUtil"

    const-string v0, "Failed to flush/close stream"

    invoke-static {p2, v0, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    nop

    .line 3013
    :cond_1
    :goto_4
    throw p0
.end method

.method public static saveYuvToJpg([BII[IJ)V
    .locals 7

    .line 3018
    if-nez p0, :cond_0

    .line 3019
    const-string p0, "CameraUtil"

    const-string p1, "saveYuvToJpg: null data"

    invoke-static {p0, p1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 3020
    return-void

    .line 3023
    :cond_0
    new-instance v6, Landroid/graphics/YuvImage;

    const/16 v2, 0x11

    move-object v0, v6

    move-object v1, p0

    move v3, p1

    move v4, p2

    move-object v5, p3

    invoke-direct/range {v0 .. v5}, Landroid/graphics/YuvImage;-><init>([BIII[I)V

    .line 3024
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "sdcard/DCIM/Camera/dump_"

    invoke-virtual {p0, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p4, p5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p3, ".jpg"

    invoke-virtual {p0, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 3025
    const-string p3, "CameraUtil"

    new-instance p4, Ljava/lang/StringBuilder;

    invoke-direct {p4}, Ljava/lang/StringBuilder;-><init>()V

    const-string p5, "saveYuvToJpg: "

    invoke-virtual {p4, p5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p4

    invoke-static {p3, p4}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    .line 3027
    :try_start_0
    new-instance p3, Landroid/graphics/Rect;

    const/4 p4, 0x0

    invoke-direct {p3, p4, p4, p1, p2}, Landroid/graphics/Rect;-><init>(IIII)V

    const/16 p1, 0x64

    new-instance p2, Ljava/io/FileOutputStream;

    invoke-direct {p2, p0}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, p3, p1, p2}, Landroid/graphics/YuvImage;->compressToJpeg(Landroid/graphics/Rect;ILjava/io/OutputStream;)Z
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 3031
    goto :goto_0

    .line 3029
    :catch_0
    move-exception p0

    .line 3030
    const-string p1, "CameraUtil"

    invoke-virtual {p0}, Ljava/io/FileNotFoundException;->getMessage()Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 3032
    :goto_0
    return-void
.end method

.method public static scaleCamera2Matrix(Landroid/graphics/Matrix;Landroid/graphics/Rect;F)V
    .locals 1

    .line 1153
    invoke-virtual {p0, p2, p2}, Landroid/graphics/Matrix;->postScale(FF)Z

    .line 1154
    invoke-virtual {p1}, Landroid/graphics/Rect;->width()I

    move-result p2

    neg-int p2, p2

    int-to-float p2, p2

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p2, v0

    invoke-virtual {p1}, Landroid/graphics/Rect;->height()I

    move-result p1

    neg-int p1, p1

    int-to-float p1, p1

    div-float/2addr p1, v0

    invoke-virtual {p0, p2, p1}, Landroid/graphics/Matrix;->preTranslate(FF)Z

    .line 1155
    return-void
.end method

.method public static setAccessibilityFocusable(Landroid/view/View;Z)V
    .locals 2

    .line 2430
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x10

    if-lt v0, v1, :cond_1

    .line 2431
    if-eqz p1, :cond_0

    .line 2432
    const/4 p1, 0x1

    invoke-static {p0, p1}, Landroid/support/v4/view/ViewCompat;->setImportantForAccessibility(Landroid/view/View;I)V

    goto :goto_0

    .line 2434
    :cond_0
    const/4 p1, 0x2

    invoke-static {p0, p1}, Landroid/support/v4/view/ViewCompat;->setImportantForAccessibility(Landroid/view/View;I)V

    .line 2437
    :cond_1
    :goto_0
    return-void
.end method

.method public static setBrightnessRampRate(I)V
    .locals 1

    .line 2169
    const-string v0, "power"

    invoke-static {v0}, Landroid/os/ServiceManager;->getService(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object v0

    invoke-static {v0}, Landroid/os/IPowerManager$Stub;->asInterface(Landroid/os/IBinder;)Landroid/os/IPowerManager;

    move-result-object v0

    .line 2170
    invoke-static {v0, p0}, Lcom/android/camera/lib/compatibility/util/CompatibilityUtils;->setBrightnessRampRate(Landroid/os/IPowerManager;I)V

    .line 2171
    return-void
.end method

.method public static setPixels([BII[B[I)V
    .locals 5

    .line 3589
    if-eqz p0, :cond_2

    if-nez p3, :cond_0

    goto :goto_1

    .line 3592
    :cond_0
    const/4 v0, 0x1

    aget v0, p4, v0

    mul-int/2addr v0, p1

    const/4 v1, 0x0

    aget v2, p4, v1

    add-int/2addr v0, v2

    mul-int/2addr v0, p2

    .line 3593
    nop

    .line 3594
    move v2, v0

    move v0, v1

    :goto_0
    const/4 v3, 0x3

    aget v3, p4, v3

    if-ge v1, v3, :cond_1

    .line 3595
    const/4 v3, 0x2

    aget v4, p4, v3

    mul-int/2addr v4, p2

    invoke-static {p3, v0, p0, v2, v4}, Ljava/lang/System;->arraycopy([BI[BII)V

    .line 3596
    aget v3, p4, v3

    mul-int/2addr v3, p2

    add-int/2addr v0, v3

    .line 3597
    mul-int v3, p1, p2

    add-int/2addr v2, v3

    .line 3594
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 3599
    :cond_1
    return-void

    .line 3590
    :cond_2
    :goto_1
    return-void
.end method

.method public static setScreenEffect(Z)V
    .locals 2

    .line 2174
    invoke-static {}, Lcom/mi/config/b;->hR()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2183
    :try_start_0
    invoke-static {}, Lmiui/hardware/display/DisplayFeatureManager;->getInstance()Lmiui/hardware/display/DisplayFeatureManager;

    move-result-object v0

    const/16 v1, 0xe

    invoke-virtual {v0, v1, p0}, Lmiui/hardware/display/DisplayFeatureManager;->setScreenEffect(II)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 2186
    goto :goto_0

    .line 2184
    :catch_0
    move-exception p0

    .line 2185
    const-string v0, "CameraUtil"

    const-string v1, "Meet Exception when calling DisplayFeatureManager#setScreenEffect()"

    invoke-static {v0, v1, p0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 2188
    :cond_0
    :goto_0
    return-void
.end method

.method public static showErrorAndFinish(Landroid/app/Activity;I)V
    .locals 10

    .line 621
    new-instance v0, Lcom/android/camera/Util$1;

    invoke-direct {v0, p0}, Lcom/android/camera/Util$1;-><init>(Landroid/app/Activity;)V

    .line 629
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-direct {v1, p0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 630
    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/app/AlertDialog$Builder;->setCancelable(Z)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    .line 631
    const v3, 0x1010355

    invoke-virtual {v1, v3}, Landroid/app/AlertDialog$Builder;->setIconAttribute(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    .line 632
    const v3, 0x7f090005

    invoke-virtual {v1, v3}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    .line 633
    invoke-virtual {v1, p1}, Landroid/app/AlertDialog$Builder;->setMessage(I)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    .line 634
    const v3, 0x7f0900c4

    invoke-virtual {v1, v3, v0}, Landroid/app/AlertDialog$Builder;->setNeutralButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 635
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    move-result-object v0

    .line 636
    const v1, 0x7f090007

    if-eq p1, v1, :cond_1

    const v1, 0x7f090006

    if-ne p1, v1, :cond_0

    goto :goto_0

    .line 638
    :cond_0
    move p1, v2

    goto :goto_1

    .line 636
    :cond_1
    :goto_0
    const/4 p1, 0x1

    .line 638
    :goto_1
    if-eqz p1, :cond_2

    .line 639
    invoke-static {}, Lcom/android/camera/statistic/CameraStatUtil;->trackCameraErrorDialogShow()V

    .line 642
    :cond_2
    sget-boolean v1, Lcom/android/camera/Util;->sIsKillCameraService:Z

    if-eqz v1, :cond_4

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x1a

    if-lt v1, v3, :cond_4

    .line 643
    invoke-static {}, Lcom/mi/config/b;->gw()Z

    move-result v1

    if-eqz v1, :cond_4

    if-eqz p1, :cond_4

    .line 646
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v3

    invoke-static {}, Lcom/android/camera/CameraSettings;->getBroadcastKillServiceTime()J

    move-result-wide v5

    sub-long/2addr v3, v5

    .line 647
    const-wide/32 v5, 0xea60

    cmp-long p1, v3, v5

    if-lez p1, :cond_3

    .line 648
    invoke-static {p0}, Lcom/android/camera/Util;->broadcastKillService(Landroid/content/Context;)V

    .line 652
    :cond_3
    const/4 p1, -0x3

    invoke-virtual {v0, p1}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v9

    .line 653
    sget p1, Lmiui/R$style;->Widget_Button_Dialog:I

    invoke-virtual {v9, p1}, Landroid/widget/Button;->setTextAppearance(I)V

    .line 654
    invoke-virtual {v9, v2}, Landroid/widget/Button;->setEnabled(Z)V

    .line 655
    new-instance p1, Lcom/android/camera/Util$2;

    const-wide/16 v4, 0x1388

    const-wide/16 v6, 0x3e8

    move-object v3, p1

    move-object v8, p0

    invoke-direct/range {v3 .. v9}, Lcom/android/camera/Util$2;-><init>(JJLandroid/app/Activity;Landroid/widget/Button;)V

    .line 670
    invoke-virtual {p1}, Lcom/android/camera/Util$2;->start()Landroid/os/CountDownTimer;

    move-result-object p1

    .line 673
    new-instance v1, Lcom/android/camera/Util$3;

    invoke-direct {v1, p1}, Lcom/android/camera/Util$3;-><init>(Landroid/os/CountDownTimer;)V

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 682
    :cond_4
    check-cast p0, Lcom/android/camera/ActivityBase;

    invoke-virtual {p0, v0}, Lcom/android/camera/ActivityBase;->setErrorDialog(Landroid/app/AlertDialog;)V

    .line 683
    return-void
.end method

.method private static skip(Lorg/xmlpull/v1/XmlPullParser;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .line 2313
    invoke-interface {p0}, Lorg/xmlpull/v1/XmlPullParser;->getEventType()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    .line 2316
    nop

    .line 2317
    const/4 v0, 0x1

    :goto_0
    if-eqz v0, :cond_0

    .line 2318
    invoke-interface {p0}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    goto :goto_1

    .line 2320
    :pswitch_0
    add-int/lit8 v0, v0, -0x1

    .line 2321
    goto :goto_1

    .line 2323
    :pswitch_1
    add-int/lit8 v0, v0, 0x1

    .line 2324
    :goto_1
    goto :goto_0

    .line 2327
    :cond_0
    return-void

    .line 2314
    :cond_1
    new-instance p0, Ljava/lang/IllegalStateException;

    invoke-direct {p0}, Ljava/lang/IllegalStateException;-><init>()V

    throw p0

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static startScreenSlideAlphaInAnimation(Landroid/view/View;)V
    .locals 2

    .line 2774
    const/4 v0, 0x0

    invoke-static {p0, v0}, Landroid/support/v4/view/ViewCompat;->setAlpha(Landroid/view/View;F)V

    .line 2775
    invoke-static {p0}, Landroid/support/v4/view/ViewCompat;->animate(Landroid/view/View;)Landroid/support/v4/view/ViewPropertyAnimatorCompat;

    move-result-object p0

    const/high16 v0, 0x3f800000    # 1.0f

    invoke-virtual {p0, v0}, Landroid/support/v4/view/ViewPropertyAnimatorCompat;->alpha(F)Landroid/support/v4/view/ViewPropertyAnimatorCompat;

    move-result-object p0

    .line 2776
    const-wide/16 v0, 0x15e

    invoke-virtual {p0, v0, v1}, Landroid/support/v4/view/ViewPropertyAnimatorCompat;->setDuration(J)Landroid/support/v4/view/ViewPropertyAnimatorCompat;

    move-result-object p0

    .line 2777
    const-wide/16 v0, 0x190

    invoke-virtual {p0, v0, v1}, Landroid/support/v4/view/ViewPropertyAnimatorCompat;->setStartDelay(J)Landroid/support/v4/view/ViewPropertyAnimatorCompat;

    move-result-object p0

    new-instance v0, Lmiui/view/animation/SineEaseInOutInterpolator;

    invoke-direct {v0}, Lmiui/view/animation/SineEaseInOutInterpolator;-><init>()V

    .line 2778
    invoke-virtual {p0, v0}, Landroid/support/v4/view/ViewPropertyAnimatorCompat;->setInterpolator(Landroid/view/animation/Interpolator;)Landroid/support/v4/view/ViewPropertyAnimatorCompat;

    move-result-object p0

    .line 2779
    invoke-virtual {p0}, Landroid/support/v4/view/ViewPropertyAnimatorCompat;->start()V

    .line 2780
    return-void
.end method

.method public static stringSparseArraysIndexOf(Landroid/util/SparseArray;Ljava/lang/String;)I
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/util/SparseArray<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            ")I"
        }
    .end annotation

    .line 2082
    if-eqz p1, :cond_1

    .line 2083
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0}, Landroid/util/SparseArray;->size()I

    move-result v1

    if-ge v0, v1, :cond_1

    .line 2084
    invoke-virtual {p0, v0}, Landroid/util/SparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2085
    return v0

    .line 2083
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 2089
    :cond_1
    const/4 p0, -0x1

    return p0
.end method

.method public static updateCountryIso(Landroid/content/Context;)V
    .locals 2

    .line 270
    nop

    .line 274
    const-string v0, "phone"

    invoke-virtual {p0, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/telephony/TelephonyManager;

    .line 275
    invoke-virtual {v0}, Landroid/telephony/TelephonyManager;->getSimState()I

    move-result v0

    .line 276
    const/4 v1, 0x5

    if-eq v0, v1, :cond_0

    .line 277
    const-string v0, "ro.miui.region"

    invoke-static {v0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 279
    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 280
    const-string v1, "country_detector"

    invoke-virtual {p0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/location/CountryDetector;

    .line 281
    invoke-virtual {p0}, Landroid/location/CountryDetector;->detectCountry()Landroid/location/Country;

    move-result-object p0

    .line 282
    if-eqz p0, :cond_1

    .line 283
    invoke-virtual {p0}, Landroid/location/Country;->getCountryIso()Ljava/lang/String;

    move-result-object v0

    .line 286
    :cond_1
    sput-object v0, Lcom/android/camera/Util;->mCountryIso:Ljava/lang/String;

    .line 287
    const-string p0, "CameraUtil"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "antiBanding mCountryIso="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/android/camera/Util;->mCountryIso:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 288
    const-string p0, "camera_dump_parameters"

    sget-boolean v0, Lcom/android/camera/Util;->DEBUG:Z

    invoke-static {p0, v0}, Landroid/os/SystemProperties;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    sput-boolean p0, Lcom/android/camera/Util;->sIsDumpLog:Z

    .line 289
    const-string p0, "camera_dump_orig_jpg"

    const/4 v0, 0x0

    invoke-static {p0, v0}, Landroid/os/SystemProperties;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    sput-boolean p0, Lcom/android/camera/Util;->sIsDumpOrigJpg:Z

    .line 290
    const-string p0, "kill_camera_service_enable"

    const/4 v0, 0x1

    invoke-static {p0, v0}, Landroid/os/SystemProperties;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    sput-boolean p0, Lcom/android/camera/Util;->sIsKillCameraService:Z

    .line 291
    return-void
.end method

.method public static verifyAssetZip(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 3360
    invoke-virtual {p0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p0

    invoke-virtual {p0, p1}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object p0

    .line 3361
    const/4 p1, 0x0

    :try_start_0
    invoke-static {p0, p2, p3}, Lcom/android/camera/Util;->verifyZip(Ljava/io/InputStream;Ljava/lang/String;I)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 3362
    if-eqz p0, :cond_0

    invoke-static {p1, p0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    .line 3363
    :cond_0
    return-void

    .line 3362
    :catchall_0
    move-exception p2

    goto :goto_0

    .line 3360
    :catch_0
    move-exception p1

    :try_start_1
    throw p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 3362
    :goto_0
    if-eqz p0, :cond_1

    invoke-static {p1, p0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    :cond_1
    throw p2
.end method

.method public static verifyZip(Ljava/io/InputStream;Ljava/lang/String;I)V
    .locals 11
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 3313
    new-instance v0, Ljava/util/zip/ZipInputStream;

    invoke-direct {v0, p0}, Ljava/util/zip/ZipInputStream;-><init>(Ljava/io/InputStream;)V

    .line 3314
    const/4 p0, 0x0

    :try_start_0
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 3315
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_0

    .line 3316
    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 3319
    :cond_0
    :goto_0
    invoke-virtual {v0}, Ljava/util/zip/ZipInputStream;->getNextEntry()Ljava/util/zip/ZipEntry;

    move-result-object v1

    if-eqz v1, :cond_8

    .line 3320
    new-instance v2, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 3322
    nop

    .line 3323
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v3

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-nez v3, :cond_2

    .line 3324
    invoke-virtual {v1}, Ljava/util/zip/ZipEntry;->isDirectory()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 3325
    invoke-virtual {v2}, Ljava/io/File;->mkdirs()Z

    goto :goto_1

    .line 3327
    :cond_1
    invoke-virtual {v2}, Ljava/io/File;->createNewFile()Z

    .line 3328
    goto :goto_2

    .line 3330
    :cond_2
    invoke-virtual {v1}, Ljava/util/zip/ZipEntry;->isDirectory()Z

    move-result v3

    if-eqz v3, :cond_3

    .line 3331
    invoke-virtual {v2}, Ljava/io/File;->isDirectory()Z

    move-result v3

    if-nez v3, :cond_5

    .line 3332
    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    .line 3333
    invoke-virtual {v2}, Ljava/io/File;->mkdirs()Z

    goto :goto_1

    .line 3336
    :cond_3
    invoke-virtual {v2}, Ljava/io/File;->isFile()Z

    move-result v3

    if-nez v3, :cond_4

    .line 3337
    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    .line 3338
    invoke-virtual {v2}, Ljava/io/File;->createNewFile()Z

    .line 3340
    :cond_4
    invoke-static {v2, p2}, Lcom/android/camera/network/download/Verifier;->crc32(Ljava/io/File;I)J

    move-result-wide v6

    .line 3341
    invoke-virtual {v1}, Ljava/util/zip/ZipEntry;->getCrc()J

    move-result-wide v8

    cmp-long v3, v6, v8

    if-eqz v3, :cond_5

    .line 3342
    goto :goto_2

    .line 3345
    :cond_5
    :goto_1
    move v4, v5

    :goto_2
    if-eqz v4, :cond_7

    .line 3346
    const-string v3, "CameraUtil"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "corrupted "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v3, v1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 3347
    new-instance v1, Ljava/io/FileOutputStream;

    invoke-direct {v1, v2}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_2

    .line 3349
    :try_start_1
    new-array v2, p2, [B

    .line 3350
    :goto_3
    invoke-virtual {v0, v2}, Ljava/util/zip/ZipInputStream;->read([B)I

    move-result v3

    if-lez v3, :cond_6

    .line 3351
    invoke-virtual {v1, v2, v5, v3}, Ljava/io/FileOutputStream;->write([BII)V
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_3

    .line 3353
    :cond_6
    :try_start_2
    invoke-static {p0, v1}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_2
    .catch Ljava/lang/Throwable; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    goto :goto_5

    :catchall_0
    move-exception p1

    move-object p2, p0

    goto :goto_4

    .line 3347
    :catch_0
    move-exception p1

    :try_start_3
    throw p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 3353
    :catchall_1
    move-exception p2

    move-object v10, p2

    move-object p2, p1

    move-object p1, v10

    :goto_4
    :try_start_4
    invoke-static {p2, v1}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw p1
    :try_end_4
    .catch Ljava/lang/Throwable; {:try_start_4 .. :try_end_4} :catch_1
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    .line 3355
    :cond_7
    :goto_5
    goto/16 :goto_0

    .line 3356
    :cond_8
    invoke-static {p0, v0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    .line 3357
    return-void

    .line 3356
    :catchall_2
    move-exception p1

    goto :goto_6

    .line 3313
    :catch_1
    move-exception p0

    :try_start_5
    throw p0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    .line 3356
    :goto_6
    invoke-static {p0, v0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw p1
.end method

.method public static verifyZip(Ljava/lang/String;Ljava/lang/String;I)V
    .locals 12
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 3263
    new-instance v0, Ljava/util/zip/ZipFile;

    invoke-direct {v0, p0}, Ljava/util/zip/ZipFile;-><init>(Ljava/lang/String;)V

    .line 3264
    const/4 p0, 0x0

    :try_start_0
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 3265
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_0

    .line 3266
    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 3268
    :cond_0
    invoke-virtual {v0}, Ljava/util/zip/ZipFile;->entries()Ljava/util/Enumeration;

    move-result-object v1

    .line 3269
    :goto_0
    invoke-interface {v1}, Ljava/util/Enumeration;->hasMoreElements()Z

    move-result v2

    if-eqz v2, :cond_9

    .line 3270
    invoke-interface {v1}, Ljava/util/Enumeration;->nextElement()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/zip/ZipEntry;

    .line 3271
    new-instance v3, Ljava/io/File;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, "/"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 3273
    nop

    .line 3274
    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v4

    const/4 v5, 0x1

    const/4 v6, 0x0

    if-nez v4, :cond_2

    .line 3275
    invoke-virtual {v2}, Ljava/util/zip/ZipEntry;->isDirectory()Z

    move-result v4

    if-eqz v4, :cond_1

    .line 3276
    invoke-virtual {v3}, Ljava/io/File;->mkdirs()Z

    goto :goto_1

    .line 3278
    :cond_1
    invoke-virtual {v3}, Ljava/io/File;->createNewFile()Z

    .line 3279
    goto :goto_2

    .line 3281
    :cond_2
    invoke-virtual {v2}, Ljava/util/zip/ZipEntry;->isDirectory()Z

    move-result v4

    if-eqz v4, :cond_3

    .line 3282
    invoke-virtual {v3}, Ljava/io/File;->isDirectory()Z

    move-result v4

    if-nez v4, :cond_5

    .line 3283
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    .line 3284
    invoke-virtual {v3}, Ljava/io/File;->mkdirs()Z

    goto :goto_1

    .line 3287
    :cond_3
    invoke-virtual {v3}, Ljava/io/File;->isFile()Z

    move-result v4

    if-nez v4, :cond_4

    .line 3288
    invoke-virtual {v3}, Ljava/io/File;->delete()Z

    .line 3289
    invoke-virtual {v3}, Ljava/io/File;->createNewFile()Z

    .line 3291
    :cond_4
    invoke-static {v3, p2}, Lcom/android/camera/network/download/Verifier;->crc32(Ljava/io/File;I)J

    move-result-wide v7

    .line 3292
    invoke-virtual {v2}, Ljava/util/zip/ZipEntry;->getCrc()J

    move-result-wide v9

    cmp-long v4, v7, v9

    if-eqz v4, :cond_5

    .line 3293
    goto :goto_2

    .line 3296
    :cond_5
    :goto_1
    move v5, v6

    :goto_2
    if-eqz v5, :cond_8

    .line 3297
    const-string v4, "CameraUtil"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "corrupted "

    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 3298
    invoke-virtual {v0, v2}, Ljava/util/zip/ZipFile;->getInputStream(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;

    move-result-object v2
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_4

    .line 3299
    :try_start_1
    new-instance v4, Ljava/io/FileOutputStream;

    invoke-direct {v4, v3}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    .line 3298
    nop

    .line 3301
    :try_start_2
    new-array v3, p2, [B

    .line 3302
    :goto_3
    invoke-virtual {v2, v3}, Ljava/io/InputStream;->read([B)I

    move-result v5

    if-lez v5, :cond_6

    .line 3303
    invoke-virtual {v4, v3, v6, v5}, Ljava/io/FileOutputStream;->write([BII)V
    :try_end_2
    .catch Ljava/lang/Throwable; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_3

    .line 3305
    :cond_6
    :try_start_3
    invoke-static {p0, v4}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_3
    .catch Ljava/lang/Throwable; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    if-eqz v2, :cond_8

    :try_start_4
    invoke-static {p0, v2}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    :try_end_4
    .catch Ljava/lang/Throwable; {:try_start_4 .. :try_end_4} :catch_2
    .catchall {:try_start_4 .. :try_end_4} :catchall_4

    goto :goto_6

    :catchall_0
    move-exception p1

    move-object p2, p0

    goto :goto_4

    .line 3298
    :catch_0
    move-exception p1

    :try_start_5
    throw p1
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 3305
    :catchall_1
    move-exception p2

    move-object v11, p2

    move-object p2, p1

    move-object p1, v11

    :goto_4
    :try_start_6
    invoke-static {p2, v4}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw p1
    :try_end_6
    .catch Ljava/lang/Throwable; {:try_start_6 .. :try_end_6} :catch_1
    .catchall {:try_start_6 .. :try_end_6} :catchall_2

    :catchall_2
    move-exception p1

    move-object p2, p0

    goto :goto_5

    .line 3298
    :catch_1
    move-exception p1

    :try_start_7
    throw p1
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_3

    .line 3305
    :catchall_3
    move-exception p2

    move-object v11, p2

    move-object p2, p1

    move-object p1, v11

    :goto_5
    if-eqz v2, :cond_7

    :try_start_8
    invoke-static {p2, v2}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    :cond_7
    throw p1
    :try_end_8
    .catch Ljava/lang/Throwable; {:try_start_8 .. :try_end_8} :catch_2
    .catchall {:try_start_8 .. :try_end_8} :catchall_4

    .line 3307
    :cond_8
    :goto_6
    goto/16 :goto_0

    .line 3308
    :cond_9
    invoke-static {p0, v0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    .line 3309
    return-void

    .line 3308
    :catchall_4
    move-exception p1

    goto :goto_7

    .line 3263
    :catch_2
    move-exception p0

    :try_start_9
    throw p0
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_4

    .line 3308
    :goto_7
    invoke-static {p0, v0}, Lcom/android/camera/Util;->$closeResource(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V

    throw p1
.end method

.method public static viewUri(Landroid/net/Uri;Landroid/content/Context;)V
    .locals 3

    .line 1096
    invoke-virtual {p1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/android/camera/Util;->isUriValid(Landroid/net/Uri;Landroid/content/ContentResolver;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1097
    const-string p1, "CameraUtil"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Uri invalid. uri="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 1098
    return-void

    .line 1102
    :cond_0
    :try_start_0
    new-instance v0, Landroid/content/Intent;

    const-string v1, "com.android.camera.action.REVIEW"

    invoke-direct {v0, v1, p0}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    invoke-virtual {p1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1109
    goto :goto_0

    .line 1103
    :catch_0
    move-exception v0

    .line 1105
    :try_start_1
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.intent.action.VIEW"

    invoke-direct {v0, v1, p0}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    invoke-virtual {p1, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_1
    .catch Landroid/content/ActivityNotFoundException; {:try_start_1 .. :try_end_1} :catch_1

    .line 1108
    goto :goto_0

    .line 1106
    :catch_1
    move-exception p1

    .line 1107
    const-string v0, "CameraUtil"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "review image fail. uri="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v0, p0, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 1110
    :goto_0
    return-void
.end method
