package com.pushwoosh.internal.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

public class TranslucentActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(512, 512);
        }
    }
}
