package com.mi.config;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.SystemProperties;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.android.camera.CameraAppImpl;
import com.android.camera.Util;
import com.android.camera.data.data.DataItemBase;
import com.android.camera.log.Log;
import com.ss.android.ttve.BuildConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Locale;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DataItemFeature */
public class a extends DataItemBase implements c {
    private static final String TAG = "DataFeature";
    private String qn;

    public a() {
        eU();
    }

    private void J(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        Iterator keys = jSONObject.keys();
        SimpleArrayMap values = getValues();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            values.put(str2, jSONObject.opt(str2));
        }
    }

    public boolean K(String str) {
        return getValues().containsKey(str);
    }

    public String L(String str) {
        return getString(c.tP, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c A[SYNTHETIC, Splitter:B:25:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A[SYNTHETIC, Splitter:B:31:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0082 A[SYNTHETIC, Splitter:B:37:0x0082] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x0071=Splitter:B:28:0x0071, B:22:0x0067=Splitter:B:22:0x0067} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void eU() {
        Context androidContext = CameraAppImpl.getAndroidContext();
        Resources resources = androidContext.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("feature_");
        sb.append(b.qo);
        int identifier = resources.getIdentifier(sb.toString(), "raw", "com.android.camera");
        if (identifier <= 0) {
            Log.e(TAG, "feature list default");
            return;
        }
        BufferedReader bufferedReader = null;
        StringBuilder sb2 = new StringBuilder();
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(androidContext.getResources().openRawResource(identifier)));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb2.append(readLine);
                } catch (IOException e) {
                    e = e;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                    }
                } catch (JSONException e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    try {
                        e.printStackTrace();
                        if (bufferedReader != null) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                    }
                    throw th;
                }
            }
            J(sb2.toString());
            try {
                bufferedReader2.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (IOException e5) {
            e = e5;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (JSONException e6) {
            e = e6;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    public boolean eV() {
        return getBoolean(c.rT, false);
    }

    public int eW() {
        return getInt(c.rU, 20);
    }

    public boolean eX() {
        if (this.qn == null) {
            this.qn = SystemProperties.get("ro.boot.hwc");
        }
        if ("india".equalsIgnoreCase(this.qn)) {
            return true;
        }
        return !TextUtils.isEmpty(this.qn) && this.qn.toLowerCase(Locale.ENGLISH).startsWith("india_");
    }

    public boolean eY() {
        return Build.getRegion().endsWith("IN");
    }

    public boolean eZ() {
        if (this.qn == null) {
            this.qn = SystemProperties.get("ro.boot.hwc");
        }
        return "cn".equalsIgnoreCase(this.qn);
    }

    public boolean fA() {
        return getBoolean(c.sq, false);
    }

    public boolean fB() {
        return getBoolean(c.so, false);
    }

    public boolean fC() {
        return getBoolean(c.sp, false) || fB();
    }

    public boolean fD() {
        return getBoolean(c.sv, false);
    }

    public String fE() {
        return getString(c.sw, "");
    }

    public int fF() {
        return getInt(c.sx, 350);
    }

    public int fG() {
        return getInt(c.sy, 300);
    }

    public boolean fH() {
        return getBoolean(c.sE, true);
    }

    public boolean fI() {
        return getBoolean(c.sL, false);
    }

    public boolean fJ() {
        return getBoolean(c.tf, false);
    }

    public boolean fK() {
        return getBoolean(c.th, true);
    }

    public boolean fL() {
        if (Util.isGlobalVersion()) {
            return false;
        }
        return getBoolean(c.sH, false);
    }

    public int fM() {
        return getInt(c.sI, 280);
    }

    public float fN() {
        return (float) getDoubleFromValues(c.sJ, 0.8766000270843506d);
    }

    public boolean fO() {
        if (Util.isGlobalVersion()) {
            return false;
        }
        return getBoolean(c.sK, false);
    }

    public boolean fP() {
        return getBoolean(c.tJ, false);
    }

    public boolean fQ() {
        return getBoolean(c.sF, false);
    }

    public boolean fR() {
        return getBoolean(c.ts, false);
    }

    public boolean fS() {
        return getBoolean(c.sP, false);
    }

    public boolean fT() {
        return getBoolean(c.sQ, false);
    }

    public boolean fU() {
        return getBoolean(c.sR, false);
    }

    public boolean fV() {
        return getBoolean(c.sZ, true);
    }

    public boolean fW() {
        return getBoolean(c.ta, false);
    }

    public int fX() {
        return getInt(c.tc, 0);
    }

    public boolean fY() {
        if (!Util.isGlobalVersion()) {
            return false;
        }
        return getBoolean(c.ti, false);
    }

    public boolean fZ() {
        return getBoolean(c.sV, false);
    }

    public boolean fa() {
        return getBoolean(c.rV, false) && eX();
    }

    public boolean fb() {
        return getBoolean(c.sT, false) && eX();
    }

    public boolean fc() {
        return getBoolean(c.rW, false);
    }

    public boolean fd() {
        return getBoolean(c.rX, false);
    }

    public boolean fe() {
        return getBoolean(c.rY, false);
    }

    public boolean ff() {
        return getBoolean(c.sC, false);
    }

    public boolean fg() {
        return getBoolean(c.rZ, false);
    }

    public boolean fh() {
        return getBoolean(c.sb, false);
    }

    public boolean fi() {
        return (eX() || eY()) && getBoolean(c.sc, false);
    }

    public boolean fj() {
        return getBoolean(c.sf, true);
    }

    public boolean fk() {
        return getBoolean(c.se, false);
    }

    public boolean fl() {
        return getBoolean(c.sg, false);
    }

    public boolean fm() {
        return getBoolean(c.sh, false);
    }

    public boolean fn() {
        return getBoolean(c.si, false);
    }

    public boolean fo() {
        return ((float) Util.sWindowHeight) / ((float) Util.sWindowWidth) >= 2.16f && getBoolean(c.sj, false);
    }

    public boolean fp() {
        return getBoolean(c.sk, false);
    }

    public boolean fq() {
        return getBoolean(c.sl, false);
    }

    public boolean fr() {
        return VERSION.SDK_INT == 28 && getBoolean(c.ss, false);
    }

    public boolean fs() {
        return getBoolean(c.st, false);
    }

    public boolean ft() {
        return getBoolean(c.sz, true);
    }

    public boolean fu() {
        return getBoolean(c.sA, false);
    }

    public boolean fv() {
        return getBoolean(c.su, false);
    }

    public boolean fw() {
        return getBoolean(c.sm, false);
    }

    public int fx() {
        return getInt(c.sS, 180);
    }

    public boolean fy() {
        return getBoolean(c.sn, false);
    }

    public boolean fz() {
        return getBoolean(c.sr, false);
    }

    public boolean gA() {
        return gb() == 48000000 && gy();
    }

    public boolean gB() {
        return getBoolean(c.tG, true);
    }

    public boolean gC() {
        return getBoolean(c.tH, true);
    }

    public boolean gD() {
        return getBoolean(c.tI, true);
    }

    public boolean gE() {
        return getBoolean(c.tK, false);
    }

    public boolean gF() {
        return getBoolean(c.tL, false);
    }

    public String gG() {
        return getString(c.tM, BuildConfig.FLAVOR);
    }

    public boolean gH() {
        return getBoolean(c.tO, false);
    }

    public boolean gI() {
        return getBoolean(c.tS, false);
    }

    public boolean gJ() {
        return getBoolean(c.tT, false);
    }

    public boolean ga() {
        return gb() <= 0 || getBoolean(c.sW, false);
    }

    public int gb() {
        return getInt(c.sU, 0);
    }

    public int gd() {
        return getInt(c.tb, 0);
    }

    public boolean ge() {
        return getBoolean(c.td, false);
    }

    public boolean gf() {
        return getBoolean(c.te, false);
    }

    public boolean gg() {
        if (b.iG()) {
            return false;
        }
        return getBoolean(c.tN, true);
    }

    public boolean gh() {
        return getBoolean(c.tj, true);
    }

    public boolean gi() {
        return getBoolean(c.tk, false);
    }

    public boolean gj() {
        if (VERSION.SDK_INT < 28) {
            return false;
        }
        return getBoolean(c.tm, false);
    }

    public boolean gk() {
        return getBoolean(c.tn, false);
    }

    public boolean gl() {
        return getBoolean(c.to, false);
    }

    public boolean gm() {
        return getBoolean(c.tp, false);
    }

    public boolean gn() {
        return getBoolean(c.tq, false);
    }

    public boolean go() {
        return getBoolean(c.tr, true);
    }

    public boolean gp() {
        return getBoolean(c.tt, false);
    }

    public boolean gq() {
        return getBoolean(c.tu, false);
    }

    public boolean gr() {
        return getBoolean(c.sG, false);
    }

    public boolean gs() {
        return getBoolean(c.tv, true);
    }

    public boolean gt() {
        return getBoolean(c.tw, false);
    }

    public boolean gu() {
        return getBoolean(c.tz, false);
    }

    public long gv() {
        return (long) getInt(c.tx, -1);
    }

    public int gw() {
        return getInt(c.ty, -1);
    }

    public boolean gx() {
        return getBoolean(c.tD, false);
    }

    public boolean gy() {
        return getBoolean(c.tF, true);
    }

    public boolean gz() {
        return getBoolean(c.tR, false);
    }

    /* access modifiers changed from: protected */
    public boolean isMutable() {
        return false;
    }

    public boolean isSupport4KUHDEIS() {
        return getBoolean(c.sO, false);
    }

    public boolean isSupportBeautyBody() {
        return getBoolean(c.sD, false);
    }

    public boolean isSupportBokehAdjust() {
        return getBoolean(c.tl, false);
    }

    public boolean isSupportMacroMode() {
        return getBoolean(c.tE, false);
    }

    public boolean isSupportNormalWideLDC() {
        return getBoolean(c.sM, false);
    }

    public boolean isSupportShortVideoBeautyBody() {
        return getBoolean(c.tA, false);
    }

    public boolean isSupportUltraWide() {
        return getBoolean(c.sB, false);
    }

    public boolean isSupportUltraWideLDC() {
        return getBoolean(c.sN, false);
    }

    public boolean isTransient() {
        return true;
    }

    public String p(boolean z) {
        return z ? getString(c.tB, "4.5") : getString(c.tC, "4");
    }

    public String provideKey() {
        return null;
    }

    public boolean q(boolean z) {
        return z && getBoolean(c.tQ, false);
    }
}
