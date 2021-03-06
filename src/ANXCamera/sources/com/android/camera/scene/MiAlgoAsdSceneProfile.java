package com.android.camera.scene;

import com.android.camera.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class MiAlgoAsdSceneProfile {
    public static final int AI_SCENE_DETECTED = 3;
    public static final int AI_SCENE_MODE_FOOD = generateCompatAiASDType(3, 1000);
    private static final int AI_SCENE_SHIFT = 1000;
    public static final int BACKLIT = 2;
    public static final int DIRTY = 6;
    public static final int MACRO = 7;
    public static final float MI_ALGO_ASD_VERSION_2_0 = 2.0f;
    public static final int NONE = 0;
    public static final int NON_SEMANTIC_CLASS = 1;
    public static final int ON_TRIPOD = 4;
    public static final int PORTRAIT = 1;
    public static final int SEMANTICS_CLASS = 0;
    public static final int STATE_CLASS = 2;
    public static final int SUPER_NIGHT = 3;
    public static final int ULTRA_WIDE = generateCompatAiASDType(2, 2000);
    private static final int ULTRA_WIDE_SCENE_SHIFT = 2000;
    private static List<MiScene> sSceneList = new ArrayList();

    @Retention(RetentionPolicy.SOURCE)
    public @interface MialgoAsdSceneClass {
    }

    static {
        MiScene create = MiScene.create();
        create.type = 1;
        create.valueArray.put(1, Integer.valueOf(R.string.recommend_portrait));
        sSceneList.add(create);
        MiScene create2 = MiScene.create();
        create2.type = 3;
        create2.valueArray.put(1, Integer.valueOf(R.string.recommend_super_night));
        sSceneList.add(create2);
        MiScene create3 = MiScene.create();
        create3.type = 2;
        sSceneList.add(create3);
        MiScene create4 = MiScene.create();
        create4.type = 4;
        sSceneList.add(create4);
        MiScene create5 = MiScene.create();
        create5.type = 6;
        sSceneList.add(create5);
        MiScene create6 = MiScene.create();
        create6.type = 7;
        create6.valueArray.put(1, Integer.valueOf(R.string.recommend_macro_mode));
        sSceneList.add(create6);
        MiScene create7 = MiScene.create();
        create7.type = AI_SCENE_MODE_FOOD;
        sSceneList.add(create7);
        MiScene create8 = MiScene.create();
        create8.type = ULTRA_WIDE;
        sSceneList.add(create8);
    }

    public static void clearInitASDScenes() {
        List<MiScene> list = sSceneList;
        if (list != null) {
            for (MiScene miScene : list) {
                miScene.setEnable(false);
                miScene.isChange(0.0f);
            }
        }
    }

    private static int generateCompatAiASDType(int i, int i2) {
        return i | i2;
    }

    public static int getSceneTipResId(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return ((Integer) ((MiScene) sSceneList.get(0)).valueArray.get(0)).intValue();
        }
        for (MiScene miScene : sSceneList) {
            if (miScene.type == i) {
                Integer num = (Integer) miScene.valueArray.get(i2);
                if (num != null) {
                    return num.intValue();
                }
            }
        }
        return ((Integer) ((MiScene) sSceneList.get(0)).valueArray.get(0)).intValue();
    }

    public static boolean isAlreadyTip() {
        for (MiScene isEnable : sSceneList) {
            if (isEnable.isEnable()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCheckSceneEnable(int i, int i2) {
        return i > 0 && i2 > 0;
    }

    public static boolean isSceneChange(int i, int i2) {
        List<MiScene> list = sSceneList;
        if (list != null) {
            for (MiScene miScene : list) {
                if (miScene.type == i) {
                    return miScene.isChange((float) i2);
                }
            }
        }
        return false;
    }

    public static boolean isTipEnable(int i) {
        List<MiScene> list = sSceneList;
        if (list != null) {
            for (MiScene miScene : list) {
                if (miScene.type == i) {
                    return miScene.isEnable();
                }
            }
        }
        return false;
    }

    public static void setTipEnable(int i, boolean z) {
        List<MiScene> list = sSceneList;
        if (list != null) {
            for (MiScene miScene : list) {
                if (miScene.type == i) {
                    miScene.setEnable(z);
                    return;
                }
            }
        }
    }
}
