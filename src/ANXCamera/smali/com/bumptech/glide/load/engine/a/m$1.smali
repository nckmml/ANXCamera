.class Lcom/bumptech/glide/load/engine/a/m$1;
.super Ljava/lang/Object;
.source "SafeKeyGenerator.java"

# interfaces
.implements Lcom/bumptech/glide/util/a/a$a;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/bumptech/glide/load/engine/a/m;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/bumptech/glide/util/a/a$a<",
        "Lcom/bumptech/glide/load/engine/a/m$a;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic iG:Lcom/bumptech/glide/load/engine/a/m;


# direct methods
.method constructor <init>(Lcom/bumptech/glide/load/engine/a/m;)V
    .locals 0

    iput-object p1, p0, Lcom/bumptech/glide/load/engine/a/m$1;->iG:Lcom/bumptech/glide/load/engine/a/m;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bM()Lcom/bumptech/glide/load/engine/a/m$a;
    .locals 2

    :try_start_0
    new-instance v0, Lcom/bumptech/glide/load/engine/a/m$a;

    const-string v1, "SHA-256"

    invoke-static {v1}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/bumptech/glide/load/engine/a/m$a;-><init>(Ljava/security/MessageDigest;)V
    :try_end_0
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    new-instance v1, Ljava/lang/RuntimeException;

    invoke-direct {v1, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v1
.end method

.method public synthetic create()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/bumptech/glide/load/engine/a/m$1;->bM()Lcom/bumptech/glide/load/engine/a/m$a;

    move-result-object v0

    return-object v0
.end method
