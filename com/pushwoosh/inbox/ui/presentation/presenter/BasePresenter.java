package com.pushwoosh.inbox.ui.presentation.presenter;

import android.os.Bundle;
import com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/presenter/BasePresenter;", "Lcom/pushwoosh/inbox/ui/presentation/lifecycle/LifecycleListener;", "()V", "restore", "", "getRestore", "()Z", "setRestore", "(Z)V", "viewEnable", "getViewEnable", "setViewEnable", "onCreate", "", "bundle", "Landroid/os/Bundle;", "onDestroy", "isFinished", "onSaveInstanceState", "out", "onStart", "onStop", "onViewCreated", "onViewDestroy", "restoreState", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BasePresenter.kt */
public class BasePresenter implements LifecycleListener {
    private boolean restore;
    private boolean viewEnable;

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onDestroy(boolean z) {
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onSaveInstanceState(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "out");
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onStart() {
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public void restoreState(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
    }

    /* access modifiers changed from: protected */
    public final boolean getViewEnable() {
        return this.viewEnable;
    }

    /* access modifiers changed from: protected */
    public final void setViewEnable(boolean z) {
        this.viewEnable = z;
    }

    /* access modifiers changed from: protected */
    public final boolean getRestore() {
        return this.restore;
    }

    /* access modifiers changed from: protected */
    public final void setRestore(boolean z) {
        this.restore = z;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onCreate(@Nullable Bundle bundle) {
        this.restore = bundle != null;
        if (bundle != null) {
            restoreState(bundle);
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onViewCreated() {
        this.viewEnable = true;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener
    public void onViewDestroy() {
        this.viewEnable = false;
    }
}
