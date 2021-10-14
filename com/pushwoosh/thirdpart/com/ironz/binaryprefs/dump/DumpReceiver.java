package com.pushwoosh.thirdpart.com.ironz.binaryprefs.dump;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.pushwoosh.e.a.a.a.e;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DumpReceiver extends BroadcastReceiver {
    private static final Map<String, e> a = new ConcurrentHashMap();

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("pref_name");
        if (!a.containsKey(stringExtra)) {
            Log.e(DumpReceiver.class.getName(), String.format("Cannot find '%s' preference for dumping!", stringExtra));
            return;
        }
        Map<String, ?> all = a.get(stringExtra).getAll();
        if (intent.hasExtra("pref_key")) {
            String stringExtra2 = intent.getStringExtra("pref_key");
            String name = DumpReceiver.class.getName();
            Log.d(name, stringExtra2 + ": " + all.get(stringExtra2) + "\n");
            return;
        }
        for (String str : all.keySet()) {
            Object obj = all.get(str);
            String name2 = DumpReceiver.class.getName();
            Log.d(name2, str + ": " + obj + "\n");
        }
    }
}
