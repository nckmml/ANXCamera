.class public Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;
.super Ljava/lang/Object;
.source "VEFaceAttribute.java"


# annotations
.annotation build Landroid/support/annotation/Keep;
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute$ExpressionType;,
        Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute$RacialType;
    }
.end annotation


# static fields
.field public static final EXPRESSION_NUM:I = 0x7

.field public static final RACIAL_NUM:I = 0x4


# instance fields
.field age:F

.field attractive:F

.field boyProb:F

.field expProbs:[F

.field expType:I

.field happyScore:F

.field racialProbs:[F

.field racialType:I


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getAge()F
    .locals 1

    iget v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->age:F

    return v0
.end method

.method public getAttractive()F
    .locals 1

    iget v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->attractive:F

    return v0
.end method

.method public getBoyProb()F
    .locals 1

    iget v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->boyProb:F

    return v0
.end method

.method public getExpProbs()[F
    .locals 1

    iget-object v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->expProbs:[F

    return-object v0
.end method

.method public getExpType()I
    .locals 1

    iget v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->expType:I

    return v0
.end method

.method public getHappyScore()F
    .locals 1

    iget v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->happyScore:F

    return v0
.end method

.method public getRacialType()I
    .locals 1

    iget v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->racialType:I

    return v0
.end method

.method public getRacialrobs()[F
    .locals 1

    iget-object v0, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->racialProbs:[F

    return-object v0
.end method

.method public setAge(F)V
    .locals 0

    iput p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->age:F

    return-void
.end method

.method public setAttractive(F)V
    .locals 0

    iput p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->attractive:F

    return-void
.end method

.method public setBoyProb(F)V
    .locals 0

    iput p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->boyProb:F

    return-void
.end method

.method public setExpProbs([F)V
    .locals 0

    iput-object p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->expProbs:[F

    return-void
.end method

.method public setExpType(I)V
    .locals 0

    iput p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->expType:I

    return-void
.end method

.method public setHappyScore(F)V
    .locals 0

    iput p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->happyScore:F

    return-void
.end method

.method public setRacialType(I)V
    .locals 0

    iput p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->racialType:I

    return-void
.end method

.method public setRacialrobs([F)V
    .locals 0

    iput-object p1, p0, Lcom/ss/android/vesdk/faceinfo/VEFaceAttribute;->racialProbs:[F

    return-void
.end method
