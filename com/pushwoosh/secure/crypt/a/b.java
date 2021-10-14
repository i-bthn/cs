package com.pushwoosh.secure.crypt.a;

import androidx.annotation.NonNull;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.crypt.b.a;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class b implements a {
    private final com.pushwoosh.secure.crypt.e.a.b a;
    private final String b;

    public b(com.pushwoosh.secure.crypt.e.a.b bVar, String str) {
        this.a = bVar;
        this.b = str;
    }

    private int a(Key key) {
        if (key instanceof RSAKey) {
            return ((RSAKey) key).getModulus().bitLength();
        }
        throw new IllegalArgumentException("Private key should be instance of RSAKey");
    }

    private String a(int i, byte[] bArr, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        int i2 = i / 8;
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (i3 < bArr.length) {
            int i4 = i3 + i2;
            sb.append(new String(cipher.doFinal(Arrays.copyOfRange(bArr, i3, Math.min(i4, bArr.length)))));
            i3 = i4;
        }
        return sb.toString();
    }

    @Override // com.pushwoosh.secure.crypt.a.a
    @NonNull
    public String a(Key key, String str) throws a {
        try {
            Cipher instance = Cipher.getInstance(com.pushwoosh.secure.crypt.a.a(this.b));
            instance.init(2, key);
            return a(a(key), this.a.a(str), instance);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            PWLog.exception(e);
            throw new a(e);
        }
    }

    @Override // com.pushwoosh.secure.crypt.a.a
    @NonNull
    public byte[] b(Key key, String str) throws a {
        try {
            Cipher instance = Cipher.getInstance(com.pushwoosh.secure.crypt.a.a(this.b));
            instance.init(2, key);
            return instance.doFinal(this.a.a(str));
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            PWLog.exception(e);
            throw new a(e);
        }
    }
}
