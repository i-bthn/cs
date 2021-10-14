package com.quiply.cordova.saveimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;

public class SaveImage extends CordovaPlugin {
    public static final int WRITE_PERM_REQUEST_CODE = 1;
    private final String ACTION = "saveImageToGallery";
    private final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    private CallbackContext callbackContext;
    private String filePath;

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext2) throws JSONException {
        if (!str.equals("saveImageToGallery")) {
            return false;
        }
        saveImageToGallery(jSONArray, callbackContext2);
        return true;
    }

    private void saveImageToGallery(JSONArray jSONArray, CallbackContext callbackContext2) throws JSONException {
        this.filePath = jSONArray.getString(0);
        this.callbackContext = callbackContext2;
        Log.d("SaveImage", "SaveImage in filePath: " + this.filePath);
        String str = this.filePath;
        if (str == null || str.equals("")) {
            callbackContext2.error("Missing filePath");
        } else if (PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Log.d("SaveImage", "Permissions already granted, or Android version is lower than 6");
            performImageSave();
        } else {
            Log.d("SaveImage", "Requesting permissions for WRITE_EXTERNAL_STORAGE");
            PermissionHelper.requestPermission(this, 1, "android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }

    private void performImageSave() throws JSONException {
        File file = new File(this.filePath);
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.d("SaveImage", "SaveImage dstGalleryFolder: " + externalStoragePublicDirectory);
        try {
            File copyFile = copyFile(file, externalStoragePublicDirectory);
            scanPhoto(copyFile);
            this.callbackContext.success(copyFile.toString());
        } catch (RuntimeException e) {
            CallbackContext callbackContext2 = this.callbackContext;
            callbackContext2.error("RuntimeException occurred: " + e.getMessage());
        }
    }

    private File copyFile(File file, File file2) {
        if (file2.exists() || file2.mkdir()) {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            File file3 = new File(file2.getPath() + File.separator + "IMG_" + format + ".jpg");
            try {
                FileChannel channel = new FileInputStream(file).getChannel();
                try {
                    FileChannel channel2 = new FileOutputStream(file3).getChannel();
                    try {
                        channel.transferTo(0, channel.size(), channel2);
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (IOException e) {
                                Log.d("SaveImage", "Error closing input file channel: " + e.getMessage());
                            }
                        }
                        if (channel2 != null) {
                            try {
                                channel2.close();
                            } catch (IOException e2) {
                                Log.d("SaveImage", "Error closing output file channel: " + e2.getMessage());
                            }
                        }
                        return file3;
                    } catch (IOException e3) {
                        throw new RuntimeException("Error transfering file, error: " + e3.getMessage());
                    } catch (Throwable th) {
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (IOException e4) {
                                Log.d("SaveImage", "Error closing input file channel: " + e4.getMessage());
                            }
                        }
                        if (channel2 != null) {
                            try {
                                channel2.close();
                            } catch (IOException e5) {
                                Log.d("SaveImage", "Error closing output file channel: " + e5.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    throw new RuntimeException("Copy file not found: " + file3 + ", error: " + e6.getMessage());
                }
            } catch (FileNotFoundException e7) {
                throw new RuntimeException("Source file not found: " + file + ", error: " + e7.getMessage());
            }
        } else {
            throw new RuntimeException("Destination folder does not exist and cannot be created.");
        }
    }

    private void scanPhoto(File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        this.cordova.getActivity().sendBroadcast(intent);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        for (int i2 : iArr) {
            if (i2 == -1) {
                Log.d("SaveImage", "Permission not granted by the user");
                this.callbackContext.error("Permissions denied");
                return;
            }
        }
        if (i == 1) {
            Log.d("SaveImage", "User granted the permission for WRITE_EXTERNAL_STORAGE");
            performImageSave();
        }
    }
}
