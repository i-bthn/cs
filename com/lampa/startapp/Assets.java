package com.lampa.startapp;

import android.content.Intent;
import android.util.Log;
import java.lang.reflect.Field;
import org.apache.cordova.CordovaPlugin;

public class Assets extends CordovaPlugin {
    protected static final String TAG = "startApp";
    protected boolean NO_PARSE_INTENT_VALS = false;

    /* access modifiers changed from: protected */
    public String parseExtraName(String str) {
        try {
            str = getIntentValueString(str);
        } catch (NoSuchFieldException unused) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return str;
        }
        Log.e("startApp", str);
        return str;
    }

    /* access modifiers changed from: protected */
    public String getIntentValueString(String str) throws NoSuchFieldException, IllegalAccessException {
        if (this.NO_PARSE_INTENT_VALS) {
            return str;
        }
        Field declaredField = Intent.class.getDeclaredField(str);
        declaredField.setAccessible(true);
        return (String) declaredField.get(null);
    }

    /* access modifiers changed from: protected */
    public int getIntentValue(String str) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = Intent.class.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.getInt(null);
    }
}
