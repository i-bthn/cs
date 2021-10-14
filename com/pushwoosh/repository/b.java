package com.pushwoosh.repository;

import android.os.Bundle;

public class b {
    private int a;
    private int b;
    private String c;
    private long d;
    private Bundle e;

    public b() {
        this.c = "";
        this.e = new Bundle();
    }

    public b(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.e = new Bundle();
    }

    public b(int i, int i2, String str, long j, Bundle bundle) {
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = j;
        this.e = bundle;
    }

    public b(int i, long j, Bundle bundle) {
        this.a = i;
        this.b = 0;
        this.c = "";
        this.d = j;
        this.e = bundle;
    }

    public Bundle a() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public int d() {
        return this.a;
    }

    public long e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != bVar.a || this.b != bVar.b || this.d != bVar.d) {
            return false;
        }
        String str = this.c;
        if (str == null ? bVar.c != null : !str.equals(bVar.c)) {
            return false;
        }
        Bundle bundle = this.e;
        return bundle != null ? bundle.equals(bVar.e) : bVar.e == null;
    }

    public int hashCode() {
        int i = ((this.a * 31) + this.b) * 31;
        String str = this.c;
        int i2 = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.d;
        int i3 = (((i + hashCode) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        Bundle bundle = this.e;
        if (bundle != null) {
            i2 = bundle.hashCode();
        }
        return i3 + i2;
    }
}
