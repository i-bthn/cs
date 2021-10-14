package com.pushwoosh.internal.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.PermissionEvent;
import java.util.ArrayList;

public class PermissionActivity extends TranslucentActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    public static void requestPermissions(Context context, String[] strArr) {
        boolean z;
        Exception e;
        try {
            z = false;
            for (String str : strArr) {
                try {
                    z = z || ContextCompat.checkSelfPermission(context, str) != 0;
                } catch (Exception e2) {
                    e = e2;
                    PWLog.error("an error occurred while trying to requestPermissions", e);
                    if (z) {
                    }
                }
            }
        } catch (Exception e3) {
            e = e3;
            z = false;
            PWLog.error("an error occurred while trying to requestPermissions", e);
            if (z) {
            }
        }
        if (z) {
            PWLog.debug("PermissionActivity", "Request permissions");
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("extra_permissions", strArr);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.utils.TranslucentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String[] strArr = (String[]) intent.getCharSequenceArrayExtra("extra_permissions");
        if (strArr == null) {
            finish();
        } else {
            ActivityCompat.requestPermissions(this, strArr, 1);
        }
    }

    @Override // androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        PWLog.debug("PermissionActivity", "onRequestPermissionsResult");
        if (i != 1) {
            PWLog.warn("PermissionActivity", "Unrecognized request code " + i);
        } else {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (iArr.length <= i2 || iArr[i2] != 0) {
                    arrayList2.add(strArr[i2]);
                } else {
                    arrayList.add(strArr[i2]);
                }
            }
            EventBus.sendEvent(new PermissionEvent(arrayList, arrayList2));
        }
        finish();
    }
}
