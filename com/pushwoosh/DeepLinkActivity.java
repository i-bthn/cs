package com.pushwoosh;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.pushwoosh.function.Callback;
import com.pushwoosh.function.Result;
import com.pushwoosh.internal.network.NetworkException;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.network.d;
import com.pushwoosh.internal.platform.utils.a;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.TranslucentActivity;
import com.pushwoosh.repository.RepositoryModule;
import java.util.Locale;

public class DeepLinkActivity extends TranslucentActivity {
    private void a(Context context) {
        d dVar = new d(a.f(), "Imported from the app");
        RequestManager requestManager = NetworkModule.getRequestManager();
        if (requestManager == null) {
            Toast.makeText(context, "Test device registration has been failed. RequestManager is null", 1).show();
        } else {
            requestManager.sendRequest(dVar, new Callback(context) {
                /* class com.pushwoosh.$$Lambda$DeepLinkActivity$uHvIJs9wQV5nGsZRenNcy43hVoQ */
                private final /* synthetic */ Context f$0;

                {
                    this.f$0 = r1;
                }

                @Override // com.pushwoosh.function.Callback
                public final void process(Result result) {
                    DeepLinkActivity.lambda$uHvIJs9wQV5nGsZRenNcy43hVoQ(this.f$0, result);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, Result result) {
        String sb;
        if (result.isSuccess()) {
            sb = "Test device has been registered.";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Test device registration has been failed. ");
            sb2.append(result.getException() == null ? "" : ((NetworkException) result.getException()).getMessage());
            sb = sb2.toString();
        }
        Toast.makeText(context, sb, 1).show();
    }

    private void a(Uri uri) {
        String str;
        String str2;
        String lowerCase = uri.getScheme().toLowerCase(Locale.getDefault());
        String host = uri.getHost();
        String str3 = RepositoryModule.getRegistrationPreferences().applicationId().get();
        if (!lowerCase.equals("pw-" + str3.toLowerCase(Locale.getDefault()))) {
            str = "DeepLinkActivity";
            str2 = "This is not pushwoosh scheme";
        } else if (host.equals("createTestDevice")) {
            PWLog.debug("DeepLinkActivity", "createTestDevice");
            a(getApplicationContext());
            return;
        } else {
            str = "DeepLinkActivity";
            str2 = "unrecognized pushwoosh command";
        }
        PWLog.error(str, str2);
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.utils.TranslucentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if (TextUtils.equals(action, "android.intent.action.VIEW")) {
            a(data);
        }
        finish();
    }
}
