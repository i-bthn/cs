package com.pushwoosh.internal.platform.prefs.migration;

import android.content.SharedPreferences;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.utils.PWLog;
import java.util.HashMap;
import java.util.Map;

public class MigrationScheme {
    private final String a;
    private final Map<String, Object> b = new HashMap();

    public enum AvailableType {
        STRING,
        BOOLEAN,
        LONG,
        INT
    }

    /* access modifiers changed from: package-private */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[AvailableType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            a[AvailableType.STRING.ordinal()] = 1;
            a[AvailableType.BOOLEAN.ordinal()] = 2;
            a[AvailableType.LONG.ordinal()] = 3;
            try {
                a[AvailableType.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public MigrationScheme(String str) {
        this.a = str;
    }

    /* access modifiers changed from: package-private */
    public void a(PrefsProvider prefsProvider) {
        PWLog.noise("MigrationScheme", "Implement scheme with scheme: " + this.b);
        SharedPreferences providePrefs = prefsProvider.providePrefs(this.a);
        if (providePrefs == null) {
            PWLog.error("Incorrect state of the app preferences is null");
            return;
        }
        SharedPreferences.Editor edit = providePrefs.edit();
        for (Map.Entry<String, Object> entry : this.b.entrySet()) {
            try {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Boolean) {
                    edit.putBoolean(key, ((Boolean) value).booleanValue());
                } else if (value instanceof String) {
                    edit.putString(key, (String) value);
                } else if (value instanceof Long) {
                    edit.putLong(key, ((Long) value).longValue());
                } else if (value instanceof Integer) {
                    edit.putInt(key, ((Integer) value).intValue());
                } else {
                    PWLog.noise("MigrationScheme", "Unknown format for key: " + key);
                }
            } catch (Exception unused) {
                PWLog.noise("MigrationScheme", "Failed put value to editor");
            }
        }
        edit.apply();
    }

    public void put(PrefsProvider prefsProvider, AvailableType availableType, String str) {
        Object obj;
        SharedPreferences providePrefs = prefsProvider.providePrefs(this.a);
        if (providePrefs != null) {
            if (!providePrefs.contains(str)) {
                providePrefs.getAll();
                if (!providePrefs.contains(str)) {
                    return;
                }
            }
            try {
                switch (a.a[availableType.ordinal()]) {
                    case 1:
                        obj = providePrefs.getString(str, "");
                        break;
                    case 2:
                        obj = Boolean.valueOf(providePrefs.getBoolean(str, false));
                        break;
                    case 3:
                        obj = Long.valueOf(providePrefs.getLong(str, 0));
                        break;
                    case 4:
                        obj = Integer.valueOf(providePrefs.getInt(str, 0));
                        break;
                    default:
                        return;
                }
                this.b.put(str, obj);
            } catch (Exception unused) {
                PWLog.noise("MigrationScheme", "Failed providing data with key: " + str);
            }
        }
    }

    public void putBoolean(String str, boolean z) {
        this.b.put(str, Boolean.valueOf(z));
    }

    public void putInt(String str, int i) {
        this.b.put(str, Integer.valueOf(i));
    }

    public void putLong(String str, long j) {
        this.b.put(str, Long.valueOf(j));
    }

    public void putString(String str, String str2) {
        this.b.put(str, str2);
    }
}
