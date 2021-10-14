package com.pushwoosh.internal.command;

import com.pushwoosh.internal.utils.PWLog;

public class CommandApplayer {
    public static final String TAG = "Command";

    public <T> void applyCommand(CommandType commandType, CommandParams<T> commandParams) {
        try {
            String type = commandType.getType();
            char c = 65535;
            if (type.hashCode() == 1775963278) {
                if (type.equals("pushStat")) {
                    c = 0;
                }
            }
            b bVar = c != 0 ? null : new b();
            if (bVar != null) {
                bVar.a(commandParams);
            }
        } catch (ClassCastException unused) {
            PWLog.error(TAG, "Can't cast command");
        }
    }
}
