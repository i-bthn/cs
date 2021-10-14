package com.pushwoosh.inbox.ui.presentation.view.activity;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.pushwoosh.inbox.ui.PushwooshInboxStyle;
import com.pushwoosh.inbox.ui.R;
import com.pushwoosh.inbox.ui.presentation.view.fragment.InboxFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.cordova.globalization.Globalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\u0004H\u0002J\b\u0010\r\u001a\u00020\u0004H\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002J\b\u0010\u000f\u001a\u00020\u0004H\u0002¨\u0006\u0011"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/activity/InboxActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "attachInboxFragment", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", Globalization.ITEM, "Landroid/view/MenuItem;", "setColorActionBar", "setColorHomeButton", "setTextColorBar", "setTitle", "Companion", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: InboxActivity.kt */
public class InboxActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FRAGMENT_TAG = "pushwoosh.inbox.ui.InboxActivity.InboxFragment";
    @NotNull
    public static final String TAG = "pushwoosh.inbox.ui.InboxActivity";
    private HashMap _$_findViewCache;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/pushwoosh/inbox/ui/presentation/view/activity/InboxActivity$Companion;", "", "()V", "FRAGMENT_TAG", "", "TAG", "pushwoosh-inbox-ui_release"}, k = 1, mv = {1, 1, 15})
    /* compiled from: InboxActivity.kt */
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
        setContentView(R.layout.pw_activity_inbox);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setColorActionBar();
        setTitle();
        setTextColorBar();
        setColorHomeButton();
        attachInboxFragment();
    }

    private final void setColorHomeButton() {
        Integer barAccentColor = PushwooshInboxStyle.INSTANCE.getBarAccentColor();
        if (barAccentColor != null) {
            int intValue = barAccentColor.intValue();
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(intValue, PorterDuff.Mode.SRC_IN);
            if (drawable != null) {
                drawable.setColorFilter(porterDuffColorFilter);
            }
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(drawable);
            }
        }
    }

    private final void setTitle() {
        ActionBar supportActionBar;
        String barTitle = PushwooshInboxStyle.INSTANCE.getBarTitle();
        if (barTitle != null && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.setTitle(barTitle);
        }
    }

    private final void setTextColorBar() {
        Integer barTextColor = PushwooshInboxStyle.INSTANCE.getBarTextColor();
        if (barTextColor != null) {
            int intValue = barTextColor.intValue();
            ActionBar supportActionBar = getSupportActionBar();
            SpannableString spannableString = new SpannableString(supportActionBar != null ? supportActionBar.getTitle() : null);
            spannableString.setSpan(new ForegroundColorSpan(intValue), 0, spannableString.length(), 18);
            ActionBar supportActionBar2 = getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setTitle(spannableString);
            }
        }
    }

    private final void setColorActionBar() {
        Integer barBackgroundColor = PushwooshInboxStyle.INSTANCE.getBarBackgroundColor();
        if (barBackgroundColor != null) {
            int intValue = barBackgroundColor.intValue();
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
            ActionBar supportActionBar2 = getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setBackgroundDrawable(new ColorDrawable(intValue));
            }
        }
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf == null || valueOf.intValue() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r0 != null) goto L_0x0018;
     */
    public void attachInboxFragment() {
        boolean z;
        InboxFragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (findFragmentByTag != null) {
            z = false;
        } else {
            z = true;
        }
        findFragmentByTag = new InboxFragment();
        Intrinsics.checkExpressionValueIsNotNull(findFragmentByTag, "supportFragmentManager.f…       ?: InboxFragment()");
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(beginTransaction, "supportFragmentManager.beginTransaction()");
        if (z) {
            beginTransaction.add(R.id.inboxContentContainer, findFragmentByTag, FRAGMENT_TAG);
        } else {
            beginTransaction.attach(findFragmentByTag);
        }
        beginTransaction.commitNowAllowingStateLoss();
    }
}
