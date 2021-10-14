package com.pushwoosh.inapp.view.inline;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.pushwoosh.R;
import com.pushwoosh.inapp.view.f;
import com.pushwoosh.inapp.view.g;
import com.pushwoosh.inapp.view.inline.b;
import com.pushwoosh.inapp.view.inline.e;
import java.util.ArrayList;
import java.util.List;

public class InlineInAppView extends f {
    private String l;
    private boolean m;
    private d n;
    private boolean o;
    private e p;
    private b q;
    private List<InlineInAppViewListener> r = new ArrayList();

    public class a extends WebView {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(context);
            InlineInAppView.this = r1;
        }

        public void computeScroll() {
        }

        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            InlineInAppView.this.p.a(z, i, i2, i3, i4);
        }

        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            return false;
        }

        public void scrollTo(int i, int i2) {
        }
    }

    public class b implements com.pushwoosh.inapp.view.c {
        b() {
            InlineInAppView.this = r1;
        }

        @Override // com.pushwoosh.inapp.view.c
        public int b() {
            return 0;
        }

        @Override // com.pushwoosh.inapp.view.c
        public void c() {
            if (InlineInAppView.this.n == d.LOADING) {
                InlineInAppView.this.setState(d.LOADED);
            }
        }

        @Override // com.pushwoosh.inapp.view.c
        public void close() {
            InlineInAppView.this.setState(d.CLOSED);
        }
    }

    public static class c extends View.BaseSavedState {
        public static final Parcelable.Creator<c> CREATOR = new a();
        boolean a;
        b.C0016b b;

        static class a implements Parcelable.Creator<c> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public c createFromParcel(Parcel parcel) {
                return new c(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public c[] newArray(int i) {
                return new c[i];
            }
        }

        private c(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt() != 1 ? false : true;
            this.b = new b.C0016b(parcel);
        }

        /* synthetic */ c(Parcel parcel, a aVar) {
            this(parcel);
        }

        c(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a ? 1 : 0);
            this.b.a(parcel);
        }
    }

    public enum d {
        LOADING,
        LOADED,
        RENDERED,
        CLOSED
    }

    public InlineInAppView(Context context) {
        super(context);
        h();
    }

    public InlineInAppView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.InlineInAppView);
        this.l = obtainStyledAttributes.getString(R.styleable.InlineInAppView_identifier);
        this.m = obtainStyledAttributes.getBoolean(R.styleable.InlineInAppView_disableLayoutAnimation, false);
        obtainStyledAttributes.recycle();
        h();
    }

    /* access modifiers changed from: public */
    private /* synthetic */ void a(int i, int i2, int i3, int i4) {
        setMeasuredDimension(FrameLayout.resolveSize(i3, i), FrameLayout.resolveSize(i4, i2));
    }

    private void h() {
        a aVar = new a(this);
        this.p = Build.VERSION.SDK_INT < 19 ? new c(this, aVar) : new d(this, aVar);
        this.q = new b(this, com.pushwoosh.inapp.b.c());
        this.n = d.LOADING;
        this.q.a(this.l);
    }

    private void i() {
        if (!this.r.isEmpty()) {
            for (InlineInAppViewListener inlineInAppViewListener : this.r) {
                if (inlineInAppViewListener != null) {
                    inlineInAppViewListener.onInlineInAppViewClosed();
                }
            }
        }
    }

    private void j() {
        this.q.c();
        if (!this.r.isEmpty()) {
            for (InlineInAppViewListener inlineInAppViewListener : this.r) {
                if (inlineInAppViewListener != null) {
                    inlineInAppViewListener.onInlineInAppLoaded();
                }
            }
        }
    }

    @Override // com.pushwoosh.inapp.view.f
    public FrameLayout.LayoutParams a(com.pushwoosh.inapp.j.l.a aVar, int i) {
        return new FrameLayout.LayoutParams(-2, -2);
    }

    @Override // com.pushwoosh.inapp.view.f
    public void a() {
    }

    public void a(com.pushwoosh.inapp.j.l.b bVar) {
        g gVar = new g(new b(), bVar);
        this.d.setWebViewClient(gVar);
        gVar.a(this);
        gVar.a(this.d);
    }

    public void addInlineInAppViewListener(InlineInAppViewListener inlineInAppViewListener) {
        if (inlineInAppViewListener != null) {
            this.r.add(inlineInAppViewListener);
        }
    }

    public void b(com.pushwoosh.inapp.i.a aVar) {
        a(aVar);
    }

    @Override // com.pushwoosh.inapp.view.f
    public WebView c() {
        return new a(getContext());
    }

    @Override // com.pushwoosh.inapp.view.f
    public void e() {
        super.e();
        this.d.setScrollContainer(false);
        this.d.setVerticalScrollBarEnabled(false);
        this.d.setHorizontalScrollBarEnabled(false);
    }

    @Override // com.pushwoosh.inapp.view.f
    public boolean g() {
        return this.o;
    }

    public FrameLayout getContainer() {
        return this.b;
    }

    public String getIdentifier() {
        return this.l;
    }

    public d getState() {
        return this.n;
    }

    public WebView getWebView() {
        return this.d;
    }

    public boolean isLayoutAnimationDisabled() {
        return this.m;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.p.a(configuration);
    }

    public void onMeasure(int i, int i2) {
        this.o = View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824;
        super.onMeasure(i, i2);
        this.p.a(i, i2, new e.a(i, i2) {
            /* class com.pushwoosh.inapp.view.inline.$$Lambda$InlineInAppView$RQPDeKQ8EzEMQDt78X1TK4Ond08 */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.pushwoosh.inapp.view.inline.e.a
            public final void a(int i, int i2) {
                InlineInAppView.lambda$RQPDeKQ8EzEMQDt78X1TK4Ond08(InlineInAppView.this, this.f$1, this.f$2, i, i2);
            }
        });
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof c) {
            c cVar = (c) parcelable;
            if (cVar.a) {
                this.n = d.CLOSED;
            }
            this.q.a(cVar.b);
            parcelable = cVar.getSuperState();
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.a = this.n == d.CLOSED;
        cVar.b = this.q.b();
        return cVar;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!this.r.isEmpty()) {
            for (InlineInAppViewListener inlineInAppViewListener : this.r) {
                if (inlineInAppViewListener != null) {
                    inlineInAppViewListener.onInlineInAppViewChangedSize(i, i2);
                }
            }
        }
    }

    public void removeInlineInAppViewListener(InlineInAppViewListener inlineInAppViewListener) {
        if (inlineInAppViewListener != null) {
            this.r.remove(inlineInAppViewListener);
        }
    }

    public void setDisableLayoutAnimation(boolean z) {
        this.m = z;
    }

    public void setIdentifier(String str) {
        String str2 = this.l;
        if (str2 == null) {
            if (str == null) {
                return;
            }
        } else if (str2.equals(str)) {
            return;
        }
        this.l = str;
        this.q.a(str);
    }

    public void setState(d dVar) {
        if (dVar != this.n) {
            this.n = dVar;
            if (dVar == d.RENDERED) {
                j();
            }
            if (dVar == d.CLOSED) {
                i();
            }
            this.p.a(dVar);
        }
    }
}
