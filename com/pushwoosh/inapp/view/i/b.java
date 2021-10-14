package com.pushwoosh.inapp.view.i;

import android.content.Context;
import androidx.annotation.Nullable;
import com.pushwoosh.inapp.view.RichMediaWebActivity;
import com.pushwoosh.internal.utils.PWLog;

/* access modifiers changed from: package-private */
public class b implements d {
    private Context a;

    b(Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.inapp.view.i.d
    public void a(@Nullable com.pushwoosh.inapp.j.l.b bVar) {
        if (bVar == null) {
            PWLog.noise("InAppRequiredViewStrategy", "resource is empty");
            return;
        }
        Context context = this.a;
        context.startActivity(RichMediaWebActivity.a(context, bVar));
    }
}
