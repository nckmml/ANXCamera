.class public Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;
.super Landroid/support/v7/widget/RecyclerView$Adapter;
.source "ExtraRecyclerViewAdapter.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/support/v7/widget/RecyclerView$Adapter<",
        "Lcom/android/camera/fragment/CommonRecyclerViewHolder;",
        ">;",
        "Landroid/view/View$OnClickListener;"
    }
.end annotation


# instance fields
.field private mComponentData:Lcom/android/camera/data/data/ComponentData;

.field private mCurrentMode:I

.field private mCurrentValue:Ljava/lang/String;

.field private mItemWidth:I

.field private mManuallyListener:Lcom/android/camera/fragment/manually/ManuallyListener;


# direct methods
.method public constructor <init>(Lcom/android/camera/data/data/ComponentData;ILcom/android/camera/fragment/manually/ManuallyListener;I)V
    .locals 0

    invoke-direct {p0}, Landroid/support/v7/widget/RecyclerView$Adapter;-><init>()V

    iput-object p1, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mComponentData:Lcom/android/camera/data/data/ComponentData;

    iput p2, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentMode:I

    iput-object p3, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mManuallyListener:Lcom/android/camera/fragment/manually/ManuallyListener;

    iput p4, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mItemWidth:I

    iget p2, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentMode:I

    invoke-virtual {p1, p2}, Lcom/android/camera/data/data/ComponentData;->getComponentValue(I)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentValue:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method protected couldNewValueTakeEffect(Ljava/lang/String;)Z
    .locals 1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentValue:Ljava/lang/String;

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method public getItemCount()I
    .locals 1

    iget-object v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mComponentData:Lcom/android/camera/data/data/ComponentData;

    invoke-virtual {v0}, Lcom/android/camera/data/data/ComponentData;->getItems()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public getValuePosition()I
    .locals 4

    invoke-virtual {p0}, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->getItemCount()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_1

    iget-object v2, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentValue:Ljava/lang/String;

    iget-object v3, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mComponentData:Lcom/android/camera/data/data/ComponentData;

    invoke-virtual {v3}, Lcom/android/camera/data/data/ComponentData;->getItems()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/android/camera/data/data/ComponentDataItem;

    iget-object v3, v3, Lcom/android/camera/data/data/ComponentDataItem;->mValue:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    return v1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, -0x1

    return v0
.end method

.method public bridge synthetic onBindViewHolder(Landroid/support/v7/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    check-cast p1, Lcom/android/camera/fragment/CommonRecyclerViewHolder;

    invoke-virtual {p0, p1, p2}, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->onBindViewHolder(Lcom/android/camera/fragment/CommonRecyclerViewHolder;I)V

    return-void
.end method

.method public onBindViewHolder(Lcom/android/camera/fragment/CommonRecyclerViewHolder;I)V
    .locals 8

    iget-object v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mComponentData:Lcom/android/camera/data/data/ComponentData;

    invoke-virtual {v0}, Lcom/android/camera/data/data/ComponentData;->getItems()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/android/camera/data/data/ComponentDataItem;

    iget-object v0, p2, Lcom/android/camera/data/data/ComponentDataItem;->mValue:Ljava/lang/String;

    iget-object v1, p1, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v1, p1, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v1, v0}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    const v1, 0x7f0e00a4

    invoke-virtual {p1, v1}, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->getView(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    const v2, 0x7f0e00a3

    invoke-virtual {p1, v2}, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->getView(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Lcom/android/camera/ui/ColorImageView;

    iget v3, p2, Lcom/android/camera/data/data/ComponentDataItem;->mIconRes:I

    const/16 v4, 0x8

    const/4 v5, -0x1

    const/4 v6, 0x0

    if-eq v3, v5, :cond_0

    invoke-virtual {v2, v6}, Lcom/android/camera/ui/ColorImageView;->setVisibility(I)V

    iget v3, p2, Lcom/android/camera/data/data/ComponentDataItem;->mIconRes:I

    invoke-virtual {v2, v3}, Lcom/android/camera/ui/ColorImageView;->setImageResource(I)V

    iget-object v3, p1, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    const v7, 0x7f0a0169

    invoke-virtual {v3, v7}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v1, v6, v3}, Landroid/widget/TextView;->setTextSize(IF)V

    goto :goto_0

    :cond_0
    invoke-virtual {v2, v4}, Lcom/android/camera/ui/ColorImageView;->setVisibility(I)V

    iget-object v3, p1, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    const v7, 0x7f0a006e

    invoke-virtual {v3, v7}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v1, v6, v3}, Landroid/widget/TextView;->setTextSize(IF)V

    :goto_0
    iget v3, p2, Lcom/android/camera/data/data/ComponentDataItem;->mDisplayNameRes:I

    if-eq v3, v5, :cond_1

    invoke-virtual {v1, v6}, Landroid/widget/TextView;->setVisibility(I)V

    iget p2, p2, Lcom/android/camera/data/data/ComponentDataItem;->mDisplayNameRes:I

    invoke-virtual {v1, p2}, Landroid/widget/TextView;->setText(I)V

    iget-object p1, p1, Lcom/android/camera/fragment/CommonRecyclerViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {p1}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const p2, 0x7f0a008e

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result p1

    invoke-virtual {v2, v6, v6, v6, p1}, Lcom/android/camera/ui/ColorImageView;->setPadding(IIII)V

    goto :goto_1

    :cond_1
    invoke-virtual {v2, v6, v6, v6, v6}, Lcom/android/camera/ui/ColorImageView;->setPadding(IIII)V

    invoke-virtual {v1, v4}, Landroid/widget/TextView;->setVisibility(I)V

    :goto_1
    iget-object p1, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentValue:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    const p1, -0xff5701

    goto :goto_2

    :cond_2
    const p1, -0x4c000001

    :goto_2
    invoke-virtual {v2, p1}, Lcom/android/camera/ui/ColorImageView;->setColor(I)V

    invoke-virtual {v1, p1}, Landroid/widget/TextView;->setTextColor(I)V

    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 4

    invoke-static {}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getInstance()Lcom/android/camera/protocol/ModeCoordinatorImpl;

    move-result-object v0

    const/16 v1, 0xa1

    invoke-virtual {v0, v1}, Lcom/android/camera/protocol/ModeCoordinatorImpl;->getAttachProtocol(I)Lcom/android/camera/protocol/ModeProtocol$BaseProtocol;

    move-result-object v0

    check-cast v0, Lcom/android/camera/protocol/ModeProtocol$CameraAction;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/android/camera/protocol/ModeProtocol$CameraAction;->isDoingAction()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->couldNewValueTakeEffect(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mComponentData:Lcom/android/camera/data/data/ComponentData;

    iget v1, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentMode:I

    invoke-virtual {v0, v1, p1}, Lcom/android/camera/data/data/ComponentData;->setComponentValue(ILjava/lang/String;)V

    invoke-virtual {p0}, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->notifyDataSetChanged()V

    iget-object v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mManuallyListener:Lcom/android/camera/fragment/manually/ManuallyListener;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mManuallyListener:Lcom/android/camera/fragment/manually/ManuallyListener;

    iget-object v1, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mComponentData:Lcom/android/camera/data/data/ComponentData;

    iget-object v2, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentValue:Ljava/lang/String;

    const/4 v3, 0x0

    invoke-interface {v0, v1, v2, p1, v3}, Lcom/android/camera/fragment/manually/ManuallyListener;->onManuallyDataChanged(Lcom/android/camera/data/data/ComponentData;Ljava/lang/String;Ljava/lang/String;Z)V

    :cond_1
    iput-object p1, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mCurrentValue:Ljava/lang/String;

    :cond_2
    return-void
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroid/support/v7/widget/RecyclerView$ViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/android/camera/fragment/CommonRecyclerViewHolder;

    move-result-object p1

    return-object p1
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/android/camera/fragment/CommonRecyclerViewHolder;
    .locals 2

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-static {p2}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p2

    const v0, 0x7f04002d

    const/4 v1, 0x0

    invoke-virtual {p2, v0, p1, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p2

    iget v0, p0, Lcom/android/camera/fragment/manually/adapter/ExtraRecyclerViewAdapter;->mItemWidth:I

    iput v0, p2, Landroid/view/ViewGroup$LayoutParams;->width:I

    new-instance p2, Lcom/android/camera/fragment/CommonRecyclerViewHolder;

    invoke-direct {p2, p1}, Lcom/android/camera/fragment/CommonRecyclerViewHolder;-><init>(Landroid/view/View;)V

    return-object p2
.end method
