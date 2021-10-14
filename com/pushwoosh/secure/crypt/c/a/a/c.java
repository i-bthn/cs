package com.pushwoosh.secure.crypt.c.a.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import com.pushwoosh.secure.crypt.c.a.a;
import com.pushwoosh.secure.crypt.c.a.d;
import javax.security.auth.x500.X500Principal;

@TargetApi(19)
public class c implements a {
    private final KeyPairGeneratorSpec.Builder a;

    public c(Context context, String str) {
        this.a = new KeyPairGeneratorSpec.Builder(context).setAlias(str).setSubject(new X500Principal(String.format("CN=%s, OU=%s", str, context.getPackageName())));
    }

    @Override // com.pushwoosh.secure.crypt.c.a.a.a
    public a a() {
        return new d(this.a);
    }
}
