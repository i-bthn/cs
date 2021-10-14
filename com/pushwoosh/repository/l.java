package com.pushwoosh.repository;

import android.content.SharedPreferences;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.internal.preference.PreferenceArrayListValue;
import com.pushwoosh.internal.preference.PreferenceBooleanValue;
import com.pushwoosh.internal.preference.PreferenceClassValue;
import com.pushwoosh.internal.preference.PreferenceIntValue;
import com.pushwoosh.internal.preference.PreferenceJsonObjectValue;
import com.pushwoosh.internal.preference.PreferenceSoundTypeValue;
import com.pushwoosh.internal.preference.PreferenceStringValue;
import com.pushwoosh.internal.preference.PreferenceVibrateTypeValue;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.notification.SoundType;
import com.pushwoosh.notification.VibrateType;

public class l {
    private final PreferenceBooleanValue a;
    private final PreferenceIntValue b;
    private final PreferenceBooleanValue c;
    private final PreferenceBooleanValue d;
    private final PreferenceIntValue e;
    private final PreferenceIntValue f;
    private final PreferenceIntValue g;
    private final PreferenceStringValue h;
    private final PreferenceBooleanValue i;
    private final PreferenceSoundTypeValue j;
    private final PreferenceVibrateTypeValue k;
    private final PreferenceStringValue l;
    private final PreferenceArrayListValue<String> m;
    private final PreferenceJsonObjectValue n;
    private final PreferenceClassValue o;
    private final PreferenceClassValue p;
    private final PreferenceBooleanValue q;
    private final PreferenceStringValue r;

    l(c cVar) {
        PWLog.noise("NotificationPrefs()...");
        SharedPreferences providePrefs = AndroidPlatformModule.getPrefsProvider().providePrefs("com.pushwoosh.pushnotifications");
        this.a = new PreferenceBooleanValue(providePrefs, "dm_multimode", cVar.j());
        this.b = new PreferenceIntValue(providePrefs, "dm_messageid", 1001);
        this.c = new PreferenceBooleanValue(providePrefs, "dm_lightson", cVar.g());
        this.d = new PreferenceBooleanValue(providePrefs, "dm_ledon", false);
        this.e = new PreferenceIntValue(providePrefs, "dm_led_color", -1);
        this.o = new PreferenceClassValue(providePrefs, "pw_notification_factory", cVar.f());
        this.p = new PreferenceClassValue(providePrefs, "pw_notification_factory", cVar.i());
        this.f = new PreferenceIntValue(providePrefs, "pw_notification_background_color", cVar.l());
        this.g = new PreferenceIntValue(providePrefs, "pw_richmedia_delay", cVar.m().richMediaStartDelay());
        this.h = new PreferenceStringValue(providePrefs, "pw_notification_stat_hash", null);
        this.i = new PreferenceBooleanValue(providePrefs, "pw_notifications_enabled", true);
        this.j = new PreferenceSoundTypeValue(providePrefs, "dm_soundtype", SoundType.DEFAULT_MODE);
        this.k = new PreferenceVibrateTypeValue(providePrefs, "dm_vibratetype", VibrateType.DEFAULT_MODE);
        this.l = new PreferenceStringValue(providePrefs, "channel_name", "Push notification");
        this.m = new PreferenceArrayListValue<>(providePrefs, "pushHistoryArray", 16, String.class);
        this.n = new PreferenceJsonObjectValue(providePrefs, "cached_tags_string");
        this.q = new PreferenceBooleanValue(providePrefs, "pw_tags_migration_done", false);
        this.r = new PreferenceStringValue(providePrefs, "pw_custom_data", null);
        PWLog.noise("NotificationPrefs() done");
    }

    static MigrationScheme a(PrefsProvider prefsProvider) {
        MigrationScheme migrationScheme = new MigrationScheme("com.pushwoosh.pushnotifications");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "dm_multimode");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_soundtype");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_vibratetype");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "channel_name");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_messageid");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "dm_lightson");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "dm_ledon");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "dm_led_color");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "pw_notification_factory");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "pushHistoryArray");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "cached_tags_string");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "pw_notification_background_color");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, "pw_notification_stat_hash");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, "pw_richmedia_delay");
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, "pw_notifications_enabled");
        return migrationScheme;
    }

    public PreferenceStringValue a() {
        return this.l;
    }

    public PreferenceStringValue b() {
        return this.r;
    }

    public PreferenceIntValue c() {
        return this.f;
    }

    public PreferenceStringValue d() {
        return this.h;
    }

    public PreferenceIntValue e() {
        return this.e;
    }

    public PreferenceBooleanValue f() {
        return this.d;
    }

    public PreferenceBooleanValue g() {
        return this.c;
    }

    public PreferenceIntValue h() {
        return this.b;
    }

    public PreferenceBooleanValue i() {
        return this.a;
    }

    public PreferenceBooleanValue j() {
        return this.i;
    }

    public PreferenceClassValue k() {
        return this.o;
    }

    public PreferenceArrayListValue<String> l() {
        return this.m;
    }

    public PreferenceIntValue m() {
        return this.g;
    }

    public PreferenceSoundTypeValue n() {
        return this.j;
    }

    public PreferenceClassValue o() {
        return this.p;
    }

    public PreferenceJsonObjectValue p() {
        return this.n;
    }

    public PreferenceBooleanValue q() {
        return this.q;
    }

    public PreferenceVibrateTypeValue r() {
        return this.k;
    }
}
