package com.google.zxing.client.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.clipboard.ClipboardInterface;
import com.google.zxing.client.android.history.HistoryActivity;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.android.result.ResultButtonListener;
import com.google.zxing.client.android.result.ResultHandler;
import com.google.zxing.client.android.result.ResultHandlerFactory;
import com.google.zxing.client.android.result.supplement.SupplementalInfoRetriever;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Map;

public final class CaptureActivity extends Activity implements SurfaceHolder.Callback {
    private static final long BULK_MODE_SCAN_DELAY_MS = 1000;
    private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500;
    private static final Collection<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE, ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);
    private static final int HISTORY_REQUEST_CODE = 47820;
    private static final String TAG = "CaptureActivity";
    private static final String[] ZXING_URLS = {"http://zxing.appspot.com/scan", "zxing://scan/"};
    private AmbientLightManager ambientLightManager;
    private BeepManager beepManager;
    private boolean beepOnScan;
    private CameraManager cameraManager;
    private String characterSet;
    private boolean copyToClipboard;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private Button flipButton;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private HistoryManager historyManager;
    private InactivityTimer inactivityTimer;
    private Result lastResult;
    private View resultView;
    private Result savedResultToShow;
    private ScanFromWebPageManager scanFromWebPageManager;
    private IntentSource source;
    private String sourceUrl;
    private TextView statusView;
    BroadcastReceiver stopReceiver;
    private Button torchButton;
    private ViewfinderView viewfinderView;

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    /* access modifiers changed from: package-private */
    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    public Handler getHandler() {
        return this.handler;
    }

    /* access modifiers changed from: package-private */
    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.capture);
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
        this.beepManager = new BeepManager(this);
        this.ambientLightManager = new AmbientLightManager(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        this.stopReceiver = new BroadcastReceiver() {
            /* class com.google.zxing.client.android.CaptureActivity.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                CaptureActivity.this.finish();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(this.stopReceiver, new IntentFilter("barcode-scanner-stop"));
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        recreate();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        int intExtra;
        super.onResume();
        this.historyManager = new HistoryManager(this);
        this.historyManager.trimHistory();
        this.cameraManager = new CameraManager(getApplication());
        this.viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        this.viewfinderView.setCameraManager(this.cameraManager);
        this.resultView = findViewById(R.id.result_view);
        this.statusView = (TextView) findViewById(R.id.status_view);
        this.flipButton = (Button) findViewById(R.id.flip_button);
        this.torchButton = (Button) findViewById(R.id.torch_button);
        this.handler = null;
        this.lastResult = null;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String stringExtra = getIntent().getStringExtra(Intents.Scan.ORIENTATION_LOCK);
        if ("landscape".equalsIgnoreCase(stringExtra)) {
            setRequestedOrientation(0);
        } else if ("portrait".equalsIgnoreCase(stringExtra)) {
            setRequestedOrientation(1);
        }
        resetStatusView();
        this.beepManager.updatePrefs();
        this.ambientLightManager.start(this.cameraManager);
        this.inactivityTimer.onResume();
        Intent intent = getIntent();
        this.copyToClipboard = defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_COPY_TO_CLIPBOARD, true) && (intent == null || intent.getBooleanExtra(Intents.Scan.SAVE_HISTORY, true));
        this.beepOnScan = intent == null || intent.getBooleanExtra(Intents.Scan.BEEP_ON_SCAN, true);
        this.source = IntentSource.NONE;
        this.sourceUrl = null;
        this.scanFromWebPageManager = null;
        this.decodeFormats = null;
        this.characterSet = null;
        if (intent != null) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            if (Intents.Scan.ACTION.equals(action)) {
                this.source = IntentSource.NATIVE_APP_INTENT;
                this.decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                this.decodeHints = DecodeHintManager.parseDecodeHints(intent);
                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int intExtra2 = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int intExtra3 = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (intExtra2 > 0 && intExtra3 > 0) {
                        this.cameraManager.setManualFramingRect(intExtra2, intExtra3);
                    }
                }
                if (intent.hasExtra(Intents.Scan.CAMERA_ID) && (intExtra = intent.getIntExtra(Intents.Scan.CAMERA_ID, -1)) >= 0) {
                    this.cameraManager.setManualCameraId(intExtra);
                }
                if (intent.getBooleanExtra(Intents.Scan.TORCH_ON, false)) {
                    this.cameraManager.setTorchInitiallyOn(true);
                }
                String stringExtra2 = intent.getStringExtra(Intents.Scan.PROMPT_MESSAGE);
                if (stringExtra2 != null) {
                    this.statusView.setText(stringExtra2);
                }
            } else if (dataString != null && dataString.contains("http://www.google") && dataString.contains("/m/products/scan")) {
                this.source = IntentSource.PRODUCT_SEARCH_LINK;
                this.sourceUrl = dataString;
                this.decodeFormats = DecodeFormatManager.PRODUCT_FORMATS;
            } else if (isZXingURL(dataString)) {
                this.source = IntentSource.ZXING_LINK;
                this.sourceUrl = dataString;
                Uri parse = Uri.parse(dataString);
                this.scanFromWebPageManager = new ScanFromWebPageManager(parse);
                this.decodeFormats = DecodeFormatManager.parseDecodeFormats(parse);
                this.decodeHints = DecodeHintManager.parseDecodeHints(parse);
            }
            this.characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);
        }
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.preview_view)).getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
        }
    }

    private int getCurrentOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (getResources().getConfiguration().orientation != 2) {
            return (rotation == 0 || rotation == 3) ? 1 : 9;
        }
        switch (rotation) {
            case 0:
            case 1:
                return 0;
            default:
                return 8;
        }
    }

    private static boolean isZXingURL(String str) {
        if (str == null) {
            return false;
        }
        for (String str2 : ZXING_URLS) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        this.inactivityTimer.onPause();
        this.ambientLightManager.stop();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (!this.hasSurface) {
            ((SurfaceView) findViewById(R.id.preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.inactivityTimer.shutdown();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.stopReceiver);
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            if (i == 27 || i == 80) {
                return true;
            }
            switch (i) {
                case 24:
                    this.cameraManager.setTorch(true);
                    return true;
                case 25:
                    this.cameraManager.setTorch(false);
                    return true;
            }
        } else if (this.source == IntentSource.NATIVE_APP_INTENT) {
            setResult(0);
            finish();
            return true;
        } else if ((this.source == IntentSource.NONE || this.source == IntentSource.ZXING_LINK) && this.lastResult != null) {
            restartPreviewAfterDelay(0);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(524288);
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_history) {
            intent.setClassName(this, HistoryActivity.class.getName());
            startActivityForResult(intent, HISTORY_REQUEST_CODE);
            return true;
        } else if (itemId == R.id.menu_settings) {
            intent.setClassName(this, PreferencesActivity.class.getName());
            startActivity(intent);
            return true;
        } else if (itemId != R.id.menu_help) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            intent.setClassName(this, HelpActivity.class.getName());
            startActivity(intent);
            return true;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        int intExtra;
        if (i2 == -1 && i == HISTORY_REQUEST_CODE && this.historyManager != null && (intExtra = intent.getIntExtra(Intents.History.ITEM_NUMBER, -1)) >= 0) {
            decodeOrStoreSavedBitmap(null, this.historyManager.buildHistoryItem(intExtra).getResult());
        }
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
        if (this.handler == null) {
            this.savedResultToShow = result;
            return;
        }
        if (result != null) {
            this.savedResultToShow = result;
        }
        if (this.savedResultToShow != null) {
            this.handler.sendMessage(Message.obtain(this.handler, R.id.decode_succeeded, this.savedResultToShow));
        }
        this.savedResultToShow = null;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.hasSurface) {
            this.hasSurface = true;
            initCamera(surfaceHolder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }

    public void handleDecode(Result result, Bitmap bitmap, float f) {
        this.inactivityTimer.onActivity();
        this.lastResult = result;
        ResultHandler makeResultHandler = ResultHandlerFactory.makeResultHandler(this, result);
        boolean z = bitmap != null;
        if (z) {
            this.historyManager.addHistoryItem(result, makeResultHandler);
            if (this.beepOnScan) {
                this.beepManager.playBeepSoundAndVibrate();
            }
            drawResultPoints(bitmap, f, result);
        }
        switch (this.source) {
            case NATIVE_APP_INTENT:
            case PRODUCT_SEARCH_LINK:
                if (!z || !getIntent().getBooleanExtra(Intents.Scan.BULK_SCAN, false)) {
                    handleDecodeExternally(result, makeResultHandler, bitmap);
                    return;
                }
                Intent intent = new Intent("bulk-barcode-result");
                intent.putExtra(Intents.Scan.RESULT, result.toString());
                intent.putExtra(Intents.Scan.RESULT_FORMAT, result.getBarcodeFormat().toString());
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                restartPreviewAfterDelay(BULK_MODE_SCAN_DELAY_MS);
                return;
            case ZXING_LINK:
                ScanFromWebPageManager scanFromWebPageManager2 = this.scanFromWebPageManager;
                if (scanFromWebPageManager2 == null || !scanFromWebPageManager2.isScanFromWebPage()) {
                    handleDecodeInternally(result, makeResultHandler, bitmap);
                    return;
                } else {
                    handleDecodeExternally(result, makeResultHandler, bitmap);
                    return;
                }
            case NONE:
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                if (!z || !defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_BULK_MODE, false)) {
                    handleDecodeInternally(result, makeResultHandler, bitmap);
                    return;
                }
                Context applicationContext = getApplicationContext();
                Toast.makeText(applicationContext, getResources().getString(R.string.msg_bulk_mode_scanned) + " (" + result.getText() + ')', 0).show();
                restartPreviewAfterDelay(BULK_MODE_SCAN_DELAY_MS);
                return;
            default:
                return;
        }
    }

    private void drawResultPoints(Bitmap bitmap, float f, Result result) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints != null && resultPoints.length > 0) {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.result_points));
            if (resultPoints.length == 2) {
                paint.setStrokeWidth(4.0f);
                drawLine(canvas, paint, resultPoints[0], resultPoints[1], f);
            } else if (resultPoints.length == 4 && (result.getBarcodeFormat() == BarcodeFormat.UPC_A || result.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
                drawLine(canvas, paint, resultPoints[0], resultPoints[1], f);
                drawLine(canvas, paint, resultPoints[2], resultPoints[3], f);
            } else {
                paint.setStrokeWidth(10.0f);
                for (ResultPoint resultPoint : resultPoints) {
                    if (resultPoint != null) {
                        canvas.drawPoint(resultPoint.getX() * f, resultPoint.getY() * f, paint);
                    }
                }
            }
        }
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint resultPoint, ResultPoint resultPoint2, float f) {
        if (resultPoint != null && resultPoint2 != null) {
            canvas.drawLine(f * resultPoint.getX(), f * resultPoint.getY(), f * resultPoint2.getX(), f * resultPoint2.getY(), paint);
        }
    }

    private void handleDecodeInternally(Result result, ResultHandler resultHandler, Bitmap bitmap) {
        CharSequence displayContents = resultHandler.getDisplayContents();
        if (this.copyToClipboard && !resultHandler.areContentsSecure()) {
            ClipboardInterface.setText(displayContents, this);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (resultHandler.getDefaultButtonID() == null || !defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_AUTO_OPEN_WEB, false)) {
            this.statusView.setVisibility(8);
            this.viewfinderView.setVisibility(8);
            this.resultView.setVisibility(0);
            ImageView imageView = (ImageView) findViewById(R.id.barcode_image_view);
            if (bitmap == null) {
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.launcher_icon));
            } else {
                imageView.setImageBitmap(bitmap);
            }
            ((TextView) findViewById(R.id.format_text_view)).setText(result.getBarcodeFormat().toString());
            ((TextView) findViewById(R.id.type_text_view)).setText(resultHandler.getType().toString());
            ((TextView) findViewById(R.id.time_text_view)).setText(DateFormat.getDateTimeInstance(3, 3).format(new Date(result.getTimestamp())));
            TextView textView = (TextView) findViewById(R.id.meta_text_view);
            View findViewById = findViewById(R.id.meta_text_view_label);
            textView.setVisibility(8);
            findViewById.setVisibility(8);
            Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
            if (resultMetadata != null) {
                StringBuilder sb = new StringBuilder(20);
                for (Map.Entry<ResultMetadataType, Object> entry : resultMetadata.entrySet()) {
                    if (DISPLAYABLE_METADATA_TYPES.contains(entry.getKey())) {
                        sb.append(entry.getValue());
                        sb.append('\n');
                    }
                }
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                    textView.setText(sb);
                    textView.setVisibility(0);
                    findViewById.setVisibility(0);
                }
            }
            TextView textView2 = (TextView) findViewById(R.id.contents_text_view);
            textView2.setText(displayContents);
            textView2.setTextSize(2, (float) Math.max(22, 32 - (displayContents.length() / 4)));
            TextView textView3 = (TextView) findViewById(R.id.contents_supplement_text_view);
            textView3.setText("");
            textView3.setOnClickListener(null);
            if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(PreferencesActivity.KEY_SUPPLEMENTAL, true)) {
                SupplementalInfoRetriever.maybeInvokeRetrieval(textView3, resultHandler.getResult(), this.historyManager, this);
            }
            int buttonCount = resultHandler.getButtonCount();
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.result_button_view);
            viewGroup.requestFocus();
            for (int i = 0; i < 4; i++) {
                TextView textView4 = (TextView) viewGroup.getChildAt(i);
                if (i < buttonCount) {
                    textView4.setVisibility(0);
                    textView4.setText(resultHandler.getButtonText(i));
                    textView4.setOnClickListener(new ResultButtonListener(resultHandler, i));
                } else {
                    textView4.setVisibility(8);
                }
            }
            return;
        }
        resultHandler.handleButtonPress(resultHandler.getDefaultButtonID().intValue());
    }

    private void handleDecodeExternally(Result result, ResultHandler resultHandler, Bitmap bitmap) {
        ScanFromWebPageManager scanFromWebPageManager2;
        String stringExtra;
        if (bitmap != null) {
            this.viewfinderView.drawResultBitmap(bitmap);
        }
        long j = DEFAULT_INTENT_RESULT_DURATION_MS;
        if (!(getIntent() == null || !getIntent().hasExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS) || (stringExtra = getIntent().getStringExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS)) == null)) {
            try {
                j = Long.parseLong(stringExtra);
            } catch (NumberFormatException e) {
                Log.e(TAG, "Could not parse " + stringExtra + " to Long", e);
            }
        }
        int i = 0;
        if (j > 0) {
            String valueOf = String.valueOf(result);
            if (valueOf.length() > 32) {
                valueOf = valueOf.substring(0, 32) + " ...";
            }
            this.statusView.setText(getString(resultHandler.getDisplayTitle()) + " : " + valueOf);
        }
        if (this.copyToClipboard && !resultHandler.areContentsSecure()) {
            ClipboardInterface.setText(resultHandler.getDisplayContents(), this);
        }
        if (this.source == IntentSource.NATIVE_APP_INTENT) {
            Intent intent = new Intent(getIntent().getAction());
            intent.addFlags(524288);
            intent.putExtra(Intents.Scan.RESULT, result.toString());
            intent.putExtra(Intents.Scan.RESULT_FORMAT, result.getBarcodeFormat().toString());
            byte[] rawBytes = result.getRawBytes();
            if (rawBytes != null && rawBytes.length > 0) {
                intent.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
            }
            Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
            if (resultMetadata != null) {
                if (resultMetadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                    intent.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION, resultMetadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
                }
                Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
                if (number != null) {
                    intent.putExtra(Intents.Scan.RESULT_ORIENTATION, number.intValue());
                }
                String str = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
                if (str != null) {
                    intent.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, str);
                }
                Iterable<byte[]> iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
                if (iterable != null) {
                    for (byte[] bArr : iterable) {
                        intent.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, bArr);
                        i++;
                    }
                }
            }
            sendReplyMessage(R.id.return_scan_result, intent, j);
        } else if (this.source == IntentSource.PRODUCT_SEARCH_LINK) {
            int lastIndexOf = this.sourceUrl.lastIndexOf("/scan");
            sendReplyMessage(R.id.launch_product_query, this.sourceUrl.substring(0, lastIndexOf) + "?q=" + ((Object) resultHandler.getDisplayContents()) + "&source=zxing", j);
        } else if (this.source == IntentSource.ZXING_LINK && (scanFromWebPageManager2 = this.scanFromWebPageManager) != null && scanFromWebPageManager2.isScanFromWebPage()) {
            Object buildReplyURL = this.scanFromWebPageManager.buildReplyURL(result, resultHandler);
            this.scanFromWebPageManager = null;
            sendReplyMessage(R.id.launch_product_query, buildReplyURL, j);
        }
    }

    private void sendReplyMessage(int i, Object obj, long j) {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            Message obtain = Message.obtain(captureActivityHandler, i, obj);
            if (j > 0) {
                this.handler.sendMessageDelayed(obtain, j);
            } else {
                this.handler.sendMessage(obtain);
            }
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                this.cameraManager.openDriver(surfaceHolder);
                if (this.handler == null) {
                    this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.decodeHints, this.characterSet, this.cameraManager);
                }
                decodeOrStoreSavedBitmap(null, null);
            } catch (IOException e) {
                Log.w(TAG, e);
                displayFrameworkBugMessageAndExit();
            } catch (RuntimeException e2) {
                Log.w(TAG, "Unexpected error initializing camera", e2);
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void restartPreviewAfterDelay(long j) {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.sendEmptyMessageDelayed(R.id.restart_preview, j);
        }
        resetStatusView();
    }

    private void resetStatusView() {
        this.resultView.setVisibility(8);
        this.statusView.setText(R.string.msg_default_status);
        this.statusView.setVisibility(0);
        this.viewfinderView.setVisibility(0);
        this.lastResult = null;
        if (getIntent().getBooleanExtra(Intents.Scan.SHOW_FLIP_CAMERA_BUTTON, false) && Camera.getNumberOfCameras() > 1) {
            this.flipButton.setVisibility(0);
            this.flipButton.setOnClickListener(new View.OnClickListener() {
                /* class com.google.zxing.client.android.CaptureActivity.AnonymousClass2 */

                public void onClick(View view) {
                    CaptureActivity.this.getIntent().putExtra(Intents.Scan.CAMERA_ID, CaptureActivity.this.getIntent().getIntExtra(Intents.Scan.CAMERA_ID, -1) == 1 ? 0 : 1);
                    CaptureActivity.this.getIntent().putExtra(Intents.Scan.SHOW_FLIP_CAMERA_BUTTON, true);
                    CaptureActivity.this.recreate();
                }
            });
        }
        if (getIntent().getBooleanExtra(Intents.Scan.SHOW_TORCH_BUTTON, false) && getIntent().getIntExtra(Intents.Scan.CAMERA_ID, -1) != 1) {
            for (FeatureInfo featureInfo : getPackageManager().getSystemAvailableFeatures()) {
                if ("android.hardware.camera.flash".equalsIgnoreCase(featureInfo.name)) {
                    this.torchButton.setVisibility(0);
                    this.torchButton.setOnClickListener(new View.OnClickListener() {
                        /* class com.google.zxing.client.android.CaptureActivity.AnonymousClass3 */

                        public void onClick(View view) {
                            CaptureActivity.this.cameraManager.setTorch(!CaptureActivity.this.cameraManager.isTorchOn());
                        }
                    });
                    return;
                }
            }
        }
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }
}
