package com.google.zxing.client.android.result;

import android.app.Activity;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.ParsedResult;

public final class EmailAddressResultHandler extends ResultHandler {
    private static final int[] buttons = {R.string.button_email, R.string.button_add_contact};

    public EmailAddressResultHandler(Activity activity, ParsedResult parsedResult) {
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
        EmailAddressParsedResult emailAddressParsedResult = (EmailAddressParsedResult) getResult();
        switch (i) {
            case 0:
                sendEmail(emailAddressParsedResult.getTos(), emailAddressParsedResult.getCCs(), emailAddressParsedResult.getBCCs(), emailAddressParsedResult.getSubject(), emailAddressParsedResult.getBody());
                return;
            case 1:
                addEmailOnlyContact(emailAddressParsedResult.getTos(), null);
                return;
            default:
                return;
        }
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return R.string.result_email_address;
    }
}
