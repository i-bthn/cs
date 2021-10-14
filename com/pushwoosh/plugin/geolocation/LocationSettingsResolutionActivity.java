package com.pushwoosh.plugin.geolocation;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ResolvableApiException;
import com.pushwoosh.internal.crash.LogSender;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;

public class LocationSettingsResolutionActivity extends Activity {
    public static final String KEY_RESOLUTION = "LocationSettingsResolutionActivityKEY_RESOLUTION";
    public static final int REQUEST_CHECK_SETTINGS = 1;
    private static final String TAG = "LocationSettingsResolutionActivity";

    public static void resolutionSettingApi(Context context, ResolvableApiException resolvableApiException) {
        PWLog.debug(TAG, "Request resolution");
        Intent intent = new Intent(context, LocationSettingsResolutionActivity.class);
        intent.addFlags(335544320);
        intent.putExtra(KEY_RESOLUTION, resolvableApiException.getResolution());
        try {
            context.startActivity(intent);
        } catch (AndroidRuntimeException e) {
            LogSender.submitCustomError(e);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra(KEY_RESOLUTION);
        if (pendingIntent == null) {
            finish();
            return;
        }
        try {
            startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            PWLog.error("Can't start resolution for Resolution: " + pendingIntent, e);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            switch (i2) {
                case -1:
                    EventBus.sendEvent(new ResolutionEvent(true));
                    break;
                case 0:
                    EventBus.sendEvent(new ResolutionEvent(false));
                    break;
            }
        }
        finish();
    }

    public static class ResolutionEvent implements Event {
        boolean success;

        ResolutionEvent(boolean z) {
            this.success = z;
        }

        public boolean isSuccess() {
            return this.success;
        }
    }
}
