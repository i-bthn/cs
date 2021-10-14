package com.pushwoosh;

import com.pushwoosh.inapp.e;
import com.pushwoosh.inapp.f.d;
import com.pushwoosh.internal.c.f;
import com.pushwoosh.internal.c.g;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.network.NetworkModule;
import com.pushwoosh.internal.network.RequestManager;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.platform.prefs.PrefsProvider;
import com.pushwoosh.internal.registrar.PushRegistrar;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.h;
import com.pushwoosh.internal.utils.i;
import com.pushwoosh.notification.NotificationServiceExtension;
import com.pushwoosh.notification.PushwooshNotificationManager;
import com.pushwoosh.notification.c;
import com.pushwoosh.repository.RegistrationPrefs;
import com.pushwoosh.repository.RepositoryModule;
import com.pushwoosh.repository.l;
import com.pushwoosh.repository.o;
import com.pushwoosh.repository.r;
import com.pushwoosh.richmedia.RichMediaStyle;
import com.pushwoosh.richmedia.animation.RichMediaAnimationSlideBottom;
import java.security.SecureRandom;

public class PushwooshPlatform {
    private static boolean v = false;
    private static PushwooshPlatform w;
    private i a;
    private c b;
    private g c;
    private final com.pushwoosh.internal.utils.c d;
    private final PushwooshNotificationManager e;
    private final o f;
    private final RegistrationPrefs g;
    private final com.pushwoosh.inapp.c h;
    private final d i;
    private NotificationServiceExtension j;
    private GDPRManager k;
    private com.pushwoosh.richmedia.a l;
    private com.pushwoosh.internal.utils.b m;
    private com.pushwoosh.repository.g n;
    private d o;
    private com.pushwoosh.repository.d p;
    private RichMediaStyle q;
    private CommandApplayer r;
    private com.pushwoosh.notification.handlers.notification.d s;
    private com.pushwoosh.appevents.b t;
    private com.pushwoosh.internal.b u;

    public static class b {
        private com.pushwoosh.internal.utils.c a;
        private PushRegistrar b;

        public b a(PushRegistrar pushRegistrar) {
            this.b = pushRegistrar;
            return this;
        }

        public b a(com.pushwoosh.internal.utils.c cVar) {
            this.a = cVar;
            return this;
        }

        public PushwooshPlatform a() {
            PushwooshPlatform unused = PushwooshPlatform.w = new PushwooshPlatform(this);
            return PushwooshPlatform.w;
        }
    }

    private PushwooshPlatform(b bVar) {
        this.a = new i();
        this.d = bVar.a;
        this.r = new CommandApplayer();
        this.s = new com.pushwoosh.notification.handlers.notification.d(this.r);
        this.p = new com.pushwoosh.repository.d();
        RepositoryModule.init(this.d, this.a, this.p);
        this.g = RepositoryModule.getRegistrationPreferences();
        com.pushwoosh.repository.config.b bVar2 = new com.pushwoosh.repository.config.b();
        NetworkModule.init(this.g, bVar2);
        this.e = new PushwooshNotificationManager(bVar.b, this.d);
        this.h = new com.pushwoosh.inapp.c(new e());
        this.b = new c();
        PrefsProvider prefsProvider = AndroidPlatformModule.getPrefsProvider();
        com.pushwoosh.internal.platform.c.a appInfoProvider = AndroidPlatformModule.getAppInfoProvider();
        h timeProvide = AndroidPlatformModule.getTimeProvide();
        this.m = new com.pushwoosh.internal.utils.b(AndroidPlatformModule.getPrefsProvider().providePrefs("PWAppVersion"));
        this.i = new d(prefsProvider, appInfoProvider, timeProvide, this.m);
        RequestManager requestManager = NetworkModule.getRequestManager();
        r rVar = new r();
        l notificationPreferences = RepositoryModule.getNotificationPreferences();
        this.f = new o(requestManager, rVar, this.g, notificationPreferences, RepositoryModule.getRequestStorage(), bVar2);
        this.k = new GDPRManager(this.f, this.e, this.h);
        this.q = new RichMediaStyle(0, new RichMediaAnimationSlideBottom());
        this.l = new com.pushwoosh.richmedia.a(new com.pushwoosh.inapp.view.i.e(), new com.pushwoosh.richmedia.b(), com.pushwoosh.inapp.b.b(), this.q);
        this.n = new com.pushwoosh.repository.g(requestManager, rVar, notificationPreferences.q(), this.m, this.e, this.g, this.p);
        this.t = new com.pushwoosh.appevents.b();
        this.n.b();
        this.u = new com.pushwoosh.internal.b(this.d.m(), this.e);
        this.o = new d(this.d, this.g, this.n, this.m, this.f, this.e, this.h, this.p, this.t, this.u);
        this.c = new g(new f(), new com.pushwoosh.internal.c.a(), new com.pushwoosh.internal.c.d(new SecureRandom()));
    }

    public static PushwooshPlatform getInstance() {
        return w;
    }

    public static void r() {
        if (!v) {
            PWLog.warn("PushwooshPlatform", "Pushwoosh library not initialized. All method calls will be ignored");
            v = true;
        }
    }

    /* access modifiers changed from: package-private */
    public com.pushwoosh.internal.utils.b a() {
        return this.m;
    }

    public d b() {
        return this.i;
    }

    public com.pushwoosh.internal.utils.c c() {
        return this.d;
    }

    public GDPRManager d() {
        return this.k;
    }

    public c e() {
        return this.b;
    }

    public com.pushwoosh.internal.b f() {
        return this.u;
    }

    public RegistrationPrefs g() {
        return this.g;
    }

    public com.pushwoosh.richmedia.a h() {
        return this.l;
    }

    public RichMediaStyle i() {
        return this.q;
    }

    public g j() {
        return this.c;
    }

    public NotificationServiceExtension k() {
        if (this.j == null) {
            try {
                Class<?> n2 = this.d.n();
                this.j = n2 != null ? (NotificationServiceExtension) n2.newInstance() : new NotificationServiceExtension();
            } catch (Exception e2) {
                PWLog.exception(e2);
                this.j = new NotificationServiceExtension();
            }
        }
        return this.j;
    }

    public void l() {
        this.o.a();
    }

    public com.pushwoosh.notification.handlers.notification.d m() {
        return this.s;
    }

    public com.pushwoosh.inapp.c n() {
        return this.h;
    }

    public PushwooshNotificationManager notificationManager() {
        return this.e;
    }

    public o o() {
        return this.f;
    }

    public void p() {
        this.o.b();
    }
}
