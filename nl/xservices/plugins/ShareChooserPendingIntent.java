package nl.xservices.plugins;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShareChooserPendingIntent extends BroadcastReceiver {
    public static String chosenComponent;

    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            chosenComponent = intent.getExtras().get("android.intent.extra.CHOSEN_COMPONENT").toString();
        }
    }
}
