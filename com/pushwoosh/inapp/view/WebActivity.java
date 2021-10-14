package com.pushwoosh.inapp.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.inapp.j.l.b;
import com.pushwoosh.richmedia.RichMediaStyle;

public abstract class WebActivity extends Activity implements c {
    private int a;
    protected b b;
    @Nullable
    private f c;

    protected static Intent a(Intent intent, b bVar, String str, int i) {
        intent.putExtra("extraInApp", bVar);
        intent.putExtra("extraSound", str);
        intent.putExtra("extraMode", i);
        intent.addFlags(!bVar.m() ? 805306368 : 268435456);
        return intent;
    }

    /* JADX INFO: finally extract failed */
    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(Intent intent) {
        b bVar = this.b;
        boolean z = bVar != null && bVar.equals(intent.getSerializableExtra("extraInApp"));
        if (!z || this.c == null) {
            try {
                boolean isInMultiWindowMode = Build.VERSION.SDK_INT >= 24 ? isInMultiWindowMode() : false;
                RichMediaStyle a2 = PushwooshPlatform.getInstance().h().a();
                if (z) {
                    this.c = new f(this, this.b.f(), a2, isInMultiWindowMode);
                    this.c.setWebViewClient(new g(this, this.b));
                    f fVar = this.c;
                    if (fVar != null) {
                        a(fVar);
                        return;
                    }
                    return;
                }
                this.b = (b) intent.getSerializableExtra("extraInApp");
                if (this.b == null) {
                    finish();
                    f fVar2 = this.c;
                    if (fVar2 != null) {
                        a(fVar2);
                        return;
                    }
                    return;
                }
                String stringExtra = intent.getStringExtra("extraSound");
                this.a = intent.getIntExtra("extraMode", 0);
                this.c = new f(this, this.b.f(), a2, isInMultiWindowMode);
                this.c.setWebViewClient(new g(this, this.b));
                a(this.b, stringExtra, this.a);
                f fVar3 = this.c;
                if (fVar3 != null) {
                    a(fVar3);
                }
            } catch (Throwable th) {
                f fVar4 = this.c;
                if (fVar4 != null) {
                    a(fVar4);
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(b bVar, String str, int i);

    /* access modifiers changed from: protected */
    public abstract void a(@Nullable f fVar);

    @Override // com.pushwoosh.inapp.view.c
    public int b() {
        return this.a;
    }

    @Override // com.pushwoosh.inapp.view.c
    public void c() {
        f fVar = this.c;
        if (fVar != null) {
            fVar.d();
        }
    }

    @Override // com.pushwoosh.inapp.view.c
    public void close() {
        f fVar = this.c;
        if (fVar != null) {
            fVar.b();
            this.c = null;
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getAttributes().flags |= Integer.MIN_VALUE;
            getWindow().setStatusBarColor(0);
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        if (bundle != null) {
            this.b = (b) bundle.getSerializable("extraInApp");
        }
        a(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.c = null;
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        a(intent);
        setIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("extraInApp", this.b);
    }
}
