package com.pushwoosh.repository.config;

import androidx.annotation.NonNull;
import java.util.List;

public class a {
    private List<Channel> a;
    private List<c> b;
    private String c;
    private int d;

    public a(@NonNull List<Channel> list, @NonNull List<c> list2, @NonNull String str, int i) {
        this.a = list;
        this.b = list2;
        this.c = str;
        this.d = i;
    }

    public List<Channel> a() {
        return this.a;
    }

    public List<c> b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public String d() {
        return this.c;
    }
}
