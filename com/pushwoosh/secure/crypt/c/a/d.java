package com.pushwoosh.secure.crypt.c.a;

import android.annotation.TargetApi;
import android.security.KeyPairGeneratorSpec;
import com.pushwoosh.internal.utils.PWLog;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

@TargetApi(19)
public class d extends b {
    private KeyPairGeneratorSpec.Builder b;

    public d(KeyPairGeneratorSpec.Builder builder) {
        super("RSA");
        this.b = builder;
        try {
            a();
        } catch (NoSuchAlgorithmException e) {
            PWLog.exception(e);
        }
    }

    private KeyPairGeneratorSpec a(int i) throws NoSuchAlgorithmException {
        return this.b.setKeySize(i).build();
    }

    private void a() throws NoSuchAlgorithmException {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(1, 100);
        this.b.setSerialNumber(BigInteger.ONE).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).setKeyType(this.a);
    }

    @Override // com.pushwoosh.secure.crypt.c.a.b, com.pushwoosh.secure.crypt.c.a.a
    public /* bridge */ /* synthetic */ KeyPair a(String str, int i) throws Exception {
        return super.a(str, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.secure.crypt.c.a.b
    public void a(KeyPairGenerator keyPairGenerator, String str, int i) throws Exception {
        keyPairGenerator.initialize(a(i));
    }
}
