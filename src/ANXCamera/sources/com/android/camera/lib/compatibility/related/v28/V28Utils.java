package com.android.camera.lib.compatibility.related.v28;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.IPackageInstallObserver2.Stub;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession.StateCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.android.camera.lib.compatibility.util.CompatibilityUtils.PackageInstallerListener;
import java.util.List;
import java.util.concurrent.Executor;

@TargetApi(28)
public class V28Utils {
    private static final String TAG = "V28Utils";

    public static Builder constructCaptureRequestBuilder(CameraMetadataNative cameraMetadataNative, boolean z, int i, CaptureRequest captureRequest) {
        Builder builder = new Builder(cameraMetadataNative, z, i, captureRequest.getLogicalCameraId(), null);
        return builder;
    }

    public static void createCaptureSessionWithSessionConfiguration(CameraDevice cameraDevice, int i, InputConfiguration inputConfiguration, List<OutputConfiguration> list, CaptureRequest captureRequest, StateCallback stateCallback, final Handler handler) throws CameraAccessException {
        SessionConfiguration sessionConfiguration = new SessionConfiguration(i, list, handler == null ? null : new Executor() {
            public void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, stateCallback);
        if (inputConfiguration != null) {
            sessionConfiguration.setInputConfiguration(inputConfiguration);
        }
        sessionConfiguration.setSessionParameters(captureRequest);
        cameraDevice.createCaptureSession(sessionConfiguration);
    }

    public static StreamConfigurationMap createStreamConfigMap(List<StreamConfiguration> list, CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamConfigurationMap = new StreamConfigurationMap((StreamConfiguration[]) list.toArray(new StreamConfiguration[0]), (StreamConfigurationDuration[]) cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MIN_FRAME_DURATIONS), (StreamConfigurationDuration[]) cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_STALL_DURATIONS), (StreamConfiguration[]) cameraCharacteristics.get(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STREAM_CONFIGURATIONS), (StreamConfigurationDuration[]) cameraCharacteristics.get(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_MIN_FRAME_DURATIONS), (StreamConfigurationDuration[]) cameraCharacteristics.get(CameraCharacteristics.DEPTH_AVAILABLE_DEPTH_STALL_DURATIONS), (HighSpeedVideoConfiguration[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_HIGH_SPEED_VIDEO_CONFIGURATIONS), (ReprocessFormatsMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_INPUT_OUTPUT_FORMATS_MAP), true);
        return streamConfigurationMap;
    }

    public static String getInstallMethodDescription() {
        return "(Landroid/content/Context;Ljava/lang/String;Landroid/content/pm/IPackageInstallObserver2;I)Z";
    }

    public static Object getPackageInstallObserver(final PackageInstallerListener packageInstallerListener) {
        return new Stub() {
            public void onPackageInstalled(String str, int i, String str2, Bundle bundle) throws RemoteException {
                StringBuilder sb = new StringBuilder();
                sb.append("packageInstalled: packageName=");
                sb.append(str);
                sb.append(" returnCode=");
                sb.append(i);
                sb.append(" msg=");
                sb.append(str2);
                Log.d(V28Utils.TAG, sb.toString());
                PackageInstallerListener packageInstallerListener = PackageInstallerListener.this;
                if (packageInstallerListener != null) {
                    boolean z = true;
                    if (i != 1) {
                        z = false;
                    }
                    packageInstallerListener.onPackageInstalled(str, z);
                }
            }

            public void onUserActionRequired(Intent intent) {
            }
        };
    }

    public static void setCutoutModeShortEdges(Window window) {
        LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
    }

    public static void setPhysicalCameraId(OutputConfiguration outputConfiguration, String str) {
        outputConfiguration.setPhysicalCameraId(str);
    }

    public static void setTemporaryAutoBrightnessAdjustment(DisplayManager displayManager, float f2) {
        displayManager.setTemporaryAutoBrightnessAdjustment(f2);
    }
}
