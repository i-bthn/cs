package com.pushwoosh.inbox.ui.presentation.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.snackbar.Snackbar;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import com.pushwoosh.inbox.ui.R;
import com.pushwoosh.inbox.ui.presentation.data.UserError;
import com.pushwoosh.inbox.ui.presentation.presenter.InboxPresenter;
import com.pushwoosh.inbox.ui.presentation.presenter.InboxView;
import com.pushwoosh.inbox.ui.presentation.view.activity.AttachmentActivity;
import com.pushwoosh.inbox.ui.presentation.view.adapter.SimpleItemTouchHelperCallback;
import com.pushwoosh.inbox.ui.presentation.view.adapter.inbox.InboxAdapter;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProviderFactory;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J&\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u001a\u0010\"\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010#\u001a\u00020\bH\u0016J\u0016\u0010$\u001a\u00020\b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\b\u0010(\u001a\u00020\bH\u0016J\b\u0010)\u001a\u00020\bH\u0016J0\u0010*\u001a\u00020\b2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020,2\b\b\u0002\u0010.\u001a\u00020,2\b\b\u0002\u0010/\u001a\u00020,H\u0002J\u001a\u00100\u001a\u00020\b2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u0002R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/fragment/InboxFragment;", "Lcom/pushwoosh/inbox/ui/presentation/view/fragment/BaseFragment;", "Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxView;", "()V", "attachmentClickListener", "Lkotlin/Function2;", "", "Landroid/view/View;", "", "callback", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/SimpleItemTouchHelperCallback;", "colorSchemeProvider", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "inboxAdapter", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/inbox/InboxAdapter;", "inboxPresenter", "Lcom/pushwoosh/inbox/ui/presentation/presenter/InboxPresenter;", "failedLoadingInboxList", "userError", "Lcom/pushwoosh/inbox/ui/presentation/data/UserError;", "hideProgress", "onAttach", "context", "Landroid/content/Context;", "onAttachmentClicked", ImagesContract.URL, "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "showEmptyView", "showList", "inboxList", "", "Lcom/pushwoosh/inbox/data/InboxMessage;", "showSwipeRefreshProgress", "showTotalProgress", "updateContent", "showProgress", "", "isEmpty", "swipeRefresh", "isError", "updateMessageTextView", "messageTextView", "Landroid/widget/TextView;", "message", "", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxFragment.kt */
public class InboxFragment extends BaseFragment implements InboxView {
    private HashMap _$_findViewCache;
    private Function2<? super String, ? super View, Unit> attachmentClickListener = new InboxFragment$attachmentClickListener$1(this);
    private SimpleItemTouchHelperCallback callback;
    private ColorSchemeProvider colorSchemeProvider;
    private InboxAdapter inboxAdapter;
    private InboxPresenter inboxPresenter;

    @Override // com.pushwoosh.inbox.ui.presentation.view.fragment.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.view.fragment.BaseFragment
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

    @Override // androidx.fragment.app.Fragment, com.pushwoosh.inbox.ui.presentation.view.fragment.BaseFragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ InboxAdapter access$getInboxAdapter$p(InboxFragment inboxFragment) {
        InboxAdapter inboxAdapter2 = inboxFragment.inboxAdapter;
        if (inboxAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        return inboxAdapter2;
    }

    public static final /* synthetic */ InboxPresenter access$getInboxPresenter$p(InboxFragment inboxFragment) {
        InboxPresenter inboxPresenter2 = inboxFragment.inboxPresenter;
        if (inboxPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxPresenter");
        }
        return inboxPresenter2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        InboxPresenter inboxPresenter2 = new InboxPresenter(this);
        addLifecycleListener(inboxPresenter2);
        this.inboxPresenter = inboxPresenter2;
        super.onAttach(context);
        this.colorSchemeProvider = ColorSchemeProviderFactory.INSTANCE.generateColorScheme(context);
        ColorSchemeProvider colorSchemeProvider2 = this.colorSchemeProvider;
        if (colorSchemeProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorSchemeProvider");
        }
        this.inboxAdapter = new InboxAdapter(context, colorSchemeProvider2, this.attachmentClickListener);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return LayoutInflater.from(getContext()).inflate(R.layout.pw_fragment_inbox, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment, com.pushwoosh.inbox.ui.presentation.view.fragment.BaseFragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        InboxAdapter inboxAdapter2 = this.inboxAdapter;
        if (inboxAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        inboxAdapter2.setOnItemRemoved(new InboxFragment$onViewCreated$1(this));
        InboxAdapter inboxAdapter3 = this.inboxAdapter;
        if (inboxAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        inboxAdapter3.setOnItemStartSwipe(new InboxFragment$onViewCreated$2(this));
        InboxAdapter inboxAdapter4 = this.inboxAdapter;
        if (inboxAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        inboxAdapter4.setOnItemStopSwipe(new InboxFragment$onViewCreated$3(this));
        InboxAdapter inboxAdapter5 = this.inboxAdapter;
        if (inboxAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        inboxAdapter5.setOnItemClick(new InboxFragment$onViewCreated$4(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "inboxRecyclerView");
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "inboxRecyclerView");
        InboxAdapter inboxAdapter6 = this.inboxAdapter;
        if (inboxAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        recyclerView2.setAdapter(inboxAdapter6);
        Integer backgroundColor = PushwooshInboxStyle.INSTANCE.getBackgroundColor();
        if (backgroundColor != null) {
            ((RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView)).setBackgroundColor(backgroundColor.intValue());
        }
        ColorSchemeProvider colorSchemeProvider2 = this.colorSchemeProvider;
        if (colorSchemeProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorSchemeProvider");
        }
        Drawable divider = colorSchemeProvider2.getDivider();
        if (divider != null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
            dividerItemDecoration.setDrawable(divider);
            ((RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView)).addItemDecoration(dividerItemDecoration);
        }
        InboxAdapter inboxAdapter7 = this.inboxAdapter;
        if (inboxAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        InboxAdapter inboxAdapter8 = inboxAdapter7;
        Context context = getContext();
        ColorSchemeProvider colorSchemeProvider3 = this.colorSchemeProvider;
        if (colorSchemeProvider3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorSchemeProvider");
        }
        this.callback = new SimpleItemTouchHelperCallback(inboxAdapter8, context, colorSchemeProvider3, 0, 8, null);
        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = this.callback;
        if (simpleItemTouchHelperCallback != null) {
            new ItemTouchHelper(simpleItemTouchHelperCallback).attachToRecyclerView((RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView));
            ((SwipeRefreshLayout) _$_findCachedViewById(R.id.inboxSwipeRefreshLayout)).setOnRefreshListener(new InboxFragment$onViewCreated$5(this));
            if (PushwooshInboxStyle.INSTANCE.getListErrorImageDrawable() == null) {
                ((ImageView) _$_findCachedViewById(R.id.inboxErrorImageView)).setImageResource(PushwooshInboxStyle.INSTANCE.getListErrorImage());
            } else {
                ((ImageView) _$_findCachedViewById(R.id.inboxErrorImageView)).setImageDrawable(PushwooshInboxStyle.INSTANCE.getListErrorImageDrawable());
            }
            if (PushwooshInboxStyle.INSTANCE.getListErrorImageDrawable() == null) {
                ((ImageView) _$_findCachedViewById(R.id.inboxEmptyImageView)).setImageResource(PushwooshInboxStyle.INSTANCE.getListEmptyImage());
            } else {
                ((ImageView) _$_findCachedViewById(R.id.inboxEmptyImageView)).setImageDrawable(PushwooshInboxStyle.INSTANCE.getListEmptyImageDrawable());
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.ItemTouchHelper.Callback");
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.InboxView
    public void showSwipeRefreshProgress() {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.inboxSwipeRefreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout, "inboxSwipeRefreshLayout");
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.InboxView
    public void showTotalProgress() {
        updateContent$default(this, true, false, false, false, 14, null);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.InboxView
    public void hideProgress() {
        updateContent$default(this, false, false, false, false, 10, null);
        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = this.callback;
        if (simpleItemTouchHelperCallback != null) {
            simpleItemTouchHelperCallback.setTouchable(true);
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.InboxView
    public void failedLoadingInboxList(@NotNull UserError userError) {
        Intrinsics.checkParameterIsNotNull(userError, "userError");
        View view = getView();
        if (view != null) {
            InboxAdapter inboxAdapter2 = this.inboxAdapter;
            if (inboxAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
            }
            if (inboxAdapter2.getItemCount() == 0) {
                updateContent$default(this, false, false, false, true, 7, null);
                TextView textView = (TextView) _$_findCachedViewById(R.id.inboxErrorTextView);
                Intrinsics.checkExpressionValueIsNotNull(textView, "inboxErrorTextView");
                updateMessageTextView(textView, userError.getMessage());
            } else if (!TextUtils.isEmpty(userError.getMessage())) {
                CharSequence message = userError.getMessage();
                if (message == null) {
                    Intrinsics.throwNpe();
                }
                Snackbar.make(view, message, 0).show();
            }
        }
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.InboxView
    public void showList(@NotNull Collection<? extends InboxMessage> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "inboxList");
        InboxAdapter inboxAdapter2 = this.inboxAdapter;
        if (inboxAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inboxAdapter");
        }
        inboxAdapter2.setCollection(collection);
        updateContent$default(this, false, false, false, false, 13, null);
    }

    @Override // com.pushwoosh.inbox.ui.presentation.presenter.InboxView
    public void showEmptyView() {
        updateContent$default(this, false, true, false, false, 13, null);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.inboxSwipeRefreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout, "inboxSwipeRefreshLayout");
        swipeRefreshLayout.setEnabled(true);
        TextView textView = (TextView) _$_findCachedViewById(R.id.inboxEmptyTextView);
        Intrinsics.checkExpressionValueIsNotNull(textView, "inboxEmptyTextView");
        updateMessageTextView(textView, PushwooshInboxStyle.INSTANCE.getListEmptyText());
    }

    static /* synthetic */ void updateContent$default(InboxFragment inboxFragment, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            if ((i & 2) != 0) {
                z2 = false;
            }
            if ((i & 4) != 0) {
                z3 = false;
            }
            if ((i & 8) != 0) {
                z4 = false;
            }
            inboxFragment.updateContent(z, z2, z3, z4);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateContent");
    }

    private final void updateContent(boolean z, boolean z2, boolean z3, boolean z4) {
        int i = 0;
        int i2 = (!z || z3) ? 0 : 8;
        if (!z || z3) {
            i = 8;
        }
        if (z2) {
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "inboxRecyclerView");
            recyclerView.setVisibility(8);
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.inboxEmpty);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "inboxEmpty");
            linearLayout.setVisibility(i2);
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.inboxError);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "inboxError");
            linearLayout2.setVisibility(8);
        } else if (!z4) {
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "inboxRecyclerView");
            recyclerView2.setVisibility(i2);
            LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(R.id.inboxEmpty);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "inboxEmpty");
            linearLayout3.setVisibility(8);
            LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(R.id.inboxError);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "inboxError");
            linearLayout4.setVisibility(8);
        } else {
            LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(R.id.inboxError);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout5, "inboxError");
            linearLayout5.setVisibility(i2);
            LinearLayout linearLayout6 = (LinearLayout) _$_findCachedViewById(R.id.inboxEmpty);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout6, "inboxEmpty");
            linearLayout6.setVisibility(8);
            RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.inboxRecyclerView);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "inboxRecyclerView");
            recyclerView3.setVisibility(8);
        }
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.inboxTotalProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "inboxTotalProgressBar");
        progressBar.setVisibility(i);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.inboxSwipeRefreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout, "inboxSwipeRefreshLayout");
        swipeRefreshLayout.setRefreshing(z3);
    }

    private final void updateMessageTextView(TextView textView, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(charSequence);
    }

    /* access modifiers changed from: private */
    public final void onAttachmentClicked(String str, View view) {
        Intent intent = new Intent(getActivity(), AttachmentActivity.class);
        intent.putExtra(AttachmentActivity.attachmentUrlExtra, str);
        if (Build.VERSION.SDK_INT >= 21) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, getString(R.string.pw_attachment_transition_id));
                Intrinsics.checkExpressionValueIsNotNull(makeSceneTransitionAnimation, "ActivityOptionsCompat.\n …ttachment_transition_id))");
                startActivity(intent, makeSceneTransitionAnimation.toBundle());
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            activity2.overridePendingTransition(17432576, 17432577);
        }
        startActivity(intent);
    }
}
