package com.pushwoosh.badge.e.a.d;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.pushwoosh.badge.e.a.a;
import com.pushwoosh.badge.e.a.b;
import java.util.Collections;
import java.util.List;

public class n implements a {
    private final Uri a = Uri.parse("content://com.android.badge/badge");

    @Override // com.pushwoosh.badge.e.a.a
    public List<String> a() {
        return Collections.singletonList("com.zui.launcher");
    }

    @Override // com.pushwoosh.badge.e.a.a
    @TargetApi(11)
    public void a(Context context, ComponentName componentName, int i) throws b {
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i);
        context.getContentResolver().call(this.a, "setAppBadgeCount", (String) null, bundle);
    }
}
