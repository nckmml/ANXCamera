package com.android.camera.effect.renders;

import android.opengl.GLES20;
import com.android.camera.effect.ShaderUtil;
import com.android.camera.effect.draw_mode.DrawAttribute;
import com.android.camera.effect.draw_mode.DrawBasicTexAttribute;
import com.android.camera.effect.draw_mode.DrawIntTexAttribute;
import com.android.camera.log.Log;
import com.android.gallery3d.ui.BasicTexture;
import com.android.gallery3d.ui.GLCanvas;

public abstract class PixelEffectRender extends ShaderRender {
    private static final String TAG = "PixelEffectRender";
    private static final float[] TEXTURES = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private static final float[] VERTICES = {0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f};

    public PixelEffectRender(GLCanvas gLCanvas) {
        super(gLCanvas);
    }

    public PixelEffectRender(GLCanvas gLCanvas, int i) {
        super(gLCanvas, i);
    }

    /* access modifiers changed from: protected */
    public void bindExtraTexture() {
    }

    public boolean draw(DrawAttribute drawAttribute) {
        if (!isAttriSupported(drawAttribute.getTarget())) {
            return false;
        }
        int target = drawAttribute.getTarget();
        if (target == 5) {
            DrawBasicTexAttribute drawBasicTexAttribute = (DrawBasicTexAttribute) drawAttribute;
            drawTexture(drawBasicTexAttribute.mBasicTexture, (float) drawBasicTexAttribute.mX, (float) drawBasicTexAttribute.mY, (float) drawBasicTexAttribute.mWidth, (float) drawBasicTexAttribute.mHeight, drawBasicTexAttribute.mIsSnapshot);
        } else if (target == 6) {
            DrawIntTexAttribute drawIntTexAttribute = (DrawIntTexAttribute) drawAttribute;
            drawTexture(drawIntTexAttribute.mTexId, (float) drawIntTexAttribute.mX, (float) drawIntTexAttribute.mY, (float) drawIntTexAttribute.mWidth, (float) drawIntTexAttribute.mHeight, drawIntTexAttribute.mIsSnapshot);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void drawTexture(int i, float f2, float f3, float f4, float f5, boolean z) {
        GLES20.glUseProgram(this.mProgram);
        bindTexture(i, 33984);
        bindExtraTexture();
        updateViewport();
        setBlendEnabled(false);
        this.mGLCanvas.getState().pushState();
        this.mGLCanvas.getState().translate(f2, f3, 0.0f);
        this.mGLCanvas.getState().scale(f4, f5, 1.0f);
        initShaderValue(z);
        GLES20.glDrawArrays(5, 0, 4);
        this.mGLCanvas.getState().popState();
    }

    /* access modifiers changed from: protected */
    public void drawTexture(BasicTexture basicTexture, float f2, float f3, float f4, float f5, boolean z) {
        GLES20.glUseProgram(this.mProgram);
        if (!basicTexture.onBind(this.mGLCanvas)) {
            StringBuilder sb = new StringBuilder();
            sb.append("drawTexture: fail bind texture ");
            sb.append(basicTexture.getId());
            Log.e(TAG, sb.toString());
        } else if (bindTexture(basicTexture, 33984)) {
            bindExtraTexture();
            updateViewport();
            setBlendEnabled(false);
            this.mGLCanvas.getState().pushState();
            this.mGLCanvas.getState().translate(f2, f3, 0.0f);
            this.mGLCanvas.getState().scale(f4, f5, 1.0f);
            initShaderValue(z);
            GLES20.glDrawArrays(5, 0, 4);
            this.mGLCanvas.getState().popState();
        }
    }

    /* access modifiers changed from: protected */
    public void initShader() {
        this.mProgram = ShaderUtil.createProgram(getVertexShaderString(), getFragShaderString());
        int i = this.mProgram;
        if (i != 0) {
            GLES20.glUseProgram(i);
            this.mUniformMVPMatrixH = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
            this.mUniformSTMatrixH = GLES20.glGetUniformLocation(this.mProgram, "uSTMatrix");
            this.mUniformTextureH = GLES20.glGetUniformLocation(this.mProgram, "sTexture");
            this.mAttributePositionH = GLES20.glGetAttribLocation(this.mProgram, "aPosition");
            this.mAttributeTexCoorH = GLES20.glGetAttribLocation(this.mProgram, "aTexCoord");
            this.mUniformAlphaH = GLES20.glGetUniformLocation(this.mProgram, "uAlpha");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass());
        sb.append(": mProgram = 0");
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void initShaderValue(boolean z) {
        GLES20.glVertexAttribPointer(this.mAttributePositionH, 2, 5126, false, 8, this.mVertexBuffer);
        GLES20.glVertexAttribPointer(this.mAttributeTexCoorH, 2, 5126, false, 8, this.mTexCoorBuffer);
        GLES20.glEnableVertexAttribArray(this.mAttributePositionH);
        GLES20.glEnableVertexAttribArray(this.mAttributeTexCoorH);
        GLES20.glUniformMatrix4fv(this.mUniformMVPMatrixH, 1, false, this.mGLCanvas.getState().getFinalMatrix(), 0);
        GLES20.glUniformMatrix4fv(this.mUniformSTMatrixH, 1, false, this.mGLCanvas.getState().getTexMatrix(), 0);
        GLES20.glUniform1i(this.mUniformTextureH, 0);
        GLES20.glUniform1f(this.mUniformAlphaH, z ? 1.0f : this.mGLCanvas.getState().getAlpha());
    }

    /* access modifiers changed from: protected */
    public void initSupportAttriList() {
        this.mAttriSupportedList.add(Integer.valueOf(5));
        this.mAttriSupportedList.add(Integer.valueOf(6));
    }

    /* access modifiers changed from: protected */
    public void initVertexData() {
        this.mVertexBuffer = ShaderRender.allocateByteBuffer((VERTICES.length * 32) / 8).asFloatBuffer();
        this.mVertexBuffer.put(VERTICES);
        this.mVertexBuffer.position(0);
        this.mTexCoorBuffer = ShaderRender.allocateByteBuffer((TEXTURES.length * 32) / 8).asFloatBuffer();
        this.mTexCoorBuffer.put(TEXTURES);
        this.mTexCoorBuffer.position(0);
    }
}
