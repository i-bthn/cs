package com.pushwoosh.badge.e.a.d;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.pushwoosh.badge.e.a.a;
import com.pushwoosh.badge.e.a.b;
import java.util.Arrays;
import java.util.List;

public class f implements a {
    @Override // com.pushwoosh.badge.e.a.a
    public List<String> a() {
        return Arrays.asList("com.huawei.android.launcher");
    }

    @Override // com.pushwoosh.badge.e.a.a
    public void a(Context context, ComponentName componentName, int i) throws b {
        Bundle bundle = new Bundle();
        bundle.putString("package", context.getPackageName());
        bundle.putString("class", componentName.getClassName());
        bundle.putInt("badgenumber", i);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
    }
}
