package com.android.camera.data.data.config;

import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import com.android.camera.R;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.ComponentDataItem;
import com.android.camera2.CameraCapabilities;
import java.util.ArrayList;
import java.util.List;

public final class ComponentConfigRaw extends ComponentData {
    private SparseBooleanArray mIsClosed;

    public ComponentConfigRaw(DataItemConfig dataItemConfig) {
        super(dataItemConfig);
    }

    @NonNull
    public String getDefaultValue(int i) {
        return null;
    }

    public int getDisplayTitleString() {
        return 0;
    }

    public List<ComponentDataItem> getItems() {
        return this.mItems;
    }

    public String getKey(int i) {
        return "pref_camera_raw_key";
    }

    public boolean isClosed(int i) {
        SparseBooleanArray sparseBooleanArray = this.mIsClosed;
        if (sparseBooleanArray == null) {
            return false;
        }
        return sparseBooleanArray.get(i);
    }

    public boolean isSwitchOn(int i) {
        if (!isEmpty() && !isClosed(i)) {
            return this.mParentDataItem.getBoolean(getKey(i), false);
        }
        return false;
    }

    public List<ComponentDataItem> reInit(int i, int i2, CameraCapabilities cameraCapabilities) {
        List<ComponentDataItem> list = this.mItems;
        if (list == null) {
            this.mItems = new ArrayList();
        } else {
            list.clear();
        }
        if (i == 167 && cameraCapabilities.isSupportRaw() && DataRepository.dataItemFeature().va()) {
            this.mItems.add(new ComponentDataItem((int) R.drawable.ic_raw_off, (int) R.drawable.ic_raw_on, -1, "on"));
        }
        return this.mItems;
    }

    public void setClosed(boolean z, int i) {
        if (this.mIsClosed == null) {
            this.mIsClosed = new SparseBooleanArray();
        }
        this.mIsClosed.put(i, z);
    }

    public void setRaw(int i, boolean z) {
        if (!isEmpty()) {
            setClosed(false, i);
            this.mParentDataItem.editor().putBoolean(getKey(i), z).apply();
        }
    }
}
