package org.apache.cordova.networkinformation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.util.Locale;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkManager extends CordovaPlugin {
    public static final String CDMA = "cdma";
    public static final String CELLULAR = "cellular";
    public static final String EDGE = "edge";
    public static final String EHRPD = "ehrpd";
    public static final String FOUR_G = "4g";
    public static final String GPRS = "gprs";
    public static final String GSM = "gsm";
    public static final String HSDPA = "hsdpa";
    public static final String HSPA = "hspa";
    public static final String HSPA_PLUS = "hspa+";
    public static final String HSUPA = "hsupa";
    private static final String LOG_TAG = "NetworkManager";
    public static final String LTE = "lte";
    public static final String MOBILE = "mobile";
    public static int NOT_REACHABLE = 0;
    public static final String ONEXRTT = "1xrtt";
    public static int REACHABLE_VIA_CARRIER_DATA_NETWORK = 1;
    public static int REACHABLE_VIA_WIFI_NETWORK = 2;
    public static final String THREE_G = "3g";
    public static final String TWO_G = "2g";
    public static final String TYPE_2G = "2g";
    public static final String TYPE_3G = "3g";
    public static final String TYPE_4G = "4g";
    public static final String TYPE_ETHERNET = "ethernet";
    public static final String TYPE_ETHERNET_SHORT = "eth";
    public static final String TYPE_NONE = "none";
    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_WIFI = "wifi";
    public static final String UMB = "umb";
    public static final String UMTS = "umts";
    public static final String WIFI = "wifi";
    public static final String WIMAX = "wimax";
    private CallbackContext connectionCallbackContext;
    private JSONObject lastInfo = null;
    BroadcastReceiver receiver;
    ConnectivityManager sockMan;

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        this.sockMan = (ConnectivityManager) cordovaInterface.getActivity().getSystemService("connectivity");
        this.connectionCallbackContext = null;
        registerConnectivityActionReceiver();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        if (!str.equals("getConnectionInfo")) {
            return false;
        }
        this.connectionCallbackContext = callbackContext;
        String str2 = "";
        try {
            str2 = getConnectionInfo(this.sockMan.getActiveNetworkInfo()).get("type").toString();
        } catch (JSONException e) {
            LOG.d(LOG_TAG, e.getLocalizedMessage());
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, str2);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
        return true;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        unregisterReceiver();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onPause(boolean z) {
        unregisterReceiver();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onResume(boolean z) {
        super.onResume(z);
        unregisterReceiver();
        registerConnectivityActionReceiver();
    }

    private void registerConnectivityActionReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() {
                /* class org.apache.cordova.networkinformation.NetworkManager.AnonymousClass1 */

                public void onReceive(Context context, Intent intent) {
                    String str;
                    if (NetworkManager.this.webView != null) {
                        NetworkManager networkManager = NetworkManager.this;
                        networkManager.updateConnectionInfo(networkManager.sockMan.getActiveNetworkInfo());
                    }
                    if (NetworkManager.this.lastInfo == null) {
                        str = NetworkManager.TYPE_NONE;
                    } else {
                        try {
                            str = NetworkManager.this.lastInfo.get("type").toString();
                        } catch (JSONException e) {
                            LOG.d(NetworkManager.LOG_TAG, e.getLocalizedMessage());
                            str = NetworkManager.TYPE_NONE;
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 23 && NetworkManager.TYPE_NONE.equals(str)) {
                        boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                        LOG.d(NetworkManager.LOG_TAG, "Intent no connectivity: " + booleanExtra);
                        if (booleanExtra) {
                            LOG.d(NetworkManager.LOG_TAG, "Really no connectivity");
                            return;
                        }
                        LOG.d(NetworkManager.LOG_TAG, "!!! Switching to unknown, Intent states there is a connectivity.");
                        NetworkManager.this.sendUpdate("unknown");
                    }
                }
            };
        }
        this.webView.getContext().registerReceiver(this.receiver, intentFilter);
    }

    private void unregisterReceiver() {
        if (this.receiver != null) {
            try {
                this.webView.getContext().unregisterReceiver(this.receiver);
            } catch (Exception e) {
                LOG.e(LOG_TAG, "Error unregistering network receiver: " + e.getMessage(), e);
            } catch (Throwable th) {
                this.receiver = null;
                throw th;
            }
            this.receiver = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateConnectionInfo(NetworkInfo networkInfo) {
        JSONObject connectionInfo = getConnectionInfo(networkInfo);
        if (!connectionInfo.equals(this.lastInfo)) {
            String str = "";
            try {
                str = connectionInfo.get("type").toString();
            } catch (JSONException e) {
                LOG.d(LOG_TAG, e.getLocalizedMessage());
            }
            sendUpdate(str);
            this.lastInfo = connectionInfo;
        }
    }

    private JSONObject getConnectionInfo(NetworkInfo networkInfo) {
        String str = TYPE_NONE;
        String str2 = "";
        if (networkInfo != null) {
            if (!networkInfo.isConnected()) {
                str = TYPE_NONE;
            } else {
                str = getType(networkInfo);
            }
            str2 = networkInfo.getExtraInfo();
        }
        LOG.d(LOG_TAG, "Connection Type: " + str);
        LOG.d(LOG_TAG, "Connection Extra Info: " + str2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("extraInfo", str2);
        } catch (JSONException e) {
            LOG.d(LOG_TAG, e.getLocalizedMessage());
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendUpdate(String str) {
        if (this.connectionCallbackContext != null) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, str);
            pluginResult.setKeepCallback(true);
            this.connectionCallbackContext.sendPluginResult(pluginResult);
        }
        this.webView.postMessage("networkconnection", str);
    }

    private String getType(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return TYPE_NONE;
        }
        String lowerCase = networkInfo.getTypeName().toLowerCase(Locale.US);
        LOG.d(LOG_TAG, "toLower : " + lowerCase.toLowerCase());
        LOG.d(LOG_TAG, "wifi : wifi");
        if (lowerCase.equals("wifi")) {
            return "wifi";
        }
        if (lowerCase.toLowerCase().equals(TYPE_ETHERNET) || lowerCase.toLowerCase().startsWith(TYPE_ETHERNET_SHORT)) {
            return TYPE_ETHERNET;
        }
        if (!lowerCase.equals(MOBILE) && !lowerCase.equals(CELLULAR)) {
            return "unknown";
        }
        String lowerCase2 = networkInfo.getSubtypeName().toLowerCase(Locale.US);
        if (lowerCase2.equals(GSM) || lowerCase2.equals(GPRS) || lowerCase2.equals(EDGE) || lowerCase2.equals("2g")) {
            return "2g";
        }
        if (lowerCase2.startsWith(CDMA) || lowerCase2.equals(UMTS) || lowerCase2.equals(ONEXRTT) || lowerCase2.equals(EHRPD) || lowerCase2.equals(HSUPA) || lowerCase2.equals(HSDPA) || lowerCase2.equals(HSPA) || lowerCase2.equals("3g")) {
            return "3g";
        }
        return (lowerCase2.equals(LTE) || lowerCase2.equals(UMB) || lowerCase2.equals(HSPA_PLUS) || lowerCase2.equals("4g")) ? "4g" : "unknown";
    }
}
