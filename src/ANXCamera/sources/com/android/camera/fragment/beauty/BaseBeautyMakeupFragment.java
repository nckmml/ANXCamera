package com.android.camera.fragment.beauty;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.camera.R;
import com.android.camera.ToastUtils;
import com.android.camera.Util;
import com.android.camera.data.data.TypeItem;
import com.android.camera.data.data.runing.ComponentRunningShine.ShineType;
import com.android.camera.fragment.DefaultItemAnimator;
import com.android.camera.fragment.RecyclerAdapterWrapper;
import com.android.camera.log.Log;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.MakeupProtocol;
import com.android.camera.protocol.ModeProtocol.MiBeautyProtocol;
import com.android.camera.ui.ColorImageView;
import java.util.List;

public abstract class BaseBeautyMakeupFragment extends BaseBeautyFragment {
    protected static final int EXTRA_CLEAR = 2;
    protected static final int EXTRA_NULL = -1;
    protected static final int EXTRA_RESET = 1;
    private static final String TAG = "BaseBeautyMakeup";
    protected OnItemClickListener mClickListener;
    protected int mFooterElement;
    protected int mHeaderCustomWidth;
    protected int mHeaderElement;
    private LinearLayout mHeaderRecyclerView;
    protected List<TypeItem> mItemList;
    private int mItemMargin;
    private int mItemWidth;
    /* access modifiers changed from: private */
    public long mLastClickTime;
    int mLastSelectedParam = -1;
    protected MyLayoutManager mLayoutManager;
    protected MakeupSingleCheckAdapter mMakeupAdapter;
    private RecyclerView mMakeupItemList;
    private boolean mNeedScroll;
    protected int mSelectedParam = 0;
    private int mTotalWidth;

    public class MyLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

        public MyLayoutManager(Context context) {
            super(context);
        }

        public boolean canScrollHorizontally() {
            return this.isScrollEnabled && super.canScrollHorizontally();
        }

        public void onLayoutChildren(Recycler recycler, State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e2) {
                e2.printStackTrace();
            }
        }

        public void setScrollEnabled(boolean z) {
            this.isScrollEnabled = z;
        }
    }

    private void animateView(View view) {
        view.clearAnimation();
        view.setRotation(0.0f);
        ViewCompat.animate(view).rotation(360.0f).setDuration(500).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                super.onAnimationEnd(view);
                view.setRotation(0.0f);
            }
        }).start();
    }

    private void calcItemWidthAndNeedScroll() {
        int i;
        int i2;
        this.mTotalWidth = getResources().getDisplayMetrics().widthPixels;
        if (this.mHeaderElement != -1) {
            i = this.mHeaderCustomWidth;
            if (i <= 0) {
                i = getResources().getDimensionPixelSize(R.dimen.beauty_header_width) + getResources().getDimensionPixelSize(R.dimen.beauty_divider_stroke);
            }
        } else {
            i = 0;
        }
        if (this.mFooterElement != -1) {
            i2 = getResources().getDimensionPixelSize(R.dimen.beauty_divider_stroke) + getResources().getDimensionPixelSize(R.dimen.beauty_header_width);
        } else {
            i2 = 0;
        }
        int size = this.mItemList.size();
        float integer = ((float) getResources().getInteger(R.integer.beauty_list_max_count)) + 0.5f;
        int i3 = this.mTotalWidth;
        int i4 = (int) (((float) (i3 - i)) / integer);
        int max = Math.max(((i3 - i) - i2) / size, i4);
        if (max == i4) {
            this.mNeedScroll = true;
        } else {
            this.mNeedScroll = false;
            max = (((this.mTotalWidth - (this.mItemMargin * 2)) - i) - i2) / size;
        }
        this.mItemWidth = max;
    }

    private View initAndGetFooterView() {
        if (this.mFooterElement == -1) {
            return null;
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.beauty_footer_layout, this.mMakeupItemList, false);
        ColorImageView colorImageView = (ColorImageView) inflate.findViewById(R.id.makeup_item_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.makeup_item_name);
        colorImageView.setColor(getResources().getColor(R.color.beautycamera_beauty_advanced_item_backgroud_normal));
        int i = this.mFooterElement;
        if (i == 1) {
            colorImageView.setImageResource(R.drawable.icon_beauty_reset);
            textView.setText(R.string.beauty_reset);
        } else if (i == 2) {
            colorImageView.setImageResource(R.drawable.icon_beauty_clear);
            textView.setText(R.string.beauty_clear);
        }
        inflate.setTag(Integer.valueOf(this.mFooterElement));
        inflate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseBeautyMakeupFragment.this.onExtraClick(view);
            }
        });
        return inflate;
    }

    private void initHeaderView() {
        if (this.mHeaderElement != -1) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.beauty_header_layout, this.mHeaderRecyclerView, false);
            ColorImageView colorImageView = (ColorImageView) inflate.findViewById(R.id.makeup_item_icon);
            TextView textView = (TextView) inflate.findViewById(R.id.makeup_item_name);
            colorImageView.setColor(getResources().getColor(R.color.beautycamera_beauty_advanced_item_backgroud_normal));
            int i = this.mHeaderElement;
            if (i == 1) {
                colorImageView.setImageResource(R.drawable.icon_beauty_reset);
                textView.setText(R.string.beauty_reset);
            } else if (i == 2) {
                colorImageView.setImageResource(R.drawable.icon_beauty_reset);
                textView.setText(R.string.face_beauty_close);
            }
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) inflate.findViewById(R.id.makeup_item).getLayoutParams();
            int i2 = this.mHeaderCustomWidth;
            if (i2 > 0) {
                marginLayoutParams.width = i2;
            }
            inflate.setTag(Integer.valueOf(this.mHeaderElement));
            inflate.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BaseBeautyMakeupFragment.this.onExtraClick(view);
                }
            });
            this.mHeaderRecyclerView.addView(inflate, 0);
        }
    }

    private final List<TypeItem> initItems() {
        MiBeautyProtocol miBeautyProtocol = (MiBeautyProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(194);
        if (miBeautyProtocol != null) {
            return miBeautyProtocol.getSupportedBeautyItems(getShineType());
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void notifyItemChanged(int i, int i2) {
        if (i > -1) {
            this.mMakeupAdapter.notifyItemChanged(i);
        }
        if (i2 > -1) {
            this.mMakeupAdapter.notifyItemChanged(i2);
        }
    }

    /* access modifiers changed from: private */
    public void onExtraClick(View view) {
        if (System.currentTimeMillis() - this.mLastClickTime < 1000) {
            Log.d(TAG, "onExtra: too quick!");
            return;
        }
        this.mLastClickTime = System.currentTimeMillis();
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue == 1) {
            animateView((ImageView) view.findViewById(R.id.makeup_item_icon));
            onResetClick();
        } else if (intValue == 2) {
            onClearClick();
        }
    }

    /* access modifiers changed from: private */
    public boolean scrollIfNeed(int i) {
        int i2 = (i == this.mLayoutManager.findFirstVisibleItemPosition() || i == this.mLayoutManager.findFirstCompletelyVisibleItemPosition()) ? Math.max(0, i - 1) : (i == this.mLayoutManager.findLastVisibleItemPosition() || i == this.mLayoutManager.findLastCompletelyVisibleItemPosition()) ? Math.min(i + 1, this.mLayoutManager.getItemCount() - 1) : i;
        if (i2 == i) {
            return false;
        }
        this.mLayoutManager.scrollToPosition(i2);
        return true;
    }

    private void setItemInCenter(int i) {
        this.mLayoutManager.scrollToPositionWithOffset(i, (this.mTotalWidth / 2) - (this.mItemWidth / 2));
    }

    /* access modifiers changed from: protected */
    public View getAnimateView() {
        return this.mHeaderRecyclerView;
    }

    /* access modifiers changed from: protected */
    public abstract String getClassString();

    /* access modifiers changed from: protected */
    public int getListItemMargin() {
        if (!isNeedScroll()) {
            return getResources().getDimensionPixelSize(R.dimen.beauty_item_margin);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    @ShineType
    public abstract String getShineType();

    /* access modifiers changed from: protected */
    public abstract void initExtraType();

    /* access modifiers changed from: protected */
    public OnItemClickListener initOnItemClickListener() {
        return new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BaseBeautyMakeupFragment.this.mLastClickTime = System.currentTimeMillis();
                BaseBeautyMakeupFragment.this.mSelectedParam = i;
                Object tag = view.getTag();
                if (tag != null && (tag instanceof TypeItem)) {
                    BaseBeautyMakeupFragment.this.onAdapterItemClick((TypeItem) tag);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        this.mHeaderRecyclerView = (LinearLayout) view.findViewById(R.id.header_recyclerView);
        this.mMakeupItemList = (RecyclerView) view.findViewById(R.id.makeup_item_list);
        this.mLayoutManager = new MyLayoutManager(getActivity());
        this.mLayoutManager.setOrientation(0);
        this.mLayoutManager.setScrollEnabled(true);
        this.mMakeupItemList.setLayoutManager(this.mLayoutManager);
        this.mMakeupItemList.setFocusable(false);
        this.mItemList = initItems();
        initExtraType();
        initHeaderView();
        calcItemWidthAndNeedScroll();
        if (!isNeedScroll()) {
            this.mLayoutManager.setScrollEnabled(false);
        }
        MakeupSingleCheckAdapter makeupSingleCheckAdapter = new MakeupSingleCheckAdapter(getActivity(), this.mItemList, 0, true, this.mItemWidth, this.mItemMargin);
        this.mMakeupAdapter = makeupSingleCheckAdapter;
        this.mClickListener = initOnItemClickListener();
        this.mMakeupAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BaseBeautyMakeupFragment baseBeautyMakeupFragment = BaseBeautyMakeupFragment.this;
                baseBeautyMakeupFragment.mLastSelectedParam = baseBeautyMakeupFragment.mSelectedParam;
                baseBeautyMakeupFragment.mClickListener.onItemClick(adapterView, view, i, j);
                if (BaseBeautyMakeupFragment.this.isNeedScroll()) {
                    BaseBeautyMakeupFragment baseBeautyMakeupFragment2 = BaseBeautyMakeupFragment.this;
                    if (baseBeautyMakeupFragment2.scrollIfNeed(baseBeautyMakeupFragment2.mSelectedParam)) {
                        BaseBeautyMakeupFragment baseBeautyMakeupFragment3 = BaseBeautyMakeupFragment.this;
                        baseBeautyMakeupFragment3.notifyItemChanged(baseBeautyMakeupFragment3.mLastSelectedParam, baseBeautyMakeupFragment3.mSelectedParam);
                    }
                }
            }
        });
        this.mMakeupAdapter.setSelectedPosition(this.mSelectedParam);
        RecyclerAdapterWrapper recyclerAdapterWrapper = new RecyclerAdapterWrapper(this.mMakeupAdapter);
        if (initAndGetFooterView() != null) {
            recyclerAdapterWrapper.addFooter(initAndGetFooterView());
        }
        this.mMakeupItemList.setAdapter(recyclerAdapterWrapper);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setChangeDuration(150);
        defaultItemAnimator.setMoveDuration(150);
        defaultItemAnimator.setAddDuration(150);
        this.mMakeupItemList.setItemAnimator(defaultItemAnimator);
        this.mMakeupAdapter.notifyDataSetChanged();
        setItemInCenter(this.mSelectedParam);
    }

    /* access modifiers changed from: protected */
    public boolean isNeedScroll() {
        return this.mNeedScroll;
    }

    /* access modifiers changed from: protected */
    public abstract void onAdapterItemClick(TypeItem typeItem);

    /* access modifiers changed from: protected */
    public abstract void onClearClick();

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_beauty_makeup, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public abstract void onResetClick();

    /* access modifiers changed from: protected */
    public void onViewCreatedAndVisibleToUser(boolean z) {
        super.onViewCreatedAndVisibleToUser(z);
        List<TypeItem> list = this.mItemList;
        if (list != null) {
            TypeItem typeItem = (TypeItem) list.get(this.mSelectedParam);
            MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
            if (makeupProtocol != null) {
                makeupProtocol.onMakeupItemSelected(typeItem.mKeyOrType, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void selectFirstItem() {
        this.mSelectedParam = 0;
        this.mMakeupAdapter.setSelectedPosition(this.mSelectedParam);
        this.mLayoutManager.scrollToPosition(this.mSelectedParam);
        MakeupProtocol makeupProtocol = (MakeupProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(180);
        if (makeupProtocol != null) {
            makeupProtocol.onMakeupItemSelected(((TypeItem) this.mItemList.get(0)).mKeyOrType, true);
        }
        this.mMakeupAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void setListPadding(RecyclerView recyclerView) {
        int i;
        if (recyclerView != null) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.beauty_model_recycler_padding_left);
            if (!isNeedScroll()) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.beauty_padding_left);
                i = getResources().getDimensionPixelSize(R.dimen.beauty_padding_right);
            } else {
                i = 0;
            }
            recyclerView.setPadding(dimensionPixelSize, 0, i, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void toast(String str) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.beauty_reset_toast_margin_bottom);
        if (!Util.sIsFullScreenNavBarHidden) {
            dimensionPixelSize -= Util.sNavigationBarHeight;
        }
        ToastUtils.showToast(getContext(), str, 80, 0, dimensionPixelSize - (getResources().getDimensionPixelSize(R.dimen.beauty_reset_toast_height) / 2));
    }
}
