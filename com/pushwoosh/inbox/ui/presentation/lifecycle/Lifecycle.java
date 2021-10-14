package com.pushwoosh.inbox.ui.presentation.lifecycle;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/lifecycle/Lifecycle;", "", "addLifecycleListener", "", "lifecycleListener", "Lcom/pushwoosh/inbox/ui/presentation/lifecycle/LifecycleListener;", "removeLifecycleListener", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: Lifecycle.kt */
public interface Lifecycle {
    void addLifecycleListener(@NotNull LifecycleListener lifecycleListener);

    void removeLifecycleListener(@NotNull LifecycleListener lifecycleListener);
}
