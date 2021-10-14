package com.pushwoosh.repository;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.internal.c.g;
import com.pushwoosh.internal.network.PushRequest;
import com.pushwoosh.internal.network.a;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.e;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class s extends PushRequest<Void> implements a {
    public s(List<String> list) {
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:? */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v23, types: [boolean] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            TelephonyManager telephonyManager = AndroidPlatformModule.getManagerProvider().getTelephonyManager();
            if (telephonyManager != null) {
                String simOperator = telephonyManager.getSimOperator();
                if (!TextUtils.isEmpty(simOperator)) {
                    jSONObject2.put("sim_operator_id", simOperator);
                }
                String simOperatorName = telephonyManager.getSimOperatorName();
                if (!TextUtils.isEmpty(simOperatorName)) {
                    jSONObject2.put("sim_operator_name", simOperatorName);
                }
                String networkOperator = telephonyManager.getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator)) {
                    jSONObject2.put("network_operator_id", networkOperator);
                }
                jSONObject2.put("phone_type", telephonyManager.getPhoneType());
                String simCountryIso = telephonyManager.getSimCountryIso();
                if (!TextUtils.isEmpty(simCountryIso)) {
                    jSONObject2.put("sim_country", simCountryIso);
                }
                jSONObject2.put("network_type", telephonyManager.getNetworkType());
            }
        } catch (Exception unused) {
        }
        Pair<String, String> pair = null;
        try {
            ConnectivityManager connectivityManager = AndroidPlatformModule.getManagerProvider().getConnectivityManager();
            NetworkInfo activeNetworkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                jSONObject2.put("connection_type", activeNetworkInfo.getType());
                String typeName = activeNetworkInfo.getTypeName();
                if (!TextUtils.isEmpty(typeName)) {
                    jSONObject2.put("connection_type_name", typeName);
                }
                jSONObject2.put("connection_sub_type", activeNetworkInfo.getSubtype());
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (!TextUtils.isEmpty(subtypeName)) {
                    jSONObject2.put("connection_sub_type_name", subtypeName);
                }
            }
        } catch (Exception unused2) {
        }
        jSONObject2.put("free_internal_space", com.pushwoosh.internal.platform.utils.a.d());
        jSONObject2.put("total_internal_space", com.pushwoosh.internal.platform.utils.a.p());
        jSONObject2.put("free_external_space", com.pushwoosh.internal.platform.utils.a.c());
        jSONObject2.put("total_external_space", com.pushwoosh.internal.platform.utils.a.o());
        jSONObject2.put("battery_level", (double) com.pushwoosh.internal.platform.utils.a.e());
        Object l = com.pushwoosh.internal.platform.utils.a.l();
        if (l != null) {
            jSONObject2.put("installer", l);
        }
        jSONObject2.put("screen_width", com.pushwoosh.internal.platform.utils.a.n());
        jSONObject2.put("screen_height", com.pushwoosh.internal.platform.utils.a.m());
        if (!TextUtils.isEmpty(Build.MODEL)) {
            jSONObject2.put("device_model", Build.MODEL);
        }
        if (!TextUtils.isEmpty(Build.MANUFACTURER)) {
            jSONObject2.put("manufacturer", Build.MANUFACTURER);
        }
        ?? r2 = -1;
        try {
            r2 = e.a();
        } catch (Exception unused3) {
        }
        jSONObject2.put("notification_enabled", r2 == true ? 1 : 0);
        jSONObject2.put("gms_version", com.pushwoosh.internal.platform.utils.a.k());
        jSONObject2.put("device_rooted", com.pushwoosh.internal.platform.utils.a.s());
        jSONObject2.put("firmware", com.pushwoosh.internal.platform.utils.a.j());
        g j = PushwooshPlatform.getInstance().j();
        try {
            String e = PushwooshPlatform.getInstance().o().e();
            if (!TextUtils.isEmpty(e)) {
                pair = j.a(jSONObject2.toString(), e);
                if (pair != null) {
                    jSONObject.put("key", pair.first);
                    jSONObject.put("data", pair.second);
                    return;
                }
                return;
            }
            PWLog.error("Public key is empty");
            throw new com.pushwoosh.exception.a("Public key is empty");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "setAttributes";
    }
}
