package com.pushwoosh.inbox.b.c;

import com.pushwoosh.inbox.data.InboxMessageType;
import com.pushwoosh.inbox.internal.data.b;
import com.pushwoosh.internal.utils.PWLog;
import org.json.JSONException;
import org.json.JSONObject;

public class c {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[InboxMessageType.values().length];

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
            a[InboxMessageType.PLAIN.ordinal()] = 1;
            a[InboxMessageType.RICH_MEDIA.ordinal()] = 2;
            a[InboxMessageType.URL.ordinal()] = 3;
            a[InboxMessageType.DEEP_LINK.ordinal()] = 4;
            a[InboxMessageType.REMOTE_URL.ordinal()] = 5;
        }
    }

    public static void a(b bVar) {
        b bVar2;
        switch (a.a[com.pushwoosh.inbox.d.a.a(bVar.a()).ordinal()]) {
            case 1:
                bVar2 = new d();
                break;
            case 2:
                bVar2 = new f();
                break;
            case 3:
                bVar2 = new g();
                break;
            case 4:
                bVar2 = new a();
                break;
            case 5:
                bVar2 = new e();
                break;
            default:
                PWLog.error("Unknown inbox message type: " + bVar.g());
                return;
        }
        try {
            bVar2.a(new JSONObject(bVar.a()));
        } catch (JSONException e) {
            PWLog.error("Action params is invalid for inbox: " + bVar.d(), e);
        }
    }
}
