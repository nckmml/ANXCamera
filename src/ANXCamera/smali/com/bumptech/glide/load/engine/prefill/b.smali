.class final Lcom/bumptech/glide/load/engine/prefill/b;
.super Ljava/lang/Object;
.source "PreFillQueue.java"


# instance fields
.field private final jm:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Lcom/bumptech/glide/load/engine/prefill/c;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final jn:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/bumptech/glide/load/engine/prefill/c;",
            ">;"
        }
    .end annotation
.end field

.field private jo:I

.field private jp:I


# direct methods
.method public constructor <init>(Ljava/util/Map;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Lcom/bumptech/glide/load/engine/prefill/c;",
            "Ljava/lang/Integer;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jm:Ljava/util/Map;

    new-instance v0, Ljava/util/ArrayList;

    invoke-interface {p1}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    iput-object v0, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jn:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    iget v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jo:I

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    add-int/2addr v1, v0

    iput v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jo:I

    goto :goto_0

    :cond_0
    return-void
.end method


# virtual methods
.method public bV()Lcom/bumptech/glide/load/engine/prefill/c;
    .locals 4

    iget-object v0, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jn:Ljava/util/List;

    iget v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jp:I

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/bumptech/glide/load/engine/prefill/c;

    iget-object v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jm:Ljava/util/Map;

    invoke-interface {v1, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v2

    const/4 v3, 0x1

    if-ne v2, v3, :cond_0

    iget-object v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jm:Ljava/util/Map;

    invoke-interface {v1, v0}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jn:Ljava/util/List;

    iget v2, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jp:I

    invoke-interface {v1, v2}, Ljava/util/List;->remove(I)Ljava/lang/Object;

    goto :goto_0

    :cond_0
    iget-object v2, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jm:Ljava/util/Map;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    sub-int/2addr v1, v3

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v2, v0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :goto_0
    iget v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jo:I

    sub-int/2addr v1, v3

    iput v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jo:I

    iget-object v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jn:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    const/4 v1, 0x0

    goto :goto_1

    :cond_1
    iget v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jp:I

    add-int/2addr v1, v3

    iget-object v2, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jn:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    rem-int/2addr v1, v2

    :goto_1
    iput v1, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jp:I

    return-object v0
.end method

.method public getSize()I
    .locals 1

    iget v0, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jo:I

    return v0
.end method

.method public isEmpty()Z
    .locals 1

    iget v0, p0, Lcom/bumptech/glide/load/engine/prefill/b;->jo:I

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method
