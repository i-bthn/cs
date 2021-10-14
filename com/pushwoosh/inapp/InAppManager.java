package com.pushwoosh.inapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.exception.ReloadInAppsException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.inapp.f.d;
import com.pushwoosh.tags.TagsBundle;

public class InAppManager {
    private static InAppManager b = new InAppManager();
    private final c a;

    private InAppManager() {
        c cVar;
        if (PushwooshPlatform.getInstance() != null) {
            cVar = PushwooshPlatform.getInstance().n();
        } else {
            PushwooshPlatform.r();
            cVar = null;
        }
        this.a = cVar;
    }

    @NonNull
    public static InAppManager getInstance() {
        return b;
    }

    public void addJavascriptInterface(@NonNull Object obj, @NonNull String str) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(obj, str);
        }
    }

    public void postEvent(@NonNull String str) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(str, null, null);
        }
    }

    public void postEvent(@NonNull String str, TagsBundle tagsBundle) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(str, tagsBundle, null);
        }
    }

    public void postEvent(@NonNull String str, @Nullable TagsBundle tagsBundle, Callback<Void, PostEventException> callback) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(str, tagsBundle, callback);
        }
    }

    public void registerJavascriptInterface(@NonNull String str, @NonNull String str2) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(str, str2);
        }
    }

    public void reloadInApps() {
        reloadInApps(null);
    }

    public void reloadInApps(Callback<Boolean, ReloadInAppsException> callback) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(callback);
        }
    }

    public void removeJavascriptInterface(@NonNull String str) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(str);
        }
    }

    public void resetBusinessCasesFrequencyCapping() {
        d b2;
        PushwooshPlatform instance = PushwooshPlatform.getInstance();
        if (instance != null && (b2 = instance.b()) != null) {
            b2.a();
        }
    }
}
