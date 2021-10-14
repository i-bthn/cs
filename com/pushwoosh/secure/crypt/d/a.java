package com.pushwoosh.secure.crypt.d;

import android.content.Context;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;
import java.math.BigInteger;

/* access modifiers changed from: package-private */
public class a implements b {
    private EncryptedPreferences a;

    a(Context context, String str, String str2) {
        this(new EncryptedPreferences.Builder(context).withPreferenceName(str).withEncryptionPassword(str2).build());
    }

    a(EncryptedPreferences encryptedPreferences) {
        this.a = encryptedPreferences;
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public int a(String str, int i) {
        return this.a.getInt(str, i);
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public void a(String str, String str2) {
        this.a.edit().putString(str, str2).apply();
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public void a(String str, BigInteger bigInteger) {
        EncryptedPreferences.EncryptedEditor edit = this.a.edit();
        edit.putString(str, bigInteger.toString());
        edit.apply();
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public boolean a(String str) {
        return this.a.contains(str);
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public String b(String str, String str2) {
        return this.a.getString(str, str2);
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public BigInteger b(String str, BigInteger bigInteger) {
        String string = this.a.getString(str, null);
        return string == null ? bigInteger : new BigInteger(string);
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public void b(String str) {
        this.a.edit().remove(str).apply();
    }

    @Override // com.pushwoosh.secure.crypt.d.b
    public void b(String str, int i) {
        this.a.edit().putInt(str, i).apply();
    }
}
