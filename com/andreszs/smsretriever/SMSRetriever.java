package com.andreszs.smsretriever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class SMSRetriever extends CordovaPlugin {
    private static final String ACTION_GET_SIGNATURE = "getHashString";
    private static final String ACTION_START_WATCH = "startWatch";
    private static final String HASH_TYPE = "SHA-256";
    public static final int NUM_BASE64_CHAR = 11;
    public static final int NUM_HASHED_BYTES = 9;
    private static final String TAG = "cordova-plugin-sms-retriever";
    private BroadcastReceiver SmsBrReceiver = new BroadcastReceiver() {
        /* class com.andreszs.smsretriever.SMSRetriever.AnonymousClass3 */

        public void onReceive(Context context, Intent intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                int statusCode = ((Status) extras.get(SmsRetriever.EXTRA_STATUS)).getStatusCode();
                if (statusCode == 0) {
                    String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    Log.d(SMSRetriever.TAG, "Retrieved SMS: " + str);
                    SMSRetriever.this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, str));
                } else if (statusCode == 15) {
                    Log.e(SMSRetriever.TAG, "TIMEOUT");
                    SMSRetriever.this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "TIMEOUT"));
                }
            }
        }
    };
    private CallbackContext callbackContext;
    private SmsRetrieverClient smsRetrieverClient;

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        Log.d(TAG, "initialize");
        super.initialize(cordovaInterface, cordovaWebView);
        this.smsRetrieverClient = SmsRetriever.getClient(cordovaInterface.getActivity().getApplicationContext());
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        if (str.equals(ACTION_START_WATCH)) {
            startWatch(callbackContext2);
        } else if (str.equals(ACTION_GET_SIGNATURE)) {
            getHashString(callbackContext2);
        } else {
            Log.d(TAG, String.format("Invalid action passed: %s", str));
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
        }
        return true;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        if (this.SmsBrReceiver != null) {
            try {
                this.cordova.getActivity().getApplicationContext().unregisterReceiver(this.SmsBrReceiver);
                this.SmsBrReceiver = null;
                Log.d(TAG, "SMS Retriever unregistered successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error unregistering network receiver: " + e.getMessage());
            }
        }
    }

    private void startWatch(final CallbackContext callbackContext2) {
        Log.d(TAG, ACTION_START_WATCH);
        try {
            Task<Void> startSmsRetriever = this.smsRetrieverClient.startSmsRetriever();
            startSmsRetriever.addOnSuccessListener(new OnSuccessListener<Void>() {
                /* class com.andreszs.smsretriever.SMSRetriever.AnonymousClass1 */

                public void onSuccess(Void r3) {
                    Log.d(SMSRetriever.TAG, "smsRetrieverClient started successfully");
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
                    try {
                        SMSRetriever.this.cordova.getActivity().getApplicationContext().registerReceiver(SMSRetriever.this.SmsBrReceiver, intentFilter);
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "SMS Retriever started OK for 5 minutes");
                        pluginResult.setKeepCallback(true);
                        callbackContext2.sendPluginResult(pluginResult);
                    } catch (Exception e) {
                        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
                    }
                }
            });
            startSmsRetriever.addOnFailureListener(new OnFailureListener() {
                /* class com.andreszs.smsretriever.SMSRetriever.AnonymousClass2 */

                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    Log.d(SMSRetriever.TAG, "smsRetrieverClient start failed.", exc);
                    callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, exc.getMessage()));
                }
            });
        } catch (Exception e) {
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
        }
    }

    private void getHashString(CallbackContext callbackContext2) {
        ArrayList<String> appSignatures = getAppSignatures();
        if (appSignatures.size() == 0 || appSignatures.get(0) == null) {
            callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Unable to find package to obtain hash"));
            return;
        }
        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, appSignatures.get(0)));
    }

    private ArrayList<String> getAppSignatures() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String packageName = this.cordova.getActivity().getApplicationContext().getPackageName();
            for (Signature signature : this.cordova.getActivity().getApplicationContext().getPackageManager().getPackageInfo(packageName, 64).signatures) {
                String hash = hash(packageName, signature.toCharsString());
                if (hash != null) {
                    arrayList.add(String.format("%s", hash));
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to find package to obtain hash.", e);
        }
        return arrayList;
    }

    private static String hash(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest instance = MessageDigest.getInstance(HASH_TYPE);
            instance.update(str3.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(Arrays.copyOfRange(instance.digest(), 0, 9), 3).substring(0, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "hash:NoSuchAlgorithm", e);
            return null;
        }
    }
}
