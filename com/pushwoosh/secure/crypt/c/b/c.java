package com.pushwoosh.secure.crypt.c.b;

import android.os.AsyncTask;
import com.pushwoosh.secure.b.a;
import java.security.KeyPair;

class c extends AsyncTask<Void, Void, KeyPair> {
    private final b a;
    private final int b;
    private a<KeyPair> c;

    c(b bVar, int i) {
        this.a = bVar;
        this.b = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public KeyPair doInBackground(Void... voidArr) {
        this.a.a(this.b);
        return this.a.c();
    }

    /* access modifiers changed from: package-private */
    public void a(a<KeyPair> aVar) {
        this.c = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(KeyPair keyPair) {
        super.onPostExecute(keyPair);
        b(keyPair);
    }

    /* access modifiers changed from: package-private */
    public void b(KeyPair keyPair) {
        a<KeyPair> aVar = this.c;
        if (aVar != null) {
            aVar.a(keyPair);
        }
    }
}
