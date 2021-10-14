package com.pushwoosh.inbox.ui.presentation.lifecycle;

import android.os.Bundle;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000f"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/lifecycle/LifecycleListener;", "", "onCreate", "", "bundle", "Landroid/os/Bundle;", "onDestroy", "isFinished", "", "onSaveInstanceState", "out", "onStart", "onStop", "onViewCreated", "onViewDestroy", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: LifecycleListener.kt */
public interface LifecycleListener {
    void onCreate(@Nullable Bundle bundle);

    void onDestroy(boolean z);

    void onSaveInstanceState(@NotNull Bundle bundle);

    void onStart();

    void onStop();

    void onViewCreated();

    void onViewDestroy();
}
