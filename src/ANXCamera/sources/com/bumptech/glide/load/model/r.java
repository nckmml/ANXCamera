package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.f;
import java.io.InputStream;

/* compiled from: ResourceLoader */
public class r<Data> implements m<Integer, Data> {
    private static final String TAG = "ResourceLoader";
    private final m<Uri, Data> ko;
    private final Resources kp;

    /* compiled from: ResourceLoader */
    public static final class a implements n<Integer, AssetFileDescriptor> {
        private final Resources kp;

        public a(Resources resources) {
            this.kp = resources;
        }

        public m<Integer, AssetFileDescriptor> a(q qVar) {
            return new r(this.kp, qVar.b(Uri.class, AssetFileDescriptor.class));
        }

        public void bX() {
        }
    }

    /* compiled from: ResourceLoader */
    public static class b implements n<Integer, ParcelFileDescriptor> {
        private final Resources kp;

        public b(Resources resources) {
            this.kp = resources;
        }

        @NonNull
        public m<Integer, ParcelFileDescriptor> a(q qVar) {
            return new r(this.kp, qVar.b(Uri.class, ParcelFileDescriptor.class));
        }

        public void bX() {
        }
    }

    /* compiled from: ResourceLoader */
    public static class c implements n<Integer, InputStream> {
        private final Resources kp;

        public c(Resources resources) {
            this.kp = resources;
        }

        @NonNull
        public m<Integer, InputStream> a(q qVar) {
            return new r(this.kp, qVar.b(Uri.class, InputStream.class));
        }

        public void bX() {
        }
    }

    /* compiled from: ResourceLoader */
    public static class d implements n<Integer, Uri> {
        private final Resources kp;

        public d(Resources resources) {
            this.kp = resources;
        }

        @NonNull
        public m<Integer, Uri> a(q qVar) {
            return new r(this.kp, u.ck());
        }

        public void bX() {
        }
    }

    public r(Resources resources, m<Uri, Data> mVar) {
        this.kp = resources;
        this.ko = mVar;
    }

    @Nullable
    private Uri d(Integer num) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("android.resource://");
            sb.append(this.kp.getResourcePackageName(num.intValue()));
            sb.append('/');
            sb.append(this.kp.getResourceTypeName(num.intValue()));
            sb.append('/');
            sb.append(this.kp.getResourceEntryName(num.intValue()));
            return Uri.parse(sb.toString());
        } catch (NotFoundException e) {
            if (Log.isLoggable(TAG, 5)) {
                String str = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Received invalid resource id: ");
                sb2.append(num);
                Log.w(str, sb2.toString(), e);
            }
            return null;
        }
    }

    /* renamed from: a */
    public com.bumptech.glide.load.model.m.a<Data> b(@NonNull Integer num, int i, int i2, @NonNull f fVar) {
        Uri d2 = d(num);
        if (d2 == null) {
            return null;
        }
        return this.ko.b(d2, i, i2, fVar);
    }

    /* renamed from: e */
    public boolean q(@NonNull Integer num) {
        return true;
    }
}
