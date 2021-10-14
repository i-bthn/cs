package com.pushwoosh.inbox.internal.data;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.internal.utils.JsonUtils;
import com.pushwoosh.internal.utils.PWLog;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements Serializable, Comparable<b> {
    private final String a;
    private final long b;
    private final long c;
    private final long d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final InboxMessageType i;
    private final String j;
    private final String k;
    private final InboxMessageStatus l;
    private final InboxMessageSource m;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[InboxMessageStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            a[InboxMessageStatus.DELIVERED.ordinal()] = 1;
            a[InboxMessageStatus.READ.ordinal()] = 2;
            a[InboxMessageStatus.OPEN.ordinal()] = 3;
            a[InboxMessageStatus.DELETED_BY_USER.ordinal()] = 4;
            a[InboxMessageStatus.DELETED_FROM_SERVICE.ordinal()] = 5;
        }
    }

    /* renamed from: com.pushwoosh.inbox.internal.data.b$b  reason: collision with other inner class name */
    public static class C0019b {
        private String a;
        private long b;
        private long c;
        private long d;
        private String e;
        private String f;
        private String g;
        private String h;
        private InboxMessageType i;
        private String j;
        private String k;
        private InboxMessageStatus l;
        private InboxMessageSource m;

        public C0019b a(long j2) {
            this.c = j2;
            return this;
        }

        public C0019b a(Bundle bundle) {
            this.a = com.pushwoosh.inbox.d.a.b(bundle);
            this.f = com.pushwoosh.inbox.d.a.g(bundle);
            this.g = com.pushwoosh.inbox.d.a.e(bundle);
            try {
                JSONObject jSONObject = new JSONObject(com.pushwoosh.inbox.d.a.c(bundle));
                if (jSONObject.has("image")) {
                    this.h = jSONObject.getString("image");
                }
                if (jSONObject.has("rt")) {
                    this.c = jSONObject.getLong("rt");
                }
            } catch (JSONException e2) {
                PWLog.error("Problem with parsing inboxParams", e2);
            }
            this.d = TimeUnit.MILLISECONDS.toSeconds(com.pushwoosh.inbox.d.a.f(bundle));
            this.i = com.pushwoosh.inbox.d.a.d(bundle);
            this.j = JsonUtils.bundleToJsonWithUserData(bundle).toString();
            this.e = com.pushwoosh.inbox.d.a.a(bundle);
            this.l = InboxMessageStatus.DELIVERED;
            this.m = InboxMessageSource.PUSH;
            return this;
        }

        public C0019b a(InboxMessage inboxMessage) {
            this.a = inboxMessage.getCode();
            this.c = -1;
            this.d = inboxMessage.getSendDate().getTime();
            this.f = inboxMessage.getTitle();
            this.g = inboxMessage.getMessage();
            this.h = inboxMessage.getImageUrl();
            this.i = inboxMessage.getType();
            this.l = inboxMessage.isActionPerformed() ? InboxMessageStatus.OPEN : inboxMessage.isRead() ? InboxMessageStatus.READ : InboxMessageStatus.DELIVERED;
            this.j = "";
            this.m = InboxMessageSource.SERVICE;
            this.e = "";
            return this;
        }

        public C0019b a(InboxMessageType inboxMessageType) {
            this.i = inboxMessageType;
            return this;
        }

        public C0019b a(InboxMessageSource inboxMessageSource) {
            this.m = inboxMessageSource;
            return this;
        }

        public C0019b a(InboxMessageStatus inboxMessageStatus) {
            this.l = inboxMessageStatus;
            return this;
        }

        public C0019b a(String str) {
            this.j = str;
            b(str);
            return this;
        }

        public C0019b a(JSONObject jSONObject) throws JSONException, c {
            if (!jSONObject.has("inbox_id") || !jSONObject.has("order") || !jSONObject.has("rt") || !jSONObject.has("text") || !jSONObject.has("action_type") || !jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                throw new c();
            }
            this.a = jSONObject.getString("inbox_id");
            this.b = jSONObject.getLong("order");
            this.c = jSONObject.getLong("rt");
            this.g = jSONObject.getString("text");
            this.i = InboxMessageType.getByCode(jSONObject.getInt("action_type"));
            this.l = InboxMessageStatus.getByCode(jSONObject.getInt(NotificationCompat.CATEGORY_STATUS));
            if (jSONObject.has("send_date")) {
                this.d = jSONObject.getLong("send_date");
            }
            if (jSONObject.has("title")) {
                this.f = jSONObject.getString("title");
            }
            if (jSONObject.has("image")) {
                this.h = jSONObject.getString("image");
            }
            if (jSONObject.has("action_params")) {
                this.j = jSONObject.getString("action_params");
                b(this.j);
            }
            if (jSONObject.has("hash")) {
                this.e = jSONObject.getString("hash");
            }
            this.m = InboxMessageSource.SERVICE;
            return this;
        }

        public b a() {
            return new b(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, null);
        }

        public C0019b b(long j2) {
            this.b = j2;
            return this;
        }

        public C0019b b(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("b")) {
                        this.k = jSONObject.get("b").toString();
                    }
                } catch (JSONException unused) {
                }
            }
            return this;
        }

        public C0019b c(long j2) {
            this.d = j2;
            return this;
        }

        public C0019b c(String str) {
            this.e = str;
            return this;
        }

        public C0019b d(String str) {
            this.a = str;
            return this;
        }

        public C0019b e(String str) {
            this.h = str;
            return this;
        }

        public C0019b f(String str) {
            this.g = str;
            return this;
        }

        public C0019b g(String str) {
            this.f = str;
            return this;
        }
    }

    public static class c extends Exception {
    }

    private b(String str, long j2, long j3, long j4, String str2, String str3, String str4, String str5, InboxMessageType inboxMessageType, String str6, String str7, InboxMessageStatus inboxMessageStatus, InboxMessageSource inboxMessageSource) {
        this.a = str;
        this.b = j2;
        this.c = j3;
        this.d = j4;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
        this.i = inboxMessageType;
        this.j = str6;
        this.k = str7;
        this.l = inboxMessageStatus;
        this.m = inboxMessageSource;
    }

    /* synthetic */ b(String str, long j2, long j3, long j4, String str2, String str3, String str4, String str5, InboxMessageType inboxMessageType, String str6, String str7, InboxMessageStatus inboxMessageStatus, InboxMessageSource inboxMessageSource, a aVar) {
        this(str, j2, j3, j4, str2, str3, str4, str5, inboxMessageType, str6, str7, inboxMessageStatus, inboxMessageSource);
    }

    /* renamed from: a */
    public int compareTo(@NonNull b bVar) {
        String str;
        int compare = this.m.compare(bVar.m);
        if (compare == 0) {
            compare = Long.valueOf(this.b).compareTo(Long.valueOf(bVar.b));
        }
        if (compare == 0) {
            compare = Long.valueOf(this.d).compareTo(Long.valueOf(bVar.d));
        }
        if (compare == 0) {
            String str2 = this.f;
            if (str2 != null && (str = bVar.f) != null) {
                compare = str2.compareTo(str);
            } else if (this.f == null && bVar.f != null) {
                compare = 1;
            } else if (this.f != null) {
                compare = -1;
            }
        }
        return compare == 0 ? this.a.compareTo(bVar.a) : compare;
    }

    public String a() {
        return this.j;
    }

    public long b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        String str = this.a;
        return str != null ? str.equals(bVar.a) : bVar.a == null;
    }

    public InboxMessageStatus f() {
        return this.l;
    }

    public InboxMessageType g() {
        return this.i;
    }

    public String getBannerUrl() {
        return this.k;
    }

    public String getMessage() {
        return this.g;
    }

    public long getSendDate() {
        return this.d;
    }

    public String getTitle() {
        return this.f;
    }

    public long h() {
        return this.b;
    }

    public int hashCode() {
        String str = this.a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String i() {
        if (TextUtils.isEmpty(this.j)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.j);
            if (jSONObject.has("md")) {
                return jSONObject.get("md").toString();
            }
        } catch (JSONException e2) {
            PWLog.error(e2.getMessage());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isRead() {
        switch (a.a[this.l.ordinal()]) {
            case 1:
                return false;
            case 2:
            case 3:
                return true;
            case 4:
            case 5:
            default:
                return false;
        }
    }

    public InboxMessageSource j() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        switch (a.a[this.l.ordinal()]) {
            case 1:
            case 2:
                return false;
            case 3:
                return true;
            case 4:
            case 5:
            default:
                return false;
        }
    }

    public boolean l() {
        switch (a.a[this.l.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return false;
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }
}
