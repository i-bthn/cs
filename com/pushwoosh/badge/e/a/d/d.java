package com.pushwoosh.badge.e.a.d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.pushwoosh.badge.e.a.a;
import com.pushwoosh.badge.e.a.b;
import java.util.ArrayList;
import java.util.List;

public class d implements a {
    @Override // com.pushwoosh.badge.e.a.a
    public List<String> a() {
        return new ArrayList(0);
    }

    @Override // com.pushwoosh.badge.e.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i);
        intent.putExtra("badge_count_package_name", componentName.getPackageName());
        intent.putExtra("badge_count_class_name", componentName.getClassName());
        if (com.pushwoosh.badge.e.a.e.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new b("unable to resolve intent: " + intent.toString());
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context) {
        return com.pushwoosh.badge.e.a.e.a.a(context, new Intent("android.intent.action.BADGE_COUNT_UPDATE"));
    }
}
