package com.google.zxing.client.android.result;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;

public final class ResultHandlerFactory {
    private ResultHandlerFactory() {
    }

    public static ResultHandler makeResultHandler(CaptureActivity captureActivity, Result result) {
        ParsedResult parseResult = parseResult(result);
        switch (parseResult.getType()) {
            case ADDRESSBOOK:
                return new AddressBookResultHandler(captureActivity, parseResult);
            case EMAIL_ADDRESS:
                return new EmailAddressResultHandler(captureActivity, parseResult);
            case PRODUCT:
                return new ProductResultHandler(captureActivity, parseResult, result);
            case URI:
                return new URIResultHandler(captureActivity, parseResult);
            case WIFI:
                return new WifiResultHandler(captureActivity, parseResult);
            case GEO:
                return new GeoResultHandler(captureActivity, parseResult);
            case TEL:
                return new TelResultHandler(captureActivity, parseResult);
            case SMS:
                return new SMSResultHandler(captureActivity, parseResult);
            case CALENDAR:
                return new CalendarResultHandler(captureActivity, parseResult);
            case ISBN:
                return new ISBNResultHandler(captureActivity, parseResult, result);
            default:
                return new TextResultHandler(captureActivity, parseResult, result);
        }
    }

    private static ParsedResult parseResult(Result result) {
        return ResultParser.parseResult(result);
    }
}
