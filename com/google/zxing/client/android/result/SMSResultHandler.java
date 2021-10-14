package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

public final class SMSResultHandler extends ResultHandler {
    private static final int[] buttons = {R.string.button_sms, R.string.button_mms};

    public SMSResultHandler(Activity activity, ParsedResult parsedResult) {
        super(activity, parsedResult);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return buttons.length;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i) {
        return buttons[i];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i) {
        SMSParsedResult sMSParsedResult = (SMSParsedResult) getResult();
        String str = sMSParsedResult.getNumbers()[0];
        switch (i) {
            case 0:
                sendSMS(str, sMSParsedResult.getBody());
                return;
            case 1:
                sendMMS(str, sMSParsedResult.getSubject(), sMSParsedResult.getBody());
                return;
            default:
                return;
        }
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public CharSequence getDisplayContents() {
        SMSParsedResult sMSParsedResult = (SMSParsedResult) getResult();
        String[] numbers = sMSParsedResult.getNumbers();
        String[] strArr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strArr[i] = PhoneNumberUtils.formatNumber(numbers[i]);
        }
        StringBuilder sb = new StringBuilder(50);
        ParsedResult.maybeAppend(strArr, sb);
        ParsedResult.maybeAppend(sMSParsedResult.getSubject(), sb);
        ParsedResult.maybeAppend(sMSParsedResult.getBody(), sb);
        return sb.toString();
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return R.string.result_sms;
    }
}
