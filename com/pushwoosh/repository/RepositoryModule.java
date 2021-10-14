package com.pushwoosh.repository;

import android.content.Context;
import android.os.AsyncTask;
import com.pushwoosh.b;
import com.pushwoosh.internal.Plugin;
import com.pushwoosh.internal.network.g;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.platform.prefs.migration.MigrationScheme;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.c;
import com.pushwoosh.internal.utils.i;
import java.util.ArrayList;
import java.util.Collection;

public class RepositoryModule {
    private static h inboxNotificationStorage;
    private static i localNotificationStorage;
    private static j lockScreenMediaStorage;
    private static l notificationPreferences;
    private static m pushBundleStorage;
    private static RegistrationPrefs registrationPreferences;
    private static g requestStorage;
    private static v silentRichMediaStorage;

    private static void createLocalNotificationStorage() {
        Context applicationContext = AndroidPlatformModule.getApplicationContext();
        if (applicationContext == null) {
            PWLog.error(AndroidPlatformModule.NULL_CONTEXT_MESSAGE);
            return;
        }
        localNotificationStorage = new i(new c(applicationContext));
        new b(applicationContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static h getInboxNotificationStorage() {
        return inboxNotificationStorage;
    }

    public static i getLocalNotificationStorage() {
        return localNotificationStorage;
    }

    public static j getLockScreenMediaStorage() {
        return lockScreenMediaStorage;
    }

    public static l getNotificationPreferences() {
        return notificationPreferences;
    }

    public static m getPushBundleStorage() {
        return pushBundleStorage;
    }

    public static RegistrationPrefs getRegistrationPreferences() {
        return registrationPreferences;
    }

    public static g getRequestStorage() {
        return requestStorage;
    }

    public static v getSilentRichMediaStorage() {
        return silentRichMediaStorage;
    }

    public static void init(c cVar, i iVar, d dVar) {
        migratePrefsIfNeeded(cVar);
        if (notificationPreferences == null) {
            notificationPreferences = new l(cVar);
        }
        if (registrationPreferences == null) {
            registrationPreferences = new RegistrationPrefs(cVar, dVar);
        }
        if (localNotificationStorage == null) {
            createLocalNotificationStorage();
        }
        if (requestStorage == null) {
            requestStorage = new g(AndroidPlatformModule.getApplicationContext(), iVar);
        }
        if (lockScreenMediaStorage == null) {
            lockScreenMediaStorage = new LockScreenMediaStorageImpl(AndroidPlatformModule.getApplicationContext());
        }
        if (pushBundleStorage == null) {
            pushBundleStorage = new PushBundleStorageImpl(AndroidPlatformModule.getApplicationContext());
        }
        if (inboxNotificationStorage == null) {
            inboxNotificationStorage = new InboxNotificationStorageImpl(AndroidPlatformModule.getApplicationContext());
        }
        if (silentRichMediaStorage == null) {
            silentRichMediaStorage = new w(AndroidPlatformModule.getApplicationContext());
        }
    }

    private static void migratePrefsIfNeeded(c cVar) {
        PrefsProvider c;
        PWLog.noise("Migrate prefs if needed");
        com.pushwoosh.internal.platform.prefs.migration.b prefsMigration = AndroidPlatformModule.getPrefsMigration();
        if (!(prefsMigration == null || (c = com.pushwoosh.internal.platform.prefs.c.c()) == null)) {
            PWLog.noise("Start migration with prevPrefsProvider: " + c.getClass().getName());
            ArrayList arrayList = new ArrayList();
            arrayList.add(RegistrationPrefs.provideMigrationScheme(c));
            arrayList.add(l.a(c));
            for (Plugin plugin : cVar.c()) {
                Collection<? extends MigrationScheme> prefsMigrationSchemes = plugin.getPrefsMigrationSchemes(c);
                if (prefsMigrationSchemes != null) {
                    arrayList.addAll(prefsMigrationSchemes);
                }
            }
            prefsMigration.a(arrayList);
        }
    }

    public static void setLocalNotificationStorage(i iVar) {
        localNotificationStorage = iVar;
    }

    public static void setNotificationPreferences(l lVar) {
        notificationPreferences = lVar;
    }

    public static void setRegistrationPreferences(RegistrationPrefs registrationPrefs) {
        registrationPreferences = registrationPrefs;
    }

    public static void setRequestStorage(g gVar) {
        requestStorage = gVar;
    }
}
