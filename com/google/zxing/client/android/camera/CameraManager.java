package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.client.android.camera.open.OpenCamera;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import java.io.IOException;

public final class CameraManager {
    private static final int MAX_FRAME_HEIGHT = 675;
    private static final int MAX_FRAME_WIDTH = 1200;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    private static final String TAG = "CameraManager";
    private AutoFocusManager autoFocusManager;
    private OpenCamera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedCameraId = -1;
    private int requestedFramingRectHeight;
    private int requestedFramingRectWidth;
    private boolean torchInitiallyOn;
    private WindowManager windowManager;

    public CameraManager(Context context2) {
        this.context = context2.getApplicationContext();
        this.configManager = new CameraConfigurationManager(context2);
        this.previewCallback = new PreviewCallback(this.configManager);
        this.windowManager = (WindowManager) this.context.getSystemService("window");
    }

    public synchronized void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        String str;
        OpenCamera openCamera = this.camera;
        if (openCamera == null) {
            openCamera = OpenCameraInterface.open(this.requestedCameraId);
            if (openCamera != null) {
                this.camera = openCamera;
            } else {
                throw new IOException("Camera.open() failed to return object from driver");
            }
        }
        if (!this.initialized) {
            this.initialized = true;
            this.configManager.initFromCameraParameters(openCamera);
            if (this.requestedFramingRectWidth > 0 && this.requestedFramingRectHeight > 0) {
                setManualFramingRect(this.requestedFramingRectWidth, this.requestedFramingRectHeight);
                this.requestedFramingRectWidth = 0;
                this.requestedFramingRectHeight = 0;
            }
        }
        Camera camera2 = openCamera.getCamera();
        Camera.Parameters parameters = camera2.getParameters();
        if (parameters == null) {
            str = null;
        } else {
            str = parameters.flatten();
        }
        try {
            this.configManager.setDesiredCameraParameters(openCamera, false);
        } catch (RuntimeException unused) {
            Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            String str2 = TAG;
            Log.i(str2, "Resetting to saved camera params: " + str);
            if (str != null) {
                Camera.Parameters parameters2 = camera2.getParameters();
                parameters2.unflatten(str);
                try {
                    camera2.setParameters(parameters2);
                    this.configManager.setDesiredCameraParameters(openCamera, true);
                } catch (RuntimeException unused2) {
                    Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
        camera2.setPreviewDisplay(surfaceHolder);
        if (this.torchInitiallyOn) {
            setTorch(true);
        }
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void closeDriver() {
        if (this.camera != null) {
            this.camera.getCamera().release();
            this.camera = null;
            this.framingRect = null;
            this.framingRectInPreview = null;
        }
    }

    public synchronized void startPreview() {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && !this.previewing) {
            openCamera.getCamera().startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, openCamera.getCamera());
        }
    }

    public synchronized void stopPreview() {
        if (this.autoFocusManager != null) {
            this.autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        if (this.camera != null && this.previewing) {
            this.camera.getCamera().stopPreview();
            this.previewCallback.setHandler(null, 0);
            this.previewing = false;
        }
    }

    public synchronized boolean isTorchOn() {
        return this.camera != null && this.configManager.getTorchState(this.camera.getCamera());
    }

    public synchronized void setTorch(boolean z) {
        OpenCamera openCamera = this.camera;
        if (!(openCamera == null || z == this.configManager.getTorchState(openCamera.getCamera()))) {
            boolean z2 = this.autoFocusManager != null;
            if (z2) {
                this.autoFocusManager.stop();
                this.autoFocusManager = null;
            }
            this.configManager.setTorch(openCamera.getCamera(), z);
            if (z2) {
                this.autoFocusManager = new AutoFocusManager(this.context, openCamera.getCamera());
                this.autoFocusManager.start();
            }
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int i) {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && this.previewing) {
            this.previewCallback.setHandler(handler, i);
            openCamera.getCamera().setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized Rect getFramingRect() {
        if (this.framingRect == null) {
            if (this.camera == null) {
                return null;
            }
            Point screenResolution = this.configManager.getScreenResolution();
            if (screenResolution == null) {
                return null;
            }
            int findDesiredDimensionInRange = findDesiredDimensionInRange(screenResolution.x, 240, MAX_FRAME_WIDTH);
            int findDesiredDimensionInRange2 = findDesiredDimensionInRange(screenResolution.y, 240, MAX_FRAME_HEIGHT);
            int i = (screenResolution.x - findDesiredDimensionInRange) / 2;
            int i2 = (screenResolution.y - findDesiredDimensionInRange2) / 2;
            this.framingRect = new Rect(i, i2, findDesiredDimensionInRange + i, findDesiredDimensionInRange2 + i2);
            String str = TAG;
            Log.d(str, "Calculated framing rect: " + this.framingRect);
        }
        return this.framingRect;
    }

    private static int findDesiredDimensionInRange(int i, int i2, int i3) {
        int i4 = (i * 5) / 8;
        if (i4 < i2) {
            return i2;
        }
        return i4 > i3 ? i3 : i4;
    }

    public synchronized Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect framingRect2 = getFramingRect();
            if (framingRect2 == null) {
                return null;
            }
            Rect rect = new Rect(framingRect2);
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            if (cameraResolution == null || screenResolution == null) {
                return null;
            }
            if (this.context.getApplicationContext().getResources().getConfiguration().orientation == 1) {
                rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
                rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
                rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
                rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
            } else {
                rect.left = (rect.left * cameraResolution.x) / screenResolution.x;
                rect.right = (rect.right * cameraResolution.x) / screenResolution.x;
                rect.top = (rect.top * cameraResolution.y) / screenResolution.y;
                rect.bottom = (rect.bottom * cameraResolution.y) / screenResolution.y;
            }
            this.framingRectInPreview = rect;
        }
        return this.framingRectInPreview;
    }

    public synchronized void setManualCameraId(int i) {
        this.requestedCameraId = i;
    }

    public synchronized void setTorchInitiallyOn(boolean z) {
        this.torchInitiallyOn = z;
    }

    public synchronized void setManualFramingRect(int i, int i2) {
        if (this.initialized) {
            this.framingRect = getFramingRect();
            String str = TAG;
            Log.d(str, "Calculated manual framing rect: " + this.framingRect);
            this.framingRectInPreview = null;
        } else {
            this.requestedFramingRectWidth = i;
            this.requestedFramingRectHeight = i2;
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        byte[] bArr2 = new byte[bArr.length];
        int i5 = this.context.getApplicationContext().getResources().getConfiguration().orientation;
        if (i5 == 1) {
            for (int i6 = 0; i6 < i2; i6++) {
                for (int i7 = 0; i7 < i; i7++) {
                    bArr2[(((i7 * i2) + i2) - i6) - 1] = bArr[(i6 * i) + i7];
                }
            }
            i3 = i;
            i4 = i2;
        } else {
            i4 = i;
            i3 = i2;
            bArr2 = null;
        }
        Rect framingRectInPreview2 = getFramingRectInPreview();
        if (framingRectInPreview2 == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(i5 == 1 ? bArr2 : bArr, i4, i3, framingRectInPreview2.left, framingRectInPreview2.top, framingRectInPreview2.width(), framingRectInPreview2.height(), false);
    }
}
