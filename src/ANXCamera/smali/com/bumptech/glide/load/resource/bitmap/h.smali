.class public final Lcom/bumptech/glide/load/resource/bitmap/h;
.super Lcom/bumptech/glide/j;
.source "BitmapTransitionOptions.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/bumptech/glide/j<",
        "Lcom/bumptech/glide/load/resource/bitmap/h;",
        "Landroid/graphics/Bitmap;",
        ">;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/bumptech/glide/j;-><init>()V

    return-void
.end method

.method public static D(I)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    new-instance v0, Lcom/bumptech/glide/load/resource/bitmap/h;

    invoke-direct {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;-><init>()V

    invoke-virtual {v0, p0}, Lcom/bumptech/glide/load/resource/bitmap/h;->E(I)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p0

    return-object p0
.end method

.method public static a(Lcom/bumptech/glide/request/a/c$a;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .param p0    # Lcom/bumptech/glide/request/a/c$a;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    new-instance v0, Lcom/bumptech/glide/load/resource/bitmap/h;

    invoke-direct {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;-><init>()V

    invoke-virtual {v0, p0}, Lcom/bumptech/glide/load/resource/bitmap/h;->b(Lcom/bumptech/glide/request/a/c$a;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p0

    return-object p0
.end method

.method public static a(Lcom/bumptech/glide/request/a/c;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .param p0    # Lcom/bumptech/glide/request/a/c;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    new-instance v0, Lcom/bumptech/glide/load/resource/bitmap/h;

    invoke-direct {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;-><init>()V

    invoke-virtual {v0, p0}, Lcom/bumptech/glide/load/resource/bitmap/h;->b(Lcom/bumptech/glide/request/a/c;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p0

    return-object p0
.end method

.method public static c(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .param p0    # Lcom/bumptech/glide/request/a/g;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/bumptech/glide/request/a/g<",
            "Landroid/graphics/drawable/Drawable;",
            ">;)",
            "Lcom/bumptech/glide/load/resource/bitmap/h;"
        }
    .end annotation

    new-instance v0, Lcom/bumptech/glide/load/resource/bitmap/h;

    invoke-direct {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;-><init>()V

    invoke-virtual {v0, p0}, Lcom/bumptech/glide/load/resource/bitmap/h;->e(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p0

    return-object p0
.end method

.method public static cQ()Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    new-instance v0, Lcom/bumptech/glide/load/resource/bitmap/h;

    invoke-direct {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;-><init>()V

    invoke-virtual {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;->cR()Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object v0

    return-object v0
.end method

.method public static d(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .param p0    # Lcom/bumptech/glide/request/a/g;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/bumptech/glide/request/a/g<",
            "Landroid/graphics/Bitmap;",
            ">;)",
            "Lcom/bumptech/glide/load/resource/bitmap/h;"
        }
    .end annotation

    new-instance v0, Lcom/bumptech/glide/load/resource/bitmap/h;

    invoke-direct {v0}, Lcom/bumptech/glide/load/resource/bitmap/h;-><init>()V

    invoke-virtual {v0, p0}, Lcom/bumptech/glide/load/resource/bitmap/h;->b(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/j;

    move-result-object p0

    check-cast p0, Lcom/bumptech/glide/load/resource/bitmap/h;

    return-object p0
.end method


# virtual methods
.method public E(I)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    new-instance v0, Lcom/bumptech/glide/request/a/c$a;

    invoke-direct {v0, p1}, Lcom/bumptech/glide/request/a/c$a;-><init>(I)V

    invoke-virtual {p0, v0}, Lcom/bumptech/glide/load/resource/bitmap/h;->b(Lcom/bumptech/glide/request/a/c$a;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p1

    return-object p1
.end method

.method public b(Lcom/bumptech/glide/request/a/c$a;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 0
    .param p1    # Lcom/bumptech/glide/request/a/c$a;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    invoke-virtual {p1}, Lcom/bumptech/glide/request/a/c$a;->fg()Lcom/bumptech/glide/request/a/c;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/bumptech/glide/load/resource/bitmap/h;->e(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p1

    return-object p1
.end method

.method public b(Lcom/bumptech/glide/request/a/c;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 0
    .param p1    # Lcom/bumptech/glide/request/a/c;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    invoke-virtual {p0, p1}, Lcom/bumptech/glide/load/resource/bitmap/h;->e(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object p1

    return-object p1
.end method

.method public cR()Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    new-instance v0, Lcom/bumptech/glide/request/a/c$a;

    invoke-direct {v0}, Lcom/bumptech/glide/request/a/c$a;-><init>()V

    invoke-virtual {p0, v0}, Lcom/bumptech/glide/load/resource/bitmap/h;->b(Lcom/bumptech/glide/request/a/c$a;)Lcom/bumptech/glide/load/resource/bitmap/h;

    move-result-object v0

    return-object v0
.end method

.method public e(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/load/resource/bitmap/h;
    .locals 1
    .param p1    # Lcom/bumptech/glide/request/a/g;
        .annotation build Landroid/support/annotation/NonNull;
        .end annotation
    .end param
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/bumptech/glide/request/a/g<",
            "Landroid/graphics/drawable/Drawable;",
            ">;)",
            "Lcom/bumptech/glide/load/resource/bitmap/h;"
        }
    .end annotation

    new-instance v0, Lcom/bumptech/glide/request/a/b;

    invoke-direct {v0, p1}, Lcom/bumptech/glide/request/a/b;-><init>(Lcom/bumptech/glide/request/a/g;)V

    invoke-virtual {p0, v0}, Lcom/bumptech/glide/load/resource/bitmap/h;->b(Lcom/bumptech/glide/request/a/g;)Lcom/bumptech/glide/j;

    move-result-object p1

    check-cast p1, Lcom/bumptech/glide/load/resource/bitmap/h;

    return-object p1
.end method
