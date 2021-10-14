package de.martinreinhardt.cordova.plugins.urlhandler;

import android.content.Intent;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class LaunchMyApp extends CordovaPlugin {
    private static final String ACTION_CHECKINTENT = "checkIntent";

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (ACTION_CHECKINTENT.equalsIgnoreCase(str)) {
            Intent intent = ((CordovaActivity) this.webView.getContext()).getIntent();
            if (intent.getDataString() != null) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, intent.getDataString()));
                intent.setData(null);
                return true;
            }
            callbackContext.error("App was not started via the launchmyapp URL scheme. Ignoring this errorcallback is the best approach.");
            return false;
        }
        callbackContext.error("This plugin only responds to the checkIntent action.");
        return false;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onNewIntent(Intent intent) {
        String dataString = intent.getDataString();
        if (dataString != null && dataString.contains("://")) {
            intent.setData(null);
            try {
                StringWriter stringWriter = new StringWriter(dataString.length() * 2);
                escapeJavaStyleString(stringWriter, dataString, true, false);
                CordovaWebView cordovaWebView = this.webView;
                cordovaWebView.loadUrl("javascript:handleOpenURL('" + stringWriter.toString() + "');");
            } catch (IOException unused) {
            }
        }
    }

    private static void escapeJavaStyleString(Writer writer, String str, boolean z, boolean z2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt > 4095) {
                    writer.write("\\u" + hex(charAt));
                } else if (charAt > 255) {
                    writer.write("\\u0" + hex(charAt));
                } else if (charAt > 127) {
                    writer.write("\\u00" + hex(charAt));
                } else if (charAt < ' ') {
                    switch (charAt) {
                        case '\b':
                            writer.write(92);
                            writer.write(98);
                            continue;
                        case '\t':
                            writer.write(92);
                            writer.write(116);
                            continue;
                        case '\n':
                            writer.write(92);
                            writer.write(110);
                            continue;
                        case 11:
                        default:
                            if (charAt <= 15) {
                                writer.write("\\u000" + hex(charAt));
                                break;
                            } else {
                                writer.write("\\u00" + hex(charAt));
                                continue;
                            }
                        case '\f':
                            writer.write(92);
                            writer.write(102);
                            continue;
                        case '\r':
                            writer.write(92);
                            writer.write(114);
                            continue;
                    }
                } else if (charAt == '\"') {
                    writer.write(92);
                    writer.write(34);
                } else if (charAt == '\'') {
                    if (z) {
                        writer.write(92);
                    }
                    writer.write(39);
                } else if (charAt == '/') {
                    if (z2) {
                        writer.write(92);
                    }
                    writer.write(47);
                } else if (charAt != '\\') {
                    writer.write(charAt);
                } else {
                    writer.write(92);
                    writer.write(92);
                }
            }
        }
    }

    private static String hex(char c) {
        return Integer.toHexString(c).toUpperCase(Locale.ENGLISH);
    }
}
