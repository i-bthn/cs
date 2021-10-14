package com.pushwoosh.inbox.ui.presentation.view.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.pushwoosh.inbox.ui.presentation.lifecycle.Lifecycle;
import com.pushwoosh.inbox.ui.presentation.lifecycle.LifecycleListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\u001a\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/fragment/BaseFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pushwoosh/inbox/ui/presentation/lifecycle/Lifecycle;", "()V", "lifecycleListeners", "", "Lcom/pushwoosh/inbox/ui/presentation/lifecycle/LifecycleListener;", "addLifecycleListener", "", "lifecycleListener", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDestroyView", "onSaveInstanceState", "outState", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "removeLifecycleListener", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BaseFragment.kt */
public class BaseFragment extends Fragment implements Lifecycle {
    private HashMap _$_findViewCache;
    private final List<LifecycleListener> lifecycleListeners = new ArrayList();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.Lifecycle
    public void addLifecycleListener(@NotNull LifecycleListener lifecycleListener) {
        Intrinsics.checkParameterIsNotNull(lifecycleListener, "lifecycleListener");
        this.lifecycleListeners.add(lifecycleListener);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.lifecycle.Lifecycle
    public void removeLifecycleListener(@NotNull LifecycleListener lifecycleListener) {
        Intrinsics.checkParameterIsNotNull(lifecycleListener, "lifecycleListener");
        this.lifecycleListeners.remove(lifecycleListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Iterator<T> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onCreate(bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Iterator<T> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onViewCreated();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Iterator<T> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Iterator<T> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        Iterator<T> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onSaveInstanceState(bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Iterator<T> it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            it.next().onViewDestroy();
        }
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        for (T t : this.lifecycleListeners) {
            FragmentActivity activity = getActivity();
            t.onDestroy(activity != null ? activity.isFinishing() : true);
        }
    }
}
