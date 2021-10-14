package com.pushwoosh.repository;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.internal.platform.utils.GeneralUtils;
import com.pushwoosh.internal.preference.PreferenceBooleanValue;
import com.pushwoosh.internal.preference.PreferenceIntValue;
import com.pushwoosh.internal.preference.PreferenceLongValue;
import com.pushwoosh.internal.preference.PreferenceStringValue;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import java.util.Locale;

public class RegistrationPrefs implements q {
    private static final String BASE_API_URL_FORMAT = "https://%s.api.pushwoosh.com/json/1.3/";
    private static final String COMMUNICATION_ENABLE = "pw_communication_enable";
    private static final String GDPR_ENABLE = "pw_gdpr_enable";
    private static final String HWID = "pw_hwid";
    private static final String OLD_BASE_API_URL = "https://cp.pushwoosh.com/json/1.3/";
    private static final String PREFERENCE = "com.pushwoosh.registration";
    private static final String PROPERTY_APPLICATION_ID = "application_id";
    private static final String PROPERTY_APP_VERSION = "app_version";
    private static final String PROPERTY_BASE_URL = "pw_base_url";
    private static final String PROPERTY_DEVICE_ID = "device_id";
    private static final String PROPERTY_FORCE_REGISTER = "force_register";
    private static final String PROPERTY_IS_REGISTERED_FOR_NOTIFICATION = "pw_registered_for_push";
    private static final String PROPERTY_LANGUAGE = "pw_language";
    private static final String PROPERTY_LAST_ATTRIBUTES_REGISTRATION = "last_attributes_registration_change";
    private static final String PROPERTY_LAST_REGISTRATION = "last_registration_change";
    private static final String PROPERTY_LOG_LEVEL = "log_level";
    private static final String PROPERTY_PROJECT_ID = "project_id";
    private static final String PROPERTY_PUSH_TOKEN = "registration_id";
    private static final String PROPERTY_REGISTERED_ON_SERVER = "registered_on_server";
    private static final String PROPERTY_SETTAGS_FAILED = "settags_failed";
    private static final String PROPERTY_USER_ID = "user_id";
    private static final String REMOVE_ALL_DEVICE_DATA = "pw_remove_all_device_data";
    private static final String TAG = "RegistrationPrefs";
    private final PreferenceStringValue applicationId;
    private final PreferenceStringValue baseUrl;
    private final PreferenceBooleanValue communicationEnable;
    private final c config;
    private final PreferenceStringValue deviceId;
    private final d deviceRegistrar;
    private final PreferenceBooleanValue forceRegister;
    private final PreferenceBooleanValue gdprEnable;
    private final PreferenceStringValue hwid;
    private final PreferenceStringValue language;
    private final PreferenceLongValue lastAttributesRegistration;
    private final PreferenceLongValue lastPushRegistration;
    private final PreferenceStringValue logLevel;
    private final PreferenceStringValue projectId;
    private final PreferenceStringValue pushToken;
    private PreferenceBooleanValue registeredForPush;
    private final PreferenceBooleanValue registeredOnServer;
    private final PreferenceBooleanValue removeAllDeviceData;
    private final PreferenceBooleanValue setTagsFailed;
    private final PreferenceStringValue userId;

    RegistrationPrefs(c cVar, d dVar) {
        PWLog.noise("RegistrationPrefs()...");
        this.config = cVar;
        this.deviceRegistrar = dVar;
        SharedPreferences providePrefs = AndroidPlatformModule.getPrefsProvider().providePrefs(PREFERENCE);
        this.applicationId = new PreferenceStringValue(providePrefs, PROPERTY_APPLICATION_ID, "");
        if (this.applicationId.get().isEmpty() && cVar.b() != null) {
            this.applicationId.set(cVar.b());
        }
        this.projectId = new PreferenceStringValue(providePrefs, PROPERTY_PROJECT_ID, "");
        if (this.projectId.get().isEmpty() && cVar.o() != null) {
            this.projectId.set(cVar.o());
        }
        this.pushToken = new PreferenceStringValue(providePrefs, PROPERTY_PUSH_TOKEN, "");
        PreferenceIntValue preferenceIntValue = new PreferenceIntValue(providePrefs, PROPERTY_APP_VERSION, 0);
        String str = this.pushToken.get();
        this.registeredForPush = new PreferenceBooleanValue(providePrefs, PROPERTY_IS_REGISTERED_FOR_NOTIFICATION, str != null && !str.isEmpty());
        this.forceRegister = new PreferenceBooleanValue(providePrefs, PROPERTY_FORCE_REGISTER, false);
        int appVersion = GeneralUtils.getAppVersion();
        if (preferenceIntValue.get() != appVersion) {
            PWLog.noise(TAG, "App version changed from " + preferenceIntValue.get() + " to " + appVersion + "; resetting registration id");
            this.pushToken.set("");
            preferenceIntValue.set(appVersion);
        }
        this.registeredOnServer = new PreferenceBooleanValue(providePrefs, PROPERTY_REGISTERED_ON_SERVER, false);
        this.lastPushRegistration = new PreferenceLongValue(providePrefs, PROPERTY_LAST_REGISTRATION, 0);
        this.lastAttributesRegistration = new PreferenceLongValue(providePrefs, PROPERTY_LAST_ATTRIBUTES_REGISTRATION, 0);
        this.userId = new PreferenceStringValue(providePrefs, PROPERTY_USER_ID, "");
        this.deviceId = new PreferenceStringValue(providePrefs, PROPERTY_DEVICE_ID, "");
        this.logLevel = new PreferenceStringValue(providePrefs, PROPERTY_LOG_LEVEL, cVar.d());
        this.setTagsFailed = new PreferenceBooleanValue(providePrefs, PROPERTY_SETTAGS_FAILED, false);
        this.communicationEnable = new PreferenceBooleanValue(providePrefs, COMMUNICATION_ENABLE, true);
        this.removeAllDeviceData = new PreferenceBooleanValue(providePrefs, REMOVE_ALL_DEVICE_DATA, false);
        this.gdprEnable = new PreferenceBooleanValue(providePrefs, GDPR_ENABLE, true);
        this.baseUrl = new PreferenceStringValue(providePrefs, PROPERTY_BASE_URL, "");
        PreferenceStringValue preferenceStringValue = this.baseUrl;
        preferenceStringValue.set(computeBaseUrl(preferenceStringValue.get()));
        this.hwid = new PreferenceStringValue(providePrefs, HWID, "");
        this.language = new PreferenceStringValue(providePrefs, PROPERTY_LANGUAGE, Locale.getDefault().getLanguage());
        PWLog.noise("RegistrationPrefs() done");
    }

    private String computeBaseUrl(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://")) {
            str = getDefaultBaseUrl();
        }
        if (str.endsWith("/")) {
            return str;
        }
        return str + "/";
    }

    static MigrationScheme provideMigrationScheme(PrefsProvider prefsProvider) {
        boolean z;
        String str;
        MigrationScheme migrationScheme = new MigrationScheme(PREFERENCE);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_APPLICATION_ID);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_PROJECT_ID);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_PUSH_TOKEN);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.INT, PROPERTY_APP_VERSION);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, PROPERTY_REGISTERED_ON_SERVER);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.LONG, PROPERTY_LAST_REGISTRATION);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.LONG, PROPERTY_LAST_ATTRIBUTES_REGISTRATION);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_USER_ID);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_DEVICE_ID);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_LOG_LEVEL);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.STRING, PROPERTY_BASE_URL);
        migrationScheme.put(prefsProvider, MigrationScheme.AvailableType.BOOLEAN, PROPERTY_SETTAGS_FAILED);
        SharedPreferences providePrefs = prefsProvider.providePrefs(PREFERENCE);
        if (providePrefs == null) {
            return migrationScheme;
        }
        if (providePrefs.contains(PROPERTY_IS_REGISTERED_FOR_NOTIFICATION)) {
            z = providePrefs.getBoolean(PROPERTY_IS_REGISTERED_FOR_NOTIFICATION, false);
            str = PROPERTY_IS_REGISTERED_FOR_NOTIFICATION;
        } else {
            String string = providePrefs.getString(PROPERTY_PUSH_TOKEN, "");
            str = PROPERTY_IS_REGISTERED_FOR_NOTIFICATION;
            z = !TextUtils.isEmpty(string);
        }
        migrationScheme.putBoolean(str, z);
        return migrationScheme;
    }

    public PreferenceStringValue applicationId() {
        return this.applicationId;
    }

    public PreferenceStringValue baseUrl() {
        return this.baseUrl;
    }

    public void clearSenderIdInfo() {
        pushToken().set("");
        lastPushRegistration().set(0);
    }

    public PreferenceBooleanValue communicationEnable() {
        return this.communicationEnable;
    }

    public PreferenceStringValue deviceId() {
        return this.deviceId;
    }

    public PreferenceBooleanValue forceRegister() {
        return this.forceRegister;
    }

    public PreferenceBooleanValue gdprEnable() {
        return this.gdprEnable;
    }

    public String getDefaultBaseUrl() {
        String a = this.config.a();
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String str = this.applicationId.get();
        if (TextUtils.equals(str, "") || str.contains(".")) {
            return OLD_BASE_API_URL;
        }
        return String.format(BASE_API_URL_FORMAT, str);
    }

    public PreferenceStringValue hwid() {
        return this.hwid;
    }

    public PreferenceBooleanValue isRegisteredForPush() {
        return this.registeredForPush;
    }

    public PreferenceStringValue language() {
        return this.language;
    }

    public PreferenceLongValue lastAttributesRegistration() {
        return this.lastAttributesRegistration;
    }

    public PreferenceLongValue lastPushRegistration() {
        return this.lastPushRegistration;
    }

    public PreferenceStringValue logLevel() {
        return this.logLevel;
    }

    public PreferenceStringValue projectId() {
        return this.projectId;
    }

    public PreferenceStringValue pushToken() {
        return this.pushToken;
    }

    public PreferenceBooleanValue registeredOnServer() {
        return this.registeredOnServer;
    }

    public PreferenceBooleanValue removeAllDeviceData() {
        return this.removeAllDeviceData;
    }

    public void removeAppId() {
        applicationId().set("");
        baseUrl().set("");
        lastAttributesRegistration().set(0);
        lastPushRegistration().set(0);
        setTagsFailed().set(false);
        this.registeredOnServer.set(false);
    }

    public void removeSenderId() {
        clearSenderIdInfo();
        projectId().set("");
    }

    public void setAppId(String str) {
        applicationId().set(str);
        baseUrl().set(getDefaultBaseUrl());
    }

    public void setLanguage(@Nullable String str) {
        if (str == null || str.length() != 2) {
            str = Locale.getDefault().getLanguage();
        }
        language().set(str);
        lastPushRegistration().set(0);
        this.deviceRegistrar.a();
    }

    public PreferenceBooleanValue setTagsFailed() {
        return this.setTagsFailed;
    }

    public PreferenceStringValue userId() {
        return this.userId;
    }
}
