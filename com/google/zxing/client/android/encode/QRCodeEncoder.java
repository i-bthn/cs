package com.google.zxing.client.android.encode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import androidx.core.content.IntentCompat;
import androidx.core.net.MailTo;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.bumptech.glide.load.Key;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

final class QRCodeEncoder {
    private static final int BLACK = -16777216;
    private static final String TAG = "QRCodeEncoder";
    private static final int WHITE = -1;
    private final Context activity;
    private String contents;
    private final int dimension;
    private String displayContents;
    private BarcodeFormat format;
    private String title;
    private final boolean useVCard;

    QRCodeEncoder(Context context, Intent intent, int i, boolean z) throws WriterException {
        this.activity = context;
        this.dimension = i;
        this.useVCard = z;
        String action = intent.getAction();
        if (Intents.Encode.ACTION.equals(action)) {
            encodeContentsFromZXingIntent(intent);
        } else if ("android.intent.action.SEND".equals(action)) {
            encodeContentsFromShareIntent(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public String getContents() {
        return this.contents;
    }

    /* access modifiers changed from: package-private */
    public String getDisplayContents() {
        return this.displayContents;
    }

    /* access modifiers changed from: package-private */
    public String getTitle() {
        return this.title;
    }

    /* access modifiers changed from: package-private */
    public boolean isUseVCard() {
        return this.useVCard;
    }

    private void encodeContentsFromZXingIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(Intents.Encode.FORMAT);
        this.format = null;
        if (stringExtra != null) {
            try {
                this.format = BarcodeFormat.valueOf(stringExtra);
            } catch (IllegalArgumentException unused) {
            }
        }
        BarcodeFormat barcodeFormat = this.format;
        if (barcodeFormat == null || barcodeFormat == BarcodeFormat.QR_CODE) {
            String stringExtra2 = intent.getStringExtra(Intents.Encode.TYPE);
            if (stringExtra2 != null && !stringExtra2.isEmpty()) {
                this.format = BarcodeFormat.QR_CODE;
                encodeQRCodeContents(intent, stringExtra2);
                return;
            }
            return;
        }
        String stringExtra3 = intent.getStringExtra(Intents.Encode.DATA);
        if (stringExtra3 != null && !stringExtra3.isEmpty()) {
            this.contents = stringExtra3;
            this.displayContents = stringExtra3;
            this.title = this.activity.getString(R.string.contents_text);
        }
    }

    private void encodeContentsFromShareIntent(Intent intent) throws WriterException {
        if (intent.hasExtra("android.intent.extra.STREAM")) {
            encodeFromStreamExtra(intent);
        } else {
            encodeFromTextExtras(intent);
        }
    }

    private void encodeFromTextExtras(Intent intent) throws WriterException {
        String trim = ContactEncoder.trim(intent.getStringExtra("android.intent.extra.TEXT"));
        if (trim == null && (trim = ContactEncoder.trim(intent.getStringExtra(IntentCompat.EXTRA_HTML_TEXT))) == null && (trim = ContactEncoder.trim(intent.getStringExtra("android.intent.extra.SUBJECT"))) == null) {
            String[] stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.EMAIL");
            trim = stringArrayExtra != null ? ContactEncoder.trim(stringArrayExtra[0]) : "?";
        }
        if (trim == null || trim.isEmpty()) {
            throw new WriterException("Empty EXTRA_TEXT");
        }
        this.contents = trim;
        this.format = BarcodeFormat.QR_CODE;
        if (intent.hasExtra("android.intent.extra.SUBJECT")) {
            this.displayContents = intent.getStringExtra("android.intent.extra.SUBJECT");
        } else if (intent.hasExtra("android.intent.extra.TITLE")) {
            this.displayContents = intent.getStringExtra("android.intent.extra.TITLE");
        } else {
            this.displayContents = this.contents;
        }
        this.title = this.activity.getString(R.string.contents_text);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ab A[SYNTHETIC, Splitter:B:41:0x00ab] */
    private void encodeFromStreamExtra(Intent intent) throws WriterException {
        Throwable th;
        IOException e;
        this.format = BarcodeFormat.QR_CODE;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Uri uri = (Uri) extras.getParcelable("android.intent.extra.STREAM");
            if (uri != null) {
                InputStream inputStream = null;
                try {
                    InputStream inputStream2 = this.activity.getContentResolver().openInputStream(uri);
                    if (inputStream2 != null) {
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr = new byte[2048];
                            while (true) {
                                int read = inputStream2.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            String str = new String(byteArray, 0, byteArray.length, Key.STRING_CHARSET_NAME);
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException unused) {
                                }
                            }
                            Log.d(TAG, "Encoding share intent content:");
                            Log.d(TAG, str);
                            ParsedResult parseResult = ResultParser.parseResult(new Result(str, byteArray, null, BarcodeFormat.QR_CODE));
                            if (parseResult instanceof AddressBookParsedResult) {
                                encodeQRCodeContents((AddressBookParsedResult) parseResult);
                                String str2 = this.contents;
                                if (str2 == null || str2.isEmpty()) {
                                    throw new WriterException("No content to encode");
                                }
                                return;
                            }
                            throw new WriterException("Result was not an address");
                        } catch (IOException e2) {
                            e = e2;
                            inputStream = inputStream2;
                            try {
                                throw new WriterException(e);
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream2 = inputStream;
                                if (inputStream2 != null) {
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException unused2) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        throw new WriterException("Can't open stream for " + uri);
                    }
                } catch (IOException e3) {
                    e = e3;
                    throw new WriterException(e);
                }
            } else {
                throw new WriterException("No EXTRA_STREAM");
            }
        } else {
            throw new WriterException("No extras");
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private void encodeQRCodeContents(Intent intent, String str) {
        char c;
        List<String> list;
        switch (str.hashCode()) {
            case -1309271157:
                if (str.equals(Contents.Type.PHONE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -670199783:
                if (str.equals(Contents.Type.CONTACT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 709220992:
                if (str.equals(Contents.Type.SMS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1349204356:
                if (str.equals(Contents.Type.LOCATION)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1778595596:
                if (str.equals(Contents.Type.TEXT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1833351709:
                if (str.equals(Contents.Type.EMAIL)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                String stringExtra = intent.getStringExtra(Intents.Encode.DATA);
                if (stringExtra != null && !stringExtra.isEmpty()) {
                    this.contents = stringExtra;
                    this.displayContents = stringExtra;
                    this.title = this.activity.getString(R.string.contents_text);
                    return;
                }
                return;
            case 1:
                String trim = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
                if (trim != null) {
                    this.contents = MailTo.MAILTO_SCHEME + trim;
                    this.displayContents = trim;
                    this.title = this.activity.getString(R.string.contents_email);
                    return;
                }
                return;
            case 2:
                String trim2 = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
                if (trim2 != null) {
                    this.contents = "tel:" + trim2;
                    this.displayContents = PhoneNumberUtils.formatNumber(trim2);
                    this.title = this.activity.getString(R.string.contents_phone);
                    return;
                }
                return;
            case 3:
                String trim3 = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
                if (trim3 != null) {
                    this.contents = "sms:" + trim3;
                    this.displayContents = PhoneNumberUtils.formatNumber(trim3);
                    this.title = this.activity.getString(R.string.contents_sms);
                    return;
                }
                return;
            case 4:
                Bundle bundleExtra = intent.getBundleExtra(Intents.Encode.DATA);
                if (bundleExtra != null) {
                    String string = bundleExtra.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                    String string2 = bundleExtra.getString("company");
                    String string3 = bundleExtra.getString("postal");
                    List<String> allBundleValues = getAllBundleValues(bundleExtra, Contents.PHONE_KEYS);
                    List<String> allBundleValues2 = getAllBundleValues(bundleExtra, Contents.PHONE_TYPE_KEYS);
                    List<String> allBundleValues3 = getAllBundleValues(bundleExtra, Contents.EMAIL_KEYS);
                    String string4 = bundleExtra.getString(Contents.URL_KEY);
                    if (string4 == null) {
                        list = null;
                    } else {
                        list = Collections.singletonList(string4);
                    }
                    String[] encode = (this.useVCard ? new VCardContactEncoder() : new MECARDContactEncoder()).encode(Collections.singletonList(string), string2, Collections.singletonList(string3), allBundleValues, allBundleValues2, allBundleValues3, list, bundleExtra.getString(Contents.NOTE_KEY));
                    if (!encode[1].isEmpty()) {
                        this.contents = encode[0];
                        this.displayContents = encode[1];
                        this.title = this.activity.getString(R.string.contents_contact);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                Bundle bundleExtra2 = intent.getBundleExtra(Intents.Encode.DATA);
                if (bundleExtra2 != null) {
                    float f = bundleExtra2.getFloat("LAT", Float.MAX_VALUE);
                    float f2 = bundleExtra2.getFloat("LONG", Float.MAX_VALUE);
                    if (f != Float.MAX_VALUE && f2 != Float.MAX_VALUE) {
                        this.contents = "geo:" + f + ',' + f2;
                        StringBuilder sb = new StringBuilder();
                        sb.append(f);
                        sb.append(",");
                        sb.append(f2);
                        this.displayContents = sb.toString();
                        this.title = this.activity.getString(R.string.contents_location);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    private static List<String> getAllBundleValues(Bundle bundle, String[] strArr) {
        String str;
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str2 : strArr) {
            Object obj = bundle.get(str2);
            if (obj == null) {
                str = null;
            } else {
                str = obj.toString();
            }
            arrayList.add(str);
        }
        return arrayList;
    }

    private void encodeQRCodeContents(AddressBookParsedResult addressBookParsedResult) {
        String[] encode = (this.useVCard ? new VCardContactEncoder() : new MECARDContactEncoder()).encode(toList(addressBookParsedResult.getNames()), addressBookParsedResult.getOrg(), toList(addressBookParsedResult.getAddresses()), toList(addressBookParsedResult.getPhoneNumbers()), null, toList(addressBookParsedResult.getEmails()), toList(addressBookParsedResult.getURLs()), null);
        if (!encode[1].isEmpty()) {
            this.contents = encode[0];
            this.displayContents = encode[1];
            this.title = this.activity.getString(R.string.contents_contact);
        }
    }

    private static List<String> toList(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return Arrays.asList(strArr);
    }

    /* access modifiers changed from: package-private */
    public Bitmap encodeAsBitmap() throws WriterException {
        EnumMap enumMap;
        String str = this.contents;
        if (str == null) {
            return null;
        }
        String guessAppropriateEncoding = guessAppropriateEncoding(str);
        if (guessAppropriateEncoding != null) {
            EnumMap enumMap2 = new EnumMap(EncodeHintType.class);
            enumMap2.put((Object) EncodeHintType.CHARACTER_SET, (Object) guessAppropriateEncoding);
            enumMap = enumMap2;
        } else {
            enumMap = null;
        }
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, this.format, this.dimension, this.dimension, enumMap);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[(width * height)];
            for (int i = 0; i < height; i++) {
                int i2 = i * width;
                for (int i3 = 0; i3 < width; i3++) {
                    iArr[i2 + i3] = encode.get(i3, i) ? -16777216 : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static String guessAppropriateEncoding(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) > 255) {
                return Key.STRING_CHARSET_NAME;
            }
        }
        return null;
    }
}
