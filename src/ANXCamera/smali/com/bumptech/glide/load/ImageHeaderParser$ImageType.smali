.class public final enum Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;
.super Ljava/lang/Enum;
.source "ImageHeaderParser.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/bumptech/glide/load/ImageHeaderParser;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "ImageType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;",
        ">;"
    }
.end annotation


# static fields
.field public static final enum dA:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dB:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dC:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dD:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field private static final synthetic dE:[Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dw:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dx:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dy:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

.field public static final enum dz:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;


# instance fields
.field private final hasAlpha:Z


# direct methods
.method static constructor <clinit>()V
    .locals 10

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "GIF"

    const/4 v2, 0x1

    const/4 v3, 0x0

    invoke-direct {v0, v1, v3, v2}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dw:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "JPEG"

    invoke-direct {v0, v1, v2, v3}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dx:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "RAW"

    const/4 v4, 0x2

    invoke-direct {v0, v1, v4, v3}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dy:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "PNG_A"

    const/4 v5, 0x3

    invoke-direct {v0, v1, v5, v2}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dz:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "PNG"

    const/4 v6, 0x4

    invoke-direct {v0, v1, v6, v3}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dA:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "WEBP_A"

    const/4 v7, 0x5

    invoke-direct {v0, v1, v7, v2}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dB:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "WEBP"

    const/4 v8, 0x6

    invoke-direct {v0, v1, v8, v3}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dC:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    new-instance v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const-string v1, "UNKNOWN"

    const/4 v9, 0x7

    invoke-direct {v0, v1, v9, v3}, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;-><init>(Ljava/lang/String;IZ)V

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dD:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    const/16 v0, 0x8

    new-array v0, v0, [Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dw:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dx:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dy:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dz:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dA:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dB:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dC:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v8

    sget-object v1, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dD:Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    aput-object v1, v0, v9

    sput-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dE:[Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;IZ)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(Z)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput-boolean p3, p0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->hasAlpha:Z

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;
    .locals 1

    const-class v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    return-object p0
.end method

.method public static values()[Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;
    .locals 1

    sget-object v0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->dE:[Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    invoke-virtual {v0}, [Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;

    return-object v0
.end method


# virtual methods
.method public hasAlpha()Z
    .locals 1

    iget-boolean v0, p0, Lcom/bumptech/glide/load/ImageHeaderParser$ImageType;->hasAlpha:Z

    return v0
.end method
