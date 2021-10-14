package com.pushwoosh.secure.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyProtection;
import android.util.Base64;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class b {
    private static final char[] c = "0123456789ABCDEF".toCharArray();
    private EncryptedPreferences a;
    private d b;

    @TargetApi(23)
    private class a implements d {
        private KeyStore b = KeyStore.getInstance("AndroidKeyStore");

        a() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
            this.b.load(null);
        }

        @Override // com.pushwoosh.secure.a.b.d
        public SecretKey a() {
            try {
                return ((KeyStore.SecretKeyEntry) this.b.getEntry("KEY_KEY", null)).getSecretKey();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // com.pushwoosh.secure.a.b.d
        public void a(byte[] bArr) throws Exception {
            this.b.setEntry("KEY_KEY", new KeyStore.SecretKeyEntry(new SecretKeySpec(bArr, "AES")), new KeyProtection.Builder(2).setEncryptionPaddings("PKCS7Padding").setBlockModes("CBC").build());
        }
    }

    /* renamed from: com.pushwoosh.secure.a.b$b  reason: collision with other inner class name */
    public static final class C0027b extends Exception {
    }

    private class c implements d {
        private EncryptedPreferences b;

        c(EncryptedPreferences encryptedPreferences) {
            this.b = encryptedPreferences;
        }

        @Override // com.pushwoosh.secure.a.b.d
        public SecretKey a() {
            String string = this.b.getString("KEY_KEY", null);
            if (string == null) {
                return null;
            }
            return new SecretKeySpec(Base64.decode(string, 0), "AES");
        }

        @Override // com.pushwoosh.secure.a.b.d
        public void a(byte[] bArr) {
            EncryptedPreferences.EncryptedEditor edit = this.b.edit();
            edit.putString("KEY_KEY", Base64.encodeToString(bArr, 0));
            edit.apply();
        }
    }

    private interface d {
        SecretKey a();

        void a(byte[] bArr) throws Exception;
    }

    b(Context context, String str) {
        c cVar;
        this.a = new EncryptedPreferences.Builder(context).withPreferenceName("PWBroadcastSecurityKey").withEncryptionPassword(str).build();
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                this.b = new a();
            } catch (Exception e) {
                e.printStackTrace();
                cVar = new c(this.a);
            }
        } else {
            cVar = new c(this.a);
            this.b = cVar;
        }
    }

    /* access modifiers changed from: package-private */
    public SecretKey a(int i) throws C0027b {
        if (this.a.getInt("VERSION_KEY", 0) == i) {
            return this.b.a();
        }
        throw new C0027b();
    }

    /* access modifiers changed from: package-private */
    public void a(byte[] bArr, int i) {
        if (this.a.getInt("VERSION_KEY", 0) != i || this.b.a() == null) {
            try {
                this.b.a(bArr);
                this.a.edit().putInt("VERSION_KEY", i).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
