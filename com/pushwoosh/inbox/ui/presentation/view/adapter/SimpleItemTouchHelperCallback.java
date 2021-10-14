package com.pushwoosh.inbox.ui.presentation.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.pushwoosh.inbox.ui.R;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider;
import com.pushwoosh.inbox.ui.utils.ViewExtensionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0003\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J@\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0010H\u0016J \u0010!\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0015H\u0016J\u0018\u0010#\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\tH\u0016J\u000e\u0010%\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u0010J(\u0010&\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/adapter/SimpleItemTouchHelperCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "adapter", "Lcom/pushwoosh/inbox/ui/presentation/view/adapter/ItemTouchHelperAdapter;", "context", "Landroid/content/Context;", "colorSchemeProvider", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "swipeIcon", "", "(Lcom/pushwoosh/inbox/ui/presentation/view/adapter/ItemTouchHelperAdapter;Landroid/content/Context;Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;I)V", "icon", "Landroid/graphics/Bitmap;", "paint", "Landroid/graphics/Paint;", "touchable", "", "getMovementFlags", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "isItemViewSwipeEnabled", "isLongPressDragEnabled", "onChildDraw", "", "canvas", "Landroid/graphics/Canvas;", "dX", "", "dY", "actionState", "isCurrentlyActive", "onMove", "target", "onSwiped", "direction", "setTouchable", "updateState", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: SimpleItemTouchHelperCallback.kt */
public final class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter adapter;
    private final Bitmap icon;
    private final Paint paint;
    private boolean touchable;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder viewHolder2) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(viewHolder2, "target");
        return false;
    }

    public SimpleItemTouchHelperCallback(@NotNull ItemTouchHelperAdapter itemTouchHelperAdapter, @Nullable Context context, @NotNull ColorSchemeProvider colorSchemeProvider, @DrawableRes int i) {
        Intrinsics.checkParameterIsNotNull(itemTouchHelperAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(colorSchemeProvider, "colorSchemeProvider");
        this.adapter = itemTouchHelperAdapter;
        this.touchable = true;
        this.icon = ViewExtensionKt.getBitmap(context, i);
        this.paint = new Paint();
        this.paint.setColor(colorSchemeProvider.getAccentColor());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SimpleItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter, Context context, ColorSchemeProvider colorSchemeProvider, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemTouchHelperAdapter, context, colorSchemeProvider, (i2 & 8) != 0 ? R.drawable.inbox_ic_delete : i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        this.adapter.onItemSwiped(viewHolder.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(@NotNull Canvas canvas, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        RectF rectF;
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        if (i == 1) {
            if (this.icon != null) {
                updateState(f, f2, z, viewHolder);
                View view = viewHolder.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view, "viewHolder.itemView");
                float bottom = ((float) view.getBottom()) - ((float) view.getTop());
                Context context = view.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "itemView.context");
                float pxFromDp = ViewExtensionKt.pxFromDp(context, 20.0f);
                float height = bottom > ((float) this.icon.getHeight()) ? (bottom / ((float) 2)) - (((float) this.icon.getHeight()) / 2.0f) : bottom / ((float) 3);
                if (f > ((float) 0)) {
                    canvas.drawRect(new RectF((float) view.getLeft(), (float) view.getTop(), f, (float) view.getBottom()), this.paint);
                    rectF = new RectF(((float) view.getLeft()) + pxFromDp, ((float) view.getTop()) + height, ((float) view.getLeft()) + ((float) this.icon.getWidth()) + pxFromDp, ((float) view.getBottom()) - height);
                } else {
                    canvas.drawRect(new RectF(((float) view.getRight()) + f, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom()), this.paint);
                    rectF = new RectF((((float) view.getRight()) - pxFromDp) - ((float) this.icon.getWidth()), ((float) view.getTop()) + height, ((float) view.getRight()) - pxFromDp, ((float) view.getBottom()) - height);
                }
                canvas.drawBitmap(this.icon, (Rect) null, rectF, this.paint);
            } else {
                return;
            }
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
    }

    private final void updateState(float f, float f2, boolean z, RecyclerView.ViewHolder viewHolder) {
        if (f == 0.0f && f2 == 0.0f) {
            if (z) {
                this.adapter.startSwipe();
            } else {
                this.adapter.stopSwipe();
            }
        }
        View view = viewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "viewHolder.itemView");
        if (f != ((float) view.getWidth())) {
            View view2 = viewHolder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "viewHolder.itemView");
            if (f2 != ((float) view2.getHeight())) {
                return;
            }
        }
        if (!z) {
            this.adapter.stopSwipe();
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        if (!this.touchable) {
            return 0;
        }
        return ItemTouchHelper.Callback.makeMovementFlags(0, 48);
    }

    public final void setTouchable(boolean z) {
        this.touchable = z;
    }
}
