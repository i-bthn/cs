package com.pushwoosh.internal.platform.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

public class a implements b {
    private final WeakReference<Context> a;

    public a(@Nullable Context context) {
        this.a = new WeakReference<>(context);
    }

    @Nullable
    private Context a() {
        return this.a.get();
    }

    @Override // com.pushwoosh.internal.platform.d.b
    public Intent a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (a() == null) {
            return null;
        }
        return a().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override // com.pushwoosh.internal.platform.d.b
    public void a(Intent intent, String str) {
        if (a() != null) {
            a().sendBroadcast(intent, str);
        }
    }
}
