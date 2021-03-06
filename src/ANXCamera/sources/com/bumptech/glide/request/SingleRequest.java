package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.A;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.Engine.b;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.n;
import com.bumptech.glide.request.target.o;
import com.bumptech.glide.util.a.d;
import com.bumptech.glide.util.a.d.a;
import com.bumptech.glide.util.a.d.c;
import com.bumptech.glide.util.a.g;
import com.bumptech.glide.util.l;

public final class SingleRequest<R> implements c, n, g, c {
    private static final Pool<SingleRequest<?>> Rf = d.a(150, (a<T>) new h<T>());
    private static final String TAG = "Request";
    private static final String sl = "Glide";
    private static final boolean tl = Log.isLoggable(TAG, 2);
    private e Eb;
    private Drawable Hk;
    private int Jk;
    private int Kk;
    private Drawable Mk;
    private final g Re;
    private Context context;
    private int height;
    private boolean ml;
    @Nullable
    private Object model;
    @Nullable
    private e<R> nl;
    private d ol;
    private com.bumptech.glide.request.a.g<? super R> pl;
    private Priority priority;
    private b ql;
    private A<R> resource;
    private Drawable rl;
    private long startTime;
    private Status status;
    @Nullable
    private final String tag;
    private o<R> target;
    private Class<R> uc;
    private Engine va;
    private f vc;
    private int width;
    private e<R> xc;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    SingleRequest() {
        this.tag = tl ? String.valueOf(super.hashCode()) : null;
        this.Re = g.newInstance();
    }

    private void E(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" this: ");
        sb.append(this.tag);
        Log.v(TAG, sb.toString());
    }

    private void Rk() {
        if (this.ml) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    private boolean Sk() {
        d dVar = this.ol;
        return dVar == null || dVar.g(this);
    }

    private Drawable T(@DrawableRes int i) {
        return com.bumptech.glide.load.b.b.a.a((Context) this.Eb, i, this.vc.getTheme() != null ? this.vc.getTheme() : this.context.getTheme());
    }

    private boolean Tk() {
        d dVar = this.ol;
        return dVar == null || dVar.c(this);
    }

    private boolean Uk() {
        d dVar = this.ol;
        return dVar == null || dVar.d(this);
    }

    private Drawable Vk() {
        if (this.rl == null) {
            this.rl = this.vc._g();
            if (this.rl == null && this.vc.Zg() > 0) {
                this.rl = T(this.vc.Zg());
            }
        }
        return this.rl;
    }

    private boolean Wk() {
        d dVar = this.ol;
        return dVar == null || !dVar.k();
    }

    private void Xk() {
        d dVar = this.ol;
        if (dVar != null) {
            dVar.e(this);
        }
    }

    private void Yk() {
        d dVar = this.ol;
        if (dVar != null) {
            dVar.b(this);
        }
    }

    private void Zk() {
        if (Tk()) {
            Drawable drawable = null;
            if (this.model == null) {
                drawable = ah();
            }
            if (drawable == null) {
                drawable = Vk();
            }
            if (drawable == null) {
                drawable = gh();
            }
            this.target.d(drawable);
        }
    }

    private static int a(int i, float f2) {
        return i == Integer.MIN_VALUE ? i : Math.round(f2 * ((float) i));
    }

    public static <R> SingleRequest<R> a(Context context2, e eVar, Object obj, Class<R> cls, f fVar, int i, int i2, Priority priority2, o<R> oVar, e<R> eVar2, e<R> eVar3, d dVar, Engine engine, com.bumptech.glide.request.a.g<? super R> gVar) {
        SingleRequest<R> singleRequest = (SingleRequest) Rf.acquire();
        if (singleRequest == null) {
            singleRequest = new SingleRequest<>();
        }
        singleRequest.b(context2, eVar, obj, cls, fVar, i, i2, priority2, oVar, eVar2, eVar3, dVar, engine, gVar);
        return singleRequest;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0092, code lost:
        if (r7.nl.a(r9, r7.model, r7.target, r10, r6) == false) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x007f, code lost:
        if (r7.xc.a(r9, r7.model, r7.target, r10, r6) == false) goto L_0x0081;
     */
    private void a(A<R> a2, R r, DataSource dataSource) {
        boolean Wk = Wk();
        this.status = Status.COMPLETE;
        this.resource = a2;
        if (this.Eb.getLogLevel() <= 3) {
            StringBuilder sb = new StringBuilder();
            sb.append("Finished loading ");
            sb.append(r.getClass().getSimpleName());
            sb.append(" from ");
            sb.append(dataSource);
            sb.append(" for ");
            sb.append(this.model);
            sb.append(" with size [");
            sb.append(this.width);
            sb.append("x");
            sb.append(this.height);
            sb.append("] in ");
            sb.append(com.bumptech.glide.util.e.g(this.startTime));
            sb.append(" ms");
            Log.d(sl, sb.toString());
        }
        this.ml = true;
        try {
            if (this.xc != null) {
            }
            if (this.nl != null) {
            }
            this.target.a(r, this.pl.a(dataSource, Wk));
            this.ml = false;
            Yk();
        } catch (Throwable th) {
            this.ml = false;
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private void a(GlideException glideException, int i) {
        this.Re.Mh();
        int logLevel = this.Eb.getLogLevel();
        if (logLevel <= i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Load failed for ");
            sb.append(this.model);
            sb.append(" with size [");
            sb.append(this.width);
            sb.append("x");
            sb.append(this.height);
            sb.append("]");
            String sb2 = sb.toString();
            String str = sl;
            Log.w(str, sb2, glideException);
            if (logLevel <= 4) {
                glideException.x(str);
            }
        }
        this.ql = null;
        this.status = Status.FAILED;
        this.ml = true;
        try {
            if ((this.xc == null || !this.xc.a(glideException, this.model, this.target, Wk())) && (this.nl == null || !this.nl.a(glideException, this.model, this.target, Wk()))) {
                Zk();
            }
            this.ml = false;
            Xk();
        } catch (Throwable th) {
            this.ml = false;
            throw th;
        }
    }

    private Drawable ah() {
        if (this.Mk == null) {
            this.Mk = this.vc.ah();
            if (this.Mk == null && this.vc.bh() > 0) {
                this.Mk = T(this.vc.bh());
            }
        }
        return this.Mk;
    }

    private void b(Context context2, e eVar, Object obj, Class<R> cls, f fVar, int i, int i2, Priority priority2, o<R> oVar, e<R> eVar2, e<R> eVar3, d dVar, Engine engine, com.bumptech.glide.request.a.g<? super R> gVar) {
        this.context = context2;
        this.Eb = eVar;
        this.model = obj;
        this.uc = cls;
        this.vc = fVar;
        this.Kk = i;
        this.Jk = i2;
        this.priority = priority2;
        this.target = oVar;
        this.nl = eVar2;
        this.xc = eVar3;
        this.ol = dVar;
        this.va = engine;
        this.pl = gVar;
        this.status = Status.PENDING;
    }

    private Drawable gh() {
        if (this.Hk == null) {
            this.Hk = this.vc.gh();
            if (this.Hk == null && this.vc.hh() > 0) {
                this.Hk = T(this.vc.hh());
            }
        }
        return this.Hk;
    }

    private void m(A<?> a2) {
        this.va.e(a2);
        this.resource = null;
    }

    public void a(A<?> a2, DataSource dataSource) {
        this.Re.Mh();
        this.ql = null;
        if (a2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected to receive a Resource<R> with an object of ");
            sb.append(this.uc);
            sb.append(" inside, but instead got null.");
            a(new GlideException(sb.toString()));
            return;
        }
        Object obj = a2.get();
        if (obj == null || !this.uc.isAssignableFrom(obj.getClass())) {
            m(a2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected to receive an object of ");
            sb2.append(this.uc);
            sb2.append(" but instead got ");
            String str = "";
            sb2.append(obj != null ? obj.getClass() : str);
            sb2.append("{");
            sb2.append(obj);
            sb2.append("} inside Resource{");
            sb2.append(a2);
            sb2.append("}.");
            if (obj == null) {
                str = " To indicate failure return a null Resource object, rather than a Resource object containing null data.";
            }
            sb2.append(str);
            a(new GlideException(sb2.toString()));
        } else if (!Uk()) {
            m(a2);
            this.status = Status.COMPLETE;
        } else {
            a(a2, obj, dataSource);
        }
    }

    public void a(GlideException glideException) {
        a(glideException, 5);
    }

    public boolean a(c cVar) {
        if (!(cVar instanceof SingleRequest)) {
            return false;
        }
        SingleRequest singleRequest = (SingleRequest) cVar;
        if (this.Kk != singleRequest.Kk || this.Jk != singleRequest.Jk || !l.c(this.model, singleRequest.model) || !this.uc.equals(singleRequest.uc) || !this.vc.equals(singleRequest.vc) || this.priority != singleRequest.priority) {
            return false;
        }
        if (this.xc != null) {
            if (singleRequest.xc == null) {
                return false;
            }
        } else if (singleRequest.xc != null) {
            return false;
        }
        return true;
    }

    public void b(int i, int i2) {
        this.Re.Mh();
        if (tl) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got onSizeReady in ");
            sb.append(com.bumptech.glide.util.e.g(this.startTime));
            E(sb.toString());
        }
        if (this.status == Status.WAITING_FOR_SIZE) {
            this.status = Status.RUNNING;
            float ih = this.vc.ih();
            this.width = a(i, ih);
            this.height = a(i2, ih);
            if (tl) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("finished setup for calling load in ");
                sb2.append(com.bumptech.glide.util.e.g(this.startTime));
                E(sb2.toString());
            }
            Engine engine = this.va;
            e eVar = this.Eb;
            b a2 = engine.a(eVar, this.model, this.vc.getSignature(), this.width, this.height, this.vc.z(), this.uc, this.priority, this.vc.eg(), this.vc.getTransformations(), this.vc.rh(), this.vc.jg(), this.vc.getOptions(), this.vc.nh(), this.vc.kh(), this.vc.jh(), this.vc.dh(), this);
            this.ql = a2;
            if (this.status != Status.RUNNING) {
                this.ql = null;
            }
            if (tl) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("finished onSizeReady in ");
                sb3.append(com.bumptech.glide.util.e.g(this.startTime));
                E(sb3.toString());
            }
        }
    }

    public void begin() {
        Rk();
        this.Re.Mh();
        this.startTime = com.bumptech.glide.util.e.Gh();
        if (this.model == null) {
            if (l.o(this.Kk, this.Jk)) {
                this.width = this.Kk;
                this.height = this.Jk;
            }
            a(new GlideException("Received null model"), ah() == null ? 5 : 3);
            return;
        }
        Status status2 = this.status;
        if (status2 == Status.RUNNING) {
            throw new IllegalArgumentException("Cannot restart a running request");
        } else if (status2 == Status.COMPLETE) {
            a(this.resource, DataSource.MEMORY_CACHE);
        } else {
            this.status = Status.WAITING_FOR_SIZE;
            if (l.o(this.Kk, this.Jk)) {
                b(this.Kk, this.Jk);
            } else {
                this.target.b((n) this);
            }
            Status status3 = this.status;
            if ((status3 == Status.RUNNING || status3 == Status.WAITING_FOR_SIZE) && Tk()) {
                this.target.c(gh());
            }
            if (tl) {
                StringBuilder sb = new StringBuilder();
                sb.append("finished run method in ");
                sb.append(com.bumptech.glide.util.e.g(this.startTime));
                E(sb.toString());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void cancel() {
        Rk();
        this.Re.Mh();
        this.target.a(this);
        this.status = Status.CANCELLED;
        b bVar = this.ql;
        if (bVar != null) {
            bVar.cancel();
            this.ql = null;
        }
    }

    public void clear() {
        l.Ih();
        Rk();
        this.Re.Mh();
        if (this.status != Status.CLEARED) {
            cancel();
            A<R> a2 = this.resource;
            if (a2 != null) {
                m(a2);
            }
            if (Sk()) {
                this.target.b(gh());
            }
            this.status = Status.CLEARED;
        }
    }

    @NonNull
    public g getVerifier() {
        return this.Re;
    }

    public boolean isCancelled() {
        Status status2 = this.status;
        return status2 == Status.CANCELLED || status2 == Status.CLEARED;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    public boolean isPaused() {
        return this.status == Status.PAUSED;
    }

    public boolean isRunning() {
        Status status2 = this.status;
        return status2 == Status.RUNNING || status2 == Status.WAITING_FOR_SIZE;
    }

    public boolean l() {
        return isComplete();
    }

    public void pause() {
        clear();
        this.status = Status.PAUSED;
    }

    public void recycle() {
        Rk();
        this.context = null;
        this.Eb = null;
        this.model = null;
        this.uc = null;
        this.vc = null;
        this.Kk = -1;
        this.Jk = -1;
        this.target = null;
        this.xc = null;
        this.nl = null;
        this.ol = null;
        this.pl = null;
        this.ql = null;
        this.rl = null;
        this.Hk = null;
        this.Mk = null;
        this.width = -1;
        this.height = -1;
        Rf.release(this);
    }
}
