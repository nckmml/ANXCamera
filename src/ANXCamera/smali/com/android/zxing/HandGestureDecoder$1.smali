.class Lcom/android/zxing/HandGestureDecoder$1;
.super Ljava/lang/Object;
.source "HandGestureDecoder.java"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/zxing/HandGestureDecoder;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lio/reactivex/functions/Consumer<",
        "Ljava/lang/Integer;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/zxing/HandGestureDecoder;


# direct methods
.method constructor <init>(Lcom/android/zxing/HandGestureDecoder;)V
    .locals 0

    iput-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public accept(Ljava/lang/Integer;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    const-string v0, "HandGestureDecoder"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Detected rect left = "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, " "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    iget v2, v2, Lcom/android/zxing/HandGestureDecoder;->mTipShowInterval:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    const/4 v0, 0x0

    const/4 v1, 0x1

    if-ltz p1, :cond_0

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1, v1}, Lcom/android/zxing/HandGestureDecoder;->access$002(Lcom/android/zxing/HandGestureDecoder;Z)Z

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1, v0}, Lcom/android/zxing/HandGestureDecoder;->access$002(Lcom/android/zxing/HandGestureDecoder;Z)Z

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$100(Lcom/android/zxing/HandGestureDecoder;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    :goto_0
    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$200(Lcom/android/zxing/HandGestureDecoder;)Z

    move-result p1

    if-eqz p1, :cond_1

    return-void

    :cond_1
    const-string p1, "HandGestureDecoder"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Continuous interval: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v3, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {v3}, Lcom/android/zxing/HandGestureDecoder;->access$100(Lcom/android/zxing/HandGestureDecoder;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v3

    invoke-virtual {v3}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {p1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$100(Lcom/android/zxing/HandGestureDecoder;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p1

    if-lez p1, :cond_2

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$100(Lcom/android/zxing/HandGestureDecoder;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndDecrement()I

    goto :goto_1

    :cond_2
    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$000(Lcom/android/zxing/HandGestureDecoder;)Z

    move-result p1

    if-eqz p1, :cond_3

    const-string p1, "HandGestureDecoder"

    const-string v2, "Triggering countdown..."

    invoke-static {p1, v2}, Lcom/android/camera/log/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v2, 0xa1

    invoke-virtual {p1, v2}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$CameraAction;

    if-eqz p1, :cond_3

    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$CameraAction;->isDoingAction()Z

    move-result v2

    if-nez v2, :cond_3

    const/16 v2, 0x64

    invoke-interface {p1, v2}, Lcom/android/camera/protocol/ModeProtocol$CameraAction;->onShutterButtonClick(I)V

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1, v1}, Lcom/android/zxing/HandGestureDecoder;->access$202(Lcom/android/zxing/HandGestureDecoder;Z)Z

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$100(Lcom/android/zxing/HandGestureDecoder;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object p1

    const/4 v2, 0x3

    invoke-static {}, Lcom/android/zxing/HandGestureDecoder;->access$300()I

    move-result v3

    mul-int/2addr v2, v3

    invoke-virtual {p1, v2}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object p1

    iget-object v2, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {v2}, Lcom/android/zxing/HandGestureDecoder;->access$200(Lcom/android/zxing/HandGestureDecoder;)Z

    move-result v2

    xor-int/2addr v2, v1

    invoke-virtual {p1, v2}, Lcom/android/camera/data/data/runing/DataItemRunning;->setHandGestureRunning(Z)V

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1, v0}, Lcom/android/zxing/HandGestureDecoder;->access$402(Lcom/android/zxing/HandGestureDecoder;Z)Z

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {}, Lcom/android/zxing/HandGestureDecoder;->access$300()I

    move-result v0

    iput v0, p1, Lcom/android/zxing/HandGestureDecoder;->mTipShowInterval:I

    :cond_3
    :goto_1
    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1}, Lcom/android/zxing/HandGestureDecoder;->access$400(Lcom/android/zxing/HandGestureDecoder;)Z

    move-result p1

    if-nez p1, :cond_5

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    iget p1, p1, Lcom/android/zxing/HandGestureDecoder;->mTipShowInterval:I

    if-gtz p1, :cond_5

    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemRunning()Lcom/android/camera/data/data/runing/DataItemRunning;

    move-result-object p1

    invoke-virtual {p1, v1}, Lcom/android/camera/data/data/runing/DataItemRunning;->setHandGestureRunning(Z)V

    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object p1

    const/16 v0, 0xac

    invoke-virtual {p1, v0}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object p1

    check-cast p1, Lcom/android/camera/protocol/ModeProtocol$TopAlert;

    if-eqz p1, :cond_4

    invoke-interface {p1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->isExtraMenuShowing()Z

    move-result v0

    if-nez v0, :cond_4

    invoke-interface {p1, v1}, Lcom/android/camera/protocol/ModeProtocol$TopAlert;->reInitAlert(Z)V

    :cond_4
    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    invoke-static {p1, v1}, Lcom/android/zxing/HandGestureDecoder;->access$402(Lcom/android/zxing/HandGestureDecoder;Z)Z

    :cond_5
    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    iget p1, p1, Lcom/android/zxing/HandGestureDecoder;->mTipShowInterval:I

    if-lez p1, :cond_6

    iget-object p1, p0, Lcom/android/zxing/HandGestureDecoder$1;->this$0:Lcom/android/zxing/HandGestureDecoder;

    iget v0, p1, Lcom/android/zxing/HandGestureDecoder;->mTipShowInterval:I

    sub-int/2addr v0, v1

    iput v0, p1, Lcom/android/zxing/HandGestureDecoder;->mTipShowInterval:I

    :cond_6
    return-void
.end method

.method public bridge synthetic accept(Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p0, p1}, Lcom/android/zxing/HandGestureDecoder$1;->accept(Ljava/lang/Integer;)V

    return-void
.end method
