package com.ss.android.utils;

import android.text.TextUtils;
import java.io.File;

public class StringUtils {
    public static String parsePathSimpleName(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String[] split = str.split(File.separator);
        return split.length > 0 ? split[split.length - 1] : str2;
    }
}
