package com.android.camera.effect;

import com.android.camera.Util;
import com.android.camera.effect.renders.VideoRecorderRender;
import com.android.camera.log.Log;
import com.android.gallery3d.ui.BaseGLCanvas;

public class VideoRecorderCanvas extends BaseGLCanvas {
    public VideoRecorderCanvas() {
        initialize();
    }

    public void applyFilterId(int i) {
        ((VideoRecorderRender) this.mRenderGroup).setFilterId(i);
    }

    public void deleteProgram() {
        super.deleteProgram();
        this.mRenderCaches.destroy();
        this.mRenderGroup.destroy();
    }

    public void setSize(int i, int i2) {
        super.setSize(i, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("setSize: size=");
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        sb.append(" modelMatrix=");
        sb.append(Util.dumpMatrix(this.mState.getModelMatrix()));
        Log.d("VideoRecorderCanvas", sb.toString());
    }
}
