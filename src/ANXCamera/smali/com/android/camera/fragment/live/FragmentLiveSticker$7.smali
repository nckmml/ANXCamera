.class Lcom/android/camera/fragment/live/FragmentLiveSticker$7;
.super Ljava/lang/Object;
.source "FragmentLiveSticker.java"

# interfaces
.implements Lcom/android/camera/network/resource/OnLiveDownloadListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/camera/fragment/live/FragmentLiveSticker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;


# direct methods
.method constructor <init>(Lcom/android/camera/fragment/live/FragmentLiveSticker;)V
    .locals 0

    iput-object p1, p0, Lcom/android/camera/fragment/live/FragmentLiveSticker$7;->this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFinish(Ljava/lang/String;I)V
    .locals 6

    const-string v0, "FragmentLiveSticker"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "finish "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ": "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    nop

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    iget-object v2, p0, Lcom/android/camera/fragment/live/FragmentLiveSticker$7;->this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;

    invoke-static {v2}, Lcom/android/camera/fragment/live/FragmentLiveSticker;->access$300(Lcom/android/camera/fragment/live/FragmentLiveSticker;)Ljava/util/List;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    const/4 v3, -0x1

    if-ge v1, v2, :cond_1

    iget-object v2, p0, Lcom/android/camera/fragment/live/FragmentLiveSticker$7;->this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;

    invoke-static {v2}, Lcom/android/camera/fragment/live/FragmentLiveSticker;->access$300(Lcom/android/camera/fragment/live/FragmentLiveSticker;)Ljava/util/List;

    move-result-object v2

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/android/camera/sticker/LiveStickerInfo;

    iget-object v2, v2, Lcom/android/camera/sticker/LiveStickerInfo;->id:Ljava/lang/String;

    invoke-virtual {p1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    nop

    goto :goto_1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    move v1, v3

    :goto_1
    if-ne v1, v3, :cond_2

    const-string p2, "FragmentLiveSticker"

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "sticker "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " not found"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p2, p1}, Lcom/android/camera/log/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_2
    nop

    iget-object v2, p0, Lcom/android/camera/fragment/live/FragmentLiveSticker$7;->this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;

    invoke-static {v2}, Lcom/android/camera/fragment/live/FragmentLiveSticker;->access$300(Lcom/android/camera/fragment/live/FragmentLiveSticker;)Ljava/util/List;

    move-result-object v2

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/android/camera/sticker/LiveStickerInfo;

    iput p2, v2, Lcom/android/camera/sticker/LiveStickerInfo;->downloadState:I

    const/4 v3, 0x3

    const/4 v4, 0x1

    if-eq p2, v3, :cond_3

    if-eq p2, v4, :cond_3

    const-string v3, "FragmentLiveSticker"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "download "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " failed, state = "

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v3, p1}, Lcom/android/camera/log/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    iget-object p1, v2, Lcom/android/camera/sticker/LiveStickerInfo;->name:Ljava/lang/String;

    invoke-static {p1, v0}, Lcom/android/camera/statistic/CameraStatUtil;->trackLiveStickerDownload(Ljava/lang/String;Z)V

    iget-object p1, p0, Lcom/android/camera/fragment/live/FragmentLiveSticker$7;->this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;

    invoke-static {p1}, Lcom/android/camera/fragment/live/FragmentLiveSticker;->access$400(Lcom/android/camera/fragment/live/FragmentLiveSticker;)V

    iget-object p1, p0, Lcom/android/camera/fragment/live/FragmentLiveSticker$7;->this$0:Lcom/android/camera/fragment/live/FragmentLiveSticker;

    invoke-static {p1}, Lcom/android/camera/fragment/live/FragmentLiveSticker;->access$000(Lcom/android/camera/fragment/live/FragmentLiveSticker;)Lcom/android/camera/fragment/live/FragmentLiveSticker$StickerItemAdapter;

    move-result-object p1

    invoke-virtual {p1, v1}, Lcom/android/camera/fragment/live/FragmentLiveSticker$StickerItemAdapter;->notifyItemChanged(I)V

    goto :goto_2

    :cond_3
    iget-object p1, v2, Lcom/android/camera/sticker/LiveStickerInfo;->name:Ljava/lang/String;

    invoke-static {p1, v4}, Lcom/android/camera/statistic/CameraStatUtil;->trackLiveStickerDownload(Ljava/lang/String;Z)V

    new-instance p1, Lcom/android/camera/fragment/live/FragmentLiveSticker$7$2;

    invoke-direct {p1, p0, v2}, Lcom/android/camera/fragment/live/FragmentLiveSticker$7$2;-><init>(Lcom/android/camera/fragment/live/FragmentLiveSticker$7;Lcom/android/camera/sticker/LiveStickerInfo;)V

    invoke-static {p1}, Lio/reactivex/Completable;->fromAction(Lio/reactivex/functions/Action;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-static {}, Lio/reactivex/schedulers/Schedulers;->io()Lio/reactivex/Scheduler;

    move-result-object p2

    invoke-virtual {p1, p2}, Lio/reactivex/Completable;->subscribeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Completable;

    move-result-object p1

    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object p2

    invoke-virtual {p1, p2}, Lio/reactivex/Completable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Completable;

    move-result-object p1

    new-instance p2, Lcom/android/camera/fragment/live/FragmentLiveSticker$7$1;

    invoke-direct {p2, p0, v1, v2}, Lcom/android/camera/fragment/live/FragmentLiveSticker$7$1;-><init>(Lcom/android/camera/fragment/live/FragmentLiveSticker$7;ILcom/android/camera/sticker/LiveStickerInfo;)V

    invoke-virtual {p1, p2}, Lio/reactivex/Completable;->subscribe(Lio/reactivex/CompletableObserver;)V

    :goto_2
    return-void
.end method

.method public onProgressUpdate(Ljava/lang/String;I)V
    .locals 3

    const-string v0, "FragmentLiveSticker"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string/jumbo v2, "update "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ": "

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/android/camera/log/Log;->v(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method
