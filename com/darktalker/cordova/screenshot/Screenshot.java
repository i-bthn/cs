package com.darktalker.cordova.screenshot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Screenshot extends CordovaPlugin {
    protected static final String[] PERMISSIONS = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final int PERMISSION_DENIED_ERROR = 20;
    public static final int SAVE_SCREENSHOT_SEC = 0;
    public static final int SAVE_SCREENSHOT_URI_SEC = 1;
    private String mAction;
    private JSONArray mArgs;
    private CallbackContext mCallbackContext;
    private String mFileName;
    private String mFormat;
    private Integer mQuality;

    @Override // org.apache.cordova.CordovaPlugin
    public Object onMessage(String str, Object obj) {
        Bitmap bitmap;
        if (!str.equals("onGotXWalkBitmap") || (bitmap = (Bitmap) obj) == null) {
            return null;
        }
        if (this.mAction.equals("saveScreenshot")) {
            saveScreenshot(bitmap, this.mFormat, this.mFileName, this.mQuality);
            return null;
        } else if (!this.mAction.equals("getScreenshotAsURI")) {
            return null;
        } else {
            getScreenshotAsURI(bitmap, this.mQuality.intValue());
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Bitmap getBitmap() {
        boolean z;
        try {
            Class.forName("org.crosswalk.engine.XWalkWebViewEngine");
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        if (z) {
            this.webView.getPluginManager().postMessage("captureXWalkBitmap", this);
            return null;
        }
        View view = this.webView.getView();
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    private void scanPhoto(String str) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        this.cordova.getActivity().sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void saveScreenshot(Bitmap bitmap, String str, String str2, Integer num) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Pictures");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str2 + "." + str);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            int i = 100;
            if (str.equals("png")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } else if (str.equals("jpg")) {
                Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
                if (num != null) {
                    i = num.intValue();
                }
                bitmap.compress(compressFormat, i, fileOutputStream);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("filePath", file2.getAbsolutePath());
            this.mCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            scanPhoto(file2.getAbsolutePath());
            fileOutputStream.close();
        } catch (JSONException e) {
            this.mCallbackContext.error(e.getMessage());
        } catch (IOException e2) {
            this.mCallbackContext.error(e2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void getScreenshotAsURI(Bitmap bitmap, int i) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream)) {
                String str = "data:image/jpeg;base64," + new String(Base64.encode(byteArrayOutputStream.toByteArray(), 2));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("URI", str);
                this.mCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            }
        } catch (JSONException e) {
            this.mCallbackContext.error(e.getMessage());
        } catch (Exception e2) {
            this.mCallbackContext.error(e2.getMessage());
        }
    }

    public void saveScreenshot() throws JSONException {
        this.mFormat = (String) this.mArgs.get(0);
        this.mQuality = (Integer) this.mArgs.get(1);
        this.mFileName = (String) this.mArgs.get(2);
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.darktalker.cordova.screenshot.Screenshot.AnonymousClass1 */

            public void run() {
                if (Screenshot.this.mFormat.equals("png") || Screenshot.this.mFormat.equals("jpg")) {
                    Bitmap bitmap = Screenshot.this.getBitmap();
                    if (bitmap != null) {
                        Screenshot screenshot = Screenshot.this;
                        screenshot.saveScreenshot(bitmap, screenshot.mFormat, Screenshot.this.mFileName, Screenshot.this.mQuality);
                        return;
                    }
                    return;
                }
                CallbackContext callbackContext = Screenshot.this.mCallbackContext;
                callbackContext.error("format " + Screenshot.this.mFormat + " not found");
            }
        });
    }

    public void getScreenshotAsURI() throws JSONException {
        this.mQuality = (Integer) this.mArgs.get(0);
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            /* class com.darktalker.cordova.screenshot.Screenshot.AnonymousClass2 */

            public void run() {
                Bitmap bitmap = Screenshot.this.getBitmap();
                if (bitmap != null) {
                    Screenshot screenshot = Screenshot.this;
                    screenshot.getScreenshotAsURI(bitmap, screenshot.mQuality.intValue());
                }
            }
        });
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        this.mCallbackContext = callbackContext;
        this.mAction = str;
        this.mArgs = jSONArray;
        if (str.equals("saveScreenshot")) {
            if (PermissionHelper.hasPermission(this, PERMISSIONS[0])) {
                saveScreenshot();
            } else {
                PermissionHelper.requestPermissions(this, 0, PERMISSIONS);
            }
            return true;
        } else if (str.equals("getScreenshotAsURI")) {
            if (PermissionHelper.hasPermission(this, PERMISSIONS[0])) {
                getScreenshotAsURI();
            } else {
                PermissionHelper.requestPermissions(this, 1, PERMISSIONS);
            }
            return true;
        } else {
            callbackContext.error("action not found");
            return false;
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        for (int i2 : iArr) {
            if (i2 == -1) {
                this.mCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
                return;
            }
        }
        switch (i) {
            case 0:
                saveScreenshot();
                return;
            case 1:
                getScreenshotAsURI();
                return;
            default:
                return;
        }
    }
}
