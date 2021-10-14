package com.pushwoosh.inbox.ui.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import com.pushwoosh.inbox.ui.presentation.view.adapter.BaseRecyclerAdapter.ViewHolder;
import com.pushwoosh.inbox.ui.utils.ViewExtensionKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.cordova.globalization.Globalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\b&\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00030\u0002*\u0004\b\u0001\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004:\u0001AB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010#\u001a\u00020\u001e2\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010%J\u001b\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00028\u0001¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u0017H\u0014¢\u0006\u0002\u0010,J\u0006\u0010-\u001a\u00020\u001eJ\u001d\u0010.\u001a\u00028\u00002\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0017H$¢\u0006\u0002\u00102J\u0017\u00103\u001a\u0004\u0018\u00018\u00012\u0006\u0010'\u001a\u00020\u0017H\u0016¢\u0006\u0002\u00104J\b\u00105\u001a\u00020\u0017H\u0016J\u001d\u00106\u001a\u00020\u001e2\u0006\u0010+\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010,J\u001d\u00107\u001a\u00028\u00002\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0017H\u0016¢\u0006\u0002\u00102J\u0015\u00108\u001a\u00020\u001e2\u0006\u0010+\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00109J\u0015\u0010:\u001a\u00020\u001e2\u0006\u0010+\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00109J\u0015\u0010;\u001a\u00020\u001e2\u0006\u0010+\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00109J\u0016\u0010<\u001a\u00020\u001e2\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010%J\u001b\u0010=\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010>\u001a\u00028\u0001¢\u0006\u0002\u0010)J \u0010?\u001a\u00020\u001e2\u0018\u0010@\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001e0\u001cR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00028\u00010\u0019j\b\u0012\u0004\u0012\u00028\u0001`\u001aX\u0004¢\u0006\u0002\n\u0000R,\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001e0\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006B"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter;", "VH", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;", "Model", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "collection", "", "getCollection", "()Ljava/util/List;", "getContext", "()Landroid/content/Context;", "duration", "", "getDuration", "()J", "interpolator", "Landroid/view/animation/Interpolator;", "getInterpolator", "()Landroid/view/animation/Interpolator;", "lastPosition", "", "mDataList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "mOnItemClickListener", "Lkotlin/Function2;", "Landroid/view/View;", "", "getMOnItemClickListener", "()Lkotlin/jvm/functions/Function2;", "setMOnItemClickListener", "(Lkotlin/jvm/functions/Function2;)V", "addCollection", "list", "", "addItem", "position", "model", "(ILjava/lang/Object;)V", "animateItem", "holder", "(Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;I)V", "clearCollection", "createViewHolderInstance", "parent", "Landroid/view/ViewGroup;", "viewType", "(Landroid/view/ViewGroup;I)Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;", "getItem", "(I)Ljava/lang/Object;", "getItemCount", "onBindViewHolder", "onCreateViewHolder", "onViewAttachedToWindow", "(Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;)V", "onViewDetachedFromWindow", "onViewRecycled", "setCollection", "setItem", Globalization.ITEM, "setOnItemClickListener", "onItemClickListener", "ViewHolder", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: BaseRecyclerAdapter.kt */
public abstract class BaseRecyclerAdapter<VH extends ViewHolder<Model>, Model> extends RecyclerView.Adapter<VH> {
    @NotNull
    private final Context context;
    private final long duration = 250;
    @NotNull
    private final Interpolator interpolator = new LinearInterpolator();
    private int lastPosition = -1;
    private final ArrayList<Model> mDataList = new ArrayList<>();
    @NotNull
    private Function2<? super View, ? super Integer, Unit> mOnItemClickListener = BaseRecyclerAdapter$mOnItemClickListener$1.INSTANCE;

    /* access modifiers changed from: protected */
    public void animateItem(@NotNull VH vh, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract VH createViewHolderInstance(@NotNull ViewGroup viewGroup, int i);

    public BaseRecyclerAdapter(@NotNull Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: kotlin.jvm.functions.Function2<? super android.view.View, ? super java.lang.Integer, kotlin.Unit>, kotlin.jvm.functions.Function2<android.view.View, java.lang.Integer, kotlin.Unit> */
    /* access modifiers changed from: protected */
    @NotNull
    public final Function2<View, Integer, Unit> getMOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    /* access modifiers changed from: protected */
    public final void setMOnItemClickListener(@NotNull Function2<? super View, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.mOnItemClickListener = function2;
    }

    /* access modifiers changed from: protected */
    public final long getDuration() {
        return this.duration;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final Interpolator getInterpolator() {
        return this.interpolator;
    }

    public final void setOnItemClickListener(@NotNull Function2<? super View, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "onItemClickListener");
        this.mOnItemClickListener = function2;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public VH onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        VH createViewHolderInstance = createViewHolderInstance(viewGroup, i);
        createViewHolderInstance.onCreate();
        return createViewHolderInstance;
    }

    public void onBindViewHolder(@NotNull VH vh, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Model item = getItem(i);
        vh.bindView();
        vh.fillView(item, i);
        if (i > this.lastPosition) {
            animateItem(vh, i);
            this.lastPosition = i;
            return;
        }
        View view = ((ViewHolder) vh).itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        ViewExtensionKt.clear(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDataList.size();
    }

    public final void setCollection(@Nullable Collection<? extends Model> collection) {
        this.mDataList.clear();
        if (collection != null) {
            this.mDataList.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public final void addCollection(@Nullable Collection<? extends Model> collection) {
        if (collection != null) {
            this.mDataList.addAll(collection);
        }
        notifyDataSetChanged();
    }

    @NotNull
    public final List<Model> getCollection() {
        return this.mDataList;
    }

    @Nullable
    public Model getItem(int i) {
        return this.mDataList.get(i);
    }

    public final void setItem(int i, Model model) {
        this.mDataList.set(i, model);
        notifyItemChanged(i);
    }

    public final void clearCollection() {
        this.mDataList.clear();
        notifyDataSetChanged();
    }

    public final void addItem(int i, Model model) {
        this.mDataList.add(i, model);
        notifyItemInserted(i);
    }

    public void onViewAttachedToWindow(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        super.onViewAttachedToWindow((RecyclerView.ViewHolder) vh);
        vh.onAttach$pushwoosh_inbox_ui_release();
    }

    public void onViewDetachedFromWindow(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        super.onViewDetachedFromWindow((RecyclerView.ViewHolder) vh);
        vh.onDetach$pushwoosh_inbox_ui_release();
    }

    public void onViewRecycled(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        super.onViewRecycled((RecyclerView.ViewHolder) vh);
        vh.recycled$pushwoosh_inbox_ui_release();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u00022\u00020\u0003B)\b\u0016\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t¢\u0006\u0002\u0010\nB\u001d\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u000e\u0010\b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t¢\u0006\u0002\u0010\rJ\u0006\u0010\u0012\u001a\u00020\u0013J\u001f\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00018\u00022\u0006\u0010\u0016\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0019J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0006\u0010\u001c\u001a\u00020\u0013J\r\u0010\u001d\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001eJ\r\u0010\u001f\u001a\u00020\u0013H\u0000¢\u0006\u0002\b R\u0016\u0010\b\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8DX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter$ViewHolder;", "Model", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "layoutId", "", "parent", "Landroid/view/ViewGroup;", "adapter", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter;", "(ILandroid/view/ViewGroup;Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter;)V", "view", "Landroid/view/View;", "(Landroid/view/View;Lcom/pushwoosh/inbox/ui/presentation/view/adapter/BaseRecyclerAdapter;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "bindView", "", "fillView", "model", "position", "(Ljava/lang/Object;I)V", "onAttach", "onAttach$pushwoosh_inbox_ui_release", "onClick", "v", "onCreate", "onDetach", "onDetach$pushwoosh_inbox_ui_release", "recycled", "recycled$pushwoosh_inbox_ui_release", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: BaseRecyclerAdapter.kt */
    public static abstract class ViewHolder<Model> extends RecyclerView.ViewHolder implements View.OnClickListener {
        private BaseRecyclerAdapter<?, ?> adapter;

        public abstract void fillView(@Nullable Model model, int i);

        public final void onAttach$pushwoosh_inbox_ui_release() {
        }

        public final void onCreate() {
        }

        public final void onDetach$pushwoosh_inbox_ui_release() {
        }

        public final void recycled$pushwoosh_inbox_ui_release() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View view, @NotNull BaseRecyclerAdapter<?, ?> baseRecyclerAdapter) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(baseRecyclerAdapter, "adapter");
            this.adapter = baseRecyclerAdapter;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        public ViewHolder(@LayoutRes int i, @NotNull ViewGroup viewGroup, @NotNull BaseRecyclerAdapter<?, ?> baseRecyclerAdapter) {
            this(r3, baseRecyclerAdapter);
            Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
            Intrinsics.checkParameterIsNotNull(baseRecyclerAdapter, "adapter");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…(layoutId, parent, false)");
        }

        public final void bindView() {
            this.itemView.setOnClickListener(this);
        }

        /* access modifiers changed from: protected */
        @NotNull
        public final Context getContext() {
            View view = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
            Context context = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "itemView.context");
            return context;
        }

        public void onClick(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "v");
            this.adapter.getMOnItemClickListener().invoke(view, Integer.valueOf(getAdapterPosition()));
        }
    }
}
