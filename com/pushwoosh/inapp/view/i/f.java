package com.pushwoosh.inapp.view.i;

import android.content.Context;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class f implements d {
    private final Context a;
    private final String b;

    f(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // com.pushwoosh.inapp.view.i.d
    public void a(b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]RichMediaLockScreenViewStrategy", "resource is empty");
            return;
        }
        PWLog.debug("[InApp]RichMediaLockScreenViewStrategy", "presenting richMedia with code: " + bVar.c() + ", url: " + bVar.j());
        Context context = this.a;
        context.startActivity(RichMediaWebActivity.a(context, bVar, this.b));
    }
}
