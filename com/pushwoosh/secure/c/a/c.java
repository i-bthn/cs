package com.pushwoosh.secure.c.a;

import android.os.Bundle;
import com.pushwoosh.secure.crypt.manager.DecryptorManager;

/* access modifiers changed from: package-private */
public class c implements a {
    private d a = new d();

    c() {
    }

    @Override // com.pushwoosh.secure.c.a.a
    public void a(Bundle bundle, DecryptorManager decryptorManager) {
        if (bundle.containsKey("pw_encrypted")) {
            String decrypt = decryptorManager.decrypt(bundle.getString("pw_encrypted"));
            bundle.remove("pw_encrypted");
            if (decrypt == null || decrypt.isEmpty()) {
                bundle.putString("title", null);
                bundle.putString("pw_silent", "true");
                return;
            }
            this.a.a(bundle, decrypt);
        }
    }
}
