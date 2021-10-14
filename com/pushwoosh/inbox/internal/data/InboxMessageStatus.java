package com.pushwoosh.inbox.internal.data;

import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum InboxMessageStatus {
    CREATED(0),
    DELIVERED(1),
    READ(2),
    OPEN(3),
    DELETED_BY_USER(4),
    DELETED_FROM_SERVICE(5);
    
    private int code;

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[InboxMessageStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            a[InboxMessageStatus.CREATED.ordinal()] = 1;
            a[InboxMessageStatus.DELIVERED.ordinal()] = 2;
            a[InboxMessageStatus.READ.ordinal()] = 3;
            a[InboxMessageStatus.OPEN.ordinal()] = 4;
            a[InboxMessageStatus.DELETED_BY_USER.ordinal()] = 5;
            try {
                a[InboxMessageStatus.DELETED_FROM_SERVICE.ordinal()] = 6;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private InboxMessageStatus(int i) {
        this.code = i;
    }

    public static List<InboxMessageStatus> getActualCodes() {
        return Arrays.asList(CREATED, DELIVERED, READ, OPEN);
    }

    @Nullable
    public static InboxMessageStatus getByCode(int i) {
        InboxMessageStatus[] values = values();
        for (InboxMessageStatus inboxMessageStatus : values) {
            if (inboxMessageStatus.code == i) {
                return inboxMessageStatus;
            }
        }
        PWLog.error("Incorrect InboxMessageStatusCode: " + i);
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public Collection<InboxMessageStatus> getLowerStatus() {
        ArrayList arrayList = new ArrayList();
        InboxMessageStatus[] values = values();
        for (InboxMessageStatus inboxMessageStatus : values) {
            if (inboxMessageStatus.isLowerStatus(this)) {
                arrayList.add(inboxMessageStatus);
            }
        }
        return arrayList;
    }

    public boolean isLowerStatus(InboxMessageStatus inboxMessageStatus) {
        switch (a.a[inboxMessageStatus.ordinal()]) {
            case 2:
                if (equals(CREATED)) {
                    return true;
                }
                break;
            case 3:
                if (equals(CREATED) || equals(DELIVERED)) {
                    return true;
                }
                break;
            case 4:
                if (equals(CREATED) || equals(DELIVERED) || equals(READ)) {
                    return true;
                }
                break;
            case 5:
                if (equals(CREATED) || equals(DELIVERED) || equals(READ) || equals(OPEN)) {
                    return true;
                }
                break;
            case 6:
                return true;
        }
        return false;
    }
}
