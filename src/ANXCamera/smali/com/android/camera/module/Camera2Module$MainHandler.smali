.class Lcom/android/camera/module/Camera2Module$MainHandler;
.super Landroid/os/Handler;
.source "Camera2Module.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/camera/module/Camera2Module;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "MainHandler"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/camera/module/Camera2Module;


# direct methods
.method public constructor <init>(Lcom/android/camera/module/Camera2Module;Landroid/os/Looper;)V
    .locals 0

    .line 2108
    iput-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    .line 2109
    invoke-direct {p0, p2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    .line 2110
    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 6

    .line 2114
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {v0}, Lcom/android/camera/module/Camera2Module;->isCreated()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    .line 2115
    invoke-virtual {p0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 2116
    return-void

    .line 2119
    :cond_0
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {v0}, Lcom/android/camera/module/Camera2Module;->getActivity()Lcom/android/camera/Camera;

    move-result-object v0

    if-nez v0, :cond_1

    .line 2120
    return-void

    .line 2123
    :cond_1
    iget v0, p1, Landroid/os/Message;->what:I

    const/16 v2, 0x80

    const/4 v3, 0x2

    if-eq v0, v3, :cond_a

    const/4 v4, 0x4

    if-eq v0, v4, :cond_9

    const/16 v5, 0x11

    if-eq v0, v5, :cond_8

    const/16 v2, 0x1f

    if-eq v0, v2, :cond_7

    const/16 v2, 0x21

    if-eq v0, v2, :cond_6

    const/16 v2, 0x23

    const/4 v3, 0x0

    const/4 v5, 0x1

    if-eq v0, v2, :cond_3

    packed-switch v0, :pswitch_data_0

    packed-switch v0, :pswitch_data_1

    packed-switch v0, :pswitch_data_2

    packed-switch v0, :pswitch_data_3

    .line 2126
    new-instance v0, Ljava/lang/RuntimeException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "no consumer for this message: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p1, Landroid/os/Message;->what:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 2226
    :pswitch_0
    invoke-static {}, Lcom/android/zxing/PreviewDecodeManager;->getInstance()Lcom/android/zxing/PreviewDecodeManager;

    move-result-object p1

    invoke-virtual {p1}, Lcom/android/zxing/PreviewDecodeManager;->reset()V

    goto/16 :goto_1

    .line 2217
    :pswitch_1
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    if-eqz p1, :cond_b

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    .line 2218
    invoke-interface {p1, v5}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->isFaceExists(I)Z

    move-result p1

    if-eqz p1, :cond_b

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    .line 2219
    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->isFocusViewVisible()Z

    move-result p1

    if-eqz p1, :cond_b

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mCamera2Device:Lcom/android/camera2/Camera2Proxy;

    .line 2221
    invoke-virtual {p1}, Lcom/android/camera2/Camera2Proxy;->getFocusMode()I

    move-result p1

    if-ne v4, p1, :cond_b

    .line 2222
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    const/4 v0, 0x7

    invoke-interface {p1, v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->clearFocusView(I)V

    goto/16 :goto_1

    .line 2209
    :pswitch_2
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {v0}, Lcom/android/camera/module/Camera2Module;->getTriggerMode()I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/android/camera/module/Camera2Module;->onShutterButtonClick(I)V

    .line 2210
    goto/16 :goto_1

    .line 2179
    :pswitch_3
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mActivity:Lcom/android/camera/Camera;

    invoke-virtual {p1}, Lcom/android/camera/Camera;->isActivityPaused()Z

    move-result p1

    if-nez p1, :cond_b

    .line 2180
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iput-boolean v5, p1, Lcom/android/camera/module/Camera2Module;->mOpenCameraFail:Z

    .line 2181
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->onCameraException()V

    goto/16 :goto_1

    .line 2203
    :pswitch_4
    invoke-static {}, Lcom/android/camera/module/Camera2Module;->access$1400()Ljava/lang/String;

    move-result-object p1

    const-string v0, "Oops, capture timeout later release timeout!"

    invoke-static {p1, v0}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 2205
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1, v3}, Lcom/android/camera/module/Camera2Module;->onPictureTakenFinished(Z)V

    .line 2206
    goto/16 :goto_1

    .line 2195
    :pswitch_5
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->isAlive()Z

    move-result p1

    if-nez p1, :cond_2

    .line 2196
    return-void

    .line 2198
    :cond_2
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$2700(Lcom/android/camera/module/Camera2Module;)V

    .line 2199
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$000(Lcom/android/camera/module/Camera2Module;)Lio/reactivex/ObservableEmitter;

    move-result-object p1

    invoke-interface {p1}, Lio/reactivex/ObservableEmitter;->onComplete()V

    .line 2200
    goto/16 :goto_1

    .line 2191
    :pswitch_6
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1, v5}, Lcom/android/camera/module/Camera2Module;->setCameraState(I)V

    .line 2192
    goto/16 :goto_1

    .line 2175
    :pswitch_7
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1, v1}, Lcom/android/camera/module/Camera2Module;->setActivity(Lcom/android/camera/Camera;)V

    .line 2176
    goto/16 :goto_1

    .line 2213
    :pswitch_8
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->restartModule()V

    .line 2214
    goto/16 :goto_1

    .line 2135
    :pswitch_9
    goto/16 :goto_1

    .line 2186
    :pswitch_a
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iput-boolean v5, p1, Lcom/android/camera/module/Camera2Module;->mOpenCameraFail:Z

    .line 2187
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->onCameraException()V

    .line 2188
    goto/16 :goto_1

    .line 2129
    :pswitch_b
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-interface {p1, v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->initializeFocusView(Lcom/android/camera/ui/FocusView$ExposureViewListener;)V

    .line 2130
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget-object p1, p1, Lcom/android/camera/module/Camera2Module;->mMainProtocol:Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-interface {p1, v0}, Lcom/android/camera/protocol/ModeProtocol$MainContentProtocol;->setObjectViewListener(Lcom/android/camera/ui/ObjectView$ObjectViewListener;)V

    .line 2132
    goto/16 :goto_1

    .line 2160
    :cond_3
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget v1, p1, Landroid/os/Message;->arg1:I

    if-lez v1, :cond_4

    move v1, v5

    goto :goto_0

    :cond_4
    move v1, v3

    :goto_0
    iget p1, p1, Landroid/os/Message;->arg2:I

    if-lez p1, :cond_5

    move v3, v5

    nop

    :cond_5
    invoke-static {v0, v1, v3}, Lcom/android/camera/module/Camera2Module;->access$3900(Lcom/android/camera/module/Camera2Module;ZZ)V

    .line 2161
    goto :goto_1

    .line 2152
    :cond_6
    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    iget v1, p1, Landroid/os/Message;->arg1:I

    iget p1, p1, Landroid/os/Message;->arg2:I

    invoke-static {v0, v1, p1}, Lcom/android/camera/module/Camera2Module;->access$3700(Lcom/android/camera/module/Camera2Module;II)V

    .line 2153
    goto :goto_1

    .line 2156
    :cond_7
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$3800(Lcom/android/camera/module/Camera2Module;)V

    .line 2157
    goto :goto_1

    .line 2168
    :cond_8
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$3600(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/module/Camera2Module$MainHandler;

    move-result-object p1

    invoke-virtual {p1, v5}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 2169
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$3600(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/module/Camera2Module$MainHandler;

    move-result-object p1

    invoke-virtual {p1, v3}, Lcom/android/camera/module/Camera2Module$MainHandler;->removeMessages(I)V

    .line 2170
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroid/view/Window;->addFlags(I)V

    .line 2171
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$3600(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/module/Camera2Module$MainHandler;

    move-result-object p1

    iget-object v0, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {v0}, Lcom/android/camera/module/Camera2Module;->getScreenDelay()I

    move-result v0

    int-to-long v0, v0

    invoke-virtual {p1, v3, v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    .line 2172
    goto :goto_1

    .line 2143
    :cond_9
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->checkActivityOrientation()V

    .line 2146
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v0

    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$3500(Lcom/android/camera/module/Camera2Module;)J

    move-result-wide v2

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x1388

    cmp-long p1, v0, v2

    if-gez p1, :cond_b

    .line 2147
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-static {p1}, Lcom/android/camera/module/Camera2Module;->access$3600(Lcom/android/camera/module/Camera2Module;)Lcom/android/camera/module/Camera2Module$MainHandler;

    move-result-object p1

    const-wide/16 v0, 0x64

    invoke-virtual {p1, v4, v0, v1}, Lcom/android/camera/module/Camera2Module$MainHandler;->sendEmptyMessageDelayed(IJ)Z

    goto :goto_1

    .line 2164
    :cond_a
    iget-object p1, p0, Lcom/android/camera/module/Camera2Module$MainHandler;->this$0:Lcom/android/camera/module/Camera2Module;

    invoke-virtual {p1}, Lcom/android/camera/module/Camera2Module;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroid/view/Window;->clearFlags(I)V

    .line 2165
    nop

    .line 2229
    :cond_b
    :goto_1
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x9
        :pswitch_b
        :pswitch_a
        :pswitch_9
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x2c
        :pswitch_8
        :pswitch_7
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x30
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
    .end packed-switch

    :pswitch_data_3
    .packed-switch 0x38
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
