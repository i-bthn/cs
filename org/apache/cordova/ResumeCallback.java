package org.apache.cordova;

import java.util.ArrayList;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

public class ResumeCallback extends CallbackContext {
    private final String TAG = "CordovaResumeCallback";
    private PluginManager pluginManager;
    private String serviceName;

    public ResumeCallback(String str, PluginManager pluginManager2) {
        super("resumecallback", null);
        this.serviceName = str;
        this.pluginManager = pluginManager2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.put("pluginServiceName", r5.serviceName);
        r1.put("pluginStatus", org.apache.cordova.PluginResult.StatusMessages[r6.getStatus()]);
        r0.put("action", "resume");
        r0.put("pendingResult", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0055, code lost:
        org.apache.cordova.LOG.e("CordovaResumeCallback", "Unable to create resume object for Activity Result");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        r0 = new org.json.JSONObject();
        r1 = new org.json.JSONObject();
     */
    @Override // org.apache.cordova.CallbackContext
    public void sendPluginResult(PluginResult pluginResult) {
        JSONObject jSONObject;
        synchronized (this) {
            if (this.finished) {
                LOG.w("CordovaResumeCallback", this.serviceName + " attempted to send a second callback to ResumeCallback\nResult was: " + pluginResult.getMessage());
                return;
            }
            this.finished = true;
        }
        PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, jSONObject);
        ArrayList arrayList = new ArrayList();
        arrayList.add(pluginResult2);
        arrayList.add(pluginResult);
        ((CoreAndroid) this.pluginManager.getPlugin(CoreAndroid.PLUGIN_NAME)).sendResumeEvent(new PluginResult(PluginResult.Status.OK, arrayList));
    }
}
