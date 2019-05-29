package com.android.camera.fragment.mimoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import com.android.camera.R;
import com.android.camera.fragment.beauty.LinearLayoutManagerWrapper;
import com.android.camera.log.Log;
import com.arcsoft.avatar.AvatarConfig.ASAvatarConfigInfo;
import com.arcsoft.avatar.util.AvatarConfigUtils;
import com.arcsoft.avatar.util.LOG;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditLevelListAdapter extends Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public static final String TAG = EditLevelListAdapter.class.getSimpleName();
    private RecyclerView colorRecyleView;
    private AvatarConfigItemClick mAvatarConfigItemClick = new AvatarConfigItemClick() {
        public void onConfigItemClick(ASAvatarConfigInfo aSAvatarConfigInfo, boolean z) {
            String access$100 = EditLevelListAdapter.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onConfigItemClick, ASAvatarConfigInfo = ");
            sb.append(aSAvatarConfigInfo);
            Log.d(access$100, sb.toString());
            EditLevelListAdapter.this.mItfGvOnItemClickListener.notifyUIChanged();
            AvatarEngineManager.getInstance().setAllNeedUpdate(true, z);
            AvatarEngineManager.getInstance().queryAvatar().setConfig(aSAvatarConfigInfo);
            AvatarConfigUtils.updateConfigID(aSAvatarConfigInfo.configType, aSAvatarConfigInfo.configID, AvatarEngineManager.getInstance().getASAvatarConfigValue());
            EditLevelListAdapter.this.mRenderThread.setConfig(aSAvatarConfigInfo);
            if (!z) {
                return;
            }
            if (!EditLevelListAdapter.this.mRenderThread.getIsRendering()) {
                EditLevelListAdapter.this.mRenderThread.draw();
            } else {
                EditLevelListAdapter.this.mRenderThread.setStopRender(true);
            }
        }
    };
    private List<ColorListAdapter> mColorListAdapters;
    private Context mContext;
    /* access modifiers changed from: private */
    public ItfGvOnItemClickListener mItfGvOnItemClickListener;
    private volatile List<MimojiLevelBean> mLevelDatas;
    private MimojiLevelBean mMimojiLevelBean;
    private List<MimojiThumbnailAdapter> mMimojiThumbnailAdapters;
    /* access modifiers changed from: private */
    public MimojiThumbnailRenderThread mRenderThread;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        RecyclerView mColorRecycleView;
        GridView mThumbnailGV;
        TextView mTvSubTitle;

        public ViewHolder(View view) {
            super(view);
            this.mTvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
            this.mColorRecycleView = (RecyclerView) view.findViewById(R.id.color_select);
            this.mThumbnailGV = (GridView) view.findViewById(R.id.thumbnail_gride_view);
        }
    }

    EditLevelListAdapter(Context context, ItfGvOnItemClickListener itfGvOnItemClickListener) {
        this.mContext = context;
        this.mLevelDatas = Collections.synchronizedList(new ArrayList());
        this.mMimojiThumbnailAdapters = Collections.synchronizedList(new ArrayList());
        this.mColorListAdapters = Collections.synchronizedList(new ArrayList());
        this.mItfGvOnItemClickListener = itfGvOnItemClickListener;
    }

    /* access modifiers changed from: private */
    public void onGvItemClick(int i, int i2) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("outerPosition = ");
        sb.append(i);
        sb.append(" Select index = ");
        sb.append(i2);
        LOG.d(str, sb.toString());
        MimojiLevelBean mimojiLevelBean = (MimojiLevelBean) this.mLevelDatas.get(i);
        if (i2 < mimojiLevelBean.thumnails.size()) {
            ASAvatarConfigInfo aSAvatarConfigInfo = (ASAvatarConfigInfo) mimojiLevelBean.thumnails.get(i2);
            AvatarEngineManager.getInstance().setInnerConfigSelectIndex(mimojiLevelBean.configType, (float) i2);
            this.mAvatarConfigItemClick.onConfigItemClick(aSAvatarConfigInfo, false);
        }
    }

    private void showColor(ViewHolder viewHolder, MimojiLevelBean mimojiLevelBean) {
        this.colorRecyleView = viewHolder.mColorRecycleView;
        LinearLayoutManagerWrapper colorLayoutManagerMap = AvatarEngineManager.getInstance().getColorLayoutManagerMap(mimojiLevelBean.configType);
        if (colorLayoutManagerMap == null) {
            colorLayoutManagerMap = new LinearLayoutManagerWrapper(this.mContext, "color_select");
            colorLayoutManagerMap.setOrientation(0);
            AvatarEngineManager.getInstance().putColorLayoutManagerMap(mimojiLevelBean.configType, colorLayoutManagerMap);
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(" configType:");
        sb.append(mimojiLevelBean.configType);
        sb.append("colorRecyleView：");
        sb.append(this.colorRecyleView.hashCode());
        sb.append(" wrapper:");
        sb.append(colorLayoutManagerMap.hashCode());
        Log.i(str, sb.toString());
        this.colorRecyleView.setLayoutManager(colorLayoutManagerMap);
        ColorListAdapter colorListAdapter = new ColorListAdapter(this.mContext, this.mAvatarConfigItemClick);
        this.colorRecyleView.setAdapter(colorListAdapter);
        TextView textView = viewHolder.mTvSubTitle;
        AvatarEngineManager.getInstance();
        if (AvatarEngineManager.showConfigTypeName(mimojiLevelBean.configType)) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("topic:");
        sb2.append(mimojiLevelBean.configTypeName);
        sb2.append("----");
        sb2.append(mimojiLevelBean.configType);
        sb2.append("----");
        AvatarEngineManager.getInstance();
        sb2.append(AvatarEngineManager.showConfigTypeName(mimojiLevelBean.configType));
        Log.i(str2, sb2.toString());
        textView.setText(this.mMimojiLevelBean.configTypeName);
        if (AvatarEngineManager.getInstance().getColorType(mimojiLevelBean.configType) < 0) {
            this.colorRecyleView.setVisibility(8);
            return;
        }
        this.colorRecyleView.setVisibility(0);
        ArrayList colorConfigInfos = mimojiLevelBean.getColorConfigInfos();
        if (colorConfigInfos == null) {
            this.colorRecyleView.setVisibility(8);
            return;
        }
        if (colorConfigInfos.size() == 0) {
            this.colorRecyleView.setVisibility(8);
        } else if (colorConfigInfos.size() > 0) {
            this.colorRecyleView.setVisibility(0);
            colorListAdapter.updateData(colorConfigInfos);
            String str3 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("color show:");
            sb3.append(mimojiLevelBean.configTypeName);
            sb3.append("color size:");
            sb3.append(colorConfigInfos.size());
            Log.i(str3, sb3.toString());
            float innerConfigSelectIndex = AvatarEngineManager.getInstance().getInnerConfigSelectIndex(((ASAvatarConfigInfo) colorConfigInfos.get(0)).configType);
            int i = 0;
            for (int i2 = 0; i2 < colorConfigInfos.size(); i2++) {
                if (innerConfigSelectIndex == ((float) ((ASAvatarConfigInfo) colorConfigInfos.get(i2)).configID)) {
                    i = i2;
                }
            }
            colorLayoutManagerMap.scrollToPosition(i);
        }
    }

    public int getItemCount() {
        return this.mLevelDatas.size();
    }

    public void notifyThumbnailUpdate(int i, int i2, int i3) {
        if (i != AvatarEngineManager.getInstance().getSelectType()) {
            Log.d(TAG, "update wrong !!!!");
            return;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("notifyThumbnailUpdate.... index = ");
        sb.append(i2);
        sb.append(", position = ");
        sb.append(i3);
        Log.d(str, sb.toString());
        if (this.mLevelDatas.size() == 0) {
            Log.e(TAG, "mLevelDatas Exception !!!!");
            return;
        }
        this.mMimojiLevelBean = (MimojiLevelBean) this.mLevelDatas.get(i2);
        ((MimojiThumbnailAdapter) this.mMimojiThumbnailAdapters.get(i2)).addData((ASAvatarConfigInfo) this.mMimojiLevelBean.thumnails.get(i3));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        this.mMimojiLevelBean = (MimojiLevelBean) this.mLevelDatas.get(i);
        GridView gridView = viewHolder.mThumbnailGV;
        final MimojiThumbnailAdapter mimojiThumbnailAdapter = (MimojiThumbnailAdapter) this.mMimojiThumbnailAdapters.get(i);
        ArrayList<ASAvatarConfigInfo> arrayList = this.mMimojiLevelBean.thumnails;
        showColor(viewHolder, this.mMimojiLevelBean);
        gridView.setAdapter(mimojiThumbnailAdapter);
        MeatureViewHeightWeight.setGridViewHeightBasedOnChildren(this.mContext, gridView, arrayList == null ? 0 : arrayList.size());
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                EditLevelListAdapter.this.onGvItemClick(i, i);
                EditLevelListAdapter.this.updateSelectView(mimojiThumbnailAdapter, i, i);
            }
        });
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(this.mContext, R.layout.mimoji_edit_level_item, null));
    }

    public void refreshData(List<MimojiLevelBean> list, boolean z, boolean z2) {
        this.mLevelDatas.clear();
        this.mLevelDatas.addAll(list);
        this.mColorListAdapters.clear();
        int i = 0;
        if (z2) {
            while (i < list.size()) {
                if (z) {
                    ((MimojiThumbnailAdapter) this.mMimojiThumbnailAdapters.get(i)).refreshData(((MimojiLevelBean) list.get(i)).thumnails);
                }
                i++;
            }
        } else {
            this.mMimojiThumbnailAdapters.clear();
            while (i < list.size()) {
                MimojiThumbnailAdapter mimojiThumbnailAdapter = new MimojiThumbnailAdapter(this.mContext, ((MimojiLevelBean) list.get(i)).configType);
                this.mMimojiThumbnailAdapters.add(mimojiThumbnailAdapter);
                if (z) {
                    mimojiThumbnailAdapter.refreshData(((MimojiLevelBean) list.get(i)).thumnails);
                }
                i++;
            }
        }
        notifyDataSetChanged();
    }

    public void setRenderThread(MimojiThumbnailRenderThread mimojiThumbnailRenderThread) {
        this.mRenderThread = mimojiThumbnailRenderThread;
    }

    public void updateSelectView(MimojiThumbnailAdapter mimojiThumbnailAdapter, int i, int i2) {
        MimojiLevelBean mimojiLevelBean = (MimojiLevelBean) this.mLevelDatas.get(i);
        if (i2 < mimojiLevelBean.thumnails.size()) {
            String str = FragmentMimojiEdit.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("click Thumbnail configType:");
            sb.append(this.mMimojiLevelBean.configType);
            sb.append(" configName:");
            sb.append(this.mMimojiLevelBean.configTypeName);
            sb.append("configId :");
            sb.append(((ASAvatarConfigInfo) mimojiLevelBean.thumnails.get(i2)).configID);
            Log.i(str, sb.toString());
            mimojiThumbnailAdapter.setSelectItem(mimojiLevelBean.configType, ((ASAvatarConfigInfo) mimojiLevelBean.thumnails.get(i2)).configID);
            mimojiThumbnailAdapter.notifyDataSetChanged();
        }
    }
}
