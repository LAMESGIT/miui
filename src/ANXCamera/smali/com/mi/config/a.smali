.class public Lcom/mi/config/a;
.super Lcom/android/camera/data/data/DataItemBase;
.source "DataItemFeature.java"

# interfaces
.implements Lcom/mi/config/c;


# static fields
.field private static final TAG:Ljava/lang/String; = "DataFeature"


# instance fields
.field private pD:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 31
    invoke-direct {p0}, Lcom/android/camera/data/data/DataItemBase;-><init>()V

    .line 32
    invoke-virtual {p0}, Lcom/mi/config/a;->eO()V

    .line 33
    return-void
.end method

.method private A(Ljava/lang/String;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/json/JSONException;
        }
    .end annotation

    .line 83
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 84
    invoke-virtual {v0}, Lorg/json/JSONObject;->keys()Ljava/util/Iterator;

    move-result-object p1

    .line 85
    invoke-virtual {p0}, Lcom/mi/config/a;->getValues()Landroid/support/v4/util/SimpleArrayMap;

    move-result-object v1

    .line 86
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 87
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 88
    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Landroid/support/v4/util/SimpleArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 89
    goto :goto_0

    .line 90
    :cond_0
    return-void
.end method


# virtual methods
.method public B(Ljava/lang/String;)Z
    .locals 1

    .line 93
    invoke-virtual {p0}, Lcom/mi/config/a;->getValues()Landroid/support/v4/util/SimpleArrayMap;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/support/v4/util/SimpleArrayMap;->containsKey(Ljava/lang/Object;)Z

    move-result p1

    return p1
.end method

.method public eO()V
    .locals 6

    .line 46
    invoke-static {}, Lcom/android/camera/CameraAppImpl;->getAndroidContext()Landroid/content/Context;

    move-result-object v0

    .line 47
    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "feature_"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v3, Lcom/mi/config/b;->pE:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v3, "raw"

    const-string v4, "com.android.camera"

    invoke-virtual {v1, v2, v3, v4}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v1

    .line 53
    if-gtz v1, :cond_0

    .line 54
    const-string v0, "DataFeature"

    const-string v1, "feature list default"

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 55
    return-void

    .line 58
    :cond_0
    const/4 v2, 0x0

    .line 60
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    .line 62
    :try_start_0
    new-instance v4, Ljava/io/BufferedReader;

    new-instance v5, Ljava/io/InputStreamReader;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->openRawResource(I)Ljava/io/InputStream;

    move-result-object v0

    invoke-direct {v5, v0}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v4, v5}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 63
    :goto_0
    :try_start_1
    invoke-virtual {v4}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 64
    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 66
    :cond_1
    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/mi/config/a;->A(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 72
    nop

    .line 74
    :try_start_2
    invoke-virtual {v4}, Ljava/io/BufferedReader;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_4

    goto :goto_3

    .line 72
    :catchall_0
    move-exception v0

    move-object v2, v4

    goto :goto_5

    .line 69
    :catch_0
    move-exception v0

    move-object v2, v4

    goto :goto_1

    .line 67
    :catch_1
    move-exception v0

    move-object v2, v4

    goto :goto_2

    .line 72
    :catchall_1
    move-exception v0

    goto :goto_5

    .line 69
    :catch_2
    move-exception v0

    .line 70
    :goto_1
    :try_start_3
    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 72
    if-eqz v2, :cond_2

    .line 74
    :try_start_4
    invoke-virtual {v2}, Ljava/io/BufferedReader;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_4

    goto :goto_3

    .line 67
    :catch_3
    move-exception v0

    .line 68
    :goto_2
    :try_start_5
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 72
    if-eqz v2, :cond_2

    .line 74
    :try_start_6
    invoke-virtual {v2}, Ljava/io/BufferedReader;->close()V
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_4

    .line 77
    :goto_3
    goto :goto_4

    .line 75
    :catch_4
    move-exception v0

    .line 76
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_3

    .line 80
    :cond_2
    :goto_4
    return-void

    .line 72
    :goto_5
    if-eqz v2, :cond_3

    .line 74
    :try_start_7
    invoke-virtual {v2}, Ljava/io/BufferedReader;->close()V
    :try_end_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_5

    .line 77
    goto :goto_6

    .line 75
    :catch_5
    move-exception v1

    .line 76
    invoke-virtual {v1}, Ljava/io/IOException;->printStackTrace()V

    .line 77
    :cond_3
    :goto_6
    throw v0
.end method

.method public eP()Z
    .locals 2

    .line 97
    const-string v0, "s_a"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public eQ()I
    .locals 2

    .line 101
    const-string v0, "c_t_r"

    const/16 v1, 0x14

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public eR()Z
    .locals 2

    .line 106
    iget-object v0, p0, Lcom/mi/config/a;->pD:Ljava/lang/String;

    if-nez v0, :cond_0

    .line 107
    const-string v0, "ro.boot.hwc"

    invoke-static {v0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/mi/config/a;->pD:Ljava/lang/String;

    .line 110
    :cond_0
    const-string v0, "india"

    iget-object v1, p0, Lcom/mi/config/a;->pD:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public eS()Z
    .locals 2

    .line 114
    invoke-static {}, Lmiui/os/Build;->getRegion()Ljava/lang/String;

    move-result-object v0

    const-string v1, "IN"

    invoke-virtual {v0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public eT()Z
    .locals 2

    .line 118
    iget-object v0, p0, Lcom/mi/config/a;->pD:Ljava/lang/String;

    if-nez v0, :cond_0

    .line 119
    const-string v0, "ro.boot.hwc"

    invoke-static {v0}, Landroid/os/SystemProperties;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/mi/config/a;->pD:Ljava/lang/String;

    .line 121
    :cond_0
    const-string v0, "cn"

    iget-object v1, p0, Lcom/mi/config/a;->pD:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method public eU()Z
    .locals 2

    .line 125
    const-string v0, "s_i_a"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/mi/config/a;->eR()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    nop

    :cond_0
    return v1
.end method

.method public eV()Z
    .locals 2

    .line 129
    const-string v0, "c_0x0c"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/mi/config/a;->eR()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    nop

    :cond_0
    return v1
.end method

.method public eW()Z
    .locals 2

    .line 133
    const-string v0, "s_z_m"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public eX()Z
    .locals 2

    .line 137
    const-string v0, "a_e_d"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public eY()Z
    .locals 2

    .line 141
    const-string v0, "s_m_f"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public eZ()Z
    .locals 2

    .line 146
    const-string v0, "i_l_m_d"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fA()Z
    .locals 2

    .line 283
    const-string v0, "s_c_w_b"

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fB()Z
    .locals 2

    .line 291
    const-string v0, "c_0x04_i_l_h_d"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fC()Z
    .locals 2

    .line 295
    const-string v0, "c_0x17"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fD()Z
    .locals 2

    .line 299
    const-string v0, "c_0x19"

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fE()Z
    .locals 2

    .line 303
    invoke-static {}, Lcom/android/camera/Util;->isGlobalVersion()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 304
    return v1

    .line 306
    :cond_0
    const-string v0, "c_0x00_s_l_s"

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fF()I
    .locals 2

    .line 310
    const-string v0, "c_0x01_p_g_a_v"

    const/16 v1, 0x118

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public fG()F
    .locals 3

    .line 314
    const-string v0, "c_0x02_p_c_r_v"

    const-wide v1, 0x3fec0d1b80000000L    # 0.8766000270843506

    invoke-virtual {p0, v0, v1, v2}, Lcom/mi/config/a;->getDoubleFromValues(Ljava/lang/String;D)D

    move-result-wide v0

    double-to-float v0, v0

    return v0
.end method

.method public fH()Z
    .locals 2

    .line 318
    invoke-static {}, Lcom/android/camera/Util;->isGlobalVersion()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 319
    return v1

    .line 321
    :cond_0
    const-string v0, "c_0x03"

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fI()Z
    .locals 2

    .line 325
    const-string v0, "s_c_w_m"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fJ()Z
    .locals 2

    .line 341
    const-string v0, "c_0x08"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fK()Z
    .locals 2

    .line 345
    const-string v0, "c_0x09"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fL()Z
    .locals 2

    .line 349
    const-string v0, "c_0x0a"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fM()Z
    .locals 2

    .line 353
    const-string v0, "c_0x10"

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fN()Z
    .locals 2

    .line 357
    const-string v0, "c_0x11"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fO()I
    .locals 2

    .line 361
    const-string v0, "c_0x13"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public fP()Z
    .locals 2

    .line 371
    const-string v0, "c_0x0e"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fQ()Z
    .locals 2

    .line 377
    invoke-virtual {p0}, Lcom/mi/config/a;->fR()I

    move-result v0

    const/4 v1, 0x0

    if-lez v0, :cond_1

    const-string v0, "c_0x0f"

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    goto :goto_1

    :cond_1
    :goto_0
    const/4 v1, 0x1

    :goto_1
    return v1
.end method

.method public fR()I
    .locals 2

    .line 381
    const-string v0, "c_0x0d"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public fS()I
    .locals 2

    .line 385
    const-string v0, "c_0x12"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public fT()Z
    .locals 2

    .line 389
    const-string v0, "c_0x14"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fU()Ljava/lang/String;
    .locals 2

    .line 393
    const-string v0, "c_0x15"

    sget-object v1, Lcom/android/camera/constant/BeautyConstant;->LEVEL_CLOSE:Ljava/lang/String;

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public fV()Z
    .locals 2

    .line 397
    const-string v0, "c_0x16"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fW()Z
    .locals 1

    .line 401
    invoke-static {}, Lcom/mi/config/b;->ie()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 402
    const/4 v0, 0x0

    return v0

    .line 404
    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method public fX()Z
    .locals 2

    .line 408
    const-string v0, "c_0x20"

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fY()Z
    .locals 2

    .line 412
    const-string v0, "c_0x21"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fZ()Z
    .locals 3

    .line 420
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v1, 0x0

    const/16 v2, 0x1c

    if-ge v0, v2, :cond_0

    .line 421
    return v1

    .line 423
    :cond_0
    const-string v0, "c_0x23"

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fa()Z
    .locals 2

    .line 150
    const-string v0, "s_p_l_b"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fb()Z
    .locals 2

    .line 154
    const-string v0, "s_p_l_f"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fc()Z
    .locals 2

    .line 158
    invoke-virtual {p0}, Lcom/mi/config/a;->eR()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/mi/config/a;->eS()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const-string v0, "s_p_l_i_e"

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v1, 0x1

    nop

    :cond_1
    return v1
.end method

.method public fd()Z
    .locals 2

    .line 162
    const-string v0, "s_f_a"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fe()Z
    .locals 2

    .line 166
    const-string v0, "s_p_a"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public ff()Z
    .locals 2

    .line 170
    const-string v0, "s_v_b"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fg()Z
    .locals 2

    .line 177
    const-string v0, "s_o_a_w"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fh()Z
    .locals 2

    .line 181
    const-string v0, "s_f_s"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fi()Z
    .locals 2

    .line 193
    const-string v0, "s_m_c_t_f"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fj()Z
    .locals 2

    .line 197
    const-string v0, "s_a_3"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fk()Z
    .locals 3

    .line 202
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v1, 0x0

    const/16 v2, 0x1c

    if-ne v0, v2, :cond_0

    const-string v0, "s_s_n"

    .line 203
    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    nop

    .line 202
    :cond_0
    return v1
.end method

.method public fl()Z
    .locals 2

    .line 213
    const-string v0, "s_m_l"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fm()Z
    .locals 2

    .line 222
    const-string v0, "s_s_v"

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fn()Z
    .locals 2

    .line 226
    const-string v0, "s_s_s"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fo()Z
    .locals 2

    .line 230
    const-string v0, "s_f_s_c"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fp()Z
    .locals 2

    .line 234
    const-string v0, "s_b_m"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fq()I
    .locals 2

    .line 238
    const-string v0, "c_0x0b"

    const/16 v1, 0xb4

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public fr()Z
    .locals 2

    .line 242
    const-string v0, "s_a_u"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fs()Z
    .locals 2

    .line 246
    const-string v0, "s_f_z_i"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public ft()Z
    .locals 2

    .line 250
    const-string v0, "s_v_f_m"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fu()Z
    .locals 2

    .line 254
    const-string v0, "s_f_9"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fv()Z
    .locals 2

    .line 259
    const-string v0, "s_s_m_t"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/mi/config/a;->fu()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    goto :goto_1

    :cond_1
    :goto_0
    const/4 v1, 0x1

    :goto_1
    return v1
.end method

.method public fw()Z
    .locals 2

    .line 263
    const-string v0, "s_e_l"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public fx()Ljava/lang/String;
    .locals 2

    .line 267
    const-string v0, "h_d_v"

    const-string v1, ""

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public fy()I
    .locals 2

    .line 271
    const-string v0, "a_l_l_l"

    const/16 v1, 0x15e

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public fz()I
    .locals 2

    .line 275
    const-string v0, "a_l_h_l"

    const/16 v1, 0x12c

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method public ga()Z
    .locals 2

    .line 426
    const-string v0, "i_s_e_r"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public gb()Z
    .locals 2

    .line 430
    const-string v0, "i_q_a_u_m"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public gd()Z
    .locals 2

    .line 434
    const-string v0, "e_p_p_l_t"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public ge()Z
    .locals 2

    .line 438
    const-string v0, "c_0x31"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method protected isMutable()Z
    .locals 1

    .line 367
    const/4 v0, 0x0

    return v0
.end method

.method public isSupport4KUHDEIS()Z
    .locals 2

    .line 337
    const-string v0, "c_0x07"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public isSupportBeautyBody()Z
    .locals 2

    .line 287
    const-string v0, "s_b_b"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public isSupportBokehAdjust()Z
    .locals 2

    .line 416
    const-string v0, "c_0x22"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public isSupportNormalWideLDC()Z
    .locals 2

    .line 329
    const-string v0, "c_0x05"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public isSupportUltraWide()Z
    .locals 2

    .line 279
    const-string v0, "s_u_w"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public isSupportUltraWideLDC()Z
    .locals 2

    .line 333
    const-string v0, "c_0x06"

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Lcom/mi/config/a;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public isTransient()Z
    .locals 1

    .line 42
    const/4 v0, 0x1

    return v0
.end method

.method public provideKey()Ljava/lang/String;
    .locals 1

    .line 37
    const/4 v0, 0x0

    return-object v0
.end method
