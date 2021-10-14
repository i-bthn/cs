package com.pushwoosh.secure.a;

import android.os.Bundle;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.exception.PushwooshException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.Subscription;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.NotificationRegistrarHelper;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.secure.c.a.d;
import com.pushwoosh.secure.crypt.c.e.b;
import com.pushwoosh.secure.crypt.manager.RsaDecryptorManager;
import com.pushwoosh.secure.crypt.manager.a;
import java.lang.ref.WeakReference;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;

public class e {
    private final String a = e.class.getSimpleName();
    private final a b;
    private final b c;
    private final a d;
    private final Subscription<d> e;
    private d f = new d();

    public e(a aVar) {
        this.b = aVar;
        this.c = new b(AndroidPlatformModule.getApplicationContext(), Pushwoosh.getInstance().getHwid());
        this.d = new a();
        final WeakReference weakReference = new WeakReference(this);
        this.e = EventBus.subscribe(d.class, new EventListener<d>() {
            /* class com.pushwoosh.secure.a.e.AnonymousClass1 */

            /* renamed from: a */
            public void onReceive(d dVar) {
                if (weakReference.get() != null) {
                    ((e) weakReference.get()).a(dVar.a());
                }
            }
        });
    }

    private void a(final Callback<Void, PushwooshException> callback) {
        b createCryptoStorage = RsaDecryptorManager.createCryptoStorage(AndroidPlatformModule.getApplicationContext());
        if (!createCryptoStorage.b()) {
            this.b.a(createCryptoStorage.a().getPublic(), new Callback<c, PushwooshException>() {
                /* class com.pushwoosh.secure.a.e.AnonymousClass3 */

                @Override // com.pushwoosh.function.Callback
                public void process(@NonNull Result<c, PushwooshException> result) {
                    Callback callback;
                    Result from;
                    if (!result.isSuccess() || result.getData() == null) {
                        f fVar = result.getException() != null ? new f(result.getException()) : new f("failed to decrypt message. key is empty");
                        callback = callback;
                        from = Result.from(null, fVar);
                    } else {
                        e.this.a(result.getData());
                        callback = callback;
                        from = Result.fromData(null);
                    }
                    callback.process(from);
                }
            });
        } else {
            callback.process(Result.from(null, new f("decryption is not set up")));
        }
    }

    private boolean a(final Bundle bundle, boolean z) {
        SecretKey secretKey;
        String str;
        try {
            secretKey = this.c.a(Integer.parseInt(bundle.getString("pwkv")));
        } catch (Exception e2) {
            e2.printStackTrace();
            secretKey = null;
        }
        if (secretKey == null) {
            if (!z || bundle.getBoolean("pwKeyRefetchedFlag", false)) {
                PWLog.error(this.a, "failed to decrypt message. cannot get key from storage or receive it from service");
            } else {
                a(new Callback<Void, PushwooshException>() {
                    /* class com.pushwoosh.secure.a.e.AnonymousClass2 */

                    @Override // com.pushwoosh.function.Callback
                    public void process(@NonNull Result<Void, PushwooshException> result) {
                        if (result.isSuccess()) {
                            bundle.putBoolean("pwKeyRefetchedFlag", true);
                            NotificationRegistrarHelper.handleMessage(bundle);
                            return;
                        }
                        PWLog.error(e.this.a, result.getException());
                    }
                });
            }
            return true;
        }
        if (bundle.containsKey("pw_encrypted")) {
            try {
                str = new String(this.d.a(Base64.decode(bundle.getString("pw_encrypted"), 0), secretKey));
            } catch (GeneralSecurityException e3) {
                e3.printStackTrace();
                str = null;
            }
            bundle.remove("pw_encrypted");
            if (str == null || str.isEmpty()) {
                bundle.putString("title", null);
                bundle.putString("pw_silent", "true");
                return false;
            }
            this.f.a(bundle, str);
        }
        return !z;
    }

    public void a(c cVar) {
        this.c.a(Base64.decode(this.b.decrypt(cVar.a()), 0), cVar.b());
    }

    public boolean a(Bundle bundle) {
        return a(bundle, true);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.e.unsubscribe();
        super.finalize();
    }
}
