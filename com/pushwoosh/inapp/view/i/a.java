package com.pushwoosh.inapp.view.i;

import android.content.Context;
import android.os.AsyncTask;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.inapp.k.c;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public class a implements d {
    private final Context a;
    private final c b;

    /* access modifiers changed from: private */
    /* renamed from: com.pushwoosh.inapp.view.i.a$a  reason: collision with other inner class name */
    public interface AbstractC0013a {
        void a();
    }

    private static class b extends AsyncTask<Void, Void, Boolean> {
        private final WeakReference<a> a;
        private final com.pushwoosh.inapp.j.l.b b;
        private final AbstractC0013a c;

        public b(a aVar, com.pushwoosh.inapp.j.l.b bVar, AbstractC0013a aVar2) {
            this.a = new WeakReference<>(aVar);
            this.b = bVar;
            this.c = aVar2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(this.a.get() != null ? this.a.get().b.b(this.b.c()) : false);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (!bool.booleanValue() || this.a.get() == null) {
                this.c.a();
                return;
            }
            Context context = this.a.get().a;
            context.startActivity(RichMediaWebActivity.a(context, this.b));
        }
    }

    a(Context context, c cVar) {
        this.a = context;
        this.b = cVar;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(com.pushwoosh.inapp.j.l.b bVar) {
        if (this.b.b(bVar.c())) {
            Context context = this.a;
            context.startActivity(RichMediaWebActivity.a(context, bVar));
            return;
        }
        PWLog.noise("[InApp]InAppDefaultViewStrategy", "resource is not downloaded, abort show inApp");
    }

    @Override // com.pushwoosh.inapp.view.i.d
    public void a(com.pushwoosh.inapp.j.l.b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]InAppDefaultViewStrategy", "resource is empty");
        } else {
            new b(this, bVar, new AbstractC0013a(bVar) {
                /* class com.pushwoosh.inapp.view.i.$$Lambda$a$3C_JsZOVc1O4sMyCizLzS2F8zpk */
                private final /* synthetic */ b f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.pushwoosh.inapp.view.i.a.AbstractC0013a
                public final void a() {
                    a.lambda$3C_JsZOVc1O4sMyCizLzS2F8zpk(a.this, this.f$1);
                }
            }).execute(new Void[0]);
        }
    }
}
