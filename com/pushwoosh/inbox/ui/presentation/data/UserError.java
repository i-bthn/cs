package com.pushwoosh.inbox.ui.presentation.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/data/UserError;", "", "title", "", "message", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)V", "getMessage", "()Ljava/lang/CharSequence;", "getTitle", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: UserError.kt */
public final class UserError {
    @Nullable
    private final CharSequence message;
    @Nullable
    private final CharSequence title;

    public UserError() {
        this(null, null, 3, null);
    }

    public static /* synthetic */ UserError copy$default(UserError userError, CharSequence charSequence, CharSequence charSequence2, int i, Object obj) {
        if ((i & 1) != 0) {
            charSequence = userError.title;
        }
        if ((i & 2) != 0) {
            charSequence2 = userError.message;
        }
        return userError.copy(charSequence, charSequence2);
    }

    @Nullable
    public final CharSequence component1() {
        return this.title;
    }

    @Nullable
    public final CharSequence component2() {
        return this.message;
    }

    @NotNull
    public final UserError copy(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        return new UserError(charSequence, charSequence2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserError)) {
            return false;
        }
        UserError userError = (UserError) obj;
        return Intrinsics.areEqual(this.title, userError.title) && Intrinsics.areEqual(this.message, userError.message);
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        int i = 0;
        int hashCode = (charSequence != null ? charSequence.hashCode() : 0) * 31;
        CharSequence charSequence2 = this.message;
        if (charSequence2 != null) {
            i = charSequence2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "UserError(title=" + this.title + ", message=" + this.message + ")";
    }

    public UserError(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        this.title = charSequence;
        this.message = charSequence2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ UserError(CharSequence charSequence, CharSequence charSequence2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : charSequence, (i & 2) != 0 ? "" : charSequence2);
    }

    @Nullable
    public final CharSequence getTitle() {
        return this.title;
    }

    @Nullable
    public final CharSequence getMessage() {
        return this.message;
    }
}
