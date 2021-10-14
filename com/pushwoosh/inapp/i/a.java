package com.pushwoosh.inapp.i;

import java.io.Serializable;

public class a implements Serializable {
    private String a;
    private String b;
    private String c;

    public a(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        String str = this.a;
        if (str != null) {
            return str.equals(aVar.a);
        }
        if (aVar.a == null) {
            String str2 = this.b;
            if (str2 != null) {
                if (str2.equals(aVar.b)) {
                    return true;
                }
            } else if (aVar.b == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "HtmlData{code='" + this.a + '\'' + ", url='" + this.b + '\'' + '}';
    }
}
