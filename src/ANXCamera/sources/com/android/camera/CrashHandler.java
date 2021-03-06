package com.android.camera;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.global.DataItemGlobal;
import com.android.camera.lib.compatibility.util.CompatibilityUtils;
import com.android.camera.log.Log;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.util.Locale;
import miui.external.Application;

public class CrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = "CameraFCHandler";
    private static CrashHandler sInstance = new CrashHandler();
    private WeakReference<Application> mContextRef;
    private UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    private void resetBrightnessInAutoMode(Context context) {
        String str = "Meet exception when resetBrightnessInAutoMode(): ";
        String str2 = TAG;
        DisplayManager displayManager = (DisplayManager) CameraAppImpl.getAndroidContext().getSystemService(DisplayManager.class);
        try {
            if (1 == System.getInt(context.getContentResolver(), "screen_brightness_mode")) {
                CompatibilityUtils.setTemporaryAutoBrightnessAdjustment(displayManager, 0.0f);
            }
        } catch (SettingNotFoundException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(e2);
            Log.e(str2, sb.toString());
        } catch (SecurityException e3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(e3);
            Log.e(str2, sb2.toString());
        }
    }

    public void init(Application application) {
        this.mContextRef = new WeakReference<>(application);
        if (this.mDefaultHandler == null) {
            this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        LocationManager.instance().recordLocation(false);
        DataItemGlobal dataItemGlobal = DataRepository.dataItemGlobal();
        String format = String.format(Locale.ENGLISH, "Camera FC, @Module = %d And @CameraId = %d", new Object[]{Integer.valueOf(dataItemGlobal.getCurrentMode()), Integer.valueOf(dataItemGlobal.getCurrentCameraId())});
        String str = TAG;
        Log.e(str, format);
        StringBuilder sb = new StringBuilder();
        sb.append("Camera FC, msg=");
        sb.append(th.getMessage());
        Log.e(str, sb.toString(), th);
        WeakReference<Application> weakReference = this.mContextRef;
        if (weakReference != null) {
            CameraSettings.setEdgeMode((Context) weakReference.get(), false);
            resetBrightnessInAutoMode((Context) this.mContextRef.get());
            Util.setBrightnessRampRate(-1);
            Util.setScreenEffect(false);
            this.mContextRef = null;
        }
        if (this.mDefaultHandler != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("mDefaultHandler=");
            sb2.append(this.mDefaultHandler);
            Log.e(str, sb2.toString());
            this.mDefaultHandler.uncaughtException(thread, th);
            this.mDefaultHandler = null;
        }
    }
}
