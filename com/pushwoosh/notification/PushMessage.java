package com.pushwoosh.notification;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PushMessage {
    private final Bundle a;
    private final String b;
    private final String c;
    private final String d;
    private final boolean e;
    private final boolean f;
    private final Integer g;
    private final Integer h;
    private final String i;
    private final boolean j;
    private final String k;
    private final String l;
    private final String m;
    private final int n;
    private final int o;
    private final int p;
    private final int q;
    private final int r;
    private final int s;
    private final List<Action> t = new ArrayList();
    private final String u;
    private final boolean v;
    private final String w;

    public PushMessage(@NonNull Bundle bundle) {
        this.a = bundle;
        this.d = b.t(bundle);
        this.e = b.E(bundle);
        this.f = b.C(bundle);
        this.g = b.i(bundle);
        this.h = b.l(bundle);
        this.i = b.y(bundle);
        this.j = b.A(bundle);
        this.c = b.p(bundle);
        this.b = b.g(bundle);
        this.k = this.c;
        this.o = b.s(bundle);
        this.q = b.B(bundle);
        this.p = b.d(bundle);
        this.w = b.f(bundle);
        this.m = b.e(bundle);
        this.l = b.k(bundle);
        this.n = b.x(bundle);
        this.r = b.n(bundle);
        this.s = b.m(bundle);
        this.u = b.q(bundle);
        this.v = b.D(bundle);
        this.t.addAll(b.b(bundle));
    }

    public List<Action> getActions() {
        return this.t;
    }

    public int getBadges() {
        return this.p;
    }

    public String getBigPictureUrl() {
        return this.m;
    }

    public String getCustomData() {
        return this.w;
    }

    public String getHeader() {
        return this.b;
    }

    public Integer getIconBackgroundColor() {
        return this.g;
    }

    public String getLargeIconUrl() {
        return this.l;
    }

    public Integer getLed() {
        return this.h;
    }

    public int getLedOffMS() {
        return this.s;
    }

    public int getLedOnMS() {
        return this.r;
    }

    public String getMessage() {
        return this.c;
    }

    public int getPriority() {
        return this.o;
    }

    public String getPushHash() {
        return this.d;
    }

    public int getSmallIcon() {
        return this.n;
    }

    public String getSound() {
        return this.i;
    }

    public String getTag() {
        return this.u;
    }

    public String getTicker() {
        return this.k;
    }

    public boolean getVibration() {
        return this.j;
    }

    public int getVisibility() {
        return this.q;
    }

    public boolean isLocal() {
        return this.f;
    }

    public boolean isLockScreen() {
        return this.v;
    }

    public boolean isSilent() {
        return this.e;
    }

    public Bundle toBundle() {
        return this.a;
    }

    public JSONObject toJson() {
        return b.a(this.a);
    }
}
