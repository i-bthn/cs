package com.pushwoosh.repository;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.pushwoosh.internal.network.LoggableRequest;
import com.pushwoosh.internal.network.PushRequest;
import java.math.BigDecimal;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class x extends PushRequest<Void> implements LoggableRequest {
    private String a;
    private Date b;
    private long c = 1;
    private String d;
    private BigDecimal e;

    public x(String str, BigDecimal bigDecimal, String str2, Date date) {
        this.a = str;
        this.b = date;
        this.e = bigDecimal;
        this.d = str2;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("productIdentifier", this.a);
        jSONObject.put("transactionDate", this.b.getTime() / 1000);
        jSONObject.put(FirebaseAnalytics.Param.QUANTITY, this.c);
        jSONObject.put("currency", this.d);
        jSONObject.put(FirebaseAnalytics.Param.PRICE, this.e);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "setPurchase";
    }
}
