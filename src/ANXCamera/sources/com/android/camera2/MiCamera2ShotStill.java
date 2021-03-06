package com.android.camera2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.camera.CameraSettings;
import com.android.camera.Util;
import com.android.camera.log.Log;
import com.android.camera.module.loader.camera2.Camera2DataContainer;
import com.android.camera2.Camera2Proxy.PictureCallback;
import com.ss.android.ttve.common.TEDefine;
import com.xiaomi.camera.base.PerformanceTracker;
import com.xiaomi.camera.core.ParallelCallback;
import com.xiaomi.camera.core.ParallelTaskData;

public class MiCamera2ShotStill extends MiCamera2Shot<ParallelTaskData> {
    /* access modifiers changed from: private */
    public static final String TAG = "MiCamera2ShotStill";
    /* access modifiers changed from: private */
    public TotalCaptureResult mCaptureResult;
    /* access modifiers changed from: private */
    public ParallelTaskData mCurrentParallelTaskData;
    private boolean mHasDepth;
    private boolean mIsIntent;
    private boolean mNeedCaptureResult;

    public MiCamera2ShotStill(MiCamera2 miCamera2) {
        super(miCamera2);
    }

    private void notifyResultData(ParallelTaskData parallelTaskData, @Nullable CaptureResult captureResult, @Nullable CameraCharacteristics cameraCharacteristics) {
        ParallelCallback parallelCallback = getParallelCallback();
        if (parallelCallback == null) {
            Log.w(TAG, "notifyResultData: null parallel callback");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.mCurrentParallelTaskData.setPreviewThumbnailHash(this.mPreviewThumbnailHash);
        parallelCallback.onParallelProcessFinish(parallelTaskData, captureResult, cameraCharacteristics);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("mJpegCallbackFinishTime = ");
        sb.append(currentTimeMillis2);
        sb.append("ms");
        Log.d(str, sb.toString());
    }

    /* access modifiers changed from: protected */
    public CaptureCallback generateCaptureCallback() {
        return new CaptureCallback() {
            public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
                String access$000 = MiCamera2ShotStill.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onCaptureCompleted: ");
                sb.append(totalCaptureResult.getFrameNumber());
                Log.d(access$000, sb.toString());
                if (MiCamera2ShotStill.this.mMiCamera.getSuperNight()) {
                    MiCamera2ShotStill.this.mMiCamera.setAWBLock(false);
                }
                MiCamera2ShotStill miCamera2ShotStill = MiCamera2ShotStill.this;
                miCamera2ShotStill.mMiCamera.onCapturePictureFinished(true, miCamera2ShotStill);
                MiCamera2ShotStill.this.mMiCamera.setCaptureEnable(true);
                MiCamera2ShotStill.this.mCaptureResult = totalCaptureResult;
            }

            public void onCaptureFailed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureFailure captureFailure) {
                super.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
                String access$000 = MiCamera2ShotStill.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onCaptureFailed: reason=");
                sb.append(captureFailure.getReason());
                Log.e(access$000, sb.toString());
                if (MiCamera2ShotStill.this.mMiCamera.getSuperNight()) {
                    MiCamera2ShotStill.this.mMiCamera.setAWBLock(false);
                }
                MiCamera2ShotStill miCamera2ShotStill = MiCamera2ShotStill.this;
                miCamera2ShotStill.mMiCamera.onCapturePictureFinished(false, miCamera2ShotStill);
                MiCamera2ShotStill.this.mMiCamera.setCaptureEnable(true);
            }

            public void onCaptureStarted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, long j, long j2) {
                super.onCaptureStarted(cameraCaptureSession, captureRequest, j, j2);
                if ((!CameraSettings.isSupportedZslShutter() || CameraSettings.isUltraPixelOn()) && !CameraSettings.getPlayToneOnCaptureStart()) {
                    PictureCallback pictureCallback = MiCamera2ShotStill.this.getPictureCallback();
                    if (pictureCallback != null) {
                        pictureCallback.onCaptureShutter(false);
                    } else {
                        Log.w(MiCamera2ShotStill.TAG, "onCaptureStarted: null picture callback");
                    }
                }
                if (0 == MiCamera2ShotStill.this.mCurrentParallelTaskData.getTimestamp()) {
                    MiCamera2ShotStill.this.mCurrentParallelTaskData.setTimestamp(j);
                }
                String access$000 = MiCamera2ShotStill.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onCaptureStarted: mCurrentParallelTaskData: ");
                sb.append(MiCamera2ShotStill.this.mCurrentParallelTaskData.getTimestamp());
                Log.d(access$000, sb.toString());
            }
        };
    }

    /* access modifiers changed from: protected */
    public Builder generateRequestBuilder() throws CameraAccessException, IllegalStateException {
        Builder createCaptureRequest = this.mMiCamera.getCameraDevice().createCaptureRequest(2);
        ImageReader photoImageReader = this.mMiCamera.getPhotoImageReader();
        createCaptureRequest.addTarget(photoImageReader.getSurface());
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("size=");
        sb.append(photoImageReader.getWidth());
        sb.append("x");
        sb.append(photoImageReader.getHeight());
        Log.d(str, sb.toString());
        if (!isInQcfaMode() || Camera2DataContainer.getInstance().getBokehFrontCameraId() == this.mMiCamera.getId()) {
            createCaptureRequest.addTarget(this.mMiCamera.getPreviewSurface());
        }
        if (this.mMiCamera.isConfigRawStream()) {
            createCaptureRequest.addTarget(this.mMiCamera.getRawSurface());
        }
        if (this.mHasDepth) {
            createCaptureRequest.addTarget(this.mMiCamera.getDepthImageReader().getSurface());
            createCaptureRequest.addTarget(this.mMiCamera.getPortraitRawImageReader().getSurface());
        }
        createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, (Integer) this.mMiCamera.getPreviewRequestBuilder().get(CaptureRequest.CONTROL_AF_MODE));
        this.mMiCamera.applySettingsForCapture(createCaptureRequest, 3);
        if (this.mMiCamera.useLegacyFlashStrategy() && this.mMiCamera.isNeedFlashOn()) {
            this.mMiCamera.pausePreview();
        }
        return createCaptureRequest;
    }

    /* access modifiers changed from: protected */
    public long getTimeStamp() {
        ParallelTaskData parallelTaskData = this.mCurrentParallelTaskData;
        if (parallelTaskData == null) {
            return 0;
        }
        return parallelTaskData.getTimestamp();
    }

    /* access modifiers changed from: protected */
    public void notifyResultData(ParallelTaskData parallelTaskData) {
        notifyResultData(parallelTaskData, null, null);
    }

    /* access modifiers changed from: protected */
    public void onImageReceived(Image image, int i) {
        PictureCallback pictureCallback = getPictureCallback();
        if (pictureCallback != null) {
            ParallelTaskData parallelTaskData = this.mCurrentParallelTaskData;
            if (parallelTaskData != null) {
                if (0 == parallelTaskData.getTimestamp()) {
                    Log.w(TAG, "onImageReceived: image arrived first");
                    this.mCurrentParallelTaskData.setTimestamp(image.getTimestamp());
                }
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onImageReceived mCurrentParallelTaskData timestamp:");
                sb.append(this.mCurrentParallelTaskData.getTimestamp());
                sb.append(" image timestamp:");
                sb.append(image.getTimestamp());
                Log.d(str, sb.toString());
                byte[] firstPlane = Util.getFirstPlane(image);
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("onImageReceived: dataLen=");
                sb2.append(firstPlane == null ? TEDefine.FACE_BEAUTY_NULL : Integer.valueOf(firstPlane.length));
                sb2.append(" resultType = ");
                sb2.append(i);
                sb2.append(" timeStamp=");
                sb2.append(image.getTimestamp());
                sb2.append(" holder=");
                sb2.append(hashCode());
                Log.d(str2, sb2.toString());
                image.close();
                this.mCurrentParallelTaskData.fillJpegData(firstPlane, i);
                boolean z = this.mNeedCaptureResult ? this.mCurrentParallelTaskData.isJpegDataReady() && this.mCaptureResult != null : this.mCurrentParallelTaskData.isJpegDataReady();
                if (z) {
                    if (this.mIsIntent) {
                        notifyResultData(this.mCurrentParallelTaskData);
                        pictureCallback.onPictureTakenFinished(true);
                    } else {
                        pictureCallback.onPictureTakenFinished(true);
                        notifyResultData(this.mCurrentParallelTaskData, this.mCaptureResult, this.mMiCamera.getCapabilities().getCameraCharacteristics());
                    }
                }
                return;
            }
        }
        String str3 = TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("onImageReceived: something wrong happened when image received: callback = ");
        sb3.append(pictureCallback);
        sb3.append(" mCurrentParallelTaskData = ");
        sb3.append(this.mCurrentParallelTaskData);
        Log.w(str3, sb3.toString());
        image.close();
    }

    /* access modifiers changed from: protected */
    public void prepare() {
        if (this.mMiCamera.getSuperNight()) {
            this.mMiCamera.setAWBLock(true);
        }
        int shotType = this.mMiCamera.getCameraConfigs().getShotType();
        if (shotType == -3) {
            this.mHasDepth = true;
            this.mIsIntent = true;
        } else if (shotType == -2) {
            this.mIsIntent = true;
        } else if (shotType == 1) {
            this.mNeedCaptureResult = true;
        } else if (shotType == 2) {
            this.mHasDepth = true;
        }
    }

    /* access modifiers changed from: protected */
    public void startSessionCapture() {
        try {
            this.mCurrentParallelTaskData = generateParallelTaskData(0);
            if (this.mCurrentParallelTaskData == null) {
                Log.w(TAG, "startSessionCapture: null task data");
                return;
            }
            this.mCurrentParallelTaskData.setShot2Gallery(this.mMiCamera.getCameraConfigs().isShot2Gallery());
            CaptureCallback generateCaptureCallback = generateCaptureCallback();
            Builder generateRequestBuilder = generateRequestBuilder();
            PerformanceTracker.trackPictureCapture(0);
            this.mMiCamera.getCaptureSession().capture(generateRequestBuilder.build(), generateCaptureCallback, this.mCameraHandler);
        } catch (CameraAccessException e2) {
            e2.printStackTrace();
            Log.e(TAG, "Cannot capture a still picture");
            this.mMiCamera.notifyOnError(e2.getReason());
        } catch (IllegalStateException e3) {
            Log.e(TAG, "Failed to capture a still picture, IllegalState", e3);
            this.mMiCamera.notifyOnError(256);
        }
    }
}
