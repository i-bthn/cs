package com.pushwoosh.notification.handlers.message.system;

import android.os.Bundle;
import android.text.TextUtils;
import com.pushwoosh.notification.b;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;

/* access modifiers changed from: package-private */
public class d extends c {
    private final RegistrationPrefs a = RepositoryModule.getRegistrationPreferences();

    d() {
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.notification.handlers.message.system.c
    public boolean a(Bundle bundle, String str) {
        if (!TextUtils.equals("setLogLevel", str)) {
            return false;
        }
        this.a.logLevel().set(b.z(bundle));
        return true;
    }
}
