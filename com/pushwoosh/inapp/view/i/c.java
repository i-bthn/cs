package com.pushwoosh.inapp.view.i;

import android.content.Context;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.inapp.view.RemoteUrlActivity;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class c implements d {
    private final Context a;

    c(Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.inapp.view.i.d
    public void a(b bVar) {
        if (bVar == null) {
            PWLog.noise("[InApp]RemoteUrlDefaultViewStrategy", "resource is empty");
        } else if (!GeneralUtils.isNetworkAvailable()) {
            PWLog.error("[InApp]RemoteUrlDefaultViewStrategy", "Remote page error: network unavailable");
        } else {
            RemoteUrlActivity.a(this.a, bVar.j());
        }
    }
}
