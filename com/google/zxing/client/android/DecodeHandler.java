package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

final class DecodeHandler extends Handler {
    private static final String TAG = "DecodeHandler";
    private final CaptureActivity activity;
    private int frameCount;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();
    private boolean running = true;

    DecodeHandler(CaptureActivity captureActivity, Map<DecodeHintType, Object> map) {
        this.multiFormatReader.setHints(map);
        this.activity = captureActivity;
    }

    public void handleMessage(Message message) {
        if (this.running) {
            if (message.what == R.id.decode) {
                decode((byte[]) message.obj, message.arg1, message.arg2);
            } else if (message.what == R.id.quit) {
                this.running = false;
                Looper.myLooper().quit();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0098  */
    private void decode(byte[] bArr, int i, int i2) {
        Result result;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.frameCount == 3) {
            this.frameCount = 0;
            int[] iArr = new int[(i * i2)];
            YUV_NV21_TO_RGB(iArr, bArr, i, i2);
            for (int i3 = 0; i3 < iArr.length; i3++) {
                iArr[i3] = ViewCompat.MEASURED_SIZE_MASK - iArr[i3];
            }
            encodeYUV420SP(bArr, iArr, i, i2);
        }
        this.frameCount++;
        PlanarYUVLuminanceSource buildLuminanceSource = this.activity.getCameraManager().buildLuminanceSource(bArr, i, i2);
        if (buildLuminanceSource != null) {
            try {
                result = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(buildLuminanceSource)));
            } catch (ReaderException unused) {
            } finally {
                this.multiFormatReader.reset();
            }
            Handler handler = this.activity.getHandler();
            if (result == null) {
                long currentTimeMillis2 = System.currentTimeMillis();
                Log.d(TAG, "Found barcode in " + (currentTimeMillis2 - currentTimeMillis) + " ms");
                if (handler != null) {
                    Message obtain = Message.obtain(handler, R.id.decode_succeeded, result);
                    Bundle bundle = new Bundle();
                    bundleThumbnail(buildLuminanceSource, bundle);
                    obtain.setData(bundle);
                    obtain.sendToTarget();
                    return;
                }
                return;
            } else if (handler != null) {
                Message.obtain(handler, R.id.decode_failed).sendToTarget();
                return;
            } else {
                return;
            }
        }
        result = null;
        Handler handler2 = this.activity.getHandler();
        if (result == null) {
        }
    }

    private static void bundleThumbnail(PlanarYUVLuminanceSource planarYUVLuminanceSource, Bundle bundle) {
        int[] renderThumbnail = planarYUVLuminanceSource.renderThumbnail();
        int thumbnailWidth = planarYUVLuminanceSource.getThumbnailWidth();
        Bitmap createBitmap = Bitmap.createBitmap(renderThumbnail, 0, thumbnailWidth, thumbnailWidth, planarYUVLuminanceSource.getThumbnailHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray(DecodeThread.BARCODE_BITMAP, byteArrayOutputStream.toByteArray());
        bundle.putFloat(DecodeThread.BARCODE_SCALED_FACTOR, ((float) thumbnailWidth) / ((float) planarYUVLuminanceSource.getWidth()));
    }

    private static void YUV_NV21_TO_RGB(int[] iArr, byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i2) {
            int i7 = i5;
            int i8 = 0;
            int i9 = 0;
            while (i8 < i) {
                int i10 = bArr[(i6 * i) + i9] & 255;
                int i11 = ((i6 >> 1) * i) + i3 + (i9 & -2);
                int i12 = bArr[i11 + 0] & 255;
                int i13 = bArr[i11 + 1] & 255;
                if (i10 < 16) {
                    i10 = 16;
                }
                int i14 = (i10 - 16) * 1192;
                int i15 = i12 - 128;
                int i16 = i13 - 128;
                int i17 = ((i15 * 1634) + i14) >> 10;
                int i18 = ((i14 - (i15 * 832)) - (i16 * 400)) >> 10;
                int i19 = (i14 + (i16 * 2066)) >> 10;
                if (i17 < 0) {
                    i17 = 0;
                } else if (i17 > 255) {
                    i17 = 255;
                }
                if (i18 < 0) {
                    i18 = 0;
                } else if (i18 > 255) {
                    i18 = 255;
                }
                if (i19 < 0) {
                    i19 = 0;
                } else if (i19 > 255) {
                    i19 = 255;
                }
                iArr[i7] = i19 | (i17 << 16) | 0 | (i18 << 8);
                i8++;
                i9++;
                i7++;
            }
            i4++;
            i6++;
            i5 = i7;
        }
    }

    /* access modifiers changed from: package-private */
    public void encodeYUV420SP(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = i * i2;
        PrintStream printStream = System.out;
        printStream.println(bArr.length + " " + i3);
        int i4 = i3;
        int length = ((bArr.length - i3) / 2) + i3;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i2) {
            int i8 = length;
            int i9 = i4;
            int i10 = i7;
            int i11 = i6;
            int i12 = 0;
            while (i12 < i) {
                int i13 = iArr[i11];
                int i14 = (iArr[i11] & 16711680) >> 16;
                int i15 = (iArr[i11] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                int i16 = 255;
                int i17 = (iArr[i11] & 255) >> 0;
                int i18 = (((((i14 * 66) + (i15 * 129)) + (i17 * 25)) + 128) >> 8) + 16;
                int i19 = (((((i14 * -38) - (i15 * 74)) + (i17 * 112)) + 128) >> 8) + 128;
                int i20 = (((((i14 * 112) - (i15 * 94)) - (i17 * 18)) + 128) >> 8) + 128;
                int i21 = i10 + 1;
                if (i18 < 0) {
                    i18 = 0;
                } else if (i18 > 255) {
                    i18 = 255;
                }
                bArr[i10] = (byte) i18;
                if (i5 % 2 == 0 && i11 % 2 == 0) {
                    int i22 = i9 + 1;
                    if (i19 < 0) {
                        i19 = 0;
                    } else if (i19 > 255) {
                        i19 = 255;
                    }
                    bArr[i9] = (byte) i19;
                    int i23 = i8 + 1;
                    if (i20 < 0) {
                        i16 = 0;
                    } else if (i20 <= 255) {
                        i16 = i20;
                    }
                    bArr[i8] = (byte) i16;
                    i8 = i23;
                    i9 = i22;
                }
                i11++;
                i12++;
                i10 = i21;
            }
            i5++;
            i6 = i11;
            i7 = i10;
            i4 = i9;
            length = i8;
        }
    }
}
