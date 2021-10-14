package com.pushwoosh.secure;

import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.secure.c.a;
import com.pushwoosh.secure.crypt.manager.RsaDecryptorManager;
import java.util.Collection;

public class SecurePlugin implements Plugin {
    @Override // com.pushwoosh.internal.Plugin
    public Collection<? extends MigrationScheme> getPrefsMigrationSchemes(PrefsProvider prefsProvider) {
        return null;
    }

    @Override // com.pushwoosh.internal.Plugin
    public void init() {
        if (RsaDecryptorManager.isKeysExist(AndroidPlatformModule.getApplicationContext())) {
            PushwooshSecure.a(new a(new com.pushwoosh.secure.crypt.manager.a(AndroidPlatformModule.getApplicationContext(), 3072, null)));
        }
    }
}
