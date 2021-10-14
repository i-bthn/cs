package com.pushwoosh.secure.crypt.manager;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.a.c;
import com.pushwoosh.secure.a.d;
import com.pushwoosh.secure.crypt.e.b.b;
import com.pushwoosh.secure.crypt.manager.b;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

public final class a extends RsaDecryptorManager implements com.pushwoosh.secure.b.a<KeyPair> {
    private static String e;
    private static List<String> f;
    private static String g;
    private final b a;
    private final String b;
    private final String c;
    private Callback<Void, PushwooshException> d;

    public a(Context context, int i, Callback<Void, PushwooshException> callback) {
        this(context, i, new com.pushwoosh.secure.crypt.e.b.a(), Pushwoosh.getInstance().getHwid(), Pushwoosh.getInstance().getAppId(), callback);
    }

    a(Context context, int i, b bVar, String str, String str2, Callback<Void, PushwooshException> callback) {
        super(context, i);
        if (e == null) {
            a(context, str);
        }
        this.a = bVar;
        this.b = str;
        this.c = str2;
        this.d = callback;
        getKeyPairAsync(this);
    }

    public static void a(Context context, String str) {
        if (e == null) {
            new b(context, str).a(new b.a() {
                /* class com.pushwoosh.secure.crypt.manager.a.AnonymousClass2 */

                @Override // com.pushwoosh.secure.crypt.manager.b.a
                public void a(String str, List<String> list, String str2) {
                    String unused = a.e = str;
                    List unused2 = a.f = list;
                    String unused3 = a.g = str2;
                }
            });
        }
    }

    public static void a(Context context, String str, String str2, List<String> list, String str3) {
        List<String> list2;
        if (!TextUtils.equals(str2, e) || ((list != (list2 = f) && (list == null || list2 == null || Arrays.deepEquals(list.toArray(), f.toArray()))) || !TextUtils.equals(str3, g))) {
            e = str2;
            f = list;
            g = str3;
            new b(context, str).a(str2, list, str3);
        }
    }

    public static boolean a() {
        if (e != null) {
            return true;
        }
        PWLog.error("PushwooshSecure", "BaseURL not set");
        return false;
    }

    public void a(KeyPair keyPair) {
        a(keyPair.getPublic(), new Callback<c, PushwooshException>() {
            /* class com.pushwoosh.secure.crypt.manager.a.AnonymousClass1 */

            @Override // com.pushwoosh.function.Callback
            public void process(@NonNull Result<c, PushwooshException> result) {
                if (result.isSuccess() && result.getData() != null) {
                    EventBus.sendEvent(new d(result.getData()));
                }
                if (a.this.d != null) {
                    a.this.d.process(Result.from(null, result.getException()));
                }
            }
        });
    }

    public void a(PublicKey publicKey, Callback<c, PushwooshException> callback) {
        if (e != null) {
            new com.pushwoosh.secure.crypt.manager.a.a(new com.pushwoosh.secure.crypt.manager.a.a.b(this.b, this.a.a(publicKey), this.c), e, f, g, callback).execute(new Void[0]);
        } else if (callback != null) {
            callback.process(Result.fromData(null));
        }
        this.d = null;
    }

    @Override // com.pushwoosh.secure.crypt.manager.RsaDecryptorManager, com.pushwoosh.secure.crypt.manager.DecryptorManager
    public void removePublicKey(Callback<Void, PushwooshException> callback) {
        super.removePublicKey(null);
        if (e != null) {
            new com.pushwoosh.secure.crypt.manager.a.a(new com.pushwoosh.secure.crypt.manager.a.a.a(this.b, this.c), e, f, g, callback).execute(new Object[0]);
        } else if (callback != null) {
            callback.process(Result.fromData(null));
        }
    }
}
