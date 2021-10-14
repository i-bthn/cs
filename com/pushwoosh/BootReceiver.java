package com.pushwoosh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.pushwoosh.internal.event.Event;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.utils.PWLog;

public class BootReceiver extends BroadcastReceiver {

    public static class DeviceBootedEvent implements Event {
        private DeviceBootedEvent() {
        }
    }

    public void onReceive(Context context, Intent intent) {
        PWLog.debug("BootReceiver", "onReceive");
        if (intent != null) {
            try {
                if (TextUtils.equals("android.intent.action.BOOT_COMPLETED", intent.getAction())) {
                    EventBus.sendEvent(new DeviceBootedEvent());
                    return;
                }
            } catch (Exception e) {
                PWLog.exception(e);
                return;
            }
        }
        PWLog.warn("BootReceiver", "Received unexpected action");
    }
}
