package com.pushwoosh.inapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.exception.MergeUserException;
import com.pushwoosh.exception.PostEventException;
import com.pushwoosh.function.Callback;
import com.pushwoosh.tags.TagsBundle;

@Deprecated
public class PushwooshInApp {
    private static PushwooshInApp a = new PushwooshInApp();

    @NonNull
    public static PushwooshInApp getInstance() {
        return a;
    }

    @Deprecated
    public void addJavascriptInterface(@NonNull Object obj, @NonNull String str) {
        InAppManager.getInstance().addJavascriptInterface(obj, str);
    }

    @Nullable
    @Deprecated
    public String getUserId() {
        return Pushwoosh.getInstance().getUserId();
    }

    @Deprecated
    public void mergeUserId(@NonNull String str, @NonNull String str2, boolean z, @Nullable Callback<Void, MergeUserException> callback) {
        Pushwoosh.getInstance().mergeUserId(str, str2, z, callback);
    }

    @Deprecated
    public void postEvent(@NonNull String str) {
        InAppManager.getInstance().postEvent(str);
    }

    @Deprecated
    public void postEvent(@NonNull String str, TagsBundle tagsBundle) {
        InAppManager.getInstance().postEvent(str, tagsBundle);
    }

    @Deprecated
    public void postEvent(@NonNull String str, @Nullable TagsBundle tagsBundle, Callback<Void, PostEventException> callback) {
        InAppManager.getInstance().postEvent(str, tagsBundle, callback);
    }

    @Deprecated
    public void registerJavascriptInterface(@NonNull String str, @NonNull String str2) {
        InAppManager.getInstance().registerJavascriptInterface(str, str2);
    }

    @Deprecated
    public void removeJavascriptInterface(@NonNull String str) {
        InAppManager.getInstance().removeJavascriptInterface(str);
    }

    @Deprecated
    public void resetBusinessCasesFrequencyCapping() {
        InAppManager.getInstance().resetBusinessCasesFrequencyCapping();
    }

    @Deprecated
    public void setUserId(@NonNull String str) {
        Pushwoosh.getInstance().setUserId(str);
    }
}
