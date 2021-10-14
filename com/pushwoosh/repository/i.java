package com.pushwoosh.repository;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.repository.c;
import java.util.Collections;
import java.util.Set;

public class i {
    @Nullable
    private final c a;

    i(@NonNull c cVar) {
        this.a = cVar;
    }

    public b a(int i) {
        if (this.a == null) {
            PWLog.error("dbLocalNotificationHelper is null, cant get Notification");
            return null;
        }
        b a2 = this.a.a(Integer.toString(i));
        if (a2 == null) {
            PWLog.noise("local notification not found");
            return null;
        }
        this.a.b(i);
        return a2;
    }

    public Set<Integer> a() {
        c cVar = this.a;
        return cVar == null ? Collections.emptySet() : cVar.a();
    }

    public void a(int i, int i2, String str) {
        b bVar = new b(i, i2, str);
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(bVar);
        }
    }

    public void a(int i, Bundle bundle, long j) {
        PWLog.noise("LocalNotificationStorage", "Saved local push: " + bundle.toString());
        try {
            if (this.a != null) {
                this.a.b(new b(i, 0, "", j, bundle));
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }

    public void a(int i, String str) {
        c cVar = this.a;
        if (cVar == null) {
            PWLog.error("dbLocalNotificationHelper is null, cant removeLocalNotificationShown");
            return;
        }
        b a2 = cVar.a(i, str);
        if (a2 != null) {
            this.a.b(a2.d());
        }
    }

    public void a(c.a aVar) {
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(aVar);
        } else {
            PWLog.error("LocalNotificationStorage", "dbLocalNotificationHelper is null, cant enumerate local notification list");
        }
    }

    public int b() {
        c cVar = this.a;
        if (cVar == null) {
            return 0;
        }
        return cVar.b();
    }

    public void b(int i) {
        PWLog.noise("LocalNotificationStorage", "Removed dbLocalNotification: " + i);
        try {
            if (this.a == null) {
                PWLog.error("dbLocalNotificationHelper is null, cant remove local push");
            } else {
                this.a.a(i);
            }
        } catch (Exception e) {
            PWLog.exception(e);
        }
    }
}
