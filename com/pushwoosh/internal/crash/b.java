package com.pushwoosh.internal.crash;

import com.pushwoosh.Pushwoosh;
import com.pushwoosh.PushwooshPlatform;
import java.util.Collections;
import java.util.List;

/* access modifiers changed from: package-private */
public final class b {
    static final List<String> a = Collections.singletonList("com.pushwoosh");

    static String a() {
        return Pushwoosh.getInstance().getApplicationCode();
    }

    static String b() {
        return PushwooshPlatform.getInstance().c().m().getPluginType();
    }
}
