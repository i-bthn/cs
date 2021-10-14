package com.pushwoosh.badge.e.a.d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.pushwoosh.badge.e.a.a;
import com.pushwoosh.badge.e.a.b;
import java.util.Arrays;
import java.util.List;

public class g implements a {
    @Override // com.pushwoosh.badge.e.a.a
    public List<String> a() {
        return Arrays.asList("com.htc.launcher");
    }

    @Override // com.pushwoosh.badge.e.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        Intent intent = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        intent.putExtra("com.htc.launcher.extra.COMPONENT", componentName.flattenToShortString());
        intent.putExtra("com.htc.launcher.extra.COUNT", i);
        Intent intent2 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intent2.putExtra("packagename", componentName.getPackageName());
        intent2.putExtra("count", i);
        if (com.pushwoosh.badge.e.a.e.a.a(context, intent) || com.pushwoosh.badge.e.a.e.a.a(context, intent2)) {
            context.sendBroadcast(intent);
            context.sendBroadcast(intent2);
            return;
        }
        throw new b("unable to resolve intent: " + intent2.toString());
    }
}
