.class public Lcom/android/camera/constant/MiCaptureResult;
.super Ljava/lang/Object;
.source "MiCaptureResult.java"


# static fields
.field public static final REMOSAIC_DETECTED:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field public static final SENSOR_HDR_ENABLE:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "Ljava/lang/Byte;",
            ">;"
        }
    .end annotation
.end field

.field public static final STATISTICS_FACE_AGE:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "[F>;"
        }
    .end annotation
.end field

.field public static final STATISTICS_FACE_FACESCORE:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "[F>;"
        }
    .end annotation
.end field

.field public static final STATISTICS_FACE_GENDER:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "[F>;"
        }
    .end annotation
.end field

.field public static final STATISTICS_FACE_INFO:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "[B>;"
        }
    .end annotation
.end field

.field public static final STATISTICS_FACE_PROP:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "[F>;"
        }
    .end annotation
.end field

.field public static final VIDEO_RECORD_STATE:Landroid/hardware/camera2/CaptureResult$Key;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/hardware/camera2/CaptureResult$Key<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field public static final VIDEO_RECORD_STATE_IDLE:I = 0x2

.field public static final VIDEO_RECORD_STATE_PROCESS:I = 0x1


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.video.recordState"

    const-class v2, Ljava/lang/Integer;

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->VIDEO_RECORD_STATE:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.faceAnalyzeResult.result"

    const-class v2, [B

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->STATISTICS_FACE_INFO:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.faceAnalyzeResult.age"

    const-class v2, [F

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->STATISTICS_FACE_AGE:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.faceAnalyzeResult.gender"

    const-class v2, [F

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->STATISTICS_FACE_GENDER:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.faceAnalyzeResult.score"

    const-class v2, [F

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->STATISTICS_FACE_FACESCORE:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.faceAnalyzeResult.prop"

    const-class v2, [F

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->STATISTICS_FACE_PROP:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "org.quic.camera2.statsconfigs.AECIsInsensorHDR"

    const-class v2, Ljava/lang/Byte;

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->SENSOR_HDR_ENABLE:Landroid/hardware/camera2/CaptureResult$Key;

    new-instance v0, Landroid/hardware/camera2/CaptureResult$Key;

    const-string v1, "xiaomi.remosaic.detected"

    sget-object v2, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    invoke-direct {v0, v1, v2}, Landroid/hardware/camera2/CaptureResult$Key;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    sput-object v0, Lcom/android/camera/constant/MiCaptureResult;->REMOSAIC_DETECTED:Landroid/hardware/camera2/CaptureResult$Key;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
