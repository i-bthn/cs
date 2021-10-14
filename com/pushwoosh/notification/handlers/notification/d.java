package com.pushwoosh.notification.handlers.notification;

import android.os.Bundle;
import android.util.Pair;
import com.pushwoosh.internal.command.CommandApplayer;
import com.pushwoosh.internal.command.CommandParams;
import com.pushwoosh.notification.b;

public class d implements PushNotificationOpenHandler {
    private CommandApplayer a;

    public d(CommandApplayer commandApplayer) {
        this.a = commandApplayer;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String a() {
        return "pushStat";
    }

    @Override // com.pushwoosh.notification.handlers.notification.PushNotificationOpenHandler
    public void postHandleNotification(Bundle bundle) {
        if (!b.C(bundle) && !b.E(bundle)) {
            Pair pair = new Pair(b.t(bundle), b.u(bundle));
            CommandApplayer commandApplayer = this.a;
            if (commandApplayer != null) {
                commandApplayer.applyCommand($$Lambda$d$BxdaYITblJVPAMRYrDdynZIJYF4.INSTANCE, new CommandParams(pair));
            }
        }
    }
}
