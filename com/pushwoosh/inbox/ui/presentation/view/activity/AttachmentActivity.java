package com.pushwoosh.inbox.ui.presentation.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.pushwoosh.inbox.ui.R;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProvider;
import com.pushwoosh.inbox.ui.presentation.view.style.ColorSchemeProviderFactory;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/activity/AttachmentActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "colorSchemeProvider", "Lcom/pushwoosh/inbox/ui/presentation/view/style/ColorSchemeProvider;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AttachmentActivity.kt */
public class AttachmentActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String attachmentUrlExtra = "ATTACHMENT_URL_EXTRA";
    private HashMap _$_findViewCache;
    private ColorSchemeProvider colorSchemeProvider;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/activity/AttachmentActivity$Companion;", "", "()V", "attachmentUrlExtra", "", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AttachmentActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pw_activity_attachment);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(attachmentUrlExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                Glide.with((FragmentActivity) this).load(stringExtra).into((ImageView) findViewById(R.id.attachment));
            }
        }
        this.colorSchemeProvider = ColorSchemeProviderFactory.INSTANCE.generateColorScheme(this);
        View findViewById = findViewById(R.id.container);
        ColorSchemeProvider colorSchemeProvider2 = this.colorSchemeProvider;
        if (colorSchemeProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorSchemeProvider");
        }
        findViewById.setBackgroundColor(colorSchemeProvider2.getBackgroundColor());
        findViewById.setOnClickListener(new AttachmentActivity$onCreate$1(this));
    }
}
