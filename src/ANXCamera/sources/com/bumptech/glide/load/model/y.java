package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.g;
import java.io.InputStream;

/* compiled from: ResourceLoader */
public class y<Data> implements t<Integer, Data> {
    private static final String TAG = "ResourceLoader";
    private final t<Uri, Data> ki;
    private final Resources resources;

    /* compiled from: ResourceLoader */
    public static final class a implements u<Integer, AssetFileDescriptor> {
        private final Resources resources;

        public a(Resources resources2) {
            this.resources = resources2;
        }

        public void D() {
        }

        public t<Integer, AssetFileDescriptor> a(x xVar) {
            return new y(this.resources, xVar.a(Uri.class, AssetFileDescriptor.class));
        }
    }

    /* compiled from: ResourceLoader */
    public static class b implements u<Integer, ParcelFileDescriptor> {
        private final Resources resources;

        public b(Resources resources2) {
            this.resources = resources2;
        }

        public void D() {
        }

        @NonNull
        public t<Integer, ParcelFileDescriptor> a(x xVar) {
            return new y(this.resources, xVar.a(Uri.class, ParcelFileDescriptor.class));
        }
    }

    /* compiled from: ResourceLoader */
    public static class c implements u<Integer, InputStream> {
        private final Resources resources;

        public c(Resources resources2) {
            this.resources = resources2;
        }

        public void D() {
        }

        @NonNull
        public t<Integer, InputStream> a(x xVar) {
            return new y(this.resources, xVar.a(Uri.class, InputStream.class));
        }
    }

    /* compiled from: ResourceLoader */
    public static class d implements u<Integer, Uri> {
        private final Resources resources;

        public d(Resources resources2) {
            this.resources = resources2;
        }

        public void D() {
        }

        @NonNull
        public t<Integer, Uri> a(x xVar) {
            return new y(this.resources, B.getInstance());
        }
    }

    public y(Resources resources2, t<Uri, Data> tVar) {
        this.resources = resources2;
        this.ki = tVar;
    }

    @Nullable
    private Uri e(Integer num) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("android.resource://");
            sb.append(this.resources.getResourcePackageName(num.intValue()));
            sb.append('/');
            sb.append(this.resources.getResourceTypeName(num.intValue()));
            sb.append('/');
            sb.append(this.resources.getResourceEntryName(num.intValue()));
            return Uri.parse(sb.toString());
        } catch (NotFoundException e2) {
            String str = TAG;
            if (Log.isLoggable(str, 5)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Received invalid resource id: ");
                sb2.append(num);
                Log.w(str, sb2.toString(), e2);
            }
            return null;
        }
    }

    public com.bumptech.glide.load.model.t.a<Data> a(@NonNull Integer num, int i, int i2, @NonNull g gVar) {
        Uri e2 = e(num);
        if (e2 == null) {
            return null;
        }
        return this.ki.a(e2, i, i2, gVar);
    }

    public boolean c(@NonNull Integer num) {
        return true;
    }
}
