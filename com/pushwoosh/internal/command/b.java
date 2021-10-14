package com.pushwoosh.internal.command;

import android.util.Pair;
import com.pushwoosh.PushwooshPlatform;
import com.pushwoosh.repository.o;

public class b implements a<Pair<String, String>> {
    @Override // com.pushwoosh.internal.command.a
    public void a(CommandParams<Pair<String, String>> commandParams) {
        Pair<String, String> params = commandParams.getParams();
        String str = (String) params.first;
        o o = PushwooshPlatform.getInstance().o();
        o.b(str);
        o.a(str, (String) params.second);
    }
}
