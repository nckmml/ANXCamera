package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.load.a.e;
import com.bumptech.glide.load.f;
import com.bumptech.glide.util.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: LoadPath */
public class n<Data, ResourceType, Transcode> {
    private final Class<Data> dataClass;
    private final Pool<List<Throwable>> fR;
    private final String fS;
    private final List<? extends f<Data, ResourceType, Transcode>> gP;

    public n(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<f<Data, ResourceType, Transcode>> list, Pool<List<Throwable>> pool) {
        this.dataClass = cls;
        this.fR = pool;
        this.gP = (List) i.b(list);
        StringBuilder sb = new StringBuilder();
        sb.append("Failed LoadPath{");
        sb.append(cls.getSimpleName());
        sb.append("->");
        sb.append(cls2.getSimpleName());
        sb.append("->");
        sb.append(cls3.getSimpleName());
        sb.append("}");
        this.fS = sb.toString();
    }

    private p<Transcode> a(e<Data> eVar, @NonNull f fVar, int i, int i2, a<ResourceType> aVar, List<Throwable> list) throws GlideException {
        p<Transcode> pVar;
        List<Throwable> list2 = list;
        int size = this.gP.size();
        p<Transcode> pVar2 = null;
        for (int i3 = 0; i3 < size; i3++) {
            try {
                pVar2 = ((f) this.gP.get(i3)).a(eVar, i, i2, fVar, aVar);
            } catch (GlideException e) {
                list2.add(e);
            }
            if (pVar2 != null) {
                break;
            }
        }
        if (pVar != null) {
            return pVar;
        }
        throw new GlideException(this.fS, (List<Throwable>) new ArrayList<Throwable>(list2));
    }

    public p<Transcode> a(e<Data> eVar, @NonNull f fVar, int i, int i2, a<ResourceType> aVar) throws GlideException {
        List list = (List) i.checkNotNull(this.fR.acquire());
        try {
            p<Transcode> a = a(eVar, fVar, i, i2, aVar, list);
            return a;
        } finally {
            this.fR.release(list);
        }
    }

    public Class<Data> aj() {
        return this.dataClass;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoadPath{decodePaths=");
        sb.append(Arrays.toString(this.gP.toArray()));
        sb.append('}');
        return sb.toString();
    }
}
