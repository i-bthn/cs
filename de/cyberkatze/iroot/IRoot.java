package de.cyberkatze.iroot;

import android.os.Build;
import com.scottyab.rootbeer.RootBeer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class IRoot extends CordovaPlugin {
    private final String LOG_TAG = "IRoot";
    private final boolean WITH = true;

    private enum Action {
        ACTION_IS_ROOTED("isRooted"),
        ACTION_IS_ROOTED_REDBEER("isRootedRedBeer"),
        ACTION_IS_ROOTED_REDBEER_WITHOUT_BUSYBOX("isRootedRedBeerWithoutBusyBox");
        
        private static final Map<String, Action> lookup = new HashMap();
        private final String name;

        static {
            Action[] values = values();
            for (Action action : values) {
                lookup.put(action.getName(), action);
            }
        }

        private Action(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public static Action get(String str) {
            return lookup.get(str);
        }
    }

    private PluginResult error(String str, Throwable th) {
        LOG.e("IRoot", str, th);
        return new PluginResult(PluginResult.Status.ERROR, str);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, final JSONArray jSONArray, final CallbackContext callbackContext) throws JSONException {
        Action action = Action.get(str);
        if (action == null) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                /* class de.cyberkatze.iroot.IRoot.AnonymousClass1 */

                public void run() {
                    LOG.e("IRoot", "unknown action");
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "unknown action"));
                }
            });
            return false;
        }
        switch (action) {
            case ACTION_IS_ROOTED:
                this.cordova.getThreadPool().execute(new Runnable() {
                    /* class de.cyberkatze.iroot.IRoot.AnonymousClass2 */

                    public void run() {
                        PluginResult pluginResult;
                        try {
                            pluginResult = IRoot.this.checkIsRooted(jSONArray, callbackContext);
                        } catch (Exception e) {
                            pluginResult = new PluginResult(PluginResult.Status.ERROR, e.toString());
                        }
                        callbackContext.sendPluginResult(pluginResult);
                    }
                });
                return true;
            case ACTION_IS_ROOTED_REDBEER:
                this.cordova.getThreadPool().execute(new Runnable() {
                    /* class de.cyberkatze.iroot.IRoot.AnonymousClass3 */

                    public void run() {
                        PluginResult pluginResult;
                        try {
                            pluginResult = IRoot.this.checkIsRootedRedBeer(jSONArray, callbackContext);
                        } catch (Exception e) {
                            pluginResult = new PluginResult(PluginResult.Status.ERROR, e.toString());
                        }
                        callbackContext.sendPluginResult(pluginResult);
                    }
                });
                return true;
            case ACTION_IS_ROOTED_REDBEER_WITHOUT_BUSYBOX:
                this.cordova.getThreadPool().execute(new Runnable() {
                    /* class de.cyberkatze.iroot.IRoot.AnonymousClass4 */

                    public void run() {
                        PluginResult pluginResult;
                        try {
                            pluginResult = IRoot.this.checkIsRootedRedBeerWithoutBusyBox(jSONArray, callbackContext);
                        } catch (Exception e) {
                            pluginResult = new PluginResult(PluginResult.Status.ERROR, e.toString());
                        }
                        callbackContext.sendPluginResult(pluginResult);
                    }
                });
                return true;
            default:
                this.cordova.getActivity().runOnUiThread(new Runnable() {
                    /* class de.cyberkatze.iroot.IRoot.AnonymousClass5 */

                    public void run() {
                        LOG.e("IRoot", "unknown action");
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "unknown action"));
                    }
                });
                return false;
        }
    }

    private boolean isDeviceRooted() {
        return checkBuildTags() || checkSuperUserApk() || checkFilePath();
    }

    private boolean checkBuildTags() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private boolean checkSuperUserApk() {
        return new File("/system/app/Superuser.apk").exists();
    }

    private boolean checkFilePath() {
        for (String str : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PluginResult checkIsRootedRedBeer(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            return new PluginResult(PluginResult.Status.OK, new RootBeer(this.cordova.getActivity().getApplicationContext()).isRooted());
        } catch (Exception e) {
            return error("Error", e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PluginResult checkIsRootedRedBeerWithoutBusyBox(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            return new PluginResult(PluginResult.Status.OK, new RootBeer(this.cordova.getActivity().getApplicationContext()).isRootedWithoutBusyBoxCheck());
        } catch (Exception e) {
            return error("Error", e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PluginResult checkIsRooted(JSONArray jSONArray, CallbackContext callbackContext) {
        boolean z;
        try {
            RootBeer rootBeer = new RootBeer(this.cordova.getActivity().getApplicationContext());
            if (!isDeviceRooted()) {
                getClass();
                if (!rootBeer.isRootedWithoutBusyBoxCheck()) {
                    z = false;
                    return new PluginResult(PluginResult.Status.OK, z);
                }
            }
            z = true;
            return new PluginResult(PluginResult.Status.OK, z);
        } catch (Exception e) {
            return error("Error", e);
        }
    }
}
