package com.android.camera.fragment.top;

import android.animation.ObjectAnimator;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.android.camera.ActivityBase;
import com.android.camera.Camera;
import com.android.camera.CameraAppImpl;
import com.android.camera.CameraSettings;
import com.android.camera.R;
import com.android.camera.ToastUtils;
import com.android.camera.Util;
import com.android.camera.animation.type.AlphaInOnSubscribe;
import com.android.camera.animation.type.AlphaOutOnSubscribe;
import com.android.camera.data.DataRepository;
import com.android.camera.data.data.ComponentData;
import com.android.camera.data.data.config.ComponentConfigFlash;
import com.android.camera.data.data.config.ComponentConfigHdr;
import com.android.camera.data.data.config.ComponentConfigMeter;
import com.android.camera.data.data.config.ComponentConfigUltraWide;
import com.android.camera.data.data.config.ComponentRunningUltraPixel;
import com.android.camera.data.data.config.DataItemConfig;
import com.android.camera.data.data.config.SupportedConfigFactory;
import com.android.camera.data.data.config.SupportedConfigs;
import com.android.camera.data.data.config.TopConfigItem;
import com.android.camera.effect.FilterInfo;
import com.android.camera.fragment.BaseFragment;
import com.android.camera.fragment.FragmentUtils;
import com.android.camera.fragment.beauty.LinearLayoutManagerWrapper;
import com.android.camera.fragment.music.FragmentLiveMusic;
import com.android.camera.fragment.top.ExpandAdapter.ExpandListener;
import com.android.camera.log.Log;
import com.android.camera.module.impl.component.MimojiStatusManager;
import com.android.camera.protocol.ModeCoordinatorImpl;
import com.android.camera.protocol.ModeProtocol.ActionProcessing;
import com.android.camera.protocol.ModeProtocol.BaseDelegate;
import com.android.camera.protocol.ModeProtocol.BottomPopupTips;
import com.android.camera.protocol.ModeProtocol.CameraAction;
import com.android.camera.protocol.ModeProtocol.CameraClickObservable;
import com.android.camera.protocol.ModeProtocol.CameraModuleSpecial;
import com.android.camera.protocol.ModeProtocol.ConfigChanges;
import com.android.camera.protocol.ModeProtocol.HandleBackTrace;
import com.android.camera.protocol.ModeProtocol.HandleBeautyRecording;
import com.android.camera.protocol.ModeProtocol.ModeCoordinator;
import com.android.camera.protocol.ModeProtocol.TopAlert;
import com.android.camera.protocol.ModeProtocol.TopConfigProtocol;
import com.android.camera.statistic.CameraStatUtil;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import miui.view.animation.CubicEaseInOutInterpolator;

public class FragmentTopConfig extends BaseFragment implements OnClickListener, ExpandListener, TopAlert, HandleBackTrace, HandleBeautyRecording {
    private static final int EXPAND_STATE_CENTER = 2;
    private static final int EXPAND_STATE_LEFT = 0;
    private static final int EXPAND_STATE_LEFT_FROM_SIBLING = 1;
    private static final int EXPAND_STATE_RIGHT = 4;
    private static final int EXPAND_STATE_RIGHT_FROM_SIBLING = 3;
    private static final String TAG = "FragmentTopConfig";
    public static final int TIP_HINT_DURATION_2S = 2000;
    private int[] mAiSceneResources;
    private int[] mAutoZoomResources;
    private List<ImageView> mConfigViews;
    private int mCurrentAiSceneLevel;
    private SparseBooleanArray mDisabledFunctionMenu;
    private int mDisplayRectTopMargin;
    private RecyclerView mExpandView;
    private int[] mFilterResources;
    private FragmentTopAlert mFragmentTopAlert;
    private FragmentTopConfigExtra mFragmentTopConfigExtra;
    private boolean mIsRTL;
    private boolean mIsShowExtraMenu;
    private boolean mIsShowTopLyingDirectHint;
    private LastAnimationComponent mLastAnimationComponent;
    private int[] mLightingResource;
    private int[] mLiveMusicSelectResources;
    private ObjectAnimator mLiveShotAnimator;
    private int[] mLiveShotResource;
    private int[] mMacroResources;
    private View mMimojiCreateLayout;
    private int[] mSuperEISResources;
    private SupportedConfigs mSupportedConfigs;
    private View mTopConfigMenu;
    private int mTopDrawableWidth;
    private ViewGroup mTopExtraParent;
    private int mTotalWidth;
    private int[] mUltraPixelPhotographyIconResources;
    private int[] mUltraPixelPhotographyTipResources;
    private String[] mUltraPixelPhotographyTipString;
    private int[] mUltraPixelPortraitResources;
    private int[] mUltraWideBokehResources;
    private int[] mUltraWideResource;
    private int[] mVideoBokehResource;
    private boolean mVideoRecordingStarted;
    private int mViewPadding;

    private void alertHDR(int i, boolean z, boolean z2, boolean z3) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            if (z3) {
                if (i != 0) {
                    this.mLastAnimationComponent.reverse(true);
                } else if (z2) {
                    ImageView topImage = getTopImage(194);
                    if (topImage != null) {
                        topImage.performClick();
                    }
                }
            }
            topAlert.alertHDR(i, z);
        }
    }

    private void alertTopMusicHint(int i, String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.alertMusicTip(i, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0116  */
    private void expandExtra(ComponentData componentData, View view, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int right;
        int i8;
        if (!this.mLastAnimationComponent.reverse(true)) {
            ExpandAdapter expandAdapter = new ExpandAdapter(componentData, this);
            if (i == 214) {
                i3 = getResources().getDimensionPixelSize(R.dimen.expanded_meter_text_item_width);
                i2 = componentData.getItems().size();
            } else {
                i3 = getResources().getDimensionPixelSize(R.dimen.expanded_text_item_width);
                i2 = componentData.getItems().size();
            }
            int i9 = i3 * i2;
            this.mExpandView.getLayoutParams().width = i9;
            this.mExpandView.setAdapter(expandAdapter);
            int i10 = 0;
            this.mExpandView.setVisibility(0);
            this.mExpandView.setTag(Integer.valueOf(i));
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.panel_imageview_button_padding_width) * 3;
            int i11 = ((LayoutParams) view.getLayoutParams()).gravity == 8388611 ? 1 : 0;
            int i12 = i11 ^ 1;
            if (this.mIsRTL) {
                i12 = 4 - i12;
            }
            if (i12 == 0) {
                this.mLastAnimationComponent.setExpandGravity(5);
                right = view.getRight() - dimensionPixelSize;
                i7 = view.getWidth();
            } else if (i12 != 1) {
                if (i12 == 2) {
                    i6 = getView().getWidth() - view.getWidth();
                    i8 = view.getLeft();
                } else if (i12 == 3) {
                    i6 = getView().getWidth() - view.getWidth();
                    i8 = view.getLeft();
                } else if (i12 != 4) {
                    i4 = 0;
                    i6 = 0;
                    i5 = 0;
                    LastAnimationComponent lastAnimationComponent = this.mLastAnimationComponent;
                    lastAnimationComponent.mRecyclerView = this.mExpandView;
                    lastAnimationComponent.mReverseLeft = view.getLeft();
                    LastAnimationComponent lastAnimationComponent2 = this.mLastAnimationComponent;
                    lastAnimationComponent2.mReverseRecyclerViewLeft = i4;
                    lastAnimationComponent2.hideOtherViews(i, this.mConfigViews);
                    if (i11 == 0) {
                        LastAnimationComponent lastAnimationComponent3 = this.mLastAnimationComponent;
                        lastAnimationComponent3.mAnchorView = view;
                        lastAnimationComponent3.translateAnchorView(i6 - view.getLeft());
                    }
                    if (this.mIsRTL) {
                        i10 = getView().getWidth() - i9;
                    }
                    this.mLastAnimationComponent.showExtraView(i4 - i10, i5 - i10);
                } else {
                    this.mLastAnimationComponent.setExpandGravity(3);
                    i6 = getView().getWidth() - view.getWidth();
                    i4 = dimensionPixelSize + (view.getLeft() - i9);
                    i5 = i6 - i9;
                    LastAnimationComponent lastAnimationComponent4 = this.mLastAnimationComponent;
                    lastAnimationComponent4.mRecyclerView = this.mExpandView;
                    lastAnimationComponent4.mReverseLeft = view.getLeft();
                    LastAnimationComponent lastAnimationComponent22 = this.mLastAnimationComponent;
                    lastAnimationComponent22.mReverseRecyclerViewLeft = i4;
                    lastAnimationComponent22.hideOtherViews(i, this.mConfigViews);
                    if (i11 == 0) {
                    }
                    if (this.mIsRTL) {
                    }
                    this.mLastAnimationComponent.showExtraView(i4 - i10, i5 - i10);
                }
                i4 = (i8 - i9) - dimensionPixelSize;
                i5 = i6 - i9;
                LastAnimationComponent lastAnimationComponent42 = this.mLastAnimationComponent;
                lastAnimationComponent42.mRecyclerView = this.mExpandView;
                lastAnimationComponent42.mReverseLeft = view.getLeft();
                LastAnimationComponent lastAnimationComponent222 = this.mLastAnimationComponent;
                lastAnimationComponent222.mReverseRecyclerViewLeft = i4;
                lastAnimationComponent222.hideOtherViews(i, this.mConfigViews);
                if (i11 == 0) {
                }
                if (this.mIsRTL) {
                }
                this.mLastAnimationComponent.showExtraView(i4 - i10, i5 - i10);
            } else {
                this.mLastAnimationComponent.setExpandGravity(3);
                right = view.getRight();
                i7 = view.getWidth();
            }
            i5 = i7 + 0;
            i6 = 0;
            LastAnimationComponent lastAnimationComponent422 = this.mLastAnimationComponent;
            lastAnimationComponent422.mRecyclerView = this.mExpandView;
            lastAnimationComponent422.mReverseLeft = view.getLeft();
            LastAnimationComponent lastAnimationComponent2222 = this.mLastAnimationComponent;
            lastAnimationComponent2222.mReverseRecyclerViewLeft = i4;
            lastAnimationComponent2222.hideOtherViews(i, this.mConfigViews);
            if (i11 == 0) {
            }
            if (this.mIsRTL) {
            }
            this.mLastAnimationComponent.showExtraView(i4 - i10, i5 - i10);
        }
    }

    private Drawable getAiSceneDrawable(int i) {
        TypedArray obtainTypedArray = getResources().obtainTypedArray(R.array.ai_scene_drawables);
        Drawable drawable = (i < 0 || i >= obtainTypedArray.length()) ? null : obtainTypedArray.getDrawable(i);
        obtainTypedArray.recycle();
        return drawable;
    }

    private int[] getAiSceneResources() {
        return new int[]{R.drawable.ic_new_ai_scene_off, R.drawable.ic_new_ai_scene_on};
    }

    private int[] getAutoZoomResources() {
        return new int[]{R.drawable.ic_autozoom_off, R.drawable.ic_autozoom_on};
    }

    private int[] getFilterResources() {
        return new int[]{R.drawable.ic_new_effect_button_normal, R.drawable.ic_new_effect_button_selected};
    }

    @DrawableRes
    private int getFocusPeakImageResource() {
        boolean isSwitchOn = DataRepository.dataItemRunning().isSwitchOn("pref_camera_peak_key");
        return "zh".equals(Locale.getDefault().getLanguage()) ? isSwitchOn ? R.drawable.ic_new_config_focus_peak_ch_on : R.drawable.ic_new_config_foucs_peak_ch_off : isSwitchOn ? R.drawable.ic_new_config_focus_peak_en_on : R.drawable.ic_new_config_focus_peak_en_off;
    }

    private int getInitialMargin(TopConfigItem topConfigItem, ImageView imageView) {
        SupportedConfigs supportedConfigs = this.mSupportedConfigs;
        int configsSize = supportedConfigs == null ? 0 : supportedConfigs.getConfigsSize();
        if (configsSize <= 0) {
            return 0;
        }
        int i = topConfigItem.index;
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.gravity = 0;
        if (configsSize == 1) {
            layoutParams.leftMargin = 0;
            int i2 = topConfigItem.gravity;
            if (i2 == 0) {
                i2 = 8388613;
            }
            layoutParams.gravity = i2;
            imageView.setLayoutParams(layoutParams);
            return 0;
        } else if (configsSize == 2) {
            if (i == 0) {
                layoutParams.leftMargin = 0;
                int i3 = topConfigItem.gravity;
                if (i3 == 0) {
                    i3 = 8388611;
                }
                layoutParams.gravity = i3;
            } else if (i == 1) {
                layoutParams.leftMargin = 0;
                int i4 = topConfigItem.gravity;
                if (i4 == 0) {
                    i4 = 8388613;
                }
                layoutParams.gravity = i4;
            }
            imageView.setLayoutParams(layoutParams);
            return 0;
        } else if (i == 0) {
            layoutParams.leftMargin = 0;
            layoutParams.gravity = GravityCompat.START;
            imageView.setLayoutParams(layoutParams);
            return 0;
        } else {
            int i5 = configsSize - 1;
            if (i == i5) {
                layoutParams.leftMargin = 0;
                layoutParams.gravity = GravityCompat.END;
                imageView.setLayoutParams(layoutParams);
                return 0;
            }
            int i6 = this.mTotalWidth;
            int i7 = this.mViewPadding;
            return (((i6 - (i7 * 2)) / i5) * i) + i7;
        }
    }

    private int[] getLightingResources() {
        return new int[]{R.drawable.ic_new_lighting_off, R.drawable.ic_new_lighting_on};
    }

    private int[] getLiveShotResources() {
        return new int[]{R.drawable.ic_motionphoto, R.drawable.ic_motionphoto_highlight};
    }

    private int[] getMacroResources() {
        return new int[]{R.drawable.ic_config_macro_mode_off, R.drawable.ic_config_macro_mode_on};
    }

    private int getMoreResources() {
        return R.drawable.ic_new_more;
    }

    private int[] getMusicSelectResources() {
        return new int[]{R.drawable.ic_live_music_normal, R.drawable.ic_live_music_selected};
    }

    private int getPortraitResources() {
        return R.drawable.ic_new_portrait_button_normal;
    }

    private int getSettingResources() {
        return R.drawable.ic_new_config_setting;
    }

    private int[] getSuperEISResources() {
        return new int[]{R.drawable.ic_config_super_eis_off, R.drawable.ic_config_super_eis_on};
    }

    private FragmentTopAlert getTopAlert() {
        FragmentTopAlert fragmentTopAlert = this.mFragmentTopAlert;
        String str = TAG;
        if (fragmentTopAlert == null) {
            Log.d(str, "getTopAlert(): fragment is null");
            return null;
        } else if (fragmentTopAlert.isAdded()) {
            return this.mFragmentTopAlert;
        } else {
            Log.d(str, "getTopAlert(): fragment is not added yet");
            return null;
        }
    }

    private FragmentTopConfigExtra getTopExtra() {
        return (FragmentTopConfigExtra) getChildFragmentManager().findFragmentByTag(String.valueOf(245));
    }

    private int[] getUltraPixelPortraitResources() {
        return new int[]{R.drawable.ic_config_ultrapixelportrait_off, R.drawable.ic_config_ultrapixelportrait_on};
    }

    private int[] getUltraWideBokehResources() {
        return new int[]{R.drawable.ic_ultra_wide_bokeh, R.drawable.ic_ultra_wide_bokeh_highlight};
    }

    private int[] getUltraWideResources() {
        return new int[]{R.drawable.icon_config_ultra_wide_off, R.drawable.icon_config_ultra_wide_on};
    }

    private int[] getVideoBokehResources() {
        return new int[]{R.drawable.ic_new_portrait_button_normal, R.drawable.ic_new_portrait_button_on};
    }

    private void initTopView() {
        ImageView imageView = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_00);
        ImageView imageView2 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_01);
        ImageView imageView3 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_02);
        ImageView imageView4 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_03);
        ImageView imageView5 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_04);
        ImageView imageView6 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_05);
        ImageView imageView7 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_06);
        ImageView imageView8 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_07);
        ImageView imageView9 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_08);
        ImageView imageView10 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_09);
        ImageView imageView11 = (ImageView) this.mTopConfigMenu.findViewById(R.id.top_config_10);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
        imageView10.setOnClickListener(this);
        imageView11.setOnClickListener(this);
        this.mConfigViews = new ArrayList();
        this.mConfigViews.add(imageView);
        this.mConfigViews.add(imageView2);
        this.mConfigViews.add(imageView3);
        this.mConfigViews.add(imageView4);
        this.mConfigViews.add(imageView5);
        this.mConfigViews.add(imageView6);
        this.mConfigViews.add(imageView7);
        this.mConfigViews.add(imageView8);
        this.mConfigViews.add(imageView9);
        this.mConfigViews.add(imageView10);
        this.mConfigViews.add(imageView11);
    }

    private void reConfigCommonTip() {
        if (CameraSettings.isHandGestureOpen() && DataRepository.dataItemRunning().getHandGestureRunning()) {
            alertTopHint(0, (int) R.string.hand_gesture_tip);
        }
        if (CameraSettings.isUltraPixelOn()) {
            alertTopHint(0, DataRepository.dataItemRunning().getComponentUltraPixel().getUltraPixelOpenTip());
        }
        if (!"-1".equals(CameraSettings.getEyeLightType())) {
            alertTopHint(0, (int) R.string.eye_light);
        }
        if (CameraSettings.isUltraPixelPortraitFrontOn()) {
            alertTopHint(0, (int) R.string.ultra_pixel_portrait_hint);
        }
    }

    private void reConfigTipOfFlash(boolean z) {
        if (!isExtraMenuShowing()) {
            ComponentConfigFlash componentFlash = DataRepository.dataItemConfig().getComponentFlash();
            if (!componentFlash.isEmpty()) {
                String componentValue = componentFlash.getComponentValue(this.mCurrentMode);
                String str = "1";
                if (str.equals(componentValue) || ComponentConfigFlash.FLASH_VALUE_SCREEN_LIGHT_ON.equals(componentValue)) {
                    alertFlash(0, str, false, z);
                } else {
                    String str2 = "2";
                    if (str2.equals(componentValue)) {
                        alertFlash(0, str2, false, z);
                    } else {
                        String str3 = "5";
                        if (str3.equals(componentValue)) {
                            alertFlash(0, str3, false, z);
                        } else {
                            alertFlash(8, str, false, z);
                        }
                    }
                }
            }
        }
    }

    private void reConfigTipOfHdr(boolean z) {
        ComponentConfigHdr componentHdr = DataRepository.dataItemConfig().getComponentHdr();
        if (!componentHdr.isEmpty()) {
            String componentValue = componentHdr.getComponentValue(this.mCurrentMode);
            FragmentTopAlert topAlert = getTopAlert();
            boolean isHDRTipShowing = (topAlert == null || !topAlert.isShow()) ? false : topAlert.isHDRTipShowing();
            if ("on".equals(componentValue) || "normal".equals(componentValue) || ("auto".equals(componentValue) && isHDRTipShowing)) {
                alertHDR(0, false, false, z);
            } else if ("live".equals(componentValue)) {
                alertHDR(0, true, false, z);
            } else {
                alertHDR(8, false, false, z);
            }
        }
    }

    private void resetImages() {
        DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
        this.mSupportedConfigs = SupportedConfigFactory.getSupportedTopConfigs(this.mCurrentMode, DataRepository.dataItemGlobal().getCurrentCameraId(), DataRepository.dataItemGlobal().isNormalIntent());
        for (int i = 0; i < this.mConfigViews.size(); i++) {
            ImageView imageView = (ImageView) this.mConfigViews.get(i);
            imageView.setEnabled(true);
            imageView.setColorFilter(null);
            TopConfigItem configItem = this.mSupportedConfigs.getConfigItem(i);
            boolean topImageResource = setTopImageResource(configItem, imageView, this.mCurrentMode, dataItemConfig, false);
            TopConfigItem topConfigItem = (TopConfigItem) imageView.getTag();
            if (topConfigItem == null || topConfigItem.configItem != configItem.configItem) {
                imageView.setTag(configItem);
                imageView.clearAnimation();
                imageView.setVisibility(0);
                if (topImageResource) {
                    ViewCompat.setAlpha(imageView, 0.0f);
                    ViewCompat.animate(imageView).alpha(1.0f).setDuration(150).setStartDelay(150).start();
                } else {
                    imageView.setVisibility(4);
                }
            } else {
                imageView.setTag(configItem);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r13v1, types: [int] */
    /* JADX WARNING: type inference failed for: r3v1, types: [int] */
    /* JADX WARNING: type inference failed for: r13v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r13v3 */
    /* JADX WARNING: type inference failed for: r13v4 */
    /* JADX WARNING: type inference failed for: r14v3 */
    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r13v7 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r14v5, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v6 */
    /* JADX WARNING: type inference failed for: r14v7, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v8 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r13v12 */
    /* JADX WARNING: type inference failed for: r3v7, types: [int] */
    /* JADX WARNING: type inference failed for: r13v13 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9, types: [int] */
    /* JADX WARNING: type inference failed for: r13v14 */
    /* JADX WARNING: type inference failed for: r13v16 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r13v17 */
    /* JADX WARNING: type inference failed for: r13v18 */
    /* JADX WARNING: type inference failed for: r13v19 */
    /* JADX WARNING: type inference failed for: r14v15 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r13v24 */
    /* JADX WARNING: type inference failed for: r13v25 */
    /* JADX WARNING: type inference failed for: r14v16, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v17 */
    /* JADX WARNING: type inference failed for: r14v18, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v19 */
    /* JADX WARNING: type inference failed for: r13v27, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v28 */
    /* JADX WARNING: type inference failed for: r13v30, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v31 */
    /* JADX WARNING: type inference failed for: r13v32 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r13v33 */
    /* JADX WARNING: type inference failed for: r13v35, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v36 */
    /* JADX WARNING: type inference failed for: r13v37, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v38 */
    /* JADX WARNING: type inference failed for: r13v40, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v41 */
    /* JADX WARNING: type inference failed for: r13v42, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v43 */
    /* JADX WARNING: type inference failed for: r13v44 */
    /* JADX WARNING: type inference failed for: r3v13, types: [int] */
    /* JADX WARNING: type inference failed for: r13v45, types: [int] */
    /* JADX WARNING: type inference failed for: r3v14, types: [int] */
    /* JADX WARNING: type inference failed for: r13v46, types: [int] */
    /* JADX WARNING: type inference failed for: r3v15, types: [int] */
    /* JADX WARNING: type inference failed for: r13v47 */
    /* JADX WARNING: type inference failed for: r13v50 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r13v51, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v52 */
    /* JADX WARNING: type inference failed for: r13v53, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v54 */
    /* JADX WARNING: type inference failed for: r3v18, types: [int] */
    /* JADX WARNING: type inference failed for: r13v57 */
    /* JADX WARNING: type inference failed for: r3v19, types: [int] */
    /* JADX WARNING: type inference failed for: r13v58 */
    /* JADX WARNING: type inference failed for: r15v10 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r13v61 */
    /* JADX WARNING: type inference failed for: r13v62 */
    /* JADX WARNING: type inference failed for: r15v11 */
    /* JADX WARNING: type inference failed for: r15v12 */
    /* JADX WARNING: type inference failed for: r14v33 */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r13v64 */
    /* JADX WARNING: type inference failed for: r13v65 */
    /* JADX WARNING: type inference failed for: r14v34, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v35 */
    /* JADX WARNING: type inference failed for: r14v36, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v37 */
    /* JADX WARNING: type inference failed for: r13v67 */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r13v68, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v69 */
    /* JADX WARNING: type inference failed for: r13v70, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v71 */
    /* JADX WARNING: type inference failed for: r3v24, types: [int] */
    /* JADX WARNING: type inference failed for: r13v74, types: [int] */
    /* JADX WARNING: type inference failed for: r3v25, types: [int] */
    /* JADX WARNING: type inference failed for: r13v75, types: [int] */
    /* JADX WARNING: type inference failed for: r14v42, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v43 */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r13v77 */
    /* JADX WARNING: type inference failed for: r13v78 */
    /* JADX WARNING: type inference failed for: r14v44 */
    /* JADX WARNING: type inference failed for: r14v45 */
    /* JADX WARNING: type inference failed for: r14v47 */
    /* JADX WARNING: type inference failed for: r3v27 */
    /* JADX WARNING: type inference failed for: r13v80 */
    /* JADX WARNING: type inference failed for: r13v81 */
    /* JADX WARNING: type inference failed for: r14v48, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v49 */
    /* JADX WARNING: type inference failed for: r14v50, types: [int[]] */
    /* JADX WARNING: type inference failed for: r14v51 */
    /* JADX WARNING: type inference failed for: r3v28, types: [int] */
    /* JADX WARNING: type inference failed for: r13v85, types: [int] */
    /* JADX WARNING: type inference failed for: r13v87 */
    /* JADX WARNING: type inference failed for: r3v29 */
    /* JADX WARNING: type inference failed for: r13v88 */
    /* JADX WARNING: type inference failed for: r13v89, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v90 */
    /* JADX WARNING: type inference failed for: r13v91, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v92 */
    /* JADX WARNING: type inference failed for: r13v96 */
    /* JADX WARNING: type inference failed for: r13v98 */
    /* JADX WARNING: type inference failed for: r13v99 */
    /* JADX WARNING: type inference failed for: r13v101 */
    /* JADX WARNING: type inference failed for: r3v30 */
    /* JADX WARNING: type inference failed for: r13v102 */
    /* JADX WARNING: type inference failed for: r13v103, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v104 */
    /* JADX WARNING: type inference failed for: r13v105, types: [int[]] */
    /* JADX WARNING: type inference failed for: r13v106 */
    /* JADX WARNING: type inference failed for: r3v31 */
    /* JADX WARNING: type inference failed for: r3v32 */
    /* JADX WARNING: type inference failed for: r3v33 */
    /* JADX WARNING: type inference failed for: r3v34 */
    /* JADX WARNING: type inference failed for: r13v107 */
    /* JADX WARNING: type inference failed for: r14v53 */
    /* JADX WARNING: type inference failed for: r14v54 */
    /* JADX WARNING: type inference failed for: r3v35 */
    /* JADX WARNING: type inference failed for: r3v36 */
    /* JADX WARNING: type inference failed for: r13v108 */
    /* JADX WARNING: type inference failed for: r3v37 */
    /* JADX WARNING: type inference failed for: r3v38 */
    /* JADX WARNING: type inference failed for: r13v109 */
    /* JADX WARNING: type inference failed for: r13v110 */
    /* JADX WARNING: type inference failed for: r13v111 */
    /* JADX WARNING: type inference failed for: r13v112 */
    /* JADX WARNING: type inference failed for: r3v39 */
    /* JADX WARNING: type inference failed for: r3v40 */
    /* JADX WARNING: type inference failed for: r13v113 */
    /* JADX WARNING: type inference failed for: r13v114 */
    /* JADX WARNING: type inference failed for: r14v55 */
    /* JADX WARNING: type inference failed for: r14v56 */
    /* JADX WARNING: type inference failed for: r13v115 */
    /* JADX WARNING: type inference failed for: r13v116 */
    /* JADX WARNING: type inference failed for: r13v117 */
    /* JADX WARNING: type inference failed for: r13v118 */
    /* JADX WARNING: type inference failed for: r13v119 */
    /* JADX WARNING: type inference failed for: r13v120 */
    /* JADX WARNING: type inference failed for: r3v41 */
    /* JADX WARNING: type inference failed for: r13v121 */
    /* JADX WARNING: type inference failed for: r3v42 */
    /* JADX WARNING: type inference failed for: r13v122 */
    /* JADX WARNING: type inference failed for: r13v123 */
    /* JADX WARNING: type inference failed for: r3v43 */
    /* JADX WARNING: type inference failed for: r13v124 */
    /* JADX WARNING: type inference failed for: r3v44 */
    /* JADX WARNING: type inference failed for: r13v125 */
    /* JADX WARNING: type inference failed for: r3v45 */
    /* JADX WARNING: type inference failed for: r3v46 */
    /* JADX WARNING: type inference failed for: r13v126 */
    /* JADX WARNING: type inference failed for: r13v127 */
    /* JADX WARNING: type inference failed for: r15v17 */
    /* JADX WARNING: type inference failed for: r15v18 */
    /* JADX WARNING: type inference failed for: r3v47 */
    /* JADX WARNING: type inference failed for: r3v48 */
    /* JADX WARNING: type inference failed for: r13v128 */
    /* JADX WARNING: type inference failed for: r13v129 */
    /* JADX WARNING: type inference failed for: r14v57 */
    /* JADX WARNING: type inference failed for: r14v58 */
    /* JADX WARNING: type inference failed for: r3v49 */
    /* JADX WARNING: type inference failed for: r3v50 */
    /* JADX WARNING: type inference failed for: r13v130 */
    /* JADX WARNING: type inference failed for: r13v131 */
    /* JADX WARNING: type inference failed for: r3v51 */
    /* JADX WARNING: type inference failed for: r13v132 */
    /* JADX WARNING: type inference failed for: r3v52 */
    /* JADX WARNING: type inference failed for: r13v133 */
    /* JADX WARNING: type inference failed for: r3v53 */
    /* JADX WARNING: type inference failed for: r3v54 */
    /* JADX WARNING: type inference failed for: r13v134 */
    /* JADX WARNING: type inference failed for: r13v135 */
    /* JADX WARNING: type inference failed for: r14v59 */
    /* JADX WARNING: type inference failed for: r14v60 */
    /* JADX WARNING: type inference failed for: r3v55 */
    /* JADX WARNING: type inference failed for: r3v56 */
    /* JADX WARNING: type inference failed for: r13v136 */
    /* JADX WARNING: type inference failed for: r13v137 */
    /* JADX WARNING: type inference failed for: r14v61 */
    /* JADX WARNING: type inference failed for: r14v62 */
    /* JADX WARNING: type inference failed for: r3v57 */
    /* JADX WARNING: type inference failed for: r13v138 */
    /* JADX WARNING: type inference failed for: r13v139 */
    /* JADX WARNING: type inference failed for: r13v140 */
    /* JADX WARNING: type inference failed for: r13v141 */
    /* JADX WARNING: type inference failed for: r13v142 */
    /* JADX WARNING: type inference failed for: r13v143 */
    /* JADX WARNING: type inference failed for: r13v144 */
    /* JADX WARNING: type inference failed for: r13v145 */
    /* JADX WARNING: type inference failed for: r13v146 */
    /* JADX WARNING: type inference failed for: r13v147 */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x022f, code lost:
        r13 = 0;
        r3 = r3;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v6
  assigns: []
  uses: []
  mth insns count: 375
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0300  */
    /* JADX WARNING: Unknown variable types count: 110 */
    private boolean setTopImageResource(TopConfigItem topConfigItem, ImageView imageView, int i, DataItemConfig dataItemConfig, boolean z) {
        CharSequence charSequence;
        ? r13;
        ? r3;
        ? r132;
        ? r32;
        ? r133;
        ? r33;
        ? r134;
        ActionProcessing actionProcessing = (ActionProcessing) ModeCoordinatorImpl.getInstance().getAttachProtocol(162);
        int i2 = topConfigItem.configItem;
        ? r34 = 0;
        if (i2 == 176) {
            return false;
        }
        if (i2 != 177) {
            if (i2 != 209) {
                if (i2 == 212) {
                    r32 = DataRepository.dataItemRunning().getComponentRunningShine().getTopConfigEntryRes(i);
                    r133 = R.string.accessibility_filter_open_panel;
                    r33 = r32;
                } else if (i2 == 225) {
                    r33 = getSettingResources();
                    r133 = 2131689534;
                } else if (i2 == 239) {
                    r33 = i != 174 ? CameraSettings.isFaceBeautyOn(this.mCurrentMode, null) : CameraSettings.isLiveBeautyOpen() ? 2131230868 : 2131230867;
                    r133 = 2131689478;
                } else if (i2 == 243) {
                    boolean isVideoBokehOn = CameraSettings.isVideoBokehOn();
                    StringBuilder sb = new StringBuilder();
                    sb.append("setTopImageResource: VIDEO_BOKEH isSwitchOn = ");
                    sb.append(isVideoBokehOn);
                    Log.d(TAG, sb.toString());
                    ? r35 = isVideoBokehOn ? this.mVideoBokehResource[1] : this.mVideoBokehResource[0];
                    if (isVideoBokehOn) {
                        r33 = r35;
                        r133 = 2131690190;
                    } else {
                        r33 = r35;
                        r133 = 2131690189;
                    }
                } else if (i2 != 245) {
                    if (i2 == 253) {
                        r134 = CameraSettings.isAutoZoomEnabled(i) ? this.mAutoZoomResources[1] : this.mAutoZoomResources[0];
                    } else if (i2 != 255) {
                        switch (i2) {
                            case 193:
                                ComponentConfigFlash componentFlash = dataItemConfig.getComponentFlash();
                                if (!componentFlash.isEmpty()) {
                                    r33 = componentFlash.getValueSelectedDrawableIgnoreClose(i);
                                    r133 = componentFlash.getValueSelectedStringIdIgnoreClose(i);
                                    if (!z) {
                                        reConfigTipOfFlash(true);
                                        break;
                                    }
                                }
                                break;
                            case 194:
                                ComponentConfigHdr componentHdr = dataItemConfig.getComponentHdr();
                                if (!componentHdr.isEmpty()) {
                                    r33 = componentHdr.getValueSelectedDrawableIgnoreClose(i);
                                    r133 = componentHdr.getValueSelectedStringIdIgnoreClose(i);
                                    if (!z) {
                                        reConfigTipOfHdr(true);
                                        break;
                                    }
                                }
                                break;
                            case 195:
                                r33 = getPortraitResources();
                                r133 = 2131689529;
                                break;
                            case 196:
                                int parseInt = Integer.parseInt(z ? DataRepository.getInstance().backUp().getBackupFilter(i, DataRepository.dataItemGlobal().getCurrentCameraId()) : DataRepository.dataItemRunning().getComponentConfigFilter().getComponentValue(i));
                                r132 = (parseInt == FilterInfo.FILTER_ID_NONE || parseInt <= 0) ? this.mFilterResources[0] : this.mFilterResources[1];
                                if (actionProcessing != null) {
                                    if (!actionProcessing.isShowFilterView()) {
                                        r32 = r132;
                                        break;
                                    } else {
                                        r34 = 2131689504;
                                        break;
                                    }
                                }
                                break;
                            case 197:
                                r33 = getMoreResources();
                                r133 = 2131689525;
                                break;
                            default:
                                switch (i2) {
                                    case 199:
                                        r33 = getFocusPeakImageResource();
                                        r133 = 2131689513;
                                        break;
                                    case 200:
                                        String componentValue = dataItemConfig.getComponentBokeh().getComponentValue(i);
                                        String str = "on";
                                        ? r36 = str.equals(componentValue) ? 2131231051 : 2131231050;
                                        if (!str.equals(componentValue)) {
                                            r33 = r36;
                                            r133 = 2131689483;
                                            break;
                                        } else {
                                            r33 = r36;
                                            r133 = 2131689484;
                                            break;
                                        }
                                    case 201:
                                        ? r37 = CameraSettings.getAiSceneOpen(i) ? this.mAiSceneResources[1] : this.mAiSceneResources[0];
                                        if (!CameraSettings.getAiSceneOpen(i)) {
                                            r33 = r37;
                                            r133 = 2131689473;
                                            break;
                                        } else {
                                            r33 = r37;
                                            r133 = 2131689474;
                                            break;
                                        }
                                    default:
                                        switch (i2) {
                                            case 203:
                                                r132 = DataRepository.dataItemRunning().getComponentRunningLighting().isSwitchOn(i) ? this.mLightingResource[1] : this.mLightingResource[0];
                                                if (actionProcessing != null) {
                                                    if (!actionProcessing.isShowLightingView()) {
                                                        r34 = 2131689486;
                                                        break;
                                                    } else {
                                                        r34 = 2131689485;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 204:
                                                r33 = dataItemConfig.getComponentConfigSlowMotion().getImageResource();
                                                r133 = dataItemConfig.getComponentConfigSlowMotion().getContentDesc();
                                                break;
                                            case 205:
                                                ComponentConfigUltraWide componentConfigUltraWide = dataItemConfig.getComponentConfigUltraWide();
                                                if (!componentConfigUltraWide.isEmpty()) {
                                                    r33 = componentConfigUltraWide.getValueSelectedDrawableIgnoreClose(i);
                                                    r133 = componentConfigUltraWide.getValueSelectedStringIdIgnoreClose(i);
                                                    break;
                                                }
                                            case 206:
                                                boolean isLiveShotOn = CameraSettings.isLiveShotOn();
                                                ? r14 = this.mLiveShotResource;
                                                ? r38 = isLiveShotOn ? r14[1] : r14[0];
                                                if (!isLiveShotOn) {
                                                    r33 = r38;
                                                    r133 = 2131689487;
                                                    break;
                                                } else {
                                                    r33 = r38;
                                                    r133 = 2131689488;
                                                    break;
                                                }
                                            case 207:
                                                String str2 = "pref_ultra_wide_bokeh_enabled";
                                                boolean backupSwitchState = z ? DataRepository.getInstance().backUp().getBackupSwitchState(i, str2, DataRepository.dataItemGlobal().getCurrentCameraId()) : DataRepository.dataItemRunning().isSwitchOn(str2);
                                                ? r39 = backupSwitchState ? this.mUltraWideBokehResources[1] : this.mUltraWideBokehResources[0];
                                                if (!backupSwitchState) {
                                                    r33 = r39;
                                                    r133 = 2131689495;
                                                    break;
                                                } else {
                                                    r33 = r39;
                                                    r133 = 2131689496;
                                                    break;
                                                }
                                            default:
                                                switch (i2) {
                                                    case 214:
                                                        ComponentConfigMeter componentConfigMeter = dataItemConfig.getComponentConfigMeter();
                                                        if (!componentConfigMeter.isEmpty()) {
                                                            r33 = componentConfigMeter.getValueSelectedDrawableIgnoreClose(i);
                                                            r133 = componentConfigMeter.getValueSelectedStringIdIgnoreClose(i);
                                                            break;
                                                        }
                                                    case 215:
                                                        r33 = CameraSettings.isUltraPixelPortraitFrontOn() ? this.mUltraPixelPortraitResources[1] : this.mUltraPixelPortraitResources[0];
                                                        r133 = 2131690397;
                                                        break;
                                                    case 216:
                                                        BaseDelegate baseDelegate = (BaseDelegate) ModeCoordinatorImpl.getInstance().getAttachProtocol(160);
                                                        if (baseDelegate != null && baseDelegate.getActiveFragment(R.id.bottom_action) == 65523) {
                                                            r132 = 2131230935;
                                                            break;
                                                        } else {
                                                            r132 = 2131230934;
                                                            break;
                                                        }
                                                        break;
                                                    case 217:
                                                        r132 = 2131230864;
                                                        break;
                                                    case 218:
                                                        r33 = CameraSettings.isSuperEISEnabled(i) ? this.mSuperEISResources[1] : this.mSuperEISResources[0];
                                                        r133 = 2131690372;
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                        }
                    } else {
                        r134 = CameraSettings.isMacroModeEnabled(i) ? this.mMacroResources[1] : this.mMacroResources[0];
                    }
                    r33 = r134;
                    r133 = R.string.autozoom_hint;
                } else {
                    String[] currentLiveMusic = CameraSettings.getCurrentLiveMusic();
                    if (!currentLiveMusic[1].isEmpty()) {
                        alertTopMusicHint(0, currentLiveMusic[1]);
                        r34 = r34;
                        r132 = this.mLiveMusicSelectResources[1];
                    } else {
                        r34 = r34;
                        r132 = this.mLiveMusicSelectResources[0];
                    }
                }
                charSequence = null;
                r13 = r133;
                r3 = r33;
            } else {
                boolean isUltraPixelOn = CameraSettings.isUltraPixelOn();
                ? r9 = isUltraPixelOn ? this.mUltraPixelPhotographyIconResources[1] : this.mUltraPixelPhotographyIconResources[0];
                charSequence = isUltraPixelOn ? this.mUltraPixelPhotographyTipString[1] : this.mUltraPixelPhotographyTipString[0];
                r13 = 0;
                r3 = r9;
            }
            if (r3 > 0) {
                Drawable drawable = getResources().getDrawable(r3);
                topConfigItem.margin = getInitialMargin(topConfigItem, imageView);
                if (topConfigItem.margin > 0) {
                    LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                    topConfigItem.margin -= (drawable.getIntrinsicWidth() / 2) + this.mViewPadding;
                    if (this.mIsRTL) {
                        layoutParams.leftMargin = ((this.mTotalWidth - topConfigItem.margin) - drawable.getIntrinsicWidth()) - (this.mViewPadding * 2);
                    } else {
                        layoutParams.leftMargin = topConfigItem.margin;
                    }
                    imageView.setLayoutParams(layoutParams);
                }
                if (topConfigItem.configItem == 177) {
                    imageView.setImageDrawable(null);
                } else {
                    imageView.setImageDrawable(drawable);
                }
                if (topConfigItem.configItem == 193) {
                    if (this.mCurrentMode != 167 || CameraSettings.isFlashSupportedInManualMode()) {
                        imageView.setAlpha(1.0f);
                    } else {
                        imageView.setAlpha(0.4f);
                    }
                }
                if (Util.isAccessible() || Util.isSetContentDesc()) {
                    if (r13 > 0) {
                        imageView.setContentDescription(getString(r13));
                    } else if (!TextUtils.isEmpty(charSequence)) {
                        imageView.setContentDescription(charSequence);
                    }
                }
            }
            return true;
        }
        r34 = r34;
        r132 = 2131231021;
        charSequence = null;
        ? r92 = r34;
        r3 = r132;
        r13 = r92;
        if (r3 > 0) {
        }
        return true;
    }

    private void showMenu() {
        this.mTopConfigMenu.setVisibility(8);
        hideSwitchHint();
        hideAlert();
        this.mFragmentTopConfigExtra = new FragmentTopConfigExtra();
        this.mFragmentTopConfigExtra.setDegree(this.mDegree);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTopConfigExtra fragmentTopConfigExtra = this.mFragmentTopConfigExtra;
        FragmentUtils.addFragmentWithTag(childFragmentManager, (int) R.id.top_config_extra, (Fragment) fragmentTopConfigExtra, fragmentTopConfigExtra.getFragmentTag());
        this.mIsShowExtraMenu = true;
    }

    public /* synthetic */ void a(FragmentTopAlert fragmentTopAlert, boolean z) {
        if (fragmentTopAlert != null) {
            fragmentTopAlert.setShow(true);
            reConfigCommonTip();
            reConfigTipOfFlash(z);
            reConfigTipOfHdr(z);
            fragmentTopAlert.updateMusicHint();
            alertUpdateValue(4);
            updateLyingDirectHint(false, true);
            TopConfigProtocol topConfigProtocol = (TopConfigProtocol) ModeCoordinatorImpl.getInstance().getAttachProtocol(193);
            if (topConfigProtocol != null) {
                int i = this.mCurrentMode;
                if (i == 162 || i == 163) {
                    topConfigProtocol.reShowMoon();
                }
            }
            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                configChanges.reCheckVideoUltraClearTip();
                configChanges.reCheckRaw();
                configChanges.reCheckMacroMode();
            }
        }
    }

    public void alertAiDetectTipHint(int i, int i2, long j) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertRecommendTipHint(i, i2, j);
        }
    }

    public void alertAiSceneSelector(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertAiSceneSelector(i);
            BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
            if (bottomPopupTips != null) {
                bottomPopupTips.reConfigQrCodeTip();
            }
        }
    }

    public void alertFlash(int i, String str, boolean z) {
        alertFlash(i, str, z, true);
    }

    public void alertFlash(int i, String str, boolean z, boolean z2) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            if (z2) {
                if (i != 0) {
                    this.mLastAnimationComponent.reverse(true);
                } else if (z) {
                    ImageView topImage = getTopImage(193);
                    if (topImage != null) {
                        topImage.performClick();
                    }
                }
            }
            topAlert.alertFlash(i, str);
        }
    }

    public void alertHDR(int i, boolean z, boolean z2) {
        alertHDR(i, z, z2, true);
    }

    public void alertLightingHint(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertLightingHint(i);
        }
    }

    public void alertLightingTitle(boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertLightingTitle(z);
        }
    }

    public void alertMimojiFaceDetect(boolean z, int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertMimojiFaceDetect(z, i);
        }
    }

    public void alertMoonModeSelector(int i, boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertMoonSelector(getResources().getString(R.string.ai_scene_top_tip), getResources().getString(R.string.ai_scene_top_moon_off), i, z);
            if (this.mCurrentMode == 163) {
                CameraModuleSpecial cameraModuleSpecial = (CameraModuleSpecial) ModeCoordinatorImpl.getInstance().getAttachProtocol(195);
                if (cameraModuleSpecial != null) {
                    cameraModuleSpecial.showOrHideChip(i != 0);
                }
            }
        }
    }

    public void alertMusicClose(boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.alertMusicClose(z);
        }
    }

    public void alertRaw(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            FragmentTopConfigExtra fragmentTopConfigExtra = this.mFragmentTopConfigExtra;
            topAlert.alertRaw(i, Util.getDisplayRect(getContext(), 0).top + getResources().getDimensionPixelSize(R.dimen.video_ultra_tip_margin_top), !(fragmentTopConfigExtra == null || !fragmentTopConfigExtra.isAdded()));
        }
    }

    public void alertSwitchHint(int i, @StringRes int i2) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertSwitchHint(i, i2);
        }
    }

    public void alertSwitchHint(int i, String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertSwitchHint(i, str);
        }
    }

    public void alertTopHint(int i, @StringRes int i2) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertTopHint(i, i2);
        }
    }

    public void alertTopHint(int i, int i2, long j) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertTopHint(i, i2, j);
        }
    }

    public void alertTopHint(int i, String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertTopHint(i, str);
        }
    }

    public void alertUpdateValue(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.alertUpdateValue(i);
        }
    }

    public void alertVideoUltraClear(int i, @StringRes int i2) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            FragmentTopConfigExtra fragmentTopConfigExtra = this.mFragmentTopConfigExtra;
            topAlert.alertVideoUltraClear(i, i2, Util.getDisplayRect(getContext()).top + getResources().getDimensionPixelSize(R.dimen.video_ultra_tip_margin_top), !(fragmentTopConfigExtra == null || !fragmentTopConfigExtra.isAdded()));
        }
    }

    public void clearAlertStatus() {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.clearAlertStatus();
        }
    }

    public void directHideLyingDirectHint() {
    }

    public void disableMenuItem(boolean z, int... iArr) {
        if (iArr != null) {
            for (int i : iArr) {
                this.mDisabledFunctionMenu.put(i, z);
                if (z) {
                    ImageView topImage = getTopImage(i);
                    if (topImage != null) {
                        AlphaOutOnSubscribe.directSetResult(topImage);
                    }
                }
            }
        }
    }

    public void enableMenuItem(boolean z, int... iArr) {
        SparseBooleanArray sparseBooleanArray = this.mDisabledFunctionMenu;
        if (sparseBooleanArray != null && sparseBooleanArray.size() != 0) {
            for (int i : iArr) {
                this.mDisabledFunctionMenu.delete(i);
                if (z) {
                    ImageView topImage = getTopImage(i);
                    if (topImage != null) {
                        AlphaInOnSubscribe.directSetResult(topImage);
                    }
                }
            }
        }
    }

    public boolean getAlertIsShow() {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert == null) {
            return false;
        }
        return topAlert.isShow();
    }

    public int getCurrentAiSceneLevel() {
        return this.mCurrentAiSceneLevel;
    }

    public int getFragmentInto() {
        return 244;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.fragment_top_config;
    }

    public ImageView getTopImage(int i) {
        for (ImageView imageView : this.mConfigViews) {
            TopConfigItem topConfigItem = (TopConfigItem) imageView.getTag();
            if (topConfigItem != null && topConfigItem.configItem == i) {
                return imageView;
            }
        }
        return null;
    }

    public void hideAlert() {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.clear(true);
            topAlert.setShow(false);
        }
    }

    public void hideConfigMenu() {
        Completable.create(new AlphaOutOnSubscribe(this.mTopConfigMenu)).subscribe();
    }

    public void hideExtraMenu() {
        onBackEvent(6);
    }

    public void hideSwitchHint() {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null && topAlert.isShow()) {
            topAlert.hideSwitchHint();
        }
    }

    /* access modifiers changed from: protected */
    public void initView(View view) {
        this.mAiSceneResources = getAiSceneResources();
        this.mAutoZoomResources = getAutoZoomResources();
        this.mUltraWideResource = getUltraWideResources();
        this.mUltraWideBokehResources = getUltraWideBokehResources();
        this.mUltraPixelPhotographyIconResources = ComponentRunningUltraPixel.getUltraPixelTopMenuResources();
        this.mUltraPixelPhotographyTipString = ComponentRunningUltraPixel.getUltraPixelSwitchTipsString();
        this.mLiveShotResource = getLiveShotResources();
        this.mLightingResource = getLightingResources();
        this.mVideoBokehResource = getVideoBokehResources();
        this.mFilterResources = getFilterResources();
        this.mLiveMusicSelectResources = getMusicSelectResources();
        this.mMacroResources = getMacroResources();
        this.mUltraPixelPortraitResources = getUltraPixelPortraitResources();
        this.mSuperEISResources = getSuperEISResources();
        this.mIsRTL = Util.isLayoutRTL(getContext());
        this.mLastAnimationComponent = new LastAnimationComponent();
        this.mDisabledFunctionMenu = new SparseBooleanArray(1);
        this.mTopExtraParent = (ViewGroup) view.findViewById(R.id.top_config_extra);
        this.mTopConfigMenu = view.findViewById(R.id.top_config_menu);
        ((MarginLayoutParams) this.mTopConfigMenu.getLayoutParams()).topMargin = Util.isNotchDevice ? Util.sStatusBarHeight : getResources().getDimensionPixelOffset(R.dimen.top_control_panel_extra_margin_top);
        initTopView();
        this.mExpandView = (RecyclerView) view.findViewById(R.id.top_config_expand_view);
        LinearLayoutManagerWrapper linearLayoutManagerWrapper = new LinearLayoutManagerWrapper(getContext(), "top_config_expand_view");
        linearLayoutManagerWrapper.setOrientation(0);
        this.mExpandView.setLayoutManager(linearLayoutManagerWrapper);
        this.mViewPadding = getResources().getDimensionPixelSize(R.dimen.panel_imageview_button_padding_width);
        this.mTopDrawableWidth = getResources().getDrawable(R.drawable.ic_new_config_flash_off).getIntrinsicWidth();
        this.mTotalWidth = Util.sWindowWidth;
        if (((ActivityBase) getContext()).getCameraIntentManager().isFromScreenSlide().booleanValue()) {
            Util.startScreenSlideAlphaInAnimation(this.mTopConfigMenu);
        }
        provideAnimateElement(this.mCurrentMode, null, 2);
    }

    public void insertConfigItem(int i) {
        resetImages();
    }

    public boolean isCurrentRecommendTipText(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert == null || !topAlert.isShow()) {
            return false;
        }
        return topAlert.isCurrentRecommendTipText(i);
    }

    public boolean isExtraMenuShowing() {
        FragmentTopConfigExtra fragmentTopConfigExtra = this.mFragmentTopConfigExtra;
        return fragmentTopConfigExtra != null && fragmentTopConfigExtra.isAdded() && this.mIsShowExtraMenu;
    }

    public boolean needViewClear() {
        return true;
    }

    public void notifyAfterFrameAvailable(int i) {
        super.notifyAfterFrameAvailable(i);
        ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
        if (configChanges != null) {
            configChanges.reCheckMutexConfigs(this.mCurrentMode);
            configChanges.reCheckFocusPeakConfig();
            configChanges.reCheckUltraPixel();
            configChanges.reCheckUltraPixelPortrait();
            configChanges.reCheckLiveShot();
            configChanges.reCheckHandGesture();
            configChanges.reCheckVideoUltraClearTip();
            configChanges.reCheckRaw();
            configChanges.reCheckMacroMode();
            configChanges.reCheckFrontBokenTip();
            configChanges.reCheckBeauty();
            configChanges.reCheckEyeLight();
        }
    }

    public void notifyDataChanged(int i, int i2) {
        super.notifyDataChanged(i, i2);
        this.mDisplayRectTopMargin = Util.getDisplayRect(getContext()).top;
        int i3 = this.mCurrentMode;
        int i4 = 7;
        if (this.mResetType != 7) {
            i4 = 2;
        }
        provideAnimateElement(i3, null, i4);
        if (this.mFragmentTopAlert == null) {
            this.mFragmentTopAlert = new FragmentTopAlert();
            this.mFragmentTopAlert.setShow(!isExtraMenuShowing());
            this.mFragmentTopAlert.setDegree(this.mDegree);
            FragmentManager childFragmentManager = getChildFragmentManager();
            FragmentTopAlert fragmentTopAlert = this.mFragmentTopAlert;
            FragmentUtils.addFragmentWithTag(childFragmentManager, (int) R.id.top_alert, (Fragment) fragmentTopAlert, fragmentTopAlert.getFragmentTag());
        }
    }

    public void onAngleChanged(float f2) {
    }

    public boolean onBackEvent(int i) {
        boolean z = false;
        if (this.mLastAnimationComponent.reverse(i != 4)) {
            return true;
        }
        boolean z2 = this.mIsShowExtraMenu;
        FragmentTopConfigExtra topExtra = getTopExtra();
        if (topExtra == null) {
            return false;
        }
        if (i == 1 || i == 2) {
            topExtra.animateOut();
            Completable.create(new AlphaInOnSubscribe(this.mTopConfigMenu).setStartDelayTime(200)).subscribe();
        } else if (i == 6) {
            topExtra.animateOut();
            Completable.create(new AlphaInOnSubscribe(this.mTopConfigMenu).setStartDelayTime(200)).subscribe();
        } else if (i != 7) {
            FragmentUtils.removeFragmentByTag(getChildFragmentManager(), String.valueOf(245));
            this.mTopConfigMenu.setVisibility(0);
        } else {
            z = z2;
        }
        if (!(i == 4 || i == 7)) {
            reInitAlert(true);
        }
        this.mIsShowExtraMenu = z;
        return true;
    }

    public void onBeautyRecordingStart() {
        onBackEvent(5);
        ViewCompat.animate(this.mTopConfigMenu).alpha(0.0f).start();
    }

    public void onBeautyRecordingStop() {
        ViewCompat.animate(this.mTopConfigMenu).alpha(1.0f).start();
    }

    public void onClick(View view) {
        String str = TAG;
        Log.d(str, "top config onclick");
        if (isEnableClick()) {
            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                CameraAction cameraAction = (CameraAction) ModeCoordinatorImpl.getInstance().getAttachProtocol(161);
                if (cameraAction != null && cameraAction.isDoingAction()) {
                    return;
                }
                if (!CameraSettings.isFrontCamera() || !((Camera) getContext()).isScreenSlideOff()) {
                    TopConfigItem topConfigItem = (TopConfigItem) view.getTag();
                    if (topConfigItem != null) {
                        if (this.mDisabledFunctionMenu.size() <= 0 || this.mDisabledFunctionMenu.indexOfKey(topConfigItem.configItem) < 0) {
                            CameraClickObservable cameraClickObservable = (CameraClickObservable) ModeCoordinatorImpl.getInstance().getAttachProtocol(227);
                            int i = topConfigItem.configItem;
                            if (i == 209) {
                                configChanges.onConfigChanged(209);
                            } else if (i == 212) {
                                configChanges.onConfigChanged(212);
                            } else if (i == 225) {
                                configChanges.showSetting();
                            } else if (i == 239) {
                                configChanges.onConfigChanged(239);
                            } else if (i == 243) {
                                configChanges.onConfigChanged(243);
                            } else if (i == 245) {
                                Fragment fragmentByTag = FragmentUtils.getFragmentByTag(getFragmentManager(), FragmentLiveMusic.TAG);
                                CameraStatUtil.trackLiveMusicClick();
                                if (fragmentByTag == null) {
                                    FragmentLiveMusic fragmentLiveMusic = new FragmentLiveMusic();
                                    fragmentLiveMusic.setStyle(2, R.style.TTMusicDialogFragment);
                                    getFragmentManager().beginTransaction().add((Fragment) fragmentLiveMusic, FragmentLiveMusic.TAG).commitAllowingStateLoss();
                                }
                            } else if (i == 253) {
                                configChanges.onConfigChanged(253);
                            } else if (i != 255) {
                                switch (i) {
                                    case 193:
                                        ComponentConfigFlash componentFlash = ((DataItemConfig) DataRepository.provider().dataConfig()).getComponentFlash();
                                        if (componentFlash.disableUpdate()) {
                                            int disableReasonString = componentFlash.getDisableReasonString();
                                            if (disableReasonString != 0) {
                                                ToastUtils.showToast(CameraAppImpl.getAndroidContext(), disableReasonString);
                                            }
                                            Log.w(str, "ignore click flash for disable update");
                                        } else if (this.mCurrentMode != 171 || !DataRepository.dataItemFeature().Jb() || !CameraSettings.isBackCamera()) {
                                            expandExtra(componentFlash, view, topConfigItem.configItem);
                                        } else {
                                            String componentValue = componentFlash.getComponentValue(this.mCurrentMode);
                                            String str2 = "0";
                                            if (componentValue == str2) {
                                                str2 = "5";
                                            }
                                            componentFlash.setComponentValue(this.mCurrentMode, str2);
                                            onExpandValueChange(componentFlash, componentValue, str2);
                                        }
                                        if (cameraClickObservable != null) {
                                            cameraClickObservable.subscribe(161);
                                            break;
                                        }
                                        break;
                                    case 194:
                                        expandExtra(((DataItemConfig) DataRepository.provider().dataConfig()).getComponentHdr(), view, topConfigItem.configItem);
                                        if (cameraClickObservable != null) {
                                            cameraClickObservable.subscribe(162);
                                            break;
                                        }
                                        break;
                                    case 195:
                                        configChanges.onConfigChanged(195);
                                        break;
                                    case 196:
                                        configChanges.onConfigChanged(196);
                                        break;
                                    case 197:
                                        showMenu();
                                        if (cameraClickObservable != null) {
                                            cameraClickObservable.subscribe(164);
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case 199:
                                                configChanges.onConfigChanged(199);
                                                ((ImageView) view).setImageResource(getFocusPeakImageResource());
                                                break;
                                            case 200:
                                                DataItemConfig dataItemConfig = (DataItemConfig) DataRepository.provider().dataConfig();
                                                dataItemConfig.getComponentBokeh().toggle(this.mCurrentMode);
                                                String componentValue2 = dataItemConfig.getComponentBokeh().getComponentValue(this.mCurrentMode);
                                                CameraStatUtil.tarckBokenChanged(this.mCurrentMode, componentValue2);
                                                updateConfigItem(200);
                                                if (dataItemConfig.reConfigHdrIfBokehChanged(this.mCurrentMode, componentValue2)) {
                                                    updateConfigItem(194);
                                                }
                                                configChanges.configBokeh(componentValue2);
                                                break;
                                            case 201:
                                                configChanges.onConfigChanged(201);
                                                if (cameraClickObservable != null) {
                                                    cameraClickObservable.subscribe(166);
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (i) {
                                                    case 203:
                                                        configChanges.onConfigChanged(203);
                                                        break;
                                                    case 204:
                                                        configChanges.onConfigChanged(204);
                                                        break;
                                                    case 205:
                                                        configChanges.onConfigChanged(205);
                                                        break;
                                                    case 206:
                                                        configChanges.onConfigChanged(206);
                                                        if (cameraClickObservable != null) {
                                                            cameraClickObservable.subscribe(163);
                                                            break;
                                                        }
                                                        break;
                                                    case 207:
                                                        configChanges.onConfigChanged(207);
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 214:
                                                                CameraStatUtil.trackMeterClick();
                                                                expandExtra(((DataItemConfig) DataRepository.provider().dataConfig()).getComponentConfigMeter(), view, topConfigItem.configItem);
                                                                break;
                                                            case 215:
                                                                configChanges.onConfigChanged(215);
                                                                break;
                                                            case 216:
                                                                configChanges.onConfigChanged(216);
                                                                break;
                                                            case 217:
                                                                configChanges.onConfigChanged(217);
                                                                break;
                                                            case 218:
                                                                configChanges.onConfigChanged(218);
                                                                break;
                                                        }
                                                }
                                        }
                                }
                            } else {
                                configChanges.onConfigChanged(255);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onExpandValueChange(ComponentData componentData, String str, String str2) {
        if (isEnableClick()) {
            DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
            ConfigChanges configChanges = (ConfigChanges) ModeCoordinatorImpl.getInstance().getAttachProtocol(164);
            if (configChanges != null) {
                int displayTitleString = componentData.getDisplayTitleString();
                if (displayTitleString == R.string.pref_camera_autoexposure_title) {
                    CameraStatUtil.trackPreferenceChange(componentData.getKey(this.mCurrentMode), str2);
                    updateConfigItem(214);
                    configChanges.configMeter(str2);
                } else if (displayTitleString == R.string.pref_camera_flashmode_title) {
                    if (componentData.getDisplayTitleString() == R.string.pref_camera_flashmode_title) {
                        String str3 = "5";
                        if (str == str3 || str2 == str3) {
                            String str4 = "0";
                            if (!(str2 == str4 || str2 == ComponentConfigFlash.FLASH_VALUE_MANUAL_OFF)) {
                                CameraStatUtil.trackFlashChanged(this.mCurrentMode, str4);
                                configChanges.configBackSoftLightSwitch(str4);
                            }
                        }
                    }
                    CameraStatUtil.trackFlashChanged(this.mCurrentMode, str2);
                    updateConfigItem(193);
                    if (dataItemConfig.reConfigHhrIfFlashChanged(this.mCurrentMode, str2)) {
                        updateConfigItem(194);
                    }
                    configChanges.configFlash(str2);
                } else if (displayTitleString == R.string.pref_camera_hdr_title) {
                    CameraStatUtil.trackHdrChanged(this.mCurrentMode, str2);
                    updateConfigItem(194);
                    configChanges.restoreMutexFlash(SupportedConfigFactory.CLOSE_BY_AI);
                    if (dataItemConfig.reConfigFlashIfHdrChanged(this.mCurrentMode, str2)) {
                        updateConfigItem(193);
                    }
                    if (dataItemConfig.reConfigBokehIfHdrChanged(this.mCurrentMode, str2)) {
                        updateConfigItem(200);
                    }
                    configChanges.configHdr(str2);
                }
                this.mLastAnimationComponent.reverse(true);
            }
        }
    }

    public void provideAnimateElement(int i, List<Completable> list, int i2) {
        int i3 = this.mCurrentMode;
        boolean z = i2 == 3;
        MimojiStatusManager mimojiStatusManager = DataRepository.dataItemLive().getMimojiStatusManager();
        if (this.mCurrentMode != 177 || !mimojiStatusManager.IsInMimojiCreate() || i2 == 3) {
            super.provideAnimateElement(i, list, i2);
            if (isInModeChanging() || i2 == 3) {
                this.mIsShowTopLyingDirectHint = false;
            }
            if (i3 == 161 ? i != 161 : !((i3 == 162 || i3 == 169) && (i == 162 || i == 169))) {
                onBackEvent(i2 == 7 ? 7 : 4);
            } else if (isExtraMenuShowing()) {
                refreshExtraMenu();
            }
            if (isExtraMenuShowing() && i2 == 7) {
                this.mFragmentTopConfigExtra.provideAnimateElement(i, list, i2);
            }
            if (z) {
                if (!DataRepository.dataItemLive().getMimojiStatusManager().IsInMimojiPreview()) {
                    enableMenuItem(true, 197, 193);
                }
                this.mDisabledFunctionMenu.clear();
            }
            FragmentTopAlert topAlert = getTopAlert();
            if (topAlert != null) {
                topAlert.provideAnimateElement(i, list, i2);
            }
            DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
            int currentCameraId = DataRepository.dataItemGlobal().getCurrentCameraId();
            if (z && this.mTopConfigMenu.getVisibility() != 0) {
                AlphaInOnSubscribe.directSetResult(this.mTopConfigMenu);
            }
            this.mSupportedConfigs = SupportedConfigFactory.getSupportedTopConfigs(this.mCurrentMode, currentCameraId, DataRepository.dataItemGlobal().isNormalIntent());
            if (this.mSupportedConfigs != null) {
                for (int i4 = 0; i4 < this.mConfigViews.size(); i4++) {
                    ImageView imageView = (ImageView) this.mConfigViews.get(i4);
                    imageView.setEnabled(true);
                    TopConfigItem configItem = this.mSupportedConfigs.getConfigItem(i4);
                    boolean topImageResource = setTopImageResource(configItem, imageView, i, dataItemConfig, list != null);
                    if (!topImageResource || this.mDisabledFunctionMenu.indexOfKey(configItem.configItem) < 0 || !this.mDisabledFunctionMenu.get(configItem.configItem)) {
                        TopConfigItem topConfigItem = (TopConfigItem) imageView.getTag();
                        if (topConfigItem == null || topConfigItem.configItem != configItem.configItem) {
                            imageView.setTag(configItem);
                            if (list == null) {
                                if (topImageResource) {
                                    AlphaInOnSubscribe.directSetResult(imageView);
                                } else {
                                    AlphaOutOnSubscribe.directSetResult(imageView);
                                }
                            } else if (topImageResource) {
                                AlphaInOnSubscribe alphaInOnSubscribe = new AlphaInOnSubscribe(imageView);
                                alphaInOnSubscribe.setStartDelayTime(150).setDurationTime(150);
                                list.add(Completable.create(alphaInOnSubscribe));
                            } else if (i3 == 165 || this.mCurrentMode == 165) {
                                AlphaOutOnSubscribe.directSetResult(imageView);
                            } else {
                                list.add(Completable.create(new AlphaOutOnSubscribe(imageView).setDurationTime(150)));
                            }
                        } else {
                            imageView.setTag(configItem);
                        }
                    }
                }
                return;
            }
            return;
        }
        FragmentTopAlert topAlert2 = getTopAlert();
        if (topAlert2 != null) {
            topAlert2.provideAnimateElement(i, list, i2);
        }
    }

    public void provideRotateItem(List<View> list, int i) {
        super.provideRotateItem(list, i);
        FragmentTopConfigExtra topExtra = getTopExtra();
        if (topExtra != null) {
            topExtra.provideRotateItem(list, i);
        }
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.provideRotateItem(list, i);
        }
    }

    public void reInitAlert(boolean z) {
        FragmentTopAlert topAlert = getTopAlert();
        if (!CameraSettings.isHandGestureOpen() || DataRepository.dataItemRunning().getHandGestureRunning()) {
            AndroidSchedulers.mainThread().scheduleDirect(new i(this, topAlert, z), this.mIsShowExtraMenu ? 120 : 0, TimeUnit.MILLISECONDS);
        }
    }

    public void refreshExtraMenu() {
        FragmentTopConfigExtra fragmentTopConfigExtra = this.mFragmentTopConfigExtra;
        if (fragmentTopConfigExtra != null && fragmentTopConfigExtra.isAdded()) {
            this.mFragmentTopConfigExtra.reFresh();
        }
    }

    /* access modifiers changed from: protected */
    public void register(ModeCoordinator modeCoordinator) {
        super.register(modeCoordinator);
        registerBackStack(modeCoordinator, this);
        modeCoordinator.attachProtocol(172, this);
    }

    public void removeConfigItem(int i) {
        resetImages();
    }

    public void removeExtraMenu(int i) {
        onBackEvent(i);
    }

    public void rotate() {
    }

    public void setAiSceneImageLevel(int i) {
        if (i == 25) {
            i = 23;
        }
        this.mCurrentAiSceneLevel = i;
        Drawable aiSceneDrawable = getAiSceneDrawable(i);
        ImageView topImage = getTopImage(201);
        if (aiSceneDrawable != null && topImage != null) {
            topImage.setImageDrawable(aiSceneDrawable);
            BottomPopupTips bottomPopupTips = (BottomPopupTips) ModeCoordinatorImpl.getInstance().getAttachProtocol(175);
            if (bottomPopupTips != null) {
                bottomPopupTips.reConfigQrCodeTip();
            }
        }
    }

    public void setClickEnable(boolean z) {
        super.setClickEnable(z);
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.setClickEnable(z);
        }
    }

    public void setRecordingTimeState(int i) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.setRecordingTimeState(i);
        } else {
            FragmentTopAlert.setPendingRecordingState(i);
        }
    }

    public void setShow(boolean z) {
        if (getTopAlert() != null) {
            getTopAlert().setShow(z);
        }
    }

    public void showConfigMenu() {
        Completable.create(new AlphaInOnSubscribe(this.mTopConfigMenu)).subscribe();
    }

    public void startLiveShotAnimation() {
        ImageView topImage = getTopImage(206);
        if (topImage != null) {
            Drawable drawable = topImage.getDrawable();
            if (drawable instanceof LayerDrawable) {
                RotateDrawable rotateDrawable = (RotateDrawable) ((LayerDrawable) drawable).getDrawable(0);
                ObjectAnimator objectAnimator = this.mLiveShotAnimator;
                if (objectAnimator == null || objectAnimator.getTarget() != rotateDrawable) {
                    this.mLiveShotAnimator = ObjectAnimator.ofInt(rotateDrawable, "level", new int[]{0, 10000});
                    this.mLiveShotAnimator.setDuration(1000);
                    this.mLiveShotAnimator.setInterpolator(new CubicEaseInOutInterpolator());
                }
                if (this.mLiveShotAnimator.isRunning()) {
                    this.mLiveShotAnimator.cancel();
                }
                this.mLiveShotAnimator.start();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void unRegister(ModeCoordinator modeCoordinator) {
        super.unRegister(modeCoordinator);
        unRegisterBackStack(modeCoordinator, this);
        modeCoordinator.detachProtocol(172, this);
    }

    public void updateConfigItem(int... iArr) {
        DataItemConfig dataItemConfig = DataRepository.dataItemConfig();
        for (int topImage : iArr) {
            ImageView topImage2 = getTopImage(topImage);
            if (topImage2 != null) {
                setTopImageResource((TopConfigItem) topImage2.getTag(), topImage2, this.mCurrentMode, dataItemConfig, false);
            }
        }
    }

    public void updateContentDescription() {
        ImageView topImage = getTopImage(196);
        if (topImage != null) {
            topImage.setContentDescription(getString(R.string.accessibility_filter_open_panel));
        }
    }

    public void updateLyingDirectHint(boolean z, boolean z2) {
        if (!z2) {
            this.mIsShowTopLyingDirectHint = z;
        }
        if (!isExtraMenuShowing()) {
            FragmentTopAlert topAlert = getTopAlert();
            if (topAlert != null && topAlert.isShow()) {
                topAlert.updateLyingDirectHint(this.mIsShowTopLyingDirectHint);
            }
        }
    }

    public void updateRecordingTime(String str) {
        FragmentTopAlert topAlert = getTopAlert();
        if (topAlert != null) {
            topAlert.updateRecordingTime(str);
        }
    }
}
