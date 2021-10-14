package com.pushwoosh.inapp.view.inline;

import android.os.Parcel;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.inapp.j.c;
import com.pushwoosh.inapp.view.a;
import com.pushwoosh.inapp.view.d;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.tags.TagsBundle;

public class b implements a.AbstractC0011a {
    private com.pushwoosh.inapp.view.a a;
    private com.pushwoosh.inapp.j.l.b b;
    private c c;
    private InlineInAppView d;
    private boolean e;

    /* renamed from: com.pushwoosh.inapp.view.inline.b$b */
    public static class C0016b {
        private boolean a;

        C0016b(Parcel parcel) {
            this.a = parcel.readInt() != 1 ? false : true;
        }

        private C0016b(boolean z) {
            this.a = z;
        }

        public void a(Parcel parcel) {
            parcel.writeInt(this.a ? 1 : 0);
        }
    }

    b(InlineInAppView inlineInAppView, c cVar) {
        this.c = cVar;
        this.d = inlineInAppView;
    }

    /* access modifiers changed from: public */
    private void b(Result<com.pushwoosh.inapp.j.l.b, PostEventException> result) {
        this.b = result.getData();
        this.d.a(this.b);
        if (this.b != null && result.getException() == null) {
            this.a = new com.pushwoosh.inapp.view.a(this.b, this);
            this.a.execute(new Void[0]);
        }
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0011a
    public void a() {
    }

    @Override // com.pushwoosh.inapp.view.a.AbstractC0011a
    public void a(Result<com.pushwoosh.inapp.i.a, com.pushwoosh.inapp.g.a> result) {
        if (result.isSuccess()) {
            this.d.b(result.getData());
        }
    }

    public void a(C0016b bVar) {
        this.e = bVar.a;
    }

    public void a(String str) {
        if (str != null && !str.isEmpty()) {
            this.e = false;
            this.c.a("inlineInApp", new TagsBundle.Builder().putString("identifier", str).build(), new Callback() {
                /* class com.pushwoosh.inapp.view.inline.$$Lambda$b$vWPwt1AiXFe_tbjD6u4bgm8yAEE */

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    b.lambda$vWPwt1AiXFe_tbjD6u4bgm8yAEE(b.this, result);
                }
            });
        }
    }

    public C0016b b() {
        return new C0016b(this.e);
    }

    public void c() {
        com.pushwoosh.inapp.j.l.b bVar = this.b;
        if (bVar != null && !this.e) {
            EventBus.sendEvent(new d(bVar));
            this.e = true;
        }
    }
}
