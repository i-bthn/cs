package com.pushwoosh.internal.platform.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.pushwoosh.e.a.a.a.b;
import com.pushwoosh.e.a.a.a.i.c;

/* access modifiers changed from: package-private */
public class a implements PrefsProvider {
    private final Context a;
    private final c b = $$Lambda$a$K3BMiNmSheWPkotsQxmww9qJo3I.INSTANCE;

    a(Context context) {
        this.a = context;
    }

    @Override // com.pushwoosh.internal.platform.prefs.PrefsProvider
    public SharedPreferences provideDefault() {
        return new b(this.a).a("pushwoosh_default").a(false).a(this.b).a();
    }

    @Override // com.pushwoosh.internal.platform.prefs.PrefsProvider
    public SharedPreferences providePrefs(String str) {
        return new b(this.a).a(str).a(false).a(this.b).a();
    }
}
