package com.pushwoosh.inapp.view.inline;

import androidx.annotation.RequiresApi;
import com.pushwoosh.inapp.view.inline.InlineInAppView;

/* access modifiers changed from: package-private */
@RequiresApi(19)
public class d extends e {
    public d(InlineInAppView inlineInAppView, a aVar) {
        super(inlineInAppView, aVar);
    }

    @Override // com.pushwoosh.inapp.view.inline.e
    public void a(InlineInAppView.d dVar) {
        super.a(dVar);
        if (dVar == InlineInAppView.d.LOADED) {
            int i = 1;
            int width = this.a.getWebView().getWidth() == 0 ? 1 : this.a.getWebView().getWidth();
            if (this.a.getWebView().getHeight() != 0) {
                i = this.a.getWebView().getHeight();
            }
            this.a.getWebView().layout(0, 0, width, i);
            this.a.setState(InlineInAppView.d.RENDERED);
        }
    }
}
