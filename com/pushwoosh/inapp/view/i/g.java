package com.pushwoosh.inapp.view.i;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class g implements d {
    private final Context a;
    private final long b;
    private Handler c = new Handler(Looper.getMainLooper());

    g(Context context, long j) {
        this.a = context;
        this.b = j;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private /* synthetic */ void a(Intent intent) {
        this.a.startActivity(intent);
    }

    @Override // com.pushwoosh.inapp.view.i.d
    public void a(b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]RichMediaViewStrategy", "resource is empty");
            return;
        }
        PWLog.debug("[InApp]RichMediaViewStrategy", "presenting richMedia with code: " + bVar.c() + ", url: " + bVar.j());
        this.c.postDelayed(new Runnable(RichMediaWebActivity.b(this.a, bVar)) {
            /* class com.pushwoosh.inapp.view.i.$$Lambda$g$MspLY9WlkW8pj7dultUAsOZT6XU */
            private final /* synthetic */ Intent f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                g.this.a((g) this.f$1);
            }
        }, this.b);
    }
}
