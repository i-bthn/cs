package com.pushwoosh.appevents;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* access modifiers changed from: package-private */
public class a implements Application.ActivityLifecycleCallbacks {
    private Handler a = new Handler();
    private c b;
    private int c;
    private String d;

    /* access modifiers changed from: package-private */
    /* renamed from: com.pushwoosh.appevents.a$a  reason: collision with other inner class name */
    public class C0004a extends FragmentManager.FragmentLifecycleCallbacks {
        C0004a() {
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
            super.onFragmentStarted(fragmentManager, fragment);
            a.this.b();
        }
    }

    /* access modifiers changed from: package-private */
    public class b extends FragmentManager.FragmentLifecycleCallbacks {
        b() {
        }

        public void onFragmentStarted(android.app.FragmentManager fragmentManager, android.app.Fragment fragment) {
            super.onFragmentStarted(fragmentManager, fragment);
            a.this.b();
        }
    }

    public interface c {
        void a(String str, String str2);
    }

    a(@NonNull c cVar) {
        this.b = cVar;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.b.a("ScreenOpened", this.d);
    }

    @RequiresApi(api = 26)
    private void a(Activity activity) {
        activity.getFragmentManager().registerFragmentLifecycleCallbacks(new b(), true);
    }

    private void a(FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(new C0004a(), true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b() {
        this.a.removeCallbacksAndMessages(null);
        this.a.postDelayed(new Runnable() {
            /* class com.pushwoosh.appevents.$$Lambda$a$WfA7XTuOBBct_uci3T5RnQucvxk */

            public final void run() {
                a.this.a();
            }
        }, 100);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity instanceof FragmentActivity) {
            a((FragmentActivity) activity);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            a(activity);
        }
        b();
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        this.d = activity.getClass().getName();
        if (this.c == 0) {
            this.b.a("ApplicationOpened", this.d);
        }
        this.c++;
    }

    public void onActivityStopped(Activity activity) {
        this.c--;
        if (this.c == 0) {
            this.b.a("ApplicationClosed", this.d);
        }
    }
}
