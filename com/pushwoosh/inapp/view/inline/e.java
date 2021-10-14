package com.pushwoosh.inapp.view.inline;

import android.content.res.Configuration;
import com.pushwoosh.inapp.view.inline.InlineInAppView;

/* access modifiers changed from: package-private */
public class e {
    protected InlineInAppView a;
    protected a b;

    /* access modifiers changed from: package-private */
    public interface a {
        void a(int i, int i2);
    }

    public e(InlineInAppView inlineInAppView, a aVar) {
        this.a = inlineInAppView;
        this.b = aVar;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.a.getWebView().forceLayout();
        this.a.forceLayout();
        if (this.a.getParent() != null) {
            this.a.getParent().requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, a aVar) {
        if (this.a.getState() == InlineInAppView.d.CLOSED || this.a.getState() == InlineInAppView.d.LOADING) {
            aVar.a(0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Configuration configuration) {
    }

    public void a(InlineInAppView.d dVar) {
        if (dVar == InlineInAppView.d.CLOSED || dVar == InlineInAppView.d.RENDERED) {
            this.b.a();
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z, int i, int i2, int i3, int i4) {
    }
}
