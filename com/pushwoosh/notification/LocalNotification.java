package com.pushwoosh.notification;

import android.os.Bundle;

public class LocalNotification {
    private int a;
    private Bundle b;

    public static class Builder {
        private LocalNotification a = new LocalNotification();

        public LocalNotification build() {
            return this.a;
        }

        public Builder setBanner(String str) {
            b.b(this.a.b(), str);
            return this;
        }

        public Builder setDelay(int i) {
            this.a.a(i);
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            if (bundle != null) {
                this.a.b().putAll(bundle);
            }
            return this;
        }

        public Builder setLargeIcon(String str) {
            b.c(this.a.b(), str);
            return this;
        }

        public Builder setLink(String str) {
            b.d(this.a.b(), str);
            return this;
        }

        public Builder setMessage(String str) {
            b.e(this.a.b(), str);
            return this;
        }

        public Builder setSmallIcon(String str) {
            b.f(this.a.b(), str);
            return this;
        }

        public Builder setTag(String str) {
            b.g(this.a.b(), str);
            return this;
        }
    }

    private LocalNotification() {
        this.b = new Bundle();
        b.b(this.b, true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i) {
        this.a = i;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public Bundle b() {
        return this.b;
    }
}
