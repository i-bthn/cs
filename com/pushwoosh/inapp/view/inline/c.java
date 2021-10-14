package com.pushwoosh.inapp.view.inline;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import com.pushwoosh.inapp.view.inline.InlineInAppView;
import com.pushwoosh.inapp.view.inline.c;
import com.pushwoosh.inapp.view.inline.e;

public class c extends e {
    private int c;
    private int d;
    private final Handler e = new Handler();
    private boolean f;
    private int g;
    private int h;

    public class b {
        static final String JS_INTEFACE_NAME;

        private b() {
            c.this = r1;
        }

        /* access modifiers changed from: public */
        private /* synthetic */ void a() {
            InlineInAppView.d state = c.this.a.getState();
            InlineInAppView.d dVar = InlineInAppView.d.RENDERED;
            if (state == dVar) {
                c.this.a();
            } else {
                c.this.a.setState(dVar);
            }
        }

        public void resize(float f, float f2) {
            if (f > 0.0f && f2 > 0.0f) {
                synchronized (c.this.e) {
                    if (((float) c.this.c) != f || ((float) c.this.d) != f2) {
                        c.this.e.removeCallbacksAndMessages(null);
                        c.this.c = (int) (f * c.this.a.getResources().getDisplayMetrics().density);
                        c.this.d = (int) (f2 * c.this.a.getResources().getDisplayMetrics().density);
                        c.this.e.post(new Runnable() {
                            /* class com.pushwoosh.inapp.view.inline.$$Lambda$c$b$7ziABg1HOIPSfQOY_3TAjEc7CiA */

                            public final void run() {
                                c.b.lambda$7ziABg1HOIPSfQOY_3TAjEc7CiA(c.b.this);
                            }
                        });
                    }
                }
            }
        }
    }

    public c(InlineInAppView inlineInAppView, a aVar) {
        super(inlineInAppView, aVar);
        inlineInAppView.getWebView().addJavascriptInterface(new b(), "pwInlineInappSizeDelegate");
    }

    /* access modifiers changed from: public */
    private void b() {
        this.a.getWebView().loadUrl("javascript:pwInlineInappSizeDelegate.resize(document.body.clientWidth, document.body.clientHeight)");
        synchronized (this.e) {
            this.e.postDelayed(new Runnable() {
                /* class com.pushwoosh.inapp.view.inline.$$Lambda$c$8Y9vsRKi0leActhi9tmDKMwHwSU */

                public final void run() {
                    c.lambda$8Y9vsRKi0leActhi9tmDKMwHwSU(c.this);
                }
            }, 400);
        }
    }

    @Override // com.pushwoosh.inapp.view.inline.e
    public void a(int i, int i2, e.a aVar) {
        int i3;
        int i4;
        if (this.a.getState() == InlineInAppView.d.RENDERED && (i3 = this.c) > 0 && (i4 = this.d) > 0) {
            aVar.a(i3, i4);
        } else if (this.a.getState() == InlineInAppView.d.LOADED) {
            aVar.a(1, 1);
        } else {
            super.a(i, i2, aVar);
        }
    }

    @Override // com.pushwoosh.inapp.view.inline.e
    public void a(Configuration configuration) {
        super.a(configuration);
        if (Build.VERSION.SDK_INT < 19) {
            this.g = this.a.getWebView().getWidth();
            this.h = this.a.getWebView().getHeight();
            this.f = true;
        }
    }

    @Override // com.pushwoosh.inapp.view.inline.e
    public void a(InlineInAppView.d dVar) {
        super.a(dVar);
        if (dVar == InlineInAppView.d.LOADED) {
            this.a.getContainer().setAlpha(0.01f);
            a();
            this.e.postDelayed(new Runnable() {
                /* class com.pushwoosh.inapp.view.inline.$$Lambda$c$8Y9vsRKi0leActhi9tmDKMwHwSU */

                public final void run() {
                    c.lambda$8Y9vsRKi0leActhi9tmDKMwHwSU(c.this);
                }
            }, 400);
        }
    }

    @Override // com.pushwoosh.inapp.view.inline.e
    public void a(boolean z, int i, int i2, int i3, int i4) {
        if (z && this.f) {
            if (i3 != this.g || i4 != this.h) {
                this.c = 0;
                this.d = 0;
                this.e.post(new Runnable() {
                    /* class com.pushwoosh.inapp.view.inline.$$Lambda$c$8Y9vsRKi0leActhi9tmDKMwHwSU */

                    public final void run() {
                        c.lambda$8Y9vsRKi0leActhi9tmDKMwHwSU(c.this);
                    }
                });
                this.f = false;
            }
        }
    }
}
