package com.google.firebase.analytics.connector;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzb implements Executor {
    static final Executor zzacf = new zzb();

    private zzb() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
