package com.alinma.tls;

import android.util.Log;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class TlsCheckerPlugin extends CordovaPlugin {
    public final String ACTION_HAS_SMS_POSSIBILITY = "HasSMSPossibility";
    public final String ACTION_IS_TLS_1_2_SUPPORTED = "IsTLS12Supported";
    public final String ACTION_RECEIVE_SMS = "StartReception";
    public final String ACTION_SEND_SMS = "SendSMS";
    public final String ACTION_STOP_RECEIVE_SMS = "StopReception";

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (!str.equals("IsTLS12Supported")) {
            return false;
        }
        boolean isTls1_2Supported = isTls1_2Supported();
        Log.d("BBB", String.valueOf(isTls1_2Supported()));
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, isTls1_2Supported));
        return true;
    }

    public boolean isTls1_2Supported() {
        SSLContext sSLContext;
        SSLSocket sSLSocket = null;
        try {
            sSLContext = SSLContext.getInstance("Default");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            sSLContext = null;
        }
        try {
            sSLSocket = (SSLSocket) sSLContext.getSocketFactory().createSocket();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        String[] enabledProtocols = sSLSocket.getEnabledProtocols();
        boolean z = false;
        for (int i = 0; i < enabledProtocols.length; i++) {
            Log.d("Plugin", "$$$$$$$$$$$$$$$$$$$$$$$ ::: " + enabledProtocols[i]);
            if (enabledProtocols[i].equals("TLSv1.2")) {
                z = true;
            }
        }
        Log.d("AAA", String.valueOf(z));
        return z;
    }
}
