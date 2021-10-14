package com.pushwoosh.badge.e.a.d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.pushwoosh.badge.e.a.b;
import java.util.Arrays;
import java.util.List;

public class a implements com.pushwoosh.badge.e.a.a {
    @Override // com.pushwoosh.badge.e.a.a
    public List<String> a() {
        return Arrays.asList("org.adw.launcher", "org.adwfreak.launcher");
    }

    @Override // com.pushwoosh.badge.e.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        Intent intent = new Intent("org.adw.launcher.counter.SEND");
        intent.putExtra("PNAME", componentName.getPackageName());
        intent.putExtra("CNAME", componentName.getClassName());
        intent.putExtra("COUNT", i);
        if (com.pushwoosh.badge.e.a.e.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new b("unable to resolve intent: " + intent.toString());
    }
}
