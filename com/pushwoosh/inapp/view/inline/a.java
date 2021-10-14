package com.pushwoosh.inapp.view.inline;

import android.animation.LayoutTransition;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import androidx.annotation.RequiresApi;
import com.pushwoosh.inapp.view.inline.InlineInAppView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

class a {
    private InlineInAppView a;
    private ArrayList<WeakReference<ViewGroup>> b = new ArrayList<>();

    /* access modifiers changed from: package-private */
    /* renamed from: com.pushwoosh.inapp.view.inline.a$a  reason: collision with other inner class name */
    public class C0015a implements LayoutTransition.TransitionListener {
        final /* synthetic */ ViewGroup a;

        C0015a(ViewGroup viewGroup) {
            this.a = viewGroup;
        }

        public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            if (view == a.this.a) {
                this.a.setLayoutTransition(null);
                a.this.d();
            }
        }

        public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            if (view == a.this.a) {
                a.this.b();
            }
        }
    }

    /* access modifiers changed from: private */
    public static class b extends LayoutTransition {
        private b() {
        }

        /* synthetic */ b(C0015a aVar) {
            this();
        }
    }

    public a(InlineInAppView inlineInAppView) {
        this.a = inlineInAppView;
    }

    @RequiresApi(api = 16)
    private void a(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != this.a && (childAt instanceof ViewGroup)) {
                ViewGroup viewGroup2 = (ViewGroup) childAt;
                if (viewGroup2.getLayoutTransition() == null) {
                    viewGroup2.setLayoutTransition(c());
                    this.b.add(new WeakReference<>(viewGroup2));
                    a(viewGroup2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        float f = 1.0f;
        this.a.getContainer().setAlpha(this.a.getState() == InlineInAppView.d.CLOSED ? 1.0f : 0.0f);
        ViewPropertyAnimator animate = this.a.getContainer().animate();
        if (this.a.getState() == InlineInAppView.d.CLOSED) {
            f = 0.0f;
        }
        animate.alpha(f).setDuration(300).start();
    }

    @RequiresApi(api = 16)
    private LayoutTransition c() {
        b bVar = new b(null);
        bVar.setDuration(300);
        bVar.enableTransitionType(4);
        return bVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        Iterator<WeakReference<ViewGroup>> it = this.b.iterator();
        while (it.hasNext()) {
            WeakReference<ViewGroup> next = it.next();
            if (next.get() != null && (next.get().getLayoutTransition() instanceof b)) {
                next.get().setLayoutTransition(null);
            }
        }
        this.b.clear();
    }

    public void a() {
        if (this.a.g()) {
            b();
        } else if (!this.a.isLayoutAnimationDisabled() && Build.VERSION.SDK_INT >= 16 && (this.a.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) this.a.getParent();
            if (viewGroup.getLayoutTransition() == null) {
                LayoutTransition c = c();
                c.addTransitionListener(new C0015a(viewGroup));
                viewGroup.setLayoutTransition(c);
                ViewParent viewParent = viewGroup;
                while (viewParent.getParent() instanceof ViewGroup) {
                    viewParent = viewParent.getParent();
                }
                a(viewParent);
            }
        }
    }
}
