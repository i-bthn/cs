package com.google.zxing.client.android.result;

import android.app.Activity;
import barcodescanner.xservices.nl.barcodescanner.R;
import com.google.zxing.client.result.GeoParsedResult;
import com.google.zxing.client.result.ParsedResult;

public final class GeoResultHandler extends ResultHandler {
    private static final int[] buttons = {R.string.button_show_map, R.string.button_get_directions};

    public GeoResultHandler(Activity activity, ParsedResult parsedResult) {
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
        GeoParsedResult geoParsedResult = (GeoParsedResult) getResult();
        switch (i) {
            case 0:
                openMap(geoParsedResult.getGeoURI());
                return;
            case 1:
                getDirections(geoParsedResult.getLatitude(), geoParsedResult.getLongitude());
                return;
            default:
                return;
        }
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return R.string.result_geo;
    }
}
